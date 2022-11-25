import Modelo.Partida;
import Controlador.Controlador;
import Vistas.VistaConsolaSwing.VConsola;

import javax.swing.*;

public class Prueba {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new VConsola();
                JFrame frame2 = new VConsola();
                frame.setSize(500,500);
                frame.setVisible(true);
                frame2.setSize(500,500);
                frame2.setVisible(true);
                Partida modelo = new Partida();
                Controlador controlador1 = new Controlador(modelo, (VConsola) frame);
                Controlador controlador2 = new Controlador(modelo, (VConsola) frame2);
                ((VConsola) frame).iniciar();
                ((VConsola) frame2).iniciar();

            }
        });
    }
}
