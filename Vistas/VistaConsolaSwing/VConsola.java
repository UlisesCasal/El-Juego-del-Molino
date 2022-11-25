package Vistas.VistaConsolaSwing;

import Controlador.Controlador;
import Vistas.Errores;
import Vistas.EstadosVista;
import Vistas.IVista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import static Vistas.EstadosVista.*;

public class VConsola extends JFrame implements IVista {
    private JPanel panel1;
    private JTextArea consola;
    private JTextField textoInput;
    private JButton botonEnter;
    private Controlador controlador;
    private EstadosVista estadoActual = EstadosVista.INGRESARFICHA;
    private String ultFichaIngre;
    private String fichaEliminar;
    private int[] ficha = new int[3]; //Verificar su integridad
    private String tablero ="""
                                    
                    A1( )════════════A2( )════════════A3( )
                    ║ B1( )══════════B2( )══════════B3( ) ║
                    ║ ║ C1( )════════C2( )════════C3( ) ║ ║
                    ║ ║ ║                             ║ ║ ║
                    ║ ║ ║                             ║ ║ ║
                    ║ ║ ║                             ║ ║ ║
                   A8( )═B8( )═C8( )                C4( )═B4( )═A4( )
                    ║ ║ ║                             ║ ║ ║
                    ║ ║ ║                             ║ ║ ║
                    ║ ║ ║                             ║ ║ ║
                    ║ ║ C7( )════════C6( )════════C5( ) ║ ║
                    ║ B7( )══════════B6( )══════════B5( ) ║
                    A7( )════════════A6( )════════════A5( )""";

