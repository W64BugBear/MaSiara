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
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import background.Constants;
import character.Advantage;
import character.AdvantageInList;
import dataclasses.SkillLists;
import dataclasses.StyleFactory;

public class ChooseYourSpellSkillsDialog 
	extends JDialog
	implements ActionListener, MouseListener, ListSelectionListener, KeyListener
{
	public static final int LISTWIDTH = 200;
	public static final int LISTHEIGHT = 400;
	public static final int LABELOFFSET = 22; // offsets to its "columns"
	
	DefaultListModel lmAvailable, lmChosen;
	
	JList listAvailable, listChosen;
	JScrollPane paneAvailable, paneChosen;
	
	JButton saveBt, cancelBt;
	
	JLabel lblAvailable, lblChosen;
	
	LinkedList<String> returnList;
	LinkedList<Integer> spellSkills = null;
	int freeSkills;
	
	public ChooseYourSpellSkillsDialog (MainWindow owner, LinkedList<Integer> spellSkills, int freeSkills)
	{
		super(owner, true); // modal dialog
		setLayout(null);
		this.spellSkills = spellSkills;
		this.freeSkills = freeSkills;
		
		generateList();
	
		paneAvailable = new JScrollPane(listAvailable);
		paneChosen = new JScrollPane(listChosen);

		saveBt = new JButton("wählen");
		cancelBt = new JButton("abbrechen");
		
		lblAvailable = new JLabel("Wählbar");
		lblChosen = new JLabel("Gewählt");
		
		listAvailable.addMouseListener(this);
		listAvailable.addListSelectionListener(this);
		listAvailable.addKeyListener(this);
		listChosen.addMouseListener(this);
		listChosen.addListSelectionListener(this);
		listChosen.addKeyListener(this);
		
		saveBt.addActionListener(this);
		cancelBt.addActionListener(this);

		lblAvailable.setBounds(Constants.OFFSETX, Constants.OFFSETY, LISTWIDTH, Constants.HEIGHT);
		paneAvailable.setBounds(Constants.OFFSETX, Constants.OFFSETY + Constants.HEIGHT + Constants.SPACEY, LISTWIDTH, LISTHEIGHT);
		
		lblChosen.setBounds(Constants.OFFSETX + Constants.SPACEX + LISTWIDTH, Constants.OFFSETY, LISTWIDTH, Constants.HEIGHT);
		paneChosen.setBounds(Constants.OFFSETX + Constants.SPACEX + LISTWIDTH, Constants.OFFSETY + Constants.HEIGHT + Constants.SPACEY, LISTWIDTH, LISTHEIGHT);
		
		cancelBt.setBounds(Constants.OFFSETX+ LISTWIDTH/2 - Constants.WIDTH2/2 + LISTWIDTH + Constants.SPACEX, paneAvailable.getY() + LISTHEIGHT + Constants.SPACEY, Constants.WIDTH2, Constants.HEIGHT);
		saveBt.setBounds(Constants.OFFSETX+ LISTWIDTH/2 - Constants.WIDTH2/2, paneAvailable.getY() + LISTHEIGHT + Constants.SPACEY, Constants.WIDTH2, Constants.HEIGHT);
		
		add(paneAvailable);
		add(paneChosen);
		add(saveBt);
		add(cancelBt);
		add(lblAvailable);
		add(lblChosen);

		saveBt.setEnabled(false);
		
		setSize(getPreferredSize());
		listAvailable.requestFocus();
	}

	public void generateList()
	{	
		lmAvailable = new DefaultListModel();
		lmChosen = new DefaultListModel();
		
		Iterator<Integer> iterator = spellSkills.iterator();
		iterator.next();
		iterator.next();	// skip spell force & spell control and the scrollbar
		iterator.next();
		
		String[] spells = SkillLists.getSpellSkills();
		
		for (int i = 0; i < spells.length; i++)
		{
			int val = iterator.next();
			if (val == -2)
				lmAvailable.addElement(spells[i]);
			else
				lmChosen.addElement(spells[i] + ": " + val+" "); // the blank is a marker to recognize it later on
		}
		
		listAvailable = new JList(lmAvailable);
		listChosen = new JList(lmChosen);
	}
	
	// this is NOT JComponent.getPreferredSize!
	public Dimension getPreferredSize()
	{
		return new Dimension(paneChosen.getX() + LISTWIDTH + Constants.OFFSETX*2, cancelBt.getY() + cancelBt.getHeight() + Constants.OFFSETY + 45);
		// i think the 45 are the title
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == cancelBt)
			dispose();
		if (ae.getSource() == saveBt)
			close();
	}
	
	public void mouseClicked(MouseEvent me) {
		if (me.getClickCount() > 1 )	// double click
		{
			if (me.getSource() == listAvailable)
			{
				Object[] objs = listAvailable.getSelectedValues();
				for (int i = 0; i < objs.length; i++)
				{
					lmChosen.addElement(objs[i]);
					lmAvailable.removeElement(objs[i]);
				}
			}
			else
			{
				Object[] objs = listChosen.getSelectedValues();
				for (int i = 0; i < objs.length; i++)
				{
					lmAvailable.addElement(objs[i]);
					lmChosen.removeElement(objs[i]);
				}
			}
		}
		
		if (lmChosen.size() < freeSkills)
			saveBt.setEnabled(false);
		else
			saveBt.setEnabled(true);
	}
	public void mousePressed(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	
	public void close()
	{
		returnList = new LinkedList<String>();
		
		for (int i = 0; i < lmChosen.getSize(); i++)
			returnList.add((String)(lmChosen.getElementAt(i)));
		
		if (returnList.size() < freeSkills)
			returnList = null;
		
		dispose();
	}
	
	public LinkedList<String> getReturnList()
	{
		return returnList;
	}
	
	public void valueChanged(ListSelectionEvent lse) {}
	
	public void keyTyped (KeyEvent ke) {}
	
	public void keyPressed (KeyEvent ke) {
		if (ke.getSource() == listAvailable && ke.getKeyCode() == KeyEvent.VK_ENTER)
		{
			Object[] objs = listAvailable.getSelectedValues();
			for (int i = 0; i < objs.length; i++)
			{
				lmChosen.addElement(objs[i]);
				lmAvailable.removeElement(objs[i]);
			}
		}													
		
		if (ke.getSource() == listChosen && ke.getKeyCode() == KeyEvent.VK_ENTER || ke.getKeyCode() == KeyEvent.VK_DELETE || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE)
		{
				Object[] objs = listChosen.getSelectedValues();
				for (int i = 0; i < objs.length; i++)
				{
					lmAvailable.addElement(objs[i]);
					lmChosen.removeElement(objs[i]);
				}
		}
		if (lmChosen.size() < freeSkills)
			saveBt.setEnabled(false);
		else
			saveBt.setEnabled(true);
	}
	
	public void keyReleased(KeyEvent ke) 
	{
		
	}
}
