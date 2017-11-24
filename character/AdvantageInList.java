package character;

public class AdvantageInList 
	extends Advantage
{
//	public AdvantageInList (String shortcut, double costs, int number, String label, String desc)
//	{
//		super (shortcut, costs, number, label, desc);
//	}
//	
//	public AdvantageInList (String shortcut, double costs, int number, String label, String desc, boolean binary)
//	{
//		super(shortcut, costs, number, label, desc, binary);
//	}
	
	public AdvantageInList (Advantage adv)
	{
		super(adv.getShortcut(), adv.getCosts(), adv.getNumber(), adv.getLabel(), adv.getDesc(), adv.isBinary());
	}
	
	public String toString()
	{
		return shortcut + " (" + costs + ", " + number + ")";
	}
}
