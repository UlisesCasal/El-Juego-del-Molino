package Clases;

public class Jugador {
    private String nombre;
    private Ficha[] fichasNoPuestas;
    private Ficha[] fichasPuestas;
    private int fichasTotales;
    private int puntaje;
    private int numero;

    public Jugador(String nombre, int numero){
        this.nombre = nombre;
        this.fichasNoPuestas = new Ficha[20];
        inicializarFichas();
        this.puntaje = 0;
        this.numero = numero;
        this.fichasTotales = 20;
    }

    private void inicializarFichas(){
        //REVISAR
        //Recorre ficha a ficha y la inicializa:
        for (Ficha f: fichasNoPuestas) {
            f = new Ficha(this);
        }
    }

    public void incPuntaje(){
        puntaje ++;
    }

    public void sacarFicha(Ficha ficha){
        /* Metodo que permite sacar una ficha, creo un array auxiliar
        donde le cargo todas las fichas menos la que hay que sacar.
         */
        Ficha[] fichasPuestasAux = new Ficha[this.fichasPuestas.length - 1];
        int i = 0;
        for (Ficha f: fichasPuestas) {
            if (!(f.getPosicion() == ficha.getPosicion())){
                fichasPuestasAux[i] = f;
            }
            i ++;
        }
        Ficha[] fichasPuestas = new Ficha[fichasPuestasAux.length];
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
            //Saco la ficha de la posicion 0 siempre:
            ficha = this.fichasNoPuestas[0];
            Ficha[] arrayFichasAux = new Ficha[this.fichasNoPuestas.length - 1];
            for (int i = 1; i < this.fichasNoPuestas.length; i++) {
                arrayFichasAux[i] = this.fichasNoPuestas[i];
            }
            this.fichasTotales --;
        }
        return ficha;
    }

    public int getNumero() {
        return this.numero;
    }
}
