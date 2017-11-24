package races;

import gui.MainWindow;
import guidialogelements.SkillUnit;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import background.Constants;
import background.SerializableInformation;
import character.Hero;
import character.Item;
import character.RaceModifiers;
import character.Skill;

public class Falconites 
	extends Race
	implements ItemListener
{
	Skill fear;
	
	Item item = new Item("Adelsring", 0);
	
	public Falconites ()
	{
		super ("Falkoniter", true, true, true, Race.POOR, "K-1, Z-1; Maximum für Z/K: 15; Tagsicht +9, Nachtsicht +5, alle anderen Sinne +7; MR: 2\nkönnen natürlich fliegen (im Flug: S+10 nur für Geschwindigkeit), müssen allerdings zumindest alle zwei bis drei Tage frei fliegen, um keine Beklemmungsängste zu bekommen;\nFalkoniter haben automatisch Raumangst: 7 und erleiden Wunden im Kampf wie Halbelfen.", "Falkoniter können natürlich fliegen (im Flug: S+10 nur für Geschwindigkeit), müssen allerdings zumindest alle zwei bis drei Tage frei fliegen, um keine Beklemmungsängste zu bekommen.\nFalkoniter erleiden Wunden im Kampf wie Halbelfen.","falc");
		LinkedList<RaceModifiers> rm = new LinkedList<RaceModifiers>();
		rm.add(new RaceModifiers("Mensch", 
				0, 0, 0, 0, -1, 0, -1, 
				0, 0, 0, 0, 0, 0, 0,
				1, -1, 0, 0, 0, 0,  
				0, 0, 0,
				false));
		rm.add(new RaceModifiers("fliegend", 
				0, 0, 0, 0, -1, 10, -1, 
				0, 0, 0, 0, 0, 0, 0,
				1, -1, 0, 0, 0, 0, 
				0, 0, 0,
				false));
		setRaceModifiers(rm);
	}
	
	public void set(Hero hero, SerializableInformation sInf, JLabel gpep, MainWindow father, boolean loading)
	{
		super.set(hero, sInf, gpep, father, loading);
		hero.getProperty("Kraft").setMaximumInt(16);
		hero.getProperty("Zähigkeit").setMaximumInt(16);
		fear = new Skill("Raumangst", false, 7, null, Skill.TYPE_LIMITATION, Skill.LIMITATION_DOESNT_COUNT);
//		hero.getSpecialSkillsLl().add(fear);
//		skillsPanel.addSkill(fear, skillsPanel.getLimitationsCombo());
		if (!loading)
			father.getSkillsPanel().getSkillPositioners()[3].addAfter(fear, father.getSkillsPanel().getLimitationsCombo(), true);
	}
	
	public JComponent specials()
	{
		JPanel spane = new JPanel();
		spane.setLayout(null);
		spane.setSize(Constants.WIDTH2*3/2+Constants.SPACEX, Constants.HEIGHT*2+Constants.SPACEY);

		String[] nobility = {"nicht adelig", "Adel"};
		JComboBox<String> combo = new JComboBox(nobility);
		combo.setSelectedIndex(hero.getNobility());
		
		combo.setName("a");
		combo.addItemListener(this);
		spane.add(combo);
		combo.setBounds(0, 0, Constants.WIDTH1, Constants.HEIGHT);
		
		String[] wealth = {"nichts", "Wohlhabend 1", "Wohlhabend 2", "Wohlhabend 3", "Wohlhabend 4", "Wohlhabend 5", "Wohlhabend 6", "Wohlhabend 7", "Wohlhabend 8", "Wohlhabend 9", "Wohlhabend 10"};
		JComboBox<String> combo2 = new JComboBox(wealth);
		combo2.setSelectedIndex(hero.getWealthy());
		
		combo2.setName("b");
		combo2.addItemListener(this);
		spane.add(combo2);
		combo2.setBounds(0, Constants.HEIGHT+Constants.SPACEY, Constants.WIDTH1, Constants.HEIGHT);
		
		hero.setSpecialsStr("keine");
		
		return spane;
//		return new SpecialsPanel();
	}
	
	
	public void itemStateChanged (ItemEvent ie)
	{
		JComboBox combo = (JComboBox)(ie.getSource());
		if (combo.getName().equals("a"))
		{
			if (hero.getNobility() != 0)
				hero.getItemsLl().remove(item);
			int difference = combo.getSelectedIndex() - hero.getNobility();
			sInf.setGpInvestedInSpecials(sInf.getGpInvestedInSpecials()+difference);
			sInf.increaseEP(difference*-175);
			setGPEPLabel();
			int nobility = combo.getSelectedIndex();
			
			switch (nobility) {
			case 1:
				hero.getItemsLl().add(item);
				hero.setNobility(Hero.NOB_NOBLE);
				break;
			default:
				hero.setNobility(hero.NOB_BASEBORN);
			}
		}
		else
		{
			int difference = combo.getSelectedIndex() - hero.getWealthy();
			sInf.setGpInvestedInSpecials(sInf.getGpInvestedInSpecials()+difference);
			sInf.increaseEP(difference*-175);
			setGPEPLabel();
			hero.setWealthy(combo.getSelectedIndex());
		}
	}
	
	public Dimension getSpecialSize()
	{
		return new Dimension (Constants.WIDTH2*3/2+Constants.SPACEX, Constants.HEIGHT*2+Constants.SPACEY*2);
	}
	
	public void remove (MainWindow father)
	{
		hero.getProperty("Kraft").setMaximumInt(20);
		hero.getProperty("Zähigkeit").setMaximumInt(20);
		
		Iterator iter = father.getSkillsPanel().getSkillPositioners()[3].getComponents().iterator();
		
		while (iter.hasNext())
		{
			try {
				SkillUnit su = (SkillUnit)(iter.next());
				if (su.getSkill().getNameStr().equals("Raumangst"))
				{
					if (su.getSkill().getValueInt() < 8)
					{
						Iterator<Skill> iterator = hero.getSpecialSkillsLl().iterator();
						while (iterator.hasNext())
						{
							Skill skill = iterator.next();
							if (skill.getNameStr().equals("Raumangst"))
							{
								hero.getSpecialSkillsLl().remove(skill);
								break;
							}
						}
						father.getSkillsPanel().getSkillPositioners()[3].remove(su);
						break;
					}
					else
					{
						su.getSkill().setValueInt(su.getSkill().getValueInt()-7);
						su.getValueSpinner().setText(su.getSkill().getValueInt()+"");
					}
				}
			} catch (ClassCastException cce)
			{ /*nothing*/ }	
		}
		
		
		
		int returngp = hero.getNobility() + hero.getWealthy();
		sInf.setGpInvestedInSpecials(0);
		sInf.increaseEP(returngp*175);
		hero.setNobility(Hero.NOB_BASEBORN);
		hero.setWealthy(0);
		try {
			hero.getItemsLl().remove(item);
		} catch (NullPointerException npe)
		{
			// nothing
		}
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
