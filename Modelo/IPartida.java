package Modelo;

import Interaccion.Observador;

public interface IPartida {
    void iniciarPartida();
    void setJugador(String nombre);
    Jugador darTurno();
    boolean ponerFicha(Jugador jugador, int t, int f, int c);
    void sacarFicha(Ficha ficha);
    void moverFichas(Ficha ficha, int tmover, int fmover, int cmover, Jugador jugador);
    int[] getPuntajes();
    String getTurnoAnterior();
    Ficha getFicha(int t, int f, int c);
    Jugador getUltimoJugadorAgregado();
    Eventos getEstadoJugador(Jugador jugador);
    String[] getNombreJugadores();
    void agregarObservador(Observador observador);
}
