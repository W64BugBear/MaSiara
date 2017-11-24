package background;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import races.Race;
import character.Hero;
import character.Item;
import character.Property;
import character.RaceModifiers;
import character.Skill;
import character.Specialization;
import dataclasses.RelatedSkill;

public abstract class Parser {
	
	public static String parseSInf (SerializableInformation sinf) {
		return sinf.getVersion() + ";" +
				(sinf.masterMode?1:0) + ";" + 
				(sinf.languageArea?1:0) + ";" + 
				sinf.epAmountInt  + ";" + 
				sinf.epInt + ";" + 
				sinf.limitationsLeftInt + ";" + 
				sinf.gpInvestedInSpecials + ";" + 
				sinf.gpInvestedInMoney + ";" + 
				(sinf.cheapPriceForAdvantages?1:0) + ";" 
				+ sinf.getFreeArchmagicField() + ";";
	}
	
	public static SerializableInformation deparseSInf (String s, int version) {
		StringTokenizer t = new StringTokenizer(s, ";");
		
		SerializableInformation sinf = new SerializableInformation();

		sinf.setVersion(Integer.parseInt(t.nextToken()));
		sinf.setMasterMode(t.nextToken().equals("1"));
		sinf.setLanguageArea(t.nextToken().equals("1"));
		sinf.setEpAmountInt(Integer.parseInt(t.nextToken()));
		sinf.setEpInt(Integer.parseInt(t.nextToken()));
		sinf.setLimitationsLeftInt(Integer.parseInt(t.nextToken()));
		sinf.setGpInvestedInSpecials(Integer.parseInt(t.nextToken()));
		sinf.setGpInvestedInMoney(Integer.parseInt(t.nextToken()));
		sinf.setCheapPriceForAdvantages(t.nextToken().equals("1"));
		sinf.setFreeArchmagicField(Integer.parseInt(t.nextToken()));
		
		return sinf;
	}
	
	public static String parseSkill (Skill skill) {
		String ret = skill.getNameStr()+ ";" + skill.getValueInt() + ";";
		try {
			ret += skill.getRelatedSkillsArrList().size() + ";";
			for (RelatedSkill rs : skill.getRelatedSkillsArrList())
				ret += rs.getName() + ";" + rs.getThreshold() + ";";
		} catch (NullPointerException npe)
		{
			ret += "0;";
		}
		
		ret += skill.getTypeInt() + ";" + skill.getGroupInt() + ";";
		if (skill.getSpecialization() == null)
			ret += "<>;";
		else
			ret += skill.getSpecialization().getNameStr() + ";" + skill.getSpecialization().getValueInt() + ";";
		
		if (skill.getNote() == null)
			ret += "<>;";
		else
			ret += skill.getNote() + ";";
		
		ret += (skill.isBaseSkill()?1:0);
		
		return ret;
	}
	
