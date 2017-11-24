package character;

import gui.SkillsPanel;
import guidialogelements.SkillUnit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import background.Constants;
import dataclasses.RelatedSkill;

public class Skill 
	implements Serializable
{
	public final static int TYPE_NORMAL = 1;
	public final static int TYPE_THEORY = 2;
	public final static int TYPE_COMPLEX = 3;
	public final static int TYPE_100EP = 4;
	public final static int TYPE_STAMINA = 5;
	public final static int TYPE_LANGUAGE = 6;
	public final static int TYPE_FONT = 7;
	public final static int TYPE_LIMITATION = 8;
	public final static int TYPE_NO_SKILL = 9;
	
	public final static int GROUP_SOCIETY = 1;
	public final static int GROUP_STEALTH= 2;
	public final static int GROUP_CRAFT = 3;
	public final static int GROUP_BODY = 4;
	public final static int GROUP_WILD = 5;
	public final static int GROUP_THEORY = 6;
	public final static int GROUP_OTHERS = 7;
	public final static int GROUP_NONE = 8;
	
	public final static int LIMITATION_COUNTS = 0;
	public final static int LIMITATION_DOESNT_COUNT = 1;
	public final static int LIMITATION_AFFINITY = 2;
	
	public final static int MINVALUE = 0;
	
	String nameStr;
//	int minValueInt;
	int valueInt;
	ArrayList<RelatedSkill> relatedSkillsArrList;		// not null, if there are related skills
	int typeInt;				// using constants TYPE_xyz
	int groupInt;				// using constants GROUP_xyz
	Specialization specialization = null;
	String note = null;
	boolean baseSkillBl = true;
	
	public boolean isBaseSkill() {
		return baseSkillBl;
	}

	public void setBaseSkill(boolean baseSkill) {
		this.baseSkillBl = baseSkill;
	}

	public String getNameStr() {
		return nameStr;
	}

	public void setNameStr(String nameStr) {
		this.nameStr = nameStr;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	public int getTypeInt() {
		return typeInt;
	}

	public void setTypeInt(int typeInt) {
		this.typeInt = typeInt;
	}

	public int getValueInt() {
		return valueInt;
	}

	public void setValueInt(int valueInt) {
		this.valueInt = valueInt;
	}

	public Skill (String name, boolean baseSkill, int value, ArrayList<RelatedSkill> relatedSkills,int type, int group)
	{
		nameStr = name;
		baseSkillBl = baseSkill;
		valueInt = value;
		relatedSkillsArrList = relatedSkills;
		typeInt = type;
		groupInt = group;
	}
	
	public Skill (String name, boolean baseSkill, int value, ArrayList<RelatedSkill> relatedSkills, int type, int group, String note)
	{
		nameStr = name;
		baseSkillBl = baseSkill;
		valueInt = value;
		relatedSkillsArrList = relatedSkills;
		typeInt = type;
		groupInt = group;
		this.note = note;
	}
	
	public Skill (String name, boolean baseSkill, int value, ArrayList<RelatedSkill> relatedSkills, int type, int group, String note, Specialization spec)
	{
		this (name, baseSkill, value, relatedSkills, type, group, note);
		this.specialization = spec;
	}


	public int getGroupInt() {
		return groupInt;
	}

	public void setGroupInt(int groupInt) {
		this.groupInt = groupInt;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public String toString()
	{
		return nameStr;
	}
	
	public Skill clone()
	{
		return new Skill(nameStr, baseSkillBl, valueInt, relatedSkillsArrList, typeInt, groupInt, note);
	}
	
	public int getCosts (int oneforRising0forDecreasing, SkillsPanel panel, boolean goodRunner, int toughness)
	{
		if (oneforRising0forDecreasing != 1 && oneforRising0forDecreasing != 0)
			return 0;
		
		int relatedSkills = 0;
		int threshold = 21;
		int relatedLangs = 0;
		
		if (relatedSkillsArrList != null && relatedSkillsArrList.size() > 0)
			threshold = relatedSkillsArrList.get(0).getThreshold();
		if (typeInt == TYPE_LANGUAGE || typeInt == TYPE_FONT)
			threshold = Constants.LANGUAGE_THRESHOLD;
		
		
		if (panel != null && relatedSkillsArrList != null  && panel.getSkillPositioners()[3] != null) 	// getCosts is called while deserializing the FightPanel for the costs preview - in the SkillPositioners' constructors
		{
			Iterator<RelatedSkill> compareStringIt = relatedSkillsArrList.iterator();
			while (compareStringIt.hasNext())
			{
				RelatedSkill compare = compareStringIt.next();
				if (relatedSkillsArrList != null)
				{
					for (int i = 0; i < 4; i++)
					{
						Iterator it = panel.getSkillPositioners()[i].getComponents().iterator();
						while (it.hasNext())
						{
							Object tok = it.next();
							if (tok instanceof SkillUnit)
							{
								SkillUnit token = (SkillUnit)tok;
								
								if (token.getSkill().getNameStr().equals(compare.getName())
										&& (	(
													oneforRising0forDecreasing == 1 ? 
													token.getSkill().getValueInt() > valueInt : 
															token.getSkill().getValueInt() >= valueInt
																&& token.getSkill() != this
												)
											)
									)
									relatedSkills++;
							}
						}
					}
				}
			}
		}
		
		if (typeInt == TYPE_LANGUAGE)
		{
			Iterator it = panel.getSkillPositioners()[3].getComponents().iterator();
			while (it.hasNext())
			{
				Object tok = it.next();
				if (tok instanceof SkillUnit)
				{
					SkillUnit token = (SkillUnit)tok;
					
					if (token.getSkill().getTypeInt() == TYPE_LANGUAGE)
						relatedLangs ++;
				}
			}
		}
			
		if (typeInt == TYPE_FONT)
		{
			Iterator it = panel.getSkillPositioners()[3].getComponents().iterator();
			while (it.hasNext())
			{
				Object tok = it.next();
				if (tok instanceof SkillUnit)
				{
					SkillUnit token = (SkillUnit)tok;
					
					if (token.getSkill().getTypeInt() == TYPE_FONT)
						relatedLangs ++;
				}
			}
		}
		
		switch (typeInt) {
		case TYPE_STAMINA:
			if (valueInt + oneforRising0forDecreasing < 6)
				return (goodRunner && valueInt <= toughness ? 3:6);
			if (valueInt + oneforRising0forDecreasing < 11)
				return (goodRunner && valueInt <= toughness ? 4:8);
			if (valueInt + oneforRising0forDecreasing < 16)
				return (goodRunner && valueInt <= toughness ? 5:10);
			return (goodRunner && valueInt <= toughness ? 6:12);
		case TYPE_NORMAL: 
			if (valueInt + oneforRising0forDecreasing < 6)
				return relatedSkills >= threshold ?3:6;
			if (valueInt + oneforRising0forDecreasing < 11)
				return relatedSkills >= threshold ?4:8;
			if (valueInt + oneforRising0forDecreasing < 16)
				return relatedSkills >= threshold ?5:10;
			return relatedSkills >= threshold ?6:12;
		case Skill.TYPE_THEORY:
			if (valueInt + oneforRising0forDecreasing < 6)
				return relatedSkills >= threshold ?2:3;
			if (valueInt + oneforRising0forDecreasing < 11)
				return relatedSkills >= threshold ?2:4;
			if (valueInt + oneforRising0forDecreasing < 16)
				return relatedSkills >= threshold ?3:5;
			return relatedSkills >= threshold ?3:6;
		case Skill.TYPE_LANGUAGE:
			int costs = 8;
			if (panel.getFather().getSInf().isLanguageArea())
				costs /= 2;
			if (relatedSkills > 0 || relatedLangs >= threshold)
				costs /= 2;	// related languages
			return costs;
					
		case Skill.TYPE_FONT:
			costs = 8;
			if (relatedLangs > 0)		// the first is 8, others 4
				costs /= 2;
			if (relatedSkills > 1 || relatedLangs >= threshold)
				costs /= 2;
			return costs;
//		case Skill.TYPE_FIGHT: return (relatedSkills >= threshold ?4:7);
//		case Skill.TYPE_COMPLEX: return (relatedSkills >= threshold ?18:35);
		case Skill.TYPE_COMPLEX: costs = 15+(valueInt + oneforRising0forDecreasing)*3; 
			return (relatedSkills >= threshold ?(costs+1)/2:costs);
		case Skill.TYPE_100EP: return (relatedSkills >= threshold ?50:100);
		case Skill.TYPE_LIMITATION: return -10;
		}
		return 0;
	}

	public ArrayList<RelatedSkill> getRelatedSkillsArrList() {
		return relatedSkillsArrList;
	}

	public boolean isBaseSkillBl() {
		return baseSkillBl;
	}

	public void setRelatedSkillsArrList(ArrayList<RelatedSkill> relatedSkillsArrList) {
		this.relatedSkillsArrList = relatedSkillsArrList;
	}

	public void setBaseSkillBl(boolean baseSkillBl) {
		this.baseSkillBl = baseSkillBl;
	}
}
