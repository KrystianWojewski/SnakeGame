package pl.edu.pjwstk;

import javax.swing.*;
import java.util.TimerTask;

public class GameFrame extends JFrame {
    GameFrame(int DELAY, int controllNumber){
        this.add(new GamePanel(DELAY, controllNumber ));
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        disposeFrame();
    }
    public void disposeFrame(){
        java.util.Timer appleTimer = new java.util.Timer();
        appleTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (LeaderBoard.disposeChcek==1){
                    disposeFrameTwo();
                    LeaderBoard.disposeChcek=0;
                }
            }
        }, 0, 50);
    }
    public void disposeFrameTwo(){
        this.dispose();
    }
}

