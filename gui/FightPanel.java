package gui;

import guidialogelements.ContextSensitiveMenuOnlyNotes;
import guidialogelements.MyButton;
import guidialogelements.MyComboBox;
import guidialogelements.MyLabel;
import guidialogelements.MyTextField;
import guidialogelements.Noteable;
import items.RangeWeaponType;

import java.awt.AWTEvent;
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
import javax.swing.JScrollBar;
import javax.swing.JTextField;

import background.Constants;
import background.SerializableNote;
import character.Advantage;
import character.AdvantageFamily;
import character.AdvantageGroup;
import character.Specialization;
import character.Style;
import dataclasses.AdvantageFactory;
import dataclasses.RangeWeaponTypeFactory;
import dataclasses.StyleFactory;

public class FightPanel extends MyPanel implements ActionListener,
		FocusListener, ItemListener, AdjustmentListener, KeyListener {
	public static final int FIELDWIDTH = 70; // single field displaying
												// advantage
	public static final int FIELDSOFFSET = 16; // once used offset (because of
												// scrollbar)
	public static final int SCROLLBARWIDTHPLUS = 25; // to adjust the length
														// of the scrollbar
	public static final int ARMEDMELEE = 0;
	public static final int UNARMEDMELEE = 1;
	public static final int ARTILLARY1 = 2;
	public static final int ARTILLARY2 = 3;
	public static final int ARTILLARY3 = 4;

	public static final int COSTS_COMPLEX = 0;
	public static final int COSTS_ELITARY = 1;

	
	JComponent notedComponent; // saves Component which gets a note or
								// specialization

	boolean doNotActualize = true; // do not check for errors until panel is
									// completely loaded
	boolean ctrlPressed = false; // allows Saving by Ctrl+S
	boolean angry = false; // This program may get angry if you drive it mad!!

	MainWindow father;
	ContextSensitiveMenuOnlyNotes csMenu;

	int atdePlus[] = 		{ 0, 0, 0 }, // rising of attack and defense (for calculation)
			initPlus[] =	{ 0, 0 }, // rising of initiative
			damPlus[] = 	{ 0, 0 }, // rising of damage
			damResPlus[] = 	{ 0, 0 }, // rising of damage resistance
			aiming[] = 		{ 0, 0 }; // aiming points (for display only)

	int at[] = { 0, 0 }; // difference from the basic (e.g. 22/18) would mean
							// an = 2;

	MyLabel fvLabel; // fighting value
	MyLabel rwfsLabel; // range weapon fighting skill
	MyLabel offDefKLabel; // topic
	MyLabel armedMeleeLabel;
	MyLabel unarmedMeleeLabel;
	MyLabel rangeWeaponsLabel;
	MyLabel artillary1Label;
	MyLabel artillary2Label;
	MyLabel artillary3Label;

	MyLabel fbvTopic, fsvTopic; // Fighting Basis Value, Fighting Skill Value
	MyTextField fbvLabel, fsvLabel;
	MyLabel rwfsvTopic;
	MyTextField rwfsvLabel;
//	MyLabel[] fightingSkillTopic;
//	MyTextField[] fightingSkillLabel;

	MyComboBox offDefKCombo; // combobox to choose style
	MyComboBox armedMeleeCombo;
	MyComboBox unarmedMeleeCombo;
	MyComboBox rangeWeaponsCombo;
	MyComboBox artillary1Combo;
	MyComboBox artillary2Combo;
	MyComboBox artillary3Combo;

	MyButton armedMeleeSpecialRulesBt;
	MyButton unarmedMeleeSpecialRulesBt;
	
	JComponent[] offDefKComArr; // fields displaying advantages
	JComponent[] armedMeleeComArr;
	JComponent[] unarmedMeleeComArr;
	JComponent[] rangeWeaponsComArr;
	JComponent[] artillary1ComArr;
	JComponent[] artillary2ComArr;
	JComponent[] artillary3ComArr;

	JScrollBar offDefKScrollbar; // scrollbar to adjust
	JScrollBar armedMeleeScrollbar;
	JScrollBar unarmedMeleeScrollbar;
	JScrollBar rangeWeaponsScrollbar;
	JScrollBar artillary1Scrollbar;
	JScrollBar artillary2Scrollbar;
	JScrollBar artillary3Scrollbar;

	MyTextField offDefKScrollbarTf; // tf to type value (instead of using the bar)
	MyTextField armedMeleeScrollbarTf;
	MyTextField unarmedMeleeScrollbarTf;
	MyTextField rangeWeaponsScrollbarTf;
	MyTextField artillary1ScrollbarTf;
	MyTextField artillary2ScrollbarTf;
	MyTextField artillary3ScrollbarTf;

	MyLabel[] atdePlusTopic; // display atde-values
	MyTextField[] atdePlusLabel;
	MyLabel[] atdeTopic;
	MyButton[] atSpinner;

	JLabel space, space2;

	MyButton[] deLabel;

	MyButton fittingUnarmedStylesBt, fittingArmedStylesBt;

	MyButton buyArmed, buyUnarmed, buyArtillary1, buyArtillary2, buyArtillary3;

	MyTextField[] atLabel; // for artillary

	MyLabel[] diceTopic;
	MyTextField[] diceLabel; // for artillary
	MyLabel[] damPlusTopic; // and so on
	MyTextField[] damPlusLabel;
	MyLabel[] damTopic;
	MyTextField[] damLabel;
	MyComboBox[] damBasis;
	MyLabel[] initPlusTopic;
	MyTextField[] initPlusLabel;
	MyLabel[] initTopic;
	MyTextField[] initLabel;
	MyLabel[] damResPlusTopic;
	MyTextField[] damResPlusLabel;
	MyLabel[] damResTopic;
	MyTextField[] damResLabel;

	MyLabel[] aimingTopic;
	MyTextField[] aimingLabel;
	MyLabel[][] artillaryValueTopics;
	MyComboBox[] rangeWeaponTypeComboBox;
//	MyComboBox[] artillaryValueProperties;
	MyTextField[] artillaryValue;
	MyTextField[] artillaryValueRaceModifier;
	MyLabel damageBasissesTopic; // topic to display damage basisses
	MyComboBox armBasisLabel; // choose damage basis for armed melee

	LinkedList armedMeleeAdvantagesLabelLl; // uncountable advantages (e. g. Critical Hit)
	LinkedList unarmedMeleeAdvantagesLabelLl;
	LinkedList artillary1AdvantagesLabelLl;
	LinkedList artillary2AdvantagesLabelLl;
	LinkedList artillary3AdvantagesLabelLl;

	LinkedList<Advantage> boughtArmed = new LinkedList<Advantage>();
	LinkedList<Advantage> boughtUnarmed = new LinkedList<Advantage>();
	LinkedList<Advantage> boughtArtillary1 = new LinkedList<Advantage>();
	LinkedList<Advantage> boughtArtillary2 = new LinkedList<Advantage>();
	LinkedList<Advantage> boughtArtillary3 = new LinkedList<Advantage>();

	MyButton saveButton; // And off!

	int index = 0; // index for MyComponents to be identificated

	// constructor

	public FightPanel(MainWindow father) {
		super(father);
		this.father = father;

		// generate elements

		csMenu = new ContextSensitiveMenuOnlyNotes(this);

		fvLabel = new MyLabel("Kampfwert", index++);
		rwfsLabel = new MyLabel("Fernkampfwert", index++);
		offDefKLabel = new MyLabel("Offensiv / Defensiv", index++);
		armedMeleeLabel = new MyLabel("Bewaffneter Kampfstil", index++);
		unarmedMeleeLabel = new MyLabel("Waffenloser Kampfstil", index++);
		rangeWeaponsLabel = new MyLabel("Fernwaffen", index++);
		artillary1Label = new MyLabel("1. Fernkampfstil", index++);
		artillary2Label = new MyLabel("2. Fernkampfstil", index++);
		artillary3Label = new MyLabel("3. Fernkampfstil", index++);

		fbvTopic = new MyLabel("Kampfbasiswert: ", index++);
		fbvLabel = new MyTextField("8.8 ~ 9", index++);

		rwfsvTopic = new MyLabel("Fernkampffertigkeitswert: ", index++);
		rwfsvLabel = new MyTextField("0", index++);

		fsvTopic = new MyLabel("Kampffertigkeitswert: ", index++);
		fsvLabel = new MyTextField("0", index++);

//		fightingSkillTopic = new MyLabel[7];
//		for (int i = 0; i < 7; i++)
//			fightingSkillTopic[i] = new MyLabel("Kampffertigkeit: ", index++);
//
//		fightingSkillLabel = new MyTextField[7];
//		for (int i = 0; i < 7; i++)
//			fightingSkillLabel[i] = new MyTextField("0", index++);

		armedMeleeCombo = new MyComboBox(index++);
		Iterator<Style> it = StyleFactory.getArmedMeleeStyles().iterator();
		while (it.hasNext())
			armedMeleeCombo.addItem(it.next());

		unarmedMeleeCombo = new MyComboBox(index++);
		it = StyleFactory.getUnarmedMeleeStyles().iterator();
		while (it.hasNext())
			unarmedMeleeCombo.addItem(it.next());

		offDefKCombo = new MyComboBox(index++);
		it = StyleFactory.getOffDefK().iterator();
		while (it.hasNext())
			offDefKCombo.addItem(it.next());

		artillary1Combo = new MyComboBox(index++);
		it = StyleFactory.getArtillaryStyles().iterator();
		while (it.hasNext())
			artillary1Combo.addItem(it.next());

		artillary2Combo = new MyComboBox(index++);
		it = StyleFactory.getArtillaryStyles().iterator();
		while (it.hasNext())
			artillary2Combo.addItem(it.next());

		artillary3Combo = new MyComboBox(index++);
		it = StyleFactory.getArtillaryStyles().iterator();
		while (it.hasNext())
			artillary3Combo.addItem(it.next());

		rangeWeaponsCombo = new MyComboBox(index++);
		rangeWeaponsCombo.addItem("Fernwaffen");

		armedMeleeSpecialRulesBt = new MyButton("Regeln", index++);
		unarmedMeleeSpecialRulesBt = new MyButton("Regeln", index++);

		offDefKComArr = new JComponent[20];
		armedMeleeComArr = new JComponent[20];
		unarmedMeleeComArr = new JComponent[20];
		rangeWeaponsComArr = new JComponent[20];
		artillary1ComArr = new JComponent[20];
		artillary2ComArr = new JComponent[20];
		artillary3ComArr = new JComponent[20];

		for (int i = 0; i < 20; i++) {
			offDefKComArr[i] = new MyTextField("", index++);
			armedMeleeComArr[i] = new MyTextField("", index++);
			unarmedMeleeComArr[i] = new MyTextField("", index++);
			rangeWeaponsComArr[i] = new MyTextField("", index++);
			artillary1ComArr[i] = new MyTextField("", index++);
			artillary2ComArr[i] = new MyTextField("", index++);
			artillary3ComArr[i] = new MyTextField("", index++);
		}

		offDefKScrollbar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 21);
		armedMeleeScrollbar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 21);
		unarmedMeleeScrollbar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 21);
		rangeWeaponsScrollbar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 21);
		artillary1Scrollbar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 21);
		artillary2Scrollbar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 21);
		artillary3Scrollbar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 21);

		offDefKScrollbarTf = new MyTextField("0", index++);
		armedMeleeScrollbarTf = new MyTextField("0", index++);
		unarmedMeleeScrollbarTf = new MyTextField("0", index++);
		rangeWeaponsScrollbarTf = new MyTextField("0", index++);
		artillary1ScrollbarTf = new MyTextField("0", index++);
		artillary2ScrollbarTf = new MyTextField("0", index++);
		artillary3ScrollbarTf = new MyTextField("0", index++);

		atdePlusTopic = new MyLabel[2];
		atdePlusTopic[0] = new MyLabel("Anab-Steigerung: ", index++);
		atdePlusTopic[1] = new MyLabel("Anab-Steigerung: ", index++);

		atdePlusLabel = new MyTextField[2];
		atdePlusLabel[0] = new MyTextField("0", index++);
		atdePlusLabel[1] = new MyTextField("0", index++);

		atdeTopic = new MyLabel[5];
		atdeTopic[0] = new MyLabel("Anab: ", index++);
		atdeTopic[1] = new MyLabel("Anab: ", index++);
		atdeTopic[2] = new MyLabel("An: ", index++);
		atdeTopic[3] = new MyLabel("An: ", index++);
		atdeTopic[4] = new MyLabel("An: ", index++);

		atSpinner = new MyButton[2];
		atSpinner[0] = new MyButton("0", index++);
		atSpinner[1] = new MyButton("0", index++);

		space = new JLabel("/");
		space2 = new JLabel("/");

		deLabel = new MyButton[2];
		deLabel[0] = new MyButton("0", index++);
		deLabel[1] = new MyButton("0", index++);

		fittingArmedStylesBt = new MyButton("bevorzugte Stile", index++);
		fittingUnarmedStylesBt = new MyButton("bevorzugte Stile", index++);

		buyArmed = new MyButton("Vorteil kaufen", index++);
		buyUnarmed = new MyButton("Vorteil kaufen", index++);
		buyArtillary1 = new MyButton("Vorteil kaufen", index++);
		buyArtillary2 = new MyButton("Vorteil kaufen", index++);
		buyArtillary3 = new MyButton("Vorteil kaufen", index++);

		atLabel = new MyTextField[3];
		atLabel[0] = new MyTextField("0", index++);
		atLabel[1] = new MyTextField("0", index++);
		atLabel[2] = new MyTextField("0", index++);

		diceTopic = new MyLabel[3];
		diceTopic[0] = new MyLabel("geworfene Würfel", index++);
		diceTopic[1] = new MyLabel("geworfene Würfel", index++);
		diceTopic[2] = new MyLabel("geworfene Würfel", index++);

		diceLabel = new MyTextField[3];
		diceLabel[0] = new MyTextField("", index++);
		diceLabel[1] = new MyTextField("", index++);
		diceLabel[2] = new MyTextField("", index++);

		damPlusTopic = new MyLabel[2];
		damPlusTopic[0] = new MyLabel("Schadenssteigerung: ", index++);
		damPlusTopic[1] = new MyLabel("Schadenssteigerung: ", index++);

		damPlusLabel = new MyTextField[2];
		damPlusLabel[0] = new MyTextField("0", index++);
		damPlusLabel[1] = new MyTextField("0", index++);

		damBasis = new MyComboBox[2];
		String[] strings1 = { "K", "KGw"};
		String[] strings2 = { "Schläge/Tritte", "Dolch" };
		damBasis[0] = new MyComboBox(strings1, index++);
		damBasis[1] = new MyComboBox(strings2, index++);

		damTopic = new MyLabel[2];
		damTopic[0] = new MyLabel("Schaden: ", index++);
		damTopic[1] = new MyLabel("Schaden: ", index++);

		damLabel = new MyTextField[2];
		damLabel[0] = new MyTextField("0", index++);
		damLabel[1] = new MyTextField("0", index++);

		initPlusTopic = new MyLabel[2];
		initPlusTopic[0] = new MyLabel("Init-Senkung: ", index++);
		initPlusTopic[1] = new MyLabel("Init-Senkung: ", index++);

		initPlusLabel = new MyTextField[2];
		initPlusLabel[0] = new MyTextField("0", index++);
		initPlusLabel[1] = new MyTextField("0", index++);

		initTopic = new MyLabel[2];
		initTopic[0] = new MyLabel("Init: ", index++);
		initTopic[1] = new MyLabel("Init: ", index++);

		initLabel = new MyTextField[2];
		initLabel[0] = new MyTextField("0", index++);
		initLabel[1] = new MyTextField("0", index++);

		damResPlusTopic = new MyLabel[2];
		damResPlusTopic[0] = new MyLabel("SW-Steigerung: ", index++);
		damResPlusTopic[1] = new MyLabel("SW-Steigerung: ", index++);

		damResPlusLabel = new MyTextField[2];
		damResPlusLabel[0] = new MyTextField("0", index++);
		damResPlusLabel[1] = new MyTextField("0", index++);

		damResTopic = new MyLabel[2];
		damResTopic[0] = new MyLabel("Schadenswiderstand: ", index++);
		damResTopic[1] = new MyLabel("Schadenswiderstand: ", index++);

		damResLabel = new MyTextField[2];
		damResLabel[0] = new MyTextField("0", index++);
		damResLabel[1] = new MyTextField("0", index++);

		aimingTopic = new MyLabel[2];
		aimingTopic[0] = new MyLabel("Zielen: ", index++);
		aimingTopic[1] = new MyLabel("Zielen: ", index++);

		aimingLabel = new MyTextField[2];
		aimingLabel[0] = new MyTextField("0", index++);
		aimingLabel[1] = new MyTextField("0", index++);

		artillaryValueTopics = new MyLabel[3][3];

		for (int i = 0; i < 3; i++) {
			artillaryValueTopics[i][0] = new MyLabel("Waffe: ", index++);
			artillaryValueTopics[i][1] = new MyLabel("FKW-Rassenmod.",
					index++);
			artillaryValueTopics[i][2] = new MyLabel("Fernkampfwert FKW",
					index++);
		}

		rangeWeaponTypeComboBox = new MyComboBox[3];
		rangeWeaponTypeComboBox[0] = new MyComboBox(RangeWeaponTypeFactory
				.getRangeWeapons(), index++);
		rangeWeaponTypeComboBox[1] = new MyComboBox(RangeWeaponTypeFactory
				.getRangeWeapons(), index++);
		rangeWeaponTypeComboBox[2] = new MyComboBox(RangeWeaponTypeFactory
				.getRangeWeapons(), index++);

