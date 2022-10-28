package Clases;

import java.util.List;

public class Partida extends Tablero{
    private List<Jugador> jugadores;
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

    public int[] getPuntajes(){
        int[] salida = new int[2];
        salida[0] = jugadores.get(0).getPuntaje();
        salida[1] = jugadores.get(1).getPuntaje();
        return salida;
    }






}
