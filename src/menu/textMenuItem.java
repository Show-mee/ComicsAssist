package menu;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import palette.DialogItem;
import canvas.PersistentCanvas;
import _main.ComicsAssist;


public class textMenuItem extends JFrame
implements ActionListener {
	/**
	 * 
	 */
	 private static final long serialVersionUID = 1L;
	 private JButton insertButton;
	 private JButton cancelButton;
	 private JTextArea textw;

	 private ComicsAssist comicsAssist;
	 private PersistentCanvas canvas;

	 public textMenuItem(ComicsAssist c) {
		 setTitle("Text");
		 comicsAssist = c;
		 canvas = comicsAssist.getCanvas();

		 Container pane = getContentPane();
		 pane.setLayout(new BorderLayout());

		 JPanel panel = new JPanel();
		 panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		 textw = new JTextArea(20,20);
		 panel.add(textw);

		 JPanel buttonPanel = new JPanel();
		 buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		 insertButton = new JButton("insert");
		 insertButton.setActionCommand("insert");       
		 insertButton.addActionListener(this);		
		 Component horizontalStrut = Box.createHorizontalStrut(200);
		 buttonPanel.add(horizontalStrut);
		 buttonPanel.add(insertButton);

		 cancelButton = new JButton("Cancel");
		 cancelButton.setActionCommand("Cancel");
		 cancelButton.addActionListener(this);
		 buttonPanel.add(cancelButton);
		 panel.add(buttonPanel);
		 pane.add(panel);
	 }


	 @SuppressWarnings("null")
	 public void actionPerformed(ActionEvent e) { 
		 String cmd =  e.getActionCommand();
		 if(cmd == "Cancel"){

			 this.dispose();}
		 else{		
			 System.out.println(textw.getText());
			 String intext = textw.getText();	
			 DialogItem item = new DialogItem(canvas, Color.WHITE, Color.WHITE, comicsAssist.getmouse(), intext);
			 canvas.addItem(item);			 
			 canvas.repaint();
			 this.dispose();
		 }

	 }
}
