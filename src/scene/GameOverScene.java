package scene;

import controllers.SceneController;
import gametest9th.utils.CommandSolver;
import gametest9th.utils.Global;
import gametest9th.utils.Path;

import java.awt.*;

import static gametest9th.utils.Global.*;

public class GameOverScene extends Scene {
    private Image bg = SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().cloud());

    @Override
    public void sceneBegin() {

    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void update() {

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.drawString("You win!!. Time: " + gameTime + " s", Global.SCREEN_X /2, Global.SCREEN_Y /2);
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
