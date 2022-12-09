package Controlador;

import Modelo.*;
import Vistas.Errores;
import Vistas.EstadosVista;
import Vistas.IVista;
import Vistas.VistaGrafica.VGrafic;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.rmi.RemoteException;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Objects;
import static Modelo.Partida.serializador;
public class Controlador implements IControladorRemoto {

    private IPartida modelo;
    private IVista vista;
    private Jugador jugador;
    public Controlador(IVista vista){
        this.vista = vista;
        this.vista.setControlador(this);
        this.vista.iniciar();
        //setModeloRemoto(this);
    }

    public boolean ponerFicha(int t, int f, int c) { // Ver por que retorna un boolean
        boolean salida = false;
        try {
            salida = this.modelo.ponerFicha(t,f,c);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return salida;
    }

    public void sacarFicha(int t, int f, int c){
        //Metodo que llama al sacar ficha:
        try {
            this.modelo.sacarFicha(this.modelo.getFicha(t,f,c));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void moverFicha(int t, int f, int c, int tm, int fm, int cm){
        try {
            this.modelo.moverFichas(this.modelo.getFicha(t,f,c), tm, fm, cm, this.modelo.darTurno());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean cambiarEstadosVista(Eventos evento){ // ver si es necesario devolver un bool
        switch (evento){
            case SACARFICHA -> this.vista.cambiarEstado(EstadosVista.SACARFICHA);
            case FICHAAGREGADA -> this.vista.cambiarEstado(EstadosVista.INGRESARFICHA);
            case SINFICHASPARAAGREGAR -> this.vista.cambiarEstado(EstadosVista.MOVERFICHA);
            case FINPARTIDA -> this.vista.mostrarPuntajesFinales();
            case SERIALIZADO -> this.vista.puntajeHistorico();
        }
        return true;
    }

    public boolean esJugadorActual(){
        Jugador jugadorTurno = null;
        try {
            jugadorTurno = this.modelo.darTurno();

        }catch (RemoteException e){
            e.printStackTrace();
        }
        return this.jugador.getNumero() == jugadorTurno.getNumero();
    }

    public String[] getPuntajesFinales() {
        String[] salida = new String[2];
        try {
            salida[0] = String.valueOf(this.modelo.getPuntajes()[0]);
            salida[1] = String.valueOf(this.modelo.getPuntajes()[1]);
            return salida;
        }
        catch( RemoteException e){
            e.printStackTrace();
        }
        return salida;
    }

    public String getCharJugadorFicha() {
        String salida = "";
        try {

            if (this.modelo.darTurno().getNumero() == 1) {
                salida = "(¤)";
            } else salida = "(×)";
        }catch (RemoteException e){
            e.printStackTrace();
        }
        return salida;
    }

    public String getCharJugadorFicha(Ficha ficha){
        String salida;
        if (ficha.getJugador().getNumero() == 1){
            salida = "(¤)";
        }else{
            salida = "(×)";
        }
        return salida;
    }

    public void setJugador(String nombre) throws RemoteException {
        try {
            this.modelo.setJugador(nombre);
            this.jugador = this.modelo.getUltimoJugadorAgregado();
            this.modelo.iniciarPartida();
        }catch (RemoteException e){
            e.printStackTrace();
        }

    }

    public boolean verificarFicha(int t, int f, int c) {
        //verifica si la ficha es nula y si no lo es verifica si es del oponente:
        boolean salida = false;
        try {

            Ficha ficha = this.modelo.getFicha(t, f, c);
            if (ficha != null) {
                if (ficha.getJugador() == this.modelo.darTurno()) {
                    salida = true;
                }
            } else {
                this.vista.mostrarErrores(Errores.NOSEPUDOMOVERFICHA);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
        return salida;
    }

    public boolean verificarPosicionVacia(int t, int f, int c){
        boolean salida = false;
        try {

            Ficha ficha = this.modelo.getFicha(t, f, c);
            if (ficha == null) {
                salida = true;
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
        return  salida;
    }

    public String getNombreJugador() {
        return this.jugador.getNombre();
    }

    public String[] getNombreJugadores() {
        String[] salida = null;
        try {

            salida = this.modelo.getNombreJugadores();
        }catch (RemoteException e){
            e.printStackTrace();
        }
        return salida;
    }

    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
        this.modelo = (IPartida) modeloRemoto;
    }

    @Override
    public void actualizar(IObservableRemoto modelo, Object evento) throws RemoteException {
        //Metodo que evalua las llamadas del modelo, y en base a eso realiza o no una accion en la vista:
        if(evento instanceof Eventos) {
            Jugador jugadorTurno = this.modelo.darTurno();

            if (evento == Eventos.ESPERANDOJUGADORES){
                this.vista.mostrarPantallaEspera();
            }
            if (evento == Eventos.MENU){
                this.vista.cambiarEstado(EstadosVista.MENU);
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
                actualizarTablero((Eventos) evento);
                //this.vista.actualizarTablero();

            }if (evento == Eventos.FINPARTIDA) {
                cambiarEstadosVista(Eventos.FINPARTIDA);
            }
            if (evento == Eventos.SACARFICHA){
                actualizarTablero(Eventos.FICHAAGREGADA);
                //this.vista.actualizarTablero();
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
            }if (evento == Eventos.SERIALIZADO){
                cambiarEstadosVista(Eventos.SERIALIZADO);
            }
        }
    }


    private void actualizarTablero(Eventos evento) {
        try {
            Ficha fichaAgregada;
            Ficha fichaEliminada;
            int[] posicion;
            String charJugador;
            if (evento == Eventos.FICHAAGREGADA || evento == Eventos.FICHAMOVIDA) {
                fichaAgregada = this.modelo.getFichaAgregada();
                posicion = fichaAgregada.getPosicion();
                charJugador = getCharJugadorFicha(fichaAgregada);
                this.vista.actualizarTablero(posicion, charJugador, Eventos.FICHAAGREGADA);
            }
            if (evento == Eventos.FICHASACADA || evento == Eventos.FICHAMOVIDA) {
                try {
                    fichaEliminada = this.modelo.getFichaEliminada();
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
                posicion = fichaEliminada.getPosicion();
                charJugador = getCharJugadorFicha(fichaEliminada);
                this.vista.actualizarTablero(posicion, charJugador, Eventos.FICHASACADA);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    public String[] desSerializar() {
        /*
        METODO QUE AGARRA TODOS LOS JUGADORES Y LOS RETORNA.
        - VERIFICAR SI FUNCIONA
         */
        ArrayList <Jugador> jugadores = null;
        Object[] recuperado = serializador.readObjects();
        for (int i = 0; i < recuperado.length; i++){
            jugadores.add((Jugador) recuperado[i]);
        }
        String[] puntajesJugadores = new String[jugadores.size()];

        for (int i = 0; i < jugadores.size(); i++) {
            puntajesJugadores[i] = jugadores.get(i).getNombre() + ": " + jugadores.get(i).getPuntaje();

        }
        return puntajesJugadores;
    }

    public void cambiarAVistaGrafica() {
        this.vista = new VGrafic();
        this.vista.setControlador(this);
        this.vista.iniciar();
        this.vista.cambiarEstado(EstadosVista.INGRESARFICHA);
    }
}
