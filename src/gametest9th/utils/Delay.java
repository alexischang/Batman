package gametest9th.utils;

import javax.swing.event.ListDataListener;

public class Delay {
    private int count;
    private int countLimit;
    private boolean isPause;
    private boolean isLoop;

    public Delay(int countLimit) {
        this.countLimit = countLimit;
        count = 0;
        isPause = true;
        this.isLoop = false;
    }

    public void loop() {
        isPause = false;
        isLoop = true;
    }

    public void setLimit(int limit) {
        this.countLimit = limit;
    }

    public void stop() {
        isPause = true;
        count = 0;
    }

    public void pause() {
        isPause = true;
    }

    public void play() {
        isPause = false;
        isLoop = false;
    }

    public boolean countToLimit() {
        if (isPause) {
            return false;
        }
        if (count >= countLimit) {
            if (isLoop) {
                count = 0;
            } else {
                stop();
            }
            return true;
        }
        count++;
        return false;
    }
}