//		artillaryValueProperties = new MyComboBox[3];
//		String[] initStrings = { "(Gs*2+In+K)/5", "" };
//		artillaryValueProperties[0] = new MyComboBox(initStrings, index++);
//		artillaryValueProperties[1] = new MyComboBox(initStrings, index++);
//		artillaryValueProperties[2] = new MyComboBox(initStrings, index++);

		artillaryValueRaceModifier = new MyTextField[3];
		artillaryValueRaceModifier[0] = new MyTextField("", index++);
		artillaryValueRaceModifier[1] = new MyTextField("", index++);
		artillaryValueRaceModifier[2] = new MyTextField("", index++);

		artillaryValue = new MyTextField[3];
		artillaryValue[0] = new MyTextField("", index++);
		artillaryValue[1] = new MyTextField("", index++);
		artillaryValue[2] = new MyTextField("", index++);

		damageBasissesTopic = new MyLabel("Schadensbasis ...", index++);

		String[] basisses = { "K", "KGw", "Gs" };

		armBasisLabel = new MyComboBox(basisses, index++);

		armedMeleeAdvantagesLabelLl = new LinkedList<JTextField>();
		unarmedMeleeAdvantagesLabelLl = new LinkedList<JTextField>();
		artillary1AdvantagesLabelLl = new LinkedList<JTextField>();
		artillary2AdvantagesLabelLl = new LinkedList<JTextField>();
		artillary3AdvantagesLabelLl = new LinkedList<JTextField>();

		saveButton = new MyButton("speichern", index++);

		// decorate

		Font font = getFont();
		font = new Font(font.getName(), Font.BOLD, font.getSize() + 2);

		fvLabel.setFont(font);
		rwfsLabel.setFont(font);
		offDefKLabel.setFont(font);
		armedMeleeLabel.setFont(font);
		unarmedMeleeLabel.setFont(font);
		rangeWeaponsLabel.setFont(font);
		artillary1Label.setFont(font);
		artillary2Label.setFont(font);
		artillary3Label.setFont(font);
		damageBasissesTopic.setFont(font);

		fbvLabel.setEditable(false);
		offDefKCombo.setEnabled(false);
		rangeWeaponsCombo.setEnabled(false);

		for (int i = 0; i < 20; i++) {
			((MyTextField) (offDefKComArr[i])).setEditable(false);
			((MyTextField) (armedMeleeComArr[i])).setEditable(false);
			((MyTextField) (unarmedMeleeComArr[i])).setEditable(false);
			((MyTextField) (rangeWeaponsComArr[i])).setEditable(false);
			((MyTextField) (artillary1ComArr[i])).setEditable(false);
			((MyTextField) (artillary2ComArr[i])).setEditable(false);
			((MyTextField) (artillary3ComArr[i])).setEditable(false);
		}

		atdePlusLabel[0].setEditable(false);
		atdePlusLabel[1].setEditable(false);

		atLabel[0].setEditable(false);
		atLabel[1].setEditable(false);
		atLabel[2].setEditable(false);

		diceLabel[0].setEditable(false);
		diceLabel[1].setEditable(false);
		diceLabel[2].setEditable(false);

		damPlusLabel[0].setEditable(false);
		damPlusLabel[1].setEditable(false);

		damLabel[0].setEditable(false);
		damLabel[1].setEditable(false);

		initPlusLabel[0].setEditable(false);
		initPlusLabel[1].setEditable(false);

		initLabel[0].setEditable(false);
		initLabel[1].setEditable(false);

		damResPlusLabel[0].setEditable(false);
		damResPlusLabel[1].setEditable(false);

		damResLabel[0].setEditable(false);
		damResLabel[1].setEditable(false);

		aimingLabel[0].setEditable(false);
		aimingLabel[1].setEditable(false);

		artillaryValue[0].setEditable(false);
		artillaryValue[1].setEditable(false);
		artillaryValue[2].setEditable(false);

//		artillaryValueProperties[0].setEditable(false);
//		artillaryValueProperties[1].setEditable(false);
//		artillaryValueProperties[2].setEditable(false);

		artillaryValueRaceModifier[0].setEditable(false);
		artillaryValueRaceModifier[1].setEditable(false);
		artillaryValueRaceModifier[2].setEditable(false);

		buyArmed.setVisible(false);
		buyUnarmed.setVisible(false);
		buyArtillary1.setVisible(false);
		buyArtillary2.setVisible(false);
		buyArtillary3.setVisible(false);
		
		// positioning

		setLayout(null);

		int offsety = Constants.OFFSETY;
		int offsetx = Constants.OFFSETX + FIELDWIDTH / 2;

		// FIGHTING VALUE

		fvLabel.setBounds(Constants.OFFSETX, offsety, Constants.WIDTH2 * 2,
				Constants.HEIGHT);

		fbvTopic.setBounds(Constants.OFFSETX + FIELDSOFFSET + FIELDWIDTH * 0,
				offsety + Constants.HEIGHT + Constants.SPACEY, FIELDWIDTH * 2
						- Constants.SPACEX, Constants.HEIGHT);
		fbvLabel.setBounds(Constants.OFFSETX + FIELDSOFFSET + FIELDWIDTH * 2,
				offsety + Constants.HEIGHT + Constants.SPACEY, FIELDWIDTH
						- Constants.SPACEX, Constants.HEIGHT);

		fsvTopic.setBounds(Constants.OFFSETX + FIELDSOFFSET + FIELDWIDTH * 4,
				offsety + Constants.HEIGHT + Constants.SPACEY, FIELDWIDTH * 2
						- Constants.SPACEX, Constants.HEIGHT);
		fsvLabel.setBounds(Constants.OFFSETX + FIELDSOFFSET + FIELDWIDTH * 6,
				offsety + Constants.HEIGHT + Constants.SPACEY, FIELDWIDTH
						- Constants.SPACEX, Constants.HEIGHT);

		// OFFENSIVE DEFENSIVE K

		offsety = fsvLabel.getY() + Constants.HEIGHT * 2;

		offDefKLabel.setBounds(Constants.OFFSETX, offsety,
				Constants.WIDTH2 * 2, Constants.HEIGHT);
		offDefKCombo.setBounds(Constants.OFFSETX, offsety + 1
				* (Constants.HEIGHT + Constants.SPACEY), Constants.WIDTH2 * 2,
				Constants.HEIGHT);

		offDefKScrollbarTf.setBounds(Constants.OFFSETX + FIELDSOFFSET + 3
				* FIELDWIDTH, offDefKCombo.getY(), FIELDWIDTH
				- Constants.SPACEX, Constants.HEIGHT);
//		fightingSkillTopic[0].setBounds(Constants.OFFSETX + FIELDSOFFSET + 5
//				* FIELDWIDTH, offDefKCombo.getY(), FIELDWIDTH * 2
//				- Constants.SPACEX, Constants.HEIGHT);
//		fightingSkillLabel[0].setBounds(Constants.OFFSETX + FIELDSOFFSET + 7
//				* FIELDWIDTH, offDefKCombo.getY(), FIELDWIDTH
//				- Constants.SPACEX, Constants.HEIGHT);

		for (int i = 1; i < 21; i++) {
			offDefKComArr[i - 1].setBounds(Constants.OFFSETX + (i * FIELDWIDTH)
					+ FIELDSOFFSET, offsety + 2
					* (Constants.HEIGHT + Constants.SPACEY), FIELDWIDTH
					- Constants.OFFSETX / 2, Constants.HEIGHT);
		}
		offDefKScrollbar.setBounds(Constants.OFFSETX, offDefKComArr[0].getY()
				+ Constants.HEIGHT + Constants.SPACEY * 2, 21 * FIELDWIDTH
				+ SCROLLBARWIDTHPLUS, 15);

		// ARMED MELEE

		offsety = offDefKScrollbar.getY() + Constants.HEIGHT * 3;

		armedMeleeLabel.setBounds(offsetx, offsety, Constants.WIDTH2 * 2,
				Constants.HEIGHT);
		armedMeleeCombo.setBounds(offsetx, offsety + 1
				* (Constants.HEIGHT + Constants.SPACEY), Constants.WIDTH2 * 2,
				Constants.HEIGHT);

		armedMeleeSpecialRulesBt.setBounds(offsetx, offsety + 2
				* (Constants.HEIGHT + Constants.SPACEY), FIELDWIDTH
				+ FIELDSOFFSET - Constants.SPACEX, Constants.HEIGHT);

		for (int i = 1; i < 21; i++) {
			armedMeleeComArr[i - 1].setBounds(offsetx + (i * FIELDWIDTH)
					+ FIELDSOFFSET, offsety + 2
					* (Constants.HEIGHT + Constants.SPACEY), FIELDWIDTH
					- offsetx / 2, Constants.HEIGHT);
		}
		armedMeleeScrollbar.setBounds(offsetx, armedMeleeComArr[0].getY()
				+ Constants.HEIGHT + Constants.SPACEY * 2, 21 * FIELDWIDTH
				+ SCROLLBARWIDTHPLUS, 15);

		armedMeleeScrollbarTf.setBounds(
				offsetx + FIELDSOFFSET + 3 * FIELDWIDTH,
				armedMeleeCombo.getY(), FIELDWIDTH - Constants.SPACEX,
				Constants.HEIGHT);
//		fightingSkillTopic[1].setBounds(
//				offsetx + FIELDSOFFSET + 5 * FIELDWIDTH,
//				armedMeleeCombo.getY(), FIELDWIDTH * 2 - Constants.SPACEX,
//				Constants.HEIGHT);
//		fightingSkillLabel[1].setBounds(
//				offsetx + FIELDSOFFSET + 7 * FIELDWIDTH,
//				armedMeleeCombo.getY(), FIELDWIDTH - Constants.SPACEX,
//				Constants.HEIGHT);

		fittingUnarmedStylesBt.setBounds(offsetx + FIELDSOFFSET + 9
				* FIELDWIDTH, armedMeleeCombo.getY(), FIELDWIDTH * 2
				- Constants.SPACEX, Constants.HEIGHT);

		buyArmed.setBounds(offsetx + FIELDSOFFSET + 14 * FIELDWIDTH,
				fittingUnarmedStylesBt.getY(), FIELDWIDTH * 2
						- Constants.SPACEX, Constants.HEIGHT);

		atdePlusTopic[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 0,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH * 2, Constants.HEIGHT);
		atdePlusLabel[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 2,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);

		atdeTopic[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 4,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);
		atSpinner[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 5,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);
		space.setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 25 / 4,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH / 2, Constants.HEIGHT);
		deLabel[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 13 / 2,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);

		initPlusTopic[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 8,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH * 2, Constants.HEIGHT);
		initPlusLabel[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 10,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);

		initTopic[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 12,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH * 2, Constants.HEIGHT);
		initLabel[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 14,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);

		damPlusTopic[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 0,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 3
						+ Constants.SPACEY * 2, FIELDWIDTH * 2,
				Constants.HEIGHT);
		damPlusLabel[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 2,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 3
						+ Constants.SPACEY * 2, FIELDWIDTH, Constants.HEIGHT);

		damTopic[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 4,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 3
						+ Constants.SPACEY * 2, FIELDWIDTH * 2,
				Constants.HEIGHT);
		damBasis[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 5,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 3
						+ Constants.SPACEY * 2, FIELDWIDTH, Constants.HEIGHT);
		damLabel[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 13 / 2,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 3
						+ Constants.SPACEY * 2, FIELDWIDTH, Constants.HEIGHT);

		damResPlusTopic[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 8,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 3
						+ Constants.SPACEY * 2, FIELDWIDTH * 2,
				Constants.HEIGHT);
		damResPlusLabel[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 10,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 3
						+ Constants.SPACEY * 2, FIELDWIDTH, Constants.HEIGHT);

		damResTopic[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 12,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 3
						+ Constants.SPACEY * 2, FIELDWIDTH * 2,
				Constants.HEIGHT);
		damResLabel[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 14,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 3
						+ Constants.SPACEY * 2, FIELDWIDTH, Constants.HEIGHT);

		aimingTopic[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 0,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 4
						+ Constants.SPACEY * 3, FIELDWIDTH * 2,
				Constants.HEIGHT);
		aimingLabel[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 2,
				armedMeleeScrollbar.getY() + Constants.HEIGHT * 4
						+ Constants.SPACEY * 3, FIELDWIDTH, Constants.HEIGHT);

		// UNARMED MELEE

		offsety = aimingLabel[0].getY() + Constants.HEIGHT * 4;

		unarmedMeleeLabel.setBounds(offsetx, offsety, Constants.WIDTH2 * 2,
				Constants.HEIGHT);
		unarmedMeleeCombo.setBounds(offsetx, offsety + 1
				* (Constants.HEIGHT + Constants.SPACEY), Constants.WIDTH2 * 2,
				Constants.HEIGHT);

		unarmedMeleeSpecialRulesBt.setBounds(offsetx, offsety + 2
				* (Constants.HEIGHT + Constants.SPACEY), FIELDWIDTH
				+ FIELDSOFFSET - Constants.SPACEX, Constants.HEIGHT);

		for (int i = 1; i < 21; i++) {
			unarmedMeleeComArr[i - 1].setBounds(offsetx + (i * FIELDWIDTH)
					+ FIELDSOFFSET, offsety + 2
					* (Constants.HEIGHT + Constants.SPACEY), FIELDWIDTH
					- offsetx / 2, Constants.HEIGHT);
		}
		unarmedMeleeScrollbar.setBounds(offsetx, unarmedMeleeComArr[1].getY()
				+ Constants.HEIGHT + Constants.SPACEY * 2, 21 * FIELDWIDTH
				+ SCROLLBARWIDTHPLUS, 15);

		unarmedMeleeScrollbarTf.setBounds(offsetx + FIELDSOFFSET + 3
				* FIELDWIDTH, unarmedMeleeCombo.getY(), FIELDWIDTH
				- Constants.SPACEX, Constants.HEIGHT);
//		fightingSkillTopic[2].setBounds(
//				offsetx + FIELDSOFFSET + 5 * FIELDWIDTH, unarmedMeleeCombo
//						.getY(), FIELDWIDTH * 2 - Constants.SPACEX,
//				Constants.HEIGHT);
//		fightingSkillLabel[2].setBounds(
//				offsetx + FIELDSOFFSET + 7 * FIELDWIDTH, unarmedMeleeCombo
//						.getY(), FIELDWIDTH - Constants.SPACEX,
//				Constants.HEIGHT);

		fittingArmedStylesBt.setBounds(offsetx + FIELDSOFFSET + 9 * FIELDWIDTH,
				unarmedMeleeCombo.getY(), FIELDWIDTH * 2 - Constants.SPACEX,
				Constants.HEIGHT);

		buyUnarmed.setBounds(offsetx + FIELDSOFFSET + 14 * FIELDWIDTH,
				fittingArmedStylesBt.getY(), FIELDWIDTH * 2 - Constants.SPACEX,
				Constants.HEIGHT);

		atdePlusTopic[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 0,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH * 2, Constants.HEIGHT);
		atdePlusLabel[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 2,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);

		atdeTopic[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 4,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);
		atSpinner[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 5,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);
		space2.setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 25 / 4,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH / 2, Constants.HEIGHT);
		deLabel[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 13 / 2,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);

		initPlusTopic[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 8,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH * 2, Constants.HEIGHT);
		initPlusLabel[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 10,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);

		initTopic[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 12,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH * 2, Constants.HEIGHT);
		initLabel[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 14,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);

		damPlusTopic[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 0,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 3
						+ Constants.SPACEY * 2, FIELDWIDTH * 2,
				Constants.HEIGHT);
		damPlusLabel[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 2,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 3
						+ Constants.SPACEY * 2, FIELDWIDTH, Constants.HEIGHT);

		damTopic[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 4,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 3
						+ Constants.SPACEY * 2, FIELDWIDTH * 2,
				Constants.HEIGHT);
		damBasis[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 5,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 3
						+ Constants.SPACEY * 2, FIELDWIDTH, Constants.HEIGHT);
		damLabel[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 13 / 2,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 3
						+ Constants.SPACEY * 2, FIELDWIDTH, Constants.HEIGHT);

		damResPlusTopic[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 8,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 3
						+ Constants.SPACEY * 2, FIELDWIDTH * 2,
				Constants.HEIGHT);
		damResPlusLabel[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 10,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 3
						+ Constants.SPACEY * 2, FIELDWIDTH, Constants.HEIGHT);

		damResTopic[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 12,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 3
						+ Constants.SPACEY * 2, FIELDWIDTH * 2,
				Constants.HEIGHT);
		damResLabel[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 14,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 3
						+ Constants.SPACEY * 2, FIELDWIDTH, Constants.HEIGHT);

		aimingTopic[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 0,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 4
						+ Constants.SPACEY * 3, FIELDWIDTH * 2,
				Constants.HEIGHT);
		aimingLabel[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 2,
				unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 4
						+ Constants.SPACEY * 3, FIELDWIDTH, Constants.HEIGHT);

		// RANGE WEAPON FIGHTING VALUE

		offsety = aimingLabel[1].getY() + Constants.HEIGHT * 3;

		rwfsLabel.setBounds(Constants.OFFSETX, offsety, Constants.WIDTH2 * 2,
				Constants.HEIGHT);

		rwfsvTopic.setBounds(Constants.OFFSETX + FIELDSOFFSET + FIELDWIDTH * 0,
				offsety + Constants.HEIGHT + Constants.SPACEY, FIELDWIDTH * 2
						- Constants.SPACEX, Constants.HEIGHT);
		rwfsvLabel.setBounds(Constants.OFFSETX + FIELDSOFFSET + FIELDWIDTH * 2,
				offsety + Constants.HEIGHT + Constants.SPACEY, FIELDWIDTH
						- Constants.SPACEX, Constants.HEIGHT);

		// Range Weapons

		offsety = rwfsvLabel.getY() + Constants.HEIGHT * 2;

		rangeWeaponsLabel.setBounds(Constants.OFFSETX, offsety,
				Constants.WIDTH2 * 2, Constants.HEIGHT);
		rangeWeaponsCombo.setBounds(Constants.OFFSETX, offsety + 1
				* (Constants.HEIGHT + Constants.SPACEY), Constants.WIDTH2 * 2,
				Constants.HEIGHT);

		for (int i = 1; i < 21; i++) {
			rangeWeaponsComArr[i - 1].setBounds(Constants.OFFSETX
					+ (i * FIELDWIDTH) + FIELDSOFFSET, offsety + 2
					* (Constants.HEIGHT + Constants.SPACEY), FIELDWIDTH
					- Constants.OFFSETX / 2, Constants.HEIGHT);
		}
		rangeWeaponsScrollbar.setBounds(Constants.OFFSETX,
				rangeWeaponsComArr[2].getY() + Constants.HEIGHT
						+ Constants.SPACEY * 2, 21 * FIELDWIDTH
						+ SCROLLBARWIDTHPLUS, 15);
		rangeWeaponsScrollbarTf.setBounds(Constants.OFFSETX + FIELDSOFFSET + 3
				* FIELDWIDTH, rangeWeaponsCombo.getY(), FIELDWIDTH
				- Constants.SPACEX, Constants.HEIGHT);
