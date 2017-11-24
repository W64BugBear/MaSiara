package guidialogelements;

import gui.MyPanel;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

import background.Constants;

public class Note 
	extends JComponent
{
	MyPanel observer = null;
	JComponent notedComponent = null;
	boolean isClosed = true;	
	String text = null;
	
	public Note(MyPanel parObserver)
	{
		this.observer = parObserver;
	}
	
	public Note(MyPanel parObserver, String noteText)
	{
		this(parObserver);
		this.setText(noteText);
	}

	public JComponent getNotedComponent() {
		return notedComponent;
	}

	public void setNotedComponent(JComponent notedComponent) {
		this.notedComponent = notedComponent;
//		setBounds(notedComponent.getX()+notedComponent.getWidth()-5, notedComponent.getY()+notedComponent.getHeight()-10, notedComponent.getX()+notedComponent.getWidth()+4, notedComponent.getY()+notedComponent.getHeight()+4);
		setBounds(notedComponent.getX()+notedComponent.getWidth()-5, notedComponent.getY()+notedComponent.getHeight()-10, 9, 14);
	}
	
	public void actualizeBounds() {
		Dimension dimension = getPreferredSize();
//		setBounds(notedComponent.getX()+notedComponent.getWidth()-5, notedComponent.getY()+notedComponent.getHeight()-10, notedComponent.getX()+notedComponent.getWidth()+4, notedComponent.getY()+notedComponent.getHeight()+4);
		setBounds(notedComponent.getX()+notedComponent.getWidth()-5, notedComponent.getY()+notedComponent.getHeight()-10, (int)(dimension.getWidth()), (int)(dimension.getHeight()));
	}

	public void paint (Graphics g)
	{
		if (isClosed)
		{
			Image img;
			try {
				img = observer.getToolkit().getImage("icons//note.jpg");
				} catch (Exception ex)
				{
					img = null;
				}
			g.drawImage(img, 0, 0, observer);
		}
		else
		{
			FontMetrics fm = getFontMetrics(getFont());
			g.setColor(new Color(255, 255, 224));
			g.fillRect(0, 0, fm.stringWidth(text)+Constants.SPACEX*2, fm.getHeight()+Constants.SPACEY*2);
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, fm.stringWidth(text)+Constants.SPACEX*2, fm.getHeight()+Constants.SPACEY*2);
			g.drawString(text, Constants.SPACEX, fm.getHeight());
		}
	} 

	public Dimension getPreferredSize()
	{
		if (isClosed)
			return new Dimension(9, 14);
		else
		{
			FontMetrics fm = getFontMetrics(getFont());
			fm = getFontMetrics(getFont());
			return new Dimension(fm.stringWidth(text)+Constants.SPACEX*2+1, fm.getHeight()+Constants.SPACEY*2+1);
		}
	}
	
	public String getText()
	{
		return this.text;
	}
	
	public void setText(String s)
	{
		this.text = s;
	}
	
	public boolean isClosed()
	{
		return isClosed;
	}
	
	public void setClosed(boolean cl)
	{
		isClosed = cl;
	}
}