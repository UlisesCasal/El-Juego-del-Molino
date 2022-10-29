package Clases;

public class Ficha {
    private int[] posicion = null; //se setea en null hasta que se le asigne una posicion
    private Jugador jugador;

    public Ficha(Jugador jugador){
        this.jugador = jugador;
    }

    public void setPosicion(int[] posicion) {
        //Creo el arreglo y seteo las posiciones, una vez se halla puesto en el tablero
        this.posicion = new int[3];
        this.posicion[0] = posicion[0];
        this.posicion[1] = posicion[1];
        this.posicion[2] = posicion[2];
    }

    //VERIFICAR QUE DEVUELVE CUANDO NO LE SETEO NADA
    public int[] getPosicion() {
        return posicion;
    }

    public Jugador getJugador() {
        return jugador;
    }
}
