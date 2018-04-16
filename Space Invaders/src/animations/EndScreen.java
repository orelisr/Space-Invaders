package animations;

import biuoop.DrawSurface;
import grafics.Sprite;
import hithandlers.Counter;

import java.awt.Color;

/**
 * @author 204225148
 * @version 3.0
 * @since 2017-05-29
 */
public class EndScreen implements Animation {
    // Properties.
    private boolean stop;
    private boolean isWin;
    private Counter score;
    private Sprite backRound;

    /**
     * This function is the counstractor of the end screen.
     *
     * @param isWon
     *            if the user has on or loosed.
     * @param points
     *            is the number of points that the user earned.
     * @param sprite
     *            is the background image if the screen.
     */
    public EndScreen(boolean isWon, Counter points,
            Sprite sprite) {
        this.score = points;
        this.stop = false;
        this.isWin = isWon;
        this.backRound = sprite;
    }

    /**
     * This method shows one frame of the end screen.
     *
     * @param d
     *            is the draw surface that the frame will be printed on.
     */
    public void doOneFrame(DrawSurface d) {
        this.backRound.drawOn(d);
        d.setColor(Color.CYAN);
        // If the user has won
        if (isWin) {
            d.drawText(40, d.getHeight() / 2,
                    "You Win! Your score is" + this.score.getValue(), 32);
        } else {
            d.drawText(40, d.getHeight() / 2,
                    "Game Over! Your score is" + this.score.getValue(), 32);
        }
        d.drawText(40, d.getHeight() / 4 * 3, "Press space to continue", 25);
    }

    /**
     * This method tells if the frame should continue or stop.
     *
     * @return a boolean tells if the frame should continue or not.
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