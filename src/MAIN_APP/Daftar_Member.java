package MAIN_APP;

/**
 *
 * @author KRISNAARISANDI(MEGUMI)
 */
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public final class Daftar_Member extends javax.swing.JFrame {

    private Connection con;
    private Statement stat;
    private ResultSet res;
    private DefaultTableModel tabmode;
    public Beras_Keluar sp = null;
    public Beras_Keluar itm = null;

    public Daftar_Member() {
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
        tabel_member();
        btn_hapusm.setEnabled(false);
        btn_cetakkrt.setEnabled(false);
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

    public void tabel_member() {

        Object[] Baris = {"ID MEMBER", "NAMA LENGKAP", "TEMPAT LAHIR", "TANGGAL LAHIR", "JENIS KELAMIN", "NO HP", "ALAMAT EMAIL", "TANGGAL PEMBUATAN", "ALAMAT"};
        tabmode = new DefaultTableModel(null, Baris);
        String cariitem = text_cari.getText();
        try {

            String sql = "SELECT * FROM member";
            stat = con.createStatement();
            res = stat.executeQuery(sql);
            if (box_id.isSelected()) {

                String sql2 = "SELECT * FROM member where id_member like '%" + cariitem + "%' order by id_member asc";
                stat = con.createStatement();
                res = stat.executeQuery(sql2);
            } else if (box_nama.isSelected()) {
                String sql3 = "SELECT * FROM member where nama_member like '%" + cariitem + "%' order by nama_member asc";
                stat = con.createStatement();
                res = stat.executeQuery(sql3);
            } else if (box_tp.isSelected()) {
                String sql4 = "SELECT * FROM member where tanggal_pembuatan like '%" + cariitem + "%' order by tanggal_pembuatan asc";
                stat = con.createStatement();
                res = stat.executeQuery(sql4);
            } else if (box_jk.isSelected()) {
                String sql5 = "SELECT * FROM member where jenis_kelamin like '%" + cariitem + "%' order by jenis_kelamin asc";
                stat = con.createStatement();
                res = stat.executeQuery(sql5);
            } else {
                String sql4 = "SELECT * FROM member where nama_member like '%" + cariitem + "%' or id_member like '%" + cariitem + "%' order by id_member asc";
                stat = con.createStatement();
                res = stat.executeQuery(sql4);
            }
            while (res.next()) {
                tabmode.addRow(new Object[]{
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6),
                    res.getString(7),
                    res.getString(8),
                    res.getString(9)
                });
            }

            tabel_member.setModel(tabmode);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data gagal dipanggil" + e);
        }
    }

    private void kosongkan() {
        text_nlp.setText("");
        text_tlp.setText("");
    }

    private void data_panggil() {
        int bar = tabel_member.getSelectedRow();
        itm.idmem = tabel_member.getValueAt(bar, 0).toString();
        itm.namamem = tabel_member.getValueAt(bar, 1).toString();
        itm.telpmem = tabel_member.getValueAt(bar, 5).toString();
        itm.almtmem = tabel_member.getValueAt(bar, 8).toString();
        itm.itemMember();
        this.dispose();
    }

    public void data() throws ParseException {
        int bar = tabel_member.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        Date x = new SimpleDateFormat("dd-MMMM-yyyy").parse(d);
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        String g = tabmode.getValueAt(bar, 6).toString();
        String h = tabmode.getValueAt(bar, 7).toString();
        String i = tabmode.getValueAt(bar, 8).toString();
        text_idm.setText(a);
        text_nama.setText(b);
        text_nlp.setText(b);
        text_tmpt.setText(c);
        text_tlp.setText(c);
        text_tl.setText(d);
        jDateChooser1.setDate(x);
        text_jk.setText(e);
        if ("Laki-Laki".equals(e)) {
            lki.setSelected(true);
        } else if ("Perempuan".equals(e)) {
            pr.setSelected(true);
        }
        text_no.setText(f);
        text_nohpp.setText(f);
        text_email.setText(g);
        text_alemp.setText(g);
        text_tp.setText(h);
        ta_alamat.setText(i);
        text_almtn.setText(i);
        btn_hapusm.setEnabled(true);
        btn_cetakkrt.setEnabled(true);

    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        text_cari = new javax.swing.JTextField();
        box_id = new javax.swing.JCheckBox();
        box_nama = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_member = new javax.swing.JTable();
        button_ulang = new javax.swing.JButton();
        button_cari = new javax.swing.JButton();
        box_tp = new javax.swing.JCheckBox();
        btn_hapusm = new javax.swing.JButton();
        box_jk = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btn_cetakkrt = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        text_idm = new javax.swing.JTextField();
        text_nama = new javax.swing.JTextField();
        text_tmpt = new javax.swing.JTextField();
        text_tl = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        text_jk = new javax.swing.JTextField();
        text_email = new javax.swing.JTextField();
        text_no = new javax.swing.JTextField();
        text_tp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_alamat = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        text_nlp = new javax.swing.JTextField();
        text_tlp = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        text_almtn = new javax.swing.JTextArea();
        text_nohpp = new javax.swing.JTextField();
        lki = new javax.swing.JRadioButton();
        pr = new javax.swing.JRadioButton();
        text_alemp = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DAFTAR MEMBER");
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Cari Member :");

        text_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                text_cariKeyPressed(evt);
            }
        });

        box_id.setText("ID");

        box_nama.setText("Nama");

        tabel_member.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tabel_member.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_member.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_memberMouseClicked(evt);
            }
        });
        tabel_member.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabel_memberKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabel_memberKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_member);

        button_ulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Refresh_25px_2.png"))); // NOI18N
        button_ulang.setText("Muat Ulang");
        button_ulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ulangActionPerformed(evt);
            }
        });
        button_ulang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                button_ulangKeyPressed(evt);
            }
        });

        button_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Search_25px.png"))); // NOI18N
        button_cari.setText("Cari");
        button_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_cariActionPerformed(evt);
            }
        });

        box_tp.setText("Tanggal Pembuatan");

        btn_hapusm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Delete_20px.png"))); // NOI18N
        btn_hapusm.setText("Hapus");
        btn_hapusm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusmActionPerformed(evt);
            }
        });

        box_jk.setText("Jenis Kelamin");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Print_25px.png"))); // NOI18N
        jButton1.setText("Cetak Laporan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Course_25px.png"))); // NOI18N
        jButton3.setText("Petunjuk");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btn_cetakkrt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Bank_Cards_20px.png"))); // NOI18N
        btn_cetakkrt.setText("Cetak Kartu");
        btn_cetakkrt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetakkrtActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 1150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(btn_hapusm, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 113, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(btn_cetakkrt)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel11)
                        .add(7, 7, 7)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(text_cari, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 171, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(button_cari)
                                .add(14, 14, 14)
                                .add(button_ulang)
                                .add(18, 18, 18)
                                .add(jButton1)
                                .add(18, 18, 18)
                                .add(jButton3))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(box_id)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(box_nama)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(box_tp)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(box_jk)))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(6, 6, 6)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel11)
                    .add(text_cari, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(button_ulang)
                    .add(button_cari)
                    .add(jButton1)
                    .add(jButton3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(box_id)
                    .add(box_nama)
                    .add(box_tp)
                    .add(box_jk))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(btn_hapusm)
                        .add(18, 18, 18)
                        .add(btn_cetakkrt)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("DAFTAR MEMBER", jPanel1);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "DATA MEMBER", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("ID Member");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Nama Lengkap");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Tempat Lahir");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Tanggal Lahir");

        text_idm.setEditable(false);
        text_idm.setBackground(new java.awt.Color(0, 0, 0));
        text_idm.setForeground(new java.awt.Color(255, 255, 255));

        text_nama.setEditable(false);
        text_nama.setBackground(new java.awt.Color(0, 120, 215));
        text_nama.setForeground(new java.awt.Color(255, 255, 255));

        text_tmpt.setEditable(false);
        text_tmpt.setBackground(new java.awt.Color(0, 120, 215));
        text_tmpt.setForeground(new java.awt.Color(255, 255, 255));
        text_tmpt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_tmptActionPerformed(evt);
            }
        });

        text_tl.setEditable(false);
        text_tl.setBackground(new java.awt.Color(0, 120, 215));
        text_tl.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Jenis Kelamin");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("No Handphone");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Alamat Email");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Tanggal Pembuatan");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Alamat");

        text_jk.setEditable(false);
        text_jk.setBackground(new java.awt.Color(0, 120, 215));
        text_jk.setForeground(new java.awt.Color(255, 255, 255));

        text_email.setEditable(false);
        text_email.setBackground(new java.awt.Color(0, 120, 215));
        text_email.setForeground(new java.awt.Color(255, 255, 255));

        text_no.setEditable(false);
        text_no.setBackground(new java.awt.Color(0, 120, 215));
        text_no.setForeground(new java.awt.Color(255, 255, 255));

        text_tp.setEditable(false);
        text_tp.setBackground(new java.awt.Color(0, 0, 0));
        text_tp.setForeground(new java.awt.Color(255, 255, 255));

        ta_alamat.setEditable(false);
        ta_alamat.setBackground(new java.awt.Color(0, 120, 215));
        ta_alamat.setColumns(20);
        ta_alamat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ta_alamat.setForeground(new java.awt.Color(255, 255, 255));
        ta_alamat.setRows(5);
        jScrollPane1.setViewportView(ta_alamat);

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jPanel6Layout.createSequentialGroup()
                                            .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .add(49, 49, 49))
                                        .add(jPanel6Layout.createSequentialGroup()
                                            .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                .add(jLabel3)
                                                .add(jLabel4)
                                                .add(jLabel2))
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .add(jPanel6Layout.createSequentialGroup()
                                        .add(jLabel5)
                                        .add(57, 57, 57)))
                                .add(jPanel6Layout.createSequentialGroup()
                                    .add(jLabel6)
                                    .add(46, 46, 46)))
                            .add(jPanel6Layout.createSequentialGroup()
                                .add(jLabel7)
                                .add(60, 60, 60)))
                        .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, text_tl, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, text_nama)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, text_idm))
                            .add(text_jk, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(text_no, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(text_email, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(text_tmpt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel6Layout.createSequentialGroup()
                                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel8)
                                    .add(jLabel9))
                                .add(56, 56, 56)
                                .add(text_tp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(47, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(text_idm, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(20, 20, 20)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel2)
                    .add(text_nama, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(11, 11, 11)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(text_tmpt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(text_tl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(8, 8, 8)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(text_jk, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(text_no, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(text_email, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel7))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(text_tp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel8))
                .add(18, 18, 18)
                .add(jLabel9)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "PERUBAHAN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        text_almtn.setColumns(20);
        text_almtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        text_almtn.setRows(5);
        jScrollPane3.setViewportView(text_almtn);

        buttonGroup1.add(lki);
        lki.setText("Laki - Laki");

        buttonGroup1.add(pr);
        pr.setText("Perempuan");
        pr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prActionPerformed(evt);
            }
        });

        jDateChooser1.setDateFormatString("dd-MMMM-yyyy");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Available_Updates_15px.png"))); // NOI18N
        jLabel12.setText("Data Baru");

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(lki)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(pr))
                    .add(text_alemp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 170, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, text_nlp, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, text_tlp))
                    .add(jDateChooser1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 180, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(text_nohpp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 170, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 280, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel12))
                .addContainerGap(145, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel12)
                .add(19, 19, 19)
                .add(text_nlp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(text_tlp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jDateChooser1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lki)
                    .add(pr))
                .add(14, 14, 14)
                .add(text_nohpp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(text_alemp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 71, Short.MAX_VALUE)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Edit_Property_25px.png"))); // NOI18N
        jButton2.setText("UBAH");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jButton2)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(0, 440, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton2)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("INFO MEMBER", jPanel2);

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 1288, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane1)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void text_cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_text_cariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            button_cari.doClick();
        } else if (evt.getKeyCode() == KeyEvent.VK_F5) {
            new Beras_Masuk().setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_text_cariKeyPressed

    private void button_ulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ulangActionPerformed
        new Daftar_Member().setVisible(true);
        dispose();
    }//GEN-LAST:event_button_ulangActionPerformed

    private void button_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_cariActionPerformed
        if (text_cari.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Input Data Dahulu!!!");
        } else {
            tabel_member();

        }
    }//GEN-LAST:event_button_cariActionPerformed

    private void tabel_memberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_memberMouseClicked
        try {
            data();
        } catch (ParseException ex) {
            Logger.getLogger(Daftar_Member.class.getName()).log(Level.SEVERE, null, ex);
        }
        data_panggil();
    }//GEN-LAST:event_tabel_memberMouseClicked

    private void prActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String jenis = (null);
        if (lki.isSelected()) {
            jenis = "Laki-Laki";
        } else if (pr.isSelected()) {
            jenis = "Perempuan";
        }
        try {
            if (JOptionPane.showConfirmDialog(null, "Yakin Ingin Merubahnya?", "Ubah Data Member", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                stat.executeUpdate("update member set "
                        + "nama_member='" + text_nlp.getText() + "',"
                        + "tempat_lahir='" + text_tlp.getText() + "',"
                        + "jenis_kelamin='" + jenis + "',"
                        + "no_hp='" + text_nohpp.getText() + "',"
                        + "alamat_email='" + text_alemp.getText() + "',"
                        + "alamat='" + text_almtn.getText() + "'"
                        + " where " + "id_member='" + text_idm.getText() + "'");

                JOptionPane.showMessageDialog(null, "Data berhasil Dirubah");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "salah");
        }
        new Daftar_Member().setVisible(true);
        dispose();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_hapusmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusmActionPerformed
        try {
            if (JOptionPane.showConfirmDialog(null, "Konfirmasi Penghapusan?", "Menghapus Member", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                stat.executeUpdate("delete from member where " + "id_member='" + text_idm.getText() + "'");
                JOptionPane.showMessageDialog(null, "Berhasil Hapus Member");
                new Daftar_Member().setVisible(true);
                dispose();
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "pesan salah : " + e);

        }

    }//GEN-LAST:event_btn_hapusmActionPerformed

    private void button_ulangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_button_ulangKeyPressed

    }//GEN-LAST:event_button_ulangKeyPressed

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed


    }//GEN-LAST:event_jPanel1KeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_R) {
            new Daftar_Member().setVisible(true);
        }
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_R) {
            new Daftar_Member().setVisible(true);
        }
    }//GEN-LAST:event_formKeyReleased

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        if (evt.getKeyCode() == KeyEvent.VK_R) {
            new Daftar_Member().setVisible(true);
        }
    }//GEN-LAST:event_formKeyTyped

    private void tabel_memberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabel_memberKeyPressed
        try {
            data();
        } catch (ParseException ex) {
            Logger.getLogger(Daftar_Member.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabel_memberKeyPressed

    private void tabel_memberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabel_memberKeyReleased
        try {
            data();
        } catch (ParseException ex) {
            Logger.getLogger(Daftar_Member.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabel_memberKeyReleased

    private void text_tmptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_tmptActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_tmptActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Locale locale = new Locale("id", "ID");
            Locale.setDefault(locale);
            HashMap parameter = new HashMap();
            String path = "./src/report/laporan_pembuatan_memeber.jasper";
            parameter.put("TANGGAL", text_cari.getText());

            JasperPrint print = JasperFillManager.fillReport(path, parameter, con);
            JasperViewer.viewReport(print, false);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(rootPane, "DokumenTidak Ada " + ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new Petunjuk_Edit().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btn_cetakkrtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetakkrtActionPerformed
        try {
            Locale locale = new Locale("id", "ID");
            Locale.setDefault(locale);
            HashMap parameter = new HashMap();
            String path = "./src/report/kartu_member.jasper";
            parameter.put("ID", text_idm.getText());
            parameter.put("NAMA", text_nama.getText());
            parameter.put("TANGGALP", text_tp.getText());
            parameter.put("HP", text_no.getText());
            parameter.put("JK", text_jk.getText());
            parameter.put("TPL", text_tmpt.getText());
            parameter.put("TL", text_tl.getText());
            parameter.put("ALE", text_email.getText());
            parameter.put("ALT", ta_alamat.getText());

            JasperPrint print = JasperFillManager.fillReport(path, parameter, con);
            JasperViewer.viewReport(print, false);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(rootPane, "DokumenTidak Ada " + ex);
        }
    }//GEN-LAST:event_btn_cetakkrtActionPerformed

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
            java.util.logging.Logger.getLogger(Daftar_Member.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Daftar_Member.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Daftar_Member.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Daftar_Member.class
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
            new Daftar_Member().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox box_id;
    private javax.swing.JCheckBox box_jk;
    private javax.swing.JCheckBox box_nama;
    private javax.swing.JCheckBox box_tp;
    private javax.swing.JButton btn_cetakkrt;
    private javax.swing.JButton btn_hapusm;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton button_cari;
    private javax.swing.JButton button_ulang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton lki;
    private javax.swing.JRadioButton pr;
    private javax.swing.JTextArea ta_alamat;
    private javax.swing.JTable tabel_member;
    private javax.swing.JTextField text_alemp;
    private javax.swing.JTextArea text_almtn;
    private javax.swing.JTextField text_cari;
    private javax.swing.JTextField text_email;
    private javax.swing.JTextField text_idm;
    private javax.swing.JTextField text_jk;
    private javax.swing.JTextField text_nama;
    private javax.swing.JTextField text_nlp;
    private javax.swing.JTextField text_no;
    private javax.swing.JTextField text_nohpp;
    private javax.swing.JTextField text_tl;
    private javax.swing.JTextField text_tlp;
    private javax.swing.JTextField text_tmpt;
    private javax.swing.JTextField text_tp;
    // End of variables declaration//GEN-END:variables

}
