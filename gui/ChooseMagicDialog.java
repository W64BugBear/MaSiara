package gui;

import guidialogelements.BitmapComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import background.Constants;

public class ChooseMagicDialog 
	extends JDialog
implements ActionListener, MouseListener, KeyListener
{
	int choice = -1;
	boolean ctrlPressed = false;
	
	JButton mageButton, natureWizardButton, intuitiveWizardButton;
	BitmapComponent mageBmp, naturewizBmp, intuitiveWizBmp;
	
	JButton saveButton;
	MainWindow father;
	
	JLabel newLabel, uHaveLabel, ugotNothingLabel;
	
	static final int IMG_HEIGHT = 330;
	static final int IMG_WIDTH = 200;
	
	static final int IMG_HEIGHT2 = 100;
	static final int IMG_WIDTH2 = 100;
	
	static final int DELETE_BUTTON_DIMENSION = 15;
	
	int chosenBranches = 0;
	
	LinkedList<JButton> branchButtons;
	LinkedList<BitmapComponent> branchBmps;
	
	int windowWidth = 0;
	
	public ChooseMagicDialog(MainWindow owner)
	{
		super(owner, "Magie", true);		// modal Dialog
		father = owner;
		setLayout(null);
		
		branchButtons = new LinkedList<JButton>();
		branchBmps = new LinkedList<BitmapComponent>();
		
		newLabel = new JLabel("einen neuen Zweig wählen: ");
		uHaveLabel = new JLabel("bereits gewählte Zweige: ");
		ugotNothingLabel = new JLabel("(keine)");
		
		mageBmp= new BitmapComponent(this);
		mageBmp.setPicture("mage");
		naturewizBmp= new BitmapComponent(this);
		naturewizBmp.setPicture("naturew");
		intuitiveWizBmp= new BitmapComponent(this);
		intuitiveWizBmp.setPicture("intuitive");
		
		mageButton = new JButton("Thaumaturgie");
		natureWizardButton = new JButton("Naturmagie");
		intuitiveWizardButton = new JButton("Intuitive Magie");
		saveButton = new JButton("abbrechen");
		
		mageButton.addActionListener(this);
		mageButton.addKeyListener(this);
		mageBmp.addMouseListener(this);
		natureWizardButton.addActionListener(this);
		natureWizardButton.addKeyListener(this);
		naturewizBmp.addMouseListener(this);
		intuitiveWizardButton.addActionListener(this);
		intuitiveWizardButton.addKeyListener(this);
		intuitiveWizBmp.addMouseListener(this);
		
		saveButton.addActionListener(this);
		saveButton.addKeyListener(this);
		
		newLabel.setBounds(Constants.OFFSETX, Constants.OFFSETY, IMG_WIDTH*3, Constants.HEIGHT);
				
		mageBmp.setBounds(Constants.OFFSETX, Constants.OFFSETY+Constants.SPACEY+Constants.HEIGHT, IMG_WIDTH, IMG_HEIGHT);
		mageButton.setBounds(Constants.OFFSETX+IMG_WIDTH/2-Constants.WIDTH1/2, 	Constants.OFFSETY+Constants.SPACEY*2+IMG_HEIGHT+Constants.HEIGHT, Constants.WIDTH1, Constants.HEIGHT);
		naturewizBmp.setBounds(Constants.OFFSETX+Constants.SPACEX+IMG_WIDTH, 	Constants.OFFSETY+Constants.SPACEY+Constants.HEIGHT, IMG_WIDTH, IMG_HEIGHT);
		natureWizardButton.setBounds(Constants.OFFSETX+IMG_WIDTH/2-Constants.WIDTH1/2+Constants.SPACEX+IMG_WIDTH, 	Constants.OFFSETY+Constants.SPACEY*2+IMG_HEIGHT+Constants.HEIGHT, Constants.WIDTH1, Constants.HEIGHT);
		intuitiveWizBmp.setBounds(Constants.OFFSETX+Constants.SPACEX*2+IMG_WIDTH+IMG_WIDTH, Constants.OFFSETY+Constants.SPACEY+Constants.HEIGHT, IMG_WIDTH, IMG_HEIGHT);
		intuitiveWizardButton.setBounds(Constants.OFFSETX+IMG_WIDTH*2+IMG_WIDTH/2-Constants.WIDTH1/2+Constants.SPACEX*2, Constants.OFFSETY+Constants.SPACEY+Constants.HEIGHT+Constants.SPACEY+IMG_HEIGHT, Constants.WIDTH1, Constants.HEIGHT);
		
		uHaveLabel.setBounds(Constants.OFFSETX, intuitiveWizardButton.getY() + intuitiveWizardButton.getHeight() + Constants.SPACEY*2, IMG_WIDTH*3, Constants.HEIGHT);
		ugotNothingLabel.setBounds(Constants.OFFSETX, intuitiveWizardButton.getY() + Constants.HEIGHT*2 + Constants.SPACEY*4, IMG_WIDTH*3, Constants.HEIGHT);
		
		saveButton.setBounds(Constants.OFFSETX+IMG_WIDTH/2-Constants.WIDTH1/2+Constants.SPACEX+IMG_WIDTH, uHaveLabel.getY() + Constants.HEIGHT*2 + Constants.SPACEY*6 + IMG_HEIGHT2, Constants.WIDTH1, Constants.HEIGHT);
		
		add(newLabel);
		add(ugotNothingLabel);
		add(uHaveLabel);
		add(mageButton);
		add(mageBmp);
		add(intuitiveWizardButton);
		add(intuitiveWizBmp);
		add(natureWizardButton);
		add(naturewizBmp);
		
		add(saveButton);

		for (int i = 0; i < Constants.MAGICBRANCHES; i++)
		{
			if (father.getHero().getMagic()[i])
			{
				JButton button = new JButton(father.getHero().getMagicBranchTitles()[i]);
				button.setBounds(ugotNothingLabel.getX()+chosenBranches*(IMG_WIDTH2+Constants.SPACEX*2), ugotNothingLabel.getY(), IMG_WIDTH2, Constants.HEIGHT);
				add(button);
				button.addActionListener(this);
				button.addKeyListener(this);
				button.setActionCommand(""+(i));
				this.branchButtons.add(button);
				
				BitmapComponent bmp = new BitmapComponent(this);
				bmp.setPicture(father.getHero().getMagicBranchPaths()[i]);
				bmp.setBounds(ugotNothingLabel.getX()+chosenBranches*(IMG_WIDTH2+Constants.SPACEX*2), ugotNothingLabel.getY()+Constants.HEIGHT+Constants.SPACEY, IMG_WIDTH2, IMG_HEIGHT2);
//				add(bmp);
				bmp.addMouseListener(this);
				this.branchBmps.add(bmp);
				
				JButton remove = new JButton()	{
					public void paint (Graphics g)
					{
						Color col = new Color(238, 238, 238);
						g.setColor(col);
						g.fillRect(0,  0, DELETE_BUTTON_DIMENSION, DELETE_BUTTON_DIMENSION);
						g.setColor(Color.BLACK);
						g.drawRect(0,  0, DELETE_BUTTON_DIMENSION-1, DELETE_BUTTON_DIMENSION-1);
						g.drawLine(3, 3, DELETE_BUTTON_DIMENSION-3, DELETE_BUTTON_DIMENSION-3);
						g.drawLine(3, DELETE_BUTTON_DIMENSION-3, DELETE_BUTTON_DIMENSION-3, 3);
					}
				};
				remove.setBounds(bmp.getX()+bmp.getWidth()-DELETE_BUTTON_DIMENSION, bmp.getY()+bmp.getHeight()-DELETE_BUTTON_DIMENSION, DELETE_BUTTON_DIMENSION, DELETE_BUTTON_DIMENSION);
				add(remove);
				remove.addActionListener(this);
				remove.addKeyListener(this);
				remove.setActionCommand("x"+(i));
				remove.setVisible(true);
				
				add(bmp);		// bitmap must be added after remove button otherewise the latter is invisible
				chosenBranches++;
				
				if (ugotNothingLabel.getX()+chosenBranches*(IMG_WIDTH2+Constants.SPACEX*2) > windowWidth)
					windowWidth = ugotNothingLabel.getX()+chosenBranches*(IMG_WIDTH2+Constants.SPACEX*2);
			}
		}
		
		if (chosenBranches > 0)
		{
			remove(ugotNothingLabel);
			branchButtons.getFirst().requestFocus();
		}
		
		windowWidth = intuitiveWizBmp.getX()+IMG_WIDTH+Constants.OFFSETX*2;
	}
	
	public void mouseClicked(MouseEvent me)
	{
		if (me.getSource() == mageBmp)
			choice = Constants.MAGE;
		if (me.getSource() == intuitiveWizBmp)
			choice = Constants.INTUITIVEWIZARD;
		if (me.getSource() == naturewizBmp)
			choice = Constants.NATUREWIZARD;
		
		for (int i = 0; i < chosenBranches; i++)
		{
			if (me.getSource() == branchBmps.get(i))
			{
				choice = Integer.parseInt(branchButtons.get(i).getActionCommand());
			}
		}
		
		dispose();
	}
	
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	public void mousePressed(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == saveButton)
		{
			dispose();
			choice = -1;
			return;
		}
		
		if (ae.getSource() == mageButton)
			choice = Constants.MAGE;
		else
			if (ae.getSource() == intuitiveWizardButton)
				choice = Constants.INTUITIVEWIZARD;
			else
				if (ae.getSource() == natureWizardButton)
					choice = Constants.NATUREWIZARD;
				else
					if (ae.getSource() instanceof JButton)
						try{ 
							choice = Integer.parseInt(((JButton)(ae.getSource())).getActionCommand());
						} catch (NumberFormatException nfe)
						{	// the delete buttons cannot be parsed
							Object[] options = {"Weiß ich", "Ups, lieber doch nicht!"};
							int n = JOptionPane.showOptionDialog(father,
							    "Damit würdest Du die komplette Begabung löschen!",
							    "Bist Du Dir sicher?",
							    JOptionPane.YES_NO_OPTION,
							    JOptionPane.QUESTION_MESSAGE,
							    null,     //do not use a custom Icon
							    options,  //the titles of buttons
							    options[0]); //default button title
							if (n == 0)
								father.getWizardPanel().removeMagicAbility();
						}
		
		dispose();
	}
	
	// this is NOT JComponent.getPreferredSize()!!!
	public Dimension getPreferredSize()
	{
		return new Dimension(windowWidth, 
				saveButton.getY()+Constants.HEIGHT+Constants.SPACEY*3+45);
		// i think the 45 are the title
	}
	
	public int getChoice()
	{
		switch (choice)
		{
		case Constants.MAGE: MageDialog md = new MageDialog(father, false);
			md.setLocation(getX()+100, getY()+100);
			md.setSize(md.getPreferredSize());
			md.setVisible(true);
			choice = md.getChoice();
			break;
		case Constants.NATUREWIZARD: NatureWizardDialog nwd = new NatureWizardDialog(father, false);
			nwd.setLocation(getX()+100, getY()+100);
			nwd.setSize(nwd.getPreferredSize());
			nwd.setVisible(true);
			choice = nwd.getChoice();break;
		case Constants.INTUITIVEWIZARD:
			IntuitiveWizardDialog iwd = new IntuitiveWizardDialog(father, false);
			iwd.setLocation(getX()+100, getY()+100);
			iwd.setSize(iwd.getPreferredSize());
			iwd.setVisible(true);
			choice = iwd.getChoice();
			break;
		}
			
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