package scene;

import GameObj.BatmanDart;
import Internet.Server;
import controllers.SceneController;
import gametest9th.utils.CommandSolver;
import gametest9th.utils.Global;
import gametest9th.utils.Path;

import java.awt.*;
import java.io.IOException;

import static gametest9th.utils.Global.gameTime;

public class SelectScene extends Scene{


    @Override
    public void sceneBegin() {

        // new Thread(new Runnable() {
        //     @Override
        //     public void run() {
        //             Servers1.getInstance().start();
               
        //     }
        // }).start();

    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void update() {

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.yellow);
        g.drawString("press a: plane game, press b: batman game ", Global.SCREEN_X /2 - 100, Global.SCREEN_Y /2);
    }

    @Override
    public CommandSolver.MouseCommandListener mouseListener() {
        return null;
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return new CommandSolver.KeyListener() {
            @Override
            public void keyPressed(int commandCode, long trigTime) {
                if (commandCode == Global.A) {
                    SceneController.instance().change(new MainScene());
                } else if (commandCode == Global.B) {
                    SceneController.instance().change(new BatmanScene());
                }
            }

            @Override
            public void keyReleased(int commandCode, long trigTime) {

            }

            @Override
            public void keyTyped(char c, long trigTime) {

            }
        };
        }

}
