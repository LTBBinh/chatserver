/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.Account;

/**
 *
 * @author AM
 */
public class ServerDAO {
    private static Connection con;
    private static String URL ;
    private static String USER;
    private static String PASSWORD;
    private static Statement statement;
    private static ResultSet result;

    public ServerDAO() {
        getConnection();
        try {
            statement = con.createStatement();
        } catch (SQLException ex) {
        }
    }
    
    public static void getConnection() {
        con = null;
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(new File("info.properties")));
            URL = properties.getProperty("url");
            USER = properties.getProperty("user");
            PASSWORD = properties.getProperty("password");
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = (Connection) DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (IOException | SQLException ex) {
            
        }
    }
    
    public static void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void runSQL(String sql) {
         try {
            result = statement.executeQuery(sql); 
        } catch (SQLException ex) {
        }
    }
    public static boolean isValidAccount (Account a) {
        String sql = "SELECT * FROM user WHERE username ='"
                     +a.getUserName()
                     +"' AND password ='"+a.getPassWord()+"'";
                     runSQL(sql);
        try {
            if(result.next()) return true;
        } catch (SQLException ex) {
        }
        return false;
    }
    /*public static void main(String[] args) {
        ServerDAO a = new ServerDAO();
        if(a.isValidAccount(new Account("smigence", "blu60810"))) System.out.println("OK");
        else System.out.println("WRONG");
    }*/
}
