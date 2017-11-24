package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import background.Constants;
import character.Item;

public class NewItemDialog 
	extends JDialog
	implements ActionListener, KeyListener
{
	JLabel nameLbl, worthLbl;
	JTextField nameTf, worthTf;
	boolean ctrlPressed = false;
	boolean cancel = false;
	MainWindow owner;
	
	public NewItemDialog(MainWindow owner)
	{
		super(owner, true);		// modal Dialog
		this.owner = owner;
		
		setTitle("neues Besitztum");
		
		nameLbl = new JLabel("Name");
		worthLbl = new JLabel("Wert");
		
		nameTf = new JTextField();
		worthTf = new JTextField();
		
		JButton button = new JButton("OK");
		JButton buttonCancel = new JButton("Abbrechen");
		
		setLayout(null);
		
		nameLbl.setBounds(Constants.OFFSETX, Constants.OFFSETY, Constants.WIDTH2, Constants.HEIGHT);
		worthLbl.setBounds(Constants.OFFSETX + Constants.WIDTH2 + Constants.SPACEX, Constants.OFFSETY, Constants.WIDTH2, Constants.HEIGHT);
		
		nameTf.setBounds(Constants.OFFSETX, Constants.OFFSETY + Constants.HEIGHT + Constants.SPACEY, Constants.WIDTH2, Constants.HEIGHT);
		worthTf.setBounds(Constants.OFFSETX + Constants.WIDTH2 + Constants.SPACEX, Constants.OFFSETY + Constants.HEIGHT + Constants.SPACEY, Constants.WIDTH2, Constants.HEIGHT);

		button.setBounds(Constants.OFFSETX, Constants.OFFSETY + Constants.HEIGHT + Constants.SPACEY + Constants.HEIGHT + Constants.SPACEY, Constants.WIDTH2, Constants.HEIGHT);
		buttonCancel.setBounds(Constants.OFFSETX + Constants.WIDTH2 + Constants.SPACEX, Constants.OFFSETY + Constants.HEIGHT + Constants.SPACEY + Constants.HEIGHT + Constants.SPACEY, Constants.WIDTH2, Constants.HEIGHT);
		button.addActionListener(this);
		button.addKeyListener(this);
		buttonCancel.addActionListener(this);
		buttonCancel.addKeyListener(this);
		
		nameTf.addActionListener(this);
		nameTf.addKeyListener(this);
	
		worthTf.addKeyListener(this);
		worthTf.addActionListener(this);
		
		add(nameLbl);
		add(worthLbl);
		add(nameTf);
		add(worthTf);
		add(button);
		add(buttonCancel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getActionCommand().startsWith("A"))
			cancel = true;
		dispose();
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
			cancel = true;
			dispose();
		}
	}
	
	public void keyReleased(KeyEvent ke){
		ctrlPressed = false;
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(Constants.WIDTH2*2 + Constants.OFFSETX*2 + Constants.SPACEX, 45+Constants.OFFSETY*2 + Constants.HEIGHT*3 + Constants.SPACEY*2);
	}
	
	public Item getItem()
	{
		if (cancel)
			return null;
		else
			try{
				return new Item(nameTf.getText(), Double.parseDouble(worthTf.getText()));	
			} catch (NumberFormatException nfe)
			{
				if (worthTf.getText().equals(""))
					owner.getStatusLabel().setText("Gratis is 0. GAR NIX eingeben is nich!");
				else
					owner.getStatusLabel().setText("Ein "+nameTf.getText()+" kostet also "+worthTf.getText()+"??");
				return null;
			}
			
	}
}
