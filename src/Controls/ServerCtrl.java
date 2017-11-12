/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.Account;
import Models.Message;

/**
 *
 * @author AM
 */
public class ServerCtrl {
    private ServerDAO db;
    private final int serverPort = 4804;
    private ListenServer listenServer;
    public ServerCtrl () {
        listenServer = new ListenServer(serverPort);
        db = new ServerDAO();
        creatGroupClient();
    }
    
    
    public Message checkLogin(ArrayList data) {
        Message response = null;
        if(db.isValidAccount((Account)data.get(0))) {
            response = new Message(1);
            response.setData("OK");
        }
        else {
            response = new Message(1);
            response.setData("FAILED");
        }
        return response;
    }
    
    public Message creatResponse(Message request) {
        Message response=null;
        switch (request.getType()) {
            case 1: response = checkLogin(request.getData());
                    break;
        }
        return response;
    }
    
        private void creatGroupClient() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<Socket> list = new ArrayList<Socket>();
                while (true) {
                    final Socket socket = listenServer.accept();
                    list.add(socket);
                    
                    new Thread (new Runnable() {
                        @Override
                        public void run() {
                            while (true) {
                                Message request = listenServer.receiveMessage(socket);
                                if (request==null) continue;
                                for(Socket soc : list) {
                                    if(soc==null) {
                                        list.remove(soc);
                                        continue;
                                    }
                                    if(soc.equals(socket)) {
                                        Message response = creatResponse(request);
                                        listenServer.sendMessage(response, socket);
                                    }
                                }
                            }
                        }
                    }).start();
                }
            }
        });
        thread.start();
    }
}
