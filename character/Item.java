package character;

import java.io.Serializable;

public class Item 
	implements Serializable
{
	String nameStr;
	double worthDouble;
	
	public Item (String name, double worth)
	{
		nameStr = name;
		worthDouble = worth;
	}

	public String getNameStr() {
		return nameStr;
	}

	public void setNameStr(String nameStr) {
		this.nameStr = nameStr;
	}

	public double getworthDouble() {
		return worthDouble;
	}

	public void setworthDouble(double worthDouble) {
		this.worthDouble = worthDouble;
	}
	
	private String concat(double number)
	{
		String ret = number+"";
		
		if (ret.length() > ret.indexOf(".")+2)
		{
			return ret.substring(0,ret.indexOf(".")+3);
		}
		else
			return ret;
	}
	
	public String toString()
	{
		return nameStr + ", " + concat(worthDouble);
	}
}
