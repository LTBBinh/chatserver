/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author AM
 */
public class Message implements Serializable{
    private int type;
    private ArrayList data;

    public Message() {
        data = new ArrayList();
        type = 0;
    }

    public Message(int type) {
        this.type = type;
        data = new ArrayList();
    }

    public Message(int type, ArrayList data) {
        this.type = type;
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public ArrayList getData() {
        return data;
    }

    public void setData(Object data) {
        this.data.add(data);
    }
    
}
