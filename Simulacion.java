/* Proyecto 2 — Ayala Chacon David — Grupo: 7CM4 */

import javax.swing.SwingUtilities;

public class Simulacion{
    public static void main(String[] args) {
        int n = 20;

        if (args.length > 0) {
            try {
                n = Integer.parseInt(args[0]);
            } catch (Exception ignored) {
            }
        }

        final int asteroides = Math.max(1, n);

        SwingUtilities.invokeLater(()-> {
            Simuframe frame = new Simuframe(asteroides);
            frame.setVisible(true);
        });
    }
}