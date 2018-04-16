package gamelogicobjects;

import geometryprimitives.Point;

/**
* @author 204225148
* @version 3.0
* @since 2017-05-29
* */
public class Velocity {

    // Properties
    private double dx;
    private double dy;

    /**
     * This method returns a velocity created due to an angle and speed.
     * @param angle (double) The angle, the velocity will have.
     * @param speed (double) The speed, the velocity will have.
     * @return (Velocity) The velocity created to the angle and speed
     * provided.*/
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx, dy;

        angle = Math.toRadians(angle);

        // Make the angle between 0 to 360
        angle = angle % 360;
        // If the angle is a 0 degrees angle.
        if (angle == 0) {
            dx = 0;
            dy = -Math.abs(speed);
        // If the angle is a straight angle.
        } else if (angle ==  90) {
            dx = Math.abs(speed);
            dy = 0;
        // If the angle is flat.
        } else if (angle == 180) {
            dx = 0;
            dy = Math.abs(speed);
        // If the angle is a 0 degrees angle.
        } else if (angle ==  270) {
            dx = -Math.abs(speed);
            dy = 0;
        // if the angle is any other angle
        } else {
            dx = speed;
            dy = dx * Math.tan(angle);
            int normlize = (int) (dy * 10);
            dy = ((double) normlize) / 10;
        }
        return new Velocity(dx, dy);
    }

    /**
     * This method returns the velocity's dx.
     * @return (double) The velocity's dx.*/
    public double getDx() {
        return this.dx;
    }

    /**
     * This method returns the velocity's dy.
     * @return (double) The velocity's dy.*/
    public double getDy() {
        return this.dy;
    }

    /**
     * This method creates a new velocity with the dx and dy provided.
     * @param dx (double) The dx of the new velocity.
     * @param dy (double) The dy of the new velocity .*/
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This method gets a point and returns a new point with
     * position (p.x+dx, p.y+dy).
     * @param p (Point) A point.
     * @param dt (double) The has passed since the last invocation,
     * affects the distance the point will be proceed.
     * @return (Point) a new point with position (p.x+dx, p.y+dy).*/
    public Point applyToPoint(Point p, double dt) {
        // TODO
        return new Point(p.getX() + this.dx * dt, p.getY() + this.dy * dt);
    }
}