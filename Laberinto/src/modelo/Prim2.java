/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

public class Prim2 {

    public String generar(int r, int c) {
        // Inicializar laberinto con paredes
        StringBuilder s = new StringBuilder(c);
        for (int x = 0; x < c; x++) {
            s.append('0');
        }
        char[][] maz = new char[r][c];
        for (int x = 0; x < r; x++) {
            maz[x] = s.toString().toCharArray();
        }

        // Seleccionar punto inicial aleatorio
        Point st = new Point((int) (Math.random() * r), (int) (Math.random() * c), null);
        maz[st.r][st.c] = 'S';

        // Vecinos del punto inicial
        ArrayList<Point> frontier = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0 || x != 0 && y != 0) continue;
                try {
                    if (maz[st.r + x][st.c + y] == '1') continue;
                } catch (Exception e) {
                    continue;
                }
                frontier.add(new Point(st.r + x, st.c + y, st));
            }
        }

        Point last = null;

        // Algoritmo de Prim para generar el camino principal
        while (!frontier.isEmpty()) {
            Point cu = frontier.remove((int) (Math.random() * frontier.size()));
            Point op = cu.opposite();
            try {
                if (maz[cu.r][cu.c] == '0' && maz[op.r][op.c] == '0') {
                    maz[cu.r][cu.c] = '1';
                    maz[op.r][op.c] = '1';
                    last = op;

                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            if (x == 0 && y == 0 || x != 0 && y != 0) continue;
                            try {
                                if (maz[op.r + x][op.c + y] == '1') continue;
                            } catch (Exception e) {
                                continue;
                            }
                            frontier.add(new Point(op.r + x, op.c + y, op));
                        }
                    }
                }
            } catch (Exception e) {
                // Ignorar errores fuera de límites
            }

            if (frontier.isEmpty() && last != null) {
                maz[last.r][last.c] = 'E';
            }
        }


        int caminosExtra = (r * c) / 20;
        for (int i = 0; i < caminosExtra; i++) {
            int x = (int) (Math.random() * (r - 2)) + 1;
            int y = (int) (Math.random() * (c - 2)) + 1;

            if (maz[x][y] == '0') {
                int count = 0;
                if (maz[x - 1][y] == '1') count++;
                if (maz[x + 1][y] == '1') count++;
                if (maz[x][y - 1] == '1') count++;
                if (maz[x][y + 1] == '1') count++;

                if (count >= 2) {
                    maz[x][y] = '1';
                }
            }
        }

        // Convertir matriz a string con comas
        s = new StringBuilder();
        for (int i = 0; i < r; i++) {
            String aux = "";
            for (int j = 0; j < c; j++) {
                aux += maz[i][j] + ",";
            }
            aux = aux.substring(0, aux.length() - 1);
            s.append(aux).append("\n");
        }

        return s.toString();
    }

    // Clase auxiliar para puntos del laberinto
    static class Point {
        Integer r;
        Integer c;
        Point parent;

        public Point(int x, int y, Point p) {
            r = x;
            c = y;
            parent = p;
        }

        public Point opposite() {
            if (this.parent == null) return null;

            if (!this.r.equals(parent.r)) {
                return new Point(this.r + (this.r - parent.r), this.c, this);
            }
            if (!this.c.equals(parent.c)) {
                return new Point(this.r, this.c + (this.c - parent.c), this);
            }
            return null;
        }
    }

}
