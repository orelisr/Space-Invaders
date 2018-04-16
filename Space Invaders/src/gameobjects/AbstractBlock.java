package gameobjects;

import biuoop.DrawSurface;
import blockfillers.BlockColorFiller;
import blockfillers.BlockFiller;
import game.GameLevel;
import gamelogicobjects.Collidable;
import gamelogicobjects.Velocity;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import grafics.Sprite;
import hithandlers.HitListener;
import hithandlers.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 204225148
 * @version 3.0
 * @since 2017-06-14
 */
public abstract class AbstractBlock implements Collidable, Sprite, HitNotifier {
    // Properties
    private Rectangle         blockRect;
    private BlockFiller       filler;
    private int               numOfHiits;
    private int               numOfHitsOriginal;
    private int               xtextCords;
    private int               ytextCords;
    private List<HitListener> hitListeners;

    /**
     * The method creates a new block with the Rectangle provided.
     *
     * @param rect
     *            (Rectangle) The Rectangle that will define the block location.
     */
    public AbstractBlock(Rectangle rect) {
        this.blockRect = new Rectangle(rect.getUpperLeft(), rect.getWidth(),
                rect.getHeight());
        this.filler = new BlockColorFiller(Color.BLUE);
        this.numOfHitsOriginal = 0;
        this.numOfHiits = this.numOfHitsOriginal;
        this.xtextCords = 0;
        this.ytextCords = 0;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * This method initialize the block's shape, color, numofthits.
     *
     * @param rect
     *            is the shape.
     * @param col
     *            is the color.
     * @param numOfHits
     *            is the number of hits.
     */
    public AbstractBlock(Rectangle rect, Color col, int numOfHits) {
        this.blockRect = new Rectangle(rect.getUpperLeft(), rect.getWidth(),
                rect.getHeight());
        this.filler = new BlockColorFiller(col);
        this.numOfHiits = numOfHits;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * This method returns the x cords of the lable of the text.
     *
     * @return the x cords.
     */
    public int getXtextCords() {
        return this.xtextCords;
    }

    /**
     * This method returns the y cords of the lable of the text.
     *
     * @return the y cords.
     */
    public int getYtextCords() {
        return this.ytextCords;
    }

    /**
     * This method sets the x cords of the lable of the text.
     *
     * @param num
     *            is the x cords.
     */
    public void setXtextCord(int num) {
        this.xtextCords = num;
    }

    /**
     * This method sets the y cords of the lable of the text.
     *
     * @param num
     *            is the y cords.
     */
    public void setYtextCord(int num) {
        this.ytextCords = num;
    }

    /**
     * This method sets the x coordinate of the block.
     * @param num The x coordinate to be set.
     */
    public void setXCord(int num) {
        Point p = new Point(num, this.blockRect.getUpperLeft().getY());
        this.blockRect = new Rectangle(p, this.blockRect.getWidth(),
                this.blockRect.getHeight());
    }

    /**
     * This method sets the y coordinate of the block.
     * @param num The y coordinate to be set.
     */
    public void setYCord(int num) {
        Point p = new Point(this.blockRect.getUpperLeft().getX(), num);
        this.blockRect = new Rectangle(p, this.blockRect.getWidth(),
                this.blockRect.getHeight());
    }

    /**
     * This method returns the number of hits.
     *
     * @return the number of hits.
     */
    public int getNumOfHits() {
        return this.numOfHiits;
    }

    /**
     * This method sets the number of hits.
     *
     * @param num
     *            is the number of hits.
     */
    public void setNumOfHits(int num) {
        this.numOfHitsOriginal = num;
        this.numOfHiits = this.numOfHitsOriginal;
    }

    /**
     * This method gets a number and reduce the number of hit that require to
     * break the block with the number.
     *
     * @param num
     *            The number to be reduce from the number of hits required to
     *            break the block.
     */
    public void reduceNumOfHits(int num) {
        this.numOfHiits -= num;
    }

    /**
     * Return the filler of this block.
     *
     * @return The filler of this block.
     */
    protected BlockFiller getFiller() {
        return this.filler;
    }

    /**
     * This method gets a blockFiller and set it as the block filler.
     *
     * @param blockFiller
     *            The block filler to b set.
     */
    public void setFiller(BlockFiller blockFiller) {
        this.filler = blockFiller;
    }

    /**
     * This method sets the color of the block.
     *
     * @param coulor
     *            is the color of the block.
     */
    public void setColor(Color coulor) {
        this.filler = new BlockColorFiller(coulor);
    }

    /**
     * The method returns the Rectangle that represents the block location.
     *
     * @return The Rectangle that represents the block location.
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.blockRect.getUpperLeft(),
                this.blockRect.getWidth(), this.blockRect.getHeight());
    }

    /**
     * The method sets the Rectangle that represents the block location.
     * @param rect The Rectangle that represents the block location.
     */
    public void setCollisionRectangle(Rectangle rect) {
        this.blockRect = rect;
    }

    /**
     * The method gets a collisionPoint and the collision's velocity, and
     * returns the velocity the object who had collied the block would have,
     * after the collision.
     *
     * @param hitter
     *            The ball that hit the block.
     * @param collisionPoint
     *            (Point) The collision point.
     * @param currentVelocity
     *            (Velocity) The collision's velocity.
     * @return The new velocity expected after the hit.
     * @throws RuntimeException
     *             The method throws RuntimeException if the collisionPoint is
     *             not on the block frame.
     */
    public abstract Velocity hit(Ball hitter, Point collisionPoint,
            Velocity currentVelocity) throws RuntimeException;

    /**
     * This method draws the block on the surface and fills it with colors in
     * it's borders.
     *
     * @param surface
     *            is the surface that the block will be drawn on.
     */
    public void drawOn(DrawSurface surface) {
        this.filler.drawOn(surface, this.getCollisionRectangle());
    }

    @Override
    /**
     * This method annnounce that a certain period of time has passed.
     *
     * @param dt
     *            (double) The period of time that has passed.
     */
    public void timePassed(double dt) {
    }

    /**
     * This method adds the game procces to the game includind the block by
     * sprite and as a item to collide with.
     *
     * @param game
     *            is the game that the block is added to.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * This method removes the game procces to the game includind the block by
     * sprite and as a item to collide with.
     *
     * @param game
     *            is the game that the block is removed from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    @Override
    /**
     * The method adds a HitListener to the list of HitListeners that will be
     * notified when the block will get hit.
     *
     * @param hl
     *            The HitListener that will be added.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    /**
     * The method removes a HitListener to the list of HitListeners that will be
     * notified when the block will get hit.
     *
     * @param hl
     *            The HitListener that will be removed.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * The method notify to all of the block listeners that the block has been
     * hit.
     *
     * @param hitter
     *            The ball that hit the block.
     */
    public void notifyHit(Ball hitter) {
        // This function notify that 'this' block has hit by a ball.
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(
                this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * The method return the points the user got from the last time the ball was
     * hit.
     *
     * @return The amount of points.
     */
    public abstract int getHitPoints();

    /**
     * This method returns the number of hits required to break the block, to
     * start.
     */
    public void restartNumofHits() {
        this.numOfHiits = this.numOfHitsOriginal;
    }

    /**
     * Returns a deep copy of this block.
     *
     * @return A deep copy of this block.
     */
    public abstract AbstractBlock copy();
}
