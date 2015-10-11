package _main;

import gesture.HelloDollar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import menu.PieMenu;
import canvas.CanvasItem;
import canvas.LayerBox;
import canvas.SmallPanel;
import canvas.PersistentCanvas;
import palette.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;

/**
 * 
 * @author Xiuming XU
 *
 */
@SuppressWarnings("serial")
public class ComicsAssist extends JFrame {

	// Graphical Interface
	private ArrayList<JButton> operations = new ArrayList<JButton>();
	private JPanel outline = null;
	private JPanel fill = null;
	private Point mousepos = new Point(0, 0); // Stores the previous mouse
												// position
	private String title = null; // Changes according to the mode
	public String mode = null; // Mode of interaction
	private PersistentCanvas canvas = null; // Stores the created items
	private CanvasItem selection = null; // Stores the selected item
	private HashSet<CanvasItem> selections = new HashSet<CanvasItem>(); // Stores
	private JButton undoButton; // Set status for undo
	private JButton redoButton; // Set status for redo

	public static Color backg = Color.GRAY;

	public ComicsAssist(String theTitle, int width, int height) {
		title = theTitle;

		initUI(width, height);
		run();
		pack();
		updateTitle();
		setVisible(true);
	}

	public PersistentCanvas getCanvas() {
		return canvas;
	}

