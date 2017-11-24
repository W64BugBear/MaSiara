	package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import character.Skill;

import background.Constants;

public class NewSkillDialog 
	extends JDialog
	implements ActionListener, KeyListener
{
	JLabel nameLbl, typeLbl;
	JTextField nameTf, typeTf = null;
	JComboBox typeCombo = null;
	String[] typeStrings = {"normal", "Theorie", "komplex", "elitär"};
//	, groupStrings = {"Gesellschaft", "Heimlichkeit", "Handwerk", "Körper", "Wildnis", "Theorie", "Sonstige", "keine"};
	boolean ctrlPressed = false;
	int mode;
	
	public NewSkillDialog(MainWindow owner, int mode)
	{
		super(owner, true);		// modal Dialog
	
		this.mode = mode;
		setTitle("neue Fertigkeit");
		
		nameLbl = new JLabel("Name");
		typeLbl = new JLabel("Typ");
		
		nameTf = new JTextField();
		if (mode == Skill.TYPE_NORMAL)
			typeCombo = new JComboBox(typeStrings);
		if (mode == Skill.TYPE_LANGUAGE)
			typeTf = new JTextField("Sprache");
		if (mode == Skill.TYPE_LIMITATION)
			typeTf = new JTextField("Einschränkung");
		if (mode == Skill.TYPE_FONT)
			typeTf = new JTextField("Schrift");

//		if (mode == Skill.TYPE_NORMAL)
//			groupCombo = new JComboBox(groupStrings);
//		else
//			groupTf = new JTextField("keine");
		
		JButton button = new JButton("OK");
		JButton buttonCancel = new JButton("Abbrechen");
		
		setLayout(null);
		
		nameLbl.setBounds(Constants.OFFSETX, Constants.OFFSETY, Constants.WIDTH1, Constants.HEIGHT);
		typeLbl.setBounds(Constants.OFFSETX + Constants.WIDTH1 + Constants.SPACEX, Constants.OFFSETY, Constants.WIDTH1, Constants.HEIGHT);
//		groupLbl.setBounds(Constants.OFFSETX + Constants.WIDTH1 + Constants.WIDTH1 + Constants.SPACEX*2, Constants.OFFSETY, Constants.WIDTH1, Constants.HEIGHT);
		
		nameTf.setBounds(Constants.OFFSETX, Constants.OFFSETY + Constants.HEIGHT + Constants.SPACEY, Constants.WIDTH1, Constants.HEIGHT);
		if (mode == Skill.TYPE_NORMAL)
			typeCombo.setBounds(Constants.OFFSETX + Constants.WIDTH1 + Constants.SPACEX, Constants.OFFSETY + Constants.HEIGHT + Constants.SPACEY, Constants.WIDTH1, Constants.HEIGHT);
		else
			typeTf.setBounds(Constants.OFFSETX + Constants.WIDTH1 + Constants.SPACEX, Constants.OFFSETY + Constants.HEIGHT + Constants.SPACEY, Constants.WIDTH1, Constants.HEIGHT);
//		if (mode == Skill.TYPE_NORMAL)
//			groupCombo.setBounds(Constants.OFFSETX + Constants.WIDTH1 + Constants.WIDTH1 + Constants.SPACEX*2, Constants.OFFSETY + Constants.HEIGHT + Constants.SPACEY, Constants.WIDTH1, Constants.HEIGHT);
//		else
//			groupTf.setBounds(Constants.OFFSETX + Constants.WIDTH1 + Constants.WIDTH1 + Constants.SPACEX*2, Constants.OFFSETY + Constants.HEIGHT + Constants.SPACEY, Constants.WIDTH1, Constants.HEIGHT);

		button.setBounds(Constants.OFFSETX, Constants.OFFSETY + Constants.HEIGHT + Constants.SPACEY + Constants.HEIGHT + Constants.SPACEY, Constants.WIDTH1, Constants.HEIGHT);
		buttonCancel.setBounds(Constants.OFFSETX + Constants.WIDTH1 + Constants.SPACEX, Constants.OFFSETY + Constants.HEIGHT + Constants.SPACEY + Constants.HEIGHT + Constants.SPACEY, Constants.WIDTH1, Constants.HEIGHT);
		button.addActionListener(this);
		button.addKeyListener(this);
		buttonCancel.addActionListener(this);
		buttonCancel.addKeyListener(this);
		
		nameTf.addActionListener(this);
		nameTf.addKeyListener(this);

//		if (mode == Skill.TYPE_NORMAL)
//			groupCombo.addKeyListener(this);
//		else
//			groupTf.setEditable(false);
		if (mode == Skill.TYPE_NORMAL)
			typeCombo.addKeyListener(this);
		else
			typeTf.setEditable(false);
		
		add(nameLbl);
		add(nameTf);
//		if (mode == Skill.TYPE_NORMAL)
//			add(groupCombo);
//		else
//			add(groupTf);
		add(typeLbl);
		if (mode == Skill.TYPE_NORMAL)
			add(typeCombo);
		else
			add(typeTf);
//		add(groupLbl);
		add(button);
		add(buttonCancel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getActionCommand().startsWith("A"))
			mode = 0;
		dispose();
	}
	
	public void keyTyped(KeyEvent ke){/*nothing*/}
	
	public void keyPressed(KeyEvent ke)
	{
		if (ke.getKeyCode() == KeyEvent.VK_CONTROL)
			ctrlPressed = true;
		else
		{
			if (ctrlPressed && ke.getKeyCode() == KeyEvent.VK_S)
				dispose();
			ctrlPressed = false;
		}
		
		if (ke.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			mode = 0;
			dispose();
		}
	}
	
	public void keyReleased(KeyEvent ke){
		ctrlPressed = false;
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(
				(typeCombo == null ? 
						typeTf.getX() + typeTf.getWidth() :
							typeCombo.getX() + typeCombo.getWidth())
				+ Constants.OFFSETX*2, 2* (Constants.OFFSETY + Constants.HEIGHT + Constants.SPACEY)+Constants.HEIGHT+45);
	}
	
	public Skill getSkill()
	{
		if (mode == 0)
			return null;
		if (mode == Skill.TYPE_NORMAL)
			return new Skill(nameTf.getText(), false, 0, null, typeCombo.getSelectedIndex()+1, typeCombo.getSelectedIndex()+1);
		else
			return new Skill(nameTf.getText(), false, 0, null, mode, Skill.GROUP_NONE);
	}
}
