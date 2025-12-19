/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcpserver;

import java.sql.Connection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author ADMIN88
 */
public class ClientHandler extends Thread {

    Socket socket;
    BufferedReader in;
    PrintWriter out;
    String username;
    Connection conn = null;

    public ClientHandler(Socket socket) throws Exception {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        conn = DBConnection.getConnection();
    }

    public void run() {
        try {
            String line;
            while ((line = in.readLine()) != null) {

                if (line.startsWith("LOGIN:")) {
                    username = line.substring(6);
                    ChatServer.onlineUsers.put(username, this);
                    out.println("LOGIN_OK");
                }
                if (line.startsWith("GO_CHAT")) {
                    sendConversationList();
                }
                if (line.startsWith("LOAD_CHAT:")) {
                    String partner = line.substring(10); // LOAD_CHAT:
                    sendChatHistory(username, partner);
                }

                if (line.startsWith("CHAT:")) {
                    System.out.println("message vua gui " + line);
                    // CHAT:Nam:Hello
                    String[] p = line.split(":", 4);
                    if (p.length < 4) {
                        return;
                    }
                    String sender = p[1];
                    String receiver = p[2];
                    String content = p[3];

                    int convId = getOrCreateConversation(sender, receiver);
                    saveMessage(convId, sender, content);

                    ClientHandler target = ChatServer.onlineUsers.get(receiver);
                    if (target != null) {
                        target.out.println("MSG:" + sender + ":" + receiver + ":" + content);
                        target.out.println("UPDATE_LIST:" + sender + ":" + content);
                    } else {
                        System.out.println("Người nhận " + receiver + " không online");
                    }
                    this.out.println("UPDATE_LIST:" + receiver + ":" + content);
                    this.out.println("MSG:" + sender + ":" + receiver + ":" + content);
                    //this.out.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void sendChatHistory(String u1, String u2) throws Exception {
        int id1 = getUserId(u1);
        int id2 = getUserId(u2);

        int a = Math.min(id1, id2);
        int b = Math.max(id1, id2);

        String sql
                = "SELECT u.username, m.content "
                + "FROM messages m "
                + "JOIN conversations c ON m.conversation_id = c.id "
                + "JOIN users u ON u.id = m.sender_id "
                + "WHERE c.user1_id=? AND c.user2_id=? "
                + "ORDER BY m.id";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, a);
        ps.setInt(2, b);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String sender = rs.getString(1);
            String content = rs.getString(2);
            out.println("HISTORY:" + sender + ":" + content);
        }

        // báo client biết đã load xong
        out.println("HISTORY_END");
    }

    void sendConversationList() throws Exception {
        int myId = getUserId(username);

        // Lấy tất cả hội thoại mà tôi tham gia, kèm theo tên của đối phương
        String query = "SELECT c.id, c.last_message, u.username "
                + "FROM conversations c "
                + "JOIN users u ON (u.id = c.user1_id OR u.id = c.user2_id) "
                + "WHERE (c.user1_id = ? OR c.user2_id = ?) AND u.id != ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, myId);
        ps.setInt(2, myId);
        ps.setInt(3, myId);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String partnerName = rs.getString("username");
            String lastMsg = rs.getString("last_message");
            if (lastMsg == null) {
                lastMsg = "";
            }

            // Gửi về Client để vẽ lên panelConversation
            out.println("UPDATE_LIST:" + partnerName + ":" + lastMsg);
        }
    }

    int getUserId(String name) throws Exception {
        PreparedStatement ps = conn.prepareStatement(
                "SELECT id FROM users WHERE username=?"
        );
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        throw new RuntimeException("User not found");
    }

    int getOrCreateConversation(String u1, String u2) throws Exception {
        int id1 = getUserId(u1);
        int id2 = getUserId(u2);

        int a = Math.min(id1, id2);
        int b = Math.max(id1, id2);

        PreparedStatement ps = conn.prepareStatement(
                "SELECT id FROM conversations WHERE user1_id=? AND user2_id=?"
        );
        ps.setInt(1, a);
        ps.setInt(2, b);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }

        ps = conn.prepareStatement(
                "INSERT INTO conversations(user1_id,user2_id, last_message) VALUES (?,?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        ps.setInt(1, a);
        ps.setInt(2, b);
        ps.setString(3, "test");
        ps.executeUpdate();
        rs = ps.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    void saveMessage(int convId, String sender, String content) throws Exception {
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO messages(conversation_id,sender_id,content) VALUES (?,?,?)"
        );
        ps.setInt(1, convId);
        ps.setInt(2, getUserId(sender));
        ps.setString(3, content);
        ps.executeUpdate();

        PreparedStatement psConv = conn.prepareStatement(
                "UPDATE conversations SET last_message = ? WHERE id = ?"
        );
        psConv.setString(1, content);
        psConv.setInt(2, convId);
        psConv.executeUpdate();
    }
}
