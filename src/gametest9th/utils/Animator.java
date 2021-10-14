package gametest9th.utils;

import controllers.SceneController;

import java.awt.*;

public class Animator  {

    public enum State {
        WALK (new int[]{0,1,2,1}, 30), // 切的圖片順序和速度
        RUN(new int[]{0,2}, 20);

        private int[] arr;
        private int speed;

        State(int arr[], int speed) {
            this.arr = arr;
            this.speed = speed;
        }
    }

    private final  Image img;
    private final Delay delay;
    private int count; // 0 1 2
    private State state;
    private final int type;

    public final void setState(State state) {
        this.state = state;
        this.delay.setLimit(state.speed);

    }

    public Animator(int type, State state) { // type是圖片人物的第幾個
        img = SceneController.instance().irc().tryGetImage(new Path().img().actors().actor());
        delay = new Delay(0);
        delay.loop();
        count = 0;
        this.type = type;
        setState(state);
    }


    public void paint(Global.Direction dir, int left, int top, int right, int bottom, Graphics g) {  // 每次畫都需要確認角色新的x y 位置 所以需要傳進來, 而不直接傳Actor是因為我不確定Animator只畫Actor的 可能還有技能的 之後要開父類分開
        g.drawImage(img, left, top, right, bottom, //角色的左上右下也就是要貼的地方 ,而且這邊是帶 painter進來 表示就可以用 painter的 width 和 height 控制圖的大小
                (type % 4 ) * Global.UNIX_X * 3 + Global.UNIX_X * state.arr[count],(type /4) * Global.UNIX_Y * 4 + Global.UNIX_Y * dir.getValue(),
                (type % 4 ) * Global.UNIX_X * 3 + Global.UNIX_X * (state.arr[count] +1),(type /4) * Global.UNIX_Y * 4 + Global.UNIX_Y * (dir.getValue() +1) ,null);

    }


    public void update() {
        System.out.println(delay.countToLimit());
        if (delay.countToLimit()) {
            count = ++count % state.arr.length;
        }
    }
}