	public static Skill deparseSkill (int version, SerializableInformation sInf, StringTokenizer t, boolean isGoodRunner, int toughness) {
		String name; 
		boolean baseSkill; 
		int value; 
		ArrayList<RelatedSkill> relatedSkills = new ArrayList<RelatedSkill>();
		int type;
		int group; 
		String note;
		Specialization spec;
		
		name = t.nextToken();
		value = Integer.parseInt(t.nextToken());
		int size = Integer.parseInt(t.nextToken());
		for (int i = 0; i < size; i++)
			relatedSkills.add(new RelatedSkill(t.nextToken(), Integer.parseInt(t.nextToken())));
		type = Integer.parseInt(t.nextToken());
		group = Integer.parseInt(t.nextToken());
		String parse = t.nextToken();
		if (parse.equals("<>"))
			spec = null;
		else
			spec = new Specialization(parse, Integer.parseInt(t.nextToken()));
		
		parse = t.nextToken();
		if (parse.equals("<>"))
			note = null;
		else
			note = parse;
		
		if (t.nextToken().equals("1"))
			baseSkill = true;
		else
			baseSkill = false;
		
		Skill ret = new Skill(name, baseSkill, value, relatedSkills, type, group, note, spec);
		
		if (version < 3)
			if (name.equals("Akrobatik") || name.equals("Religionen"))
			{
				while (ret.getValueInt() > 0)
				{
					sInf.increaseEP(ret.getCosts(0, null, false, 0));
					ret.setValueInt(ret.getValueInt()-1);
					System.out.println(name + ", EP: " + sInf.getEpInt() + "/" + sInf.getEpAmountInt());
				}
				return null;
			}
		
		if (version < 5)
			if (name.equals("Ausdauer") && isGoodRunner)
			{
				int epreturn = 0;
				for (int i = 0; i < ret.getValueInt(); i++)
				{
					if (ret.getValueInt() <= toughness)
					{
						if (i < 6)
							epreturn += 3;
						else
							if (i < 11)
								epreturn += 4;
							else
								if (i < 16)
									epreturn += 5;
								else
									epreturn +=6;
					}
				}
				sInf.increaseEP(epreturn);
				ret.setTypeInt(Skill.TYPE_STAMINA);
			}
		
		if (version < 6)
			if (name.equals("Reparaturen"))
			{
				int epreturn = 0;
				for (int i = 0; i < ret.getValueInt(); i++)
				{
					if (i < 6)
						epreturn += 6;
					else
						if (i < 11)
							epreturn += 8;
						else
							if (i < 16)
								epreturn += 10;
							else
								epreturn +=12;
				}
				sInf.increaseEP(epreturn);
				return null;
			}
		
		if (version < 7)
		{
			if (name.equals("Kunst") || name.equals("Juristik"))
			{
				int epreturn = 0;
				for (int i = 0; i < ret.getValueInt(); i++)
				{
					if (i < 6)
						epreturn += 3;
					else
						if (i < 11)
							epreturn += 4;
						else
							if (i < 16)
								epreturn += 5;
							else
								epreturn +=6;
				}
				sInf.increaseEP(epreturn);
				return null;
			}

			if (name.equals("Mechanik"))
				ret.setNameStr("Politik");
			
			if (name.equals("Fallen Bauen"))
			{
				ret.setNameStr("Mechanik");
				ret.setGroupInt(Skill.GROUP_CRAFT);
			}
		}
		return ret;
		
	}

	public static String parseSkillList (LinkedList<Skill> skills) {
		if (skills == null)
			return "0;";
		String ret = skills.size() + ";";
		for (Skill skill : skills)
			ret += parseSkill(skill) + ";";
		return ret;
	}
	
	public static void deparseSkillList (int version, SerializableInformation sInf, StringTokenizer t, LinkedList<Skill> skills, boolean isGoodRunner, int toughness) {
		int size = Integer.parseInt(t.nextToken());
		
		for (int i = 0; i < size; i++)
		{
			Skill skill = deparseSkill(version, sInf, t, isGoodRunner, toughness);
			
			if (skill != null)
				skills.add(skill);
		}
	}
	
