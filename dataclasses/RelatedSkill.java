package dataclasses;

public class RelatedSkill {

	String name;
	int threshold;
	
	public RelatedSkill(String name) {
		this.name = name;
		this.threshold = 1;
	}
	
	public RelatedSkill(String name, int threshold) {
		super();
		this.name = name;
		this.threshold = threshold;
	}
	
	public String getName() {
		return name;
	}
	public int getThreshold() {
		return threshold;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
}
