package MAIN_APP;

/**
 *
 * @author KRISNAARISANDI(MEGUMI)
 */
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public final class Beras_Masuk extends javax.swing.JFrame {
    Locale locale = new Locale("id", "ID");
    private Connection con;
    private Statement stat;
    private ResultSet res;
    private final DefaultTableModel tabmode;
    private DefaultTableModel tt;
    public PreparedStatement pst, pst2;
    public String kdsp, namasp;
    java.util.Date tglsekarang = new java.util.Date();
    private final SimpleDateFormat smpdtfmt = new SimpleDateFormat("dd-MMMM-yyyy", locale);
    public boolean datab;

    public Beras_Masuk() {
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
        datatabel();
        button_tambahberas.setEnabled(false);
        Object[] Baris = {"ID TRANSAKSI", "KD SUPPLIER", "KD BERAS", "MERK BERAS", "HARGA BERAS", "JENIS BERAS", "JENIS KARUNG ", "JUMLAH", "TANGGAL", "TOTAL", "STATUS"};
        tabmode = new DefaultTableModel(null, Baris);
        tabel_trans.setModel(tabmode);
        tabel_beras.setEnabled(false);
        button_selesai.setEnabled(false);
        btn_daftarsupp.setEnabled(false);
        btn_tambah.setEnabled(false);
        btn_hpus.setEnabled(false);
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

    private void autonumber() {
        try {

            String sql = "SELECT MAX(RIGHT(no_transaksi,4)) AS NO FROM beras_masuk";
            stat = con.createStatement();
            res = stat.executeQuery(sql);
            while (res.next()) {
                if (res.first() == false) {
                    text_trans.setText("TRANS-001");
                } else {
                    res.last();
                    int auto_id = res.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int idmember = no.length();

                    for (int j = 0; j < 4 - idmember; j++) {
                        no = "0" + no;
                    }
                    text_trans.setText("TRANS-" + no);
                }
            }
            res.close();
            stat.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "ERROR: \n" + e.toString(),
                    "Kesalahan", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void itemTerpilih2() {
        Buat_Supplier Pp = new Buat_Supplier();
        Pp.sp = this;
        text_kdsp.setText(kdsp);
        text_namasup.setText(namasp);
    }

    public void datatabel() {

        Object[] Baris = {"ID BERAS", "MERK BERAS", "HARGA", "JENIS BERAS", "JENIS KARUNG", "STOCK"};
        tt = new DefaultTableModel(null, Baris);
        String cariitem = text_cari.getText();
        try {

            String sql = "SELECT * FROM beras order by idberas asc";
            stat = con.createStatement();
            res = stat.executeQuery(sql);
            if (box_id.isSelected()) {

                String sql2 = "SELECT * FROM beras where idberas like '%" + cariitem + "%' order by idberas asc";
                stat = con.createStatement();
                res = stat.executeQuery(sql2);
            } else if (box_merk.isSelected()) {
                String sql3 = "SELECT * FROM beras where merkberas like '%" + cariitem + "%' order by merkberas asc";
                stat = con.createStatement();
                res = stat.executeQuery(sql3);
            } else if (box_jenis.isSelected()) {
                String sql3 = "SELECT * FROM beras where jenisberas like '%" + cariitem + "%' order by merkberas asc";
                stat = con.createStatement();
                res = stat.executeQuery(sql3);
            } else if (box_brt.isSelected()) {
                String sql4 = "SELECT * FROM beras where jeniskarung like '%" + cariitem + "%' order by jeniskarung asc";
                stat = con.createStatement();
                res = stat.executeQuery(sql4);
            } else {
                String sql4 = "SELECT * FROM beras where merkberas like '%" + cariitem + "%' or idberas like '%" + cariitem + "%' order by merkberas asc";
                stat = con.createStatement();
                res = stat.executeQuery(sql4);
            }
            while (res.next()) {
                tt.addRow(new Object[]{
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6)
                });
            }

            tabel_beras.setModel(tt);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data gagal dipanggil" + e);
        }

    }

    public void tabel() {
        String a = text_trans.getText();
        String b = text_kdsp.getText();
        String c = text_idberas.getText();
        String d = text_merk.getText();
        int e = Integer.parseInt(text_harga.getText());
        Object f = combo_jenisberas.getSelectedItem();
        Object g = combo_jeniskarung.getSelectedItem();
        int h = Integer.parseInt(text_stock.getText());
        String i = smpdtfmt.format(datec.getDate());
        String j = null;
        String k = Textstatus.getText();
        tabmode.addRow(new Object[]{a, b, c, d, e, f, g, h, i, j, k});
        tabel_trans.setModel(tabmode);

    }

    public void tabel2() {
        String a = text_trans.getText();
        String b = text_kdsp.getText();
        String c = text_idberas.getText();
        String d = text_merk.getText();
        int e = Integer.parseInt(text_harga.getText());
        Object f = combo_jenisberas.getSelectedItem();
        Object g = combo_jeniskarung.getSelectedItem();
        int h = Integer.parseInt(text_tambah_unit.getText());
        int j = Integer.parseInt(text_totalstock.getText());
        String i = smpdtfmt.format(datec.getDate());
        String k = Textstatus.getText();
        tabmode.addRow(new Object[]{a, b, c, d, e, f, g, h, i, j, k});
        tabel_trans.setModel(tabmode);
    }

    public void kosong() {
        text_idberas.setText("");
        text_merk.setText("");
        text_stock.setText("");
        text_harga.setText("");
        combo_jeniskarung.setSelectedItem("");
        combo_jenisberas.setSelectedItem("");
        //text_kdsp.setText("");
        //text_namasup.setText("");

    }

    public void kosong2() {
        combo_jeniskarung.setSelectedItem(null);
        combo_jenisberas.setSelectedItem(null);
        text_totalstock.setText("");
        text_tambah_unit.setText("");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabel_sup = new javax.swing.JInternalFrame();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_supplier = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        text_merk = new javax.swing.JTextField();
        text_idberas = new javax.swing.JTextField();
        button_ulang = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        combo_jenisberas = new javax.swing.JComboBox<>();
        btn_daftarsupp = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        text_kdsp = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        text_namasup = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        text_trans = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_trans = new javax.swing.JTable();
        btn_hpus = new javax.swing.JButton();
        btn_tambah = new javax.swing.JButton();
        Textstatus = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        button_tambahberas = new javax.swing.JButton();
        button_baru = new javax.swing.JButton();
        button_selesai = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        text_stock = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        text_tambah_unit = new javax.swing.JTextField();
        text_harga = new javax.swing.JTextField();
        button_tambah_unit = new javax.swing.JButton();
        combo_jeniskarung = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        text_totalstock = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_beras = new javax.swing.JTable();
        bg1 = new Gambar.bg();
        jLabel11 = new javax.swing.JLabel();
        text_cari = new javax.swing.JTextField();
        button_cari = new javax.swing.JButton();
        box_id = new javax.swing.JCheckBox();
        box_merk = new javax.swing.JCheckBox();
        box_jenis = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        box_brt = new javax.swing.JCheckBox();
        datec = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();

        tabel_sup.setVisible(true);

        tabel_supplier.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_supplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_supplierMouseClicked(evt);
            }
        });
        tabel_supplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabel_supplierKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tabel_supplier);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 570, Short.MAX_VALUE)
            .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel3Layout.createSequentialGroup()
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 570, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 352, Short.MAX_VALUE)
            .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel3Layout.createSequentialGroup()
                    .add(18, 18, 18)
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .add(19, 19, 19)))
        );

        org.jdesktop.layout.GroupLayout tabel_supLayout = new org.jdesktop.layout.GroupLayout(tabel_sup.getContentPane());
        tabel_sup.getContentPane().setLayout(tabel_supLayout);
        tabel_supLayout.setHorizontalGroup(
            tabel_supLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(tabel_supLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        tabel_supLayout.setVerticalGroup(
            tabel_supLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(tabel_supLayout.createSequentialGroup()
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 28, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BERAS MASUK");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 4), "MENU BERAS MASUK", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel1.setText("Merk :");

        jLabel2.setText("ID Beras :");

        text_idberas.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        text_idberas.setSelectedTextColor(new java.awt.Color(51, 255, 51));

        button_ulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Refresh_15px.png"))); // NOI18N
        button_ulang.setText("Muat Ulang");
        button_ulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ulangActionPerformed(evt);
            }
        });

        jLabel6.setText("Jenis Beras :");

        combo_jenisberas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "PULEN/IR64", "PULEN/IR42", "PANDAN WANGI", "KETAN HITAM", "KETAN PUTIH", "BERAS MERAH" }));
        combo_jenisberas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_jenisberasActionPerformed(evt);
            }
        });

        btn_daftarsupp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Supplier_15px_1.png"))); // NOI18N
        btn_daftarsupp.setText("Daftar Supplier");
        btn_daftarsupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_daftarsuppActionPerformed(evt);
            }
        });

        jLabel12.setText("Kode Supplier :");

        text_kdsp.setEditable(false);
        text_kdsp.setBackground(new java.awt.Color(0, 120, 215));
        text_kdsp.setForeground(new java.awt.Color(255, 255, 255));
        text_kdsp.setDisabledTextColor(new java.awt.Color(255, 0, 0));

        jLabel13.setText("Nama Supplier :");

        text_namasup.setEditable(false);
        text_namasup.setBackground(new java.awt.Color(0, 120, 215));
        text_namasup.setForeground(new java.awt.Color(255, 255, 255));
        text_namasup.setDisabledTextColor(new java.awt.Color(255, 0, 0));

        jLabel14.setText("ID Transaksi :");

        text_trans.setEditable(false);
        text_trans.setBackground(new java.awt.Color(0, 120, 215));
        text_trans.setForeground(new java.awt.Color(255, 255, 255));

        tabel_trans.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tabel_trans.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_trans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_transMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_trans);

        btn_hpus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Clear_Shopping_Cart_15px.png"))); // NOI18N
        btn_hpus.setText("Hapus");
        btn_hpus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hpusActionPerformed(evt);
            }
        });

        btn_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Add_Shopping_Cart_15px.png"))); // NOI18N
        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        Textstatus.setEditable(false);
        Textstatus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Textstatus.setForeground(new java.awt.Color(255, 0, 0));

        jLabel9.setText("Status :");

        button_tambahberas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_New_15px.png"))); // NOI18N
        button_tambahberas.setText("Beras Baru");
        button_tambahberas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_tambahberasActionPerformed(evt);
            }
        });

        button_baru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Split_Transaction_Euro_15px.png"))); // NOI18N
        button_baru.setText("Transaksi Baru");
        button_baru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_baruActionPerformed(evt);
            }
        });

        button_selesai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Task_Completed_15px.png"))); // NOI18N
        button_selesai.setText("Selesai Transaksi");
        button_selesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_selesaiActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Graph_Report_15px.png"))); // NOI18N
        jButton1.setText("Riwayat / Laporan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(2, 2, 2)
                                .add(button_baru)
                                .add(9, 9, 9)
                                .add(button_selesai))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(btn_daftarsupp)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(button_ulang, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(26, 26, 26)
                                .add(text_trans, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel2)
                                .add(53, 53, 53)
                                .add(text_idberas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel1)
                                .add(71, 71, 71)
                                .add(text_merk, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel6)
                                .add(39, 39, 39)
                                .add(combo_jenisberas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(25, 25, 25)
                                .add(Textstatus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel13)
                                    .add(jLabel12))
                                .add(26, 26, 26)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(text_kdsp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(text_namasup, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(12, 12, 12)
                        .add(button_tambahberas)))
                .add(24, 24, 24)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(btn_tambah)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(btn_hpus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jButton1))
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 1000, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(4, 4, 4)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(button_baru)
                            .add(button_selesai))
                        .add(28, 28, 28)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(btn_daftarsupp)
                            .add(button_ulang))
                        .add(12, 12, 12)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(3, 3, 3)
                                .add(jLabel12))
                            .add(text_kdsp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(6, 6, 6)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(5, 5, 5)
                                .add(jLabel13))
                            .add(text_namasup, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(18, 18, 18)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(3, 3, 3)
                                .add(jLabel14))
                            .add(text_trans, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(6, 6, 6)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(3, 3, 3)
                                .add(jLabel2))
                            .add(text_idberas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(6, 6, 6)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(3, 3, 3)
                                .add(jLabel1))
                            .add(text_merk, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(6, 6, 6)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel6)
                            .add(combo_jenisberas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(11, 11, 11)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(3, 3, 3)
                                .add(jLabel9))
                            .add(Textstatus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 290, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(14, 14, 14)
                        .add(button_tambahberas))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(btn_tambah)
                            .add(btn_hpus)
                            .add(jButton1))))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 4), " STATUS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N

        jLabel3.setText("Stock Awal :");

        jLabel4.setText("Jenis Karung :");

        jLabel5.setText("Harga :");

        jLabel8.setText("Tambah Unit :");

        text_tambah_unit.setForeground(new java.awt.Color(255, 255, 255));
        text_tambah_unit.setDisabledTextColor(new java.awt.Color(255, 0, 0));

        text_harga.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        text_harga.setDragEnabled(true);

        button_tambah_unit.setText("+");
        button_tambah_unit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_tambah_unitActionPerformed(evt);
            }
        });

        combo_jeniskarung.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "  ", "20 KG", "25 KG", "50 KG" }));

        jLabel7.setText("Total Stock :");

        text_totalstock.setEditable(false);
        text_totalstock.setBackground(new java.awt.Color(255, 255, 255));
        text_totalstock.setForeground(new java.awt.Color(255, 255, 255));
        text_totalstock.setSelectionColor(new java.awt.Color(255, 255, 255));

        jLabel10.setText("Tanggal Masuk :");

        tabel_beras.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tabel_beras.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_beras.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabel_beras.setName("DATA"); // NOI18N
        tabel_beras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_berasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_beras);

        org.jdesktop.layout.GroupLayout bg1Layout = new org.jdesktop.layout.GroupLayout(bg1);
        bg1.setLayout(bg1Layout);
        bg1Layout.setHorizontalGroup(
            bg1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 131, Short.MAX_VALUE)
        );
        bg1Layout.setVerticalGroup(
            bg1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 109, Short.MAX_VALUE)
        );

        jLabel11.setText("Cari Beras :");

        text_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                text_cariKeyPressed(evt);
            }
        });

        button_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Search_25px.png"))); // NOI18N
        button_cari.setText("Cari");
        button_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_cariActionPerformed(evt);
            }
        });

        box_id.setText("ID");
        box_id.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        box_id.setMargin(new java.awt.Insets(0, 0, 0, 0));

        box_merk.setText("Merk");
        box_merk.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        box_merk.setMargin(new java.awt.Insets(0, 0, 0, 0));

        box_jenis.setText("Jenis Beras");

        jLabel15.setText("Karung");

        jLabel16.setText("Karung");

        box_brt.setText("Jenis Karung");

        datec.setDateFormatString("dd-MMMM-yyyy");

        jLabel17.setText("Rupiah");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel7)
                    .add(jLabel4)
                    .add(jLabel5)
                    .add(jLabel10)
                    .add(jLabel3))
                .add(23, 23, 23)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(combo_jeniskarung, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(text_totalstock, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel15))
                            .add(datec, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 134, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(text_harga, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel17)))
                        .add(137, 137, 137)
                        .add(bg1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(text_stock, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel16)
                        .add(32, 32, 32)
                        .add(jLabel8)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(text_tambah_unit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(button_tambah_unit)))
                .add(18, 18, 18)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 770, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(box_id)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(box_merk)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(box_jenis)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(box_brt))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel11)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(text_cari, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(button_cari)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(10, 10, 10)
                                .add(jLabel3))
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(7, 7, 7)
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel16)
                                    .add(text_stock, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(6, 6, 6)
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel8)
                                    .add(text_tambah_unit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(button_tambah_unit))))
                        .add(6, 6, 6))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(text_cari, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(button_cari)))
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(3, 3, 3)
                                .add(jLabel7)
                                .add(12, 12, 12)
                                .add(jLabel4)
                                .add(12, 12, 12)
                                .add(jLabel5)
                                .add(14, 14, 14)
                                .add(jLabel10))
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(text_totalstock, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabel15))
                                .add(6, 6, 6)
                                .add(combo_jeniskarung, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(6, 6, 6)
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(text_harga, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabel17))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(datec, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(15, 15, 15)
                                .add(bg1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(117, 117, 117))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(5, 5, 5)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(box_merk, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(box_id, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(box_jenis, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(box_brt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabel_berasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_berasMouseClicked
        if (text_trans.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "MULAI TRANSAKSI DAHULU");
        } else {
            int bar = tabel_beras.getSelectedRow();
            String a = tt.getValueAt(bar, 0).toString();
            String b = tt.getValueAt(bar, 1).toString();
            String c = tt.getValueAt(bar, 2).toString();
            String d = tt.getValueAt(bar, 3).toString();
            String e = tt.getValueAt(bar, 4).toString();
            String f = tt.getValueAt(bar, 5).toString();

            text_idberas.setText(a);
            text_idberas.setEnabled(false);
            text_merk.setText(b);
            text_merk.setEnabled(false);
            text_harga.setText(c);
            text_harga.setEnabled(false);
            combo_jenisberas.setSelectedItem(d);
            combo_jenisberas.setEnabled(false);
            combo_jeniskarung.setSelectedItem(e);
            combo_jeniskarung.setEnabled(false);
            text_stock.setText(f);

            text_tambah_unit.setEnabled(true);
            text_tambah_unit.setBackground(Color.RED);
            button_tambahberas.setEnabled(true);
            text_stock.setEnabled(false);
            text_totalstock.setEnabled(true);
            // text_totalstock.setForeground(Color.WHITE);
            Textstatus.setText("RESTOCK");
        }

    }//GEN-LAST:event_tabel_berasMouseClicked

    private void button_tambah_unitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_tambah_unitActionPerformed
        try {
            int stock_awal = Integer.parseInt(text_stock.getText());
            int tambah_unit = Integer.parseInt(text_tambah_unit.getText());
            int stock_sekarang = stock_awal + tambah_unit;
            {
                //text_tambah_unit.setText("");
            }
            text_totalstock.setText(String.valueOf(stock_sekarang));
        } catch (NumberFormatException e) {
           JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_button_tambah_unitActionPerformed

    private void button_baruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_baruActionPerformed
        autonumber();
        text_tambah_unit.setEnabled(false);
        text_tambah_unit.setBackground(Color.BLACK);
        text_totalstock.setEnabled(false);
        text_totalstock.setBackground(Color.RED);
        button_baru.setEnabled(false);
        Textstatus.setText("BARU");
        tabel_beras.setEnabled(true);
        //button_selesai.setEnabled(true);
        btn_daftarsupp.setEnabled(true);
        btn_tambah.setEnabled(true);
        btn_hpus.setEnabled(true);

    }//GEN-LAST:event_button_baruActionPerformed

    private void button_tambahberasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_tambahberasActionPerformed
        Textstatus.setText("BARU");
        kosong();
        kosong2();
        button_tambahberas.setEnabled(false);
        text_idberas.setEnabled(true);
        text_merk.setEnabled(true);
        combo_jenisberas.setEnabled(true);
        combo_jeniskarung.setEnabled(true);
        text_harga.setEnabled(true);
        text_stock.setEnabled(true);
        text_tambah_unit.setEnabled(false);
    }//GEN-LAST:event_button_tambahberasActionPerformed

    private void button_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_cariActionPerformed
        datatabel();
    }//GEN-LAST:event_button_cariActionPerformed

    private void text_cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_text_cariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            button_cari.doClick();
        } else if (evt.getKeyCode() == KeyEvent.VK_F5) {
            new Beras_Masuk().setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_text_cariKeyPressed

    private void btn_daftarsuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_daftarsuppActionPerformed
        Buat_Supplier x = new Buat_Supplier();
        x.sp = this;
        x.setVisible(true);
        x.setResizable(false);
    }//GEN-LAST:event_btn_daftarsuppActionPerformed

    private void combo_jenisberasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_jenisberasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_jenisberasActionPerformed

    private void button_selesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_selesaiActionPerformed
        try {
            if (text_trans.getText().equals("") || text_kdsp.getText().equals("") || text_namasup.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Lengkapi Data Terlebih Dahulu");
            } else {
                if (JOptionPane.showConfirmDialog(null, "SELESAI TRANSAKSI?", "Transaksi!!!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    int t = tabel_trans.getRowCount();
                    for (int i = 0; i < t; i++) {
                        String sql = "insert into beras values(?,?,?,?,?,?)";
                        String sql2 = "insert into beras_masuk values(?,?,?,?,?,?,?,?,?)";
                        String cariitem = (String) tabel_trans.getValueAt(i, 10);
                        pst = con.prepareStatement(sql2);
                        pst.setString(1, tabel_trans.getValueAt(i, 0).toString());
                        pst.setString(2, tabel_trans.getValueAt(i, 1).toString());
                        pst.setString(3, tabel_trans.getValueAt(i, 2).toString());
                        pst.setString(4, tabel_trans.getValueAt(i, 3).toString());
                        pst.setString(5, tabel_trans.getValueAt(i, 4).toString());
                        pst.setString(6, tabel_trans.getValueAt(i, 5).toString());
                        pst.setString(7, tabel_trans.getValueAt(i, 6).toString());
                        pst.setString(8, tabel_trans.getValueAt(i, 7).toString());
                        pst.setString(9, smpdtfmt.format(datec.getDate()));
                        pst.executeUpdate();

                        if (cariitem.equals("BARU")) {
                            pst = con.prepareStatement(sql);
                            pst.setString(1, tabel_trans.getValueAt(i, 2).toString());
                            pst.setString(2, tabel_trans.getValueAt(i, 3).toString());
                            pst.setString(3, tabel_trans.getValueAt(i, 4).toString());
                            pst.setString(4, tabel_trans.getValueAt(i, 5).toString());
                            pst.setString(5, tabel_trans.getValueAt(i, 6).toString());
                            pst.setString(6, tabel_trans.getValueAt(i, 7).toString());
                            pst.executeUpdate();

                        } else if (cariitem.equals("RESTOCK")) {
                            int tx = tabel_trans.getRowCount();
                            for (int x = 0; x < tx; x++) {

                                pst.executeUpdate("update beras set "
                                        + "stokkarung='" + tabel_trans.getValueAt(i, 9) + "'"
                                        + " where " + "idberas='" + tabel_trans.getValueAt(i, 2) + "'");

                            }

                        }

                    }
                }
                JOptionPane.showMessageDialog(null, "Transaksi Berhasil");
                new Beras_Masuk().setVisible(true);
                dispose();
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }//GEN-LAST:event_button_selesaiActionPerformed

    private void button_ulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ulangActionPerformed
        new Beras_Masuk().setVisible(true);
        dispose();
    }//GEN-LAST:event_button_ulangActionPerformed

    private void tabel_transMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_transMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabel_transMouseClicked

    private void btn_hpusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hpusActionPerformed
        int index = tabel_trans.getSelectedRow();
        tabmode.removeRow(index);
        tabel_trans.setModel(tabmode);


    }//GEN-LAST:event_btn_hpusActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        if (text_kdsp.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Lengkapi Data Terlebih Dahulu");
        } else if (!text_totalstock.getText().equals("")) {
            tabel2();
        } else {
            tabel();
        }
        //text_stock.setEnabled(true);
        kosong();
        kosong2();
        button_selesai.setEnabled(true);
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void tabel_supplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_supplierMouseClicked

    }//GEN-LAST:event_tabel_supplierMouseClicked

    private void tabel_supplierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabel_supplierKeyPressed

    }//GEN-LAST:event_tabel_supplierKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        ImageIcon ico = new ImageIcon("src/Gambar/logo/ico.png");
        setIconImage(ico.getImage());
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new Riwayat_Beras_Masuk().setVisible(true);
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
            for (UIManager.LookAndFeelInfo installedLookAndFeel : installedLookAndFeels) {
                if ("Nimbus".equals(installedLookAndFeel.getName())) {
                    javax.swing.UIManager.setLookAndFeel(installedLookAndFeel.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Beras_Masuk.class
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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
            new Beras_Masuk().setVisible(true);

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Textstatus;
    private Gambar.bg bg1;
    private javax.swing.JCheckBox box_brt;
    private javax.swing.JCheckBox box_id;
    private javax.swing.JCheckBox box_jenis;
    private javax.swing.JCheckBox box_merk;
    private javax.swing.JButton btn_daftarsupp;
    private javax.swing.JButton btn_hpus;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton button_baru;
    private javax.swing.JButton button_cari;
    private javax.swing.JButton button_selesai;
    private javax.swing.JButton button_tambah_unit;
    private javax.swing.JButton button_tambahberas;
    private javax.swing.JButton button_ulang;
    private javax.swing.JComboBox<String> combo_jenisberas;
    private javax.swing.JComboBox<String> combo_jeniskarung;
    private com.toedter.calendar.JDateChooser datec;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tabel_beras;
    private javax.swing.JInternalFrame tabel_sup;
    private javax.swing.JTable tabel_supplier;
    private javax.swing.JTable tabel_trans;
    private javax.swing.JTextField text_cari;
    private javax.swing.JTextField text_harga;
    private javax.swing.JTextField text_idberas;
    private javax.swing.JTextField text_kdsp;
    private javax.swing.JTextField text_merk;
    private javax.swing.JTextField text_namasup;
    private javax.swing.JTextField text_stock;
    private javax.swing.JTextField text_tambah_unit;
    private javax.swing.JTextField text_totalstock;
    private javax.swing.JTextField text_trans;
    // End of variables declaration//GEN-END:variables

  

}
