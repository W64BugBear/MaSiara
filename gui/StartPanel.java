package gui;

import guidialogelements.Beastinger;
import guidialogelements.ContextSensitiveMenuOnlyNotes;
import guidialogelements.MyLabel;
import guidialogelements.MyTextField;
import guidialogelements.Noteable;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import races.Race;
import races.Vampire;
import races.Werewolf;
import background.Constants;
import background.SerializableNote;
import character.Hero;
import character.Specialization;

public class StartPanel 
extends MyPanel
implements ActionListener, KeyListener, FocusListener, ItemListener
{
	MainWindow father;

	ContextSensitiveMenuOnlyNotes csMenu;
	JComponent notedComponent;
	
	MyLabel nameLbl;
	MyTextField nameTf;
	MyLabel raceLbl;
	JButton raceButton;
	JComboBox manVampWwCB;
	JComboBox sexCB;
	
	Beastinger beastinger;
	JPanel panel; //only to catch Mouse Motion Events
	
	boolean altPressed;
	
	public StartPanel(MainWindow father)
	{
		super(father);
		this.father = father;
		setLayout(null);
				
		
		csMenu = new ContextSensitiveMenuOnlyNotes(this);
		
		nameLbl = new MyLabel("Name: ", 0);
		nameTf = new MyTextField("", 1);
		raceLbl = new MyLabel("(R)asse: ", 2);
		raceButton = new JButton("Eardons Haus");
		beastinger = new Beastinger (this, father);
		panel = new JPanel();
		String[] manVampww = {"Mensch", "Vampir", "Werwolf"};
		manVampWwCB = new JComboBox(manVampww);
		String[] sexStrings = {"männlich", "weiblich"};
		sexCB = new JComboBox(sexStrings);
		
		// adding listeners
		
		nameTf.addKeyListener(this);
		nameTf.addFocusListener(this);
		raceButton.addKeyListener(this);
		raceButton.addActionListener(this);
		panel.addMouseListener(this);
		panel.addMouseMotionListener(beastinger);
		manVampWwCB.addKeyListener(this);
		manVampWwCB.addItemListener(this);
		sexCB.addKeyListener(this);
		sexCB.addItemListener(this);
		
		// positioning
		
		nameLbl.setBounds(Constants.OFFSETX, Constants.OFFSETY, Constants.WIDTH1, Constants.HEIGHT);
		nameTf.setBounds(Constants.OFFSETX+Constants.WIDTH1+Constants.SPACEX, Constants.OFFSETY, Constants.WIDTH1, Constants.HEIGHT);
		
		raceLbl.setBounds(Constants.OFFSETX, Constants.OFFSETY+Constants.SPACEY+Constants.HEIGHT, Constants.WIDTH1, Constants.HEIGHT);
		raceButton.setBounds(Constants.OFFSETX+Constants.WIDTH1+Constants.SPACEX, Constants.OFFSETY+Constants.SPACEY+Constants.HEIGHT, Constants.WIDTH1, Constants.HEIGHT);
		
		manVampWwCB.setBounds(Constants.OFFSETX+Constants.WIDTH1+Constants.SPACEX, Constants.OFFSETY+Constants.SPACEY*2+Constants.HEIGHT*2, Constants.WIDTH1, Constants.HEIGHT);
		sexCB.setBounds(Constants.OFFSETX+Constants.WIDTH1+Constants.SPACEX, Constants.OFFSETY+Constants.SPACEY*3+Constants.HEIGHT*3, Constants.WIDTH1, Constants.HEIGHT);
		
		beastinger.setBounds(Constants.OFFSETX*2, Constants.OFFSETY+Constants.HEIGHT*5+Constants.SPACEY*5, beastinger.getWidth(), beastinger.getHeight());
		panel.setBounds(Constants.OFFSETX*2, Constants.OFFSETY+Constants.HEIGHT*5+Constants.SPACEY*5, beastinger.getWidth(), beastinger.getHeight());
		
		// adding
		
		addComponents();
		
		enableEvents(AWTEvent.MOUSE_EVENT_MASK);
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(raceButton.getX() + raceButton.getWidth() + Constants.OFFSETX, beastinger.getY() + beastinger.getHeight() + Constants.OFFSETY);
	}
	
	public String checkForErrors()
	{
		return "alles in Butter";
	}

	public void actionPerformed (ActionEvent ae)
	{
		if (ae.getSource() == raceButton)
		{
			chooseRace();
			setGPEPLabel();
			father.pack();
			return;
		}
		try {
			if (ae.getActionCommand().equals("Notiz hinzufügen/bearbeiten"))
			{
				addEditNote(notedComponent);
			}
			
			if (ae.getActionCommand().equals("Notiz löschen"))
			{
				deleteNote(notedComponent);
			}
		} catch (ClassCastException cce)
		{
			// nothing
		} catch (NullPointerException npe)
		{ /*nothing*/ }

		father.getStatusLabel().setText(checkForErrors());
	}
	
	public void mouseClicked (MouseEvent me) {
		super.mouseClicked(me); // react to notes and specs (MyLabel.mouseClicked)
		
		father.getHero().setName(nameTf.getText());
		beastinger.uWereClicked(me.getX(), me.getY());
	} 

	public void mousePressed (MouseEvent me) {}
	public void mouseReleased (MouseEvent me) {}
	public void mouseExited (MouseEvent me) {}
	public void mouseEntered (MouseEvent me) {}

	
	public void keyTyped(KeyEvent ke){
//		father.getHero().setName(nameTf.getText());
	}
	
	public void keyPressed(KeyEvent ke)
	{
		father.getHero().setName(nameTf.getText());
				
		if (ke.getKeyCode() == 525)	// "mouse click" by keyboard
		{
			JComponent component = (JComponent)(ke.getComponent());
			
			csMenu.show(
			component,
			component.getWidth()-3, 
			component.getHeight()-3
			);
				
			notedComponent = component;
		}
		
		if (ke.getKeyCode() == ke.VK_ALT)
			altPressed = true;
		else
		{
			if (altPressed && ke.getKeyCode() == ke.VK_R)
				chooseRace();

			if (altPressed && ke.getKeyCode() == ke.VK_E)
				father.displayPropertiesPanel();
			if (altPressed && ke.getKeyCode() == ke.VK_F)
				father.displaySkillsPanel();
			if (altPressed && ke.getKeyCode() == ke.VK_K)
				father.displayFightPanel();
			if (altPressed && ke.getKeyCode() == ke.VK_B)
				father.displayEquipmentPanel();
			if (altPressed && ke.getKeyCode() == ke.VK_M)
				father.displayMagicDialog();
			
			altPressed = false;
		}
	}
	
	public void keyReleased(KeyEvent ke)
	{
		altPressed = false;
	}
	
	public void focusGained(FocusEvent fe) {}
	
	public void focusLost(FocusEvent fe) {
		father.getHero().setName(nameTf.getText());
	}
	
	public void processMouseEvent(MouseEvent event)
	{		// displays context sensitive menu
		if (event.isPopupTrigger()) {
			csMenu.show(
			event.getComponent(),
			event.getX(),
			event.getY()
			);
			
			notedComponent = getClickedComponent(event.getX(), event.getY());
		}
		super.processMouseEvent(event);
	}

	public void itemStateChanged(ItemEvent ie)
	{
		if (ie.getSource() == manVampWwCB)
		{
			int becomes = ((JComboBox)(ie.getSource())).getSelectedIndex();
			
			if (father.getHero().isVampire())
				Vampire.becomeHuman(father.getHero(), father.getSInf(), father.gpepLabel, father.skillsPanel);
			if (father.getHero().isWerewolf())
				Werewolf.becomeHuman(father.getHero(), father.getSInf(), father.gpepLabel, father.skillsPanel);
			
			if (becomes == 1)
				Vampire.becomeVampire(father.getHero(), father.getSInf(), father.skillsPanel);
			if (becomes == 2)
				Werewolf.becomeWerewolf(father.getHero(), father.getSInf(), father.skillsPanel);
		}
		
		if (ie.getSource() == sexCB)
		{
			int becomes = ((JComboBox)(ie.getSource())).getSelectedIndex();
			
			if (becomes == father.getHero().getSex())
				return;
			
			if (becomes == Hero.SEX_FEMALE)
			{
				father.getHero().getRace().unapplyMale();
				father.getHero().getRace().applyFemale();
			}
			else
			{
				father.getHero().getRace().applyMale();
				father.getHero().getRace().unapplyFemale();
			}
			
			father.getHero().setSex(becomes);
		}
		father.propertiesPanel.actualizeValues();
	}
	
	private void chooseRace()
	{
		
		ChooseRaceDialog crd = new ChooseRaceDialog(father);
		crd.setLocation(getX()+100, getY()+100);
		crd.setSize(crd.getPreferredSize());
		crd.setVisible(true);
		
		Race race = crd.getRace();
				
		if (race == null)
			return;
	
		father.getHero().setSpecialsStr("");	// do not allow ken'thanis to be noble
		father.getSInf().setGpInvestedInSpecials(0);	// and do not let 'em pay for
		
		father.getHero().setName(nameTf.getText());
		
		if (father.getHero().getSex() == Hero.SEX_MALE)
			father.getHero().getRace().unapplyMale();
		else
			father.getHero().getRace().unapplyFemale();	// undo sexmods
	
		father.getHero().getRace().remove(father); // undo racemods
	
		
		father.getHero().setRace(race);
		race.set(father.getHero(), father.getSInf(), father.gpepLabel, father, false);
	
		if (father.getHero().getSex() == Hero.SEX_MALE)
			father.getHero().getRace().applyMale();
		else
			father.getHero().getRace().applyFemale();
		
		raceButton.setText(race.getName());
		
		father.propertiesPanel.actualizeValues();
		return;
	}
	
	public LinkedList getSpecIcons() { return null; }
	public JComponent getDefaultFocusComponent() {
		return nameTf;
	}
	public JComponent getFocusComponentInClickedRow(JComponent comp) {return null; }
	public MyLabel getTopicInClickedRow(JComponent component) {return null; }
	public void save() {}
	
	public void addComponents() {
		add(csMenu);
		add(nameLbl);
		add(nameTf);
		add(raceLbl);
		add(raceButton);
		add(manVampWwCB);
		add(sexCB);
		
		add(beastinger);
		add(panel);
	}
	
	public String serializeYourself() {
		
		String ret = new String("start;");
		
		Noteable[] noteComps = getNoteableComponents();
		for (int i = 0; i < noteComps.length; i++)
		{
			try {
				ret += noteComps[i].getNote().getText() + ";" + noteComps[i].getIndex() + ";";
			} catch (NullPointerException npe)
			{ /* nothing */ }
		}
		
		return ret;
	}
	
	public void deserializeYourself(String s, int version) {
		nameTf.setText(father.getHero().getName());
		raceButton.setText(father.getHero().getRace().getName());
		
		StringTokenizer t = new StringTokenizer(s, ";");
		
		if (!t.nextToken().equals("start"))
		{
			father.getStatusLabel().setText("StartPanel falsch deserialisiert");
			return;
		}
		
		Noteable[] notecomps = getNoteableComponents();
		
		while (t.hasMoreTokens())
		{
			String text = t.nextToken();
			int index = Integer.parseInt(t.nextToken());
			addNote((JComponent)(notecomps[index]), text, true);
		}
	}

	public void deserializeYourself() {
		try {
			nameTf.setText(father.getHero().getName());
		} catch (NullPointerException nle)
		{
			nameTf.setText("");
		}
		raceButton.setText(father.getHero().getRace().getName());
	}
	
	public MyTextField getNameTf() {
		return nameTf;
	}

	public void setNameTf(MyTextField nameTf) {
		this.nameTf = nameTf;
	}
	
	public void setSpecializationByText(String text, String note, Specialization spec)
	{
		
	}
	
	public LinkedList getNotes()
	{
		return father.getRInf().getStartNotesLl();
	}

	public JComboBox getManVampWwCB() {
		return manVampWwCB;
	}

	public void setManVampWwCB(JComboBox manVampWwCB) {
		this.manVampWwCB = manVampWwCB;
	}
}
