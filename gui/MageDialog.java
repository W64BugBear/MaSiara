package gui;

import guidialogelements.BitmapComponent;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import background.Constants;

public class MageDialog 
	extends JDialog
implements ActionListener, MouseListener, KeyListener
{
	int choice = -1;
	boolean ctrlPressed = false;
	
	JButton grandMagicianButton, magicianButton, sorcButton, sorclingButton;
	BitmapComponent grandMagicianBmp, magicianBmp, sorcBmp, sorclingBmp;
	
	JButton saveButton;
	MainWindow father;
	
	static final int IMG_HEIGHT = 100;
	static final int IMG_WIDTH = 100;
	
	public MageDialog(MainWindow owner, boolean isFree)
	{
		super(owner, true);		// modal Dialog
		father = owner;
		setLayout(null);
				
		
		grandMagicianBmp = new BitmapComponent(this);
		grandMagicianBmp.setPicture("grandmagician");
		magicianBmp= new BitmapComponent(this);
		magicianBmp.setPicture("magician");
		sorcBmp= new BitmapComponent(this);
		sorcBmp.setPicture("magesorc");
		sorclingBmp= new BitmapComponent(this);
		sorclingBmp.setPicture("intsorc");
		
		grandMagicianButton = new JButton("Großmagier (4)");
		magicianButton = new JButton("Magier (3)");
		sorcButton = new JButton("Hexer (2)");
		sorclingButton = new JButton("Hexerling (1)");
		saveButton = new JButton("abbrechen");
		
		grandMagicianButton.addActionListener(this);
		grandMagicianButton.addKeyListener(this);
		grandMagicianBmp.addMouseListener(this);
		magicianButton.addActionListener(this);
		magicianButton.addKeyListener(this);
		magicianBmp.addMouseListener(this);
		sorcButton.addActionListener(this);
		sorcButton.addKeyListener(this);
		sorcBmp.addMouseListener(this);
		sorclingButton.addActionListener(this);
		sorclingButton.addKeyListener(this);
		sorclingBmp.addMouseListener(this);
		
		saveButton.addActionListener(this);
		saveButton.addKeyListener(this);
		
		grandMagicianBmp.setBounds(Constants.OFFSETX, Constants.OFFSETY, IMG_WIDTH, IMG_HEIGHT);
		grandMagicianButton.setBounds(Constants.OFFSETX+IMG_WIDTH/2-Constants.WIDTH2/2, Constants.OFFSETY+Constants.SPACEY+IMG_HEIGHT, Constants.WIDTH2, Constants.HEIGHT);
		magicianBmp.setBounds(Constants.OFFSETX+Constants.SPACEX+IMG_WIDTH, Constants.OFFSETY, IMG_WIDTH, IMG_HEIGHT);
		magicianButton.setBounds(Constants.OFFSETX+IMG_WIDTH/2-Constants.WIDTH2/2+Constants.SPACEX+IMG_WIDTH, Constants.OFFSETY+Constants.SPACEY+IMG_HEIGHT, Constants.WIDTH2, Constants.HEIGHT);
		sorcBmp.setBounds(Constants.OFFSETX+(Constants.SPACEX+IMG_WIDTH)*2, Constants.OFFSETY, IMG_WIDTH, IMG_HEIGHT);
		sorcButton.setBounds(Constants.OFFSETX+(Constants.SPACEX+IMG_WIDTH)*2+IMG_WIDTH/2-Constants.WIDTH2/2, Constants.OFFSETY+Constants.SPACEY+IMG_HEIGHT, Constants.WIDTH2, Constants.HEIGHT);
		sorclingBmp.setBounds(Constants.OFFSETX+(Constants.SPACEX+IMG_WIDTH)*3, Constants.OFFSETY, IMG_WIDTH, IMG_HEIGHT);
		sorclingButton.setBounds(Constants.OFFSETX+(Constants.SPACEX+IMG_WIDTH)*3+IMG_WIDTH/2-Constants.WIDTH2/2, Constants.OFFSETY+Constants.SPACEY+IMG_HEIGHT, Constants.WIDTH2, Constants.HEIGHT);
		

		saveButton.setBounds(sorclingButton.getX()+IMG_WIDTH/2-Constants.WIDTH1/2, Constants.OFFSETY+Constants.SPACEY*2+Constants.HEIGHT*2+IMG_HEIGHT, Constants.WIDTH1, Constants.HEIGHT);
		
		add(grandMagicianBmp);
		add(grandMagicianButton);
		add(magicianButton);
		add(sorcBmp);
		add(sorcButton);
		add(magicianBmp);
		add(sorclingBmp);
		add(sorclingButton);
		
		add(saveButton);
	}
	
	public void mouseClicked(MouseEvent me)
	{
		if (me.getSource() == grandMagicianBmp)
			choice = Constants.GRANDMAGICIAN;
		if (me.getSource() == magicianBmp)
			choice = Constants.MAGICIAN;
		if (me.getSource() == sorcBmp)
			choice = Constants.SORC;
		if (me.getSource() == sorclingBmp)
			choice = Constants.SORCLING;
		
		dispose();
	}
	
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	public void mousePressed(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == grandMagicianButton)
			choice = Constants.GRANDMAGICIAN;
		if (ae.getSource() == magicianButton)
			choice = Constants.MAGICIAN;
		if (ae.getSource() == sorcButton)
			choice = Constants.SORC;
		if (ae.getSource() == sorclingButton)
			choice = Constants.SORCLING;
		
		dispose();
	}
	
	// this is NOT JComponent.getPreferredSize()!!!
	public Dimension getPreferredSize()
	{
		return new Dimension(sorclingBmp.getX()+IMG_WIDTH+Constants.OFFSETX*2, 
				saveButton.getY()+Constants.HEIGHT+Constants.SPACEY*3+45);
		// i think the 45 are the title
	}
	
	public int getChoice()
	{
		return choice;
	}
	
	public void keyPressed(KeyEvent ke)
	{
		if (ke.getKeyCode() == ke.VK_CONTROL)
			ctrlPressed = true;
		else
		{
			if (ctrlPressed)
				if (ke.getKeyCode() == ke.VK_S)
					dispose();
			ctrlPressed = false;
		}

	}
	
	public void keyReleased(KeyEvent ke) {
		ctrlPressed = false;
	}
	
	public void keyTyped(KeyEvent ke) {}

}