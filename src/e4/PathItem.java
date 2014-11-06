package e4;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.GeneralPath;


public class PathItem extends CanvasItem {
//GeneralPath
	public PathItem(PersistentCanvas c, Color o, Color f, Point p) {
		super(c, o, f);
//		Shape shapeTemp = (Shape) c.getItemAt(p);
//		shape = new GeneralPath(shapeTemp);
		// TODO Auto-generated constructor stub	
	}

	@Override
	public CanvasItem duplicate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Point p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void move(int dx, int dy) {
		// TODO Auto-generated method stub

	}

}
