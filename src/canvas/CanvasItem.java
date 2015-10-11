package canvas;

import java.awt.Graphics2D;

import java.awt.Shape;
import java.awt.Color;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.BasicStroke;

import javax.swing.JComponent;
import java.io.Serializable;


/**
 * @author Nicolas Roussel (roussel@lri.fr)
 * @modifier Xiuming XU
 */
public abstract class CanvasItem implements Serializable{
	//Xiuming: locate on which canvas!
	protected PersistentCanvas canvas;
	public Color outline;
	public Color fill;
	public  Shape shape;
	public Boolean isSelected;
	protected Point firstPoint;
	protected int layerID = 0;
	protected Boolean isEnable = true;
	protected boolean isBlue  = false;

	public CanvasItem(PersistentCanvas c, Color o, Color f) {
		canvas = c;
		fill = f;
		outline = o;
		shape = null;
		isSelected = false;
	}
	
	public void enable(){
		isEnable = true;
	}
	
	public void disable(){
		isEnable = false;
	}
	
	public void setLayerID(int num){
		layerID = num;
	}
	public int getLayerID(){
		return layerID;
	}

	public void setOutlineColor(Color c) {
		outline = c;
		canvas.repaint();
	}

	public void setFillColor(Color c) {
		fill = c;
		canvas.repaint();
	}

	public void select() {
		isSelected = true;
		canvas.repaint();
	}

	public void deselect() {
		isSelected = false;
		canvas.repaint();
	}

	protected void fillShape(Graphics2D g) {
		g.setColor(fill);
		g.fill(shape);
	}

	protected void drawShape(Graphics2D g, Color c) {
		//change the width of the selected stroke
		Stroke oldstrk = null;
		if (isSelected) {
			oldstrk = g.getStroke();
			g.setStroke(new BasicStroke(2));
		}
		g.setColor(c);
		g.draw(shape);
		if (oldstrk != null)
			g.setStroke(oldstrk);
	}
	
	protected void drawShape(Graphics2D g) {
		//change the width of the selected stroke
		Stroke oldstrk = null;
		if (isSelected) {
			oldstrk = g.getStroke();
			g.setStroke(new BasicStroke(2));
		}
		g.setColor(outline);
		g.draw(shape);
		if (oldstrk != null)
			g.setStroke(oldstrk);
	}

	public void paint(Graphics2D g) {
		if(layerID == 2){
			//Blue Ink panel
			outline = Color.BLUE;
			fill = Color.WHITE;
			drawShape(g);
			fillShape(g);
			
		}else{
			fillShape(g);
			drawShape(g);
		}
	}

	public boolean contains(Point p) {
		return shape.contains(p);
	}
	
	public abstract CanvasItem clone();
	
	public abstract CanvasItem duplicate();

	public abstract void update(Point p);

	public abstract void move(int dx, int dy);
	


}
