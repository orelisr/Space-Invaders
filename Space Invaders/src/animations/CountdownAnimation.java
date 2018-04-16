package animations;

import biuoop.DrawSurface;
import grafics.Sprite;
import grafics.SpriteCollection;
import java.awt.Color;

/**
 * @author 204225148
 * @version 3.0
 * @since 2017-05-29
 */
public class CountdownAnimation implements Animation {
    // Properties.
    private boolean          stop;
    private SpriteCollection gameScreen;
    private double           numOfSeconds;
    private long             endTime;
    private int              currentCount;
    private double           durationPerCount;
    private Sprite backRound;

    /**
     * This method counts down the seconds before a level begins.
     *
     * @param numOfSeconds
     *            is the number of seconds.
     * @param countFrom
     *            is the number that the code counts down from.
     * @param gameScreen
     *            is the collection of sprites that will be shown on the screen.
     * @param backgroung is the sprite of the background.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
            SpriteCollection gameScreen, Sprite backgroung) {
        this.stop = false;
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds;
        this.currentCount = countFrom + 1;
        this.durationPerCount = this.numOfSeconds * 1000 / countFrom;
        this.endTime = -1;
        this.backRound = backgroung;
    }

    /**
     * This method prints one frame of the animation.
     *
     * @param d
     *            is the draw surface that the frame will be printed on.
     */
    public void doOneFrame(DrawSurface d) {
        this.backRound.drawOn(d);
        d.setColor(Color.CYAN);
        this.gameScreen.drawAllOn(d);

        // The the count down has not begun yet
        if (this.endTime == -1) {
            this.endTime = System.currentTimeMillis()
                    + (long) this.durationPerCount;
        }

        d.setColor(Color.CYAN);
        if (this.currentCount == 1) {
            d.drawText(400, 400, "GO!", 40);
        } else {
            d.drawText(400, 400, String.valueOf(this.currentCount - 1) + "...",
                    40);
        }

        // If the current count has over
        if (this.endTime <= System.currentTimeMillis()) {
            this.currentCount--;
            this.endTime = System.currentTimeMillis()
                    + (long) this.durationPerCount;
        }

        // If the count down has over
        if (this.currentCount == 0) {
            this.stop = true;
        }
    }

    /**
     * This method returns if the animation should stop or continue.
     *
     * @return boolan if should stop or continue.
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * The Method restarts the animation.
     */
    public void restartAnimation() {
        this.stop = false;
    }
}