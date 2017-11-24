package guidialogelements;

import javax.swing.JComboBox;


public class MyComboBox 
	extends JComboBox
	implements Noteable
{
	int index;
	Note note = null;
	SpecIcon spec = null;

	public MyComboBox(int index)
	{
		super();
		this.index = index;
	}
	
	public MyComboBox(Object[] objects, int index)
	{
		super(objects);
		this.index = index;
	}
	
	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}
	
	public SpecIcon getSpec() {
		return spec;
	}
	
	public void setSpec(SpecIcon spec){
		this.spec = spec;
	}
	
	public int getIndex(){
		return index;
	}
}
