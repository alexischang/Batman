package GameObj;

import gametest9th.utils.Animator;
import gametest9th.utils.Animator.State;
import gametest9th.utils.Global.Direction;

import java.awt.*;

public class Actor extends GameObject {

    private Animator animator;
    private State state;
    private Direction dir;

    public Actor(int x, int y, int type) {
        super(x, y, 32, 32);
        state = State.WALK;
        animator = new Animator(type, state);
        dir = Direction.RIGHT;
    }

    @Override
    public void paintComponent(Graphics g) {
        animator.paint(dir, painter().left(), painter().top(), painter().right(), painter().bottom(), g);
    }

    @Override
    public void update() {
        animator.update();
    }
}
