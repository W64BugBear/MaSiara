package character;

import java.io.Serializable;

public class RaceModifiers 
	implements Serializable
{
	public boolean isGoodRunners() {
		return goodRunners;
	}


	String nameStr;
	
	int mods[] = new int[23];
	boolean goodRunners = false;		// some races can skill stamina cheaper
	
	public RaceModifiers(String name, int[] mods, boolean goodRunners)
	{
		nameStr = name;
		
		this.mods = mods;
		this.goodRunners = goodRunners;
	}
	
	public RaceModifiers(String name, 
			int appearance, int dexterity, int agility, int impressivity, int strength, int swiftness, int toughness,
			int charisma, int empathy, int intuition, int intelligence, int bravery, int selfcontrol, int soulpower,
			int daysight, int nightsight, int hearing, int smelling, int tasting, int feeling, 
			int projectileWeapons, int throwingWeapons, int magic,
			boolean goodRunners)
	{
		nameStr = name;
		
		mods[ 0] = appearance;
		mods[ 1] = dexterity;
		mods[ 2] = agility;
		mods[ 3] = impressivity;
		mods[ 4] = strength;
		mods[ 5] = swiftness;
		mods[ 6] = toughness;
		
		mods[ 7] = charisma;
		mods[ 8] = empathy;
		mods[ 9] = intuition;
		mods[10] = intelligence;
		mods[11] = soulpower;
		mods[12] = bravery;
		mods[13] = selfcontrol;
		
		mods[14] = daysight;
		mods[15] = nightsight;
		mods[16] = hearing;
		mods[17] = smelling;
		mods[18] = tasting;
		mods[19] = feeling;
		
		mods[20] = projectileWeapons;
		mods[21] = throwingWeapons;
		mods[22] = magic;
		
		this.goodRunners = goodRunners;
	}

	public int[] getMods() {
		return mods;
	}

	
	public void setMods(int[] mods) {
		this.mods = mods;
	}


	public String getNameStr() {
		return nameStr;
	}


	public void setNameStr(String nameStr) {
		this.nameStr = nameStr;
	}
}
