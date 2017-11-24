package character;

import gui.MyPanel;
import guidialogelements.MyComboBox;
import guidialogelements.MyTextField;

import java.awt.Color;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.JTextField;

public class AdvantageFamily {
	
	String note = null;
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public JComponent generateComponent(MyPanel panel, int i)
	{
//		if (this == null)
//			return new JTextField(" ");
		
		if (this instanceof Advantage)
		{
			MyTextField textField = new MyTextField(i);
			textField.setText(((Advantage)(this)).getShortcut());
			textField.setToolTipText(((Advantage)(this)).getLabel() + " - " + ((Advantage)(this)).getDesc());
			
			if (note != null)
				panel.addNote(textField, note, true);
			
			return textField;
		}
		// else
		
		MyComboBox comboBox = new MyComboBox(i);
		comboBox.setToolTipText("");
		if (note != null)
			panel.addNote(comboBox, note, true);
					
		AdvantageGroup ag = (AdvantageGroup)this;
		
		if (!ag.commonpath.isEmpty())
		{
			comboBox.setBackground(new Color(200, 250, 200));
			Iterator<Advantage> it = ag.commonpath.iterator();
			
			while (it.hasNext())
			{
				Advantage adv = it.next();
				comboBox.addItem(adv + "/");
				comboBox.setToolTipText(comboBox.getToolTipText() + adv.getLabel() + " - " + adv.getDesc() + "   ");
			}
		}
		else
			if (!ag.path1.isEmpty())
			{
				comboBox.setBackground(new Color(200, 200, 250));
				
				Iterator<Advantage> it = ag.path1.iterator();
				
				while (it.hasNext())
				{
					Advantage adv = it.next();
					
					String str = adv.getShortcut();
					if (it.hasNext())
						str += "/";
					else
						str += "|";
					comboBox.addItem(str);
					comboBox.setToolTipText(comboBox.getToolTipText() + adv.getLabel() + " - " + adv.getDesc() + "   ");
				}
					
				it = ag.path2.iterator();
				
				while (it.hasNext())
				{
					Advantage adv = it.next();
					
					String str = adv.getShortcut();
					if (it.hasNext())
						str += "/";
					comboBox.addItem(str);
					comboBox.setToolTipText(comboBox.getToolTipText() + adv.getLabel() + " - " + adv.getLabel() + "   ");
				}
			}	
			if (!ag.path3.isEmpty())
			{
				comboBox.setBackground(new Color(250, 200, 200));
				
				Iterator<Advantage> it = ag.path3.iterator();
				
				while (it.hasNext())
				{
					Advantage adv = it.next();
					
					String str = adv.getShortcut();
					if (it.hasNext())
						str += "/";
					comboBox.addItem(str);
					comboBox.setToolTipText(comboBox.getToolTipText() + adv.getLabel() + " - " + adv.getLabel() + "   ");
				}
				
				it = ag.path4.iterator();
				
				while (it.hasNext())
				{
					Advantage adv = it.next();
					
					String str = adv.getShortcut();
					if (it.hasNext())
						str += "/";
					comboBox.addItem(str);
					comboBox.setToolTipText(comboBox.getToolTipText() + adv.getLabel() + " - " + adv.getLabel() + "   ");
				}
		}
		return comboBox;
	}
}
