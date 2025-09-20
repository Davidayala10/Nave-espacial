/* Proyecto 2 — Ayala Chacon David — Grupo: 7CM4 */

import javax.swing.JPanel;
import java.awt.*;

public class Simupanel extends JPanel{
    private final int W, H;
    //Actualizacion de panel
    private javax.swing.Timer timer;
    private long lastNano;
    private double xTest = 20;
    private Nave nave;

    public Simupanel(int w, int h){
        this.W = w;
        this.H = h;
        setPreferredSize(new Dimension(W, H));
        double iniy = 80 + new java.util.Random().nextDouble() * (H - 160);
        double inix = 60;
        nave = new Nave(inix, iniy);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        lastNano = System.nanoTime();
        timer = new javax.swing.Timer(16, e -> tick());
        timer.start();
    }

    private void tick(){
        long now = System.nanoTime();
        double delta = (now - lastNano) / 1e9;
        lastNano = now;
        delta = Math.min(delta, 0.05);
        xTest += 100 * delta;
        if(xTest > W) xTest = 0;
        repaint();
    }

    @Override
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.drawString("Simulación de Asteroides", 10, 20);
        g2.fillOval((int)xTest, 100, 50, 50);
        nave.draw(g2);
    }
}