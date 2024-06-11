/**
 * Yehonatan Abramovitch yona132@gmail.com 318800182.
 */
package GeometricPrimitives;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle class.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Color color;

    /**
     * Constructs a rectangle with the specified upper-left point, width, height, and color.
     *
     * @param upperLeft the upper-left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     * @param color     the color of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Calculates and returns a list of intersection points between the rectangle and a given line.
     *
     * @param line the line to check for intersections
     * @return a list of intersection points, possibly empty
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line line1 = new Line(upperLeft, new Point(upperLeft.getX(), upperLeft.getY() + getHeight()));
        Line line2 = new Line(upperLeft, new Point(upperLeft.getX() + getWidth(), upperLeft.getY()));
        Line line3 = new Line(new Point(upperLeft.getX(), upperLeft.getY() + getHeight()),
                new Point((upperLeft.getX() + getWidth()), upperLeft.getY() + getHeight()));
        Line line4 = new Line(new Point(upperLeft.getX() + getWidth(), upperLeft.getY()),
                new Point((upperLeft.getX() + getWidth()), upperLeft.getY() + getHeight()));
        List<Point> list = new ArrayList<>();
        if (line.intersectionWith(line1) != null) {
            list.add(line1.intersectionWith(line));
        }
        if (line.intersectionWith(line2) != null) {
            if (!(list.isEmpty())) {
                boolean x = true;
                for (Point point : list) {
                    if ((line2.intersectionWith(line).equals(point))) {
                        x = false;
                    }
                }
                if (x) {
                    list.add(line2.intersectionWith(line));
                }
            } else {
                list.add(line2.intersectionWith(line));
            }
        }
        if (line.intersectionWith(line3) != null) {
            if (!(list.isEmpty())) {
                boolean x = true;
                for (Point point : list) {
                    if ((line3.intersectionWith(line).equals(point))) {
                        x = false;
                    }
                }
                if (x) {
                    list.add(line3.intersectionWith(line));
                }
            } else {
                list.add(line3.intersectionWith(line));
            }
        }
        if (line.intersectionWith(line4) != null) {
            if (!(list.isEmpty())) {
                boolean x = true;
                for (Point point : list) {
                    if ((line4.intersectionWith(line).equals(point))) {
                        x = false;
                    }
                }
                if (x) {
                    list.add(line4.intersectionWith(line));
                }
            } else {
                list.add(line4.intersectionWith(line));
            }
        }
        return list;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the color of the rectangle.
     *
     * @return the color of the rectangle
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}

