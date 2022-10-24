package Clases;

public class Tablero {
    //Utilizare de tablero un tensor, que representara los 3 cuadrados, el 0-> mas externo, el 1-> el del medio y el 3-> interno.
    private int[][][] tablero;

    public Tablero(){
        this.tablero = new int[3][3][3];
    }

    private void inicializarTablero(){
        //RECORRER VECTOR DE MATRICES Y CADA POSICION PARA INICIALIZARLA EN 0.
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++){
                    this.tablero[j][k][i] = 0;
                }
            }

        }
    }

    private boolean agregarFicha(int fila, int columna, int cuadrado, Jugador jugador){

    }
}
