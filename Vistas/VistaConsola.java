package Vistas;

import Controlador.Controlador;

import java.util.Scanner;

public class VistaConsola implements IVista{
    private Scanner sc;
    private Controlador controlador;

    public VistaConsola(){
        this.sc = new Scanner(System.in);
    }
    @Override
    public void iniciar() {

    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
}
