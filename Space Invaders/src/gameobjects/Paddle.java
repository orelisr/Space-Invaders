package gameobjects;

import grafics.Sprite;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameLevel;
import gamelogicobjects.Collidable;
import gamelogicobjects.Velocity;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

import java.awt.Color;

/**
 * @author 204225148
 * @version 3.0
 * @since 2017-05-29
 */
public class Paddle implements Sprite, Collidable {

    // Properties
    private biuoop.KeyboardSensor keyboard;
    private Rectangle             paddleRect;
    private Color        color;
    private double                leftBorder;
    private double                rightBorder;
    private int                   speed;

    /**
     * This method is the constructor of the paddle which initialize it's
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
     */
    public Paddle(Rectangle rect, int moveSpeed, double leftBorder,
            double rightBorder, GUI gui, Color colorr) {
        this.paddleRect = new Rectangle(rect.getUpperLeft(), rect.getWidth(),
                rect.getHeight());
        this.color = colorr;
        this.keyboard = gui.getKeyboardSensor();
        this.speed = moveSpeed;

        if (leftBorder < rightBorder) {
            this.setBorders(leftBorder, rightBorder);
        } else {
            this.setBorders(rightBorder, leftBorder);
        }
    }

    /**
     * This method returns the color of the paddle to the programmer.
     *
     * @return the color of the paddle.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * This method gets a color and sets it to the paddles color.
     *
     * @param cuolor
     *            is the color to be set as the paddle's color.
     */
    public void setColor(Color cuolor) {
        this.color = cuolor;
    }

    /**
     * This method returns the left border of the paddle.
     *
     * @return the left border of the paddle.
     */
    private double getLeftBorder() {
        return this.leftBorder;
    }

    /**
     * This method returns the right border of the paddle.
     *
     * @return the right border of the paddle.
     */
    private double getRightBorder() {
        return this.rightBorder;
    }

    /**
     * This method initialize the left and right cordinates of the paddle.
     *
     * @param left
     *            is the left border of the paddle.
     * @param right
     *            is the right border of the paddle.
     */
    private void setBorders(double left, double right) {
        this.leftBorder = left;
        this.rightBorder = right;
    }

    /**
     * This method is the method which cause the paddle to move left when the
     * gamer pushes the left key of the keyboard. This method initialize the
     * paddle's x and y cordinates acording to the movement of the left arrow.
     *
     * @param dt
     *            (double) The time has passed since the last invocation.
     */
    public void moveLeft(double dt) {
        Point newLocation = new Point(
                this.getCollisionRectangle().getUpperLeft().getX()
                        - this.speed * dt,
                this.getCollisionRectangle().getUpperLeft().getY());

        // If the paddle crossed the left border
        if (newLocation.getX() < this.getLeftBorder()) {
            newLocation = new Point(this.getLeftBorder(),
                    this.getCollisionRectangle().getUpperLeft().getY());
        }

        this.paddleRect = new Rectangle(newLocation,
                this.getCollisionRectangle().getWidth(),
                this.getCollisionRectangle().getHeight());
    }

    /**
     * This method is the method which cause the paddle to move right when the
     * gamer pushes the right key of the keyboard. This method initialize the
     * paddle's x and y cordinates acording
     *
     * @param dt
     *            (double) The time has passed since the last invocation.
     */
    public void moveRight(double dt) {
        Point newLocation = new Point(
                this.getCollisionRectangle().getUpperLeft().getX()
                        + this.speed * dt,
                this.getCollisionRectangle().getUpperLeft().getY());

        // If the paddle crossed the right borders
        if (newLocation.getX() > this.rightBorder) {
            newLocation = new Point(this.getRightBorder(),
                    this.getCollisionRectangle().getUpperLeft().getY());
        }

        this.paddleRect = new Rectangle(newLocation,
                this.getCollisionRectangle().getWidth(),
                this.getCollisionRectangle().getHeight());
    }

    /**
     * The method moves the paddle, to the x coordinate it gets.
     *
     * @param x
     *            The paddle will move to this x coordinate.
     */
    public void moveTo(int x) {
        // If the place to move the paddle, doesn't crosses the paddle's
        // borders.
        if (x < this.rightBorder && x > this.leftBorder) {
            // Moves the paddle to the new locaion.
            Point newLocation = new Point(x,
                    this.getCollisionRectangle().getUpperLeft().getY());
            this.paddleRect = new Rectangle(newLocation,
                    this.getCollisionRectangle().getWidth(),
                    this.getCollisionRectangle().getHeight());
        }
    }

    /**
     * This method announce that a certian period of time has passed.
     *
     * @param dt
     *            (double) The period of time that has passed.
     */
    public void timePassed(double dt) {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        }
    }

    /**
     * this method is the method that draw on the surface the paddle and fills
     * it to the borders with its color.
     *
     * @param d
     *            (DrawSurface) The interface the paddle will draw himself on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(
                (int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
    }

    /**
     * This method tie between a collidble item and between a rectangle, from
     * this function it is possible to get to the properties of the rectangle.
     *
     * @return a new rectangle which is used to be the paddle of the game.
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.paddleRect.getUpperLeft(),
                this.paddleRect.getWidth(), this.paddleRect.getHeight());
    }

    /**
     * This method announce the block or a paddle is hitting it.
     *
     * @param hitter
     *            The ball that hit the paddle.
     * @param collisionPoint
     *            is the point of the possible collision between the paddle and
     *            the ball.
     * @param currentVelocity
     *            is the current velocitiy of the ball when hitting the paddle.
     * @return The new velocity the ball will have after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
            Velocity currentVelocity) {
        boolean collisionOnTop, collisionOnBottom, collisionOnLeft,
                collisionOnRight;
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
        // If the hit has occurred at the top or the bottom of the paddle
        if (collisionOnTop || collisionOnBottom) {
            double collisionOnPaddle = collisionPoint.getX()
                    - this.getCollisionRectangle().getUpperLeft().getX();
            double width = this.getCollisionRectangle().getWidth();
            // If the hit has occurred at the paddle's far left
            if (width / 5 >= collisionOnPaddle) {
                return Velocity.fromAngleAndSpeed(10, -300);
                // If the hit has occurred at the paddle's middle left
            } else if ((width / 5) * 2 >= collisionOnPaddle) {
                return Velocity.fromAngleAndSpeed(40, -200);
                // If the hit has occurred at the paddle's far right
            } else if ((width / 5) * 4 <= collisionOnPaddle) {
                return Velocity.fromAngleAndSpeed(170, 300);
                // If the hit has occurred at the paddle's middle right
            } else if ((width / 5) * 3 <= collisionOnPaddle) {
                return Velocity.fromAngleAndSpeed(140, 200);

                // If the hit has occurred at the paddle's middle
            } else {
                if (currentVelocity.getDx() == 0
                        || currentVelocity.getDy() == 0) {
                    return new Velocity(-currentVelocity.getDx(),
                            -currentVelocity.getDy());
                } else {
                    return new Velocity(currentVelocity.getDx(),
                            -currentVelocity.getDy());
                }
            }
        } else if (collisionOnLeft) {
            return Velocity.fromAngleAndSpeed(10, -40);
            // If the collision has occurred on the left side of the padele
        } else if (collisionOnRight) {
            return Velocity.fromAngleAndSpeed(170, 40);
        }
        return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
    }

    /**
     * This method adds the paddle to the game.
     *
     * @param g
     *            - is the game that the paddle is added to.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    /**
     * This method retruns the classe's keybored key.
     * @return the keybored key.
     */
    protected KeyboardSensor getKeyBoard() {
        return this.keyboard;
    }
}