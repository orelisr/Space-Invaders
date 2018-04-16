package gameobjects;


import grafics.Sprite;
import gamelogicobjects.Collidable;
import gamelogicobjects.Velocity;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import hithandlers.HitNotifier;

/**
 * @author 204225148
 * @version 3.0
 * @since 2017-05-29
 */
public class DeathBlock extends AbstractBlock
        implements Collidable, Sprite, HitNotifier {

    /**
     * This method is the constractor of the death block.
     *
     * @param rect
     *            is the shape of the block.
     */
    public DeathBlock(Rectangle rect) {
        super(rect);
    }

    @Override
    /**
     * Returns a deep copy of this block.
     *
     * @return
     */
    public AbstractBlock copy() {
        DeathBlock block = new DeathBlock(this.getCollisionRectangle().copy());
        block.setFiller(this.getFiller());
        return block;
    }

    @Override
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
        this.notifyHit(hitter);
        return new Velocity(1, 1);
    }

    @Override
    /**
     * This method returns the number of points when hitting this block.
     *
     * @return the number of points.
     */
    public int getHitPoints() {
        return 0;
    }
}