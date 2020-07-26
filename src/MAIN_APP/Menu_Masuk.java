package MAIN_APP;
/**
 *
 * @author KRISNAARISANDI(MEGUMI)
 */
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

public class Menu_Masuk extends javax.swing.JFrame implements ActionListener, KeyListener, MouseListener {

    private Connection Con;
    private Statement stat;
    private ResultSet res;

    public Menu_Masuk() {
        String tampilan = javax.swing.UIManager.getSystemLookAndFeelClassName();
        try {
            javax.swing.UIManager.setLookAndFeel(tampilan);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }

        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage("src/Gambar/logo/ico.png");
        setIconImage(icon);
        koneksi();
    }

     private static String user,hak;

    public static void setUserLogin(String user){
        Menu_Masuk.user =user;
       
    }
    public static String getUserLogin(){
        return user;
    }
     public static void setHakLogin(String hak){
        Menu_Masuk.hak =hak;
    }
      public static String getHakLogin(){
        return hak;
    }
    private void koneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/database_harapan_padi", "root", "");
            stat = Con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        blog = new javax.swing.JButton();
        bcan = new javax.swing.JButton();
        lpass = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        luser = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bg1 = new Gambar.bg();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MASUK & DAFTAR");
        setBackground(new java.awt.Color(0, 255, 204));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 2, 2, new java.awt.Color(153, 153, 153)));

        blog.setBackground(new java.awt.Color(0, 0, 204));
        blog.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        blog.setForeground(new java.awt.Color(0, 0, 204));
        blog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/menu_masuk/icons8_Login_20px.png"))); // NOI18N
        blog.setText("Masuk");
        blog.addActionListener(this);

        bcan.setBackground(new java.awt.Color(255, 0, 0));
        bcan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bcan.setForeground(new java.awt.Color(255, 0, 0));
        bcan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/menu_masuk/icons8_Cancel_20px_1.png"))); // NOI18N
        bcan.setText("Batal");
        bcan.addActionListener(this);

        lpass.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lpass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/menu_masuk/icons8_Password_30px.png"))); // NOI18N
        lpass.setText("Kata Sandi");

        username.setBackground(new java.awt.Color(0, 0, 0));
        username.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        username.setForeground(new java.awt.Color(204, 204, 204));

        password.setBackground(new java.awt.Color(0, 0, 0));
        password.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        password.setForeground(new java.awt.Color(204, 204, 204));
        password.addKeyListener(this);

        luser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        luser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/menu_masuk/icons8_Name_30px.png"))); // NOI18N
        luser.setText("Nama Pengguna");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/menu_masuk/icons8_Workspace_45px.png"))); // NOI18N

        org.jdesktop.layout.GroupLayout bg1Layout = new org.jdesktop.layout.GroupLayout(bg1);
        bg1.setLayout(bg1Layout);
        bg1Layout.setHorizontalGroup(
            bg1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 200, Short.MAX_VALUE)
        );
        bg1Layout.setVerticalGroup(
            bg1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 180, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(9, 9, 9)
                .add(bg1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(65, 65, 65)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(lpass)
                            .add(password, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 152, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, username)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, luser, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(blog)
                        .add(78, 78, 78)
                        .add(bcan)))
                .addContainerGap(155, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLabel2)
                .add(275, 275, 275))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(19, 19, 19)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(luser, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(2, 2, 2)
                        .add(username, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lpass)
                        .add(4, 4, 4)
                        .add(password, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(33, 33, 33)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(blog)
                            .add(bcan)))
                    .add(bg1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(0, 0, 0));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 153, 153)));

        jMenu1.setBorder(null);
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/menu_masuk/icons8_Enter_45px.png"))); // NOI18N
        jMenu1.setText("MASUK");
        jMenu1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jMenu1.addMouseListener(this);
        jMenuBar1.add(jMenu1);

        jMenu2.setBorder(null);
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/menu_masuk/icons8_Sign_Up_45px_1.png"))); // NOI18N
        jMenu2.setText("BUAT AKUN");
        jMenu2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jMenu2.addMouseListener(this);
        jMenuBar1.add(jMenu2);

        jMenu3.setBorder(null);
        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/menu_masuk/icons8_Close_Window_45px.png"))); // NOI18N
        jMenu3.setText("TUTUP");
        jMenu3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jMenu3.addMouseListener(this);
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    // Code for dispatching events from components to event handlers.

    public void actionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == blog) {
            Menu_Masuk.this.blogActionPerformed(evt);
        }
        else if (evt.getSource() == bcan) {
            Menu_Masuk.this.bcanActionPerformed(evt);
        }
    }

    public void keyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getSource() == password) {
            Menu_Masuk.this.passwordKeyPressed(evt);
        }
    }

    public void keyReleased(java.awt.event.KeyEvent evt) {
    }

    public void keyTyped(java.awt.event.KeyEvent evt) {
    }

    public void mouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getSource() == jMenu1) {
            Menu_Masuk.this.jMenu1MouseClicked(evt);
        }
        else if (evt.getSource() == jMenu2) {
            Menu_Masuk.this.jMenu2MouseClicked(evt);
        }
        else if (evt.getSource() == jMenu3) {
            Menu_Masuk.this.jMenu3MouseClicked(evt);
        }
    }

    public void mouseEntered(java.awt.event.MouseEvent evt) {
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
    }

    public void mousePressed(java.awt.event.MouseEvent evt) {
    }

    public void mouseReleased(java.awt.event.MouseEvent evt) {
    }// </editor-fold>//GEN-END:initComponents

    private void blogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blogActionPerformed
        if (username.getText().equals("") && password.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Masukan nama pengguna dan kata sandi!!");
        } else {
            try {

                res = stat.executeQuery("select * from pengguna where nama_pengguna='" + username.getText() + "' and kata_sandi = '" + password.getText() + "'");

                if (res.next()) {
                    if (password.getText().equals(res.getString("kata_sandi"))) {
                        JOptionPane.showMessageDialog(null, "Login Anda Berhasil"+ "\nHak Akses : " + res.getString("hak_akses") + "\nID Pengguna Anda : " + res.getString("id_pengguna"));
                        this.dispose();
                        Menu_Masuk.setUserLogin(res.getString("nama_pengguna"));
                        Menu_Masuk.setHakLogin(res.getString("hak_akses"));
                        new Menu_Utama().setVisible(true);
                        this.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "nama pengguna and katasandi salah");
                        username.setText("");
                        password.setText("");
                        username.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "nama pengguana dan kata sandi tidak tersedia");
                    username.setText("");
                    password.setText("");
                    username.requestFocus();
                }

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "terjadi kesalahan");
                Logger.getLogger(Menu_Masuk.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }//GEN-LAST:event_blogActionPerformed

    private void bcanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcanActionPerformed

        username.setText("");
        password.setText("");
        username.requestFocus(true);
    }//GEN-LAST:event_bcanActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, " Masukan akun untuk ke menu utama");
    }//GEN-LAST:event_jMenu1MouseClicked

    private void passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            blog.doClick();
        }


    }//GEN-LAST:event_passwordKeyPressed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        new Buat_Pengguna().setVisible(true);
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        System.exit(WIDTH);
    }//GEN-LAST:event_jMenu3MouseClicked

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
            java.util.logging.Logger.getLogger(Menu_Masuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Masuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Masuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Masuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Menu_Masuk().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bcan;
    private Gambar.bg bg1;
    private javax.swing.JButton blog;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lpass;
    private javax.swing.JLabel luser;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables

}
