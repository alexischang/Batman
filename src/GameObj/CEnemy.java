package GameObj;

import controllers.SceneController;
import gametest9th.utils.CommandSolver;
import gametest9th.utils.Path;

import java.awt.*;
import java.awt.event.MouseEvent;

public class CEnemy extends  GameObject implements  CommandSolver.MouseCommandListener {

;
    private int d;
    private Image img;




    public CEnemy (int x, int y, int d) {
        super(x, y, 50, 50);
        this.d = d;
//        img = ImageResourceController.instance().tryGetImage(new Path().img().actors().enemy());
        img = SceneController.instance().irc().tryGetImage(new Path().img().actors().enemy());

    }

    public boolean move () {
        translateY(4);
        translateX(d);
        return painter().top() <= 600;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, painter().left() , painter().top()  ,painter().width() , painter().height(),null);
    }

    @Override
    public void update() {
        move();
    }  // 沒用到

    @Override
    public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {

    }
}
