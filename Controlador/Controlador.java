package Controlador;

import Modelo.*;
import Interaccion.Observable;
import Interaccion.Observador;
import Vistas.Errores;
import Vistas.EstadosVista;
import Vistas.IVista;

import java.util.Objects;

public class Controlador implements Observador {
    private IPartida modelo;
    private IVista vista;
    private Jugador jugador;
    public Controlador(IPartida partida, IVista vista){
        this.modelo = partida;
        this.vista = vista;
        this.vista.setControlador(this);
        this.modelo.agregarObservador(this);
    }

    public boolean ponerFicha(int t, int f, int c){ // Ver por que retorna un boolean
        return this.modelo.ponerFicha(this.modelo.darTurno(),t,f,c);
    }

    public void sacarFicha(int t, int f, int c){
        //Metodo que llama al sacar ficha:
        this.modelo.sacarFicha(this.modelo.getFicha(t,f,c));

    }

    public void moverFicha(int t, int f, int c, int tm, int fm, int cm){
        this.modelo.moverFichas(this.modelo.getFicha(t,f,c), tm, fm, cm, this.modelo.darTurno());
    }

    public boolean cambiarEstadosVista(Eventos evento){ // ver si es necesario devolver un bool
        switch (evento){
            case SACARFICHA -> this.vista.cambiarEstado(EstadosVista.SACARFICHA);
            case FICHAAGREGADA -> this.vista.cambiarEstado(EstadosVista.INGRESARFICHA);
            case SINFICHASPARAAGREGAR -> this.vista.cambiarEstado(EstadosVista.MOVERFICHA);
            case FINPARTIDA -> this.vista.mostrarPuntajesFinales();
        }
        return true;
    }

    @Override
    public void actualizar(Object evento, Observable observable) {
        //Metodo que evalua las llamadas del modelo, y en base a eso realiza o no una accion en la vista:
        if(evento instanceof Eventos) {
            Jugador jugadorTurno = this.modelo.darTurno();

            if (evento == Eventos.ESPERANDOJUGADORES){
                this.vista.mostrarPantallaEspera();
            }
            if (evento == Eventos.INICIARPARTIDA){
                this.vista.mostrarTablero();
                this.vista.cambiarEstado(EstadosVista.INGRESARFICHA);
            }
            if (evento == Eventos.CAMBIODETURNO){
                if (jugadorTurno.getNumero() != this.jugador.getNumero()){
                    this.vista.cambiarEstado(EstadosVista.BLOQUEADA);
                }else{
                    cambiarEstadosVista(this.modelo.getEstadoJugador(this.jugador));
                }
            }
            if ((evento == Eventos.FICHAAGREGADA) || (evento == Eventos.FICHAMOVIDA) || (evento == Eventos.FICHASACADA)){
                this.vista.actualizarTablero();

            }if (evento == Eventos.FINPARTIDA) {
                cambiarEstadosVista(Eventos.FINPARTIDA);
            }
            if (evento == Eventos.SACARFICHA){
                this.vista.actualizarTablero();
                if (this.jugador.getNumero() == jugadorTurno.getNumero()){
                    cambiarEstadosVista(Eventos.SACARFICHA);
                }

            }if (evento == Eventos.NOSACADA){
                if (esJugadorActual()) {
                    this.vista.mostrarErrores(Errores.NOSEPUDOSACARFICHA);
                }
            }if (evento == Eventos.FICHANOMOVIDA){
                if (esJugadorActual()) {
                    this.vista.mostrarErrores(Errores.NOSEPUDOMOVERFICHA);
                }
            }if (evento == Eventos.SINFICHASPARAAGREGAR){
                this.vista.cambiarEstado(EstadosVista.MOVERFICHA);
            }if (evento == Eventos.FICHANOAGREGADA){
                if (esJugadorActual()) {
                    this.vista.mostrarErrores(Errores.NOSEPUDOAGREGARFICHA);
                }
            }
        }
    }

    public boolean esJugadorActual(){
        Jugador jugadorTurno = this.modelo.darTurno();
        return this.jugador.getNumero() == jugadorTurno.getNumero();
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

    public void setJugador(String nombre) {
        this.modelo.setJugador(nombre);
        this.jugador = this.modelo.getUltimoJugadorAgregado();
        this.modelo.iniciarPartida();

    }

    public boolean verificarFicha(int t, int f, int c) {
        //verifica si la ficha es nula y si no lo es verifica si es del oponente:
        Ficha ficha = this.modelo.getFicha(t,f,c);
        boolean salida = false;
        if (ficha != null) {
            if (ficha.getJugador() == this.modelo.darTurno()) {
                salida = true;
            }
        }else {
            this.vista.mostrarErrores(Errores.NOSEPUDOMOVERFICHA);
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

    public String getNombreJugador() {
        return this.jugador.getNombre();
    }

    public String[] getNombreJugadores() {
        return this.modelo.getNombreJugadores();
    }
}
