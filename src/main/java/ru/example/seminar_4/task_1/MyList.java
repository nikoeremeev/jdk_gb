package ru.example.seminar_4.task_1;

public class MyList {
    private Element firstElement;
    private Element lastElement;
    public MyList(){

    }

    public void add(Element element){
        if (this.firstElement == null) {
            firstElement = element;
            lastElement = element;
            
            firstElement.getNext().set(null);
            firstElement.getPreveus().set(null);

            lastElement.getNext().set(null);
            lastElement.getPreveus().set(null);
        } else {
            lastElement.getNext().set(element);
            element.getNext().set(null);
            element.getPreveus().set(lastElement);
            lastElement = element;
        }
    } 
}
