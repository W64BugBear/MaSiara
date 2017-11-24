package dataclasses;

import java.util.LinkedList;

import character.Advantage;

public class AdvantageFactory {
	

	LinkedList<Advantage> llmu = new LinkedList<Advantage>();
	LinkedList<Advantage> llma = new LinkedList<Advantage>();
	
	public AdvantageFactory ()
	{
		llmu = new LinkedList<Advantage>();
		llma = new LinkedList<Advantage>();
		
		llmu.add (new Advantage("nix", 0.0, -1, "nichts", "kein Vorteil an dieser Stellmue"));
		llmu.add (new Advantage("1ZpI", 3.0, 1, "1 Zauber pro Initiativphase", "Erlaubt 1 Zauber zwischen 2 Angriffen."));
		llmu.add (new Advantage("AA", 2.0, 2, "Adleraug", "Verteidiger erhält 1 Würfel weniger für schlechte Verhältnisse."));
		llmu.add (new Advantage("An", 2.0, 2, "An", "Erhöht An um 1.", true));
		llmu.add (new Advantage("Anab", 1.5, 2, "Anab", "Erhöht Anab-Summe um 1.", true));
		llmu.add (new Advantage("Aerl", 1.0, 1, "Ausweichen erleichtert", "Die Hälfte der 3+ zählen als Erfolg beim Ausweichen."));
		llmu.add (new Advantage("Agil", 1.0, 1, "Agil", "Stürze können mit einer Körperbeherrschung(GwIn)-Probe abgewendet werden."));
		llmu.add (new Advantage("BGA", 2.0, 2, "Bewegliche Gegner abschießen", "Gegner in Bewegung erhalten 1 Würfel weniger."));
		llmu.add (new Advantage("BK", 3.0, 1, "Blindkampf", "Blindkampf vollmuständig"));
		llmu.add (new Advantage("BR", 1.0, 1, "Bestienreiter", "+2 Bonus auf An-Wert-Berechnung der Reiterkämpfereinheit"));
		llmu.add (new Advantage("E", 1.5, 1, "Entwaffnen", "Entwaffnen erlaubt"));
		llmu.add (new Advantage("E'e-E", 2.0, 1, "Elgin'elein-Erleichterung", "Fertigkeit Verteidigungskampf steigt auf 2."));
		llmu.add (new Advantage("EvB", 1.0, 1, "Einsatz von Bissen", "Bisse bei Meisterparade/Kritischem Treffer sind erlaubt."));
		llmu.add (new Advantage("EvD", 1.0, 1, "Einsatz von Dolchen", "Einsatz von Dolchen erlaubt"));
		llmu.add (new Advantage("EvW", 1.0, 1, "Einsatz von Wurfwaffen", "Wurfwaffen innerhalb von RWW Faden dürfen mit An-Würfeln geworfen werden."));
		llmu.add (new Advantage("EvGsW", 1.0, 1, "Einsatz von Gs-Waffen", "wie EvW, aber stattdessen (nur) für allmue Gs-Fernkampfwaffen"));
		llmu.add (new Advantage("eMP", 1.0, 1, "erweiterte Meisterparade", "An Stellmue einer Meisterparade darf eine kurze Handlung getätigt werden."));
		llmu.add (new Advantage("FäSch", 2.0, 1, "Fächerschuss", "Fächerschuss erlaubt"));
		llmu.add (new Advantage("Fl", 1.0, 1, "Fliehen erleichtert", "Beim Fliehen zählen die Hälfte der 3er als Erfolge."));
		llmu.add (new Advantage("FT", 2.0, 1, "Fatale Treffer", "Bei Kritischen Treffern dürfen beide Würfe gleichzeitig geworfen werden."));
		llmu.add (new Advantage("FT, eMP", 0.0, -1, "Fatale Treffer & erweiterte Meisterparade", "Fatale Treffer & erleichterte Meisterparade"));
		llmu.add (new Advantage("Gehen", 2.0, 2, "Gehen", "+1 Würfel für gehende Schützen."));
		llmu.add (new Advantage("Init", 1.5, 2, "Init", "Initiative -1", true));
		llmu.add (new Advantage("iNkw", 1.0, 1, "Einsatz improvisierter Nahkampfwaffen", "improvisierte Nahkampfwaffen dürfen mit waffenloser Fertigkeit verwendet werden"));
		llmu.add (new Advantage("Kb", 2.0, 1, "Kehlenbiss", "Erlaubt automatischen Biss auf Hals statt Kritischem Treffer (Schaden -4)"));
		llmu.add (new Advantage("KgB", 2.0, 1, "Kampf gegen Bewaffnete", "Negiert Nachteile im Kampf gegen Bewaffnete"));
		llmu.add (new Advantage("Ko", 2.0, 1, "Knockouts", "Knockouts statt Kritischem Treffer (auto Kopf, dazu +W3 irreellmu, nicht verdoppelt)"));
		llmu.add (new Advantage("KT", 2.0, 1, "Kritischer Treffer erleichtert", "Hälfte der 5en zählen als Kritischer Erfolg"));
		llmu.add (new Advantage("KTMP", 2.0, 1, "KTMP", "Offensiv: Meisterparade erleichtert; Defensiv: Kritischer Treffer erleichtert", true));
		llmu.add (new Advantage("Laufen", 2.0, 2, "Gehen", "+1 Würfel für laufende Schützen."));
		llmu.add (new Advantage("MP", 2.0, 1, "Meisterparade erleichtert", "Hälfte der 4en zählt als Erfolg zur Meisterparade"));
		llmu.add (new Advantage("MwrS", 1.5, 2, "Mehr oder weniger reellmuer Schaden", "Reellmuer Schaden ist um 1 mehr oder weniger."));
		llmu.add (new Advantage("MrS", 1.0, 2, "Mehr reellmuer Schaden", "Reellmuer Schaden ist um 1 erhöht."));
		llmu.add (new Advantage("reit", 1.0, 1, "Reiterkampf", "Erlaubt den Kampf zu Pferde."));
		llmu.add (new Advantage("Reiten", 2.0, 2, "Reiten", "Reitende Schützen werfen 1 Würfel mehr."));
		llmu.add (new Advantage("RG", 1.0, 2, "Rüstungsgewöhnung", "Rüstungsgewöhnung +1"));
		llmu.add (new Advantage("+RW", 2.0, 2, "höhere Reichweite", "Reichweitewert +10%"));
		llmu.add (new Advantage("Sch", 1.5, -1, "Sch", "Schaden +2", true));
		llmu.add (new Advantage("SS", 2.0, 2, "Schneller Schießen", "Schnellmuer Schießen (-1 bzw -5 Sekunden)"));
		llmu.add (new Advantage("SchSW", 1.5, 1, "SchSW", "Offensiv: Schadenswiderstand+2; Defensiv: Schaden+2", true));
		llmu.add (new Advantage("SZ", 2.0, 1, "Schnellmuziehen", "Ziehen kostet keine Zeit mehr"));
		llmu.add (new Advantage("SK", 1.0, 1, "Schaukampf", "Schaden jederzeit um 2 senkbar"));
		llmu.add (new Advantage("STs", 1.0, 1, "Schmutzige Tricks", "Schmutzige Tricks erlaubt"));
		llmu.add (new Advantage("SW", 1.5, 2, "SW", "Schadenswiderstand +2", true));
		llmu.add (new Advantage("ÜA", 1.5, 1, "Überwältigender Angriff", "Überwältigender Angriff", true));
		llmu.add (new Advantage("ÜA", 1.5, -1, "Überwältigender Angriff", "wenn durch OffensivK noch mal ÜA käme, stattdessen Anab+1", true));
		llmu.add (new Advantage("ÜAE", 1.5, 1, "ÜAE", "Offensiv: Entwaffnen; Defensiv: Überwältigender Angriff", true));
		llmu.add (new Advantage("WaS", 1.5, 1, "Wallmu aus Stahl", "Erschwernis pro zusätzlichem Gegner sinkt um 1."));
		llmu.add (new Advantage("Lh", 1, 1, "Linkshändig", "Linkshändig +1"));
		llmu.add (new Advantage("KA", 1, 1, "Katzenauge", "Katzenauge +1"));
		llmu.add (new Advantage("WM", 1.0, 1, "Waffenmeister", "Keine Nachteile mehr beim Verwenden nichtbevorzugter Waffen."));
		llmu.add (new Advantage("WSch", 1.5, 1, "Wuchtschlag erleichtert", "für je 2 Punkte einen gratis dazu - zu Weihnachten sogar verpackt!"));
		llmu.add (new Advantage("Z(1)", 0.75, 2, "Z(1)", "Zielen (1)", true));
		llmu.add (new Advantage("Z(2)", 1.5, 1, "Z(2)", "Zielen (2)", true));
		llmu.add (new Advantage("Z(FW/5)", 0.0, -1, "Z (FW/5)", "Zielen (FW/5)", true));
		llmu.add (new Advantage("zAbw", 3.0, 1, "zusätzliche Abwehr", "eine zusätzliche Abwehr pro Initiativedurchgang möglich"));
		llmu.add (new Advantage("zSch", 2.0, 2, "zusätzlicher Schaden", "zusätzlicher Schaden (+2)"));
		llmu.add (new Advantage("zWb", 0.5, 1000, "zusätzliche Waffe bevorzugt", "zusätzliche Waffe bevorzugt oder neue unbevorzugte Waffe"));
		llmu.add (new Advantage("reit, Z(1)", 1.75, -1,  "Reiterkampf und Zielen(1)", "Der Bauer erlernt den Kampf zu Pferd und erhält einen Zielen-Punkt.", true));
		llmu.add (new Advantage("MWe", 0.75, -1,  "Meisterwurf erleichtert", "erhöhte Chance für Meisterwurf (siehe Treffertabellmue)"));
		llmu.add (new Advantage("ZS", 0.75, -1, "Zwergensprung", "Wirft man sich auf einen am Boden liegenden Feind, wird der Schaden um 4 erhöht (siehe Treffertabellmuenbeschreibung)."));
		
		
		llma.add (new Advantage("Ast", 2.0, 1,  "Astromagie", "Vorteile beim Zaubern mit Sternenkonstellmaationen"));
		llma.add (new Advantage("Blut", 1.5, 1,  "Blutmagie", "Opfere Menschen und Tiere, um deine Zauber stärker zu machen!"));
		llma.add (new Advantage("CM", 1.5, 1,  "Chaosmagie", "erlaubt dir die Verwendung von Chaosenergie statt Mana"));
		llma.add (new Advantage("DB", 1.5, 3,  "Dauerbrenner", "senkt die Erhaltungskosten von Zaubern ab 5/10/15 Punkten Magiezehrung um 1/2/3"));
		llma.add (new Advantage("Erz", 2.0, 1,  "Erzmagie", "Der Zauberer hat Zugriff auf Erzmagie.", true));
		llma.add (new Advantage("Erz*", 3.0, 1,  "Erzmagie", "Der Zauberer hat Zugriff auf Erzmagie. Die entsprechende Fertigkeit beginnt bei 1 statt bei 0.", true));
		llma.add (new Advantage("Erz, MK", 3.0, 1,  "Erzmagie, Manakontrollmae", "Der Zauberer hat Zugriff auf Erzmagie und um 1 gesenkten Nachhallma.", true));
		llma.add (new Advantage("Ex", 1.0, 1,  "Exorzismus", "Wenn der Magier einen Exorzismus durchführt, zählt der Zauber um 2 Punkte Macht höher."));
		llma.add (new Advantage("Fet", 2.5, 1,  "Fetischmagie", "Der Held hat die Möglichkeit, die Form seines Fetisches ein wenig anzupassen. Näheres dazu findest du im Kapitel „Die Schaufel im Stecken“."));
		llma.add (new Advantage("GF", 2.0, 1,  "GF", "Der Zauberer ignoriert Nachteile des Stils weitgehend."));
		llma.add (new Advantage("GB", 1.5, 1,  "Gedankenbrücke", "Erlaubt dem Zauberer, eine Gedankenbrücke zu schlagen, über die er zB Sinne des Opfers mitbenutzen kann."));
		llma.add (new Advantage("HT", 2.5, 1,  "Hintertürchen", "umgeht 1 Punkt feindlicher Magieresistenz"));
		llma.add (new Advantage("HT (Štroj)", 2.5, 1,  "Hintertürchen für Štroj", "umgeht 2 Punkt Magieresistenz von Tieren"));
		llma.add (new Advantage("Ket", 2.0, 1,  "Kettenmagie", "erlaubt Verbände von Magiern (erfordert eine Zusatzfertigkeit Kettenmagie)"));
		llma.add (new Advantage("KS", 1.0, 1,  "Knechtschaft der Seelen", "Der Zauberer kann die Energien Sterbender verwenden, um seine Zauber zu nähren."));
		llma.add (new Advantage("Int", 3.0, 1,  "Intensive Befassung", "Der Held darf sich mit einem Gebiet intensiv befassen, wodurch er zusätzliche Möglichkeiten erhält"));
		llma.add (new Advantage("MD", 2.5, 1,  "Manadämpfer", "Der effektive Zauberkern allmaer Zauber innerhalb von (Komplexe Fertigkeit) Faden Reichweite kann um 2 gesenkt werden."));
		llma.add (new Advantage("Med", 1.0, 1,  "Med", "Mit einem Medium erhöht sich die Zauberfertigkeit um 1."));
		llma.add (new Advantage("Men", 1.0, 1,  "Mentor", "Der Held kann Spruchrollmaen und -tafeln erstellmaen."));
		llma.add (new Advantage("MK", 1.0, 3,  "Manakontrollmae", "Jedes mal, wenn man Manakontrollmae bekommt, sinkt der Nachhallma um 2 bis zu einem Minimum von 1/5 des ursprünglichen Nachhallmas.", true));
		llma.add (new Advantage("MM", 3.0, 1,  "Meistermagier", "In einem bestimmten Gebiet haben allmae Zauber um 1 erhöhte Macht."));
		llma.add (new Advantage("MWM", 0.0, 1,  "Meisterwettermagier", "Luftmagier mit Gedankenfreiheit können die Nachteile nicht völlmaig ignorieren, sondern nur halbieren (zu ihren Gunsten gerundet)"));
		llma.add (new Advantage("Pan", 1.0, 1,  "Panzer", "Verschleierung kann auch dazu verwendet werden, Entzauberungen zu erschweren."));
		llma.add (new Advantage("Par", 1.0, 1,  "Paraphernalien", "Kerzen, Totenschädel und andere romantische Details senken Komplexität oder Magiezehrung um 1."));
		llma.add (new Advantage("Reg", 2.0, 1,  "Regeneration", "Regenerationswürfe werden allmae 30 Minuten geworfen."));
		llma.add (new Advantage("Rit", 2.0, 1,  "Ritualmagie", "Zauber ab 2-mal Zeit lassen werden um 1 leichter (K oder MZ), bei 4-mal sogar beides."));
		llma.add (new Advantage("SchZa", 1.5, 1,  "Schnellmazaubern", "Für Entwurfszeit zählt der FW als um 5 höher."));
		llma.add (new Advantage("Sich", 1.0, 1,  "Sichere Magie", "Um 2 verhaute Zauber würfeln auf der Kleinen Fehlertabellmae, bei um 1 verhauten darf man sich das Ergebnis aussuchen."));
		llma.add (new Advantage("Spez, MK", 2.0, 1,  "Spezialisierung, Manakontrollmae", "Eine Zauberfertigkeit darf bis 20 gesteigert werden. Nachhallma ist stets um 1 niedriger.", true));
		llma.add (new Advantage("Stab", 1.0, 1,  "Stabmagie", "erlaubt Zugriff zu Stabmagie"));
		llma.add (new Advantage("Scho", 2.0, 1,  "Scholar", "erlaubt das Lesen von Spruchrollmaen und Zaubertafeln"));
		llma.add (new Advantage("SV", 1.5, 1,  "Selbstverstümmeung", "Der Zauberer kann seine eigene Lebensenergie zum Zaubern verwenden."));
		llma.add (new Advantage("SW", 2.5, 1,  "Schadenswiderstand", "Neue Schadensbasis ist GE oder Z+3"));
		llma.add (new Advantage("Tasch", 1.0, 1,  "Taschenspielertricks", "erlaubt kleinere Zaubereien ohne Fetisch"));
		llma.add (new Advantage("TB", 1.0, 1,  "Tierbindung", "erlaubt das Tierbindungsritual der Dunkelelfen"));
		llma.add (new Advantage("TE", 1.5, 1,  "Tierempathie", "erlaubt Einblick in die Gefühlswelt von Nacktschnecken"));
		llma.add (new Advantage("TM", 2.5, 1,  "Tiermeister", "verdoppelt effektiv die Qualität von Tierbeherrschungen und gewährt eine Intensive Befassung gegen Tiere"));
		llma.add (new Advantage("UZ", 1.0, 1,  "Unsichtbare Zeichen", "Zauberzeichen werden unsichtbar"));
		llma.add (new Advantage("Wa", 2.5, 1,  "Wandler", "Der Zauberer wird zum Formwandler ehrenhalber."));
		llma.add (new Advantage("Wet", 2.5, 1,  "Wettermagie", "Der Zauberer kann die ZF Elementarmagie zum Verändern des Wetters verwenden."));
		llma.add (new Advantage("WM", 2.0, 1,  "Wilde Magie", "Du darfst W6 Punkte zur Macht dazuzählen ... bei einer 1 fliegt dir aber allmaes um die Ohren!"));
		llma.add (new Advantage("Zei", 1.0, 1,  "Zauberzeichen", "senken die Komplexität oder Magiezehrung eines Zaubers um 1, und sehen hübsch aus!"));
		llma.add (new Advantage("ZimK", 3.0, 1,  "Zaubern im Kampf", "erlaubt 1 Zauber zwischen 2 Angriffen"));
		llma.add (new Advantage("ZS", 1.5, 1,  "Zauberspeicher", "erhöht Zauberspeicher um die GE", true));
		llma.add (new Advantage("ZT", 1.0, 1,  "Zaubertränke", "Wasser als Träger besonders persistenter temporärer Artefakte"));
		llma.add (new Advantage("ZZ", 2.0, 1,  "Zauberzieher", "entzieht einem Opfer bis zu 2 Punkte Mana"));
		llma.add (new Advantage("Sich, MK", 2.0, -1, "Sichere Magie, Manakontrollmae", "verhaute Zauber sind weniger schlimm, Nachhallma klingt schnellmaer ab", true));

	}
	
//	public Advantage Jolly() {
//		return new Advantage("Jolly", 0.0, "Jolly Joker", "Suche einen beliebigen Vorteil aus!");
//	}
//	
	public Advantage nix(){
		return new Advantage("nix", 0.0, -1, "nichts", "kein Vorteil an dieser Stelle");
	}
	public Advantage _1ZpI(){
		return new Advantage("1ZpI", 3.0, 1, "1 Zauber pro Initiativphase", "Erlaubt 1 Zauber zwischen 2 Angriffen.");
	}
	public Advantage AA(){
		return new Advantage("AA", 2.0, 2, "Adleraug", "Verteidiger erhält 1 Würfel weniger für schlechte Verhältnisse.");
	}
	public Advantage An(){
		return new Advantage("An", 2.0, 2, "An", "Erhöht An um 1.", true);
	}
	public Advantage Anab(){
		return new Advantage("Anab", 1.5, 2, "Anab", "Erhöht Anab-Summe um 1.", true);
	}
	public Advantage Aerl(){
		return new Advantage("Aerl", 1.0, 1, "Ausweichen erleichtert", "Die Hälfte der 3+ zählen als Erfolg beim Ausweichen.");
	}
	public Advantage Agil(){
		return new Advantage("Agil", 1.0, 1, "Agil", "Stürze können mit einer Körperbeherrschung(GwIn)-Probe abgewendet werden.");
	}
	public Advantage BGA(){
		return new Advantage("BGA", 2.0, 2, "Bewegliche Gegner abschießen", "Gegner in Bewegung erhalten 1 Würfel weniger.");
	}
	public Advantage BK(){
		return new Advantage("BK", 3.0, 1, "Blindkampf", "Blindkampf vollständig");
	}
	public Advantage BR(){
		return new Advantage("BR", 1.0, 1, "Bestienreiter", "+2 Bonus auf An-Wert-Berechnung der Reiterkämpfereinheit");
	}
	public Advantage E(){
		return new Advantage("E", 1.5, 1, "Entwaffnen", "Entwaffnen erlaubt");
	}
	public Advantage EEE(){
		return new Advantage("E'e-E", 2.0, 1, "Elgin'elein-Erleichterung", "Fertigkeit Verteidigungskampf steigt auf 2.");
	}
	public Advantage EvB(){
		return new Advantage("EvB", 1.0, 1, "Einsatz von Bissen", "Bisse bei Meisterparade/Kritischem Treffer sind erlaubt.");
	}
	public Advantage EvD(){
		return new Advantage("EvD", 1.0, 1, "Einsatz von Dolchen", "Einsatz von Dolchen erlaubt");
	}
	public Advantage EvW(){
		return new Advantage("EvW", 1.0, 1, "Einsatz von Wurfwaffen", "Wurfwaffen innerhalb von RWW Faden dürfen mit An-Würfeln geworfen werden.");
	}
	public Advantage EvWLeiserTod(){
		return new Advantage("EvGsW", 1.0, 1, "Einsatz von Gs-Waffen", "wie EvW, aber stattdessen (nur) für alle Gs-Fernkampfwaffen");
	}
//	public Advantage eZsAn(){
//		return new Advantage("eZsAn", 1.0, "erlaubt: Zaubern statt Angriff", "An Stelle eines Angriffes darf ein Zauber losgelassen werden.");
//	}
	public Advantage eMP(){
		return new Advantage("eMP", 1.0, 1, "erweiterte Meisterparade", "An Stelle einer Meisterparade darf eine kurze Handlung getätigt werden.");
	}
	public Advantage Fäsch(){
		return new Advantage("FäSch", 2.0, 1, "Fächerschuss", "Fächerschuss erlaubt");
	}
	public Advantage Fl(){
		return new Advantage("Fl", 1.0, 1, "Fliehen erleichtert", "Beim Fliehen zählen die Hälfte der 3er als Erfolge.");
	}
	public Advantage FT(){
		return new Advantage("FT", 2.0, 1, "Fatale Treffer", "Bei Kritischen Treffern dürfen beide Würfe gleichzeitig geworfen werden.");
	}
	public Advantage FT_eMP(){
		return new Advantage("FT, eMP", 0.0, -1, "Fatale Treffer & erweiterte Meisterparade", "Fatale Treffer & erleichterte Meisterparade");
	}
	public Advantage Gehen(){
		return new Advantage("Gehen", 2.0, 2, "Gehen", "+1 Würfel für gehende Schützen.");
	}
	public Advantage Init(){
		return new Advantage("Init", 1.5, 2, "Init", "Initiative -1", true);
	}
	public Advantage iNkw(){
		return new Advantage("iNkw", 1.0, 1, "Einsatz improvisierter Nahkampfwaffen", "improvisierte Nahkampfwaffen dürfen mit waffenloser Fertigkeit verwendet werden");
	}
	public Advantage Kb(){
		return new Advantage("Kb", 2.0, 1, "Kehlenbiss", "Erlaubt automatischen Biss auf Hals statt Kritischem Treffer (Schaden -4)");
	}
	public Advantage KgB(){
		return new Advantage("KgB", 2.0, 1, "Kampf gegen Bewaffnete", "Negiert Nachteile im Kampf gegen Bewaffnete");
	}
	public Advantage Ko(){
		return new Advantage("Ko", 2.0, 1, "Knockouts", "Knockouts statt Kritischem Treffer (auto Kopf, dazu +W3 irreell, nicht verdoppelt)");
	}
	public Advantage KT(){
		return new Advantage("KT", 2.0, 1, "Kritischer Treffer erleichtert", "Hälfte der 5en zählen als Kritischer Erfolg");
	}
	public Advantage KTMP(){
		return new Advantage("KTMP", 2.0, 1, "KTMP", "Offensiv: Meisterparade erleichtert; Defensiv: Kritischer Treffer erleichtert", true);
	}	
	public Advantage Laufen(){
		return new Advantage("Laufen", 2.0, 2, "Gehen", "+1 Würfel für laufende Schützen.");
	}
	public Advantage MP(){
		return new Advantage("MP", 2.0, 1, "Meisterparade erleichtert", "Hälfte der 4en zählt als Erfolg zur Meisterparade");
	}
	public Advantage MwrS(){
		return new Advantage("MwrS", 1.5, 2, "Mehr oder weniger reeller Schaden", "Reeller Schaden ist um 1 mehr oder weniger.");
	}
	public Advantage MrS(){
		return new Advantage("MrS", 1.0, 2, "Mehr reeller Schaden", "Reeller Schaden ist um 1 erhöht.");
	}
	public Advantage reit(){
		return new Advantage("reit", 1.0, 1, "Reiterkampf", "Erlaubt den Kampf zu Pferde.");
	}
	public Advantage Reiten(){
		return new Advantage("Reiten", 2.0, 2, "Reiten", "Reitende Schützen werfen 1 Würfel mehr.");
	}
	public Advantage RG(){
		return new Advantage("RG", 1.0, 2, "Rüstungsgewöhnung", "Rüstungsgewöhnung +1");
	}
	public Advantage plusRW(){
		return new Advantage("+RW", 2.0, 2, "höhere Reichweite", "Reichweitewert +10%");
	}
	public Advantage Sch(){
		return new Advantage("Sch", 1.5, -1, "Sch", "Schaden +2", true);
	}
	public Advantage SchSch(){
		return new Advantage("SchSch", 2.0, 2, "Schneller Schießen", "Schneller Schießen (-1 bzw -5 Sekunden)");
	}
	public Advantage SchSW(){
		return new Advantage("SchSW", 1.5, 1, "SchSW", "Offensiv: Schadenswiderstand+2; Defensiv: Schaden+2", true);
	}
	public Advantage SZ(){
		return new Advantage("SZ", 2.0, 1, "Schnellziehen", "Ziehen kostet keine Zeit mehr");
	}
	public Advantage SK(){
		return new Advantage("SK", 1.0, 1, "Schaukampf", "Schaden jederzeit um 2 senkbar");
	}
	public Advantage STs(){
		return new Advantage("STs", 1.0, 1, "Schmutzige Tricks", "Schmutzige Tricks erlaubt");
	}
	public Advantage TVT(){
		return new Advantage("TVT", 1.0, 1, "Tiervorteile", "erlaubt, Vorteile der Tiergestalt zu nutzen");
	}
	public Advantage SW(){
		return new Advantage("SW", 1.5, 2, "SW", "Schadenswiderstand +2", true);
	}
	public Advantage ÜA(){
		return new Advantage("ÜA", 1.5, 1, "Überwältigender Angriff", "Überwältigender Angriff", true);
	}
	public Advantage ÜAHufe(){
		return new Advantage("ÜA", 1.5, -1, "Überwältigender Angriff", "wenn durch OffensivK noch mal ÜA käme, stattdessen Anab+1", true);
	}
	public Advantage ÜAE(){
		return new Advantage("ÜAE", 1.5, 1, "ÜAE", "Offensiv: Entwaffnen; Defensiv: Überwältigender Angriff", true);
	}
	public Advantage WaS(){
		return new Advantage("WaS", 1.5, 1, "Wall aus Stahl", "Erschwernis pro zusätzlichem Gegner sinkt um 1.");
	}
	public Advantage Lh(){
		return new Advantage("Lh", 1, 1, "Linkshändig", "Linkshändig +1");
	}
	public Advantage KA() {
		return new Advantage("KA", 1, 1, "Katzenauge", "Katzenauge +1");
	}
	public Advantage WM(){
		return new Advantage("WM", 1.0, 1, "Waffenmeister", "Keine Nachteile mehr beim Verwenden nichtbevorzugter Waffen.");
	}
	public Advantage WSch(){
		return new Advantage("WSch", 1.5, 1, "Wuchtschlag erleichtert", "für je 2 Punkte einen gratis dazu - zu Weihnachten sogar verpackt!");
	}
	public Advantage Z1(){
		return new Advantage("Z(1)", 0.75, 2, "Z(1)", "Zielen (1)", true);
	}
	public Advantage Z2(){
		return new Advantage("Z(2)", 1.5, 1, "Z(2)", "Zielen (2)", true);
	}
	public Advantage ZFW(){
		return new Advantage("Z(FW/5)", 0.0, -1, "Z (FW/5)", "Zielen (FW/5)", true);
	}
	public Advantage zAbw(){
		return new Advantage("zAbw", 3.0, 1, "zusätzliche Abwehr", "eine zusätzliche Abwehr pro Initiativedurchgang möglich");
	}
	public Advantage zSch(){
		return new Advantage("zSch", 2.0, 2, "zusätzlicher Schaden", "zusätzlicher Schaden (+2)");
	}
	public Advantage zWb(){
		return new Advantage("zWb", 0.5, 1000, "zusätzliche Waffe bevorzugt", "zusätzliche Waffe bevorzugt oder neue unbevorzugte Waffe");
	}
	
