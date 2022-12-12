package Services;

import Modelo.Jugador;
import Modelo.Partida;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Serializacion {
    private static Serializador serializador = new Serializador("datos.dat");
    private static ArrayList<Jugador> jugadores;
    public void serializar(Jugador[] jugadores){
        if (jugadores.length >= 1){
            serializador.writeOneObject(jugadores[0]);
            for (int i = 1; i < jugadores.length; i++){
                serializador.addOneObject(jugadores[i]);
            }
        }
    }

    public String desSerializar(){
        String salida;
        Object[] recuperado = serializador.readObjects();

        //PRIMERO RECUPERO LOS OBJETOS DE JUGADOR:
        for (int i = 0; i < recuperado.length; i ++){
            jugadores.add((Jugador) recuperado[i]);
        }
        sortJugadores();

        return armarString();
    }

    public static void sortJugadores() {
        Jugador auxiliar = null;
        for (int i = 0; i < jugadores.size(); i++) {
            for (int j = 0; j < jugadores.size() - 1; j++) {
                if (jugadores.get(j).getPuntaje() > jugadores.get(j + 1).getPuntaje()){
                    auxiliar = jugadores.get(j);
                    jugadores.add(j, jugadores.get(j+1));
                    jugadores.add(j+1, auxiliar);
                }
            }
        }
    }

    public static String armarString(){
        StringBuilder salida = new StringBuilder();
        for (int i = 0; i < jugadores.size(); i++) {
            salida.append(jugadores.get(i).getNombre()).append(": ").append(jugadores.get(i).getPuntaje()).append("\r\n");
        }
        return String.valueOf(salida);
    }
}
