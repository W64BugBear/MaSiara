package guidialogelements;

import javax.swing.JLabel;

public class MyLabel 
	extends JLabel
	implements Noteable
{
	int index;
	Note note = null;
	SpecIcon spec = null;

	public MyLabel(int index)
	{
		super();
		this.index = index;
	}
	
	public MyLabel(String text, int index)
	{
		super(text);
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
