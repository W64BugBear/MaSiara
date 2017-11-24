package races;

import gui.SkillsPanel;

import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import background.SerializableInformation;
import character.Hero;
import character.RaceModifiers;
import character.Skill;
import dataclasses.SkillLists;

public class Efreet {
	public static void becomeEfreet(Hero hero, SerializableInformation sInf, SkillsPanel skillsPanel)
	{
		LinkedList<RaceModifiers> rml = hero.getRace().getRaceModifiers();
		Iterator<RaceModifiers> it = rml.iterator();
		
		RaceModifiers rm = it.next();
		
		int[] mods = rm.getMods();
		mods[2]+=2; // Gw
		mods[5]+=2; // S
		mods[3]+=2; // Im
		
		mods[4]++; // K
		mods[6]++; // Z
		mods[13]++; // Sb
		
		mods[14]+=2; // day vision
		mods[15]+=4; // night vision
		mods[16]+=2; // hearing
		mods[17]+=3; // smelling
			
		rm.setMods(mods);
		
		rml.removeFirst();
		rml.addFirst(rm);
		
		hero.getRace().setRaceModifiers(rml);
		
		hero.setEfreet(true);
		
		JComboBox combo = skillsPanel.getAdditionalSkillsCombo();
		String[] strings = SkillLists.getVampireAbilites();
		for (int i = 0; i < strings.length; i++)
			combo.addItem(new Skill(strings[i], true, 1, null, Skill.TYPE_COMPLEX, Skill.GROUP_NONE));
	}
	
	public static void becomeHuman (Hero hero, SerializableInformation sInf, JLabel gpep, SkillsPanel skillsPanel)
	{
		LinkedList<RaceModifiers> rml = hero.getRace().getRaceModifiers();
		Iterator<RaceModifiers> it = rml.iterator();
		
		RaceModifiers rm = it.next();
		
		int[] mods = rm.getMods();
		mods[2]-=2; // Gw
		mods[5]-=2; // S
		mods[3]-=2; // Im
		
		mods[4]--; // K
		mods[6]--; // Z
		mods[13]--; // Sb
		
		mods[14]-=2; // day vision
		mods[15]-=4; // night vision
		mods[16]-=2; // hearing
		mods[17]-=3; // smelling
		
		rm.setMods(mods);
		
		rml.removeFirst();
		rml.addFirst(rm);
		
		hero.getRace().setRaceModifiers(rml);
		
		hero.setVampire(false);
		
		JComboBox combo = skillsPanel.getAdditionalSkillsCombo();
		
		int toDelete = SkillLists.getVampireAbilites().length;
		for (int i = 0; i < toDelete; i++)
			combo.removeItemAt(combo.getItemCount()-1);
	}
}
