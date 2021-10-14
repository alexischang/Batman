package GameObj;

import controllers.SceneController;
import gametest9th.utils.CommandSolver;
import gametest9th.utils.Global.Direction;
import gametest9th.utils.Path;

import java.awt.*;
import java.awt.event.MouseEvent;

public class AirCraft extends  GameObject implements  CommandSolver.MouseCommandListener {

    private Image img;
    private Direction dir;


    public AirCraft(int x, int y) {
        super(x, y, 55, 55,x, y, 50, 50);
        dir = Direction.RIGHT;
        //img = ImageResourceController.instance().tryGetImage(new Path().img().actors().airCraft());
        img = SceneController.instance().irc().tryGetImage(new Path().img().actors().airCraft());
    }




    public void move(){
        if(dir == Direction.RIGHT && !touchRight()) {
            translateX(4);
        } else if ( dir == Direction.LEFT && !touchLeft()){
            translateX(-4);
        }
    }

    public void changeDir( int x){
        if (painter().left() > x) {
            dir = Direction.LEFT;
        } else {
            dir = Direction.RIGHT ;
        }
    }



    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, painter().left(), painter().top(), painter().width(), painter().height(),null); // 給左上角座標 和 寬高
    }

    @Override
    public void update() {
        //每秒跑UPDATE_TIMES_PER_SEC次
        move();
    }

    @Override
    public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {  // 抓滑鼠在動的時候鼠標的位置
        if (state == CommandSolver.MouseState.MOVED) {
            changeDir(e.getX());
        }
    }
}
