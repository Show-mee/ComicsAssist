package palette;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.io.Serializable;

import canvas.CanvasItem;
import canvas.PersistentCanvas;

/**
 * Panels item on the canvas 
 * @author Xiuming XU (gracexuxiuming@gmail.com)
 */

public class DialogItem extends CanvasItem{

	private static Font monoFont = new Font("Monospaced", 
			Font.ITALIC, 16);

	private String text=null;
	private int w;
	private int h;

	public DialogItem(PersistentCanvas c, Color o, Color f ,Point p, String t) {
		super(c, Color.BLACK, f);	
		//isEnable = true;
		firstPoint = p;
		text = t;
		w=monoFont.getSize()*(t.length());
		h=monoFont.getSize()+5;		
		shape = new Rectangle(p.x, p.y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CanvasItem clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CanvasItem duplicate() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Point p) {
		((Rectangle) shape).setFrameFromDiagonal(firstPoint, p);

		canvas.repaint();
		// TODO Auto-generated method stub

	}

	@Override
	public void move(int dx, int dy) {
		((Rectangle) shape).x += dx;
		((Rectangle) shape).y += dy;
		canvas.repaint();
		firstPoint.x += dx;
		firstPoint.y += dy;


		// TODO Auto-generated method stub

	}

	public void paint(Graphics2D g) {

		g.setFont(monoFont);
		g.setColor(Color.BLACK);
		FontMetrics fm = g.getFontMetrics();
		w = fm.stringWidth(text);
		h = fm.getAscent();
		int x = firstPoint.x;
		int y = firstPoint.y;
		for (String line : text.split("\n")){
	
			g.drawString(line, x, y+h);
			y=y+h+2;
		}
	}

}