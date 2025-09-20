/* Proyecto 2 — Ayala Chacon David — Grupo: 7CM4 */

import javax.swing.JPanel;
import java.awt.*;

public class Simupanel extends JPanel{
    private final int W, H;

    public Simupanel(int w, int h){
        this.W = w;
        this.H = h;
        setPreferredSize(new Dimension(W, H));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
    }

    @Override
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.drawString("Simulación de Asteroides", 10, 20);
    }
}