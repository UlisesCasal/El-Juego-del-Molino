package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private Ficha[] fichasNoPuestas;
    private Ficha[] fichasPuestas;
    private int fichasTotales;
    private int puntaje;
    private int numero;
    private int numeroPuestas = 0;//Sacar
    private List<Molino> molinos = new ArrayList<Molino>();

    public Jugador(String nombre, int numero){
        this.nombre = nombre;
        this.fichasNoPuestas = new Ficha[2]; // CAMBIAR A 9 FICHAS
        inicializarFichas();
        this.puntaje = 0;
        this.numero = numero;
        this.fichasTotales = 2;
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
        this.numeroPuestas ++;
        this.fichasTotales --;
    }

    public int getPuntaje() {
        return this.puntaje;
    }

    public Ficha[] getFichas(){
        return this.fichasPuestas;
    }

    public Ficha getFichaNoPuesta(){
        Ficha ficha = null;
        if (fichasTotales >= 1) {
            //Saco la ficha de la ultima posicion siempre:
            ficha = this.fichasNoPuestas[this.fichasNoPuestas.length-1];
            Ficha[] arrayFichasAux = new Ficha[this.fichasNoPuestas.length - 1];
            for (int i = 1; i < this.fichasNoPuestas.length - 1; i++) {
                arrayFichasAux[i] = this.fichasNoPuestas[i];
            }
            this.fichasNoPuestas = arrayFichasAux;
            this.fichasTotales --;
        }
        return ficha;
    }

    public Ficha getFicha(int t, int f, int c){
        int[] posicionFichaActual = new int[3];
        Ficha salida = null;
        boolean terminar = false;
        int i = 0;
        int contadorFichasPuestas = 0;
        while((!terminar) && (i < fichasPuestas.length) && (contadorFichasPuestas < this.numeroPuestas)){
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
            this.fichasPuestas = new Ficha[9];
        }
        ficha.setPosicion(posicion);
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
        return 2 - numeroPuestas; //CORREGIR
    }
}
