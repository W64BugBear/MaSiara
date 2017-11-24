package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import background.Constants;


public class FightingStyleSpecialRulesDialog  
	extends JDialog
	implements ActionListener, KeyListener
{
	JTextArea ta;
	JButton bt;
	boolean ctrlPressed = false;
	
	public FightingStyleSpecialRulesDialog (MainWindow owner, String text)
	{
		super(owner, "Sonderregeln");	// no modal Dialog
		setLayout(null);
		
		if (text == null)
			text = new String("Keine Sonderregeln");
		ta = new JTextArea(text);
		ta.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(ta);
		scrollPane.setBounds(Constants.OFFSETX, Constants.OFFSETY, Constants.WIDTH1*4, Constants.HEIGHT*7);
		add(scrollPane);
		
		bt = new JButton("OK");
		bt.addActionListener(this);
		bt.addKeyListener(this);
		
		bt.setBounds(scrollPane.getX(), scrollPane.getY() + scrollPane.getHeight() + Constants.SPACEY, Constants.WIDTH2, Constants.HEIGHT);
		add(bt);
		add(bt);
		
		setSize(scrollPane.getX() + scrollPane.getWidth() + Constants.OFFSETX*2, 40 + bt.getY() + bt.getHeight() + Constants.OFFSETY);
	}
		
	public void actionPerformed(ActionEvent ae)
	{
		dispose();
	}
	
	public void keyTyped(KeyEvent ke){/*nothing*/}
	
	public void keyPressed(KeyEvent ke)
	{
		if (ke.getKeyCode() == ke.VK_CONTROL)
			ctrlPressed = true;
		else
		{
			if (ctrlPressed && ke.getKeyCode() == ke.VK_S)
				dispose();
			ctrlPressed = false;
		}
		
		if (ke.getKeyCode() == ke.VK_ESCAPE)
		{
			dispose();
		}
	}
	
	public void keyReleased(KeyEvent ke){
		ctrlPressed = false;
	}	
}