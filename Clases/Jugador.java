package Clases;

public class Jugador {
    private String nombre;
    private Ficha[] fichas;
    private int puntaje;

    public Jugador(String nombre){
        this.nombre = nombre;
        this.fichas = new Ficha[20];
        this.puntaje = 0;
    }

    public void incPuntaje(){
        puntaje ++;
    }

    //VER COMO IMPLEMENTAR ELIMINAR UNA FICHA:
    public void decFichas(Ficha ficha){}

    public int getPuntaje() {
        return this.puntaje;
    }

    public Ficha[] getFichas(){
        return this.fichas;
    }


}
