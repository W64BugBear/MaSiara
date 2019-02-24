package races;

import gui.MainWindow;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JComponent;
import javax.swing.JLabel;

import background.Constants;
import background.SerializableInformation;
import character.Hero;

public abstract class Race 
	implements Serializable
{
	public static final int POOR = 0;
	public static final int RICH = 1;
	public static final int PRIMITIVE = 2;
	public static final int DWARF = 3;
	
	String nameStr;
	int equipmentInt;
	String descStr;
	String pictureStr;
	String specialRulesStr;
	LinkedList raceModifiers;
	boolean vampirePossible;
	boolean werewolfPossible;
	boolean efreetPossible;
	
	Hero hero;
	SerializableInformation sInf;
	JLabel gpEpLabel;
	MainWindow father;
	
	public Race (String name, boolean vampire, boolean werewolf, boolean efreet, int equipment, String desc, String specialRules, String picture)
	{
		nameStr = name;
		vampirePossible = vampire;
		werewolfPossible = werewolf;
		efreetPossible = efreet;
		equipmentInt = equipment;
		descStr = desc;
		pictureStr = picture;
		specialRulesStr = specialRules;
		this.raceModifiers = raceModifiers;
	}
	
	public abstract String serializeYourself ();
	
	public abstract void deserializeYourself (StringTokenizer t);
	
	public String toString()
	{
		return nameStr;
	}
	
	public String getName()
	{
		return nameStr;
	}
	
	public String getDesc()
	{
		return descStr;
	}
	
	public LinkedList getRaceModifiers ()
	{
		return raceModifiers;
	}
	
	public String getPictureStrBmp() 
	{
		return pictureStr;
	}
	
	public void set(Hero hero, SerializableInformation sInf, JLabel gpep, MainWindow father, boolean loading)
	{
		this.hero = hero;
		this.sInf = sInf;
		this.gpEpLabel = gpep;
		this.father = father;
	}
	public abstract void remove (MainWindow father);
	public abstract JComponent specials();
	
	public void setRaceModifiers(LinkedList raceModifiers) {
		this.raceModifiers = raceModifiers;
	}

	public String getSpecialRulesStr() {
		return specialRulesStr;
	}

	public int getEquipmentInt() {
		return equipmentInt;
	}

	public void setEquipmentInt(int equipmentInt) {
		this.equipmentInt = equipmentInt;
	}
	
	public void setGPEPLabel()
	{
		gpEpLabel.setText("EP: " + sInf.getEpInt() + "/" + sInf.getEpAmountInt());
	}
	
	public Dimension getSpecialSize()
	{
		return new Dimension (Constants.WIDTH2*3/2+Constants.SPACEX, Constants.HEIGHT);
	}
	
	public void applyMale()
	{
		
	}
	
	public void applyFemale()
	{
		
	}
	
	public void unapplyMale()
	{
		
	}
	
	public void unapplyFemale()
	{
		
	}
	
	public static Race getRaceByName (String race) {
		if (race.equals(	"Eardons Haus"			))
			return			new EardonsHouse();
		
		if (race.equals(	"Nur'thanis"			))
			return			new Nurthanis();
		
		if (race.equals(	"Sun'thanis"			))
			return 			new SunThani();
		
		if (race.equals(	"Ken'thanis (Seefahrer)"			))
			return			new KenthaniSailors();
		
		if (race.equals(	"Ken'thanis (Barbaren)"			))
			return			new KenthaniBarbarians();
		
		if (race.equals(	"Uthos"			))
			return			new Uthos();
		
		if (race.equals(	"Zwerge"			))
			return			new Dwarves();
		
		if (race.equals(	"Hochelfen"			))
			return			new HighElves();
		if (race.equals(	"Halbelfen"			))
			return			new HalfElves();
		
		if (race.equals(	"Feen"			))
			return			new Fairies();
		
		
		if (race.equals(	"Goblins"			))
			return			new Goblins();
		if (race.equals(	"Halborken"			))
			return			new Halforcs();
		if (race.equals(	"Oger"			))
			return			new Ogres();

		
		if (race.equals(	"Biestinger"			))
			return			new Beastingers();
		if (race.equals(	"Falkoniter"			))
			return			new Falconites();
		if (race.equals(	"Gnolle"			))
			return			new Gnolls();
		if (race.equals(    "Parder"			))
			return			new Parders();
		if (race.equals(    "Andalib"			))
			return			new Andalib();
		if (race.equals(    "Skirks"			))
			return			new Skirks();

		
		if (race.equals(    "Schattendrachen"			))
			return			new ShadowDragons();

		
		return null;
	}
}
