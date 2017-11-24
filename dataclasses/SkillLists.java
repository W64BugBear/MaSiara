package dataclasses;

import gui.SkillsPanel;
import guidialogelements.MyComboBox;
import guidialogelements.MyLabel;

import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JLabel;

import character.Skill;

public class SkillLists {

	public static final int SPELLSKILLS = 20;
	public static final int ARCHMAGESKILLS = SPELLSKILLS;
	
	public static LinkedList<Skill> get1stRow(SkillsPanel father)
	{
		LinkedList<Skill> skills = new LinkedList();
		ArrayList<RelatedSkill> related;
		
		// skills.add(new Skill("", 0, null, Skill.TYPE_NORMAL, Skill.));
		skills.add(new Skill("Gesellschaft", true, 0, null, Skill.TYPE_NO_SKILL, Skill.GROUP_NONE));
		skills.add(null);
		skills.add(new Skill("Feilschen", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_SOCIETY));
		skills.add(new Skill("Führung", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_SOCIETY));
		
		related = new ArrayList<RelatedSkill>();
		related.add(new RelatedSkill("Gebräuche"));
		skills.add(new Skill("Gebräuche", true, 0, related, Skill.TYPE_NORMAL, Skill.GROUP_SOCIETY, "Bürger"));
		skills.add(new Skill("Lehren", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_SOCIETY));
		skills.add(new Skill("Lügen", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_SOCIETY));
		skills.add(new Skill("Menschenkenntnis", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_SOCIETY));
		skills.add(new Skill("Schauspiel", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_SOCIETY));
		skills.add(new Skill("Überreden", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_SOCIETY));
		skills.add(new Skill("Unterhalten", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_SOCIETY));
		skills.add(new Skill("Verführung", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_SOCIETY));
		skills.add(null);
		skills.add(new Skill("Heimlichkeit", true, 0, null, Skill.TYPE_NO_SKILL, Skill.GROUP_NONE));
		skills.add(null);
		skills.add(new Skill("Diebstahl", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_STEALTH));
		skills.add(new Skill("Schleichen", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_STEALTH));
		skills.add(new Skill("Schlösser Knacken", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_STEALTH));
		skills.add(new Skill("Verkleiden", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_STEALTH));
		skills.add(new Skill("Verstecken", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_STEALTH));
		skills.add(null);
				
		skills.add(new Skill("Wildnis", true, 0, null, Skill.TYPE_NO_SKILL, Skill.GROUP_NONE));
		skills.add(null);
		skills.add(new Skill("Botanik", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_WILD));
		skills.add(new Skill("Feuertechnik", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_WILD));
		skills.add(new Skill("Fischen", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_WILD));
		skills.add(new Skill("Geräusche Nachahmen", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_WILD));
		skills.add(new Skill("Orientierung", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_WILD));
		skills.add(new Skill("Spurenlesen", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_WILD));
		skills.add(new Skill("Wettervorhersage", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_WILD));
		skills.add(new Skill("Zoologie", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_WILD));

		skills.add(null);
		
		father.index++;
		
		return skills;
	}

