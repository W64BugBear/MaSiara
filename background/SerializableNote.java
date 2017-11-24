package background;

import java.io.Serializable;

public class SerializableNote 
	implements Serializable
{
		// Serializable form of guidialogelements.Note
		// contains information to be deserialized
	
	String text;
	int index;
	
	public SerializableNote(String text, int index)
	{
		this.text = text;
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
