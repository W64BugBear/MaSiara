package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;

import background.Constants;

public class From7OnDialog 
	extends JDialog
	implements ActionListener, MouseListener, KeyListener
{
	JList list;
	JButton button;
	MainWindow father;
	
	public From7OnDialog(MainWindow father, ArrayList<String> arrayList)
	{
		super(father, true);
		list = new JList(arrayList.toArray());
		list.setSelectedIndex(0);
		button = new JButton("speichern");
		this.father = father;
		
		list.addMouseListener(this);
		list.addKeyListener(this);
		button.addActionListener(this);
		
		JScrollPane scrollPane = new JScrollPane(list);
		add(scrollPane);
		add(button);
		
		setLayout(null);
		
		scrollPane.setBounds(Constants.OFFSETX, Constants.OFFSETY, Constants.WIDTH1, Constants.HEIGHT*4 + Constants.SPACEY*3);
		button.setBounds(Constants.OFFSETX, Constants.OFFSETY + Constants.HEIGHT*4 + Constants.SPACEY*4, Constants.WIDTH2, Constants.HEIGHT);
	}
	
	public void actionPerformed (ActionEvent ae)
	{
		dispose();
	}
	
	public void mouseClicked(MouseEvent me)
	{
		if (me.getClickCount() > 1)
			dispose();
	}
	
	public void mousePressed(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	
public void keyTyped(KeyEvent ke){/*nothing*/}
	
	public void keyPressed(KeyEvent ke)
	{
		if (ke.getKeyCode() == ke.VK_ENTER)
			dispose();
	}
	
	public void keyReleased(KeyEvent ke)
	{
		// nothing
	}	
	
	public String getString()
	{
		return (String)(list.getSelectedValue());
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(Constants.OFFSETX*2 + Constants.WIDTH1, 45 + Constants.OFFSETY*2 + Constants.HEIGHT*5 + Constants.SPACEY*4);
	}
}
