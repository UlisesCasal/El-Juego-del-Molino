package Modelo;

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
    boolean ponerFicha(Jugador jugador, int t, int f, int c) throws RemoteException;
    void sacarFicha(Ficha ficha) throws RemoteException;
    void moverFichas(Ficha ficha, int tmover, int fmover, int cmover, Jugador jugador) throws RemoteException;
    int[] getPuntajes() throws RemoteException;
    String getTurnoAnterior() throws RemoteException;
    Ficha getFicha(int t, int f, int c) throws RemoteException;
    Jugador getUltimoJugadorAgregado() throws RemoteException;
    Eventos getEstadoJugador(Jugador jugador) throws RemoteException;
    String[] getNombreJugadores() throws RemoteException;
    List<Ficha> getFichasPuestas() throws RemoteException;

}
