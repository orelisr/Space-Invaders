package geometryprimitives;
import java.util.ArrayList;
import java.util.List;

/**
* @author 204225148
* @version 3.0
* @since 2017-05-29
* */
public class Rectangle {
    // Properties
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Creates a new Rectangle, from the upper left point, width and
     * height provided.
     * @param upperLeft (Point) Will be the Rectangle's upper left point.
     * @param width (double) Will be the Rectangle's width.
     * @param height (double) Will be the Rectangle's height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = Math.abs(width);
        this.height = Math.abs(height);
    }

    /**
     * This method returns a deep copy of this rectangle.
     *
     * @return A deep copy of this rectangle
     */
    public Rectangle copy() {
        Point p = new Point(this.getUpperLeft().getX(),
                this.getUpperLeft().getY());
        return new Rectangle(p, this.width, this.height);
    }

    /**
     * The method gets a Line, and return the list of intersection
     * points between 'this' Rectangle and the Line provided.
     * @param line (Line) The line it's intersection points with
     * 'this' Rectangle are calculated.
     * @return The list of intersection points between 'this' Rectangle,
     * and the Line provided. If there are no intersection points, the
     * method returns null.
     */
    public List<Point> intersectionPoints(Line line) {
        Line topLine, bottomLine, leftLine, rightLine;
        List<Point> intersectionList = new ArrayList<Point>();

        // Calculating the the rectangle's lines.
        topLine = new Line(this.getUpperLeft(), this.getUpperRight());
        bottomLine = new Line(this.getBottomLeft(), this.getBottomRight());
        leftLine = new Line(this.getUpperLeft(), this.getBottomLeft());
        rightLine = new Line(this.getUpperRight(), this.getBottomRight());
        // Adds the intersection point of 'line' with the rectangle's lines,
        // to 'intersectionList'.
        if (line.isIntersecting(topLine)) {
            intersectionList.add(line.intersectionWith(topLine));
        }

        if (line.isIntersecting(bottomLine)) {
            intersectionList.add(line.intersectionWith(bottomLine));
        }

        if (line.isIntersecting(leftLine)) {
            intersectionList.add(line.intersectionWith(leftLine));
        }

        if (line.isIntersecting(rightLine)) {
            intersectionList.add(line.intersectionWith(rightLine));
        }
        return intersectionList;
    }

    /**
     * The method returns the Rectangle's width.
     * @return The Rectangle's width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * The method returns the Rectangle's height.
     * @return The Rectangle's height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * The method returns the Rectangle's upper left point.
     * @return The Rectangle's upper left point.
     */
    public Point getUpperLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY());
    }

    /**
     * The method returns the Rectangle's upper right point.
     * @return The Rectangle's upper right point.
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + this.getWidth(),
                this.upperLeft.getY());
    }

    /**
     * The method returns the Rectangle's bottom left point.
     * @return The Rectangle's bottom left point.
     */
    public Point getBottomLeft() {
        return new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.getHeight());
    }

    /**
     * The method returns the Rectangle's bottom right point.
     * @return The Rectangle's bottom right point.
     */
    public Point getBottomRight() {
        return new Point(this.upperLeft.getX() + this.getWidth(),
                this.upperLeft.getY() + this.getHeight());
    }
}