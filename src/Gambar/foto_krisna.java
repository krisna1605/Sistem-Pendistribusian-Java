/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gambar;

/**
 *
 * @author Krisna
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class foto_krisna extends JPanel {
    private Image image2;

    public foto_krisna() {
    image2 = new ImageIcon(getClass().getResource("/Gambar/krisna.jpg")).getImage();
}
    
@Override
protected void paintComponent(Graphics grphcs){
    super.paintComponent(grphcs);
    
    Graphics2D gd = (Graphics2D) grphcs.create();
    gd.drawImage(image2, 0, 0, getWidth(), getHeight(), null);
    gd.dispose();
}
}