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

public class Uthos 
	extends Race
{
	public Uthos ()
	{
		super ("Uthos", true, true, true, Race.POOR, "Gw+1, In+1, Z+1; alle Sinne +2;\r\nUthos dürfen sich eine Fertigkeitsgruppe bei Spielbeginn aussuchen, deren GFW bei 1 beginnt statt bei 0.", "keine", "utho");
		LinkedList<RaceModifiers> rm = new LinkedList<RaceModifiers>();
		rm.add(new RaceModifiers("Mensch", 
				0, 0, 1, 0, 0, 0, 1, 	// physical attributes
				0, 0, 0, 1, 0, 0, 0,	// mental attributes
				2, 2, 2, 2, 2, 2, 	// senses
				0, 0, 0,
				true));			// range weapon modifiers (bows, throwing weapons, magic)
		setRaceModifiers(rm);
	}
	
	public void set(Hero hero, SerializableInformation sInf, JLabel gpep, MainWindow father, boolean loading)
	{
		super.set(hero, sInf, gpep, father, loading);
		
//		father.freeRaceGroupValMod(true);		
	}
	
	public JComponent specials()
	{
		return new JLabel("keine Besonderheiten");
	}
	
	
	public void remove (MainWindow father)
	{
//		father.freeRaceGroupValMod(false);
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
