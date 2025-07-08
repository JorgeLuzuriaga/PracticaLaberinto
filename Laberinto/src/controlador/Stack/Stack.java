/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.Stack;


public class Stack <E> {
    private StackImplementation<E> stack;
    public Stack(Integer top) {
        stack = new StackImplementation<>(top);
    }
    public Boolean push(E data) {
        try {
            stack.push(data);
            return true;
        } catch (Exception e) {
            return false;
            // TODO: handle exception
        }
    }

    public E pop() {
        try {
            return stack.pop();
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public Boolean isFullStack() {
        return stack.isFullStack();
    }

    public Integer top() {
        return stack.getTop();
    }

    public Integer size() {
        return stack.getLength();
    }
}