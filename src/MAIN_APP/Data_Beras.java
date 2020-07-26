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

public final class Data_Beras extends javax.swing.JFrame {

    private Connection con;
    private Statement stat;
    private ResultSet res;
    private DefaultTableModel tabmode;
    public Beras_Keluar sp = null;
    public Beras_Keluar itm = null;

    public Data_Beras() {
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
        datatabel();
        tombol_nonaktif();
        hitung();
        hitung2();

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

    public void datatabel() {

        Object[] Baris = {"ID BERAS", "MERK BERAS", "HARGA BERAS", "JENIS BERAS", "JENIS KARUNG", "STOK BERAS"};
        tabmode = new DefaultTableModel(null, Baris);
        tabel_beras.setModel(tabmode);
        tabel_beras.setModel(tabmode);
        String cariitem = text_cari.getText();
        try {

            String sql = "SELECT * FROM beras";
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
                String sql4 = "SELECT * FROM beras where jenisberas like '%" + cariitem + "%' or idberas like '%" + cariitem + "%' order by merkberas asc";
                stat = con.createStatement();
                res = stat.executeQuery(sql4);
            } else if (box_brt.isSelected()) {
                String sql5 = "SELECT * FROM beras where jeniskarung like '%" + cariitem + "%' order by jeniskarung asc";
                stat = con.createStatement();
                res = stat.executeQuery(sql5);
            } else {
                String sql4 = "SELECT * FROM beras where merkberas like '%" + cariitem + "%' or idberas like '%" + cariitem + "%' order by merkberas asc";
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
                    res.getString(6),});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data gagal dipanggil" + e);
        }

    }

    private void data() {
        int bar = tabel_beras.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();

        text_idberas.setText(a);
        text_ubahid.setText(a);
        text_merk.setText(b);
        text_harga.setText(c);
        combo_jenisberas.setSelectedItem(d);
        combo_jeniskarung.setSelectedItem(e);
        text_stock.setText(f);
        text_harga.setText(c);
    }

    private void data_panggil() {

        int bar = tabel_beras.getSelectedRow();
        itm.idbrs = tabel_beras.getValueAt(bar, 0).toString();
        itm.namabrs = tabel_beras.getValueAt(bar, 1).toString();
        itm.jenisbrs = tabel_beras.getValueAt(bar, 3).toString();
        itm.jeniskrg = tabel_beras.getValueAt(bar, 4).toString();
        itm.hrgbrs = tabel_beras.getValueAt(bar, 2).toString();
        itm.stokbrs = tabel_beras.getValueAt(bar, 5).toString();
        itm.itemBeras();
        this.dispose();

    }

    private void tombol_nonaktif() {
        button_edit.setEnabled(false);
        button_hapus.setEnabled(false);
    }

    private void tombol_aktif() {
        button_edit.setEnabled(true);
        button_hapus.setEnabled(true);
    }

    public void hitung() {
        int i = tabel_beras.getRowCount();
        IDBERAS.setText("" + i);

        int totalkarung = 0;
        for (int x = 0; x < tabel_beras.getRowCount(); x++) {
            int amount;
            amount = Integer.valueOf(tabel_beras.getValueAt(x, 5).toString());
            totalkarung += amount;
        }
        TOTALKARUNG.setText(Integer.toString(totalkarung));
    }

    public void hitung2() {

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        IDBERAS = new javax.swing.JTextField();
        TOTALKARUNG = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        text_cari = new javax.swing.JTextField();
        text_merk = new javax.swing.JTextField();
        box_id = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        text_idberas = new javax.swing.JTextField();
        box_merk = new javax.swing.JCheckBox();
        button_cari = new javax.swing.JButton();
        button_ulang = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        combo_jenisberas = new javax.swing.JComboBox<>();
        bg1 = new Gambar.bg();
        jLabel4 = new javax.swing.JLabel();
        combo_jeniskarung = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        text_stock = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        text_harga = new javax.swing.JTextField();
        button_hapus = new javax.swing.JButton();
        button_edit = new javax.swing.JButton();
        ic_karung1 = new Gambar.ic_karung();
        box_jenis = new javax.swing.JCheckBox();
        text_ubahid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        box_brt = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_beras = new javax.swing.JTable();

        IDBERAS.setText("jTextField1");

        TOTALKARUNG.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DATA BERAS");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATA - DATA BERAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel1.setText("ID Beras :");

        jLabel2.setText("Merk :");

        text_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                text_cariKeyPressed(evt);
            }
        });

        box_id.setText("ID");
        box_id.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        box_id.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel11.setText("Cari Beras :");

        text_idberas.setEditable(false);
        text_idberas.setBackground(new java.awt.Color(0, 153, 255));
        text_idberas.setForeground(new java.awt.Color(255, 255, 255));
        text_idberas.setDisabledTextColor(new java.awt.Color(255, 0, 0));

        box_merk.setText("Merk");
        box_merk.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        box_merk.setMargin(new java.awt.Insets(0, 0, 0, 0));

        button_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Search_25px.png"))); // NOI18N
        button_cari.setText("Cari");
        button_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_cariActionPerformed(evt);
            }
        });

        button_ulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Refresh_25px_2.png"))); // NOI18N
        button_ulang.setText("Muat Ulang");
        button_ulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ulangActionPerformed(evt);
            }
        });

        jLabel6.setText("Jenis Beras :");

        combo_jenisberas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "PULEN/IR64", "PULEN/IR42", "PANDAN WANGI", "KETAN HITAM", "KETAN PUTIH", "BERAS MERAH" }));

        org.jdesktop.layout.GroupLayout bg1Layout = new org.jdesktop.layout.GroupLayout(bg1);
        bg1.setLayout(bg1Layout);
        bg1Layout.setHorizontalGroup(
            bg1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 198, Short.MAX_VALUE)
        );
        bg1Layout.setVerticalGroup(
            bg1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );

        jLabel4.setText("Jenis Karung :");

        combo_jeniskarung.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "  ", "20 KG", "25 KG", "50 KG" }));

        jLabel7.setText("Stok :");

        jLabel5.setText("Harga :");

        text_harga.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        text_harga.setDragEnabled(true);

        button_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Erase_25px.png"))); // NOI18N
        button_hapus.setText("Hapus");
        button_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_hapusActionPerformed(evt);
            }
        });

        button_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Edit_Property_25px.png"))); // NOI18N
        button_edit.setText("Ubah");
        button_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_editActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout ic_karung1Layout = new org.jdesktop.layout.GroupLayout(ic_karung1);
        ic_karung1.setLayout(ic_karung1Layout);
        ic_karung1Layout.setHorizontalGroup(
            ic_karung1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );
        ic_karung1Layout.setVerticalGroup(
            ic_karung1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );

        box_jenis.setText("Jenis Beras");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Available_Updates_15px.png"))); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon/icons8_Print_25px.png"))); // NOI18N
        jButton1.setText("Cetak Laporan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        box_brt.setText("Jenis Karung");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(jButton1)
                                    .add(jLabel4)
                                    .add(jLabel7)
                                    .add(jLabel5))
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel11))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel6))
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel2))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(combo_jeniskarung, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(ic_karung1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(bg1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, text_harga)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, text_stock)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, text_merk, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(button_edit))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jPanel1Layout.createSequentialGroup()
                                                .add(text_cari, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                                .add(button_cari))
                                            .add(combo_jenisberas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(button_ulang))
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(text_idberas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(6, 6, 6)
                                        .add(jLabel3)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(text_ubahid, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(3, 3, 3)
                                        .add(box_id)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(box_merk)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(box_jenis)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(box_brt)))
                                .add(0, 222, Short.MAX_VALUE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(button_hapus))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(text_cari, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(button_cari)
                                    .add(button_ulang)
                                    .add(jLabel11))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(box_id)
                                    .add(box_merk)
                                    .add(box_jenis)
                                    .add(box_brt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(21, 21, 21)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(text_idberas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabel1)
                                    .add(text_ubahid, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabel3))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(text_merk, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabel2))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(combo_jenisberas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabel6)))
                            .add(ic_karung1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel4)
                            .add(combo_jeniskarung, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(text_stock, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel7)))
                    .add(bg1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(button_hapus)
                        .add(button_edit))
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(text_harga, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel5)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 16, Short.MAX_VALUE)
                .add(jButton1))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DAFTAR BERAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Copyright@KrisnaArisandi");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("2019");

        tabel_beras.setAutoCreateRowSorter(true);
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
        tabel_beras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_berasMouseClicked(evt);
            }
        });
        tabel_beras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabel_berasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabel_berasKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_beras);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jScrollPane2)
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(jLabel8)
                        .add(309, 309, 309))))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLabel9)
                .add(372, 372, 372))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 266, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jLabel8)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel9)
                .add(37, 37, 37))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 365, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_cariActionPerformed
        datatabel();
    }//GEN-LAST:event_button_cariActionPerformed

    private void text_cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_text_cariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            button_cari.doClick();
        } else if (evt.getKeyCode() == KeyEvent.VK_F5) {
            new Data_Beras().setVisible(true);
            dispose();
        }

    }//GEN-LAST:event_text_cariKeyPressed

    private void button_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_editActionPerformed
        try {
            if (text_idberas.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Pilih Data Beras Dahulu!!!");
            } else {
                stat.executeUpdate("update beras set "
                        + "idberas='" + text_ubahid.getText() + "',"
                        + "merkberas='" + text_merk.getText() + "',"
                        + "stokkarung='" + text_stock.getText() + "',"
                        + "hargaberas='" + text_harga.getText() + "',"
                        + "jenisberas='" + combo_jenisberas.getSelectedItem() + "',"
                        + "jeniskarung='" + combo_jeniskarung.getSelectedItem() + "'"
                        + " where " + "idberas='" + text_idberas.getText() + "'");

                stat.executeUpdate("update beras_masuk set "
                        + "harga_beras='" + text_harga.getText() + "',"
                        + "merk_beras='" + text_merk.getText() + "'"
                        + " where " + "kd_beras='" + text_idberas.getText() + "'");

                JOptionPane.showMessageDialog(rootPane, "Data berhasil Diubah");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
        new Data_Beras().setVisible(true);
        dispose();

    }//GEN-LAST:event_button_editActionPerformed

    private void button_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_hapusActionPerformed
        try {

            stat.executeUpdate("delete from beras where " + "idberas'" + text_idberas.getText() + "'");
            JOptionPane.showMessageDialog(null, "Berhasil Hapus Data Beras");

        } catch (SQLException ex) {
            Logger.getLogger(Data_Beras.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Data_Beras().setVisible(true);
        dispose();
    }//GEN-LAST:event_button_hapusActionPerformed

    private void button_ulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ulangActionPerformed
        new Data_Beras().setVisible(true);
        dispose();
    }//GEN-LAST:event_button_ulangActionPerformed

    private void tabel_berasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_berasMouseClicked
        tombol_aktif();
        data();
        data_panggil();

    }//GEN-LAST:event_tabel_berasMouseClicked

    private void tabel_berasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabel_berasKeyPressed
        tombol_aktif();
        data();
    }//GEN-LAST:event_tabel_berasKeyPressed

    private void tabel_berasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabel_berasKeyReleased
        tombol_aktif();
        data();
    }//GEN-LAST:event_tabel_berasKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Locale locale = new Locale("id", "ID");
            Locale.setDefault(locale);
            HashMap parameter = new HashMap();
            String path = "./src/report/stok_beras.jasper";
            parameter.put("TOTALBERAS", IDBERAS.getText());
            parameter.put("TOTALKARUNG", TOTALKARUNG.getText());

            JasperPrint print = JasperFillManager.fillReport(path, parameter, con);
            JasperViewer.viewReport(print, false);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(rootPane, "DokumenTidak Ada " + ex);
        }
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
            java.util.logging.Logger.getLogger(Data_Beras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Data_Beras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Data_Beras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Data_Beras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            new Data_Beras().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IDBERAS;
    private javax.swing.JTextField TOTALKARUNG;
    private Gambar.bg bg1;
    private javax.swing.JCheckBox box_brt;
    private javax.swing.JCheckBox box_id;
    private javax.swing.JCheckBox box_jenis;
    private javax.swing.JCheckBox box_merk;
    private javax.swing.JButton button_cari;
    private javax.swing.JButton button_edit;
    private javax.swing.JButton button_hapus;
    private javax.swing.JButton button_ulang;
    private javax.swing.JComboBox<String> combo_jenisberas;
    private javax.swing.JComboBox<String> combo_jeniskarung;
    private Gambar.ic_karung ic_karung1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabel_beras;
    private javax.swing.JTextField text_cari;
    private javax.swing.JTextField text_harga;
    private javax.swing.JTextField text_idberas;
    private javax.swing.JTextField text_merk;
    private javax.swing.JTextField text_stock;
    private javax.swing.JTextField text_ubahid;
    // End of variables declaration//GEN-END:variables

}
