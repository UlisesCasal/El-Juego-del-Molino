import Modelo.Partida;
import Controlador.Controlador;
import Vistas.VistaConsolaSwing.VConsola;

import javax.swing.*;
import java.rmi.RemoteException;

public class Prueba {
    public static void main(String[] args) {
        /*
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
                Controlador controlador1 = new Controlador((VConsola) frame);
                Controlador controlador2 = new Controlador((VConsola) frame2);
                try {
                    controlador1.setModeloRemoto(modelo);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
                try {
                    controlador2.setModeloRemoto(modelo);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
                ((VConsola) frame).iniciar();
                ((VConsola) frame2).iniciar();

            }
        });
    }
    */
    }
}
