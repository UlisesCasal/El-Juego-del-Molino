package Controlador;

import Clases.Eventos;
import Clases.Jugador;
import Clases.Partida;
import Interaccion.Observable;
import Interaccion.Observador;
import Vistas.IVista;
import Vistas.VistaConsola;

import java.util.Objects;

public class Controlador implements Observador {
    private Partida modelo;
    private IVista vista;
    public Controlador(Partida partida, IVista vista){
        this.modelo = partida;
        this.vista = vista;
        this.vista.setControlador(this);
        this.modelo.agregarObservador(this);
    }

    public boolean ponerFicha(int t, int f, int c){
        return this.modelo.ponerFicha(this.modelo.getTurnoActual(),t,f,c);
    }

    public void sacarFicha(int t, int f, int c){
        //Metodo que llama al sacar ficha:
        this.modelo.sacarFicha(this.modelo.getTurnoActual().getFicha(t,f,c));

    }

    //public void moverFicha(int t, int f, int c);


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

    public String[] getPuntajesFinales() {
        String[] salida = new String[2];
        salida[0] = String.valueOf(this.modelo.getPuntajes()[0]);
        salida[1] = String.valueOf(this.modelo.getPuntajes()[1]);
        return salida;
    }

    public String getCharJugadorFicha() {
        String salida = "";
        if (Objects.equals(this.modelo.getTurnoAnterior(), "1")){
            salida = "(¤)";
        }else salida = "(×)";
        return salida;
    }

    public void agregarJugadoresDePrueba() {
        this.modelo.setJugador("Pepe");
        this.modelo.setJugador("Pepe2");
    }
}
