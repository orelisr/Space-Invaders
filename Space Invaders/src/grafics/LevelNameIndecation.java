package grafics;

import biuoop.DrawSurface;

import java.awt.Color;

/**
* @author 204225148
* @version 3.0
* @since 2017-05-29 */
public class LevelNameIndecation implements Sprite {
    private Color  textColor;
    private String name;

    /**
     * This method is the counstractor of the LevelNameIndecation.
     *
     * @param levelName
     *            is the level's name.
     * @param color
     *            is the color of the LivesIndicator.
     */
    public LevelNameIndecation(String levelName, Color color) {
        this.name = levelName;
        this.textColor = color;
    }

    @Override
    /**
     * This method draws on the surface the text of the level number.
     *
     * @param is
     *            the draw surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.textColor);
        d.drawText(540, 25, "Level: " + this.name, 20);
    }

    @Override
    /**
     * This method announs that time has passed.
     * @param dt (double) The period of time that has passed.
     */
    public void timePassed(double dt) {
         // TODO
    }
}