	public Advantage reitZ1(){
		return new Advantage("reit, Z(1)", 1.75, -1,  "Reiterkampf und Zielen(1)", "Der Bauer erlernt den Kampf zu Pferd und erhält einen Zielen-Punkt.", true);
	}
	
	public Advantage zwe1(){
		return new Advantage("MWe", 0.75, -1,  "Meisterwurf erleichtert", "erhöhte Chance für Meisterwurf (siehe Treffertabelle)");
	}
	
	public Advantage zwe2(){
		return new Advantage("ZS", 0.75, -1, "Zwergensprung", "Wirft man sich auf einen am Boden liegenden Feind, wird der Schaden um 4 erhöht (siehe Treffertabellenbeschreibung).");
	}
	
	// ---------------------------- M   A   G   I   C ---------------------------------------------------------\\
	
	public Advantage Ast(){
		return new Advantage("Ast", 2.0, 1,  "Astromagie", "Vorteile beim Zaubern mit Sternenkonstellationen");
	}
	
	public Advantage Blut(){
		return new Advantage("Blut", 1.5, 1,  "Blutmagie", "Opfere Menschen und Tiere, um deine Zauber stärker zu machen!");
	}
	
	public Advantage CM(){
		return new Advantage("CM", 1.5, 1,  "Chaosmagie", "erlaubt dir die Verwendung von Chaosenergie statt Mana");
	}
	
