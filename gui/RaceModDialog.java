package gui;

import guidialogelements.MySpinner;
import guidialogelements.SkillUnit;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;

import background.Constants;
import character.Skill;

public class RaceModDialog 
	extends JDialog
	implements ActionListener, ItemListener, KeyListener
{
	public static final int PROPERTIES = 0;
	public static final int GROUPVALUES = 1;
	
	boolean ctrlPressed;
	
	CheckboxGroup group = new CheckboxGroup();
	Checkbox[] boxes;
	
	MainWindow owner;
	int chosenOne = -1;
	LinkedList <String> list = null;	
	JButton saveBt;
	boolean set = true;
	int positions[];	// positions in original lists
	
	int propertiesOrGroupValues;
	/**
	 * @param set true when race is chosen, false when is undone
	 */
	public RaceModDialog(MainWindow owner, int propertiesOrGroupValues, boolean set)
	{
		super(owner, true); // modal dialog
		this.owner = owner;
		this.set = set;
		this.propertiesOrGroupValues = propertiesOrGroupValues;
		setLayout(null);
		
		invokeDialog(propertiesOrGroupValues);
		if (list.size() < 2)
			return;
	
		int i = 0;
		
		boxes = new Checkbox[list.size()];
		
		for (; i < list.size(); i++)
		{
			boxes[i] = new Checkbox(list.get(i), group, false);
			boxes[i].setBounds(Constants.OFFSETX, Constants.OFFSETY + i*(Constants.HEIGHT + Constants.SPACEY), Constants.WIDTH1, Constants.HEIGHT);
			boxes[i].addItemListener(this);
			boxes[i].addKeyListener(this);
			add(boxes[i]);
		}
		
		saveBt = new JButton("wählen");
		saveBt.addActionListener(this);
		saveBt.addKeyListener(this);
		
		saveBt.setBounds(Constants.OFFSETX, Constants.OFFSETY + (i)*(Constants.HEIGHT + Constants.SPACEY) + Constants.SPACEY*2, Constants.WIDTH2, Constants.HEIGHT);
		
		saveBt.setEnabled(false);
		
		add(saveBt);
		
		setLocation(350, 250);
		setSize(getPreferredSize());
		setVisible(true);
	}

	// this is NOT JComponent.getPreferredSize!
	public Dimension getPreferredSize()
	{
		return new Dimension(Constants.OFFSETX*2 + Constants.WIDTH1 + Constants.SPACEX*2, saveBt.getY() + saveBt.getHeight() + 45); 
		// i think the 45 are the title
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		dispose(); 
	}
	
	public void invokeDialog(int propertiesOrGroupValues){
		list = new LinkedList<String>();
		positions = new int[12];
		for (int i = 0; i < 12; i++)
			positions[i] = -1;
		int counter1 = 0, counter2 = 0;
		if (set)
		{
			if (propertiesOrGroupValues == PROPERTIES)
			{
				MySpinner[] ownmods = owner.getPropertiesPanel().getOwnmods();
				
				for (int i = 0; i < 12; i++)
				{
					list.add(owner.getPropertiesPanel().getPropertyByIndex(i) + " ("+ownmods[i].getText()+")");
					positions[i] = i;
				}
			}
			else
			{
				SkillUnit su = null;
		 		for (int i = 0; i < 4; i++)
		 		{
					Iterator it = owner.getSkillsPanel().getSkillPositioners()[i].getComponents().iterator();
					while (it.hasNext())
					{
						try {
							su = (SkillUnit)(it.next());
							if (su.getSkill().getTypeInt() == Skill.TYPE_COMPLEX && su.getSkill().isBaseSkill())	// Complex basic skill, i.e. group skill
							{
								list.add(su.getSkill().getNameStr() + " (" + su.getSkill().getValueInt() + ")");
								positions[counter1] = counter1++;
							}
						} catch (ClassCastException cce) {/*nothing*/}
					}
		 		}
			}
		}
		else
		{
			if (propertiesOrGroupValues == PROPERTIES)
			{
				MySpinner[] ownmods = owner.getPropertiesPanel().getOwnmods();
				
				for (int i = 0; i < 12; i++)
				{
					int value = 0;
					try {
						value = Integer.parseInt(ownmods[i].getText());
					} catch (NumberFormatException nfe)
					{
						nfe.printStackTrace();
					}
					
					if (value > 0)
					{
						list.add(owner.getPropertiesPanel().getPropertyByIndex(i) + " ("+value+")");
						positions[counter1++] = i;
					}
				}
			}
			else
			{
				SkillUnit su = null;
		 		for (int i = 0; i < 4; i++)
		 		{
					Iterator it = owner.getSkillsPanel().getSkillPositioners()[i].getComponents().iterator();
					while (it.hasNext())
					{
						try {
							su = (SkillUnit)(it.next());
							if (su.getSkill().getTypeInt() == Skill.TYPE_COMPLEX && su.getSkill().isBaseSkill())	// Complex basic skill, i.e. group skill
							{
								if (su.getSkill().getValueInt() > 0)
								{
									list.add(su.getSkill().getNameStr() + " (" + su.getSkill().getValueInt() + ")");
									positions[counter1++] = counter2;
								}
								counter2++;
							}
						} catch (ClassCastException cce) {/*nothing*/}
					}
		 		}
			}
			if (list.size() == 1)
				chosenOne = positions[0];
		}
	}
	
	public void itemStateChanged(ItemEvent ie)
	{
		for (int i = 0; i < boxes.length; i++)
			if (ie.getSource() == boxes[i])
				chosenOne = positions[i];
		
		saveBt.setEnabled(true);
	}
	
	public int getChosenOne() {
		return chosenOne;
	}
	
	public void keyPressed(KeyEvent ke)
	{
		if (ke.getKeyCode() == ke.VK_ESCAPE || ke.getKeyCode() == ke.VK_ENTER)
			dispose();
		
//		if (ke.getKeyCode() == ke.VK_DOWN)
//		{
//			boolean found = false;
//			
//			for (int i = 0; i < boxes.length; i++)
//			{
//				if (boxes[i] == group.getSelectedCheckbox())
//				{
//					found = true;
//					if (i != boxes.length)
////						group.setSelectedCheckbox(boxes[i+1]);
//						boxes[i+1].setState(true);
//					i = boxes.length;
//				}
//			}
//			if (found == false)
//				group.setSelectedCheckbox(boxes[0]);
//		}
//		
//		if (ke.getKeyCode() == ke.VK_UP)
//		{
//			boolean found = false;
//			
//			for (int i = 0; i < boxes.length; i++)
//			{
//				if (boxes[i] == group.getSelectedCheckbox())
//				{
//					found = true;
//					if (i != 0)
//						group.setSelectedCheckbox(boxes[i-1]);
//					i = boxes.length;
//				}
//			}
//			if (found == false)
//				group.setSelectedCheckbox(boxes[0]);
//		}
		
		if (ke.getKeyCode() == ke.VK_CONTROL)
			ctrlPressed = true;
		else
		{
			if (ctrlPressed)
				if (ke.getKeyCode() == ke.VK_S)
					dispose();
			ctrlPressed = false;
		}
	}
	
	public void keyReleased(KeyEvent ke)
	{
		ctrlPressed = false;
	}
	
	public void keyTyped(KeyEvent ke){/*nothing*/}
}
