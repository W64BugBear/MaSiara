package gui;

import guidialogelements.MySpinner;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputStream;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import background.Constants;
import background.Parser;
import background.SerializableInformation;

public class StartDialog 
	extends JDialog
	implements ActionListener, ItemListener, ListSelectionListener, MouseListener
{
	public static final int LISTWIDTH = 200;
	public static final int LISTHEIGHT = 200;
	public static final int LABELOFFSET = 22; // offsets to its "columns"
	
	DefaultListModel def;
	
	JList list;
	JScrollPane listScrollPane;
	
	JCheckBox newCheckbox;	
	
	JLabel epLabel;
	JTextField epTf;
	
	JButton saveBt;
	JButton delBt;
	
	JLabel note;
	
	public StartDialog(MainWindow owner)
	{
		super(owner, true); // modal dialog
		setLayout(null);
		
		generateList();
		listScrollPane = new JScrollPane(list);
		newCheckbox = new JCheckBox("neuer Held", true);
		epLabel = new JLabel("EP frei:");
		epTf = new JTextField("4000");
		saveBt = new JButton("wählen");
		delBt = new JButton("löschen");
		note = new JLabel("Ändere hier die EP eines Helden!");
		
		list.addListSelectionListener(this);
		list.addMouseListener(this);
		newCheckbox.addItemListener(this);
		saveBt.addActionListener(this);
		delBt.addActionListener(this);
		epTf.addActionListener(this);

		listScrollPane.setBounds(Constants.OFFSETX, Constants.OFFSETY, LISTWIDTH, LISTHEIGHT);
		
		newCheckbox.setBounds(Constants.OFFSETX + LISTWIDTH + Constants.SPACEX, Constants.OFFSETY + 0 * (Constants.HEIGHT + Constants.SPACEY), Constants.WIDTH2, Constants.HEIGHT);
		epLabel.setBounds(Constants.OFFSETX + LISTWIDTH + Constants.SPACEX, Constants.OFFSETY + 1 * (Constants.HEIGHT + Constants.SPACEY), Constants.WIDTH2, Constants.HEIGHT);
		
		epTf.setBounds(epLabel.getX() + epLabel.getWidth() + Constants.SPACEX, newCheckbox.getY() + (Constants.HEIGHT + Constants.SPACEY), Constants.WIDTH2/2, Constants.HEIGHT);
		
		delBt.setBounds(newCheckbox.getX(), listScrollPane.getY() + listScrollPane.getHeight() - Constants.HEIGHT*2 - Constants.SPACEY, Constants.WIDTH2, Constants.HEIGHT);
		saveBt.setBounds(newCheckbox.getX(), listScrollPane.getY() + listScrollPane.getHeight() - Constants.HEIGHT, Constants.WIDTH2, Constants.HEIGHT);
		
		note.setBounds(Constants.OFFSETX, listScrollPane.getY() + listScrollPane.getHeight(), LISTWIDTH + Constants.WIDTH2*2, Constants.HEIGHT);
		
		add(listScrollPane);
		add(newCheckbox);
		add(epLabel);
		add(epTf);
		add(saveBt);
		add(delBt);
		add(note);
	}

	public void generateList()
	{	// load heroes
		def = new DefaultListModel();
		
		File directory = new File(Constants.RUNTIMESTRING);
		String[] files = directory.list();
		
		for (int i = 0; i < files.length; i++)
			def.addElement(files[i].substring(0, files[i].length()-8)); // cut away .masiara
		
		list = new JList(def);
	}
	
	// this is NOT JComponent.getPreferredSize!
	public Dimension getPreferredSize()
	{
		return new Dimension(epTf.getX() + epTf.getWidth() + Constants.OFFSETX*2, note.getY() + note.getHeight() + Constants.OFFSETY + 45);
		// i think the 45 are the title
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if ((ae.getSource() == saveBt || ae.getSource() == epTf))
		{
			close(); // disposes, if allowed
			return;
		}
		
		try {	// delete button pressed
			Object[] selVals = list.getSelectedValues();
			int[] selInd = list.getSelectedIndices();
			for (int i = 0; i < selVals.length; i++)
			{
				File file = new File(Constants.HEROSTRING + "//"  + (String)(selVals[i]) + ".hero");
				long time = System.currentTimeMillis();
				while (!file.delete())
					if (System.currentTimeMillis() - time > 5000)
						return;
				file = new File(Constants.RUNTIMESTRING + "//"  + (String)(selVals[i]) + ".masiara");
				while (!file.delete())
					if (System.currentTimeMillis() - time > 3000)
						return;
				
				def.removeElementAt(selInd[i]-i);
				if (def.getSize() == 0)
					newCheckbox.setSelected(true);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
 	public void itemStateChanged(ItemEvent ie)
	{
 		// newCheckbox hit
 		
		if (newCheckbox.isSelected())
			epTf.setText("4000");
		else
			valueChanged(null);
	}
	
	public void valueChanged(ListSelectionEvent lse)
	{
		try {	// load act gp and ep from file
			if (list.getSelectedIndex() == -1)
				list.setSelectedIndex(0);

			File file = new File(Constants.RUNTIMESTRING + "//" + list.getSelectedValue() + ".masiara");
			
			BufferedReader reader = new BufferedReader(new FileReader(file));
			int version = Integer.parseInt(reader.readLine());
			SerializableInformation si = Parser.deparseSInf(reader.readLine(), version);
			reader.close();
			
			epTf.setText(si.getEpInt()+"");
				
		} catch (Exception ex) {}
		newCheckbox.setSelected(false);
		
	}
	
	public void mouseClicked(MouseEvent me) {
		if (me.getClickCount() > 1 )	// double click
			close();
	}
	public void mousePressed(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	
	public String getSelectedHero()
	{
		if (newCheckbox.isSelected())
			return null;
		return (String)(list.getSelectedValue());
	}
	
//	public int getEp()
//	{
//		if (epCheckbox.isSelected())
//			try {
//				File file = new File(Constants.RUNTIMESTRING + "//" + list.getSelectedValue() + ".dat");
//				ObjectInputStream os = new ObjectInputStream(new FileInputStream(file));
//
//				SerializableInformation si = ((SerializableInformation)(os.readObject()));
//				int ep = si.getEpInt();
//				os.close();
//				
//				return ep;
//			} catch (Exception ex)
//			{
//				return 0;
//			}
//		else
//			return 0;
//	}
//	
//	public int getGP()
//	{
//		if (gpCheckbox.isSelected())
//			try {
//				File file = new File(Constants.RUNTIMESTRING + "//" + list.getSelectedValue() + ".dat");
//				ObjectInputStream os = new ObjectInputStream(new FileInputStream(file));
//
//				SerializableInformation si = ((SerializableInformation)(os.readObject()));
//				int gp = si.getGpInt();
//				os.close();
//				
//				return gp;
//			} catch (Exception ex)
//			{
//				return 0;
//			}
//		else
//			return 0;
//	}

	public int getEp()
	{
		try {
			return Integer.parseInt(epTf.getText());
		} catch (NumberFormatException nfe)
		{
			return 0;
		}
	}
		
	public void close()
	{
		int gp = 0, ep = 0;
		try {
				ep = Integer.parseInt(epTf.getText());
			} catch (NumberFormatException nfe)
			{
				epTf.requestFocus();
				return;
			}
		dispose();
	}
}