    public VConsola(){
        super("El Juego del Molino");
        setContentPane(this.panel1);
        botonEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch (estadoActual){
                    case INGRESONOMBRE:{
                        registrarJugador();
                        break;
                    }
                    case ESPERANDOCONEXION:{
                        mostrarPantallaEspera();
                        break;
                    }
                    case INGRESARFICHA: {
                        mostrarTurno();
                        println("Ingrese la posición de la ficha que quiere ingresar: ");
                        ponerFicha();
                        break;
                    }
                    case SACARFICHA:{
                        mostrarTurno();
                        mostrarSacarFicha();
                        //cambiarEstado(MOVERFICHA);
                        break;
                    }
                    case MOVERFICHA:{
                        mostrarTurno();
                        mostrarMoverFicha1fase();
                        break;
                    }
                    case MOVERFICHA2DAFASE:{
                        mostrarTurno();
                        mostrarMoverFicha2fase();
                    }
                }
            }
        });
    }

    private void registrarJugador() {
        String nombre = textoInput.getText().trim();
        if (nombre != null){
            this.controlador.setJugador(nombre);
            cambiarEstado(ESPERANDOCONEXION);
        }else{
            this.println("Por favor ingrese un nombre válido...");
        }
    }


    public void mostrarTurno(){
        this.println("Es el turno de: " + this.controlador.getTurno());
    }

    public void println(String texto) {
        this.consola.append(texto + "\n");
    }

    public void println() {
        println("");
    }

    public void limpiarConsola(){
        consola.setText("");
    }

    @Override
    public void iniciar() {
        /*
        1- a la hora se iniciar se debe generar una espera de los jugadores
        2- una vez se presione un input del boton y el estado es espera, se debe cargar el jugador
        3- si hay menos de un jugador no inicia
        4- sino el estado se cambia a Estado.INGRESARFICHA
         */
        this.println("Bienvenido al juego del Molino!!!");
        this.println("Para iniciar ingrese su nombre: ");
        cambiarEstado(INGRESONOMBRE);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public void mostrarTablero() {
        println(this.tablero);
    }

    @Override
    public void mostrarPuntajesFinales() {

    }

    @Override
    public int[] traductor(String posicionSimbolica) {
        //Metodo que traduce una posicion simbolica una efectiva.
        //VER SI SE LO PUEDO PASAR AL CONTROLADOR:
        int[] salida = new int[]{-1,-1,-1};
        switch (posicionSimbolica) {
            case "A1" -> {
                salida = new int[]{0, 0, 0};
                break;
            }
            case "A2" -> {
                salida = new int[]{0,0,1};
                break;
            }
            case "A3" -> {
                salida = new int[]{0,0,2};
                break;
            }
            case "A4" -> {
                salida = new int[]{0,1,2};
                break;
            }
            case "A5" -> {
                salida = new int[]{0,2,2};
                break;
            }
            case "A6" -> {
                salida = new int[]{0,2,1};
                break;
            }
            case "A7" -> {
                salida = new int[]{0,2,0};
                break;
            }
            case "A8" -> {
                salida = new int[]{0,1,0};
                break;
            }
            case "B1" -> {
                salida = new int[]{1,0,0};
                break;
            }
            case "B2" -> {
                salida = new int[]{1,0,1};
                break;
            }
            case "B3" -> {
                salida = new int[]{1,0,2};
                break;
            }
            case "B4" -> {
                salida = new int[]{1,1,2};
                break;
            }
            case "B5" -> {
                salida = new int[]{1,2,2};
                break;
            }
            case "B6" -> {
                salida = new int[]{1,2,1};
                break;
            }
            case "B7" -> {
                salida = new int[]{1,2,0};
                break;
            }
            case "B8" -> {
                salida = new int[]{1,1,0};
                break;
            }
            case "C1" -> {
                salida = new int[]{2,0,0};
                break;
            }
            case "C2" -> {
                salida = new int[]{2,0,1};
                break;
            }
            case "C3" -> {
                salida = new int[]{2,0,2};
                break;
            }
            case "C4" -> {
                salida = new int[]{2,1,2};
                break;
            }
            case "C5" -> {
                salida = new int[]{2,2,2};
                break;
            }
            case "C6" -> {
                salida = new int[]{2,2,1};
                break;
            }
            case "C7" -> {
                salida = new int[]{2,2,0};
                break;
            }
            case "C8" -> {
                salida = new int[]{2,1,0};
                break;
            }
            default -> println("La posicion ingresada es inválida...");
        }
        textoInput.setText("");
        return salida;
    }

    @Override
    public void cambiarEstado(EstadosVista estado) {
        this.estadoActual = estado;
        switch (this.estadoActual) {
            case ESPERANDOCONEXION -> mostrarPantallaEspera();
            case INGRESONOMBRE -> this.println("Por favor ingrese su nombre: ");
            case INGRESARFICHA -> this.println("Ingrese la ficha que quiere agregar: ");
            case SACARFICHA -> this.println("Ingrese la posicion de la ficha a eliminar: ");
            case MOVERFICHA -> this.println("Ingrese la ficha a mover: ");
            case MOVERFICHA2DAFASE -> this.println("Ingrese la posicion a mover la ficha: ");
        }
    }

    @Override
    public void mostrarErrores(Errores errores) {
        if (errores == Errores.NOSEPUDOSACARFICHA){
            mostrarTablero();
            this.println("LA FICHA INGRESADA NO SE HA PODIDO ELIMINAR..." + "\n" +
                    "Por favor ingrese una posicion válida");
        }
    }

    @Override
    public void mostrarSacarFicha() {
        /* 1- se muestra el tablero.
           2- se pide el dato de la ficha a eliminar.
           3- informo si se elimino de manera adecuada.
           4- muestro tablero actualizado.
         */
        //TENER EN CUENTA EL JUGADOR QUE EJECUTA DICHA PETICION:
        String ficha = textoInput.getText().toUpperCase();
        int[] posicion = traductor(ficha);
        this.ultFichaIngre = null; //Asigno nulo asi cuando actualizo no me agrega otro char
        this.fichaEliminar = this.tablero.substring(this.tablero.indexOf(ficha), this.tablero.indexOf(ficha) + 5);
        this.controlador.sacarFicha(posicion[0], posicion[1], posicion[2]);
        //QUEDA SACAR EL CHAR DEL JUGADOR

    }

    @Override
    public void actualizarTablero() {
        String fichaPura;
        //Hace un reemplazo y procede a mostrar el tablero actualizado
        if (ultFichaIngre != null){
            fichaPura = ultFichaIngre.substring(0,2);
            this.tablero = this.tablero.replaceAll(Pattern.quote(ultFichaIngre), fichaPura + this.controlador.getCharJugadorFicha());
            ultFichaIngre = null;
        }if (fichaEliminar != null) {
            fichaPura = this.fichaEliminar.substring(this.fichaEliminar.indexOf(fichaEliminar), this.fichaEliminar.indexOf(fichaEliminar) + 2);
            fichaPura = fichaPura + "( )";
            this.tablero = this.tablero.replaceAll(Pattern.quote(fichaEliminar), fichaPura); //ARREGLAR
            fichaEliminar = null;
        }
        mostrarTablero();
    }

    @Override
    public void ponerFicha() {
                /*1- muestro tablero.
          2- pido el dato de la posicion a ingresar
          3- llamo al modelo donde quiero ingresar la ficha
          4- informo si se pudo insertar de manera adecuado
          5- muestro tablero actualizado.
         */
        //VER SI FUNCIONA LA RECURSIVIDAD:
        //mostrarTablero();
         //corregir y ponerlo en el action del boton
        String ficha = textoInput.getText().toUpperCase().trim();
        boolean salida = false;
        int[] posicion = traductor(ficha);
        ultFichaIngre = ficha + "( )"; //Guardo la ultima ficha ingresada por si me da un evento de que se ingreso con exito, procedo a hacer el cambio
        this.controlador.ponerFicha(posicion[0], posicion[1], posicion[2]);


    }

    @Override
    public void mostrarMoverFicha1fase(){
        /*
        1- tomo la ficha ingresada a mover y verifico si es del jugador.
        2- si es del jugador le pido que me ingrese la posicion a mover.
        3- verifico si la posicion a mover es valida y esta desocupada.
         */
        String fichaAMover = textoInput.getText().toUpperCase().trim();
        int[] posicionFicha = traductor(fichaAMover);
        boolean valido = this.controlador.verificarFicha(posicionFicha[0], posicionFicha[1], posicionFicha[2]);
        textoInput.setText("");
        if (valido) {
            fichaEliminar = fichaAMover;
            fichaEliminar = tablero.substring(tablero.indexOf(fichaEliminar), tablero.indexOf(fichaEliminar) + 5);//La guardo ya que la debere eliminar
            limpiarConsola();
            mostrarTablero();
            ficha = posicionFicha;
            cambiarEstado(MOVERFICHA2DAFASE);
        }
    }

    @Override
    public void mostrarMoverFicha2fase() {
        String fichaAMover = textoInput.getText().toUpperCase().trim();
        int[] posicionFichaMover = traductor(fichaAMover);
        boolean valido = this.controlador.verificarPosicionVacia(posicionFichaMover[0], posicionFichaMover[1], posicionFichaMover[2]);
        textoInput.setText("");
        if (valido){
            ultFichaIngre = fichaAMover + "( )";
            this.controlador.moverFicha(ficha[0], ficha[1], ficha[2], posicionFichaMover[0], posicionFichaMover[1], posicionFichaMover[2]);
        }else {
            this.println("Por favor ingrese una posicion a mover valida: ");
        }
    }

    @Override
    public void mostrarPantallaEspera() {
        limpiarConsola();
        this.println("   Bienvenido " + this.controlador.getNombreJugador() + " al Juego del Molino");
        this.println("   Esperando la conexión de un nuevo jugador...");
    }
}
