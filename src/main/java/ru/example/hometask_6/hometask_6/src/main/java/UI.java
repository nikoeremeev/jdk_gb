package ru.example.hometask_6.hometask_6.src.main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Map.*;
public class UI extends JFrame {
    private int WINDOW_POS_X = 100;
    private int WINDOW_POS_Y = 300;
    private int WINDOW_WIDTH = 640;
    private int WINDOW_HEIGHT = 480;
    private String WINDOW_TITLE = "Парадокс Монти Холла";

    private JPanel mainPanel = new JPanel(new GridLayout(5,1));
    private JPanel fieldGrid;

    private JPanel infoGrid = new JPanel(new GridLayout(1,2));

    private JPanel restartGrid = new JPanel();
    private JTextArea resultGrid;

    private JTextArea statisticGrid;
    private JTextArea userRollText;
    private JTextArea masterRollText;

    private JButton[] tempButtons;
    private JButton restartButton;
    private Integer userRoll;

    private Integer masterRoll;

    private Integer tipRoll;

    private Boolean win;
    private Logic logic;

    private Results results;

    private Game game;

    public UI(Game argGame, Logic argLogic, Results argResults){

        this.game = argGame;
        this.logic = argLogic;
        this.results = argResults;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(WINDOW_TITLE);
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);

        init();
        setVisible(true);
    }

    public void init(){
        userRoll = null;
        masterRoll = null;
        tipRoll = null;

        fieldGrid = new JPanel(new GridLayout(1,logic.getGameField().length));
        tempButtons = new MyButton[logic.getGameField().length];

        resultGrid = new JTextArea("Результат: ");;
        userRollText = new JTextArea("Игрок выбрал: ");
        masterRollText = new JTextArea("Ведущий советует: ");

        statisticGrid = new JTextArea("Всего бросков :");

        for (int i = 0; i < logic.getGameField().length; i++) {
            MyButton tempButton = new MyButton(i);
            tempButton.setBackground(Color.LIGHT_GRAY);
            JTextArea tempValue = new JTextArea();
            tempValue.setBackground(Color.BLACK);
            tempButtons[i] = tempButton;
            tempButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userRoll = tempButton.getIndex();
                    if (tipRoll == null){
                        masterRoll = logic.masterAnswer(userRoll);
                    }
                    update();
                }
            });
            fieldGrid.add(tempButton);
        }

        restartButton = new JButton("Перезапуск");
        restartButton.setVisible(false);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.restart();
            }
        });

        restartGrid.add(restartButton);

        infoGrid.add(userRollText);
        infoGrid.add(masterRollText);

        mainPanel.add(fieldGrid);
        mainPanel.add(infoGrid);
        mainPanel.add(resultGrid);
        mainPanel.add(statisticGrid);
        mainPanel.add(restartGrid);

        add(mainPanel);

        StringBuilder statisticText = new StringBuilder();
        statisticText.append(String.format("Всего игр: %d\n", results.getResult().size()));
        statisticText.append(String.format("При неизменном выборе вероятность побед: %f\n",results.getLoyalWinRate()));
        statisticText.append(String.format("При изменении выбора вероятность побед: %f",results.getTraitorWinRate()));
        statisticGrid.setText(statisticText.toString());
    }
    public void update(){
        logic.print();
        for (int i = 0; i < tempButtons.length; i++) {
            tempButtons[i].setBackground(Color.LIGHT_GRAY);
            if (i == userRoll) {
                tempButtons[i].setBackground(Color.GREEN);
            }
            if (i == masterRoll) {
                tempButtons[i].setBackground(Color.BLUE);
            }
        }

        if (tipRoll == null) {
            tipRoll = logic.showTip(userRoll, masterRoll);
            tempButtons[tipRoll].setText("КОЗА");
            tempButtons[tipRoll].setEnabled(false);
        } else {
            if (userRoll != tipRoll){
                for (int i = 0; i < tempButtons.length; i++) {
                    if (logic.getGameField()[i].getValue()) {
                        tempButtons[i].setText("МАШИНА");
                    } else {
                        tempButtons[i].setText("КОЗА");
                    }
                    tempButtons[i].setEnabled(false);
                    tempButtons[i].setBackground(Color.LIGHT_GRAY);
                }
                tempButtons[userRoll].setBackground(Color.GREEN);
                restartButton.setVisible(true);

                if (logic.checkEnd(userRoll)){
                    resultGrid.setText("Игрок победил");
                    win = true;
                } else {
                    resultGrid.setText("Игрок проиграл");
                    win = false;
                }
                results.setResult(results.getResult().size(), new AbstractMap.SimpleEntry<>(userRoll == masterRoll, win));
            }
        }

        userRollText.setText("Игрок выбрал: " + userRoll);
        masterRollText.setText("Ведущий советует: " + masterRoll);
        System.out.println(results.getTraitorWinRate());
        System.out.println(results.getLoyalWinRate());
        StringBuilder statisticText = new StringBuilder();
        statisticText.append(String.format("Всего игр: %d\n", results.getResult().size()));
        statisticText.append(String.format("При неизменном выборе вероятность побед: %f\n",results.getLoyalWinRate()));
        statisticText.append(String.format("При изменении выбора вероятность побед: %f",results.getTraitorWinRate()));
        statisticGrid.setText(statisticText.toString());
    }
}
