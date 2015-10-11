package palette;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D.Double;

import canvas.CanvasItem;
import canvas.SmallPanel;
import canvas.PersistentCanvas;

public class LineItem extends _PaletteItem {

	public LineItem(PersistentCanvas c, Color o, Color f, Point p) {
		super(c, o, f);
		shape = new Line2D.Double(p,p);
		firstPoint = p;
	}

	public LineItem(LineItem other) {
		super(other.canvas, other.outline, other.fill);
		Line2D.Double tmp = (Line2D.Double)other.shape;
		shape = new Line2D.Double(tmp.getP1(), tmp.getP2());
		isSelected = false;
		firstPoint = other.firstPoint;
	}
	public boolean contains(Point p){
		Line2D.Double l = (Line2D.Double) shape;
		return (l.ptSegDist(p)<10);
	}
	@Override
	public CanvasItem duplicate() {
		LineItem r = new LineItem(this);
		r.move(2, 2);
		return canvas.addPaletteItem(r);
	}

	@Override
	public void update(Point p) {
		((Line2D) shape).setLine(firstPoint.x, firstPoint.y, p.x, p.y);
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

	@Override
	public CanvasItem clone() {
		System.out.println("Okay");
		return new LineItem(this);
	}

}
