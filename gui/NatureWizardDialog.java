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

import com.sun.corba.se.pept.transport.ContactInfo;

import background.Constants;

public class NatureWizardDialog 
	extends JDialog
implements ActionListener, MouseListener, KeyListener
{
	int choice = -1;
	boolean ctrlPressed = false;
	
	JButton changerButton, beastButton, plantButton, elementButton, shamanButton, orelianButton, witchButton, shattanButton, berserkerButton;
	BitmapComponent changerBmp, beastBmp, plantBmp, elementBmp, shamanBmp, orelianBmp, witchBmp, shattanBmp, berserkerBmp;
	
	JButton saveButton;
	MainWindow father;
	
	static final int IMG_HEIGHT = 100;
	static final int IMG_WIDTH = 100;
	
	public NatureWizardDialog(MainWindow owner, boolean isFree)
	{
		super(owner, true);		// modal Dialog
		father = owner;
		setLayout(null);
						
		changerBmp= new BitmapComponent(this);
		changerBmp.setPicture("bodych");
		beastBmp= new BitmapComponent(this);
		beastBmp.setPicture("beastMaster");
		plantBmp= new BitmapComponent(this);
		plantBmp.setPicture("plantmaster");
		elementBmp= new BitmapComponent(this);
		elementBmp.setPicture("element");
		shamanBmp= new BitmapComponent(this);
		shamanBmp.setPicture("shaman2");
		orelianBmp= new BitmapComponent(this);
		orelianBmp.setPicture("orelian");
		witchBmp= new BitmapComponent(this);
		witchBmp.setPicture("witch");
		shattanBmp= new BitmapComponent(this);
		shattanBmp.setPicture("shattan");
		berserkerBmp = new BitmapComponent(this);
		berserkerBmp.setPicture("berserk");
		
		changerButton = new JButton("Gestaltwandler");
		beastButton = new JButton("Tierherr");
		plantButton = new JButton("Pflanzenherr");
		elementButton = new JButton("Elementarist");
		shamanButton = new JButton("Schamane");
		orelianButton = new JButton("Orelier");
		witchButton = new JButton("Hexe");
		shattanButton = new JButton("Schettene");
		berserkerButton = new JButton("Berserker");
		saveButton = new JButton("abbrechen");
		
		changerButton.addActionListener(this);
		changerButton.addKeyListener(this);
		changerBmp.addMouseListener(this);
		beastButton.addActionListener(this);
		beastButton.addKeyListener(this);
		beastBmp.addMouseListener(this);
		plantButton.addActionListener(this);
		plantButton.addKeyListener(this);
		plantBmp.addMouseListener(this);
		elementButton.addActionListener(this);
		elementButton.addKeyListener(this);
		elementBmp.addMouseListener(this);
		shamanButton.addActionListener(this);
		shamanButton.addKeyListener(this);
		shamanBmp.addMouseListener(this);
		orelianButton.addActionListener(this);
		orelianButton.addKeyListener(this);
		orelianBmp.addMouseListener(this);
		witchButton.addActionListener(this);
		witchButton.addKeyListener(this);
		witchBmp.addMouseListener(this);
		shattanButton.addActionListener(this);
		shattanButton.addKeyListener(this);
		shattanBmp.addMouseListener(this);
		berserkerButton.addActionListener(this);
		berserkerButton.addKeyListener(this);
		berserkerBmp.addMouseListener(this);
		
		saveButton.addActionListener(this);
		saveButton.addKeyListener(this);
		
		changerBmp.setBounds(Constants.OFFSETX, Constants.OFFSETY, IMG_WIDTH, IMG_HEIGHT);
		changerButton.setBounds(Constants.OFFSETX+IMG_WIDTH/2-Constants.WIDTH2/2, Constants.OFFSETY+Constants.SPACEY+IMG_HEIGHT, Constants.WIDTH2, Constants.HEIGHT);
		beastBmp.setBounds(Constants.OFFSETX+Constants.SPACEX+IMG_WIDTH, Constants.OFFSETY, IMG_WIDTH, IMG_HEIGHT);
		beastButton.setBounds(Constants.OFFSETX+IMG_WIDTH/2-Constants.WIDTH2/2+Constants.SPACEX+IMG_WIDTH, Constants.OFFSETY+Constants.SPACEY+IMG_HEIGHT, Constants.WIDTH2, Constants.HEIGHT);
		plantBmp.setBounds(Constants.OFFSETX+(Constants.SPACEX+IMG_WIDTH)*2, Constants.OFFSETY, IMG_WIDTH, IMG_HEIGHT);
		plantButton.setBounds(Constants.OFFSETX+(Constants.SPACEX+IMG_WIDTH)*2+IMG_WIDTH/2-Constants.WIDTH2/2, Constants.OFFSETY+Constants.SPACEY+IMG_HEIGHT, Constants.WIDTH2, Constants.HEIGHT);
		
		elementBmp.setBounds(Constants.OFFSETX, Constants.OFFSETY+Constants.HEIGHT+IMG_HEIGHT+Constants.SPACEY*2, IMG_WIDTH, IMG_HEIGHT);
		elementButton.setBounds(Constants.OFFSETX+IMG_WIDTH/2-Constants.WIDTH2/2, Constants.OFFSETY+Constants.HEIGHT+Constants.SPACEY+IMG_HEIGHT+IMG_HEIGHT+Constants.SPACEY*2, Constants.WIDTH2, Constants.HEIGHT);
		shamanBmp.setBounds(Constants.OFFSETX+Constants.SPACEX+IMG_WIDTH, Constants.OFFSETY+Constants.HEIGHT+IMG_HEIGHT+Constants.SPACEY*2, IMG_WIDTH, IMG_HEIGHT);
		shamanButton.setBounds(Constants.OFFSETX+Constants.SPACEX+IMG_WIDTH+IMG_WIDTH/2-Constants.WIDTH2/2, Constants.OFFSETY+Constants.HEIGHT+Constants.SPACEY+IMG_HEIGHT+IMG_HEIGHT+Constants.SPACEY*2, Constants.WIDTH2, Constants.HEIGHT);
		orelianBmp.setBounds(Constants.OFFSETX+(Constants.SPACEX+IMG_WIDTH)*2, Constants.OFFSETY+Constants.HEIGHT+IMG_HEIGHT+Constants.SPACEY*2, IMG_WIDTH, IMG_HEIGHT);
		orelianButton.setBounds(Constants.OFFSETX+(Constants.SPACEX+IMG_WIDTH)*2+IMG_WIDTH/2-Constants.WIDTH2/2, Constants.OFFSETY+Constants.HEIGHT+IMG_HEIGHT+Constants.SPACEY*2+Constants.SPACEY+IMG_HEIGHT, Constants.WIDTH2, Constants.HEIGHT);
		
		witchBmp.setBounds(Constants.OFFSETX, Constants.OFFSETY+(IMG_HEIGHT+Constants.SPACEY*2+Constants.HEIGHT)*2, IMG_WIDTH, IMG_HEIGHT);
		witchButton.setBounds(Constants.OFFSETX+IMG_WIDTH/2-Constants.WIDTH2/2, Constants.OFFSETY+Constants.SPACEY+IMG_HEIGHT+(IMG_HEIGHT+Constants.SPACEY*2+Constants.HEIGHT)*2, Constants.WIDTH2, Constants.HEIGHT);
		shattanBmp.setBounds(Constants.OFFSETX+Constants.SPACEX+IMG_WIDTH, Constants.OFFSETY+(IMG_HEIGHT+Constants.SPACEY*2+Constants.HEIGHT)*2, IMG_WIDTH, IMG_HEIGHT);
		shattanButton.setBounds(Constants.OFFSETX+Constants.SPACEX+IMG_WIDTH+IMG_WIDTH/2-Constants.WIDTH2/2, Constants.OFFSETY+Constants.SPACEY+IMG_HEIGHT+(IMG_HEIGHT+Constants.SPACEY*2+Constants.HEIGHT)*2, Constants.WIDTH2, Constants.HEIGHT);
		berserkerBmp.setBounds(Constants.OFFSETX+(Constants.SPACEX+IMG_WIDTH)*2, Constants.OFFSETY+(IMG_HEIGHT+Constants.SPACEY*2+Constants.HEIGHT)*2, IMG_WIDTH, IMG_HEIGHT);
		berserkerButton.setBounds(Constants.OFFSETX+(Constants.SPACEX+IMG_WIDTH)*2+IMG_WIDTH/2-Constants.WIDTH2/2, Constants.OFFSETY+(IMG_HEIGHT+Constants.SPACEY*2+Constants.HEIGHT)*2+Constants.SPACEY+IMG_HEIGHT, Constants.WIDTH2, Constants.HEIGHT);
		
		saveButton.setBounds(Constants.OFFSETX+Constants.SPACEX+IMG_WIDTH+IMG_WIDTH/2, Constants.OFFSETY+(IMG_HEIGHT+Constants.SPACEY*2+Constants.HEIGHT)*2+Constants.SPACEY*2+Constants.HEIGHT*2+IMG_HEIGHT, Constants.WIDTH1, Constants.HEIGHT);
		
		add(changerButton);
		add(changerBmp);
		add(beastButton);
		add(beastBmp);
		add(plantButton);
		add(plantBmp);
		add(elementButton);
		add(elementBmp);
		add(shamanButton);
		add(shamanBmp);
		add(orelianButton);
		add(orelianBmp);
		add(witchButton);
		add(witchBmp);
		add(shattanButton);
		add(shattanBmp);
		add(berserkerButton);
		add(berserkerBmp);
		
		add(saveButton);
	}
	
	public void mouseClicked(MouseEvent me)
	{
		if (me.getSource() == changerBmp)
			choice = Constants.BODYCHANGER;
		if (me.getSource() == beastBmp)
			choice = Constants.BEASTMASTER;
		if (me.getSource() == plantBmp)
			choice = Constants.PLANTMASTER;
		if (me.getSource() == elementBmp)
			choice = Constants.ELEMENTALDRUID;
		if (me.getSource() == shamanBmp)
			choice = Constants.SHAMAN;
		if (me.getSource() == orelianBmp)
			choice = Constants.ORELIAN;
		if (me.getSource() == witchBmp)
			choice = Constants.WITCH;
		if (me.getSource() == shattanBmp)
			choice = Constants.SHATTAN;
		if (me.getSource() == berserkerBmp)
			choice = Constants.BERSERKER;
		
		dispose();
	}
	
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	public void mousePressed(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == changerButton)
			choice = Constants.BODYCHANGER;
		if (ae.getSource() == beastButton)
			choice = Constants.BEASTMASTER;
		if (ae.getSource() == plantButton)
			choice = Constants.PLANTMASTER;
		if (ae.getSource() == elementButton)
			choice = Constants.ELEMENTALDRUID;
		if (ae.getSource() == shamanButton)
			choice = Constants.SHAMAN;
		if (ae.getSource() == orelianButton)
			choice = Constants.ORELIAN;
		if (ae.getSource() == witchButton)
			choice = Constants.WITCH;
		if (ae.getSource() == shattanButton)
			choice = Constants.SHATTAN;
		if (ae.getSource() == berserkerButton)
			choice = Constants.BERSERKER;
		
		dispose();
	}
	
	// this is NOT JComponent.getPreferredSize()!!!
	public Dimension getPreferredSize()
	{
		return new Dimension(berserkerBmp.getX()+IMG_WIDTH+Constants.OFFSETX*2, 
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