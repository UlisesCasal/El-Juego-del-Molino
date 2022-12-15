package Vistas;

import Controlador.Controlador;
import Modelo.Eventos;

public interface IVista {
    void iniciar();
    void setControlador(Controlador controlador);
    void mostrarTablero();
    void mostrarPuntajesFinales();

    void mostrarSacarFicha();


    void actualizarTablero(int[] ficha, String jugador, Eventos eventoMostrar);

    void ponerFicha();

    int[] traductor(String posicionSimbolica);

    void cambiarEstado(EstadosVista estado);
    void mostrarErrores(Errores errores);
    void mostrarMoverFicha1fase();
    void mostrarMoverFicha2fase();

    void mostrarPantallaEspera();
    void mostrarConsola();

    void puntajeHistorico();
    void eliminar();

}
