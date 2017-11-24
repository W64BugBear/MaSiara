package items;

public class RangeWeaponType {
	String name;
	int type;
	
	public static final int BOWS = 1;
	public static final int JAVELIN = 2;
	public static final int AXE = 3;
	public static final int ROPE = 4;
	public static final int KNIFE = 5;
	public static final int SLINGER = 6;
	public static final int PIPE = 7;
	public static final int ARTILLERY = 8;
	public static final int MAGIC = 9;
	
	public RangeWeaponType (String name, int type)
	{
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public int getType() {
		return type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public String toString()
	{
		return this.name;
	}
}
