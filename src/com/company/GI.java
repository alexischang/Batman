package com.company;

import controllers.SceneController;
import gametest9th.utils.CommandSolver.*;
import gametest9th.utils.CommandSolver.MouseState;
import gametest9th.utils.GameKernel;
import scene.*;

import java.awt.*;
import java.awt.event.MouseEvent;

public class GI implements GameKernel.GameInterface, MouseCommandListener, KeyListener {


    public GI () {
        SceneController.instance().change(new SelectScene()); // new GI 時就把 mainSchene 丟給controller
    }

    //Game Interface
    @Override
    public void paint(Graphics g) {   //這裡只處理畫圖 不要把邏輯放在這裡面
        SceneController.instance().paint(g);
    }

    @Override
    public void update() {
        SceneController.instance().update();

    }

    // MouseCommandListener
    @Override
    public void mouseTrig(MouseEvent e, MouseState state, long trigTime) {
        MouseCommandListener ml = SceneController.instance().mouseListener();
        if (ml != null) {
            ml.mouseTrig(e, state, trigTime);
        }

    }

    //KeyCommandListener
    @Override
    public void keyPressed(int commandCode, long trigTime) {
        KeyListener kl = SceneController.instance().keyListener();
        if (kl != null) {
            kl.keyPressed(commandCode, trigTime);
        }
    }

    @Override
    public void keyReleased(int commandCode, long trigTime) {
        KeyListener kl = SceneController.instance().keyListener();
        if (kl != null) {
            kl.keyReleased(commandCode, trigTime);
        }
    }

    @Override
    public void keyTyped(char c, long trigTime) {
        KeyListener kl = SceneController.instance().keyListener();
        if (kl != null) {
            kl.keyTyped(c, trigTime);
        }
    }
}
