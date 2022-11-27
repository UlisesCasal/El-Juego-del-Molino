package Modelo;

import Interaccion.Observable;
import Interaccion.Observador;

import java.util.ArrayList;
import java.util.List;

public class Partida implements Observable {
    private List<Jugador> jugadores = new ArrayList<Jugador>();
    private Tablero tablero;
    private int turno = 1; //True -> jugador1, False -> Jugador2
    private int numeroJugadores;
    private List<Observador>  observadores = new ArrayList<>();

    public Partida(){
        tablero = new Tablero();
        this.numeroJugadores = 0;
        boolean turno = true;
    }

    public void iniciarPartida(){
        //Metodo que verifica si hay dos jugadores conectados, en caso de haberlo inicia la partida:
        if (jugadores.size() == 2){
            this.notificar(Eventos.INICIARPARTIDA);
        }
    }
    public void setJugador(String nombre){
        jugadores.add(new Jugador(nombre, this.numeroJugadores++ ));
    }

    public Jugador darTurno(){
        if (this.turno == 1) {
            return jugadores.get(0);
        }else if (this.turno == 2){
            return jugadores.get(1);
        }
        return null;
    }

    public boolean ponerFicha(Jugador jugador, int t, int f, int c){
        Ficha ficha = jugador.getFichaNoPuesta();
        boolean salida = false;

        //Corroboro que no sea nulo y tenga mas de una fich:
        if ((jugador.getNumeroFichasRestante() >= 0) && (ficha != null)){
             if (this.tablero.agregarFicha(f,c,t,ficha,jugador)){
                 //Si la pudo ingresar, pongo el flag en true, y verifico si se produjo una raya:
                 jugador.setPosicionFicha(new int[]{t, f, c}, ficha);
                 ficha = jugador.getFicha(t,f,c);
                 salida = true;
                 if (this.tablero.verificarRaya(t,f,c,jugador, ficha)){
                     //Si me da true que hay una nueva raya:
                     //1. debo retirar alguna ficha del oponente:
                     //2. me debe ingresar la ficha a retirar
                     //3. debo incrementar el puntaje del jugador
                     //DEBERE NOTIFICAR DE LO SUCEDIDO:
                     jugador.incPuntaje();
                     this.notificar(Eventos.SACARFICHA);
                 }else{
                     this.notificar(Eventos.FICHAAGREGADA);
                     cambiarTurno();
                 }
            }
        }if ((jugador.getNumeroFichasRestante() == 0)) { //Si tiene 0 fichas entonces notifico
            this.notificar(Eventos.SINFICHASPARAAGREGAR);
        }

        return salida;
    }

    public void cambiarTurno(){
        if (this.turno == 1)
            this.turno ++;
        else this.turno --;
        this.notificar(Eventos.CAMBIODETURNO);
    }

    public void sacarFicha(Ficha ficha){
        boolean resultadoSacar = false;
        if (ficha != null) {
            Jugador duenioFicha = ficha.getJugador();
            resultadoSacar = this.tablero.sacarFicha(ficha, darTurno());
            if (((boolean) resultadoSacar)) {
                switch (duenioFicha.getNumero()) {
                    case 0 -> {
                        jugadores.get(0).sacarFicha(ficha);
                        break;
                    }
                    case 1 -> {
                        jugadores.get(1).sacarFicha(ficha);
                        break;
                    }
                }
            }
        }
        //Debo llamar a termino la partida cada vez que se saca una ficha, para verificar si alguno puede seguir jugando o no.
        if (terminoLaPartida()){
            //INFORMO QUE LA PARTIDA SE HA TERMINADO Y MUESTRO GANADOR:
            //this.notificar(Eventos.FINPARTIDA); //VER SI FUNCIONA ACA O SI CONVIENE PASARLO A EL METODO terminoLaPartida();
        } else if (resultadoSacar) {
            cambiarTurno(); //Solo si saco la ficha cambia el turno
            this.notificar(Eventos.FICHASACADA);
            this.notificar(Eventos.CAMBIODETURNO); //VERIFICAR SI ESTA POSICION ESTA BIEN

        } else if (!resultadoSacar) {
            this.notificar(Eventos.NOSACADA);

        }
    }

    public void moverFichas(Ficha ficha, int tmover, int fmover, int cmover, Jugador jugador){
        if (this.tablero.moverFichas(ficha,tmover,fmover,cmover,jugador)){
            this.notificar(Eventos.FICHAMOVIDA);
            if (this.tablero.verificarRaya(tmover,fmover,cmover,jugador,ficha)){
                this.notificar(Eventos.SACARFICHA);
            }
            cambiarTurno();
        }else{
            this.notificar(Eventos.FICHANOMOVIDA);

        }
    }

    public boolean terminoLaPartida(){
        //AGREGAR LA VERIFICACION DE MOVIMIENTOS, VIENDO SI TIENE ALGUNA FICHA QUE PUEDA MOVER:
        boolean salida = false;
        if ((jugadores.get(0).getFichasTotales() < 3) || (jugadores.get(1).getFichasTotales() < 3)){
            if (this.tablero.sinMovimientos(jugadores.get(0)) || (this.tablero.sinMovimientos(jugadores.get(1)))) {
                this.notificar(Eventos.FINPARTIDA);
                salida = true;
            }
        }
        return salida;
    }

    public int[] getPuntajes(){
        int[] salida = new int[2];
        salida[0] = jugadores.get(0).getPuntaje();
        salida[1] = jugadores.get(1).getPuntaje();
        return salida;
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
        if (this.turno == 1){
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
        }else if (fichaJugador2 != null){
            fichaSalida = fichaJugador2;
        }
        return fichaSalida;
    }

    public Jugador getUltimoJugadorAgregado() {
        return jugadores.get(jugadores.size() - 1); //verificar si no se va de rango
    }

    public Eventos getEstadoJugador(Jugador jugador) {
        Eventos eventos = null;
        if (jugador.getNumeroFichasRestante() == 1){
            eventos = Eventos.SINFICHASPARAAGREGAR;
        } else if (jugador.getNumeroFichasRestante() > 1) {
            eventos = Eventos.FICHAAGREGADA;
        }else if (terminoLaPartida()){
            eventos = Eventos.FINPARTIDA;
        }
        return eventos;
    }

    public String[] getNombreJugadores() {
        String[] salida = new String[2];
        salida[0] = this.jugadores.get(0).getNombre();
        salida[1] = this.jugadores.get(1).getNombre();
        return salida;
    }
}
