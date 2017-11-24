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

public class Parders 
	extends Race
{
	public Parders ()
	{
		super ("Parder", true, true, true, Race.POOR, "A+1, Gs-1, Gw+3, K-1, S+2, Z-2, Im-1; Tagsicht +8, Nachtsicht +10, Riechen +8, andere +7; MR: 2\nVom Schaden her zählen Parderangriffe immer als bewaffnet, jedoch mit einem Schadensmodifikator von -1. Parder können Gebiss und Klauen im Kampf einsetzen (Schadenswert 2).\nWeibliche Parder werden bis zu viermal jährlich für 24 Stunden bis zu vier Tagen rollig. Stelle das durch die Einschränkung „Rolligkeit“ dar und verteil einen Wert von 1 (1 Tag pro Jahr) bis 16 (4x4 Tage pro Jahr) darauf! Zusätzlich dazu können noch 25 weitere Punkte Einschränkungen gewählt werden. Sie sind nur in dieser Zeit fruchtbar.\nWeibliche Parder können auch im späteren Spielverlauf nicht unter 1 kommen.", 
																											"Vom Schaden her zählen Parderangriffe immer als bewaffnet, jedoch mit einem Schadensmodifikator von -1. Parder können Gebiss und Klauen im Kampf einsetzen (Schadenswert 2).\nWeibliche Parder werden bis zu viermal jährlich für 24 Stunden bis zu vier Tagen rollig. Stelle das durch die Einschränkung „Rolligkeit“ dar und verteil einen Wert von 1 (1 Tag pro Jahr) bis 16 (4x4 Tage pro Jahr) darauf! Zusätzlich dazu können noch 25 weitere Punkte Einschränkungen gewählt werden. Sie sind nur in dieser Zeit fruchtbar.\nWeibliche Parder können auch im späteren Spielverlauf nicht unter 1 kommen.","pard");
		LinkedList rm = new LinkedList<RaceModifiers>();
		rm.add(new RaceModifiers("Mensch", 
				1, -1, 3, -1, -1, 2, -2, 
				1, -1, 0, 0, 0, 0, 0,
				1, 3, 1, 0, 0, 0, 
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
