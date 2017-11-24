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
import dataclasses.StyleFactory;

public class BuyNewAdvantageDialog 
	extends JDialog
	implements ActionListener, MouseListener, ListSelectionListener, KeyListener
{
	public static final int OFFARMEDMELEE = 0;
	public static final int DEFARMEDMELEE = 1;
	public static final int UNARMEDMELEE = 2;
	public static final int ARTILLARY = 3;
	public static final int MAGIC = 4;
	
	public static final int LISTWIDTH = 200;
	public static final int LISTHEIGHT = 400;
	public static final int LABELOFFSET = 22; // offsits to its "columns"
	
	DefaultListModel lmAvailable, lmChosen;
	
	JList listAvailable, listChosen;
	JScrollPane paneAvailable, paneChosen;
	
	JButton saveBt, cancelBt;
	
	JLabel lblAvailable, lblChosen;
	
	LinkedList<Advantage> returnList = new LinkedList<Advantage>();
	LinkedList<Advantage> alreadyBought = null;
	int mode;
	
	public BuyNewAdvantageDialog(MainWindow owner, int mode, LinkedList<Advantage> alreadyBought)
	{
		super(owner, true); // modal dialog
		setLayout(null);
		this.mode = mode;
		this.alreadyBought = alreadyBought;
		
		generateList(mode);
	
		paneAvailable = new JScrollPane(listAvailable);
		paneChosen = new JScrollPane(listChosen);

		saveBt = new JButton("wählen");
		cancelBt = new JButton("abbrechen");
		
		lblAvailable = new JLabel("Wählbar (Kosten, max. Anzahl)");
		lblChosen = new JLabel("Gewählte Vorteile");
		
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
		
		setSize(getPreferredSize());
		listAvailable.requestFocus();
	}

	public void generateList(int mode)
	{	
		lmAvailable = new DefaultListModel();
		lmChosen = new DefaultListModel();
		
		LinkedList<AdvantageInList> advantages;
		switch (mode)
		{
		case OFFARMEDMELEE: advantages = StyleFactory.buyArmedAdvantages(true); break;
		case DEFARMEDMELEE: advantages = StyleFactory.buyArmedAdvantages(false); break;
		case UNARMEDMELEE: advantages = StyleFactory.buyUnarmedAdvantages(); break;
		case ARTILLARY: advantages = StyleFactory.buyArtillaryAdvantages(); break;
		case MAGIC: advantages = StyleFactory.buyMagicAdvantages(); break;
		default: advantages = null;
		}
		
		Iterator<AdvantageInList> it = advantages.iterator();
		while (it.hasNext())
			lmAvailable.addElement(it.next());

		Iterator<Advantage> it2 = alreadyBought.iterator();
		while (it2.hasNext())
			lmChosen.addElement(new AdvantageInList(it2.next()));
		
		
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
					lmChosen.addElement(objs[i]);
			}
			else
			{
				Object[] objs = listChosen.getSelectedValues();
				for (int i = 0; i < objs.length; i++)
					lmChosen.removeElement(objs[i]);
			}
		}
	}
	public void mousePressed(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	
	public void close()
	{
		for (int i = 0; i < lmChosen.getSize(); i++)
			returnList.add((Advantage)(lmChosen.getElementAt(i)));
		
		dispose();
	}
	
	public LinkedList<Advantage> getReturnList()
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
				lmChosen.addElement(objs[i]);
		}													
		
		if (ke.getSource() == listChosen && ke.getKeyCode() == KeyEvent.VK_ENTER || ke.getKeyCode() == KeyEvent.VK_DELETE || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE)
		{
				Object[] objs = listChosen.getSelectedValues();
				for (int i = 0; i < objs.length; i++)
					lmChosen.removeElement(objs[i]);
		}
	}
	
	public void keyReleased(KeyEvent ke) 
	{
		
	}
}
