package Vistas;

import Controlador.Controlador;

import java.util.Scanner;

public class VistaConsola implements IVista{
    private Scanner sc;

    @Override
    public void cambiarEstado(EstadosVista estado) {

    }

    @Override
    public void mostrarErrores(Errores errores) {

    }

    @Override
    public void mostrarMoverFicha1fase() {

    }

    @Override
    public void mostrarMoverFicha2fase() {

    }

    @Override
    public EstadosVista getEstadoVista() {
        return null;
    }

    @Override
    public void mostrarPantallaEspera() {

    }


    private Controlador controlador;

    private String tablero = """
                                    
                    A1════════════A2════════════A3
                    ║ B1══════════B2══════════B3 ║
                    ║ ║ C1════════C2════════C3 ║ ║
                    ║ ║ ║                    ║ ║ ║
                    ║ ║ ║                    ║ ║ ║
                    ║ ║ ║                    ║ ║ ║
                   A8═B8═C8                C4═B4═A4
                    ║ ║ ║                    ║ ║ ║
                    ║ ║ ║                    ║ ║ ║
                    ║ ║ ║                    ║ ║ ║
                    ║ ║ C7════════C6════════C5 ║ ║
                    ║ B7══════════B6══════════B5 ║
                    A7════════════A6════════════A5""";

    public VistaConsola(){
        this.sc = new Scanner(System.in);
    }

    public void mostrarTableroVacio(){
        String tablero = """
                                    
                    A1════════════A2════════════A3
                    ║ B1══════════B2══════════B3 ║
                    ║ ║ C1════════C2════════C3 ║ ║
                    ║ ║ ║                    ║ ║ ║
                    ║ ║ ║                    ║ ║ ║
                    ║ ║ ║                    ║ ║ ║
                   A8═B8═C8                C4═B4═A4
                    ║ ║ ║                    ║ ║ ║
                    ║ ║ ║                    ║ ║ ║
                    ║ ║ ║                    ║ ║ ║
                    ║ ║ C7════════C6════════C5 ║ ║
                    ║ B7══════════B6══════════B5 ║
                    A7════════════A6════════════A5""";
        System.out.println(tablero);

    }
    @Override
    public void ponerFicha(){
        /*1- muestro tablero.
          2- pido el dato de la posicion a ingresar
          3- llamo al modelo donde quiero ingresar la ficha
          4- informo si se pudo insertar de manera adecuado
          5- muestro tablero actualizado.
         */
        mostrarTablero();
        System.out.println("Ingrese la posición de la ficha que quiere ingresar: ");
        String ficha = sc.nextLine().toUpperCase();
        boolean salida = false;
        switch (ficha) {
            case "A1" -> {
                salida = this.controlador.ponerFicha(0, 0, 0);
                break;
            }
            case "A2" -> {
                salida = this.controlador.ponerFicha(0, 0, 1);
                break;
            }
            case "A3" -> {
                salida = this.controlador.ponerFicha(0, 0, 2);
                break;
            }
            case "A4" -> {
                salida = this.controlador.ponerFicha(0, 1, 2);
                break;
            }
            case "A5" -> {
                salida = this.controlador.ponerFicha(0, 2, 2);
                break;
            }
            case "A6" -> {
                salida = this.controlador.ponerFicha(0, 2, 1);
                break;
            }
            case "A7" -> {
                salida = this.controlador.ponerFicha(0, 2, 0);
                break;
            }
            case "A8" -> {
                salida = this.controlador.ponerFicha(0, 1, 0);
                break;
            }
            case "B1" -> {
                salida = this.controlador.ponerFicha(1, 0, 0);
                break;
            }
            case "B2" -> {
                salida = this.controlador.ponerFicha(1, 0, 1);
                break;
            }
            case "B3" -> {
                salida = this.controlador.ponerFicha(1, 0, 2);
                break;
            }
            case "B4" -> {
                salida = this.controlador.ponerFicha(1, 1, 2);
                break;
            }
            case "B5" -> {
                salida = this.controlador.ponerFicha(1, 2, 2);
                break;
            }
            case "B6" -> {
                salida = this.controlador.ponerFicha(1, 2, 1);
                break;
            }
            case "B7" -> {
                salida = this.controlador.ponerFicha(1, 2, 0);
                break;
            }
            case "B8" -> {
                salida = this.controlador.ponerFicha(1, 1, 0);
                break;
            }
            case "C1" -> {
                salida = this.controlador.ponerFicha(2, 0, 0);
                break;
            }
            case "C2" -> {
                salida = this.controlador.ponerFicha(2, 0, 1);
                break;
            }
            case "C3" -> {
                salida = this.controlador.ponerFicha(2, 0, 2);
                break;
            }
            case "C4" -> {
                salida = this.controlador.ponerFicha(2, 1, 2);
                break;
            }
            case "C5" -> {
                salida = this.controlador.ponerFicha(2, 2, 2);
                break;
            }
            case "C6" -> {
                salida = this.controlador.ponerFicha(2, 2, 1);
                break;
            }
            case "C7" -> {
                salida = this.controlador.ponerFicha(2, 2, 0);
                break;
            }
            case "C8" -> {
                salida = this.controlador.ponerFicha(2, 1, 0);
                break;
            }
            default -> System.out.println("La posicion ingresada es inválida...");
        }
        if (!salida){
            //Si la insercion no fue exitosa:
            System.out.println("La ficha no se pudo ingresar en la posicion deseada");
        }else{
            /*Si la insercion fue exitosa, entonces reemplazo la posicion y le agrego el char del jugador que
            agrego dicha ficha:
            */
            this.tablero.replaceAll(ficha, ficha + this.controlador.getCharJugadorFicha());//Mostrar tablero actualizado
            this.mostrarPonerFicha();
        }

    }

    private void mostrarPonerFicha() {
    }

    @Override
    public int[] traductor(String posicionSimbolica) {
        return new int[0];
    }

    @Override
    public void actualizarTablero() {

    }

    @Override
    public void mostrarPuntajesFinales() {
        String[] puntajes = this.controlador.getPuntajesFinales();
        String salida = """
                   ███      ██                          ███                                                  ██       ██        ███
                  ██ ██                                  ██                                                  ██                  ██
                   █       ███     █████                 ██    ████             ██████    ████    ██████    █████    ███         ██    ████
                 ████       ██     ██  ██             █████   ██  ██             ██  ██      ██    ██  ██    ██       ██      █████       ██
                  ██        ██     ██  ██            ██  ██   ██████             ██  ██   █████    ██        ██       ██     ██  ██    █████
                  ██        ██     ██  ██            ██  ██   ██                 █████   ██  ██    ██        ██ ██    ██     ██  ██   ██  ██
                 ████      ████    ██  ██             ███████   █████             ██       █████   █████        ███    ████     ██████   █████
                                                                                ████
                                
                """;

    }

    @Override
    public void mostrarSacarFicha() {

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
        System.out.println(this.tablero);
    }

}
