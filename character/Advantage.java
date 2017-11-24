package character;

import java.io.Serializable;

public class Advantage 
	extends AdvantageFamily
	implements Serializable
{
	String shortcut;
	double costs;
	int number; // how often can I choose it?
	String label;
	String desc;
	boolean binary = false;	// if the attribute is processed in a later progress (e. g. Anab+1, KTMP, ...)
	
	public Advantage (String shortcut, double costs, int number, String label, String desc)
	{
		this.shortcut = shortcut;
		this.label = label;
		this.number = number;
		this.costs = costs;
		this.desc = desc;
	}
	
	public Advantage (String shortcut, double costs, int number, String label, String desc, boolean binary)
	{
		this(shortcut, costs, number, label, desc);
		this.binary = binary;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getCosts() {
		return costs;
	}

	public String getDesc() {
		return desc;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public String getShortcut()
	{
		return shortcut;
	}
	
	public boolean isBinary()
	{
		return binary;
	}
	
	public String toString()
	{
		return shortcut;
	}
}
