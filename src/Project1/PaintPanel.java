package Project1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PaintPanel extends JPanel {

    private final double v = 100;
    private final double t = 0.1;
    public int at;
    public int d;
    private Image surface = new ImageIcon("C:\\Users\\vinh.tn173468\\Documents\\NetBeansProjects\\NetBeansProjects-20191213T155253Z-001\\NetBeansProjects\\Project1\\src\\img\\surface.jpg").getImage();

    //mang toa do dang int de ve
    private ArrayList<Double> x;
    private ArrayList<Double> y;

    //khoi tao mang luu toa do dang double
    public PaintPanel() {
        x = new ArrayList<>();
        y = new ArrayList<>();
    }

    public void setNullList() {
        x = new ArrayList<>();
        y = new ArrayList<>();
    }

    public void setList() {
        Random Rand = new Random();
        int xStart = Math.abs(Rand.nextInt() % this.getWidth());
        int yStart = Math.abs(Rand.nextInt() % this.getHeight());

//        int aStart = 600;
//        int bStart = 600;
        for (int i = 0; i < d; i++) {
            x.add((double) xStart);
            y.add((double) yStart);
        }
    }

    public void setD(int d) {
        this.d = d;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(surface, 0, 0, this.getWidth(), this.getHeight(), null);

        g.setColor(Color.black);

        // Gan vi tri ban dau bang random
        Random Rand = new Random();
        for (int i = 0; i < x.size() - 1; i++) {
            double alpha = Rand.nextDouble();
            double degrees = alpha * 360.0;
            double radians = Math.toRadians(degrees);
            x.set(i, x.get(i) + t * v * Math.cos(radians));
            y.set(i, y.get(i) + t * v * Math.sin(radians));
//            if (x.get(i).intValue() >= this.getWidth()){
//                x.set(i, 500.0);
//                if(x.get(i).intValue() <= 0){
//                    x.set(i, 500.0);
//                }
//            }
//              if (y.get(i).intValue() >= this.getHeight()){
//                y.set(i, 500.0);
//                if(y.get(i).intValue() <= 0){
//                    y.set(i, 500.0);
//                }
//            }
            if (x.get(i).intValue() <= 0) {
                x.remove(i);
                y.remove(i);
            }
            if (y.get(i).intValue() <= 0) {
                x.remove(i);
                y.remove(i);
            }
            if (y.get(i).intValue() >= this.getHeight()) {
                x.remove(i);
                y.remove(i);
            }
            if (x.get(i).intValue() >= this.getWidth()) {
                x.remove(i);
                y.remove(i);
            }
            g.drawLine(x.get(i).intValue(), y.get(i).intValue(), x.get(i).intValue(), y.get(i).intValue());
        }
    }
}
