package gameobjects;

import biuoop.DrawSurface;
import game.GameLevel;
import gamelogicobjects.CollisionInfo;
import gamelogicobjects.GameEnvironment;
import gamelogicobjects.Velocity;
import geometryprimitives.Line;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import grafics.Sprite;

import java.awt.Color;

/**
 * @author 204225148
 * @version 3.0
 * @since 2017-05-29
 */
public class Ball implements Sprite {

    // Properties
    private Point center;
    private int radios;
    private Color color;
    private Velocity vel;
    private Rectangle borders;
    private GameEnvironment game;
    private Point destPoint;

    /**
     * This method creates a new Ball with the center Point, radios and the
     * color provided.
     *
     * @param center
     *            (Point) The center point of the new Ball.
     * @param r
     *            (int) The radios of the new Ball
     * @param color
     *            (java.awt.Color) The color of the new Ball.
     */
    public Ball(Point center, int r, Color color) {
        this((int) center.getX(), (int) center.getY(), r, color);
    }

    /**
     * This method creates a new Ball with the center coordinates, radios, and
     * the color provided.
     *
     * @param x
     *            (int) the center's x coordinate of the new Ball.
     * @param y
     *            (int) the center's y coordinate of the new Ball.
     * @param r
     *            (int) The radios of the new Ball
     * @param color
     *            (java.awt.Color) The color of the new Ball.
     */
    public Ball(int x, int y, int r, Color color) {
        this.center = new Point(x, y);
        this.radios = r;
        this.color = color;
        this.vel = null;
        this.game = null;
        this.borders = null;
        // this.upperPoint = null;
        // this.bottomPoint = null;
        // this.destUpper = null;
        // this.destBottom = null;
        this.destPoint = null;
    }

    /**
     * This method returns the Ball's center's x coordinate.
     *
     * @return (int) The Ball's center's x coordinate.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * This method returns the Ball's center's y coordinate.
     *
     * @return (int) The Ball's center's y coordinate.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * This method returns the Ball's radios.
     *
     * @return (int) The Ball's radios.
     */
    public int getSize() {
        return this.radios;
    }

    /**
     * This method returns the center of the ball.
     *
     * @return the center of the ball.
     */
    public Point getCenter() {
        return new Point(this.center.getX(), this.center.getY());
    }

    /**
     * This method returns the Ball's color.
     *
     * @return (java.awt.Color) The Ball's color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * This method sets the coulor of the ball.
     *
     * @param coulor
     *            is the coulor of the ball.
     */
    public void setColor(Color coulor) {
        this.color = coulor;
    }

    /**
     * This method returns the Ball's velocity.
     *
     * @return (Velocity) The Ball's velocity.
     */
    public Velocity getVelocity() {
        return this.vel;
    }

    /**
     * This method gets a velocity and sets it as the ball's velocity.
     *
     * @param v
     *            (Velocity) The Ball's new velocity.
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
        this.calcUpperAndBottompoint();
        this.calcDestpoint();
    }

    /**
     * This method gets a velocity's dx and a dy, makes a velocity from them,
     * and sets the velocity as the ball's velocity.
     *
     * @param dx
     *            (double) The Ball's new velocity's dx.
     * @param dy
     *            (double) The Ball's new velocity's dy.
     */
    public void setVelocity(double dx, double dy) {
        this.setVelocity(new Velocity(dx, dy));
    }

    /**
     * This method sets the ball velocity, due to the given angle and speed.
     *
     * @param angle
     *            is the angle of the velocity.
     * @param speed
     *            is the speed of the velocity.
     */
    public void setVelocityByAngle(double angle, double speed) {
        this.setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
    }

