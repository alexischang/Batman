package controllers;

import gametest9th.utils.CommandSolver;
import scene.Scene;

import java.awt.*;

public class SceneController {
    private static SceneController sceneController;
    private Scene currentScence;
    private Scene lastScene;
    private ImageResourceController lastIrc;
    private ImageResourceController currentIrc;


    private SceneController () {
        lastIrc = new ImageResourceController();
        currentIrc = new ImageResourceController();
    }

    public static SceneController instance() {
        if (sceneController == null) {
            sceneController = new SceneController();
        }
        return sceneController;
    }



    public ImageResourceController irc() {
        return currentIrc;
    }

    public void change(Scene scene) {  // 靠這裡控制 scene
        lastScene = currentScence;
//        if (currentScence != null) {
//            currentScence.sceneEnd();
//        }
        ImageResourceController tmp = currentIrc;    // last是空的 把current 與 last交換表示 current又回到空的 last帶著資訊到update時再清空 達到換場景釋放資源的目的
        currentIrc = lastIrc;
        lastIrc = tmp;

        scene.sceneBegin();
        currentScence =scene;
    }



    public void update() {
        if (lastScene != null) {  //  上一圈沒法處理完的事情留待下一圈處理的方式 比如game over 把array清空
            lastScene.sceneEnd();
            lastIrc.clear();
            lastScene = null;
        }
        if (currentScence != null) {
            currentScence.update();
        }

    }

    public void paint(Graphics g) {
        if (currentScence != null) {
            currentScence.paint(g);
        }
    }


    public CommandSolver.MouseCommandListener mouseListener() {
        return currentScence.mouseListener();
    }


    public CommandSolver.KeyListener keyListener() {
        return currentScence.keyListener();
    }
}
