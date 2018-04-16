package game;

import grafics.Sprite;
import geometryprimitives.Point;

import java.util.List;

/**
 * @author 204225148
 * @version 3.0
 * @since 2017-05-29
 */
public interface LevelInformation {
    /**
     * This method return the number off aliens at the level.
     *
     * @return the number of aliens.
     */
    int numberOfAliens();

    /**
     * This method returns the speed of the paddle in the level.
     *
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * This method returns the width of the paddle.
     *
     * @return the width of the paddle.
     */
    int paddleWidth();

    /**
     * This method return the name of the level.
     *
     * @return the name of the level.
     */
    String levelName();

    /**
     * This method produce the background of the level that makes it special.
     *
     * @return a whole sprite to show on screen which is the background.
     */
    Sprite getBackground();

    /**
     * This method returns the list of aliens positions within the
     * aliens formation.
     *
     * @return the list of blocks.
     */
    List<Point> alienPossitions();

    /**
     * This method returns the number of rows.
     * @return the number of rows.
     */
    int numberOfAlienRows();
    /**
     * This method returns the formation speed.
     * @return the formation speed.
     */
    double formationSpeed();
}