package GameObj;

import controllers.SceneController;
import gametest9th.utils.Delay;
import gametest9th.utils.Path;

import java.awt.*;

public class BatmanDart extends GameObject{
    private Image img1, img2;
//    private Delay delay; // delay 要寫在子彈裡因為所有子彈的 delay 是共用的



    public BatmanDart ( int x, int y){
        super(x, y, 50, 50);
//            img1 = ImageResourceController.instance().tryGetImage(new Path().img().objs().boom());
//            img2 = ImageResourceController.instance().tryGetImage(new Path().img().objs().boom2());
        img1 = SceneController.instance().irc().tryGetImage(new Path().img().objs().batmanDart());
//        delay = new Delay(30); //delay 30楨 ==0.5秒
    }



    @Override
    public void paintComponent(Graphics g) {

                g.drawImage(img1, painter().left(), painter().top(), painter().width(), painter().height(), null);

    }

    @Override
    public void update() {
//        if (state == CBoom.State.NORMAL) {
            translateY(-8);
//        } else if (state == CBoom.State.BOOM) {
//            delay.play();  // 細節是盡可能讓 play 呼叫一次就好了
//        }
//        if (delay.countToLimit()) { // 如果數到 count的limit的時候
//            state = CBoom.State.REMOVED;
//        }

    }
}
