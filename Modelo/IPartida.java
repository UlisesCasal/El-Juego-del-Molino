package Modelo;

import Interaccion.Observador;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPartida extends IObservableRemoto, Remote {
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

}
