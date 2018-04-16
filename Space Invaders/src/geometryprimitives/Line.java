package geometryprimitives;

import java.util.List;

/**
 * @author 204225148
 * @version 3.0
 * @since 2017-05-29
 */
public class Line {

    // properties
    private Point start;
    private Point end;
    private double incline;

    // constructors
    /**
     * This method creates a new line with the start and end points provided.
     *
     * @param start
     *            (point) The start point of the new line.
     * @param end
     *            (point) The end point of the new line.
     */
    public Line(Point start, Point end) {
        this(start.getX(), start.getY(), end.getX(), end.getY());
    }

    /**
     * This method creates a new line with the points built from the coordinates
     * provided.
     *
     * @param x1
     *            (double) The new line's start's point x coordinate.
     * @param y1
     *            (double) The new line's start's point y coordinate.
     * @param x2
     *            (double) The new line's end's point x coordinate.
     * @param y2
     *            (double) The new line's start's point x coordinate.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point((int) x1, (int) y1);
        this.end = new Point((int) x2, (int) y2);

        // If the line is a single point or parallel to any axis.
        if ((this.start.getY() == this.end.getY()) || (this.start.getX() == this.end.getX())) {
            this.incline = 0;
        } else {
            this.incline = ((this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX()));
        }
    }

    /**
     * This method returns the line's length.
     *
     * @return (double) The line's length .
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * This method returns the line's middle point.
     *
     * @return (Point) The line's middle point.
     */
    public Point middle() {
        Point middle = new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
        return middle;
    }

    /**
     * This method returns the line's start point.
     *
     * @return (Point) The line's start point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * This method returns the line's end point.
     *
     * @return (Point) The line's end point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * This method returns the line's incline.
     *
     * @return (double) The line's incline.
     */
    public double incline() {
        return this.incline;
    }

    /**
     * This method returns if 'this' line intersects with the 'other' line
     * provided. live provided in case of both of the lines are horizontal.
     *
     * @param other
     *            (Line) A line.
     * @return (boolean) 'this' line intersects with the 'other' line provided.
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    /**
     * This method returns the intersection point of 'this' line and the live
     * provided in case of both of the lines are horizontal.
     *
     * @param other
     *            (Line) A line.
     * @return (Point) If the lines intersects, the method returns the middle
     *         point of the lines' shared part, otherwise, the method returns
     *         null.
     */
    private Point dealWithTwoHorizontalLines(Line other) {
        // If the two lines are not on one horizontal line.
        if (!this.start.arePointsOnHurizontalLine(other.start())) {
            // There is no intersection between the lines.
            return null;
            // If the two lines are not on one horizontal line.
        } else {
            return this.dealWithTwoLinesOnTheSameInfinityLine(other);
        }
    }

    /**
     * This method returns the intersection point of 'this' line and the line
     * provided in case of both of the lines are vertical.
     *
     * @param other
     *            (Line) A line.
     * @return (Point) If the lines intersects, the method returns the middle
     *         point of the lines' shared part, otherwise, the method returns
     *         null.
     */
    private Point dealWithTwoVerticalLines(Line other) {
        // If the two lines are not on one vertical line
        if (!this.start.arePointOnVerticalLine(other.start())) {
            // There is no intersection between the lines.
            return null;
            // The two lines are on one vertical line.
        } else {
            Point startOfSharedPart = null, endOfSharedPart = null;

            // Finds the shared part of the two lines
            // If this.start is on other
            if (this.start.isBetweenYcoords(other.start(), other.end())) {
                startOfSharedPart = this.start;
            }

            // If this.end is on other
            if (this.end().isBetweenYcoords(other.start(), other.end())) {
                if (startOfSharedPart == null) {
                    startOfSharedPart = this.end;
                } else {
                    endOfSharedPart = this.end;
                    Line sharedPart = new Line(startOfSharedPart, endOfSharedPart);
                    return this.start.closer(sharedPart.start, sharedPart.end);
                }
            }

            // If other.start is on this
            if (other.start().isBetweenYcoords(this.start, this.end)) {
                if (startOfSharedPart == null) {
                    startOfSharedPart = other.start();
                } else {
                    endOfSharedPart = other.start();
                    Line sharedPart = new Line(startOfSharedPart, endOfSharedPart);
                    return this.start.closer(sharedPart.start, sharedPart.end);
                }
            }

            if (startOfSharedPart != null) {
                endOfSharedPart = other.end();
                Line sharedPart = new Line(startOfSharedPart, endOfSharedPart);
                return this.start.closer(sharedPart.start, sharedPart.end);
            }

            // There is no intersection between the two lines.
            return null;
        }
    }

    /**
     * This method returns the intersection point of 'this' line and the line
     * provided in case of 'this' line is horizontal and 'other' is vertical.
     *
     * @param vertical
     *            (Line) A line.
     * @return (Point) If the lines intersects, the method returns the
     *         intersection point of the lines, otherwise, the method returns
     *         null.
     */
    private Point dealWithOnehorizontalOneVertical(Line vertical) {
        Point sharedPoint = new Point(vertical.start().getX(), this.start.getY());

        // If the shared point is on 'this' and on 'vertical'
        if (sharedPoint.isBetweenXcoords(this.start, this.end)
                && sharedPoint.isBetweenYcoords(vertical.start(), vertical.end())) {
            return sharedPoint;
        }
        return null;
    }