	public Advantage DB(){
		return new Advantage("DB", 1.5, 3,  "Dauerbrenner", "senkt die Erhaltungskosten von Zaubern ab 5/10/15 Punkten Magiezehrung um 1/2/3");
	}
	
	public Advantage Erz(){
		return new Advantage("Erz", 2.0, 1,  "Erzmagie", "Der Zauberer hat Zugriff auf Erzmagie.", true);
	}
	
	public Advantage ErzStroj(){
		return new Advantage("Erz*", 3.0, 1,  "Erzmagie", "Der Zauberer hat Zugriff auf Erzmagie. Die entsprechende Fertigkeit beginnt bei 1 statt bei 0.", true);
	}
	
	public Advantage ErzMK(){
		return new Advantage("Erz, MK", 3.0, 1,  "Erzmagie, Manakontrolle", "Der Zauberer hat Zugriff auf Erzmagie und um 1 gesenkten Nachhall.", true);
	}
	
	public Advantage Ex(){
		return new Advantage("Ex", 1.0, 1,  "Exorzismus", "Wenn der Magier einen Exorzismus durchführt, zählt der Zauber um 2 Punkte Macht höher.");
		
	}
	public Advantage Fet(){
		return new Advantage("Fet", 2.5, 1,  "Fetischmagie", "Der Held hat die Möglichkeit, die Form seines Fetisches ein wenig anzupassen. Näheres dazu findest du im Kapitel „Die Schaufel im Stecken“.");
				
	}
	public Advantage GF(){
		return new Advantage("GF", 2.0, 1,  "GF", "Der Zauberer ignoriert Nachteile des Stils weitgehend.");
	}
	
