package gamelogicobjects;

import geometryprimitives.Line;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

import java.util.ArrayList;
import java.util.List;

import gameobjects.AbstractBlock;

/**
 * @author 204225148
 * @version 3.0
 * @since 2017-05-29
 * */
public class GameEnvironment {

    private List<Collidable> collidablesArray;

    /**
     * This method initialize an new list of collidble objects that will take
     * part in the game.
     */
    public GameEnvironment() {
        this.collidablesArray = new ArrayList<Collidable>();
    }

    /**
     *  This method add the given collidable to the environment.
     * @param c is the object that will be added.
     */
    public void addCollidable(Collidable c) {
        this.collidablesArray.add(c);
    }

    /**
     *  This method remove the given collidable from the environment.
     * @param c is the object that will be remived.
     */
    public void removeCollidable(Collidable c) {
        this.collidablesArray.remove(c);
    }

    /**
     * This method Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory is the line that the ball will move on.
     * @return the closest collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo collisionInf = null;
        // Going through all the collidable in collidablesArray.
        for (int j = 0; j < this.collidablesArray.size(); j++) {
            Rectangle collisionRec = ((Collidable) this.collidablesArray.get(j))
                    .getCollisionRectangle();
            Point collisionPoint = trajectory
                    .closestIntersectionToStartOfLine(collisionRec);

            // If there is a collision point between the current collidable in
            // collidablesArray
            if (collisionPoint != null) {
                if (collisionInf == null) {
                    collisionInf = new CollisionInfo(collisionPoint,
                            (Collidable) this.collidablesArray.get(j));
                } else if (trajectory.start()
                        .distance(collisionPoint) < trajectory.start()
                        .distance(collisionInf.collisionPoint())) {
                    collisionInf = new CollisionInfo(collisionPoint,
                            (Collidable) this.collidablesArray.get(j));
                }
            }
        }
        return collisionInf;
    }

    /**
     * This method returns the array of colidible objects.
     * @return the list of colidible objects.
     */
    public List<Collidable> getColidables() {
        return this.collidablesArray;
    }

    /**
     * Returns the game environment to the state is was before
     * the game started.
     */
    public void restartGameInvironment() {
        for (Collidable collidable : this.collidablesArray) {
            if  (collidable instanceof AbstractBlock) {
                ((AbstractBlock) collidable).restartNumofHits();
            }
        }
    }
}