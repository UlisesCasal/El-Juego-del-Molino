package Ejecucion;

import Ejecucion.AppCliente;
import Ejecucion.AppCliente2;
import Ejecucion.AppServidor;
import Modelo.Partida;
import Controlador.Controlador;
import Services.Serializador;
import Vistas.VistaConsolaSwing.VConsola;
import ar.edu.unlu.rmimvc.cliente.Cliente;
import ar.edu.unlu.rmimvc.servidor.Servidor;

import javax.swing.*;
import java.rmi.RemoteException;

public class Prueba {
    AppServidor servidor = new AppServidor();
    AppCliente cliente1 = new AppCliente();
    AppCliente cliente2 = new AppCliente();
    private static Prueba instance;

    public static Prueba getInstance(){
        if (instance == null){
            instance = new Prueba();
        }else{
            instance = null;
            getInstance();
        }
        return instance;
    }
    public static void main(String[] args) {
        getInstance();
        instance.iniciar();
    }

    public void iniciar(){
        servidor.iniciar();
        cliente1.iniciar();
        cliente2.iniciar();
    }

}
