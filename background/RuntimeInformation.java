package background;

import java.util.LinkedList;

import character.Hero;
import character.Property;

public class RuntimeInformation	// NOT serializable, will not be saved 
{
	LinkedList propertyNotesLl;			// instances of guidialogelements.Note
	LinkedList skillNotesLl;
	LinkedList fightNotesLl;
	LinkedList startNotesLl;
	
	LinkedList propertySpecsLl;	// instances of guidialogelements.SpecIcon
	LinkedList skillSpecsLl;	
	
	public RuntimeInformation()
	{
		propertyNotesLl = new LinkedList();
		skillNotesLl = new LinkedList();
		fightNotesLl = new LinkedList();
		startNotesLl = new LinkedList();
		
		propertySpecsLl = new LinkedList();
		skillSpecsLl = new LinkedList();
	}

	public LinkedList getFightNotesLl() {
		return fightNotesLl;
	}

	public LinkedList getPropertyNotesLl() {
		return propertyNotesLl;
	}

	public LinkedList getSkillNotesLl() {
		return skillNotesLl;
	}

	public void setFightNotesLl(LinkedList fightNotesLl) {
		this.fightNotesLl = fightNotesLl;
	}

	public void setPropertyNotesLl(LinkedList propertyNotesLl) {
		this.propertyNotesLl = propertyNotesLl;
	}

	public void setSkillNotesLl(LinkedList skillNotesLl) {
		this.skillNotesLl = skillNotesLl;
	}

	public LinkedList getPropertySpecsLl() {
		return propertySpecsLl;
	}

	public void setPropertySpecsLl(LinkedList propertySpecsLl) {
		this.propertySpecsLl = propertySpecsLl;
	}

	public LinkedList getSkillSpecsLl() {
		return skillSpecsLl;
	}

	public void setSkillSpecsLl(LinkedList skillSpecsLl) {
		this.skillSpecsLl = skillSpecsLl;
	}

	public LinkedList getStartNotesLl() {
		return startNotesLl;
	}

	public void setStartNotesLl(LinkedList startNotesLl) {
		this.startNotesLl = startNotesLl;
	}
}
