package ru.example.hometask_6.hometask_6.src.main.java;

public class Cell {
    private boolean value = false;



    private boolean hidden = true;
    Cell(){
        value = false;
    }
    public void setValue(boolean arg) {
        value = arg;
    }

    public boolean getValue(){
        return value;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean getHidden(){
        return hidden;
    }
}
