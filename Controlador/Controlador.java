package Controlador;

import Modelo.Eventos;
import Modelo.Ficha;
import Modelo.Jugador;
import Modelo.Partida;
import Interaccion.Observable;
import Interaccion.Observador;
import Vistas.Errores;
import Vistas.EstadosVista;
import Vistas.IVista;

import java.util.Objects;

public class Controlador implements Observador {
    private Partida modelo;
    private IVista vista;
    private Jugador jugador;
    public Controlador(Partida partida, IVista vista){
        this.modelo = partida;
        this.vista = vista;
        this.vista.setControlador(this);
        this.modelo.agregarObservador(this);
    }

    public boolean ponerFicha(int t, int f, int c){
        return this.modelo.ponerFicha(this.modelo.darTurno(),t,f,c);
    }

    public void sacarFicha(int t, int f, int c){
        //Metodo que llama al sacar ficha:
        this.modelo.sacarFicha(this.modelo.getFicha(t,f,c));

    }

    public void moverFicha(int t, int f, int c, int tm, int fm, int cm){
        this.modelo.moverFichas(this.modelo.getFicha(t,f,c), tm, fm, cm, this.modelo.darTurno());
    }


    @Override
    public void actualizar(Object evento, Observable observable) {
        //Metodo que evalua las llamadas del modelo, y en base a eso realiza o no una accion en la vista:
        if(evento instanceof Eventos) {
            if (evento == Eventos.ESPERANDOJUGADORES){
                this.vista.mostrarPantallaEspera();
            }
            if (evento == Eventos.INICIARPARTIDA){
                this.vista.cambiarEstado(EstadosVista.INGRESARFICHA);
            }
            if (evento == Eventos.CAMBIODETURNO){
                Jugador jugadorTurno = this.modelo.darTurno();
                if (jugadorTurno.getNumero() != this.jugador.getNumero()){
                    this.vista.cambiarEstado(EstadosVista.BLOQUEADA);
                    
                }
            }
            if ((evento == Eventos.FICHAAGREGADA) || (evento == Eventos.FICHAMOVIDA) || (evento == Eventos.FICHASACADA)){
                this.vista.actualizarTablero();
                this.vista.cambiarEstado(EstadosVista.INGRESARFICHA);
            }if (evento == Eventos.FINPARTIDA) {
                this.vista.mostrarPuntajesFinales();
            }
            if (evento == Eventos.SACARFICHA){
                this.vista.cambiarEstado(EstadosVista.SACARFICHA);
            }if (evento == Eventos.NOSACADA){
                this.vista.mostrarErrores(Errores.NOSEPUDOSACARFICHA);
            }if (evento == Eventos.FICHANOMOVIDA){
                this.vista.mostrarErrores(Errores.NOSEPUDOMOVERFICHA);
            }if (evento == Eventos.SINFICHASPARAAGREGAR){
                this.vista.cambiarEstado(EstadosVista.MOVERFICHA);
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

    public void setJugador(String nombre) {
        this.modelo.setJugador(nombre);
        this.jugador = this.modelo.getUltimoJugadorAgregado();
        this.modelo.iniciarPartida();

    }

    public boolean verificarFicha(int t, int f, int c) {
        //Verificar la integridad de tener este Ficha importado en el controlador
        //verifica si la ficha es nula y si no lo es verifica si es del oponente:
        Ficha ficha = this.modelo.getFicha(t,f,c);
        boolean salida = false;
        if (ficha != null) {
            if (ficha.getJugador() == this.modelo.darTurno()) {
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

    public String getTurno() {
        return this.modelo.darTurno().getNombre();
    }

    public String getNombreJugador() {
        return this.jugador.getNombre();
    }
}