    /**
     * This method checks is a point is on 'this' line in case of the line is
     * horizontal/ vertical. The point is actually a point that its start point
     * equals to it's end point.
     *
     * @param other
     *            (Point) A point.
     * @return (Point) If the point is on the 'this' line the function returns
     *         the point, and if not the function return null.
     */
    private Point dealWithOneLineOnePoint(Point other) {
        // If 'this' is horizontal
        if (this.isHorizontal()) {
            // If other is on this
            if (other.getY() == this.start.getY() && other.isBetweenXcoords(this.start, this.end)) {
                return new Point(other.getX(), other.getY());
            }
            return null;
            // 'this' is vertical, if 'this' on 'other'.
        } else if (other.getX() == this.start.getX() && other.isBetweenYcoords(this.start, this.end)) {
            return new Point(other.getX(), other.getY());
        }
        return null;
    }

    /**
     * This method checks where is the intersection point of two lines in case
     * two of the lines are on the same infinity line. The method does not work
     * on two vertical lines.
     *
     * @param other
     *            (Point) A point.
     * @return (Point) If the lines intersects, the method returns the middle
     *         point of the lines' shared part, otherwise, the method returns
     *         null.
     */
    private Point dealWithTwoLinesOnTheSameInfinityLine(Line other) {
        Point startOfSharedPart = null, endOfSharedPart = null;

        // Finds the shared part of the two lines
        // If this.start is on other
        if (this.start.isBetweenXcoords(other.start(), other.end())) {
            startOfSharedPart = this.start;
        }

        // If this.end is on other
        if (this.end().isBetweenXcoords(other.start(), other.end())) {
            if (startOfSharedPart == null) {
                startOfSharedPart = this.end;
            } else {
                endOfSharedPart = this.end;
                Line sharedPart = new Line(startOfSharedPart, endOfSharedPart);
                return this.start.closer(sharedPart.start, sharedPart.end);
            }
        }

        // If other.start is on this
        if (other.start().isBetweenXcoords(this.start, this.end)) {
            if (startOfSharedPart == null) {
                startOfSharedPart = other.start();
            } else {
                endOfSharedPart = other.start();
                Line sharedPart = new Line(startOfSharedPart, endOfSharedPart);
                return this.start.closer(sharedPart.start, sharedPart.end);
            }
        }

        if (startOfSharedPart != null) {
            endOfSharedPart = other.end();
            Line sharedPart = new Line(startOfSharedPart, endOfSharedPart);
            return this.start.closer(sharedPart.start, sharedPart.end);
        }

        // There is no intersection between the two lines.
        return null;
    }

    /**
     * This method checks where is the intersection point of two lines in case
     * one line is in the form y = ax + b (a != 0) and the other one is not.
     *
     * @param notNormal
     *            (Line) A line.
     * @return (Point) If the lines intersects, the method returns the
     *         intersection point of the lines, otherwise, the method returns
     *         null.
     */
    private Point dealWithOneNormalAndOneAint(Line notNormal) {
        if (notNormal.isHorizontal()) {
            double aThis = this.incline;
            double bThis = (this.start.getY() - this.start.getX() * aThis);
            double yHorizontal = notNormal.start.getY();
            double x = (yHorizontal - bThis) / aThis;
            Point nPoint = new Point(x, yHorizontal);
            if ((nPoint.isBetweenXcoords(this.start, this.end))
                    && (nPoint.isBetweenXcoords(notNormal.start, notNormal.end))) {
                return nPoint;
            } else {
                return null;
            }
        } else if (notNormal.isVertical()) {
            double aThis = this.incline;
            double bThis = (this.start.getY() - this.start.getX() * aThis);
            double xVertical = notNormal.start.getX();
            double y = aThis * xVertical + bThis;
            Point nPoint = new Point(xVertical, y);
            if ((nPoint.isBetweenXcoords(this.start, this.end))
                    && (nPoint.isBetweenYcoords(notNormal.start, notNormal.end))) {
                return nPoint;
            } else {
                return null;
            }
        } else {
            double aThis = this.incline;
            double bThis = (this.start.getY() - this.start.getX() * aThis);

            double yExpected = aThis * notNormal.start().getX() + bThis;

            // If notNormal (Line that is actually a point) is on
            // 'this' line.
            if (yExpected != notNormal.start().getY()) {
                return null;
            }

            // 'notNormal' is on 'this' line, checking ranges
            Point intersection = new Point(notNormal.start().getX(), notNormal.start().getY());
            if (intersection.isBetweenXcoords(this.start, this.end)) {
                return intersection;
            }

            // There is no intersection between the line.
            return null;
        }
    }

