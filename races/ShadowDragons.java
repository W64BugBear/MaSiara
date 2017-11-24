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

public class ShadowDragons 
	extends Race
{
	public ShadowDragons ()
	{
		super ("Schattendrachen", false, false, false, Race.POOR, "Gs-3, Gw+3, K-1, Z-2, C+1, Iz+1, SK+2; Tag- und Nachtsicht +1, Riechen, Geschmack +4, Gehör -1, Tastsinn -2; Natürlicher Rüstungsschutz (Leder): 2; Klauen (+6)\n\rZwergdrachen können natürlich fliegen (für Geschwindigkeit S+12). \n\rAnstatt eines Angriffes (sie werfen immer mindestens (Iz+In)/3 Wfürfel, falls sie durch den An-Wert weniger werfen dürften) oder immer zusätzlich bei einem Kritschen Treffer oder statt Schmutziger Tricks (siehe „Die Ästhetik der Kampfkunst“) kann ein Drache den Feuerodem (2 W3, 2 W6) (siehe Zoolexikon) anwenden. Danach muss er 50 Sekunden warten, bevor er einen neuen Flammenstoß von sich geben kann.\n\rZwergdrachen verwenden im waffenlosen Nahkampf Krallen und können ihr Gebiss einsetzen (Schadenswert 8, siehe \"Die Ästhetik der Kampfkunst\" – Einsatz von Bissen).","Zwergdrachen können natürlich fliegen (für Geschwindigkeit S+12). \n\rAnstatt eines Angriffes (sie werfen immer mindestens (Iz+In)/3 Wfürfel, falls sie durch den An-Wert weniger werfen dürften) oder immer zusätzlich bei einem Kritschen Treffer oder statt Schmutziger Tricks (siehe „Die Ästhetik der Kampfkunst“) kann ein Drache den Feuerodem (2 W3, 2 W6) (siehe Zoolexikon) anwenden. Danach muss er 50 Sekunden warten, bevor er einen neuen Flammenstoß von sich geben kann.\n\rZwergdrachen verwenden im waffenlosen Nahkampf Krallen und können ihr Gebiss einsetzen (Schadenswert 8, siehe \"Die Ästhetik der Kampfkunst\" – Einsatz von Bissen).", "shaddrag");
		LinkedList<RaceModifiers> rm = new LinkedList<RaceModifiers>();
		rm.add(new RaceModifiers("normal", 
				0, -3, 3, 0, -1, 0, -2, 
				1, 0, 1, 0, 2, 0, 0,
				1, 1, -1, 4, 4, -2, 
				0, 0, 0,
				false));
		rm.add(new RaceModifiers("fliegen", 
				0, -3, 3, 0, -1, 12, -2, 
				1, 0, 1, 0, 2, 0, 0,
				1, 1, -1, 4, 4, -2, 
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
			mods[7]++; // C
			mods[11]++; // SK
			mods[12]++; // M
				
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
			mods[7]--; // C
			mods[11]--; // SK
			mods[12]--; // M
				
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
			mods[7]++; // C
			mods[11]++; // SK
			mods[12]++; // M
			
				
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
			mods[7]--; // C
			mods[11]--; // SK
			mods[12]--; // M
			
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
