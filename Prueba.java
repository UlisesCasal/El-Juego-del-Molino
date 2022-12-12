import Ejecucion.AppCliente;
import Ejecucion.AppCliente2;
import Modelo.Partida;
import Controlador.Controlador;
import Services.Serializador;
import Vistas.VistaConsolaSwing.VConsola;
import ar.edu.unlu.rmimvc.cliente.Cliente;
import ar.edu.unlu.rmimvc.servidor.Servidor;

import javax.swing.*;
import java.rmi.RemoteException;

public class Prueba {
    public static void main(String[] args) {
        Servidor servidor = new Servidor("127.0.0.1", 8888);
        AppCliente cliente = new AppCliente();
        AppCliente2 cliente2 = new AppCliente2();
    }
}
