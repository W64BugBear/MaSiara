package races;

import gui.MainWindow;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;

import background.Constants;
import background.SerializableInformation;
import character.Hero;
import character.RaceModifiers;

public class Ogres 
	extends Race
	implements ItemListener
{
	
	public Ogres ()
	{
		super ("Oger", false, false, true, Race.POOR, "Gs-1, K+4, Z+4, C-2, Im+3, Iz-2, M+1; alle Sinne +6 außer Geruch +9; MR: 2\nAlle Proben auf Schusswaffen sind um 3 erschwert.\nOger erleiden keinen Gewandtheitsabzug durch Korpulenz und einen Punkt weniger Abzug auf Gewandtheit und Schnelligkeit bei Fettleibigkeit.\nOgerschamanen können Blutgeister rufen. Regeltechnisch bedeutet das, dass sie aus Toten Energie ziehen können. Sie können ohne Probe aus einem Leichnam so viele Punkte Mana heraufbeschwören, wie er Zähigkeit hatte.\nOger können ihr Gebiss im Kampf einsetzen (Schadenswert 1).", 
																											"Alle Proben auf Schusswaffen sind um 3 erschwert.\nOger erleiden keinen Gewandtheitsabzug durch Korpulenz und einen Punkt weniger Abzug auf Gewandtheit und Schnelligkeit bei Fettleibigkeit.\nOger können ihr Gebiss im Kampf einsetzen (Schadenswert 1).","ogre");
		LinkedList<RaceModifiers> rm = new LinkedList<RaceModifiers>();
		rm.add(new RaceModifiers("Mensch", 
				0, -1, 0, 2, 4, 0, 4, 
				-2, -2, -2, 0, 2, 1, 0,
				-1, -1, -1, 2, -1, -1, 
				-3, 0, 0,
				false));
		setRaceModifiers(rm);
	}
	
	public void set(Hero hero, SerializableInformation sInf, JLabel gpep, MainWindow father, boolean loading)
	{
		super.set(hero, sInf, gpep, father, loading);
	}
	
	public JComponent specials()
	{
		String[] wealth = {"nichts", "Wohlhabend 1", "Wohlhabend 2", "Wohlhabend 3", "Wohlhabend 4", "Wohlhabend 5", "Wohlhabend 6", "Wohlhabend 7", "Wohlhabend 8", "Wohlhabend 9", "Wohlhabend 10"};
		JComboBox<String> combo2 = new JComboBox(wealth);
		combo2.setSelectedIndex(hero.getWealthy());
		
		combo2.setName("b");
		combo2.addItemListener(this);
		
		hero.setSpecialsStr("keine");
		
		return combo2;
//		return new SpecialsPanel();
	}
	
	
	public void itemStateChanged (ItemEvent ie)
	{
		JComboBox combo = (JComboBox)(ie.getSource());
		int difference = combo.getSelectedIndex() - hero.getWealthy();
		sInf.setGpInvestedInSpecials(sInf.getGpInvestedInSpecials()+difference);
		sInf.increaseEP(difference*-175);
		setGPEPLabel();
		hero.setWealthy(combo.getSelectedIndex());
	}
	
	public Dimension getSpecialSize()
	{
		return new Dimension (Constants.WIDTH2*3/2+Constants.SPACEX, Constants.HEIGHT*2+Constants.SPACEY*2);
	}
	
	public void remove (MainWindow father)
	{
		sInf.setGpInvestedInSpecials(0);
		sInf.increaseEP(hero.getWealthy()*175);
		hero.setWealthy(0);
	}
	
	public void applyMale()
	{
		LinkedList<RaceModifiers> rml = hero.getRace().getRaceModifiers();
		LinkedList<RaceModifiers> newrml = new LinkedList<RaceModifiers>();
		Iterator<RaceModifiers> it = rml.iterator();
		
		while (it.hasNext())
		{
			RaceModifiers rm = it.next();
			
			int[] mods = rm.getMods();
			mods[3]++; // Im
			mods[4]++; // K
			mods[5]++; // S
			mods[6]++; // Z
				
			rm.setMods(mods);
			
			newrml.add(rm);
		}	
			
		hero.getRace().setRaceModifiers(newrml);
	}
	
	public void applyFemale()
	{
		LinkedList<RaceModifiers> rml = hero.getRace().getRaceModifiers();
		LinkedList<RaceModifiers> newrml = new LinkedList<RaceModifiers>();
		Iterator<RaceModifiers> it = rml.iterator();
		
		while (it.hasNext())
		{
			RaceModifiers rm = it.next();
			
			int[] mods = rm.getMods();
			mods[0]++; // A
			mods[2]++; // Gw
			mods[6]++; // Z
			mods[8]++; // E
				
			rm.setMods(mods);
			
			newrml.add(rm);
		}	
			
		hero.getRace().setRaceModifiers(newrml);
	}
	
	public void unapplyMale()
	{
		LinkedList<RaceModifiers> rml = hero.getRace().getRaceModifiers();
		LinkedList<RaceModifiers> newrml = new LinkedList<RaceModifiers>();
		Iterator<RaceModifiers> it = rml.iterator();
		
		while (it.hasNext())
		{
			RaceModifiers rm = it.next();
			
			int[] mods = rm.getMods();
			mods[3]--; // Im
			mods[4]--; // K
			mods[5]--; // S
			mods[6]--; // Z
				
			rm.setMods(mods);
			
			newrml.add(rm);
		}	
			
		hero.getRace().setRaceModifiers(newrml);		
	}
	
	public void unapplyFemale()
	{
		LinkedList<RaceModifiers> rml = hero.getRace().getRaceModifiers();
		LinkedList<RaceModifiers> newrml = new LinkedList<RaceModifiers>();
		Iterator<RaceModifiers> it = rml.iterator();
		
		while (it.hasNext())
		{
			RaceModifiers rm = it.next();
			
			int[] mods = rm.getMods();
			mods[0]--; // A
			mods[2]--; // Gw
			mods[6]--; // Z
			mods[8]--; // E
				
			rm.setMods(mods);
			
			newrml.add(rm);
		}	
			
		hero.getRace().setRaceModifiers(newrml);
	}

	@Override
	public String serializeYourself() {
		String ret = super.getName() + ";";
		return ret;
	}

	@Override
	public void deserializeYourself(StringTokenizer t) {
		// TODO Auto-generated method stub
		
	}
}