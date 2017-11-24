package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

public class NewNoteDialog 
	extends JDialog
	implements ActionListener, KeyListener
{
	JTextField noteText;
	boolean ctrlPressed = false;
	
	public NewNoteDialog(MainWindow owner)
	{
		super(owner, true);		// modal Dialog
		setLayout(new BorderLayout());
		noteText = new JTextField(15);
		noteText.addKeyListener(this);
		noteText.addActionListener(this);
		add(noteText, BorderLayout.NORTH);
		JButton button = new JButton("OK");
		button.addActionListener(this);
		button.addKeyListener(this);
		add(button, BorderLayout.SOUTH);
		pack();
	}
	
	public NewNoteDialog(MainWindow owner, String text)
	{
		this(owner);
		this.noteText.setText(text);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		dispose();
	}
	
	public String getNoteText()
	{
		return noteText.getText();
	}
	
	public void setNoteText(String s)
	{
		noteText.setText(s);
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
			this.noteText.setText("");
			dispose();
		}
	}
	
	public void keyReleased(KeyEvent ke){
		ctrlPressed = false;
	}
}
