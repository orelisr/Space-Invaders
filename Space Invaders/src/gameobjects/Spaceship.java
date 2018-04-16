package gameobjects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamelogicobjects.BulletCreator;
import gamelogicobjects.Velocity;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import hithandlers.HitListener;
import hithandlers.HitNotifier;

/**
 * @author 204225148
 * @version 3.0
 * @since 2017-06-14
 */
public class Spaceship extends Paddle implements HitNotifier {
    private int timeAtShot;
    private BulletCreator bulletCreatorr;
    private List<HitListener> hitListeners;

    /**
     * This method is the constructor of the spaceship which initialize it's
     * properties.
     *
     * @param rect
     *            is the rectangle that will act as a paddle.
     * @param moveSpeed
     *            The speed the paddle will move;
     * @param leftBorder
     *            is the left border of the paddle.
     * @param rightBorder
     *            is the right border of the paddle.
     * @param gui
     *            is the surface that the paddle will be drawn on.
     * @param colorr
     *            is the color the paddle will have.
     * @param bulletCreator
     *            The bulletCreatot that will create the bullets the alien will
     *            shoot.
     */
    public Spaceship(Rectangle rect, int moveSpeed, double leftBorder, double rightBorder, GUI gui, Color colorr,
            BulletCreator bulletCreator) {
        super(rect, moveSpeed, leftBorder, rightBorder, gui, colorr);
        this.timeAtShot = 0;
        this.bulletCreatorr = bulletCreator;
        this.hitListeners = new LinkedList<HitListener>();
    }

    /**
     * This method makes the spaceship to shoot a bullet.
     */
    public void shoot() {
        int now = (int) (System.currentTimeMillis() % 100000000);
        if (now - this.timeAtShot >= 350) {
            Point middle = new Point(
                    this.getCollisionRectangle().getUpperLeft().getX() + this.getCollisionRectangle().getWidth() / 2,
                    this.getCollisionRectangle().getUpperLeft().getY() - 5);
            Velocity vel = new Velocity(0, -500);
            this.timeAtShot = now;
            this.bulletCreatorr.addBulletToGame(middle, vel, Color.WHITE, 3);
        }
    }

    @Override
    /**
     * The method gets a collisionPoint and the collision's velocity, and
     * returns the velocity the object who had collied the align would have,
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
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) throws RuntimeException {
        this.notifyHit(hitter);
        return new Velocity(0, 0);
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
     * The method notify to all of the spaceship listeners that the block has
     * been hit.
     *
     * @param hitter
     *            The bullet that hit the spaceship.
     */
    public void notifyHit(Ball hitter) {
        // This function notify that 'this' block has hit by a ball.
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(null, hitter);
        }
    }

    @Override
    /**
     * This method is the method notiying to shot by the time that has passesd
     * since the spaceship shot from pushing the space key.
     *
     * @param dt
     *            is the time that has passed.
     */
    public void timePassed(double dt) {
        super.timePassed(dt);
        if (this.getKeyBoard().isPressed(KeyboardSensor.SPACE_KEY)) {
            this.shoot();
        }
    }
}