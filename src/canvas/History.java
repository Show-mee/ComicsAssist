package canvas;

import java.util.ArrayList;
import java.util.Stack;
import java.io.Serializable;

import palette.PathItem;
import palette._PaletteItem;

/*
 * History for restoring the canvas stauts
 */
public class History implements Serializable {


	private Stack<ArrayList<CanvasItem>> itemsBackward = new Stack<ArrayList<CanvasItem>>();
	private Stack<ArrayList<SmallPanel>> panelsBackward = new Stack<ArrayList<SmallPanel>>();

	private Stack<ArrayList<CanvasItem>> itemsForward = new Stack<ArrayList<CanvasItem>>();
	private Stack<ArrayList<SmallPanel>> panelsForward = new Stack<ArrayList<SmallPanel>>();

	public ArrayList<CanvasItem> items = new ArrayList<CanvasItem>();
	public ArrayList<SmallPanel> panels = new ArrayList<SmallPanel>();


	public ArrayList<SmallPanel> backwardPanels(ArrayList<SmallPanel> panels2) {

		ArrayList<SmallPanel> tmp = new ArrayList<SmallPanel>(panels2.size());

		ArrayList<CanvasItem> tmp1 = new ArrayList<CanvasItem>();
		for (SmallPanel i : panels2) {
			// System.out.println(i.clone());
			SmallPanel newPanel = i.clone();
			newPanel.items.clear();
			for (_PaletteItem s : i.items) {
				_PaletteItem w = (_PaletteItem) s.clone();
				newPanel.items.add(w);
				tmp1.add(w);
			}
			tmp.add(newPanel);
		}
		panelsForward.push(tmp);
		itemsForward.push(tmp1);
		
		items = itemsBackward.pop();
		panels = panelsBackward.pop();

		if (itemsBackward.isEmpty()) {
			System.out.println("Undo is no longer available");
		}
		return panels;
	}

	// redo
	public ArrayList<SmallPanel> forwardPanels(ArrayList<SmallPanel> current) {

		ArrayList<SmallPanel> tmp = new ArrayList<SmallPanel>(current.size());
		ArrayList<CanvasItem> tmp1 = new ArrayList<CanvasItem>();
		
		
		for (SmallPanel i : current) {
			// System.out.println(i.clone());
			SmallPanel newPanel = i.clone();
			newPanel.items.clear();
			for (_PaletteItem s : i.items) {
				_PaletteItem w = (_PaletteItem) s.clone();
				newPanel.items.add(w);
				tmp1.add(w);
			}
			tmp.add(newPanel);
		}
		panelsBackward.push(tmp);
		itemsBackward.push(tmp1);
		
		items = itemsForward.pop();
		panels = panelsForward.pop();
		
		if (itemsForward.isEmpty()) {
			System.out.println("Redo is no longer available");
		}
		return panels;
	}


	public void storePanels(ArrayList<SmallPanel> panels2) {
		ArrayList<SmallPanel> tmp = new ArrayList<SmallPanel>(panels2.size());
		ArrayList<CanvasItem> tmp1 = new ArrayList<CanvasItem>();

		for (SmallPanel i : panels2) {
			SmallPanel newPanel = i.clone();
			newPanel.items.clear();
			for (_PaletteItem s : i.items) {
				_PaletteItem w = (_PaletteItem) s.clone();
				newPanel.items.add(w);
				tmp1.add(w);
			}
			tmp.add(newPanel);
		}
		panelsBackward.push(tmp);
		itemsBackward.push(tmp1);
	}

}