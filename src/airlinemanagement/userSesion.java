/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airlinemanagement; 
/**
 *
 * @author ADMIN88
 */
public class userSesion {
    private static userSesion instance;
    private currentUser user;

    private userSesion() {}; // Private constructor

    public static userSesion getInstance() {
        if (instance == null) {
            instance = new userSesion();
        }
        return instance;
    }

    public void setUser(currentUser user) { this.user = user; }
    public currentUser getUser() { return user; }
}
