package e4;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class LineItem extends CanvasItem {
	Point firstpoint;

	public LineItem(PersistentCanvas c, Color o, Color f, Point p) {
		super(c, o, f);
		shape = new Line2D.Double(p,p);
		firstpoint = p;
	}

	public LineItem(LineItem other) {
		super(other.canvas, other.outline, other.fill);
		//shape = new Ellipse2D((Ellipse2D) other.shape);
		isSelected = false;
		firstpoint = other.firstpoint;
	}
	public Boolean contains(Point p){
		Line2D.Double l = (Line2D.Double) shape;
		return (l.ptSegDist(p)<10);
	}
	@Override
	public CanvasItem duplicate() {
		return canvas.addItem(new LineItem(this));
	}

	@Override
	public void update(Point p) {
		((Line2D) shape).setLine(firstpoint.x, firstpoint.y, p.x, p.y);
		canvas.repaint();
	}

	@Override
	public void move(int dx, int dy) {
		// TODO Auto-generated method stub
		((Line2D.Double) shape).x1 += dx;
		((Line2D.Double) shape).y1 += dy;
		((Line2D.Double) shape).x2 += dx;
		((Line2D.Double) shape).y2 += dy;
		canvas.repaint();

	}

}
