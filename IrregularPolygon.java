import java.awt.geom.*;
import java.util.ArrayList;
import gpdraw.*;

public class IrregularPolygon {
    private ArrayList<Point2D.Double> myPolygon = new ArrayList<Point2D.Double>();

    public IrregularPolygon() {}

    public void add(Point2D.Double aPoint) {
        myPolygon.add(aPoint);
    }

    public double perimeter() {
        if (myPolygon.size() < 2) return 0.0;

        double sum = 0.0;

        for (int i = 0; i < myPolygon.size(); i++) {
            Point2D.Double current = myPolygon.get(i);
            Point2D.Double next = myPolygon.get((i + 1) % myPolygon.size());

            sum += current.distance(next);
        }

        return sum;
    }

    public double area() {
        if (myPolygon.size() < 3) return 0.0;

        double sum = 0.0;

        for (int i = 0; i < myPolygon.size(); i++) {
            Point2D.Double current = myPolygon.get(i);
            Point2D.Double next = myPolygon.get((i + 1) % myPolygon.size());

            sum += (current.x * next.y) - (current.y * next.x);
        }

        return Math.abs(sum) / 2.0;
    }

    public void draw() {
        try {
            if (myPolygon.size() == 0) return;

            DrawingTool pen = new DrawingTool(new SketchPad(500, 500));

            Point2D.Double first = myPolygon.get(0);
            pen.move(first.x, first.y);

            for (int i = 1; i < myPolygon.size(); i++) {
                Point2D.Double p = myPolygon.get(i);
                pen.drawTo(p.x, p.y);
            }

            pen.drawTo(first.x, first.y);

        } catch (java.awt.HeadlessException e) {
            System.out.println("Exception: No graphics support available.");
        }
    }
}