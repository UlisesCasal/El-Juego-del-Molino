package Vistas.VistaConsolaSwing;

import Clases.Ficha;
import Controlador.Controlador;
import Vistas.Errores;
import Vistas.EstadosVista;
import Vistas.IVista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class VConsola extends JFrame implements IVista {
    private JPanel panel1;
    private JTextArea consola;
    private JTextField textoInput;
    private JButton botonEnter;
    private Controlador controlador;
    private EstadosVista estadoActual = EstadosVista.INGRESARFICHA;
    private String ultFichaIngre;
    private String fichaEliminar;
    private String tablero ="""
                                    
                    A1════════════A2════════════A3
                    ║ B1══════════B2══════════B3 ║
                    ║ ║ C1════════C2════════C3 ║ ║
                    ║ ║ ║                    ║ ║ ║
                    ║ ║ ║                    ║ ║ ║
                    ║ ║ ║                    ║ ║ ║
                   A8═B8═C8                A4═B4═C4
                    ║ ║ ║                    ║ ║ ║
                    ║ ║ ║                    ║ ║ ║
                    ║ ║ ║                    ║ ║ ║
                    ║ ║ C7════════C6════════C5 ║ ║
                    ║ B7══════════B6══════════B5 ║
                    A7════════════A6════════════A5""";

    public VConsola(){
        super("El Juego del Molino");
        setContentPane(this.panel1);
        botonEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (estadoActual){
                    case INGRESARFICHA: {
                        println("Ingrese la posición de la ficha que quiere ingresar: ");
                        ponerFicha();
                        break;
                    }
                    case SACARFICHA:{
                        mostrarSacarFicha();
                        break;
                    }
                }
            }
        });
    }


    public void println(String texto) {
        consola.append(texto + "\n");
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
        switch (this.estadoActual){
            case SACARFICHA -> println("Ingrese la posicion de la ficha a eliminar: ");
        }
    }

    @Override
    public void mostrarErrores(Errores errores) {
        if (errores == Errores.NOSEPUDOSACARFICHA){
            mostrarTablero();
            println("LA FICHA INGRESADA NO SE HA PODIDO ELIMINAR..." + "\n" +
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
        //Hace un reemplazo y procede a mostrar el tablero actualizado
        if (ultFichaIngre != null){
            this.tablero = this.tablero.replaceAll(ultFichaIngre, ultFichaIngre + this.controlador.getCharJugadorFicha());
        } else if (fichaEliminar != null) {
            String fichaPura = this.fichaEliminar.substring(this.fichaEliminar.indexOf(fichaEliminar), this.fichaEliminar.indexOf(fichaEliminar) + 2);
            this.tablero = this.tablero.replaceAll(Pattern.quote(fichaEliminar), fichaPura);; //ARREGLAR

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
        String ficha = textoInput.getText().toUpperCase();
        boolean salida = false;
        int[] posicion = traductor(ficha);
        ultFichaIngre = ficha; //Guardo la ultima ficha ingresada por si me da un evento de que se ingreso con exito, procedo a hacer el cambio
        this.controlador.ponerFicha(posicion[0], posicion[1], posicion[2]);

    }
}
