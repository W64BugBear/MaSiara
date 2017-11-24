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
import character.Skill;

public class Fairies 
	extends Race
{
	Skill fear;
	
	public Fairies ()
	{
		super ("Feen", false, false, false, Race.POOR, "A+1, C+1; für intuitive Magie: In+3, Z/K nach Feenmaßstab*, max 15.; alle Sinne+9; MR: 3, können Gedankenlesen (3 GP), Lichtkugel (2 GP), Tierempathie (1 GP) lernen und sind automatisch intuitiv magiebegabt. Für Feen gelten besondere Regeln, um sie zu treffen und zu verwunden.", "Normalerweise haben Feen effektiv ungefähr ein  Viertel ihrer Zähigkeit. Wenn beispielsweise eine Fee mit Z: 14 auf dem Tisch sitzt und jemand sie zerklatscht, hat sie effektiv Z: 4 (3,5 wird gerundet). Dadurch, dass eine Fee weniger Gewicht hat, wird viel Schlagenergie in Bewegungsenergie umgewandelt, wenn die Fee kein Hindernis hinter sich hat. Für die Schadenswiderstandsprobe darf sie deswegen Z/2 verwenden.\nBeispiel: Ein Besoffener will eine fliegende Fee erschlagen. Würde er sie gegen eine Wand klatschen können, hätte sie (Z: 14) nur effektiv Z: 4, da sie aber in der Luft herumfliegt, darf sie Schadenswiderstand: 7 verwenden. Er richtet eine Schwere Wunde an, also 4 Schadenspunkte. Da sie tatsächlich Z: 4 hat, erreicht ihre Wundensumme die Z und sie wird kampf-/flugunfähig.\nIm Nahkampf sind Feen sehr schwer zu treffen. Wende die ganz normalen Kampfregeln für fliegende Gegner an und würfle dann die Trefferzone aus. Sämtliche Ergebnisse von 2-20 werden ignoriert, nur eine 1 erwischt die Fee. Ob Du nun noch genauer ermittelst, wo die Fee erwischt wird, hängt von Deiner Laune, der Waffe und allgemein den Umständen ab. Diese Regeln gelten nicht, wenn Feen gegen Feen oder Schmetterlinge oder Kampfwespen kämpfen.\nAuch die effektive Kraft kann man ungefähr mit einem Viertel ihres K-Wertes annehmen.\nSollte Heilmagie oder andere Kräfte die Kraft einer Fee verändern, tut sie das natürlich vor der Viertelung oder Halbierung.","fairy");
		LinkedList<RaceModifiers> rm = new LinkedList<RaceModifiers>();
		rm.add(new RaceModifiers("Mensch", 
				1, 0, 0, 0, 0, 0, 0, 
				1, 0, 0, 0, 1, 0, 0,
				2, 2, 2, 2, 2, 2,  
				0, 0, 0, 
				false));
		rm.add(new RaceModifiers("Intuitive Magie", 
				1, 0, 0, 0, 0, 0, 0, 
				1, 0, 0, 3, 1, 0, 0,
				2, 2, 2, 2, 2, 2, 
				0, 0, 0, 
				false));
		setRaceModifiers(rm);
	}
	
	public void set(Hero hero, SerializableInformation sInf, JLabel gpep, MainWindow father, boolean loading)
	{
		super.set(hero, sInf, gpep, father, loading);
		hero.getProperty("Kraft").setMaximumInt(15);
		hero.getProperty("Zähigkeit").setMaximumInt(15);
		
		father.getSkillsPanel().getAdditionalSkillsCombo().addItem(new Skill("Gedankenlesen (erf. Wert: 15", true, 1, null, Skill.TYPE_COMPLEX, Skill.GROUP_NONE));
		father.getSkillsPanel().getAdditionalSkillsCombo().addItem(new Skill("Lichtkugel (erf. Wert: 10", true, 1, null, Skill.TYPE_COMPLEX, Skill.GROUP_NONE));
		father.getSkillsPanel().getAdditionalSkillsCombo().addItem(new Skill("Tierempathie (erf. Wert: 5", true, 1, null, Skill.TYPE_COMPLEX, Skill.GROUP_NONE));
	}
	
	public void remove (MainWindow father)
	{
		hero.getProperty("Kraft").setMaximumInt(20);
		hero.getProperty("Zähigkeit").setMaximumInt(20);
		hero.getSpecialSkillsLl().remove(fear);
		father.getSkillsPanel().getAdditionalSkillsCombo().removeItemAt(father.getSkillsPanel().getAdditionalSkillsCombo().getItemCount()-1); 
		father.getSkillsPanel().getAdditionalSkillsCombo().removeItemAt(father.getSkillsPanel().getAdditionalSkillsCombo().getItemCount()-1);
		father.getSkillsPanel().getAdditionalSkillsCombo().removeItemAt(father.getSkillsPanel().getAdditionalSkillsCombo().getItemCount()-1);
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
			mods[0]++; // Im
			mods[2]++; // K
			mods[6]++; // S
			mods[8]++; // Z
				
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
			mods[0]--; // Im
			mods[2]--; // K
			mods[6]--; // S
			mods[8]--; // Z
				
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
