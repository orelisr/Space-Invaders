package grafics;

import hithandlers.Counter;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * @author 204225148
 * @version 3.0
 * @since 2017-05-29
 */
public class LivesIndicator implements Sprite {

    // Properties.
    private Counter lives;
    private Color   textColor;

    /**
     * This method is the counstractor of the LivesIndicator.
     *
     * @param numOfLives
     *            is the number of lives.
     * @param color
     *            is the color of the LivesIndicator.
     */
    public LivesIndicator(Counter numOfLives, Color color) {
        this.lives = numOfLives;
        this.textColor = color;
    }

    @Override
    /**
     * This method draws on the surface the text of the number of lives.
     *
     * @param is
     *            the draw surface.
     */
    public void drawOn(DrawSurface d) {
        String numToString = Integer.toString(this.lives.getValue());
        d.setColor(this.textColor);
        d.drawText(120, 25, "Lives: " + numToString, 20);
    }

    @Override
    /**
     * This method announs that time has passed.
     * @param dt (double) The period of time that has passed.
     */
    public void timePassed(double dt) {
    }
}