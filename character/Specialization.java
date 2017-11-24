package character;

import java.io.Serializable;

public class Specialization 
	implements Serializable
{
	String nameStr;
	int valueInt;
	
	public Specialization (String name, int value)
	{
		nameStr= name;
		valueInt = value;
	}

	public String getNameStr() {
		return nameStr;
	}

	public void setNameStr(String nameStr) {
		this.nameStr = nameStr;
	}

	public int getValueInt() {
		return valueInt;
	}

	public void setValueInt(int valueInt) {
		this.valueInt = valueInt;
	}
}
