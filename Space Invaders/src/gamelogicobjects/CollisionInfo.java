package gamelogicobjects;

import geometryprimitives.Point;

/**
* @author 204225148
* @version 3.0
* @since 2017-05-29
* */
public class CollisionInfo {
    // Properties
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * This method initialize the collision point and the collision object.
     *
     * @param collPoint
     *            the collision point.
     * @param colObject
     *            the collision object.
     */
    public CollisionInfo(Point collPoint, Collidable colObject) {
        this.collisionPoint = collPoint;
        this.collisionObject = colObject;
    }

    /**
     * The method returns the point at which the collision occurs.
     *
     * @return The point at which the collision occurs.
     */
    public Point collisionPoint() {
        return new Point(this.collisionPoint.getX(),
                this.collisionPoint.getY());
    }

    /**
     * The method returns the collidable object involved in the collision.
     *
     * @return The collidable object involve in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}