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

public class Gnolls 
	extends Race
	implements ItemListener
{
	
	public Gnolls ()
	{
		super ("Gnolle", true, true, true, Race.POOR, "Gs-1, Gw+2, K+2, S+2, C-2, M-1; Tagsicht +5, Tasten+6, Nachtsicht und Geschmack +8, Gehör+9, Geruch+10; MR: 2\nGnolle richten im waffenlosen Kampf echte Schadenspunkte an und können ihr Gebiss einsetzen (Schadenswert 8).", "Gnolle richten im waffenlosen Kampf echte Schadenspunkte an und können ihr Gebiss einsetzen (Schadenswert 8).","gnoll");
		LinkedList<RaceModifiers> rm = new LinkedList<RaceModifiers>();
		rm.add(new RaceModifiers("Mensch", 
				0, -1, 2, 0, 2, 2, 0, 
				-2, -1, 0, 0, 0, -1, 0,
				-2, 1, 2, 3, 1, -1, 
				0, 0, 0,
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
			mods[3]++; // A
			mods[4]++; // Gw
			mods[5]++; // Z
			mods[6]++; // E
				
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
			mods[3]--; // A
			mods[4]--; // Gw
			mods[5]--; // Z
			mods[6]--; // E
				
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
