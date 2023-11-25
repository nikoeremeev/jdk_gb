package ru.example.seminar_3.task_1;

import java.io.InputStream;
import java.io.Serializable;

public class GenericClass <T extends Comparable, V extends InputStream & Serializable , K extends Number> {
    private T t;
    private V v;
    private K k;

    GenericClass(T t, V v, K k) {
        this.t = t;
        this.v = v;
        this.k = k;
    }

    public T retT(){
        return t;
    }

    public V retV(){
        return v;
    }

    public K retK(){
        return k;
    }

    public String info(){
        return String.format("T - type of %s, V - type of %s, K - type of %s", 
        t.getClass().getName(), 
        v.getClass().getName(), 
        k.getClass().getName());
    }

}