	public static String parseHero (Hero hero) {
		String ret = new String();
		
		ret +=	hero.getName() + ";" +
				hero.getRace().serializeYourself() +
				(hero.isVampire()?1:0) + ";" + 
				(hero.isWerewolf()?1:0) + ";" + 
				(hero.isEfreet()?1:0) + ";" +
				hero.getSex() + ";";
		
		for (int i = 0; i < 14; i++)
			ret += hero.getProperties()[i].getNameString() + ";" + hero.getProperties()[i].getValueInt() + ";" + hero.getProperties()[i].getMinimumInt()+ ";" + hero.getProperties()[i].getMaximumInt()+ ";";
		for (int i = 0; i < 14; i++)
			ret += hero.getOwnModifiers()[i] + ";";
		for (int i = 0; i <  6; i++)
			ret += hero.getSenses()[i].getNameString() + ";" + hero.getSenses()[i].getValueInt() + ";" + hero.getSenses()[i].getMinimumInt()+ ";" + hero.getSenses()[i].getMaximumInt()+ ";";
		for (int i = 0; i <  6; i++)
			ret += hero.getOwnSenseMods()[i] + ";";
		
		ret += hero.getNobility() + ";"
				+ hero.getWealthy() + ";";
		
		ret += parseSkillList(hero.getBasicSkillsLl());
		ret += parseSkillList(hero.getSpecialSkillsLl());
		ret += hero.getFightingSkillsLl().size() + ";";
		for (Integer i : hero.getFightingSkillsLl())
			ret += i + ";";
		ret += hero.getMagesSkillsLl().size() + ";";
		for (Integer i : hero.getMagesSkillsLl())
			ret += i + ";";
		
		ret += parseSkillList(hero.getChangerSkillsLl());
		ret += parseSkillList(hero.getElementalDruidSkillsLl());
		ret += parseSkillList(hero.getControllerDruidSkillsLl());
		ret += parseSkillList(hero.getPriestSkillsLl());
		
		ret += parseSkillList(hero.getTempleKnightSkillsLl());
		ret += parseSkillList(hero.getShamanSkillsLl());
		ret += parseSkillList(hero.getOrelierSkillsLl());
		ret += parseSkillList(hero.getIntuitiveMagicSkillsLl());
		
		ret += hero.getMagic().length + ";";
		for (int i = 0; i < hero.getMagic().length; i++)
			ret += (hero.getMagic()[i]?1:0) + ";";
		
		ret += hero.getMagicBranchTitles().length + ";";
		for (int i = 0; i < hero.getMagicBranchTitles().length; i++)
			ret += hero.getMagicBranchTitles()[i] + ";";
		
		ret += hero.getMagicBranchPaths().length + ";";
		for (int i = 0; i < hero.getMagicBranchPaths().length; i++)
			ret += hero.getMagicBranchPaths()[i] + ";";
		
		ret += hero.getItemsLl().size() + ";";
		for (Item item : hero.getItemsLl())
			ret += item.getNameStr() + ";" + item.getworthDouble() + ";";
		
		ret += (hero.isPhoenixFeatherBool()?1:0) + ";";
		
		if (hero.getSpecialsStr().equals(""))
			ret += " ;";
		else
			ret += hero.getSpecialsStr() + ";";
		
		ret += hero.getFreeRaceModifierIndex() + ";";
		
		return ret;
	}
	
