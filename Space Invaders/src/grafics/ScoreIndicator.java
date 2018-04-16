package grafics;

import biuoop.DrawSurface;
import hithandlers.Counter;

import java.awt.Color;

/**
 * @author 204225148
 * @version 3.0
 * @since 2017-05-29
 */
public class ScoreIndicator implements Sprite {

    // Properties.
    private Counter countScore;
    private Color   textColor;

    /**
     * This method is the constractor of the score indicator.
     *
     * @param countScor
     *            is the counter itself.
     * @param colorr
     *            is the color of the counter.
     */
    public ScoreIndicator(Counter countScor, Color colorr) {
        this.countScore = countScor;
        this.textColor = colorr;
    }

    @Override
    /**
     * This method draws on the screen the score counter.
     *
     * @param d
     *            is the draw surfae that the counter will be printed on.
     */
    public void drawOn(DrawSurface d) {
        String numToString = Integer.toString(this.countScore.getValue());
        d.setColor(this.textColor);
        d.drawText(350, 25, "Score: " + numToString, 20);
    }

    @Override
    /**
     * This method annnounce that a certain period of time has passed.
     * @param dt (double) The period of time that has passed.
     */
    public void timePassed(double dt) {
    }
}