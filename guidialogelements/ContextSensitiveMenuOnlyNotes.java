package guidialogelements;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;

public class ContextSensitiveMenuOnlyNotes
	extends PopupMenu
{
	public ContextSensitiveMenuOnlyNotes(ActionListener listener)
	{
		MenuItem mi;
		
		mi = new MenuItem("Notiz hinzufügen/bearbeiten");
		mi.addActionListener(listener);
		add(mi);
		
		mi = new MenuItem("Notiz löschen");
		mi.addActionListener(listener);
		add(mi);
	}
}
