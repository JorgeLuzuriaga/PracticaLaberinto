/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.List;

/**
 *
 * @author usuario
 */
//K, V  -- key, value
//E     -- colecciones
//T     -- dato generico
public class Node <E> {
    private E data;
    private Node<E> next;

    public E getData() {
        return this.data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getNext() {
        return this.next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }

    public Node(E data) {
        this.data = data;
        this.next = null;
    }
    public Node() {
        this.data = null;
        this.next = null;
    }
}
