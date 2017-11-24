package guidialogelements;

import gui.ChooseMagicDialog;
import gui.MainWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

import background.Constants;

public class Beastinger
	extends JComponent
	implements MouseMotionListener
{
		private static final int NONE = 0;
		private static final int PROPERTIES = 1;
		private static final int SKILLS = 2;
		private static final int FIGHT = 3;
		private static final int EQUIPMENT = 4;
		private static final int MAGIC = 5;
	
		int rectHeight, fontHeight;
		int propX, propY, propWidth, propHeight;
		int fightX, fightY, fightWidth, fightHeight;
		int skillX, skillY, skillWidth, skillHeight;
		int equipX, equipY, equipWidth, equipHeight;
		int magicX, magicY, magicWidth, magicHeight;
		
		String propString = "(E)igenschaften";
		String fightString = "(K)ampf";
		String skillString = "(F)ertigkeiten";
		String equipString = "(B)esitz";
		String magicString = "(M)agie";
		
		JPanel observer = null;
		MainWindow father = null;
		Image img = null;
		
		int active = NONE;	// marks where mouse is positioned, using constants
		
		boolean first = true; // must calculate members while 1st call of paint, cuz constructor doesn't get FontMetrics
		
		public Beastinger(JPanel parObserver, MainWindow father)
		{
			try {
			this.observer = parObserver;
			this.father = father;
			
			MediaTracker tracker = new MediaTracker(observer);	
			img = observer.getToolkit().getImage("icons//shaman.jpg");
			tracker.addImage(img, 0);
			tracker.waitForAll();	// waits in order to have the panel which listens for mousemotions to be adjusted
			} catch (Exception ex)
			{
				img = null;
			}
		}
		
		public void paint (Graphics g)
		{
			if (first)
			{
				first = false;
				FontMetrics fm = getFontMetrics(getFont());
				
				rectHeight = fm.getHeight() + Constants.SPACEY*2;
				fontHeight = fm.getHeight() - 2;
				
				propX = 55;
				propY = 90;
				propWidth = fm.stringWidth(propString)+Constants.SPACEX*2;
				propHeight = 80;

				fightX = 155;
				fightY =  60;
				fightWidth = 75;
				fightHeight = 108;
				
				skillX = 30;
				skillY =  230;
				skillWidth = fm.stringWidth(skillString)+Constants.SPACEX*2;
				skillHeight = 133;

				equipX = 120;
				equipY = 210;
				equipWidth = 67;
				equipHeight = 106;
				
				magicX = 2;
				magicY = 150;
				magicWidth = fm.stringWidth(magicString)+Constants.SPACEX*2;
				magicHeight = 60;
				
		/*		settingsX = 0;
				settingsY = 0;
				settingsWidth = fm.stringWidth(settingsString)+Constants.SPACEX*2;
				settingsHeight = 40;
		*/	}

			g.drawImage(img, 0, 0, observer);
		
			g.setColor(Color.WHITE);
			g.fillRoundRect(propX+1, propY, propWidth-1, fontHeight + Constants.SPACEX, 20, 20);
			g.fillRoundRect(fightX+1, fightY, fightWidth-1, fontHeight + Constants.SPACEX, 20, 20);
			g.fillRoundRect(skillX+1, skillY, skillWidth-1, fontHeight + Constants.SPACEX, 20, 20);
			g.fillRoundRect(equipX+1, equipY, equipWidth-1, fontHeight + Constants.SPACEX, 20, 20);
			g.fillRoundRect(magicX+1, magicY, magicWidth-1, fontHeight + Constants.SPACEX, 20, 20);
						
			if (active == PROPERTIES)
				g.setColor(Color.BLUE);
			else
				g.setColor(Color.BLACK);
			g.drawRoundRect(propX, propY, propWidth, propHeight, 20, 20);
			g.drawString(propString, propX+Constants.SPACEX, propY+fontHeight);
			
			if (active == FIGHT)
				g.setColor(Color.BLUE);
			else
				g.setColor(Color.BLACK);
			g.drawRoundRect(fightX, fightY, fightWidth, fightHeight, 20, 20);
			g.drawString(fightString, fightX+Constants.SPACEX, fightY+fontHeight);
			
			if (active == SKILLS)
				g.setColor(Color.BLUE);
			else
				g.setColor(Color.BLACK);
			g.drawRoundRect(skillX, skillY, skillWidth, skillHeight, 20, 20);
			g.drawString(skillString, skillX+Constants.SPACEX, skillY+fontHeight);

			if (active == EQUIPMENT)
				g.setColor(Color.BLUE);
			else
				g.setColor(Color.BLACK);
			g.drawRoundRect(equipX, equipY, equipWidth, equipHeight, 20, 20);
			g.drawString(equipString, equipX+Constants.SPACEX, equipY+fontHeight);
			
			if (active == MAGIC)
				g.setColor(Color.BLUE);
			else
				g.setColor(Color.BLACK);
			g.drawRoundRect(magicX, magicY, magicWidth, magicHeight, 20, 20);
			g.drawString(magicString, magicX+Constants.SPACEX, magicY+fontHeight);
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
		
		private int getClickedArea(int x, int y)
		{
			if (x > propX 
					&&  y > propY
					&&  x < propX + propWidth
					&&  y < propY + propHeight)
				return PROPERTIES;
			
			if (x > fightX 
				&&  y > fightY
				&&  x < fightX + fightWidth
				&&  y < fightY + fightHeight)
					return FIGHT;
			
			if (x > skillX 
					&&  y > skillY
					&&  x < skillX + skillWidth
					&&  y < skillY + skillHeight)
				return SKILLS;

			if (x > equipX 
					&&  y > equipY
					&&  x < equipX + equipWidth
					&&  y < equipY + equipHeight)
				return EQUIPMENT;

			if (x > magicX 
					&&  y > magicY
					&&  x < magicX + magicWidth
					&&  y < magicY + magicHeight)
				return MAGIC;
			
			return 0;
			
		}
		
		public void uWereClicked(int x, int y)
		{
			switch (getClickedArea(x, y))
			{
			case PROPERTIES: father.displayPropertiesPanel(); break;
			case SKILLS: father.displaySkillsPanel(); break;
			case FIGHT: father.displayFightPanel(); break;
			case EQUIPMENT: father.displayEquipmentPanel(); break;
			case MAGIC: father.displayMagicDialog(); break;
			}
		}
		
		public void mouseMoved(MouseEvent me) {
			int oldval = active;
			active = getClickedArea(me.getX(), me.getY());
			if (oldval != active)
			{
				father.repaint();
				father.setVisible(true);
			}
		}
		public void mouseDragged(MouseEvent me) {
		}
}