    /**
     * This method returns the intersection point of 'this' line and the line
     * provided.
     *
     * @param other
     *            (Line) A line.
     * @return (Point) The intersection point of 'this' line and the line
     *         provided, if there is no intersection point between the two
     *         lines, the method returns null.
     */
    public Point intersectionWith(Line other) {
        // If both of the lines are not in form of y = ax + b (a != 0)
        if (this.incline == 0 && other.incline == 0) {
            // If both of the lines are horizontal
            if (this.isHorizontal() && other.isHorizontal()) {
                return this.dealWithTwoHorizontalLines(other);
                // If both of the lines are vertical
            } else if (this.isVertical() && other.isVertical()) {
                return this.dealWithTwoVerticalLines(other);
                // If the current line is horizontal and the other
                // is vertical.
            } else if (this.isHorizontal() && other.isVertical()) {
                return this.dealWithOnehorizontalOneVertical(other);
                // If the current line is vertical and the other
                // is horizontal.
            } else if (this.isVertical() && other.isHorizontal()) {
                return other.dealWithOnehorizontalOneVertical(this);
                // If the current line is a dot (implies that other is a line).
            } else if (this.start.equals(this.end)) {
                return other.dealWithOneLineOnePoint(this.start);
                // 'other' is a dot and 'this' is line, or, this and other are
                // both points.
            } else {
                return this.dealWithOneLineOnePoint(other.start());
            }
            // If both of the lines are in form of y = ax + b (a != 0)
        } else if (this.incline != 0 && other.incline != 0) {
            // without checking ranges
            double aThis = this.incline;
            double aOther = other.incline;
            double bThis = (this.start.getY() - this.start.getX() * aThis);
            double bOther = (other.start.getY() - other.start.getX() * aOther);

            // If the two lines have the same incline
            if (aThis == aOther) {
                // If the two lines are parallel
                if (bThis != bOther) {
                    return null;
                }
                // The lines are the same
                return this.dealWithTwoLinesOnTheSameInfinityLine(other);
            }

            // The the lines are not the same line, and there are not parallel.
            double x = (bOther - bThis) / (aThis - aOther);
            double y = aThis * x + bThis;
            Point nPoint = new Point(x, y);
            if ((nPoint.isBetweenXcoords(this.start, this.end)) && (nPoint.isBetweenXcoords(other.start, other.end))) {
                return nPoint;
            } else {
                return null;
            }
            // One of the lines are in a form of y= ax + b (a != 0)
            // and the other is not.
        } else {
            if ((this.incline == 0) && (other.incline != 0)) {
                return other.dealWithOneNormalAndOneAint(this);
            } else {
                return this.dealWithOneNormalAndOneAint(other);
            }
        }
    }

    /**
     * This method returns if the line is equal to the line the function
     * received.
     *
     * @param other
     *            (Line) A line that 'this' line will be compared with.
     * @return (boolean) returns if 'this' line is equal to the line the
     *         function received.
     */
    public boolean equals(Line other) {
        if ((this.start.equals(other.start)) && (this.end.equals(other.end))) {
            return true;
        }
        return false;
    }

    /**
     * This method returns if the line represents a horizontal line.
     *
     * @return (boolean) returns if the line represents a horizontal line.
     */
    public boolean isHorizontal() {
        // If the line represents one point
        if (this.start.equals(this.end)) {
            return false;
        }
        return this.start.arePointsOnHurizontalLine(this.end);
    }

    /**
     * This method returns if the line represents a vertical line.
     *
     * @return (boolean) returns if the line represents a vertical line.
     */
    public boolean isVertical() {
        // If the line represents one point
        if (this.start.equals(this.end)) {
            return false;
        }
        return this.start.arePointOnVerticalLine(this.end);
    }

    /**
     * This method return the closest intersection point to the start of the
     * line by checking distance and return the closest one.
     *
     * @param rect
     *            is the rectangle that the method returns the intersection
     *            point that is the closest to it's upper left point.
     * @return the closest intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionList = rect.intersectionPoints(this);
        if (intersectionList.size() == 0) {
            return null;
        } else {
            Point p = (Point) intersectionList.get(0);
            for (int i = 0; i < intersectionList.size(); i++) {
                Point check = (Point) intersectionList.get(i);
                if (check.distance(this.start()) < p.distance(this.start())) {
                    p = (Point) intersectionList.get(i);
                }
            }
            return p;
        }
    }

    /**
     * The method gets a point and returns the distance between this line and
     * the point.
     *
     * @param p
     *            A point.
     * @return The distance between this line and the point.
     */
    public double distanceFromPoint(Point p) {
        // If this line is hurizontal
        if (this.isHorizontal()) {
            return Math.abs(p.getY() - this.start().getY());
            // If the line is vertical
        } else if (this.isVertical()) {
            return Math.abs(p.getX() - this.start().getX());
        } else {
            double nOfLinearEquation = -this.incline() * this.start().getX() + this.start().getY();
            double upperPartOfFregment = this.incline() * p.getX() - p.getY() + nOfLinearEquation;
            double lowerPartOfFregment = this.incline() * this.incline() + 1;
            lowerPartOfFregment = Math.sqrt(lowerPartOfFregment);
            return Math.abs(upperPartOfFregment / lowerPartOfFregment);
        }
    }
}