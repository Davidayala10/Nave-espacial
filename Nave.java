/* Proyecto 2 — Ayala Chacon David — Grupo: 7CM4 */

import java.awt.*;
import java.awt.geom.AffineTransform;


public class Nave {
    private double x, y; 
    private double v=60; 
    private double a=120;
    private final double V_MAX=320.0;
    private final double A_MAX=20.0;
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

    public void MRUA(double delta) {
        x += v * delta + 0.5 * a * delta * delta;
        v += a * delta;
        if (v > V_MAX) v = V_MAX;
        if (v < 0) v = 0;
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
   public double RadioDeColision() { return R; }

}

