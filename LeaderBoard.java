package pl.edu.pjwstk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LeaderBoard extends JFrame {
    public static int disposeChcek=0;
    LeaderBoard() {
        JFrame leaderBoard = new JFrame("Snake");
        leaderBoard.setLayout(null);
        leaderBoard.setSize(250, 300);
        leaderBoard.getContentPane().setBackground(Color.black);
        JTextField nickField = new JTextField();
        JButton save = new JButton("Save");
        String nick;


        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File scoreFile = new File("C:\\Users\\PeadC\\Desktop\\PJATK\\Semestr 2\\POJ\\Snake\\src\\pl\\edu\\pjwstk\\SnakeScoreBoard.txt");
                if (!scoreFile.exists()){
                    try{
                        scoreFile.createNewFile();
                    } catch (IOException en) {
                        en.printStackTrace();
                    }
                }
                //TODO:
                disposeChcek=1;
                //GamePanel.applesEaten
                leaderBoard.dispose();
                new ControllPanel();
            }
        });
        leaderBoard.setResizable(false);
        nickField.setBounds(65, 90, 120, 30);
        save.setBounds(75, 140, 100, 30);
        leaderBoard.add(nickField);
        leaderBoard.add(save);

        leaderBoard.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        leaderBoard.setVisible(true);
        leaderBoard.setLocationRelativeTo(null);
    }
}

