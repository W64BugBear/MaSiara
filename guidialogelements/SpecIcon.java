package guidialogelements;

import gui.MyPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

import background.Constants;

public class SpecIcon 
	extends JComponent
{
	MyPanel observer = null;
	JComponent specComponent = null;
	boolean isClosed = true;	
	String text = null;
	String val = null;

	public SpecIcon(MyPanel parObserver)
	{
		this.observer = parObserver;
	}
	
	public SpecIcon(MyPanel parObserver, String specText, String specVal)
	{
		this(parObserver);
		text = specText;
		val = specVal;
	}
	public JComponent getSpecComponent() {
		return specComponent;
	}

	public void setSpecComponent(JComponent specComponent) {
		this.specComponent = specComponent;
		setBounds(specComponent.getX()+specComponent.getWidth()-15, specComponent.getY()+specComponent.getHeight()-10, specComponent.getX()+specComponent.getWidth()-6, specComponent.getY()+specComponent.getHeight()+4);
	}
	
	public void actualizeBounds() {
		setBounds(specComponent.getX()+specComponent.getWidth()-15, specComponent.getY()+specComponent.getHeight()-10, specComponent.getX()+specComponent.getWidth()-6, specComponent.getY()+specComponent.getHeight()+4);
	}

	public void paint (Graphics g)
	{
		if (isClosed)
		{
			Image img;
			try {
				img = observer.getToolkit().getImage("icons//Spec.jpg");
				} catch (Exception fnfex)
				{
					img = null;
				}
			g.drawImage(img, 0, 0, observer);
		}
		else
		{
			FontMetrics fm = getFontMetrics(getFont());
			g.setColor(new Color(255, 255, 224));
			g.fillRect(0, 0, fm.stringWidth(text)+fm.stringWidth(", ")+fm.stringWidth(val)+Constants.SPACEX*2, fm.getHeight()+Constants.SPACEY*2);
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, fm.stringWidth(text)+fm.stringWidth(", ")+fm.stringWidth(val)+Constants.SPACEX*2, fm.getHeight()+Constants.SPACEY*2);
			g.drawString(text+", "+val, Constants.SPACEX, fm.getHeight());
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
			return new Dimension(fm.stringWidth(text)+fm.stringWidth(", ")+fm.stringWidth(val)+Constants.SPACEX*2, fm.getHeight()+Constants.SPACEY*2);
		}
	}

	public boolean isClosed()
	{
		return isClosed;
	}
	
	public void setClosed(boolean cl)
	{
		isClosed = cl;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

}
