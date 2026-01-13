
package airlinemanagement;

import airlinemanagement.DBConnection;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TicketBooking extends javax.swing.JFrame {

    public TicketBooking() {
        initComponents();
        GetPassenger();
        NationalityTb.setEditable(false);
        passenNameTb.setEditable(false);
        passportNumTb.setEditable(false);
        GenderCb.setEditable(false);
        GetFlifhts();
        DisplayBookings();
    }
    private void Search() {
    if (SearchTb.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Enter Flight Code to search");
        return;
    }
    try {
        Con = DBConnection.getConnection();
        String query = "SELECT * FROM Bookingtbl WHERE PName LIKE ?";
        PreparedStatement pst = Con.prepareStatement(query);
        pst.setString(1, "%" + SearchTb.getText() + "%");
        Rs = pst.executeQuery();
        BookingsTable.setModel(DbUtils.resultSetToTableModel(Rs));
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e);
    }
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        passportNumTb = new javax.swing.JTextField();
        passenNameTb = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        amountTb = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        NationalityTb = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        GenderCb = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        BookBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        BookingsTable = new javax.swing.JTable();
        ResetBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        PassenIdCb = new javax.swing.JComboBox<>();
        FlightCodeCb = new javax.swing.JComboBox<>();
        SearchTb = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        Reset = new javax.swing.JButton();
        Export = new javax.swing.JButton();
        FareTb = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("VNI-Book", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("Airline Management");

        passportNumTb.setFont(new java.awt.Font("VNI-Book", 1, 18)); // NOI18N
        passportNumTb.setForeground(new java.awt.Color(204, 0, 51));
        passportNumTb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passportNumTbActionPerformed(evt);
            }
        });

        passenNameTb.setFont(new java.awt.Font("VNI-Book", 1, 18)); // NOI18N
        passenNameTb.setForeground(new java.awt.Color(204, 0, 0));
        passenNameTb.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                passenNameTbAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        passenNameTb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passenNameTbActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("VNI-Book", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("Booking tiket");

        jLabel10.setFont(new java.awt.Font("VNI-Book", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 0, 0));
        jLabel10.setText("Nationality");

        amountTb.setFont(new java.awt.Font("VNI-Book", 1, 18)); // NOI18N
        amountTb.setForeground(new java.awt.Color(204, 0, 51));
        amountTb.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                amountTbInputMethodTextChanged(evt);
            }
        });
        amountTb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountTbActionPerformed(evt);
            }
        });
        amountTb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                amountTbKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                amountTbKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                amountTbKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("VNI-Book", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("Passenger Name");

        NationalityTb.setFont(new java.awt.Font("VNI-Book", 1, 18)); // NOI18N
        NationalityTb.setForeground(new java.awt.Color(204, 0, 51));
        NationalityTb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NationalityTbActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("VNI-Book", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 0, 0));
        jLabel5.setText("Flight Code");

        GenderCb.setFont(new java.awt.Font("VNI-Book", 1, 14)); // NOI18N
        GenderCb.setForeground(new java.awt.Color(204, 0, 0));
        GenderCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Others" }));
        GenderCb.setAutoscrolls(true);
        GenderCb.setEnabled(false);
        GenderCb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GenderCbMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                GenderCbMousePressed(evt);
            }
        });
        GenderCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenderCbActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("VNI-Book", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 0, 0));
        jLabel6.setText("Amount");

        jLabel7.setFont(new java.awt.Font("VNI-Book", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 0, 0));
        jLabel7.setText("Passport Number");

        jLabel9.setFont(new java.awt.Font("VNI-Book", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 0, 0));
        jLabel9.setText("Bookings");

        backBtn.setFont(new java.awt.Font("VNI-Book", 1, 14)); // NOI18N
        backBtn.setForeground(new java.awt.Color(204, 0, 51));
        backBtn.setText("Back");
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backBtnMouseClicked(evt);
            }
        });

        BookBtn.setFont(new java.awt.Font("VNI-Book", 1, 14)); // NOI18N
        BookBtn.setForeground(new java.awt.Color(204, 0, 51));
        BookBtn.setText("Book");
        BookBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BookBtnMouseClicked(evt);
            }
        });
        BookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookBtnActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("VNI-Book", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 0, 0));
        jLabel8.setText("Gender");

        BookingsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(BookingsTable);

        ResetBtn.setFont(new java.awt.Font("VNI-Book", 1, 14)); // NOI18N
        ResetBtn.setForeground(new java.awt.Color(204, 0, 51));
        ResetBtn.setText("Reset");
        ResetBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResetBtnMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("VNI-Book", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 0, 0));
        jLabel11.setText("Passenger Id");

        PassenIdCb.setFont(new java.awt.Font("VNI-Book", 1, 14)); // NOI18N
        PassenIdCb.setForeground(new java.awt.Color(204, 0, 0));
        PassenIdCb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PassenIdCbMouseClicked(evt);
            }
        });
        PassenIdCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PassenIdCbActionPerformed(evt);
            }
        });

        FlightCodeCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FlightCodeCbActionPerformed(evt);
            }
        });

        SearchTb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchTbActionPerformed(evt);
            }
        });

        Search.setText("Search");
        Search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SearchMouseClicked(evt);
            }
        });

        Reset.setText("Reset");
        Reset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResetMouseClicked(evt);
            }
        });

        Export.setFont(new java.awt.Font(".VnArial", 1, 14)); // NOI18N
        Export.setForeground(new java.awt.Color(204, 51, 0));
        Export.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Excel2_35735.png"))); // NOI18N
        Export.setText("Export");
        Export.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExportMouseClicked(evt);
            }
        });

        FareTb.setEditable(false);
        FareTb.setFont(new java.awt.Font(".VnArial", 1, 14)); // NOI18N
        FareTb.setForeground(new java.awt.Color(204, 51, 0));
        FareTb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FareTbActionPerformed(evt);
            }
        });
        FareTb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                FareTbKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font(".VnArial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 51, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Fare");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(120, 120, 120)
                                        .addComponent(BookBtn)
                                        .addGap(112, 112, 112))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(passenNameTb, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ResetBtn)
                                    .addComponent(FlightCodeCb, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(GenderCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 20, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel8)
                                        .addGap(36, 36, 36)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(passportNumTb, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(amountTb, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel10)
                                        .addGap(65, 65, 65)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(NationalityTb, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(FareTb, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(85, 85, 85)
                                        .addComponent(backBtn)
                                        .addGap(83, 83, 83)
                                        .addComponent(Export)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SearchTb, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Reset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(44, 44, 44)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(304, 304, 304)
                                .addComponent(jLabel2))
                            .addComponent(PassenIdCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(322, 322, 322)
                                .addComponent(jLabel3)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FareTb, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(passenNameTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FlightCodeCb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PassenIdCb)
                        .addComponent(GenderCb, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(passportNumTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(amountTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(NationalityTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Export, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ResetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Search)
                    .addComponent(Reset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
@SuppressWarnings("unchecked")
    Connection Con = null;
    PreparedStatement pst = null;
    ResultSet Rs = null, Rs1 = null;
    Statement St = null, St1 = null;
private  void GetPassenger(){
    try {
            Con = DBConnection.getConnection();
            St = Con.createStatement();
            //String Querry "select * from PassengersTbl";
            Rs = St.executeQuery("select * from PassengersTbl");
            while(Rs.next()){
                String PId = String.valueOf( Rs.getInt("PID"));
                PassenIdCb.addItem(PId);
            }
        } catch (Exception e) {
        }
}
private  void GetFlifhts(){
    try {
            Con = DBConnection.getConnection();
            St = Con.createStatement();
            //String Querry "select * from PassengersTbl";
            Rs = St.executeQuery("select * from FlightTbl");
            while(Rs.next()){
                String FCode =  Rs.getString("FlCode");
                FlightCodeCb.addItem(FCode);
            }
        } catch (Exception e) {
        }
}
private  void GetPassengerData(){
    if (PassenIdCb.getSelectedItem() == null) {
        return;
    }
    String querry = "SELECT * FROM PassengersTbl WHERE PID =" + PassenIdCb.getSelectedItem().toString();
    Statement st = null;
    ResultSet Rs;
    try {
        Con = DBConnection.getConnection();
        st = Con.createStatement();
        Rs = st.executeQuery(querry);
        if(Rs.next()){
            passenNameTb.setText(Rs.getString("PName"));
            //FlightCodeCb.setText(Rs.getString("PNat"));
            GenderCb.setSelectedItem(Rs.getString("PGen"));
            passportNumTb.setText(Rs.getString("PPass"));
            NationalityTb.setText(Rs.getString("PNat"));
        }
    } catch (Exception e) {
    }
}

private void GetFare(){
   if (FlightCodeCb.getSelectedItem() == null) {
        return;
    }
    int amount = 1;
    String querry = "SELECT Fare FROM FlightTbl WHERE FLCode ='" + FlightCodeCb.getSelectedItem().toString() + "'";
    Statement st = null;
    ResultSet Rs;
     if (!amountTb.getText().isEmpty()) {
        try {
            amount = Integer.parseInt(amountTb.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Amount must be a number");
            amountTb.setText("1");
            return;
        }
    }
    try {
        Con = DBConnection.getConnection();
        st = Con.createStatement();
        Rs = st.executeQuery(querry);
        if(Rs.next()){
            FareTb.setText( String.valueOf( (Integer.parseInt( Rs.getString("Fare") )*amount) ) );
        }
    } catch (Exception e) {
        
    }
}

private void ExportToExcel() {
    try {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Excel File");
        fileChooser.setSelectedFile(new File("TicketBooking.xlsx"));

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File fileToSave = fileChooser.getSelectedFile();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("TicketBooking");

        DefaultTableModel model = (DefaultTableModel) BookingsTable.getModel();

        // Header
        Row headerRow = sheet.createRow(0);
        for (int col = 0; col < model.getColumnCount(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(model.getColumnName(col));
        }

        // Data
        for (int row = 0; row < model.getRowCount(); row++) {
            Row excelRow = sheet.createRow(row + 1);
            for (int col = 0; col < model.getColumnCount(); col++) {
                Cell cell = excelRow.createCell(col);
                Object value = model.getValueAt(row, col);
                cell.setCellValue(value == null ? "" : value.toString());
            }
        }

        // Auto size columns
        for (int i = 0; i < model.getColumnCount(); i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fos = new FileOutputStream(fileToSave);
        workbook.write(fos);
        fos.close();
        workbook.close();

        JOptionPane.showMessageDialog(this, "Export Excel Successfully!");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e);
    }
}
    private void passportNumTbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passportNumTbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passportNumTbActionPerformed

    private void passenNameTbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passenNameTbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passenNameTbActionPerformed

    private void amountTbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountTbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountTbActionPerformed

    private void NationalityTbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NationalityTbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NationalityTbActionPerformed

    private void BookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BookBtnActionPerformed

    private void PassenIdCbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PassenIdCbMouseClicked

    }//GEN-LAST:event_PassenIdCbMouseClicked

    private void passenNameTbAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_passenNameTbAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_passenNameTbAncestorAdded

    private void PassenIdCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PassenIdCbActionPerformed
        // TODO add your handling code here:
        GetPassengerData();
    }//GEN-LAST:event_PassenIdCbActionPerformed

    private void backBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseClicked
            new MainForm().setVisible(true);
            this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_backBtnMouseClicked

    private void ResetBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResetBtnMouseClicked
        Clear();
    }//GEN-LAST:event_ResetBtnMouseClicked
private  void Clear(){
        passportNumTb.setText("");
        passenNameTb.setText("");
        GenderCb.setSelectedIndex(-1);
        NationalityTb.setText("");
        amountTb.setText("");
        FareTb.setText("");
        FlightCodeCb.setSelectedIndex(-1);
        PassenIdCb.setSelectedIndex(-1);
}
    private void GenderCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenderCbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GenderCbActionPerformed
private void DisplayBookings(){
        try {
            Con = DBConnection.getConnection();
            St = Con.createStatement();
            String query =
            "SELECT b.*, f.Fare " +
            "FROM BookingTbl b " +
            "JOIN FlightTbl f ON b.FiCode = f.FlCode";
            
            Rs = St.executeQuery(query);
            BookingsTable.setModel(DbUtils.resultSetToTableModel(Rs));
        } catch (Exception e) {
        }
    }
    private void BookBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BookBtnMouseClicked
        // TODO add your handling code here:
        // Kiểm tra dữ liệu còn thiếu
if(passenNameTb.getText().isEmpty() || FlightCodeCb.getSelectedIndex() == -1 
   || GenderCb.getSelectedIndex() == -1 || passportNumTb.getText().isEmpty() 
   || amountTb.getText().isEmpty() || NationalityTb.getText().isEmpty()) {
    JOptionPane.showMessageDialog(this, "Missing Information");
} else {
    try {
        Con = DBConnection.getConnection();

        // Lấy số ghế còn lại từ FlightTbl
        String flightCode = FlightCodeCb.getSelectedItem().toString();
        PreparedStatement pstCheckSeats = Con.prepareStatement("SELECT FlSeats FROM FlightTbl WHERE FlCode = ?");
        pstCheckSeats.setString(1, flightCode);
        ResultSet rs = pstCheckSeats.executeQuery();

        if(rs.next()) {
            int availableSeats = rs.getInt("FlSeats");
            int bookingAmount = Integer.parseInt(amountTb.getText());

            if(bookingAmount > availableSeats) {
                JOptionPane.showMessageDialog(this, "Not enough seats available!");
            } else {
                // Insert vào BookingTbl
                PreparedStatement Add = Con.prepareStatement(
                    "INSERT INTO BookingTbl (PName, Ficode, PGen, Ppass, Amount, Nationality) VALUES (?,?,?,?,?,?)");
                Add.setString(1, passenNameTb.getText());
                Add.setString(2, flightCode);
                Add.setString(3, GenderCb.getSelectedItem().toString());
                Add.setString(4, passportNumTb.getText());
                Add.setInt(5, bookingAmount);
                Add.setString(6, NationalityTb.getText());

                int row = Add.executeUpdate();

                // Trừ số ghế trong FlightTbl
                PreparedStatement updateSeats = Con.prepareStatement(
                    "UPDATE FlightTbl SET FlSeats = FlSeats - ? WHERE FLCode = ?");
                updateSeats.setInt(1, bookingAmount);
                updateSeats.setString(2, flightCode);
                updateSeats.executeUpdate();

                JOptionPane.showMessageDialog(this, "Passenger Added");
                DisplayBookings();
                Clear();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Flight not found!");
        }

        Con.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e);
    }
}
    }//GEN-LAST:event_BookBtnMouseClicked

    private void SearchTbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchTbActionPerformed
        // TODO add your handling code here:
        Search();
    }//GEN-LAST:event_SearchTbActionPerformed

    private void SearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchMouseClicked
        // TODO add your handling code here:
        Search();
    }//GEN-LAST:event_SearchMouseClicked

    private void ResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResetMouseClicked
        // TODO add your handling code here:
        SearchTb.setText("");
        DisplayBookings();
    }//GEN-LAST:event_ResetMouseClicked

    private void ExportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExportMouseClicked
        // TODO add your handling code here:
        ExportToExcel();
    }//GEN-LAST:event_ExportMouseClicked

    private void FlightCodeCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FlightCodeCbActionPerformed
        // TODO add your handling code here:
        GetFare();
        amountTb.setText("1");
    }//GEN-LAST:event_FlightCodeCbActionPerformed

    private void FareTbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FareTbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FareTbActionPerformed

    private void GenderCbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GenderCbMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_GenderCbMouseClicked

    private void GenderCbMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GenderCbMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_GenderCbMousePressed

    private void FareTbKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FareTbKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_FareTbKeyTyped

    private void amountTbKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountTbKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_amountTbKeyTyped

    private void amountTbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountTbKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountTbKeyPressed

    private void amountTbInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_amountTbInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_amountTbInputMethodTextChanged

    private void amountTbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountTbKeyReleased
        // TODO add your handling code here:
        GetFare();
    }//GEN-LAST:event_amountTbKeyReleased


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
            java.util.logging.Logger.getLogger(TicketBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TicketBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TicketBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TicketBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TicketBooking().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BookBtn;
    private javax.swing.JTable BookingsTable;
    private javax.swing.JButton Export;
    private javax.swing.JTextField FareTb;
    private javax.swing.JComboBox<String> FlightCodeCb;
    private javax.swing.JComboBox<String> GenderCb;
    private javax.swing.JTextField NationalityTb;
    private javax.swing.JComboBox<String> PassenIdCb;
    private javax.swing.JButton Reset;
    private javax.swing.JButton ResetBtn;
    private javax.swing.JButton Search;
    private javax.swing.JTextField SearchTb;
    private javax.swing.JTextField amountTb;
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField passenNameTb;
    private javax.swing.JTextField passportNumTb;
    // End of variables declaration//GEN-END:variables
}
