package Clases;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    //Utilizare de tablero un tensor, que representara los 3 cuadrados, el 0-> mas externo, el 1-> el del medio y el 3-> interno.
    private Ficha[][][] tablero;

    public Tablero(){
        this.tablero = new Ficha[3][3][3];
        inicializarTablero();
    }

    private void inicializarTablero(){
        //RECORRER VECTOR DE MATRICES Y CADA POSICION PARA INICIALIZARLA EN 0.
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++){
                    this.tablero[i][j][k] = null;
                }
            }
        }
    }

    public boolean moverFichas(Ficha ficha, int tmover, int fmover, int cmover, Jugador jugador){
        //metodo que se encarga de mover una ficha
        //primero verifica si es un movimiento valida, y luego lo realiza
        List listaMovimientos = moviPosibles(ficha);
        boolean encontro = false;
        int i = 0;
        int[] arregloLista = new int[3];

        while ((!encontro) || (i <= listaMovimientos.size())){
            arregloLista = (int[]) listaMovimientos.get(i);

            if ((arregloLista[0] == tmover) && (arregloLista[1] == fmover) && (arregloLista[2] == cmover)){
                encontro = true;
                //Asigno a la posicion anterior nulo y luego muevo la ficha a la nueva posicion:
                this.tablero[ficha.getPosicion()[0]][ficha.getPosicion()[1]][ficha.getPosicion()[2]] = null;
                this.tablero[tmover][fmover][cmover] = ficha;
                ficha.setPosicion(arregloLista);
            }
            i ++;

        }
        //PROBAR QUE RETORNA BIEN
        return encontro;
    }

    private int[] crearArrayPosiciones(int t, int f, int c){
        int[] posiciones = new int[3];
        posiciones[0] = t;
        posiciones[1] = f;
        posiciones[2] = c;
        return posiciones;
    }

    public List moviPosibles(Ficha ficha){
        int t = ficha.getPosicion()[0];
        int f = ficha.getPosicion()[1];
        int c = ficha.getPosicion()[2];
        //metodo que al pasarle una posicion de una ficha devuelve todos sus movimientos posibles:
        List lista = new ArrayList<int[]>();
        int[] posicion = new int[3];


        //Verifica todos los movimientos posibles:
        //Movimientos posibles cuando estamos en el cuadrado mas externo:
        if (t == 0){
           if ((c == 0) && (f ==2)){
               /*1. si c == 0 y f == 2:
               si c + 1 = 0 -> movi.add()
               si f - 1 = 0 -> movi.add()*/
               if (tablero[t][f][c+1] == null) {lista.add(crearArrayPosiciones(t, f, c + 1));}
               if (tablero[t][f-1][c] == null){lista.add(crearArrayPosiciones(t,f-1,c));}
           }
           if ((c == 1) && (f == 2)){
               /*2. si c == 1 y f == 2:
               si c + 1 = 0 -> movi.add()
               si c - 1 = 0 -> movi.add()
               si t + 1 = 0 -> movi.add()*/
               if (tablero[t][f][c+1] == null){lista.add(crearArrayPosiciones(t,f,c+1));}
               if (tablero[t][f][c-1] == null) {lista.add(crearArrayPosiciones(t,f,c-1));}
               if ((tablero[t+1][f][c]) == null){lista.add(crearArrayPosiciones(t+1,f,c));}
           }
           if ((c == 2) && (f == 2)){
                /*
                  3. si c == 2 y f == 2:
                        si f - 1 = 0 -> movi.add()
                        si c - 1 = 0 -> movi.add()
                 */
               if (tablero[t][f-1][c] == null ){lista.add(crearArrayPosiciones(t,f-1,c));}
               if (tablero[t][f][c-1] == null){lista.add(crearArrayPosiciones(t,f,c-1));}
           }
           if ((c == 0) && (f == 1)){
               /*4. si c == 0 y f == 1:
               si f + 1 = 0 -> movi.add()
               si f - 1 = 0 -> movi.add()
               si t + 1 = 0 -> movi.add()*/
               if (tablero[t][f+1][c] == null){lista.add(crearArrayPosiciones(t,f+1,c));}
               if (tablero[t][f-1][c] == null){lista.add(crearArrayPosiciones(t,f-1,c));}
               if (tablero[t+1][f][c] == null){lista.add(crearArrayPosiciones(t+1,f,c));}
           }
           if ((c == 0) && (f == 0)){
             /*  5. si c == 0 y f == 0:
                 si c + 1 = 0 -> movi.add()
                 si f + 1 = 0 -> movi.add()*/
               if (tablero[t][f][c+1] == null){lista.add(crearArrayPosiciones(t,f,c+1));}
               if (tablero[t][f+1][c] == null){lista.add(crearArrayPosiciones(t,f+1,c));}
           }
           if ((c == 1) && (f == 0)) {

               /*6. si c == 1 y f == 0:
                si c + 1 = 0 -> movi.add()
                si c - 1 = 0 -> movi.add()
                si t + 1 = 0 -> movi.add()*/
               if (tablero[t][f][c + 1] == null) {lista.add(crearArrayPosiciones(t, f, c + 1));}
               if (tablero[t][f][c - 1] == null) {lista.add(crearArrayPosiciones(t, f, c-1));}
               if (tablero[t + 1][f][c] == null) {lista.add(crearArrayPosiciones(t + 1, f, c));}
           }
           if ((c == 2) && (f == 0)){
               /*7. si c == 2 y f == 0:
               si c - 1 = 0 -> movi.add()
               si f + 1 = 0 -> movi.add()*/
               if (tablero[t][f][c - 1] == null) {lista.add(crearArrayPosiciones(t, f, c - 1));}
               if (tablero[t][f + 1][c] == null) {lista.add(crearArrayPosiciones(t, f + 1, c));}
           }
           if ((c == 2) && (f == 1)){
             /*  8. si c == 2 y f == 1:
                si f + 1 = 0 -> movi.add()
                si f - 1 = 0 -> movi.add()
                si t + 1 = 0 -> movi.add()*/
               if (tablero[t][f + 1][c] == null) {lista.add(crearArrayPosiciones(t, f + 1, c));}
               if (tablero[t][f - 1][c] == null) {lista.add(crearArrayPosiciones(t, f - 1, c));}
               if (tablero[t + 1][f][c] == null) {lista.add(crearArrayPosiciones(t + 1, f, c));}
           }

        } else if (t == 1) {
            if ((c == 0) && (f == 2)){
                /*1. si c == 0 y f == 2:
                si f - 1 = 0 -> movi.add()
                si c + 1 = 0 -> movi.add()*/
                if (tablero[t][f - 1][c] == null) {lista.add(crearArrayPosiciones(t, f - 1, c));}
                if (tablero[t][f][c + 1] == null) {lista.add(crearArrayPosiciones(t, f, c + 1));}
            }
            if ((c == 1) && (f == 2)){
                /*  2. si c == 1 y f == 2:
                    si c - 1 = 0 -> movi.add()
                    si c + 1 = 0 -> movi.add()
                    si t - 1 = 0 -> movi.add()
                    si t + 1 = 0 -> movi.add()*/
                if (tablero[t][f][c - 1] == null) {lista.add(crearArrayPosiciones(t, f, c - 1));}
                if (tablero[t][f][c + 1] == null) {lista.add(crearArrayPosiciones(t, f, c + 1));}
                if (tablero[t - 1][f][c] == null) {lista.add(crearArrayPosiciones(t - 1, f, c));}
                if (tablero[t + 1][f][c] == null) {lista.add(crearArrayPosiciones(t + 1, f, c));}
            }
            if ((c == 2) && (f == 2)){
                /*  3. si c == 2 y f == 2:
                  si f - 1 = 0 -> movi.add()
                  si c - 1 = 0 -> movi.add()*/
                if (tablero[t][f - 1][c] == null) {lista.add(crearArrayPosiciones(t, f - 1, c));}
                if (tablero[t][f][c - 1] == null) {lista.add(crearArrayPosiciones(t, f, c - 1));}
            }
            if ((c == 0) && (f == 1)){
            /*  4. si c == 0 y f == 1:
                si t - 1 = 0 -> movi.add()
                si t + 1 = 0 -> movi.add()
                si f - 1 = 0 -> movi.add()
                si f + 1 = 0 -> movi.add()*/
                if (tablero[t - 1][f][c] == null) {lista.add(crearArrayPosiciones(t - 1, f, c));}
                if (tablero[t + 1][f][c] == null) {lista.add(crearArrayPosiciones(t + 1, f, c));}
                if (tablero[t][f - 1][c] == null) {lista.add(crearArrayPosiciones(t, f - 1, c));}
                if (tablero[t][f + 1][c] == null) {lista.add(crearArrayPosiciones(t, f + 1, c));}
            }
            if ((c == 0) && (f == 0)){
              /*  5. si c == 0 y f == 0:
                si f + 1 = 0 -> movi.add()
                si c + 1 = 0 -> movi.add()*/
                if (tablero[t][f + 1][c] == null) {lista.add(crearArrayPosiciones(t, f + 1, c));}
                if (tablero[t][f][c + 1] == null) {lista.add(crearArrayPosiciones(t, f, c + 1));}
            }
            if ((c == 1) && (f == 0)){
               /*6. si c == 1 y f == 0:
                 si c - 1 = 0 -> movi.add()
                 si c + 1 = 0 -> movi.add()
                 si t + 1 = 0 -> movi.add()
                 si t - 1 = 0 -> movi.add()*/
                if (tablero[t][f][c - 1] == null) {lista.add(crearArrayPosiciones(t, f, c - 1));}
                if (tablero[t][f][c + 1] == null) {lista.add(crearArrayPosiciones(t, f, c + 1));}
                if (tablero[t + 1][f][c] == null) {lista.add(crearArrayPosiciones(t + 1, f, c));}
                if (tablero[t - 1][f][c] == null) {lista.add(crearArrayPosiciones(t - 1, f, c));}
            }
            if ((c == 2) && (f == 0)){
                /*7. si  c == 2 y f == 0:
                  si c - 1 = 0 -> movi.add()
                  si f + 1 = 0 -> movi.add()*/
                if (tablero[t][f][c - 1] == null) {lista.add(crearArrayPosiciones(t, f, c - 1));}
                if (tablero[t][f + 1][c] == null) {lista.add(crearArrayPosiciones(t, f + 1, c));}
            }
            if ((c == 2) && (f == 1)){
             /*  8. si c == 2 y f == 1:
                 si  f - 1 = 0 -> movi.add()
                 si f + 1 = 0 -> movi.add()
                 si t - 1 = 0 -> movi.add()
                 si t + 1 = 0 -> movi.add()*/
                if (tablero[t][f - 1][c] == null) {lista.add(crearArrayPosiciones(t, f - 1, c));}
                if (tablero[t][f + 1][c] == null) {lista.add(crearArrayPosiciones(t, f + 1, c));}
                if (tablero[t - 1][f][c] == null) {lista.add(crearArrayPosiciones(t - 1, f, c));}
                if (tablero[t + 1][f][c] == null) {lista.add(crearArrayPosiciones(t + 1, f, c));}
            }

        }else if (t == 2){

            if ((c == 0) && (f == 2)){
              /*  1. si c == 0 y f == 2:
                  si c + 1 = 0 -> movi.add()
                  si f - 1 = 0 -> movi.add()*/
                if (tablero[t][f][c + 1] == null) {lista.add(crearArrayPosiciones(t, f, c + 1));}
                if (tablero[t][f - 1][c] == null) {lista.add(crearArrayPosiciones(t, f - 1, c));}
            }
            if ((c == 1) && (f == 2)){
                /*2. si c == 1 y f == 2:
                si c + 1 = 0 -> movi.add()
                si c - 1 = 0 -> movi.add()
                si t - 1 = 0 -> movi.add()*/
                if (tablero[t][f][c + 1] == null) {lista.add(crearArrayPosiciones(t, f, c + 1));}
                if (tablero[t][f][c - 1] == null) {lista.add(crearArrayPosiciones(t, f, c - 1));}
                if (tablero[t - 1][f][c] == null) {lista.add(crearArrayPosiciones(t - 1, f, c));}
            }
            if ((c == 2) && (f == 2)){
                /*3. si c == 2 y f == 2:
                si f - 1 = 0 -> movi.add()
                si c - 1 = 0 -> movi.add()*/
                if (tablero[t][f - 1][c] == null) {lista.add(crearArrayPosiciones(t, f - 1, c));}
                if (tablero[t][f][c - 1] == null) {lista.add(crearArrayPosiciones(t, f, c - 1));}
            }
            if ((c == 0) && (f == 1)){
                /*4. si  c == 0 y f == 1:
                si f + 1 = 0 -> movi.add()
                si f - 1 = 0 -> movi.add()
                si t - 1 = 0 -> movi.add()*/
                if (tablero[t][f + 1][c] == null) {lista.add(crearArrayPosiciones(t, f + 1, c));}
                if (tablero[t][f - 1][c] == null) {lista.add(crearArrayPosiciones(t, f - 1, c));}
                if (tablero[t - 1][f][c] == null) {lista.add(crearArrayPosiciones(t - 1, f, c));}
            }
            if ((c == 0) && (f == 0)){
                /*5. si c == 0 y f == 0:
                si c + 1 = 0 -> movi.add()
                si f + 1 = 0 -> movi.add()*/
                if (tablero[t][f][c + 1] == null) {lista.add(crearArrayPosiciones(t, f, c + 1));}
                if (tablero[t][f + 1][c] == null) {lista.add(crearArrayPosiciones(t, f + 1, c));}
            }
            if ((c == 1) && (f == 0)){
                /*6. si c == 1 y f == 0:
                si c - 1 = 0 -> movi.add()
                si c + 1 = 0 -> movi.add()
                si t - 1 = 0 -> movi.add()*/
                if (tablero[t][f][c - 1] == null) {lista.add(crearArrayPosiciones(t, f, c - 1));}
                if (tablero[t][f][c + 1] == null) {lista.add(crearArrayPosiciones(t, f, c + 1));}
                if (tablero[t - 1][f][c] == null) {lista.add(crearArrayPosiciones(t - 1, f, c));}
            }
            if ((c == 2) && (f == 0)){
                /*7. si c == 2 y f == 0:
                si f + 1 = 0 -> movi.add()
                si c - 1 = 0 -> movi.add()*/
                if (tablero[t][f + 1][c] == null) {lista.add(crearArrayPosiciones(t, f + 1, c));}
                if (tablero[t][f][c - 1] == null) {lista.add(crearArrayPosiciones(t, f, c - 1));}
            }
            if ((c == 2) && (f == 1)){
                /*8. si c == 2 y f == 1:
                si f - 1 = 0 -> movi.add()
                si f + 1 = 0 -> movi.add()
                si t - 1 = 0 -> movi.add()*/
                if (tablero[t][f - 1][c] == null) {lista.add(crearArrayPosiciones(t, f - 1, c));}
                if (tablero[t][f + 1][c] == null) {lista.add(crearArrayPosiciones(t, f + 1, c));}
                if (tablero[t - 1][f][c] == null) {lista.add(crearArrayPosiciones(t - 1, f, c));}
            }
        }
        return lista;
    }


    public boolean agregarFicha(int fila, int columna, int cuadrado, Ficha ficha, Jugador jugador){
        /*Metodo que agrega una ficha, si la posicion no esta ocupada devuelve True,
        si esta ocupada devuelve False*/
        boolean salida = false;
        if (!(this.tablero[cuadrado][fila][columna] == null)){
            this.tablero[cuadrado][fila][columna] = ficha;
            ficha.setPosicion(new int[]{cuadrado, fila, columna});
            salida = true;
        }
        return salida;
    }

    public boolean verificarRaya(int t, int f, int c, Jugador jugador){
        /*funcion que verifica si se produjo una raya o no,
        le ingresa la posicion de la ultima ficha, y cuenta las fichas adyacentes.
         */
        boolean raya = false;
        if (t == 0){
            if ((c == 0) && (f ==2)){
                if ((tablero[t][f][c+1].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f][c+2].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
                if ((tablero[t][f-1][c].getJugador().getNumero() == jugador.getNumero()) && ((tablero[t][f-2][c].getJugador().getNumero() == jugador.getNumero()))){raya = true;}
            }
            if ((c == 1) && (f == 2)){
                if ((tablero[t][f][c+1].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f][c-1].getJugador().getNumero() == jugador.getNumero())){raya = true;}
                if ((tablero[t+1][f][c].getJugador().getNumero() == jugador.getNumero()) &&(tablero[t+2][f][c].getJugador().getNumero() == jugador.getNumero())){raya = true;}
            }
            if ((c == 2) && (f == 2)){

                if ((tablero[t][f-1][c].getJugador().getNumero() == jugador.getNumero() ) && (tablero[t][f-2][c].getJugador().getNumero() == jugador.getNumero())){raya = true;}
                if ((tablero[t][f][c-1].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f][c-2].getJugador().getNumero() == jugador.getNumero())){raya = true;}
            }
            if ((c == 0) && (f == 1)){
                if ((tablero[t][f+1][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f-1][c].getJugador().getNumero() == jugador.getNumero())){raya = true;}
                if ((tablero[t+1][f][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t+2][f][c].getJugador().getNumero() == jugador.getNumero())){raya = true;}
            }
            if ((c == 0) && (f == 0)){
                if ((tablero[t][f][c+1].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f][c+2].getJugador().getNumero() == jugador.getNumero())){raya = true;}
                if ((tablero[t][f+1][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f+2][c].getJugador().getNumero() == jugador.getNumero())){raya = true;}
            }
            if ((c == 1) && (f == 0)) {
                if ((tablero[t][f][c + 1].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f][c - 1].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
                if ((tablero[t + 1][f][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t + 2][f][c].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
            }
            if ((c == 2) && (f == 0)){
                if ((tablero[t][f][c - 1].getJugador().getNumero() == jugador.getNumero()) && ((tablero[t][f][c - 2].getJugador().getNumero() == jugador.getNumero()))) {raya = true;}
                if ((tablero[t][f + 1][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f + 2][c].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
            }
            if ((c == 2) && (f == 1)){
                if ((tablero[t][f + 1][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f - 1][c].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
                if (tablero[t + 1][f][c].getJugador().getNumero() == jugador.getNumero() && (tablero[t + 2][f][c].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
            }

        } else if (t == 1) {

            if ((c == 0) && (f == 2)){
                if ((tablero[t][f][c+1].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f][c+2].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
                if ((tablero[t][f-1][c].getJugador().getNumero() == jugador.getNumero()) && ((tablero[t][f-2][c].getJugador().getNumero() == jugador.getNumero()))){raya = true;}
            }
            if ((c == 1) && (f == 2)){
                if ((tablero[t][f][c - 1].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f][c + 1].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
                if ((tablero[t - 1][f][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t + 1][f][c].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
            }
            if ((c == 2) && (f == 2)){
                if ((tablero[t][f-1][c].getJugador().getNumero() == jugador.getNumero() ) && (tablero[t][f-2][c].getJugador().getNumero() == jugador.getNumero())){raya = true;}
                if ((tablero[t][f][c-1].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f][c-2].getJugador().getNumero() == jugador.getNumero())){raya = true;}
            }
            if ((c == 0) && (f == 1)){
                if ((tablero[t - 1][f][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t + 1][f][c].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
                if ((tablero[t][f - 1][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f + 1][c].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
            }
            if ((c == 0) && (f == 0)){
                if ((tablero[t][f][c+1].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f][c+2].getJugador().getNumero() == jugador.getNumero())){raya = true;}
                if ((tablero[t][f+1][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f+2][c].getJugador().getNumero() == jugador.getNumero())){raya = true;}
            }
            if ((c == 1) && (f == 0)){
                if ((tablero[t][f][c - 1].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f][c + 1].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
                if ((tablero[t + 1][f][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t - 1][f][c].getJugador().getNumero() == jugador.getNumero())) {raya = true;}

            }
            if ((c == 2) && (f == 0)){
                if ((tablero[t][f][c - 1].getJugador().getNumero() == jugador.getNumero()) && ((tablero[t][f][c - 2].getJugador().getNumero() == jugador.getNumero()))) {raya = true;}
                if ((tablero[t][f + 1][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f + 2][c].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
            }
            if ((c == 2) && (f == 1)){
                if ((tablero[t][f - 1][c].getJugador().getNumero() == jugador.getNumero()) && ((tablero[t][f + 1][c].getJugador().getNumero() == jugador.getNumero()))) {raya = true;}
                if ((tablero[t - 1][f][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t + 1][f][c].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
            }

        }else if (t == 2){

            if ((c == 0) && (f == 2)){
                if ((tablero[t][f][c+1].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f][c+2].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
                if ((tablero[t][f-1][c].getJugador().getNumero() == jugador.getNumero()) && ((tablero[t][f-2][c].getJugador().getNumero() == jugador.getNumero()))){raya = true;}
            }
            if ((c == 1) && (f == 2)){
                if ((tablero[t][f][c + 1].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f][c - 1].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
                if ((tablero[t - 1][f][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t - 2][f][c].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
            }
            if ((c == 2) && (f == 2)){
                if ((tablero[t][f-1][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f-2][c].getJugador().getNumero() == jugador.getNumero())){raya = true;}
                if ((tablero[t][f][c-1].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f][c-2].getJugador().getNumero() == jugador.getNumero())){raya = true;}
            }
            if ((c == 0) && (f == 1)){
                if ((tablero[t][f + 1][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f - 1][c].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
                if ((tablero[t - 1][f][c].getJugador().getNumero() == jugador.getNumero()) && ((tablero[t - 2][f][c].getJugador().getNumero() == jugador.getNumero()))) {raya = true;}
            }
            if ((c == 0) && (f == 0)){
                if ((tablero[t][f][c+1].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f][c+2].getJugador().getNumero() == jugador.getNumero())){raya = true;}
                if ((tablero[t][f+1][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f+2][c].getJugador().getNumero() == jugador.getNumero())){raya = true;}
            }
            if ((c == 1) && (f == 0)){

                if ((tablero[t][f][c - 1].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f][c + 1].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
                if ((tablero[t - 1][f][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t - 2][f][c].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
            }
            if ((c == 2) && (f == 0)){

                if ((tablero[t][f][c - 1].getJugador().getNumero() == jugador.getNumero()) && ((tablero[t][f][c - 2].getJugador().getNumero() == jugador.getNumero()))) {raya = true;}
                if ((tablero[t][f + 1][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f + 2][c].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
            }
            if ((c == 2) && (f == 1)){
                if ((tablero[t][f - 1][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t][f + 1][c].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
                if ((tablero[t - 1][f][c].getJugador().getNumero() == jugador.getNumero()) && (tablero[t - 2][f][c].getJugador().getNumero() == jugador.getNumero())) {raya = true;}
            }
        }
        return raya;
    }

    public void sacarFicha(Ficha ficha){
        //Saco la ficha del tablero, y luego se la saco al jugador:
        if (tablero[ficha.getPosicion()[0]][ficha.getPosicion()[1]][ficha.getPosicion()[2]] != null){
            tablero[ficha.getPosicion()[0]][ficha.getPosicion()[1]][ficha.getPosicion()[2]] = null;
            Jugador jugador = ficha.getJugador();
            jugador.sacarFicha(ficha);
        }

    }

}
