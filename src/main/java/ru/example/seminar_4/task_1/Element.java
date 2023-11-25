package ru.example.seminar_4.task_1;

public class Element{
    private Object element;
    private Element nextElement;
    private Element prevElement;

    public Element getNext(){
        return this.nextElement;
    }

    public Element getPreveus(){
        return this.prevElement;
    }

    public void set(Object o){
        element = o;
    }
}