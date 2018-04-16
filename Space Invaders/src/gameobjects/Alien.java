package gameobjects;

import java.awt.Color;

import blockfillers.BlockColorFiller;
import blockfillers.BlockFiller;
import game.GameLevel;
import gamelogicobjects.BulletCreator;
import gamelogicobjects.Velocity;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

/**
 * @author 204225148
 * @version 3.0
 * @since 2017-06-14
 */
public class Alien extends AbstractBlock implements Comparable<Alien> {
    private BulletCreator bulletCreatorr;
    private Rectangle originalPlace;

    /**
     * This constructor creates a new Alien from a Rectangle and a
     * BulletCreator.
     *
     * @param rect
     *            The place of the alien on the board.
     * @param filer
     *            The image or color of the align.
     * @param bulletCreator
     *            The bulletCreatot that will create the bullets the alien will
     *            shoot.
     */
    public Alien(Rectangle rect, BlockFiller filer, BulletCreator bulletCreator) {
        super(rect);
        this.setFiller(filer);
        this.setNumOfHits(1);
        this.bulletCreatorr = bulletCreator;
        this.originalPlace = this.getCollisionRectangle().copy();
    }

    /**
     * This constructor creates a new Alien from a Rectangle and a
     * BulletCreator.
     *
     * @param rect
     *            The place of the alien on the board.
     * @param bulletCreator
     *            The bulletCreatot that will create the bullets the alien will
     *            shoot.
     */
    public Alien(Rectangle rect, BulletCreator bulletCreator) {
        this(rect, new BlockColorFiller(Color.WHITE), bulletCreator);
    }

    /**
     * This method makes the align to shoot a bullet.
     */
    public void shoot() {
        Point middle = new Point(
                this.getCollisionRectangle().getUpperLeft().getX() + this.getCollisionRectangle().getWidth() / 2,
                this.getCollisionRectangle().getBottomRight().getY() + 6);
        Velocity vel = new Velocity(0, 300);
        this.bulletCreatorr.addBulletToGame(middle, vel, Color.RED, 5);
    }

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
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) throws RuntimeException {
        if (hitter.getColor() != Color.RED) {
            this.reduceNumOfHits(1);
            this.notifyHit(hitter);
            return new Velocity(0, 0);
        }
        return hitter.getVelocity();
    }

    @Override
    /**
     * This method returns the points that the user receive when hitting or
     * destroying the align.
     *
     * @return the number of points.
     */
    public int getHitPoints() {
        return 100;
    }

    /**
     * The method return a hard copy of this align.
     *
     * @return A hard copy of this align.
     */
    @Override
    public AbstractBlock copy() {
        Alien ali = new Alien(this.getCollisionRectangle().copy(), this.bulletCreatorr);
        ali.setFiller(this.getFiller());
        return ali;
    }

    /**
     * This method is the method which cause the align to move left. This method
     * initialize the
     *
     * @param move
     *            (double) The number of pixels to move the align.
     */
    public void moveLeft(double move) {
        Point newLocation = new Point(this.getCollisionRectangle().getUpperLeft().getX() - move,
                this.getCollisionRectangle().getUpperLeft().getY());
        this.setCollisionRectangle(new Rectangle(newLocation, this.getCollisionRectangle().getWidth(),
                this.getCollisionRectangle().getHeight()));
    }

    /**
     * This method is the method which cause the align to move right. This
     * method initialize the
     *
     * @param move
     *            (double) The number of pixels to move the align.
     */
    public void moveRight(double move) {
        Point newLocation = new Point(this.getCollisionRectangle().getUpperLeft().getX() + move,
                this.getCollisionRectangle().getUpperLeft().getY());
        this.setCollisionRectangle(new Rectangle(newLocation, this.getCollisionRectangle().getWidth(),
                this.getCollisionRectangle().getHeight()));
    }

    /**
     * This method is the method which cause the align to move down. This method
     * initialize the
     *
     * @param move
     *            (double) The number of pixels to move the align.
     */
    public void moveDown(double move) {
        Point newLocation = new Point(this.getCollisionRectangle().getUpperLeft().getX(),
                this.getCollisionRectangle().getUpperLeft().getY() + move);
        this.setCollisionRectangle(new Rectangle(newLocation, this.getCollisionRectangle().getWidth(),
                this.getCollisionRectangle().getHeight()));
    }

    /**
     * This method removes to the original place.
     */
    public void moveToOriginalPlace() {
        this.setCollisionRectangle(this.originalPlace);
    }

    @Override
    /**
     * This method returns the upper alien by comparing the points.
     *
     * @return the upper alien.
     */
    public int compareTo(Alien ali) {
        return this.getCollisionRectangle().getUpperLeft().compareTo(ali.getCollisionRectangle().getUpperLeft());
    }

    @Override
    /**
     * This method removes tha alien from the game.
     *
     * @param game
     *            is the game that the alien is removed from.
     */
    public void removeFromGame(GameLevel game) {
        super.removeFromGame(game);
        game.removAlien(this);
    }
}
