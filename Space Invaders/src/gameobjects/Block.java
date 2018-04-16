package gameobjects;

import grafics.Sprite;
import gamelogicobjects.Collidable;
import gamelogicobjects.Velocity;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import hithandlers.HitNotifier;

import java.awt.Color;

/**
 * @author 204225148
 * @version 3.0
 * @since 2017-05-29
 */
public class Block extends AbstractBlock
        implements Collidable, Sprite, HitNotifier {
    /**
     * The method creates a new block with the Rectangle provided.
     *
     * @param rect
     *            (Rectangle) The Rectangle that will define the block location.
     */
    public Block(Rectangle rect) {
        super(rect);
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
    public Block(Rectangle rect, Color col, int numOfHits) {
        super(rect, col, numOfHits);
    }

    @Override
    /**
     * Returns a deep copy of this block.
     * @return A deep copy of this block.
     */
    public AbstractBlock copy() {
        Block block = new Block(this.getCollisionRectangle().copy());
        block.setFiller(this.getFiller());
        block.setNumOfHits(this.getNumOfHits());
        return block;
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
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) throws RuntimeException {
        Velocity vel;
        boolean collisionOnTop, collisionOnBottom, collisionOnLeft,
                collisionOnRight;

        // Checks where the collision happened.
        collisionOnTop = collisionPoint.isBetweenXcoords(
                this.getCollisionRectangle().getUpperLeft(),
                this.getCollisionRectangle().getUpperRight())
                && (collisionPoint.isBetweenYcoords(
                this.getCollisionRectangle().getUpperLeft(),
                this.getCollisionRectangle().getUpperRight()));
        collisionOnBottom = (collisionPoint.isBetweenXcoords(
                this.getCollisionRectangle().getBottomLeft(),
                this.getCollisionRectangle().getBottomRight()))
                && (collisionPoint.isBetweenYcoords(
                this.getCollisionRectangle().getBottomLeft(),
                this.getCollisionRectangle().getBottomRight()));
        collisionOnLeft = (collisionPoint.isBetweenXcoords(
                this.getCollisionRectangle().getUpperLeft(),
                this.getCollisionRectangle().getBottomLeft()))
                && (collisionPoint.isBetweenYcoords(
                this.getCollisionRectangle().getUpperLeft(),
                this.getCollisionRectangle().getBottomLeft()));
        collisionOnRight = (collisionPoint.isBetweenXcoords(
                this.getCollisionRectangle().getUpperRight(),
                this.getCollisionRectangle().getBottomRight()))
                && (collisionPoint.isBetweenYcoords(
                this.getCollisionRectangle().getUpperRight(),
                this.getCollisionRectangle().getBottomRight()));

        // If the collision has happend on one of the corners.
        if ((collisionOnTop && collisionOnLeft)
                || (collisionOnTop && collisionOnRight)
                || (collisionOnBottom && collisionOnLeft)
                || (collisionOnBottom && collisionOnRight)) {
            vel = new Velocity(-1 * currentVelocity.getDx(),
                    -1 * currentVelocity.getDy());
            // If the collision happened at the top size of the block
        } else if (collisionOnTop) {
            vel = new Velocity(currentVelocity.getDx(),
                    -1 * currentVelocity.getDy());
            // If the collision happened at the bottom size of the block
        } else if (collisionOnBottom) {
            vel = new Velocity(currentVelocity.getDx(),
                    -1 * currentVelocity.getDy());
            // If the collision happened at the left size of the block
        } else if (collisionOnLeft) {
            vel = new Velocity(-1 * currentVelocity.getDx(),
                    currentVelocity.getDy());
            // If the collision happened at the right size of the block
        } else if (collisionOnRight) {
            vel = new Velocity(-1 * currentVelocity.getDx(),
                    currentVelocity.getDy());
        } else {
            // The collision point is not on the block.
            throw new RuntimeException("CollisionPoint is not on block  col = "
                    + collisionPoint.getX() + "," + collisionPoint.getY()
                    + "point "
                    + this.getCollisionRectangle().getUpperLeft().getX() + ","
                    + this.getCollisionRectangle().getUpperLeft().getY());
        }
        // Update of hits
        if (this.getNumOfHits() != 0) {
            this.reduceNumOfHits(1);
        }

        if (currentVelocity.getDy() == 0 || currentVelocity.getDx() == 0) {
            vel = new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }

        this.notifyHit(hitter);
        return vel;
    }

    /**
     * This method returns the points that the user receives when hitting or
     * destroying the block.
     *
     * @return the number of points.
     */
    public int getHitPoints() {
        if (this.getNumOfHits() == 0) {
            return 15;
        } else {
            return 5;
        }
    }
}