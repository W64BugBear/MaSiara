package gui;

import guidialogelements.ContextSensitiveMenu;
import guidialogelements.MyButton;
import guidialogelements.MyComboBox;
import guidialogelements.MyLabel;
import guidialogelements.MySpinner;
import guidialogelements.MyTextField;
import guidialogelements.Noteable;
import guidialogelements.SkillPositioner;
import guidialogelements.SkillUnit;

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
import javax.swing.UIManager;

import background.Constants;
import background.SerializableNote;
import character.Hero;
import character.RaceModifiers;
import character.Skill;
import character.Specialization;
import dataclasses.SkillLists;

public class SkillsPanel 
	extends MyPanel
	implements ActionListener, KeyListener, ItemListener, FocusListener
{
	boolean itemStateChangedCalled = false; // itemStateChanged is called twice
	
	JComponent notedComponent;	// saves Component which gets a note or specialization
	boolean ctrlPressed = false;	// allows Saving by Ctrl+S
	boolean angry = false;			// This program may get angry if you drive it mad!!
	public int index = 0;
	
	MainWindow father;
	
	ContextSensitiveMenu csMenu;
	
	SkillPositioner[] skillPositioners;
	
	MyLabel[] valueLbl = new MyLabel[4];
	MyLabel[] costsLbl = new MyLabel[4];

	MyComboBox additionalSkillsCombo, languagesCombo, fontsCombo, limitationsCombo;
	
	MyButton saveBt;
	
	// constructor
	
	public SkillsPanel(MainWindow father)
	{
		super(father);
		this.father = father;
		
		// Generate Elements
		
		for (int i = 0; i < 4; i++)
		{
			valueLbl[i] = new MyLabel("Wert", index++);
			costsLbl[i] = new MyLabel("Kosten", index++);
		}
		
		saveBt = new MyButton("Speichern", index++);
		csMenu = new ContextSensitiveMenu(this);
		
		additionalSkillsCombo = new MyComboBox(SkillLists.getSpecialSkills(), index++);
		languagesCombo = new MyComboBox(SkillLists.getLanguageSkills(), index++);
		fontsCombo = new MyComboBox(SkillLists.getFontSkills(), index++);
		limitationsCombo = new MyComboBox(SkillLists.getLimitations(), index++);
		
		// decorating
		
		Font font = new Font(getFont().getName(), Font.BOLD, getFont().getSize()+2);
		
		for (int i = 0; i < 4; i++)
		{
			valueLbl[i].setFont(font);
			costsLbl[i].setFont(font);
		}
		
		// positioning
		
		setLayout(null);

		for (int i = 0; i < 4; i++)
		{
			valueLbl[i].setBounds(Constants.OFFSETX + i * (Constants.WIDTH1 + Constants.WIDTH2 + Constants.SPACEX*3) + Constants.WIDTH1 + Constants.SPACEX, Constants.OFFSETY, Constants.WIDTH2/2, Constants.HEIGHT);
			costsLbl[i].setBounds(Constants.OFFSETX + i * (Constants.WIDTH1 + Constants.WIDTH2 + Constants.SPACEX*3) + Constants.WIDTH1 + Constants.WIDTH2/2 + Constants.SPACEX*2, Constants.OFFSETY, Constants.WIDTH2, Constants.HEIGHT);
		}
		
// no need for add Components, because they are already added in SkillPositioners' constructors
		
		add(csMenu);
		
		for (int i = 0; i < 4; i++)
		{
			add(valueLbl[i]);
			add(costsLbl[i]);
		}
		
//		 enables ContextSensitiveMenu
		enableEvents(AWTEvent.MOUSE_EVENT_MASK);
		
		// add listeners
		
		saveBt.addActionListener(this);
		
		additionalSkillsCombo.addItemListener(this);
		languagesCombo.addItemListener(this);
		fontsCombo.addItemListener(this);
		limitationsCombo.addItemListener(this);
		
		additionalSkillsCombo.addKeyListener(this);
		languagesCombo.addKeyListener(this);
		fontsCombo.addKeyListener(this);
		limitationsCombo.addKeyListener(this);
	}

	public Dimension getPreferredSize()
	{
		return new Dimension((Constants.WIDTH1 + Constants.WIDTH2 + Constants.SPACEX*3) * 4 + Constants.OFFSETX*2, saveBt.getY() + saveBt.getHeight() + Constants.OFFSETY*2 + 2*(Constants.HEIGHT+Constants.SPACEY)); // Wild Skills are below save button
	}

	
//	 abstract method implementations
	
	public void setSpecializationByText(String text, String note, Specialization spec)
	{
		father.getHero().getSkill(text, note).setSpecialization(spec);
	}
	
	public void addComponents()
	{
		add(csMenu);
		
		for (int i = 0; i < 4; i++)
		{
			add(valueLbl[i]);
			add(costsLbl[i]);
			
			Iterator<JComponent> it = skillPositioners[i].getComponents().iterator();
			while (it.hasNext())
			{
				Object obj = it.next();
				if (obj instanceof SkillUnit)
				{
					add (((SkillUnit)(obj)).getNameLbl());
					add (((SkillUnit)(obj)).getValueSpinner());
					add (((SkillUnit)(obj)).getCostsTf());
				}
				else 
					if (obj instanceof JComponent)
					{
						add ((JComponent)(obj));
					}
			}
		}
	}
	
	public LinkedList getNotes()
	{
		return father.getRInf().getSkillNotesLl();
	}
	
	public LinkedList getSpecIcons()
	{
		return father.getRInf().getSkillSpecsLl();
	}
	
	public JComponent getFocusComponentInClickedRow(JComponent comp)
	{
		if (comp == null)
			return null;
		
		SkillUnit su = getSkillUnit(comp);
		if (su == null)
			return getDefaultFocusComponent();
		else
			return su.getValueSpinner();
	}
	
	public MyLabel getTopicInClickedRow(JComponent component)
	{
		if (component == null)
			return null;
		
		SkillUnit su = getSkillUnit(component);
		if (su == null)
			return null;
		else
			return su.getNameLbl();
	}
	
	public String serializeYourself()
	{
		String ret = new String("skills;");
		
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
		if (skillPositioners != null)
			for (int i = 0; i < 4; i++)
				skillPositioners[i].undoYourself();
		
		skillPositioners = new SkillPositioner[4];
		skillPositioners[0] = new SkillPositioner(0, this, SkillLists.get1stRow(this), false);
		skillPositioners[1] = new SkillPositioner(1, this, SkillLists.get2ndRow(this), false);
		skillPositioners[2] = new SkillPositioner(2, this, SkillLists.get3rdRow(this), false);
		skillPositioners[3] = new SkillPositioner(3, this, SkillLists.get4thRow(this, additionalSkillsCombo, languagesCombo, fontsCombo, limitationsCombo), false);

		skillPositioners[0].add(saveBt, false);

		Iterator<Skill> basicSkills = father.getHero().getBasicSkillsLl().iterator();
		
		for (int i = 0; i < 3; i++)
		{
			Iterator actSkillUnit = skillPositioners[i].getComponents().iterator();
			
			while (actSkillUnit.hasNext())
			{
				try {
					SkillUnit su = (SkillUnit)(actSkillUnit.next());
					su.setSkill(basicSkills.next());
				} catch (ClassCastException cce)
				{
					
				}
			}
		}
		
		Iterator<Skill> additionalSkills = father.getHero().getSpecialSkillsLl().iterator();
		while (additionalSkills.hasNext())
		{
			Skill skill = additionalSkills.next();
			if (skill.getTypeInt() == Skill.TYPE_LANGUAGE)
				skillPositioners[3].addAfter(skill, languagesCombo, false);
			else
				if (skill.getTypeInt() == Skill.TYPE_FONT)
					skillPositioners[3].addAfter(skill, fontsCombo, false);
				else
					if (skill.getTypeInt() == Skill.TYPE_LIMITATION)
						skillPositioners[3].addAfter(skill, limitationsCombo, false);
						else
							skillPositioners[3].addAfter(skill, additionalSkillsCombo, false);
		}
		
		Noteable[] notecomps = getNoteableComponents();
		
		StringTokenizer t = new StringTokenizer(s, ";");
		
		if (!t.nextToken().equals("skills"))
		{
			father.getStatusLabel().setText("SkillsPanel falsch deserialisiert");
			return;
		}
		
		while (t.hasMoreTokens())
		{
			try {
				String text = t.nextToken();
				int index = Integer.parseInt(t.nextToken());
				addNote((JComponent)(getNoteable(notecomps, index)), text, true);	
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public Noteable getNoteable (Noteable[] notecomps, int index) {
		for (int i = 0; i < notecomps.length; i++)
			if (notecomps[i].getIndex() == index)
				return notecomps[i];
		return null;
	}
	
	public void deserializeYourself()
	{
		if (skillPositioners != null)
			for (int i = 0; i < 4; i++)
				skillPositioners[i].undoYourself();
		
		skillPositioners = new SkillPositioner[4];
		skillPositioners[0] = new SkillPositioner(0, this, SkillLists.get1stRow(this), true);
		skillPositioners[1] = new SkillPositioner(1, this, SkillLists.get2ndRow(this), true);
		skillPositioners[2] = new SkillPositioner(2, this, SkillLists.get3rdRow(this), true);
		skillPositioners[3] = new SkillPositioner(3, this, SkillLists.get4thRow(this, additionalSkillsCombo, languagesCombo, fontsCombo, limitationsCombo), true);

		skillPositioners[0].add(saveBt, false);
	}
	
	public JComponent getDefaultFocusComponent()
	{
		try {
			return ((SkillUnit)(skillPositioners[0].getComponents().getFirst())).getValueSpinner();
		} catch (Exception ex)
		{
			return saveBt;
		} 
	}
	
// error handling
	
	/**
	 * checks for number format exception, too high or too low values
	 * @return status string which should be "alles in Butter" in order to proceed
	 * @param skillUnit: skillUnit of the freshly entered skill (containing old value)
	 * @param newVal: new typed value
	 */
	public String checkForErrors(SkillUnit skillUnit, int newVal)
	{
		String returnString = "alles in Butter";		// default init
		
 		for (int i = 0; i < 4; i++)
		{
			SkillUnit su = null;
			Iterator it = skillPositioners[i].getComponents().iterator();
			while (it.hasNext())
				try {
					try {
						su = (SkillUnit)(it.next());
						int val = Integer.parseInt(su.getValueSpinner().getText());	// val of current skillUnit, i.e. su
						int j = 0;
						try {	// if prop has a spec
							j = su.getSkill().getSpecialization().getValueInt();
						} catch  (NullPointerException npe) {/*nothing*/}
						if (getMaximum(su.getSkill().getTypeInt(), 0) != 0 && val > getMaximum(su.getSkill().getTypeInt(), su.getSkill().getGroupInt()))
							return valueTooHigh(su);
						if (val - j < Skill.MINVALUE)
							return valueTooLow(su, j);
						if (su.getSkill().getTypeInt() == Skill.TYPE_COMPLEX && su.getSkill().isBaseSkill())	// Complex basic skill, i.e. group skill
						{
							int count = 0,	// counts skill in group 
							sum = 0;		// the sum of the values
							
							Iterator <SkillUnit> subit = skillPositioners[i].getComponents().iterator();	// goes through skill group
							while (subit.hasNext() && subit.next() != su);		// skips all before, i.e. subit is where it is
							while (subit.hasNext())
							{
								SkillUnit subSkillUnit = subit.next();
								
								if (subSkillUnit.getSkill().getTypeInt() == Skill.TYPE_COMPLEX && subSkillUnit.getSkill().isBaseSkill())
									break; // next group value found
								// else:
								count++;
								if (subSkillUnit == skillUnit)
									sum += newVal;	// in skillUnit, there is still the old value, which is going to be updated AFTER this function
								else
									sum += subSkillUnit.getSkill().getValueInt();
							}
							
							double halfaverage = (double)sum/(double)count/2.0;

							boolean changed = false;	// display status change?
							while (val > halfaverage && val > 0)		// typed in value compared to calculation
							{
								val--;			// new value as high as allowed
								changed = true;
							}
							
							while (val > su.getSkill().getValueInt())			// costs management
							{
								father.getSInf().increaseEP(-1 * su.getSkill().getCosts(1, this, ((RaceModifiers)(father.getHero().getRace().getRaceModifiers().getFirst())).isGoodRunners(), father.getHero().getProperties()[Hero.PROP_TOUGHNESS].getValueInt()));
								su.getSkill().setValueInt(su.getSkill().getValueInt()+1);
							}
							
							while (val < su.getSkill().getValueInt())
							{
								if (skillUnit != su)	// group skill value has to be lowered because a skill of this group has been lowered - display it
									changed = true;
								father.getSInf().increaseEP(su.getSkill().getCosts(0, this, ((RaceModifiers)(father.getHero().getRace().getRaceModifiers().getFirst())).isGoodRunners(), father.getHero().getProperties()[Hero.PROP_TOUGHNESS].getValueInt()));
								su.getSkill().setValueInt(su.getSkill().getValueInt()-1);
							}
							
							su.getValueSpinner().setText(val+"");
							
							if (changed)
								returnString =  "alles in Butter (GFW " + su.getSkill().getNameStr() + " auf " + val + " gesenkt)";
						}
					} catch (NumberFormatException nfe)
					{
						return NumberFormatExceptionCaught(su);
					} catch (ClassCastException cce)
					{ /* nothing */ }
					
				} catch (Exception ex)
				{
					ex.printStackTrace();
				}
		}
		
 		angry = false;
		return returnString;
	}
	
	public String valueTooHigh(SkillUnit su)
	{
		Random rnd = new Random(System.currentTimeMillis());

		if (angry == true)	// error # 13 makes it angry ^^
		{
			su.getValueSpinner().setText(getMaximum(su.getSkill().getTypeInt(), su.getSkill().getGroupInt()) + "");
			angry = false;
			return "alles in Butter";
		}
		
		switch (Math.abs(rnd.nextInt() % 13)) {
		case 0: return "Haha.";
		case 1: return "Nicht gerade bescheiden.";
		case 2: return "Warum nicht " + (Integer.parseInt(su.getValueSpinner().getText()) + 1) + " oder " + (Integer.parseInt(su.getValueSpinner().getText()) + 2) + "?";
		case 3: return "Ein bisschen großspurig, was?";
		case 4: return "Das 6. Gebot lehrt \"KANDAR gibt jedem Menschen, was er verdient. Sei deshalb zufrieden damit, was du hast!\"";
		case 5: return Skill.MINVALUE + " bis " + getMaximum(su.getSkill().getTypeInt(), su.getSkill().getGroupInt()) + " - ist das nicht hinzubekommen?";
		case 6: return "Du wärst der Größte ... wärst!";
		case 7: return "Du bist schon über die Grenze hinaus. Höchst grenzwertig, könnte man sagen.";
		case 8: return "Wuuuuusch! " + su.getSkill().getNameStr() + ": " + su.getValueSpinner().getText() + ", das hat was!";
		case 9: return "Netter Versuch, Freundchen.";
		case 10: return "Dachtest Du, ich bemerk's nicht?";
		case 11: String s1 = null;
		switch (su.getSkill().getGroupInt())
		{
		case Skill.GROUP_BODY: s1 = "Bratak"; break;
		case Skill.GROUP_CRAFT: s1 = "Daleila"; break;
		case Skill.GROUP_SOCIETY: s1 = "Djala"; break;
		case Skill.GROUP_WILD: s1 = "Kharion vom Düsterklamm"; break;
		case Skill.GROUP_THEORY: s1 = "Kha'zul"; break;
		case Skill.GROUP_STEALTH: s1 = "Shekina"; break;
		case Skill.GROUP_NONE: s1 = "Niemand"; break;
		case Skill.GROUP_OTHERS: s1 = "Ich"; break;
		}
			return s1 + " könnt's nicht besser.";
		case 12: angry = true; 
			return "Ich bin gnädig. Ich stells dir so ein, wies erlaubt ist, kay? Hab heute nen guten Tag!";
		
		default: return "Irgendwas geht gründlich schief...";
		}
	}
	
	public String groupSkillTooHigh(SkillUnit su)
	{
		return "Schuh";
		
//		Random rnd = new Random(System.currentTimeMillis());
//
//		if (angry == true)	// error # 13 makes it angry ^^
//		{
//			su.getValueSpinner().setText(getMaximum(su.getSkill().getTypeInt(), su.getSkill().getGroupInt()) + "");
//			angry = false;
//			return "alles in Butter";
//		}
//		
//		switch (Math.abs(rnd.nextInt() % 13)) {
//		case 0: return "Haha.";
//		case 1: return "Nicht gerade bescheiden.";
//		case 2: return "Warum nicht " + (Integer.parseInt(su.getValueSpinner().getText()) + 1) + " oder " + (Integer.parseInt(su.getValueSpinner().getText()) + 2) + "?";
//		case 3: return "Ein bisschen großspurig, was?";
//		case 4: return "Das 6. Gebot lehrt \"KANDAR gibt jedem Menschen, was er verdient. Sei deshalb zufrieden damit, was du hast!\"";
//		case 5: return su.getSkill().getMinValueInt() + " bis " + getMaximum(su.getSkill().getTypeInt(), su.getSkill().getGroupInt()) + " - ist das nicht hinzubekommen?";
//		case 6: return "Du wärst der Größte ... wärst!";
//		case 7: return "Du bist schon über die Grenze hinaus. Höchst grenzwertig, könnte man sagen.";
//		case 8: return "Wuuuuusch! " + su.getSkill().getNameStr() + ": " + su.getValueSpinner().getText() + ", das hat was!";
//		case 9: return "Netter Versuch, Freundchen.";
//		case 10: return "Dachtest Du, ich bemerk's nicht?";
//		case 11: String s1 = null;
//		switch (su.getSkill().getGroupInt())
//		{
//		case Skill.GROUP_BODY: s1 = "Bratak"; break;
//		case Skill.GROUP_CRAFT: s1 = "Daleila"; break;
//		case Skill.GROUP_SOCIETY: s1 = "Djala"; break;
//		case Skill.GROUP_WILD: s1 = "Kharion vom Düsterklamm"; break;
//		case Skill.GROUP_THEORY: s1 = "Kha'zul"; break;
//		case Skill.GROUP_STEALTH: s1 = "Shekina"; break;
//		case Skill.GROUP_NONE: s1 = "Niemand"; break;
//		case Skill.GROUP_OTHERS: s1 = "Ich"; break;
//		}
//			return s1 + " könnt's nicht besser.";
//		case 12: angry = true; 
//			return "Ich bin gnädig. Ich stells dir so ein, wies erlaubt ist, kay? Hab heute nen guten Tag!";
//		
//		default: return "Irgendwas geht gründlich schief...";
//		}
	}
	
	public String valueTooLow(SkillUnit su, int specValue)
	{
		Random rnd = new Random(System.currentTimeMillis());

		if (angry)
		{
			Font font = new Font("Wingdings", Font.PLAIN, 12);
			for (int i = 0; i < 4; i++)
			{
				Iterator it = skillPositioners[i].getComponents().iterator();
				while (it.hasNext())
					try {
						SkillUnit skillunit = (SkillUnit)(it.next());
						if (skillunit.getSkill().getTypeInt() == Skill.TYPE_COMPLEX)
							skillunit.getNameLbl().setFont(font);
					} catch (Exception ex)
					{
						
					}
			}
			angry = false;
			return "alles in Butter";
		}
		
		switch (Math.abs(rnd.nextInt() % 13)) {
		case 0: return "Das nenne ich mal 'leicht zufriedengestellt'!";
		case 1: return "Wer mit solchen Werten spielen will, muss schon ein ordentliches Ego haben!";
		case 2: return "Ich würde einen Wert über " + Skill.MINVALUE + " vorschlagen.";
		case 3: if (father.getHero().getName().equals(""))
			return "Haha! " + su.getSkill().getNameStr() + ": " + (Integer.parseInt(su.getValueSpinner().getText()) - specValue) + "!!";
		else
			return father.getHero().getName() + " hat " + su.getSkill().getNameStr() + " auf " + (Integer.parseInt(su.getValueSpinner().getText()) - specValue) + "!! *herumposaun*";
		case 4: return "Es würde nur " + su.getSkill().getCosts(1, this, ((RaceModifiers)(father.getHero().getRace().getRaceModifiers().getFirst())).isGoodRunners(), father.getHero().getProperties()[Hero.PROP_TOUGHNESS].getValueInt()) + " EP pro Punkt kosten! Das ist nicht sooo teuer...";
		case 5: return "*rargh*";
		case 6: return Skill.MINVALUE + " bis " + getMaximum(su.getSkill().getTypeInt(), su.getSkill().getGroupInt()) + " - ist das nicht hinzubekommen?";
		case 7: return "Du bist zu minimalistisch.";
		case 8: return "Na los! Trau Dich!";
		case 9: return "Tu das Minus weg! Du hasts fast geschafft!";
		case 10: return "Wirkt ein wenig plump mit " + su.getSkill().getNameStr() + ": " + (Integer.parseInt(su.getValueSpinner().getText()) - specValue) + ".";
		case 11: return "Wow! Damit beeindruckst Du mich!";
		case 12: angry = true; 
			return "Du hast noch eine Chance, bevor ich Kasterln mach!";
		default: return "Irgendwas geht gründlich schief...";
		}
	}
	
	public String checkSpecialization(SkillUnit su)
	{
		int val = su.getSkill().getSpecialization().getValueInt();
		
		if (val > 3 && su.getSkill().getTypeInt() == Skill.TYPE_COMPLEX)
		{
			Random rnd = new Random(System.currentTimeMillis());
			
			switch (Math.abs(rnd.nextInt()) % 3) {
			case 0: return "Der Wert darf maximal 3 sein.";
			case 1: return "Netter Versuch! Ab 3 ist bei Spezialisierungen auf Komplexe Fertigkeit aber Sense!";
			case 2: return "Nanana, ein bisschen bescheidener!";
			default: return "Irgendwas geht gründlich schief...";
			}
		}
		
		if (val > su.getSkill().getValueInt())
		{
			Random rnd = new Random(System.currentTimeMillis());
			
			switch (Math.abs(rnd.nextInt()) % 3) {
			case 0: return "Spezialisierung zu hoch!";
			case 1: return "Die Spezialisierung darf nie höher sein wie der Fertigkeitswert!";
			case 2: return "Pack die Spezialisierung runter!";
			default: return "Irgendwas geht gründlich schief...";
			}
		}
		
		if (su.getSkill().getTypeInt() == Skill.TYPE_100EP)
			return "So teure Fertigkeiten dürfen keine Spezialisierungen haben.";
		
//		if (su.getSkill().getTypeInt() == Skill.TYPE_FIGHT)
//			return "Hast Du schon mal gesehen, dass irgendwer eine Spezialisierung auf eine Kampffertigkeit hatte? Ich nicht.";
		
		if (su.getSkill().getTypeInt() == Skill.TYPE_LANGUAGE)
			return "Worauf willst Du Dich denn spezialisieren? Flüssige Aussprache? Jodeln?";
		
		if (su.getSkill().getTypeInt() == Skill.TYPE_FONT)
			return "Wenn Du Dich näher mit Schriften befassen willst, nimm die Zusatzfertigkeit 'Graphologie'";
		
		return "alles in Butter";
	}
	
	public String NumberFormatExceptionCaught(SkillUnit su)
	{	
		Random rnd = new Random(System.currentTimeMillis());
			
		if (angry)
		{
			angry = false;
			int newVal = rnd.nextInt() % 10;
			su.getValueSpinner().setText(newVal + "");
			addNote(su.getValueSpinner(), "Ich habe den Wert auf " + newVal + " gesetzt, weil Du mich geärgert hast.", false);
			father.displayStartPanel();
			return "alles in Butter";
		}
		
		switch (Math.abs(rnd.nextInt() % 13)) {
		case 0: return "Wenn Du weißt, was eine NumberFormatException ist, dann beheb den Fehler!";
		case 1: return "Es fällt mir gewissermaßen schwer, " + su.getValueSpinner().getText() + " und andere Zahlen zusammenzuzählen...";
		case 2: return "Brzlg rmgrlirgnum rugraar " + su.getValueSpinner().getText() + " gklbmpth¿";
		case 3: return "Ja, ich fange auch sowas ab.";
		case 4: return "War das Absicht, um mich zu ärgern?";
		case 5: return "Ich kann Buchstaben sowenig in Zahlen umwandeln, wie du Scheiße in Gold!";
		case 6: return "Was soll ich mit diesem Mist berechnen?";
		case 7: return "Ich berechne mit Zahlen wie " + su.getValueSpinner().getText() + " und Du überweist mir 90.000.000 €, okay?";
		case 8: return "Du hackst auf meinen Gefühlen rum. Du weißt, dass ich " + su.getValueSpinner().getText() + " nicht verstehe.";
		case 9: return "Ist das Deine Art, Humor zu zeigen?";
		case 10: return "-.-";
		case 11: return "Der Wert soll zwischen " + Skill.MINVALUE + " und " + getMaximum(su.getSkill().getTypeInt(), su.getSkill().getGroupInt()) + " sein, nicht zwischen " + su.getValueSpinner().getText() + " und gnlpft.";
		case 12: angry = true;
			return "Ändere das SOFORT oder ich schmeiß Dich auf der Stelle raus!";
		default: return "Irgendwas geht gründlich schief...";
		}
	}

// own methods
	/**
	 * Add a skill manually, e.g. limitations of special races (falconites).
	 * @param newskill the skill to be inserted
	 * @param source the combobox it belongs to (e.g. limitationsCombo)  
	 */
	public void addSkill(Skill newskill, JComboBox source)
	{
		if (!father.getHero().skillAlreadyExists(newskill))		
			skillPositioners[3].addAfter(newskill, source, true);			// sort in
	}
	
	public SkillUnit getSkillUnitPerSkill (Skill skill)
	{ 
		for (int i = 3; i >= 0; i--)
		{
			Iterator<JComponent> it = skillPositioners[i].getComponents().iterator();
			while (it.hasNext())
			{
				Object obj = it.next();
				if (obj instanceof SkillUnit)
					if (((SkillUnit)(obj)).getSkill() == skill)
						return (SkillUnit)obj;
			}
		}
		
		return null;
	}
	
	public SkillUnit getSkillUnit (JComponent comp)
	{
		if (comp instanceof MyLabel)
		{
			for (int i = 0; i < 4; i++)
			{
				Iterator it = skillPositioners[i].getComponents().iterator();
				while (it.hasNext())
				{
					try {
						SkillUnit su = (SkillUnit)(it.next());
						if (comp == su.getNameLbl())
							return su;
					} catch (ClassCastException cce)
					{
						// nothing
					}
				}
			}
		}
		
		if (comp instanceof MySpinner)
		{
			for (int i = 0; i < 4; i++)
			{
				Iterator it = skillPositioners[i].getComponents().iterator();
				while (it.hasNext())
				{
					try {
						SkillUnit su = (SkillUnit)(it.next());
						if (comp == su.getValueSpinner())
							return su;
					} catch (ClassCastException cce)
					{
//						cce.printStackTrace();
					}
				}
			}
		}
		
		if (comp instanceof MyTextField)
		{
			for (int i = 0; i < 4; i++)
			{
				Iterator it = skillPositioners[i].getComponents().iterator();
				while (it.hasNext())
				{
					try {
						SkillUnit su = (SkillUnit)(it.next());
						if (comp == su.getCostsTf())
							return su;
					} catch (ClassCastException cce)
					{
//						cce.printStackTrace();
					}
				}
			}
		}
		
		return null;
	}
	
	public void actualizeValues(SkillUnit su)
	{
		if (su == null)
			return;
		
		try 
		{
			int newVal = Integer.parseInt(su.getValueSpinner().getText());
			
			if (!su.getSkill().isBaseSkill() && newVal == 0) // remove additional Skill
			{
				while (newVal < su.getSkill().getValueInt())
				{
					father.getSInf().increaseEP(su.getSkill().getCosts(0, this, ((RaceModifiers)(father.getHero().getRace().getRaceModifiers().getFirst())).isGoodRunners(), father.getHero().getProperties()[Hero.PROP_TOUGHNESS].getValueInt()));
					su.getSkill().setValueInt(su.getSkill().getValueInt()-1);
				}
				
				skillPositioners[3].remove(su);
				return;
			}
			
			father.getStatusLabel().setText(checkForErrors(su, newVal));			// else check for errors
			if (!father.getStatusLabel().getText().startsWith("alles in Butter"))
				return;
			
			if (!(su.getSkill().getTypeInt() == Skill.TYPE_COMPLEX && su.getSkill().isBaseSkill()))	// skill groups have already been dealth with in checkForErrors
			{
				while (newVal > su.getSkill().getValueInt())			// costs management
				{
					father.getSInf().increaseEP(-1 * su.getSkill().getCosts(1, this, ((RaceModifiers)(father.getHero().getRace().getRaceModifiers().getFirst())).isGoodRunners(), father.getHero().getProperties()[Hero.PROP_TOUGHNESS].getValueInt()));
					su.getSkill().setValueInt(su.getSkill().getValueInt()+1);
//					if (su.getSkill().getValueInt() == 8 && su.getSkill().getFrom7onArrList() != null)
//					{
//						From7OnDialog f7nd = new From7OnDialog(father, su.getSkill().getFrom7onArrList());
//						f7nd.setLocation(su.getValueSpinner().getX()+father.getX(), su.getValueSpinner().getY()+father.getY()-father.getScrollPane().getVerticalScrollBar().getValue());
//						f7nd.setSize(f7nd.getPreferredSize());
//						f7nd.setVisible(true);
//						
//						if (su.getValueSpinner().getNote() == null)
//							addNote(su.getValueSpinner(), f7nd.getString(), false);
//						else
//							addNote(su.getValueSpinner(), su.getValueSpinner().getNote().getText() + f7nd.getString(), false);
//					}
				}
				
				while (newVal < su.getSkill().getValueInt())
				{
					father.getSInf().increaseEP(su.getSkill().getCosts(0, this, ((RaceModifiers)(father.getHero().getRace().getRaceModifiers().getFirst())).isGoodRunners(), father.getHero().getProperties()[Hero.PROP_TOUGHNESS].getValueInt()));
					su.getSkill().setValueInt(su.getSkill().getValueInt()-1);
				}
			}
			
			su.getCostsTf().setText(su.getSkill().getCosts(1, this, ((RaceModifiers)(father.getHero().getRace().getRaceModifiers().getFirst())).isGoodRunners(), father.getHero().getProperties()[Hero.PROP_TOUGHNESS].getValueInt())+"");
		} catch (NumberFormatException nfe) {
			father.getStatusLabel().setText(NumberFormatExceptionCaught(su));
		}
	}
	
	public int getMaximum (int skillType, int skillGroup)
	{
		switch (skillType) {
		case Skill.TYPE_NORMAL: 
		case Skill.TYPE_THEORY:	return 20;
		case Skill.TYPE_LANGUAGE: return 26;
		case Skill.TYPE_FONT: return 22;
		case Skill.TYPE_COMPLEX: return 0;
		case Skill.TYPE_100EP: return 0;
		case Skill.TYPE_LIMITATION: return 12;
		}
		return 0;
	}
	
// event handlers
	
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
				getSkillUnit(notedComponent).getSkill().setNote(((Noteable)(notedComponent)).getNote().getText());
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
		
		if (ae.getSource().equals(saveBt))
		{
			father.displayStartPanel();
			return;
		}
		
		try {
			actualizeValues(getSkillUnit((JComponent)(ae.getSource())));
		} catch (ClassCastException cce)
		{ /* nothing*/ }
		
//		father.gpepLabel.setText("GP: " + father.getSInf().getGpInt() + "/" + (father.getSInf().getGpAmountInt()) + " EP: " + father.getSInf().getEpInt() + "/" + (father.getSInf().getEpAmountInt()));
		setGPEPLabel();
		
		if (ae.getActionCommand().equals("Spezialisierung hinzufügen/bearbeiten"))
		{
			SkillUnit skillUnit = getSkillUnit(notedComponent);
			if (skillUnit.getSkill().getSpecialization() != null) // if spec added
				father.getStatusLabel().setText(checkSpecialization(skillUnit));
			if (!father.getStatusLabel().getText().equals("alles in Butter"))
			{
				deleteSpec(skillUnit.getNameLbl()); // if bad then delete
			}
		}
		
		repaint();
		setVisible(true);
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

	public MainWindow getFather() {
		return father;
	}

	public void setFather(MainWindow father) {
		this.father = father;
	}

	public void itemStateChanged (ItemEvent ie)
	{
		if (itemStateChangedCalled)
		{
			itemStateChangedCalled = false;
			return;
		}
		else
			itemStateChangedCalled = true;
		
		JComboBox source = (JComboBox)(ie.getSource());
		if (source.getSelectedIndex() == 0)
			return;		// e. g. "Bitte Zusatzfertigkeit auswaehlen"
		else
		{
			Skill newskill = null;
			if (source.getSelectedIndex() > 1)
				newskill = ((Skill)(source.getSelectedItem())).clone();		// pre-defined skill chosen
			else
			{
				NewSkillDialog nsd;
				if (source == languagesCombo)
					nsd = new NewSkillDialog(father, Skill.TYPE_LANGUAGE);	// else
				else 
					if (source == fontsCombo)
						nsd = new NewSkillDialog(father, Skill.TYPE_FONT);
					else
						if (source == limitationsCombo)
							nsd = new NewSkillDialog(father, Skill.TYPE_LIMITATION);
						else
							nsd = new NewSkillDialog(father, Skill.TYPE_NORMAL);
				nsd.setLocation(200, 400);
				nsd.setSize(nsd.getPreferredSize());
				nsd.setVisible(true);
				newskill = nsd.getSkill();
			}
		
			if (newskill != null)
			{
				if (!father.getHero().skillAlreadyExists(newskill))
				{
					father.getSInf().increaseEP(-1 * newskill.getCosts(1, this, ((RaceModifiers)(father.getHero().getRace().getRaceModifiers().getFirst())).isGoodRunners(), father.getHero().getProperties()[Hero.PROP_TOUGHNESS].getValueInt()));
					newskill.setValueInt(1);
					
					skillPositioners[3].addAfter(newskill, source, true);			// sort in
					
					setGPEPLabel();
				}
			}
			
			source.setSelectedIndex(0);								// initialize
			
			repaint();
			setVisible(true);
		}
	}
	
	public void focusLost(FocusEvent fe)
	{
		actualizeValues(getSkillUnit((JComponent)(fe.getSource())));
		
		setGPEPLabel();
	}

	public MyComboBox getAdditionalSkillsCombo() {
		return additionalSkillsCombo;
	}

	public MyComboBox getFontsCombo() {
		return fontsCombo;
	}

	public MyComboBox getLanguagesCombo() {
		return languagesCombo;
	}

	public MyComboBox getLimitationsCombo() {
		return limitationsCombo;
	}

	public SkillPositioner[] getSkillPositioners() {
		return skillPositioners;
	}
}
