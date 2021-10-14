package scene;

import gametest9th.utils.CommandSolver.*;

import java.awt.*;

public abstract class Scene {
    private boolean isPause;
    public Scene() {
        isPause = false;
    }

    public abstract void sceneBegin();
    public abstract void sceneEnd();

//    public void paint (Graphics g) {
//        if (isPause) {
//            paintPause(g);
//        } else {
//            paintPause(g);
//        }
//    }
//
//    public abstract void paintPlay(Graphics q);
//    public abstract void paintPause(Graphics q);
    public abstract void update();
    public abstract void paint(Graphics g);

//    public void scenceUpdate() {
//        if (!isPause) {
//            update();
//        }
//    }
//
//    public void pause() {
//        isPause = true;
//    }
//    public void play() {
//        isPause = false;
//    }

    public abstract MouseCommandListener mouseListener();
    public abstract KeyListener keyListener();
}
