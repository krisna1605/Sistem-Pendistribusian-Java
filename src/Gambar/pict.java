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


public class pict extends JPanel {
    private Image image;

    public pict() {
    image = new ImageIcon(getClass().getResource("/Gambar/karungberas.jpg")).getImage();
}
    
@Override
protected void paintComponent(Graphics grphcs){
    super.paintComponent(grphcs);
    
    Graphics2D gd = (Graphics2D) grphcs.create();
    gd.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    gd.dispose();
}
}