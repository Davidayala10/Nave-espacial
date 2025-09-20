/* Proyecto 2 — Ayala Chacon David — Grupo: 7CM4 */

import javax.swing.JFrame;

import javax.swing.JFrame;

public class Simuframe extends JFrame{
    public static final int WIDTH =1280;
    public static final int HEIGHT = 720;
    
    public Simuframe(int asteroides){
        super("Proyecto 2 - Simulacion- David Ayala");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        add(new Simupanel(WIDTH, HEIGHT));
    }
}
