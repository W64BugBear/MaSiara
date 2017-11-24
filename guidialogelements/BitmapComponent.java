package guidialogelements;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;
import javax.swing.JDialog;

public class BitmapComponent 
	extends JComponent
{
	JDialog observer = null;
	Image img = null;
	
	public BitmapComponent(JDialog parObserver)
	{
		this.observer = parObserver;
	}
	
	public BitmapComponent(JDialog parObserver, String icon)
	{
		try {
		this.observer = parObserver;
		img = observer.getToolkit().getImage(icon+".jpg");
		} catch (Exception ex)
		{
			img = null;
		}
	}
	
	public void paint (Graphics g)
	{
		g.drawImage(img, 0, 0, observer);
	} 

	public Dimension getPreferredSize()
	{
		return new Dimension (img.getWidth(observer), img.getHeight(observer));
	}

	public int getWidth()
	{
		return img.getWidth(observer); 
	}
	
	public int getHeight()
	{
		return img.getHeight(observer);
	}
	
	public void setPicture(String path)
	{
		try{
		img = observer.getToolkit().getImage("icons//"+path+".jpg");
		} catch (Exception ex)
		{
			img = null;
		}
	}
}
