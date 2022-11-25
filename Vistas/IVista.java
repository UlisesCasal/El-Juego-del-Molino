package Vistas;

import Controlador.Controlador;

public interface IVista {
    void iniciar();
    void setControlador(Controlador controlador);
    void mostrarTablero();
    void mostrarPuntajesFinales();

    void mostrarSacarFicha();
    void ponerFicha();

    int[] traductor(String posicionSimbolica);

    void actualizarTablero();
    void cambiarEstado(EstadosVista estado);
    void mostrarErrores(Errores errores);
    void mostrarMoverFicha1fase();
    void mostrarMoverFicha2fase();
}
