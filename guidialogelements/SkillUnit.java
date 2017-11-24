package guidialogelements;

import gui.SkillsPanel;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JPanel;

import background.Constants;
import character.Hero;
import character.RaceModifiers;
import character.Skill;

public class SkillUnit {
	SkillsPanel father;
	MyLabel nameLbl;
	MySpinner valueSpinner;
	MyTextField costsTf;
	Skill skill;
	
	public SkillUnit(Skill skill, SkillsPanel father, boolean inList)
	{
		this.father = father;
		
		nameLbl = new MyLabel(skill.getNameStr(), father.index++);
		valueSpinner = new MySpinner(skill.getValueInt(), father.index++);
		valueSpinner.addKeyListener(father);
		valueSpinner.addActionListener(father);
		valueSpinner.addFocusListener(father);
		valueSpinner.setToolTipText(skill.getNameStr());
		costsTf = new MyTextField(skill.getCosts(1, father, ((RaceModifiers)(father.getFather().getHero().getRace().getRaceModifiers().getFirst())).isGoodRunners(), father.getFather().getHero().getProperties()[Hero.PROP_TOUGHNESS].getValueInt())+"", father.index++);
		costsTf.addKeyListener(father);
		costsTf.addActionListener(father);
		costsTf.setEditable(false);
		
		if (skill.getNote() != null)
			father.addNote(nameLbl, skill.getNote(), false);
		
		if (skill.getTypeInt() == Skill.TYPE_COMPLEX)
			nameLbl.setFont(new Font(father.getFont().getName(), Font.BOLD, father.getFont().getSize()));
		if (skill.getTypeInt() == Skill.TYPE_100EP)
			nameLbl.setFont(new Font(father.getFont().getName(), Font.BOLD, father.getFont().getSize()+2));
		
		this.skill = skill;
		
		if (inList)	// I dunno what this was supposed to do in the first place, but it seems it's important to turn that thingy off when I load. Otherwise I add an empty list of skills to the already loaded skills.
		{
			if (skill.isBaseSkill())
				father.getFather().getHero().getBasicSkillsLl().add(skill);
			else
				father.getFather().getHero().getSpecialSkillsLl().add(skill);
		}
		
		if (skill.getTypeInt() == Skill.TYPE_NO_SKILL)
		{
			valueSpinner.setVisible(false);
			costsTf.setVisible(false);
		}
	}
	
	public void setBorders(int x, int y)
	{
		nameLbl.setBounds(Constants.OFFSETX + x * (Constants.WIDTH1 + Constants.WIDTH2 + Constants.SPACEX*3), Constants.OFFSETY + y*(Constants.HEIGHT + Constants.SPACEY), Constants.WIDTH1, Constants.HEIGHT);
		valueSpinner.setBounds(Constants.OFFSETX + x * (Constants.WIDTH1 + Constants.WIDTH2 + Constants.SPACEX*3) + Constants.WIDTH1 + Constants.SPACEX, Constants.OFFSETY + y*(Constants.HEIGHT + Constants.SPACEY), Constants.WIDTH2/2, Constants.HEIGHT);
		costsTf.setBounds(Constants.OFFSETX + x * (Constants.WIDTH1 + Constants.WIDTH2 + Constants.SPACEX*3) + Constants.WIDTH1 + Constants.WIDTH2/2 + Constants.SPACEX*2, Constants.OFFSETY + y*(Constants.HEIGHT + Constants.SPACEY), Constants.WIDTH2/2, Constants.HEIGHT);
		
		if (nameLbl.getNote() != null)
			nameLbl.getNote().actualizeBounds();
	}
	
	public void setBorders(Rectangle borders)
	{
		nameLbl.setBounds(borders);
		valueSpinner.setBounds((int)(borders.getX()) + Constants.WIDTH1 + Constants.SPACEX, (int)(borders.getY()), Constants.WIDTH2/2, Constants.HEIGHT);
		costsTf.setBounds((int)(borders.getX()) + Constants.WIDTH1 + Constants.WIDTH2/2 + Constants.SPACEX*2, (int)(borders.getY()), Constants.WIDTH2/2, Constants.HEIGHT);
		
		if (nameLbl.getNote() != null)
			nameLbl.getNote().actualizeBounds();
	}
	
	public void add(JPanel panel)
	{
		panel.add(nameLbl);
		panel.add(valueSpinner);
		panel.add(costsTf);
	}

	public MyTextField getCostsTf() {
		return costsTf;
	}

	public MyLabel getNameLbl() {
		return nameLbl;
	}

	public MySpinner getValueSpinner() {
		return valueSpinner;
	}

	public void setCostsTf(MyTextField costsTf) {
		this.costsTf = costsTf;
	}

	public void setNameLbl(MyLabel nameLbl) {
		this.nameLbl = nameLbl;
	}

	public void setValueSpinner(MySpinner valueSpinner) {
		this.valueSpinner = valueSpinner;
	}
	
	public void oneRowUp()
	{
		costsTf.setLocation(costsTf.getX(), costsTf.getY() + Constants.HEIGHT + Constants.SPACEY);
		if (costsTf.getNote() != null)
			costsTf.getNote().actualizeBounds();
		nameLbl.setLocation(nameLbl.getX(), nameLbl.getY() + Constants.HEIGHT + Constants.SPACEY);
		if (nameLbl.getNote() != null)
			nameLbl.getNote().actualizeBounds();
		valueSpinner.setLocation(valueSpinner.getX(), valueSpinner.getY() + Constants.HEIGHT + Constants.SPACEY);
		if (valueSpinner.getNote() != null)
			valueSpinner.getNote().actualizeBounds();
	}
	
	public void oneRowDown()
	{
		costsTf.setLocation(costsTf.getX(), costsTf.getY() - Constants.HEIGHT - Constants.SPACEY);
		if (costsTf.getNote() != null)
			costsTf.getNote().actualizeBounds();
		nameLbl.setLocation(nameLbl.getX(), nameLbl.getY() - Constants.HEIGHT - Constants.SPACEY);
		if (nameLbl.getNote() != null)
			nameLbl.getNote().actualizeBounds();
		valueSpinner.setLocation(valueSpinner.getX(), valueSpinner.getY() - Constants.HEIGHT - Constants.SPACEY);
		if (valueSpinner.getNote() != null)
			valueSpinner.getNote().actualizeBounds();
	}
	
	public String toString()
	{
		return skill.getNameStr();
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
		this.nameLbl.setText(skill.getNameStr());
		this.valueSpinner.setText(skill.getValueInt()+"");
		this.costsTf.setText(skill.getCosts(1, father, ((RaceModifiers)(father.getFather().getHero().getRace().getRaceModifiers().getFirst())).isGoodRunners(), father.getFather().getHero().getProperties()[Hero.PROP_TOUGHNESS].getValueInt())+"");
		
		if (skill.getSpecialization() != null)
			father.addSpec(nameLbl, skill.getSpecialization().getNameStr(), skill.getSpecialization().getValueInt());
	}
	
	public void removeYourself(SkillsPanel panel)
	{
		panel.remove(nameLbl);
		panel.remove(valueSpinner);
		panel.remove(costsTf);
	}
}
