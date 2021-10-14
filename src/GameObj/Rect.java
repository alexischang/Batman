package GameObj;

import java.awt.*;

import static gametest9th.utils.Global.IS_DEBUG;

public class Rect { //這個類存在因為碰撞的範圍不一定跟物件一樣
    private int x;
    private int y;
    private int width;
    private int height;

    public Rect (int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public final int centerX() { //找 centerX,
        return x + width / 2;
    }

    public final int centerY() { //找  centerY
        return y + height / 2;
    }

    public final void setCenter(int centerX, int centerY) { // centerX centerY 為中心點座標
        this.x = centerX - width /2;
        this.y = centerY - height/2;
    }

    public final static Rect genWithCenter(int centerX, int centerY , int width, int height) {  // centerX centerY 為中心點座標
        return new Rect( centerX - width / 2 , centerY - height / 2 , width, height);
    }


    public final Rect translate ( int x, int y) { // translate 移動
        this.x  += x;
        this.y  += y;
        return this;
    }

    public final Rect translateX ( int x) {
        this.x  += x;
        return this;
    }

    public final Rect translateY (int y) {
        this.y  += y;
        return this;
    }

    public final Rect scale (int width, int height) {   // 寬 高
        this.width = width;
        this.height = height;
        return this;
    }

    public final Rect scaleX (int width) {
        this.width = width;
        return this;
    }

    public final Rect scaleY (int height) {
        this.height = height;
        return this;
    }

    public final int left() {
        return x;
    }    // 物體左邊x coor

    public final int right() {
        return x + width;
    }  // 物體右邊x coor

    public final int top() {
        return y;
    } // 物體上邊y coor

    public final int bottom() {
        return y + height;
    }  // 物體下邊y coor
    public final int width() {
        return width;
    }

    public final int height() {
        return height;
    }

    public final boolean overlap(Rect obj) {
        if (right() < obj.left()) {
            return false;
        }
        if (left() > obj.right()) {
            return false;
        }
        if (top() > obj.bottom()) {
            return false;
        }
        if (bottom() < obj.top()) {
            return false;
        }
        return true;
    }

    public final Rect clone() {
        return new Rect( left(), right(), width(), height());
    }

    public final void paint(Graphics g) {
            g.drawRect(x, y, width, height);
    }

    public final void setX(int x){
        this.x = x;
    }
    public final void setY(int y){
        this.y = y;
    }

}
