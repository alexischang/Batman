package GameObj;

import com.company.GameJpanel;
import gametest9th.utils.CommandSolver;
import gametest9th.utils.GameKernel.*;
import gametest9th.utils.Global;
import GameObj.Rect.*;

import static gametest9th.utils.Global.*;

import java.awt.*;


public abstract class GameObject implements GameInterface {

    private Rect collider;
    private Rect painter;


    public GameObject (int x, int y, int width, int height) { // x y 給 center 座標
//        collider = new Rect(x ,y , width , height);
//        painter = new Rect(x ,y , width , height);
        collider = Rect.genWithCenter(x, y, width, height);
        painter = Rect.genWithCenter(x, y, width, height);


    }

    public GameObject (Rect rect) {
        collider = rect.clone();
        painter = rect.clone();
    }

    public GameObject (int x, int y, int width, int height, int x2, int y2, int width2, int height2) {   // x y 給 center 座標
//        collider = new Rect(x ,y , width , height);
//        painter = new Rect(x2 ,y2 , width2 , height2);
//        painter.setCenter( collider.centerX(), collider.centerY());
        collider = Rect.genWithCenter(x, y, width, height);
        painter = Rect.genWithCenter(x2, y2, width2, height2);

    }

    public GameObject (Rect rect, Rect rect2) {
        collider = rect.clone();
        painter = rect2.clone();
        painter.setCenter(rect.centerX(), rect.centerY());

    }


    public boolean isCollision (GameObject obj) {
        return collider.overlap(obj.collider);
    }

    public final void translate ( int x, int y) {
    collider.translate(x,y);
    painter.translate(x,y);
    }

    public final void translateX ( int x) {
        collider.translateX(x);
        painter.translateX(x);
    }

    public final void translateY (int y) {
        collider.translateY(y);
        painter.translateY(y);
    }

    public boolean touchTop() {
        return collider.top() <= 0;
    }
    public boolean touchLeft() {
        return collider.left() <= 0;
    }
    public boolean touchRight() {
        return collider.right() >= SCREEN_X;
    }
    public boolean touchBottom() {
        return collider.bottom() >= SCREEN_Y;
    }

    public final Rect collider() {
        return collider;
    }  // get collider

    public final Rect painter() {
        return painter;
    }   //  get painter




    @Override
    public final void paint(Graphics g) {
        paintComponent(g);
        if (!IS_DEBUG) {
            //愈早畫的東西在愈下面,所以邊框要比飛機晚畫
            g.setColor(Color.red);
            collider.paint(g);
            g.setColor(Color.green);
            painter.paint(g);
            g.setColor(Color.black); //畫完就還原顏色
        }
    }

    public abstract void paintComponent (Graphics g);

}
