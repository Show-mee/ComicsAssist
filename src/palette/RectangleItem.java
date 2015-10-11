package palette;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import canvas.CanvasItem;
import canvas.SmallPanel;
import canvas.PersistentCanvas;

/**
 * @author Xiuming XU based on Nicolas Roussel (roussel@lri.fr)
 * 
 */
public class RectangleItem extends _PaletteItem {

	public void printFirstPoint(){
		System.out.print("firstpoint:");
		System.out.println(firstPoint);
	}

	public RectangleItem(PersistentCanvas c, Color o, Color f, Point p) {
		super(c, o, f);
		shape = new Rectangle(p.x, p.y, 0, 0);
		firstPoint = p;
	}

	public RectangleItem(RectangleItem other) {
		super(other.canvas, other.outline, other.fill);
		shape = new Rectangle((Rectangle) other.shape);
		isSelected = false;
		firstPoint = other.firstPoint;
	}
	

	public CanvasItem duplicate() {
		RectangleItem r = new RectangleItem(this);
		((Rectangle) r.shape).x += 2;
		((Rectangle) r.shape).y += 2;
		return canvas.addPaletteItem(r);
	}

	public void update(Point p) {

		((Rectangle) shape).setFrameFromDiagonal(firstPoint, p);
		canvas.repaint();
	}

	public void move(int dx, int dy) {
		((Rectangle) shape).x += dx;
		((Rectangle) shape).y += dy;
		canvas.repaint();
		firstPoint.x += dx;
		firstPoint.y += dy;
	}

	@Override
	public CanvasItem clone() {
		// TODO Auto-generated method stub
		return new RectangleItem(this);
	}

}
