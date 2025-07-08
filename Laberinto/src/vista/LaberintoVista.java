/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package vista;

import controlador.LaberintoController;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class LaberintoVista extends JFrame {

    private JSpinner spinnerSize;
    private JButton btnGenerar;

    public LaberintoVista() {
        setTitle("Laberinto");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        spinnerSize = new JSpinner(new SpinnerNumberModel(30, 30, 100, 1));
        btnGenerar = new JButton("Generar Laberinto");

        add(new JLabel("Dimensión (30 a 100):"));
        add(spinnerSize);
        add(btnGenerar);

        btnGenerar.addActionListener(e -> generarYLanzar());
    }

    private void generarYLanzar() {
        int dim = (int) spinnerSize.getValue();

        LaberintoController controller = new LaberintoController(dim, dim);

        if (!controller.tieneInicioYFin()) {
            JOptionPane.showMessageDialog(this, "No se encontró nodo S o E en el laberinto.");
            return;
        }

        controller.resolverLaberinto();

        SwingUtilities.invokeLater(() -> {
            new LaberintoVista.LaberintoGrafico(controller.getLaberinto(), controller.getCamino()).setVisible(true);
        });
    }

    static class LaberintoGrafico extends JFrame {
        private JPanel gridPanel;
        private int filas, columnas;
        private JLabel[][] celdas;
        private char[][] laberinto;

        public LaberintoGrafico(char[][] laberinto, String[] camino) {
            this.laberinto = laberinto;
            this.filas = laberinto.length;
            this.columnas = laberinto[0].length;

            setTitle("Laberinto con Dijkstra");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(800, 800);
            setLocationRelativeTo(null);

            gridPanel = new JPanel(new GridLayout(filas, columnas));
            celdas = new JLabel[filas][columnas];

            HashSet<String> caminoSet = new HashSet<>();
            if (camino != null) {
                for (String paso : camino) {
                    caminoSet.add(paso);
                }
            }

            for (int r = 0; r < filas; r++) {
                for (int c = 0; c < columnas; c++) {
                    JLabel celda = new JLabel();
                    celda.setOpaque(true);
                    celda.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    String pos = r + "," + c;

                    switch (laberinto[r][c]) {
                        case '0' -> celda.setBackground(Color.BLACK);
                        case '1' -> celda.setBackground(caminoSet.contains(pos) ? Color.YELLOW : Color.WHITE);
                        case 'S' -> celda.setBackground(Color.GREEN);
                        case 'E' -> celda.setBackground(Color.RED);
                        default -> celda.setBackground(Color.WHITE);
                    }

                    celdas[r][c] = celda;
                    gridPanel.add(celda);
                }
            }

            add(gridPanel);
        }

        // Método opcional si necesitas actualizar visualmente el camino
        public void actualizarCamino(String[] nuevoCamino) {
            HashSet<String> caminoSet = new HashSet<>();
            if (nuevoCamino != null) {
                for (String paso : nuevoCamino) {
                    caminoSet.add(paso);
                }
            }

            for (int r = 0; r < filas; r++) {
                for (int c = 0; c < columnas; c++) {
                    String pos = r + "," + c;
                    if (celdas[r][c].getBackground() != Color.GREEN &&
                        celdas[r][c].getBackground() != Color.RED &&
                        laberinto[r][c] != '0') {
                        celdas[r][c].setBackground(caminoSet.contains(pos) ? Color.YELLOW : Color.WHITE);
                    }
                }
            }
            repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LaberintoVista laberinto = new LaberintoVista();
            laberinto.setVisible(true);
        });
    }
}

