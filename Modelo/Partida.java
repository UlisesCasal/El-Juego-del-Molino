package Modelo;

import Controlador.TipoFicha;
import Services.Serializacion;
import Vistas.Errores;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import Services.Serializador;


import java.io.Serial;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Partida extends ObservableRemoto implements IPartida, Serializable {
    @Serial
    private static final long serialVersionUID = -3650013750644466352L;
    private List<Jugador> jugadores = new ArrayList<Jugador>();
    private Tablero tablero;
    private int turno = 1;
    private int numeroJugadores;

    private Ficha fichaAgregada;
    private Ficha fichaEliminada;
    private boolean serializado;
    /*
    @Override
    public Ficha getFichaAgregada() throws RemoteException {
        return fichaAgregada;
    }
    @Override
    public Ficha getFichaEliminada() throws RemoteException {
        return fichaEliminada;
    }

     */

    public Partida(){
        this.tablero = null;
        this.fichaEliminada = null;
        this.jugadores = new ArrayList<>();
        this.fichaAgregada = null;
        tablero = new Tablero();
        this.numeroJugadores = 0;
    }

    @Override
    public void iniciarPartida() throws RemoteException {
        //Metodo que verifica si hay dos jugadores conectados, en caso de haberlo inicia la partida:
        if (jugadores.size() == 2){
            notificarObservadores(Eventos.MENU);
        }
    }
    @Override
    public void setJugador(String nombre) throws RemoteException{
        if (jugadores.size() < 2) {
            jugadores.add(new Jugador(nombre, this.numeroJugadores++));
        }else{
            notificarObservadores(Eventos.ESPERANDOJUGADORES);
        }
    }

    @Override
    public Jugador darTurno() throws RemoteException{
        Jugador salida = null;
        if (this.turno == 1) {
            salida =  jugadores.get(0);
        }else if (this.turno == 2){
            salida = jugadores.get(1);
        }
        return salida;
    }

    @Override
    public boolean ponerFicha(int t, int f, int c) throws RemoteException {
        /* METODO QUE REALIZA LA INSERCION DE FICHA, TAMBIEN REVISA SI SE PRODUJO
         UN MOLINO*/
        Ficha ficha = darTurno().getFichaNoPuesta();
        boolean salida = false;

        //Corroboro que no sea nulo y tenga mas de una fich:
        if ((darTurno().getNumeroFichasRestante() >= 1) && (ficha != null)){
             if (this.tablero.agregarFicha(f,c,t,ficha,darTurno())){
                 //Si la pudo ingresar, pongo el flag en true, y verifico si se produjo un molino:
                 darTurno().setPosicionFicha(new int[]{t, f, c}, ficha);
                 ficha = darTurno().getFicha(t,f,c);
                 this.fichaAgregada = ficha;
                 salida = true;
                 if (this.tablero.verificarRaya(t,f,c,darTurno(), ficha)){
                     //Si me da true que hay una nueva raya:
                     //1. debo retirar alguna ficha del oponente:
                     //2. me debe ingresar la ficha a retirar
                     //3. debo incrementar el puntaje del jugador
                     //DEBERE NOTIFICAR DE LO SUCEDIDO:
                     darTurno().incPuntaje();
                     notificarObservadores(Eventos.SACARFICHA);
                 }else{
                     notificarObservadores(Eventos.FICHAAGREGADA);
                     cambiarTurno();
                     terminoLaPartida();
                 }
            }
        }if ((darTurno().getNumeroFichasRestante() == 0)) { //Si tiene 0 fichas entonces notifico
            //notificarObservadores(Eventos.SINFICHASPARAAGREGAR);
        }if (!salida){
            notificarObservadores(Eventos.FICHANOAGREGADA);
        }

        return salida;
    }

    private void cambiarTurno() throws RemoteException {
        if (this.turno == 1)
            this.turno ++;
        else this.turno --;
        notificarObservadores(Eventos.CAMBIODETURNO);
    }

    @Override
    public void sacarFicha(int t, int f, int c) throws RemoteException {
        Ficha ficha = getFicha(t,f,c);
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
                    this.fichaEliminada = ficha;
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
                notificarObservadores(Eventos.FICHASACADA);
                cambiarTurno(); //Solo si saco la ficha cambia el turno

            } else if (!resultadoSacar) {
                notificarObservadores(Eventos.NOSACADA);

            }
        }
    }

    @Override
    public void moverFichas(int t, int f, int c, int tmover, int fmover, int cmover) throws RemoteException {
        //Set posicion y set jugador a ficha

        Jugador jugadorActual = darTurno();
        Ficha ficha = jugadorActual.getFicha(t,f,c);

        //Creo una nueva ficha con la posicion de la eliminada por que sino se pierde.
        this.fichaEliminada = new Ficha(darTurno());
        this.fichaEliminada.setPosicion(new int[]{t,f,c});
        if (this.tablero.moverFichas(ficha,tmover,fmover,cmover,jugadorActual)){

            fichaAgregada = jugadorActual.getFicha(tmover,fmover,cmover);
            notificarObservadores(Eventos.FICHAMOVIDA);

            if (this.tablero.verificarRaya(tmover,fmover,cmover,jugadorActual,ficha)){
                jugadorActual.incPuntaje();
                notificarObservadores(Eventos.SACARFICHA);
            }else{
                cambiarTurno();
            }
            terminoLaPartida();

        }else{
            notificarObservadores(Eventos.FICHANOMOVIDA);

        }
    }

    private boolean terminoLaPartida() throws RemoteException {
        /* METODO QUE VERIFICA SI LA PARTIDA TERMINO, TENIENDO EN CUENTA SI ALGUN JUGADOR NO POSEE
        MAS FICHAS O ALGUNO NO POSEE MAS MOVIMIENTOS EN TODAS SUS FICHAS PUESTAS EN EL TABLERO
         */
        boolean salida = false;
        if (jugadores.get(0).getNumeroPuestas() == 9 && jugadores.get(1).getNumeroPuestas() == 9) {
            if ((jugadores.get(0).getFichasTotales() < 3) || (jugadores.get(1).getFichasTotales() < 3) || (this.tablero.sinMovimientos(jugadores.get(0)) || (this.tablero.sinMovimientos(jugadores.get(1))))) {
                notificarObservadores(Eventos.FINPARTIDA);
                serializar();
                notificarObservadores(Eventos.SERIALIZADO);
                salida = true;
            }
        }
        return salida;
    }

    private void serializar() throws RemoteException {
        /*
        METODO QUE REALIZA LA SERIALIZACION
         */
        if (!serializado) {
            this.jugadores.get(0).resetFichas();
            this.jugadores.get(1).resetFichas();
            Serializacion.serializar(this.jugadores);
        }
    }

    @Override
    public int[] getPuntajes() throws RemoteException{
        int[] salida = new int[2];
        salida[0] = jugadores.get(0).getPuntaje();
        salida[1] = jugadores.get(1).getPuntaje();
        return salida;
    }

    /**
    public List<Ficha> getFichasPuestas() throws RemoteException {
        /*
        METODO QUE RETORNA TODAS LAS FICHAS PUESTAS EN EL TABLERO:
         *//*
        List<Ficha> fichas = jugadores.get(0).getFichas();
        List<Ficha> fichas2 = jugadores.get(1).getFichas();
        fichas.addAll(fichas2);
        return fichas;
    }
    */


    @Override
    public void desconexion(String jugador) throws RemoteException {
        for (Jugador j: jugadores) {
            if (Objects.equals(j.getNombre(), jugador)){
                j.incPuntaje();

            }
        }
        serializar();
        notificarObservadores(Eventos.DESCONEXION);
    }

    @Override
    public void reiniciar() throws RemoteException{
        this.tablero = null;
        this.fichaEliminada = null;
        this.jugadores = new ArrayList<>();
        this.fichaAgregada = null;
        tablero = new Tablero();
        this.numeroJugadores = 0;
    }

    @Override
    public boolean verificarNombreUnivoco(String nombre) throws RemoteException{
        boolean salida = true;
        for (int i = 0; i < jugadores.size(); i++) {
            if (Objects.equals(jugadores.get(i).getNombre(), nombre)){
                salida = false;
            }
        }
        return salida;
    }

    @Override
    public String getTurnoAnterior() throws RemoteException{
        //SACAR
        String jugador;
        if (this.turno == 1){
         jugador = String.valueOf(this.jugadores.get(1).getNumero());
        }else jugador = String.valueOf(this.jugadores.get(0).getNumero());
        return jugador;
    }

    @Override
    public Ficha getFicha(int t, int f, int c) throws RemoteException{
        Ficha fichaJugador1 = jugadores.get(0).getFicha(t,f,c);
        Ficha fichaJugador2 = jugadores.get(1).getFicha(t,f,c);
        Ficha fichaSalida = null;
        if (fichaJugador1 != null){
            fichaSalida = fichaJugador1;
        }else if (fichaJugador2 != null){
            fichaSalida = fichaJugador2;
        }
        return fichaSalida;
    }

    @Override
    public int[] getPosicionFicha(TipoFicha tipoFicha) throws RemoteException {
        int[] salida = null;
        switch (tipoFicha){
            case AGREGADA -> salida = this.fichaAgregada.getPosicion();
            case ELIMINADA -> salida = this.fichaEliminada.getPosicion();
        }
        return salida;
    }

    @Override
    public boolean verificarFichaMover(int t, int f, int c) throws RemoteException {
        boolean salida = false;

            Ficha ficha = getFicha(t, f, c);
            if (ficha != null) {
                if (ficha.getJugador() == darTurno()) {
                    salida = true;
                }

            }
        return salida;
    }

    @Override
    public boolean verificarPosicionVacia(int t, int f, int c) throws RemoteException {
        boolean salida = false;
        Ficha ficha = getFicha(t, f, c);
        if (ficha == null) {

            salida = true;
        }
        return  salida;
    }

    @Override
    public Jugador getUltimoJugadorAgregado() throws RemoteException{
        return jugadores.get(jugadores.size() - 1);
    }

    @Override
    public int getNumeroJugador(TipoFicha tipoFicha) throws RemoteException {
        int salida = 0;
        switch (tipoFicha){
           case AGREGADA -> salida = this.fichaAgregada.getJugador().getNumero();
            case ELIMINADA -> salida = this.fichaEliminada.getJugador().getNumero();

        }
        return salida;
    }

    @Override
    public Eventos getEstadoJugador() throws RemoteException{
        //METODO QUE DEVUELVE EL ESTADO DEL JUGADOR DEPENDE EL NUMERO DE FICHAS O SI TERMINO LA PARTIDA

        Eventos eventos = null;
        Jugador jugador = darTurno();
        if (jugador.getNumeroFichasRestante() == 0){
            eventos = Eventos.SINFICHASPARAAGREGAR;
        } else if (jugador.getNumeroFichasRestante() > 0) {
            eventos = Eventos.FICHAAGREGADA;
        }else{
            terminoLaPartida();
        }
        return eventos;
    }

    @Override
    public String[] getNombreJugadores() throws RemoteException {
        String[] salida = new String[2];
        salida[0] = this.jugadores.get(0).getNombre();
        salida[1] = this.jugadores.get(1).getNombre();
        return salida;
    }
    
    

}
