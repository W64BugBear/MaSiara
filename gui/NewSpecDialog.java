package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class NewSpecDialog 
	extends JDialog
	implements ActionListener, KeyListener
{
	JTextField specText;
	JTextField specValue;
	boolean ctrlPressed = false;
	
	public NewSpecDialog(MainWindow owner)
	{
		super(owner, true);	// modal Dialog
		setLayout(new BorderLayout());
		
		JPanel north = new JPanel();
		north.setLayout(new BorderLayout());
		add(north, BorderLayout.NORTH);
		
		specText = new JTextField(12);
		specText.addKeyListener(this);
		specText.addActionListener(this);
		north.add(specText, BorderLayout.WEST);
		north.add(new JLabel(", "), BorderLayout.CENTER);
		
		specValue = new JTextField(2);
		specValue.addKeyListener(this);
		specValue.addActionListener(this);
		north.add(specValue, BorderLayout.EAST);
		
		JButton button = new JButton("OK");
		button.addActionListener(this);
		button.addKeyListener(this);
		add(button, BorderLayout.SOUTH);
		
		pack();
	}
	
	public NewSpecDialog(MainWindow owner, String specText, String specValue)
	{
		this(owner);
		this.specText.setText(specText);
		this.specValue.setText(specValue);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		dispose();
	}
	
	public String getSpecText()
	{
		return specText.getText();
	}
	
	public int getSpecValue()
	{
		try {
		return Integer.parseInt(specValue.getText());
		} catch (NumberFormatException nfe)
		{
			return -1;
		}
	}
	
	public void keyTyped(KeyEvent ke){/*nothing*/}
	
	public void keyPressed(KeyEvent ke)
	{
		if (ke.getKeyCode() == ke.VK_CONTROL)
			ctrlPressed = true;
		else
		{
			if (ctrlPressed && ke.getKeyCode() == ke.VK_S)
				dispose();
			ctrlPressed = false;
		}
		
		if (ke.getKeyCode() == ke.VK_ESCAPE)
		{
			this.specText.setText("");
			dispose();
		}
	}
	
	public void keyReleased(KeyEvent ke){
		ctrlPressed = false;
	}	
}
