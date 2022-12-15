package Ejecucion;

import Modelo.Partida;
import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.servidor.Servidor;

import java.rmi.RemoteException;

public class ServidorDebug {
    public static void main(String[] args) {
        Partida juego = new Partida(); // modelo
        Servidor servidor = new Servidor("127.0.0.1", 8888);
        try {
            servidor.iniciar(juego);
        } catch (RemoteException e) {
            e.printStackTrace();
            // error de conexión
        } catch (RMIMVCException e) {
            e.printStackTrace();
            // error al crear el objeto de acceso remoto del modelo
        }
    }
}
