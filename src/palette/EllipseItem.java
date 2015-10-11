package palette;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.print.Printable;

import javax.swing.plaf.synth.Region;

import canvas.CanvasItem;
import canvas.SmallPanel;
import canvas.PersistentCanvas;

public class EllipseItem extends _PaletteItem {

	public EllipseItem(PersistentCanvas c, Color o, Color f, Point p) {
		super(c, o, f);
		shape = new Ellipse2D.Double(p.x, p.y, 0, 0);
		firstPoint = p;
	}


	public EllipseItem(EllipseItem other) {
		super(other.canvas, other.outline, other.fill);
		Ellipse2D.Double temp = (Ellipse2D.Double) other.shape;
		shape = new Ellipse2D.Double(temp.x, temp.y, temp.getWidth(), temp.getHeight());
		isSelected = false;
		firstPoint = other.firstPoint;
	}
	@Override
	public CanvasItem clone() {
		EllipseItem e = new EllipseItem(this);
		return e;
	}

	public CanvasItem duplicate() {
		EllipseItem e = new EllipseItem(this);
		((Ellipse2D.Double) e.shape).x += 2;
		((Ellipse2D.Double) e.shape).y += 2;
		return canvas.addPaletteItem(e);
	}

	public void update(Point p) {
		((Ellipse2D.Double) shape).setFrameFromDiagonal(firstPoint, p);
		canvas.repaint();
	}


	@Override
	public void move(int dx, int dy) {
		// TODO Auto-generated method stub
		((Ellipse2D.Double) shape).x += dx;
		((Ellipse2D.Double) shape).y += dy;
		canvas.repaint();
	}


}
