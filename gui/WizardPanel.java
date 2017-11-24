package gui;


import guidialogelements.ContextSensitiveMenuOnlyNotes;
import guidialogelements.MyButton;
import guidialogelements.MyComboBox;
import guidialogelements.MyLabel;
import guidialogelements.MyTextField;
import guidialogelements.Noteable;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

import background.Constants;
import background.SerializableInformation;
import background.SerializableNote;
import character.Advantage;
import character.AdvantageFamily;
import character.AdvantageGroup;
import character.Skill;
import character.Specialization;
import character.Style;
import dataclasses.AdvantageFactory;
import dataclasses.SkillLists;
import dataclasses.StyleFactory;

public class WizardPanel 
	extends MyPanel 
	implements ActionListener,
		FocusListener, ItemListener, AdjustmentListener, KeyListener {
	public static final int FIELDWIDTH = 70; // single field displaying
												// advantage
	public static final int FIELDSOFFSET = 16; // once used offset (because of
												// scrollbar)
	public static final int SCROLLBARWIDTHPLUS = 25; // to adjust the length
														// of the scrollbar
	JComponent notedComponent; // saves Component which gets a note or
								// specialization

	boolean doNotActualize = true; // do not check for errors until panel is
									// completely loaded
	boolean ctrlPressed = false; // allows Saving by Ctrl+S
	boolean angry = false; // This program may get angry if you drive it mad!!

	MainWindow father;
	ContextSensitiveMenuOnlyNotes csMenu;

	boolean basicSkillsHaveBeenInitialized = false;
	
	boolean spellStorePlus = false;
	int manaControlPlus = 0;
	boolean archMage = false;

	MyLabel basicSpellSkillTopic, additionalSpellSkillTopic, archmagicSpellSkillTopic, valuesTopic, styleTopic;
	
	LinkedList<MyLabel> spellSkillLabels, archmageSpellSkillLabels;
	LinkedList<MyTextField> spellSkillTextfields, archmageSpellSkillTextfields;
	MyButton buySpellSkill, buyArchmageSpellSkill;
	
	MyLabel spellForceLbl /*Zaubermacht*/,  spellControlLbl; /*Zaubersteuerung*/
	MyTextField spellForceTf, spellControlTf;

	MyComboBox styleCombo;

	MyButton styleSpecialRulesBt;
	
	JComponent[] comArr; // fields displaying advantages
	
	JScrollBar scrollbar; // scrollbar to adjust
	
	MyTextField scrollbarTf; // tf to type value (instead of using the bar)
	
	MyLabel spellPowerLbl, /*Zauberkraft*/ spellStoreLbl, manaControlLbl, spellPowerBasisLbl, spellSpeedLbl;
	MyTextField spellPowerTf, spellStoreTf, manaControlTf, spellPowerBasisTf, spellSpeedTf;
	
	MyButton buyAdvantageBt;

	LinkedList advantagesLabelLl; // uncountable advantages (e. g. Critical Hit)
	
	LinkedList<Advantage> bought= new LinkedList<Advantage>();
	
	MyButton saveButton; // And off!

	int index = 0; // index for MyComponents to be identificated

	// constructor

	public WizardPanel(MainWindow father) {
		super(father);
		this.father = father;

		// generate elements

		csMenu = new ContextSensitiveMenuOnlyNotes(this);

		basicSpellSkillTopic = new MyLabel("Basiszauberfertigkeiten", index++);
		additionalSpellSkillTopic = new MyLabel("Zusatzzauberfertigkeiten", index++);
		archmagicSpellSkillTopic = new MyLabel("Erzmagie", index++);
		valuesTopic = new MyLabel("Grundwerte", index++);
		styleTopic = new MyLabel("Zauberstil", index++);
		
		spellSkillLabels = new LinkedList<MyLabel>();
		spellSkillTextfields = new LinkedList<MyTextField>();
		String[] skills = SkillLists.getSpellSkills();
		for (int i = 0; i < skills.length; i++)
		{
			MyLabel lbl = new MyLabel(skills[i], index++);
			spellSkillLabels.add(lbl);
			MyTextField tf = new MyTextField("-2", index++);
			spellSkillTextfields.add(tf);
		}
		
//		additionalSpellSkillLabels = new LinkedList<MyLabel>();
//		additionalSpellSkillTextfields = new LinkedList<MyTextField>();
//		skills = SkillLists.getAdditionalSpellSkills();
//		for (int i = 0; i < skills.length; i++)
//		{
//			MyLabel lbl = new MyLabel(skills[i], index++);
//			additionalSpellSkillLabels.add(lbl);
//			MyTextField tf = new MyTextField("-2", index++);
//			additionalSpellSkillTextfields.add(tf);
//		}
		
		archmageSpellSkillLabels = new LinkedList<MyLabel>();
		archmageSpellSkillTextfields = new LinkedList<MyTextField>();
		skills = SkillLists.getArchmagicSpellSkills();
		for (int i = 0; i < skills.length; i++)
		{
			MyLabel lbl = new MyLabel(skills[i], index++);
			archmageSpellSkillLabels.add(lbl);
			MyTextField tf = new MyTextField("-2", index++);
			archmageSpellSkillTextfields.add(tf);
		}
		
		buySpellSkill = new MyButton      ("Neue ZF erlernen", index++);
//		buyAdditionalSpellSkill = new MyButton ("Neue Zusatz-ZF erlernen", index++);
		buyArchmageSpellSkill = new MyButton ("Erzmagie", index++);

		spellForceLbl = new MyLabel("Zaubermacht", index++);
		spellControlLbl = new MyLabel("Zaubersteuerung", index++);
		spellForceTf = new MyTextField("0", index++);
		spellControlTf = new MyTextField("0", index++);
				
		styleCombo = new MyComboBox(index++);
		Iterator<Style> it = StyleFactory.getWizardStyles().iterator();
		while (it.hasNext())
			styleCombo.addItem(it.next());

		styleSpecialRulesBt = new MyButton("Regeln", index++);
		
		comArr = new JComponent[15];; // fields displaying advantages
		for (int i = 0; i < 15; i++) 
			comArr[i] = new MyTextField("", index++);
		
		scrollbar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 16);
		scrollbarTf = new MyTextField("0", index++);
		
		spellPowerLbl = new MyLabel("Zauberkraft", index++);
		spellStoreLbl = new MyLabel("Zauberspeicher", index++);
		manaControlLbl = new MyLabel("Manakontrolle", index++);
		spellPowerBasisLbl = new MyLabel("Zauberkraftbasis", index++);
		spellSpeedLbl = new MyLabel("Zauberinitiative", index++);
		spellPowerTf = new MyTextField("7", index++);
		spellStoreTf = new MyTextField("0", index++);
		manaControlTf = new MyTextField("0", index++);
		spellPowerBasisTf = new MyTextField("0", index++);
		spellSpeedTf = new MyTextField("0", index++);
		
		buyAdvantageBt = new MyButton("Vorteil kaufen", index++);

		advantagesLabelLl = new LinkedList<JTextField>();
		
		LinkedList<Advantage> bought= new LinkedList<Advantage>();

		saveButton = new MyButton("speichern", index++);

		// decorate

		Font font = getFont();
		font = new Font(font.getName(), Font.BOLD, font.getSize() + 2);
		
		basicSpellSkillTopic.setFont(font);
		additionalSpellSkillTopic.setFont(font);
		archmagicSpellSkillTopic.setFont(font);
		valuesTopic.setFont(font);
		styleTopic.setFont(font);
		spellPowerTf.setEditable(false);
		spellPowerLbl.setToolTipText("7+ Zaubermacht / 3");
		manaControlTf.setEditable(false);
		manaControlTf.setToolTipText("(E-12)/3 + (Sb – 12)/3 + Zaubersteuerung/3");
		spellStoreTf.setEditable(false);
		spellStoreTf.setToolTipText("(Iz + In + M + Zaubersteuerung/2)");
		spellSpeedTf.setEditable(false);
		spellControlTf.setToolTipText("(Gs+Iz)/2");
		spellPowerBasisTf.setEditable(false);
		spellPowerBasisLbl.setToolTipText("(E + In + M + Sb)/6 - 2");

		for (int i = 0; i < 15; i++) 
			((MyTextField) (comArr[i])).setEditable(false);
		
		buyAdvantageBt.setVisible(false);
		
//		actualizeBounds();
		
		// add components
		addComponents();
		
		// enable ContextSensitiveMenu
		enableEvents(AWTEvent.MOUSE_EVENT_MASK);

		// add listeners

		addListeners(basicSpellSkillTopic);
		addListeners(additionalSpellSkillTopic);
		addListeners(archmagicSpellSkillTopic);
		addListeners(valuesTopic);
		addListeners(styleTopic);
		
//		addListeners(buyAdditionalSpellSkill);
		addListeners(buySpellSkill);
		addListeners(buyArchmageSpellSkill);
		addListeners(saveButton);
		
		Iterator<MyLabel> it1 = spellSkillLabels.iterator();
		while (it1.hasNext())
			addListeners(it1.next());
//		it1 = additionalSpellSkillLabels.iterator();
//		while (it1.hasNext())
//			addListeners(it1.next());
		it1 = archmageSpellSkillLabels.iterator();
		while (it1.hasNext())
			addListeners(it1.next());
		
		Iterator<MyTextField> it2 = spellSkillTextfields.iterator();
		while (it2.hasNext())
			addListeners(it2.next());
//		it2 = additionalSpellSkillTextfields.iterator();
//		while (it2.hasNext())
//			addListeners(it2.next());
		it2 = archmageSpellSkillTextfields.iterator();
		while (it2.hasNext())
			addListeners(it2.next());

		addListeners(spellForceLbl);
		addListeners(spellControlLbl);
		addListeners(spellForceTf);
		addListeners(spellControlTf);
		addListeners(styleCombo);
		addListeners(styleSpecialRulesBt);
		
		scrollbar.addAdjustmentListener(this);
		
		addListeners(scrollbarTf);
		
		addListeners(spellPowerLbl);
		addListeners(spellPowerTf);
		addListeners(spellStoreLbl);
		addListeners(spellStoreTf);
		addListeners(manaControlLbl);
		addListeners(manaControlTf);
		
		addListeners(buyAdvantageBt);

		addListeners(spellPowerBasisTf);
		addListeners(spellPowerBasisLbl);
		addListeners(spellSpeedLbl);
		addListeners(spellSpeedTf);
		
		// call itemStateChanged

		styleCombo.setSelectedIndex(1);
		styleCombo.setSelectedIndex(0);

		doNotActualize = false;
	}

	public Dimension getPreferredSize() {
		return new Dimension(manaControlTf.getX() + manaControlTf.getWidth()
				+ Constants.OFFSETX, saveButton.getY() + saveButton.getHeight()
				+ Constants.OFFSETY);
	}

	public void actualizeBounds() {
		setLayout(null);

		int offsety = Constants.OFFSETY;
		int offsetx = Constants.OFFSETX + FIELDWIDTH / 2;

		// VALUES
		
		valuesTopic.setBounds(offsetx, offsety, Constants.WIDTH1, Constants.HEIGHT);
		
		spellForceLbl.setBounds(offsetx, offsety + Constants.HEIGHT + Constants.SPACEY*2, Constants.WIDTH2, Constants.HEIGHT);
		spellForceTf.setBounds(offsetx + Constants.SPACEX + Constants.WIDTH2, spellForceLbl.getY(), Constants.WIDTH2, Constants.HEIGHT);
		spellControlLbl.setBounds(spellForceTf.getX() + Constants.SPACEX + Constants.WIDTH2, spellForceLbl.getY(), Constants.WIDTH2, Constants.HEIGHT);
		spellControlTf.setBounds(spellControlLbl.getX() + Constants.SPACEX + Constants.WIDTH2, spellControlLbl.getY(), Constants.WIDTH2, Constants.HEIGHT);
		
		spellPowerBasisLbl.setBounds(spellForceLbl.getX(), offsety + Constants.HEIGHT*2 + Constants.SPACEY*3, Constants.WIDTH2, Constants.HEIGHT);
		spellPowerBasisTf.setBounds(spellForceTf.getX(), offsety + Constants.HEIGHT*2 + Constants.SPACEY*3, Constants.WIDTH2, Constants.HEIGHT);
		spellStoreLbl.setBounds(spellForceTf.getX() + Constants.SPACEX + Constants.WIDTH2, offsety + Constants.HEIGHT*2 + Constants.SPACEY*3, Constants.WIDTH2, Constants.HEIGHT);
		spellStoreTf.setBounds(spellStoreLbl.getX() + Constants.SPACEX + Constants.WIDTH2, offsety + Constants.HEIGHT*2 + Constants.SPACEY*3, Constants.WIDTH2, Constants.HEIGHT);
		manaControlLbl.setBounds(spellStoreTf.getX() + Constants.SPACEX + Constants.WIDTH2, offsety + Constants.HEIGHT*2 + Constants.SPACEY*3, Constants.WIDTH2, Constants.HEIGHT);
		manaControlTf.setBounds(manaControlLbl.getX() + Constants.SPACEX + Constants.WIDTH2, offsety + Constants.HEIGHT*2 + Constants.SPACEY*3, Constants.WIDTH2, Constants.HEIGHT);
		
		spellPowerLbl.setBounds(offsetx, offsety + Constants.HEIGHT*3 + Constants.SPACEY*4, Constants.WIDTH2, Constants.HEIGHT);
		spellPowerTf.setBounds(offsetx + Constants.SPACEX + Constants.WIDTH2, offsety + Constants.HEIGHT*3 + Constants.SPACEY*4, Constants.WIDTH2, Constants.HEIGHT);
		spellSpeedLbl.setBounds(spellStoreLbl.getX(), offsety + Constants.HEIGHT*3 + Constants.SPACEY*4, Constants.WIDTH2, Constants.HEIGHT);
		spellSpeedTf.setBounds(spellStoreTf.getX(), offsety + Constants.HEIGHT*3 + Constants.SPACEY*4, Constants.WIDTH2, Constants.HEIGHT);
		
		// BASIC SKILLS
		
		offsety = spellSpeedTf.getY() + Constants.HEIGHT*2;
		
		basicSpellSkillTopic.setBounds(offsetx, offsety, Constants.WIDTH1*2, Constants.HEIGHT);
		
		
		int i = 0;	// counts only active skills
		for (int j = 0; j < spellSkillLabels.size(); j++)
		{
			if (Integer.parseInt(spellSkillTextfields.get(j).getText()) >= 0)
			{
				spellSkillLabels.get(j).setBounds(offsetx + 2*(i%3)*(Constants.WIDTH2+Constants.SPACEX), offsety+(1+i/3)*(Constants.HEIGHT+Constants.SPACEY), Constants.WIDTH2, Constants.HEIGHT);
				spellSkillTextfields.get(j).setBounds(offsetx + (1+2*(i%3))*(Constants.WIDTH2+Constants.SPACEX), offsety+(1+i/3)*(Constants.HEIGHT+Constants.SPACEY), Constants.WIDTH2, Constants.HEIGHT);

				spellSkillLabels.get(j).setVisible(true);
				spellSkillTextfields.get(j).setVisible(true);
				i++;
			}
			else
			{
				spellSkillLabels.get(j).setVisible(false);
				spellSkillTextfields.get(j).setVisible(false);
			}
		}
		
		if (i < SkillLists.SPELLSKILLS)
		{
			i += (3-i%3);	// new line 
						
			buySpellSkill.setBounds(offsetx + (2*(i%3))*(Constants.WIDTH2+Constants.SPACEX), offsety+(1+i/3)*(Constants.HEIGHT+Constants.SPACEY), Constants.WIDTH2*2+Constants.SPACEX, Constants.HEIGHT);
		}
		else
			buySpellSkill.setBounds(0, 0, 0, 0);

		// SPECIAL SKILLS
		
//		int lowestSpecial = 0;
//		
//		if (i < SkillLists.BASICSKILLS)
//			offsety = buyBasicSpellSkill.getY() + Constants.HEIGHT*2;
//		else
//			offsety = basicSpellSkillTextfields.getLast().getY() + Constants.HEIGHT*2;
//		
//		additionalSpellSkillTopic.setBounds(offsetx, offsety, Constants.WIDTH1*2, Constants.HEIGHT);
//		
//		int j = 0;	// counts only active skills
//		for (i = 0; i < additionalSpellSkillLabels.size(); i++)
//		{
//			if (Integer.parseInt(additionalSpellSkillTextfields.get(i).getText()) >= 0)
//			{
//				additionalSpellSkillLabels.get(i).setBounds(offsetx, offsety+(1+j)*(Constants.HEIGHT+Constants.SPACEY), Constants.WIDTH2, Constants.HEIGHT);
//				additionalSpellSkillTextfields.get(i).setBounds(offsetx + Constants.WIDTH2+Constants.SPACEX, offsety+(1+j)*(Constants.HEIGHT+Constants.SPACEY), Constants.WIDTH2, Constants.HEIGHT);
//				additionalSpellSkillLabels.get(i).setVisible(true);
//				additionalSpellSkillTextfields.get(i).setVisible(true);
//				j++;
//				lowestSpecial = i;
//			}
//			else
//			{
//				additionalSpellSkillLabels.get(i).setVisible(false);
//				additionalSpellSkillTextfields.get(i).setVisible(false);
//			}
//		}
//		buyAdditionalSpellSkill.setBounds(offsetx, offsety+(2+j)*(Constants.HEIGHT+Constants.SPACEY), Constants.WIDTH2*2+Constants.SPACEX, Constants.HEIGHT);
//
//		
		
		// ARCHMAGIC SKILLS
		
		int lowestArchmagic = 0;
		
		offsety+= (3+i/3)*(Constants.HEIGHT+Constants.SPACEY);
				
		archmagicSpellSkillTopic.setBounds(offsetx, offsety, Constants.WIDTH1*2, Constants.HEIGHT);
		
		int j = 0;	// counts only active skills
		for (i = 0; i < archmageSpellSkillLabels.size(); i++)
		{
			if (Integer.parseInt(archmageSpellSkillTextfields.get(i).getText()) >= 0)
			{
				archmageSpellSkillLabels.get(i).setBounds(offsetx, offsety+(1+j)*(Constants.HEIGHT+Constants.SPACEY), Constants.WIDTH2, Constants.HEIGHT);
				archmageSpellSkillTextfields.get(i).setBounds(offsetx + Constants.WIDTH2+Constants.SPACEX, offsety+(1+j)*(Constants.HEIGHT+Constants.SPACEY), Constants.WIDTH2, Constants.HEIGHT);
				archmageSpellSkillLabels.get(i).setVisible(true);
				archmageSpellSkillTextfields.get(i).setVisible(true);
				j++;
				lowestArchmagic = i;
			}
			else
			{
				archmageSpellSkillLabels.get(i).setVisible(false);
				archmageSpellSkillTextfields.get(i).setVisible(false);
			}
		}
		
		if (j == 0)	// no styles found
			archmagicSpellSkillTopic.setVisible(false);
		else
			archmagicSpellSkillTopic.setVisible(true);
		// STYLE

		offsetx = Constants.OFFSETX + FIELDWIDTH / 2;

			if (buySpellSkill.getY() > archmageSpellSkillLabels.get(lowestArchmagic).getY())
				offsety = buySpellSkill.getY() + Constants.HEIGHT*2;
			else
				offsety = archmageSpellSkillLabels.get(lowestArchmagic).getY() + Constants.HEIGHT*2;	
		
		
		styleTopic.setBounds(offsetx, offsety, Constants.WIDTH1, Constants.HEIGHT);
		
		styleCombo.setBounds(offsetx, offsety + Constants.HEIGHT + Constants.SPACEY, Constants.WIDTH2 * 2,Constants.HEIGHT);

		styleSpecialRulesBt.setBounds(offsetx, offsety + 2* (Constants.HEIGHT + Constants.SPACEY), FIELDWIDTH+ FIELDSOFFSET - Constants.SPACEX, Constants.HEIGHT);
		
		for (i = 1; i < 16; i++)																		// before: ants.SPACEY), FIELDWIDTH	- offsetx / 2, Constants.HEIGHT				 
			comArr[i - 1].setBounds(offsetx + (i * FIELDWIDTH) + FIELDSOFFSET, offsety + 2	* (Constants.HEIGHT + Constants.SPACEY), FIELDWIDTH	- Constants.SPACEX, Constants.HEIGHT);
		
		scrollbar.setBounds(offsetx, comArr[0].getY() + Constants.HEIGHT + Constants.SPACEY * 2, 16 * FIELDWIDTH + SCROLLBARWIDTHPLUS, 15);

		scrollbarTf.setBounds(offsetx + FIELDSOFFSET + 3 * FIELDWIDTH, styleCombo.getY(), FIELDWIDTH - Constants.SPACEX, Constants.HEIGHT);
		
				// LAST BUTTONS

		buyAdvantageBt.setBounds(offsetx + FIELDSOFFSET + 14 * FIELDWIDTH, styleCombo.getY()-Constants.HEIGHT-Constants.SPACEY, FIELDWIDTH * 2 - Constants.SPACEX, Constants.HEIGHT);
		buyArchmageSpellSkill.setBounds(offsetx + FIELDSOFFSET + 14 * FIELDWIDTH, styleCombo.getY(), FIELDWIDTH * 2 - Constants.SPACEX, Constants.HEIGHT);
		saveButton.setBounds(offsetx, buyArchmageSpellSkill.getY() + Constants.HEIGHT*5+Constants.SPACEY*2, Constants.WIDTH2, Constants.HEIGHT);

	}
	
	public void addComponents() {
		add(csMenu);

		add(basicSpellSkillTopic);
		add(additionalSpellSkillTopic);
		add(archmagicSpellSkillTopic);
		add(valuesTopic);
		add(styleTopic);
		
		Iterator<MyLabel> it1 = spellSkillLabels.iterator();
		while (it1.hasNext())
			add(it1.next());
//		it1 = additionalSpellSkillLabels.iterator();
//		while (it1.hasNext())
//			add(it1.next());
		it1 = archmageSpellSkillLabels.iterator();
		while (it1.hasNext())
			add(it1.next());
		
		Iterator<MyTextField> it2 = spellSkillTextfields.iterator();
		while (it2.hasNext())
			add(it2.next());
//		it2 = additionalSpellSkillTextfields.iterator();
//		while (it2.hasNext())
//			add(it2.next());
		it2 = archmageSpellSkillTextfields.iterator();
		while (it2.hasNext())
			add(it2.next());
		
		add(buySpellSkill);
//		add(buyAdditionalSpellSkill);
		add(buyArchmageSpellSkill);
		
		add(spellForceLbl);
		add(spellControlLbl);
		add(spellForceTf);
		add(spellControlTf);

		add(spellSpeedLbl);
		add(spellSpeedTf);
		add(spellPowerBasisLbl);
		add(spellPowerBasisTf);
		
		add(styleCombo);

		add(styleSpecialRulesBt);
		
		for (int i = 0; i < comArr.length; i++)
			add(comArr[i]);
		
		add(scrollbar);
		
		add(scrollbarTf);
		
		add(spellPowerLbl);
		add(spellStoreLbl);
		add(manaControlLbl);

		add(spellPowerTf);
		add(spellStoreTf);
		add(manaControlTf);
		
		add(buyAdvantageBt);
		
		add(saveButton);
	}

	public LinkedList getNotes() {
		return father.getRInf().getFightNotesLl();
	}

	public LinkedList getSpecIcons() {
		return null;
	}

	public JComponent getFocusComponentInClickedRow(JComponent comp) {

		if (spellSkillLabels.contains(comp))
			return basicSpellSkillTopic;
//		if (additionalSpellSkillLabels.contains(comp))
//			return additionalSpellSkillTopic;
		if (archmageSpellSkillLabels.contains(comp))
			return archmagicSpellSkillTopic;
		
		if (comp == spellForceLbl)
			return spellForceTf;
		if (comp == spellControlLbl)
			return spellControlTf;
		if (comp == spellPowerLbl)
			return spellPowerTf;
		if (comp == manaControlLbl)
			return manaControlTf;
		if (comp == spellStoreLbl)
			return spellStoreTf;
		if (comp == spellPowerBasisLbl)
			return spellPowerBasisTf;
		if (comp == spellSpeedLbl)
			return spellSpeedTf;

		if (comp == styleCombo || comp == scrollbar)
			return scrollbarTf;

		for (int i = 0; i < comArr.length; i++)
			if (comp == comArr[i])
				return scrollbarTf;
		
		Iterator it = advantagesLabelLl.iterator();
		while (it.hasNext())
			if (comp == it.next())
				return null;
		
		if (comp instanceof MyButton)
			return null;
		
		return comp;
	}

	public MyLabel getTopicInClickedRow(JComponent component) {
		return null;
	}

	public String serializeYourself() {
		String ret = new String("wiz;");
		
		ret += spellSkillTextfields.size() + ";";
		for (MyTextField tf : spellSkillTextfields)
			ret += tf.getText() + ";";
		
//		ret += additionalSpellSkillTextfields.size() + ";";
//		for (MyTextField tf : additionalSpellSkillTextfields)
//			ret += tf.getText() + ";";
		
		ret += archmageSpellSkillTextfields.size() + ";";
		for (MyTextField tf : archmageSpellSkillTextfields)
			ret += tf.getText() + ";";
		
		ret += 
				spellForceTf.getText() + ";" +
				spellControlTf.getText() + ";" +
				spellPowerBasisTf.getText() + ";" +
				spellSpeedTf.getText() + ";" +
				styleCombo.getSelectedIndex() + ";";
		
		for (int i = 0; i < comArr.length; i++)
			if (comArr[i] instanceof MyComboBox)
				ret += ((MyComboBox) (comArr[i])).getSelectedIndex() + ";";
				
		ret += scrollbarTf.getText() + ";";
		ret += bought.size() + ";";
		
		for (Advantage a : bought)
			ret += a.getShortcut() + ";";

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
		doNotActualize = true;
		basicSkillsHaveBeenInitialized = true; // otherwise they'd get initialized!
		AdvantageFactory af = new AdvantageFactory();
		
		StringTokenizer t = new StringTokenizer(s, ";");
		
		if (!t.nextToken().equals("wiz"))
		{
			father.getStatusLabel().setText("WizardPanel falsch deserialisiert");
			return;
		}
		
		int size = Integer.parseInt(t.nextToken());
		for (int i = 0; i < size; i++)
			spellSkillTextfields.get(i).setText(t.nextToken());

		if (version < 3)	// in older versions, basic spell skills and additional spell skills were in a different list. Thus, thez were stored separately, too.
		{
			size = Integer.parseInt(t.nextToken());
			for (int i = 0; i < size; i++)
				spellSkillTextfields.get(i).setText(t.nextToken());
		}
//		size = Integer.parseInt(t.nextToken());
//		for (int i = 0; i < size; i++)
//			additionalSpellSkillTextfields.get(i).setText(t.nextToken());
		
		size = Integer.parseInt(t.nextToken());
		for (int i = 0; i < size; i++)
			archmageSpellSkillTextfields.get(i).setText(t.nextToken());
		
		spellForceTf.setText(t.nextToken());
		spellControlTf.setText(t.nextToken());
		spellPowerBasisTf.setText(t.nextToken());
		spellSpeedTf.setText(t.nextToken());
		styleCombo.setSelectedIndex(Integer.parseInt(t.nextToken()));
		
		for (int i = 0; i < comArr.length; i++)
			if (comArr[i] instanceof MyComboBox)
				((MyComboBox) (comArr[i])).setSelectedIndex(Integer.parseInt(t.nextToken()));

		scrollbarTf.setText(t.nextToken());
		
		bought= new LinkedList<Advantage>();
		size = Integer.parseInt(t.nextToken());
		for (int i = 0; i < size; i++)
			bought.add(af.getMagicalAdvantageByShortcut(t.nextToken()));

		try {
			Noteable[] notecomps = getNoteableComponents();
			
			while (t.hasMoreTokens())
			{
				String text = t.nextToken();
				int index = Integer.parseInt(t.nextToken());
				addNote((JComponent)(notecomps[index]), text, true);
			}
			
			doNotActualize = false;

			actualizeValues();
			setGPEPLabel();
		} catch (Exception ex) {
			ex.printStackTrace();
			father.getStatusLabel().setText("Etwas geht schief");
		}
	}

	public void deserializeYourself() {
		doNotActualize = true;
		
		int i = 0;
		Iterator<MyTextField> it2 = spellSkillTextfields.iterator();
		while (it2.hasNext())
			it2.next().setText("-2");
		
//		it2 = additionalSpellSkillTextfields.iterator();
//		while (it2.hasNext())
//			it2.next().setText("-2");
		
		it2 = archmageSpellSkillTextfields.iterator();
		while (it2.hasNext())
			it2.next().setText("-2");
		
		spellForceTf.setText("0");
		spellControlTf.setText("0");
		spellPowerBasisTf.setText("0");
		spellSpeedTf.setText("0");
		scrollbar.setValue(0);
		
		styleCombo.setSelectedIndex(0);
		
		scrollbarTf.setText("0");
		bought = new LinkedList<Advantage>();

		doNotActualize = false;

		actualizeValues();
		setGPEPLabel();
	}

	public JComponent getDefaultFocusComponent() {
		return spellSkillTextfields.getFirst();
	}

	// error handling

	public String checkForErrors() {
		try {
			if (father.getHero().getMagic()[Constants.MAGICIAN])
			{
				int spellSkills = Integer.parseInt(spellForceTf.getText()) + Integer.parseInt(spellControlTf.getText());
				Iterator<MyTextField> iterator = spellSkillTextfields.iterator();
				while (iterator.hasNext())
					spellSkills += Integer.parseInt(iterator.next().getText());
				
				if (spellSkills < 10)
					addNote(saveButton, "Als Magier musst du mindestens 10 Fertigkeitspunkte auf Zauberfertigkeiten verteilen.", true, false);
				else
					if (saveButton.getNote() != null)
					deleteNote(saveButton);
			}
			
			if (Integer.parseInt(spellForceTf.getText()) > 15)
				return valueTooHigh(spellForceTf);
			if (Integer.parseInt(spellForceTf.getText()) < 0)
				return valueTooLow(spellForceTf);

			if (Integer.parseInt(spellControlTf.getText()) > 15)
				return valueTooHigh(spellControlTf);
			if (Integer.parseInt(spellControlTf.getText()) < 0)
				return valueTooLow(spellControlTf);
			
			for (int i = 0; i < spellSkillTextfields.size(); i++)
			{
				if (Integer.parseInt(spellSkillTextfields.get(i).getText()) > 20)
					return valueTooHigh(spellSkillTextfields.get(i));
				if (Integer.parseInt(spellSkillTextfields.get(i).getText()) < -2)
					return valueTooLow(spellSkillTextfields.get(i));
			}
			
//			for (int i = 0; i < additionalSpellSkillTextfields.size(); i++) 
//			{
//				if (Integer.parseInt(additionalSpellSkillTextfields.get(i).getText()) > 20)
//					return valueTooHigh(additionalSpellSkillTextfields.get(i));
//				if (Integer.parseInt(additionalSpellSkillTextfields.get(i).getText()) < -2)
//					return valueTooLow(additionalSpellSkillTextfields.get(i));
//			}
			
			for (int i = 0; i < archmageSpellSkillTextfields.size(); i++) 
			{
				if (Integer.parseInt(archmageSpellSkillTextfields.get(i).getText()) > 20)
					return valueTooHigh(archmageSpellSkillTextfields.get(i));
				if (Integer.parseInt(archmageSpellSkillTextfields.get(i).getText()) < -2)
					return valueTooLow(archmageSpellSkillTextfields.get(i));
			}

			Integer.parseInt(scrollbarTf.getText());
			} catch (NumberFormatException nfe) {
			return numberFormatExceptionCaught(nfe.getMessage().substring(19,
					nfe.getMessage().length() - 1));
		}

		return "alles in Butter";
	}

	public String valueTooHigh(MyTextField comp) {
		Random rnd = new Random(System.currentTimeMillis());

		if (angry == true) // error # 13 makes it angry ^^
		{
			Component[] comps = getComponents();
			Color[] colors = {Color.RED, Color.MAGENTA, Color.YELLOW, Color.LIGHT_GRAY, Color.CYAN, 
					Color.BLACK, Color.GRAY, Color.BLUE, Color.GREEN, Color.PINK, Color.YELLOW, Color.ORANGE};
			for (int i = 0; i < comps.length; i++)
				comps[i].setBackground(colors[i%colors.length]);
			angry = false;
			return "alles in Butter";
		}

		switch (Math.abs(rnd.nextInt() % 13)) {
		case 0:
			return "Dieser Wert ist ganz eindeutig zu hoch!";
		case 1:
				return "Du hast es eindeutig ein wenig übertrieben. Runter vom Gas!";
		case 2:

				return "Schau her. Ich bin friedlich. Du bist friedlich. Aber diese "+comp.getText()+" ist drauf und dran, unseren Frieden zu stören!";
		case 3:
			return "Schau Dir noch mal GUT an, was Du da gerade getippt hast.";
		case 4:
			return "Für wen hältst Du Dich eigentlich? "+comp.getText()+" weißt Du sicher auch, dass zu viel ist!";
		case 5:
			return "Jetz' is' aber genug.";
		case 6:
			return "Mit einer kleinen Geldspende an Dominik lässt sich das vielleicht regeln, aber momentan muss ich da einfach Nein sagen!";
		case 7:
			return "Du willst ja hoch hinaus! Ehrgeiz ist ja schön und gut, aber schau mal im \"Feuer und Stab\" nach!";
		case 8:
			return "Hochmut kommt vor dem Fall!";
		case 9:
			return "Machen wir einen Handel: Du darfst den Wert behalten und ich meistere das nächste Abenteuer!";
		case 10:
			return "Mit etwas Verhandlungsgeschick lass ich Dir den Wert durchgehn. Ops, mit Maschinen kann man nicht handeln!";
		case 11:
			return "Wer hoch fliegt, kann tief fallen. So, jetzt runter mit dem Wert!";
		case 12:
			angry = true;
			return "Nicht nur Du willst mächtig sein! Auch ich - und ich werde Dir gleich eine Kostprobe meiner Macht servieren! HARHARHAR!";

		default:
			return "Irgendwas geht gründlich schief...";
		}
	}

	public String valueTooLow(MyTextField comp) {
		Random rnd = new Random(System.currentTimeMillis());

		if (angry == true) // error # 13 makes it angry ^^
		{
			addNote(saveButton,
					"Size does matter!", false);
			father.setSize(20, 30);
			angry = false;
			return "alles in Butter";
		}

		switch (Math.abs(rnd.nextInt() % 13)) {
		case 0:
			return "Nana, wer wird denn überbescheiden sein!";
		case 1:
			return "Du bist verrückt. Jeder andere würde einen zu hohen Wert nehmen und du ...";
		case 2:
			return "Es ist weder maßlos noch arrogant, wenn Du einen Wert über 0 nimmst. Ehrlich!";
		case 3:
			return "Dein Wert ist einfach zu niedrig.";
		case 4:
			return "Trau Dich ruhig! Du darfst einen höheren Wert nehmen. Du MUSST!";
		case 5:
			return "Denk mal gut darüber nach, was Du da gerade getippt hast!";
		case 6:
			return "Bescheidenheit ist ja eine Tugend...";
		case 7:
			return "Man kann das Sparen auch übertreiben, weißt Du?";
		case 8:
			return "Ich hab ja auch was gegen Powergaming, aber man kann wirklich zu weit gehen.";
		case 9:
			return "Wenn Du schlechter Helden spielen willst, könntest Du ja weniger GP nehmen.";

		case 10:
			return "Hast Du schon mal versucht, den Scrollbar in den negativen Bereich zu ziehen? Vielleicht kriegst Du dann Nachteile statt Vorteilen!";
		case 11:
			return "Wenn nur alle Menschen so bescheiden wären wie Du!";
		case 12:
			angry = true;
			return "In Ordnung, jetz' is' aber genug. Mach den Wert größer oder ich mach das Fenster kleiner!";

		default:
			return "Irgendwas geht gründlich schief...";
		}
	}

	public String numberFormatExceptionCaught(String errorMessage) {
		Random rnd = new Random(System.currentTimeMillis());

		if (angry == true) // error # 13 makes it angry ^^
		{
			basicSpellSkillTopic.setText("Schrmbzglgrmstüdm?");
			additionalSpellSkillTopic.setText("&&schar33/3ts?§$$§FRRUZ");
			archmagicSpellSkillTopic.setText("Njadarmygaddawyeeh!!");
			valuesTopic.setText("Trz%&§=))erz(sff§$?");
			styleTopic.setText("FRABRÛL?");
			
			spellForceLbl.setText("!trubeghcsrA");  
			styleSpecialRulesBt.setText("((sadfkl?($@quzaä+##ä-");
			
			spellStoreLbl.setText("abrabbelsabbel -.-");
			saveButton.setText("µ"); 

			angry = false;
			return "alles in Butter";
		}

		switch (Math.abs(rnd.nextInt() % 13)) {
		case 0:
			return "Wenn Du weißt, was eine NumberFormatException ist, dann beheb den Fehler!";
		case 1:
			return "Es fällt mir gewissermaßen schwer, " + errorMessage
					+ " und andere Zahlen zusammenzuzählen...";
		case 2:
			return "Brzlg rmgrlirgnum rugraar " + errorMessage + " gklbmpth¿";
		case 3:
			return "Ja, ich fange auch sowas ab.";
		case 4:
			return "War das Absicht, um mich zu ärgern?";
		case 5:
			return "Ich kann Buchstaben sowenig in Zahlen umwandeln, wie du Scheiße in Gold!";
		case 6:
			return "Was soll ich mit diesem Mist berechnen?";
		case 7:
			return "Ich berechne mit Zahlen wie " + errorMessage
					+ " und Du überweist mir 90.000.000 €, okay?";
		case 8:
			return "Du hackst auf meinen Gefühlen rum. Du weißt, dass ich "
					+ errorMessage + " nicht verstehe.";
		case 9:
			return "Ist das Deine Art, Humor zu zeigen?";
		case 10:
			return "-.-";
		case 11:
			return "Ist das zwergisch?";
		case 12:
			angry = true;
			return "Das kann ich auch! Ich warne Dich!!";

		default:
			return "Irgendwas geht gründlich schief...";
		}
	}

	// own methods

