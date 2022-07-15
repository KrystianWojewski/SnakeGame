package pl.edu.pjwstk;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.TimerTask;

public class GamePanel extends JPanel implements ActionListener{
    static final int SCREEN_WIDTH = 700;
    static final int SCREEN_HEIGHT = 700;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    int x[] = new int[GAME_UNITS];
    int y[] = new int[GAME_UNITS];
    int controllNumber;
    int DELAY;
    int bodyParts = 5;
    static int applesEaten;
    int appleX;
    int appleY;
    int appleGoldX = SCREEN_WIDTH + (UNIT_SIZE);
    int appleGoldY = 0;
    int applePoisonX = SCREEN_WIDTH + (UNIT_SIZE);
    int applePoisonY = 0;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    GamePanel(int DELAY, int controllNumber) {
        random = new Random();
        this.controllNumber=controllNumber;
        this.DELAY=DELAY;
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        newAppleRed();
        newAppleGold();
        newApplePoison();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            g.setColor(new Color(255, 204, 51));
            g.fillOval(appleGoldX, appleGoldY, UNIT_SIZE, UNIT_SIZE);

            g.setColor(new Color(182, 83, 226));
            g.fillOval(applePoisonX, applePoisonY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(new Color(39, 172, 208));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(Color.cyan);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.green);
            g.setFont(new Font("Algerian", Font.BOLD, 40));
            FontMetrics score = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - score.stringWidth("Score: " + applesEaten)) / 2, SCREEN_HEIGHT);
        } else if (!running) {
            gameOver(g);
            new LeaderBoard();
        }
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void newAppleRed() {
        appleX = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        appleY = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;

    }

    public void newAppleGold() {
        java.util.Timer appleTimer = new java.util.Timer();
        appleTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                appleGoldX = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
                appleGoldY = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
            }
        }, 15000, 5000);
    }

    public void newApplePoison() {
        java.util.Timer appleTimer = new java.util.Timer();
        appleTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                applePoisonX = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
                applePoisonY = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
            }
        }, 20000, 7500);
    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(new File("Jedzenie jabłka2.wav").getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
            bodyParts++;
            applesEaten++;
            newAppleRed();
        }
        if ((x[0] == appleGoldX) && (y[0] == appleGoldY)) {
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(new File("Jabłko Gold.wav").getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
            bodyParts++;
            applesEaten += 10;
            appleGoldX = SCREEN_WIDTH + (UNIT_SIZE);
            appleGoldY = 0;
        }
        if ((x[0] == applePoisonX) && (y[0] == applePoisonY)) {
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(new File("Jabłko speed.wav").getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
            bodyParts++;
            applePoisonX = SCREEN_WIDTH + (UNIT_SIZE);
            applePoisonY = 0;
            if (applesEaten > 5) {
                applesEaten -= 5;
            } else {
                applesEaten = 0;
            }
            DELAY -= 5;
            timer.stop();
            timer = new Timer(DELAY, this);
            timer.start();
        }
    }

    public void checkColisions() {
        //sprawdza kolizje z ciałem
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        //sprawdza kolizje ze ścianą
        if (x[0] < 0) {
            running = false;
        }
        if (y[0] < 0) {
            running = false;
        }
        if (x[0] > SCREEN_WIDTH-UNIT_SIZE) {
            running = false;
        }
        if (y[0] > SCREEN_HEIGHT-UNIT_SIZE) {
            running = false;
        }
        if (!running) {
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(new File("BUM.wav").getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
            timer.stop();
        }
    }
    public void gameOver(Graphics g) {
        g.setColor(Color.green);
        g.setFont(new Font("Algerian", Font.BOLD, 40));
        FontMetrics score = getFontMetrics(g.getFont());
        g.drawString("Your Score: " + applesEaten, (SCREEN_WIDTH - score.stringWidth("Your Score: " + applesEaten)) / 2, (SCREEN_HEIGHT / 2)+UNIT_SIZE);

        g.setColor(Color.green);
        g.setFont(new Font("Algerian", Font.BOLD, 75));
        FontMetrics gameOver = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - gameOver.stringWidth("Game Over")) / 2, (SCREEN_HEIGHT / 2 - UNIT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkColisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (controllNumber==0) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (direction != 'R') {
                            direction = 'L';
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction != 'L') {
                            direction = 'R';
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (direction != 'D') {
                            direction = 'U';
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != 'U') {
                            direction = 'D';
                        }
                        break;
                    default:
                        break;
                }
            }
            if (controllNumber==1) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A:
                        if (direction != 'R') {
                            direction = 'L';
                        }
                        break;
                    case KeyEvent.VK_D:
                        if (direction != 'L') {
                            direction = 'R';
                        }
                        break;
                    case KeyEvent.VK_W:
                        if (direction != 'D') {
                            direction = 'U';
                        }
                        break;
                    case KeyEvent.VK_S:
                        if (direction != 'U') {
                            direction = 'D';
                        }
                        break;
                }
            }
        }
    }
}

