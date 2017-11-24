package character;

import java.util.LinkedList;

public class Style {

	public static final int ARMED_MELEE_STYLE = 1;
	public static final int UNARMED_MELEE_STYLE = 2;
	public static final int ARTILLARY_STYLE = 3;
	public static final int WIZARD_STYLE = 4;
	public static final int META_STYLE = 5;	// offensive, defensive, range weapons
	
	public static final boolean OFFENSIVE = true;
	public static final boolean DEFENSIVE = false;
	
	public static final boolean DAGGERS = true;
	public static final boolean NODAGGERS = false;
	
	String name;
	int type;
	AdvantageFamily[] advantages;
	boolean offensiveOrDaggers = false;
	
	LinkedList<String> fittingStylesStrLl = null;
	
	LinkedList<Integer> notAvailableRangedWeapons = null;
	
	String specialRules = null;
	
	int atdeMod = 0, initMod = 0, damMod = 0; 
	
	public int getAtdeMod() {
		return atdeMod;
	}

	public int getDamMod() {
		return damMod;
	}

	public int getInitMod() {
		return initMod;
	}

	public void setAtdeMod(int atdeMod) {
		this.atdeMod = atdeMod;
	}

	public void setDamMod(int damMod) {
		this.damMod = damMod;
	}

	public void setInitMod(int initMod) {
		this.initMod = initMod;
	}

	public Style (String name, int type, AdvantageFamily[] advantages)
	{
		this.name = name;
		this.type = type;
		this.advantages = advantages;
	}
	
	public Style (String name, int type, AdvantageFamily[] advantages, String specialRules)
	{
		this(name, type, advantages);
		this.specialRules = specialRules;
	}
	
	public LinkedList<String> getFittingStylesStrLl() {
		return fittingStylesStrLl;
	}

	public void setFittingStylesStrLl(LinkedList<String> fittingStylesStrLl) {
		this.fittingStylesStrLl = fittingStylesStrLl;
	}

	public Style (String name, int type, AdvantageFamily[] advantages, boolean offensiveOrDaggers, LinkedList<String> fittingStylesStrLl)
	{
		this (name, type, advantages);
		this.offensiveOrDaggers = offensiveOrDaggers;
		this.fittingStylesStrLl = fittingStylesStrLl;
		this.notAvailableRangedWeapons = null;
	}
	
	public Style (String name, int type, AdvantageFamily[] advantages, boolean offensiveOrDaggers, LinkedList<String> fittingStylesStrLl, String specialRules)
	{
		this (name, type, advantages, offensiveOrDaggers, fittingStylesStrLl);
		this.specialRules = specialRules;
	}
	
	public Style (String name, int type, AdvantageFamily[] advantages, boolean offensiveOrDaggers, LinkedList<String> fittingStylesStrLl, String specialRules, int atdeMod, int initMod, int damMod)
	{
		this (name, type, advantages, offensiveOrDaggers, fittingStylesStrLl, specialRules);
		this.atdeMod = atdeMod;
		this.damMod = damMod;
		this.initMod = initMod;
	}
	
	public Style (String name, int type, AdvantageFamily[] advantages, LinkedList<Integer> notAvailableRangeWeapons)
	{
		this (name, type, advantages);
		this.fittingStylesStrLl = null;
		this.notAvailableRangedWeapons = notAvailableRangeWeapons;
	}

	public String toString()
	{
		return name;
	}

	public AdvantageFamily[] getAdvantages() {
		return advantages;
	}

	public String getName() {
		return name;
	}

	public boolean isOffensiveOrDaggers() {
		return offensiveOrDaggers;
	}

	public int getType() {
		return type;
	}

	public LinkedList<Integer> getNotAvailableRangedWeapons() {
		return notAvailableRangedWeapons;
	}

	public void setNotAvailableRangedWeapons(
			LinkedList<Integer> notAvailableRangedWeapons) {
		this.notAvailableRangedWeapons = notAvailableRangedWeapons;
	}

	public String getSpecialRules() {
		return specialRules;
	}

	public void setSpecialRules(String specialRules) {
		this.specialRules = specialRules;
	}
}
