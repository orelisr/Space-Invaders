package geometryprimitives;

/**
* @author 204225148
* @version 3.0
* @since 2017-05-29
* */
public class Point implements Comparable<Point> {

    // properties
    private double x;
    private double y;

    /**
     * This method creates a new point with the x and y coordinates provided.
     * @param x (double) The x coordinate of the new point.
     * @param y (double) The y coordinate of the new point.*/
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method gets a point and returns the distance between the point
     * and the current point.
     * The distance is calculated by pytagoras.
     * @param other (Point) A point.
     * @return (double) The distance between 'other' and the current point.*/
    public double distance(Point other) {
        double distance = Math.pow(this.x - other.getX(), 2)
                + Math.pow(this.y - other.getY(), 2);
        return Math.sqrt(distance);
    }

    /**
     * This method gets a point and returns if the point equals to the
     * current point.
     * Two points are equal is both of their x coordinate and y coordinate
     * are equals.
     * @param other (Point) A point.
     * @return (Boolean) The method returns if the two points are equal*/
    public boolean equals(Point other) {
        // If the x coordinate and the y coordinate of other are
        // equals to the x coordinate and the y coordinate of the current point.
        if ((this.x == other.getX()) && (this.y == other.getY())) {
            return true;
        }
        return false;
    }

    /**
     * This method returns the point's x coordinate.
     * @return (double) The point's x coordinate. */
    public double getX() {
        return this.x;
    }

    /**
     * This method returns the point's y coordinate.
     * @return (double) The point's y coordinate. */
    public double getY() {
        return this.y;
    }

    /**
     * This method gets a point and returns if the line that connects
     * 'this' point, to 'other' is horizontal.
     * @param other (Point) A point.
     * @return (boolean) The method returns if the two points sits on
     * the same horizontal line.*/
    public boolean arePointsOnHurizontalLine(Point other) {
        // If the two points' y coordinates are equals
        if (this.y == other.getY()) {
            return true;
        }
        return false;
    }

    /**
     * This method gets a point and returns if the line that connects
     * 'this' point, to 'other' is vertical.
     * @param other (Point) A point.
     * @return (boolean) The method returns if the two points sits on
     * the same vertical line line.*/
    public boolean arePointOnVerticalLine(Point other) {
        // If the two points' x coordinates are equals
        if (this.x == other.getX()) {
            return true;
        }
        return false;
    }

    /**
     * This method gets two point and returns if the x coordinate of 'this'
     * is between the x coordinates of the two points.
     * 'this' point, to 'other' is horizontal.
     * @param start (Point) A point.
     * @param end (Point) A point.
     * @return (boolean) The method returns if the x coordinate of 'this'
     * is between the x coordinates of the two points the method got.*/
    public boolean isBetweenXcoords(Point start, Point end) {
        Point big, small;

        // Checking which point x coordinate is bigger
        if (start.getX() > end.getX()) {
            big = start;
            small = end;
        } else {
            big = end;
            small = start;
        }

        // Checks if the x coordinate of middle is between the two points.
        if ((this.x >= small.getX()) && (this.x <= big.getX())) {
            return true;
        }
        return false;
    }

    /**
     * This method gets two point and returns if the y coordinate of 'this'
     * is between the y coordinates of the two points.
     * 'this' point, to 'other' is horizontal.
     * @param start (Point) A point.
     * @param end (Point) A point.
     * @return (boolean) The method returns if the y coordinate of 'this'
     * is between the y coordinates of the two points the method got.*/
    public boolean isBetweenYcoords(Point start, Point end) {
        Point big, small;

        // Checking which point x coordinate is bigger
        if (start.getY() > end.getY()) {
            big = start;
            small = end;
        } else {
            big = end;
            small = start;
        }

        // Checks if the x coordinate of middle is between the to point.
        if ((this.y >= small.getY()) && (this.y <= big.getY())) {
            return true;
        }
        return false;
    }

    /**
     * The method gets two points, and returns the point
     * that closer to 'this' point.
     * @param one (Point) A Point.
     * @param other (Point) A Point.
     * @return The point that closer to 'this' point.
     */
    public Point closer(Point one, Point other) {
        if (this.distance(one) < this.distance(other)) {
            return new Point(one.getX(), one.getY());
        } else {
            return new Point(other.getX(), other.getY());
        }
    }

    @Override
    public int compareTo(Point p) {
        double thisSize = this.getX() * this.getX() + this.getY() * this.getY();
        double otherPointSize = p.getX() * p.getX() + p.getY() * p.getY();
        if (thisSize > otherPointSize) {
            return 1;
        } else if (thisSize == otherPointSize) {
            return 0;
        } else {
            return -1;
        }
    }
}