    /**
     * This method sets the ball's moving area borders.
     *
     * @param topLeft
     *            (Point). Will be the top left border, of the ball's moving
     *            area.
     * @param bottomRight
     *            (Point). Will be the bottom right border, of the ball's moving
     *            area.
     * @return (boolean) true if the operation has succeed, false otherwise.
     */
    public boolean setBorders(Point topLeft, Point bottomRight) {
        // If 'topLeft' Point, is at the top left side of 'bottomRight' Point.
        if ((topLeft.getX() < bottomRight.getX()) && (topLeft.getY() < bottomRight.getY())) {
            this.setBorders(new Point(topLeft.getX(), topLeft.getY()), bottomRight.getX() - topLeft.getX(),
                    bottomRight.getY() - topLeft.getY());
            return true;
        }
        return false;
    }

    /**
     * This method is sets the borders of the ball by point width and height.
     *
     * @param topLeft
     *            is the top left point of the rectangel that bordes the ball.
     * @param width
     *            is the width of the rectangle.
     * @param height
     *            is the height of the rectangle.
     */
    public void setBorders(Point topLeft, double width, double height) {
        this.borders = new Rectangle(new Point(topLeft.getX(), topLeft.getY()), width, height);
        this.calcDestpoint();
    }

    /**
     * This method sets the borders of the space for the ball due to the upper
     * left point and the bottom right point, provided as coordinates.
     *
     * @param xtl
     *            (int) Will be the ball's top-left corner border's x
     *            coordinate.
     * @param ytl
     *            (int) Will be the ball's top-left corner border's y
     *            coordinate.
     * @param xbr
     *            (int) Will be the ball's bottom right corner border's x
     *            coordinate.
     * @param ybr
     *            (int) Will be the ball's bottom right corner border's y
     *            coordinate.
     * @return (boolean) true if the operation has succeed, false otherwise.
     */
    public boolean setBorders(int xtl, int ytl, int xbr, int ybr) {
        return this.setBorders(new Point(xtl, ytl), new Point(xbr, ybr));
    }

    /**
     * This method sets the game enviorment of the ball.
     *
     * @param gamee
     *            is the enviorment that the ball will be added to.
     * @throws RuntimeException
     *             exception if trying to initialze the game before velocity and
     *             borders are initialize.
     */
    public void setGameEnvironment(GameEnvironment gamee) throws RuntimeException {
        if (this.vel == null) {
            throw new RuntimeException("The ball's velocity is not initiated");
        }
        if (this.borders == null) {
            throw new RuntimeException("The ball's movment borders are not initiated");
        }
        this.game = gamee;
    }

    /**
     * This method is calculating the ball's upperPoint and the bottomPoint by
     * the velocity. This method is being invoked every time step the ball does.
     */
    private void calcUpperAndBottompoint() {
            // Calc the ball's upperPoint and the bottomPoint by the velocity
            Velocity verticalVel = new Velocity(this.getVelocity().getDy(), -this.getVelocity().getDx());
            double t = Math.pow(verticalVel.getDy(), 2) + Math.pow(verticalVel.getDx(), 2);
            t = Math.sqrt(t);
            t = this.getSize() / t;
    }

