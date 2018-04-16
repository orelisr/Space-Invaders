package grafics;

import biuoop.DrawSurface;

/**
 * @author 204225148
 * @version 3.0
 * @since 2017-05-29
 */
public interface Sprite {
    /**
     * This method draw the sprite to the screen.
     *
     * @param d
     *            is the surface that the sprite will be drawed on.
     */
    void drawOn(DrawSurface d);

    /**
     * This method notify the sprite that time has passed.
     *
     * @param dt
     *            (double) The period of time that has passed.
     */
    void timePassed(double dt);
}