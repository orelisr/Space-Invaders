package grafics;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
* @author 204225148
* @version 3.0
* @since 2017-05-29 */
public class SpriteCollection {
    // Properties
    private List<Sprite> spriteList;

    /**
     * This method initialize the new list of sprites.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }

    /**
     * This method is adding a sprite list of sprites.
     *
     * @param s
     *            is the sprite that added to the list.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * This method removes a sprite from the list of sprites.
     *
     * @param s
     *            is the sprite that removed to the list.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * This method call timePassed() on all sprites.
     * @param dt (double) The period of time that has passed.
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            Sprite s = (Sprite) this.spriteList.get(i);
            s.timePassed(dt);
        }
    }

    /**
     * This method call drawOn(d) on all sprites.
     *
     * @param d
     *            is the surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            Sprite s = (Sprite) this.spriteList.get(i);
            s.drawOn(d);
        }
    }

    /**
     * This method returns the number of sprite to be shown on the screen.
     * @return the size of the array of the sprite.
     */
    public int getNumOfSpreites() {
        return this.spriteList.size();
    }
    /**
     * This method returns the list of sprites.
     * @return the list of sprites.
     */
    public List<Sprite> getSptitesLists() {
        return this.spriteList;
    }
}