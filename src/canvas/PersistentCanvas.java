package canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JFrame;

import palette._PaletteItem;
import canvas.SmallPanel;

/**
 * @author Xiuming XU based on Nicolas Roussel main canvas for paining the items
 */
public class PersistentCanvas extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private int width = 400;
//	private int height = 400;

	public ArrayList<CanvasItem> items = new ArrayList<CanvasItem>();
	public ArrayList<SmallPanel> panels = new ArrayList<SmallPanel>();

	public SmallPanel firstPanel;

	// TODO add setter and getter
	public Vector<Boolean> layerVisible = new Vector<Boolean>();
	private History history = new History();

	public int currentlayer = 0;

	// for alignment
	private String mode;

	public ArrayList<CanvasItem> getItems() {
		return items;
	}

	public ArrayList<SmallPanel> getPanels() {
		return panels;
	}

	public void removeBlue() {
		// must use this kind of format
		Iterator<CanvasItem> iterator = items.iterator();
		while (iterator.hasNext()) {
			CanvasItem mp = iterator.next();
			if (mp.layerID == 2) {
				iterator.remove(); // You can do the modification here.
			}
		}
		repaint();
	}

	public void clean() {

		items.clear();
		panels.clear();
	}

	public void setMode(String m) {
		mode = m;
	}

	public PersistentCanvas() {
		// for (int i = 0; i < 3; i++) {
		// layerVisible[i] = true;
		// }
		int index = 0;
		for (boolean b : layerVisible) {
			layerVisible.set(index, false);
			index++;
		}

		setBackground(Color.WHITE);
		setPreferredSize(getMaximumSize());
//		setExtendedState(JFrame.MAXIMIZED_BOTH);

	}

	public int getCurrentLayer() {
		return currentlayer;
	}

	public ArrayList<CanvasItem> getAll() {
		ArrayList<CanvasItem> All = new ArrayList<CanvasItem>();
		for (CanvasItem item : panels)
			All.add(item);
		for (CanvasItem item : items)
			All.add(item);

		return All;

	}

	// pick the item on the top
	public CanvasItem getItemAt(Point p) {

		Collections.reverse(items);
		for (CanvasItem item : items) {
			if (item.contains(p)) {
				if (item.isEnable) {
					item.select();
					Collections.reverse(items);
					return item;
				} 
			}
		}

		Collections.reverse(items);
		return null;
	}
	public SmallPanel getPanelat(Point p) {

		// ugly
		if (panels.size() == 0) {
			firstPanel = new SmallPanel(this, new Point(0, 0), Color.black, Color.white,this.getWidth(),
					this.getHeight());
			firstPanel.isEnable = false;
			panels.add(firstPanel);
		}
		Collections.reverse(panels);
		for (SmallPanel pl : panels) {

			if (pl.contains(p)) {
				if (pl.isEnable) {
					Collections.reverse(panels);
					return pl;
				}
			}
		}
		Collections.reverse(panels);
		return null;
	}
	public _PaletteItem addPaletteItem(_PaletteItem item) {
		if (item == null)
			return null;
		items.add(item);
		repaint();
		return item;
	}

	public CanvasItem addItem(CanvasItem item) {
		if (item == null)
			return null;
		items.add(item);
		repaint();
		return item;
	}

	public SmallPanel addPanel(SmallPanel panel) {
		if (panel == null)
			return null;
		panels.add(panel);
		repaint();
		return panel;
	}

	public void removeItem(CanvasItem item) {
		if (item == null)
			return;
		items.remove(item);
		repaint();
	}

	public void addFirstPanel() {
		clean();
		firstPanel = new SmallPanel(this, new Point(0, 0),Color.black, Color.white, this.getWidth(),
				this.getHeight());
		firstPanel.isEnable = false;
		panels.add(firstPanel);
		repaint();
	}

	public void setLayout1() {
		clean();
		int width = this.getWidth();
		int height = this.getHeight();

		int panelWidth = width - 20;
		int panelHeight = (height - 30) / 2;
		firstPanel = new SmallPanel(this, new Point(0, 0),Color.black, Color.white, this.getWidth(),
				this.getHeight());
		firstPanel.isEnable = false;
		SmallPanel Panel0 = new SmallPanel(this, new Point(10, 10), Color.black, Color.white,panelWidth,
				panelHeight);
		SmallPanel Panel1 = new SmallPanel(this,
				new Point(10, 20 + panelHeight),Color.black, Color.white, panelWidth, panelHeight);
		panels.add(firstPanel);
		panels.add(Panel0);
		panels.add(Panel1);
		repaint();
	}

	public void setLayout2() {
		clean();
		int width = this.getWidth();
		int height = this.getHeight();
		firstPanel = new SmallPanel(this, new Point(0, 0), Color.black, Color.white,this.getWidth(),
				this.getHeight());
		firstPanel.isEnable = false;

		int panelWidth = width - 20;
		int panelHeight = (height - 40) / 3;

		SmallPanel Panel0 = new SmallPanel(this, new Point(10, 10), Color.black, Color.white,panelWidth,
				panelHeight);
		SmallPanel Panel1 = new SmallPanel(this,
				new Point(10, 20 + panelHeight), Color.black, Color.white,panelWidth, panelHeight);
		SmallPanel Panel2 = new SmallPanel(this, new Point(10,
				30 + 2 * panelHeight),Color.black, Color.white, panelWidth, panelHeight);

		panels.add(firstPanel);
		panels.add(Panel0);
		panels.add(Panel1);
		panels.add(Panel2);

		repaint();

	}

	public void setLayout3() {
		clean();
		int width = this.getWidth();
		int height = this.getHeight();

		firstPanel = new SmallPanel(this, new Point(0, 0), Color.black, Color.white,this.getWidth(),
				this.getHeight());
		firstPanel.isEnable = false;

		int panelWidth = (width - 30) / 2;
		int panelHeight = (height - 30) / 2;

		SmallPanel Panel0 = new SmallPanel(this, new Point(10, 10), Color.black, Color.white,panelWidth,
				panelHeight);
		SmallPanel Panel1 = new SmallPanel(this,
				new Point(10, 20 + panelHeight), Color.black, Color.white,panelWidth, panelHeight);
		SmallPanel Panel2 = new SmallPanel(this,
				new Point(20 + panelWidth, 10),Color.black, Color.white, panelWidth, panelHeight);
		SmallPanel Panel3 = new SmallPanel(this, new Point(20 + panelWidth,
				20 + panelHeight), Color.black, Color.white,panelWidth, panelHeight);

		panels.add(firstPanel);
		panels.add(Panel0);
		panels.add(Panel1);
		panels.add(Panel2);
		panels.add(Panel3);

		repaint();

	}



	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());

		Boolean flag;
		for (CanvasItem item : panels) {
			flag = layerVisible.get(item.layerID);
			if (flag == true)
				item.paint(g);
		}

		for (CanvasItem item : items) {
			flag = layerVisible.get(item.layerID);
			if (flag == true)
				item.paint(g);
		}
	}

	public void storeHistory() {
		history.storePanels(panels);
	}

	public void undo() {
//		items = history.backwardItems(items);
		history.backwardPanels(panels);
		items = history.items;
		panels = history.panels;
		repaint();

	}

	public void redo() {
//		items = history.forwardItems(items);
		history.forwardPanels(panels);
		items = history.items;
		panels = history.panels;
		repaint();
	}



	public void removePanel(CanvasItem selection) {
		// TODO Auto-generated method stub
		if (selection == null)
			return;
		panels.remove(selection);
		repaint();
	}

	public void addText(Graphics g) {
		// TODO Auto-generated method stub

	}

}
