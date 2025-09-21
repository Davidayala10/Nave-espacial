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
    private java.util.List<Asteroide> asteroides = new java.util.ArrayList<>();
    private java.util.Random rand = new java.util.Random();

    public Simupanel(int w, int h){
        this.W = w;
        this.H = h;
        setPreferredSize(new Dimension(W, H));
        double iniy = 80 + new java.util.Random().nextDouble() * (H - 160);
        double inix = 60;
        nave = new Nave(inix, iniy);

        for (int i = 0; i < 20; i++){
            asteroides.add(randomAsteroide());
        }

        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        lastNano = System.nanoTime();
        timer = new javax.swing.Timer(16, e -> tick());
        timer.start();
    }

    private Asteroide randomAsteroide(){
        double r = 12 + rand.nextDouble() * 38;
        double speed = 40 + rand.nextDouble() * 120;
        double a = rand.nextDouble() * Math.PI * 2;
        double vx = speed * Math.cos(a);
        double vy = speed * Math.sin(a);
        double x = rand.nextDouble() *W;
        double y = rand.nextDouble() *H;
        double Vangulo = (rand.nextDouble() - 0.5) * 1.5;
        return new Asteroide(x, y, vx, vy, r, Vangulo, rand);
    }

    private void tick(){
        long now = System.nanoTime();
        double delta = (now - lastNano) / 1e9;
        lastNano = now;
        delta = Math.min(delta, 0.05);
        xTest += 100 * delta;
        if(xTest > W) xTest = 0;
        nave.MRUA(delta);
        
        for(Asteroide a : asteroides){
            a.mover(delta);
            a.wrap(W, H);
        }

        repaint();
    }

    @Override
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);
        g2.drawString("Simulación de Asteroides", 10, 20);
        
        for(Asteroide a : asteroides){
            a.dibujar(g2);
        }
        g2.fillOval((int)xTest, 100, 50, 50);
        nave.draw(g2);
    }
}