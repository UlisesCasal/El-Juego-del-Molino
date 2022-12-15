package Vistas.VistaGrafica.ResultadosFinales;

import Ejecucion.Prueba;
import Vistas.EstadosVista;
import Vistas.IVista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Resultados extends JFrame{
    private JTextArea textoMostrar;
    private JPanel panel1;
    private JButton iniciarButton;
    private JButton cerrarButton;
    private IVista vistaQueLlamo;

    public Resultados(String mostrar, IVista vista){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.vistaQueLlamo = vista;
        System.out.println("Entro a Resultados");
        this.iniciar();
        this.setContentPane(this.panel1);
        this.textoMostrar.setText(mostrar);

        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                vistaQueLlamo.eliminar();
            }
        });
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                vistaQueLlamo.cambiarEstado(EstadosVista.REINICIARJUEGO);
            }
        });
    }

    public void iniciar(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    setSize(500,500);
                    setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
