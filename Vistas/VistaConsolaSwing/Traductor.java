package Vistas.VistaConsolaSwing;

import java.util.Arrays;
import java.util.Objects;

public class Traductor {

    public static String traductorInverso(int[] ficha){
        String salida = null;

        if (Arrays.equals(ficha, new int[]{0, 0, 0})) {
            salida = "A1";
        } else if (Arrays.equals(ficha, new int[]{0, 0, 1})) {
            salida = "A2";
        } else if (Arrays.equals(ficha, new int[]{0, 0, 2})) {
            salida = "A3";
        } else if (Arrays.equals(ficha, new int[]{0, 1, 2})) {
            salida = "A4";
        } else if (Arrays.equals(ficha, new int[]{0, 2, 2})) {
            salida = "A5";
        } else if (Arrays.equals(ficha, new int[]{0, 2, 1})) {
            salida = "A6";
        } else if (Arrays.equals(ficha, new int[]{0, 2, 0})) {
            salida = "A7";
        } else if (Arrays.equals(ficha, new int[]{0, 1, 0})) {
            salida = "A8";
        } else if (Arrays.equals(ficha, new int[]{1, 0, 0})) {
            salida = "B1";
        } else if (Arrays.equals(ficha, new int[]{1, 0, 1})) {
            salida = "B2";
        } else if (Arrays.equals(ficha, new int[]{1, 0, 2})) {
            salida = "B3";
        } else if (Arrays.equals(ficha, new int[]{1, 1, 2})) {
            salida = "B4";
        } else if (Arrays.equals(ficha, new int[]{1, 2, 2})) {
            salida = "B5";
        } else if (Arrays.equals(ficha, new int[]{1, 2, 1})) {
            salida = "B6";
        } else if (Arrays.equals(ficha, new int[]{1, 2, 0})) {
            salida = "B7";
        } else if (Arrays.equals(ficha, new int[]{1, 1, 0})) {
            salida = "B8";
        } else if (Arrays.equals(ficha, new int[]{2, 0, 0})) {
            salida = "C1";
        } else if (Arrays.equals(ficha, new int[]{2, 0, 1})) {
            salida = "C2";
        } else if (Arrays.equals(ficha, new int[]{2, 0, 2})) {
            salida = "C3";
        } else if (Arrays.equals(ficha, new int[]{2, 1, 2})) {
            salida = "C4";
        } else if (Arrays.equals(ficha, new int[]{2, 2, 2})) {
            salida = "C5";
        } else if (Arrays.equals(ficha, new int[]{2, 2, 1})) {
            salida = "C6";
        } else if (Arrays.equals(ficha, new int[]{2, 2, 0})) {
            salida = "C7";
        } else if (Arrays.equals(ficha, new int[]{2, 1, 0})) {
            salida = "C8";
        }
        return salida;
    }
}
