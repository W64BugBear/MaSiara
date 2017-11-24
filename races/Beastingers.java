package races;

import gui.MainWindow;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JComponent;
import javax.swing.JLabel;

import background.SerializableInformation;
import character.Hero;
import character.RaceModifiers;

public class Beastingers 
	extends Race
{
	public Beastingers ()
	{
		super ("Biestinger", true, true, true, Race.POOR, "Gs-1, Gw+2, S+2, In+1, Iz-1; alle Sinne +7 außer: Hören+9, Riechen+11; MR: 2\nWenn sie auf allen vieren laufen, bekommen Biestinger zur Berechnung der Geschwindigkeit noch mal S+4.\nBiestinger richten im waffenlosen Kampf echte Schadenspunkte an und können ihr Gebiss einsetzen (Schadenswert 4).", "Wenn sie auf allen vieren laufen, bekommen Biestinger zur Berechnung der Geschwindigkeit noch mal S+4.\nBiestinger richten im waffenlosen Kampf echte Schadenspunkte an und können ihr Gebiss einsetzen (Schadenswert 4).","beast");
		LinkedList<RaceModifiers> rm = new LinkedList<RaceModifiers>();
		rm.add(new RaceModifiers("aufrecht", 
				0, -1, 2, 0, 0, 2, 0, 
				0, 0, -1, 1, 0, 0, 0,
				0, 0, 2, 4, 0, 0, 
				0, 0, 0, 
				false));
		rm.add(new RaceModifiers("auf allen vieren", 
				0, -1, 2, 0, 0, 6, 0, 
				0, 0, -1, 1, 0, 0, 0,
				0, 0, 2, 4, 0, 0, 
				0, 0, 0,
				false));
		setRaceModifiers(rm);
	}
	
	public void set(Hero hero, SerializableInformation sInf, JLabel gpep, MainWindow father, boolean loading)
	{
		super.set(hero, sInf, gpep, father, loading);
	}
	
	public void remove (MainWindow father)
	{

	}
	
	public JComponent specials()
	{
		return new JLabel("keine Besonderheiten");
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