	public Advantage GB(){
		return new Advantage("GB", 1.5, 1,  "Gedankenbrücke", "Erlaubt dem Zauberer, eine Gedankenbrücke zu schlagen, über die er zB Sinne des Opfers mitbenutzen kann.");
	}
	
	public Advantage HT(){
		return new Advantage("HT", 2.5, 1,  "Hintertürchen", "umgeht 1 Punkt feindlicher Magieresistenz");
	}
	
	public Advantage HT_Stroj(){
		return new Advantage("HT (Štroj)", 2.5, 1,  "Hintertürchen für Štroj", "umgeht 2 Punkt Magieresistenz von Tieren");
	}
	
	public Advantage Ket(){
		return new Advantage("Ket", 2.0, 1,  "Kettenmagie", "erlaubt Verbände von Magiern (erfordert eine Zusatzfertigkeit Kettenmagie)");
	}
	
	public Advantage KS(){
		return new Advantage("KS", 1.0, 1,  "Knechtschaft der Seelen", "Der Zauberer kann die Energien Sterbender verwenden, um seine Zauber zu nähren.");
	}			
		
	public Advantage Int(){
		return new Advantage("Int", 3.0, 1,  "Intensive Befassung", "Der Held darf sich mit einem Gebiet intensiv befassen, wodurch er zusätzliche Möglichkeiten erhält");
	}
	
