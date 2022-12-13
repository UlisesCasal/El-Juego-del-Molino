package Vistas.VistaGrafica;

import ar.edu.unlu.rmimvc.servidor.Servidor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Resultados extends JDialog {
    private JPanel contentPane;
    private JButton okButton;
    private JTextArea txt;
    private JButton nuevaP;
    private static String resultado;

    public Resultados(String resultadoEntrada) {
        resultado = resultadoEntrada;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(okButton);
        txt.setText(resultado);
        txt.setVisible(true);
        setTitle("Resultados del juego");
        //TextoInformativo.setText(resultado);


        nuevaP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }
    public static void main(String[] args) {
        Resultados dialog = new Resultados(resultado);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }


}
