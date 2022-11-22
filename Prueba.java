import Clases.Jugador;
import Clases.Partida;
import Controlador.Controlador;
import Vistas.VistaConsola;
import Vistas.VistaConsolaSwing.VConsola;

import javax.swing.*;

public class Prueba {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new VConsola();
                frame.setSize(500,500);
                frame.setVisible(true);
                Partida modelo = new Partida();
                Controlador controlador = new Controlador(modelo, (VConsola) frame);
                controlador.agregarJugadoresDePrueba();

            }
        });
    }
}
