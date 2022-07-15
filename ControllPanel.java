package pl.edu.pjwstk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class ControllPanel extends JFrame {
    static int DELAY = 75;
    static int controllNumber = 0;
    static int ile = 0;

    ControllPanel() {
        JFrame snakePanel = new JFrame("Snake");
        snakePanel.setLayout(null);
        snakePanel.setSize(250, 300);
        snakePanel.getContentPane().setBackground(Color.black);
        JButton startGame = new JButton("Start Game");
        JButton mode = new JButton("Mode");
        JButton controlls = new JButton("Controlls");
        JButton quit = new JButton("Quit Game");
        JButton leaderBoard = new JButton("Score Board");

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snakePanel.dispose();
                new GameFrame(DELAY, controllNumber);
            }
        });

        mode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame modePanel = new JFrame("Mode");
                modePanel.setLayout(null);
                modePanel.setSize(250, 300);
                modePanel.getContentPane().setBackground(Color.black);
                JButton hard = new JButton("Hard");
                JButton medium = new JButton("Medium");
                JButton easy = new JButton("Easy");

                hard.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DELAY = 50;
                        modePanel.dispose();
                    }
                });
                medium.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DELAY = 75;
                        modePanel.dispose();
                    }
                });
                easy.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DELAY = 100;
                        modePanel.dispose();
                    }
                });

                hard.setBounds(75, 60, 100, 30);
                medium.setBounds(75, 110, 100, 30);
                easy.setBounds(75, 160, 100, 30);
                modePanel.add(hard);
                modePanel.add(medium);
                modePanel.add(easy);

                modePanel.setResizable(false);
                modePanel.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                modePanel.setVisible(true);
                modePanel.setLocationRelativeTo(null);
            }
        });

        controlls.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame controllsPanel = new JFrame("Mode");
                controllsPanel.setLayout(null);
                controllsPanel.setSize(250, 300);
                controllsPanel.getContentPane().setBackground(Color.black);
                JButton wsad = new JButton("WSAD");
                JButton strzalki = new JButton("Strzałki");

                wsad.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controllNumber = 1;
                        controllsPanel.dispose();
                    }
                });
                strzalki.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controllNumber = 0;
                        controllsPanel.dispose();
                    }
                });
                wsad.setBounds(75, 95, 100, 30);
                strzalki.setBounds(75, 145, 100, 30);
                controllsPanel.add(wsad);
                controllsPanel.add(strzalki);

                controllsPanel.setResizable(false);
                controllsPanel.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                controllsPanel.setVisible(true);
                controllsPanel.setLocationRelativeTo(null);
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        leaderBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame leaderBoardFrame = new JFrame("Score Board");
                JLabel readFile = new JLabel();
                leaderBoardFrame.setLayout(null);
                leaderBoardFrame.setSize(250, 300);
                leaderBoardFrame.getContentPane().setBackground(Color.black);
                JButton back = new JButton("Back");

                Scanner scanner = null;
                String[] tab = new String[10];
                try {
                    scanner = new Scanner(new File("SnakeScoreBoard.txt"));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                for (int i = 0; i < 10; i++) {
                    try {
                        tab[i] = scanner.nextLine();
                    } catch (Exception exe) {
                        tab[i] = "";
                    }
                }
                readFile.setText("<html>" + tab[ile] + "<br/>");
                while (ile < tab.length - 1) {
                    ile++;
                    readFile.setText(readFile.getText() + (tab[ile] + "<br/"));
                }

                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ile = 0;
                        leaderBoardFrame.dispose();
                    }
                });
                back.setBounds(75, 190, 100, 30);
                readFile.setBounds(25, 10, 200, 170);

                leaderBoardFrame.setResizable(false);
                leaderBoardFrame.add(readFile);
                leaderBoardFrame.add(back);
                leaderBoardFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                leaderBoardFrame.setVisible(true);
                leaderBoardFrame.setLocationRelativeTo(null);
            }
        });

        startGame.setBounds(60, 30, 120, 30);
        mode.setBounds(60, 70, 120, 30);
        controlls.setBounds(60, 110, 120, 30);
        leaderBoard.setBounds(60, 150, 120, 30);
        quit.setBounds(60, 190, 120, 30);

        snakePanel.add(startGame);
        snakePanel.add(mode);
        snakePanel.add(controlls);
        snakePanel.add(leaderBoard);
        snakePanel.add(quit);

        snakePanel.setResizable(false);
        snakePanel.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        snakePanel.setVisible(true);
        snakePanel.setLocationRelativeTo(null);
    }
}