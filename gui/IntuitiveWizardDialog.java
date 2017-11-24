package gui;

import guidialogelements.BitmapComponent;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.event.TreeSelectionListener;

import background.Constants;

public class IntuitiveWizardDialog 
	extends JDialog
implements ActionListener, MouseListener, KeyListener
{
	int choice = -1;
	boolean ctrlPressed = false;
	
	JButton intSorcButton, freeWizButton, empatheButton, shapeChangerButton, devilsChildButton;
	BitmapComponent intSorcBmp, freeWizBmp, empatheBmp, shapeChangerBmp, devilsChildBmp;
	
	JButton saveButton;
	MainWindow father;
	
	static final int IMG_HEIGHT = 100;
	static final int IMG_WIDTH = 100;
	
	public IntuitiveWizardDialog(MainWindow owner, boolean isFree)
	{
		super(owner, true);		// modal Dialog
		father = owner;
		setLayout(null);
				
		intSorcBmp= new BitmapComponent(this);
		intSorcBmp.setPicture("intsorc");
		freeWizBmp= new BitmapComponent(this);
		freeWizBmp.setPicture("freewiz");
		empatheBmp= new BitmapComponent(this);
		empatheBmp.setPicture("empathe");
		shapeChangerBmp= new BitmapComponent(this);
		shapeChangerBmp.setPicture("shapech");
		devilsChildBmp= new BitmapComponent(this);
		devilsChildBmp.setPicture("devilsch");
		
		intSorcButton = new JButton("int. Hexer");
		freeWizButton = new JButton("Freizauberer");
		empatheButton = new JButton("Empath");
		shapeChangerButton = new JButton("Formwandler");
		devilsChildButton = new JButton("Teufelskind");
		saveButton = new JButton("abbrechen");
		
		intSorcButton.addActionListener(this);
		intSorcButton.addKeyListener(this);
		intSorcBmp.addMouseListener(this);
		freeWizButton.addActionListener(this);
		freeWizButton.addKeyListener(this);
		freeWizBmp.addMouseListener(this);
		empatheButton.addActionListener(this);
		empatheButton.addKeyListener(this);
		empatheBmp.addMouseListener(this);
		shapeChangerButton.addActionListener(this);
		shapeChangerButton.addKeyListener(this);
		shapeChangerBmp.addMouseListener(this);
		devilsChildButton.addActionListener(this);
		devilsChildButton.addKeyListener(this);
		devilsChildBmp.addMouseListener(this);
		
		saveButton.addActionListener(this);
		saveButton.addKeyListener(this);
		
		freeWizBmp.setBounds(Constants.OFFSETX, Constants.OFFSETY, IMG_WIDTH, IMG_HEIGHT);
		freeWizButton.setBounds(Constants.OFFSETX+IMG_WIDTH/2-Constants.WIDTH2/2, Constants.OFFSETY+Constants.SPACEY+IMG_HEIGHT, Constants.WIDTH2, Constants.HEIGHT);
		intSorcBmp.setBounds(Constants.OFFSETX+Constants.SPACEX+IMG_WIDTH, Constants.OFFSETY, IMG_WIDTH, IMG_HEIGHT);
		intSorcButton.setBounds(Constants.OFFSETX+IMG_WIDTH/2-Constants.WIDTH2/2+Constants.SPACEX+IMG_WIDTH, Constants.OFFSETY+Constants.SPACEY+IMG_HEIGHT, Constants.WIDTH2, Constants.HEIGHT);
		empatheBmp.setBounds(Constants.OFFSETX+(Constants.SPACEX+IMG_WIDTH)*2, Constants.OFFSETY, IMG_WIDTH, IMG_HEIGHT);
		empatheButton.setBounds(Constants.OFFSETX+(Constants.SPACEX+IMG_WIDTH)*2+IMG_WIDTH/2-Constants.WIDTH2/2, Constants.OFFSETY+Constants.SPACEY+IMG_HEIGHT, Constants.WIDTH2, Constants.HEIGHT);
		shapeChangerBmp.setBounds(Constants.OFFSETX+(Constants.SPACEX+IMG_WIDTH)*3, Constants.OFFSETY, IMG_WIDTH, IMG_HEIGHT);
		shapeChangerButton.setBounds(Constants.OFFSETX+(Constants.SPACEX+IMG_WIDTH)*3+IMG_WIDTH/2-Constants.WIDTH2/2, Constants.OFFSETY+Constants.SPACEY+IMG_HEIGHT, Constants.WIDTH2, Constants.HEIGHT);
		devilsChildBmp.setBounds(Constants.OFFSETX+(Constants.SPACEX+IMG_WIDTH)*4, Constants.OFFSETY, IMG_WIDTH, IMG_HEIGHT);
		devilsChildButton.setBounds(Constants.OFFSETX+(Constants.SPACEX+IMG_WIDTH)*4+IMG_WIDTH/2-Constants.WIDTH2/2, Constants.OFFSETY+Constants.SPACEY+IMG_HEIGHT, Constants.WIDTH2, Constants.HEIGHT);
		saveButton.setBounds(Constants.OFFSETX+(Constants.SPACEX+IMG_WIDTH)*2+IMG_WIDTH/2-Constants.WIDTH1/2, Constants.OFFSETY+Constants.SPACEY*2+Constants.HEIGHT*2+IMG_HEIGHT, Constants.WIDTH1, Constants.HEIGHT);
		
		add(intSorcButton);
		add(intSorcBmp);
		add(freeWizButton);
		add(freeWizBmp);
		add(shapeChangerButton);
		add(shapeChangerBmp);
		add(empatheButton);
		add(empatheBmp);
		add(devilsChildButton);
		add(devilsChildBmp);
		
		add(saveButton);
	}
	
	public void mouseClicked(MouseEvent me)
	{
		if (me.getSource() == intSorcBmp)
//			choice = Constants.INTUITIVESORCERER;
		if (me.getSource() == freeWizBmp)
			choice = Constants.FREEWIZARD;
		if (me.getSource() == empatheBmp)
			choice = Constants.EMPATHE;
		if (me.getSource() == shapeChangerBmp)
			choice = Constants.SHAPECHANGER;
		if (me.getSource() == devilsChildBmp)
			choice = Constants.DEVILSCHILD;
		
		dispose();
	}
	
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	public void mousePressed(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
	
	public void actionPerformed(ActionEvent ae)
	{
//		if (ae.getSource() == intSorcButton)
//			choice = Constants.INTUITIVESORCERER;
		if (ae.getSource() == freeWizButton)
			choice = Constants.FREEWIZARD;
		if (ae.getSource() == empatheButton)
			choice = Constants.EMPATHE;
		if (ae.getSource() == shapeChangerButton)
			choice = Constants.SHAPECHANGER;
		if (ae.getSource() == devilsChildButton)
			choice = Constants.DEVILSCHILD;
		
		dispose();
	}
	
	// this is NOT JComponent.getPreferredSize()!!!
	public Dimension getPreferredSize()
	{
		return new Dimension(devilsChildBmp.getX()+IMG_WIDTH+Constants.OFFSETX*2, 
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