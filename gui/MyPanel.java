package gui;

import guidialogelements.MyLabel;
import guidialogelements.Note;
import guidialogelements.Noteable;
import guidialogelements.SpecIcon;

import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import background.Constants;
import character.Specialization;

public abstract class MyPanel 
	extends JPanel
	implements MouseListener
{
	FontMetrics fm;				// for drawing notes
	
	MainWindow father;
	
	public MyPanel(MainWindow father)
	{
		super();
		this.father = father;
		this.addMouseListener(this);		
	}
	
	public void addEditNote(JComponent notedComponent)
	{
		if (notedComponent == null)
			return;
		
		if (((Noteable)(notedComponent)).getNote() != null)	// edit
		{
			// opens dialogue to get note text
			NewNoteDialog nnDialog = new NewNoteDialog(father, ((Noteable)(notedComponent)).getNote().getText());
			nnDialog.setLocation(notedComponent.getX()+father.getX(), notedComponent.getY()+father.getY()-father.getScrollPane().getVerticalScrollBar().getValue());
			nnDialog.setVisible(true);
			if (nnDialog.getNoteText().equals(""))
				deleteNote(notedComponent);
			else
				((Noteable)(notedComponent)).getNote().setText(nnDialog.getNoteText());
		}
		else	// new Note
		{
			// opens dialogue to get note text
			NewNoteDialog nnDialog = new NewNoteDialog(father);
			nnDialog.setLocation(notedComponent.getX()+father.getX(), notedComponent.getY()+father.getY()-father.getScrollPane().getVerticalScrollBar().getValue());
			nnDialog.setVisible(true);
			
			if (!nnDialog.getNoteText().equals(""))
			{
				Note note = new Note(this, nnDialog.getNoteText());
				
				remove(notedComponent);		// must remove and add it in order to have the note displayed in foreground

				note.setNotedComponent(notedComponent);	// to adjust
				((Noteable)(notedComponent)).setNote(note);
				
				getNotes().add(note);	// save
				
				add(note);
				add(notedComponent);
				
				repaint();
				setVisible(true);
			}
		}
		try {
			(getFocusComponentInClickedRow(notedComponent)).requestFocus();
		} catch (NullPointerException npe)
		{
			getDefaultFocusComponent().requestFocus();
		}
		
		return;
	}
	
	public void addNote(JComponent notedComponent, String text, boolean overWrite)
	{
		addNote(notedComponent, text, overWrite, false);
	}
	
	public void addNote(JComponent notedComponent, String text, boolean overWrite, boolean requestFocus)
	{
		if (notedComponent == null)
			return;
		
		if (((Noteable)(notedComponent)).getNote() != null)	// edit
		{
			if (!((Noteable)(notedComponent)).getNote().getText().equals(text))
				if (overWrite)
					((Noteable)(notedComponent)).getNote().setText(text);
				else
					((Noteable)(notedComponent)).getNote().setText(((Noteable)(notedComponent)).getNote().getText()+" "+text);
		}
		else	// new Note
		{
			Note note = new Note(this, text);
			
			remove(notedComponent);		// must remove and add it in order to have the note displayed in foreground

			note.setNotedComponent(notedComponent);	// to adjust
			((Noteable)(notedComponent)).setNote(note);
			
			getNotes().add(note);	// save
			
			add(note);
			add(notedComponent);
			
			repaint();
			setVisible(true);
		}
		if (requestFocus)
			try {
				(getFocusComponentInClickedRow(notedComponent)).requestFocus();
			} catch (NullPointerException npe)
			{
				getDefaultFocusComponent().requestFocus();
			}
		
		return;
	}
	
	public void addEditSpec (JComponent specComponent)
	{
		if (specComponent == null)
			return;
	
		MyLabel specLabel = getTopicInClickedRow(specComponent);	// Specialization are always attached to the topic, e. g. "Gebräuche"
		
		if (((Noteable)(specLabel)).getSpec() != null)	//edit
		{
			// opens dialogue to get note text
			NewSpecDialog nsDialog = new NewSpecDialog(father, ((Noteable)(specLabel)).getSpec().getText(), ((Noteable)(specLabel)).getSpec().getVal());
			nsDialog.setLocation(specLabel.getX()+father.getX(), specLabel.getY()+father.getY()-father.getScrollPane().getVerticalScrollBar().getValue());
			nsDialog.setVisible(true);
			if (nsDialog.getSpecText().equals("") || nsDialog.getSpecValue() < 1)
				deleteSpec(specLabel);
			else
			{
				((Noteable)(specLabel)).getSpec().setText(nsDialog.getSpecText());
				((Noteable)(specLabel)).getSpec().setVal(nsDialog.getSpecValue()+"");
				String note = null;
				try {
					note = ((Noteable)specLabel).getNote().getText();
				} catch (NullPointerException npe) {}
				
				setSpecializationByText(specLabel.getText(), note, new Specialization(nsDialog.getSpecText(), nsDialog.getSpecValue()));
			}
		}
		else	// new Specialization
		{
			// opens dialogue to get note text
			NewSpecDialog nsDialog = new NewSpecDialog(father);
			nsDialog.setLocation(specLabel.getX()+father.getX(), specLabel.getY()+father.getY()-father.getScrollPane().getVerticalScrollBar().getValue());
			nsDialog.setVisible(true);
			
			if (!nsDialog.getSpecText().equals("") && nsDialog.getSpecValue() > 0)
			{
				SpecIcon specIcon = new SpecIcon(this, nsDialog.getSpecText(), nsDialog.getSpecValue()+"");
				
				remove(specLabel);		// must remove and add it in order to have the note displayed in foreground
				specIcon.setSpecComponent(specLabel);	// to adjust
				((Noteable)(specLabel)).setSpec(specIcon);
				
				String note = null;
				try {
					note = ((Noteable)specLabel).getNote().getText();
				} catch (NullPointerException npe) {}
				
				setSpecializationByText(specLabel.getText(), note, new Specialization(nsDialog.getSpecText(), nsDialog.getSpecValue()));
				getSpecIcons().add(specIcon);
				
				add(specIcon);
				add(specLabel);
				
				repaint();
				setVisible(true);
			}
		}
		try {
			(getFocusComponentInClickedRow(specComponent)).requestFocus();
		} catch (NullPointerException npe)
		{
			getDefaultFocusComponent().requestFocus();
		}
		
		return;
	}
	
	/**
	 * must NOT used unless for deserializing!!!
	 * if u need it unless for deserializing you only have to insert the 2 statements like
	 * father.getHero().getProperty(specLabel.getText()).setSpecialization(new Specialization(nsDialog.getSpecText(), nsDialog.getSpecValue())); 
	 */
	public void addSpec (JComponent specComponent, String text, int val)
	{
		if (specComponent == null)
			return;
	
		MyLabel specLabel = getTopicInClickedRow(specComponent);	// Specialization are alwazs attached to the topic, e. g. "Aussehen"
		
		if (((Noteable)(specLabel)).getSpec() != null)	//edit
		{
			((Noteable)(specLabel)).getSpec().setText(text);
			((Noteable)(specLabel)).getSpec().setVal(val+"");
		}
		else	// new Specialization
		{
			// opens dialogue to get note text
			SpecIcon specIcon = new SpecIcon(this, text, val+"");
			
			remove(specLabel);		// must remove and add it in order to have the note displayed in foreground
			specIcon.setSpecComponent(specLabel);	// to adjust
			((Noteable)(specLabel)).setSpec(specIcon);
			
			getSpecIcons().add(specIcon);

			add(specIcon);
			add(specLabel);
			
			repaint();
			setVisible(true);
		}
		try {
			(getFocusComponentInClickedRow(specComponent)).requestFocus();
		} catch (NullPointerException npe)
		{
			getDefaultFocusComponent().requestFocus();
		}
		
		return;
	}
	
	public void deleteNote(JComponent notedComponent)
	{
		try {
			remove(((Noteable)(notedComponent)).getNote());
			getNotes().remove(((Noteable)(notedComponent)).getNote());	// save
			((Noteable)(notedComponent)).setNote(null);
			
			repaint();
			setVisible(true);
		} catch (NullPointerException npe) {}
		
		try {
			(getFocusComponentInClickedRow(notedComponent)).requestFocus();
		} catch (NullPointerException npe)
		{
			getDefaultFocusComponent().requestFocus();
		}
	}
	
	public void deleteSpec(JComponent specComponent)
	{
		try {
			JLabel specLabel = getTopicInClickedRow(specComponent);
			remove(((Noteable)(specLabel)).getSpec());
			getSpecIcons().remove(((Noteable)(specLabel)).getSpec());
			
			String note = null;
			try {
				note = ((Noteable)specLabel).getNote().getText();
			} catch (NullPointerException npe) {}
			setSpecializationByText(specLabel.getText(), note, null);
			
			((Noteable)(specLabel)).setSpec(null);
			
			repaint();
			setVisible(true);
		} catch (NullPointerException npe) {}
		
		try {
			(getFocusComponentInClickedRow(specComponent)).requestFocus();
		} catch (NullPointerException npe)
		{
			getDefaultFocusComponent().requestFocus();
		}
	}
	
	public JComponent getClickedComponent(int x, int y)
	{
		Component[] components = getComponents();
		for (int i = 0; i < components.length; i++)
			if (	x >= components[i].getX() && 
					x <= components[i].getX()+components[i].getWidth() &&
					y >= components[i].getY() && 
					y <= components[i].getY()+components[i].getHeight()
			)
				return (JComponent)(components[i]);
		return null;
	}
	
	public Noteable[] getNoteableComponents ()
	{
		int cnt = 0;
		Component[] comps = getComponents();
		for (int i = 0; i < comps.length; i++)
		{
			if (comps[i] instanceof Noteable)
				cnt++;
		}
				
		Noteable[] ret = new Noteable[cnt];
		
		cnt = 0;
		for (int i = 0; i < comps.length; i++)
			if (comps[i] instanceof Noteable)
				ret[cnt++] = (Noteable)(comps[i]);
		
		return ret;
	}

	public Noteable getNoteableComponentByIndex(int index)
	{
		Noteable[] noteables = getNoteableComponents();
		for (int i = 0; i < noteables.length; i++)
			if (noteables[i].getIndex() == index)
				return noteables[i];
		return null;
	}
	
	public void mouseClicked (MouseEvent me) {
		fm = getFontMetrics(getFont());

		// open or close notes
		Note note;
		
		if (getNotes() != null)
		{
			Iterator<Note> it = getNotes().iterator();
			
			while (it.hasNext())
			{
				note = it.next();
				
				if (me.getX() > note.getX() && me.getY() > note.getY() && me.getX() < note.getX() + note.getWidth() && me.getY() < note.getY() + note.getHeight())
				{			
					note.setClosed(!note.isClosed());
					if (note.isClosed())
						note.setBounds(note.getNotedComponent().getX()+note.getNotedComponent().getWidth()-5, note.getNotedComponent().getY()+note.getNotedComponent().getHeight()-10, 9, 14);
					else
						note.setBounds(note.getNotedComponent().getX()+note.getNotedComponent().getWidth()-5, note.getNotedComponent().getY()+note.getNotedComponent().getHeight()-10, (int)(note.getPreferredSize().getWidth()), (int)(note.getPreferredSize().getHeight()));
					actualize();
				}
			}
		}
		
		// open or close notes
		SpecIcon spec;
		
		if (getSpecIcons() != null)
		{
			Iterator<SpecIcon> it2 = getSpecIcons().iterator();
			
			while (it2.hasNext())
			{
				spec = it2.next();
				
				if (me.getX() > spec.getX() && me.getY() > spec.getY() && me.getX() < spec.getWidth() && me.getY() < spec.getHeight())
				{			
					if (!spec.isClosed())
						spec.setBounds(spec.getSpecComponent().getX()+spec.getSpecComponent().getWidth()-15, spec.getSpecComponent().getY()+spec.getSpecComponent().getHeight()-10, spec.getSpecComponent().getX()+spec.getSpecComponent().getWidth()-6, spec.getSpecComponent().getY()+spec.getSpecComponent().getHeight()+4);
					else
						spec.setBounds(spec.getSpecComponent().getX()+spec.getSpecComponent().getWidth()-15, spec.getSpecComponent().getY()+spec.getSpecComponent().getHeight()-10, spec.getSpecComponent().getX()+spec.getSpecComponent().getWidth()-15+fm.stringWidth(spec.getText())+Constants.SPACEX*2, spec.getSpecComponent().getY()+spec.getSpecComponent().getHeight()-10+fm.getHeight()+Constants.SPACEY*2);
					actualize();
					spec.setClosed(!spec.isClosed());
				}
			} 
		} 
	}
	
	/**
	 * Called if note or specialization is added.
	 * Removes all Components and adds it in the right order.
	 * Otherwise notes and specs would be displayed BEHIND other comps.
	 */
	public void actualize()
	{
		Component focusComponent = null; // for whatever reason actualizing would have the same effect like <TAB> if I would not intervene
		Component[] comps = getComponents();
		for (int i = 0; i < comps.length; i++)
		{
			if (comps[i].hasFocus())
				focusComponent = comps[i];
			remove(comps[i]);	// remove them ...
		}
		
		try {
			Iterator<Note> it = getNotes().iterator();
			while (it.hasNext())
			{
				Note note = it.next();
				add(note); // ...first add notes...
				((Noteable)(note.getNotedComponent())).setNote(note);
			}
		} catch (NullPointerException npe) {}
		
		try {
			Iterator<SpecIcon> it2 = getSpecIcons().iterator();
		
			while (it2.hasNext())
			{
				SpecIcon sp = it2.next();
				add(sp);	// ... and specs...
				((Noteable)(sp.getSpecComponent())).setSpec(sp);
			}
		} catch (NullPointerException npe) {}
		
		addComponents(); // ... and then tha comps come back
		if (focusComponent != null)
			focusComponent.requestFocus();
		else
			getDefaultFocusComponent().requestFocus();
	}
	
	public void setGPEPLabel()
	{
		father.gpepLabel.setText("EP: " + father.getSInf().getEpInt() + "/" + (father.getSInf().getEpAmountInt()));
	}
	
	public void mousePressed (MouseEvent me) {}
	public void mouseReleased (MouseEvent me) {}
	public void mouseExited (MouseEvent me) {}
	public void mouseEntered (MouseEvent me) {}

	public abstract void setSpecializationByText(String text, String note, Specialization spec);
	public abstract void addComponents();
	public abstract LinkedList getNotes();
	public abstract LinkedList getSpecIcons();
	public abstract JComponent getFocusComponentInClickedRow(JComponent comp);
	public abstract MyLabel getTopicInClickedRow(JComponent component);
	public abstract String serializeYourself();
	public abstract void deserializeYourself(String s, int version);
	public abstract void deserializeYourself();
	public abstract JComponent getDefaultFocusComponent();
//	public abstract String checkForErrors();
}
