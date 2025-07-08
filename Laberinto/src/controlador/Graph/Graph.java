/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.Graph;

/**
 *
 * @author usuario
 */
import controlador.List.LinkedList;
import controlador.Stack.StackImplementation;

public abstract class Graph {

    public abstract Integer nro_vertex();
    public abstract Integer nro_edge();
    public abstract Adjacency exists_Edge(Integer o, Integer d);
    public abstract Float weight_edge(Integer o, Integer d);
    public abstract void insert(Integer o, Integer d);
    public abstract void insert(Integer o, Integer d, Float weigth);
    public abstract LinkedList<Adjacency> adjacencies(Integer o);

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= nro_vertex();i++){
            sb.append("Vertex = ").append(String.valueOf(i)).append("\n");
            LinkedList<Adjacency> list = adjacencies(i);
            if(!list.isEmpty()){
                Adjacency[] matrix =list.toArray();
                for (Adjacency ad: matrix){
                    sb.append("\tAdjacency ").append("\n").append("\t Vertex = ").append(String.valueOf(ad.getDestiny()));
                    if (!ad.getWeigth().isNaN()){
                        sb.append(" weight = "+ad.getWeigth().toString()).append("\n");
                    }
                }
            }
        }
        return sb.toString();
    }
}

