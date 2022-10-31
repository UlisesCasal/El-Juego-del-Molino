package Clases;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    private List<Jugador> jugadores = new ArrayList<Jugador>();
    private Tablero tablero;
    private boolean turno; //True -> jugador1, False -> Jugador2
    private int numeroJugadores;

    public Partida(){
        tablero = new Tablero();
        this.numeroJugadores = 0;
        boolean turno = true;
    }

    public void setJugador(String nombre){
        jugadores.add(new Jugador(nombre, this.numeroJugadores++ ));
    }

    public Jugador darTurno(){
        if (this.turno == true) {
            this.turno = false;
            return jugadores.get(0);
        }else if (this.turno == false){
            this.turno = true;
            return jugadores.get(1);
        }
        return null;
    }

    public boolean ponerFicha(Jugador jugador, int t, int f, int c){
        Ficha ficha = jugador.getFichaNoPuesta();
        boolean salida = false;

        //Corroboro que no sea nulo:
        if (ficha != null){
             if (this.tablero.agregarFicha(f,c,t,ficha,jugador)){
                 //Si la pudo ingresar, pongo el flag en true, y verifico si se produjo una raya:
                 jugador.setPosicionFicha(new int[]{t, f, c}, ficha);
                 salida = true;
                 if (this.tablero.verificarRaya(t,f,c,jugador)){
                     //Si me da true que hay una nueva raya:
                     //1. debo retirar alguna ficha del oponente:
                     //2. me debe ingresar la ficha a retirar
                     //3. debo incrementar el puntaje del jugador
                     //DEBERE NOTIFICAR DE LO SUCEDIDO:
                     jugador.incPuntaje();
                     System.out.println("Se produjo una raya!!!");
                 }


            }
        }
        return salida;
    }

    public void sacarFicha(Ficha ficha){
        this.tablero.sacarFicha(ficha);
    }

    public boolean moverFichas(Ficha ficha, int tmover, int fmover, int cmover, Jugador jugador){
        return this.tablero.moverFichas(ficha,tmover,fmover,cmover,jugador);
    }



    public int[] getPuntajes(){
        int[] salida = new int[2];
        salida[0] = jugadores.get(0).getPuntaje();
        salida[1] = jugadores.get(1).getPuntaje();
        return salida;
    }






}
