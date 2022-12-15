package Services;

import Modelo.Jugador;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Serializacion {
    private static Serializador serializador = new Serializador("datos.dat");
    private static ArrayList<Jugador> jugadores;
    public static void serializar(List<Jugador> jugadores){
        if (jugadores.size() >= 1){
            boolean noExiste = Files.notExists(Path.of("datos.dat"));
            Path path = Path.of("datos.dat");
            File archivo = path.toFile();
            if (noExiste || archivo.length() == 0) {

                serializador.writeOneObject(jugadores.get(0));
            }else{
                serializador.addOneObject(jugadores.get(0));
            }
            for (int i = 1; i < jugadores.size(); i++){
                serializador.addOneObject(jugadores.get(i));
            }
        }
    }

    public static String desSerializar(){
        if (jugadores == null){
            System.out.println("Crea arreglo de jugadores!!!");
            jugadores = new ArrayList<>();
        }
        String salida;
        Object[] recuperado = serializador.readObjects();
        System.out.println("la cantidad de jugadores recuperados es: " + recuperado.length);

        //PRIMERO RECUPERO LOS OBJETOS DE JUGADOR:
        for (int i = 0; i < recuperado.length; i ++){
            jugadores.add((Jugador) recuperado[i]);
        }
        sortJugadores();

        return armarString();
    }

    public static void sortJugadores() {
        Jugador[] jugadoresArray = jugadores.toArray(new Jugador[0]);
        Jugador auxiliar = null;
        for (int i = 0; i < jugadores.size(); i++) {
            for (int j = 0; j < jugadores.size() - 1; j++) {
                if (jugadoresArray[j].getPuntaje() > jugadoresArray[j + 1].getPuntaje()){
                    auxiliar = jugadoresArray[j];
                    jugadoresArray[j] = jugadoresArray[j+1];
                    jugadoresArray[j+1] =  auxiliar;
                }
            }
        }

        jugadores.clear();
        jugadores.addAll(Arrays.asList(jugadoresArray));
    }

    public static String armarString(){
        StringBuilder salida = new StringBuilder();
        String armado = "";
        String s;
        for (int i = 0; i < jugadores.size(); i++) {
            armado = jugadores.get(i).getNombre() + ": " + (jugadores.get(i).getPuntaje()) + ("\r\n");
            s = String.valueOf(salida);
            if (!s.contains(armado)) {
                salida.append(armado);
            }
        }
        return String.valueOf(salida);
    }
}
