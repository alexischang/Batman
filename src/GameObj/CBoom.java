package GameObj;

import controllers.ImageResourceController;
import controllers.SceneController;
import gametest9th.utils.Delay;
import gametest9th.utils.Path;


import java.awt.*;

public class CBoom extends GameObject{
    private Image img1, img2;
    private Delay delay; // delay 要寫在子彈裡因為所有子彈的 delay 是共用的

    public enum State {
        NORMAL,
        BOOM,
        REMOVED
    }
    private State state;


    public CBoom ( int x, int y){
            super(x, y, 50, 50);
            state = State.NORMAL;
//            img1 = ImageResourceController.instance().tryGetImage(new Path().img().objs().boom());
//            img2 = ImageResourceController.instance().tryGetImage(new Path().img().objs().boom2());
            img1 = SceneController.instance().irc().tryGetImage(new Path().img().objs().boom());
            img2 = SceneController.instance().irc().tryGetImage(new Path().img().objs().boom2());


            delay = new Delay(30); //delay 30楨 ==0.5秒
    }

    public void setState(State state) {
        this.state = state;
    }


    public State getState() {
        return state;
    }

    @Override
    public void paintComponent(Graphics g) {
        switch (state) {
            case NORMAL:
                g.drawImage(img1, painter().left(), painter().top(), painter().width(), painter().height(), null);
                break;
            case BOOM:
                g.drawImage(img2, painter().left(), painter().top(), painter().width(), painter().height(), null);
                break;
        }
    }

    @Override
    public void update() {
        if (state == State.NORMAL) {
            translateY(-8);
        } else if (state == State.BOOM) {
            delay.play();  // 細節是盡可能讓 play 呼叫一次就好了
        }
        if (delay.countToLimit()) { // 如果數到 count的limit的時候
            state = State.REMOVED;
        }

    }
}
