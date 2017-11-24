package races;

import gui.MainWindow;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.xml.internal.bind.v2.util.FatalAdapter;

import sun.awt.image.ImageWatched.Link;

import background.Constants;
import background.SerializableInformation;
import character.Hero;
import character.Item;
import character.RaceModifiers;

public class Dwarves 
	extends Race
	implements ItemListener
{
	Item item = new Item("Adelsring", 0);
	
	boolean resistent = false;
	
	public Dwarves ()
	{
		super ("Zwerge", false, true, false, Race.DWARF, "Gs+2, Gw-2, K+2, S-2, Z+3/+5/+8 (Wunden, Gifte (erhöht ggf auch die GE bei Schlafgift um 2 bzw bei Kreislaufgift alle Eigenschaften um 2), Krankheiten), M+2, Sb+3; Nachtsicht +9, Gehör +6, Rest +7\nMetallrüstungen behindern Tharg’Gurath-Zwerge um 2 Punkte weniger (nach Rechnung der BV), Satyrnkammzwerge um 1 weniger. Tharg’Gurath-Zwerge können (psychisch) keine Pferde reiten, Satyrnkammzwerge nur mit 3 Punkten Erschwernis auf Reit-Proben.\nSolange sie im direkten Sonnenlicht stehen, sind sie kraftlos. All ihre Eigenschaften (außer A und Z) sind 1 (Mittagssonne) bis 2 (enorme Hitze) gesenkt, ihre Motivation hält sich in Grenzen und überhaupt scheinen sie energielos. Ansonsten kommen sie mit halb so viel Schlaf aus wie Dúradram (regenerieren aber normal), wobei sie schlafen wie Steine. Wenn sie dennoch eine ganze Nacht regenerieren, zählt ihre Z für den Regenerationswurf nochmals um 3 erhöht, außerdem können sie Z-Proben leichter wiederholen (Genaueres bei den Regenerationsregeln). Gegen Heilzauber setzen sie MR: 3 entgegen, gegen alle anderen Zauber gar 6! Für Zwerge sind Sinnesproben zum Durchschauen von Illusionen um 6 erleichtert. Resistente Zwerge haben MR: unendlich (gegen Heilzauber 6), sie können Dämonen u. Geister mit Waffen verletzen, außerdem erhöht sich die Z gegen magische Effekte (wie Feuerauren oder Eislanzen) um 6 (also mit dem gewöhnlichen Z-Bonus um insgesamt 9, gegen magische Gifte sogar insgesamt 11, etc).\nZwerge spüren die Auswirkungen von Wunden auch nach dem Kampf nicht stärker als während des Kampfes.", "Metallrüstungen behindern Tharg’Gurath-Zwerge um 2 Punkte weniger (nach Rechnung der BV), Satyrnkammzwerge um 1 weniger. Tharg’Gurath-Zwerge können (psychisch) keine Pferde reiten, Satyrnkammzwerge nur mit 3 Punkten Erschwernis auf Reit-Proben.\nSolange sie im direkten Sonnenlicht stehen, sind sie kraftlos. All ihre Eigenschaften (außer A und Z) sind 1 (Mittagssonne) bis 2 (enorme Hitze) gesenkt, ihre Motivation hält sich in Grenzen und überhaupt scheinen sie energielos. Ansonsten kommen sie mit halb so viel Schlaf aus wie Dúradram (regenerieren aber normal), wobei sie schlafen wie Steine. Wenn sie dennoch eine ganze Nacht regenerieren, zählt ihre Z für den Regenerationswurf nochmals um 3 erhöht, außerdem können sie Z-Proben leichter wiederholen (Genaueres bei den Regenerationsregeln). Gegen Heilzauber setzen sie MR: 3 entgegen, gegen alle anderen Zauber gar 6! Für Zwerge sind Sinnesproben zum Durchschauen von Illusionen um 6 erleichtert. Resistente Zwerge haben MR: unendlich (gegen Heilzauber 6), sie können Dämonen u. Geister mit Waffen verletzen, außerdem erhöht sich die Z gegen magische Effekte (wie Feuerauren oder Eislanzen) um 6 (also mit dem gewöhnlichen Z-Bonus um insgesamt 9, gegen magische Gifte sogar insgesamt 11, etc).\nZwerge spüren die Auswirkungen von Wunden auch nach dem Kampf nicht stärker als während des Kampfes.","dwarf");
		LinkedList<RaceModifiers> rm = new LinkedList<RaceModifiers>();
		rm.add(new RaceModifiers("normal", 
				0, 2, -2, 0, 2, -2, 3, 
				0, 0, 0, 0, 4, 2, 3,
				0, 2, -1, 0, 0, 0, 
				0, 0, 0,
				false));
		rm.add(new RaceModifiers("Gifte allgemein", 
				0, 2, -2, 0, 2, -2, 5, 
				0, 0, 0, 0, 4, 2, 3,
				0, 2, -1, 0, 0, 0,
				0, 0, 0,
				false));
		rm.add(new RaceModifiers("Bögen/Wurfspeere", 
				0, 2, -2, 0, 2, -2, 3, 
				0, 0, 0, 0, 4, 2, 3,
				0, 2, -1, 0, 0, 0,
				-2, -2, 0,
				false));
		rm.add(new RaceModifiers("Krankheiten", 
				0, 2, -2, 0, 2, -2, 8, 
				0, 0, 0, 0, 4, 2, 3,
				0, 2, -1, 0, 0, 0,
				0, 0, 0,
				false));
		rm.add(new RaceModifiers("Sonne allgemein", 
				0, 1, -3, -1, 1, -3, 3, 
				-1, -1, -1, -1, 4, 1, 2,
				0, 2, -1, 0, 0, 0,
				0, 0, 0,
				false));
		rm.add(new RaceModifiers("Mittagssonne allgemein", 
				0, 0, -4, -2, 0, -4, 3, 
				-2, -2, -2, -2, 4, 0, 1,
				0, 2, -1, 0, 0, 0,
				0, 0, 0,
				false));
		setRaceModifiers(rm);
	}
	
	public void set(Hero hero, SerializableInformation sInf, JLabel gpep, MainWindow father, boolean loading)
	{
		super.set(hero, sInf, gpep, father, loading);
	}
	
	public void remove (MainWindow father)
	{
		int returngp = hero.getNobility() + hero.getWealthy() + (resistent?1:0);
		sInf.setGpInvestedInSpecials(0);
		sInf.increaseEP(returngp*175);
		hero.setSpecialsStr("keine");
		hero.setNobility(Hero.NOB_BASEBORN);
		hero.setWealthy(0);
		
		try {
			hero.getItemsLl().remove(item);
		} catch (NullPointerException npe)
		{
			// nothing
		}
		
	}
	
	public JComponent specials()
	{
		JPanel spane = new JPanel();
		spane.setLayout(null);
		spane.setSize(Constants.WIDTH2*3/2+Constants.SPACEX, Constants.HEIGHT*2+Constants.SPACEY);

		JCheckBox checkbox = new JCheckBox ("resistent", resistent);
		spane.add(checkbox, BorderLayout.WEST);
		checkbox.setBounds(0, 0, Constants.WIDTH2*3/2, Constants.HEIGHT);
		checkbox.addItemListener(this);
		
		String[] nobility = {"nicht adelig", "Adel", "Hochadel"};
		JComboBox<String> combo = new JComboBox<String>(nobility);
		combo.setSelectedIndex(hero.getNobility());
		combo.setName("a");
		combo.addItemListener(this);
		spane.add(combo);
		combo.setBounds(0, Constants.HEIGHT+Constants.SPACEY, Constants.WIDTH1, Constants.HEIGHT);
		
		String[] wealth = {"nichts", "Wohlhabend 1", "Wohlhabend 2", "Wohlhabend 3", "Wohlhabend 4", "Wohlhabend 5", "Wohlhabend 6", "Wohlhabend 7", "Wohlhabend 8", "Wohlhabend 9", "Wohlhabend 10"};
		JComboBox<String> combo2 = new JComboBox<String>(wealth);
		combo2.setSelectedIndex(hero.getWealthy());
		combo2.setName("b");
		combo2.addItemListener(this);
		spane.add(combo2);
		combo2.setBounds(0, Constants.HEIGHT*2+Constants.SPACEY*2, Constants.WIDTH1, Constants.HEIGHT);
		
		hero.setSpecialsStr("keine");
		
		return spane;
//		return new SpecialsPanel();
	}
	
	
	public void itemStateChanged (ItemEvent ie)
	{
		if (ie.getSource() instanceof JCheckBox)	// resistance
		{
			if (!resistent)
			{
				hero.setSpecialsStr("resistent");
				sInf.setGpInvestedInSpecials(sInf.getGpInvestedInSpecials()+1);
				sInf.increaseEP(-175);
				setGPEPLabel();
				resistent = true;
			}
			else
			{
				hero.setSpecialsStr("keine");
				sInf.setGpInvestedInSpecials(sInf.getGpInvestedInSpecials()-1);
				sInf.increaseEP(175);
				setGPEPLabel();
				resistent = false;
			}
		}
		
		else
		{
			JComboBox<String> combo = (JComboBox<String>)(ie.getSource());
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
				case 2:
					hero.getItemsLl().add(item);
					hero.setNobility(Hero.NOB_HIGH_NOBILITY);
					break;
				default:
					hero.setSpecialsStr("keine");
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
		
	}
	
	public Dimension getSpecialSize()
	{
		return new Dimension (Constants.WIDTH2*3/2+Constants.SPACEX, Constants.HEIGHT*3+Constants.SPACEY*3);
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
		String ret = super.getName() + ";" + (resistent?1:0) + ";";
		return ret;

	}

	@Override
	public void deserializeYourself(StringTokenizer t) {
		if (t.nextToken().equals("1"))
			resistent = true;
	}
}
