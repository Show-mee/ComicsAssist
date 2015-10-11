package canvas;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.Border;
import javax.swing.JScrollPane;

/**
 * @author Xiuming XU (gracexuxiuming@gmail.com) LayerBox for changing the items
 *         status on the canvas
 */
@SuppressWarnings("serial")
public class LayerBox extends JPanel {

	JLabel label;
	int layerNum = 0;
	ArrayList<JCheckBox> layers = new ArrayList<JCheckBox>();
	ArrayList<String> layerIDs = new ArrayList<String>();
	JCheckBox layer1;
	JCheckBox layer2;
	JCheckBox layer3;

	JButton addButton = new JButton("AddLayer");
	JPanel checkPanel = new JPanel();

	JComboBox<String> choices;

	PersistentCanvas canvas;

	ItemListener itemListen = new ItemListener() {
		/** Listens to the check boxes. */
		public void itemStateChanged(ItemEvent e) {

			ItemSelectable source = e.getItemSelectable();
			if (source == choices) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					JComboBox<String> combo = (JComboBox) e.getSource();
					int index = combo.getSelectedIndex();
					// //TODO change the current layer
					canvas.currentlayer = index;
				}
			} else {
				JCheckBox tmp = (JCheckBox) source;
				int index = layers.indexOf(tmp);
				canvas.layerVisible.set(index, isSelected(e));
				canvas.repaint();
			}
		}
	};
	private JPanel panel;

	void initLayers() {
		createLayer();
		createLayer();
		createLayer();

	}

	public LayerBox(PersistentCanvas c) {
		super();
		canvas = c;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(Color.GRAY);
		add(Box.createRigidArea(new Dimension(0, 5)));
		label = new JLabel("LAYER PANEL");
		label.setBackground(Color.GRAY);
		label.setAlignmentX(CENTER_ALIGNMENT);
		add(label);
		add(Box.createRigidArea(new Dimension(0, 5)));


		/* Put the check boxes in a column in a panel */

		checkPanel.setBackground(Color.LIGHT_GRAY);
		checkPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		checkPanel.setLayout(new GridLayout(0, 1));



		/****** Add the layer choices *******/
		choices = new JComboBox<String>();
		choices.addItemListener(itemListen);

		checkPanel.add(choices);

		JButton blueRemove = new JButton("BlueRemove");
		blueRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.removeBlue();
			}
		});
		
				addButton.addActionListener(new ActionListener() {
		
					@Override
					public void actionPerformed(ActionEvent e) {
						createLayer();
					}
				});
				checkPanel.add(addButton);
		// checkPanel.add(Box.createVerticalStrut(30));

		checkPanel.add(blueRemove);
		
		initLayers();

		add(checkPanel);

	}

	public void createLayer() {
		String layerName = "Layer" + layerNum;
		layerNum++;
		layerIDs.add(layerName);

		canvas.layerVisible.add(true);
		
		panel = new JPanel();
		checkPanel.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		JCheckBox newLayer = new JCheckBox(layerName);
		panel.add(newLayer);
		newLayer.setSelected(true);
		newLayer.addItemListener(itemListen);
		layers.add(newLayer);
		updateUI();

		choices.removeAllItems();
		for (String s : layerIDs) {
			choices.addItem(s);
		}
	}

	private Boolean isSelected(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED)
			return true;
		else
			return false;
	}

}
