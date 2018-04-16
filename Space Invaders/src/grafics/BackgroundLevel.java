package grafics;

import biuoop.DrawSurface;

/**
 * @author 204225148
 * @version 3.0
 * @since 2017-05-29
 */
public class BackgroundLevel implements Sprite {

    // Property
    private SpriteCollection sprites;

    /**
     * This method is the counstractor of the background.
     */
    public BackgroundLevel() {
        sprites = new SpriteCollection();
    }

    @Override
    /**
     * This method draws on the background on the window of the game.
     *
     * @param d
     *            is the draw surface which the game will be drawed on.
     */
    public void drawOn(DrawSurface d) {
        this.sprites.drawAllOn(d);
    }

    @Override
    /**
     * This method announs that time has passed.
     * @param dt (double) The period of time that has passed.
     */
    public void timePassed(double dt) {
         // TODO
    }

    /**
     * This method adds an object to be a sprite which is shown on the board to
     * an array of sprites.
     *
     * @param s
     *            is the sprite to be added to the boared as being a sprite
     *            object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * This method removes a sprite from the background to be shown on the
     * screen.
     *
     * @param s
     *            is the sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * This method returns the number of sprites.
     *
     * @return the number of sprites.
     */
    public int getNumOfSpreites() {
        return this.sprites.getNumOfSpreites();
    }
}