	public static Hero deparseHero (String s, int version, SerializableInformation sInf) {
		StringTokenizer t = new StringTokenizer(s, ";");
		
		Hero hero = new Hero();
		
//		hero.setVersion(Integer.parseInt(t.nextToken()));
		String puffer = t.nextToken();		// in the first versions, the version id was stored in the hero file
		try {
			Integer.parseInt(puffer);		// if this is parseable, it is the version id. Thus, we skip it
			puffer = t.nextToken();			// and retrieve the name
		} catch (NumberFormatException npe) 
		{
			// everything is in order
		}
		hero.setName(puffer);
		hero.setRace(Race.getRaceByName(t.nextToken()));
		hero.getRace().deserializeYourself(t);
		if (t.nextToken().equals("1"))
			hero.setVampire(true);
		if (t.nextToken().equals("1"))
			hero.setWerewolf(true);
		if (t.nextToken().equals("1"))
			hero.setEfreet(true);
		
		hero.setSex(Integer.parseInt(t.nextToken()));
		
		Property[] properties = new Property[14];
		for (int i = 0; i < 14; i++)
			properties[i] = new Property(t.nextToken(), Integer.parseInt(t.nextToken()), Integer.parseInt(t.nextToken()), Integer.parseInt(t.nextToken()));
		int[] ownMods = new int[14];
		for (int i = 0; i < 14; i++)
			ownMods[i] = Integer.parseInt(t.nextToken());
		Property[] senses = new Property[6];
		for (int i = 0; i <  6; i++)
			senses[i] = new Property(t.nextToken(), Integer.parseInt(t.nextToken()), Integer.parseInt(t.nextToken()), Integer.parseInt(t.nextToken()));
		int[] ownModsS = new int[6];
		for (int i = 0; i <  6; i++)
			ownModsS[i] = Integer.parseInt(t.nextToken());
		
		if (version < 4) 		// in older versions, minimum and maximum were swapped in order; also, the maximum for properties has been raised to 25 (normally)
		{
			int swapper = 0;
			for (Property p : properties)
			{
				swapper = p.getMinimumInt();
				p.setMinimumInt(p.getMaximumInt());
				p.setMaximumInt(swapper+5);
				
			}
			for (Property p : senses)
			{
				swapper = p.getMinimumInt();
				p.setMinimumInt(p.getMaximumInt());
				p.setMaximumInt(swapper+5);
			}
		}
		
		hero.setProperties(properties);
		hero.setOwnModifiers(ownMods);
		hero.setSenses(senses);
		hero.setOwnSenseMods(ownModsS);
		
		hero.setNobility(Integer.parseInt(t.nextToken()));
		hero.setWealthy(Integer.parseInt(t.nextToken()));

		boolean goodRunner = ((RaceModifiers)(hero.getRace().getRaceModifiers().getFirst())).isGoodRunners();
		int toughness = hero.getProperties()[Hero.PROP_TOUGHNESS].getValueInt();
		
		deparseSkillList(version, sInf, t, hero.getBasicSkillsLl(), goodRunner, toughness);
		deparseSkillList(version, sInf, t, hero.getSpecialSkillsLl(), goodRunner, toughness);

		int size = Integer.parseInt(t.nextToken());
		for (int i = 0; i < size; i++)
			hero.getFightingSkillsLl().set(i, new Integer(t.nextToken()));
		
		size = Integer.parseInt(t.nextToken());
		for (int i = 0; i < size; i++)
			hero.getMagesSkillsLl().set(i, new Integer(t.nextToken()));
		
		deparseSkillList(version, sInf, t, hero.getChangerSkillsLl(), goodRunner, toughness);
		deparseSkillList(version, sInf, t, hero.getElementalDruidSkillsLl(), goodRunner, toughness);
		deparseSkillList(version, sInf, t, hero.getControllerDruidSkillsLl(), goodRunner, toughness);
		deparseSkillList(version, sInf, t, hero.getPriestSkillsLl(), goodRunner, toughness);
		
		deparseSkillList(version, sInf, t, hero.getTempleKnightSkillsLl(), goodRunner, toughness);
		deparseSkillList(version, sInf, t, hero.getShamanSkillsLl(), goodRunner, toughness);
		deparseSkillList(version, sInf, t, hero.getOrelierSkillsLl(), goodRunner, toughness);
		deparseSkillList(version, sInf, t, hero.getIntuitiveMagicSkillsLl(), goodRunner, toughness);
		
		size = Integer.parseInt(t.nextToken());
		boolean[] magic = new boolean[size];
		for (int i = 0; i < size; i++)
			if (t.nextToken().equals("1"))
				magic[i] = true;
			else
				magic[i] = false;
		hero.setMagic(magic);
		
		size = Integer.parseInt(t.nextToken());
		for (int i = 0; i < size; i++)
			hero.getMagicBranchTitles()[i] = t.nextToken();
		
		size = Integer.parseInt(t.nextToken());
		for (int i = 0; i < size; i++)
			hero.getMagicBranchPaths()[i] = t.nextToken();

		size = Integer.parseInt(t.nextToken());
		for (int i = 0; i < size; i++)
			hero.getItemsLl().add(new Item(t.nextToken(), Double.parseDouble(t.nextToken())));
		
		if (t.nextToken().equals("0"))
			hero.setPhoenixFeatherBool(false);
		
		hero.setSpecialsStr(t.nextToken());
		
		if (version >= 2)
			hero.setFreeRaceModifierIndex(Integer.parseInt(t.nextToken()));
		else
			hero.setFreeRaceModifierIndex(-1);
				
		return hero;
	}
}
