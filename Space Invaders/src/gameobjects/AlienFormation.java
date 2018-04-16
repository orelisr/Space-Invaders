package gameobjects;

import java.io.IOException;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import biuoop.DrawSurface;
import blockfillers.BlockColorFiller;
import blockfillers.BlockFiller;
import blockfillers.BlockImageFiller;
import gamelogicobjects.BulletCreator;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import grafics.Sprite;
import hithandlers.FormationLisener;
import hithandlers.FormationNotifier;
/**
 * @author 204225148
 * @version 3.0
 * @since 2017-05-29
 */
public class AlienFormation implements Sprite, FormationNotifier {

    // Propreties
    private List<List<Alien>> foramtion;
    private boolean isRight;
    private double leftBorder;
    private double rightBorder;
    private double bottomBoeder;
    private double speed;
    private double originalSpeed;
    private BulletCreator bulletCreatorr;
    private int colomns;
    private boolean isDoneInitiating;
    private int timeAtShot;
    private Random rand;
    private List<FormationLisener> liseners;
    private BlockFiller alienImage;

    /**
     * This method is the constructor of the class initializing the properties
     * of the aliens formation.
     *
     * @param leftBorderr
     *            is the left border of the alien formation.
     * @param rightBorderr
     *            is the right border of the alien formation.
     * @param bottomBorderr
     *            is the bottom border of the alien formation.
     * @param speedd
     *            is the speed of the alien formation.
     * @param bulletCreator
     *            is the bullet creator of the alien formation.
     * @param numberOfColomns
     *            is the number of columns in the alien formation.
     */
    public AlienFormation(double leftBorderr, double rightBorderr, double bottomBorderr, double speedd,
            BulletCreator bulletCreator, int numberOfColomns) {
        this.openAlienImage();

        // Initiate the list of formation listeners
        this.liseners = new ArrayList<>();

        // Sets the board borders
        this.leftBorder = leftBorderr;
        this.rightBorder = rightBorderr;
        this.bottomBoeder = bottomBorderr;

        // Sets the moving speed of the aligns in the formation.
        this.originalSpeed = speedd;
        this.speed = this.originalSpeed;

        // Sets the BulletCreator that will create the bullets
        // the aliens will shoot.
        this.bulletCreatorr = bulletCreator;

        // Set the number of aliens columns.
        this.colomns = numberOfColomns;

        // Create the formation
        this.foramtion = new ArrayList<List<Alien>>();
        for (int i = 0; i < this.colomns; i++) {
            List<Alien> aliColomn = new ArrayList<Alien>();
            this.foramtion.add(aliColomn);
        }

        // Sets the last time one of the aliens shot
        this.timeAtShot = 0;

        // Set the moving to the right directions.
        this.isRight = true;

        this.rand = new Random();

        this.isDoneInitiating = false;
    }

    @Override
    /**
     * This method draws on the surface the formation of the aliens.
     *
     * @param d
     *            is the surface that the formation is drawed on.
     */
    public void drawOn(DrawSurface d) {
        for (List<Alien> aliensColomn : this.foramtion) {
            for (Alien alien : aliensColomn) {
                alien.drawOn(d);
            }
        }
    }

    /**
     * This function is the function that calls the formation to change its
     * speed according to the time that has passed since the game started.
     * @param dt is the time has passed.
     */
    public void timePassed(double dt) {
        if (!this.isDoneInitiating) {
            this.removeEmptyColumns();
            this.isDoneInitiating = true;
        }
        this.move(dt);
        this.shoot();
    }

    /**
     * This function checks if the formation still moving to the left and move
     * the formation to the other side if does.
     * @param dt is the time has passed.
     */
    public void move(double dt) {
        if (this.foramtion.size() != 0) {
            // If the formation moves to the right.
            if (isRight) {
                // If the formation is about to hit the right border
                if (this.foramtion.get(this.foramtion.size() - 1).get(0).getCollisionRectangle().getUpperRight().getX()
                        + this.speed * dt > this.rightBorder) {
                    // Moves the formation down.
                    for (List<Alien> aliensColoms : this.foramtion) {
                        for (Alien alien : aliensColoms) {
                            alien.moveDown(this.foramtion.get(0).get(0).getCollisionRectangle().getHeight());
                        }
                    }
                    // Changes the moving direction.
                    this.isRight = !this.isRight;

                    // Changes the speed
                    this.speed = this.speed * 1.1;

                    // If the formation reached the bottom border
                    Alien ali = this.getLowestAlien();
                    if (ali.getCollisionRectangle().getBottomLeft().getY()
                            + ali.getCollisionRectangle().getHeight() >= this.bottomBoeder) {
                        this.notifyLisenersFormationStopeedMovind();
                    }

                } else {
                    // Moves the formation right.
                    for (List<Alien> aliensColomn : this.foramtion) {
                        for (Alien alien : aliensColomn) {
                            alien.moveRight(dt * this.speed);
                        }
                    }
                }
            } else {
                // If the formation is about to hit the left border
                if (this.foramtion.get(0).get(0).getCollisionRectangle().getUpperLeft().getX()
                        - dt * this.speed < this.leftBorder) {
                    // Moves the formation down.
                    for (List<Alien> aliensColoms : this.foramtion) {
                        for (Alien alien : aliensColoms) {
                            alien.moveDown(this.foramtion.get(0).get(0).getCollisionRectangle().getHeight());
                        }
                    }
                    // Changes the moving direction.
                    this.isRight = !this.isRight;

                    // Changes the speed
                    this.speed = this.speed * 1.1;

                    // If the formation reached the bottom border
                    Alien ali = this.getLowestAlien();
                    if (ali.getCollisionRectangle().getBottomLeft().getY()
                            + ali.getCollisionRectangle().getHeight() >= this.bottomBoeder) {
                        this.notifyLisenersFormationStopeedMovind();
                    }
                } else {
                    // Moves the formation left.
                    for (List<Alien> aliensColomn : this.foramtion) {
                        for (Alien alien : aliensColomn) {
                            alien.moveLeft(dt * this.speed);
                        }
                    }
                }
            }
        }
    }

