package guidialogelements;

import gui.SkillsPanel;

import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import background.Constants;
import character.Skill;

public class SkillPositioner {

	int cnt;
	int y = 2;
	SkillsPanel panel;
	LinkedList components;
	
	public SkillPositioner(int cnt, SkillsPanel father)
	{
		this.cnt = cnt;
		panel = father;
		
		components = new LinkedList<JComponent>();
	}
	
	public SkillPositioner(int cnt, SkillsPanel father, LinkedList skills, boolean createNewHero)	// if createNewHero is false, it won't add an extra set of skills to a preexisting one
	{
		this (cnt, father);
		
		Iterator it = skills.iterator();
		while (it.hasNext())
			add(it.next(), createNewHero);
	}
	
	public void add (Object obj, boolean createNewHero)
	{
		if (obj == null)
			y++;
		
		if (obj instanceof JComponent)
		{
			((JComponent)(obj)).setBounds(Constants.OFFSETX + cnt * (Constants.WIDTH1 + Constants.WIDTH2 + Constants.SPACEX*3), Constants.OFFSETY + y++*(Constants.HEIGHT + Constants.SPACEY), Constants.WIDTH1, Constants.HEIGHT);
			panel.add(((JComponent)(obj)));
			components.add(obj);
		}
		
		if (obj instanceof Skill)
		{
			SkillUnit su = new SkillUnit((Skill)(obj), panel, createNewHero);
			su.setBorders(cnt, y++);
			su.add(panel);
			
			components.add(su);
		}
	}
	
	public void remove (Object obj)
	{
		Iterator it = components.iterator();
		
		while (it.hasNext() && it.next() != obj);
		
		while (it.hasNext())		// all following components are movard upwards
		{
			Object o = it.next();
			if (o instanceof JComponent)
				((JComponent)(o)).setLocation(((JComponent)(o)).getX(), ((JComponent)(o)).getY() - Constants.HEIGHT - Constants.SPACEY);
			if (o instanceof SkillUnit)
				((SkillUnit)(o)).oneRowDown(); 
		}
		
		if (obj == null)
			y--;
		
		if (obj instanceof JComponent)
		{
			panel.remove(((JComponent)(obj)));
			try {
				panel.remove(((Noteable)(obj)).getNote());
				((Noteable)(obj)).setNote(null);
			} catch (ClassCastException cce)
			{ /* nothing */ } catch (NullPointerException npe) 
			{ /* nothing */ }
			components.remove(obj);
			y--;
		}
		
		if (obj instanceof SkillUnit)
		{
			panel.remove(((SkillUnit)(obj)).getCostsTf());
			try {
				panel.remove(((SkillUnit)(obj)).getCostsTf().getNote());
				((SkillUnit)(obj)).getCostsTf().setNote(null);
			} catch (ClassCastException cce)
			{ /* nothing */ } catch (NullPointerException npe) 
			{ /* nothing */ }
			
			panel.remove(((SkillUnit)(obj)).getNameLbl());
			try {
				panel.remove(((SkillUnit)(obj)).getNameLbl().getNote());
				((SkillUnit)(obj)).getNameLbl().setNote(null);
			} catch (ClassCastException cce)
			{ /* nothing */ } catch (NullPointerException npe) 
			{ /* nothing */ }
			panel.remove(((SkillUnit)(obj)).getValueSpinner());
			try {
				panel.remove(((SkillUnit)(obj)).getValueSpinner().getNote());
				((SkillUnit)(obj)).getValueSpinner().setNote(null);
			} catch (ClassCastException cce)
			{ /* nothing */ } catch (NullPointerException npe) 
			{ /* nothing */ }
			
			panel.getFather().getHero().getSpecialSkillsLl().remove(((SkillUnit)(obj)).getSkill());
			components.remove(obj);
			y--;
		}
	}

	public LinkedList getComponents() {
		return components;
	}

	public void setComponents(LinkedList<JComponent> components) {
		this.components = components;
	}
	
	public void addAfter (Object obj, JComboBox combo, boolean inList)
	{
		Rectangle borders = null;		// intended borders of the new element 'obj'
		
		Iterator<JComponent> it = components.iterator(), it2 = components.iterator();	
		it.next(); // always one step ahead *gg*
		
		while (it.hasNext() && it.next() != combo)
			it2.next();					// look for the aimed ComboBox
		it2.next();						// it.next() is once oftener called then it2.next
		while (it.hasNext() && !(it.next() instanceof JComponent))
			it2.next();					// look for the next Combobox or MyLabel
		it2.next();						// dito, one step behind
		
		it = null;		// we don't need thy anymore!
			
		while (it2.hasNext())	// get all elements after the one put it one row down
		{
			Object o = it2.next();
			
			if (borders == null)		// save the borders of the first element
			{	
				borders = ((JComponent)(o)).getBounds();
				borders.setLocation((int)(borders.getX()), (int)(borders.getY()) - Constants.HEIGHT - Constants.SPACEY);
			}							// put it one row up because there should be a space between the chapters
			
			if (o instanceof JComponent)
				((JComponent)(o)).setLocation(((JComponent)(o)).getX(), ((JComponent)(o)).getY() + Constants.HEIGHT + Constants.SPACEY);
			if (o instanceof SkillUnit)
				((SkillUnit)(o)).oneRowUp();
		}

		it = components.iterator();		// a new quest: look for the next component which is no SkillUnit
		Object object = null;			// why?
		while (it.hasNext() && it.next() != combo);	// because I have to insert the new element before the next Combobox, Label or anything
		while (it.hasNext())
		try {
			Object tmp = it.next();
			if (!(tmp instanceof SkillUnit))
			{
				object = tmp;
				it = null;
				break;
			}
			
		} catch (ClassCastException cce)
		{ /* nothing */ }
		
		if (obj instanceof JComponent)	
		{
			if (borders == null)
				((JComponent)(obj)).setBounds(Constants.OFFSETX + cnt * (Constants.WIDTH1 + Constants.WIDTH2 + Constants.SPACEX*3), Constants.OFFSETY + y*(Constants.HEIGHT + Constants.SPACEY), Constants.WIDTH1, Constants.HEIGHT);
			else
				((JComponent)(obj)).setBounds(borders);
			panel.add(((JComponent)(obj)));
			
			components.add(components.indexOf(object), obj);
		}
		
		if (obj instanceof Skill)
		{
			SkillUnit su = new SkillUnit((Skill)(obj), panel, inList);
//			if (skill.getSpecialization() != null)
//				father.addSpec(nameLbl, skill.getSpecialization().getNameStr(), skill.getSpecialization().getValueInt());

			if (borders == null)
				su.setBorders(cnt, y);
			else
				su.setBorders(borders);
			su.add(panel);
		
			components.add(components.indexOf(object), su);
			
			if (((Skill)(obj)).getSpecialization() != null)
				panel.addSpec(su.getNameLbl(), ((Skill)(obj)).getSpecialization().getNameStr(), ((Skill)(obj)).getSpecialization().getValueInt());
			// can't add it earlier, becaus it needs to be settled in components

		}
		
		y++;
	}
	
	public void undoYourself()
	{
		Iterator it = components.iterator();
		while (it.hasNext())
		{
			Object obj = it.next();
			if (obj instanceof JComponent)
				panel.remove((JComponent)obj);
			if (obj instanceof SkillUnit)
				((SkillUnit)obj).removeYourself(panel);
		}
	}
}
