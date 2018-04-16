package gamelogicobjects;

import java.awt.Color;

import game.GameLevel;
import gameobjects.Ball;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
/**
 * @author 204225148
 * @version 4.0
 * @since 2017-06-14
 */
public class BulletCreator {
    private GameLevel gameLvl;
    private Rectangle border;
    private GameEnvironment environment;

    /**
     * This method is the constructor of the class initializing
     * the properties of the bullet creator.
     * @param gameLvel is the level of the game.
     * @param borders is the borders of the bullet creator.
     * @param gameEnvironment is the game environment of the bullet
     * creator.
     */
    public BulletCreator(GameLevel gameLvel, Rectangle borders,
            GameEnvironment gameEnvironment) {
        this.gameLvl = gameLvel;
        this.environment = gameEnvironment;
        this.border = borders;
    }

    /**
     * This method adds a bullet creator to the game.
     * @param point is the point of the bullet.
     * @param vel is the velocity of the bullet.
     * @param color is the color of the bullet.
     * @param bulletRadius is the radius of the bullet.
     */
    public void addBulletToGame(Point point, Velocity vel, Color color, int bulletRadius) {
        Ball bullet = new Ball(point, bulletRadius, color);
        bullet.setBorders(this.border.getUpperLeft(), this.border.getWidth(),
                this.border.getHeight());
        bullet.setVelocity(vel);
        bullet.setGameEnvironment(this.environment);
        bullet.addToGame(this.gameLvl);
    }
}