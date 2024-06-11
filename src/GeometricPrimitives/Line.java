/**
 * Yehonatan Abramovitch yona132@gmail.com 318800182.
 */

package GeometricPrimitives;

/**
 * Line class.
 */
public class Line {
    private Point start;
    private Point end;
    private double x1;
    private double y1;
    private double x2;
    private double y2;


// Constructors

    /**
     * Constructs a line segment from two given points.
     *
     * @param start the starting point of the line
     * @param end   the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a line segment from given coordinates.
     *
     * @param x1 the x-coordinate of the starting point
     * @param y1 the y-coordinate of the starting point
     * @param x2 the x-coordinate of the ending point
     * @param y2 the y-coordinate of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        if (x1 < x2) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        } else {
            this.start = new Point(x2, y2);
            this.end = new Point(x1, y1);
        }
    }

    /**
     * Returns the length of the line segment.
     *
     * @return the length of the line segment
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * Returns the middle point of the line segment.
     *
     * @return the middle point of the line segment
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * Returns the starting point of the line segment.
     *
     * @return the starting point of the line segment
     */
    public Point start() {
        return start;
    }

    /**
     * Returns the ending point of the line segment.
     *
     * @return the ending point of the line segment
     */
    public Point end() {
        return end;
    }

    /**
     * Checks if this line segment intersects with another line segment.
     *
     * @param other the other line segment to check intersection with
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (((end.getY() - start.getY()) / (end.getX() - start.getX())) == ((other.end().getY() - other.start().getY())
                / (other.end().getX() - other.start().getX()))) {
            if ((end.getX() < other.start().getX()) || (other.end().getX() < start.getX())) {
                return false;
            }
            if ((start.getY() > other.end().getY()) || (other.start().getY() > end.getY())) {
                return false;
            }
            return true;
        }
        double xIntersection = ((start.getX() * end.getY() - start.getY() * end.getX())
                * (other.start().getX() - other.end().getX()) - (start.getX() - end.getX())
                * (other.start().getX() * other.end().getY() - other.start().getY() * other.end().getX()))
                / ((start.getX() - end.getX()) * (other.start().getY() - other.end().getY())
                - (start.getY() - end.getY()) * (other.start().getX() - other.end().getX()));

        double yIntersection = ((start.getX() * end.getY() - start.getY() * end.getX())
                * (other.start().getY() - other.end().getY()) - (start.getY() - end.getY())
                * (other.start().getX() * other.end().getY() - other.start().getY() * other.end().getX()))
                / ((start.getX() - end.getX()) * (other.start().getY() - other.end().getY())
                - (start.getY() - end.getY()) * (other.start().getX() - other.end().getX()));

        // Check if the intersection point is within the bounds of both lines
        if (xIntersection >= Math.min(start.getX(), end.getX())
                && xIntersection <= Math.max(start.getX(), end.getX())
                && yIntersection >= Math.min(start.getY(), end.getY())
                && yIntersection <= Math.max(start.getY(), end.getY())
                && xIntersection >= Math.min(other.start().getX(), other.end().getX())
                && xIntersection <= Math.max(other.start().getX(), other.end().getX())
                && yIntersection >= Math.min(other.start().getY(), other.end().getY())
                && yIntersection <= Math.max(other.start().getY(), other.end().getY())) {
            return true; // Lines intersect
        }
        return false; // Lines do not intersect
    }

    /**
     * Checks if this line segment intersects with two other line segments.
     *
     * @param other1 the first other line segment
     * @param other2 the second other line segment
     * @return true if this line intersects with both other lines, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        if (this.isIntersecting(other1)) {
            if (this.isIntersecting(other2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the intersection point if this line segment intersects with another line segment.
     *
     * @param other the other line segment to find intersection with
     * @return the intersection point if the lines intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        if (((end.getY() - start.getY()) / (end.getX() - start.getX()))
                == ((other.end().getY() - other.start().getY()) / (other.end().getX() - other.start().getX()))) {
            if ((end.getX() < other.start().getX()) || (other.end().getX() < start.getX())) {
                return null;
            }
            if ((start.getY() > other.end().getY()) || (other.start().getY() > end.getY())) {
                return null;
            }
        }
        double xIntersection = ((start.getX() * end.getY() - start.getY() * end.getX())
                * (other.start().getX() - other.end().getX()) - (start.getX() - end.getX())
                * (other.start().getX() * other.end().getY() - other.start().getY()
                * other.end().getX())) / ((start.getX() - end.getX())
                * (other.start().getY() - other.end().getY()) - (start.getY() - end.getY())
                * (other.start().getX() - other.end().getX()));

        double yIntersection = ((start.getX() * end.getY() - start.getY() * end.getX())
                * (other.start().getY() - other.end().getY()) - (start.getY() - end.getY())
                * (other.start().getX() * other.end().getY() - other.start().getY()
                * other.end().getX())) / ((start.getX() - end.getX())
                * (other.start().getY() - other.end().getY()) - (start.getY() - end.getY())
                * (other.start().getX() - other.end().getX()));

        // Check if the intersection point is within the bounds of both lines
        if (xIntersection >= Math.min(start.getX(), end.getX())
                && xIntersection <= Math.max(start.getX(), end.getX())
                && yIntersection >= Math.min(start.getY(), end.getY())
                && yIntersection <= Math.max(start.getY(), end.getY())
                && xIntersection >= Math.min(other.start().getX(), other.end().getX())
                && xIntersection <= Math.max(other.start().getX(), other.end().getX())
                && yIntersection >= Math.min(other.start().getY(), other.end().getY())
                && yIntersection <= Math.max(other.start().getY(), other.end().getY())) {
            return new Point(xIntersection, yIntersection); // Lines intersect
        }
        return null; // Lines do not intersect
    }

    /**
     * Checks if this line segment is equal to another line segment.
     *
     * @param other the other line segment to compare
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (((end.getY() - start.getY()) / (end.getX() - start.getX()))
                == ((other.end().getY() - other.start().getY()) / (other.end().getX() - other.start().getX()))) {
            return this.length() == other.length();
        }
        return false;
    }

    /**
     * Finds the closest intersection point to the start of the line within the given rectangle.
     *
     * @param rect the rectangle to check for intersections
     * @return the closest intersection point, or null if there are no intersections
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line[] arr = new Line[4];
        arr[0] = new Line(rect.getUpperLeft(), new Point(rect.getUpperLeft().getX(),
                rect.getUpperLeft().getY() + rect.getHeight()));
        arr[1] = new Line(rect.getUpperLeft(), new Point(rect.getUpperLeft().getX() + rect.getWidth(),
                rect.getUpperLeft().getY()));
        arr[2] = new Line(new Point(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY()),
                new Point(rect.getUpperLeft().getX() + rect.getWidth(),
                        rect.getUpperLeft().getY() + rect.getHeight()));
        arr[3] = new Line(new Point(rect.getUpperLeft().getX(), rect.getUpperLeft().getY() + rect.getHeight()),
                new Point(rect.getUpperLeft().getX() + rect.getWidth(),
                        rect.getUpperLeft().getY() + rect.getHeight()));
        Point cPoint = null;
        Point c = null;
        double minDis = -1;
        for (int i = 0; i < arr.length; i++) {
            c = this.intersectionWith(arr[i]);
            if (c != null) {
                if (cPoint == null) {
                    cPoint = c;
                    minDis = cPoint.distance(this.start);
                } else {
                    if (c.distance(this.start) < cPoint.distance(this.start)) {
                        cPoint = c;
                        minDis = cPoint.distance(this.start);
                    }
                }
            }
        }
        return cPoint;
    }

    /**
     * Checks if a given point is within the range of the line segment.
     *
     * @param p the point to check
     * @return true if the point is within the range of the line segment, false otherwise
     */
    public boolean contain(Point p) {
        return (Math.min(this.start.getX(), this.end.getX()) <= p.getX()
                && Math.max(this.start.getX(), this.end.getX()) >= p.getX()
                && Math.min(this.start.getY(), this.end.getY()) <= p.getY()
                && Math.max(this.start.getY(), this.end.getY()) >= p.getY());
    }
}