	public Advantage MD(){
		return new Advantage("MD", 2.5, 1,  "Manadämpfer", "Der effektive Zauberkern aller Zauber innerhalb von (Komplexe Fertigkeit) Faden Reichweite kann um 2 gesenkt werden.");
	}
		
	public Advantage Med(){
		return new Advantage("Med", 1.0, 1,  "Med", "Mit einem Medium erhöht sich die Zauberfertigkeit um 1.");
	}
	
	public Advantage Men(){
		return new Advantage("Men", 1.0, 1,  "Mentor", "Der Held kann Spruchrollen und -tafeln erstellen.");
	}
	
	public Advantage MK(){
		return new Advantage("MK", 1.0, 3,  "Manakontrolle", "Jedes mal, wenn man Manakontrolle bekommt, sinkt der Nachhall um 2 bis zu einem Minimum von 1/5 des ursprünglichen Nachhalls.", true);
	}	// (wenn du mehr als 3 mal MK nimmst, kostet jeder zusätzliche 0,5 mehr
	
	public Advantage MM(){
		return new Advantage("MM", 3.0, 1,  "Meistermagier", "In einem bestimmten Gebiet haben alle Zauber um 1 erhöhte Macht.");
	}
	
	public Advantage MWM(){
		return new Advantage("MWM", 0.0, 1,  "Meisterwettermagier", "Luftmagier mit Gedankenfreiheit können die Nachteile nicht völlig ignorieren, sondern nur halbieren (zu ihren Gunsten gerundet)");
	}
	
