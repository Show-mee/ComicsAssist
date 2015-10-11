package menu;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import _main.ComicsAssist;
import canvas.CanvasItem;
import canvas.PersistentCanvas;
import canvas.SmallPanel;

/**
 * @author Xiuming XU (gracexuxiuming@gmail.com)
 */
public class MenuBar {
	private JMenuBar menuBar;
	private JMenu menuFile = null;
	private JMenu menuHelp = null;
	private JMenu menuInsert = null;
	private JMenuItem menuItemNew = null;
	private JMenuItem menuItemOpen = null;
	private JMenuItem menuItemSave = null;
	private JMenuItem menuItemAbout = null;
	private JMenuItem menuItemInsert = null;
	private ComicsAssist comicsAssist;
	private PersistentCanvas canvas;

	// private PersistentCanvas canvas;

	public MenuBar(ComicsAssist c) {
		comicsAssist = c;
		canvas= c.getCanvas();
	}

	/**
	 * This method initializes menuBar
	 * 
	 * @return javax.swing.JMenuBar
	 */
	public JMenuBar getMenubar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.setBackground(comicsAssist.backg);
			menuBar.setBorderPainted(false);
			menuBar.add(getMenuFile());
			
			menuBar.add(getMenuHelp());
		}
		
		return menuBar;

	}

	public PersistentCanvas getcanvas() {
		return canvas;
	}

	/**
	 * This method initializes menuFile
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getMenuFile() {
		if (menuFile == null) {
			menuFile = new JMenu();
			menuFile.setBackground(comicsAssist.backg);
			menuFile.setText("File");
			menuFile.add(getMenuItemNew());
			menuFile.add(getMenuItemOpen());
			menuFile.add(getMenuItemSave());
		}
		return menuFile;
	}
	
	
	

	/**
	 * This method initializes menuHelp
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getMenuHelp() {
		if (menuHelp == null) {
			menuHelp = new JMenu();
			menuHelp.setBackground(comicsAssist.backg);
			menuHelp.setText("Help");
			menuHelp.add(getMenuItemAbout());
		}
		return menuHelp;
	}

	/**
	 * his method initializes menuItemNew <NEW> menu item
	 * 
	 * @return javax.swing.JMenuItem
	 */


	private JMenuItem getMenuItemNew() {
		if (menuItemNew == null) {
			menuItemNew = new JMenuItem();
			menuItemNew.setText("New");
			menuItemNew.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					newMenuItem newFrame = new newMenuItem(comicsAssist);
					newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					// Display the window.
					newFrame.pack();
					newFrame.setVisible(true);

				}
			});
		}
		return menuItemNew;
	}

	/**
	 * This method initializes menuItemOpen
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getMenuItemOpen() {
		if (menuItemOpen == null) {
			menuItemOpen = new JMenuItem();
			menuItemOpen.setText("Open");
			menuItemOpen.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(new File("./"));
					int retrival = chooser.showOpenDialog(null);
					if (retrival == JFileChooser.APPROVE_OPTION) {
						try {

							FileInputStream file = new FileInputStream(chooser
									.getSelectedFile());
							ObjectInputStream ois = new ObjectInputStream(file);
							@SuppressWarnings("unchecked")
							ArrayList<CanvasItem> openitems = (ArrayList<CanvasItem>)ois.readObject();
							@SuppressWarnings("unchecked")
							ArrayList<SmallPanel> openpanels = (ArrayList<SmallPanel>)ois.readObject();
							ois.close();
							canvas.getAll().removeAll(canvas.getAll());
							for (SmallPanel panel: openpanels) canvas.addPanel(panel);
							for (CanvasItem item: openitems) canvas.addItem(item);
							canvas.repaint();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}

				}
			});
		}

		return menuItemOpen;
	}
	

	private JMenuItem getMenuItemSave() {
		if (menuItemSave == null) {
			menuItemSave = new JMenuItem();
			menuItemSave.setText("Save");
			menuItemSave.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(new File("./"));
					int retrival = chooser.showSaveDialog(null);
					if (retrival == JFileChooser.APPROVE_OPTION) {
						try {

							FileOutputStream file = new FileOutputStream(
									chooser.getSelectedFile());
							ObjectOutputStream oos = new ObjectOutputStream(
									file);
							
							oos.writeObject(canvas.getItems());
							oos.writeObject(canvas.getPanels());
							
							oos.flush();
							oos.close();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			});
		}
		return menuItemSave;
	}
	
	private JMenuItem getMenuItemText() {
		if (menuItemInsert == null) {
			menuItemInsert = new JMenuItem();
			menuItemInsert.setText("Text");
			menuItemInsert.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					textMenuItem newFrame = new textMenuItem(comicsAssist);
					newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					// Display the window.
					newFrame.pack();
					newFrame.setVisible(true);
				}
			});
		}
		return menuItemInsert;
	}

	/**
	 * This method initializes menuItemAbout
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getMenuItemAbout() {
		if (menuItemAbout == null) {
			menuItemAbout = new JMenuItem();
			menuItemAbout.setText("About");
			menuItemAbout
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							JOptionPane.showMessageDialog(
									comicsAssist.getContentPane(), "ComicAssist1.1");
							System.out.println("hello");
						}
					});
		}
		return menuItemAbout;
	}

}
