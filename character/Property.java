package character;

import java.io.Serializable;

public class Property
	implements Serializable
{
	String nameString;
	int valueInt;
	int maximumInt;
	int minimumInt;
	
	Specialization specialization = null;
	
	public Property (String name, int value, int min, int max)
	{
		nameString = name;
		valueInt = value;
		minimumInt = min;
		maximumInt = max;
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	public int getValueInt() {
		return valueInt;
	}

	public void setValueInt(int valueInt) {
		this.valueInt = valueInt;
	}

	public int getMaximumInt() {
		return maximumInt;
	}

	public void setMaximumInt(int maximumInt) {
		this.maximumInt = maximumInt;
	}

	public int getMinimumInt() {
		return minimumInt;
	}

	public void setMinimumInt(int minimumInt) {
		this.minimumInt = minimumInt;
	}
}
