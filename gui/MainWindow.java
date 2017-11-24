package gui;
import guidialogelements.MaSiaraMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import races.Dwarves;
import races.Vampire;
import races.Werewolf;
import background.Constants;
import background.Parser;
import background.RuntimeInformation;
import background.SerializableInformation;
import character.Hero;

public class MainWindow 
	extends JFrame
{
	Hero hero;
	RuntimeInformation rInf;
	SerializableInformation sInf;
	
	MaSiaraMenu menu;
	
	JScrollPane scrollPane;
	
//	JPanel[] panels = new JPanel[6];
	StartPanel startPanel;
	PropertiesPanel propertiesPanel;
	FightPanel fightPanel;
	WizardPanel wizardPanel;
	SkillsPanel skillsPanel;
	EquipmentPanel equipmentPanel;
	
	JPanel statusPanel;
	JLabel gpepLabel;
	JLabel statusLabel;

	public static void main(String[] args) {
		MainWindow wnd = new MainWindow();
		wnd.setLocation(300, 100);
		wnd.pack();
		wnd.setVisible(true);
	}

	public MainWindow()
	{
		super("MaSiara");
		addWindowListener(new WindowAdapter() {
		
			public void windowClosing(WindowEvent arg0) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});

//	    try 
//	    {
//	    	UIManager.setLookAndFeel(new SyntheticaSilverMoonLookAndFeel());
//	    } 
//	    catch (Exception e) 
//	    {
//	    	e.printStackTrace();
//	    }

	    // Generate empty data
		rInf = new RuntimeInformation();
		sInf = new SerializableInformation();
		hero = new Hero();
		
		// build Frame
		
		getContentPane().setLayout(new BorderLayout());
	
		statusPanel = new JPanel();
		gpepLabel = new JLabel();
		statusLabel = new JLabel("alles in Butter");
		
		statusPanel.setBackground(Color.LIGHT_GRAY);
		
		hero.getRace().set(hero, sInf, gpepLabel, this, true);
		
		startPanel = new StartPanel(this);
//			panels[0] = startPanel;
		propertiesPanel = new PropertiesPanel(this);
//			panels[1] = propertiesPanel;
		fightPanel = new FightPanel(this);
//			panels[2] = fightPanel;
		wizardPanel = new WizardPanel(this);
//			panels[3] = wizardPanel;
		skillsPanel = new SkillsPanel(this);
//			panels[4] = skillsPanel;
		equipmentPanel = new EquipmentPanel(this);
//			panels[5] = equipmentPanel;
		
		scrollPane = new JScrollPane(startPanel);
		
		getContentPane().add(scrollPane, BorderLayout.NORTH);
		getContentPane().add(statusPanel, BorderLayout.SOUTH);
		statusPanel.add(gpepLabel);
		statusPanel.add(statusLabel);
		
		// add menu
		
		menu = new MaSiaraMenu(this, startPanel.getNameTf());
		setJMenuBar(menu);
			
		try
		{
			// StartDialog
			StartDialog sd = new StartDialog(this);
			
			sd.setLocation(this.getX()+100, this.getY()+100);
			sd.setSize(sd.getPreferredSize());
			sd.setVisible(true);

			if (sd.getSelectedHero() == null)	// new hero
			{
				this.setHero(new Hero());
				hero.setRace(new Dwarves());
				hero.getRace().set(hero, sInf, gpepLabel, this, true);
				hero.getRace().applyMale();
				
				this.getSInf().setEpInt(sd.getEp());
				this.getSInf().setEpAmountInt(sd.getEp());
				
				this.setPanels(null, 0);	// initialize 'em
				
				this.getStatusLabel().setText("alles in Butter");
			}
			else
			{
				File file = new File(Constants.HEROSTRING + "//" + sd.getSelectedHero() + ".hero");
				
				BufferedReader reader = new BufferedReader(new FileReader(file));
				int version = Integer.parseInt(reader.readLine());
				this.setHero(Parser.deparseHero(reader.readLine(), version, sInf));
				
				reader.close();
				
				int returnedEP = sInf.getEpInt();		// EP returned through rules erratae
				
				file = new File(Constants.RUNTIMESTRING + "//"  + sd.getSelectedHero() + ".masiara");
				reader = new BufferedReader(new FileReader(file));
				version = Integer.parseInt(reader.readLine());
				this.setSInf(Parser.deparseSInf(reader.readLine(), version));
				this.getSInf().setEpInt(sd.getEp() + returnedEP);
								
				hero.getRace().set(hero, sInf, gpepLabel, this, true);
				if (hero.getSex() == Constants.MALE)
					hero.getRace().applyMale();
				else
					hero.getRace().applyFemale();
				
				this.setPanels(reader.readLine(), version);
				
				reader.close();
				
				startPanel.getNameTf().setText(hero.getName());
				
//				sInf.setLatestSumOfGp(hero);
				if (hero.isVampire())
				{
					Vampire.becomeVampire(hero, sInf, skillsPanel);
					startPanel.getManVampWwCB().setSelectedIndex(1);
				}
				if (hero.isWerewolf())
				{
					Werewolf.becomeWerewolf(hero, sInf, skillsPanel);
					startPanel.getManVampWwCB().setSelectedIndex(2);
				}
				
				this.getStatusLabel().setText("alles in Butter");
			}
		} catch (FileNotFoundException fnfe)
		{
			this.getStatusLabel().setText("Datei nicht gefunden!");
		} catch (IOException ioe)
		{
			this.getStatusLabel().setText("IO-Fehler!");
		} 
	}
	
	public void displayStartPanel ()
	{
		getContentPane().remove(scrollPane);
		scrollPane = new JScrollPane(startPanel);
		getContentPane().add(scrollPane);
		pack();
		startPanel.getDefaultFocusComponent().requestFocus();
		this.setLocation(300, 100);
		this.setVisible(true);
		menu.save();
	}

	public void displayPropertiesPanel ()
	{
		getContentPane().remove(scrollPane);
		scrollPane = new JScrollPane(propertiesPanel);
		getContentPane().add(scrollPane);
		pack();
		propertiesPanel.getDefaultFocusComponent().requestFocus();
		this.setLocation(300, 50);
		this.setVisible(true);
		menu.save();
	}
	
	public void displayFightPanel ()
	{
		getContentPane().remove(scrollPane);
		scrollPane = new JScrollPane(fightPanel);
		getContentPane().add(scrollPane);
		setSize(1260, 750);
		fightPanel.getDefaultFocusComponent().requestFocus();
		fightPanel.actualizeTextValues();
		this.setLocation(10, 10);
		this.setVisible(true);
		menu.save();
	}
	
	public void displayWizardPanel (int spellForce, int numberOfBranches)
	{
		getContentPane().remove(scrollPane);
		scrollPane = new JScrollPane(wizardPanel);
		getContentPane().add(scrollPane);
		setSize(1260, 610);
		wizardPanel.getDefaultFocusComponent().requestFocus();
		wizardPanel.initializeBasicSkills(spellForce, numberOfBranches);
//		wizardPanel.actualizeValues();
		this.setLocation(40, 50);
		this.setVisible(true);
		menu.save();
	}
	
	public void displaySkillsPanel ()
	{
		getContentPane().remove(scrollPane);
		scrollPane = new JScrollPane(skillsPanel);
		getContentPane().add(scrollPane);
		setSize(1260, 750);
		skillsPanel.getDefaultFocusComponent().requestFocus();
		this.setLocation(60, 0);
		this.setVisible(true);
		menu.save();
	}
	
	public void displayEquipmentPanel ()
	{
		getContentPane().remove(scrollPane);
		scrollPane = new JScrollPane(equipmentPanel);
		getContentPane().add(scrollPane);
		equipmentPanel.refreshItemList();
		pack();
		this.setLocation(300, 50);
		this.setVisible(true);
		menu.save();
	}
	
	public String getPanels() {
		String s = 
				startPanel.serializeYourself() + "$" +
				propertiesPanel.serializeYourself() + "$" +
				fightPanel.serializeYourself() + "$" +
				wizardPanel.serializeYourself() + "$" +
				skillsPanel.serializeYourself() + "$" +
				equipmentPanel.serializeYourself();
		
		return s;
	}

	public void setPanels(String panels, int version) {

		if (panels == null)
		{
			startPanel.deserializeYourself();	// initializing
			propertiesPanel.deserializeYourself();
			fightPanel.deserializeYourself();
			wizardPanel.deserializeYourself();
			skillsPanel.deserializeYourself();
			equipmentPanel.deserializeYourself();
			return;
		}
		
		StringTokenizer t = new StringTokenizer(panels, "$");
		
		startPanel.deserializeYourself(t.nextToken(), version);
		propertiesPanel.deserializeYourself(t.nextToken(), version);
		fightPanel.deserializeYourself(t.nextToken(), version);
		wizardPanel.deserializeYourself(t.nextToken(), version);
		skillsPanel.deserializeYourself(t.nextToken(), version);
		equipmentPanel.deserializeYourself(t.nextToken(), version);
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public RuntimeInformation getRInf() {
		return rInf;
	}

	public void setRInf(RuntimeInformation inf) {
		rInf = inf;
	}

	public JLabel getStatusLabel() {
		return statusLabel;
	}

	public void setStatusLabel(JLabel statusLabel) {
		this.statusLabel = statusLabel;
	}

	public SerializableInformation getSInf() {
		return sInf;
	}

	public void setSInf(SerializableInformation inf) {
		sInf = inf;
	}

	public JLabel getGpepLabel() {
		return gpepLabel;
	}

	public void setGpepLabel(JLabel gpepLabel) {
		this.gpepLabel = gpepLabel;
	}
	
	public JScrollPane getScrollPane()
	{
		return this.scrollPane;
	}

	public PropertiesPanel getPropertiesPanel() {
		return propertiesPanel;
	}

	public void setPropertiesPanel(PropertiesPanel propertiesPanel) {
		this.propertiesPanel = propertiesPanel;
	}

	public SkillsPanel getSkillsPanel() {
		return skillsPanel;
	}
	
	public void displayMagicDialog () {
		ChooseMagicDialog cmd = new ChooseMagicDialog(this);
		cmd.setLocation(getX()+100, getY());
		cmd.setSize(cmd.getPreferredSize());
		cmd.setVisible(true);
		int choice = cmd.getChoice();
		
		if (choice == -1)
			return;
		
//		hero.getMagic()[choice] = true;
		
		boolean alreadyThaumat = false;
		for (int i = 1; i < Constants.THAUMATURGICALBRANCHES+1; i++)
			if (hero.getMagic()[i])
				alreadyThaumat = true;
		
		int numberOfSpells = 3*choice;
		int spellForce = -3;
	
		if (alreadyThaumat)
			spellForce = hero.getMagesSkillsLl().getFirst().intValue();	// spellForce is the 1st in the list
		else
		{
			switch (choice) {
			case Constants.SORCLING: spellForce = 4; break;
			case Constants.SORC: spellForce = 7; break;
			case Constants.MAGICIAN: spellForce = 9; break;
			case Constants.GRANDMAGICIAN: spellForce = 11; break;
			}
		}
		
		if (!alreadyThaumat)	// just become magician
		{
			sInf.increaseEP(choice*-175);	
			hero.getMagic()[choice] = true;
		}
		displayWizardPanel(spellForce, numberOfSpells);
		
		wizardPanel.actualizeValues();
		wizardPanel.checkGPEP();
		wizardPanel.actualizeBounds();
	}

	public StartPanel getStartPanel() {
		return startPanel;
	}

	public void setStartPanel(StartPanel startPanel) {
		this.startPanel = startPanel;
	}

	public FightPanel getFightPanel() {
		return fightPanel;
	}

	public WizardPanel getWizardPanel() {
		return wizardPanel;
	}

	public EquipmentPanel getEquipmentPanel() {
		return equipmentPanel;
	}

	public void setWizardPanel(WizardPanel wizardPanel) {
		this.wizardPanel = wizardPanel;
	}

	public void setSkillsPanel(SkillsPanel skillsPanel) {
		this.skillsPanel = skillsPanel;
	}

	public void setEquipmentPanel(EquipmentPanel equipmentPanel) {
		this.equipmentPanel = equipmentPanel;
	}

	public void setFightPanel(FightPanel fightPanel) {
		this.fightPanel = fightPanel;
	}
	
	/**
	 * @param set true when race is chosen, false when is undone
	 */
	public void freeRaceMod(boolean set) {
		if (set && hero.getFreeRaceModifierIndex() != -1)		// already chosen, e.g. load file
			return;
		
		if (!set)	// undo
		{
			propertiesPanel.getOwnmods()[hero.getFreeRaceModifierIndex()].minusminus();
			hero.setFreeRaceModifierIndex(-1);
			return;
		}
		
		RaceModDialog rmd = new RaceModDialog(this, RaceModDialog.PROPERTIES, set);
		
//		if (rmd.getChosenOne() == -1)
//		{
//			statusLabel.setText("Es war kein freier Rassenmod. mehr da, den ich wegtun hï¿½tte kï¿½nnen. :-(");
//			return;
//		}
		propertiesPanel.getOwnmods()[rmd.getChosenOne()].plusplus();
		hero.setFreeRaceModifierIndex(rmd.getChosenOne());
	}
	
	/**
	 * @param set true when race is chosen, false when is undone
	 */
//	public void freeRaceGroupValMod(boolean set) {
//	RaceModDialog rmd = new RaceModDialog(this, RaceModDialog.GROUPVALUES, set);
//		int pos = rmd.getChosenOne();
//		
//		if (pos == -1)
//		{
//			sInf.increaseEP(-35);
//			statusLabel.setText("Anstatt dem freien GFW wurden 35 EP abgezogen (wahrsch. wurde der GFW wegen zu niedrigen FW zurï¿½ckgesetzt)");
//			return;
//		}
//		
//		int counter = 0;
//		for (int i = 0; i < 4; i++)
// 		{
//			Iterator it = getSkillsPanel().getSkillPositioners()[i].getComponents().iterator();
//			while (it.hasNext())
//			{
//				try {
//					SkillUnit su = (SkillUnit)(it.next());
//					if (su.getSkill().getTypeInt() == Skill.TYPE_COMPLEX && su.getSkill().isBaseSkill())	// Complex basic skill, i.e. group skill
//					{
//						if (counter == pos)
//						{
//							if (set)
//								su.getSkill().setValueInt(su.getSkill().getValueInt()+1);
//							else
//								su.getSkill().setValueInt(su.getSkill().getValueInt()-1);
//							su.getValueSpinner().setText(su.getSkill().getValueInt()+"");
//							return;
//						}
//						counter++;
//					}
//				} catch (ClassCastException cce) {/*nothing*/}
//			}
// 		}
//	}
}
