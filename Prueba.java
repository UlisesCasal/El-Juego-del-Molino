import Clases.Jugador;
import Clases.Partida;

public class Prueba {
    public static void main(String[] args) {
        Partida partida = new Partida();
        partida.setJugador("Ulises");
        partida.setJugador("Ulises2");
        Jugador jugador1 = partida.darTurno();
        Jugador jugador2 = partida.darTurno();
        partida.ponerFicha(jugador1, 0,0,0);
        partida.ponerFicha(jugador1, 0,1,0);
        partida.ponerFicha(jugador1, 0,2,0);
        partida.sacarFicha(jugador1.getFicha(0,0,0));
        partida.moverFichas(jugador1.getFicha(0,1,0), 1,1,0, jugador1);
        partida.terminoLaPartida();
    }
}
