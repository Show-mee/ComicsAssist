package menu;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import canvas.PersistentCanvas;
import _main.ComicsAssist;


public class newMenuItem extends JFrame
                             implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String layout0 = "0";
    static String layout1 = "1";
    static String layout2 = "2";
    static String layout3 = "3";
    

    JLabel picture;
    ButtonGroup group;
	private String submit = layout0;
	private ComicsAssist comicsAssist;
	private PersistentCanvas canvas;
    
    public newMenuItem(ComicsAssist c) {
    	setTitle("New");
    	comicsAssist = c;
    	canvas = comicsAssist.getCanvas();
    	JPanel whole =  new JPanel();

        JRadioButton layout2Button = new JRadioButton(layout2);
        layout2Button.setMnemonic(KeyEvent.VK_D);
        layout2Button.setActionCommand(layout2);

        JRadioButton layout3Button = new JRadioButton(layout3);
        layout3Button.setMnemonic(KeyEvent.VK_R);
        layout3Button.setActionCommand(layout3);


        //Group the radio buttons.
        group = new ButtonGroup();
        group.add(layout2Button);
        group.add(layout3Button);
        layout2Button.addActionListener(this);
        layout3Button.addActionListener(this);


        //Put the radio buttons in a column in a panel.
        JPanel radioPanel = new JPanel(new GridLayout(0, 4));
        
        JLabel lblNewLabel = new JLabel(createImageIcon("/images/smallPreview/"
                + layout0
                + ".gif"));
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        radioPanel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel(createImageIcon("/images/smallPreview/"
                + layout1
                + ".gif"));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
        radioPanel.add(lblNewLabel_1);
        
        
        JRadioButton layout0Button = new JRadioButton(layout0);
        layout0Button.setMnemonic(KeyEvent.VK_B);
        layout0Button.setActionCommand(layout0);
        layout0Button.setSelected(true);
        group.add(layout0Button);
        
                //Register a listener for the radio buttons.
                layout0Button.addActionListener(this);
                
                JLabel lblNewLabel_2 = new JLabel(createImageIcon("/images/smallPreview/"
                        + layout2
                        + ".gif"));
                lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
                radioPanel.add(lblNewLabel_2);
                
                JLabel lblNewLabel_3 = new JLabel(createImageIcon("/images/smallPreview/"
                        + layout3
                        + ".gif"));
                lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
                radioPanel.add(lblNewLabel_3);
                radioPanel.add(layout0Button);
                                                          
                JRadioButton layout1Button = new JRadioButton(layout1);
                layout1Button.setMnemonic(KeyEvent.VK_C);
                layout1Button.setActionCommand(layout1);
                group.add(layout1Button);
                layout1Button.addActionListener(this);
                radioPanel.add(layout1Button);
        radioPanel.add(layout2Button);
        radioPanel.add(layout3Button);
        
        JPanel submitPanel = new JPanel();
        whole.setLayout(new BoxLayout(whole, BoxLayout.Y_AXIS));


        whole.add(radioPanel);
        
        JPanel panel = new JPanel();
        whole.add(panel);
        
        Component horizontalStrut_1 = Box.createHorizontalStrut(20);
        panel.add(horizontalStrut_1);
        
        
        
        
        

        //Set up the picture label.
        picture = new JLabel(createImageIcon("/images/"
                                             + layout0
                                             + ".gif"));
        panel.add(picture);
        picture.setHorizontalAlignment(SwingConstants.LEADING);
        
                //The preferred size is hard-coded to be the width of the
                //widest image and the height of the tallest image.
                //A real program would compute this.
                picture.setPreferredSize(new Dimension(177, 200));
                
                Component horizontalStrut_2 = Box.createHorizontalStrut(40);
                panel.add(horizontalStrut_2);
                
                Component horizontalGlue = Box.createHorizontalGlue();
                panel.add(horizontalGlue);
        whole.add(submitPanel);
		submitPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Component horizontalStrut = Box.createHorizontalStrut(200);
		submitPanel.add(horizontalStrut);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(this);
		
		
		        
				submitPanel.add(cancelButton);
				
						JButton chooseButton = new JButton("Choose");
						chooseButton.setActionCommand("Choose");
						
						chooseButton.addActionListener(this);
						submitPanel.add(chooseButton);
        whole.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        getContentPane().add(whole);
        setLocation(300, 100);
    }
    
 

    /** Listens to the radio buttons. */
    public void actionPerformed(ActionEvent e) {
    	//TODO change the canvas
    	String cmd =  e.getActionCommand();
    	if(cmd == "Cancel"){
    		submit = "Cancel";
    		this.dispose();
    	}else if(cmd == "Choose"){
    		System.out.println(submit);
    		if(submit == "0"){
    			canvas.addFirstPanel();
    			
    		}else if(submit == "1"){
    			canvas.setLayout1();
    		}else if(submit == "2"){
    			canvas.setLayout2();
    		}else if(submit == "3"){
    			canvas.setLayout3();
    		}
    		
    		this.dispose();
    	}else{
    		picture.setIcon(createImageIcon("/images/"
                                        + e.getActionCommand()
                                        + ".gif"));
    		submit = e.getActionCommand();
    	}
    }
    public String getSubmit(){
    	return submit;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = newMenuItem.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}
