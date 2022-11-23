package Clases;

import Interaccion.Observable;
import Interaccion.Observador;

import java.util.ArrayList;
import java.util.List;

public class Partida implements Observable {
    private List<Jugador> jugadores = new ArrayList<Jugador>();
    private Tablero tablero;
    private boolean turno; //True -> jugador1, False -> Jugador2
    private int numeroJugadores;
    private List<Observador>  observadores = new ArrayList<>();

    public Partida(){
        tablero = new Tablero();
        this.numeroJugadores = 0;
        boolean turno = true;
    }

    public void setJugador(String nombre){
        jugadores.add(new Jugador(nombre, this.numeroJugadores++ ));
    }

    public Jugador darTurno(){
        if (this.turno == true) {
            this.turno = false;
            return jugadores.get(0);
        }else if (this.turno == false){
            this.turno = true;
            return jugadores.get(1);
        }
        return null;
    }

    public boolean ponerFicha(Jugador jugador, int t, int f, int c){
        Ficha ficha = jugador.getFichaNoPuesta();
        boolean salida = false;

        //Corroboro que no sea nulo:
        if (ficha != null){
             if (this.tablero.agregarFicha(f,c,t,ficha,jugador)){
                 //Si la pudo ingresar, pongo el flag en true, y verifico si se produjo una raya:
                 jugador.setPosicionFicha(new int[]{t, f, c}, ficha);
                 salida = true;
                 this.notificar(Eventos.FICHAAGREGADA);
                 if (this.tablero.verificarRaya(t,f,c,jugador)){
                     //Si me da true que hay una nueva raya:
                     //1. debo retirar alguna ficha del oponente:
                     //2. me debe ingresar la ficha a retirar
                     //3. debo incrementar el puntaje del jugador
                     //DEBERE NOTIFICAR DE LO SUCEDIDO:
                     jugador.incPuntaje();
                     this.notificar(Eventos.SACARFICHA);
                 }else{
                     this.turno = !this.turno; //Cambio de turno si no tiene que sacar una ficha
                 }


            }
        }
        return salida;
    }

    public void sacarFicha(Ficha ficha){
        Boolean resultadoSacar = this.tablero.sacarFicha(ficha, getTurnoActual());

        //Debo llamar a termino la partida cada vez que se saca una ficha, para verificar si alguno puede seguir jugando o no.
        if (terminoLaPartida()){
            //INFORMO QUE LA PARTIDA SE HA TERMINADO Y MUESTRO GANADOR:
            this.notificar(Eventos.FINPARTIDA);
        } else if (resultadoSacar) {
            this.notificar(Eventos.FICHASACADA);

        } else if (!resultadoSacar) {
            this.notificar(Eventos.NOSACADA);

        }
    }

    public void moverFichas(Ficha ficha, int tmover, int fmover, int cmover, Jugador jugador){
        if (this.tablero.moverFichas(ficha,tmover,fmover,cmover,jugador)){
            this.notificar(Eventos.FICHAMOVIDA);
        }else{
            this.notificar(Eventos.FICHANOMOVIDA);
        }
    }

    public boolean terminoLaPartida(){
        boolean salida = false;
        if ((jugadores.get(0).getFichasTotales() < 3) || (jugadores.get(1).getFichasTotales() < 3)){
            salida = true;
        }
        return salida;
    }

    public int[] getPuntajes(){
        int[] salida = new int[2];
        salida[0] = jugadores.get(0).getPuntaje();
        salida[1] = jugadores.get(1).getPuntaje();
        return salida;
    }

    public Jugador getTurnoActual(){
        Jugador jugador;
        if (this.turno){jugador = jugadores.get(0);}
        else {jugador = jugadores.get(1);}

        return jugador;
    }

    @Override
    public void notificar(Object evento) {
        for (Observador observador : this.observadores) {
            observador.actualizar(evento, this);
        }
    }

    @Override
    public void agregarObservador(Observador observador) {
        this.observadores.add(observador);
    }

    public String getTurnoAnterior() {
        String jugador;
        if (this.turno){
         jugador = String.valueOf(this.jugadores.get(1).getNumero());
        }else jugador = String.valueOf(this.jugadores.get(0).getNumero());
        return jugador;
    }

    public Ficha getFicha(int t, int f, int c){
        Jugador jugador1 = jugadores.get(0);
        Jugador jugador2 = jugadores.get(1);
        Ficha fichaJugador1 = jugador1.getFicha(t,f,c);
        Ficha fichaJugador2 = jugador2.getFicha(t,f,c);
        Ficha fichaSalida = null;
        if (fichaJugador1 != null){
            fichaSalida = fichaJugador1;
        }else if (fichaJugador1 != null){
            fichaSalida = fichaJugador2;
        }
        return fichaSalida;
    }
}
