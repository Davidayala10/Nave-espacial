/* Proyecto 2 — Ayala Chacon David — Grupo: 7CM4 */

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class Asteroide {
    public double x, y;
    public double vx, vy;
    public double angulo;
    public double Vangulo;
    public final double radio;
    private final Polygon meteoro;
    private final Color relleno = new Color(169, 169, 169);
    private final Color borde = new Color(105, 105, 105);
    
    public Asteroide(double x, double y, double vx, double vy, double r, double Vangulo, Random rand) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radio = r;
        this.Vangulo = Vangulo;
        this.angulo = rand.nextDouble() * 2 * Math.PI;

        int lados = 8 + rand.nextInt(5);
        Polygon p = new Polygon();
        for (int i = 0; i < lados; i++) {
            double a = i * (Math.PI * 2 / lados);
            double ruido = 0.65 + rand.nextDouble() * 0.5;
            int px = (int)Math.round(radio * ruido * Math.cos(a)); 
            int py = (int)Math.round(radio * ruido * Math.sin(a)); 
            p.addPoint(px, py);
        }
        this.meteoro = p;
    }

    public void mover(double delta) {
        x += vx * delta;
        y += vy * delta;
        angulo += Vangulo * delta;
    }

    public void wrap(int W, int H) {
        if (x < -radio) x += W + 2 * radio;
        if (x > W + radio) x -= W + 2 * radio;
        if (y < -radio) y += H + 2 * radio;
        if (y > H + radio) y -= H + 2 * radio;
    }

    public void dibujar(Graphics2D g2) {
        AffineTransform old = g2.getTransform();
        g2.translate(x, y);
        g2.rotate(angulo);
        g2.setColor(relleno);
        g2.fillPolygon(meteoro);
        g2.setColor(borde);
        g2.drawPolygon(meteoro);
        g2.setTransform(old);
    }   
}

    
