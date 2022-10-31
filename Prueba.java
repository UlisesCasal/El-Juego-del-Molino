import Clases.Jugador;
import Clases.Partida;

public class Prueba {
    public static void main(String[] args) {
        Partida partida = new Partida();
        partida.setJugador("Ulises");
        partida.setJugador("Ulises2");
        Jugador jugador1 = partida.darTurno();
        Jugador jugadorTurno2 = partida.darTurno();
        partida.ponerFicha(jugador1, 0,0,0);
        partida.ponerFicha(jugador1, 0,1,0);
        partida.ponerFicha(jugador1, 0,2,0);
        System.out.println("El puntaje del jugador2 es: " + partida.getPuntajes()[0]);
        System.out.println("El puntaje del jugador1 es: " + partida.getPuntajes()[1]);
    }
}
