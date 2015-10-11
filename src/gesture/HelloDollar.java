package gesture;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.JComponent;
import _main.ComicsAssist;
import lx.interaction.dollar.*;

public class HelloDollar 
extends JComponent 
implements MouseListener, MouseMotionListener, DollarListener 
{
	int x;
	int y;
	int state;
	private ComicsAssist comicsAssist;
	
	Dollar dollar = new Dollar(Dollar.GESTURES_DEFAULT);
	String name = "";
	double score = 0;
	boolean ok = false;;
	
	Image offScreen;
	 
	public HelloDollar(ComicsAssist c){
		comicsAssist = c;
		init();
	}
	
	
	public void init() {
		setPreferredSize(new Dimension(200, 200));
		setBackground(Color.WHITE);

		offScreen = createImage(getSize().width, getSize().height);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		dollar.setListener(this);
		dollar.setActive(true);
	}
	
	public void mouseEntered(MouseEvent e) //mouse entered canvas
	{	}
	
	public void mouseExited(MouseEvent e) //mouse left canvas
	{	}
	
	public void mouseClicked(MouseEvent e) //mouse pressed-depressed (no motion in between), if there's motion -> mouseDragged
	{   }
		
	public void update(MouseEvent e)
	{	
		x = e.getX();
		y = e.getY();
	
		repaint();
		e.consume();
	}	

	public void mousePressed(MouseEvent e) 
	{
		state = 1;
		dollar.pointerPressed(e.getX(), e.getY());		
		update(e);
	}
	public void mouseReleased(MouseEvent e) 
	{ 
		state = 0;
		dollar.pointerReleased(e.getX(), e.getY());		
		update(e);
	}
	public void mouseMoved(MouseEvent e) 
	{  
		state = 0;
		update(e);
	}
	
	public void mouseDragged(MouseEvent e) 
	{		
		state = 2;
		dollar.pointerDragged(e.getX(), e.getY());				
		update(e);
	}
	
	public void update(Graphics g)
	{
		Graphics temp = offScreen.getGraphics();
		temp.setColor(getBackground());
		temp.fillRect(0, 0, getWidth(), getHeight());
		temp.setColor(getForeground());
		
		paint(temp);
		
		temp.dispose();
		
		g.drawImage(offScreen, 0, 0, null);
	}
	
	public void paint(Graphics g)
	{
//		g.drawLine(0, y, getWidth(), y);
//		g.drawLine(x, 0, x, getHeight());

//		g.drawString("[" + x + " " + y + "] [" + state + "]", 10, 20);
		g.drawString("Gesture V->Select/Move",10,30);
		g.drawString("Gesture  anticlockwise rectangle-> rectangle tool", 10, 50);
		g.drawString("Gesture  anticlockwise circle-> circle tool", 10, 70);
		g.drawString("Gesture  pigTail-> path tool", 10, 90);


		dollar.render(g);
		
	}
	
	public void dollarDetected(Dollar dollar)
	{
		score = dollar.getScore();
		name = dollar.getName();
		
		ok = score > 0.80;
		if(ok){
			if(name =="v") {
				comicsAssist.mode = "Select/Move";
			
			}
			else if(name == "rectangle CCW") {
				comicsAssist.mode = "Rectangle";
				//comicsAssist.updateTitle();
				}
			else if(name == "circle CCW") {
				comicsAssist.mode = "Ellipse";
				//comicsAssist.updateTitle();
				}
			else if(name == "pigTail") {
				comicsAssist.mode = "Path";			
				//comicsAssist.updateTitle();
				}
			else if(name=="arrow") {
				comicsAssist.mode = "Line";
			}
			comicsAssist.updateTitle();	
		}
	}
}