//		fightingSkillTopic[3].setBounds(Constants.OFFSETX + FIELDSOFFSET + 5
//				* FIELDWIDTH, rangeWeaponsCombo.getY(), FIELDWIDTH * 2
//				- Constants.SPACEX, Constants.HEIGHT);
//		fightingSkillLabel[3].setBounds(Constants.OFFSETX + FIELDSOFFSET + 7
//				* FIELDWIDTH, rangeWeaponsCombo.getY(), FIELDWIDTH
//				- Constants.SPACEX, Constants.HEIGHT);

		// ARTILLARY 1

		offsety = rangeWeaponsScrollbar.getY() + Constants.HEIGHT * 3;

		artillary1Label.setBounds(offsetx, offsety, Constants.WIDTH2 * 2,
				Constants.HEIGHT);
		artillary1Combo.setBounds(offsetx, offsety + 1
				* (Constants.HEIGHT + Constants.SPACEY), Constants.WIDTH2 * 2,
				Constants.HEIGHT);

		artillaryValueTopics[0][0].setBounds(offsetx + FIELDWIDTH * 0
				+ FIELDSOFFSET, artillary1Combo.getY() + Constants.HEIGHT
				+ Constants.SPACEY, FIELDWIDTH * 2 - Constants.SPACEX,
				Constants.HEIGHT);
		rangeWeaponTypeComboBox[0].setBounds(offsetx + FIELDWIDTH * 1
				+ FIELDSOFFSET, artillary1Combo.getY() + Constants.HEIGHT
				+ Constants.SPACEY, FIELDWIDTH * 4 - Constants.SPACEX*3,
				Constants.HEIGHT);
//		artillaryValueTopics[0][1].setBounds(offsetx + FIELDWIDTH * 4
//				+ FIELDSOFFSET, artillary1Combo.getY() + Constants.HEIGHT
//				+ Constants.SPACEY, FIELDWIDTH * 2 - Constants.SPACEX,
//				Constants.HEIGHT);
//		artillaryValueProperties[0].setBounds(offsetx + FIELDWIDTH * 6
//				+ FIELDSOFFSET, artillary1Combo.getY() + Constants.HEIGHT
//				+ Constants.SPACEY, FIELDWIDTH * 3 / 2 - Constants.SPACEX,
//				Constants.HEIGHT);
		artillaryValueTopics[0][1].setBounds(offsetx + FIELDWIDTH * 8
				+ FIELDWIDTH / 2 + FIELDSOFFSET, artillary1Combo.getY()
				+ Constants.HEIGHT + Constants.SPACEY, FIELDWIDTH * 2
				- Constants.SPACEX, Constants.HEIGHT);
		artillaryValueRaceModifier[0].setBounds(offsetx + FIELDWIDTH * 10
				+ FIELDWIDTH / 2 + FIELDSOFFSET, artillary1Combo.getY()
				+ Constants.HEIGHT + Constants.SPACEY, FIELDWIDTH
				- Constants.SPACEX, Constants.HEIGHT);
		artillaryValueTopics[0][2].setBounds(offsetx + FIELDWIDTH * 12
				+ FIELDWIDTH / 2 + FIELDSOFFSET, artillary1Combo.getY()
				+ Constants.HEIGHT + Constants.SPACEY, FIELDWIDTH * 2
				- Constants.SPACEX, Constants.HEIGHT);
		artillaryValue[0].setBounds(offsetx + FIELDWIDTH * 14 + FIELDWIDTH / 2
				+ FIELDSOFFSET, artillary1Combo.getY() + Constants.HEIGHT
				+ Constants.SPACEY, FIELDWIDTH - Constants.SPACEX,
				Constants.HEIGHT);

		for (int i = 1; i < 21; i++) {
			artillary1ComArr[i - 1].setBounds(offsetx + (i * FIELDWIDTH)
					+ FIELDSOFFSET, offsety + 3
					* (Constants.HEIGHT + Constants.SPACEY), FIELDWIDTH
					- offsetx / 2, Constants.HEIGHT);
		}
		artillary1Scrollbar.setBounds(offsetx, artillary1ComArr[2].getY()
				+ Constants.HEIGHT + Constants.SPACEY * 2, 21 * FIELDWIDTH
				+ SCROLLBARWIDTHPLUS, 15);

		artillary1ScrollbarTf.setBounds(
				offsetx + FIELDSOFFSET + 3 * FIELDWIDTH,
				artillary1Combo.getY(), FIELDWIDTH - Constants.SPACEX / 2,
				Constants.HEIGHT);
//		fightingSkillTopic[4].setBounds(
//				offsetx + FIELDSOFFSET + 5 * FIELDWIDTH,
//				artillary1Combo.getY(), FIELDWIDTH * 2 - Constants.SPACEX,
//				Constants.HEIGHT);
//		fightingSkillLabel[4].setBounds(
//				offsetx + FIELDSOFFSET + 7 * FIELDWIDTH,
//				artillary1Combo.getY(), FIELDWIDTH - Constants.SPACEX,
//				Constants.HEIGHT);

		buyArtillary1.setBounds(offsetx + FIELDSOFFSET + 14 * FIELDWIDTH,
				artillary1Combo.getY(),
				FIELDWIDTH * 2 - Constants.SPACEX, Constants.HEIGHT);

		atdeTopic[2].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 0,
				artillary1Scrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);
		atLabel[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 2,
				artillary1Scrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);
		diceTopic[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 4,
				artillary1Scrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH * 2, Constants.HEIGHT);
		diceLabel[0].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 6,
				artillary1Scrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);

		// ARTILLARY 2

		offsety = atdeTopic[2].getY() + Constants.HEIGHT * 4;

		artillary2Label.setBounds(offsetx, offsety, Constants.WIDTH2 * 2,
				Constants.HEIGHT);
		artillary2Combo.setBounds(offsetx, offsety + 1
				* (Constants.HEIGHT + Constants.SPACEY), Constants.WIDTH2 * 2,
				Constants.HEIGHT);

		artillaryValueTopics[1][0].setBounds(offsetx + FIELDWIDTH * 0
				+ FIELDSOFFSET, artillary2Combo.getY() + Constants.HEIGHT
				+ Constants.SPACEY, FIELDWIDTH * 2 - Constants.SPACEX,
				Constants.HEIGHT);
		rangeWeaponTypeComboBox[1].setBounds(offsetx + FIELDWIDTH * 1
				+ FIELDSOFFSET, artillary2Combo.getY() + Constants.HEIGHT
				+ Constants.SPACEY, FIELDWIDTH * 4 - Constants.SPACEX*3,
				Constants.HEIGHT);
//		artillaryValueTopics[1][1].setBounds(offsetx + FIELDWIDTH * 4
//				+ FIELDSOFFSET, artillary2Combo.getY() + Constants.HEIGHT
//				+ Constants.SPACEY, FIELDWIDTH * 2 - Constants.SPACEX,
//				Constants.HEIGHT);
//		artillaryValueProperties[1].setBounds(offsetx + FIELDWIDTH * 6
//				+ FIELDSOFFSET, artillary2Combo.getY() + Constants.HEIGHT
//				+ Constants.SPACEY, FIELDWIDTH * 3 / 2 - Constants.SPACEX,
//				Constants.HEIGHT);
		artillaryValueTopics[1][1].setBounds(offsetx + FIELDWIDTH * 8
				+ FIELDWIDTH / 2 + FIELDSOFFSET, artillary2Combo.getY()
				+ Constants.HEIGHT + Constants.SPACEY, FIELDWIDTH * 2
				- Constants.SPACEX, Constants.HEIGHT);
		artillaryValueRaceModifier[1].setBounds(offsetx + FIELDWIDTH * 10
				+ FIELDWIDTH / 2 + FIELDSOFFSET, artillary2Combo.getY()
				+ Constants.HEIGHT + Constants.SPACEY, FIELDWIDTH
				- Constants.SPACEX, Constants.HEIGHT);
		artillaryValueTopics[1][2].setBounds(offsetx + FIELDWIDTH * 12
				+ FIELDWIDTH / 2 + FIELDSOFFSET, artillary2Combo.getY()
				+ Constants.HEIGHT + Constants.SPACEY, FIELDWIDTH * 2
				- Constants.SPACEX, Constants.HEIGHT);
		artillaryValue[1].setBounds(offsetx + FIELDWIDTH * 14 + FIELDWIDTH / 2
				+ FIELDSOFFSET, artillary2Combo.getY() + Constants.HEIGHT
				+ Constants.SPACEY, FIELDWIDTH - Constants.SPACEX,
				Constants.HEIGHT);

		for (int i = 1; i < 21; i++) {
			artillary2ComArr[i - 1].setBounds(offsetx + (i * FIELDWIDTH)
					+ FIELDSOFFSET, offsety + 3
					* (Constants.HEIGHT + Constants.SPACEY), FIELDWIDTH
					- offsetx / 2, Constants.HEIGHT);
		}
		artillary2Scrollbar.setBounds(offsetx, artillary2ComArr[2].getY()
				+ Constants.HEIGHT + Constants.SPACEY * 2, 21 * FIELDWIDTH
				+ SCROLLBARWIDTHPLUS, 15);

		artillary2ScrollbarTf.setBounds(
				offsetx + FIELDSOFFSET + 3 * FIELDWIDTH,
				artillary2Combo.getY(), FIELDWIDTH - Constants.SPACEX / 2,
				Constants.HEIGHT);
//		fightingSkillTopic[5].setBounds(
//				offsetx + FIELDSOFFSET + 5 * FIELDWIDTH,
//				artillary2Combo.getY(), FIELDWIDTH * 2 - Constants.SPACEX,
//				Constants.HEIGHT);
//		fightingSkillLabel[5].setBounds(
//				offsetx + FIELDSOFFSET + 7 * FIELDWIDTH,
//				artillary2Combo.getY(), FIELDWIDTH - Constants.SPACEX,
//				Constants.HEIGHT);

		buyArtillary2.setBounds(offsetx + FIELDSOFFSET + 14 * FIELDWIDTH,
				artillary2Combo.getY(),
				FIELDWIDTH * 2 - Constants.SPACEX, Constants.HEIGHT);

		atdeTopic[3].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 0,
				artillary2Scrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);
		atLabel[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 2,
				artillary2Scrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);
		diceTopic[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 4,
				artillary2Scrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH * 2, Constants.HEIGHT);
		diceLabel[1].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 6,
				artillary2Scrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);

		// ARTILLARY 3

		offsety = atdeTopic[3].getY() + Constants.HEIGHT * 4;

		artillary3Label.setBounds(offsetx, offsety, Constants.WIDTH2 * 2,
				Constants.HEIGHT);
		artillary3Combo.setBounds(offsetx, offsety + 1
				* (Constants.HEIGHT + Constants.SPACEY), Constants.WIDTH2 * 2,
				Constants.HEIGHT);

		artillaryValueTopics[2][0].setBounds(offsetx + FIELDWIDTH * 0
				+ FIELDSOFFSET, artillary3Combo.getY() + Constants.HEIGHT
				+ Constants.SPACEY, FIELDWIDTH * 2 - Constants.SPACEX,
				Constants.HEIGHT);
		rangeWeaponTypeComboBox[2].setBounds(offsetx + FIELDWIDTH * 1
				+ FIELDSOFFSET, artillary3Combo.getY() + Constants.HEIGHT
				+ Constants.SPACEY, FIELDWIDTH * 4 - Constants.SPACEX*3,
				Constants.HEIGHT);
//		artillaryValueTopics[2][1].setBounds(offsetx + FIELDWIDTH * 4
//				+ FIELDSOFFSET, artillary3Combo.getY() + Constants.HEIGHT
//				+ Constants.SPACEY, FIELDWIDTH * 2 - Constants.SPACEX,
//				Constants.HEIGHT);
//		artillaryValueProperties[2].setBounds(offsetx + FIELDWIDTH * 6
//				+ FIELDSOFFSET, artillary3Combo.getY() + Constants.HEIGHT
//				+ Constants.SPACEY, FIELDWIDTH * 3 / 2 - Constants.SPACEX,
//				Constants.HEIGHT);
		artillaryValueTopics[2][1].setBounds(offsetx + FIELDWIDTH * 8
				+ FIELDWIDTH / 2 + FIELDSOFFSET, artillary3Combo.getY()
				+ Constants.HEIGHT + Constants.SPACEY, FIELDWIDTH * 2
				- Constants.SPACEX, Constants.HEIGHT);
		artillaryValueRaceModifier[2].setBounds(offsetx + FIELDWIDTH * 10
				+ FIELDWIDTH / 2 + FIELDSOFFSET, artillary3Combo.getY()
				+ Constants.HEIGHT + Constants.SPACEY, FIELDWIDTH
				- Constants.SPACEX, Constants.HEIGHT);
		artillaryValueTopics[2][2].setBounds(offsetx + FIELDWIDTH * 12
				+ FIELDWIDTH / 2 + FIELDSOFFSET, artillary3Combo.getY()
				+ Constants.HEIGHT + Constants.SPACEY, FIELDWIDTH * 2
				- Constants.SPACEX, Constants.HEIGHT);
		artillaryValue[2].setBounds(offsetx + FIELDWIDTH * 14 + FIELDWIDTH / 2
				+ FIELDSOFFSET, artillary3Combo.getY() + Constants.HEIGHT
				+ Constants.SPACEY, FIELDWIDTH - Constants.SPACEX,
				Constants.HEIGHT);

		for (int i = 1; i < 21; i++) {
			artillary3ComArr[i - 1].setBounds(offsetx + (i * FIELDWIDTH)
					+ FIELDSOFFSET, offsety + 3
					* (Constants.HEIGHT + Constants.SPACEY), FIELDWIDTH
					- offsetx / 2, Constants.HEIGHT);
		}
		artillary3Scrollbar.setBounds(offsetx, artillary3ComArr[2].getY()
				+ Constants.HEIGHT + Constants.SPACEY * 2, 21 * FIELDWIDTH
				+ SCROLLBARWIDTHPLUS, 15);

		artillary3ScrollbarTf.setBounds(
				offsetx + FIELDSOFFSET + 3 * FIELDWIDTH,
				artillary3Combo.getY(), FIELDWIDTH - Constants.SPACEX / 2,
				Constants.HEIGHT);