	public static LinkedList get2ndRow(SkillsPanel father)
	{
		LinkedList skills = new LinkedList();
		ArrayList<RelatedSkill> related;
		
		// skills.add(new Skill("", true, 0, null, Skill.TYPE_NORMAL, Skill.));
		skills.add(new Skill("Handwerk", true, 0, null, Skill.TYPE_NO_SKILL, Skill.GROUP_NONE));
		skills.add(null);
		skills.add(new Skill("Giftbehandlung", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_CRAFT));
		skills.add(new Skill("Holzarbeiten/Schnitzen", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_CRAFT));
		skills.add(new Skill("Kochen", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_CRAFT));
		skills.add(new Skill("Krankenheilung", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_CRAFT));
		skills.add(new Skill("Lederarbeiten", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_CRAFT));
//		skills.add(new Skill("Reparaturen", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_CRAFT));
		skills.add(new Skill("Mechanik", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_CRAFT));
		skills.add(new Skill("Schneiderei", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_CRAFT));
		skills.add(new Skill("Wundverarztung", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_CRAFT));
		skills.add(null);
		skills.add(new Skill("Körper", true, 0, null, Skill.TYPE_NO_SKILL, Skill.GROUP_NONE));
		skills.add(null);
		skills.add(new Skill("Athletik", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_BODY));
		skills.add(new Skill("Ausdauer", true, 0, null, Skill.TYPE_STAMINA, Skill.GROUP_BODY));
		skills.add(new Skill("Hitzeresistenz", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_BODY));
		skills.add(new Skill("Kälteresistenz", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_BODY));
		skills.add(new Skill("Klettern", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_BODY));
		skills.add(new Skill("Körperbeherrschung", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_BODY));
		skills.add(new Skill("Schwimmen", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_BODY));
		skills.add(new Skill("Tanz", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_BODY));
		
		skills.add(null);
		skills.add(new Skill("Sonstige", true, 0, null, Skill.TYPE_NO_SKILL, Skill.GROUP_NONE));
		skills.add(null);
		skills.add(new Skill("Aufmerksamkeit", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_STEALTH));
		skills.add(new Skill("Bescheidenheit", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_OTHERS));
		skills.add(new Skill("Fesseln/Entfesseln", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_OTHERS));
		skills.add(new Skill("Geduld", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_OTHERS));
		skills.add(new Skill("Malen", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_OTHERS));
		
		related = new ArrayList<RelatedSkill>();
		related.add(new RelatedSkill("Musizieren"));
		skills.add(new Skill("Singen", true, 0, related, Skill.TYPE_NORMAL, Skill.GROUP_OTHERS));
		skills.add(new Skill("Trinkfestigkeit", true, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_OTHERS));
				
		skills.add(null);
		
		return skills;
	}
	