    /**
     * This method returns the formation to it's initial place and speed.
     */
    public void restartLocationAndSpeed() {
        for (List<Alien> alienColomn : this.foramtion) {
            for (Alien alien : alienColomn) {
                alien.moveToOriginalPlace();
            }
        }
        this.speed = this.originalSpeed;

    }

    /**
     * This method adds an alien to the aliens formation of the game.
     *
     * @param row
     *            is the row that the alien will be added to.
     * @param column
     *            is the column that the alien will be added to.
     * @return ernio is the alien to be returned.
     */
    public Alien addAlien(int row, int column) {
        double startXPosition = (800 - (this.colomns * 50)) / 2;
        Rectangle rect = new Rectangle(new Point(startXPosition + 50 * column, 60 + 50 * row), 40, 30);
        Alien ernio = new Alien(rect, this.bulletCreatorr);
        ernio.setFiller(this.alienImage);
        this.foramtion.get(column).add(ernio);
        Collections.sort(this.foramtion.get(column));
        return ernio;
    }

    /**
     * This method removes an alien from the aliens formation of the game.
     * @param alien is the alien to be removed.
     */
    public void removeAlien(Alien alien) {
        for (List<Alien> alienColomn : this.foramtion) {
            if (alienColomn.contains(alien)) {
                alienColomn.remove(alien);
                break;
            }
        }
        this.removeEmptyColumns();
    }

    /**
     * This method checks if there are empty columns in the formation, and
     * remove them from the formation.
     */
    private void removeEmptyColumns() {

        // Removes empty alien columns.
        List<List<Alien>> colomsToRemove = new ArrayList<List<Alien>>();
        for (List<Alien> alienColomn : this.foramtion) {
            // If the column is empty
            if (alienColomn.size() == 0) {
                colomsToRemove.add(alienColomn);
            }
        }

        for (List<Alien> emptyColomn : colomsToRemove) {
            this.foramtion.remove(emptyColomn);
        }
    }

    /**
     * This method pick one of the aliens columns in the formation, and makes
     * the first alien in this column to shoot.
     */
    private void shoot() {
        int now = (int) (System.currentTimeMillis() % 100000000);
        if (now - this.timeAtShot >= 500) {
            int colToShoot = this.rand.nextInt(this.foramtion.size());
            Alien ali = this.foramtion.get(colToShoot).get(this.foramtion.get(colToShoot).size() - 1);
            ali.shoot();
            this.timeAtShot = now;
        }
    }

    /**
     * This method returns the align in the lowest position in the formation.
     *
     * @return The align in the lowest position in the formation.
     */
    private Alien getLowestAlien() {
        Alien lowest = this.foramtion.get(0).get(this.foramtion.get(0).size() - 1);
        for (List<Alien> alienColomn : this.foramtion) {
            Alien lowestInColumn = alienColomn.get(alienColomn.size() - 1);
            if (lowestInColumn.getCollisionRectangle().getBottomLeft().getY() > lowest.getCollisionRectangle()
                    .getBottomLeft().getY()) {
                lowest = lowestInColumn;
            }
        }
        return lowest;
    }

    @Override
    /**
     * This method Add fl as to the list of listeners to the formation's
     * messages.
     *
     * @param hl
     *            is the listener to be added.
     */
    public void addFormationLisener(FormationLisener fl) {
        this.liseners.add(fl);
    }

    @Override
    /**
     * This method Remove fl from the list of listeners to the formation's
     * messages.
     *
     * @param fl
     *            is the listener to be removed.
     */
    public void removeFormationLisener(FormationLisener fl) {
        this.liseners.remove(fl);
    }

    /**
     * This method notifies all the fornation's listeners that the formation
     * stopped moving.
     */
    public void notifyLisenersFormationStopeedMovind() {
        for (FormationLisener listener : this.liseners) {
            listener.formationStoppedMovigEvent(this);
        }
    }

    /**
     * This method reads the image the represents alien from a file.
     */
    private void openAlienImage() {
        InputStream is = null;
        try {
            String fillerString = "enemy.png";
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(fillerString);
            Image img = ImageIO.read(is);
            this.alienImage = new BlockImageFiller(img);
        } catch (Exception e) {
            this.alienImage = new BlockColorFiller(Color.WHITE);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println("There was a problem with closing the file");
                }
            }
        }
    }
}