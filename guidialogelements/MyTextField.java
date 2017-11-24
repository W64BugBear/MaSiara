package guidialogelements;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class MyTextField 
	extends JTextField
	implements Noteable, KeyListener
{
	int index;
	Note note = null;
	SpecIcon spec = null;

	public MyTextField(int index)
	{
		super();
		this.index = index;
		addKeyListener(this);
	}
	
	public MyTextField(String text, int index)
	{
		super(text);
		this.index = index;
		addKeyListener(this);
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
	
	public int getIndex()
	{ return index; }
	
	public void setIndex(int index)
	{
		this.index = index;
	}
	
	public void keyReleased (KeyEvent ke) {
		
	}
	
	public void keyPressed (KeyEvent ke) {
		if (ke.getKeyCode() == ke.VK_UP)
		try {
			int key = Integer.parseInt(getText()) + 1;
			setText(key+"");
		} catch (NumberFormatException nfe) {
			// nothing
		}
		
		if (ke.getKeyCode() == ke.VK_DOWN)
			try {
				int key = Integer.parseInt(getText()) - 1;
				setText(key+"");
			} catch (NumberFormatException nfe) {
				// nothing
			}
	}
	
	public void keyTyped (KeyEvent ke) {
		
	}

}
