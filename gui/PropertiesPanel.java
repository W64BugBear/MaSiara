package gui;

import guidialogelements.ContextSensitiveMenu;
import guidialogelements.MyButton;
import guidialogelements.MyComboBox;
import guidialogelements.MyLabel;
import guidialogelements.MySpinner;
import guidialogelements.MyTextField;
import guidialogelements.Noteable;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Font;
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
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import races.Dwarves;
import races.Ogres;
import races.Race;
import background.Constants;
import background.SerializableNote;
import character.Hero;
import character.Property;
import character.RaceModifiers;
import character.Specialization;

public class PropertiesPanel 
	extends MyPanel
	implements ActionListener, FocusListener, KeyListener, ItemListener
{
	JComponent notedComponent;	// saves Component which gets a note or specialization
	boolean ctrlPressed = false;	// allows Saving by Ctrl+S
	boolean doNotActualize = false;	// while actualizing JComboBox fires event and wants to actualize values which are using the JComboBox... no more!
									// focusLost may call .requestFocus, which is not very 8-)
	boolean angry = false;			// This program may get angry if you drive it mad!!
	int index = 5;
	
	MainWindow father;
	
	ContextSensitiveMenu csMenu;
	
	JComboBox comboBox;			// saves applicable forms (e. g. Man, Werewolf)
	
	MyLabel valueLabel = new MyLabel("Wert", 0);
	MyLabel raceModLabel = new MyLabel("Rasse/Geschl.", 1);
	MyLabel ownModLabel = new MyLabel("eigene Mods", 2);
	MyLabel totalLabel = new MyLabel("gesamt", 3);
	MyLabel costsLabel = new MyLabel("Kosten", 4);
	
	MyLabel names[] = new MyLabel[14];
	MySpinner values[] = new MySpinner[14];
	MyLabel racemods[] = new MyLabel[14];
	MySpinner ownmods[] = new MySpinner[14];
	MyLabel total[] = new MyLabel[14];
	MyTextField costs[] = new MyTextField[14];
	
	MyLabel namesS[] = new MyLabel[6];	// senses
	MySpinner valuesS[] = new MySpinner[6];
	MyLabel racemodsS[] = new MyLabel[6];
	MySpinner ownmodsS[] = new MySpinner[6];
	MyLabel totalS[] = new MyLabel[6];
	MyTextField costsS[] = new MyTextField[6];
	
	MyComboBox FBVsLabel;
	MyComboBox damBasesLabel;
	MyLabel initBLabel;
	
	MyLabel FBVsVal;
	MyLabel damBasesVal;
	MyLabel initBVal;
	
	MyLabel specialRulesLabel;
	MyLabel specialsLabel;
	JScrollPane specialRulesScrollP;
//	JScrollPane specialsScrollP;
	JTextArea specialRulesTextArea;
//	JTextArea specialsTextArea;
	JScrollPane specialsPanel;	
	JComponent specials;	// received from races.Race instance
	
	MyButton saveButton;

	LinkedList notes;
	LinkedList specIcons;

// constructor
	
	public PropertiesPanel (MainWindow father)
	{
		super(father);
		
		this.father = father;
		
		// Generate elements
		
		csMenu = new ContextSensitiveMenu(this);
		
		String[] topics = {"Mensch"};
		comboBox = new JComboBox(topics);

		Hero temp = new Hero();
		
		for (int i = 0; i < 14; i++)
		{
			names[i] = new MyLabel(temp.getProperties()[i].getNameString(), index++);
			values[i] = new MySpinner("10", index++);
			ownmods[i] = new MySpinner("0", index++);
			racemods[i] = new MyLabel("0", index++);
			total[i] = new MyLabel(index++);
			costs[i] = new MyTextField("", index++);
		}
		
		for (int i = 0; i < 6; i++)
		{
			namesS[i] = new MyLabel(temp.getSenses()[i].getNameString(), index++);
			valuesS[i] = new MySpinner("10", index++);
			ownmodsS[i] = new MySpinner("0", index++);
			racemodsS[i] = new MyLabel("0", index++);
			totalS[i] = new MyLabel(index++);
			costsS[i] = new MyTextField("", index++);
		}
		
		String[] fbvstrings = {"KBW (Gs+Gw+K+S+M)/3", 
				"Bogen (Gs*2+In+Sb+Sicht)/3", 
				"Armbrust (Gs*2+In+Sb+Sicht)/3",
				"Wurfspeer (Gs+Gw+K+S+In)/3",
				"Wurfbeil (Gs*2+Gw+K+In)/3",
				"Seil (Gs*2+Gw+K+In)/3",
				"Wurfmesser (Gs*3+In*2)/3",
				"Schleuderwaffen (Gs*3+Gw+In)/3",
				"Blasrohr (Gs*2+Iz+In+Sb)/3",
				"Geschütze (Gs+Iz*2+In+Sb)/3",
				"Kampfmagie (Gs+Iz+In*2+Sb)/3"
		};
		FBVsLabel = new MyComboBox(fbvstrings, index++);
		String[] damBaseString = {"K", "KGw"};
		damBasesLabel = new MyComboBox(damBaseString, index++);
		initBLabel = new MyLabel("Init-Basis", index++);
		FBVsVal = new MyLabel(concat(calculateFBV()) + " ~ " + Math.round(calculateFBV()), index++);
		damBasesVal = new MyLabel(index++);
		initBVal = new MyLabel(index++);
		
		specialRulesLabel = new MyLabel("Sonderregeln", index++);
		specialsLabel = new MyLabel("Besonderheiten", index++);
		specialRulesTextArea = new JTextArea("keine");
			specialRulesTextArea .setLineWrap(true);
			specialRulesTextArea .setWrapStyleWord(true);
			specialRulesTextArea .setEditable(false);
		
//		specialsTextArea = new JTextArea("keine");
//			specialsTextArea .setLineWrap(true);
//			specialsTextArea .setWrapStyleWord(true);
//			specialsTextArea .setEditable(false);
			
		specialRulesScrollP = new JScrollPane(specialRulesTextArea);
//		specialsScrollP = new JScrollPane(specialsTextArea);
		specialsPanel = new JScrollPane();	// wraps special1GP so that it can easily be exchanged
		
		specials = father.getHero().getRace().specials();
		
		saveButton = new MyButton("Speichern", index++);
		
		notes = father.getRInf().getPropertyNotesLl();
		
		specIcons = father.getRInf().getPropertySpecsLl();
		
		// decorating ^^
		
		comboBox.setVisible(false);
		
		Font font = getFont();
		font = new Font(font.getName(), Font.BOLD, font.getSize()+2);
		
		valueLabel.setFont(font);
		raceModLabel.setFont(font);
		ownModLabel.setFont(font);
		totalLabel.setFont(font);
		costsLabel.setFont(font);
		
		specialRulesLabel.setFont(font);
		specialsLabel.setFont(font);
		
		font = new Font(font.getName(), Font.BOLD, font.getSize()-2);
		
		for (int i = 0; i < 14; i++)
		{
			total[i].setFont(font);
			costs[i].setEditable(false);
		}
		
		for (int i = 0; i < 6; i++)
		{
			totalS[i].setFont(font);
			costsS[i].setEditable(false);
		}
		
		// positioning

		setLayout(null);		// null Manager is tha best!
		
		comboBox.setBounds(Constants.OFFSETX, Constants.OFFSETY, Constants.WIDTH1, Constants.HEIGHT);
		totalLabel.setBounds(Constants.OFFSETX+Constants.WIDTH1+Constants.SPACEX, Constants.OFFSETY, Constants.WIDTH2+Constants.SPACEX, Constants.HEIGHT);		
		valueLabel.setBounds(Constants.OFFSETX+Constants.WIDTH1+Constants.SPACEX+Constants.WIDTH2/2+Constants.SPACEX, Constants.OFFSETY, Constants.WIDTH2, Constants.HEIGHT);
		raceModLabel.setBounds(Constants.OFFSETX+Constants.WIDTH1+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2/2+Constants.SPACEX, Constants.OFFSETY, Constants.WIDTH2, Constants.HEIGHT);
		ownModLabel.setBounds(Constants.OFFSETX+Constants.WIDTH1+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2/2+Constants.SPACEX, Constants.OFFSETY, Constants.WIDTH2, Constants.HEIGHT);
		costsLabel.setBounds(Constants.OFFSETX+Constants.WIDTH1+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2/2+Constants.SPACEX, Constants.OFFSETY, Constants.WIDTH2+Constants.SPACEX, Constants.HEIGHT);
		
		for (int i = 0; i < 14; i++)
		{
			names[i].setBounds(Constants.OFFSETX, Constants.OFFSETY+Constants.HEIGHT+Constants.SPACEY*3+i * (Constants.SPACEY+Constants.HEIGHT), Constants.WIDTH1, Constants.HEIGHT);
			total[i].setBounds(Constants.OFFSETX+Constants.WIDTH1+Constants.SPACEX, Constants.OFFSETY+Constants.HEIGHT+Constants.SPACEY*3+i * (Constants.SPACEY+Constants.HEIGHT), Constants.WIDTH2/2, Constants.HEIGHT);
			values[i].setBounds(Constants.OFFSETX+Constants.WIDTH1+Constants.SPACEX+Constants.WIDTH2/2+Constants.SPACEX, Constants.OFFSETY+Constants.HEIGHT+Constants.SPACEY*3+i * (Constants.SPACEY+Constants.HEIGHT), Constants.WIDTH2, Constants.HEIGHT);
			racemods[i].setBounds(Constants.OFFSETX+Constants.WIDTH1+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2/2+Constants.SPACEX, Constants.OFFSETY+Constants.HEIGHT+Constants.SPACEY*3+i * (Constants.SPACEY+Constants.HEIGHT), Constants.WIDTH2, Constants.HEIGHT);
			ownmods[i].setBounds(Constants.OFFSETX+Constants.WIDTH1+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2/2+Constants.SPACEX, Constants.OFFSETY+Constants.HEIGHT+Constants.SPACEY*3+i * (Constants.SPACEY+Constants.HEIGHT), Constants.WIDTH2, Constants.HEIGHT);
			costs[i].setBounds(Constants.OFFSETX+Constants.WIDTH1+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2/2+Constants.SPACEX, Constants.OFFSETY+Constants.HEIGHT+Constants.SPACEY*3+i * (Constants.SPACEY+Constants.HEIGHT), Constants.WIDTH2/2, Constants.HEIGHT);
		}

		for (int i = 14; i < 20; i++)
		{
			namesS[i-14].setBounds(Constants.OFFSETX, Constants.OFFSETY+Constants.HEIGHT+Constants.SPACEY*3+i * (Constants.SPACEY+Constants.HEIGHT), Constants.WIDTH1, Constants.HEIGHT);
			totalS[i-14].setBounds(Constants.OFFSETX+Constants.WIDTH1+Constants.SPACEX, Constants.OFFSETY+Constants.HEIGHT+Constants.SPACEY*3+i * (Constants.SPACEY+Constants.HEIGHT), Constants.WIDTH2, Constants.HEIGHT);
			valuesS[i-14].setBounds(Constants.OFFSETX+Constants.WIDTH1+Constants.SPACEX+Constants.WIDTH2/2+Constants.SPACEX, Constants.OFFSETY+Constants.HEIGHT+Constants.SPACEY*3+i * (Constants.SPACEY+Constants.HEIGHT), Constants.WIDTH2, Constants.HEIGHT);
			racemodsS[i-14].setBounds(Constants.OFFSETX+Constants.WIDTH1+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2/2+Constants.SPACEX, Constants.OFFSETY+Constants.HEIGHT+Constants.SPACEY*3+i * (Constants.SPACEY+Constants.HEIGHT), Constants.WIDTH2, Constants.HEIGHT);
			ownmodsS[i-14].setBounds(Constants.OFFSETX+Constants.WIDTH1+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2/2+Constants.SPACEX, Constants.OFFSETY+Constants.HEIGHT+Constants.SPACEY*3+i * (Constants.SPACEY+Constants.HEIGHT), Constants.WIDTH2, Constants.HEIGHT);
			costsS[i-14].setBounds(Constants.OFFSETX+Constants.WIDTH1+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2/2+Constants.SPACEX, Constants.OFFSETY+Constants.HEIGHT+Constants.SPACEY*3+i * (Constants.SPACEY+Constants.HEIGHT), Constants.WIDTH2/2, Constants.HEIGHT);
		}
	
		FBVsLabel.setBounds(namesS[5].getX(), namesS[5].getY()+Constants.HEIGHT*2+Constants.SPACEY*2, Constants.WIDTH1, Constants.HEIGHT);
		FBVsVal.setBounds(valuesS[5].getX(), valuesS[5].getY()+Constants.HEIGHT*2+Constants.SPACEY*2, Constants.WIDTH2, Constants.HEIGHT);
		
		damBasesLabel.setBounds(FBVsLabel.getX(), FBVsLabel.getY()+Constants.HEIGHT+Constants.SPACEY, Constants.WIDTH1, Constants.HEIGHT);
		damBasesVal.setBounds(FBVsVal.getX(), FBVsVal.getY()+Constants.HEIGHT+Constants.SPACEY, Constants.WIDTH2, Constants.HEIGHT);
		
		initBLabel.setBounds(FBVsLabel.getX(), FBVsLabel.getY()+2*(Constants.HEIGHT+Constants.SPACEY), Constants.WIDTH1, Constants.HEIGHT);
		initBVal.setBounds(FBVsVal.getX(), FBVsVal.getY()+2*(Constants.HEIGHT+Constants.SPACEY), Constants.WIDTH2, Constants.HEIGHT);
		
		specialRulesLabel.setBounds(racemods[0].getX(), namesS[5].getY() + Constants.HEIGHT + Constants.SPACEY, Constants.WIDTH2*3/2, Constants.HEIGHT);
		specialsLabel.setBounds(ownmods[0].getX() + Constants.WIDTH2/2, specialRulesLabel.getY(), Constants.WIDTH2*3/2+Constants.SPACEX, Constants.HEIGHT);
		specialRulesScrollP.setBounds(specialRulesLabel.getX(), FBVsLabel.getY(), specialRulesLabel.getWidth(), Constants.HEIGHT*6 + Constants.SPACEY*5);
//		specialsScrollP.setBounds(specialsLabel.getX(), damBasesVal.getY(), specialsLabel.getWidth(), Constants.HEIGHT*5 + Constants.SPACEY*4);

		specialsPanel.setBounds(specialsLabel.getX(), FBVsLabel.getY(), specialsLabel.getWidth(), Constants.HEIGHT);
		
		saveButton.setBounds(initBLabel.getX(), initBLabel.getY()+Constants.HEIGHT*3+Constants.SPACEY*5, Constants.WIDTH1, Constants.HEIGHT);
		
		// add components

		addComponents();
		addNote(saveButton, " ", true);
		saveButton.getNote().setVisible(false);
		
		// enables ContextSensitiveMenu
		enableEvents(AWTEvent.MOUSE_EVENT_MASK);
		
		// add listeners
		
		comboBox.addItemListener(this);
		comboBox.addKeyListener(this);
		
		FBVsLabel.addItemListener(this);
		FBVsLabel.addKeyListener(this);
		
		damBasesLabel.addItemListener(this);
		damBasesLabel.addKeyListener(this);
		
		for (int i = 0; i < 14; i++)
		{
			values[i].addActionListener(this);
			values[i].addFocusListener(this);
			values[i].addKeyListener(this);

			ownmods[i].addActionListener(this);
			ownmods[i].addFocusListener(this);
			ownmods[i].addKeyListener(this);

			costs[i].addActionListener(this);
			costs[i].addFocusListener(this);
			costs[i].addKeyListener(this);
		}

		for (int i = 0; i < 6; i++)
		{
			valuesS[i].addActionListener(this);
			valuesS[i].addFocusListener(this);
			valuesS[i].addKeyListener(this);
			
			ownmodsS[i].addActionListener(this);
			ownmodsS[i].addFocusListener(this);
			ownmodsS[i].addKeyListener(this);
			
			costsS[i].addActionListener(this);
			costsS[i].addFocusListener(this);
			costsS[i].addKeyListener(this);

		}
		
		
		specials.addFocusListener(this);
		
		saveButton.addActionListener(this);
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(Constants.OFFSETX*2+Constants.WIDTH1+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2+Constants.SPACEX+Constants.WIDTH2*3/2+Constants.SPACEX, saveButton.getY()+Constants.HEIGHT+Constants.SPACEY*3);
	}

// abstract method implementations
	
	public void setSpecializationByText(String text, String note, Specialization spec)
	{
		father.getHero().getProperty(text).setSpecialization(spec);
	}
	
	/**
	 * adds all Components to PropertiesPanel
	 */
	public void addComponents()
	{
		add(csMenu);
		
		add(comboBox);
		add(valueLabel);
		add(raceModLabel);
		add(ownModLabel);
		add(totalLabel);
		add(costsLabel);
		
		for (int i = 0; i < 14; i++)
		{
			add(names[i]);
			add(values[i]);
			add(racemods[i]);
			add(ownmods[i]);
			add(total[i]);
			add(costs[i]);
		}

		for (int i = 0; i < 6; i++)
		{
			add(namesS[i]);
			add(valuesS[i]);
			add(racemodsS[i]);
			add(ownmodsS[i]);
			add(totalS[i]);
			add(costsS[i]);
		}
		
		add(FBVsLabel);
		add(damBasesLabel);
		add(initBLabel);
		add(FBVsVal);
		add(damBasesVal);
		add(initBVal);
		
		add(specialRulesLabel);
		add(specialsLabel);
		add(specialRulesScrollP);
		add(specialsPanel);
		add(saveButton);
	}
	
	/**
	 * Implementation of abstract method
	 */
	public LinkedList getNotes()
	{
		return father.getRInf().getPropertyNotesLl();
	} 

	/**
	 * Implementation of abstract method
	 */
	public LinkedList getSpecIcons ()
	{
		return father.getRInf().getPropertySpecsLl();
	} 

	/**
	 * returns values[i] if possible
	 */
	public JComponent getFocusComponentInClickedRow(JComponent comp)
	{
		if (comp == null)
			return null;
		
		if (comp == comboBox || comp == valueLabel || comp == raceModLabel || comp == ownModLabel)
			return comboBox;

		for (int i = 0; i < 14; i++)
		{
			if (comp == names[i] || comp == values[i] || comp == racemods[i] || comp == ownmods[i])
				return values[i];
		}
		
		for (int i = 0; i < 6; i++)
		{
			if (comp == namesS[i] || comp == valuesS[i] || comp == racemodsS[i])
				return valuesS[i];
			if (comp == ownmodsS[i])
				return comp;
		}
		
		return null;
	}
	
	/** 
	 * returns names[i] if possible
	 */
	public MyLabel getTopicInClickedRow(JComponent comp)
	{
		if (comp == null)
			return null;
		
		for (int i = 0; i < 14; i++)
		{
			if (comp == names[i] || comp == values[i] || comp == racemods[i] || comp == ownmods[i])
				return names[i];
		}
		
		for (int i = 0; i < 6; i++)
		{
			if (comp == namesS[i] || comp == valuesS[i] || comp == racemodsS[i] || comp == ownmodsS[i])
				return namesS[i];
		}
		
		return null;
	}
	
	public String serializeYourself()
	{
		String ret = new String("prop;");
		
		for (int i = 0; i < 14; i++)
			ret += this.values[i].getText() + ";";
		
		for (int i = 0; i < 6; i++)
			ret += this.valuesS[i].getText() + ";";
		
		for (int i = 0; i < 14; i++)
			ret += this.ownmods[i].getText() + ";";
		
		for (int i = 0; i < 6; i++)
			ret += this.ownmodsS[i].getText() + ";";
		
		
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
	
	public void deserializeYourself(String s, int version)
	{
		StringTokenizer t = new StringTokenizer(s, ";");
		
		if (!t.nextToken().equals("prop"))
		{
			father.getStatusLabel().setText("PropertiesPanel falsch deserialisiert");
			return;
		}
		
		Noteable[] notecomps = getNoteableComponents();
		
		try {
			for (int i = 0; i < 14; i++)
				this.values[i].setText(t.nextToken());
			
			for (int i = 0; i < 6; i++)
				this.valuesS[i].setText(t.nextToken());
			
			for (int i = 0; i < 14; i++)
				this.ownmods[i].setText(t.nextToken());
			
			for (int i = 0; i < 6; i++)
				this.ownmodsS[i].setText(t.nextToken());
			
			while (t.hasMoreTokens())
			{
				String text = t.nextToken();
				int index = Integer.parseInt(t.nextToken());
				addNote((JComponent)(notecomps[index]), text, true);
			}
		
			Property[] props = father.getHero().getProperties();
			
			for (int i = 0; i < props.length; i++)
				if (props[i].getSpecialization() != null)
					addSpec((JComponent)(this.names[i]), props[i].getSpecialization().getNameStr(), props[i].getSpecialization().getValueInt());
			
			Property[] senses = father.getHero().getSenses();
			
			for (int i = 0; i < senses.length; i++)
				if (senses[i].getSpecialization() != null)
					addSpec((JComponent)(this.namesS[i]), props[i].getSpecialization().getNameStr(), props[i].getSpecialization().getValueInt());	
		
			actualizeValues();
			setGPEPLabel();
		} catch (Exception ex)
		{
			ex.printStackTrace();
			father.getStatusLabel().setText("Etwas geht schief");
		}
	}
	
	public void deserializeYourself()	// init, not from file
	{
		for (int i = 0; i < 14; i++)
			this.values[i].setText("10");
		
		for (int i = 0; i < 6; i++)
			this.valuesS[i].setText("10");
	
		for (int i = 0; i < 12; i++)
			this.ownmods[i].setText("0");
		
		for (int i = 0; i < 6; i++)
			this.ownmodsS[i].setText("0");
		
		actualizeValues();
		setGPEPLabel();
	}
	
	public JComponent getDefaultFocusComponent() {
		return values[0];
	}
	
// error handling	
	
	public String checkForErrors()
	{
		for (int i = 0; i < 14; i++)
		{
			try {
				try {
					int j = 0;
					try {	// if prop has a spec
						j = Integer.parseInt(((Noteable)(names[i])).getSpec().getVal());
					} catch  (NullPointerException npe) {/*nothing*/}
					if (Integer.parseInt(values[i].getText()) + j > father.getHero().getProperty(getTopicInClickedRow(values[i]).getText()).getMaximumInt())
						{
						System.out.println("Integer.parseInt(values[i].getText()): " + Integer.parseInt(values[i].getText()));
						System.out.println("j: "+j+", i: "+i);
						System.out.println("father.getHero().getProperty(getTopicInClickedRow(values[i]).getText()).getMaximumInt(): "+(father.getHero().getProperty(getTopicInClickedRow(values[i]).getText()).getMaximumInt()));
						return valueTooHigh(values[i]);
						
						}
					if (Integer.parseInt(values[i].getText()) - j < father.getHero().getProperty(getTopicInClickedRow(values[i]).getText()).getMinimumInt())
					{
						System.out.println("Integer.parseInt(values[i].getText()): " + Integer.parseInt(values[i].getText()));
						System.out.println("j: "+j+", i: "+i);
						System.out.println("father.getHero().getProperty(getTopicInClickedRow(values[i]).getText()).getMinimumInt(): "+(father.getHero().getProperty(getTopicInClickedRow(values[i]).getText()).getMinimumInt()));
						return valueTooLow(values[i]);
					}
				} catch (NumberFormatException nfe)
				{
					return NumberFormatExceptionCaught(values[i]);
				}
				try {
					if (Integer.parseInt(ownmods[i].getText()) > (Integer.parseInt(values[i].getText())+1)/2)
					return ownModTooHigh(ownmods[i]);
				} catch (NumberFormatException nfe)
				{
					return NumberFormatExceptionCaught(ownmods[i]);
				}
				
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		try {
			for (int i = 0; i < 6; i++)
			{
				int j = 0;
				try {
					j = Integer.parseInt(((Noteable)(namesS[i])).getSpec().getVal());
				} catch  (NullPointerException npe)
				{ /* doesn't matter */ }
				if (Integer.parseInt(valuesS[i].getText()) + j > father.getHero().getProperty(getTopicInClickedRow(valuesS[i]).getText()).getMaximumInt())
					return valueTooHigh(valuesS[i]);
				if (Integer.parseInt(valuesS[i].getText()) - j < father.getHero().getProperty(getTopicInClickedRow(valuesS[i]).getText()).getMinimumInt())
					return valueTooLow(valuesS[i]);
				if (Integer.parseInt(ownmodsS[i].getText()) > (Integer.parseInt(valuesS[i].getText())+1)/2)
					return ownModTooHigh(ownmodsS[i]);
			}
			
		} catch (NumberFormatException nfe)
		{
			return NumberFormatExceptionCaught(null);
		}
	
		angry = false;
		return "alles in Butter";
	}
	
	public String valueTooHigh(MySpinner comp)
	{
		
		doNotActualize = true;
		
		Random rnd = new Random(System.currentTimeMillis());

		if (angry == true)	// error # 13 makes it angry ^^
		{
			comp.setText(father.getHero().getProperty(getTopicInClickedRow(comp).getText()).getMinimumInt()+"");
			angry = false;
			return "alles in Butter";
		}
		
		switch (Math.abs(rnd.nextInt() % 13)) {
		case 0: return "Hätte KANDAR das gewollt, hättest Du "+ getTopicInClickedRow(comp).getText() + ": " + comp.getText();
		case 1: return "'n bisschen übers Ziel hinausgeschossen, hm?";
		case 2: 
		if (father.getHero().getProperty(getTopicInClickedRow(comp).getText()).getMaximumInt() >= 25)
			return "Das Rassenmaximum ist auch für " + father.getHero().getRace().getName() + " 25!";
		else
			if (Integer.parseInt(comp.getText()) < 25)
				return "Tja, " + father.getHero().getRace().getName() + " sin' schon hart, aber mach mal halblang.";
			else
				return "LOOOOOOOL!! LOL! Du spielst " + father.getHero().getRace().getName() + " und willst " + father.getHero().getProperty(getTopicInClickedRow(comp).getText()) + "auf " + comp.getText() + "nehmen! LÖÖÖL!";
		case 3: return "Das Rassenmaximum ist " + father.getHero().getProperty(getTopicInClickedRow(comp).getText()).getMaximumInt()+".";
		case 4: return "Machst Du Witze?";
		case 5: return "Zu hoch.";
		case 6: return "Ja neh, is klar. " + getTopicInClickedRow(comp).getText() + ": " + comp.getText();
		case 7: return "Willst Du vielleicht auch KBW: 19 oder etwa Init: 3? Lässt sich sicher machen...";
		case 8: return "Wirkt fast ein wenig arrogant, was Du da versuchst!";
		case 9: return "Im Tierversuchslabor da ist es geschehn...";
		case 10: return "Zwischen Dir und der Weltherrschaft liegt nur noch das Rassenmaximum!";
		case 11: return "Schminks Dir ab, ja? Das ist 'ne Liga zu hoch für Dich!";
		case 12: angry = true; 
			return "Mach's noch mal und ich setz den Wert automatisch auf " + father.getHero().getProperty(getTopicInClickedRow(comp).getText()).getMinimumInt() +"!";
		
		default: return "Irgendwas geht gründlich schief...";
		}
	}
	
	public String valueTooLow(MySpinner comp)
	{
		doNotActualize = true;
		
		Random rnd = new Random(System.currentTimeMillis());

		if (angry)
		{
			comp.setText(Math.abs(rnd.nextInt()%100)+"");
			angry = false;
			return "alles in Butter";
		}
		
		switch (Math.abs(rnd.nextInt() % 13)) {
		case 0: return "Wenn Hochmut vor dem Fall kommt, brauchst Du sicher nie Knieschützer!";
		case 1: return "Du bist verrückt. Jeder andere würde einen zu hohen Wert nehmen und du ...";
		case 2: return "Es ist weder maßlos noch arrogant, wenn Du einen Wert über 7 nimmst. Ehrlich!";
		case 3: return "Zu niedrig.";
		case 4: return "Es gibt auch sowas wie eine untere Eigenschaftsschwelle.";
		case 5: return "Denk mal gut darüber nach, was Du da gerade getippt hast!";
		case 6: return "Bescheidenheit ist ja eine Tugend...";
		case 7: return "Lieber arm dran als Arm ab, nicht wahr?";
		case 8: return "Ich hab ja auch was gegen Powergaming, aber man kann wirklich zu weit gehen.";
		case 9: return "Wenn Du schlechter Helden spielen willst, könntest Du ja weniger GP nehmen.";
		case 10: 
			if (father.getHero().getRace().getName().equals("Feen"))
				if (getTopicInClickedRow(comp).getText().equals("Kraft") || getTopicInClickedRow(comp).getText().equals("Zähigkeit"))
					return "Du musst auch bei Feen Kraft und Zähigkeit im Feenmaßstab angeben!";
				else
					return "Die wird nicht mal in Feenmaßstab umgerechnet, geschweige denn eingegeben!";
			else
				if (getTopicInClickedRow(comp).getText().equals("Kraft") || getTopicInClickedRow(comp).getText().equals("Zähigkeit"))
					return "Versuchs mal mit Feen! Die haben wirklich erbärmliche K und Z";
				else
					return "Bist mal wieder so ein Klotz, der nur auf hohe K und Z schaut, hm?";
		case 11: return "Is' schon schwer, einen Wert zwischen " + father.getHero().getProperty(getTopicInClickedRow(comp).getText()).getMinimumInt() + " und " + father.getHero().getProperty(getTopicInClickedRow(comp).getText()).getMaximumInt() + " zu wählen, oder?";
		case 12: angry = true; 
			return "Ändere diesen Wert oder ich tu's!";
		default: return "Irgendwas geht gründlich schief...";
		}
	}
	
//	public String tooManySensePoints()
//	{
//		doNotActualize = true;
//		
//		Random rnd = new Random(System.currentTimeMillis());
//
//		if (angry == true)	// error # 13 makes it angry ^^
//		{
//			String[] engl = {"Appearance", "Dexterity", "Agility", "Strength", "Swiftness", "Thoughness", "Charisma", "Impressivity", "Intelligence", "Intuition", "Bravery", "Self-control"};
//			for (int i = 0; i < 12; i++)
//				names[i].setText(engl[i]);
//			
//			String[] senses = {"Daysight", "Nightsight", "Hearing", "Smelling", "Feeling", "Tasting"};
//			for (int i = 0; i < 6; i++)
//				namesS[i].setText(senses[i]);
//			
//			valueLabel.setText("Value");
//			raceModLabel.setText("Race mods");
//			ownModLabel.setText("Own mods");
//			totalLabel.setText("total");
//			
//			magicResistanceLabel.setText("Magic resistance");
//			
//			String[] fbvstrings = {"FBV", 
//					"Bow (Gs*2+In+K)/5", 
//					"Crossbow (Gs*2+In+K)/5",
//					"Throwing spear (Gs*2+In+K)/5",
//					"Throwing axe (Gs*2+In+K)/5",
//					"Rope (Gs*2+In+K)/5",
//					"Throwing knife (Gs*3+In)/5",
//					"Slinging weapons (Gs*2+In*2)/5",
//					"Blow Pipe (Gs*2+In*2)/5",
//					"Heavz artillary (Gs*2+In*2)/5",
//					"Battle magic (Gs+In*2+Iz)/5"
//			};
//			
//			FBVsLabel.removeAllItems();
//			
//			for (int i = 0; i < fbvstrings.length; i++)
//				FBVsLabel.addItem(fbvstrings[i]);
//			
//			String[] damBaseStrings = {"St", "StAg", "DAg"};
//			
//			damBasesLabel.removeAllItems();
//			
//			for (int i = 0; i < damBaseStrings.length; i++)
//				damBasesLabel.addItem(damBaseStrings[i]);
//
//			MPLabel.setText("øMP");
//			sensesLabel.setText("Senses");
//			
//			saveButton.setText("Save");
//			
//			specialRulesLabel.setText("Special Rules");
//			specialsLabel.setText("Specials");
//			
//			angry = false;
//			return "alles in Butter";
//		}
//
//		
//		switch (Math.abs(rnd.nextInt() % 13)) {
//		case 0: return "Du kriegst zwar nicht Augen wie ein Luchs, aber Rückenhaare wie ein Hund.";
//		case 1: return "Du riechst wie'n Schwein ... Schweine haben aber auch ziemlich gute Nasen.";
//		case 2: return "Du könntest mit Deinen Sinnen ein bisschen bescheidener sein.";
//		case 3: return getProperty(8, "Sinne") + " + " + getProperty(9, "Sinne") + " = " + ((int)(getProperty(8, "Sinne") + getProperty(9, "Sinne"))) + " und nicht " + getSenses();
//		case 4: return "Jetzt kannst Du sehen/hören/riechen, wie gut ich aussehe/klinge/dufte! *strahl*";
//		case 5: return "Ich würd's gern erlauben, aber mir sind die Hände gebunden.";
//		case 6: return getSenses() + " Sinnespunkte sind offensichtlich zu viel.";
//		case 7: return "In + Iz, Einstein!";
//		case 8: return "Wenn Du schon so gute Sinne hast, schau mal genau ins Regelwerk!";
//		case 9: return "Du hast noch " + ((int)(getProperty(8, "Sinne") + getProperty(9, "Sinne") - getSenses())) + " Sinnespunkte frei.";
//		case 10: return "Ich glaub', Du hast Dich verrechnet!";
//		case 11: return "Versuch's mal mit Bescheidenheit!";
//		case 12: angry = true; 
//			return "I'll translate everything to English if you don't stop making me angry!";
//		
//		default: return "Irgendwas geht gründlich schief...";
//		}
//	}
	
	public String checkSpecialization(MySpinner comp, int val)
	{
		if (val > 3)
		{
			Random rnd = new Random(System.currentTimeMillis());
			
			switch (Math.abs(rnd.nextInt()) % 3) {
			case 0: return "Der Wert darf maximal 3 sein.";
			case 1: return "Netter Versuch! Ab 3 ist bei Eigenschaftsspezialisierungen aber Sense!";
			case 2: return "Nanana, ein bisschen bescheidener!";
			default: return "Irgendwas geht gründlich schief...";
			}
		}
		
		if (Integer.parseInt(comp.getText()) - val < father.getHero().getProperty(getTopicInClickedRow(comp).getText()).getMinimumInt())
		{
			Random rnd = new Random(System.currentTimeMillis());
			doNotActualize = true;
			
			switch (Math.abs(rnd.nextInt()) % 3) {
			case 0: return "Wenn Du glaubst, dass ichs übersehe, hast Du Dich mächtig geschnitten!";
			case 1: return "Rofl!  Und mit " + getTopicInClickedRow(comp).getText() + ": " + (Integer.parseInt(comp.getText()) - val)  + " möchtest Du was zustande bringen?";
			case 2: return "Schon mal daran gedacht, dass du jetzt " + getTopicInClickedRow(comp).getText() + ": " + (Integer.parseInt(comp.getText()) - val)  + " hättest??";
			default: return "Irgendwas geht gründlich schief...";
			}
		}

		if (Integer.parseInt(comp.getText()) + val > father.getHero().getProperty(getTopicInClickedRow(comp).getText()).getMaximumInt())
		{
			Random rnd = new Random(System.currentTimeMillis());
			doNotActualize = true;
			
			switch (Math.abs(rnd.nextInt()) % 3) {
			case 0: return "Wenn Du glaubst, dass ichs übersehe, hast Du Dich mächtig geschnitten!";
			case 1: return "Wow!  Nicht schlecht... Nur nicht erlaubt!";
			case 2: return "Lass ich Dir nicht durchgehn. Da musst Du noch ein klein wenig üben!";
			default: return "Irgendwas geht gründlich schief...";
			}
		}
		
		return "alles in Butter";
	}
	
	public String NumberFormatExceptionCaught(MySpinner comp)
	{	
		doNotActualize = true;
		
		if (angry)
		{
			angry = false;
			if (comp == null)	// senses
			{
				for (int i = 0; i < 6; i++)
				{
					valuesS[i].setText("0");
					addNote(valuesS[i], "Habs auf 0 gesetzt, weil Du mich eh nur sekierst!", false);
				}
				father.displayStartPanel();
				return "alles in Butter";
			}
			comp.setText("10");
			addNote(comp, "Ich habe den Wert auf 10 gesetzt, weil Du mich geärgert hast.", false);
			father.displayStartPanel();
			return "alles in Butter";
		}
		
		Random rnd = new Random(System.currentTimeMillis());
		
		switch (Math.abs(rnd.nextInt() % 13)) {
		case 0: return "Wenn Du weißt, was eine NumberFormatException ist, dann beheb den Fehler!";
		case 1: return "Es fällt mir gewissermaßen schwer, " + comp.getText() + " und andere Zahlen zusammenzuzählen...";
		case 2: return "Brzlg rmgrlirgnum rugraar " + comp.getText() + " gklbmpth¿";
		case 3: return "Ja, ich fange auch sowas ab.";
		case 4: return "War das Absicht, um mich zu ärgern?";
		case 5: return "Ich kann Buchstaben sowenig in Zahlen umwandeln, wie du Scheiße in Gold!";
		case 6: return "Was soll ich mit diesem Mist berechnen?";
		case 7: return "Ich berechne mit Zahlen wie " + comp.getText() + " und Du überweist mir 90.000.000 €, okay?";
		case 8: return "Du hackst auf meinen Gefühlen rum. Du weißt, dass ich " + comp.getText() + " nicht verstehe.";
		case 9: return "Ist das Deine Art, Humor zu zeigen?";
		case 10: return "-.-";
		case 11: return "Der Wert soll zwischen " + father.getHero().getProperty(getTopicInClickedRow(comp).getText()).getMinimumInt() + " und " + father.getHero().getProperty(getTopicInClickedRow(comp).getText()).getMaximumInt() + " sein, nicht zwischen " + comp.getText() + " und gnlpft.";
		case 12: angry = true;
			return "Ändere das SOFORT oder ich schmeiß Dich auf der Stelle raus!";
		default: return "Irgendwas geht gründlich schief...";
		}
	}
	
	public String ownModTooHigh(MySpinner comp)
	{
		
		doNotActualize = true;
		
		Random rnd = new Random(System.currentTimeMillis());

		if (angry == true)
		{
			father.getHero().getRace().remove(father);
			
			Race race;
			if (father.getHero().getRace().getName().equals("Zwerge"))
				race = new Ogres();
			else
				race = new Dwarves();
			
			father.getHero().setRace(race);
			race.set(father.getHero(), father.getSInf(), father.gpepLabel, father, false);
			father.startPanel.raceButton.setText(race.getName());
			
			actualizeValues();
			repaint();
			setVisible(true);
			
			angry = false;
			
			return "alles in Butter";
		}
		
		switch (Math.abs(rnd.nextInt() % 13)) {
		case 0: return "Wow! Nicht schlecht! Nur leider nicht erlaubt!";
		case 1: return "Denk' noch mal drüber nach!";
		case 2: return "Schlaf 'ne Nacht drüber und korrigier das!";
		case 3: return "Der eigene Modifikator darf maximal 1,5 mal so hoch sein wie der Basiswert.";
		case 4: if (comp == ownmods[0] || comp == ownmods[1] || comp == ownmods[2] || comp == ownmods [3] || comp == ownmods[4] || comp == ownmods[6])
					return "Arbeitest Du auf den Ironman Attarinth 1532 hin?";
				if (comp == ownmods[6] || comp == ownmods[7] || comp == ownmods[8] || comp == ownmods [0] || comp == ownmods[10] || comp == ownmods[11])
					return "Kha'zul würde Dich dafür beneiden!";
				return "Du stellst Luchs und Falke in den Schatten!";
		case 5: return "Zu hoch.";
		case 6: return "Du erweckst meinen Zorn.";
		case 7: return "Vielleicht ließe sich durch eine Opfergabe am nächsten Maresch-Schrein was machen.";
		case 8: return "Ahja?!";
		case 9: return "Hättest Du wohl gerne, was?";
		case 10: return "Ein Maximum von " + father.getHero().getProperty(getTopicInClickedRow(comp).getText()).getMaximumInt() + " + " + comp.getText() + " hättest Du wohl gern!";
		case 11: return "Tja, dafür müsstest Du schon mit dem Meister ins Bett steigen!";
		case 12: angry = true; 
			if (father.getHero().getRace().getName().equals("Eardons Haus"))
				return "Möchtest Du ein Oger sein? Wenn nicht, korrigier das!";
			else
				return "Möchtest wieder ein Eardinger sein? Wenn nicht, korrigier das!";
		default: return "Irgendwas geht gründlich schief...";
		}
	}
	
// own methods
	
	/**
	 * saves single component
	 * @param comp
	 */
	public void save(MySpinner comp)
	{
		for (int i = 0; i < 14; i++)
		{
			if (comp == values[i])
				father.getHero().getProperties()[i].setValueInt(Integer.parseInt(comp.getText()));
			if (comp == ownmods[i])
				father.getHero().getOwnModifiers()[i] = Integer.parseInt(comp.getText());
		}
		
		for (int i = 0; i < 6; i++)
		{
			if (comp == valuesS[i])
				father.getHero().getSenses()[i].setValueInt(Integer.parseInt(comp.getText()));
			if (comp == ownmodsS[i])
				father.getHero().getOwnSenseMods()[i] = Integer.parseInt(comp.getText());
		}
	}
		
	/**
	 * called if new race is chosen
	 */
	public void actualizeValues()
	{
			remove (specialsPanel);
			
		specials = father.getHero().getRace().specials();
		specialsPanel = new JScrollPane(specials);
		specials.addFocusListener(this);
		specialsPanel.setBorder(null);
		
		specialsPanel.setLocation(specialsLabel.getX(), FBVsLabel.getY());
		specialsPanel.setSize(father.getHero().getRace().getSpecialSize());
//		if (specialsPanel.getSize().getWidth() == 0)
//			specialsPanel.setSize(Constants.WIDTH1, Constants.HEIGHT);
		add (specialsPanel);
		
		
		doNotActualize = true;	// avoids call of focusLost or itemState Changed
		comboBox.removeAllItems(); 
		Iterator<RaceModifiers> it = father.getHero().getRace().getRaceModifiers().iterator();
		while (it.hasNext())
			comboBox.addItem(it.next().getNameStr());
		if (comboBox.getItemCount() == 1)
			comboBox.setVisible(false);	// hide combobox if unnecessary
		else
			comboBox.setVisible(true);
		doNotActualize = false;
		actualizeTextValues();
	}
	
	/**
	 * repeatedly called, e. g. by focusLost
	 */
	public void actualizeTextValues()
	{	
		try {
			if (doNotActualize)
				return;
			for (int i = 0; i < 14; i++)
			{
				racemods[i].setText((father.getHero().getRaceModifiersByName((String)(comboBox.getSelectedItem())).getMods()[i]+""));
				total[i].setText((""+(Integer.parseInt(values[i].getText())+Integer.parseInt(ownmods[i].getText())+Integer.parseInt(racemods[i].getText()))));
			}
			
			for (int i = 0; i < 6; i++)
			{
				racemodsS[i].setText((father.getHero().getRaceModifiersByName((String)(comboBox.getSelectedItem())).getMods()[i+14]+""));
				totalS[i].setText((""+(Integer.parseInt(valuesS[i].getText())+Integer.parseInt(ownmodsS[i].getText())+Integer.parseInt(racemodsS[i].getText()))));
			}
			
//			specialsTextArea.setText(father.getHero().getSpecialsStr());
//				specialsTextArea.setCaretPosition(0);	// scroll up
			specialRulesTextArea.setText(father.getHero().getRace().getSpecialRulesStr());
				specialRulesTextArea.setCaretPosition(0);

			switch (FBVsLabel.getSelectedIndex())
			{
			case 0: FBVsVal.setText((concat(calculateFBV()) + " ~ " + Math.round(calculateFBV()))); break;
			case 1: FBVsVal.setText((concat(calculateAVBows()) + " ~ " + Math.round(calculateAVBows()))); break;
			case 2: FBVsVal.setText((concat(calculateAVJavelins()) + " ~ " + Math.round(calculateAVJavelins()))); break;
			case 3: FBVsVal.setText((concat(calculateAVAxes()) + " ~ " + Math.round(calculateAVAxes()))); break;
			case 4: FBVsVal.setText((concat(calculateAVLassos()) + " ~ " + Math.round(calculateAVLassos()))); break;
			case 5: FBVsVal.setText((concat(calculateAVKnives()) + " ~ " + Math.round(calculateAVKnives()))); break;
			case 6: FBVsVal.setText((concat(calculateAVSlinger()) + " ~ " + Math.round(calculateAVSlinger()))); break;
			case 7: FBVsVal.setText((concat(calculateAVPipes()) + " ~ " + Math.round(calculateAVPipes()))); break;
			case 8: FBVsVal.setText((concat(calculateAVArtillery()) + " ~ " + Math.round(calculateAVArtillery()))); break;
			case 9: FBVsVal.setText((concat(calculateAVMagic()) + " ~ " + Math.round(calculateAVMagic()))); break;
			}
		
			switch (damBasesLabel.getSelectedIndex())
			{
			case 0: damBasesVal.setText(getProperty(3, "Nahkampf") + ""); break;
			case 1: damBasesVal.setText(calculateStAg() + ""); break;
			}
			
			initBVal.setText(Math.round(calculateInitBasis())+"");
			
			for (int i = 0; i < 14; i++)
				costs[i].setText("" + getCostsOfProperty(Integer.parseInt(values[i].getText())+1));
			
			for (int i = 0; i < 6; i++)  
				costsS[i].setText("" + getCostsOfSense(Integer.parseInt(valuesS[i].getText())+1, i));
		} catch (NumberFormatException nfe)
		{ 
			// nothing - checkForErrors checks for errors
		}
	}
	
	/** 
	 * returns property (not sense) by index (used for MP and FBV calc)
	 * also takes care of specializations and modifiers
	*/ 
	public int getProperty (int index, String reason)
	{
		int ret = 0;
		if (names[index].getSpec() != null)
			if (reason.equals(names[index].getSpec().getText()))
				ret += (Integer.parseInt(names[index].getSpec().getVal()));
			else
				ret -= (Integer.parseInt(names[index].getSpec().getVal()));
		return ret + Integer.parseInt(values[index].getText()) + Integer.parseInt(ownmods[index].getText()) + Integer.parseInt(racemods[index].getText());
	}
	
	public int getSightValue (String reason)
	{
		int day = 0, night = 0;
		if (names[0].getSpec() != null)
			if (reason.equals(names[0].getSpec().getText()))
				day += (Integer.parseInt(names[0].getSpec().getVal()));
			else
				day -= (Integer.parseInt(names[0].getSpec().getVal()));
		
		if (names[1].getSpec() != null)
			if (reason.equals(names[1].getSpec().getText()))
				night += (Integer.parseInt(names[1].getSpec().getVal()));
			else
				night-= (Integer.parseInt(names[1].getSpec().getVal()));
		
		day += Integer.parseInt(ownmods[0].getText()) + Integer.parseInt(racemods[0].getText());
		night+= Integer.parseInt(ownmods[1].getText()) + Integer.parseInt(racemods[1].getText());
		return (1 + day + night)/2;
	}
	
	/**
	 * @return Fighting Basis Value
	 */
	public double calculateFBV()
	{
		try {
			return (getProperty(1, "Nahkampf") + getProperty(2, "Nahkampf") + getProperty(4, "Nahkampf") + getProperty(5, "Nahkampf") + getProperty(11, "Nahkampf")+1)
			/3.0;
		} catch (NumberFormatException nfex) {
			return 0.0;
		}
	}
	
	/**
	 * @return Artillary Value for bows and crossbows 
	 */
	public double calculateAVBows()
	{
		try {
			return (getProperty(1, "Fernkampf")*2 + getProperty(10, "Fernkampf") + getProperty(13, "Fernkampf") + getSightValue("Fernkampf")+1)
			/3.0;
		} catch (NumberFormatException nfex) {
			return 0.0;
		}
	}
	
	
	/**
	 * @return Artillary Value for Javelins
	 */
	public double calculateAVJavelins()
	{
		try {
			return (getProperty(1, "Fernkampf") + getProperty(2, "Fernkampf") + getProperty(4, "Fernkampf") + getProperty(5, "Fernkampf") + getProperty(10, "Fernkampf") + getProperty(10, "Fernkampf")+1)
			/3.0;
		} catch (NumberFormatException nfex) {
			return 0.0;
		}
	}
	
	/**
	 * @return Artillary Value for Throwing Knives
	 */
	public double calculateAVKnives()
	{
		try {
			return (getProperty(1, "Fernkampf")*3 + getProperty(10, "Fernkampf")*2 +1)
			/3.0;
		} catch (NumberFormatException nfex) {
			return 0.0;
		}
	}
	
	
	/**
	 * @return Artillary Value for Throwing Axes
	 */
	public double calculateAVAxes()
	{
		try {
			return (getProperty(1, "Fernkampf")*2 + getProperty(2, "Fernkampf") + getProperty(4, "Fernkampf") + getProperty(10, "Fernkampf") +1)
			/3.0;
		} catch (NumberFormatException nfex) {
			return 0.0;
		}
	}
	
	/**
	 * @return Artillary Value for Lassos
	 */
	public double calculateAVLassos()
	{
		try {
			return (getProperty(1, "Fernkampf")*2 + getProperty(2, "Fernkampf") + getProperty(4, "Fernkampf") + getProperty(10, "Fernkampf") +1)
			/3.0;
		} catch (NumberFormatException nfex) {
			return 0.0;
		}
	}
	
	/**
	 * @return @return Artillary Value for Slingers, heavy artillary and so on
	 */
	public double calculateAVSlinger()
	{
		try {
			return (getProperty(1, "Fernkampf")*3 + getProperty(2, "Fernkampf") + getProperty(10, "Fernkampf") +1)
			/3.0;
		} catch (NumberFormatException nfex) {
			return 0.0;
		}
	}
	
	/**
	 * @return Artillary Value for Blowing Pipes
	 */
	public double calculateAVPipes()
	{
		try {
			return (getProperty(1, "Fernkampf")*2 + getProperty(9, "Fernkampf") + getProperty(10, "Fernkampf")+ getProperty(13, "Fernkampf") +1)
			/3.0;
		} catch (NumberFormatException nfex) {
			return 0.0;
		}
	}
	
	/**
	 * @return Artillary Value for Artillery
	 */
	public double calculateAVArtillery()
	{
		try {
			return (getProperty(1, "Fernkampf") + getProperty(9, "Fernkampf")*2 + getProperty(10, "Fernkampf")+ getProperty(13, "Fernkampf") +1)
			/3.0;
		} catch (NumberFormatException nfex) {
			return 0.0;
		}
	}
	
	/**
	 * @return Artillary Value for Battle Magic
	 */
	public double calculateAVMagic()
	{
		try {
			return (getProperty(1, "Fernkampf") + getProperty(9, "Fernkampf") + getProperty(10, "Fernkampf")*2+ getProperty(13, "Fernkampf") +1)
			/3.0;
		} catch (NumberFormatException nfex) {
			return 0.0;
		}
	}
	
	/**
	 * @return Damage Basis Strength Agility
	 */
	public int calculateStAg()
	{
		int ag = getProperty(2, "Nahkampf");
		int st = getProperty(4, "Nahkampf");

		if (ag > st)
			return ag + (st+1)/2;
		else
			return st + (ag+1)/2;
	}

	/**
	 * @return Init Basis
	 */
	public double calculateInitBasis()
	{
		try {
			return 35 - ((getProperty(5, "Nahkampf") + 1 + getProperty(10, "Nahkampf")))/2;	// +1 because it is rounded down
		} catch (NumberFormatException nfex) {
			return 0.0;
		}
	}
	
	private String concat(double number)
	{
		String ret = number+"";
		if (ret.length() > 4)
		{
			return ret.substring(0,5);
		}
		else
			return ret;
	}
		
	public void checkGPEP(JComponent source)
	{
		int oldval = 0;
		int newval = 0;
	
		for (int i = 0; i < 14; i++)
		{
			if (source == ownmods[i])
				return;
			if (source == values[i])
			{
				oldval = father.getHero().getProperties()[i].getValueInt();
				newval = Integer.parseInt(values[i].getText());
				if (oldval == newval)
					return;
				i = 14;
				
				try {
					// lowering
					while (oldval > newval)
						father.getSInf().increaseEP(getCostsOfProperty(oldval--));  
					// rising
					while (newval > oldval)
						father.getSInf().increaseEP(-1 * getCostsOfProperty(++oldval));
					
					setGPEPLabel();
			} catch (Exception ex)
			{
				// nothing, this is not my beer / that's what checkError's for!
			}
			}
		}
		
		for (int i = 0; i < 6; i++)
			if (source == ownmodsS[i])
				return;
		
		for (int i = 0; i < 6; i++)
			if (source == valuesS[i])
			{
				oldval = father.getHero().getSenses()[i].getValueInt();
				newval = Integer.parseInt(valuesS[i].getText());
				if (oldval == newval)
					return;
				
				try {
					// lowering
					while (oldval > newval)
						father.getSInf().increaseEP(getCostsOfSense(oldval--, i));
					// rising
					while (newval > oldval)
						father.getSInf().increaseEP(-1 * getCostsOfSense(++oldval, i));
					
					setGPEPLabel();
			} catch (Exception ex)
			{
				// nothing, this is not my beer / that's what checkError's for!
			}
			}
	}

// Event Handlers
	
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
	
	public void actionPerformed (ActionEvent ae)
	{
		try {
			if (ae.getActionCommand().equals("Notiz hinzufügen/bearbeiten"))
			{
				addEditNote(notedComponent);
			}
			
			if (ae.getActionCommand().equals("Notiz löschen"))
			{
				deleteNote(notedComponent);
			}
			
			if (ae.getActionCommand().equals("Spezialisierung hinzufügen/bearbeiten"))
			{
				addEditSpec(notedComponent);
			}
			
			if (ae.getActionCommand().equals("Spezialisierung löschen"))
			{
				deleteSpec(notedComponent);
			}
		} catch (ClassCastException cce)
		{
			// nothing
		} catch (NullPointerException npe)
		{ /*nothing*/ }
		
		if (ae.getSource().equals(saveButton))
		{
			father.displayStartPanel();
			return;
		}
		else
		{
			actualizeTextValues();
			father.getStatusLabel().setText(checkForErrors());
			if (father.getStatusLabel().getText().equals("alles in Butter"))
				if (ae.getSource() instanceof MySpinner)
				{
					checkGPEP((JComponent)(ae.getSource()));
					save((MySpinner)(ae.getSource()));
				}
		}
		
		if (ae.getActionCommand().equals("Spezialisierung hinzufügen/bearbeiten"))
		{
			MyLabel topic = getTopicInClickedRow(notedComponent);
			if (((Noteable)(topic)).getSpec() != null) // if spec added
				father.getStatusLabel().setText(checkSpecialization((MySpinner)(getFocusComponentInClickedRow(notedComponent)), Integer.parseInt(((Noteable)(topic)).getSpec().getVal())));
			if (!father.getStatusLabel().getText().equals("alles in Butter"))
			{
				deleteSpec(topic); // if bad then delete
				doNotActualize = true;
			}
		}
	}
	
	public void focusLost (FocusEvent fe)
	{
		if (doNotActualize)
			doNotActualize = false;
		else
		{
			actualizeTextValues();
			father.getStatusLabel().setText(checkForErrors());
			
			if (fe.getSource() instanceof MySpinner)
			{
				checkGPEP((JComponent)(fe.getSource()));
				save((MySpinner)(fe.getSource()));
			}
		}	
	}
	
	public void focusGained (FocusEvent fe) {/*nothing*/}

	public void keyTyped(KeyEvent ke){/*nothing*/}
	
	public void keyPressed(KeyEvent ke)
	{
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
		
		if (ke.getKeyCode() == ke.VK_CONTROL)
			ctrlPressed = true;
		else
		{
			if (ctrlPressed)
				if (ke.getKeyCode() == ke.VK_S)
					father.displayStartPanel();
			ctrlPressed = false;
		}
	}
	
	public void keyReleased(KeyEvent ke)
	{
		ctrlPressed = false;
	}

	public void itemStateChanged(ItemEvent ie)
	{
		actualizeTextValues();
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	public MySpinner[] getOwnmods() {
		return ownmods;
	}

	public void setOwnmods(MySpinner[] ownmods) {
		this.ownmods = ownmods;
	}
	
	public String getPropertyByIndex (int index)
	{
		return names[index].getText();
	}
	
	public int getCostsOfProperty (int value) {
		if (value < 11)
			return 75;
		if (value < 16)
			return 50;
		if (value < 21)
			return 75;
		return 250;
	}
	
	public int getCostsOfSense(int value, int index) {
		
		boolean highestSense = true;
		
		for (int i = 0; i < 6; i++)
			if (i != index)	// don't check for the value in question itself
				if (father.getHero().getSenses()[i].getValueInt() >= value)
					highestSense = false;
		
		if (highestSense)
		{
			if (value < 6)
				return 6;
			if (value < 11)
				return 8;
			if (value < 16)
				return 10;
			if (value < 21)
				return 12;
			return 40;
		}
		else
		{
			if (value < 6)
				return 3;
			if (value < 11)
				return 4;
			if (value < 16)
				return 5;
			return 6;
		}
	} 
}