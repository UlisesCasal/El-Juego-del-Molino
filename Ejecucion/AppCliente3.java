package Ejecucion;

import Controlador.Controlador;
import Vistas.IVista;
import Vistas.VistaConsolaSwing.VConsola;
import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.cliente.Cliente;

import javax.swing.*;
import java.rmi.RemoteException;

public class AppCliente3 {
    public static void main(String[] args) {
        JFrame vista = new VConsola();
        Controlador controlador = new Controlador((IVista) vista);
        Cliente cliente = new Cliente("127.0.0.1",9997,"127.0.0.1",8888);
        try {
            cliente.iniciar(controlador);
        } catch (RemoteException e) {
            //vista.printError(ErrorVista.CONEXION);
            //error de conexion
        } catch (RMIMVCException e) {
            //vista.printError(ErrorVista.CONEXION);
            //throw new RuntimeException(e);
            // error al crear el objeto de acceso remoto del modelo o del controlador
        }
    }

}
