/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airlinemanagement;
import  java.net.*;
import java.io.*;
/**
 *
 * @author ADMIN88
 */
public class socketClient {

    private static BufferedReader in;
    private static PrintWriter out;
    private static ServerListener listener;

    public static void connect() throws Exception {
        Socket socket = new Socket("0.tcp.ap.ngrok.io", 12504);
        //Socket socket = new Socket("localhost", 1234);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        startListening();
    }

    public static BufferedReader getIn() { return in; }
    public static PrintWriter getOut() { return out; }

    public static void setListener(ServerListener l) {
        listener = l;
    }

    private static void startListening() {
        new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null) {
                    if (listener != null) {
                        listener.onMessage(msg);
                    }
                }
            } catch (Exception e) {
                System.out.println("Server disconnected");
            }
        }).start();
    }

    public static void send(String msg) {
        out.println(msg);
    }
}
