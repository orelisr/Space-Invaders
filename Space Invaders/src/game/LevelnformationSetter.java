package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import grafics.Sprite;
import gameobjects.Block;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

/**
 * @author 204225148
 * @version 4.0
 * @since 2017-06-14
 */
public class LevelnformationSetter implements LevelInformation {

    private int numberOfAliens;
    private List<Point> aliensPalces;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private int numberOfAlienRows;
    private double formationSpeed;

    /**
     * This method is the information setter of the levels. This method
     * initialize the information of the levels.
     */
    public LevelnformationSetter() {
        this.aliensPalces = new ArrayList<Point>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                this.addAlienPossition(new Point(i, j));
            }
        }
        this.setNumberOfAlienRows(10);

        this.setNumberOfAliens(50);
        this.setPaddleSpeed(200);
        this.setPaddleWidth(50);
        this.setLevelName("Best Game Ever 2");
        Rectangle rec = new Rectangle(new Point(0, 0), 800, 600);
        Block block = new gameobjects.Block(rec, Color.BLACK, 1);
        this.setBackground(block);
        this.setFormationSpeed(50);
    }

    @Override
    /**
     * This method returns the number of the aliens.
     *
     * @return the number of the aliens currently in the level.
     */
    public int numberOfAliens() {
        return this.numberOfAliens;
    }

    /**
     * This method sets the number of aliens of the level.
     *
     * @param num
     *            is the number of aliens.
     */
    public void setNumberOfAliens(int num) {
        this.numberOfAliens = num;
    }

    @Override
    /**
     * This method returns the speed of the paddle in the level.
     *
     * @return the speed of the level.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * This method sets the speed of the paddle in the paddle.
     *
     * @param speed
     *            is the speed of the paddle to be set.
     */
    public void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }

    @Override
    /**
     * This method returns the paddle width.
     *
     * @return the paddle width.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * This method sets the paddle width in the evel.
     *
     * @param width
     *            is the width of the paddle to be set.
     */
    public void setPaddleWidth(int width) {
        this.paddleWidth = width;
    }

    @Override
    /**
     * This method returns the name of the level.
     *
     * @return the name of the level.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * This method sets the name of the level.
     *
     * @param name
     *            is the name of the level to be set.
     */
    public void setLevelName(String name) {
        this.levelName = name;
    }

    @Override
    /**
     * This method returns the background of the level.
     *
     * @return the background of the level.
     */
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * This method sets the background of the level.
     *
     * @param backroud
     *            is the background of the level to be set.
     */
    public void setBackground(Sprite backroud) {
        this.background = backroud;
    }

    @Override
    /**
     * This method retuns a list of points represents the aliens positions.
     *
     * @return the list of positions of the aliens by points.
     */
    public List<Point> alienPossitions() {
        return this.aliensPalces;
    }

    /**
     * This method adds a point position to the list of the aliens positions by
     * points.
     *
     * @param possition
     *            is the point of position of the alien.
     */
    public void addAlienPossition(Point possition) {
        this.aliensPalces.add(possition);
    }

    /**
     * This method removes a point of position of an alien.
     *
     * @param possition
     *            is the point of position to be removed.
     */
    public void removeAlienPossition(Point possition) {
        this.aliensPalces.remove(possition);
    }

    @Override
    /**
     * This method returns the number of the alien formation rows.
     *
     * @return the number of alien formation rows.
     */
    public int numberOfAlienRows() {
        return this.numberOfAlienRows;
    }

    /**
     * This method sets the number of rows in the alien formation.
     *
     * @param num
     *            is the number of rows of the alien formation to be set.
     */
    public void setNumberOfAlienRows(int num) {
        this.numberOfAlienRows = num;
    }

    @Override
    /**
     * This method returns the speed of the formation speed.
     *
     * @return the formation speed.
     */
    public double formationSpeed() {
        return this.formationSpeed;
    }

    /**
     * This method sets the speed of the aliens formation.
     *
     * @param speed
     *            is the speed of the formation of aliens to be set.
     */
    public void setFormationSpeed(double speed) {
        this.formationSpeed = speed;
    }
}