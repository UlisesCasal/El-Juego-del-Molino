package Modelo;

import Controlador.TipoFicha;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IPartida extends IObservableRemoto, Remote {
    Ficha getFichaAgregada() throws RemoteException;

    Ficha getFichaEliminada() throws RemoteException;

    void iniciarPartida() throws RemoteException;
    void setJugador(String nombre) throws RemoteException;
    Jugador darTurno() throws RemoteException;

    boolean ponerFicha(int t, int f, int c) throws RemoteException;

    void sacarFicha(int t, int f, int c) throws RemoteException;
    void moverFichas(int t, int f, int c, int tmover, int fmover, int cmover) throws RemoteException;
    int[] getPuntajes() throws RemoteException;
    String getTurnoAnterior() throws RemoteException;
    Ficha getFicha(int t, int f, int c) throws RemoteException;
    int[] getPosicionFicha(TipoFicha tipoFicha) throws RemoteException;
    boolean verificarFichaMover(int t, int f, int c) throws RemoteException;
    boolean verificarPosicionVacia(int t, int f, int c) throws RemoteException;
    Jugador getUltimoJugadorAgregado() throws RemoteException;
    int getNumeroJugador(TipoFicha tipoFicha) throws RemoteException;
    Eventos getEstadoJugador() throws RemoteException;
    String[] getNombreJugadores() throws RemoteException;
    List<Ficha> getFichasPuestas() throws RemoteException;

    void desconexion(String jugador) throws RemoteException;

    void reiniciar() throws RemoteException;

    boolean verificarNombreUnivoco(String nombre) throws RemoteException;
}
