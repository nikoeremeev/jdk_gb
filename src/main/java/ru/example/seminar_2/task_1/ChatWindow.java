package ru.example.seminar_2.task_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindow extends JFrame{

    JButton btnStart = new JButton("Start");
    JButton btnStop = new JButton("Stop");
    JPanel grid = new JPanel();
    Server server;

    ChatWindow(Server server){
        this.server = server;
        setBounds(100, 300, 360, 240);

        grid = new JPanel(new GridLayout(1, 2));
        grid.add(btnStart);
        grid.add(btnStop);

        btnStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                server.start();
            }
            
        });

        btnStop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                server.stop();
            }
            
        });

        add(grid);
        setVisible(true);
    }
}
