package Ejecucion;

import Modelo.IPartida;
import Modelo.Partida;
import ar.edu.unlu.rmimvc.RMIMVCException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.servidor.Servidor;

public class AppServidor {
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