	public Advantage Pan(){
		return new Advantage("Pan", 1.0, 1,  "Panzer", "Verschleierung kann auch dazu verwendet werden, Entzauberungen zu erschweren.");
	}
	
	public Advantage Par(){
		return new Advantage("Par", 1.0, 1,  "Paraphernalien", "Kerzen, Totenschädel und andere romantische Details senken Komplexität oder Magiezehrung um 1.");
	}
	
	public Advantage Reg(){
		return new Advantage("Reg", 2.0, 1,  "Regeneration", "Regenerationswürfe werden alle 30 Minuten geworfen.");
	}
	
	public Advantage Rit(){
		return new Advantage("Rit", 2.0, 1,  "Ritualmagie", "Zauber ab 2-mal Zeit lassen werden um 1 leichter (K oder MZ), bei 4-mal sogar beides.");
	}
	
	public Advantage SchZa(){
		return new Advantage("SchZa", 1.5, 1,  "Schnellzaubern", "Für Entwurfszeit zählt der FW als um 5 höher.");
	}
	
	public Advantage Sich(){
		return new Advantage("Sich", 1.0, 1,  "Sichere Magie", "Um 2 verhaute Zauber würfeln auf der Kleinen Fehlertabelle, bei um 1 verhauten darf man sich das Ergebnis aussuchen.");
	}
	
