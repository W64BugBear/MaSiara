package races;

import gui.MainWindow;
import gui.SkillsPanel;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JComponent;
import javax.swing.JLabel;

import background.SerializableInformation;
import character.Hero;
import character.RaceModifiers;

public class Nagas 
	extends Race
{
	public Nagas ()
	{
		super ("Skirks", true, true, true, Race.POOR, "Gw+3, K-2, S+3, Z-1, C-1, Im-1, Iz+1, M-1; Tagsicht +5, Nachtsicht +6, Tastsinn+7, Geschmack +8, Gehör+9, Geruch+10; MR: 2\nAuf allen vieren laufend erhalten sie zur Berechnung der Geschwindigkeit zusätzlich +3.\nIm Kampf erhalten Skirks für jeden Gegner Überzahl/Unterzahl +1/-1 auf M, wobei dies maximal +3/-3 bewirken kann. Der Mut kann nicht sinken, wenn der Skirk sein Leben verteidigt und nicht flüchten kann.\nSkirks sind gegenüber Krankheiten und Giften ähnlich resistent wie Kanalratten (Z+5 bzw. +3 auf andere relevante Eigenschaften statt der regulären Modifikatoren.)… und wie diese haben sie einen Schwanz: Den können sie für alles Mögliche benutzen: Zum Balancehalten (bis zu 5 Erleichterung auf Körperbeherrschung, Klettern, …), zum Kämpfen (zählt wie eine zweite linke Hand, allerdings mit KW und K je -5 (statt -2)) oder einfach aus Langeweile (bis zu 3 Erleichterung beim Nervös-Herumklopfen). Aufgrund ihrer geringen Körpergröße und der in der Regel unauffälligen Fellfarbe sollte ein Spielleiter einige Heimlichkeitsproben (insbesondere Schleichen und Verstecken) etwas erleichtern.\nSkirks verwenden im waffenlosen Nahkampf Krallen und können ihr Gebiss einsetzen (Schadenswert 2, siehe \"Die Ästhetik der Kampfkunst\" – Einsatz von Bissen).","Auf allen vieren laufend erhalten sie zur Berechnung der Geschwindigkeit zusätzlich +3. Im Kampf erhalten Skirks für jeden Gegner Überzahl/Unterzahl +1/-1 auf M, wobei dies maximal +3/-3 bewirken kann. Der Mut kann nicht sinken, wenn der Skirk sein Leben verteidigt und nicht flüchten kann.\nSkirks sind gegenüber Krankheiten und Giften ähnlich resistent wie Kanalratten (Z+5 bzw. +3 auf andere relevante Eigenschaften statt der regulären Modifikatoren.)… und wie diese haben sie einen Schwanz: Den können sie für alles Mögliche benutzen: Zum Balancehalten (bis zu 5 Erleichterung auf Körperbeherrschung, Klettern, …), zum Kämpfen (zählt wie eine zweite linke Hand, allerdings mit KW und K je -5 (statt -2)) oder einfach aus Langeweile (bis zu 3 Erleichterung beim Nervös-Herumklopfen). Aufgrund ihrer geringen Körpergröße und der in der Regel unauffälligen Fellfarbe sollte ein Spielleiter einige Heimlichkeitsproben (insbesondere Schleichen und Verstecken) etwas erleichtern.\nSkirks verwenden im waffenlosen Nahkampf Krallen und können ihr Gebiss einsetzen (Schadenswert 2, siehe \"Die Ästhetik der Kampfkunst\" – Einsatz von Bissen).", "skirk");
		LinkedList<RaceModifiers> rm = new LinkedList<RaceModifiers>();
		rm.add(new RaceModifiers("normal", 
				0, 0, 2, 0, 3, 0, 0, 
				0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 
				0, 0, 0,
				false));
		rm.add(new RaceModifiers("auf allen vieren", 
				0, 0, 3, -1, -2, 6, -1, 
				-1, -1, 1, 0, 0, -1, 0,
				-2, -1, 3, 1, 2, 0, 
				0, 0, 0,
				false));
		rm.add(new RaceModifiers("Gifte/Krankheiten", 
				0, 0, 3, -1, -2, 3, 5, 
				-1, -1, 1, 0, 0, -1, 0,
				-2, -1, 3, 1, 2, 0, 
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
