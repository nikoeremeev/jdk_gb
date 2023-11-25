package ru.example.seminar_3.task_1;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    
    public static void main(String[] args) {
        GenericClass genericClass = new GenericClass(
          " ", 
          new InputStream() {
            @Override
            public int read() throws IOException {
                throw new UnsupportedOperationException("Unimplemented method 'read'");
            }
            
          }, 
        5);

        System.out.println(genericClass.info());
    }
}