	public void initUI(int width, int height) {
		// Color backg = Color.DARK_GRAY;
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Container pane = getContentPane();

		pane.setLayout(new BorderLayout());

		pane.setBackground(backg);
		JPanel panel = new JPanel();
		panel.setBackground(backg);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Create the mode selection button list
		mode = "AddPanel";

		// TODO change all the buttons

		JPanel palettePanel = new JPanel();
		palettePanel.setBackground(Color.GRAY);
		palettePanel.setLayout(new BoxLayout(palettePanel, BoxLayout.X_AXIS));

		JLabel lblNewLabel = new JLabel("Global Bar");
		lblNewLabel.setAlignmentX(0.5f);
		panel.add(lblNewLabel);

		JPanel GlobalPanel = new JPanel();
		GlobalPanel.setBackground(Color.GRAY);
		panel.add(GlobalPanel);
		GlobalPanel.setLayout(new BoxLayout(GlobalPanel, BoxLayout.X_AXIS));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		GlobalPanel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		// deleteClone.add(createOperation("Delete"));

		JButton btn_delete = new JButton("  Delete  ");
		btn_delete.setBackground(Color.GRAY);
		btn_delete.setIcon(new ImageIcon(ComicsAssist.class
				.getResource("/resources/icon/delete.png")));
		btn_delete.setActionCommand("Delete");
		btn_delete.addActionListener(operationListener);
		operations.add(btn_delete);
		panel_1.add(btn_delete);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		GlobalPanel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.GRAY);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.GRAY);
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

		String name = "/resources/icon/" + "rectangle.gif";
		JButton btn_rectangle = new JButton(new ImageIcon(getClass()
				.getResource(name)));
		btn_rectangle.setActionCommand("Rectangle");
		btn_rectangle.addActionListener(modeListener);
		JButton selectButton = new JButton(
				new ImageIcon(
						ComicsAssist.class
								.getResource("/resources/icon/selection.png")));
		panel1.add(selectButton);
		selectButton.setActionCommand("Select/Move");
		selectButton.addActionListener(modeListener);
		panel1.add(btn_rectangle);

		name = "/resources/icon/" + "circle.gif";
		JButton btn_circle = new JButton(new ImageIcon(getClass().getResource(
				name)));
		btn_circle.setActionCommand("Ellipse");
		btn_circle.addActionListener(modeListener);
		panel1.add(btn_circle);

		JButton btnChange = new JButton("");
		btnChange.setIcon(new ImageIcon(ComicsAssist.class
				.getResource("/resources/icon/resize.png")));
		btnChange.setActionCommand("Resize");
		btnChange.addActionListener(modeListener);

		panel2.add(btnChange);

		panel2.add(createButtonMode("Line", "line.gif"));

		name = "/resources/icon/" + "pencil.gif";

		palettePanel.add(panel1);
		palettePanel.add(panel2);
		JButton btn_path = new JButton(new ImageIcon(getClass().getResource(
				name)));
		panel2.add(btn_path);
		btn_path.setActionCommand("Path");
		btn_path.addActionListener(modeListener);

		JLabel lblPalettebar = new JLabel("Palette Bar");
		lblPalettebar.setAlignmentX(0.5f);
		panel.add(lblPalettebar);
		// panel.add(createButtonMode("Text"));

		panel.add(palettePanel);

		JPanel panelPanel = new JPanel();
		panelPanel.setBackground(Color.GRAY);
		panel.add(panelPanel);
		panelPanel.setLayout(new BoxLayout(panelPanel, BoxLayout.Y_AXIS));

		JLabel lblNewLabel_1 = new JLabel("Panel Bar");
		panelPanel.add(lblNewLabel_1);
		lblNewLabel_1.setAlignmentX(0.5f);

		JButton btnSelect = new JButton("   Select   ");
		panelPanel.add(btnSelect);

		btnSelect.setActionCommand("MovePanel");
		btnSelect.addActionListener(modeListener);

		btnSelect.setAlignmentX(0.5f);

		JButton btn_add = new JButton("    Add     ");
		panelPanel.add(btn_add);
		btn_add.setAlignmentX(0.5f);
		FuctionButton(btn_add, "AddPanel");
		JButton btn_resize = new JButton("  Resize   ");
		panelPanel.add(btn_resize);
		btn_resize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_resize.setAlignmentX(0.5f);
		FuctionButton(btn_resize, "ResizePanel");
		JButton btn_group = new JButton("  Group   ");
		panelPanel.add(btn_group);
		btn_group.setAlignmentX(0.5f);
		FuctionButton(btn_group, "GroupPanel");

		// Create mode
		JPanel colorPanel = new JPanel();
		colorPanel.setBackground(Color.GRAY);
		colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));
		fill = createColorSample(Color.WHITE);
		colorPanel.add(fill, BorderLayout.NORTH);
		outline = createColorSample(Color.BLACK);
		colorPanel.add(outline, BorderLayout.SOUTH);

		JPanel colorPanel1 = new JPanel();
		colorPanel1.setBackground(Color.GRAY);

		colorPanel1.add(colorPanel);

		panel.add(colorPanel1);

		pane.add(panel, BorderLayout.WEST);

		// Create the canvas for drawing
		canvas = new PersistentCanvas();
		canvas.setBackground(Color.WHITE);
		canvas.setPreferredSize(new Dimension(600, 500));
		pane.add(canvas, BorderLayout.CENTER);

		// MenuBar(this);
		this.setJMenuBar(new menu.MenuBar(this).getMenubar());
		// this.setJMenuBar(this.getMenubar());
		this.setBackground(backg);
		// create the layer panel
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(backg);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));
		JPanel others = new JPanel();

		others.setBackground(backg);
		others.setLayout(new BoxLayout(others, BoxLayout.PAGE_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		others.add(scrollPane);

		LayerBox layerSetting = new LayerBox(canvas);
		scrollPane.setViewportView(layerSetting);

		Component verticalStrut_1 = Box.createVerticalStrut(50);
		layerSetting.add(verticalStrut_1);
		// undoredo Manager
		JPanel undoRedoManager = new JPanel();
		undoRedoManager.setBackground(backg);
		undoRedoManager.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		undoButton = createEditer("Undo", "undo.gif");
		undoRedoManager.add(undoButton);
		redoButton = createEditer("Redo", "redo.gif");
		undoRedoManager.add(redoButton);
		others.add(undoRedoManager);

		JButton btn_clean = new JButton("Clean");
		undoRedoManager.add(btn_clean);
		btn_clean.setIcon(new ImageIcon(ComicsAssist.class
				.getResource("/resources/icon/clean.gif")));
		btn_clean.setActionCommand("Clean");
		btn_clean.addActionListener(editerListener);

		JPanel deleteClone = new JPanel();
		deleteClone.setBackground(backg);

		// file manager
		JPanel fileManager = new JPanel();
		fileManager.setBackground(backg);

		others.add(fileManager);

		JPanel panelGesture = new JPanel();
		fileManager.add(panelGesture);
		panelGesture.setBackground(Color.LIGHT_GRAY);
		panelGesture.add(new HelloDollar(this));
		panelGesture.setPreferredSize(new Dimension(200, 200));
		panelGesture.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		rightPanel.add(others);

		JPanel panelText = new JPanel();
		panelText.setBackground(backg);
		panelText.setLayout(new BoxLayout(panelText, BoxLayout.Y_AXIS));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.GRAY);
		panelText.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.GRAY);
		panel_4.add(panel_3);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.GRAY);
		panel_5.setBorder(new TitledBorder(null, "Text Field",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.add(panel_5);

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_5.add(scrollPane_1);

		JTextArea textw = new JTextArea(10, 7);
		scrollPane_1.setViewportView(textw);

		JButton insertButton = new JButton("  insert  ");
		panel_4.add(insertButton);
		insertButton.setAlignmentX(0.5f);

		insertButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String intext = textw.getText();
				DialogItem item = new DialogItem(canvas, Color.WHITE,
						Color.WHITE, mousepos, intext);
				canvas.addItem(item);
				canvas.repaint();
			}
		});

		Component verticalGlue_1 = Box.createVerticalGlue();
		panel.add(verticalGlue_1);

		Component verticalStrut = Box.createVerticalStrut(40);
		panel.add(verticalStrut);
		panel.add(panelText);

		pane.add(rightPanel, BorderLayout.EAST);

	}

	private void FuctionButton(JButton btn, String str) {
		btn.setActionCommand(str);
		btn.addActionListener(modeListener);
	}

	private JButton createEditer(String description, String imageDescription) {
		// TODO Auto-generated method stub
		String name = "/resources/icon/" + imageDescription;
		ImageIcon tmp = new ImageIcon(getClass().getResource(name));
		JButton btn = new JButton(tmp);
		btn.setActionCommand(description);
		btn.addActionListener(editerListener);
		return btn;
	}

	/**
	 * Listen the mode changes and update the Title
	 */
	private ActionListener modeListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// you can use the function updateTitle();
			String seMo = e.getActionCommand();

			if (seMo.equals("Select/Move")) {
				mode = "Select/Move";
			} else if (seMo.equals("Group")) {
				if (selection != null) {
					selections.add(selection);
				}
				mode = "Group";
			} else if (seMo.equals("Rectangle")) {
				mode = "Rectangle";
			} else if (seMo.equals("Ellipse")) {
				mode = "Ellipse";
			} else if (seMo.equals("Line")) {
				mode = "Line";
			} else if (seMo.equals("Path")) {
				mode = "Path";
			} else if (seMo.equals("Resize")) {
				mode = "Resize";
			} else if (seMo.equals("AddPanel")) {
				mode = "AddPanel";
			} else if (seMo.equals("MovePanel")) {
				mode = "MovePanel";
			} else if (seMo.equals("ResizePanel")) {
				mode = "ResizePanel";
			} else if (seMo.equals("GroupPanel")) {
				if (selection != null) {
					selection.deselect();
					selection = null;
				}
				if (selections.size() != 0) {
					for (CanvasItem s : selections) {
						s.deselect();
					}
					selections.clear();

				}
				mode = "GroupPanel";
			}

			updateTitle();
		}
	};

	private void deselectMany() {
		if (selections.size() != 0) {
			for (CanvasItem s : selections) {
				s.deselect();
			}
			selections.clear();
		}
	}

	/**
	 * Listen the action on the button related to operations clone and delete
	 */
	private ActionListener operationListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (selection == null && selections.size() == 0)
				return;

			String op = e.getActionCommand();
			if (op.equals("Delete")) {

				canvas.storeHistory();
				System.out.println(selections);

				if (selections.size() != 0) {
					System.out.println("twtwets");
					for (CanvasItem p : selections) {
						SmallPanel tmp = (SmallPanel) p;
						for (CanvasItem s : tmp.items) {
							canvas.removeItem(s);
						}
						canvas.removePanel(tmp);
					}
				}

				if (selection instanceof SmallPanel) {
					SmallPanel tmp = (SmallPanel) selection;
					for (CanvasItem i : tmp.items) {
						canvas.removeItem(i);
					}
					canvas.removePanel(selection);

				} else {
					canvas.removeItem(selection);
				}

			} else if (op.equals("Clone")) {
				canvas.storeHistory();
				selection.duplicate();
			}
		}
	};

	/**
	 * Listen to the actions
	 */
	private ActionListener editerListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String op = e.getActionCommand();
			if (op.equals("Redo")) {
				canvas.redo();
				undoButton.setEnabled(true);
			} else if (op.equals("Undo")) {
				canvas.undo();
				redoButton.setEnabled(true);
			} else if (op.equals("Clean")) {
				canvas.storeHistory();
				selection = null;
				selections.clear();
				canvas.clean();
				canvas.repaint();
			}
		}
	};

	// Listen the click on the color chooser
	private MouseAdapter colorListener = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			if (selection == null && selections.size() == 0)
				return;
			JPanel p = (JPanel) e.getSource();
			Color c = p.getBackground();
			c = JColorChooser.showDialog(null, "Select a color", c);
			// Manage the color change
			p.setBackground(c);

			canvas.storeHistory();
			if (p == fill) {
				if (selection != null) {
					selection.setFillColor(c);
				}
				if (selections.size() != 0) {
					for (CanvasItem i : selections) {
						SmallPanel tp = (SmallPanel) i;
						tp.setFillColor(c);
					}
				}
			} else if (p == outline) {
				if (selection != null) {
					selection.setOutlineColor(c);
				}
				if (selections.size() != 0) {
					for (CanvasItem i : selections) {
						SmallPanel tp = (SmallPanel) i;
						tp.setFillColor(c);
					}
				}
				// canvas.storeHistory();
			}

		}
	};

	// Create the button for the mode
	private JButton createButtonMode(String description, String imageDescription) {
		// JButton btn = new JButton(description);
		String name = "/resources/icon/" + imageDescription;
		JButton btn = new JButton(new ImageIcon(getClass().getResource(name)));
		btn.setSize(32, 32);
		btn.setActionCommand(description);
		btn.addActionListener(modeListener);
		return btn;
	}

	// Create the color sample used to choose the color
	private JPanel createColorSample(Color c) {
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(90, 30));
		p.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		p.setOpaque(true);
		p.setBackground(c);
		// p.setMaximumSize(new Dimension(500, 150));
		p.addMouseListener(colorListener);
		return p;
	}

	// make canvas listen
	public void run() {

		PieMenu pieMain = new PieMenu();
		PieMenu pieTools = new PieMenu("Tools");

		pieMain.add("Move").addActionListener(pieSliceListener);
		pieMain.add("Undo").addActionListener(pieSliceListener);

		pieTools.add("Path").addActionListener(pieSliceListener);
		pieTools.add("Ellipse").addActionListener(pieSliceListener);
		pieTools.add("Rectangle").addActionListener(pieSliceListener);

		pieMain.add(pieTools);
		pieMain.add("Delete").addActionListener(pieSliceListener);
		pieMain.addPieMenuTo(canvas);

		canvas.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				Point p = e.getPoint();
				// left click

				int mods = e.getModifiers();
				if ((mods & InputEvent.BUTTON1_MASK) != 0) {
					canvas.storeHistory();
					undoButton.setEnabled(true);
					SmallPanel pl = canvas.getPanelat(p);

					if (mode.equals("Select/Move")) {
						CanvasItem tmp = canvas.getItemAt(p);
						if (tmp != null) {
							select(canvas.getItemAt(p));
						}

					} else if (mode.equals("Resize")) {
						CanvasItem tmp = canvas.getItemAt(p);
						select(tmp);

					} else if (mode.equals("Group")) {
						selectMany(canvas.getItemAt(p));
					} else if (mode.equals("AddPanel")) {
						SmallPanel item = new SmallPanel(canvas, p, outline.getBackground(),fill.getBackground());
						item.setLayerID(canvas.currentlayer);
						canvas.panels.add(item);
						// canvas.addItem(item);
						select((SmallPanel) item);
					} else if (mode.equals("MovePanel")) {

						select(pl);
						if (selection != null) {
							SmallPanel tmp = (SmallPanel) selection;
							tmp.isMoving = true;
						}

					} else if (mode.equals("ResizePanel")) {
						select(pl);
					} else if (mode.equals("GroupPanel")) {
						SmallPanel tmp = canvas.getPanelat(p);
						if (tmp != null) {
							tmp.select();
							selections.add(tmp);
							System.out.println(selections);
						}
					}

					else {

						_PaletteItem item = null;
						Color o = outline.getBackground();
						Color f = fill.getBackground();
						boolean flag = true;

						if (mode.equals("Rectangle")) {
							item = new RectangleItem(canvas, o, f, p);
						} else if (mode.equals("Ellipse")) {
							// create a new ellipse
							item = new EllipseItem(canvas, o, f, p);
						} else if (mode.equals("Line")) {
							// create a new line
							item = new LineItem(canvas, o, f, p);
						} else if (mode.equals("Path")) {
							// TODO create a new path
							item = new PathItem(canvas, o, f, p);
						} else {
							flag = false;
						}
						if (flag == true) {
							item.setLayerID(canvas.getCurrentLayer());
							System.out.println(canvas.getCurrentLayer());
							if (pl != null) {
								pl.items.add((_PaletteItem) item);
							} else {
								canvas.firstPanel.items
										.add((_PaletteItem) item);
							}
							canvas.addPaletteItem(item);
							select(item);
						}

					}
				}
				mousepos = p;

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				int mods = e.getModifiers();
				if ((mods & InputEvent.BUTTON1_MASK) != 0) {
					if (mode.equals("MovePanel")) {
						if (selection != null) {
							SmallPanel pl = (SmallPanel) selection;
							pl.isMoving = false;
							canvas.repaint();
						}

					} else if (mode.equals("Select/Move")) {
						if (selection != null) {
							if (selection instanceof SmallPanel) {
								if (selection != null) {
									SmallPanel pl = (SmallPanel) selection;
									pl.isMoving = false;
									canvas.repaint();

								}
							}
						}

					}
				}
			}

		});

		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				int mods = e.getModifiers();

				if ((mods & InputEvent.BUTTON1_MASK) != 0) {
					if (selection == null && selections.size() == 0)
						return;
					if (mode.equals("MovePanel")) {
						int dx = e.getPoint().x - mousepos.x;
						int dy = e.getPoint().y - mousepos.y;

						selection.move(dx, dy);
						// TODO
						SmallPanel panel = (SmallPanel) selection;
						for (_PaletteItem it : panel.items) {
							it.move(dx, dy);

						}
					} else if (mode.equals("ResizePanel")) {
						selection.update(e.getPoint());
					} else if (mode.equals("Select/Move")) {
						if (selection instanceof SmallPanel) {
							selection.deselect();
						} else {
							selection.move(e.getPoint().x - mousepos.x,
									e.getPoint().y - mousepos.y);
						}

					} else if (mode.equals("Group")) {
						selections.add(selection);
						for (CanvasItem s : selections) {
							s.move(e.getPoint().x - mousepos.x, e.getPoint().y
									- mousepos.y);
						}
					} else if (mode.equals("GroupPanel")) {
						// selections.add(selection);

						int dx = e.getPoint().x - mousepos.x;
						int dy = e.getPoint().y - mousepos.y;
						//
						for (CanvasItem it : selections) {
							it.move(dx, dy);
							for (CanvasItem s : ((SmallPanel) it).items) {
								s.move(dx, dy);
							}
						}

					}

					else {
						// change the shape when move
						selection.update(e.getPoint());
					}
					mousepos = e.getPoint();

				}
			}
		});
	}

	// Update the Title
	public void updateTitle() {
		setTitle(title + " - " + mode);
	}

	// Select an Item
	private void select(CanvasItem item) {
		if (selection != null)
			selection.deselect();

		selection = item;
		if (selection != null) {
			selection.select();
			fill.setBackground(selection.fill);
			outline.setBackground(selection.outline);
			for (JButton op : operations) {
				op.setEnabled(true);
			}
		} else {
			for (JButton op : operations)
				op.setEnabled(false);
		}
	}

	private void selectMany(CanvasItem item) {
		if (item != null) {
			item.select();
			selections.add(item);
			fill.setBackground(item.fill);
			outline.setBackground(item.outline);

			for (JButton op : operations)
				op.setEnabled(true);
		} else {
			for (JButton op : operations)
				op.setEnabled(false);
		}
	}

	public static void main(String[] args) {

		ComicsAssist editor = new ComicsAssist("ComicsAssist", 800, 600);
		editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editor.setBackground(backg);

	}

	public Point getmouse() {
		return mousepos;
	}

	ActionListener pieSliceListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd == "Delete") {

				CanvasItem tmp = canvas.getItemAt(mousepos);
				if (tmp != null) {
					select(canvas.getItemAt(mousepos));
				} else {
					tmp = canvas.getPanelat(mousepos);
					if (tmp != null) {
						select(tmp);
					}
				}

				if (selection == null && selections.size() == 0)
					return;
				canvas.storeHistory();
				if (selection instanceof SmallPanel) {
					SmallPanel tmp1 = (SmallPanel) selection;
					for (CanvasItem i : tmp1.items) {
						canvas.removeItem(i);
					}
					canvas.removePanel(selection);

				} else {
					canvas.removeItem(selection);
				}

			} else if (cmd == "Undo") {
				canvas.undo();

			} else if (cmd == "Move") {
				CanvasItem tmp = canvas.getItemAt(mousepos);
				if (tmp != null) {
					select(canvas.getItemAt(mousepos));
					mode = "Select/Move";
					updateTitle();
				} else {
					tmp = canvas.getPanelat(mousepos);

					if (tmp != null) {
						select(tmp);
						mode = "MovePanel";
						updateTitle();
					}
				}

			} else {
				if (cmd == "Ellipse")
					mode = "Ellipse";
				else if (cmd == "Path") {
					mode = "Path";
				} else if (cmd == "Rectangle") {
					mode = "Rectangle";
				}
				updateTitle();
			}

			System.out.println("Activating " + e.getActionCommand());

		}
	};

}