//		fightingSkillTopic[6].setBounds(
//				offsetx + FIELDSOFFSET + 5 * FIELDWIDTH,
//				artillary3Combo.getY(), FIELDWIDTH * 2 - Constants.SPACEX,
//				Constants.HEIGHT);
//		fightingSkillLabel[6].setBounds(
//				offsetx + FIELDSOFFSET + 7 * FIELDWIDTH,
//				artillary3Combo.getY(), FIELDWIDTH - Constants.SPACEX,
//				Constants.HEIGHT);

		buyArtillary3.setBounds(offsetx + FIELDSOFFSET + 14 * FIELDWIDTH,
				artillary3Combo.getY(),
				FIELDWIDTH * 2 - Constants.SPACEX, Constants.HEIGHT);

		atdeTopic[4].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 0,
				artillary3Scrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);
		atLabel[2].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 2,
				artillary3Scrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);
		diceTopic[2].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 4,
				artillary3Scrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH * 2, Constants.HEIGHT);
		diceLabel[2].setBounds(offsetx + FIELDSOFFSET + FIELDWIDTH * 6,
				artillary3Scrollbar.getY() + Constants.HEIGHT * 2
						+ Constants.SPACEY, FIELDWIDTH, Constants.HEIGHT);

		saveButton.setBounds(offsetx, atdeTopic[4].getY() + Constants.HEIGHT
				* 4, Constants.WIDTH2, Constants.HEIGHT);

		// add components
		addComponents();
		for (int i = 0; i < diceLabel.length; i++) {
			addNote(diceLabel[i], "", false);
			diceLabel[i].getNote().setVisible(false);
		}

		// enable ContextSensitiveMenu
		enableEvents(AWTEvent.MOUSE_EVENT_MASK);

		// add listeners

		addListeners(fbvLabel);
		addListeners(fsvLabel);
		addListeners(rwfsvLabel);

		addListeners(offDefKCombo);
		addListeners(armedMeleeCombo);
		addListeners(unarmedMeleeCombo);
		addListeners(rangeWeaponsCombo);
		addListeners(artillary1Combo);
		addListeners(artillary2Combo);
		addListeners(artillary3Combo);

		addListeners(armedMeleeSpecialRulesBt);
		addListeners(unarmedMeleeSpecialRulesBt);

		offDefKScrollbar.addAdjustmentListener(this);
		armedMeleeScrollbar.addAdjustmentListener(this);
		unarmedMeleeScrollbar.addAdjustmentListener(this);
		rangeWeaponsScrollbar.addAdjustmentListener(this);
		artillary1Scrollbar.addAdjustmentListener(this);
		artillary2Scrollbar.addAdjustmentListener(this);
		artillary3Scrollbar.addAdjustmentListener(this);

		addListeners(offDefKScrollbarTf);
		addListeners(armedMeleeScrollbarTf);
		addListeners(unarmedMeleeScrollbarTf);
		addListeners(rangeWeaponsScrollbarTf);
		addListeners(artillary1ScrollbarTf);
		addListeners(artillary2ScrollbarTf);
		addListeners(artillary3ScrollbarTf);

//		for (int i = 0; i < 7; i++)
//			addListeners(fightingSkillLabel[i]);

		for (int i = 0; i < atdePlusLabel.length; i++)
			addListeners(atdePlusLabel[i]);

		addListeners(atSpinner[0]);
		addListeners(atSpinner[1]);

		for (int i = 0; i < deLabel.length; i++)
			addListeners(deLabel[i]);

		addListeners(fittingArmedStylesBt);
		addListeners(fittingUnarmedStylesBt);

		addListeners(buyUnarmed);
		addListeners(buyArmed);
		addListeners(buyArtillary1);
		addListeners(buyArtillary2);
		addListeners(buyArtillary3);

		for (int i = 0; i < atLabel.length; i++)
			addListeners(atLabel[i]);

		for (int i = 0; i < diceLabel.length; i++)
			addListeners(diceLabel[i]);

		for (int i = 0; i < damPlusLabel.length; i++)
			addListeners(damPlusLabel[i]);

		for (int i = 0; i < damLabel.length; i++)
			addListeners(damLabel[i]);

		for (int i = 0; i < damResPlusLabel.length; i++)
			addListeners(damResPlusLabel[i]);

		for (int i = 0; i < damResLabel.length; i++)
			addListeners(damResLabel[i]);

		for (int i = 0; i < initLabel.length; i++)
			addListeners(initLabel[i]);

		for (int i = 0; i < initLabel.length; i++)
			addListeners(initLabel[i]);

		for (int i = 0; i < aimingLabel.length; i++)
			addListeners(aimingLabel[i]);

		addListeners(rangeWeaponTypeComboBox[0]);
		addListeners(rangeWeaponTypeComboBox[1]);
		addListeners(rangeWeaponTypeComboBox[2]);

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				addListeners(artillaryValueTopics[i][j]);

		addListeners(artillaryValue[0]);
		addListeners(artillaryValue[1]);
		addListeners(artillaryValue[2]);

