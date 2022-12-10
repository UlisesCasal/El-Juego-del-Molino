package Vistas.VistaConsolaSwing;

import Controlador.Controlador;
import Modelo.Eventos;
import Vistas.Errores;
import Vistas.EstadosVista;
import Vistas.IVista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.regex.Pattern;

import static Vistas.EstadosVista.*;
import static Vistas.VistaConsolaSwing.Traductor.traductorInverso;

public class VConsola extends JFrame implements IVista {
    private JPanel panel1;
    private JTextArea consola;
    private JTextField textoInput;
    private JButton botonEnter;
    private Controlador controlador;
    private EstadosVista estadoActual = EstadosVista.INGRESARFICHA;
    int[] ficha = new int[3];
    private final String tableroVacio ="""
                                    
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
    private String tableroDinamico = tableroVacio;

    public VConsola(){
        super("El Juego del Molino");
        setContentPane(this.panel1);
        botonEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch (estadoActual){
                    case INGRESONOMBRE:{
                        try {
                            registrarJugador();
                        } catch (RemoteException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                    case ESPERANDOCONEXION:{
                        mostrarPantallaEspera();
                        break;
                    }
                    case INGRESARFICHA: {
                        ponerFicha();
                        break;
                    }
                    case SACARFICHA:{
                        mostrarSacarFicha();
                        break;
                    }
                    case MOVERFICHA:{
                        mostrarMoverFicha1fase();
                        break;
                    }
                    case MOVERFICHA2DAFASE:{
                        mostrarMoverFicha2fase();
                    }
                    case MENU:{
                        mostrarMenuEleccion();
                    }
                }
            }
        });
    }

    private void mostrarMenuEleccion() {
        String texto = this.textoInput.getText().trim().toUpperCase();
        if (texto.equals("S")){
            this.dispose();
            this.controlador.cambiarAVistaGrafica();
        }else {
            textoInput.setText("");
            limpiarConsola();
            mostrarTablero();
            cambiarEstado(INGRESARFICHA);
        }
    }

    public void mostrarConsola(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    setSize(500,500);
                    setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void registrarJugador() throws RemoteException {
        String nombre = textoInput.getText().trim();
        if ((nombre != null) && (nombre != "")){
            this.controlador.setJugador(nombre);
            textoInput.setText("");
            //cambiarEstado(MENU);
        }else{
            this.println("Por favor ingrese un nombre válido...");
        }
    }

    private void println(String texto) {
        this.consola.append(texto + "\n");
    }

    private void limpiarConsola(){
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
        this.mostrarConsola();
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public void mostrarTablero() {
        limpiarConsola(); // Ver si dejar
        println(tableroDinamico);
    }

    @Override
    public void mostrarPuntajesFinales() {
        String[] puntaje = this.controlador.getPuntajesFinales();
        String[] nombreJugadores = this.controlador.getNombreJugadores();
        println("===FIN DE PARTIDA===");
        println("Los puntajes son: ");
        println("- Jugador1 (" + nombreJugadores[0] + "): " + puntaje[0]);
        println("- Jugador2 (" + nombreJugadores[1] + "): " + puntaje[1]);


    }

    @Override
    public int[] traductor(String posicionSimbolica) {
        //Metodo que traduce una posicion simbolica una efectiva.
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

    // SACAR:


    @Override
    public void cambiarEstado(EstadosVista estado) {
        this.estadoActual = estado;
        switch (this.estadoActual) {
            case MENU -> println("Ingrese (S/N) si desea cambiar a la vista grafica: ");
            case BLOQUEADA -> println("Espere su turno...");
            case ESPERANDOCONEXION -> mostrarPantallaEspera();
            case INGRESARFICHA -> this.println("Su ficha es: " + this.controlador.getCharJugadorFicha() + "\n" + "Ingrese la ficha que quiere agregar: ");
            case SACARFICHA -> this.println("Su ficha es: " + this.controlador.getCharJugadorFicha() + "\n" + "Ingrese la posicion de la ficha a eliminar: ");
            case MOVERFICHA -> this.println("Su ficha es: " + this.controlador.getCharJugadorFicha() + "\n" + "Ingrese la ficha a mover: ");
            case MOVERFICHA2DAFASE -> this.println("Su ficha es: " + this.controlador.getCharJugadorFicha() + "\n" + "Ingrese la posicion a mover la ficha: ");
        }
    }

    @Override
    public void mostrarErrores(Errores errores) {
        if (errores == Errores.NOSEPUDOSACARFICHA){
            limpiarConsola();
            mostrarTablero();
            this.println("LA FICHA INGRESADA NO SE HA PODIDO ELIMINAR..." + "\n" +
                    "Por favor ingrese una posicion válida");
        }if (errores == Errores.NOSEPUDOAGREGARFICHA){ // VER SI ANDA
            println("La posicion ingresada es inválida, vuelva a intentarlo...");
            cambiarEstado(INGRESARFICHA); // VER SI ANDA
        }if (errores == Errores.NOSEPUDOMOVERFICHA){
            println("No se pudo mover la ficha, vuelva a intentarlo...");
            cambiarEstado(MOVERFICHA); // VER SI ANDA
        }
    }

    @Override
    public void mostrarSacarFicha() {
        /* 1- se muestra el tablero.
           2- se pide el dato de la ficha a eliminar.
           3- informo si se elimino de manera adecuada.
           4- muestro tablero actualizado.
         */
        String ficha = textoInput.getText().toUpperCase().trim();
        int[] posicion = traductor(ficha);
        if (posicion[0] != -1) {
            //ultFichaIngre = null; //Asigno nulo asi cuando actualizo no me agrega otro char
            //fichaEliminar = tablero.substring(tablero.indexOf(ficha), tablero.indexOf(ficha) + 5);
            this.controlador.sacarFicha(posicion[0], posicion[1], posicion[2]);
            //QUEDA SACAR EL CHAR DEL JUGADOR
        }else{
            cambiarEstado(SACARFICHA);

        }
    }


    @Override
    public void actualizarTablero(int[] ficha, String jugador, Eventos eventoMostrar) {
        String fichaPura;
        switch (eventoMostrar){
            case FICHAAGREGADA -> {
                String posiSimbolica = traductorInverso(ficha);
                fichaPura = posiSimbolica + "( )";
                tableroDinamico = tableroDinamico.replaceAll(Pattern.quote(fichaPura), posiSimbolica + jugador);
            }
            case FICHASACADA -> {
                String fichaAEliminar = traductorInverso(ficha) + jugador;
                fichaPura = traductorInverso(ficha) + "( )";
                tableroDinamico = tableroDinamico.replaceAll(Pattern.quote(fichaAEliminar), fichaPura);
            }
        }
        limpiarConsola();
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
        String ficha = textoInput.getText().toUpperCase().trim();
        int[] posicion = traductor(ficha);
        if (posicion[0] != -1) {
            //ultFichaIngre = ficha + "( )"; //Guardo la ultima ficha ingresada por si me da un evento de que se ingreso con exito, procedo a hacer el cambio
            this.controlador.ponerFicha(posicion[0], posicion[1], posicion[2]);
        }else {cambiarEstado(INGRESARFICHA);}

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
        if (posicionFicha[0] != -1) {
            boolean valido = this.controlador.verificarFicha(posicionFicha[0], posicionFicha[1], posicionFicha[2]);
            textoInput.setText("");
            if (valido) {
                //fichaEliminar = fichaAMover;
                //fichaEliminar = tablero.substring(tablero.indexOf(fichaEliminar), tablero.indexOf(fichaEliminar) + 5);//La guardo ya que la debere eliminar
                limpiarConsola();
                mostrarTablero();
                ficha = posicionFicha;
                cambiarEstado(MOVERFICHA2DAFASE);
            }else{
                mostrarErrores(Errores.NOSEPUDOMOVERFICHA);
            }
        }else{
            cambiarEstado(MOVERFICHA);
        }
    }

    @Override
    public void mostrarMoverFicha2fase() {
        String fichaAMover = textoInput.getText().toUpperCase().trim();
        int[] posicionFichaMover = traductor(fichaAMover);
        if (posicionFichaMover[0] != -1) {
            boolean valido = this.controlador.verificarPosicionVacia(posicionFichaMover[0], posicionFichaMover[1], posicionFichaMover[2]);
            textoInput.setText("");
            if (valido) {
                //ultFichaIngre = fichaAMover + "( )";
                this.controlador.moverFicha(ficha[0], ficha[1], ficha[2], posicionFichaMover[0], posicionFichaMover[1], posicionFichaMover[2]);
            } else {
                cambiarEstado(MOVERFICHA);
                //this.println("Por favor ingrese una posicion a mover valida: ");
            }
        }else {
            cambiarEstado(MOVERFICHA);
        }
    }

    @Override
    public void mostrarPantallaEspera() {
        limpiarConsola();
        this.println("   Bienvenido " + this.controlador.getNombreJugador() + " al Juego del Molino");
        this.println("   Esperando la conexión de un nuevo jugador...");
    }

    @Override
    public void puntajeHistorico() {
        /*
        METODO QUE MUESTRA LOS PUNTAJES HISTORICOS.
        - VERIFICAR SI FUNCIONA.
         */
        println("===PUNTAJES HISTORICOS===");
        String[] puntajes = this.controlador.desSerializar();
        for (int i = 0; i < puntajes.length; i++) {
            println(puntajes[i]);
        }
    }

}
