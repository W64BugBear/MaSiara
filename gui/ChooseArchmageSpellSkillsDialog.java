package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import background.Constants;
import dataclasses.SkillLists;

public class ChooseArchmageSpellSkillsDialog 
	extends JDialog
	implements ActionListener, MouseListener, ListSelectionListener, KeyListener
{
	public static final int LISTWIDTH = 200;
	public static final int LISTHEIGHT = 400;
	public static final int LABELOFFSET = 22; // offsets to its "columns"
	
	DefaultListModel lmAvailable;
	
	JList listAvailable;
	JScrollPane paneAvailable;
	
	JButton saveBt, cancelBt;
	
	JLabel lblAvailable;
	
	String retStr = null;
	LinkedList<Integer> spellSkills = null;
	boolean openWindow = false;	
	
	public ChooseArchmageSpellSkillsDialog (MainWindow owner, LinkedList<Integer> spellSkills)
	{
		super(owner, true); // modal dialog
		setLayout(null);
		this.spellSkills = spellSkills;
		
		generateList();
	
		if (listAvailable == null)
		{
			JOptionPane.showMessageDialog(this, "Du hast keine Zauberfertigkeit auf 15!");
			dispose();
			return;
		}
		else
			openWindow = true;
		
		paneAvailable = new JScrollPane(listAvailable);
		
		saveBt = new JButton("wählen");
		cancelBt = new JButton("abbrechen");
		
		lblAvailable = new JLabel("Wählbar");
		
		listAvailable.addMouseListener(this);
		listAvailable.addListSelectionListener(this);
		listAvailable.addKeyListener(this);
		
		saveBt.addActionListener(this);
		cancelBt.addActionListener(this);

		lblAvailable.setBounds(Constants.OFFSETX, Constants.OFFSETY, LISTWIDTH, Constants.HEIGHT);
		paneAvailable.setBounds(Constants.OFFSETX, Constants.OFFSETY + Constants.HEIGHT + Constants.SPACEY, LISTWIDTH, LISTHEIGHT);
		
		saveBt.setBounds(Constants.OFFSETX, paneAvailable.getY() + LISTHEIGHT + Constants.SPACEY, LISTWIDTH/2-Constants.SPACEX/2, Constants.HEIGHT);
		cancelBt.setBounds(Constants.OFFSETX + LISTWIDTH/2+Constants.SPACEX/2, paneAvailable.getY() + LISTHEIGHT + Constants.SPACEY, LISTWIDTH/2+Constants.SPACEX/2, Constants.HEIGHT);
		
		add(paneAvailable);
		add(saveBt);
		add(cancelBt);
		add(lblAvailable);
		
		saveBt.setEnabled(false);
		setSize(getPreferredSize());
		listAvailable.requestFocus();
	}

	public boolean openWindow() {
		return openWindow;
	}

	public void generateList()
	{	
		lmAvailable = new DefaultListModel();
		
		Iterator<Integer> iterator = spellSkills.iterator();
		iterator.next();
		iterator.next();	// skip spell force & spell control and the scrollbar
		iterator.next();
		
		String[] spells = SkillLists.getSpellSkills();
//		String[] addies = SkillLists.getAdditionalSpellSkills();

		boolean foundOne = false;
		for (int i = 0; i < spells.length; i++)
		{
			int val = iterator.next();
			if (val >= 15)
			{
				lmAvailable.addElement(spells[i]);
				foundOne = true;
			}
		}
				
//		for (int i = 0; i < addies.length; i++)
//		{
//			int val = iterator.next();
//			if (val >= 15)
//			{
//				lmAvailable.addElement(addies[i]);
//				foundOne = true;
//			}
//		}
//		
//		if (!foundOne)
//			return;

		
		listAvailable = new JList(lmAvailable);
	}
	
	// this is NOT JComponent.getPreferredSize!
	public Dimension getPreferredSize()
	{
		return new Dimension(paneAvailable.getX() + LISTWIDTH + Constants.OFFSETX*2, cancelBt.getY() + cancelBt.getHeight() + Constants.OFFSETY + 45);
		// i think the 45 are the title
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == cancelBt)
			retStr = null;
		dispose();
	}
	
	public void mouseClicked(MouseEvent me) {
		saveBt.setEnabled(true);
		retStr = (String)(listAvailable.getSelectedValue());
		if (me.getClickCount() > 1)	// double click
			dispose();
	}

	public void mousePressed(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	
	public void valueChanged(ListSelectionEvent lse) {}
	
	public void keyTyped (KeyEvent ke) {}
	
	public void keyPressed (KeyEvent ke) {
		retStr = (String)(listAvailable.getSelectedValue());
		saveBt.setEnabled(true);
	}
	
	public void keyReleased(KeyEvent ke) 
	{
		
	}
	
	public String getChosenSkill() {
		return "Erz-"+retStr;
	}
}