public void adjustAdvantages() {
	
		int value = scrollbar.getValue();
		Style style = (Style)(styleCombo.getSelectedItem());
		AdvantageFamily[] advantages = style.getAdvantages();
		
		spellStorePlus = false; 
		manaControlPlus = 0;
		
		Iterator<JLabel> advIt = advantagesLabelLl.iterator();
		while (advIt.hasNext())
			remove(advIt.next());
		advantagesLabelLl = new LinkedList<JLabel>();

		for (int i = 0; i < value + (bought == null ? 0 : bought.size()); i++) {
			Advantage iteratedAdvantage = null;
			if (i < value) // regular advantages
			{
				if (advantages[i] == null)
					iteratedAdvantage = null;
				else {
					if (advantages[i] instanceof Advantage) // easy case - it's
															// no group
						iteratedAdvantage = (Advantage) (advantages[i]);
					else if (advantages[i] instanceof AdvantageGroup) // holy
																		// fuck
					{
						AdvantageGroup group = (AdvantageGroup) (advantages[i]);
						// there can be the case of a single path (commonpath)
						// or two different pathes (path1 and path2);
						// I must match both cases by simply iterating them.

						if (comArr[i] instanceof MyTextField)
							/*
							 * This is a very special case. E.g. an offensive
							 * style has been raised to 9 or so and then changed
							 * to a defensive one with comboboxes. offDefKCombo
							 * is automatically switched to defensive this calls
							 * itemStateChanged, and then adjustadvantages. But
							 * still compArr is not updated, so it would cause
							 * an error. If I simply break the itemStateChanged
							 * of the style itself will call adjustAdvantages
							 * immediately.
							 */
							return;

						int index = ((MyComboBox) (comArr[i])).getSelectedIndex();

						if (group.getCommonpath().isEmpty() == false) {
							Iterator<Advantage> it = group.getCommonpath().iterator();
							int j = 0;
							while (j++ <= index)
								iteratedAdvantage = it.next();
						} else {
							if (!group.getPath1().isEmpty()) {
								Iterator<Advantage> it = group.getPath1()
										.iterator();
								int j = 0;
								while (j++ <= index && it.hasNext())
									iteratedAdvantage = it.next();

								j--;
								it = group.getPath2().iterator();
								while (j++ <= index)
									iteratedAdvantage = it.next();
							}
							if (!group.getPath3().isEmpty()) {
								Iterator<Advantage> it = group.getPath3()
										.iterator();
								int j = 0;
								while (j++ <= index && it.hasNext())
									iteratedAdvantage = it.next();

								j--;
								it = group.getPath4().iterator();
								while (j++ <= index)
									iteratedAdvantage = it.next();
							}
						}
					} else
						father.statusLabel.setText("Irgendwas rennt verdammt schief!!");
				}
			} else
				// bought extra advantages
				iteratedAdvantage = bought.get(i - value);

			// Well, now we have the iteratedAdvantage. Let's take care of it:

			if (iteratedAdvantage != null) // not an empty advantage field
			{
				if (iteratedAdvantage.isBinary() == false) // Init+1, SW+1, KTMP,
															// ...
				{
					JTextField label = new JTextField(iteratedAdvantage.getShortcut());
					label.setToolTipText(iteratedAdvantage.getLabel() + " - " + iteratedAdvantage.getDesc());
					label.setEditable(false);
					addListeners(label);
	
					advantagesLabelLl.add(label);
					
				} else {
					String shortcut = iteratedAdvantage.getShortcut();
					if (shortcut.equals("MK")) 
						manaControlPlus++;
					if (shortcut.equals("ZS"))
						spellStorePlus = true;
					if (shortcut.equals("Erz"))
						archMage = true;
					if (shortcut.equals("Sich, MK"))
					{
						manaControlPlus++;
						JTextField label = new JTextField("Sich");
						label.setToolTipText("Um 2 verhaute Zauber würfeln auf der Kleinen Fehlertabelle, bei um 1 verhauten darf man sich das Ergebnis aussuchen.");
						label.setEditable(false);
						addListeners(label);
						advantagesLabelLl.add(label);
					}
					if (shortcut.equals("Erz, MK"))
					{
						manaControlPlus++;
						archMage = true;
						JTextField label = new JTextField("Erz");
						label.setToolTipText("Der Zauberer hat Zugriff auf Erzmagie.");
						label.setEditable(false);
						addListeners(label);
						advantagesLabelLl.add(label);
					}
				}
			}
		}
		
		if (archMage) {
			if (father.getSInf().getFreeArchmagicField() == SerializableInformation.ARCHMAGIC_NONE) 
				father.getSInf().setFreeArchmagicField(SerializableInformation.ARCHMAGIC_UNUSED);
			buyArchmageSpellSkill.setVisible(true);
			}
		else
		{
			switch (father.getSInf().getFreeArchmagicField()) {
			case SerializableInformation.ARCHMAGIC_USED: // FEHLERMELDUNG!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			case SerializableInformation.ARCHMAGIC_UNUSED: father.getSInf().setFreeArchmagicField(SerializableInformation.ARCHMAGIC_NONE); break;
			}
			buyArchmageSpellSkill.setVisible(false);
		}
	}

	public void actualizeValues() {
		if (doNotActualize)
			return;

		doNotActualize = true;

		// coordinate scrollbar and textfield
		int st = Integer.parseInt(scrollbarTf.getText());
		if (st < 0 || st > 15)
			scrollbarTf.setText(scrollbar.getValue() + "");
		else
			scrollbar.setValue(st);

		if (st < 15 || st > 15
				&& scrollbar.getValue() < 15)
			{
				buyAdvantageBt.setVisible(false);
				Iterator<Advantage> it1 = bought.iterator();
				while (it1.hasNext())
					father.getSInf().increaseEP(costsOfAdvantage(it1.next()));	// clear of all bought advantages
				bought= new LinkedList<Advantage>();
			}
		else
			buyAdvantageBt.setVisible(true);
		
		adjustAdvantages(); 

		father.getStatusLabel().setText(checkForErrors());

		if (!father.getStatusLabel().getText().equals("alles in Butter"))
		{
			doNotActualize = false;
			return;
		}

		int spellForce = Integer.parseInt(spellPowerBasisTf.getText());
		spellPowerTf.setText("" + (spellForce + (Integer.parseInt(spellForceTf.getText())+1)/3));
//		if (spellForce == 0)
//			spellPowerTf.setText("7");
//		if (spellForce > 0 && spellForce < 3)
//			spellPowerTf.setText("8");
//		if (spellForce > 2 && spellForce < 6)
//			spellPowerTf.setText("9");
//		if (spellForce > 5 && spellForce < 10)
//			spellPowerTf.setText("10");
//		if (spellForce > 9 && spellForce < 15)
//			spellPowerTf.setText("11");
//		if (spellForce == 15)
//			spellPowerTf.setText("12");
						// normally it's -12, but since everything is rounded down the result is the same
		int manaControl = (
				father.getHero().getProperty("Empathie").getValueInt()-11)/3 + 
				(father.getHero().getProperty("Selbstbeherrschung").getValueInt()-11)/3 + 
				(Integer.parseInt(spellControlTf.getText())+1)/3;
		
		if (manaControlPlus == 0)
			manaControlTf.setText(manaControl+"");
		else
			manaControlTf.setText(manaControl+" ("+(manaControl+2*manaControlPlus)+")");
		
		spellStoreTf.setText(""+ ( 
				father.getHero().getProperty("Intelligenz").getValueInt() + 
				father.getHero().getProperty("Intuition").getValueInt() + 
				father.getHero().getProperty("Mut").getValueInt() + 
				(Integer.parseInt(spellControlTf.getText())+1)/2 +
		(spellStorePlus?father.getHero().getProperty("Seelenkraft").getValueInt():0)));
			
		double calc = father.getHero().getProperty("Empathie").getValueInt() + 
				father.getHero().getProperty("Intuition").getValueInt() + 
				father.getHero().getProperty("Mut").getValueInt() + 
				father.getHero().getProperty("Selbstbeherrschung").getValueInt();
		
		spellPowerBasisTf.setText("" + (((int)(calc) +3) / 6 - 2));
		
		
		spellPowerBasisTf.setToolTipText(Math.round(((calc+3/6-2)*100.0))/100.0+"");
		
		spellSpeedTf.setText("" + (
				father.getHero().getProperty("Geschick").getValueInt() + 
				father.getHero().getProperty("Intelligenz").getValueInt()
				+1) / 2);
		
		int offsetx = Constants.OFFSETX + FIELDWIDTH / 2;

		Iterator<JTextField> iterator = advantagesLabelLl.iterator();
		int index = 0;
		while (iterator.hasNext())
			iterator.next().setBounds(offsetx + (index++ * FIELDWIDTH) + FIELDSOFFSET, buyArchmageSpellSkill.getY() + Constants.HEIGHT*3+Constants.SPACEY*3,FIELDWIDTH - Constants.SPACEX, Constants.HEIGHT);

		iterator = advantagesLabelLl.iterator();
		while (iterator.hasNext())
			add(iterator.next());

		doNotActualize = false;
		
		repaint();
		setVisible(true); // display changes, e. g. advantages taken back
	}

	public void addListeners(JComponent mtf) {
		MyComboBox com;

		if (mtf instanceof MyComboBox) {
			((MyComboBox) (mtf)).addActionListener(this);
			((MyComboBox) (mtf)).addItemListener(this);
			((MyComboBox) (mtf)).addKeyListener(this);
		}
		if (mtf instanceof JTextField)
			((JTextField) (mtf)).addActionListener(this);
		if (mtf instanceof JButton)
			((JButton) (mtf)).addActionListener(this);

		mtf.addFocusListener(this);
		mtf.addKeyListener(this);
	}

	public void removeCompletely(JComponent comp) {
		try {
			deleteNote(comp);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		remove(comp);
		comp = null;
	}

	// Event Handlers

	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == 525) // "mouse click" by keyboard
		{
			JComponent component = (JComponent) (ke.getComponent());

			csMenu.show(component, component.getWidth() - 3, component
					.getHeight() - 3);

			notedComponent = component;
		}

		if (ke.getKeyCode() == ke.VK_CONTROL)
			ctrlPressed = true;
		else {
			if (ctrlPressed)
				if (ke.getKeyCode() == ke.VK_S)
					father.displayStartPanel();
			ctrlPressed = false;
		}
	}

	public void keyReleased(KeyEvent ke) {
		ctrlPressed = false;
	}

	public void keyTyped(KeyEvent ke) {
	}

	public void processMouseEvent(MouseEvent event) { // displays context
														// sensitive menu
		if (event.isPopupTrigger()) {
			csMenu.show(event.getComponent(), event.getX(), event.getY());

			notedComponent = getClickedComponent(event.getX(), event.getY());
		}
		super.processMouseEvent(event);
	}

	public void actionPerformed(ActionEvent ae) {
		if (doNotActualize)
			return;

		if (ae.getSource() == styleSpecialRulesBt) {
			FightingStyleSpecialRulesDialog fssrd = new FightingStyleSpecialRulesDialog(father, ((Style) (styleCombo.getSelectedItem())).getSpecialRules());
			fssrd.setLocation(300, 300);
			fssrd.setVisible(true);
			return;
		}

		if (ae.getSource() == buyAdvantageBt) {
			Iterator<Advantage> it1 = bought.iterator();
			while (it1.hasNext())
				father.getSInf().increaseEP(costsOfAdvantage(it1.next()));	// if they are left, ep costs are added afterwards
			BuyNewAdvantageDialog bnad = new BuyNewAdvantageDialog(father, BuyNewAdvantageDialog.MAGIC, bought);
			bnad.setLocation(300, 140);
			bnad.setVisible(true);
			Iterator<Advantage> it2 = bnad.getReturnList().iterator();
			bought = new LinkedList<Advantage>(); // has to be emptied, otherwise left-over advantages would be doubled
			while (it2.hasNext())
			{
				Advantage adv = it2.next();
				
				father.getSInf().increaseEP(-1*costsOfAdvantage(adv));
				bought.add(adv);
			}
		}	
				
		if (ae.getSource() == buyArchmageSpellSkill)  
		{
			ChooseArchmageSpellSkillsDialog cassd = new ChooseArchmageSpellSkillsDialog(father, father.getHero().getMagesSkillsLl());
			if (cassd.openWindow)
			{
				cassd.setLocation(300, 140);
				cassd.setVisible(true);
			}
			
			String chosen = cassd.getChosenSkill();
			if (chosen != null)
			{
				if (father.getSInf().getFreeArchmagicField() == SerializableInformation.ARCHMAGIC_USED)
					father.getSInf().increaseEP(-70);
				father.getSInf().setFreeArchmagicField(SerializableInformation.ARCHMAGIC_USED);
				
				String[] archmag = SkillLists.getArchmagicSpellSkills();
				for (int cnt = 0; cnt < SkillLists.ARCHMAGESKILLS; cnt++)
					if (chosen.equals(archmag[cnt]))
						father.getHero().getMagesSkillsLl().set(3+cnt+SkillLists.SPELLSKILLS, new Integer(0));
			}
		}
		
		if (ae.getSource() == buySpellSkill)
		{
			addSkills(0);
		}
		
		if (ae.getSource() == buySpellSkill|| ae.getSource() == buyArchmageSpellSkill)
		{	
			Iterator<Integer> it = father.getHero().getMagesSkillsLl().iterator();
			it.next();
			it.next();
			it.next();
			
			for (int cnt = 0; cnt < spellSkillTextfields.size(); cnt++)
				spellSkillTextfields.get(cnt).setText(it.next().toString());
//			for (int cnt = 0; cnt < additionalSpellSkillTextfields.size(); cnt++)
//				additionalSpellSkillTextfields.get(cnt).setText(it.next().toString());
			for (int cnt = 0; cnt < archmageSpellSkillTextfields.size(); cnt++)
				archmageSpellSkillTextfields.get(cnt).setText(it.next().toString());
			
			actualizeBounds();
		}
		
		try {
			if (ae.getActionCommand().equals("Notiz hinzufügen/bearbeiten")) {
				addEditNote(notedComponent);
				return;
			}

			if (ae.getActionCommand().equals("Notiz löschen")) {
				deleteNote(notedComponent);
				return;
			}
		} catch (ClassCastException cce) {
			return;
		} catch (NullPointerException npe) {
			return;
		}

		if (ae.getSource() == saveButton) {
			father.displayStartPanel();
			return;
		}

		actualizeValues();
		checkGPEP();
	}

	public void focusGained(FocusEvent fe) {/* nothing */
	}

	public void focusLost(FocusEvent fe) {
		if (doNotActualize)
			return;
		actualizeValues();
		checkGPEP();
	}

	/**
	 * actualizes fields displaying advantages
	 */
	public void itemStateChanged(ItemEvent ie) {
		int offsetx = Constants.OFFSETX + FIELDWIDTH / 2;
		int offsety = styleSpecialRulesBt.getY();

		Style style = (Style)(styleCombo.getSelectedItem());
		AdvantageFamily[] advantages = style.getAdvantages();
		
		if (ie.getSource() == styleCombo)
			try {
				for (int i = 0; i < 15; i++) {
					int ind = ((Noteable) (comArr[i])).getIndex();
		
					try {
						removeCompletely(comArr[i]);
						comArr[i] = advantages[i].generateComponent(this, ind);
						add(comArr[i]);
						addListeners(comArr[i]);
						if (comArr[i] instanceof MyComboBox)
							((MyComboBox) (comArr[i])).addItemListener(this);
						else
							((MyTextField) (comArr[i])).setEditable(false);
					} catch (NullPointerException npe) {
						MyTextField tf = new MyTextField(" ", ind);
						tf.setEditable(false);
						comArr[i] = tf;
						add(comArr[i]);
						addListeners(comArr[i]);
					}
				}
			
			for (int i = 1; i < 16; i++) {
				comArr[i - 1].setBounds(offsetx + (i * FIELDWIDTH) + FIELDSOFFSET, styleSpecialRulesBt.getY(), FIELDWIDTH	- Constants.SPACEX, Constants.HEIGHT);
				
				if (((Noteable) (comArr[i - 1])).getNote() != null)
					((Noteable) (comArr[i - 1])).getNote().actualizeBounds();
			}
			} catch (ClassCastException cce)
			{
				// I'm not entirely sure if this case can actuelly happen, but at this place in the FightPanel there is the line "if style instance of Style"...
			}
		
		if (doNotActualize)
			return;

		for (int i = 0; i < 15; i++)
			if (ie.getSource() == comArr[i])
			{
				doNotActualize = true;
				JComboBox box = (JComboBox) (ie.getSource());
				AdvantageFamily[] fam = style.getAdvantages();
				if (!((AdvantageGroup) (fam[i])).getPath1().isEmpty()) {
					for (int j = 0; j < 15; j++)
						if (fam[j] instanceof AdvantageGroup && !((AdvantageGroup) (fam[j])).getPath1().isEmpty())
							((JComboBox) (comArr[j])).setSelectedIndex(((AdvantageGroup) (fam[j])).setActivePath(((AdvantageGroup) (fam[i])).getActivePath(box.getSelectedIndex()), ((JComboBox) (comArr[j])).getSelectedIndex()));
				}
				if (!((AdvantageGroup) (fam[i])).getPath3().isEmpty()) {
					for (int j = 0; j < 15; j++)
						if (fam[j] instanceof AdvantageGroup && !((AdvantageGroup) (fam[j])).getPath3().isEmpty())
							((JComboBox) (comArr[j])).setSelectedIndex(((AdvantageGroup) (fam[j])).setActivePath(((AdvantageGroup) (fam[i])).getActivePath(box.getSelectedIndex()), ((JComboBox) (comArr[j])).getSelectedIndex()));
				}
				doNotActualize = false;
			}
		actualizeValues();
	}

	public void adjustmentValueChanged(AdjustmentEvent ae) {
		if (doNotActualize)
			return;

		scrollbarTf.setText(scrollbar.getValue() + "");
		
		actualizeValues();
		checkGPEP();
	}

	public void checkGPEP() {
		if (!father.getStatusLabel().getText().equals("alles in Butter"))
			return;

		LinkedList<Integer> oldskills = father.getHero().getMagesSkillsLl();
		Iterator<Integer> it = oldskills.iterator();
		LinkedList<Integer> newskills = new LinkedList<Integer>();
		
		checkTF(spellForceTf, it.next().intValue(), newskills, false);
		checkTF(spellControlTf, it.next().intValue(), newskills, false);
		checkTF(scrollbarTf, it.next().intValue(), newskills, true);
		
		for (int i = 0; i < spellSkillTextfields.size(); i++)
			checkTF(spellSkillTextfields.get(i), it.next().intValue(), newskills, false);
		
//		for (int i = 0; i < additionalSpellSkillTextfields.size(); i++)
//			checkTF(additionalSpellSkillTextfields.get(i), it.next().intValue(), newskills, false);
		
		for (int i = 0; i < archmageSpellSkillTextfields.size(); i++)
			checkTF(archmageSpellSkillTextfields.get(i), it.next().intValue(), newskills, false);
		
		father.getHero().setMagesSkillsLl(newskills);
		setGPEPLabel();
	}

	public void checkTF(MyTextField compareWith, int oldval, LinkedList<Integer> newskills, boolean isComplex) {
		try {
			int newval = Integer.parseInt(compareWith.getText());

			if (!father.getSInf().isMasterMode())
			{
				if (isComplex)
					father.getSInf().increaseEP(35 * (oldval-newval));
				else
				{
					if (newval > oldval)
						for (int i = oldval+1; i <= newval; i++)
							if (i <= 0)
								father.getSInf().increaseEP(-35);
							else
								father.getSInf().increaseEP(-(15+i*3));
					if (oldval > newval)
						for (int i = oldval; i > newval; i--)
							if (i <= 0)
								father.getSInf().increaseEP(35);
							else
								father.getSInf().increaseEP(15+i*3);
				}
			}
			newskills.add(new Integer(newval));
		} catch (NumberFormatException cce) {
			// nothing
		}
	}

	/**
	 * opens the dialogue where you can pick magical skills
	 * @param freeSkills the number of skills you get from your class; 0 if you opened the dialogue manually
	 */
	public void addSkills(int freeSkills)
	{
		ChooseYourSpellSkillsDialog cyssd = new ChooseYourSpellSkillsDialog(father, father.getHero().getMagesSkillsLl(), freeSkills);
		cyssd.setLocation(300, 140);
		cyssd.setVisible(true);
		
		LinkedList<String> retList = cyssd.getReturnList();
		
		if (freeSkills > 0 && retList == null)	// called from MainWindow, sorcerer's 1st time, and cancelled
		{
			removeMagicAbility();
//			removeMagicAbility(Constants.INTUITIVESORCERER);
			father.displayStartPanel();
		}
			
		if (retList != null && retList.size() >= freeSkills)
		{	
			father.getSInf().increaseEP(70 * freeSkills);		// compensate for the free skills 
			Iterator<Integer> iterator = father.getHero().getMagesSkillsLl().iterator();
			LinkedList<Integer> newSkillList = new LinkedList<Integer>();
			
			newSkillList.add(iterator.next());
			newSkillList.add(iterator.next());
			newSkillList.add(iterator.next()); // skip spell control and spell force and the scrollbar
			
			int i = 0;
			while (iterator.hasNext())
			{							// already taken but now entirely removed
				Integer integer = iterator.next();
//				if (i < SkillLists.BASICSKILLS)
//				{
					if (integer.intValue() > -2 && !listContains(retList, SkillLists.getSpellSkills()[i]))
					{		
						for (int i2 = integer.intValue(); i2 > 0; i2--)
							father.getSInf().increaseEP(-(15+i2*3));
						father.getSInf().increaseEP(70);
						newSkillList.add(new Integer(-2));
					}
					else
						newSkillList.add(integer);
//				}
//				else
//				{
//					if (i < SkillLists.BASICSKILLS + SkillLists.ADDITIONALSKILLS)
//					{
//						if (integer.intValue() > -2 && !listContains(retList, SkillLists.getAdditionalSpellSkills()[i-SkillLists.BASICSKILLS]))
//						{		// already taken but now entirely removed
//							for (int i2 = integer.intValue(); i2 > -2; i2--)
//								father.getSInf().increaseEP(-(15+i2*3));
//							father.getSInf().increaseEP(70);
//	
//							newSkillList.add(new Integer(-2));
//						}
//						else
//							newSkillList.add(integer);
//					}
//					else
//						newSkillList.add(integer);		// just add Archmage skills without changing them 						
//				}
				i++;
			}
	
			i = 0;
			Iterator<String> iterator2 = retList.iterator();
			while (iterator2.hasNext())
			{
				String s = iterator2.next();
				if (!s.endsWith(" "))	// case: i had already had it, and have it still. This one must be ignored. I already treated with those I had had, but do not want anymore. Those cases where I did neither have nor choose it are invisible. Here comes the last one:
				{	// I chose it newly.
					father.getSInf().increaseEP(-70);
					boolean found = false;
					for (int cnt = 0; cnt < SkillLists.SPELLSKILLS; cnt++)
						if (s.equals(SkillLists.getSpellSkills()[cnt]))
						{
							found = true;
							newSkillList.set(3+cnt, new Integer(0));
							break;
						}
//					if (!found)
//						for (int cnt = 0; cnt < SkillLists.ADDITIONALSKILLS; cnt++)
//							if (s.equals(SkillLists.getAdditionalSpellSkills()[cnt]))
//								newSkillList.set(3+cnt+SkillLists.BASICSKILLS, new Integer(0));
					i++;
				}
			}
			
			father.getHero().setMagesSkillsLl(newSkillList);
			
			Iterator<Integer> it = newSkillList.iterator();
			it.next();
			it.next();
			it.next();	// skip spellForce and stuff
			
			for (MyTextField tf : spellSkillTextfields)
				tf.setText(it.next().toString());
		}
	}
	
	private String concat(double number) {
		String ret = number + "";
		if (ret.length() > 4) {
			return ret.substring(0, 5);
		} else
			return ret;
	}

	public int costsOfAdvantage(Advantage adv)
	{
		if (father.getSInf().isCheapPriceForAdvantages())
			return (int)(adv.getCosts()*35 + (adv.getCosts()%1.0>0?1:0));	// 35*1.5 (rounded down) would be 1 ep too low.
		else																// in that case add 1 ep
			return (int)(adv.getCosts()+0.75)*35;
	}
	
	public void initializeBasicSkills(int spellForce, int numberOfSkills)
	{
		actualizeBounds();

		spellForceTf.setText(spellForce+"");

		if (basicSkillsHaveBeenInitialized)
			return;

		Iterator<MyTextField> iterator = spellSkillTextfields.iterator(); 
		for (int i = 0; i < spellSkillTextfields.size(); i++)
			iterator.next().setText("-2");
		
		if (father.getSInf().isMasterMode())
			checkGPEP();
		else
		{
			father.getSInf().setMasterMode(true);
			checkGPEP();
			father.getSInf().setMasterMode(false);
		}
			
		basicSkillsHaveBeenInitialized = true;
		
		addSkills(numberOfSkills);
	}
	
	public boolean listContains(LinkedList<String> list, String s)
	{
		Iterator<String> it = list.iterator();
		while (it.hasNext())
			if (it.next().startsWith(s))
				return true;
		return false;
	}
	
	public void removeMagicAbility ()
	{
		JOptionPane.showMessageDialog(this, "Einen Zweig zu löschen ist noch nicht möglich (WizardPanel.removeMagicAbility)");
		
//		for (int i = 0; i < Constants.THAUMATURGICALBRANCHES; i++)
//			if (father.getHero().getMagic()[i])
//			{
//				
//			}
//		if (father.getHero().getMagic()[magicBranch])
//			switch (magicBranch)
//			{
//			case Constants.MAGICIAN: father.getSInf().increaseEP(-525); 		// it's 560 EP for magicians an 35 for sorcerers; since there is no break, 525 and 35 will sum up to 560. Clever, innit?
//			case Constants.MAGESORCERER:
//			case Constants.INTUITIVESORCERER: father.getSInf().increaseEP(-35);
//				spellForceTf.setText("0");
//				spellControlTf.setText("0");
//				scrollbarTf.setText("0");				
//				
//				Iterator<MyTextField> iterator = basicSpellSkillTextfields.iterator(); 
//				for (int i = 0; i < basicSpellSkillTextfields.size(); i++)
//					iterator.next().setText("-2");
//				
//				iterator = additionalSpellSkillTextfields.iterator(); 
//				for (int i = 0; i < additionalSpellSkillTextfields.size(); i++)
//					iterator.next().setText("-2");
//				
//				iterator = archmageSpellSkillTextfields.iterator(); 
//				for (int i = 0; i < archmageSpellSkillTextfields.size(); i++)
//					iterator.next().setText("-2");
//				
//				checkGPEP();
//				basicSkillsHaveBeenInitialized = false;
//				break;
//			}
//		father.getHero().getMagic()[magicBranch] = false;
	}

	@Override
	public void setSpecializationByText(String text, String note, Specialization spec) {
		// TODO Auto-generated method stub
		
	}
	
	
}
