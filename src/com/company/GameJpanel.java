package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameJpanel extends JPanel {
    private Image img;
    private int x;
    private int y;
    private int dir;
    public class MyListener extends MouseAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            changeDir(e.getX());
        }
    }


    public GameJpanel() {

        x = 30;
        y = 250;
        dir = 0;

        try {
            img = ImageIO.read(getClass().getResource("/resources/airplane1.png"));
        }catch (IOException ex) {
            Logger.getLogger(GameJpanel.class.getName()).log(Level.SEVERE, (String) null);
        }

        //
        this.addMouseListener(new MyListener());
        this.addMouseMotionListener(new MyListener());

    }
    public void move(){
        if(dir == 0) {
            x += 4;
        } else {
            x -= 4;
        }
    }

    public void changeDir( int x){
        if (this.x > x) {
            dir = 1;
        } else {
            dir = 0;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, x , y , this);

    }
}
