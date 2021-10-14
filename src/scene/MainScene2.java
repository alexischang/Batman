package scene;

import GameObj.Actor;
import gametest9th.utils.CommandSolver;
import gametest9th.utils.Global;

import java.awt.*;


public class MainScene2 extends Scene{

    private Actor actor;


    @Override
    public void sceneBegin() {
        actor = new Actor(Global.SCREEN_X /2 , Global.SCREEN_Y /2 , 4);
    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void update() {
        actor.update();
    }

    @Override
    public void paint(Graphics g) {
        actor.paint(g);
    }

    @Override
    public CommandSolver.MouseCommandListener mouseListener() {
        return null;
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return null;
    }
}
