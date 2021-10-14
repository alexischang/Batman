package GameObj;

import controllers.SceneController;
import gametest9th.utils.Delay;
import gametest9th.utils.Global;
import gametest9th.utils.Path;

import java.awt.*;

public class Joker extends  GameObject {
    private Image img;
    private Global.Direction dir;
    private int d;
    private Delay delay, delay2, delay3, delay4;
    private int secondCount;
    private int changeTime;

    public int getHitTimes() {
        return hitTimes;
    }

    public void setHitTimes(int hitTimes) {
        this.hitTimes = hitTimes;
    }

    private int hitTimes;

    public Joker (int x, int y,int d) {
        super(x, y, 60, 60,x, y, 50, 50);
        dir = Global.Direction.RIGHT;
        //img = ImageResourceController.instance().tryGetImage(new Path().img().actors().airCraft());
        img = SceneController.instance().irc().tryGetImage(new Path().img().actors().joker());
        this.d = d;
        delay = new Delay(60); //delay 30楨 ==0.5秒
        delay2 = new Delay(600);
        delay3 = new Delay(120);
        delay4 = new Delay(600);
        state = State.NORMAL;
        hitTimes = 0;
        secondCount = 0;
    }

    public enum State {
        NORMAL,
        STAR
    }

    public State getState() {
        return state;
    }

    private Joker.State state;

    public void addPassCount(int sec) {
        secondCount = sec;
    }


    public void move(){
        if(dir == Global.Direction.RIGHT && !touchRight()) {
            translateX(4);
        } else if ( dir == Global.Direction.LEFT && !touchLeft()){
            translateX(-4);
        }
    }
    
    public Global.Direction changeDir( int x){
        if (painter().left() > x) {
            dir = Global.Direction.LEFT;
        } else {
            dir = Global.Direction.RIGHT ;
        }
        return dir;
    }



    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, painter().left(), painter().top(), painter().width(), painter().height(),null); // 給左上角座標 和 寬高
        if (state == State.STAR) {
            g.setColor(Color.yellow);
            collider().paint(g);
        }
    }

    @Override
    public void update() {
        //每秒跑UPDATE_TIMES_PER_SEC次
        move();
        delay.loop();
//        delay4.play();
        // if (delay.countToLimit()) { // 如果數到 count的limit的時候 決定移動方向和移動速度
        //     changeDir(Global.random(0,Global.SCREEN_X - collider().width()));
        //     d = Global.random(1,6);
        // }
//        if (delay2.countToLimit() || delay4.countToLimit()) { // 切換無敵狀態
//            state = State.STAR;
//            delay3.play();
//        }
//        if (delay3.countToLimit()) { // 切換普通狀態
//            state = State.NORMAL;
//            delay2.play();
//        }

        //如果在無敵狀態並且 時間差大餘2秒 則變回普通狀態
        if (state == State.STAR && (secondCount - changeTime) <= 2) {
            state = State.NORMAL;
        }

        //每10秒變身一次
            if (secondCount % 10 == 0 && secondCount >= 3) {
            state = State.STAR;
            changeTime = secondCount;
        }



    }

}