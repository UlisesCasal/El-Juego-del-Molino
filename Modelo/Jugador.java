package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Jugador implements Serializable {
    private String nombre;
    private Ficha[] fichasNoPuestas;
    private Ficha[] fichasPuestas = null;
    private int fichasTotales;
    private int puntaje;
    private int numero;
    private int numeroPuestas = 0;
    private List<Molino> molinos = new ArrayList<Molino>();

    public Jugador(String nombre, int numero){
        this.nombre = nombre;
        this.fichasNoPuestas = new Ficha[10]; // CAMBIAR A 9 , DEBE SER SIEMPRE +1
        inicializarFichas();
        this.puntaje = 0;
        this.numero = numero;
        this.fichasTotales = 9;// cambiar
    }

    private void inicializarFichas(){
        //REVISAR
        //Recorre ficha a ficha y la inicializa:
        int i = 0;
        for (Ficha f: fichasNoPuestas) {
            fichasNoPuestas[i] = new Ficha(this);
            i++;
        }
    }

    public void incPuntaje(){
        puntaje ++;
    }

    public int getFichasTotales() {
        return fichasTotales;
    }

    public int getNumeroPuestas(){
        return this.numeroPuestas;
    }

    public void sacarFicha(Ficha ficha){
        /* Metodo que permite sacar una ficha, creo un array auxiliar
        donde le cargo todas las fichas menos la que hay que sacar.
         */
        Ficha[] fichasPuestasAux = new Ficha[this.fichasPuestas.length - 1];
        int i = 0;
        Ficha f = null;
        while(i < this.fichasPuestas.length-1) {
            f = this.fichasPuestas[i];
            if (f == null) {
               fichasPuestasAux[i] = f;
            }else if (!(f.getPosicion() == ficha.getPosicion())) {
                fichasPuestasAux[i] = f;
            }
            i ++;
        }
        this.fichasPuestas = fichasPuestasAux;//da error
        this.fichasTotales --;
    }

    public int getPuntaje() {
        return this.puntaje;
    }

    public List<Ficha> getFichas(){
        /*
        METODO QUE ROTORNA TODAS LAS FICHAS PUESTAS DEL JUGADOR:
         */
        List<Ficha> salida = new ArrayList<>();
        Ficha ficha;
        int i = 0;
        while (i < this.fichasPuestas.length){
            ficha = this.fichasPuestas[i];
            if (ficha != null){
                salida.add(ficha);
            }
            i++;
        }
        return salida;
    }

    public Ficha getFichaNoPuesta(){
        Ficha ficha = null;
        //VERIFICAR SI ESTO FUNCIONA EL MAYOR A CERO
        if ((fichasTotales >= 1) && (this.fichasNoPuestas.length-1 >= 0)) {
            //Saco la ficha de la ultima posicion siempre:
            ficha = this.fichasNoPuestas[this.fichasNoPuestas.length-1];
            Ficha[] arrayFichasAux = new Ficha[this.fichasNoPuestas.length - 1];
            for (int i = 1; i < this.fichasNoPuestas.length - 1; i++) {
                arrayFichasAux[i] = this.fichasNoPuestas[i];
            }
            this.fichasNoPuestas = arrayFichasAux;

        }
        return ficha;
    }

    public Ficha getFicha(int t, int f, int c){
        int[] posicionFichaActual;
        Ficha salida = null;
        boolean terminar = false;
        int i = 0;
        int contadorFichasPuestas = 0;
        while((!terminar) && (i < fichasPuestas.length - 1) && (contadorFichasPuestas < this.numeroPuestas)){
            if (fichasPuestas[i] != null) {
                contadorFichasPuestas ++;
                posicionFichaActual = fichasPuestas[i].getPosicion();
                if ((posicionFichaActual[0] == t) && (posicionFichaActual[1] == f) && (posicionFichaActual[2] == c)) {
                    salida = fichasPuestas[i];
                    terminar = true;
                }
            }
            i++;
        }
        return salida;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setPosicionFicha(int[] posicion, Ficha ficha) {
        //Metodo que le setea una posicion a las fichas, en la primera ejecucion crea el arreglo de fichas puestas

        if (this.fichasPuestas == null){
            this.fichasPuestas = new Ficha[20]; //Lo sobredimensiono
        }
        ficha.setPosicion(posicion);
        //En algunos casos cuando se elimina una ficha, y luego se ingresa una, el arreglo debe crecer:
        /*
        POSIBLE ERROR:
         */
        if ((this.fichasPuestas.length) <= this.numeroPuestas + 1){
            Ficha[] arregloAuxiliar = new Ficha[this.numeroPuestas + 3]; //POSIBLE CAUSANTE DE ERROR
            int i = 0;
            while (i < this.fichasPuestas.length){
                arregloAuxiliar[i] = this.fichasPuestas[i];
                i ++;
            }
            this.fichasPuestas = arregloAuxiliar;
        }
        this.fichasPuestas[numeroPuestas] = ficha;
        this.numeroPuestas++;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void agregarMolino(Molino molino){
        molinos.add(molino);
    }

    public boolean perteneceAMolino(Ficha ficha){
        int i = 0;
        boolean salida = false;
        while(i < molinos.size()){
            if (molinos.get(i).pertenece(ficha)){
                salida = true;
            }
            i++;
        }
        return salida;
    }

    public void eliminarMolino(Ficha ficha) {
        int i = 0;
        //VERIFICAR SI FUNCIONA CORRECTAMENTE:
        while (i < molinos.size()){
            if (molinos.get(i).pertenece(ficha)){
                molinos.remove(i);
            }
            i++;
        }
    }

    public int getNumeroFichasRestante() {
        //Metodo que me devuelve la cantidad de fichas que le quedan por poner al jugador:
        return 9 - (numeroPuestas); //Ver si cambiarlo por 9
    }

    public boolean verificarFichasEliminables(){
        /*
        Metodo que se fija si el jugador tiene fichas eliminables
         */
        boolean salida = false;
        int i = 0;
        Ficha ficha;

        while (i < fichasPuestas.length){
            ficha = fichasPuestas[i];

            if (ficha != null){
                // Si hay una ficha que no pertenezca al molino significa que hay fichas eliminables.
                if (!perteneceAMolino(ficha)){
                    salida = true;
                }
            }
            i++;
        }
        return salida;
    }
}
