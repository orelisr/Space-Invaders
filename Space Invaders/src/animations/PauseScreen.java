package animations;

import biuoop.DrawSurface;
import grafics.Sprite;

import java.awt.Color;

/**
* @author 204225148
* @version 3.0
* @since 2017-05-29 */
public class PauseScreen implements Animation {
    // Properties.
    private Sprite          backround;

    /**
     * This method is the constractor of the Pause screen.
     * @param sprite
     *            is the background image if the screen.
     */
    public PauseScreen(Sprite sprite) {
        this.backround = sprite;
    }

    /**
     * This method prints one frame of the pause screen.
     * @param d is the method that the screen will be printed on.
     */
    public void doOneFrame(DrawSurface d) {
        this.backround.drawOn(d);
        d.setColor(Color.WHITE);
        d.drawText(40, d.getHeight() / 2, "paused -- press space to continue",
                32);
    }

    /**
     * This method tells if the animation should stop or not.
     * @return a boolean which tells if the animation should stop or not.
     */
    public boolean shouldStop() {
        return false;
    }

    /**
     * The Method restarts the animation.
     */
    public void restartAnimation() {
    }
}