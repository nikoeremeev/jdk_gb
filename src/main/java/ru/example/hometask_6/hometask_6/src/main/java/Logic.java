package ru.example.hometask_6.hometask_6.src.main.java;

import java.util.Random;

public class Logic {
    private final boolean CAR_VALUE = true;
    private final boolean GOAT_VALUE = false;
    private  final  int FIELD_SIZE = 3;
    private Random rand = new Random();

    private Integer playerRoll;
    private Integer masterRoll;
    private Integer tipRoll;
    private Cell[] gameField;

    Logic(){
        this.gameField = new Cell[FIELD_SIZE];
        for (int i = 0; i < gameField.length; i++) {
            gameField[i] = new Cell();
        }
    }
    public void roll(){
        int carIndex = rand.nextInt(0,FIELD_SIZE);

        for (int i = 0; i < gameField.length; i++) {
            if (carIndex == i) gameField[i].setValue(CAR_VALUE);
            else gameField[i].setValue(GOAT_VALUE);
        }
    }

    public int masterAnswer(int playerRoll){
        this.playerRoll = playerRoll;
        masterRoll = rand.nextInt(0, FIELD_SIZE);
        while (masterRoll == playerRoll) {
            masterRoll = rand.nextInt(0, FIELD_SIZE);
        }
        return masterRoll;
    }

    public int showTip(int playerRoll, int masterRoll){
        for (int i = 0; i < gameField.length; i++) {
            if (i != playerRoll && i != masterRoll && gameField[i].getValue() == GOAT_VALUE){
                return i;
            }
        }
        return masterRoll;
    }
    public boolean checkEnd(int playerRoll){
        this.playerRoll = playerRoll;
        if (playerRoll < 0 || playerRoll >= gameField.length){
            return false;
        } else {
            return gameField[playerRoll].getValue() == CAR_VALUE;
        }

    }
    public Cell[] getGameField() {
        return gameField;
    }

    public void print(){
        for (int i = 0; i < gameField.length; i++) {
            System.out.println(i + " : " + gameField[i].getValue());
        }

    }
}
