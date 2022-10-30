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

    public boolean ponerFicha(Jugador jugador, int t, int f, int c){
        Ficha ficha = jugador.getFichaNoPuesta();
        boolean salida = false;

        if (ficha != null){
             if (this.tablero.agregarFicha(f,c,t,ficha,jugador)){
                 //Si la pudo ingresar, pongo el flag en true, y verifico si se produjo una raya:
                 salida = true;
                 if (verificarRaya(t,f,c,jugador)){
                     //Si me da true que hay una nueva raya:
                     //1. debo retirar alguna ficha del oponente:
                     //2. me debe ingresar la ficha a retirar
                     //DEBERE NOTIFICAR DE LO SUCEDIDO:
                 }


            }
        }
        return salida;
    }


    public int[] getPuntajes(){
        int[] salida = new int[2];
        salida[0] = jugadores.get(0).getPuntaje();
        salida[1] = jugadores.get(1).getPuntaje();
        return salida;
    }






}
