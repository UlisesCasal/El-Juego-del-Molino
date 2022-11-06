package Controlador;

import Clases.Eventos;
import Clases.Partida;
import Interaccion.Observable;
import Interaccion.Observador;
import Vistas.VistaConsola;

public class Controlador implements Observador {
    private Partida modelo;
    private VistaConsola vista;
    public Controlador(Partida partida, VistaConsola vista){
        this.modelo = partida;
        this.vista = vista;
        this.vista.setControlador(this);
        this.modelo.agregarObservador(this);
    }

    public void sacarFicha(int t, int f, int c){
        //Metodo que llama al sacar ficha:
        this.modelo.sacarFicha(this.modelo.getTurnoActual().getFicha(t,f,c));

    }

    @Override
    public void actualizar(Object evento, Observable observable) {
        //Metodo que evalua las llamadas del modelo, y en base a eso realiza o no una accion en la vista:
        if(evento instanceof Eventos) {
            if ((evento == Eventos.FICHAAGREGADA) || (evento == Eventos.FICHAMOVIDA) || (evento == Eventos.FICHASACADA)){
                this.vista.mostrarTablero();
            }if (evento == Eventos.FINPARTIDA) {
                this.vista.mostrarPuntajesFinales();
            }
            if (evento == Eventos.SACARFICHA){
                this.vista.mostrarSacarFicha();
            }
        }
        }
}