	public static LinkedList get3rdRow(SkillsPanel father)
	{
		LinkedList skills = new LinkedList();
		
		// skills.add(new Skill("", true, 0, null, Skill.TYPE_NORMAL, Skill.));
		skills.add(new Skill("Theorie", true, 0, null, Skill.TYPE_NO_SKILL, Skill.GROUP_NONE));
		skills.add(null);
		skills.add(new Skill("Alchimie", true, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
		skills.add(new Skill("Architektur", true, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
		skills.add(new Skill("Geographie", true, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
		skills.add(new Skill("Historie", true, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
		skills.add(new Skill("Kulturen", true, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
//		skills.add(new Skill("Kunst", true, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));

		skills.add(new Skill("Legenden", true, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
		skills.add(new Skill("Magiekunde", true, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY, "Thaumaturgie"));
		skills.add(new Skill("Mathematik", true, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
		skills.add(new Skill("Politik", true, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
		skills.add(new Skill("Rassen", true, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
		skills.add(new Skill("Schwarzmagie", true, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
		skills.add(new Skill("Theologie", true, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
		
		skills.add(new Skill("Waffenkunde", true, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
		
		skills.add(null);
		
		return skills;
	}
	
	public static LinkedList get4thRow(SkillsPanel father, MyComboBox additionalSkillsCombo, MyComboBox languagesCombo, MyComboBox fontsCombo, MyComboBox limitationsCombo)
	{
		LinkedList ret = new LinkedList();
		
		MyLabel lbl = new MyLabel("Zusatzfertigkeiten", father.index++);
		lbl.setFont(new Font(father.getFont().getName(), Font.BOLD, father.getFont().getSize()));
		MyLabel lim = new MyLabel("Einschränkungen", father.index++);
		lim.setFont(new Font(father.getFont().getName(), Font.BOLD, father.getFont().getSize()));
		
		ret.add(lbl);
		ret.add(null);
		ret.add(additionalSkillsCombo);
		ret.add(null);
		ret.add(languagesCombo);
		ret.add(null);
		ret.add(fontsCombo);
		ret.add(null);
		ret.add(lim);
		ret.add(limitationsCombo);
		ret.add(null);
		ret.add(new JLabel("")); // addLast doesn't work, so I have to insert a dummy component to insert limitations before it
		
		return ret;
	}
	
	public static Object[] getSpecialSkills()
	{
		LinkedList skills = new LinkedList();
		ArrayList<RelatedSkill> related;
		
		skills.add("Zusatzfertigkeit wählen");
		skills.add("neu");
		skills.add(new Skill("Bekehrung", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_SOCIETY));
		skills.add(new Skill("Betrug", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_SOCIETY));
		skills.add(new Skill("Bühnenspiel", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_SOCIETY));
		
		related = new ArrayList<RelatedSkill>();
		related.add(new RelatedSkill("Gebräuche", 2));
		skills.add(new Skill("Gebräuche", false, 0, related, Skill.TYPE_NORMAL, Skill.GROUP_SOCIETY));
		skills.add(new Skill("Verhör", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_SOCIETY));
		skills.add(new Skill("Abrichten", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_CRAFT));
//		skills.add(new Skill("Abrichten (Wildnis)", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_WILD));
		
		related = new ArrayList<RelatedSkill>();
		related.add(new RelatedSkill("Grobschmied"));
		related.add(new RelatedSkill("Goldschmied"));
		related.add(new RelatedSkill("Harnischmacher"));
		related.add(new RelatedSkill("Waffenschmied"));
		
		skills.add(new Skill("Grobschmied", false, 0, related, Skill.TYPE_NORMAL, Skill.GROUP_CRAFT));
		skills.add(new Skill("Goldschmied", false, 0, related, Skill.TYPE_NORMAL, Skill.GROUP_CRAFT));
		skills.add(new Skill("Waffenschmied", false, 0, related, Skill.TYPE_NORMAL, Skill.GROUP_CRAFT));
		skills.add(new Skill("Harnischmacher", false, 0, related, Skill.TYPE_NORMAL, Skill.GROUP_CRAFT));
		skills.add(new Skill("Beruf", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_CRAFT));
		skills.add(new Skill("Tränke Brauen", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_CRAFT));
		skills.add(new Skill("Einbruch", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_STEALTH));
		skills.add(new Skill("Untertauchen", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_STEALTH));
		skills.add(new Skill("Disziplin", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_BODY));
		skills.add(new Skill("Fliegen", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_BODY));
		skills.add(new Skill("Liebesspiel", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_BODY));
		skills.add(new Skill("Reiten", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_BODY));
		skills.add(new Skill("Jagd", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_WILD));
		
		related = new ArrayList<RelatedSkill>();
		related.add(new RelatedSkill("Geländekunde", 2));
		skills.add(new Skill("Geländekunde", false, 0, related, Skill.TYPE_NORMAL, Skill.GROUP_WILD));
		skills.add(new Skill("Anatomie", false, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
		skills.add(new Skill("Astrologie", false, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
		skills.add(new Skill("Geometrie", false, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
//		skills.add(new Skill("Juristik", false, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
		skills.add(new Skill("Naturmagie", false, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
		skills.add(new Skill("Physik", false, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
		skills.add(new Skill("Sphärologie", false, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
		skills.add(new Skill("Strategie", false, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
		skills.add(new Skill("Thaumaturgie", false, 0, null, Skill.TYPE_THEORY, Skill.GROUP_THEORY));
		skills.add(new Skill("Gauklertricks", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_OTHERS));
		skills.add(new Skill("Kutschen Fahren", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_OTHERS));
		
		related = new ArrayList<RelatedSkill>();
		related.add(new RelatedSkill("Singen"));
		related.add(new RelatedSkill("Musizieren"));
		
		skills.add(new Skill("Musizieren", false, 0, related, Skill.TYPE_NORMAL, Skill.GROUP_OTHERS));
		skills.add(new Skill("Boote fahren", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_OTHERS));
		skills.add(new Skill("Seefahrt", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_OTHERS));
		skills.add(new Skill("Untergrundkunde", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_OTHERS));
		skills.add(new Skill("Wirtschaft", false, 0, null, Skill.TYPE_NORMAL, Skill.GROUP_OTHERS));
		
		return skills.toArray();
	}
	
	public static Object[] getLanguageSkills()
	{
		LinkedList skills = new LinkedList();
		ArrayList related;
		
		skills.add("Sprache wählen");
		skills.add("neu");
		
		related = new ArrayList<RelatedSkill>();
		related.add(new RelatedSkill("(Leygen')Elb'Etelé"));
		related.add(new RelatedSkill("(Riem')Im'Ikemi"));
		
		skills.add(new Skill("(Gil')Alb'Atala", false, 0, related, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "20, Ithen'mar (andere Elfensprachen für 4 EP)"));
		
		related = new ArrayList<RelatedSkill>();
		related.add(new RelatedSkill("(Gil')Alb'Atala"));
		related.add(new RelatedSkill("(Riem')Im'Ikemi"));
		skills.add(new Skill("(Leygen')Elb'Etelé", false, 0, related, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "20, Ithen'mar (andere Elfensprachen für 4 EP)"));
		
		related = new ArrayList<RelatedSkill>();
		related.add(new RelatedSkill("(Gil')Alb'Atala"));
		related.add(new RelatedSkill("(Leygen')Elb'Etelé"));
		skills.add(new Skill("(Riem')Im'Ikemi", false, 0, related, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "20, Ithen'mar (andere Elfensprachen für 4 EP)"));
		skills.add(new Skill("Alodarisch", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "18-Nölgr/7, imperiale Lettern"));
		skills.add(new Skill("Aroktâr", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "18, Torêlak"));

		related = new ArrayList<RelatedSkill>();
		related.add(new RelatedSkill("Eardin"));
		skills.add(new Skill("Carfaszisch ", false, 0, related, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "18, Koraken"));
		skills.add(new Skill("Dorbakuluk", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "20, Gatre'mar"));
		
		related = new ArrayList<RelatedSkill>();
		related.add(new RelatedSkill("Carfaszisch"));
		skills.add(new Skill("Eardin", false, 0, related, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "18, imperiale Lettern"));

		related = new ArrayList<RelatedSkill>();
		related.add(new RelatedSkill("Entempah"));
		skills.add(new Skill("Elamidja", false, 0, related, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "18, Sihim"));
		skills.add(new Skill("Elandii", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "16, Schettenenmalereien"));

		related = new ArrayList<RelatedSkill>();
		related.add(new RelatedSkill("Elamidja"));
		skills.add(new Skill("Entempah", false, 0, related, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "18, Temit bzw auf Stein Hieroglyphen"));
		skills.add(new Skill("Esalim", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "18, Hikana"));
		skills.add(new Skill("Gnutesch/Gnutisch", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "20, Gnutesische/Gnutesianische Keilschrift"));
		skills.add(new Skill("Golothar", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "15, orkische Runenschrift"));
		skills.add(new Skill("Gotharin", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "16, imperiale Lettern"));
		skills.add(new Skill("Nölgr", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "22-Alodarisch/7, keine Schrift"));
		skills.add(new Skill("Nrowski", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "16, Nrowski-Runen"));
		skills.add(new Skill("Oldarokh", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "16-1/8 von Gotharin, Eardin, Alodarisch; keine Schrift"));
		skills.add(new Skill("Posnicz", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "18, keine Schrift"));
		skills.add(new Skill("Sekeph'Samorraw", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "22, Sekeph'Tammon"));
		skills.add(new Skill("Tetylonisch", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "20, Graphia"));
		skills.add(new Skill("Utho'Atalaé", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "12 Sprachen je 12-20, keine Schrift"));
		skills.add(new Skill("Utala", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "16, Geräusche Nachahmen min 1/2 soviel"));
		skills.add(new Skill("Vaciano", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "18, keine Schrift"));
		skills.add(new Skill("Zeolat", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "17, imperiale Lettern"));
		skills.add(new Skill("Zir'Chamas", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "26, Chek'Sariz"));
		skills.add(new Skill("Zyklopäer-Gotharin", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "Gotharin-3, keine Schrift"));
		
		skills.add(new Skill("Kházulim", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "15, Láídar"));
		skills.add(new Skill("Goblinisch", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "8"));
		skills.add(new Skill("Flöte Spielen", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "20-Gs (voller FW benötigt)"));
		skills.add(new Skill("Zeichensprache Stumme", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "6 (voller FW benötigt)"));
		skills.add(new Skill("Zeichensprache Uthos", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "8 (voller FW benötigt"));
		skills.add(new Skill("Zeichensprache Schettenen", false, 0, null, Skill.TYPE_LANGUAGE, Skill.GROUP_NONE, "8 (voller FW benötigt"));
		
		return skills.toArray();
	}
	
	public static Object[] getFontSkills()
	{
		LinkedList skills = new LinkedList();
		ArrayList related = new ArrayList<RelatedSkill>();
		
		skills.add("Schrift wählen");
		skills.add("neu");
		
		skills.add(new Skill("Chek'Sariz", false, 0, null, Skill.TYPE_FONT, Skill.GROUP_NONE, "21"));
		skills.add(new Skill("Gatre'mar", false, 0, null, Skill.TYPE_FONT, Skill.GROUP_NONE, "14"));
		skills.add(new Skill("Graphia", false, 0, null, Skill.TYPE_FONT, Skill.GROUP_NONE, "12"));
		skills.add(new Skill("Gnutesische Keilschrift", false, 0, null, Skill.TYPE_FONT, Skill.GROUP_NONE, "14"));
		skills.add(new Skill("Hieroglyphen", false, 0, null, Skill.TYPE_FONT, Skill.GROUP_NONE, "20"));
		skills.add(new Skill("Hikana", false, 0, null, Skill.TYPE_FONT, Skill.GROUP_NONE, "30"));
		skills.add(new Skill("Imperiale Lettern", false, 0, null, Skill.TYPE_FONT, Skill.GROUP_NONE, "12"));
		skills.add(new Skill("Ithen'mar", false, 0, null, Skill.TYPE_FONT, Skill.GROUP_NONE, "14"));
		skills.add(new Skill("Koraken", false, 0, null, Skill.TYPE_FONT, Skill.GROUP_NONE, "12"));
		skills.add(new Skill("Láídar", false, 0, null, Skill.TYPE_FONT, Skill.GROUP_NONE, "15"));
		
		related = new ArrayList<RelatedSkill>();
		related.add(new RelatedSkill("Torêlak"));
		skills.add(new Skill("Nrowski-Runen", false, 0, null, Skill.TYPE_FONT, Skill.GROUP_NONE, "10 (Torêlak für 4 EP)"));
		skills.add(new Skill("orkische Runenschrift", false, 0, null, Skill.TYPE_FONT, Skill.GROUP_NONE, "20"));
		skills.add(new Skill("Schettenenmalereien", false, 0, null, Skill.TYPE_FONT, Skill.GROUP_NONE, "3 zum Aktivieren, dann FW wie Zeichnen (10)"));
		skills.add(new Skill("Sekeph'Tammon", false, 0, null, Skill.TYPE_FONT, Skill.GROUP_NONE, "26"));

		related = new ArrayList<RelatedSkill>();
		related.add(new RelatedSkill("Temit"));
		skills.add(new Skill("Sihim", false, 0, related, Skill.TYPE_FONT, Skill.GROUP_NONE, "18"));

		related = new ArrayList<RelatedSkill>();
		related.add(new RelatedSkill("Sihim"));
		skills.add(new Skill("Temit", false, 0, related, Skill.TYPE_FONT, Skill.GROUP_NONE, "14"));
		
		related = new ArrayList<RelatedSkill>();
		related.add(new RelatedSkill("Nrowski-Runen"));
		skills.add(new Skill("Torêlak", false, 0, related, Skill.TYPE_FONT, Skill.GROUP_NONE, "16, 13, 10, bei Geometrie 0, 1-6, 7+"));
		
		return skills.toArray();
	}

	public static Object[] getLimitations()
	{
		LinkedList skills = new LinkedList();
		ArrayList from7on = new ArrayList<String>();
		
		skills.add("Einschränkung wählen");
		skills.add("neu");
		skills.add(new Skill("Angst vor...", false, 0, null, Skill.TYPE_LIMITATION, Skill.LIMITATION_COUNTS));
		skills.add(new Skill("Hass auf...", false, 0, null, Skill.TYPE_LIMITATION, Skill.LIMITATION_COUNTS));
		skills.add(new Skill("Wollust", false, 0, null, Skill.TYPE_LIMITATION, Skill.LIMITATION_COUNTS));
		skills.add(new Skill("Neugier", false, 0, null, Skill.TYPE_LIMITATION, Skill.LIMITATION_COUNTS));
		skills.add(new Skill("Rachsucht", false, 0, null, Skill.TYPE_LIMITATION, Skill.LIMITATION_COUNTS));
		skills.add(new Skill("Jähzorn", false, 0, null, Skill.TYPE_LIMITATION, Skill.LIMITATION_COUNTS));
		skills.add(new Skill("Eitelkeit", false, 0, null, Skill.TYPE_LIMITATION, Skill.LIMITATION_COUNTS));
		skills.add(new Skill("Reinlichkeit", false, 0, null, Skill.TYPE_LIMITATION, Skill.LIMITATION_COUNTS));
		skills.add(new Skill("Ehrgefühl", false, 0, null, Skill.TYPE_LIMITATION, Skill.LIMITATION_COUNTS));
		skills.add(new Skill("Goldgier", false, 0, null, Skill.TYPE_LIMITATION, Skill.LIMITATION_COUNTS));
		skills.add(new Skill("Arroganz", false, 0, null, Skill.TYPE_LIMITATION, Skill.LIMITATION_COUNTS));
		skills.add(new Skill("Stolz", false, 0, null, Skill.TYPE_LIMITATION, Skill.LIMITATION_COUNTS));
		skills.add(new Skill("Aberglauben", false, 0, null, Skill.TYPE_LIMITATION, Skill.LIMITATION_COUNTS));
		skills.add(new Skill("Totenangst", false, 0, null, Skill.TYPE_LIMITATION, Skill.LIMITATION_COUNTS));
		skills.add(new Skill("Höhenangst", false, 0, null, Skill.TYPE_LIMITATION, Skill.LIMITATION_COUNTS));
		skills.add(new Skill("Raumangst", false, 0, null, Skill.TYPE_LIMITATION, Skill.LIMITATION_COUNTS));
		skills.add(new Skill("Platzangst", false, 0, null, Skill.TYPE_LIMITATION, Skill.LIMITATION_COUNTS));
		
		return skills.toArray();
	}

	public static String[] getVampireAbilites()
	{
		String[] skills ={"Fledermaus (2)", 
				"Nebelgestalt (5)", 
				"Fledermausschwarm (3)", 
				"Wolfsgestalt (2)", 
				"Hollywood-Gestalt (1)", 
				"Fluggestalt (3)", 
				"Nosferatu (6)", 
				"Schwingen (10)", 
				"Herr der Ratten (3)", 
				"Herr der Fledermäuse (2)", 
				"Herr der Wölfe (2+Anzahl)", 
				"Herr der Pferde (2+Anzahl)", 
				"Schwerkraftkontrolle (3)", 
				"Mauern klettern (2)", 
				"Herr der Winde (8)", 
				"Meister der Nebel (beliebig)"}; 
		
		return skills;
	}
	
	public static String[] getWerewolfAbilites()

	{
		String[] skills ={"Erhöhte Magieresistenz I (5)", 
				"Erhöhte Magieresistenz II (5)", 
				"Erhöhte Magieresistenz III (5)", 
				"Kontrollierter Blutrausch I (5)", 
				"Kontrollierter Blutrausch II (5)", 
				"Kontrollierter Blutrausch III (5)", 
				"Ruf des Rudels (3)",
				"Herr der Wölfe (Anzahl)", 
				"Herr der Pferde (2+Anzahl)", 
				"Verschmelzen mit Schatten (5)", 
				"Herr der Winde (bis 8)", 
				"Zauberer (5/10)"}; 
		
		return skills;
	}
	
	public static String[] getSpellSkills()
	{
		String[] skills = {"Beherrschung", 
				"Bewegung", 
				"Elementarmagie", 
				"Entzaubern", 
				"Gedankenlesen", 
				"Heilung", 
				"Hellsicht",
				"Illusion", 
				"Manabälle", 
				"Sphärenblick", 
				"Veränderung", 
				"Verschleierung", 
				"Zauberschild",
				"Artefaktmagie",
				"Dämonenanrufung",
				"Dämonenmeisterschaft",
				"Golemantie", 
				"Hybridologie", 
				"Nekromantie", 
				"Schattenmagie"
		};
		
		return skills;
	}
	
	
	public static String[] getArchmagicSpellSkills() {
		String[] skills = new String [ARCHMAGESKILLS];
		for (int i = 0; i < SPELLSKILLS; i++)
			skills[i] = "Erz-" + getSpellSkills()[i];
		
		return skills;
	}
	
	public LinkedList getChangerSkills()
	{
		LinkedList skills = new LinkedList();
		
		return skills;
	}
	
	public LinkedList getElementalDruidSkills()
	{
		LinkedList skills = new LinkedList();
		
		return skills;
	}
	public LinkedList getShamanSkills()
	{
		LinkedList skills = new LinkedList();
		
		return skills;
	}
	public LinkedList getControllerDruidSkills()
	{
		LinkedList skills = new LinkedList();
		
		return skills;
	}
	public LinkedList getPriestSkills()
	{
		LinkedList skills = new LinkedList();
		
		return skills;
	}
	public LinkedList getTempleKnightSkills()
	{
		LinkedList skills = new LinkedList();
		
		return skills;
	}
	
	public LinkedList getOrelierSkills()
	{
		LinkedList skills = new LinkedList();
		
		return skills;
	}
	
	public LinkedList getIntuitivMagicSkills()
	{
		LinkedList skills = new LinkedList();
		
		return skills;
	}	
}
