package MAIN_APP;
/**
 *
 * @author KRISNAARISANDI(MEGUMI)
 */

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

public class Buat_Member extends javax.swing.JFrame {

    private Connection con;
    private Statement stat;
    private ResultSet res;
    private SimpleDateFormat smpdtfmt;
    private String tanggal;
    private PreparedStatement pst;

    public Buat_Member() {
        Locale locale = new Locale("id", "ID");
        Locale.setDefault(locale);
        String tampilan = javax.swing.UIManager.getSystemLookAndFeelClassName();
        try {
            javax.swing.UIManager.setLookAndFeel(tampilan);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }

        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage("src/Gambar/logo/ico.png");
        setIconImage(icon);
        koneksi();
        tanggal();
        autonumber();
        unvisible(false);

    }

    private void tanggal() {
        java.util.Date tglsekarang = new java.util.Date();
        smpdtfmt = new SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault());
        tanggal = smpdtfmt.format(tglsekarang);
        text_tglp.setText(tanggal);

    }

    private void koneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/database_harapan_padi", "root", "");
            stat = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void status() {
        String jenis = (null);
        if (lki.isSelected()) {
            jenis = "Laki-Laki";
        } else if (pr.isSelected()) {
            jenis = "Perempuan";
        }
        ta_status.setText(" == Data Member Harapan Padi =="
                + "\n == Telp.(082)8001646 =="
                + "\n ==================================\n"
                + "\n Id Member                     = " + text_idm.getText() + "\n"
                + "\n Nama Lengkap               = " + text_namam1.getText() + "\n"
                + "\n Tempat Tanggal Lahir    = " + text_tmptl.getText() + "-" + smpdtfmt.format(jCalendar1.getDate()) + "\n"
                + "\n Jenis Kelamin                 = " + jenis + "\n"
                + "\n No Telepon                    = " + text_nom.getText() + "\n"
                + "\n Alamat Email                  = " + text_email.getText() + "\n"
                + "\n Tanggal Pembuatan       = " + text_tglp.getText() + "\n"
                + "\n Alamat                          = " + Txa_almt.getText() + ""
        );

    }

    private void autonumber() {
        try {

            String sql = "SELECT MAX(RIGHT(id_member,4)) AS NO FROM member";
            stat = con.createStatement();
            res = stat.executeQuery(sql);
            while (res.next()) {
                if (res.first() == false) {
                    text_idm.setText("MBR-001");
                } else {
                    res.last();
                    int auto_id = res.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int idmember = no.length();

                    for (int j = 0; j < 4 - idmember; j++) {
                        no = "0" + no;
                    }
                    text_idm.setText("MBR-" + no);
                }
            }
            res.close();
            stat.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "ERROR: \n" + e.toString(),
                    "Kesalahan", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void unvisible(boolean a) {
        button_konfi.setEnabled(a);

    }

    public void kosong() {
        text_namam1.setText("");
        text_email.setText("");
        text_tmptl.setText("");
        text_nom.setText("");
        Txa_almt.setText("");
        String jenis = (null);
        if (lki.isSelected()) {
            jenis = "Laki-Laki";
        } else if (pr.isSelected()) {
            jenis = "Perempuan";
        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        text_tmptl = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        text_nom = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lki = new javax.swing.JRadioButton();
        pr = new javax.swing.JRadioButton();
        text_idm = new javax.swing.JTextField();
        button_edit1 = new javax.swing.JButton();
        button_btl = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        Txa_almt = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        text_namam1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        text_tglp = new javax.swing.JTextField();
        text_email = new javax.swing.JTextField();
        bg_anime21 = new Gambar.bg_anime2();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ta_status = new javax.swing.JTextArea();
        button_konfi = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        muatulang = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("REGISTRASI MEMBER");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 5));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3), "REGISTRASI MEMBER", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel1.setText("Nama Lengkap    ");

        jLabel2.setText("Tanggal Pembuatan ");

        text_tmptl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                text_tmptlKeyPressed(evt);
            }
        });

        jLabel11.setText("ID Member          ");

        jLabel10.setText("No Telepon      ");

        jLabel12.setText("Jenis Kelamin       ");

        bg.add(lki);
        lki.setText("Laki - Laki");
        lki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lkiActionPerformed(evt);
            }
        });

        bg.add(pr);
        pr.setText("Perempuan");
        pr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prActionPerformed(evt);
            }
        });

        text_idm.setEditable(false);
        text_idm.setBackground(new java.awt.Color(0, 0, 0));
        text_idm.setForeground(new java.awt.Color(255, 255, 255));
        text_idm.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        text_idm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                text_idmKeyPressed(evt);
            }
        });

        button_edit1.setBackground(new java.awt.Color(51, 255, 51));
        button_edit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Create_25px.png"))); // NOI18N
        button_edit1.setText("Buat");
        button_edit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_edit1ActionPerformed(evt);
            }
        });

        button_btl.setBackground(new java.awt.Color(255, 0, 0));
        button_btl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Unavailable_25px.png"))); // NOI18N
        button_btl.setText("Batal");
        button_btl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_btlActionPerformed(evt);
            }
        });

        Txa_almt.setColumns(20);
        Txa_almt.setRows(5);
        jScrollPane3.setViewportView(Txa_almt);

        jLabel3.setText("Tempat Lahir       ");

        jLabel4.setText("Tanggal Lahir   :");

        text_namam1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                text_namam1KeyPressed(evt);
            }
        });

        jLabel5.setText("Alamat Email    ");

        jLabel6.setText("Alamat             ");

        text_tglp.setEditable(false);
        text_tglp.setBackground(new java.awt.Color(0, 0, 0));
        text_tglp.setForeground(new java.awt.Color(255, 255, 255));

        org.jdesktop.layout.GroupLayout bg_anime21Layout = new org.jdesktop.layout.GroupLayout(bg_anime21);
        bg_anime21.setLayout(bg_anime21Layout);
        bg_anime21Layout.setHorizontalGroup(
            bg_anime21Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 378, Short.MAX_VALUE)
        );
        bg_anime21Layout.setVerticalGroup(
            bg_anime21Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 319, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel12)
                                    .add(jLabel3)
                                    .add(jLabel11)
                                    .add(jLabel1))
                                .add(34, 34, 34)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(lki)
                                        .add(13, 13, 13)
                                        .add(pr))
                                    .add(text_tmptl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(text_idm, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(text_namam1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 175, Short.MAX_VALUE)
                                        .add(jLabel4))))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel2)
                                    .add(jLabel5)
                                    .add(jLabel10)
                                    .add(jLabel6))
                                .add(21, 21, 21)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jScrollPane3)
                                        .add(95, 95, 95))
                                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                        .add(text_email, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(text_nom, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(text_tglp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(button_edit1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(button_btl)
                        .add(193, 193, 193)))
                .add(jCalendar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(20, 20, 20))
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                    .add(146, 146, 146)
                    .add(bg_anime21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(176, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(14, 14, 14)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel11)
                    .add(text_idm, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(6, 6, 6)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel1)
                                .add(text_namam1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jLabel4))
                        .add(6, 6, 6)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel3)
                            .add(text_tmptl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(5, 5, 5)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel12)
                            .add(lki)
                            .add(pr))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel10)
                            .add(text_nom, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel5)
                            .add(text_email, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel2)
                            .add(text_tglp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jCalendar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel6)
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(button_edit1)
                    .add(button_btl))
                .addContainerGap(34, Short.MAX_VALUE))
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(bg_anime21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(82, Short.MAX_VALUE)))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3), "STATUS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N

        ta_status.setColumns(20);
        ta_status.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ta_status.setRows(5);
        ta_status.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setViewportView(ta_status);

        button_konfi.setBackground(new java.awt.Color(51, 255, 204));
        button_konfi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Ok_25px.png"))); // NOI18N
        button_konfi.setText("Konfirmasi");
        button_konfi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_konfiActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane2)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(button_konfi)
                .add(311, 311, 311))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(button_konfi)
                .add(23, 23, 23))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Course_25px.png"))); // NOI18N
        jButton1.setText("PETUNJUK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Member_25px.png"))); // NOI18N
        jButton2.setText("DAFTAR MEMBER");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        muatulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Refresh_25px_2.png"))); // NOI18N
        muatulang.setText("MUAT ULANG");
        muatulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muatulangActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jButton1)
                        .add(18, 18, 18)
                        .add(jButton2)
                        .add(18, 18, 18)
                        .add(muatulang)
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton1)
                    .add(jButton2)
                    .add(muatulang))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 183, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(34, 34, 34))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 686, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void text_tmptlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_text_tmptlKeyPressed

    }//GEN-LAST:event_text_tmptlKeyPressed

    private void lkiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lkiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lkiActionPerformed

    private void prActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prActionPerformed

    private void text_idmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_text_idmKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_idmKeyPressed

    private void button_edit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_edit1ActionPerformed
        try {
            if (text_idm.getText().equals("") || text_namam1.getText().equals("") || text_tmptl.getText().equals("") || text_nom.getText().equals("") || text_email.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "lengkapi data terlebih dahulu");

            } else {
                unvisible(true);
                status();
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_button_edit1ActionPerformed

    private void button_btlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_btlActionPerformed
        kosong();
        new Buat_Member().setVisible(true);
        dispose();
    }//GEN-LAST:event_button_btlActionPerformed

    private void button_konfiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_konfiActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Konfirmasi?", "Buat Member Baru", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            String jenis = (null);
            if (lki.isSelected()) {
                jenis = "Laki-Laki";
            } else if (pr.isSelected()) {
                jenis = "Perempuan";
            }

            String sql = "insert into member values(?,?,?,?,?,?,?,?,?)";
            try {
                pst = con.prepareStatement(sql);
                pst.setString(1, text_idm.getText());
                pst.setString(2, text_namam1.getText());
                pst.setString(3, text_tmptl.getText());
                pst.setString(4, smpdtfmt.format(jCalendar1.getDate()));
                pst.setString(5, jenis);
                pst.setString(6, text_nom.getText());
                pst.setString(7, text_email.getText());
                pst.setString(8, text_tglp.getText());
                pst.setString(9, Txa_almt.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Berhasil Buat Member Baru");
                autonumber();

                kosong();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan " + e);
            }
            kosong();
            new Buat_Member().setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_button_konfiActionPerformed

    private void text_namam1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_text_namam1KeyPressed

    }//GEN-LAST:event_text_namam1KeyPressed

    private void muatulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muatulangActionPerformed
        new Buat_Member().setVisible(true);
        dispose();
    }//GEN-LAST:event_muatulangActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new Daftar_Member().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new Petunjuk_Input_Data().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            javax.swing.UIManager.LookAndFeelInfo[] installedLookAndFeels = javax.swing.UIManager.getInstalledLookAndFeels();
            for (int idx = 0; idx < installedLookAndFeels.length; idx++) {
                if ("Nimbus".equals(installedLookAndFeels[idx].getName())) {
                    javax.swing.UIManager.setLookAndFeel(installedLookAndFeels[idx].getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Buat_Member.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Buat_Member.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Buat_Member.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Buat_Member.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Buat_Member().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Txa_almt;
    private javax.swing.ButtonGroup bg;
    private Gambar.bg_anime2 bg_anime21;
    private javax.swing.JButton button_btl;
    private javax.swing.JButton button_edit1;
    private javax.swing.JButton button_konfi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton lki;
    private javax.swing.JButton muatulang;
    private javax.swing.JRadioButton pr;
    private javax.swing.JTextArea ta_status;
    private javax.swing.JTextField text_email;
    private javax.swing.JTextField text_idm;
    private javax.swing.JTextField text_namam1;
    private javax.swing.JTextField text_nom;
    private javax.swing.JTextField text_tglp;
    private javax.swing.JTextField text_tmptl;
    // End of variables declaration//GEN-END:variables

}
