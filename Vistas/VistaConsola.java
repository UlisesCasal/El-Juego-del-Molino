package Vistas;

import Controlador.Controlador;

import java.util.Scanner;

public class VistaConsola implements IVista{
    private Scanner sc;
    private Controlador controlador;

    public VistaConsola(){
        this.sc = new Scanner(System.in);
    }

    public void mostrarTablero(){
        String tablero = """
                
         A1 ->      0════════════0════════════0  <- A3
         B1 ->      ║ 0══════════0══════════0 ║  <- B3
         C1 ->      ║ ║ 0════════0════════0 ║ ║  <- C3
                    ║ ║ ║                 ║ ║ ║
                    ║ ║ ║                 ║ ║ ║
                    ║ ║ ║                 ║ ║ ║
         A8,B8,C8-> 0═0═0                 0═0═0 <-A4,B4,C4
                    ║ ║ ║                 ║ ║ ║
                    ║ ║ ║                 ║ ║ ║
                    ║ ║ ║                 ║ ║ ║
         C7 ->      ║ ║ 0════════0════════0 ║ ║  <- C5
         B7 ->      ║ 0══════════0══════════0 ║  <- B5
         A7 ->      0════════════0════════════0  <- A5""";
        tablero = tablero.replaceAll("<- A3", ""); //Prueba de eliminacion
        System.out.println(tablero);
    }

    public void ponerFicha(){
        /*1- muestro tablero.
          2- pido el dato de la posicion a ingresar
          3- llamo al modelo donde quiero ingresar la ficha
          4- informo si se pudo insertar de manera adecuado
          5- muestro tablero actualizado.
         */
        mostrarTablero();
        System.out.println("Ingrese la posicion de la ficha que quiere ingresar: ");
        String ficha = sc.nextLine();
        switch (ficha){
            case "A1":
                //LLamo a sacar ficha con la posicion de ficha
                break;
            case "A2":
                break;
            case "A3":
                break;
            case "A4":
                break;
            case "A5":
                break;
            case "A6":
                break;
            case "A7":
                break;
            case "A8":
                break;
            case "B1":
                break;
            case "B2":
                break;
            case "B3":
                break;
            case "B4":
                break;
            case "B5":
                break;
            case "B6":
                break;
            case "B7":
                break;
            case "B8":
                break;
            case "C1":
                break;
            case "C2":
                break;
            case "C3":
                break;
            case "C4":
                break;
            case "C5":
                break;
            case "C6":
                break;
            case "C7":
                break;
            case "C8":
                break;
        }


    }

    @Override
    public void mostrarPuntajesFinales() {

    }

    @Override
    public void mostrarSacarFicha() {

    }

    @Override
    public void mostrarPonerFicha() {

    }

    @Override
    public void iniciar() {

    }


    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
}
