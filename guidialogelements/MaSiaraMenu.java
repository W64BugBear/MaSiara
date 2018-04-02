package guidialogelements;

import gui.MainWindow;
import gui.MyPanel;
import gui.StartDialog;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import races.Dwarves;
import background.Constants;
import background.Parser;
import background.RuntimeInformation;
import background.SerializableInformation;
import character.Hero;

public class MaSiaraMenu 
	extends JMenuBar
	implements ActionListener
{
	MainWindow father;
	MyTextField nameTF;
	
	public MaSiaraMenu(MainWindow father, MyTextField nameTF)
	{
		this.father = father;
		this.nameTF = nameTF;
		
		JMenu hero = new JMenu("Datei");
		
		add(hero);
		hero.setMnemonic('D');
		
		JMenuItem mi;
		
		mi = new JMenuItem("Speichern", 'S');
		mi.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK));
		mi.addActionListener(this);
		hero.add(mi);
		
		mi = new JMenuItem("Öffnen", 'F');
		mi.setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK));
		mi.addActionListener(this);
		hero.add(mi);
		
		hero.addSeparator();
		
		mi = new JCheckBoxMenuItem("Master Mode", false);
		mi.setAccelerator(KeyStroke.getKeyStroke('M', Event.CTRL_MASK));
		mi.addActionListener(this);
		hero.add(mi);
		
		mi = new JCheckBoxMenuItem("im Sprachgebiet", true);
		mi.setAccelerator(KeyStroke.getKeyStroke('G', Event.CTRL_MASK + Event.SHIFT_MASK));
		mi.addActionListener(this);
		hero.add(mi);
		
		hero.addSeparator();
		
		mi = new JMenuItem("Beenden", 'B');
		mi.addActionListener(this);
		hero.add(mi);
		
		JMenu windows = new JMenu("Ansichten");
		
		add(windows);
		windows.setMnemonic('A');
		
		mi = new JMenuItem("Startfenster", 'S');
		mi.addActionListener(this);
		windows.add(mi);
		
		mi = new JMenuItem("Eigenschaften", 'E');
		mi.addActionListener(this);
		windows.add(mi);
		
		mi = new JMenuItem("Fertigkeiten", 'F');
		mi.addActionListener(this);
		windows.add(mi);
		
		mi = new JMenuItem("Kampf", 'K');
		mi.addActionListener(this);
		windows.add(mi);
		
		mi = new JMenuItem("Besitz", 'B');
		mi.addActionListener(this);
		windows.add(mi);
		
		mi = new JMenuItem("Magie", 'M');
		mi.addActionListener(this);
		windows.add(mi);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		try 
		{
			if (ae.getActionCommand().startsWith("B"))
				father.dispose();
			
			if (ae.getActionCommand().startsWith("Mast"))
				father.getSInf().setMasterMode(((JCheckBoxMenuItem)(ae.getSource())).getState());

			if (ae.getActionCommand().startsWith("im"))
				father.getSInf().setLanguageArea(((JCheckBoxMenuItem)(ae.getSource())).getState());
			
			if (ae.getActionCommand().startsWith("St"))
				father.displayStartPanel();
			
			if (ae.getActionCommand().startsWith("E"))
				father.displayPropertiesPanel();
				
			if (ae.getActionCommand().startsWith("F"))
				father.displaySkillsPanel();
			
			if (ae.getActionCommand().startsWith("K"))
				father.displayFightPanel();
			
			if (ae.getActionCommand().startsWith("Besitz"))
				father.displayEquipmentPanel();
			
			if (ae.getActionCommand().startsWith("Magie"))
				father.displayMagicDialog();

			if (ae.getActionCommand().startsWith("Sp"))
			{
				// Save
				save();
			}
			
			if (ae.getActionCommand().startsWith("Ö"))
			{
				// Load
				
				father.displayStartPanel();

				for (int i = 0; i < 4; i++)
				{
					Iterator<Note> it = null;
					switch (i) {
					case 0: it = father.getRInf().getPropertyNotesLl().iterator(); break;
					case 1: it = father.getRInf().getSkillNotesLl().iterator(); break;
					case 2: it = father.getRInf().getFightNotesLl().iterator(); break;
					case 3: it = father.getRInf().getStartNotesLl().iterator(); break;
					}
					
					while (it.hasNext())	// delete old notes and specs
					{
						Note note = it.next();
						((MyPanel)(note.getParent())).remove(note);
						((Noteable)(note.getNotedComponent())).setNote(null);
					}
				}
				
				Iterator<SpecIcon> it2 = father.getRInf().getPropertySpecsLl().iterator();
				
				while (it2.hasNext())
				{
					SpecIcon spec = it2.next();
					((MyPanel)(spec.getParent())).remove(spec);
					((Noteable)(spec.getSpecComponent())).setSpec(null);
				}
				
				it2 = father.getRInf().getSkillSpecsLl().iterator();
				
				while (it2.hasNext())
				{
					SpecIcon spec = it2.next();
					((MyPanel)(spec.getParent())).remove(spec);
					((Noteable)(spec.getSpecComponent())).setSpec(null);
				}

				
				father.setRInf(new RuntimeInformation());
				
				StartDialog sd = new StartDialog(father);
				
				sd.setLocation(father.getX()+100, father.getY()+100);
				sd.setSize(sd.getPreferredSize());
				sd.setVisible(true);
	
				if (sd.getSelectedHero() == null)	// new hero
				{
					father.setHero(new Hero());
					father.getHero().setRace(new Dwarves());
					father.getHero().getRace().set(father.getHero(), father.getSInf(), father.getGpepLabel(), father, true);
					father.setSInf(new SerializableInformation());
					father.getSInf().setEpInt(sd.getEp());  
					father.getSInf().setEpAmountInt(sd.getEp());
					
					father.setPanels(null, 0);
					nameTF.setText("");
					
					father.getStatusLabel().setText("alles in Butter");
				}
				else //load from file
				{
					File file = new File(Constants.RUNTIMESTRING + "//"  + sd.getSelectedHero() + ".masiara");
					BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
					int version = Integer.parseInt(reader.readLine());
					father.setSInf(Parser.deparseSInf(reader.readLine(), version));
					father.getSInf().setEpInt(sd.getEp());
					
					
					file = new File(Constants.HEROSTRING + "//"  + sd.getSelectedHero() + ".hero");
					reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
					version = Integer.parseInt(reader.readLine());
					father.setHero(Parser.deparseHero(reader.readLine(), version, father.getSInf()));
					reader.close();
					
					
					father.getHero().getRace().set(father.getHero(), father.getSInf(), father.getGpepLabel(), father, true);
					if (father.getHero().getSex() == Constants.MALE)
						father.getHero().getRace().applyMale();
					else
						father.getHero().getRace().applyFemale();
					
					father.setPanels(reader.readLine(), version);
					reader.close();
					
					nameTF.setText(father.getHero().getName());
	
//					father.getSInf().setLatestSumOfGp(father.getHero());
					if (father.getHero().isVampire())
						father.getStartPanel().getManVampWwCB().setSelectedIndex(1);
					if (father.getHero().isWerewolf())
						father.getStartPanel().getManVampWwCB().setSelectedIndex(2);

					father.getStatusLabel().setText("alles in Butter");
				}
				
				father.displayStartPanel();
			}
		} catch (FileNotFoundException fnfe)
		{
			father.getStatusLabel().setText("Datei nicht gefunden!");
		} catch (IOException ioe)
		{
			ioe.printStackTrace();
			father.getStatusLabel().setText("IO-Fehler!");
		}
	}
	
	public void save()
	{
		try {
			if (nameTF.getText().equals(""))
				father.getStatusLabel().setText("Held ist namenlos!");
			else
			{
				File file = new File(Constants.HEROSTRING + "//"  + nameTF.getText() + ".hero");
				if (!file.exists())
					file.createNewFile();
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),
																				  StandardCharsets.UTF_8));
			    writer.write(Constants.FILEVERSION+"");
			    writer.newLine();
				writer.write (Parser.parseHero(father.getHero()));
			    writer.close();
				
				file = new File(Constants.RUNTIMESTRING + "//"  + nameTF.getText() + ".masiara");
				if (!file.exists())
					file.createNewFile();
				
				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
			    writer.write(Constants.FILEVERSION+"");
			    writer.newLine();
				writer.write (Parser.parseSInf(father.getSInf()));
			    writer.newLine();
			    writer.write (father.getPanels());
			    writer.close();
				
//				os = new ObjectOutputStream(new FileOutputStream(file));
//				os.writeObject(father.getSInf());
//				os.writeObject(father.getPanels());
//				os.close();
				
				father.getStatusLabel().setText(nameTF.getText() + " wurde gespeichert");
			}
		} catch (FileNotFoundException fnfe)
		{
			father.getStatusLabel().setText("Datei nicht gefunden!");
		} catch (IOException ioe)
		{
			ioe.printStackTrace();
			father.getStatusLabel().setText("IO-Fehler!");
		}
	}
}