	public Advantage Stab(){
		return new Advantage("Stab", 1.0, 1,  "Stabmagie", "erlaubt Zugriff zu Stabmagie");
	}
	
	public Advantage Scho(){
		return new Advantage("Scho", 2.0, 1,  "Scholar", "erlaubt das Lesen von Spruchrollen und Zaubertafeln");
	}
	
	public Advantage SV(){
		return new Advantage("SV", 1.5, 1,  "Selbstverstümmeung", "Der Zauberer kann seine eigene Lebensenergie zum Zaubern verwenden.");
	}
	
	public Advantage SWmag(){
		return new Advantage("SW", 2.5, 1,  "Schadenswiderstand", "Neue Schadensbasis ist GE oder Z+3");
	}
	
	public Advantage Tasch(){
		return new Advantage("Tasch", 1.0, 1,  "Taschenspielertricks", "erlaubt kleinere Zaubereien ohne Fetisch");
	}
	
	public Advantage TB(){
		return new Advantage("TB", 1.0, 1,  "Tierbindung", "erlaubt das Tierbindungsritual der Dunkelelfen");
	}
	
	public Advantage TE(){
		return new Advantage("TE", 1.5, 1,  "Tierempathie", "erlaubt Einblick in die Gefühlswelt von Nacktschnecken");
	}
	
