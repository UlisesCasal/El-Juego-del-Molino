package Vistas;

import Controlador.Controlador;

public interface IVista {
    void iniciar();
    void setControlador(Controlador controlador);
    void mostrarTablero();
    void mostrarPuntajesFinales();

    void mostrarSacarFicha();
    void mostrarPonerFicha();

}
