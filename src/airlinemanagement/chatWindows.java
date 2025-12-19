/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package airlinemanagement;

import java.sql.*;

/**
 *
 * @author ADMIN88
 */
public class chatWindows extends javax.swing.JFrame implements ServerListener {

    /**
     * Creates new form chatWindows2
     */
    private String curentReciver = "";
    private Connection conn = null;
    private javax.swing.JPopupMenu searchPopup;
    private javax.swing.JList<String> listResults;
    private javax.swing.DefaultListModel<String> listModel;

    public chatWindows() {
        initComponents();
        jPanelConversation.setLayout(
                new javax.swing.BoxLayout(
                        jPanelConversation,
                        javax.swing.BoxLayout.Y_AXIS
                )
        );
        // Khởi tạo List và Model
        listModel = new javax.swing.DefaultListModel<>();
        listResults = new javax.swing.JList<>(listModel);
        boolean isAdmin = userSesion.getInstance().getUser().getRole() == 1;
        txtSearch.setVisible(isAdmin);
        jButton2.setVisible(isAdmin);


// Đưa List vào ScrollPane và Popup
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(listResults);
        scrollPane.setPreferredSize(new java.awt.Dimension(200, 100));

        searchPopup = new javax.swing.JPopupMenu();
        searchPopup.add(scrollPane);
        searchPopup.setFocusable(false); // Quan trọng để không mất focus ô nhập

// Sự kiện khi click chọn 1 người từ danh sách kết quả
        listResults.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String selected = listResults.getSelectedValue();
                if (selected != null) {
                    curentReciver = selected; // Gán người nhận
                    txtSearch.setText("");    // Xóa ô tìm kiếm
                    searchPopup.setVisible(false);
                    socketClient.send("LOAD_CHAT:" + curentReciver);
                    ChatTextArea.setText("--- Bắt đầu chat với: " + selected + " ---\n");
                    ChatTextField.requestFocus();
                }
            }
        });
        socketClient.setListener(this);
        socketClient.send("GO_CHAT");
    }

    private void searchInDatabase(String keyword) {
        listModel.clear();
        try {
            String myName = userSesion.getInstance().getUser().getUserName();
            // Truy vấn tìm các user khác có tên giống từ khóa
            String sql = "SELECT username FROM users WHERE username LIKE ? AND username != ?";
            conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, myName);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listModel.addElement(rs.getString("username"));
            }

            if (!listModel.isEmpty()) {
                // Hiển thị danh sách ngay dưới ô txtSearch
                searchPopup.show(txtSearch, 0, txtSearch.getHeight());
            } else {
                searchPopup.setVisible(false);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateConversationUI(String username, String message) {
        javax.swing.SwingUtilities.invokeLater(() -> {

            ChatItem found = null;

            for (java.awt.Component c : jPanelConversation.getComponents()) {
                if (c instanceof ChatItem item) {
                    if (item.getUserName().equals(username)) {
                        found = item;
                        break;
                    }
                }
            }

            if (found != null) {
                found.setInfo(username, message);
                jPanelConversation.remove(found);
                jPanelConversation.add(found, 0);
            } else {
                ChatItem item = new ChatItem(username, message);
                item.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        curentReciver = username;
                        System.out.println("Dang chat voi " + curentReciver);
                        ChatTextField.setText("");
                        ChatTextArea.setText("");
                        socketClient.send("LOAD_CHAT:" + curentReciver);
                        ChatTextField.requestFocus();
                    }
                });
                jPanelConversation.add(item, 0);
            }

            jPanelConversation.revalidate();
            jPanelConversation.repaint();
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void sendMessage() {
        String content = ChatTextField.getText().trim();
        if (content.isEmpty()) {
            return;
        }
        if (curentReciver == null || curentReciver.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Vui lòng chọn người để chat",
                    "Chưa chọn người nhận",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        currentUser user = userSesion.getInstance().getUser();
        String sender = user.getUserName();
        int senderRole = user.getRole();
        System.out.println("DEBUG SENDER GỬI ĐI: [" + sender + "]"); // Ki
        if (sender != null) {
            if (senderRole != 1) {
                socketClient.send("CHAT:" + sender + ":admin2:" + content);
            } else {
                socketClient.send("CHAT:" + sender + ":" + curentReciver + ":" + content);
            }
        } else {
            System.out.println("loi roi ");
        }
        //ChatTextArea.append("Me: " + content + "\n");
        ChatTextField.setText("");
    }

    /* ================== SOCKET ================== */
    @Override
    public void onMessage(String msg) {

        String trimmed = msg.trim();
        System.out.println("Nhan: " + trimmed);

        if (trimmed.startsWith("MSG:")) {
            String[] p = trimmed.split(":", 4);
            if (p.length == 4) {
                String sender = p[1];
                String receiver = p[2];
                String content = p[3];

                // Chỉ hiển thị nếu đúng cuộc chat đang mở
                if (sender.equals(curentReciver) || receiver.equals(curentReciver)) {
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        ChatTextArea.append(sender + ": " + content + "\n");
                    });
                }
            }
        }
        if (trimmed.startsWith("HISTORY:")) {
            String[] p = trimmed.split(":", 3);
            if (p.length == 3) {
                String sender = p[1];
                String content = p[2];

                javax.swing.SwingUtilities.invokeLater(() -> {
                    ChatTextArea.append(sender + ": " + content + "\n");
                });
            }
        }

        if (trimmed.startsWith("UPDATE_LIST:")) {
            String[] p = trimmed.split(":", 3);
            if (p.length == 3) {
                updateConversationUI(p[1], p[2]);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ChatTextArea = new javax.swing.JTextArea();
        ChatTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanelConversation = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ChatTextArea.setColumns(20);
        ChatTextArea.setRows(5);
        jScrollPane1.setViewportView(ChatTextArea);

        ChatTextField.setText("jTextField1");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setOpaque(false);

        jPanelConversation.setLayout(new javax.swing.BoxLayout(jPanelConversation, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane2.setViewportView(jPanelConversation);

        jButton1.setText("Send");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jButton2.setText("Search");

        backBtn.setForeground(new java.awt.Color(255, 0, 0));
        backBtn.setText("Back");
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ChatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(28, 28, 28)
                .addComponent(backBtn)
                .addGap(55, 55, 55))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        sendMessage();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        String keyword = txtSearch.getText().trim();
        if (keyword.isEmpty()) {
            searchPopup.setVisible(false);
        } else {
            searchInDatabase(keyword);
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void backBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseClicked
        // TODO add your handling code here:
        new MainForm().setVisible(true);
        this.dispose();
        socketClient.setListener(null);
    }//GEN-LAST:event_backBtnMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(chatWindows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chatWindows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chatWindows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chatWindows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chatWindows().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea ChatTextArea;
    private javax.swing.JTextField ChatTextField;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanelConversation;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
