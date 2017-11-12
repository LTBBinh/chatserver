/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author AM
 */
public class User {
    private String name;
    private String birthday;
    private Account account;
    private String email;
    public User() {
    }

    public User(String name, String birthday, Account account, String email) {
        this.name = name;
        this.birthday = birthday;
        this.account = account;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public Account getAccount() {
        return account;
    }

    public String getEmail() {
        return email;
    }
    
}
