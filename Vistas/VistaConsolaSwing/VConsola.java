package Vistas.VistaConsolaSwing;

import Controlador.Controlador;
import Vistas.IVista;

import javax.swing.*;

public class VConsola extends JFrame implements IVista {
    private JPanel panel1;
    private JTextArea consola;
    private JTextField textoInput;
    private JButton botonEnter;
    private Controlador controlador;
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
        return salida;
    }

    @Override
    public void mostrarSacarFicha() {
        /* 1- se muestra el tablero.
           2- se pide el dato de la ficha a eliminar.
           3- informo si se elimino de manera adecuada.
           4- muestro tablero actualizado.
         */
        //TENER EN CUENTA EL JUGADOR QUE EJECUTA DICHA PETICION:
        mostrarTablero();
        println("Ingrese la posicion de la ficha a eliminar: ");
        String ficha = textoInput.getText().toUpperCase();
        int[] posicion = traductor(ficha);
        this.controlador.sacarFicha(posicion[0], posicion[1], posicion[2]);

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
        mostrarTablero();
        println("Ingrese la posición de la ficha que quiere ingresar: ");
        String ficha = textoInput.getText().toUpperCase();
        boolean salida = false;
        int[] posicion = traductor(ficha);
        salida = this.controlador.ponerFicha(posicion[0], posicion[1], posicion[2]);
        if (!salida){
            //Si la insercion no fue exitosa:
            println("La ficha no se pudo ingresar en la posicion deseada");
            ponerFicha();
        }else{
            /*Si la insercion fue exitosa, entonces reemplazo la posicion y le agrego el char del jugador que
            agrego dicha ficha:
            */
            this.tablero.replaceAll(ficha, ficha + this.controlador.getCharJugadorFicha());//Mostrar tablero actualizado
            this.mostrarTablero();
        }
    }
}
