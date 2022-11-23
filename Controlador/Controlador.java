package Controlador;

import Clases.Eventos;
import Clases.Ficha;
import Clases.Jugador;
import Clases.Partida;
import Interaccion.Observable;
import Interaccion.Observador;
import Vistas.Errores;
import Vistas.EstadosVista;
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
        this.modelo.sacarFicha(this.modelo.getFicha(t,f,c));

    }

    public void moverFicha(int t, int f, int c, int tm, int fm, int cm){
        this.modelo.moverFichas(this.modelo.getFicha(t,f,c), tm, fm, cm, this.modelo.getTurnoActual());
    }


    @Override
    public void actualizar(Object evento, Observable observable) {
        //Metodo que evalua las llamadas del modelo, y en base a eso realiza o no una accion en la vista:
        if(evento instanceof Eventos) {
            if ((evento == Eventos.FICHAAGREGADA) || (evento == Eventos.FICHAMOVIDA) || (evento == Eventos.FICHASACADA)){
                this.vista.actualizarTablero();
            }if (evento == Eventos.FINPARTIDA) {
                this.vista.mostrarPuntajesFinales();
            }
            if (evento == Eventos.SACARFICHA){
                this.vista.cambiarEstado(EstadosVista.SACARFICHA);
            }if (evento == Eventos.NOSACADA){
                this.vista.mostrarErrores(Errores.NOSEPUDOSACARFICHA);
            }if (evento == Eventos.FICHANOMOVIDA){
                this.vista.mostrarErrores(Errores.NOSEPUDOMOVERFICHA);
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

    public boolean verificarFicha(int t, int f, int c) {
        //Verificar la integridad de tener este Ficha importado en el controlador
        //verifica si la ficha es nula y si no lo es verifica si es del oponente:
        Ficha ficha = this.modelo.getFicha(t,f,c);
        boolean salida = false;
        if (ficha != null) {
            if (ficha.getJugador() != this.modelo.getTurnoActual()) {
                salida = true;
            }
        }
        return salida;
    }

    public boolean verificarPosicionVacia(int t, int f, int c){
        Ficha ficha = this.modelo.getFicha(t,f,c);
        boolean salida = false;
        if (ficha == null){
            salida = true;
        }
        return  salida;
    }
}
