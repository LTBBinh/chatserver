/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import Models.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author AM
 */
public class ListenServer {
    private ServerSocket serverSocket;
    
    public ListenServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            int localPort = serverSocket.getLocalPort();
            System.out.println("Hệ thống đã lắng nghe kết nối từ cổng " +localPort);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public Socket accept () {
        try {
            return serverSocket.accept();
        } catch (IOException ex) {
            System.out.println("accept");        
        }
        return null;
    }
    
    public Message receiveMessage(Socket socket) {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            try {
                Message mess = (Message) in.readObject();
                
                return mess;
            } catch (ClassNotFoundException ex) {
            }
        } catch (IOException ex) {
        }
        return null;
    }
    
    public void sendMessage(Message mess, Socket socket) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(mess);
        } catch (IOException ex) {
        }
    }
    

    
}
