/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlador.Graph.Adjacency;
import controlador.Graph.DirectLableGraph;
import controlador.List.LinkedList;
import modelo.Prim2;

import java.util.HashMap;

public class LaberintoController {

    private char[][] laberinto;
    private String[] camino;
    private String start;
    private String end;

    public LaberintoController(int filas, int columnas) {
        generarLaberinto(filas, columnas);
    }

    private void generarLaberinto(int filas, int columnas) {
        Prim2 prim = new Prim2();
        String labStr = prim.generar(filas, columnas);

        String[] lineas = labStr.split("\n");
        laberinto = new char[lineas.length][];
        for (int i = 0; i < lineas.length; i++) {
            laberinto[i] = lineas[i].replace(",", "").toCharArray();
        }

        buscarInicioYFin();
    }

    private void buscarInicioYFin() {
        start = null;
        end = null;
        for (int r = 0; r < laberinto.length; r++) {
            for (int c = 0; c < laberinto[0].length; c++) {
                if (laberinto[r][c] == 'S') {
                    start = r + "," + c;
                } else if (laberinto[r][c] == 'E') {
                    end = r + "," + c;
                }
            }
        }
    }

    public boolean tieneInicioYFin() {
        return start != null && end != null;
    }

    public void resolverLaberinto() {
        DirectLableGraph<String> grafo = laberintoAGrafo(laberinto);
        camino = dijkstra(grafo, start, end);
    }

    public char[][] getLaberinto() {
        return laberinto;
    }

    public String[] getCamino() {
        return camino;
    }

    private DirectLableGraph<String> laberintoAGrafo(char[][] maz) {
        int filas = maz.length;
        int cols = maz[0].length;
        HashMap<String, Integer> mapaIndices = new HashMap<>();
        int totalNodos = 0;

        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < cols; c++) {
                if (maz[r][c] != '0') {
                    String label = r + "," + c;
                    mapaIndices.put(label, ++totalNodos);
                }
            }
        }

        DirectLableGraph<String> grafo = new DirectLableGraph<>(totalNodos, String.class);

        for (String label : mapaIndices.keySet()) {
            grafo.label_vertex(mapaIndices.get(label), label);
        }

        int[][] direcciones = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < cols; c++) {
                if (maz[r][c] != '0') {
                    String desde = r + "," + c;
                    int idDesde = mapaIndices.get(desde);

                    for (int[] d : direcciones) {
                        int nr = r + d[0];
                        int nc = c + d[1];
                        if (nr >= 0 && nr < filas && nc >= 0 && nc < cols && maz[nr][nc] != '0') {
                            String hacia = nr + "," + nc;
                            int idHacia = mapaIndices.get(hacia);
                            grafo.insert_label(desde, hacia, 1f);
                        }
                    }
                }
            }
        }

        return grafo;
    }

    private String[] dijkstra(DirectLableGraph<String> g, String inicio, String fin) {
        int n = g.nro_vertex();
        float[] dist = new float[n + 1];
        boolean[] visitado = new boolean[n + 1];
        int[] padre = new int[n + 1];

        final float INFINITO = Float.MAX_VALUE / 2;

        for (int i = 1; i <= n; i++) {
            dist[i] = INFINITO;
            visitado[i] = false;
            padre[i] = -1;
        }

        int start = g.getVertex(inicio);
        int end = g.getVertex(fin);
        dist[start] = 0;

        for (int i = 1; i <= n; i++) {
            int u = -1;
            float minDist = INFINITO;
            for (int j = 1; j <= n; j++) {
                if (!visitado[j] && dist[j] < minDist) {
                    minDist = dist[j];
                    u = j;
                }
            }
            if (u == -1) break;
            visitado[u] = true;
            if (u == end) break;

            LinkedList<Adjacency> adyacentes = g.adjacencies(u);
            Adjacency[] adyArray = adyacentes.toArray();
            for (Adjacency ad : adyArray) {
                int v = ad.getDestiny();
                float peso = ad.getWeigth();
                if (!visitado[v] && dist[u] + peso < dist[v]) {
                    dist[v] = dist[u] + peso;
                    padre[v] = u;
                }
            }
        }

        LinkedList<String> camino = new LinkedList<>();
        for (int v = end; v != -1; v = padre[v]) {
            camino.addFirst(g.getLabel(v));
        }

        String[] resultado = new String[camino.getLength()];
        for (int i = 0; i < camino.getLength(); i++) {
            resultado[i] = camino.get(i);
        }
        return resultado;
    }
}
