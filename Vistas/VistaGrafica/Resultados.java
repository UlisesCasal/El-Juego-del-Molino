package Vistas.VistaGrafica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Resultados extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel TextoInformativo;
    private static String resultado;

    public Resultados(String resultadoEntrada) {
        resultado = resultadoEntrada;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Resultados del juego");
        TextoInformativo.setText(resultado);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
                dispose();
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