//		addListeners(artillaryValueProperties[0]);
//		addListeners(artillaryValueProperties[1]);
//		addListeners(artillaryValueProperties[2]);

		addListeners(artillaryValueRaceModifier[0]);
		addListeners(artillaryValueRaceModifier[1]);
		addListeners(artillaryValueRaceModifier[2]);

		addListeners(armBasisLabel);

		addListeners(saveButton);

		addListeners(damBasis[0]);
		addListeners(damBasis[1]);

		// call itemStateChanged

		offDefKCombo.setSelectedIndex(1);
		offDefKCombo.setSelectedIndex(0);

		armedMeleeCombo.setSelectedIndex(1);
		armedMeleeCombo.setSelectedIndex(0);

		unarmedMeleeCombo.setSelectedIndex(1);
		unarmedMeleeCombo.setSelectedIndex(0);

		artillary1Combo.setSelectedIndex(1);
		artillary1Combo.setSelectedIndex(0);

		artillary2Combo.setSelectedIndex(1);
		artillary2Combo.setSelectedIndex(0);

		artillary3Combo.setSelectedIndex(1);
		artillary3Combo.setSelectedIndex(0);

		fillRangeWeapons();

		doNotActualize = false;
	}

	public Dimension getPreferredSize() {
		return new Dimension(aimingLabel[0].getX() + aimingLabel[0].getWidth()
				+ Constants.OFFSETX, saveButton.getY() + saveButton.getHeight()
				+ Constants.OFFSETY);
	}

	// abstract method implementations

	public void setSpecializationByText(String text, String note, Specialization spec) {

	}

	public void addComponents() {
		add(csMenu);

		add(fvLabel);
		add(offDefKLabel);
		add(armedMeleeLabel);
		add(unarmedMeleeLabel);
		add(rangeWeaponsLabel);
		add(artillary1Label);
		add(artillary2Label);
		add(artillary3Label);

		add(fbvTopic);
		add(fbvLabel);
		add(fsvTopic);
		add(fsvLabel);

		add(rwfsLabel);
		add(rwfsvLabel);
		add(rwfsvTopic);

		add(offDefKCombo);
		add(armedMeleeCombo);
		add(unarmedMeleeCombo);
		add(rangeWeaponsCombo);
		add(artillary1Combo);
		add(artillary2Combo);
		add(artillary3Combo);

		add(armedMeleeSpecialRulesBt);
		add(unarmedMeleeSpecialRulesBt);

		for (int i = 0; i < 20; i++) {
			add(offDefKComArr[i]);
			add(armedMeleeComArr[i]);
			add(unarmedMeleeComArr[i]);
			add(rangeWeaponsComArr[i]);
			add(artillary1ComArr[i]);
			add(artillary2ComArr[i]);
			add(artillary3ComArr[i]);
		}

		add(offDefKScrollbar);
		add(armedMeleeScrollbar);
		add(unarmedMeleeScrollbar);
		add(rangeWeaponsScrollbar);
		add(artillary1Scrollbar);
		add(artillary2Scrollbar);
		add(artillary3Scrollbar);

		add(offDefKScrollbarTf);
		add(armedMeleeScrollbarTf);
		add(unarmedMeleeScrollbarTf);
		add(rangeWeaponsScrollbarTf);
		add(artillary1ScrollbarTf);
		add(artillary2ScrollbarTf);
		add(artillary3ScrollbarTf);

//		for (int i = 0; i < 7; i++) {
//			add(fightingSkillLabel[i]);
//			add(fightingSkillTopic[i]);
//		}

		add(atdePlusTopic[0]);
		add(atdePlusTopic[1]);

		add(atdePlusLabel[0]);
		add(atdePlusLabel[1]);

		add(atdeTopic[0]);
		add(atdeTopic[1]);
		add(atdeTopic[2]);
		add(atdeTopic[3]);
		add(atdeTopic[4]);

		add(atSpinner[0]);
		add(atSpinner[1]);

		add(space);
		add(space2);

		add(deLabel[0]);
		add(deLabel[1]);

		add(fittingArmedStylesBt);
		add(fittingUnarmedStylesBt);

		add(buyArmed);
		add(buyUnarmed);
		add(buyArtillary1);
		add(buyArtillary2);
		add(buyArtillary3);

		add(atLabel[0]);
		add(atLabel[1]);
		add(atLabel[2]);

		add(diceTopic[0]);
		add(diceTopic[1]);
		add(diceTopic[2]);

		add(diceLabel[0]);
		add(diceLabel[1]);
		add(diceLabel[2]);

		add(damPlusTopic[0]);
		add(damPlusTopic[1]);

		add(damPlusLabel[0]);
		add(damPlusLabel[1]);

		add(damTopic[0]);
		add(damTopic[1]);

		add(damBasis[0]);
		add(damBasis[1]);

		add(damLabel[0]);
		add(damLabel[1]);

		add(damResPlusTopic[0]);
		add(damResPlusTopic[1]);

		add(damResPlusLabel[0]);
		add(damResPlusLabel[1]);

		add(damResTopic[0]);
		add(damResTopic[1]);

		add(damResLabel[0]);
		add(damResLabel[1]);

		add(initPlusTopic[0]);
		add(initPlusTopic[1]);

		add(initPlusLabel[0]);
		add(initPlusLabel[1]);

		add(initTopic[0]);
		add(initTopic[1]);

		add(initLabel[0]);
		add(initLabel[1]);

		add(aimingTopic[0]);
		add(aimingTopic[1]);

		add(aimingLabel[0]);
		add(aimingLabel[1]);

		add(rangeWeaponTypeComboBox[0]);
		add(rangeWeaponTypeComboBox[1]);
		add(rangeWeaponTypeComboBox[2]);

		add(artillaryValue[0]);
		add(artillaryValue[1]);
		add(artillaryValue[2]);

//		add(artillaryValueProperties[0]);
//		add(artillaryValueProperties[1]);
//		add(artillaryValueProperties[2]);

		add(artillaryValueRaceModifier[0]);
		add(artillaryValueRaceModifier[1]);
		add(artillaryValueRaceModifier[2]);

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				add(artillaryValueTopics[i][j]);

		add(armBasisLabel);

		add(saveButton);
	}

	public LinkedList getNotes() {
		return father.getRInf().getFightNotesLl();
	}

	public LinkedList getSpecIcons() {
		return null;
	}

	public JComponent getFocusComponentInClickedRow(JComponent comp) {
		if (comp == fvLabel || comp == fbvLabel || comp == fbvTopic
				|| comp == fsvLabel || comp == fsvTopic)
			return fsvLabel;

		if (comp == rwfsLabel || comp == rwfsvLabel || comp == rwfsvTopic)
			return rwfsvLabel;

		// offDefK

		if (comp == offDefKLabel || comp == offDefKCombo
				|| comp == offDefKScrollbarTf || comp == offDefKScrollbar)
			return offDefKScrollbarTf;

//		if (comp == fightingSkillLabel[0] || comp == fightingSkillTopic[0])
//			return fightingSkillTopic[0];

		for (int i = 0; i < offDefKComArr.length; i++)
			if (comp == offDefKComArr[i])
				return offDefKScrollbarTf;

		// armedMelee

		if (comp == armedMeleeLabel || comp == armedMeleeCombo
				|| comp == armedMeleeScrollbarTf || comp == armedMeleeScrollbar)
			return armedMeleeScrollbarTf;

//		if (comp == fightingSkillLabel[1] || comp == fightingSkillTopic[1])
//			return fightingSkillTopic[1];

		for (int i = 0; i < armedMeleeComArr.length; i++)
			if (comp == armedMeleeComArr[i])
				return armedMeleeScrollbarTf;

		// unarmedMelee

		if (comp == unarmedMeleeLabel || comp == unarmedMeleeCombo
				|| comp == unarmedMeleeScrollbarTf
				|| comp == unarmedMeleeScrollbar)
			return unarmedMeleeScrollbarTf;

//		if (comp == fightingSkillLabel[2] || comp == fightingSkillTopic[2])
//			return fightingSkillTopic[2];

		for (int i = 0; i < unarmedMeleeComArr.length; i++)
			if (comp == unarmedMeleeComArr[i])
				return unarmedMeleeScrollbarTf;

		// rangeWeapons

		if (comp == rangeWeaponsLabel || comp == rangeWeaponsCombo
				|| comp == rangeWeaponsScrollbarTf
				|| comp == rangeWeaponsScrollbar)
			return rangeWeaponsScrollbarTf;

//		if (comp == fightingSkillLabel[3] || comp == fightingSkillTopic[3])
//			return fightingSkillTopic[3];

		for (int i = 0; i < rangeWeaponsComArr.length; i++)
			if (comp == rangeWeaponsComArr[i])
				return rangeWeaponsScrollbarTf;

		// artillary1

		if (comp == artillary1Label || comp == artillary1Combo
				|| comp == artillary1ScrollbarTf || comp == artillary1Scrollbar)
			return artillary1ScrollbarTf;

//		if (comp == fightingSkillLabel[4] || comp == fightingSkillTopic[4])
//			return fightingSkillTopic[4];

		for (int i = 0; i < artillary1ComArr.length; i++)
			if (comp == artillary1ComArr[i])
				return artillary1ScrollbarTf;

		// artillary2

		if (comp == artillary2Label || comp == artillary2Combo
				|| comp == artillary2ScrollbarTf || comp == artillary2Scrollbar)
			return artillary2ScrollbarTf;

//		if (comp == fightingSkillLabel[5] || comp == fightingSkillTopic[5])
//			return fightingSkillTopic[5];

		for (int i = 0; i < artillary2ComArr.length; i++)
			if (comp == artillary2ComArr[i])
				return artillary2ScrollbarTf;

		// artillary3

		if (comp == artillary3Label || comp == artillary3Combo
				|| comp == artillary3ScrollbarTf || comp == artillary3Scrollbar)
			return artillary3ScrollbarTf;

//		if (comp == fightingSkillLabel[6] || comp == fightingSkillTopic[6])
//			return fightingSkillTopic[6];

		for (int i = 0; i < artillary3ComArr.length; i++)
			if (comp == artillary3ComArr[i])
				return artillary3ScrollbarTf;

		return null;
	}

	public MyLabel getTopicInClickedRow(JComponent component) {
		return null;
	}

	public String serializeYourself() {
		String ret = new String("fight;");

		ret +=  fsvLabel.getText() + ";" +
				rwfsvLabel.getText() + ";";
		
//		for (int i = 0; i < fightingSkillLabel.length; i++)
//			ret += fightingSkillLabel[i].getText() + ";";

		ret += offDefKCombo.getSelectedIndex() + ";" +
				offDefKScrollbarTf.getText() + ";";

		for (int i = 0; i < offDefKComArr.length; i++)
			if (offDefKComArr[i] instanceof MyComboBox)
				ret += ((MyComboBox) (offDefKComArr[i])).getSelectedIndex() + ";";		
		
		// armed melee

		ret	+=	armedMeleeCombo.getSelectedIndex() + ";" +

				armedMeleeScrollbarTf.getText() + ";";

		for (int i = 0; i < armedMeleeComArr.length; i++)
			if (armedMeleeComArr[i] instanceof MyComboBox)
				ret += ((MyComboBox) (armedMeleeComArr[i])).getSelectedIndex() + ";";

		ret += boughtArmed.size() + ";";
		
		for (Advantage a : boughtArmed)
			ret += a.getShortcut() + ";";
		
		// unarmed melee

		ret	+=	unarmedMeleeCombo.getSelectedIndex() + ";" +

				unarmedMeleeScrollbarTf.getText() + ";";

		for (int i = 0; i < unarmedMeleeComArr.length; i++)
			if (unarmedMeleeComArr[i] instanceof MyComboBox)
				ret += ((MyComboBox) (unarmedMeleeComArr[i])).getSelectedIndex() + ";";

		ret += boughtUnarmed.size() + ";";

		for (Advantage a : boughtUnarmed)
			ret += a.getShortcut() + ";";
		
		// rest

		ret	+= rangeWeaponsScrollbarTf.getText() + ";" +

			artillary1Combo.getSelectedIndex() + ";" +
			artillary1ScrollbarTf.getText() + ";" +
			
			artillary2Combo.getSelectedIndex() + ";" +
			artillary2ScrollbarTf.getText() + ";" +
			
			artillary3Combo.getSelectedIndex() + ";" +
			artillary3ScrollbarTf.getText() + ";" +
		
			at[0] + ";" + at[1] + ";";
		
		ret += boughtArtillary1.size() + ";";
		for (Advantage a : boughtArtillary1)
			ret += a.getShortcut() + ";";

		ret += boughtArtillary2.size() + ";";
		for (Advantage a : boughtArtillary2)
			ret += a.getShortcut() + ";";

		ret += boughtArtillary2.size() + ";";
		for (Advantage a : boughtArtillary2)
			ret += a.getShortcut() + ";";

		Noteable[] noteComps = getNoteableComponents();
		for (int i = 0; i < noteComps.length; i++)
		{
			try {
				if (!noteComps[i].getNote().getText().equals(""))
					ret += noteComps[i].getNote().getText() + ";" + noteComps[i].getIndex() + ";";
			} catch (NullPointerException npe)
			{ /* nothing */ }
		}

		return ret;
	}

	public void deserializeYourself(String s, int version) {
		doNotActualize = true;
		
		try {
		AdvantageFactory af = new AdvantageFactory();
		
		StringTokenizer t = new StringTokenizer(s, ";");
		
		if (!t.nextToken().equals("fight"))
		{
			father.getStatusLabel().setText("FightPanel falsch deserialisiert");
			return;
		}
		
		fsvLabel.setText(t.nextToken());
		rwfsvLabel.setText(t.nextToken());

//		for (int i = 0; i < fightingSkillLabel.length; i++)
//			fightingSkillLabel[i].setText(t.nextToken());

		offDefKCombo.setSelectedIndex(Integer.parseInt(t.nextToken()));
		offDefKScrollbarTf.setText(t.nextToken());

		for (int i = 0; i < offDefKComArr.length; i++)
			if (offDefKComArr[i] instanceof MyComboBox)
				((MyComboBox) (offDefKComArr[i])).setSelectedIndex(Integer.parseInt(t.nextToken()));
		
		armedMeleeCombo.setSelectedIndex(Integer.parseInt(t.nextToken()));
		armedMeleeScrollbarTf.setText(t.nextToken());
		
		for (int i = 0; i < armedMeleeComArr.length; i++)
			if (armedMeleeComArr[i] instanceof MyComboBox)
				((MyComboBox) (armedMeleeComArr[i])).setSelectedIndex(Integer.parseInt(t.nextToken()));
		
		boughtArmed = new LinkedList<Advantage>();
		int size = Integer.parseInt(t.nextToken());
		for (int i = 0; i < size; i++)
			boughtArmed.add(af.getMundaneAdvantageByShortcut(t.nextToken()));
	
		unarmedMeleeCombo.setSelectedIndex(Integer.parseInt(t.nextToken()));

		unarmedMeleeScrollbarTf.setText(t.nextToken());
			
		for (int i = 0; i < unarmedMeleeComArr.length; i++)
			if (unarmedMeleeComArr[i] instanceof MyComboBox)
				((MyComboBox) (unarmedMeleeComArr[i])).setSelectedIndex(Integer.parseInt(t.nextToken()));
		
		boughtUnarmed = new LinkedList<Advantage>();
		size = Integer.parseInt(t.nextToken());
		for (int i = 0; i < size; i++)
			boughtUnarmed.add(af.getMundaneAdvantageByShortcut(t.nextToken()));

		rangeWeaponsScrollbarTf.setText(t.nextToken());

		artillary1Combo.setSelectedIndex(Integer.parseInt(t.nextToken()));
		artillary1ScrollbarTf.setText(t.nextToken());
		
		artillary2Combo.setSelectedIndex(Integer.parseInt(t.nextToken()));
		artillary2ScrollbarTf.setText(t.nextToken());
		
		artillary3Combo.setSelectedIndex(Integer.parseInt(t.nextToken()));
		artillary3ScrollbarTf.setText(t.nextToken());
		
		at[0] = Integer.parseInt(t.nextToken());
		at[1] = Integer.parseInt(t.nextToken());

		boughtArtillary1 = new LinkedList<Advantage>();
		size = Integer.parseInt(t.nextToken());
		for (int i = 0; i < size; i++)
			boughtArtillary1.add(af.getMundaneAdvantageByShortcut(t.nextToken()));
	
		boughtArtillary2 = new LinkedList<Advantage>();
		size = Integer.parseInt(t.nextToken());
		for (int i = 0; i < size; i++)
			boughtArtillary2.add(af.getMundaneAdvantageByShortcut(t.nextToken()));
		
		boughtArtillary3 = new LinkedList<Advantage>();
		size = Integer.parseInt(t.nextToken());
		for (int i = 0; i < size; i++)
			boughtArtillary3.add(af.getMundaneAdvantageByShortcut(t.nextToken()));
		
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
		
		fsvLabel.setText("0");
		rwfsvLabel.setText("0");

//		for (int i = 0; i < fightingSkillLabel.length; i++)
//			fightingSkillLabel[i].setText("0");

		offDefKScrollbarTf.setText("0");
		armedMeleeScrollbarTf.setText("0");
		unarmedMeleeScrollbarTf.setText("0");
		rangeWeaponsScrollbarTf.setText("0");
		artillary1ScrollbarTf.setText("0");
		artillary2ScrollbarTf.setText("0");
		artillary3ScrollbarTf.setText("0");
		
		boughtArmed = new LinkedList<Advantage>();
		boughtUnarmed = new LinkedList<Advantage>();
		boughtArtillary1= new LinkedList<Advantage>();
		boughtArtillary2 = new LinkedList<Advantage>();
		boughtArtillary3 = new LinkedList<Advantage>();

		doNotActualize = false;
		offDefKScrollbar.setValue(0);
		armedMeleeScrollbar.setValue(0);
		unarmedMeleeScrollbar.setValue(0);
		artillary1Scrollbar.setValue(0);
		artillary2Scrollbar.setValue(0);
		artillary3Scrollbar.setValue(0);
		
		offDefKCombo.setSelectedIndex(0);
		armedMeleeCombo.setSelectedIndex(0);
		unarmedMeleeCombo.setSelectedIndex(0);
		artillary1Combo.setSelectedIndex(0);
		artillary2Combo.setSelectedIndex(0);
		artillary3Combo.setSelectedIndex(0);
		
		
		actualizeTextValues();
		setGPEPLabel();
	}

	public JComponent getDefaultFocusComponent() {
		return offDefKCombo;
	}

	// error handling

	public String checkForErrors() {
		try {
			if (Integer.parseInt(fsvLabel.getText()) > 5)
				return valueTooHigh(fsvLabel);
			if (Integer.parseInt(fsvLabel.getText()) < 0)
				return valueTooLow(fsvLabel);

			if (Integer.parseInt(rwfsvLabel.getText()) > 5)
				return valueTooHigh(rwfsvLabel);
			if (Integer.parseInt(rwfsvLabel.getText()) < 0)
				return valueTooLow(rwfsvLabel);

			Integer.parseInt(offDefKScrollbarTf.getText());
			Integer.parseInt(armedMeleeScrollbarTf.getText());
			Integer.parseInt(unarmedMeleeScrollbarTf.getText());
			Integer.parseInt(rangeWeaponsScrollbarTf.getText());
			Integer.parseInt(artillary1ScrollbarTf.getText());
			Integer.parseInt(artillary2ScrollbarTf.getText());
			Integer.parseInt(artillary3ScrollbarTf.getText());

//			for (int i = 0; i < fightingSkillLabel.length; i++) {
//				int val = Integer.parseInt(fightingSkillLabel[i].getText());
//				if (val > 10)
//					return valueTooHigh(fightingSkillLabel[i]);
//				else if (val < 0)
//					return valueTooLow(fightingSkillLabel[i]);
//				else
//					fightingSkillLabel[i].setText(val + "");
//			}

			int val = Integer.parseInt(fsvLabel.getText());
			if (val > 10)
				return valueTooHigh(fsvLabel);
			else if (val < 0)
				return valueTooLow(fsvLabel);
			else
				fsvLabel.setText(val + "");
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
			addNote(saveButton,
					"Mit Strg+S kannst du immer noch raus, keine Sorge!", false);
			saveButton.setVisible(false);
			angry = false;
			return "alles in Butter";
		}

		switch (Math.abs(rnd.nextInt() % 13)) {
		case 0:
			if (father.getHero().getRace().getName().equals("Eardons Haus"))
				return "Du müsstest ganz gut wissen, dass Eardinger miese Kämpfer sind. Und ein Wert von "
						+ comp.getText()
						+ " ist auch bei anderen Rassen nicht drin.";
			else
				return "Ganz wurscht, ob du "
						+ father.getHero().getRace()
						+ " oder sonstwas spielst, der Wert ist einfach nicht drin.";
		case 1:
			if (comp == fsvLabel || comp == rwfsvLabel)
				return "Das Maximum für den (Fern-)Kampffertigkeitswert ist 5.";
			else
				return "Keine Kampffertigkeit darf über 10 sein.";
		case 2:
			if (comp == fsvLabel || comp == rwfsvLabel)
				return "Ich glaub, dein (F)KFW ist um "
						+ (Integer.parseInt(comp.getText()) - 5) + " zu hoch.";
			else if (Integer.parseInt(comp.getText()) == 11)
				return "Wenn ich's nicht besser wüsste, würde ich sagen, du musst genau einen Punkt abspecken.";
			else
				return "Wenn ich's nicht besser wüsste, würde ich sagen, du musst "
						+ (Integer.parseInt(comp.getText()) - 10)
						+ " Punkte abspecken.";
		case 3:
			return "Der Wert ist eindeutig zu hoch.";
		case 4:
			Iterator<JTextField> it = armedMeleeAdvantagesLabelLl.iterator();
			while (it.hasNext())
				if (it.next().getText().equals("reit"))
					return "Du hast zwar den Reiterkampf drauf, nun aber runter von Deinem hohen Ross!";

			return "Wenn Du schon so hoch hinauf willst, solltest Du mal den Reiterkampf lernen. Vielleicht bringt Dich die Höhenluft zur Vernunft?";
		case 5:
			return "Jetz' is' aber genug.";
		case 6:
			return "Krieg dich wieder ein!";
		case 7:
			if (comp == fsvLabel || comp == rwfsvLabel)
				return "Du solltest Deine Ambitionen um genau "
						+ (int) (((Double.parseDouble(comp.getText()) - 5) * 20))
						+ "% zurückdrehen.";
			else
				return "Du solltest Deine Ambitionen um genau "
						+ (int) (((Double.parseDouble(comp.getText()) - 10) * 10))
						+ "% zurückdrehen.";
		case 8:
			return "Verstecke Dich vor meinem Zorn!";
		case 9:
			return "Machen wir einen Handel: Du darfst den Wert behalten und ich meistere das nächste Abenteuer!";
		case 10:
			return "Ich erzittere vor Ehrfurcht!";
		case 11:
			return "Fragen wir mal den Dominik, ob er da mal eine Regel erratieren kann...";
		case 12:
			angry = true;
			return "Deine hohen Werte bringen dir gar nichts, wenn du den Speichern-Button nicht findest! Und den versteck ich dir gleich...";

		default:
			return "Irgendwas geht gründlich schief...";
		}
	}

	public String valueTooLow(MyTextField comp) {
		Random rnd = new Random(System.currentTimeMillis());

		if (angry == true) // error # 13 makes it angry ^^
		{
			comp.setLocation((int) (Math.abs(rnd.nextInt()
					% (getPreferredSize().getWidth() - 100))),
					(int) (Math.abs(rnd.nextInt()
							% (getPreferredSize().getHeight() - 100))));
			angry = false;
			return "alles in Butter";
		}

		switch (Math.abs(rnd.nextInt() % 13)) {
		case 0:
			return "Wenn Hochmut vor dem Fall kommt, brauchst Du sicher nie Knieschützer!";
		case 1:
			return "Du bist verrückt. Jeder andere würde einen zu hohen Wert nehmen und du ...";
		case 2:
			return "Es ist weder maßlos noch arrogant, wenn Du einen Wert über 0 nimmst. Ehrlich!";
		case 3:
			return "Zu niedrig.";
		case 4:
			return "Du kriegst auch nicht mehr Punkte, wenn Du Minuswerte nimmst.";
		case 5:
			return "Denk mal gut darüber nach, was Du da gerade getippt hast!";
		case 6:
			return "Bescheidenheit ist ja eine Tugend...";
		case 7:
			return "Lieber arm dran als Arm ab, nicht wahr?";
		case 8:
			return "Ich hab ja auch was gegen Powergaming, aber man kann wirklich zu weit gehen.";
		case 9:
			return "Wenn Du schlechter Helden spielen willst, könntest Du ja weniger GP nehmen.";

		case 10:
			return "Hast Du schon mal versucht, den Scrollbar in den negativen Bereich zu ziehen? Vielleicht kriegst Du dann Nachteile statt Vorteilen!";
		case 11:
			return "Verschnüre Deine Bescheidenheit und verschenk sie an die Welt!";
		case 12:
			angry = true;
			return "Bring das in Ordnung oder ich du darfst dein Textfeld suchen!";

		default:
			return "Irgendwas geht gründlich schief...";
		}
	}

	public String numberFormatExceptionCaught(String errorMessage) {
		Random rnd = new Random(System.currentTimeMillis());

		if (angry == true) // error # 13 makes it angry ^^
		{
			fvLabel.setText("Gruäarghwrzdrä");
			offDefKLabel.setText("algs38x(92");
			armedMeleeLabel.setText("dk asdfI?ASÖ");
			unarmedMeleeLabel.setText("Hlasdkf\" drug2&%sd");
			rangeWeaponsLabel.setText("Gruy\\ysdfz1/§$LX9s");
			artillary1Label.setText("drurgaß28?kn sfö`sl287");
			artillary2Label.setText("drurgaß28?kn sfö`sl288");
			artillary3Label.setText("drurgaß28?kn sfö`sl289");

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
		adjustAdvantages(ARMEDMELEE, 0);
		adjustAdvantages(UNARMEDMELEE, 0);
		adjustAdvantages(ARTILLARY1, 0);
		adjustAdvantages(ARTILLARY2, 0);
		adjustAdvantages(ARTILLARY3, 0);

		adjustAdvantages(ARMEDMELEE, 1);
		adjustAdvantages(UNARMEDMELEE, 1);
		adjustAdvantages(ARTILLARY1, 1);
		adjustAdvantages(ARTILLARY2, 1);
		adjustAdvantages(ARTILLARY3, 1);
	}

	public void adjustAdvantages(int whichstyle, int number) {
		int value;
		Style style;
		AdvantageFamily[] advantages;
		JComponent[] compArr;
		LinkedList<Advantage> bought;

		try {
			switch (whichstyle) {
			case ARMEDMELEE:
				if (number == 0) {
					value = offDefKScrollbar.getValue();
					style = (Style) (offDefKCombo.getSelectedItem());
					advantages = style.getAdvantages();
					compArr = offDefKComArr;
					bought = null;
					break;
				} else {
					value = armedMeleeScrollbar.getValue();
					style = (Style) (armedMeleeCombo.getSelectedItem());
					advantages = style.getAdvantages();
					compArr = armedMeleeComArr;
					bought = boughtArmed;
					break;
				}

			case UNARMEDMELEE:
				if (number == 0) {
					value = offDefKScrollbar.getValue();
					style = (Style) (offDefKCombo.getSelectedItem());
					advantages = style.getAdvantages();
					compArr = offDefKComArr;
					bought = null;
				} else {
					value = unarmedMeleeScrollbar.getValue();
					style = (Style) (unarmedMeleeCombo.getSelectedItem());
					advantages = style.getAdvantages();
					compArr = unarmedMeleeComArr;
					bought = boughtUnarmed;
				}

				atdePlus[UNARMEDMELEE] += style.getAtdeMod();
				damPlus[UNARMEDMELEE] += style.getDamMod();
				initPlus[UNARMEDMELEE] += style.getInitMod();

				break;
			case ARTILLARY1:
				if (number == 0) {
					value = rangeWeaponsScrollbar.getValue();
					style = StyleFactory.getRangeWeaponsK().getFirst();
					advantages = style.getAdvantages();
					compArr = rangeWeaponsComArr;
					bought = null;
					break;
				} else {
					value = artillary1Scrollbar.getValue();
					style = (Style) (artillary1Combo.getSelectedItem());
					advantages = style.getAdvantages();
					compArr = artillary1ComArr;
					bought = boughtArtillary1;
					break;
				}

			case ARTILLARY2:
				if (number == 0) {
					value = rangeWeaponsScrollbar.getValue();
					style = StyleFactory.getRangeWeaponsK().getFirst();
					advantages = style.getAdvantages();
					compArr = rangeWeaponsComArr;
					bought = null;
					break;
				} else {
					value = artillary2Scrollbar.getValue();
					style = (Style) (artillary2Combo.getSelectedItem());
					advantages = style.getAdvantages();
					compArr = artillary2ComArr;
					bought = boughtArtillary2;
					break;
				}

			case ARTILLARY3:
				if (number == 0) {
					value = rangeWeaponsScrollbar.getValue();
					style = StyleFactory.getRangeWeaponsK().getFirst();
					advantages = style.getAdvantages();
					compArr = rangeWeaponsComArr;
					bought = null;
					break;
				} else {
					value = artillary3Scrollbar.getValue();
					style = (Style) (artillary3Combo.getSelectedItem());
					advantages = style.getAdvantages();
					compArr = artillary3ComArr;
					bought = boughtArtillary3;
					break;
				}
			default:
				value = 0;
				style = null;
				advantages = null;
				compArr = null;
				bought = null;
			}
		} catch (ClassCastException cce) {
			return;
		} catch (NullPointerException npe) {
			return;
		}

		if (number == 0) // initialization of course only once
		{
			try {
				atdePlus[whichstyle] = 0;
				initPlus[whichstyle] = 0;
				damPlus[whichstyle] = 0;
				damResPlus[whichstyle] = 0;
				aiming[whichstyle] = 0;
			} catch (ArrayIndexOutOfBoundsException aioobe) {
				// nothing - artillary doesn't have most of these values
			}

			Iterator<JLabel> it = null;

			switch (whichstyle) {
			case ARMEDMELEE:
				it = armedMeleeAdvantagesLabelLl.iterator();
				break;
			case UNARMEDMELEE:
				it = unarmedMeleeAdvantagesLabelLl.iterator();
				break;
			case ARTILLARY1:
				it = artillary1AdvantagesLabelLl.iterator();
				break;
			case ARTILLARY2:
				it = artillary2AdvantagesLabelLl.iterator();
				break;
			case ARTILLARY3:
				it = artillary3AdvantagesLabelLl.iterator();
				break;
			}

			while (it.hasNext())
				remove(it.next());

			switch (whichstyle) {
			case ARMEDMELEE:
				armedMeleeAdvantagesLabelLl = new LinkedList<JLabel>();
				break;
			case UNARMEDMELEE:
				unarmedMeleeAdvantagesLabelLl = new LinkedList<JLabel>();
				if (style.isOffensiveOrDaggers()) {
					JTextField label = new JTextField("EvD");
					label
							.setToolTipText("Einsatz von Dolchen - Einsatz von Dolchen erlaubt");
					label.setEditable(false);
					addListeners(label);
					unarmedMeleeAdvantagesLabelLl.add(label);
				}
				break;
			case ARTILLARY1:
				artillary1AdvantagesLabelLl = new LinkedList<JLabel>();
				break;
			case ARTILLARY2:
				artillary2AdvantagesLabelLl = new LinkedList<JLabel>();
				break;
			case ARTILLARY3:
				artillary3AdvantagesLabelLl = new LinkedList<JLabel>();
				break;
			}
		}

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

						if (compArr[i] instanceof MyTextField)
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

						int index = ((MyComboBox) (compArr[i]))
								.getSelectedIndex();

						if (group.getCommonpath().isEmpty() == false) {
							Iterator<Advantage> it = group.getCommonpath()
									.iterator();
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
						father.statusLabel
								.setText("Irgendwas rennt verdammt schief!!");
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
					JTextField label = new JTextField(iteratedAdvantage
							.getShortcut());
					label.setToolTipText(iteratedAdvantage.getLabel() + " - "
							+ iteratedAdvantage.getDesc());
					label.setEditable(false);
					addListeners(label);
	
					switch (whichstyle) {
					case ARMEDMELEE:
						armedMeleeAdvantagesLabelLl.add(label);
						break;
					case UNARMEDMELEE:
						unarmedMeleeAdvantagesLabelLl.add(label);
						break;
					case ARTILLARY1:
						artillary1AdvantagesLabelLl.add(label);
						break;
					case ARTILLARY2:
						artillary2AdvantagesLabelLl.add(label);
						break;
					case ARTILLARY3:
						artillary3AdvantagesLabelLl.add(label);
						break;
					}
				} else {
					String shortcut = iteratedAdvantage.getShortcut();
					if (shortcut.equals("Anab"))
						atdePlus[whichstyle]++;
					if (shortcut.equals("Init"))
						initPlus[whichstyle]++;
					if (shortcut.equals("Sch"))
						damPlus[whichstyle] += 2;
					if (shortcut.equals("SW"))
						damResPlus[whichstyle] += 2;
					if (shortcut.equals("Z(1)"))
						aiming[whichstyle]++;
					if (shortcut.equals("Z(2)"))
						aiming[whichstyle] += 2;
					if (shortcut.equals("Z(FW/5)"))
						aiming[whichstyle] += value / 5;
	
					if (shortcut.equals("SchSW"))
						if (offDefKCombo.getSelectedIndex() == 0)
							damResPlus[whichstyle] += 2;
						else
							damPlus[whichstyle] += 2;
	
					if (shortcut.equals("ÜA")) {
						if (whichstyle == UNARMEDMELEE) {
							Iterator<JTextField> it = unarmedMeleeAdvantagesLabelLl
									.iterator();
							boolean found = false;
							while (!found && it.hasNext()) {
								if (it.next().getText().equals("ÜA"))
									found = true;
							}
	
							if (found)
								atdePlus[whichstyle]++;
							else {
								JTextField label = new JTextField("ÜA");
								label.setToolTipText("Überwältigender Angriff");
								label.setEditable(false);
								addListeners(label);
								unarmedMeleeAdvantagesLabelLl.add(label);
							}
						} else {
							JTextField label = new JTextField("ÜA");
							label.setToolTipText("Überwältigender Angriff");
							label.setEditable(false);
							addListeners(label);
							armedMeleeAdvantagesLabelLl.add(label);
						}
					}
					if (shortcut.equals("ÜAE"))
						if (offDefKCombo.getSelectedIndex() == 0) {
							JTextField label = new JTextField("E");
							label.setToolTipText("Entwaffnen");
							label.setEditable(false);
							addListeners(label);
							unarmedMeleeAdvantagesLabelLl.add(label);
						} else {
							JTextField label = new JTextField("ÜA");
							label.setToolTipText("Überwältigender Angriff");
							label.setEditable(false);
							addListeners(label);
							unarmedMeleeAdvantagesLabelLl.add(label);
						}
	
					if (shortcut.equals("KTMP"))
						if (offDefKCombo.getSelectedIndex() == 0) {
							JTextField label = new JTextField("MP");
							label.setToolTipText("Meisterparade erleichtert");
							label.setEditable(false);
							addListeners(label);
							unarmedMeleeAdvantagesLabelLl.add(label);
						} else {
							JTextField label = new JTextField("KT");
							label.setToolTipText("Kritischer Treffer erleichtert");
							label.setEditable(false);
							addListeners(label);
							unarmedMeleeAdvantagesLabelLl.add(label);
						}
	
					if (shortcut.equals("reit, Z(1)")) {
						aiming[whichstyle]++;
						JTextField label = new JTextField("reit");
						label
								.setToolTipText("Reiterkampf - Erlaubt den Kampf zu Pferde.");
						label.setEditable(false);
						addListeners(label);
						armedMeleeAdvantagesLabelLl.add(label);
					}
				}
			}
		}
	}

	public void actualizeValues() {
		if (doNotActualize)
			return;

		doNotActualize = true;

		adjustAdvantages(); // fills also variables like atdePlus[]

		father.getStatusLabel().setText(checkForErrors());

		if (!father.getStatusLabel().getText().equals("alles in Butter"))
		{
			doNotActualize = false;
			return;
		}

		for (int i = ARMEDMELEE; i <= UNARMEDMELEE; i++) {
			atdePlusLabel[i].setText(atdePlus[i] + "");
			initPlusLabel[i].setText(initPlus[i] + "");
			damPlusLabel[i].setText(damPlus[i] + "");
			damResPlusLabel[i].setText(damResPlus[i] + "");
			aimingLabel[i].setText(aiming[i] + "");
		}

		// coordinate scrollbar and textfield
		int offDefK = Integer.parseInt(offDefKScrollbarTf.getText());
		if (offDefK < 0 || offDefK > 20)
			offDefKScrollbarTf.setText(offDefKScrollbar.getValue() + "");
		else
			offDefKScrollbar.setValue(offDefK);

		int armedmelee = Integer.parseInt(armedMeleeScrollbarTf.getText());
		if (armedmelee < 0 || armedmelee > 20)
			armedMeleeScrollbarTf.setText(armedMeleeScrollbar.getValue() + "");
		else
			armedMeleeScrollbar.setValue(armedmelee);
		if (armedmelee < 20 || armedmelee > 20
				&& armedMeleeScrollbar.getValue() < 20)
			{
				buyArmed.setVisible(false);
				Iterator<Advantage> it1 = boughtArmed.iterator();
				while (it1.hasNext())
					father.getSInf().increaseEP(costsOfAdvantage(it1.next()));	// clear of all bought advantages
				boughtArmed = new LinkedList<Advantage>();
			}
		else
			buyArmed.setVisible(true);

		int unarmedmelee = Integer.parseInt(unarmedMeleeScrollbarTf.getText());
		if (unarmedmelee < 0 || unarmedmelee > 20)
			unarmedMeleeScrollbarTf.setText(unarmedMeleeScrollbar.getValue()
					+ "");
		else
			unarmedMeleeScrollbar.setValue(unarmedmelee);
		if (unarmedmelee < 20 || unarmedmelee > 20
				&& unarmedMeleeScrollbar.getValue() < 20)
		{
			buyUnarmed.setVisible(false);
			Iterator<Advantage> it1 = boughtUnarmed.iterator();
			while (it1.hasNext())
				father.getSInf().increaseEP(costsOfAdvantage(it1.next()));	// clear of all bought advantages
			boughtUnarmed = new LinkedList<Advantage>();
		}
		else
			buyUnarmed.setVisible(true);

		int rangeWeapons = Integer.parseInt(rangeWeaponsScrollbarTf.getText());
		if (rangeWeapons < 0 || rangeWeapons > 20)
			rangeWeaponsScrollbarTf.setText(rangeWeaponsScrollbar.getValue()
					+ "");
		else
			rangeWeaponsScrollbar.setValue(rangeWeapons);

		int artillary1 = Integer.parseInt(artillary1ScrollbarTf.getText());
		if (artillary1 < 0 || artillary1 > 20)
			artillary1ScrollbarTf.setText(artillary1Scrollbar.getValue() + "");
		else
			artillary1Scrollbar.setValue(artillary1);
		if (artillary1 < 20 || artillary1 > 20
				&& artillary1Scrollbar.getValue() < 20)
		{
			buyArtillary1.setVisible(false);
			Iterator<Advantage> it1 = boughtArtillary1.iterator();
			while (it1.hasNext())
				father.getSInf().increaseEP(costsOfAdvantage(it1.next()));	// clear of all bought advantages
			boughtArtillary1 = new LinkedList<Advantage>();
		}
		else
			buyArtillary1.setVisible(true);

		int artillary2 = Integer.parseInt(artillary2ScrollbarTf.getText());
		if (artillary2 < 0 || artillary2 > 20)
			artillary2ScrollbarTf.setText(artillary2Scrollbar.getValue() + "");
		else
			artillary2Scrollbar.setValue(artillary2);
		if (artillary2 < 20 || artillary2 > 20
				&& artillary2Scrollbar.getValue() < 20)
		{
			buyArtillary2.setVisible(false);
			Iterator<Advantage> it1 = boughtArtillary2.iterator();
			while (it1.hasNext())
				father.getSInf().increaseEP(costsOfAdvantage(it1.next()));	// clear of all bought advantages
			boughtArtillary2 = new LinkedList<Advantage>();
		}
		else
			buyArtillary2.setVisible(true);

		int artillary3 = Integer.parseInt(artillary3ScrollbarTf.getText());
		if (artillary3 < 0 || artillary3 > 20)
			artillary3ScrollbarTf.setText(artillary3Scrollbar.getValue() + "");
		else
			artillary3Scrollbar.setValue(artillary3);
		if (artillary3 < 20 || artillary3 > 20
				&& artillary3Scrollbar.getValue() < 20)
		{
			buyArtillary3.setVisible(false);
			Iterator<Advantage> it1 = boughtArtillary3.iterator();
			while (it1.hasNext())
				father.getSInf().increaseEP(costsOfAdvantage(it1.next()));	// clear of all bought advantages
			boughtArtillary3 = new LinkedList<Advantage>();
		}
		else
			buyArtillary3.setVisible(true);

		int offsetx = Constants.OFFSETX + FIELDWIDTH / 2;

		Iterator<JTextField> iterator = armedMeleeAdvantagesLabelLl.iterator();
		int index = 0;
		while (iterator.hasNext())
			iterator.next().setBounds(
					offsetx + (index++ * FIELDWIDTH) + FIELDSOFFSET,
					armedMeleeScrollbar.getY() + Constants.HEIGHT * 5
							+ Constants.SPACEY * 4,
					FIELDWIDTH - Constants.SPACEX, Constants.HEIGHT);

		iterator = unarmedMeleeAdvantagesLabelLl.iterator();
		index = 0;
		while (iterator.hasNext())
			iterator.next().setBounds(
					offsetx + (index++ * FIELDWIDTH) + FIELDSOFFSET,
					unarmedMeleeScrollbar.getY() + Constants.HEIGHT * 5
							+ Constants.SPACEY * 4,
					FIELDWIDTH - Constants.SPACEX, Constants.HEIGHT);

		iterator = artillary1AdvantagesLabelLl.iterator();
		index = 0;
		while (iterator.hasNext())
			iterator.next().setBounds(
					offsetx + (index++ * FIELDWIDTH) + FIELDSOFFSET,
					artillary1Scrollbar.getY() + Constants.HEIGHT * 4
							+ Constants.SPACEY * 3,
					FIELDWIDTH - Constants.SPACEX, Constants.HEIGHT);

		iterator = artillary2AdvantagesLabelLl.iterator();
		index = 0;
		while (iterator.hasNext())
			iterator.next().setBounds(
					offsetx + (index++ * FIELDWIDTH) + FIELDSOFFSET,
					artillary2Scrollbar.getY() + Constants.HEIGHT * 4
							+ Constants.SPACEY * 3,
					FIELDWIDTH - Constants.SPACEX, Constants.HEIGHT);

		iterator = artillary3AdvantagesLabelLl.iterator();
		index = 0;
		while (iterator.hasNext())
			iterator.next().setBounds(
					offsetx + (index++ * FIELDWIDTH) + FIELDSOFFSET,
					artillary3Scrollbar.getY() + Constants.HEIGHT * 4
							+ Constants.SPACEY * 3,
					FIELDWIDTH - Constants.SPACEX, Constants.HEIGHT);

		iterator = armedMeleeAdvantagesLabelLl.iterator();
		while (iterator.hasNext())
			add(iterator.next());

		iterator = unarmedMeleeAdvantagesLabelLl.iterator();
		while (iterator.hasNext())
			add(iterator.next());

		iterator = artillary1AdvantagesLabelLl.iterator();
		while (iterator.hasNext())
			add(iterator.next());

		iterator = artillary2AdvantagesLabelLl.iterator();
		while (iterator.hasNext())
			add(iterator.next());

		iterator = artillary3AdvantagesLabelLl.iterator();
		while (iterator.hasNext())
			add(iterator.next());

		doNotActualize = true;

		for (int i = 0; i < 3; i++) {
			try {
				Iterator<Integer> it = null;
				switch (i) {
				case 0:
					it = ((Style) (artillary1Combo.getSelectedItem()))
							.getNotAvailableRangedWeapons().iterator();
					break;
				case 1:
					it = ((Style) (artillary2Combo.getSelectedItem()))
							.getNotAvailableRangedWeapons().iterator();
					break;
				case 2:
					it = ((Style) (artillary3Combo.getSelectedItem()))
							.getNotAvailableRangedWeapons().iterator();
					break;
				}

				while (it.hasNext())
					if (rangeWeaponTypeComboBox[i].getSelectedIndex() == (it
							.next().intValue())) {
						for (int j = 0; j < rangeWeaponTypeComboBox[i]
								.getItemCount(); j++) {
							Iterator<Integer> intIterator = null;
							switch (i) {
							case 0:
								intIterator = ((Style) (artillary1Combo
										.getSelectedItem()))
										.getNotAvailableRangedWeapons()
										.iterator();
								break;
							case 1:
								intIterator = ((Style) (artillary2Combo
										.getSelectedItem()))
										.getNotAvailableRangedWeapons()
										.iterator();
								break;
							case 2:
								intIterator = ((Style) (artillary3Combo
										.getSelectedItem()))
										.getNotAvailableRangedWeapons()
										.iterator();
								break;
							}

							boolean found = false;

							while (intIterator.hasNext())
								if (intIterator.next().intValue() == j)
									found = true;

							if (!found) {
								rangeWeaponTypeComboBox[i].setSelectedIndex(j);
								j = rangeWeaponTypeComboBox[i].getItemCount();
							}
						}
					}

			} catch (NullPointerException npe) {
				// nothing, it's okay, when there are no not available weapons
			}
		}

		doNotActualize = false;

		actualizeTextValues();

		repaint();
		setVisible(true); // display changes, e. g. advantages taken back
	}

	public void actualizeTextValues() {
		// FBV

		fbvLabel.setText((concat(father.getPropertiesPanel().calculateFBV())
				+ " ~ " + Math
				.round(father.getPropertiesPanel().calculateFBV())));

		// AtDe armed

		int armedMeleeAnab = (int) (Math.round(father.getPropertiesPanel()
				.calculateFBV())) // FBV
				+ Integer.parseInt(fsvLabel.getText()) // FSV
//				+ Integer.parseInt(fightingSkillLabel[0].getText()) / 2 // offensive/defensive
				+ (Integer.parseInt(offDefKScrollbarTf.getText()) + 1) / 2 // offK/defK
//				+ Integer.parseInt(fightingSkillLabel[1].getText()) // FSV
				+ Integer.parseInt(armedMeleeScrollbarTf.getText()) // FSV
				+ atdePlus[0];
		;

		atSpinner[0].setText(((armedMeleeAnab + 1) / 2 + at[0]) + "");
		deLabel[0].setText(((armedMeleeAnab) / 2 - at[0]) + "");

		// Init armed

		initLabel[0].setText((int) (father.getPropertiesPanel()
				.calculateInitBasis())
				- initPlus[0] - Integer.parseInt(fsvLabel.getText()) + "");

		// Damage armed

		switch (damBasis[0].getSelectedIndex()) {
		case 0:
			damLabel[0].setText((father.getPropertiesPanel().getProperty(3,
					"Nahkampf") + damPlus[0])
					+ "");
			break;
		case 1:
			damLabel[0]
					.setText((father.getPropertiesPanel().calculateStAg() + damPlus[0])
							+ "");
			break;
		}

		// Damage resistence armed

		damResLabel[0].setText((father.getPropertiesPanel().getProperty(5,
				"Nahkampf") + damResPlus[0])
				+ "");

		// AtDe unarmed

		int unarmedMeleeAnab = (int) (Math.round(father.getPropertiesPanel()
				.calculateFBV())) // FBV
				+ Integer.parseInt(fsvLabel.getText()) // FSV
//				+ Integer.parseInt(fightingSkillLabel[0].getText()) / 2 // offensive/defensive
				+ (Integer.parseInt(offDefKScrollbarTf.getText()) + 1) / 2 // offK/defK
//				+ Integer.parseInt(fightingSkillLabel[2].getText()) // FSV
				+ Integer.parseInt(unarmedMeleeScrollbarTf.getText()) // FSV
				+ atdePlus[1];
		;

		atSpinner[1].setText(((unarmedMeleeAnab + 1) / 2 + at[1]) + "");
		deLabel[1].setText(((unarmedMeleeAnab) / 2 - at[1]) + "");

		// Init unarmed

		initLabel[1].setText((int) (father.getPropertiesPanel()
				.calculateInitBasis())
				- initPlus[1] - Integer.parseInt(fsvLabel.getText()) + "");

		// Damage unarmed

		damLabel[1]
				.setText((father.getPropertiesPanel().calculateStAg() + damPlus[1])
						+ "");

		// Damage resistence unarmed

		damResLabel[1].setText((father.getPropertiesPanel().getProperty(5,
				"Nahkampf") + damResPlus[1])
				+ "");

		// AV race modifier

		for (int i = 0; i < 3; i++)   		
			switch (rangeWeaponTypeComboBox[i].getSelectedIndex()) {
			case 1:
				artillaryValueRaceModifier[i].setText("3");
				break;
			case 0:
			case 7:
				artillaryValueRaceModifier[i].setText((father.getHero()
						.getRaceModifiersByName(
								(String) (father.getPropertiesPanel()
										.getComboBox().getSelectedItem()))
						.getMods()[18] + ""));
				break;
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
				artillaryValueRaceModifier[i].setText((father.getHero()
						.getRaceModifiersByName(
								(String) (father.getPropertiesPanel()
										.getComboBox().getSelectedItem()))
						.getMods()[19] + ""));
				break;
			case 9:
				artillaryValueRaceModifier[i].setText((father.getHero()
						.getRaceModifiersByName(
								(String) (father.getPropertiesPanel()
										.getComboBox().getSelectedItem()))
						.getMods()[20] + ""));
				break;
			case 8:
				if (father.getHero().getRace().getName().equals("Gnome"))
					artillaryValueRaceModifier[i].setText("3");
				else
					artillaryValueRaceModifier[i].setText("0");
			}

		// AV

		doNotActualize = true;

//		for (int i = 0; i < 3; i++) {
//
//			switch (((RangeWeaponType) (rangeWeaponTypeComboBox[i]
//					.getSelectedItem())).getType()) {
//			case RangeWeaponType.KNIFE:
//				artillaryValueProperties[i].removeAllItems();
//				artillaryValueProperties[i].addItem((concat(father
//						.getPropertiesPanel().calculateAVKnives())
//						+ " ~ " + Math.round(father.getPropertiesPanel()
//						.calculateAVKnives())));
//				artillaryValueProperties[i].addItem("(Gs*3+In)/5");
//				break;
//
//			case RangeWeaponType.SLINGER:
//				artillaryValueProperties[i].removeAllItems();
//				artillaryValueProperties[i].addItem((concat(father
//						.getPropertiesPanel().calculateAVSlinger())
//						+ " ~ " + Math.round(father.getPropertiesPanel()
//						.calculateAVSlinger())));
//				artillaryValueProperties[i].addItem("(Gs*2+In*2)/5");
//				break;
//
//			case RangeWeaponType.BOWS:
//				artillaryValueProperties[i].removeAllItems();
//				artillaryValueProperties[i].addItem((concat(father
//						.getPropertiesPanel().calculateAVBows())
//						+ " ~ " + Math.round(father.getPropertiesPanel()
//						.calculateAVBows())));
//				artillaryValueProperties[i].addItem("(Gs*2+In+K)/5");
//				break;
//
//			case RangeWeaponType.MAGIC:
//				artillaryValueProperties[i].removeAllItems();
//				artillaryValueProperties[i].addItem((concat(father
//						.getPropertiesPanel().calculateAVMagic())
//						+ " ~ " + Math.round(father.getPropertiesPanel()
//						.calculateAVMagic())));
//				artillaryValueProperties[i].addItem("(Gs+In*2+Iz)/5");
//				break;
//			}
//		}
		for (int i = 0; i < 3; i++) {
			int abv = 0;
			switch (((RangeWeaponType) (rangeWeaponTypeComboBox[i]
					.getSelectedItem())).getType()) {
			case RangeWeaponType.BOWS:
						abv = (int) (Math.round(father.getPropertiesPanel()
								.calculateAVBows()));
						break;
			case RangeWeaponType.JAVELIN:
				abv = (int) (Math.round(father.getPropertiesPanel()
						.calculateAVJavelins()));
				break;
			case RangeWeaponType.KNIFE:
				abv = (int) (Math.round(father.getPropertiesPanel()
						.calculateAVKnives()));
				break;
			case RangeWeaponType.AXE:
				abv = (int) (Math.round(father.getPropertiesPanel()
						.calculateAVAxes()));
				break;
			case RangeWeaponType.ROPE:
				abv = (int) (Math.round(father.getPropertiesPanel()
						.calculateAVLassos()));
				break;
			case RangeWeaponType.SLINGER:
				abv = (int) (Math.round(father.getPropertiesPanel()
						.calculateAVSlinger()));
				break;
			case RangeWeaponType.ARTILLERY:
				abv = (int) (Math.round(father.getPropertiesPanel()
						.calculateAVArtillery()));
				break;
			case RangeWeaponType.PIPE:
				abv = (int) (Math.round(father.getPropertiesPanel()
						.calculateAVPipes()));
				break;
			case RangeWeaponType.MAGIC:
				abv = (int) (Math.round(father.getPropertiesPanel()
						.calculateAVMagic()));
				break;
			}

			abv += Integer.parseInt(artillaryValueRaceModifier[i].getText());
			abv += Integer.parseInt(rwfsvLabel.getText());

			artillaryValue[i].setText(abv + "");

			abv += (rangeWeaponsScrollbar.getValue() + 1) / 2;

			switch (i) {
			case 0:
				abv += artillary1Scrollbar.getValue();
				break;
			case 1:
				abv += artillary2Scrollbar.getValue();
				break;
			case 2:
				abv += artillary3Scrollbar.getValue();
				break;
			}

			atLabel[i].setText(abv + "");

			abv = (abv + 2) / 4;
			try {
				diceLabel[i].getNote().setVisible(false);
			} catch (NullPointerException npe) {
				// when fighting panel is still opened while hero is loaded
				// there is this note missing
				// but this can be ignored, cuz a new is generated anywhere
				// don't worry be happy
			}
			String message = null;

//			if ((father.getPropertiesPanel().getProperty(1, "FKW") + 1) / 2 > abv) {
//				abv = (father.getPropertiesPanel().getProperty(9, "FKW") + 1) / 2;
//				message = "Du kriegts Gs/2 oder In/2 Würfel";
//			}

//			if ((father.getPropertiesPanel().getProperty(9, "FKW") + 1) / 2 > abv) {
//				abv = (father.getPropertiesPanel().getProperty(9, "FKW") + 1) / 2;
//				message = "Du kriegts Gs/2 oder In/2 Würfel";
//			}

			try {
				if (message != null) {
					diceLabel[i].getNote().setText(message);
					diceLabel[i].getNote().setVisible(true);
				}
				diceLabel[i].setText(abv + "");
			} catch (NullPointerException npe) {
				// when fighting panel is still opened while hero is loaded
				// there is this note missing
				// but this can be ignored, cuz a new is generated anywhere
				// don't worry be happy
			}
		}

		doNotActualize = false;
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

		if (ae.getSource() == fittingUnarmedStylesBt) {
			FittingUnarmedStylesDialog dialog = new FittingUnarmedStylesDialog(
					father, ((Style) (armedMeleeCombo.getSelectedItem())),
					ARMEDMELEE);
			dialog.setLocation(fittingUnarmedStylesBt.getX() + father.getX()
					- 20, fittingUnarmedStylesBt.getY() + father.getY() - 20
					- father.getScrollPane().getVerticalScrollBar().getValue());
			dialog.setSize(dialog.getPreferredSize());
			dialog.setVisible(true);

			for (int i = 0; i < unarmedMeleeCombo.getItemCount(); i++)
				if (((Style) (unarmedMeleeCombo.getItemAt(i))).getName()
						.equals(dialog.getReturnString())) {
					unarmedMeleeCombo.setSelectedIndex(i);
					i = unarmedMeleeCombo.getItemCount();
				}

			return;
		}

		if (ae.getSource() == fittingArmedStylesBt) {
			FittingUnarmedStylesDialog dialog = new FittingUnarmedStylesDialog(
					father, ((Style) (unarmedMeleeCombo.getSelectedItem())),
					UNARMEDMELEE);
			dialog.setLocation(fittingUnarmedStylesBt.getX() + father.getX()
					- 20, fittingUnarmedStylesBt.getY() + father.getY() - 20
					- father.getScrollPane().getVerticalScrollBar().getValue());
			dialog.setSize(dialog.getPreferredSize());
			dialog.setVisible(true);

			for (int i = 0; i < armedMeleeCombo.getItemCount(); i++)
				if (((Style) (armedMeleeCombo.getItemAt(i))).getName().equals(
						dialog.getReturnString())) {
					armedMeleeCombo.setSelectedIndex(i);
					i = armedMeleeCombo.getItemCount();
				}

			return;
		}

		if (ae.getSource() == armedMeleeSpecialRulesBt) {
			FightingStyleSpecialRulesDialog fssrd = new FightingStyleSpecialRulesDialog(
					father, ((Style) (armedMeleeCombo.getSelectedItem()))
							.getSpecialRules());
			fssrd.setLocation(300, 300);
			fssrd.setVisible(true);
			return;
		}

		if (ae.getSource() == unarmedMeleeSpecialRulesBt) {
			FightingStyleSpecialRulesDialog fssrd = new FightingStyleSpecialRulesDialog(
					father, ((Style) (unarmedMeleeCombo.getSelectedItem()))
							.getSpecialRules());
			fssrd.setLocation(300, 300);
			fssrd.setVisible(true);
			return;
		}

		if (ae.getSource() == buyArmed) {
			Iterator<Advantage> it1 = boughtArmed.iterator();
			while (it1.hasNext())
				father.getSInf().increaseEP(costsOfAdvantage(it1.next()));	// if they are left, ep costs are added afterwards
			BuyNewAdvantageDialog bnad = new BuyNewAdvantageDialog(father, offDefKCombo.getSelectedIndex() == 0?BuyNewAdvantageDialog.OFFARMEDMELEE:BuyNewAdvantageDialog.DEFARMEDMELEE, boughtArmed);
			bnad.setLocation(300, 140);
			bnad.setVisible(true);
			Iterator<Advantage> it2 = bnad.getReturnList().iterator();
			boughtArmed = new LinkedList<Advantage>(); // has to be emptied, otherwise left-over advantages would be doubled
			while (it2.hasNext())
			{
				Advantage adv = it2.next();
				
				father.getSInf().increaseEP(-1*costsOfAdvantage(adv));
				boughtArmed.add(adv);
			}
		}	
		
		if (ae.getSource() == buyUnarmed) {
			Iterator<Advantage> it1 = boughtUnarmed.iterator();
			while (it1.hasNext())
				father.getSInf().increaseEP(costsOfAdvantage(it1.next()));	// if they are left, ep costs are added afterwards
			BuyNewAdvantageDialog bnad = new BuyNewAdvantageDialog(father, BuyNewAdvantageDialog.UNARMEDMELEE, boughtUnarmed);
			bnad.setLocation(300, 140);
			bnad.setVisible(true);
			Iterator<Advantage> it2 = bnad.getReturnList().iterator();
			boughtUnarmed = new LinkedList<Advantage>(); // has to be emptied, otherwise left-over advantages would be doubled
			while (it2.hasNext())
			{
				Advantage adv = it2.next();
				
				father.getSInf().increaseEP(-1*costsOfAdvantage(adv));
				boughtUnarmed.add(adv);
			}
		}	
		
		if (ae.getSource() == buyArtillary1) {
			Iterator<Advantage> it1 = boughtArtillary1.iterator();
			while (it1.hasNext())
				father.getSInf().increaseEP(costsOfAdvantage(it1.next()));	// if they are left, ep costs are added afterwards
			BuyNewAdvantageDialog bnad = new BuyNewAdvantageDialog(father, BuyNewAdvantageDialog.ARTILLARY, boughtArtillary1);
			bnad.setLocation(300, 140);
			bnad.setVisible(true);
			Iterator<Advantage> it2 = bnad.getReturnList().iterator();
			boughtArtillary1 = new LinkedList<Advantage>(); // has to be emptied, otherwise left-over advantages would be doubled
			while (it2.hasNext())
			{
				Advantage adv = it2.next();
				
				father.getSInf().increaseEP(-1*costsOfAdvantage(adv));
				boughtArtillary1.add(adv);
			}
		}
		
		if (ae.getSource() == buyArtillary2) {
			Iterator<Advantage> it1 = boughtArtillary2.iterator();
			while (it1.hasNext())
				father.getSInf().increaseEP(costsOfAdvantage(it1.next()));	// if they are left, ep costs are added afterwards
			BuyNewAdvantageDialog bnad = new BuyNewAdvantageDialog(father, BuyNewAdvantageDialog.ARTILLARY, boughtArtillary2);
			bnad.setLocation(300, 140);
			bnad.setVisible(true);
			Iterator<Advantage> it2 = bnad.getReturnList().iterator();
			boughtArtillary2 = new LinkedList<Advantage>(); // has to be emptied, otherwise left-over advantages would be doubled
			while (it2.hasNext())
			{
				Advantage adv = it2.next();
				
				father.getSInf().increaseEP(-1*costsOfAdvantage(adv));
				boughtArtillary2.add(adv);
			}
		}
		
		if (ae.getSource() == buyArtillary3) {
			Iterator<Advantage> it1 = boughtArtillary3.iterator();
			while (it1.hasNext())
				father.getSInf().increaseEP(costsOfAdvantage(it1.next()));	// if they are left, ep costs are added afterwards
			BuyNewAdvantageDialog bnad = new BuyNewAdvantageDialog(father, BuyNewAdvantageDialog.ARTILLARY, boughtArtillary3);
			bnad.setLocation(300, 140);
			bnad.setVisible(true);
			Iterator<Advantage> it2 = bnad.getReturnList().iterator();
			boughtArtillary3 = new LinkedList<Advantage>(); // has to be emptied, otherwise left-over advantages would be doubled
			while (it2.hasNext())
			{
				Advantage adv = it2.next();
				
				father.getSInf().increaseEP(-1*costsOfAdvantage(adv));
				boughtArtillary3.add(adv);
			}
		}
		
		for (int i = 0; i <= UNARMEDMELEE; i++) {
			if (ae.getSource() == atSpinner[i]) {
				at[i]++;

				if (Integer.parseInt(atSpinner[i].getText())
						- Integer.parseInt(deLabel[i].getText()) > 3)
					at[i]--;
			}

			if (ae.getSource() == deLabel[i]) {
				at[i]--;
				if (Integer.parseInt(deLabel[i].getText())
						- Integer.parseInt(atSpinner[i].getText()) > 3)
					at[i]++;
			}
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
		int offsety = fsvLabel.getY() + Constants.HEIGHT * 2;

		Object style = null;
		Object armedStyle = null;
		AdvantageFamily[] advantages = null;

		if (ie.getSource() == offDefKCombo) {
			style = offDefKCombo.getSelectedItem();
			armedStyle = armedMeleeCombo.getSelectedItem();

			if (style instanceof Style && armedStyle instanceof Style) {
				if (((Style) (armedStyle)).isOffensiveOrDaggers()
						&& ((Style) (style)).getName().equals("DefensivK")) {
					offDefKCombo.setSelectedIndex(0);
					return;
				}

				if (((Style) (armedStyle)).isOffensiveOrDaggers() == false
						&& ((Style) (style)).getName().equals("OffensivK")) {
					offDefKCombo.setSelectedIndex(1);
					return;
				}

				advantages = ((Style) (style)).getAdvantages();

				for (int i = 0; i < 20; i++) {
					int ind = ((Noteable) (offDefKComArr[i])).getIndex();
					try {
						removeCompletely(offDefKComArr[i]);
						offDefKComArr[i] = advantages[i].generateComponent(
								this, ind);
						add(offDefKComArr[i]);
						addListeners(offDefKComArr[i]);
						if (offDefKComArr[i] instanceof MyTextField)
							((MyTextField) (offDefKComArr[i]))
									.setEditable(false);
					} catch (NullPointerException npe) {
						MyTextField tf = new MyTextField(" ", ind);
						tf.setEditable(false);
						offDefKComArr[i] = tf;
						add(offDefKComArr[i]);
						addListeners(offDefKComArr[i]);
					}
				}
				for (int i = 1; i < 21; i++) {
					offDefKComArr[i - 1].setBounds(Constants.OFFSETX
							+ (i * FIELDWIDTH) + FIELDSOFFSET, offsety + 2
							* (Constants.HEIGHT + Constants.SPACEY), FIELDWIDTH
							- Constants.SPACEX, Constants.HEIGHT);
					if (((Noteable) (offDefKComArr[i - 1])).getNote() != null)
						((Noteable) (offDefKComArr[i - 1])).getNote()
								.actualizeBounds();
				}
			}
		}

		if (ie.getSource() == armedMeleeCombo) {
			armedStyle = armedMeleeCombo.getSelectedItem();
			style = offDefKCombo.getSelectedItem();

			if (style instanceof Style && armedStyle instanceof Style) {
				if (((Style) (armedStyle)).isOffensiveOrDaggers()
						&& ((Style) (style)).getName().equals("DefensivK")) {
					offDefKCombo.setSelectedIndex(0);
				} else if (((Style) (armedStyle)).isOffensiveOrDaggers() == false
						&& ((Style) (style)).getName().equals("OffensivK")) {
					offDefKCombo.setSelectedIndex(1);
				}

				advantages = ((Style) (armedStyle)).getAdvantages();

				for (int i = 0; i < 20; i++) {
					int ind = ((Noteable) (armedMeleeComArr[i])).getIndex();

					try {
						removeCompletely(armedMeleeComArr[i]);
						armedMeleeComArr[i] = advantages[i].generateComponent(
								this, ind);
						add(armedMeleeComArr[i]);
						addListeners(armedMeleeComArr[i]);
						if (armedMeleeComArr[i] instanceof MyComboBox)
							((MyComboBox) (armedMeleeComArr[i]))
									.addItemListener(this);
						else
							((MyTextField) (armedMeleeComArr[i]))
									.setEditable(false);
					} catch (NullPointerException npe) {
						MyTextField tf = new MyTextField(" ", ind);
						tf.setEditable(false);
						armedMeleeComArr[i] = tf;
						add(armedMeleeComArr[i]);
						addListeners(armedMeleeComArr[i]);
					}
				}
				offsety = offDefKScrollbar.getY() + Constants.HEIGHT * 3;

				for (int i = 1; i < 21; i++) {
					armedMeleeComArr[i - 1].setBounds(offsetx
							+ (i * FIELDWIDTH) + FIELDSOFFSET, offsety + 2
							* (Constants.HEIGHT + Constants.SPACEY), FIELDWIDTH
							- Constants.SPACEX, Constants.HEIGHT);
					if (((Noteable) (armedMeleeComArr[i - 1])).getNote() != null)
						((Noteable) (armedMeleeComArr[i - 1])).getNote()
								.actualizeBounds();
				}
			}
		}

		if (ie.getSource() == unarmedMeleeCombo) {
			style = unarmedMeleeCombo.getSelectedItem();
			if (style instanceof Style) {
				advantages = ((Style) (style)).getAdvantages();

				for (int i = 0; i < 20; i++) {
					int ind = ((Noteable) (unarmedMeleeComArr[i])).getIndex();

					try {
						removeCompletely(unarmedMeleeComArr[i]);
						unarmedMeleeComArr[i] = advantages[i]
								.generateComponent(this, ind);
						add(unarmedMeleeComArr[i]);
						addListeners(unarmedMeleeComArr[i]);
						if (unarmedMeleeComArr[i] instanceof MyComboBox)
							((MyComboBox) (unarmedMeleeComArr[i]))
									.addItemListener(this);
						else
							((MyTextField) (unarmedMeleeComArr[i]))
									.setEditable(false);

					} catch (NullPointerException npe) {
						MyTextField tf = new MyTextField(" ", ind);
						tf.setEditable(false);
						unarmedMeleeComArr[i] = tf;
						add(unarmedMeleeComArr[i]);
						addListeners(unarmedMeleeComArr[i]);
					}
				}
				offsety = aimingLabel[0].getY() + Constants.HEIGHT * 3;

				for (int i = 1; i < 21; i++) {
					unarmedMeleeComArr[i - 1].setBounds(offsetx
							+ (i * FIELDWIDTH) + FIELDSOFFSET, offsety + 3
							* (Constants.HEIGHT + Constants.SPACEY), FIELDWIDTH
							- Constants.SPACEX, Constants.HEIGHT);
					if (((Noteable) (unarmedMeleeComArr[i - 1])).getNote() != null)
						((Noteable) (unarmedMeleeComArr[i - 1])).getNote()
								.actualizeBounds();
				}
			}
		}

		if (ie.getSource() == artillary1Combo) {
			style = artillary1Combo.getSelectedItem();
			if (style instanceof Style) {
				advantages = ((Style) (style)).getAdvantages();

				for (int i = 0; i < 20; i++) {
					int ind = ((Noteable) (artillary1ComArr[i])).getIndex();
					try {
						removeCompletely(artillary1ComArr[i]);
						artillary1ComArr[i] = advantages[i].generateComponent(
								this, ind);
						add(artillary1ComArr[i]);
						addListeners(artillary1ComArr[i]);
						if (artillary1ComArr[i] instanceof MyTextField)
							((MyTextField) (artillary1ComArr[i]))
									.setEditable(false);

					} catch (NullPointerException npe) {
						MyTextField tf = new MyTextField(" ", ind);
						tf.setEditable(false);
						artillary1ComArr[i] = tf;
						add(artillary1ComArr[i]);
						addListeners(artillary1ComArr[i]);
					}
				}
				offsety = rangeWeaponsScrollbar.getY() + Constants.HEIGHT * 3;

				for (int i = 1; i < 21; i++) {
					artillary1ComArr[i - 1].setBounds(offsetx
							+ (i * FIELDWIDTH) + FIELDSOFFSET, offsety + 3
							* (Constants.HEIGHT + Constants.SPACEY), FIELDWIDTH
							- Constants.SPACEX, Constants.HEIGHT);
					if (((Noteable) (artillary1ComArr[i - 1])).getNote() != null)
						((Noteable) (artillary1ComArr[i - 1])).getNote()
								.actualizeBounds();
				}
			}
		}

		if (ie.getSource() == artillary2Combo) {
			style = artillary2Combo.getSelectedItem();
			if (style instanceof Style) {
				advantages = ((Style) (style)).getAdvantages();

				for (int i = 0; i < 20; i++) {
					int ind = ((Noteable) (artillary2ComArr[i])).getIndex();
					try {
						removeCompletely(artillary2ComArr[i]);
						artillary2ComArr[i] = advantages[i].generateComponent(
								this, ind);
						add(artillary2ComArr[i]);
						addListeners(artillary2ComArr[i]);
						if (artillary2ComArr[i] instanceof MyTextField)
							((MyTextField) (artillary2ComArr[i]))
									.setEditable(false);

					} catch (NullPointerException npe) {
						MyTextField tf = new MyTextField(" ", ind);
						tf.setEditable(false);
						artillary2ComArr[i] = tf;
						add(artillary2ComArr[i]);
						addListeners(artillary1ComArr[i]);
					}
				}
				offsety = atdeTopic[2].getY() + Constants.HEIGHT * 4;

				for (int i = 1; i < 21; i++) {
					artillary2ComArr[i - 1].setBounds(offsetx
							+ (i * FIELDWIDTH) + FIELDSOFFSET, offsety + 3
							* (Constants.HEIGHT + Constants.SPACEY), FIELDWIDTH
							- Constants.SPACEX, Constants.HEIGHT);
					if (((Noteable) (artillary2ComArr[i - 1])).getNote() != null)
						((Noteable) (artillary2ComArr[i - 1])).getNote()
								.actualizeBounds();
				}
			}
		}

		if (ie.getSource() == artillary3Combo) {
			style = artillary3Combo.getSelectedItem();
			if (style instanceof Style) {
				advantages = ((Style) (style)).getAdvantages();

				for (int i = 0; i < 20; i++) {
					int ind = ((Noteable) (artillary3ComArr[i])).getIndex();
					try {
						removeCompletely(artillary3ComArr[i]);
						artillary3ComArr[i] = advantages[i].generateComponent(
								this, ind);
						add(artillary3ComArr[i]);
						addListeners(artillary3ComArr[i]);
						if (artillary3ComArr[i] instanceof MyTextField)
							((MyTextField) (artillary3ComArr[i]))
									.setEditable(false);
					} catch (NullPointerException npe) {
						MyTextField tf = new MyTextField(" ", ind);
						tf.setEditable(false);
						artillary3ComArr[i] = tf;
						add(artillary3ComArr[i]);
						addListeners(artillary1ComArr[i]);
					}
				}
				offsety = atdeTopic[3].getY() + Constants.HEIGHT * 4;

				for (int i = 1; i < 21; i++) {
					artillary3ComArr[i - 1].setBounds(offsetx
							+ (i * FIELDWIDTH) + FIELDSOFFSET, offsety + 3
							* (Constants.HEIGHT + Constants.SPACEY), FIELDWIDTH
							- Constants.SPACEX, Constants.HEIGHT);
					if (((Noteable) (artillary3ComArr[i - 1])).getNote() != null)
						((Noteable) (artillary3ComArr[i - 1])).getNote()
								.actualizeBounds();

				}
			}
		}

		if (doNotActualize)
			return;

		JComponent[] comArr = null;
		Style advStyle = null;
		int i = 0;

		for (; i < 20; i++) {
			if (offDefKComArr[i] == ie.getSource()) {
				comArr = offDefKComArr;
				advStyle = (Style) offDefKCombo.getSelectedItem();
				break;
			}
			if (armedMeleeComArr[i] == ie.getSource()) {
				comArr = armedMeleeComArr;
				advStyle = (Style) armedMeleeCombo.getSelectedItem();
				break;
			}
			if (unarmedMeleeComArr[i] == ie.getSource()) {
				comArr = unarmedMeleeComArr;
				advStyle = (Style) unarmedMeleeCombo.getSelectedItem();
				break;
			}
		}

		if (comArr != null) // Combobox in a Style
		{
			doNotActualize = true;
			JComboBox box = (JComboBox) (ie.getSource());
			AdvantageFamily[] fam = advStyle.getAdvantages();
			if (!((AdvantageGroup) (fam[i])).getPath1().isEmpty()) {
				int pos = box.getSelectedIndex();
				for (int j = 0; j < 20; j++)
					if (fam[j] instanceof AdvantageGroup
							&& !((AdvantageGroup) (fam[j])).getPath1()
									.isEmpty())
						((JComboBox) (comArr[j])).setSelectedIndex(pos);
			}
			if (!((AdvantageGroup) (fam[i])).getPath3().isEmpty()) {
				int pos = box.getSelectedIndex();
				for (int j = 0; j < 20; j++)
					if (fam[j] instanceof AdvantageGroup
							&& !((AdvantageGroup) (fam[j])).getPath3()
									.isEmpty())
						((JComboBox) (comArr[j])).setSelectedIndex(pos);
			}
			doNotActualize = false;
		}
	}

	public void adjustmentValueChanged(AdjustmentEvent ae) {
		if (doNotActualize)
			return;

		offDefKScrollbarTf.setText(offDefKScrollbar.getValue() + "");
		armedMeleeScrollbarTf.setText(armedMeleeScrollbar.getValue() + "");
		unarmedMeleeScrollbarTf.setText(unarmedMeleeScrollbar.getValue() + "");
		rangeWeaponsScrollbarTf.setText(rangeWeaponsScrollbar.getValue() + "");
		artillary1ScrollbarTf.setText(artillary1Scrollbar.getValue() + "");
		artillary2ScrollbarTf.setText(artillary2Scrollbar.getValue() + "");
		artillary3ScrollbarTf.setText(artillary3Scrollbar.getValue() + "");

		actualizeValues();
		checkGPEP();
	}

	public void checkGPEP() {
		if (!father.getStatusLabel().getText().equals("alles in Butter"))
			return;

		LinkedList<Integer> oldskills = father.getHero().getFightingSkillsLl();
		Iterator<Integer> it = oldskills.iterator();
		LinkedList<Integer> newskills = new LinkedList<Integer>();

		checkTF(fsvLabel, it.next().intValue(), newskills, COSTS_ELITARY);
		checkTF(rwfsvLabel, it.next().intValue(), newskills, COSTS_ELITARY);

//		for (int i = 0; i < fightingSkillLabel.length; i++)
//			checkTF(fightingSkillLabel[i], it.next().intValue(), newskills, 7);

		checkTF(offDefKScrollbarTf, it.next().intValue(), newskills, COSTS_COMPLEX);
		checkTF(armedMeleeScrollbarTf, it.next().intValue(), newskills, COSTS_COMPLEX);
		checkTF(unarmedMeleeScrollbarTf, it.next().intValue(), newskills, COSTS_COMPLEX);
		checkTF(rangeWeaponsScrollbarTf, it.next().intValue(), newskills, COSTS_COMPLEX);
		checkTF(artillary1ScrollbarTf, it.next().intValue(), newskills, COSTS_COMPLEX);
		checkTF(artillary2ScrollbarTf, it.next().intValue(), newskills, COSTS_COMPLEX);
		checkTF(artillary3ScrollbarTf, it.next().intValue(), newskills, COSTS_COMPLEX);

		father.getHero().setFightingSkillsLl(newskills);
		setGPEPLabel();
	}

	private int getComplexCosts(int val)
	{
		int ret = 0;
		for (int i = 1; i <= val; i++)
			ret += 15+3*i;
		return ret;
	}
	
	public void checkTF(MyTextField compareWith, int oldval,
			LinkedList<Integer> newskills, int costs) {
		try {
			int newval = Integer.parseInt(compareWith.getText());

			if (costs == COSTS_ELITARY)
				father.getSInf().increaseEP((oldval - newval) * 100);
			else
				if (costs == COSTS_COMPLEX)
					father.getSInf().increaseEP(getComplexCosts(oldval) - getComplexCosts(newval));

			newskills.add(new Integer(newval));
		} catch (NumberFormatException cce) {
			// nothing
		}
	}

	private String concat(double number) {
		String ret = number + "";
		if (ret.length() > 4) {
			return ret.substring(0, 5);
		} else
			return ret;
	}

	private void fillRangeWeapons() {
		AdvantageFamily[] advantages = ((Style) (StyleFactory
				.getRangeWeaponsK().getFirst())).getAdvantages();
		for (int i = 0; i < 20; i++) {
			int ind = ((Noteable) (rangeWeaponsComArr[i])).getIndex();
			try {
				removeCompletely(rangeWeaponsComArr[i]);
				rangeWeaponsComArr[i] = advantages[i].generateComponent(this,
						ind);
				add(rangeWeaponsComArr[i]);
				addListeners(rangeWeaponsComArr[i]);
				if (rangeWeaponsComArr[i] instanceof MyTextField)
					((MyTextField) (rangeWeaponsComArr[i])).setEditable(false);
			} catch (NullPointerException npe) {
				MyTextField tf = new MyTextField(" ", ind);
				tf.setEditable(false);
				rangeWeaponsComArr[i] = tf;
				add(rangeWeaponsComArr[i]);
				addListeners(rangeWeaponsComArr[i]);
			}
		}
		int offsety = rwfsvLabel.getY() + Constants.HEIGHT * 2;

		for (int i = 1; i < 21; i++) {
			rangeWeaponsComArr[i - 1].setBounds(Constants.OFFSETX
					+ (i * FIELDWIDTH) + FIELDSOFFSET, offsety + 2
					* (Constants.HEIGHT + Constants.SPACEY), FIELDWIDTH
					- Constants.SPACEX, Constants.HEIGHT);
			if (((Noteable) (rangeWeaponsComArr[i - 1])).getNote() != null)
				((Noteable) (rangeWeaponsComArr[i - 1])).getNote()
						.actualizeBounds();
		}
	}
	
	public int costsOfAdvantage(Advantage adv)
	{
		if (father.getSInf().isCheapPriceForAdvantages())
			return (int)(adv.getCosts()*35 + (adv.getCosts()%1.0>0?1:0));	// 35*1.5 (rounded down) would be 1 ep too low.
		else																// in that case add 1 ep
			return (int)(adv.getCosts()+0.75)*35;
	}
}
