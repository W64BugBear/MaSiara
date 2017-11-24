package guidialogelements;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;

public class ContextSensitiveMenu 
	extends PopupMenu
{
	public ContextSensitiveMenu(ActionListener listener)
	{
		MenuItem mi;
		
		mi = new MenuItem("Notiz hinzufügen/bearbeiten");
		mi.addActionListener(listener);
		add(mi);
		
		mi = new MenuItem("Notiz löschen");
		mi.addActionListener(listener);
		add(mi);
		
		addSeparator();
		
		mi = new MenuItem("Spezialisierung hinzufügen/bearbeiten");
		mi.addActionListener(listener);
		add(mi);
		
		mi = new MenuItem("Spezialisierung löschen");
		mi.addActionListener(listener);
		add(mi);
	}
}
