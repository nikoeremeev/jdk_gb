package ru.example.seminar_2.task_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindow extends JFrame implements ClientListener {

    JButton btnStart = new JButton("Start");
    JButton btnStop = new JButton("Stop");
    JPanel grid = new JPanel();
    Server server;
    BaseListener listener;

    ChatWindow(BaseListener listener, Server server){
        this.listener = listener;
        this.server = server;
        setBounds(100, 300, 360, 240);

        grid = new JPanel(new GridLayout(1, 2));
        grid.add(btnStart);
        grid.add(btnStop);

        btnStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                server.start();
                event("Нажата кнопка пуск сервер");
            }
            
        });

        btnStop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                server.stop();
                event("Нажата кнопка стоп сервер");
            }
            
        });

        add(grid);
        setVisible(true);
    }

    @Override
    public void event(String msg) {
        listener.generateMessage(msg);
    }


}
