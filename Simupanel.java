/* Proyecto 2 — Ayala Chacon David — Grupo: 7CM4 */

import javax.swing.JPanel;
import java.awt.*;

public class Simupanel extends JPanel{
    private final int W, H;
    private final double inix;
    private final double iniy;
    private static final double futuro = 2.0;
    private static final double anticipa = 0.2;
    private static final double seguridad = 8.0;
    //Actualizacion de panel
    private javax.swing.Timer timer;
    private long lastNano;
    //private double xTest = 20;
    private Nave nave;
    private java.util.List<Asteroide> asteroides = new java.util.ArrayList<>();
    private java.util.Random rand = new java.util.Random();

    public Simupanel(int w, int h, int nAsteroides){
        this.W = w;
        this.H = h;
        setPreferredSize(new Dimension(W, H));
        this.iniy = 80 + new java.util.Random().nextDouble() * (H - 160);
        this.inix = 60;
        nave = new Nave(inix, iniy);

        for (int i = 0; i < nAsteroides; i++){
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
        //xTest += 100 * delta;
        //if(xTest > W) xTest = 0;
        //nave.MRUA(delta);

        double Velocimetro = Choque() ? 60.0 : 300.0;
        nave.ActualizarVelocidad(delta, Velocimetro);
        
        for(Asteroide a : asteroides){
            if (colicion(nave.getX(), nave.getY(), nave.RadioDeColision(), a.x, a.y, a.radio)) {
                timer.stop();
                int porcentaje = (int)Math.round(progreso());
                System.out.println("Colicion. Avance " + porcentaje + "%");
                javax.swing.JOptionPane.showMessageDialog(this, "Choque de nave.\nAvance: "+ porcentaje + "%","Fin de la simulacion", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            a.mover(delta);
            a.wrap(W, H);
        }

        if (nave.getX() - nave.RadioDeColision() > W) {
            timer.stop();
            System.out.println("¡Éxito! Avance: 100%");
            javax.swing.JOptionPane.showMessageDialog(this,"¡Misión cumplida!\nAvance: 100%","Completado",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
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

        //g2.fillOval((int)xTest, 100, 50, 50);
        nave.draw(g2);

        int porcentaje = (int)Math.round(progreso());
        g2.setColor(Color.white);
        g2.drawString("Avance: " + porcentaje + "%", 16, 40);
    }

    private boolean colicion(double x1, double y1, double r1, double x2, double y2, double r2){
        double dx = x1 - x2;
        double dy = y1 - y2;
        double rr = r1 + r2;
        return dx * dx + dy * dy <= rr * rr;
    }

    private double progreso(){
        double avance = (W + nave.RadioDeColision()) - inix;
        double porcentaje = 100.0 * (nave.getX() - inix) / avance;
        return Math.max(0, Math.min(100, porcentaje));
    }

    private boolean Choque(){

        for(double t = anticipa; t<= futuro; t += anticipa ){
            double naveFutura = nave.getX() + nave.getV() * t;
            if (naveFutura > W) break;

            for (Asteroide a : asteroides){
                double futuroAx = a.x + a.vx * t;
                double futuroAy = a.y + a.vy * t;
                double dx = naveFutura - futuroAx;
                double dy = nave.getY() - futuroAy;
                double rr = nave.RadioDeColision() + a.radio + seguridad;

                if (dx * dx + dy * dy <= rr * rr){
                    return true;
                }
            }
        }
        return false;
    }
}