    /**
     * This method is calculating when the ball will reach it's border. This
     * method is being invoked every time the ball's border or the ball's
     * velocity changes.
     */
    private void calcDestpoint() {
        // If both of the ball's velocity and borders are initiated
        if (this.borders != null && this.vel != null) {
            // The the ball move vertically
            if (this.getVelocity().getDx() == 0) {
                // If the ball moves up
                if (this.getVelocity().getDy() < 0) {
                    this.destPoint = new Point(this.getX(), this.borders.getUpperLeft().getY());
                    // this.destBottom = new Point(this.bottomPoint.getX(),
                    // this.borders.getUpperLeft().getY());
                    // this.destUpper = new Point(this.upperPoint.getX(),
                    // this.borders.getUpperLeft().getY());
                    // The ball moves down
                } else {
                    this.destPoint = new Point(this.getX(), this.borders.getBottomLeft().getY());
                    // this.destBottom = new Point(this.bottomPoint.getX(),
                    // this.borders.getBottomLeft().getY());
                    // this.destUpper = new Point(this.upperPoint.getX(),
                    // this.borders.getBottomLeft().getY());
                }
                // If the ball moves horizontal.
            } else if (this.getVelocity().getDy() == 0) {
                // If the ball moves towards the right border.
                if (this.getVelocity().getDx() > 0) {
                    this.destPoint = new Point(this.borders.getUpperRight().getX(), this.getY());
                    // The ball moves towards the left border.
                } else {
                    this.destPoint = new Point(this.borders.getUpperLeft().getX(), this.getY());
                }
            } else {
                // Calculate destPoint
                double incline = this.vel.getDy() / this.vel.getDx();
                double constantTerm = this.getY() - this.getX() * incline;
                double x, y;
                Point verticalDest, hurizontalDest;
                // If the ball moves towards the top border.
                if (this.vel.getDy() < 0) {
                    y = this.borders.getUpperLeft().getY();
                    // If the ball moves towards the bottom border.
                } else {
                    y = this.borders.getBottomLeft().getY();
                }
                x = (y - constantTerm) / incline;
                verticalDest = new Point(x, y);

                // Calculates the intersection point with a vertical border.
                // If the ball moves towards the right border.
                if (this.vel.getDx() > 0) {
                    x = this.borders.getUpperRight().getX();
                    // If the ball move towards the left border.
                } else {
                    x = this.borders.getUpperLeft().getX();
                }
                y = incline * x + constantTerm;
                hurizontalDest = new Point(x, y);

                this.destPoint = this.center.closer(hurizontalDest, verticalDest);
            }
        }
    }

    /**
     * This method get a DrawInterface and prints the Ball on it.
     *
     * @param surface
     *            (DrawSurface) The interface the Ball will be printed on.
     * @override
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * This method change the ball's center. The method moves the ball's center
     * to the next place, due to the balls velocity. For the method to work
     * properly, the ball's border, velocity and game environment should all be
     * initiated.
     *
     * @param dt
     *            (double) The has passed since the last invocation, affects the
     *            distance the ball will be proceed.
     */
    public void moveOneStep(double dt) {
        // If the ball's velocity and borders are already initiated.
        if ((this.vel != null) && (this.borders != null) && (this.game != null)) {
            this.calcUpperAndBottompoint();
            Point tempDestPoint;
            CollisionInfo col;

            col = this.game.getClosestCollision(new Line(this.center, this.destPoint));

            // Checks the closest intersection of this.bottomPoint
            if (col != null) {
                tempDestPoint = col.collisionPoint();
            } else {
                tempDestPoint = new Point(this.getX(), this.getY());
            }

            Point destPointForThisTurn = tempDestPoint;

            CollisionInfo colForThisTurn;

            colForThisTurn = col;

            Point afterStep = this.getVelocity().applyToPoint(this.center, dt);
            Line line = new Line(this.center, afterStep);

            // If the collision may happen after this step.
            if (line.distanceFromPoint(destPointForThisTurn) <= this.radios) {
                boolean ishit = true;
                if (line.isVertical()) {
                    ishit = destPointForThisTurn.isBetweenYcoords(this.center, afterStep);
                } else {
                    ishit = destPointForThisTurn.isBetweenXcoords(this.center, afterStep);
                }
                // If the hit had happend
                if (ishit) {
                    // Change the velocity to the velocity after the hit.
                    this.setVelocity(
                            colForThisTurn.collisionObject().hit(this, destPointForThisTurn, this.getVelocity()));
                }
                this.center = this.getVelocity().applyToPoint(this.center, dt);
            } else {
                this.center = this.getVelocity().applyToPoint(this.center, dt);
            }
        }
    }

    /**
     * This method annnounce that a certain period of time has passed.
     *
     * @param dt
     *            (double) The period of time that has passed.
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * This method add the ball to the game procces.
     *
     * @param gamee
     *            is the game process that the ball will be added to.
     */
    public void addToGame(GameLevel gamee) {
        gamee.addSprite(this);
    }

    /**
     * This method removes the game procces to the game including the block by
     * sprite and as a item to collide with.
     *
     * @param gamee
     *            is the game that the ball is removed from.
     */
    public void removeFromGame(GameLevel gamee) {
        gamee.removeSprite(this);
    }
}