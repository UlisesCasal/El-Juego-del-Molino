package Vistas.VistaGrafica;


import Controlador.Controlador;
import Modelo.Eventos;
import Vistas.Errores;
import Vistas.EstadosVista;
import Vistas.IVista;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

import static Vistas.EstadosVista.*;
import static Vistas.VistaConsolaSwing.Traductor.traductorInverso;

public class VGrafic extends javax.swing.JFrame implements IVista {
    private EstadosVista estadoActual;
    private Controlador controlador;
    private String textoInput;
    int[] ficha = new int[3];
    /**
     * Creates new form VGrafic
     */
    public VGrafic() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controlador.desconectado();
                super.windowClosing(e);
                System.exit(0);
            }
        });
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        bg = new javax.swing.JPanel();
        FondoTablero = new javax.swing.JPanel();
        A1 = new javax.swing.JButton();
        A2 = new javax.swing.JButton();
        A3 = new javax.swing.JButton();
        A4 = new javax.swing.JButton();
        A5 = new javax.swing.JButton();
        A6 = new javax.swing.JButton();
        A7 = new javax.swing.JButton();
        A8 = new javax.swing.JButton();
        B1 = new javax.swing.JButton();
        B2 = new javax.swing.JButton();
        B3 = new javax.swing.JButton();
        B4 = new javax.swing.JButton();
        B5 = new javax.swing.JButton();
        B6 = new javax.swing.JButton();
        B7 = new javax.swing.JButton();
        B8 = new javax.swing.JButton();
        C1 = new javax.swing.JButton();
        C2 = new javax.swing.JButton();
        C3 = new javax.swing.JButton();
        C4 = new javax.swing.JButton();
        C5 = new javax.swing.JButton();
        C6 = new javax.swing.JButton();
        C7 = new javax.swing.JButton();
        C8 = new javax.swing.JButton();
        Tablero = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        TextoInformativo = new javax.swing.JLabel();
        Fondo = new javax.swing.JLabel();

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FondoTablero.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(60, 63, 65), 1, true));
        FondoTablero.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        A1.setText("Vacío");
        A1.setBorder(null);
        A1.setBorderPainted(false);
        A1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A1ActionPerformed(evt);
            }
        });
        FondoTablero.add(A1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 40, 20));

        A2.setText("Vacío");
        A2.setBorder(null);
        A2.setBorderPainted(false);
        A2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A2ActionPerformed(evt);
            }
        });
        FondoTablero.add(A2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 40, 20));

        A3.setText("Vacío");
        A3.setBorder(null);
        A3.setBorderPainted(false);
        A3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A3ActionPerformed(evt);
            }
        });
        FondoTablero.add(A3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 40, 20));

        A4.setText("Vacío");
        A4.setBorder(null);
        A4.setBorderPainted(false);
        A4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A4ActionPerformed(evt);
            }
        });
        FondoTablero.add(A4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, 40, 20));

        A5.setText("Vacío");
        A5.setBorder(null);
        A5.setBorderPainted(false);
        A5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A5ActionPerformed(evt);
            }
        });
        FondoTablero.add(A5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, 40, 20));

        A6.setText("Vacío");
        A6.setBorder(null);
        A6.setBorderPainted(false);
        A6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A6ActionPerformed(evt);
            }
        });
        FondoTablero.add(A6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 40, 20));

        A7.setText("Vacío");
        A7.setBorder(null);
        A7.setBorderPainted(false);
        A7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A7ActionPerformed(evt);
            }
        });
        FondoTablero.add(A7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 40, 20));

        A8.setText("Vacío");
        A8.setBorder(null);
        A8.setBorderPainted(false);
        A8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A8ActionPerformed(evt);
            }
        });
        FondoTablero.add(A8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 40, 20));

        B1.setText("Vacío");
        B1.setBorder(null);
        B1.setBorderPainted(false);
        B1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B1ActionPerformed(evt);
            }
        });
        FondoTablero.add(B1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 40, 20));

        B2.setText("Vacío");
        B2.setBorder(null);
        B2.setBorderPainted(false);
        B2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B2ActionPerformed(evt);
            }
        });
        FondoTablero.add(B2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 40, 20));

        B3.setText("Vacío");
        B3.setBorder(null);
        B3.setBorderPainted(false);
        B3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B3ActionPerformed(evt);
            }
        });
        FondoTablero.add(B3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 40, 20));

        B4.setText("Vacío");
        B4.setBorder(null);
        B4.setBorderPainted(false);
        B4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B4ActionPerformed(evt);
            }
        });
        FondoTablero.add(B4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, 40, 20));

        B5.setText("Vacío");
        B5.setBorder(null);
        B5.setBorderPainted(false);
        B5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B5ActionPerformed(evt);
            }
        });
        FondoTablero.add(B5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 270, 40, 20));

        B6.setText("Vacío");
        B6.setBorder(null);
        B6.setBorderPainted(false);
        B6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B6ActionPerformed(evt);
            }
        });
        FondoTablero.add(B6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 40, 20));

        B7.setText("Vacío");
        B7.setBorder(null);
        B7.setBorderPainted(false);
        B7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B7ActionPerformed(evt);
            }
        });
        FondoTablero.add(B7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 40, 20));

        B8.setText("Vacío");
        B8.setBorder(null);
        B8.setBorderPainted(false);
        B8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B8ActionPerformed(evt);
            }
        });
        FondoTablero.add(B8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 40, 20));

        C1.setText("Vacío");
        C1.setBorder(null);
        C1.setBorderPainted(false);
        C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C1ActionPerformed(evt);
            }
        });
        FondoTablero.add(C1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 40, 20));

        C2.setText("Vacío");
        C2.setBorder(null);
        C2.setBorderPainted(false);
        C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C2ActionPerformed(evt);
            }
        });
        FondoTablero.add(C2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 40, 20));

        C3.setText("Vacío");
        C3.setBorder(null);
        C3.setBorderPainted(false);
        C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C3ActionPerformed(evt);
            }
        });
        FondoTablero.add(C3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 40, 20));

        C4.setText("Vacío");
        C4.setBorder(null);
        C4.setBorderPainted(false);
        C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C4ActionPerformed(evt);
            }
        });
        FondoTablero.add(C4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 40, 20));

        C5.setText("Vacío");
        C5.setBorder(null);
        C5.setBorderPainted(false);
        C5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C5ActionPerformed(evt);
            }
        });
        FondoTablero.add(C5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, 40, 20));

        C6.setText("Vacío");
        C6.setBorder(null);
        C6.setBorderPainted(false);
        C6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C6ActionPerformed(evt);
            }
        });
        FondoTablero.add(C6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 40, 20));

        C7.setText("Vacío");
        C7.setBorder(null);
        C7.setBorderPainted(false);
        C7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C7ActionPerformed(evt);
            }
        });
        FondoTablero.add(C7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 40, 20));

        C8.setText("Vacío");
        C8.setBorder(null);
        C8.setBorderPainted(false);
        C8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C8ActionPerformed(evt);
            }
        });
        FondoTablero.add(C8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 40, 20));

        //Mio
        Icon iconTablero = new ImageIcon("Vistas/VistaGrafica/Imagenes/tablero.jpg");
        ImageIcon iconFondo = new ImageIcon("Vistas/VistaGrafica/Imagenes/Fondo.png");
        Tablero.setIcon(iconTablero); // NOI18N
        FondoTablero.add(Tablero, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -40, 460, -1));

        bg.add(FondoTablero, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 490, 350));

        Fondo.setIcon(new javax.swing.ImageIcon(iconFondo.getImage())); // NOI18N
        bg.add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, -4, 650, 470));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(TextoInformativo, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 20, Short.MAX_VALUE)
                                .addComponent(TextoInformativo))
        );

        bg.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 490, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    private void A1ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("A1");
    }

    private void A2ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("A2");
    }
    private void A3ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("A3");
    }
    private void A4ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("A4");
    }
    private void A5ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("A5");
    }
    private void A6ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("A6");
    }
    private void A7ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("A7");
    }
    private void A8ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("A8");
    }
    private void B1ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("B1");
    }
    private void B2ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("B2");
    }
    private void B3ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("B3");
    }
    private void B4ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("B4");
    }
    private void B5ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("B5");
    }
    private void B6ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("B6");
    }
    private void B7ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("B7");
    }
    private void B8ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("B8");
    }
    private void C1ActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("=========");
        System.out.println("C1 apretado!!!");
        manejadorBotones("C1");
    }
    private void C2ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("C2");
    }
    private void C3ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("C3");
    }
    private void C4ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("C4");
    }
    private void C5ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("C5");
    }
    private void C6ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("C6");
    }
    private void C7ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("C7");
    }
    private void C8ActionPerformed(java.awt.event.ActionEvent evt) {
        manejadorBotones("C8");
    }


    private void manejadorBotones(String name) {
        System.out.println("===========================");
        System.out.println("Ingreso a manejador botones!!!");
        System.out.println("El estado es: " + this.estadoActual);
        this.textoInput = name;
        switch (this.estadoActual){
            case INGRESARFICHA -> {
                this.ponerFicha();
            }
            case MOVERFICHA -> {
                System.out.println("Entro al case mover ficha!!!");
                mostrarMoverFicha1fase();
            }
            case BLOQUEADA -> {

            }
            case SACARFICHA -> {
                this.mostrarSacarFicha();

            }
            case MOVERFICHA2DAFASE -> {
                mostrarMoverFicha2fase();
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VGrafic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VGrafic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VGrafic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VGrafic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VGrafic().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton A1;
    private javax.swing.JButton A2;
    private javax.swing.JButton A3;
    private javax.swing.JButton A4;
    private javax.swing.JButton A5;
    private javax.swing.JButton A6;
    private javax.swing.JButton A7;
    private javax.swing.JButton A8;
    private javax.swing.JButton B1;
    private javax.swing.JButton B2;
    private javax.swing.JButton B3;
    private javax.swing.JButton B4;
    private javax.swing.JButton B5;
    private javax.swing.JButton B6;
    private javax.swing.JButton B7;
    private javax.swing.JButton B8;
    private javax.swing.JButton C1;
    private javax.swing.JButton C2;
    private javax.swing.JButton C3;
    private javax.swing.JButton C4;
    private javax.swing.JButton C5;
    private javax.swing.JButton C6;
    private javax.swing.JButton C7;
    private javax.swing.JButton C8;
    private javax.swing.JLabel Fondo;
    private javax.swing.JPanel FondoTablero;
    private javax.swing.JLabel Tablero;
    private javax.swing.JLabel TextoInformativo;
    private javax.swing.JPanel bg;
    private javax.swing.JPanel jPanel1;

    @Override
    public void iniciar() {
        this.mostrarConsola();
        this.TextoInformativo.setText("Seleccione la posicion donde ingresar la ficha");
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
        setTitle("El Juego del Molino, jugador: " + this.controlador.getNombreJugador());
    }

    @Override
    public void mostrarTablero() {
        this.mostrarConsola();
    }

    @Override
    public void mostrarPuntajesFinales() {

        // VERIFICAR:
        TextoInformativo.setText("Ganaste!!!, el rival se ha desconectado.");
        cambiarEstado(BLOQUEADA);
    }

    @Override
    public void mostrarSacarFicha() {
        String ficha = textoInput;
        int[] posicion = traductor(ficha);
        if (posicion[0] != -1) {
            this.controlador.sacarFicha(posicion[0], posicion[1], posicion[2]);
        }else{
            cambiarEstado(SACARFICHA);

        }
    }

    @Override
    public void actualizarTablero(int[] ficha, String jugador, Eventos eventoMostrar) {
        JButton boton;
        switch (eventoMostrar){
            case FICHAAGREGADA -> {
                habiDeshabiBotones(false);
                boton = getBoton(ficha);
                //Recibo lo pasado y lo transformo a un boton y lo bloqueo.
                boton.setEnabled(false);
                boton.setText(jugador);
            }
            case FICHASACADA -> {
                habiDeshabiBotones(false);
                boton = getBoton(ficha);
                boton.setEnabled(true);
                boton.setText("Vacío");
            }
        }
    }

    @Override
    public void ponerFicha() {
        String ficha = textoInput;
        int[] posicion = traductor(ficha);
        if (posicion[0] != -1) {
            this.controlador.ponerFicha(posicion[0], posicion[1], posicion[2]);
        }else {cambiarEstado(INGRESARFICHA);}

    }



    @Override
    public int[] traductor(String posicionSimbolica) {
        int[] salida = new int[]{-1,-1,-1};
        switch (posicionSimbolica) {
            case "A1" -> {
                salida = new int[]{0, 0, 0};
                break;
            }
            case "A2" -> {
                salida = new int[]{0,0,1};
                break;
            }
            case "A3" -> {
                salida = new int[]{0,0,2};
                break;
            }
            case "A4" -> {
                salida = new int[]{0,1,2};
                break;
            }
            case "A5" -> {
                salida = new int[]{0,2,2};
                break;
            }
            case "A6" -> {
                salida = new int[]{0,2,1};
                break;
            }
            case "A7" -> {
                salida = new int[]{0,2,0};
                break;
            }
            case "A8" -> {
                salida = new int[]{0,1,0};
                break;
            }
            case "B1" -> {
                salida = new int[]{1,0,0};
                break;
            }
            case "B2" -> {
                salida = new int[]{1,0,1};
                break;
            }
            case "B3" -> {
                salida = new int[]{1,0,2};
                break;
            }
            case "B4" -> {
                salida = new int[]{1,1,2};
                break;
            }
            case "B5" -> {
                salida = new int[]{1,2,2};
                break;
            }
            case "B6" -> {
                salida = new int[]{1,2,1};
                break;
            }
            case "B7" -> {
                salida = new int[]{1,2,0};
                break;
            }
            case "B8" -> {
                salida = new int[]{1,1,0};
                break;
            }
            case "C1" -> {
                salida = new int[]{2,0,0};
                break;
            }
            case "C2" -> {
                salida = new int[]{2,0,1};
                break;
            }
            case "C3" -> {
                salida = new int[]{2,0,2};
                break;
            }
            case "C4" -> {
                salida = new int[]{2,1,2};
                break;
            }
            case "C5" -> {
                salida = new int[]{2,2,2};
                break;
            }
            case "C6" -> {
                salida = new int[]{2,2,1};
                break;
            }
            case "C7" -> {
                salida = new int[]{2,2,0};
                break;
            }
            case "C8" -> {
                salida = new int[]{2,1,0};
                break;
            }
            default -> println("La posicion ingresada es inválida...");
        }
        println("");
        return salida;
    }

    @Override
    public void cambiarEstado(EstadosVista estado) {
        this.estadoActual = estado;
        switch (this.estadoActual) {
            case BLOQUEADA -> println("Espere su turno...");
            case ESPERANDOCONEXION -> mostrarPantallaEspera();
            case INGRESARFICHA -> this.println("Su ficha es: " + this.controlador.getCharJugadorFicha() + ". " + "Ingrese la ficha que quiere agregar: ");
            case SACARFICHA -> {this.println("Su ficha es: " + this.controlador.getCharJugadorFicha() + ". " + "Ingrese la posicion de la ficha a eliminar: ");
            habiDeshabiBotones(true);}
            case MOVERFICHA ->{
                habiDeshabiBotones(true);
                this.println("Su ficha es: " + this.controlador.getCharJugadorFicha() + ". " + "Seleccione la ficha a mover: ");
            }
            case MOVERFICHA2DAFASE -> this.println("Su ficha es: " + this.controlador.getCharJugadorFicha() + ". " + "Ingrese la posicion a mover la ficha: ");
        }
    }

    @Override
    public void mostrarErrores(Errores errores) {
        if (errores == Errores.NOSEPUDOSACARFICHA){
            limpiarConsola();
            mostrarTablero();
            this.println("Por favor ingrese una posicion válida");
        }if (errores == Errores.NOSEPUDOAGREGARFICHA){ // VER SI ANDA
            println("La posicion ingresada es inválida, vuelva a intentarlo...");
            cambiarEstado(INGRESARFICHA); // VER SI ANDA
        }if (errores == Errores.NOSEPUDOMOVERFICHA){
            println("No se pudo mover la ficha, vuelva a intentarlo...");
            cambiarEstado(MOVERFICHA); // VER SI ANDA
        }
    }

    private void limpiarConsola() {
        println("");
    }

    @Override
    public void mostrarMoverFicha1fase() {
        System.out.println("Entro al mostrarMoverFicha1Fase!!!");
        String fichaAMover = textoInput;
        int[] posicionFicha = traductor(fichaAMover);
        if (posicionFicha[0] != -1) {
            System.out.println("Posicion correcta");
            boolean valido = this.controlador.verificarFicha(posicionFicha[0], posicionFicha[1], posicionFicha[2]);
            if (valido) {
                limpiarConsola();
                mostrarTablero(); // ver si lo dejo
                ficha = posicionFicha;
                //Pongo el boton como apretado, ya que fue elegido
                JButton botonElegido = getBoton(posicionFicha);
                botonElegido.setEnabled(false);
                cambiarEstado(MOVERFICHA2DAFASE);
            }else{
                mostrarErrores(Errores.NOSEPUDOMOVERFICHA);
            }
        }else{
            cambiarEstado(MOVERFICHA);
        }
    }

    @Override
    public void mostrarMoverFicha2fase() {
        String fichaAMover = textoInput;
        int[] posicionFichaMover = traductor(fichaAMover);
        if (posicionFichaMover[0] != -1) {
            boolean valido = this.controlador.verificarPosicionVacia(posicionFichaMover[0], posicionFichaMover[1], posicionFichaMover[2]);
            if (valido) {
                this.controlador.moverFicha(ficha[0], ficha[1], ficha[2], posicionFichaMover[0], posicionFichaMover[1], posicionFichaMover[2]);
            } else {
                cambiarEstado(MOVERFICHA);
            }
        }else {
            cambiarEstado(MOVERFICHA);
        }
    }


    @Override
    public void mostrarPantallaEspera() {
        //No posee
    }

    @Override
    public void mostrarConsola() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void puntajeHistorico() {

        String[] puntaje = this.controlador.getPuntajesFinales();
        String[] nombreJugadores = this.controlador.getNombreJugadores();
        String parametro = "Los puntajes de esta partida son: " + "\n\r";
        parametro += "===PUNTAJES HISTORICOS===" + "\n\r";
        parametro += this.controlador.desSerializar();
        //Llamo a la ventana con los resultados.
        Resultados r = new Resultados(parametro);
        Resultados.main(null);
    }

    // End of variables declaration
    public void println(String texto){
        this.TextoInformativo.setText(texto);
    }

    public JButton getBoton(int[] ficha) {
        JButton salida = null;

        if (Arrays.equals(ficha, new int[]{0, 0, 0})) {
            salida = A1;
        } else if (Arrays.equals(ficha, new int[]{0, 0, 1})) {
            salida = A2;
        } else if (Arrays.equals(ficha, new int[]{0, 0, 2})) {
            salida = A3;
        } else if (Arrays.equals(ficha, new int[]{0, 1, 2})) {
            salida = A4;
        } else if (Arrays.equals(ficha, new int[]{0, 2, 2})) {
            salida = A5;
        } else if (Arrays.equals(ficha, new int[]{0, 2, 1})) {
            salida = A6;
        } else if (Arrays.equals(ficha, new int[]{0, 2, 0})) {
            salida = A7;
        } else if (Arrays.equals(ficha, new int[]{0, 1, 0})) {
            salida = A8;
        } else if (Arrays.equals(ficha, new int[]{1, 0, 0})) {
            salida = B1;
        } else if (Arrays.equals(ficha, new int[]{1, 0, 1})) {
            salida = B2;
        } else if (Arrays.equals(ficha, new int[]{1, 0, 2})) {
            salida = B3;
        } else if (Arrays.equals(ficha, new int[]{1, 1, 2})) {
            salida = B4;
        } else if (Arrays.equals(ficha, new int[]{1, 2, 2})) {
            salida = B5;
        } else if (Arrays.equals(ficha, new int[]{1, 2, 1})) {
            salida = B6;
        } else if (Arrays.equals(ficha, new int[]{1, 2, 0})) {
            salida = B7;
        } else if (Arrays.equals(ficha, new int[]{1, 1, 0})) {
            salida = B8;
        } else if (Arrays.equals(ficha, new int[]{2, 0, 0})) {
            salida = C1;
        } else if (Arrays.equals(ficha, new int[]{2, 0, 1})) {
            salida = C2;
        } else if (Arrays.equals(ficha, new int[]{2, 0, 2})) {
            salida = C3;
        } else if (Arrays.equals(ficha, new int[]{2, 1, 2})) {
            salida = C4;
        } else if (Arrays.equals(ficha, new int[]{2, 2, 2})) {
            salida = C5;
        } else if (Arrays.equals(ficha, new int[]{2, 2, 1})) {
            salida = C6;
        } else if (Arrays.equals(ficha, new int[]{2, 2, 0})) {
            salida = C7;
        } else if (Arrays.equals(ficha, new int[]{2, 1, 0})) {
            salida = C8;
        }

        return salida;
    }

    public void habiDeshabiBotones(boolean habilitar){
        Component[] componentes = FondoTablero.getComponents();
        if (habilitar) {
            for (int i = 0; i < componentes.length; i++) {
                if (componentes[i] instanceof JButton) {
                    componentes[i].setEnabled(true);
                }
            }
        }else{
            for (int i = 0; i < componentes.length; i++) {
                if (componentes[i] instanceof JButton) {
                    if (((JButton) componentes[i]).getText() != "Vacío") {
                        componentes[i].setEnabled(false);
                    }
                }
            }
        }

    }
}


