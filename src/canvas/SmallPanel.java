package canvas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.io.Serializable;

import palette._PaletteItem;

/**
 * Panels item on the canvas 
 * @author Xiuming XU (gracexuxiuming@gmail.com)
 */

public class SmallPanel extends CanvasItem{

	private static final long serialVersionUID = 1L;

	public HashSet<_PaletteItem> items = new HashSet<_PaletteItem>();

	public boolean isMoving = false;
	
	public SmallPanel(PersistentCanvas c, Point p,Color o, Color f){
		super(c, o, f);
		shape = new Rectangle(p.x, p.y, 200, 200);
		firstPoint = p;
	}
	
	public SmallPanel(PersistentCanvas c, Point p, Color o, Color f, int w, int h){
		super(c, o, f);
		shape = new Rectangle(p.x, p.y, w, h);
		firstPoint = p;
	}

	public SmallPanel(SmallPanel c) {
		super(c.canvas, c.outline, c.fill);
		shape = new Rectangle((Rectangle) c.shape);
		firstPoint = c.firstPoint;
		isEnable = c.isEnable;
		for(_PaletteItem t: c.items){
			items.add((_PaletteItem)(t.clone()));
		}
		System.out.println(items);
	}
	

	public CanvasItem duplicate() {
		return null;
	}
	
	//call when mouse motion 
	public void update(Point p) {
//		(Rectangle)shape.
		((Rectangle) shape).setFrameFromDiagonal(firstPoint, p);
//		for(_PaletteTool i: items){
//			i.update(p);
//		}
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
	public void paint(Graphics2D g) {
//		System.out.println(outline);
		if(layerID == 2){

			outline = Color.BLUE;
			fill = Color.WHITE;
			System.out.println(outline);
			drawShape(g);
			fillShape(g);
			
			
		}else{
			fillShape(g);
			drawShape(g,outline);
			if(isMoving == true){
				Rectangle tmp = (Rectangle) shape;
				
	            Graphics2D g2 = (Graphics2D)g; 
//	            float[] arr = {4.0f,2.0f}; 
	            float[] arr = {5.0f,3.0f,2.0f,3.0f};  //Dashed line Model
	            BasicStroke stroke = new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL, 1.0f,arr,0); 
	            g2.setStroke(stroke);
				Line2D.Double subline1 = new Line2D.Double(new Point2D.Double(0, tmp.y), new Point2D.Double(canvas.getWidth(),tmp.y));
				Line2D.Double subline2 = new Line2D.Double(new Point2D.Double(tmp.x, 0), new Point2D.Double(tmp.x,canvas.getHeight()));
				Line2D.Double subline3 = new Line2D.Double(new Point2D.Double(0, tmp.y + tmp.height), new Point2D.Double(canvas.getWidth(),tmp.y + tmp.height));
				Line2D.Double subline4 = new Line2D.Double(new Point2D.Double(tmp.x+tmp.width, 0), new Point2D.Double(tmp.x+tmp.width,canvas.getHeight()));

				
				g2.draw(subline1);
				g2.draw(subline2);
				g2.draw(subline3);
				g2.draw(subline4);


			}
		}
	}
	
	@Override
	public SmallPanel clone() {
		return new SmallPanel(this);
	}

}
