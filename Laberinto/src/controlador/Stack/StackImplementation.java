/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.Stack;

import controlador.List.LinkedList;

public class StackImplementation<E> extends LinkedList<E> {
    private Integer top;

    public Integer getTop() {
        return this.top;
    }

    public StackImplementation(Integer top){
        this.top = top;
    }

    protected Boolean isFullStack() {
        return getLength() >  this.top ;
    }

    protected void push(E info) throws Exception {
        if(!isFullStack()) {
            add(info, 0);
        } else {
            throw new ArrayIndexOutOfBoundsException("Stack full");
        }
    }
    protected E pop() throws Exception {       
        return deleteFirst();
        
    }
}
