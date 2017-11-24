package character;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

import dataclasses.SkillLists;

import races.Dwarves;
import races.Race;
import background.Constants;

public class Hero 
	implements Serializable
{
	public static final int SEX_MALE = 0;
	public static final int SEX_FEMALE = 1;
	
	
	// most important
//	int version = Constants.FILEVERSION;
	
	String name;
	Race race = new Dwarves();
	boolean isVampire = false, isWerewolf = false, isEfreet = false;
	int sex = 0;
	
	public Property[] getProperties() {
		return properties;
	}

	// Properties
	Property properties[] = new Property[14];
	int ownModifiers[] = new int[14];		// e. g. God gifts, vampire age mods, ...
	Property senses[] = new Property[6];
	int ownSenseMods[] = new int[6];

	public static final int PROP_APPEARANCE = 0;
	public static final int PROP_DEXTERITY = 1;
	public static final int PROP_AGILITY = 2;
	public static final int PROP_IMPOSANCE = 3;
	public static final int PROP_STRENGTH = 4;
	public static final int PROP_SPEED = 5;
	public static final int PROP_TOUGHNESS = 6;
	public static final int PROP_CHARISMA = 7;
	public static final int PROP_EMPATHY = 8;
	public static final int PROP_INTELLIGENCE = 9;
	public static final int PROP_INTUITION = 10;
	public static final int PROP_COURAGE = 11;
	public static final int PROP_SOULFORCE = 12;
	public static final int PROP_SELFCONTROL = 13;

	int freeRaceModifierIndex = -1; 		// e.g. Eardon's house
	
	// specials
	public static final int NOB_BASEBORN = 0;
	public static final int NOB_NOBLE = 1;
	public static final int NOB_HIGH_NOBILITY = 2;
	
	int nobility = NOB_BASEBORN;
	int wealthy = 0;

	// Skills
	LinkedList<Skill> basicSkillsLl = null;
	LinkedList<Skill> specialSkillsLl = null;
	LinkedList<Integer> fightingSkillsLl = null;
	LinkedList<Integer> magesSkillsLl = null;
	LinkedList<Skill> changerSkillsLl = null;
	LinkedList<Skill> elementalDruidSkillsLl = null;
	LinkedList<Skill> controllerDruidSkillsLl = null;
	LinkedList<Skill> priestSkillsLl = null;
	LinkedList<Skill> templeKnightSkillsLl = null;
	LinkedList<Skill> shamanSkillsLl = null;
	LinkedList<Skill> orelierSkillsLl = null;
	LinkedList<Skill> intuitiveMagicSkillsLl = null;
	
	// magic
	
	boolean[] magic;	
	String[] magicBranchTitles;
	String[] magicBranchPaths;
	
	// Equipment
	LinkedList<Item> itemsLl = null;
//	LinkedList weaponsLl = null;
//	LinkedList armorLl = null;
		
	// Phoenix Feather
	boolean phoenixFeatherBool = true;

	// Specials
	String specialsStr= null;
	
	public Hero ()
	{
		properties[0] = new Property("Aussehen", 10, 6, 25);
		properties[1] = new Property("Geschick", 10, 6, 25);
		properties[2] = new Property("Gewandtheit", 10, 6, 25);
		properties[3] = new Property("Imposanz", 10, 6, 25);
		properties[4] = new Property("Kraft", 10, 6, 25);
		properties[5] = new Property("Schnelligkeit", 10, 6, 25);
		properties[6] = new Property("Zähigkeit", 10, 6, 25);
		
		properties[7] = new Property("Charisma", 10, 6, 25);
		properties[8] = new Property("Empathie", 10, 6, 25);
		properties[9] = new Property("Intelligenz", 10, 6, 25);
		properties[10] = new Property("Intuition", 10, 6, 25);
		properties[11]= new Property("Mut", 10, 6, 25);
		properties[12] = new Property("Seelenkraft", 10, 6, 25);
		properties[13]= new Property("Selbstbeherrschung", 10, 6, 25);
		
		senses[0] = new Property("Tagsicht", 10, 6, 25);
		senses[1] = new Property("Nachtsicht", 10, 6, 25);
		senses[2] = new Property("Hören", 10, 6, 25);
		senses[3] = new Property("Riechen", 10, 6, 25);
		senses[4] = new Property("Schmecken", 10, 6, 25);
		senses[5] = new Property("Tasten", 10, 6, 25);
		
		itemsLl = new LinkedList<Item>();
//		armorLl = new LinkedList();
//		weaponsLl = new LinkedList();
		
		basicSkillsLl = new LinkedList<Skill>();
		specialSkillsLl = new LinkedList<Skill>();
		
		fightingSkillsLl = new LinkedList<Integer>();
		for (int i = 0; i < 16; i++)	// fsv, rwfsv, (complex and normal) of offensive/defensive, armed, unarmed, rangeWeapons, 3*artillary
			fightingSkillsLl.add(new Integer(0));
		
		magesSkillsLl = new LinkedList<Integer>();
		magesSkillsLl.add(new Integer(0));	// spellforce
		magesSkillsLl.add(new Integer(0));	// spellcontrol ... or the other way round
		magesSkillsLl.add(new Integer(0));	// scrollbar
		
		for (int i = 0; i < SkillLists.SPELLSKILLS+SkillLists.ARCHMAGESKILLS; i++)
			magesSkillsLl.add(new Integer(-2));
		
		magic = new boolean[Constants.MAGICBRANCHES];
		for (int i = 0; i < Constants.MAGICBRANCHES; i++)
		{
			magic[i] = false;
		}
		
		magicBranchTitles = new String[Constants.MAGICBRANCHES+1];
		magicBranchPaths= new String[Constants.MAGICBRANCHES+1];
		
		magicBranchTitles[0] = "nichtmagiebegabt";
		magicBranchTitles[1] = "Hexerling";
		magicBranchTitles[2] = "Hexer";
		magicBranchTitles[3] = "Magier";
		magicBranchTitles[4] = "Großmagier";
		
		magicBranchTitles[5] = "Freizauberer";
		magicBranchTitles[6] = "Empath";
		magicBranchTitles[7] = "Formwandler";
		
		magicBranchTitles[8] = "Teufelskind";
		magicBranchTitles[9] = "Gestaltwandler";
		magicBranchTitles[10] = "Tierherr";
		magicBranchTitles[11] = "Pflanzenherr";
		magicBranchTitles[12] = "Elementarist";
		
		magicBranchTitles[13] = "Schamane";
		magicBranchTitles[14] = "Orelier";
		magicBranchTitles[15] = "Hexe";
		magicBranchTitles[16] = "Schettene";
		magicBranchTitles[17] = "Berserker";
		
		
		magicBranchPaths[0] = "berserk";
		magicBranchPaths[1] = "grandmagician";
		magicBranchPaths[2] = "magician";
		magicBranchPaths[3] = "magesorc";
		magicBranchPaths[4] = "intsorc";
		magicBranchPaths[5] = "freewiz";
		magicBranchPaths[6] = "empathe";
		
		magicBranchPaths[7] = "shapech";
		magicBranchPaths[8] = "devilsch";
		magicBranchPaths[9] = "bodych";
		magicBranchPaths[10] = "beastmaster";
		magicBranchPaths[11] = "plantmaster";
		magicBranchPaths[12] = "element";
		
		magicBranchPaths[13] = "shaman";
		magicBranchPaths[14] = "orelian";
		magicBranchPaths[15] = "witch";
		magicBranchPaths[16] = "shattan";
		magicBranchPaths[17] = "berserk";
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	
	public boolean isEfreet() {
		return isEfreet;
	}

	public void setEfreet(boolean isEfreet) {
		this.isEfreet = isEfreet;
	}

	public LinkedList<Item> getItemsLl() {
		return itemsLl;
	}

	public boolean isPhoenixFeatherBool() {
		return phoenixFeatherBool;
	}

	public void setPhoenixFeatherBool(boolean phoenixFeatherBool) {
		this.phoenixFeatherBool = phoenixFeatherBool;
	}
	public LinkedList<Skill> getBasicSkillsLl() {
		System.out.println(basicSkillsLl.size());
		return basicSkillsLl;
	}

	public LinkedList<Skill> getSpecialSkillsLl() {
		return specialSkillsLl;
	}

	public LinkedList<Integer> getFightingSkillsLl() {
		return fightingSkillsLl;
	}

	public LinkedList<Integer> getMagesSkillsLl() {
		return magesSkillsLl;
	}

	public LinkedList<Skill> getChangerSkillsLl() {
		return changerSkillsLl;
	}

	public LinkedList<Skill> getElementalDruidSkillsLl() {
		return elementalDruidSkillsLl;
	}

	public LinkedList<Skill> getControllerDruidSkillsLl() {
		return controllerDruidSkillsLl;
	}

	public LinkedList<Skill> getPriestSkillsLl() {
		return priestSkillsLl;
	}

	public LinkedList<Skill> getTempleKnightSkillsLl() {
		return templeKnightSkillsLl;
	}

	public LinkedList<Skill> getShamanSkillsLl() {
		return shamanSkillsLl;
	}

	public LinkedList<Skill> getOrelierSkillsLl() {
		return orelierSkillsLl;
	}

	public LinkedList<Skill> getIntuitiveMagicSkillsLl() {
		return intuitiveMagicSkillsLl;
	}

	public void setBasicSkillsLl(LinkedList<Skill> basicSkillsLl) {
		this.basicSkillsLl = basicSkillsLl;
	}

	public void setSpecialSkillsLl(LinkedList<Skill> specialSkillsLl) {
		this.specialSkillsLl = specialSkillsLl;
	}

	public void setFightingSkillsLl(LinkedList<Integer> fightingSkillsLl) {
		this.fightingSkillsLl = fightingSkillsLl;
	}

	public void setMagesSkillsLl(LinkedList<Integer> magesSkillsLl) {
		this.magesSkillsLl = magesSkillsLl;
	}

	public void setChangerSkillsLl(LinkedList<Skill> changerSkillsLl) {
		this.changerSkillsLl = changerSkillsLl;
	}

	public void setElementalDruidSkillsLl(LinkedList<Skill> elementalDruidSkillsLl) {
		this.elementalDruidSkillsLl = elementalDruidSkillsLl;
	}

	public void setControllerDruidSkillsLl(LinkedList<Skill> controllerDruidSkillsLl) {
		this.controllerDruidSkillsLl = controllerDruidSkillsLl;
	}

	public void setPriestSkillsLl(LinkedList<Skill> priestSkillsLl) {
		this.priestSkillsLl = priestSkillsLl;
	}

	public void setTempleKnightSkillsLl(LinkedList<Skill> templeKnightSkillsLl) {
		this.templeKnightSkillsLl = templeKnightSkillsLl;
	}

	public void setShamanSkillsLl(LinkedList<Skill> shamanSkillsLl) {
		this.shamanSkillsLl = shamanSkillsLl;
	}

	public void setOrelierSkillsLl(LinkedList<Skill> orelierSkillsLl) {
		this.orelierSkillsLl = orelierSkillsLl;
	}

	public void setIntuitiveMagicSkillsLl(LinkedList<Skill> intuitiveMagicSkillsLl) {
		this.intuitiveMagicSkillsLl = intuitiveMagicSkillsLl;
	}

	public void setItemsLl(LinkedList<Item> itemsLl) {
		this.itemsLl = itemsLl;
	}

	public Property getProperty (String name) {
		for (int i = 0; i < 14; i++)
			if (properties[i].getNameString().equals(name))
				return properties[i];
		
		for (int i = 0; i < 6; i++)
			if (senses[i].getNameString().equals(name))
				return senses[i];
		return null;
	}
	
	public Skill getSkill (String name, String note) {
		Iterator<Skill> it = basicSkillsLl.iterator();
		while (it.hasNext())
		{
			Skill skill = it.next();
			
			if (skill.getNameStr().equals(name) && skill.getNote().equals(note))
				return skill;
		}
		
		it = specialSkillsLl.iterator();
		while (it.hasNext())
		{
			Skill skill = it.next();
			if (skill.getNameStr().equals(name))
				return skill;
		}
		return null;
	}
	
	public void setProperties(Property[] properties) {
		this.properties = properties;
	}

	public int[] getOwnModifiers() {
		return ownModifiers;
	}

	public void setOwnModifiers(int[] ownModifiers) {
		this.ownModifiers = ownModifiers;
	}

	public Property[] getSenses() {
		return senses;
	}

	public void setSenses(Property[] senses) {
		this.senses = senses;
	}

	public int[] getOwnSenseMods() {
		return ownSenseMods;
	}

	public void setOwnSenseMods(int[] ownSenseMods) {
		this.ownSenseMods = ownSenseMods;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}
	
	public RaceModifiers getRaceModifiersByName (String name)
	{
		Iterator<RaceModifiers> it = race.getRaceModifiers().iterator();
		while (it.hasNext())
		{
			RaceModifiers rm = it.next();
			if (rm.getNameStr().equals(name))
				return rm;
		}
		return null;
	}

	public String getSpecialsStr() {
		return specialsStr;
	}

	public void setSpecialsStr(String specialsStr) {
		this.specialsStr = specialsStr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean skillAlreadyExists(Skill skill)
	{
		Iterator<Skill> it = specialSkillsLl.iterator();
		while (it.hasNext())
		{
			Skill next = it.next();
			if (next.getNameStr().equals(skill.getNameStr()) && next.getValueInt() == skill.getValueInt() && ((next.getNote() == null && skill.getNote() == null) || (next.getNote() != null && skill.getNote() != null && skill.getNote().equals(skill.getNote()))))
				return true;
		}
		return false;
	}

	public boolean similarSkillAlreadyExists(Skill skill)
	{
		Iterator<Skill> it = specialSkillsLl.iterator();
		while (it.hasNext())
		{
			Skill next = it.next();
			if (next.getNameStr().equals(skill.getNameStr()) && ((next.getNote() == null && skill.getNote() == null) || (next.getNote() != null && skill.getNote() != null && skill.getNote().equals(skill.getNote()))))
				return true;
		}
		return false;
	}

	
	public boolean[] getMagic() {
		return magic;
	}

	public String[] getMagicBranchPaths() {
		return magicBranchPaths;
	}

	public String[] getMagicBranchTitles() {
		return magicBranchTitles;
	}

	public void setMagic(boolean[] magic) {
		this.magic = magic;
	}

	public void setMagicBranchPaths(String[] magicBranchPaths) {
		this.magicBranchPaths = magicBranchPaths;
	}

	public void setMagicBranchTitles(String[] magicBranchTitles) {
		this.magicBranchTitles = magicBranchTitles;
	}

	public boolean isWerewolf() {
		return isWerewolf;
	}

	public void setWerewolf(boolean isWerewolf) {
		this.isWerewolf = isWerewolf;
	}

	public boolean isVampire() {
		return isVampire;
	}

	public void setVampire(boolean isVampire) {
		this.isVampire = isVampire;
	}
	
	public int getNobility() {
		return nobility;
	}

	public int getWealthy() {
		return wealthy;
	}

	public void setNobility(int nobility) {
		this.nobility = nobility;
	}

	public void setWealthy(int wealthy) {
		this.wealthy = wealthy;
	}
	
//	public int getVersion() {
//		return version;
//	}
//
//	public void setVersion(int version) {
//		this.version = version;
//	}

	public int getFreeRaceModifierIndex() {
		return freeRaceModifierIndex;
	}

	public void setFreeRaceModifierIndex(int freeRaceModifierIndex) {
		this.freeRaceModifierIndex = freeRaceModifierIndex;
	}

}
