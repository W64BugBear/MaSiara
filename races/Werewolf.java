// NICHT AKTUELL

package races;

import gui.SkillsPanel;
import guidialogelements.SkillPositioner;
import guidialogelements.SkillUnit;

import java.awt.Component;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import background.SerializableInformation;
import character.Hero;
import character.RaceModifiers;
import character.Skill;
import dataclasses.SkillLists;

public class Werewolf {
	public static void becomeWerewolf(Hero hero, SerializableInformation sInf, SkillsPanel skillsPanel)
	{
		LinkedList<RaceModifiers> rml = hero.getRace().getRaceModifiers();
		Iterator<RaceModifiers> it = rml.iterator();
		
		RaceModifiers rm = it.next();
		
		int[] mods = rm.getMods();
		mods[4]++; // K
		mods[6]++; // Z
		mods[11]++; // M
		mods[12]+=2; // Sk
		
		mods[14]-=2; // day vision
		mods[15]-=1; // night vision
		mods[16]+=2; // hearing
		mods[17]+=3; // smelling
		mods[18]+=2; // tasting
		mods[19]-=1; // feeling
		
		rm.setMods(mods);
		rml.removeFirst();
		rml.addFirst(rm);

		int[] wwmods = mods.clone();
		
		wwmods[1]-=3; // Gs
		wwmods[2]+=3; // Gw
		wwmods[4]+=5; // K
		wwmods[5]+=5; // S
		wwmods[6]+=4; // Z
		wwmods[7]-=1; // C
		wwmods[8]-=1; // E
		wwmods[3]+=6; // Im
		wwmods[11]+=3; // M
		
		wwmods[14]-=4; // day vision
		wwmods[15]-=3; // night vision
		wwmods[16]+=4; // hearing
		wwmods[17]+=6; // smelling
		wwmods[18]+=4; // tasting
		wwmods[19]-=2; // feeling
		
		rml.add(new RaceModifiers("Werwolfsgestalt", wwmods, ((RaceModifiers)(hero.getRace().getRaceModifiers().getFirst())).isGoodRunners()));
		
		hero.getRace().setRaceModifiers(rml);
		
		hero.setWerewolf(true);
		
		Skill skill = new Skill("Jähzorn", true, 5, null, Skill.TYPE_LIMITATION, Skill.LIMITATION_DOESNT_COUNT);
		if (hero.similarSkillAlreadyExists(skill))
		{
			skill = hero.getSkill("Jähzorn", null);
			skill.setValueInt(skill.getValueInt()+3);
			if (skill.getValueInt() < 5)
				skill.setValueInt(5);
		}
		else
		{
//			skillsPanel.addSkill(skill, skillsPanel.getLimitationsCombo());
			skillsPanel.getSkillPositioners()[3].addAfter(skill, skillsPanel.getLimitationsCombo(), true);
		}
		
		SkillPositioner sp = skillsPanel.getSkillPositioners()[3];
		Iterator iter = sp.getComponents().iterator();
		
		while (iter.hasNext())
		{
			try {
			SkillUnit su = (SkillUnit)(iter.next());
			if (su.getSkill().equals(skill))
				su.getValueSpinner().setText(skill.getValueInt()+"");
			} catch (ClassCastException cce)
			{ /*nothing*/ }
			
		}
		
		JComboBox combo = skillsPanel.getAdditionalSkillsCombo();
		String[] strings = SkillLists.getWerewolfAbilites();
		for (int i = 0; i < strings.length; i++)
			combo.addItem(new Skill(strings[i], true, 1, null, Skill.TYPE_COMPLEX, Skill.GROUP_NONE));
	}
	
	public static void becomeHuman (Hero hero, SerializableInformation sInf, JLabel gpep, SkillsPanel skillsPanel)
	{
		LinkedList<RaceModifiers> rml = hero.getRace().getRaceModifiers();
		Iterator<RaceModifiers> it = rml.iterator();
		
		RaceModifiers rm = it.next();
		
		int[] mods = rm.getMods();
		mods[4]--; // K
		mods[6]--; // Z
		mods[11]--; // M
		mods[12]-=2; // Sk
		
		mods[14]+=2; // day vision
		mods[15]+=1; // night vision
		mods[16]-=2; // hearing
		mods[17]-=3; // smelling
		mods[18]-=2; // tasting
		mods[19]+=1; // feeling
		
		rm.setMods(mods);
		rml.removeFirst();
		rml.addFirst(rm);
		
		rm.setMods(mods);
		rml.removeFirst();
		rml.addFirst(rm);

		rml.removeLast();
		
		hero.getRace().setRaceModifiers(rml);
		
		hero.setWerewolf(false);
		
		Iterator iter = skillsPanel.getSkillPositioners()[3].getComponents().iterator();
		
		while (iter.hasNext())
		{
			try {
				SkillUnit su = (SkillUnit)(iter.next());
				if (su.getSkill().getNameStr().equals("Jähzorn"))
				{
					if (su.getSkill().getValueInt() < 6)
					{
						Iterator<Skill> iterator = hero.getSpecialSkillsLl().iterator();
						while (iterator.hasNext())
						{
							Skill skill = iterator.next();
							if (skill.getNameStr().equals("Jähzorn"))
							{
								hero.getSpecialSkillsLl().remove(skill);
								break;
							}
						}
						skillsPanel.getSkillPositioners()[3].remove(su);
						break;
					}
					else
					{
						su.getSkill().setValueInt(su.getSkill().getValueInt()-3);
						su.getValueSpinner().setText(su.getSkill().getValueInt()+"");
					}
				}
			} catch (ClassCastException cce)
			{ /*nothing*/ }	
		}
		
		JComboBox combo = skillsPanel.getAdditionalSkillsCombo();
		
		int toDelete = SkillLists.getWerewolfAbilites().length;
		for (int i = 0; i < toDelete; i++)
			combo.removeItemAt(combo.getItemCount()-1);
	}
}
