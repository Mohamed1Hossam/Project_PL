/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tms;

/**
 *
 * @author Mina ESSAM
 */
public interface LoginManager {
    boolean loginUser(String username, String password);
    void logoutUser();
    boolean checkLogin();
}

