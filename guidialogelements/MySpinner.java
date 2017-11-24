// FightPanel.addListeners aktualisieren!!


package guidialogelements;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

import javax.swing.JTextField;

public class MySpinner 
	extends JTextField
	implements Noteable, Serializable, KeyListener
{
	int index;
	Note note = null;
	SpecIcon spec = null;

	public MySpinner(int index)
	{
		super();
		this.index = index;
		addKeyListener(this);
	}
	
	public MySpinner(String s, int index)
	{
		super();
		setText(s);
		this.index = index;
		addKeyListener(this);
	}
	
	public MySpinner(int i, int index)
	{
		super();
		setText(i+"");
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
	
	public int getIndex(){
		return index;
	}
	
	public void plusplus()
	{
		try {
			setText((Integer.parseInt(getText())+1)+"");
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void minusminus()
	{
		try {
			setText((Integer.parseInt(getText())-1)+"");
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
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
