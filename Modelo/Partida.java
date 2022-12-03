package Modelo;

import Interaccion.Observable;
import Interaccion.Observador;

import java.util.ArrayList;
import java.util.List;

public class Partida implements Observable, IPartida {
    private List<Jugador> jugadores = new ArrayList<Jugador>();
    private Tablero tablero;
    private int turno = 1;
    private int numeroJugadores;
    private List<Observador>  observadores = new ArrayList<>();

    public Partida(){
        tablero = new Tablero();
        this.numeroJugadores = 0;
        boolean turno = true;
    }

    @Override
    public void iniciarPartida(){
        //Metodo que verifica si hay dos jugadores conectados, en caso de haberlo inicia la partida:
        if (jugadores.size() == 2){
            this.notificar(Eventos.INICIARPARTIDA);
        }
    }
    @Override
    public void setJugador(String nombre){
        jugadores.add(new Jugador(nombre, this.numeroJugadores++ ));
    }

    @Override
    public Jugador darTurno(){
        if (this.turno == 1) {
            return jugadores.get(0);
        }else if (this.turno == 2){
            return jugadores.get(1);
        }
        return null;
    }

    @Override
    public boolean ponerFicha(Jugador jugador, int t, int f, int c){
        /* METODO QUE REALIZA LA INSERCION DE FICHA, TAMBIEN REVISA SI SE PRODUJO
         UN MOLINO*/
        Ficha ficha = jugador.getFichaNoPuesta();
        boolean salida = false;

        //Corroboro que no sea nulo y tenga mas de una fich:
        if ((jugador.getNumeroFichasRestante() >= 1) && (ficha != null)){
             if (this.tablero.agregarFicha(f,c,t,ficha,jugador)){
                 //Si la pudo ingresar, pongo el flag en true, y verifico si se produjo un molino:
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
                     terminoLaPartida();
                 }
            }
        }if ((jugador.getNumeroFichasRestante() == 0)) { //Si tiene 0 fichas entonces notifico
            //this.notificar(Eventos.SINFICHASPARAAGREGAR);
        }if (!salida){
            this.notificar(Eventos.FICHANOAGREGADA);
        }

        return salida;
    }

    private void cambiarTurno(){
        if (this.turno == 1)
            this.turno ++;
        else this.turno --;
        this.notificar(Eventos.CAMBIODETURNO);
    }

    @Override
    public void sacarFicha(Ficha ficha){
        boolean resultadoSacar = false;
        //Primero me fijo si el jugador contrario tiene fichas a eliminar
        Jugador jugadorContrario;
        if (darTurno() == jugadores.get(0)){
            jugadorContrario = jugadores.get(1);
        }else{
            jugadorContrario = jugadores.get(0);
        }

        if (ficha != null) {
            if ((jugadorContrario.verificarFichasEliminables())) {
                Jugador duenioFicha = ficha.getJugador();
                resultadoSacar = this.tablero.sacarFicha(ficha, darTurno());
                if (resultadoSacar) {
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
            else{
                cambiarTurno(); //Si el jugador contrario no tiene fichas eliminables cambio de turno y salgo
                return;         // del metodo
            }
        }
        //Debo llamar a termino la partida cada vez que se saca una ficha, para verificar si alguno puede seguir jugando o no.
        if (!terminoLaPartida()) {
            if (resultadoSacar) {
                this.notificar(Eventos.FICHASACADA);
                cambiarTurno(); //Solo si saco la ficha cambia el turno

            } else if (!resultadoSacar) {
                this.notificar(Eventos.NOSACADA);

            }
        }
    }

    @Override
    public void moverFichas(Ficha ficha, int tmover, int fmover, int cmover, Jugador jugador){
        Jugador jugadorActual;
        if (this.tablero.moverFichas(ficha,tmover,fmover,cmover,jugador)){
            this.notificar(Eventos.FICHAMOVIDA);
            if (this.tablero.verificarRaya(tmover,fmover,cmover,jugador,ficha)){
                this.notificar(Eventos.SACARFICHA);
                jugador.incPuntaje();
            }else{
                cambiarTurno();
            }
            terminoLaPartida(); //VER SI ANDA

        }else{
            this.notificar(Eventos.FICHANOMOVIDA);

        }
    }

    private boolean terminoLaPartida(){
        /* METODO QUE VERIFICA SI LA PARTIDA TERMINO, TENIENDO EN CUENTA SI ALGUN JUGADOR NO POSEE
        MAS FICHAS O ALGUNO NO POSEE MAS MOVIMIENTOS EN TODAS SUS FICHAS PUESTAS EN EL TABLERO
         */
        boolean salida = false;
        if (jugadores.get(0).getNumeroPuestas() == 9 && jugadores.get(1).getNumeroPuestas() == 9) {
            if ((jugadores.get(0).getFichasTotales() < 3) || (jugadores.get(1).getFichasTotales() < 3) || (this.tablero.sinMovimientos(jugadores.get(0)) || (this.tablero.sinMovimientos(jugadores.get(1))))) {
                this.notificar(Eventos.FINPARTIDA);
                salida = true;
            }
        }
        return salida;
    }

    @Override
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

    @Override
    public String getTurnoAnterior() {
        String jugador;
        if (this.turno == 1){
         jugador = String.valueOf(this.jugadores.get(1).getNumero());
        }else jugador = String.valueOf(this.jugadores.get(0).getNumero());
        return jugador;
    }

    @Override
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

    @Override
    public Jugador getUltimoJugadorAgregado() {
        return jugadores.get(jugadores.size() - 1); //verificar si no se va de rango
    }

    @Override
    public Eventos getEstadoJugador(Jugador jugador) {
        //METODO QUE DEVUELVE EL ESTADO DEL JUGADOR DEPENDE EL NUMERO DE FICHAS O SI TERMINO LA PARTIDA

        Eventos eventos = null;
        if (jugador.getNumeroFichasRestante() == 0){
            eventos = Eventos.SINFICHASPARAAGREGAR;
        } else if (jugador.getNumeroFichasRestante() > 0) {
            eventos = Eventos.FICHAAGREGADA;
        }else{
            System.out.println("Error de cantidad menor a 3"); //SACAR
            terminoLaPartida();
        }
        return eventos;
    }

    @Override
    public String[] getNombreJugadores() {
        String[] salida = new String[2];
        salida[0] = this.jugadores.get(0).getNombre();
        salida[1] = this.jugadores.get(1).getNombre();
        return salida;
    }
}