	public Advantage TM(){
		return new Advantage("TM", 2.5, 1,  "Tiermeister", "verdoppelt effektiv die Qualität von Tierbeherrschungen und gewährt eine Intensive Befassung gegen Tiere");
	}
	
	public Advantage UZ(){
		return new Advantage("UZ", 1.0, 1,  "Unsichtbare Zeichen", "Zauberzeichen werden unsichtbar");
	}
	
	public Advantage Wa(){
		return new Advantage("Wa", 2.5, 1,  "Wandler", "Der Zauberer wird zum Formwandler ehrenhalber.");
	}
	
	public Advantage Wet(){
		return new Advantage("Wet", 2.5, 1,  "Wettermagie", "Der Zauberer kann die ZF Elementarmagie zum Verändern des Wetters verwenden.");
	}
	
	public Advantage WMmag(){
		return new Advantage("WM", 2.0, 1,  "Wilde Magie", "Du darfst W6 Punkte zur Macht dazuzählen ... bei einer 1 fliegt dir aber alles um die Ohren!");
	}
	
	public Advantage Zei(){
		return new Advantage("Zei", 1.0, 1,  "Zauberzeichen", "senken die Komplexität oder Magiezehrung eines Zaubers um 1, und sehen hübsch aus!");
	}
		
	public Advantage ZimK(){
		return new Advantage("ZimK", 3.0, 1,  "Zaubern im Kampf", "erlaubt 1 Zauber zwischen 2 Angriffen");
	}
	
	public Advantage ZS(){
		return new Advantage("ZS", 1.5, 1,  "Zauberspeicher", "erhöht Zauberspeicher um die GE", true);
	}
	
	public Advantage ZT(){
		return new Advantage("ZT", 1.0, 1,  "Zaubertränke", "Wasser als Träger besonders persistenter temporärer Artefakte");
	}
	
	public Advantage ZZ(){
		return new Advantage("ZZ", 2.0, 1,  "Zauberzieher", "entzieht einem Opfer bis zu 2 Punkte Mana");
	}
	
	public Advantage SichMK(){
		return new Advantage("Sich, MK", 2.0, -1, "Sichere Magie, Manakontrolle", "verhaute Zauber sind weniger schlimm, Nachhall klingt schneller ab", true);
	}
	
	public Advantage getMundaneAdvantageByShortcut (String s) {
		for (Advantage a : llmu) 
			if (a.getShortcut().equals(s))
				return a;
		return null;
	}
	
	public Advantage getMagicalAdvantageByShortcut (String s) {
		for (Advantage a : llma) 
			if (a.getShortcut().equals(s))
				return a;
		return null;
	}
}
