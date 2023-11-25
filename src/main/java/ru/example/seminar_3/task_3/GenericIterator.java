package ru.example.seminar_3.task_3;

import java.util.Iterator;

public class GenericIterator<T> implements Iterator<T>, Iterable<T>{
    private T[] arr;
    private Integer index = 0;
    
    GenericIterator(T[] arr){
        this.arr = arr;
    }

    @Override
    public boolean hasNext() {
        return (arr.length > index);
    }

    @Override
    public T next(){
        if (hasNext()) {
            return arr[index++]; 
        }
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new GenericIterator<>(arr);
    }


}