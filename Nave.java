/* Proyecto 2 — Ayala Chacon David — Grupo: 7CM4 */

import java.awt.*;
import java.awt.geom.AffineTransform;


public class Nave {
    private double x, y; 
    private final Polygon shape; 
    private final double R=16;

    public Nave(double Inix, double Iniy) {
        this.x = Inix;
        this.y = Iniy;
        Polygon t = new Polygon();
        t.addPoint(24, 0);
        t.addPoint(-18, -12);
        t.addPoint(-18, 12);
        this.shape = t;
    }

    public void draw(Graphics2D g2) {
        AffineTransform old = g2.getTransform();
        g2.translate(x, y);
        g2.setColor(Color.GREEN);
        g2.fillPolygon(shape);
        g2.setColor(Color.BLACK);
        g2.drawPolygon(shape);
        g2.setTransform(old);
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getR() {
        return R;
    }

}

