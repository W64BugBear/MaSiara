package gui;

import guidialogelements.MyLabel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import character.Item;
import character.Specialization;

import races.Race;
import background.Constants;

public class EquipmentPanel 
	extends MyPanel
	implements ActionListener, MouseListener, KeyListener, ItemListener
{
	JScrollPane jscrollpane;
	MainWindow father;
	JTextField moneyTf;
	JLabel topicLbl, dublonsLbl;
	JList chosenList;
	ButtonGroup buttonGroup;
	JRadioButton[] radioButtons = new JRadioButton[5];
	DefaultListModel dlm = new DefaultListModel();
	JButton exitButton, newButton, sellButton;
	
	boolean ctrlPressed = false;
	
	public EquipmentPanel(MainWindow father)
	{
		super(father);
		this.father = father;
		setLayout(null);
	}	
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == exitButton)
		{
			father.displayStartPanel();
			return;
		}
		
		if (ae.getSource() == newButton)
		{
			NewItemDialog nid= new NewItemDialog(father);
			nid.setLocation(newButton.getX()+father.getX(), newButton.getY()+father.getY()-father.getScrollPane().getVerticalScrollBar().getValue());
			nid.setSize(nid.getPreferredSize());
			nid.setVisible(true);
			
			if (nid.getItem() != null)
			{
				dlm.addElement(nid.getItem());
				father.getHero().getItemsLl().add(nid.getItem());
			}
		}
		
		if (ae.getSource() == sellButton)
		{
			dlm.removeElementAt(chosenList.getSelectedIndex());
		}
	}
	
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	public void mousePressed(MouseEvent me) {	}
	public void mouseReleased(MouseEvent me) {}
	public void mouseClicked(MouseEvent me) {
		if (me.getClickCount() > 1)
			father.displayStartPanel();
	}
	
	public void keyReleased(KeyEvent ke) {}
	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == ke.VK_CONTROL)
			ctrlPressed = true;
		else
		{
			if (ctrlPressed)
				if (ke.getKeyCode() == ke.VK_S)
					father.displayStartPanel();
			ctrlPressed = false;
		}
	}
	public void keyTyped(KeyEvent ke) {}
	
	public void setSpecializationByText(String text, String note, Specialization spec) {}
	public void addComponents()
	{
		for (int i = 0; i < 5; i++)
			add(radioButtons[i]);
		add(jscrollpane);
		add(moneyTf);
		add(dublonsLbl);
		add(newButton);
		add(exitButton);
		add(topicLbl);
		add(sellButton);
	}
	
	public LinkedList getNotes() {return null;}
	public LinkedList getSpecIcons() {return null;}
	public JComponent getFocusComponentInClickedRow(JComponent comp) {return exitButton; }
	public MyLabel getTopicInClickedRow(JComponent component) {return null; }
	public String serializeYourself()
	{
		String ret = new String("equip;");
		
		int index = 0;
		for (int i = 0; i < 5; i++)
			if (radioButtons[i].isSelected())
				index = i;
		
		ret += moneyTf.getText() + ";"
		 + index;
		
		return ret;
	}
	public void deserializeYourself(String s, int version)
	{
		deserializeYourself();
		
		StringTokenizer t = new StringTokenizer(s, ";");
		
		if (!t.nextToken().equals("equip"))
		{
			father.getStatusLabel().setText("EquipmentPanel falsch deserialisiert");
			return;
		}
		
		moneyTf.setText(t.nextToken());
		radioButtons[Integer.parseInt(t.nextToken())].setSelected(true);
	}
	
	public void deserializeYourself()
	{
		buttonGroup = new ButtonGroup();
		
		int[] steps = new int[5];

		if (father.getHero().getRace().getEquipmentInt() == Race.POOR)
		{
			steps[0] = 10;
			steps[1] = 100;
			steps[2] = 200;
			steps[3] = 400;
			steps[4] = 600;
		}
		
		if (father.getHero().getRace().getEquipmentInt() == Race.RICH)
		{
			steps[0] = 15;
			steps[1] = 150;
			steps[2] = 300;
			steps[3] = 600;
			steps[4] = 1000;
		}
		
		if (father.getHero().getRace().getEquipmentInt() == Race.DWARF)
		{
			steps[0] = 30;
			steps[1] = 200;
			steps[2] = 500;
			steps[3] = 800;
			steps[4] = 1500;
		}
		
		for (int i = 0; i < 5; i++)
		{
			radioButtons[i] = new JRadioButton(steps[i]+"");
			buttonGroup.add(radioButtons[i]);
			radioButtons[i].setBounds(Constants.OFFSETX, Constants.OFFSETY + (5-i)*(Constants.HEIGHT + Constants.SPACEY), Constants.WIDTH2*2-Constants.WIDTH1, Constants.HEIGHT);
			radioButtons[i].addKeyListener(this);
			radioButtons[i].addItemListener(this);
		}
		
		radioButtons[0].setSelected(true);
		
		moneyTf = new JTextField(steps[0]+"");
		moneyTf.setBounds(Constants.OFFSETX + Constants.WIDTH2 + Constants.SPACEX, Constants.OFFSETY, Constants.WIDTH2, Constants.HEIGHT);
		moneyTf.addKeyListener(this);
		
		topicLbl = new JLabel("Startvermögen");
		topicLbl.setBounds(Constants.OFFSETX, Constants.OFFSETY, Constants.WIDTH2, Constants.HEIGHT);
		
		dublonsLbl= new JLabel("Dublonen");
		dublonsLbl.setBounds(Constants.OFFSETX + Constants.WIDTH2*2 + Constants.SPACEX*2, Constants.OFFSETY, Constants.WIDTH2, Constants.HEIGHT);
		

		chosenList = new JList(dlm);
		jscrollpane= new JScrollPane(chosenList);
		jscrollpane.setBounds(Constants.OFFSETX + Constants.WIDTH2*2 - Constants.WIDTH1 + Constants.SPACEX, Constants.OFFSETY + Constants.HEIGHT*2 + Constants.SPACEY*2, Constants.WIDTH1+Constants.WIDTH2-Constants.OFFSETX, Constants.HEIGHT*4 + Constants.SPACEY*3);
		chosenList.addKeyListener(this);
		
		exitButton = new JButton("Speichern");
		exitButton.setBounds(Constants.OFFSETX, Constants.OFFSETY + Constants.HEIGHT*7 + Constants.SPACEY*6, Constants.WIDTH2, Constants.HEIGHT);
		exitButton.addActionListener(this);
		exitButton.addKeyListener(this);
		
		newButton = new JButton("kaufen");
		newButton.setBounds(Constants.OFFSETX + Constants.WIDTH2 + Constants.SPACEX, Constants.OFFSETY + Constants.HEIGHT*7 + Constants.SPACEY*6, Constants.WIDTH2, Constants.HEIGHT);
		newButton.addActionListener(this);
		newButton.addKeyListener(this);		
		
		sellButton = new JButton("verkaufen");
		sellButton.setBounds(Constants.OFFSETX + Constants.WIDTH2*2 + Constants.SPACEX*2, Constants.OFFSETY + Constants.HEIGHT*7 + Constants.SPACEY*6, Constants.WIDTH2, Constants.HEIGHT);
		sellButton.addActionListener(this);
		sellButton.addKeyListener(this);
		
		addComponents();
	}
	
	public JComponent getDefaultFocusComponent() {return exitButton;}
	
	public String checkForErrors() {return "alles in Butter";}

	public Dimension getPreferredSize()
	{
		return new Dimension(sellButton.getX() + sellButton.getWidth() + Constants.OFFSETX, sellButton.getY() + sellButton.getHeight() + Constants.OFFSETY);
	}
	
	public void itemStateChanged (ItemEvent ie)
	{
		int index = 0;
		for (int i = 0; i < 5; i++)
			if (radioButtons[i] == ie.getSource())
				index = i;
		
		father.getSInf().increaseEP(175*father.getSInf().getGpInvestedInMoney() - 175*index);
		father.getSInf().setGpInvestedInMoney(index);
		setGPEPLabel();
	}
	
	public void refreshItemList()
	{
		dlm.removeAllElements();
		
		Iterator<Item> iter = father.getHero().getItemsLl().iterator();
		while (iter.hasNext())
			dlm.addElement(iter.next());

	}
}
