package palette;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D.Float;
import java.util.ArrayList;

import javax.swing.plaf.metal.MetalBorders.PaletteBorder;

import canvas.CanvasItem;
import canvas.SmallPanel;
import canvas.PersistentCanvas;

/**
 * 
 * @author Xiuming XU
 *
 *         created on Nov 8, 2014 8:10:41 AM
 */

public class PathItem extends _PaletteItem {
	// GeneralPath
	ArrayList<Integer> xList = new ArrayList<Integer>();
	ArrayList<Integer> yList = new ArrayList<Integer>();

	public PathItem(PersistentCanvas c, Color o, Color f, Point p) {
		super(c, o, f);
		firstPoint = p;
		shape = new GeneralPath();
		((GeneralPath) shape).moveTo(p.x, p.y);
	}

	public PathItem(PathItem n) {
		super(n.canvas, n.outline, n.fill);
		firstPoint = n.firstPoint;
		shape = new GeneralPath(n.shape);
		((GeneralPath) shape).moveTo(firstPoint.x, firstPoint.y);
	}

	@Override
	public CanvasItem clone() {
		return new PathItem(this);
	}

	@Override
	public void paint(Graphics2D g) {
		if (layerID == 2) {
			// Blue Ink panel
			outline = Color.BLUE;
			drawShape(g);

		} else {
			drawShape(g);
		}
	}

	@Override
	public CanvasItem duplicate() {
		PathItem r = new PathItem(this);
		r.move(2, 2);
		return canvas.addPaletteItem(r);
	}

	@Override
	public void update(Point p) {
		// TODO Auto-generated method stub
		((GeneralPath) shape).lineTo(p.x, p.y);
		canvas.repaint();
	}

	@Override
	public void move(int dx, int dy) {

		((GeneralPath) shape).transform(AffineTransform.getTranslateInstance(
				dx, dy));
		System.out.println("Okay");

		canvas.repaint();
		firstPoint.x += dx;
		firstPoint.y += dy;
	}

}
