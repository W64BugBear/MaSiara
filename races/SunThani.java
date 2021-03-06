package races;

import gui.MainWindow;
import gui.RaceModDialog;
import gui.SkillsPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import background.Constants;
import background.SerializableInformation;
import character.Hero;
import character.Item;
import character.RaceModifiers;

public class SunThani 
	extends Race
	implements ItemListener
{
	public SunThani ()
	{
		super ("Sun'thanis", true, true, true, Race.RICH, "K-1, Iz+1; Sun'thains dürfen sich eine beliebige Eigenschaft aussuchen, für die der Rassenmodifikator +1 ist. Außerdem dürfen sie Ausdauer zum halben Preis steigern, solang es nicht höher ist als die Z.", "keine", "sunthani");
		LinkedList<RaceModifiers> rm = new LinkedList<RaceModifiers>();
		rm.add(new RaceModifiers("Mensch", 
				0, 0, 0, 0, -1, 0, 0, 	// physical attributes
				0, 0, 1, 0, 0, 0, 0,	// mental attributes
				0, 0, 0, 0, 0, 0, 	// senses
				0, 0, 0,
				true));			// range weapon modifiers (bows, throwing weapons, magic)
		setRaceModifiers(rm);
	}
	
	public void set(Hero hero, SerializableInformation sInf, JLabel gpep, MainWindow father, boolean loading)
	{
		super.set(hero, sInf, gpep, father, loading);
		
		father.freeRaceMod(true);
//		father.freeRaceGroupValMod(true);		
	}
	
	public JComponent specials()
	{
		JPanel spane = new JPanel();
		spane.setLayout(null);
		spane.setSize(Constants.WIDTH2*3/2+Constants.SPACEX, Constants.HEIGHT*2+Constants.SPACEY);

		String[] nobility = {"nicht adelig", "Adel", "Hochadel"};
		JComboBox<String> combo = new JComboBox(nobility);
		combo.setSelectedIndex(hero.getNobility());
		
		combo.setName("a");
		combo.addItemListener(this);
		spane.add(combo);
		combo.setBounds(0, 0, Constants.WIDTH1, Constants.HEIGHT);
		
		String[] wealth = {"nichts", "Wohlhabend 1", "Wohlhabend 2", "Wohlhabend 3", "Wohlhabend 4", "Wohlhabend 5", "Wohlhabend 6", "Wohlhabend 7", "Wohlhabend 8", "Wohlhabend 9", "Wohlhabend 10"};
		JComboBox<String> combo2 = new JComboBox(wealth);
		combo2.setSelectedIndex(hero.getWealthy());
		
		combo2.setName("b");
		combo2.addItemListener(this);
		spane.add(combo2);
		combo2.setBounds(0, Constants.HEIGHT+Constants.SPACEY, Constants.WIDTH1, Constants.HEIGHT);
		
		hero.setSpecialsStr("keine");
		
		return spane;
//		return new SpecialsPanel();
	}
	
	
	public void itemStateChanged (ItemEvent ie)
	{
		JComboBox combo = (JComboBox)(ie.getSource());
		if (combo.getName().equals("a"))
		{
			int difference = combo.getSelectedIndex() - hero.getNobility();
			sInf.setGpInvestedInSpecials(sInf.getGpInvestedInSpecials()+difference);
			sInf.increaseEP(difference*-175);
			setGPEPLabel();
			int nobility = combo.getSelectedIndex();
			
			switch (nobility) {
			case 1:
				hero.setNobility(Hero.NOB_NOBLE);
				break;
			case 2:
				hero.setNobility(Hero.NOB_HIGH_NOBILITY);
				break;
			default:
				hero.setSpecialsStr("keine");
				hero.setNobility(hero.NOB_BASEBORN);
			}
		}
		else
		{
			int difference = combo.getSelectedIndex() - hero.getWealthy();
			sInf.setGpInvestedInSpecials(sInf.getGpInvestedInSpecials()+difference);
			sInf.increaseEP(difference*-175);
			setGPEPLabel();
			hero.setWealthy(combo.getSelectedIndex());
		}
	}
	
	public Dimension getSpecialSize()
	{
		return new Dimension (Constants.WIDTH2*3/2+Constants.SPACEX, Constants.HEIGHT*2+Constants.SPACEY*2);
	}
	
	public void remove (MainWindow father)
	{
		father.freeRaceMod(false);
//		father.freeRaceGroupValMod(false);
		
		
		int returngp = hero.getNobility() + hero.getWealthy();
		sInf.setGpInvestedInSpecials(0);
		sInf.increaseEP(returngp*175);
		hero.setNobility(Hero.NOB_BASEBORN);
		hero.setWealthy(0);
	}
	
	public void applyMale()
	{
		LinkedList<RaceModifiers> rml = hero.getRace().getRaceModifiers();
		LinkedList<RaceModifiers> newrml = new LinkedList<RaceModifiers>();
		Iterator<RaceModifiers> it = rml.iterator();
		
		while (it.hasNext())
		{
			RaceModifiers rm = it.next();
			
			int[] mods = rm.getMods();
			mods[3]++; // Im
			mods[4]++; // K
			mods[5]++; // S
			mods[6]++; // Z
				
			rm.setMods(mods);
			
			newrml.add(rm);
		}	
			
		hero.getRace().setRaceModifiers(newrml);
	}
	
	public void applyFemale()
	{
		LinkedList<RaceModifiers> rml = hero.getRace().getRaceModifiers();
		LinkedList<RaceModifiers> newrml = new LinkedList<RaceModifiers>();
		Iterator<RaceModifiers> it = rml.iterator();
		
		while (it.hasNext())
		{
			RaceModifiers rm = it.next();
			
			int[] mods = rm.getMods();
			mods[0]++; // A
			mods[2]++; // Gw
			mods[6]++; // Z
			mods[8]++; // E
				
			rm.setMods(mods);
			
			newrml.add(rm);
		}	
			
		hero.getRace().setRaceModifiers(newrml);
	}
	
	public void unapplyMale()
	{
		LinkedList<RaceModifiers> rml = hero.getRace().getRaceModifiers();
		LinkedList<RaceModifiers> newrml = new LinkedList<RaceModifiers>();
		Iterator<RaceModifiers> it = rml.iterator();
		
		while (it.hasNext())
		{
			RaceModifiers rm = it.next();
			
			int[] mods = rm.getMods();
			mods[3]--; // Im
			mods[4]--; // K
			mods[5]--; // S
			mods[6]--; // Z
				
			rm.setMods(mods);
			
			newrml.add(rm);
		}	
			
		hero.getRace().setRaceModifiers(newrml);		
	}
	
	public void unapplyFemale()
	{
		LinkedList<RaceModifiers> rml = hero.getRace().getRaceModifiers();
		LinkedList<RaceModifiers> newrml = new LinkedList<RaceModifiers>();
		Iterator<RaceModifiers> it = rml.iterator();
		
		while (it.hasNext())
		{
			RaceModifiers rm = it.next();
			
			int[] mods = rm.getMods();
			mods[0]--; // A
			mods[2]--; // Gw
			mods[6]--; // Z
			mods[8]--; // E
				
			rm.setMods(mods);
			
			newrml.add(rm);
		}	
			
		hero.getRace().setRaceModifiers(newrml);
	}

	@Override
	public String serializeYourself() {
		String ret = super.getName() + ";";
		return ret;

	}

	@Override
	public void deserializeYourself(StringTokenizer t) {
		// TODO Auto-generated method stub
		
	}
}