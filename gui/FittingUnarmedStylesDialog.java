package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import character.Style;

public class FittingUnarmedStylesDialog 
	extends JDialog
	implements ActionListener, MouseListener, ListSelectionListener
{
	public static final int ARMEDMELEE = 0;
	public static final int UNARMEDMELEE = 1;
	
	public static final int LISTWIDTH = 200;
	public static final int LISTHEIGHT = 100;
	public static final int LABELOFFSET = 22; // offsits to its "columns"
	
	DefaultListModel fittingListModel;
	
	JList fittingList;
	JScrollPane fittingPane;
	
	JButton saveBt, cancelBt;
	
	JLabel fittingLabel;
	
	String returnString = null;
	
	int mode;
	
	public FittingUnarmedStylesDialog(MainWindow owner, Style style, int mode)
	{
		super(owner, true); // modal dialog
		setLayout(null);
		this.mode = mode;
		
		generateLists(style.getFittingStylesStrLl());
		fittingPane = new JScrollPane(fittingList);
		saveBt = new JButton("wählen");
		cancelBt = new JButton("abbrechen");
		if (mode == ARMEDMELEE)
			fittingLabel = new JLabel("Für Meisterparade bevorzugte Stile");
		else
			fittingLabel = new JLabel("Geeignete bewaffnete Stile");
		
		fittingList.addMouseListener(this);
		
		fittingList.addListSelectionListener(this);
		
		saveBt.addActionListener(this);
		cancelBt.addActionListener(this);

		fittingLabel.setBounds(Constants.OFFSETX, Constants.OFFSETY, Constants.WIDTH2*3, Constants.HEIGHT);
		fittingPane.setBounds(Constants.OFFSETX, Constants.OFFSETY + Constants.HEIGHT + Constants.SPACEY, LISTWIDTH, LISTHEIGHT);
		
		cancelBt.setBounds(Constants.OFFSETX + LISTWIDTH + Constants.SPACEX, fittingPane.getY() + LISTHEIGHT - Constants.HEIGHT, Constants.WIDTH2, Constants.HEIGHT);
		saveBt.setBounds(Constants.OFFSETX + LISTWIDTH + Constants.SPACEX, fittingPane.getY() + LISTHEIGHT - Constants.HEIGHT*2 - Constants.SPACEY, Constants.WIDTH2, Constants.HEIGHT);
		
		add(fittingPane);
		add(saveBt);
		add(cancelBt);
		add(fittingLabel);
	}

	public void generateLists(LinkedList<String> fitting)
	{	
		fittingListModel = new DefaultListModel();
		
		Iterator<String> it = fitting.iterator();
		while (it.hasNext())
			fittingListModel.addElement(it.next());

		fittingList = new JList(fittingListModel);
	}
	
	// this is NOT JComponent.getPreferredSize!
	public Dimension getPreferredSize()
	{
		return new Dimension(cancelBt.getX() + cancelBt.getWidth() + Constants.OFFSETX*2, cancelBt.getY() + cancelBt.getHeight() + Constants.OFFSETY + 45);
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
			close();
	}
	public void mousePressed(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	
	public void close()
	{
		if (fittingList.getSelectedIndex() != -1)
			if (mode == ARMEDMELEE)
				returnString = (String)(fittingList.getSelectedValue());
			else
				returnString = ((String)(fittingList.getSelectedValue())).substring(0, ((String)(fittingList.getSelectedValue())).length()-4);
		
		dispose();
	}
	
	public String getReturnString()
	{
		return returnString;
	}
	
	public void valueChanged(ListSelectionEvent lse)
	{
//		if (lse.getSource() == fittingList)
//			unfittingList.clearSelection();
//		
//		if (lse.getSource() == unfittingList)
//			fittingList.clearSelection();
		
		if (fittingList.getSelectedIndex() == -1) //  && unfittingList.getSelectedIndex() == -1
			saveBt.setEnabled(false);
		else
			saveBt.setEnabled(true);
	}
}
