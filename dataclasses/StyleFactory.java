package dataclasses;

import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import com.sun.org.apache.xerces.internal.impl.dv.DVFactoryException;
import com.sun.org.apache.xpath.internal.operations.Plus;

import character.Advantage;
import character.AdvantageFamily;
import character.AdvantageGroup;
import character.AdvantageInList;
import character.Style;

public class StyleFactory {

	public static LinkedList<Style> getArmedMeleeStyles()
	{
		LinkedList <Style> ret = new LinkedList<Style>();
		AdvantageFactory af = new AdvantageFactory();
		AdvantageGroup ag, ag2, ag3, ag4, ag5;
		LinkedList<String> fittingStyles, unfittingStyles;

		ag = new AdvantageGroup();
		ag2 = new AdvantageGroup();
		ag3 = new AdvantageGroup();
		ag4 = new AdvantageGroup();
		ag5 = new AdvantageGroup();
		
		String specialRules;
				
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Klauen");
		fittingStyles.add("Leiser Tod");
		fittingStyles.add("Schattentanz");
		fittingStyles.add("Schaukampf");
		fittingStyles.add("Sediko/Emerino");
		
		ag.add(0, af.Aerl());
		ag.add(0, af.reit());
		
		ag2.add(0, af.Lh());
		ag2.add(0, af.KA());
		ag2.add(0, af.EvW());

		ag3.add(0, af.Agil());
		ag3.add(0, af.eMP());
		
		specialRules = "Sun’tanis haben Übung darin, auf Sand zu kämpfen.";
		ret.add(new Style("Alim", Style.ARMED_MELEE_STYLE, advantages(null, null, null, af.Anab(), null, af.Z2(), ag3, null, af.Init(), ag, af.SK(), af.SW(), null, af.Sch(), af.Anab(), null, af.WaS(), af.FT(), null, ag2), Style.OFFENSIVE, fittingStyles, specialRules));
		
		ag = new AdvantageGroup(); 
		ag.add(0, af.reit());
		ag.add(0, af.Lh());
		ag.add(0, af.KA());
		ag.add(0, af.eMP());
		ag.add(0, af.STs());
		
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Agil());
		ag2.add(0, af.EvW());
		
		ag3 = new AdvantageGroup();
		ag3.add(0, af.eMP());
		ag3.add(0, af.reit());
		ag3.add(0, af.STs());
		ag3.add(0, af.Lh());
		ag3.add(0, af.KA());
		
		ag4.add(0, af.WaS());
		ag4.add(0, af.Z2());
		ag4.add(0, af.BR());
		ag4.setNote("Nur wenn vorher reit gewählt wurde");
		
		ag5.add(0, af.KT());
		ag5.add(0, af._1ZpI());
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Holzfällerstil");
		fittingStyles.add("Klauen");
		fittingStyles.add("Miliz");
		fittingStyles.add("Ringkampf");
		fittingStyles.add("Sediko/Emerino");
		fittingStyles.add("Schattentanz");
		fittingStyles.add("Schaukampf");
		
		specialRules = "Ein Gladiator kann kämpfen, bis seine Wundensumme die Z um 2 übersteigt (normalerweise bis sie\ndie Z erreicht). Er ist aber trotzdem tot, wenn sie die anderthalbfache Zähigkeit erreicht oder\nein Schlag Z/2+3 Schaden anrichtet.\nGladiatoren können ihre Initiativphasen nicht verkürzen. Wenn Gladiatoren kämpfen, sieht es außerdem besser aus!";
		ret.add(new Style("Arena", Style.ARMED_MELEE_STYLE, advantages(null, null, af.SK(), ag2, null, af.Anab(), af.zWb(), ag, null, af.ÜA(), null, af.Init(), null, ag3, af.SW(), af.WM(), null, ag4, null, ag5), Style.DEFENSIVE, fittingStyles, specialRules));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Bragas Faust");
		fittingStyles.add("Goblinisches Gezanke");
		fittingStyles.add("Ringkampf");
		ag = new AdvantageGroup();
		ag.add(0, af.STs());
		ag.add(0, af.BR());
		
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Aerl());
		ag2.add(0, af.Lh());
		ag2.add(0, af.KA());
		
		ag3 = new AdvantageGroup();
		ag3.add(0, af.Aerl());
		ag3.add(0, af.reit());
		
		ret.add(new Style("Bragas Speer", Style.ARMED_MELEE_STYLE, advantages(null, null, null, ag3, null, af.Anab(), ag, null, af.Sch(), af.zWb(), af.WSch(), null, af.Init(), af.EvW(), null, af.MP(), ag2, null, af.SW(), af.Init()), Style.OFFENSIVE, fittingStyles));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Arm der Natur");
		fittingStyles.add("Harpyien");
		fittingStyles.add("Holzfällerstil");
		fittingStyles.add("Hufe");
		fittingStyles.add("Klauen");
		fittingStyles.add("Ringkampf");
		
		ag = new AdvantageGroup();
		ag.add(0, af.reit());
		ag.add(0, af.eMP());
		
		ag2 = new AdvantageGroup();
		ag2.add(0, af.SW());
		ag2.add(0, af.WSch());
		
		
		ag3 = new AdvantageGroup();
		ag3.add(0, af.MP());
		ag3.add(0, af.FT());
		ret.add(new Style("Barbarenstil", Style.ARMED_MELEE_STYLE, advantages(null, null, null, af.SW(), ag, af.EvW(), null, af.Anab(), af.Aerl(), null, af.Sch(), null, af.WM(), af.Z1(), null, ag3, af.STs(), null, ag2, af.Init()), Style.OFFENSIVE, fittingStyles));
		
		ag = new AdvantageGroup();
		ag.add(0, af.WM());
		ag.add(0, af.reitZ1());
		
		ag2 = new AdvantageGroup();
		ag2.add(0, af.EvW());
		ag2.add(0, af.eMP());
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Goblinisches Gezanke");
		fittingStyles.add("Holzfällerstil");
		fittingStyles.add("Hufe");
		fittingStyles.add("Klauen");
		fittingStyles.add("Miliz");
		specialRules = "Bauern erhalten deutlich geringere bis gar keine Nachteile, wenn sie improvisierte Waffen verwenden.";
		ret.add(new Style("Bauernstil", Style.ARMED_MELEE_STYLE, advantages(null, null, af.SW(), af.Fl(), null, af.Sch(), af.zWb(), af.STs(), null, ag, ag2, af.WSch(), null, af.Init(), null, af.Aerl(), null, af.Sch(), null, af.MP()), Style.OFFENSIVE, fittingStyles, specialRules));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Arm der Natur");
		fittingStyles.add("Bragas Faust");
		fittingStyles.add("Elfenbein");
		fittingStyles.add("Holzfällerstil");
		fittingStyles.add("Hufe");
		fittingStyles.add("Ki-mana");
		fittingStyles.add("Klauen");
		fittingStyles.add("Miliz");
		fittingStyles.add("Orkisch");
		fittingStyles.add("Ringkampf");
		fittingStyles.add("Rittersfaust");
		fittingStyles.add("Ritterstanz");
		fittingStyles.add("Schattentanz");
		fittingStyles.add("Sediko/Emerino");
		fittingStyles.add("Tanz der Wildkatze");
		fittingStyles.add("Utho");
		fittingStyles.add("Zwergenboxen");
		fittingStyles.add("Zwergenringen");
		
		ag = new AdvantageGroup();
		ag.add(0, af.KA());
		ag.add(0, af.reit());
		ag.add(0, af.WM());
		
		specialRules = "Drachentöter wissen genau, wo es bei großen Viechern weh tut.\nJeder Einzeltreffer eines Drachentöters auf ein großes Ziel zählt automatisch als Kritischer Treffer.\nOhnehin Kritische Treffer zählen als Fatale Treffer.\nWenn jemand von selbst einen Fatalen Treffer landet, darf er beide Würfel neu werfen, wenn er will.\nDann muss er aber einen der neuen Würfel nehmen.\nGegen alle Wesen, die nicht mindestens ogergroß sind, erleiden Drachentöter An -1.";
		ret.add(new Style("Drachentöter", Style.ARMED_MELEE_STYLE, advantages(null, null, null, af.WSch(), af.Aerl(), af.Fl(), null, af.Sch(), null, af.RG(), af.SW(), null, af.Anab(), af.Init(), null, af.SW(), null, af.FT(), af.Anab(), ag), Style.OFFENSIVE, fittingStyles, specialRules));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Elfenbein");
		fittingStyles.add("Leiser Tod");
		fittingStyles.add("Marine");
		fittingStyles.add("Schattentanz");
		ag = new AdvantageGroup();
		ag.add(0, af.Aerl());
		ag.add(0, af.Agil());
		ag.add(0, af.BR());
		
		ret.add(new Style("Dunkelelfenstil", Style.ARMED_MELEE_STYLE, advantages(null, null, af.reit(), null, af.SW(), null, af.Anab(), null, af.Init(), null, ag, null, af.Z2(), af.zWb(), null, af.FT(), af.Sch(), null, af.Init(), af.SZ()), Style.OFFENSIVE, fittingStyles));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Cher'aton");
		fittingStyles.add("Elfenbein");
		fittingStyles.add("Elfentanz");
		fittingStyles.add("Goblinisches Gezanke");
		fittingStyles.add("Hufe");
		fittingStyles.add("Klauen");
		fittingStyles.add("Miliz");
		fittingStyles.add("Minoro");
		fittingStyles.add("Sediko/Emerino");
		fittingStyles.add("Tanz der Wildkatze");

		ag = new AdvantageGroup();
		ag.add(0, af.eMP());
		ag.add(0, af.EvW());
		
		ag2 = new AdvantageGroup();
		ag2.add(1, af.nix());
		ag2.add(2, af.zWb());
		
		ag3 = new AdvantageGroup();
		ag3.add(0, af.BR());
		ag3.add(0, af.SK());
		
		ag4 = new AdvantageGroup();
		ag4.add(0, af.FT());
		ag4.add(0, af.KT());
		
		ag5 = new AdvantageGroup();
		ag5.add(1, af.WaS());
		ag5.add(2, af.WM());
		
		ret.add(new Style("Eiliger Reiter", Style.ARMED_MELEE_STYLE, advantages(null, null, af.reit(), af.Fl(), null, af.Init(), ag, null, af.Anab(), null, af.Sch(), ag2, af.Z2(), ag3, af.Anab(), null, af.SW(), null, ag4, ag5), Style.DEFENSIVE, fittingStyles));

		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Leiser Tod");
		fittingStyles.add("Rattentechnik");
		fittingStyles.add("Schattentanz");
		fittingStyles.add("Tanz der Schattenkatze");
		
		ag = new AdvantageGroup();
		ag.add(0, af.KT());
		ag.add(0, af.SZ());
		
		ag2 = new AdvantageGroup();
		ag2.add(0, af._1ZpI());
		ag2.add(0, af.FT());
		
		ag3 = new AdvantageGroup();
		ag3.add(0, af.Aerl());
		ag3.add(0, af.Agil());
		
		specialRules = "In vollem Licht und bzw. in sehr natürlicher Umgebung verlieren Tiefelfen sämtliche Boni, die sie\ndurch die Tabelle am Ende dieses Abschnitts erhalten, wenn sie allerdings in trübem Licht in\ngewohnter Umgebung kämpfen, erhalten sie +1/+1 auf Angriff und Abwehr. In engen Räumlichkeiten\n(Tunneln, engen Höhlen, kleinen Räumen etc) erhalten sie auch +1/+1 (der Bonus ist kumulativ).";
		ret.add(new Style("Ekitur", Style.ARMED_MELEE_STYLE, advantages(null, null, af.zWb(), af.Fl(), null, af.Init(), af.STs(), af.BK(), null, af.Anab(), af.eMP(), null, af.Sch(), null, ag, ag3, null, af.Z2(),null, ag2), Style.DEFENSIVE, fittingStyles, specialRules));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Elfentanz");
		fittingStyles.add("Ritterstanz");
		fittingStyles.add("Sediko/Emerino");
		
		ag = new AdvantageGroup();
		ag.add(0, af.WM());
		ag.add(0, af.BR());
		
		ag2 = new AdvantageGroup();
		ag2.add(0, af.E());
		ag2.add(0, af.WSch());
		specialRules = "Elfenritter bekommen ein Zehntel ihrer Komplexen Fertigkeit (kaufmännisch gerundet). Auch über 15\nhinaus gekaufte Fertigkeiten zählen hier ausnahmsweise dazu. Näheres im Kapitel V) als Bonus auf\nihren Angriff, solange sie im Reiterkampf kämpfen. Dieselbe Zahl bekommen sie aber als Malus auf\nAngriff und Abwehr, wenn sie zu Fuß kämpfen.\nCuellin hat seinen Stil bereits auf 15 und sich dann noch den Vorteil Aerl dazugekauft. Damit hat\ner einen Fertigkeitswert von 16, was kaufmännisch gerundet /10 zwei ergibt. Im Reiterkampf erhält\ner +2/±0, im Kampf zu Fuß hingegen -2/-2.";
		ret.add(new Style("Elfenritter", Style.ARMED_MELEE_STYLE, advantages(null, null, af.reit(), null, af.RG(), null, af.Sch(), af.Z1(), null, af.SW(), af.MP(), null, af.RG(), af.Init(), ag, null, af.FT(), null, ag2, af.Anab()), Style.OFFENSIVE, fittingStyles, specialRules));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Zombiestil");
		unfittingStyles = new LinkedList<String>();
		
		ret.add(new Style("Kampf der Untoten", Style.ARMED_MELEE_STYLE, advantages(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), Style.OFFENSIVE, fittingStyles));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Elfenbein");
		fittingStyles.add("Goblinisches Gezanke");
		fittingStyles.add("Holzfällerstil");
		fittingStyles.add("Hufe");
		fittingStyles.add("Klauen");
		fittingStyles.add("Leiser Tod");
		fittingStyles.add("Miliz");
		fittingStyles.add("Rattentechnik");
		fittingStyles.add("Ringkampf");
		
		ag = new AdvantageGroup();
		ag.add(0, af.Aerl());
		ag.add(0, af.reit());
		
		ret.add(new Style("Kampfregeln der Reisenden", Style.ARMED_MELEE_STYLE, advantages(null, null, af.Fl(), af.Anab(), null, af.SW(), null, af.eMP(), af.EvW(), af.STs(), ag, null, af.Init(), null, af.Sch(), af.zWb(), null, af.E(), af.Z1(), af.WaS()), Style.OFFENSIVE, fittingStyles));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Chi-nissou");
		fittingStyles.add("Ki-mana");
		fittingStyles.add("Minoro");
		fittingStyles.add("Sediko/Emerino");
		unfittingStyles = new LinkedList<String>();
		
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Lh());
		ag2.add(0, af.KA());
		ag2.add(0, af.reit());
		
		ag = new AdvantageGroup();
		ag.add(0, af.eMP());
		ag.add(0, af.WM());
		
		ag3 = new AdvantageGroup();
		ag3.add(0, af.Agil());
		ag3.add(0, af.EvW());
		
		
		ret.add(new Style("Kiso-nawa", Style.ARMED_MELEE_STYLE, advantages(null, null, af.Anab(), null, af.Init(), null, ag2, af.SZ(), ag3, null, af.Aerl(), null, af.Sch(), af.Z2(), null, af.FT(), null, ag, null, af.KT()), Style.DEFENSIVE, fittingStyles));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Elfentanz");
		fittingStyles.add("Hufe");
		fittingStyles.add("Klauen");
		fittingStyles.add("Miliz");
		fittingStyles.add("Rittersfaust");
		fittingStyles.add("Sediko/Emerino");
		
		ag = new AdvantageGroup();
		ag.add(0, af.Fl());
		ag.add(0, af.reit());
		ag.add(0, af.EvW());
		
		specialRules = "Wenn sie die Fähigkeit eMP anwenden, dürfen sie Zauber wirken, die (Komplexe Fertigkeit)/2 Phasen\ndauern, anstatt von (Komplexe Fertigkeit)/3.";
		ret.add(new Style("Magierstil", Style.ARMED_MELEE_STYLE, advantages(null, null, null, af.eMP(), null, ag, null, af.Anab(), null, af.Init(), af.WSch(), null, af.Sch(), af.SW(), null, af.Z2(), null, af.KT(), null, af._1ZpI()), Style.DEFENSIVE, fittingStyles, specialRules));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Klauen");
		fittingStyles.add("Leiser Tod");
		fittingStyles.add("Rattentechnik");
		fittingStyles.add("Schattentanz");
		
		specialRules = "In vollem Licht und/oder in der Natur verlieren miese Asseln sämtliche Boni, die sie durch die\nTabelle am Ende dieses Abschnitts erhalten, wenn sie allerdings in trübem Licht in gewohnter\nUmgebung kämpfen, erhalten sie +1/+1 auf Angriff und Abwehr. In engen urbanen Räumlichkeiten\n(Tunneln, kleinen Räumen etc) erhalten sie ebenfalls +1/+1 (die Boni sind kumulativ).";
		ret.add(new Style("Miese-Assel-Technik", Style.ARMED_MELEE_STYLE, advantages(null, null, af.STs(), af.Fl(), null, af.EvW(), null, af.Init(), af.Anab(), null, af.SW(), af.eMP(), af.zWb(), null, af.Z2(), af.SZ(), null, af.MP(), null, af.E()), Style.OFFENSIVE, fittingStyles, specialRules));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Elfentanz");
		fittingStyles.add("Ritterstanz");
		fittingStyles.add("Schattentanz");
		fittingStyles.add("Sediko/Emerino");
		
		ag = new AdvantageGroup();
		ag.add(0, af.eMP());
		ag.add(0, af.RG());
		ag.add(0, af.SK());
		
		ag2 = new AdvantageGroup();
		ag2.add(0, af.BK());
		ag2.add(0, af.FT());
		ag2.add(0, af.SZ());
		
		ag3 = new AdvantageGroup();
		ag3.add(0, af.KT());
		ag3.add(0, af.EEE());
		ag3.add(0, af.SZ());
		
		ret.add(new Style("Ni'tarkhir gán", Style.ARMED_MELEE_STYLE, advantages(null, null, af.Aerl(), af.Agil(), null, af.Init(), null, af.WaS(), null, af.Sch(), ag, null, af.WM(), null, af.Anab(), null, ag2, null, af.SW(), ag3), Style.DEFENSIVE, fittingStyles));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Holzfällerstil");
		fittingStyles.add("Klauen");
		fittingStyles.add("Leiser Tod");
		fittingStyles.add("Marine");
		fittingStyles.add("Rattentechnik");
		
		specialRules = "Auf schwankendem Boden und im Wasser bekommen sie Boni im Kampf (zB +2/+2). Auf festem Boden\nerleiden sie aber -1/-0.";
		ret.add(new Style("Piratenstil", Style.ARMED_MELEE_STYLE, advantages(null, null, af.SW(), null, af.Fl(), null, af.Sch(), null, af.EvW(), af.STs(), af.Z2(), null, af.Init(), af.Aerl(), null, af.eMP(), af.MP(), null, af.WSch(), af.Anab()), Style.OFFENSIVE, fittingStyles, specialRules));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Cher'aton");
		fittingStyles.add("Leiser Tod");
		unfittingStyles = new LinkedList<String>();
		
		specialRules = "Amon-a’Seth haben mit Klingenwaffen Reichweite -0,7, richten dafür 4 Schaden mehr an. Sie können\nhervorragend in der Wüste kämpfen.";
		ret.add(new Style("Rakhil", Style.ARMED_MELEE_STYLE, advantages(null, null, null, af.Anab(), af.Init(), null, af.Sch(), af.reit(), null, af.Aerl(), null, af.SZ(), null, af.Init(), af.WaS(), null, af.Z2(), af.SW(), null, af.KT()), Style.DEFENSIVE, fittingStyles, specialRules));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Hufe");
		fittingStyles.add("Miliz");
		fittingStyles.add("Rittersfaust");

		ag = new AdvantageGroup();
		ag.add(0, af.WM());
		ag.add(0, af.BR());
		
		ag2 = new AdvantageGroup();
		ag2.add(0, af.reit());
		ag2.add(0, af.RG());
		
		ag3 = new AdvantageGroup();
		ag3.add(0, af.RG());
		ag3.add(0, af.SK());
		
		ret.add(new Style("Ritterstil (O)", Style.ARMED_MELEE_STYLE, advantages(null, null, null, ag2, ag3, null, af.eMP(), af.Anab(), null, af.Z2(), af.WSch(), null, af.Sch(), af.RG(), ag, null, af.MP(), null, af.SW(), af.Init()), Style.OFFENSIVE, fittingStyles));
		ret.add(new Style("Ritterstil (D)", Style.ARMED_MELEE_STYLE, advantages(null, null, null, ag2, ag3, null, af.eMP(), af.Anab(), null, af.Z2(), af.WSch(), null, af.Sch(), af.RG(), ag, null, af.MP(), null, af.SW(), af.Init()), Style.DEFENSIVE, fittingStyles));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Leiser Tod");
		fittingStyles.add("Miliz");
		fittingStyles.add("Ringkampf");
		fittingStyles.add("Rittersfaust");
		fittingStyles.add("Schattentanz");
		fittingStyles.add("Sediko/Emerino");
		
		ret.add(new Style("Schattenklinge", Style.ARMED_MELEE_STYLE, advantages(null, null, af.Anab(), af.Aerl(), null, af.SW(), null, af.Z1(), af.Init(), af.Agil(), null, af.SZ(), null, af.Sch(), null, af.KT(), null, af.WaS(), null, af.FT()), Style.DEFENSIVE, fittingStyles));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Elfenbein");
		fittingStyles.add("Ki-mana");
		fittingStyles.add("Leiser Tod");
		fittingStyles.add("Minoro");
		fittingStyles.add("Rattentechnik");
		fittingStyles.add("Sediko/Emerino");
		ag = new AdvantageGroup();
		ag.add(0, af.Aerl());
		ag.add(0, af.Fl());
		ag.add(0, af.reit());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Anab());
		ag2.add(0, af.WM());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.FT());
		ag3.add(0, af.SZ());
		ag4 = new AdvantageGroup();
		ag4.add(0, af._1ZpI());
		ag4.add(0, af.BK());
		ag4.setNote("1ZpI nur nach eMP");
		
		ag5 = new AdvantageGroup();
		ag5.add(0, af.Lh());
		ag5.add(0, af.KA());

		AdvantageGroup ag6 = new AdvantageGroup();
		ag6.add(0, af.Agil());
		ag6.add(0, af.EvW());
		
		
		specialRules = "Schattenläufer bekommen +1/+1 auf ihre Anab für jeden der folgenden Umstände:\n- Kampf im Schatten/in der Nacht\n- Kampf an einem Ort, an dem sich die Umgebung gut gegen den Feind nutzen lässt (tückische Moore,\nsteile Klippen, Schankräume, …)\nSchattenläufer erleiden -1/-1 auf ihre Anab für jeden der folgenden Umstände:\n- Kampf in sehr gutem Licht\n- Kampf in völlig offener, ebener Fläche\nWenn sich der Gegner vor dem Kampf bereits auf den Kampfstil des Gegners einstellen konnte, bekommt\nder Schattenläufer einen Bonus weniger.";
		ret.add(new Style("Schattenlauf", Style.ARMED_MELEE_STYLE, advantages(null, null, null, af.STs(), ag, null, af.Z1(), ag6, null, af.Sch(), af.MP(), ag2, null, af.E(), af.MP(), null, ag3, null, ag5, ag4), Style.OFFENSIVE, fittingStyles, specialRules));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Elfenbein");
		fittingStyles.add("Holzfällerstil");
		fittingStyles.add("Klauen");
		fittingStyles.add("Hufe");
		fittingStyles.add("Marine");
		fittingStyles.add("Miliz");
		fittingStyles.add("Rittersfaust");
		fittingStyles.add("Ritterstanz");
		fittingStyles.add("Sediko/Emerino");
		ag = new AdvantageGroup();
		ag.add(0, af.Aerl());
		ag.add(0, af.Fl());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.WaS());
		ag2.add(0, af.SW());
		
		specialRules = "Auf schwankendem Boden und im Wasser bekommen sie Boni im Kampf (zB +2/+2). Auf festem Boden\nerleiden sie aber -1/-0.";
		ret.add(new Style("Seefahrer", Style.ARMED_MELEE_STYLE, advantages(null, null, null, af.Anab(), af.zWb(), ag, null, af.eMP(), af.zWb(), null, af.Init(), null, af.Z2(), af.E(), af.Anab(), null, af.MP(), null, af.Sch(), ag2), Style.OFFENSIVE, fittingStyles, specialRules));
		
		ag = new AdvantageGroup();
		ag.add(0, af.reit());
		ag.add(0, af.eMP());
		ag.add(0, af.Lh());
		ag.add(0, af.KA());
		ag.add(0, af.zWb());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.WM());
		ag2.add(0, af.BR());
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Elfentanz");
		fittingStyles.add("Holzfällerstil");
		fittingStyles.add("Klauen");
		fittingStyles.add("Hufe");
		fittingStyles.add("Marine");
		fittingStyles.add("Miliz");
		fittingStyles.add("Ringkampf");
		fittingStyles.add("Rittersfaust");
		fittingStyles.add("Ritterstanz");
		
		specialRules = "Soldaten sind es gewöhnt, in Formationen zu kämpfen. Wer diesen Stil beherrscht und gemeinsam mit\nanderen desselben Stils kämpft, erhält einen Bonus von +1/+1 auf die Anab. Wer Soldatenstil\nmindestens auf 10 gebracht hat, beherrscht außerdem das rollende R.";
		ret.add(new Style("Soldatenstil", Style.ARMED_MELEE_STYLE, advantages(null, null, null, af.SW(), ag, af.Fl(), null, af.RG(), af.zWb(), af.EvW(), af.WSch(), af.SW(), null, af.Sch(), null, af.MP(), null, af.Z2(), ag2, af.Init()), Style.OFFENSIVE, fittingStyles, specialRules));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Arm der Natur");
		fittingStyles.add("Elfentanz");
		fittingStyles.add("Goblinisches Gezanke");
		fittingStyles.add("Harpyien");
		fittingStyles.add("Holzfällerstil");
		fittingStyles.add("Hufe");
		fittingStyles.add("Leiser Tod");
		fittingStyles.add("Orkisch");
		fittingStyles.add("Tanz der Wildkatze");
		fittingStyles.add("Utho");
		ag = new AdvantageGroup();
		ag.add(0, af.eMP());
		ag.add(0, af.STs());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Aerl());
		ag2.add(0, af.BR());
		
		ret.add(new Style("Späher", Style.ARMED_MELEE_STYLE, advantages(null, null, af.Fl(), null, af.EvW(), null, af.Sch(), af.reit(), null, af.WSch(), null, af.Init(), ag, null, af.Anab(), ag2, null, af.FT(), null, af.MP()), Style.OFFENSIVE, fittingStyles));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Arm der Natur");
		fittingStyles.add("Goblinisches Gezanke");
		fittingStyles.add("Harpyien");
		fittingStyles.add("Holzfällerstil");
		fittingStyles.add("Hufe");
		fittingStyles.add("Klauen");
		fittingStyles.add("Orkisch");
		fittingStyles.add("Utho");
		ag = new AdvantageGroup();
		ag.add(0, af.Aerl());
		ag.add(0, af.reit());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.BR());
		ag2.add(0, af.WSch());
		ag3 = new AdvantageGroup();
		ag3.add(1, af.EvW());
		ag3.add(2, af.STs());
		ag3.setNote("Waldtrolle EvW, sonst STs");
		ag4 = new AdvantageGroup();
		ag4.add(1, af.STs());
		ag4.add(2, af.EvW());
		ag4.setNote("Waldtrolle STs, sonst EvW");
		
		specialRules = "In ihrem angestammten Habitat erhalten diese Kämpfer einen Bonus von +1/+2 auf Anab, in völlig\ngegensätzlichem aber -1/-1.";
		ret.add(new Style("Stammeskrieger", Style.ARMED_MELEE_STYLE, advantages(null, null, af.zWb(), ag3, af.Fl(), null, ag, ag4, af.Init(), null, af.Sch(), null, af.ÜA(), ag2, af.Anab(), af.WM(), null, af.KT(), null, af.SW()), Style.DEFENSIVE, fittingStyles, specialRules));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Holzfällerstil");
		fittingStyles.add("Hufe");
		fittingStyles.add("Orkisch");

		ag = new AdvantageGroup();
		ag.add(0, af.Aerl());
		ag.add(0, af.Fl());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Aerl());
		ag2.add(0, af.RG());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.zWb());
		ag3.add(0, af.eMP());
		
		ret.add(new Style("Trollstil", Style.ARMED_MELEE_STYLE, advantages(null, null, af.Init(), ag, af.EvW(), null, af.Anab(), af.zWb(), null, af.SW(), ag2, af.STs(), null, ag3, af.WSch(), af.E(), null, af.Sch(), null, af.Z2()), Style.OFFENSIVE, fittingStyles));
	
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Leiser Tod");
		fittingStyles.add("Tanz der Wildkatze");
		unfittingStyles = new LinkedList<String>();
		
		specialRules = "Im Kampf im Wald erhalten Waldelfen +1/+2 auf Anab, im thil ’l Laren sogar +2/+3, in städtischer\nUmgebung allerdings -1/-1.";
		ret.add(new Style("Waldelfenstil", Style.ARMED_MELEE_STYLE, advantages(null, null, null, af.SW(), af.SK(), null, af.reit(), af.Fl(), af.Aerl(), null, af.Z2(), af.eMP(), af.Anab(), null, af.FT(), null, af.Init(), af.MP(), null, af.SW()), Style.OFFENSIVE, fittingStyles, specialRules));

		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Arm der Natur");
		fittingStyles.add("Bragas Faust");
		fittingStyles.add("Elfenbein");
		fittingStyles.add("Elfentanz");
		fittingStyles.add("Goblinisches Gezanke");
		fittingStyles.add("Harpyien");
		fittingStyles.add("Holzfällerstil");
		fittingStyles.add("Hufe");
		fittingStyles.add("Klauen");
		fittingStyles.add("Orkisch");
		fittingStyles.add("Sediko/Emerino");
		fittingStyles.add("Tanz der Wildkatze");
		fittingStyles.add("Utho");
		
		ag = new AdvantageGroup();
		ag.add(0, af.STs());
		ag.add(0, af.reit());
		
		specialRules = "In ihrem angestammten Habitat erhalten diese Kämpfer einen Bonus von +1/+1 auf Anab, in völlig\ngegensätzlichem aber -1/-1. Im Kampf gegen Gegner mit Iz: 7 oder weniger erhalten sie noch einmal \n+1/+1.";
		ret.add(new Style("Wilde Jagd", Style.ARMED_MELEE_STYLE, advantages(null, null, af.Aerl(), af.Fl(), null, af.SW(), af.Anab(), af.EvW(), null, af.Sch(), null, ag, null, af.FT(), af.Z1(), null, af.ÜA(), af.WSch(), null, af.KT()), Style.DEFENSIVE, fittingStyles, specialRules));
		
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Zwergenboxen");
		fittingStyles.add("Zwergenringen");
		unfittingStyles = new LinkedList<String>();
		unfittingStyles.add("Genormter Ringkampf");
		unfittingStyles.add("Orkisch");
		
		specialRules = "Zwerge können im Kampf quasi nicht zurückgedrängt werden. Sie erleiden kaum Einbußen aufgrund\nmangelnden Platzes.";
		ret.add(new Style("Zwergenstil", Style.ARMED_MELEE_STYLE, advantages(null, null, null, af.RG(), null, af.EvW(), af.WSch(), null, af.Anab(), af.SW(), null, af.Z2(), af.RG(), null, af.Sch(), null, af.Init(), af.Anab(), null, af.MP()), Style.OFFENSIVE, fittingStyles));
		
		return ret;
	}
		
	public static LinkedList<Style> getOffDefK()
	{
		LinkedList <Style> ret = new LinkedList<Style>();
		AdvantageFactory af = new AdvantageFactory();

		AdvantageGroup ag = new AdvantageGroup();
		ag.add(0, af._1ZpI());
		ag.add(0, af.Aerl());
		ag.add(0, af.Anab());
		ag.add(0, af.BK());
		ag.add(0, af.BR());
		ag.add(0, af.E());
		ag.add(0, af.EEE());
		ag.add(0, af.EvB());
		ag.add(0, af.EvD());
		ag.add(0, af.EvW());
		ag.add(0, af.eMP());
		ag.add(0, af.Fl());
		ag.add(0, af.FT());
		ag.add(0, af.Init());
		ag.add(0, af.iNkw());
		ag.add(0, af.KA());
		ag.add(0, af.Kb());
		ag.add(0, af.KgB());
		ag.add(0, af.Ko());
		ag.add(0, af.KT());
		ag.add(0, af.Lh());
		ag.add(0, af.MP());
		ag.add(0, af.MwrS());
		ag.add(0, af.reit());
		ag.add(0, af.RG());
		ag.add(0, af.Sch());
		ag.add(0, af.SW());
		ag.add(0, af.SK());
		ag.add(0, af.STs());
		ag.add(0, af.SZ());
		ag.add(0, af.ÜA());
		ag.add(0, af.WaS());
		ag.add(0, af.WM());
		ag.add(0, af.WSch());
		ag.add(0, af.Z2());
		ag.add(0, af.zAbw());
		ag.add(0, af.zWb());	
		ret.add(new Style("OffensivK", Style.META_STYLE, advantages(null, null, null, af.Sch(), null, null, null, af.ZFW(), null, null, null, af.ÜA(), null, null, null, af.KT(), null, null, null, ag)));
		ret.add(new Style("DefensivK", Style.META_STYLE, advantages(null, null, null, af.SW(),  null, null, null, af.ZFW(), null, null, null, af.E(),  null, null, null, af.MP(), null, null, null, ag)));

		return ret;
	}
	
	public static LinkedList<Style> getUnarmedMeleeStyles()
	{
		LinkedList <Style> ret = new LinkedList<Style>();
		AdvantageFactory af = new AdvantageFactory();
		AdvantageGroup ag;
		AdvantageGroup ag2;
		AdvantageGroup ag3;
		AdvantageGroup ag4;
		AdvantageGroup ag5;
		
		String specialRules;
		LinkedList<String> fittingStyles;
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Barbarenstil (O)");
		fittingStyles.add("Drachentöter (O)");
		fittingStyles.add("Späher (O)");
		fittingStyles.add("Stammeskrieger (D)");
		fittingStyles.add("Wilde Jagd (D)");
		
		ag = new AdvantageGroup();
		ag.add(0, af.EvB());
		ag.add(0, af.EvW());

		specialRules = "Solange sich der Krieger in seiner angestammten Umgebung befindet (und der Gegner nicht ebenfalls\nmit Arm der Natur kämpft), kann er seinen Feind immer, wenn er wegen eMP, ÜA, STs oder einem Ergebnis\nvon über 20 auf dem schwarz-weißen Ritter auf Totalen Verhau würfeln muss, dazu zwingen, noch einmal\nzu würfeln. Das zweite Ergebnis ist dann bindend.";
		ret.add(new Style("Arm der Natur", Style.UNARMED_MELEE_STYLE, advantages(null, null, af.Aerl(), af.Fl(), af.EvD(), null, af.STs(), ag, null, af.SW(), af.eMP(), af.Init(), null, af.Sch(), af.MrS(), null, af.Anab(), null, af.Init(), af.SchSW()), Style.NODAGGERS, fittingStyles, specialRules));

		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Bragas Speer (O)");
		fittingStyles.add("Drachentöter (O)");
		fittingStyles.add("Wilde Jagd (D)");
		
		ag = new AdvantageGroup();
		ag.add(0, af.EvD());
		ag.add(0, af.STs());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.EvD());
		ag2.add(0, af.STs());
		ret.add(new Style("Bragas Faust", Style.UNARMED_MELEE_STYLE, advantages(null, null, null, af.SchSW(), null, ag, af.Z1(), null, af.WSch(), ag2, null, af.Anab(), af.EvB(), null, af.Init(), af.KTMP(), null, af.KgB(), null, af.Ko()), Style.NODAGGERS, fittingStyles));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Eiliger Reiter (D)");
		fittingStyles.add("Rakhil (D)");

		specialRules = "Mit dem Dolch haben Cher’aton-Kämpfer An-1, aber Schaden+3.";
		ret.add(new Style("Cher'aton", Style.UNARMED_MELEE_STYLE, advantages(af.EvD(), null, af.Anab(), null, af.Init(), null, af.Sch(), null, af.Aerl(), null, af.SW(), null, af.KgB(), af.Init(), null, af.Z2(), null, af.KTMP(), null, af.KT()), Style.DAGGERS, fittingStyles, specialRules));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Kiso-nawa (D)");
		
		specialRules = "Für gewöhnlich kann ein Chi-nissou-Kämpfer nicht angreifen. Tut er es doch, verliert er sämtliche\nVorteile aus der Tabelle am Ende des Abschnitts. Dann wehrt er auch ganz gewöhnlich ab.\nAnsonsten gelten bei der Abwehr Folgendes:\nAbwehr mit 3+: normale Abwehr\nAbwehr mit 4+: entweder Wurf (siehe „Schlag auf Schlag“) oder normaler Kampfschaden wie nach\ngelungenem Angriff, nach Wahl (Trefferzonen lt. Great Goblin)\nAbwehr mit 5+: anstatt einer Meisterparade wahlweise:\n• Meisterwurf (beliebige Zone außer Hals), der Schaden des folgenden Angriffs ist nochmals um 2\n\terhöht, wenn waffenlos (Meisterwurf siehe „Schlag auf Schlag“)\n• Fixierung: Nach einem „Zu Boden werfen“ (siehe „Schlag auf Schlag“) wird der Gegner am Boden\n\tfixiert. Solange der Chi-nissou-Kämpfer nicht loslässt, ist es unmöglich, sich zu \n\tbefreien (ohne sich Gelenke auszukegeln oder Knochen zu brechen)\n• Gelenk auskegeln (siehe „Schlag auf Schlag“)\n• Kritischer Treffer, Trefferzonen lt. Great Goblin \n\nChi-nissou kann auch als Meisterparade verwendet werden. In diesem Fall würfelst du einfach noch \neine Abwehr gegen den bereits vorhandenen Angriff. Misslingt diese, ist der Chi-nissou – Kämpfer \ndennoch natürlich nicht getroffen worden.";
		ret.add(new Style("Chi-nissou", Style.UNARMED_MELEE_STYLE, advantages(null, null, null, af.Agil(), af.Fl(), null, af.Anab(), af.EvD(), null, null, af.KgB(), af.eMP(), null, af.Sch(), af.MwrS(), null, af.SW(), af.WaS(), null, af.zAbw()), Style.NODAGGERS, fittingStyles, specialRules));
		
		ag = new AdvantageGroup();
		ag2 = new AdvantageGroup();
		ag3 = new AdvantageGroup();
		ag.add(0, af.STs());
		ag.add(0, af.eMP());

		ag2.add(0, af.SW());
		ag2.add(0, af.FT());
		
		ag3.add(0, af.Agil());
		ag3.add(0, af.EvD());
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Drachentöter (O)");
		fittingStyles.add("Dunkelelfenstil (O)");
		fittingStyles.add("Eiliger Reiter");
		fittingStyles.add("Kampfregeln der Reisenden (O)");
		fittingStyles.add("Schattenlauf (O)");
		fittingStyles.add("Seefahrer (O)");
		fittingStyles.add("Wilde Jagd (D)");
		
		specialRules = "Initiative +1, Schaden +3";
		
		ret.add(new Style("Elfenbein", Style.UNARMED_MELEE_STYLE, advantages(null, null, null, af.Aerl(), null, ag3, af.MrS(), null, af.Sch(), af.WSch(), af.Anab(), null, ag, null, ag2, af.Init(), null, af.KTMP(), af.Z2(), af.MrS()), Style.NODAGGERS, fittingStyles, specialRules, 0, -1, 3));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Elfenritter (O)");
		fittingStyles.add("Eiliger Reiter (D)");
		fittingStyles.add("Magierstil (D)");
		fittingStyles.add("Ni'tarkhir gán (D)");
		fittingStyles.add("Späher (O)");
		fittingStyles.add("Wilde Jagd (D)");
		
		
		ag = new AdvantageGroup();
		ag.add(0, af.Anab());
		ag.add(0, af.EEE());
		
		ag3 = new AdvantageGroup();
		ag3.add(0, af.Agil());
		ag3.add(0, af.EvD());
		
		
		ret.add(new Style("Elfentanz", Style.UNARMED_MELEE_STYLE, advantages(null, null, null, af.Aerl(), null, af.Anab(), null, ag3, null, af.SW(), null, af.KgB(), null, af.Sch(), null, af.KTMP(), null, ag, null, af.zAbw()), Style.NODAGGERS, fittingStyles, "Ein Elfenkrieger dieses Stils sollte zumindest halb so viele Fertigkeitspunkt auf die Fertigkeit Tanz verwenden wie auf seinen Kampfstil."));
	
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Bragas Faust (O)");
		fittingStyles.add("Bauernstil (O)");
		fittingStyles.add("Eiliger Reiter (D)");
		fittingStyles.add("Kampfregeln der Reisenden (O)");
		fittingStyles.add("Späher (O)");
		fittingStyles.add("Stammeskrieger (D)");
		fittingStyles.add("Wilde Jagd (O)");

		specialRules = "Dieser Stil ist Voraussetzung für die Spezialattacken, die im Ein Held wird geboren beschrieben sind.\nWer normal kämpfen will (in der Regel Anführer und sehr gute Krieger) kann aber ruhig einen anderen\nStil wählen.";
		ret.add(new Style("Goblinisches Gezanke", Style.UNARMED_MELEE_STYLE, advantages(null, null, af.Fl(), af.iNkw(), af.STs(), null, af.SW(), af.Aerl(), null, af.Anab(), af.EvD(), af.eMP(), af.EvW(), null, af.Sch(), af.Z1(), null, af.KgB(), null, af.FT()), Style.NODAGGERS, fittingStyles, specialRules));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Barbarenstil (O)");
		fittingStyles.add("Späher (O)");
		fittingStyles.add("Stammeskrieger (D)");
		fittingStyles.add("Wilde Jagd (O)");
		
		specialRules = "Harpyien richten mit dem Stil, wenn sie die Krallen einsetzen, einen Punkt zusätzlichen Schaden an.\nAußerdem haben sie ein besonderes waffenloses Manöver, wenn sie auf der Treffertabelle 1 würfeln:\nGegner verschleppen\nDie Harpyie packt den Gegner an den Oberarmen und erhebt sich in die Lüfte. Für jeden Meter Höhe,\nden die Harpyie gewinnt, können die beiden eine vergleichende K(10)-Probe würfeln, wobei die Harpyie\nab der zweiten Probe +2 auf ihren Wurf bekommt, da sie den Griff gefestigt habe (dieser Bonus ist\nnicht kumulativ, eine vierte Probe bekäme noch immer nur +2). Wenn die Harpyie den Gegner fallen\nlässt, entsteht Sturzschaden (siehe „Mit Stahl und Verstand“).";
		ret.add(new Style("Harpyien", Style.UNARMED_MELEE_STYLE, advantages(null, null, null, af.Sch(), null, af.STs(), null, af.Init(), af.Anab(), null, af.KgB(), af.eMP(), null, af.SW(), null, af.KTMP(), af.Z1(), null, af.SchSW(), af.FT()), Style.NODAGGERS, fittingStyles, specialRules));
		
		ag = new AdvantageGroup();
		ag.add(0, af.EvB());
		ag.add(0, af.STs());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Kb());
		ag2.add(0, af.Ko());
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Arena (D)");
		fittingStyles.add("Bauernstil (O)");
		fittingStyles.add("Drachentöter");
		fittingStyles.add("Kampfregeln der Reisenden (O)");
		fittingStyles.add("Piratenstil (O)");
		fittingStyles.add("Seefahrer (O)");
		fittingStyles.add("Soldatenstil (O)");
		fittingStyles.add("Späher (O)");
		fittingStyles.add("Stammeskrieger (D)");
		fittingStyles.add("Trollstil (O)");
		fittingStyles.add("Wilde Jagd (O)");

		specialRules = "Initiative +1, Schaden +2";
		ret.add(new Style("Holzfällerstil", Style.UNARMED_MELEE_STYLE, advantages(null, null, af.iNkw(), null, af.Sch(), af.Fl(), null, af.EvD(), ag, null, af.SW(), af.EvW(), null, af.WSch(), null, af.Init(), af.Aerl(), af.SW(), null, ag2), Style.NODAGGERS, fittingStyles, specialRules, 0, -1, 2));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Barbarenstil (O)");
		fittingStyles.add("Bauernstil (O)");
		fittingStyles.add("Drachentöter (O)");
		fittingStyles.add("Eiliger Reiter (D)");
		fittingStyles.add("Kampfregeln der REisenden (O)");
		fittingStyles.add("Magierstil (D)");
		fittingStyles.add("Rittersstil (O)");
		fittingStyles.add("Seefahrer (O)");
		fittingStyles.add("Soldatenstil (O)");
		fittingStyles.add("Späher (O)");
		fittingStyles.add("Stammeskrieger (D)");
		fittingStyles.add("Trollstil (O)");
		fittingStyles.add("Wilde Jagd (O)");
		ag = new AdvantageGroup();
		ag.add(0,af.EvB());
		ag.add(0,af.iNkw());
		ag.add(0,af.STs());
		ag2 = new AdvantageGroup();
		ag2.add(0,af.eMP());
		ag2.add(0,af.RG());
		ag2.add(0, af.STs());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.EvD());
		ag3.add(0, af.eMP());
		ag3.add(0, af.RG());
		ag3.add(0, af.STs());
		
		specialRules = "Meisterparaden und Kritische Treffer werden mit den Hufen durchgeführt (+2 Schaden, nur 1 irreell).\nWenn ein Gegner am Boden liegt und eine Form des Wurfes erwürfelt wird, darf der Kämpfer statt dem\nnormalen Angriff (siehe etwas später) mit dem Huf nach dem Gegner treten. Dann verursacht er, da er\nsein ganzes Körpergewicht in den Tritt setzen kann, nicht nur +2, sondern +4 Schaden (ebenfalls nur\n1 irreell). Wenn der Treffer \nein Kritischer Treffer war (und der Gegner danach nicht kampfunfähig ist), erleidet der am Boden\nLiegende außerdem eine Kampffolgeunterbrechung.\nWas die Abwehr angeht, zählt ein Huftritt wie ein Dolchstoß (man braucht also KgB, um ihn waffenlos\nohne Einschränkungen abwehren zu können).";
		ret.add(new Style("Hufe", Style.UNARMED_MELEE_STYLE, advantages(null, null, null, null, af.ÜAHufe(), af.Fl(), ag, null, af.Sch(), ag2, af.WSch(), null, af.SchSW(), af.Z1(), null, ag3, null, af.KTMP(), null, af.Ko()), Style.NODAGGERS, fittingStyles, specialRules));
		
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Drachentöter (O)");
		fittingStyles.add("Kiso-nawa (D)");
		fittingStyles.add("Schattenlauf (O)");

		specialRules = "Anab +1/±0, Initiative +1 (wird also schlechter), Schaden +2";
		ret.add(new Style("Ki-mana", Style.UNARMED_MELEE_STYLE, advantages(null, null, null, af.Sch(), af.Init(), null, af.MrS(), af.eMP(), null, af.Anab(), af.Agil(), null, af.Init(), af.Aerl(), af.Sch(), null, af.FT(), af.MrS(), null, af.KTMP()), Style.NODAGGERS, fittingStyles, specialRules, 1, -1, 2));
		
		ag = new AdvantageGroup();
		ag2 = new AdvantageGroup();
		ag3 = new AdvantageGroup();
		ag4 = new AdvantageGroup();
		ag5 = new AdvantageGroup();
		
		ag.add(1, af.EvD());
		ag.add(2, af.EvB());
		ag.setNote("empfohlen für Kreaturen, die Waffen verwenden (Gnolle, Thorg, …) | andere");
		ag2.add(1, af.EvB());
		ag2.add(2, af.EvD());
		ag2.setNote("empfohlen für Kreaturen, die Waffen verwenden (Gnolle, Thorg, …) | andere");
		ag3.add(0, af.eMP());
		ag3.add(0, af.STs());
		ag3.setNote("gewöhnliche Auswahloption, unabhängig davon, ob man Waffen verwendet");
		ag4.add(1, af.KgB());
		ag4.add(2, af.Sch());
		ag4.setNote("empfohlen für Kreaturen, die Waffen verwenden (Gnolle, Thorg, …) | andere");
		ag5.add(1, af.SchSW());
		ag5.add(2, af.Kb());
		ag5.setNote("empfohlen für Kreaturen, die Waffen verwenden (Gnolle, Thorg, …) | andere");

		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Alim (O)");
		fittingStyles.add("Arena (D)");
		fittingStyles.add("Barbarenstil (O)");
		fittingStyles.add("Bauernstil (O)");
		fittingStyles.add("Drachentöter (O)");
		fittingStyles.add("Eiliger Reiter (D)");
		fittingStyles.add("Kampfregeln der Reisenden (O)");
		fittingStyles.add("Magierstil (D)");
		fittingStyles.add("Miese-Assel-Technik (O)");
		fittingStyles.add("Piratenstil (O)");
		fittingStyles.add("Seefahrer (O)");
		fittingStyles.add("Soldatenstil (O)");
		fittingStyles.add("Wilde Jagd (O)");
		
		specialRules = "Man kann gezielt die Klauen nicht (oder weniger) einsetzen, darf also bestimmen, ob man 0, 1 oder 2\nPunkte irreellen Schaden machen will. Verwendet man die Klauen voll (also kein irreeller Schaden),\nrichtet man 1 Schadenspunkt mehr an.";
		ret.add(new Style("Klauen", Style.UNARMED_MELEE_STYLE, advantages(null, null, null, ag, af.SK(), af.Aerl(), ag2, null, af.Init(), af.Fl(), ag3, af.Anab(), null, af.SW(), null, ag4, null, af.KTMP(), null, ag5), Style.NODAGGERS, fittingStyles));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Alim (O)");
		fittingStyles.add("Dunkelelfenstil (O)");
		fittingStyles.add("Ekitur (D)");
		fittingStyles.add("Kampfregeln der Reisenden (O)");
		fittingStyles.add("Miese-Assel-Technik (O)");
		fittingStyles.add("Piratenstil (O)");
		fittingStyles.add("Rakhil (D)");
		fittingStyles.add("Schattenklinge (D)");
		fittingStyles.add("Schattenlauf (O)");
		fittingStyles.add("Späher (O)");
		fittingStyles.add("Stammeskrieger (D)");
		fittingStyles.add("Waldelfenstil (O)");
		ag = new AdvantageGroup();
		ag.add(0, af.Aerl());
		ag.add(0, af.STs());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.BK());
		ag2.add(0, af.FT_eMP());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.KgB());
		ag3.add(0, af.SZ());
		
		specialRules = "Wenn das Opfer noch nicht auf den Mörder aufmerksam ist, erhält der Meuchelmörder (zusätzlich zu dem\nVorteil, dass der Gegner keine oder nur die halbe Abwehr hat) An +3, Sch+4 und den Vorteil FT. Gegen\neine Überzahl von Gegnern erleidet der Kämpfer allerdings M-1 und Anab -1/-1.";
		ret.add(new Style("Leiser Tod", Style.UNARMED_MELEE_STYLE, advantages(af.EvD(), null, af.WSch(), af.Fl(), null, af.Sch(), null, af.Z1(), null, ag, null, af.EvWLeiserTod(), af.Z1(), null, af.KTMP(), null, ag2, null, null, ag3), Style.DAGGERS, fittingStyles));

		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Dunkelelfenstil (O)");
		fittingStyles.add("Piratenstil (O)");
		fittingStyles.add("Seefahrer (O)");
		fittingStyles.add("Soldatenstil (O)");
		
		ag = new AdvantageGroup();
		ag.add(0, af.Aerl());
		ag.add(0, af.RG());
		ag.add(0, af.STs());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Ko());
		ag2.add(0, af.FT());
		
		specialRules = "Auf schwankendem Boden und im Wasser bekommen sie Boni im Kampf (zB +2/+2). Auf festem Boden\nerleiden sie aber -1/-0.";
		ret.add(new Style("Marine", Style.UNARMED_MELEE_STYLE, advantages(null, null, null, af.SW(), af.EvD(), null, af.Anab(), null, af.Z2(), af.eMP(), null, af.SchSW(), null, af.Init(), ag, af.ÜAE(), null, af.KTMP(), null, ag2), Style.NODAGGERS, fittingStyles));
		
		ag = new AdvantageGroup();
		ag.add(0, af.MwrS());
		ag.add(0, af.EvB());
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Arena (D)");
		fittingStyles.add("Bauernstil (O)");
		fittingStyles.add("Drachentöter (O)");
		fittingStyles.add("Eiliger Reiter (D)");
		fittingStyles.add("Kampfregeln der Reisenden (O)");
		fittingStyles.add("Magierstil (D)");
		fittingStyles.add("Rittersstil (O)");
		fittingStyles.add("Seefahrer (O)");
		fittingStyles.add("Soldatenstil (O)");

		specialRules = "Beim Trefferzonenwurf, der bestimmt ob ein Schlag oder Wurf etc. gemacht wird, erhält der\nMilizkämpfer 1 Punkt Zielen dazu, wenn der Gegner eine Rüstung trägt und der Zielenpunkt dazu führen\nwürde, einen Wurf/Griff etc. auszuführen. \nBei einem Trefferwurf auf Great Goblin erhält der Milizkämpfer einen Punkt Zielen dazu, wenn es dazu\nführen würde, eine ungerüstete(re) Stelle zu treffen.";
		ret.add(new Style("Miliz", Style.UNARMED_MELEE_STYLE, advantages(af.EvD(), null, null, af.Z1(), ag, null, af.RG(), af.eMP(), null, af.iNkw(), null,  af.ÜAE(), af.Anab(), af.Z1(), null, af.KgB(), null, af.SchSW(), null, af.KTMP()), Style.DAGGERS, fittingStyles, specialRules));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Eiliger Reiter (D)");
		fittingStyles.add("Kiso-nawa (D)");
		fittingStyles.add("Schattenlauf (O)");
		
		specialRules = "während einer Meisterparade angewandt Schaden +4, sonst Schaden -2";
		ret.add(new Style("Minoro", Style.UNARMED_MELEE_STYLE, advantages(af.EvD(), null, af.iNkw(), null, af.KgB(), null, null, af.Anab(), af.MwrS(), null, af.SW(), af.EvW(), null, af.Sch(), null, af.Init(), null, af.Z2(), null, af.KTMP()), Style.DAGGERS, fittingStyles, specialRules)); 
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Drachentöter (O)");
		fittingStyles.add("Späher (O)");
		fittingStyles.add("Stammeskrieger (D)");
		fittingStyles.add("Trollstil (O)");
		fittingStyles.add("Wilde Jagd (O)");
		ag = new AdvantageGroup();
		ag.add(0, af.EvB());
		ag.add(0, af.EvD());

		specialRules = "Initiative +1, Schaden +2";
		ret.add(new Style("Orkisch", Style.UNARMED_MELEE_STYLE, advantages(null, null, null, af.SW(), af.STs(), af.iNkw(), null, ag, null, af.Sch(), af.WSch(), af.Fl(), null, af.WaS(), null, af.Z2(), af.Init(), null, af.eMP(), af.Ko()), Style.NODAGGERS, fittingStyles, specialRules, 0, -1, 2));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Kampfregeln der Reisenden (O)");
		fittingStyles.add("Ekitur (D)");
		fittingStyles.add("Miese-Assel-Technik (O)");
		fittingStyles.add("Piratenstil (O)");
		fittingStyles.add("Schattenlauf (O)");
		
		specialRules = "In vollem Licht und/oder in der Natur verlieren die Kämpfer sämtliche Boni, die sie durch die\nTabelle am Ende dieses Abschnitts erhalten, wenn sie allerdings in trübem Licht in gewohnter\nUmgebung kämpfen, erhalten sie +1/+1 auf Angriff und Abwehr. In engen urbanen Räumlichkeiten\n(Tunneln, kleinen Räumen etc) erhalten sie ebenfalls +1/+1 (die Boni sind kumulativ).";
		ret.add(new Style("Rattentechnik", Style.UNARMED_MELEE_STYLE, advantages(af.EvD(), null, af.iNkw(), af.Fl(), af.STs(), null, af.Init(), af.EvW(), null, af.SW(), null, af.MwrS(), af.Aerl(), null, af.KgB(), af.EvB(), null, af.Anab(), null, af.Sch()), Style.DAGGERS, fittingStyles, specialRules));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Arena (D)");
		fittingStyles.add("Bragas Speer (O)");
		fittingStyles.add("Barbarenstil (O)");
		fittingStyles.add("Drachentöter (O)");
		fittingStyles.add("Kampfregeln der Reisenden (O)");
		fittingStyles.add("Schattenklinge (D)");
		ag = new AdvantageGroup();
		ag.add(0, af.EvB());
		ag.add(0, af.EvD());
		
		specialRules = "Wenn du mit den optionalen Regeln für Reichweite im Nahkampf spielst, müssen Ringkämpfer auch\nriesige Kämpfer nicht unterlaufen.";
		ret.add(new Style("Ringkampf", Style.UNARMED_MELEE_STYLE, advantages(null, null, null, af.Init(), null, af.SW(), null, af.Sch(), af.SK(), ag, null, af.Anab(), null, af.Z2(), null, af.KTMP(), af.WaS(), null, af.STs(), af.KgB()), Style.NODAGGERS, fittingStyles, specialRules));

		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Drachentöter (O)");
		fittingStyles.add("Magierstil (D)");
		fittingStyles.add("Rittersstil (O)");
		fittingStyles.add("Schattenklinge (D)");
		fittingStyles.add("Seefahrer (O)");
		fittingStyles.add("Soldatenstil (O)");
		
		ret.add(new Style("Rittersfaust", Style.UNARMED_MELEE_STYLE, advantages(null, null, af.SW(), af.EvD(), null, af.Z1(), null, af.Anab(), null, af.ÜAE(), null, af.Sch(), null, af.WSch(), null, af.KTMP(), null, af.KgB(), null, af.Ko()), Style.NODAGGERS, fittingStyles));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Drachentöter (O)");
		fittingStyles.add("Elfenritter (O)");
		fittingStyles.add("Ni'tarkhir gán (D)");
		fittingStyles.add("Seefahrer (O)");
		fittingStyles.add("Soldatenstil (O)");
		ag = new AdvantageGroup();
		ag.add(0, af.WaS());
		ag.add(0, af.WSch());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Aerl());
		ag3.add(0, af.Agil());
		ag2.add(0, af.Lh());
		ag2.add(0, af.KA());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.RG());
		ag3.add(0, af.SK());
		ag4 = new AdvantageGroup();
		ag4.add(0, af.FT());
		ag4.add(0, af.Ko());

		ret.add(new Style("Ritterstanz", Style.UNARMED_MELEE_STYLE, advantages(null, null, af.EvD(), null, af.MwrS(), null, af.Z1(), null, af.KgB(), af.SchSW(), null, af.ÜAE(), ag, ag2, ag3, null, af.Init(), af.KTMP(), null, ag4), Style.NODAGGERS, fittingStyles, "Ein Kämpfer dieses Stils sollte die Fertigkeit Tanz mindestens ein Drittel so hoch haben wie seinen Kampfstil."));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Alim (O)");
		fittingStyles.add("Arena (D)");
		fittingStyles.add("Drachentöter (O)");
		fittingStyles.add("Dunkelelfenstil (O)");
		fittingStyles.add("Ekitur (D)");
		fittingStyles.add("Miese-Assel-Technik (O)");
		fittingStyles.add("Ni'tarkhir gán (D)");
		fittingStyles.add("Schattenklinge (O)");

		specialRules = "Ein Kämpfer dieses Stils sollte die Fertigkeit Tanz mindestens ein Drittel so hoch haben wie seinen\nKampfstil.\nEin Schattentänzer kann seinen Feind immer, wenn dieser wegen eMP, ÜA, STs oder einem Ergebnis von\nüber 20 auf dem schwarz-weißen Ritter auf Totalen Verhau würfeln muss, dazu zwingen, noch einmal zu\nwürfeln. Das zweite Ergebnis ist dann bindend.\nEin Schattentänzer kann, wenn er auf der Treffertabelle „Great Goblin“ würfelt, entscheiden, diesen\nWurf mit 2 W20 zu würfeln. Das muss er schon vor dem Wurf ansagen. Wenn er schon deswegen mit 2 W20\nwürfeln würde, weil der Verteidiger einen Nettoerfolg hatte, nützt das natürlich nichts.\nDer Kampfstil der Schattentänzer ist darauf ausgelegt, seinen Feind zu erschöpfen. Für „Ausdauer im\nKampf“ zählt die Ausdauer als um 3 höher.\nWenn ein auf der Treffertabelle „Great Goblin“ würfelt und normalen Schaden anrichtet, darf der\nGegner ihn immer dazu zwingen, den Schadenswurf einmal zu wiederholen. Das zweite Ergebnis ist\nbindend (also wie bei einem Kritischen Treffer, nur umgekehrt). Falls dem Schattentänzer ein\nKritischer Treffer gelungen ist, gilt diese Regel nicht, der Schattentänzer kann aber auch den\nVorteil aus Kritischer Treffer nicht nutzen. \nEin Schattentänzer darf niemals FT lernen (Das gilt natürlich nicht für den entsprechenden\nbewaffneten Stil).";
		ret.add(new Style("Schattentanz", Style.UNARMED_MELEE_STYLE, advantages(null, null, af.eMP(), af.Aerl(), af.STs(), af.Agil(), null, af.Anab(), null, af.ÜAE(), null, af.KgB(), null, af.KTMP(), af.WaS(), null, af.Z1(), af.SW(), null, af.zAbw()), Style.NODAGGERS, fittingStyles, specialRules));

		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Alim (O)");
		fittingStyles.add("Arena (D)");
		ag = new AdvantageGroup();
		ag.add(0, af.EvB());
		ag.add(0, af.EvW());
		ag.add(0, af.iNkw());
		ag.add(0, af.STs());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.MwrS());
		ag2.add(0, af.WaS());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.SZ());
		ag3.add(0, af.ÜAE());
		ag4 = new AdvantageGroup();
		ag4.add(0, af.EvW());
		ag4.add(0, af.iNkw());
		ag4.add(0, af.STs());
		ag4.add(0, af.Lh());
		ag4.add(0, af.KA());
		
		ag5 = new AdvantageGroup();
		ag5.add(0, af._1ZpI());
		ag5.add(0, af.Anab());
		ag5.add(0, af.BK());
		ag5.add(0, af.BR());
		ag5.add(0, af.E());
		ag5.add(0, af.EEE());
		ag5.add(0, af.EvB());
		ag5.add(0, af.EvD());
		ag5.add(0, af.EvW());
		ag5.add(0, af.Fl());
		ag5.add(0, af.FT());
		ag5.add(0, af.Init());
		ag5.add(0, af.iNkw());
		ag5.add(0, af.KA());
		ag5.add(0, af.Kb());
		ag5.add(0, af.KgB());
		ag5.add(0, af.Ko());
		ag5.add(0, af.KT());
		ag5.add(0, af.Lh());
		ag5.add(0, af.MP());
		ag5.add(0, af.MwrS());
		ag5.add(0, af.reit());
		ag5.add(0, af.RG());
		ag5.add(0, af.Sch());
		ag5.add(0, af.STs());
		ag5.add(0, af.SZ());
		ag5.add(0, af.ÜA());
		ag5.add(0, af.WaS());
		ag5.add(0, af.WM());
		ag5.add(0, af.WSch());
		ag5.add(0, af.zAbw());
		ag5.add(0, af.zWb());
		
		AdvantageGroup ag6 = new AdvantageGroup();
		ag.add(0, af.Agil());
		ag.add(0, af.eMP());
		
		specialRules = "Schaukämpfer dürfen den Wurf auf die Treffertabelle einmal wiederholen. Spielst du mit der\nSonderregel „Hab isch Kontrolle!“ (siehe „Mit Stahl und Verstand“), dürfen Schaukämpfer zweimal\nstatt nur einmal wiederholen.\nSchaukämpfer können ihre Initiativphasen nicht verkürzen. Wenn Schaukämpfer kämpfen, sieht es\naußerdem besser aus!";
		ret.add(new Style("Schaukampf", Style.UNARMED_MELEE_STYLE, advantages(null, null, af.SK(), af.Aerl(), af.Z1(), null, af.SW(), null, ag, ag6, af.Anab(), null, af.Z1(), ag2, af.SchSW(), null, ag3, ag4, null, ag5), Style.NODAGGERS, fittingStyles, specialRules));

		
		ag = new AdvantageGroup();
		ag2= new AdvantageGroup();
		
		ag.add(0, af.Init());
		ag.add(0, af.EvD());
		ag.setNote("Sediko: Init, Emerino: EvD");
		
		ag2.add(0, af.EvB());
		ag2.add(0, af.STs());
		ag2.setNote("EvB nur für Andalib/Breta, STs für andere (sie verwenden nicht wirklich gemeine Tricks, sondern nutzen geeignete Momente, um schnell zuzuschlagen)");
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Alim (D)");
		fittingStyles.add("Arena (D)");
		fittingStyles.add("Drachentöter (O)");
		fittingStyles.add("Elfenritter (O)");
		fittingStyles.add("Kiso-nawa (D)");
		fittingStyles.add("Magierstil (D)");
		fittingStyles.add("Ni'tarkhir gán (D)");
		fittingStyles.add("Schattenklinge (D)");
		fittingStyles.add("Schattenlauf (O)");
		fittingStyles.add("Seefahrer (O)");
		fittingStyles.add("Wilde Jagd (O)");
		
		ret.add(new Style("Sediko/Emerino", Style.UNARMED_MELEE_STYLE, advantages(null, null, ag, null, af.Anab(), ag2, null, af.Z2(), af.MrS(), null, af.Sch(), null, af.KgB(), null, af.KTMP(), af.Agil(), null, af.FT(), null, af.SW()), Style.NODAGGERS, fittingStyles));
		
		fittingStyles = new LinkedList<String>();
		
		fittingStyles.add("Ekitur (D)");
		
		ag = new AdvantageGroup();
		ag.add(0, af.Agil());
		ag.add(0, af.EvD());
		specialRules = "In vollem Licht und bzw. in sehr natürlicher Umgebung verlieren Tiefelfen sämtliche Boni, die sie\ndurch die Tabelle am Ende dieses Abschnitts erhalten, wenn sie allerdings in trübem Licht in\ngewohnter Umgebung kämpfen, erhalten sie +1/+1 auf Angriff und Abwehr. In engen Räumlichkeiten\n(Tunneln, engen Höhlen, kleinen Räumen etc) erhalten sie auch +1/+1 (der Bonus ist kumulativ).";
		ret.add(new Style("Tanz der Wildkatze", Style.UNARMED_MELEE_STYLE, advantages(null, null, af.Aerl(), null, ag, af.Sch(), null, af.eMP(), af.Anab(), null, af.Init(), af.MrS(), af.Fl(), null, af.SW(), null, af.FT(), af.SK(), null, af.KTMP()), Style.NODAGGERS, fittingStyles, specialRules));
		
		ag = new AdvantageGroup();
		ag.add(0, af.Agil());
		ag.add(0, af.EvD());
		
		fittingStyles = new LinkedList<String>();
		
		fittingStyles.add("Drachentöter (O)");
		fittingStyles.add("Eiliger Reiter (D)");
		fittingStyles.add("Späher (O)");
		fittingStyles.add("Waldelfenstil (O)");
		fittingStyles.add("Wilde Jagd (O)");

		specialRules = "Im Wald erhalten Waldelfen +1/+2 auf Anab (+2/+3 im thil ’l laren), in urbaner Umgebung erleiden sie aber -1/-1.";
		ret.add(new Style("Tanz der Wildkatze", Style.UNARMED_MELEE_STYLE, advantages(null, null, af.Aerl(), null, ag, af.Sch(), null, af.eMP(), af.Anab(), null, af.Init(), af.MrS(), af.Fl(), null, af.SW(), null, af.FT(), af.SK(), null, af.KTMP()), Style.NODAGGERS, fittingStyles, specialRules));
		
		fittingStyles = new LinkedList<String>();
		
		ret.add(new Style("Tierwehr", Style.UNARMED_MELEE_STYLE, advantages(null, null, af.TVT(), af.EvB(), null, af.KgB(), null, af.Sch(), null, af.TVT(), null, af.Aerl(), null, af.Anab(), af.TVT(), null, af.Init(), null, af.TVT(), af.SW()), Style.NODAGGERS, fittingStyles));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Drachentöter (O)");
		fittingStyles.add("Späher (O)");
		fittingStyles.add("Stammeskrieger (D)");
		fittingStyles.add("Wilde Jagd (O)");

		specialRules = "In natürlicher Umgebung (egal welches Terrain, solang es dem Kämpfer einigermaßen vertraut ist)\nerhält der Kämpfer +1/+1.";
		ret.add(new Style("Utho", Style.UNARMED_MELEE_STYLE, advantages(null, null, af.Sch(), null, af.Init(), af.EvD(), af.Aerl(), null, af.SW(), af.MrS(), null, af.Z2(), af.Fl(), af.SchSW(), af.WSch(), null, af.KTMP(), af.MrS(), null, af.FT()), Style.NODAGGERS, fittingStyles, specialRules));
		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Kampf der Untoten (O)");
		
		ret.add(new Style("Zombiestil", Style.UNARMED_MELEE_STYLE, advantages(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), Style.NODAGGERS, fittingStyles));

		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Drachentöter (O)");
		fittingStyles.add("Zwergenstil (O)");
		ag = new AdvantageGroup();
		ag.add(0, af.MrS());
		ag.add(0, af.RG());
		ag.add(0, af.SK());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.KTMP());
		ag2.add(0, af.EEE());
		
		specialRules = "Gegner, die mehr als einen Kopf größer sind, erleiden An -2 gegen Zwergenboxer. Sie wissen, dass sie nur den Kopf decken müssen. Du solltest diese Regel aber nur anwenden, wenn du mit den Optionalregeln für Reichweite im waffenlosen Kampf spielst. Es wäre ungerecht, die Vorteile fürs Kleinsein anzuwenden und die Nachteile nicht zu beachten.";
		ret.add(new Style("Zwergenboxen", Style.UNARMED_MELEE_STYLE, advantages(null, null, null, af.Z1(), null, ag, af.WSch(), null, af.SW(), af.Anab(), af.EvD(), null, af.Sch(), af.Z1(), ag2, null, af.SchSW(), null, af.KgB(), af.Ko()), Style.NODAGGERS, fittingStyles, specialRules));

		
		fittingStyles = new LinkedList<String>();
		fittingStyles.add("Drachentöter (O)");
		fittingStyles.add("Zwergenstil (O)");
		
		specialRules = "Spielst du mit den Optionalregeln für Reichweite im waffenlosen Nahkampf, müssen Zwerge größere Kämpfer (keine Seltenheit) nicht unterlaufen. Sie werfen sie lieber an den Armen.";
		ret.add(new Style("Zwergisches Ringen", Style.UNARMED_MELEE_STYLE, advantages(null, null, null, af.Sch(), null, af.Z2(), null, af.SW(), null, af.zwe1(), null, af.zwe2(), af.MwrS(), null, af.Anab(), null, af.KgB(), af.SW(), null, af.Sch()), Style.NODAGGERS, fittingStyles, specialRules));
		
		return ret;
	}

	public static LinkedList<Style> getRangeWeaponsK()

	{
		LinkedList <Style> ret = new LinkedList<Style>();
		AdvantageFactory af = new AdvantageFactory();

		AdvantageGroup ag = new AdvantageGroup();
		ag.add(0, af.BGA());
		ag.add(0, af.Gehen());
		ag.add(0, af.Laufen());
		ag.add(0, af.Reiten());
		ag.add(0, af.SchSch());
		ag.add(0, af.AA());
		ag.add(0, af.plusRW());
		ag.setNote("INKL(!) FK-Stile: BGa, Gehen, Laufen, Reiten: 0-1x, SchSch, AA, +RW: 0-2x");
		
		ret.add(new Style("FernwaffenK", Style.META_STYLE, advantages(null, null, null, af.SchSch(), null, null, af.zSch(), null, null, af.Fäsch(), null, null, ag, null, null, af.plusRW(), null, null, null, af.An())));

		return ret;
	}
	
	public static LinkedList<Style> getArtillaryStyles()
	{
		LinkedList <Style> ret = new LinkedList<Style>();
		AdvantageFactory af = new AdvantageFactory();
		AdvantageGroup ag = new AdvantageGroup();
		AdvantageGroup ag2= new AdvantageGroup();

		LinkedList<Integer> notAvailable = new LinkedList<Integer>();
		
		notAvailable.add(new Integer(0));
		notAvailable.add(new Integer(1));
		notAvailable.add(new Integer(2));
		notAvailable.add(new Integer(3));
		notAvailable.add(new Integer(4));
		notAvailable.add(new Integer(5));
		notAvailable.add(new Integer(6));
		notAvailable.add(new Integer(7));
		notAvailable.add(new Integer(9));
		
		ret.add(new Style("Geschütze", Style.ARTILLARY_STYLE, advantages(null, null, null, af.plusRW(), null, null, af.SchSch(), null, null, null, af.BGA(), null, af.SchSch(), null, null, af.AA(), null, af.SchSch(), null, af.plusRW()), notAvailable));
		
		notAvailable = new LinkedList<Integer>();
		notAvailable.add(new Integer(9));
				
		ret.add(new Style("Jäger", Style.ARTILLARY_STYLE, advantages(null, null, null, af.plusRW(), null, null, af.AA(), null, null, af.BGA(), null, null, af.AA(), null, null, af.SchSch(), null, af.Gehen(), null, af.zSch()), notAvailable));
		
		notAvailable = new LinkedList<Integer>();
		
		notAvailable.add(new Integer(0));
		notAvailable.add(new Integer(1));
		notAvailable.add(new Integer(2));
		notAvailable.add(new Integer(3));
		notAvailable.add(new Integer(4));
		notAvailable.add(new Integer(5));
		notAvailable.add(new Integer(6));
		notAvailable.add(new Integer(7));
		notAvailable.add(new Integer(8));
		
		ag = new AdvantageGroup();
		ag.add(1, af.Laufen());
		ag.add(2, af.Reiten());
		
		ag2 = new AdvantageGroup();
		ag2.add(1, af.Reiten());
		ag2.add(2, af.Laufen());
		
		ret.add(new Style("Kampfmagie", Style.ARTILLARY_STYLE, advantages(null, null, null, af.AA(), null, null, af.Gehen(), null, af.BGA(), null, null, ag, null, null, ag2, null, null, af.AA(), null, af.plusRW()), notAvailable));
		notAvailable = new LinkedList<Integer>();
		notAvailable.add(new Integer(9));
		
		ret.add(new Style("Räuber", Style.ARTILLARY_STYLE, advantages(null, null, null, af.AA(), null, null, af.Gehen(), null, af.BGA(), null, null, null, af.SchSch(), null, null, af.Laufen(), null, af.AA(), null, af.SchSch()), notAvailable));
		notAvailable = new LinkedList<Integer>();
		notAvailable.add(new Integer(9));
		ret.add(new Style("Reiter", Style.ARTILLARY_STYLE, advantages(null, null, af.Reiten(), null, af.BGA(), null, null, null, af.SchSch(), null, null, af.Laufen(), null, null, af.Gehen(), null, af.AA(), null, null, af.plusRW()), notAvailable));
		
		ag.add(0, af.Reiten());
		ag.add(0, af.Gehen());
		
		ag2.add(0, af.Reiten());
		ag2.add(0, af.Gehen());
		ag2.add(0, af.Laufen());
		notAvailable = new LinkedList<Integer>();
		notAvailable.add(new Integer(9));
		
		ret.add(new Style("Scharmützel", Style.ARTILLARY_STYLE, advantages(null, null, null, af.BGA(), null, null, ag, null, af.SchSch(), null, null, null, af.AA(), null, null, af.plusRW(), null, ag2, null, af.zSch()), notAvailable));
		notAvailable = new LinkedList<Integer>();
		notAvailable.add(new Integer(9));
		ret.add(new Style("Söldner", Style.ARTILLARY_STYLE, advantages(null, null, af.plusRW(), null, af.Gehen(), null, null, af.BGA(), null, null, null, af.AA(), null, null, null, af.SchSch(), null, af.Laufen(), null, af.zSch()), notAvailable));

		ret.add(new Style("Skelette", Style.ARTILLARY_STYLE, advantages(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), notAvailable));
		
		notAvailable = new LinkedList<Integer>();
		
		notAvailable.add(new Integer(0));
		notAvailable.add(new Integer(1));
		notAvailable.add(new Integer(2));
		notAvailable.add(new Integer(3));
		notAvailable.add(new Integer(8));
		notAvailable.add(new Integer(9));
		
		ret.add(new Style("Streuner", Style.ARTILLARY_STYLE, advantages(null, null, af.AA(), null, null, null, af.SchSch(), null, af.BGA(), null, null, af.Gehen(), null, null, af.AA(), null, af.Laufen(), null, null, af.SchSch()), notAvailable));
		
		ag = new AdvantageGroup();
		ag.add(0, af.AA());
		ag.add(0, af.SchSch());

		notAvailable = new LinkedList<Integer>();
		
		notAvailable.add(new Integer(1));
		notAvailable.add(new Integer(7));
		notAvailable.add(new Integer(8));
		
		ret.add(new Style("Waldelfen", Style.ARTILLARY_STYLE, advantages(null, null, null, af.Reiten(), null, null, af.AA(), null, af.BGA(), null, null, af.plusRW(), null, null, null, ag, null, af.Gehen(), null, af.plusRW()), notAvailable));
		
		ag = new AdvantageGroup();
		ag.add(0, af.SchSch());
		ag.add(0, af.Gehen());
		ag.setNote("Armbrüste: SchSch, alle anderen Gehen");
		
		notAvailable = new LinkedList<Integer>();
		
		notAvailable.add(new Integer(0));
		notAvailable.add(new Integer(2));
		notAvailable.add(new Integer(4));
		notAvailable.add(new Integer(5));
		notAvailable.add(new Integer(6));
		notAvailable.add(new Integer(7));
		notAvailable.add(new Integer(9));
				
		ret.add(new Style("Zwerge", Style.ARTILLARY_STYLE, advantages(null, null, null, af.plusRW(), null, null, af.AA(), null, af.BGA(), null, null, af.SchSch(), null, null, null, af.Gehen(), null, af.SchSch(), null, af.AA()), notAvailable));
		
		return ret;
	}
	
	public static LinkedList<Style> getWizardStyles() {
		LinkedList <Style> ret = new LinkedList<Style>();
		AdvantageFactory af = new AdvantageFactory();
		AdvantageGroup ag, ag2, ag3, ag4, ag5, ag6, ag7;
		String specialRules;
		
		ag = new AdvantageGroup();
		ag.add(0, af.Men());
		ag.add(0, af.MK());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Ast());
		ag2.add(0, af.Ex());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.MK());
		ag3.add(0, af.ZT());
		specialRules = "Spruchrollen und Zauberbücher, die von Akademiethaumaturgen, die Akademiethaumaturgie auf 15\nbesitzen, verfasst werden, büßen nur einen Punkt Macht weniger ein als gewöhnlich, also nur 1 Punkt\nbei Spruchrollen und nur 3 bei Zauberbüchern.";
		ret.add(new Style("Akadmiethaumaturgen", Style.WIZARD_STYLE, advantages(af.Tasch(), null, af.ZS(), af.Stab(), af.Scho(), af.Zei(), null, ag, ag2, null, af.SWmag(), ag3, af.UZ(), null, af.Erz(), null, null, null, null, null), specialRules));
		
		
		ag = new AdvantageGroup();
		ag.add(0, af.Pan());
		ag.add(0, af.Sich());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Ket());
		ag2.add(0, af.DB());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.Fet());
		ag3.add(0, af.SWmag());
		ag4 = new AdvantageGroup();
		ag4.add(0, af.Reg());
		ag4.add(0, af.DB());
		ag5 = new AdvantageGroup();
		ag5.add(0, af.Int());
		ag5.add(0, af.MM());
		ag6 = new AdvantageGroup();
		ag6.add(0, af.GF());
		ag6.add(0, af.ZZ());
		ag7 = new AdvantageGroup();
		ag7.add(0, af.Reg());
		ag7.add(0, af.DB());
		specialRules = "Die Anri leben in einer ungewöhnlichen, unbewussten Symbiose mit Erdgeistern. Aus diesem Grund\nsind Zauber innerhalb fester Gemäuer (massiver Gebäude, Festungen, …) um 1 erleichtert, in Höhlen\noder Tunneln sogar um 2. Zu Luftgeistern hingegen haben sie jeglichen Bezug verloren: Außerhalb von\nGebäuden sind Zauber um 1 erschwert, sogar um 2, wenn der Tiefelfen langfristig keinen Kontakt zum\nErdboden heißt (d.h. fliegt). Die Tiefelfen spüren die Verbundenheit zu den Erdgeistern auch, was\nsie zwar in keinster Weise davon abhält, Erde zu verformen, zu bearbeiten und zu durchtunneln (ganz\nim Gegenteil), aber rasend macht, wenn Erde auf dämonische oder chemische Art und Weise\nverschmutzt wird.";
		ret.add(new Style("Anritur", Style.WIZARD_STYLE, advantages(null, ag, null, ag2, null, ag3, null, ag4, null, null, ag5, null, ag7, null, ag6, null, null, null, null, null), specialRules));

		
		ag = new AdvantageGroup();
		ag.add(0, af.Ket());
		ag.add(0, af.DB());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Par());
		ag2.add(0, af.Ex());
		ag2.add(0, af.ZT());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.Rit());
		ag3.add(0, af.WMmag());
		ag4 = new AdvantageGroup();
		ag4.add(0, af.Blut());
		ag4.add(0, af.SV());
		ag5 = new AdvantageGroup();
		ag5.add(0, af.CM());
		ag5.add(0, af.Erz());
		ag5.add(0, af.ZS());
		ag5.setNote("CM nur wenn vorher WM gewählt wurde");
		ag6 = new AdvantageGroup();
		ag6.add(0, af.MM());
		ag6.add(0, af.TM());
		ag6.add(0, af.Wa());
		ag6.add(0, af.Wet());
		
		ag7 = new AdvantageGroup();
		ag7.add(0, af.Ast());
		ag7.add(0, af.TE());
		specialRules = "Beim Beherrschen von Tieren haben Gnutesianer keine Erschwernisse, beim Gedankenlesen\n(zumindest bei nahen Verwandten) deutlich geringere. Die 3-Punkte-Beschränkung bei\n„schlampiger Magie“ (siehe „Feuer und Stab“ – „Optionen“) fällt weg, dafür dürfen Gnutesianer\ndie Option „Sauberes Zaubern“ nicht verwenden.";
		ret.add(new Style("Bestienmagie", Style.WIZARD_STYLE, advantages(af.Med(), ag7, null, ag, ag2, null, af.SWmag(), null, ag3, null, ag4, null, ag5, null, ag6, null, null, null, null, null), specialRules));

		
		ag = new AdvantageGroup();
		ag.add(0, af.Stab());
		ag.add(0, af.MK());
		ag2 = new AdvantageGroup();
		ag2.add(1, af.Scho());
		ag2.add(2, af.Reg());
		ag2.add(2, af.Rit());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.SWmag());
		ag3.add(0, af.Ket());
		ag4 = new AdvantageGroup();
		ag4.add(0, af.Blut());
		ag4.add(0, af.GB());
		ag4.add(0, af.SV());
		ag5 = new AdvantageGroup();
		ag5.add(1, af.Men());
		ag5.add(2, af.Tasch());
		ag5.add(2, af.KS());
		ag6 = new AdvantageGroup();
		ag6.add(0, af.MK());
		ag6.add(0, af.Par());
		ag6.add(0, af.ZT());
		ag7 = new AdvantageGroup();
		ag7.add(0, af.HT());
		ag7.add(0, af.MD());
		
		specialRules = "Manche von ihnen stinken fürchterlich, weil sie sich im Untergrund verstecken müssen.\nAndere aber auch nicht. *schulterzuck*";
		ret.add(new Style("Böse Buben", Style.WIZARD_STYLE, advantages(null, af.ZZ(), null, ag, ag2, null, af.ZS(), af.SchZa(), null, ag3, null, ag4, ag5, ag6, ag7, null, null, null, null, null), specialRules));


		ag = new AdvantageGroup();
		ag.add(0, af.Par());
		ag.add(0, af.Stab());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Ex());
		ag2.add(0, af.KS());
		ag2.add(0, af.Rit());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.Blut());
		ag3.add(0, af.ZS());
		ag4 = new AdvantageGroup();
		ag4.add(0, af.Ex());
		ag4.add(0, af.KS());
		ag4.add(0, af.Par());
		ag4.add(0, af.Stab());
		ag5 = new AdvantageGroup();
		ag5.add(0, af.Ast());
		ag5.add(0, af.Ket());
		ag6 = new AdvantageGroup();
		ag6.add(0, af.HT());
		ag6.add(0, af.MD());
		
		specialRules = "Wenn sie den Vorteil CM verwenden und inzugedessen beim Wilde-Magie-Wurf eine 1 oder 6 würfeln,\ndürfen sie (wenn sie wollen) einen W6 würfeln. Ist das Ergebnis höher wie der Zauberstil/5, abgerundet\n(nachträglich dazugekaufte Vorteile zählen nicht, das Maximum ist also immer 3), folgt der Zauber\nden normalen Regeln. Ist das Ergebnis aber höchstens so hoch wie der Zauberstil/5, wird der\nWilde-Magie-Wurf einfach ignoriert. In dem Fall wird der Zauber zwar mit Chaosenergie gewirkt, folgt\naber sonst den normalen Regeln.";
		ret.add(new Style("Chaosmagie", Style.WIZARD_STYLE, advantages(ag, null, af.GB(), null, ag2, af.WMmag(), ag3, null, af.Rit(), null, ag4, af.CM(), null, ag5, ag6, null, null, null, null, null), specialRules));

		ag = new AdvantageGroup();
		ag.add(0, af.SchZa());
		ag.add(0, af.SV());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Int());
		ag2.add(0, af.HT());
		
		specialRules = "keine";
		ret.add(new Style("Clansmagie", Style.WIZARD_STYLE, advantages(null, af.MK(), ag, null, af.Tasch(), af.TB(), af.SWmag(), af.Reg(), af.MK(), null, af.WMmag(), null, af.ZZ(), null, ag2, null, null, null, null, null), specialRules));


		ag = new AdvantageGroup();
		ag.add(0, af.Par());
		ag.add(0, af.Stab());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Ex());
		ag2.add(0, af.Stab());
		ag3 = new AdvantageGroup();
		ag3.add(1, af.Scho());
		ag3.add(2, af.nix());
		ag4 = new AdvantageGroup();
		ag4.add(3, af.Blut());
		ag4.add(4, af.WMmag());
		ag5 = new AdvantageGroup();
		ag5.add(1, af.Men());
		ag5.add(2, af.HT());
		ag5.add(2, af.MD());
		ag5.add(2, af.SWmag());
		ag6 = new AdvantageGroup();
		ag6.add(0, af.Reg());
		ag6.add(0, af.Rit());
		ag7 = new AdvantageGroup();
		ag7.add(3, af.ZZ());
		ag7.add(4, af.CM());
		AdvantageGroup ag8 = new AdvantageGroup();
		ag8.add(0, af.Par());
		ag8.add(0, af.Ast());
		
		specialRules = "Wenn sie den Vorteil CM verwenden und inzugedessen beim Wilde-Magie-Wurf eine 1 oder 6 würfeln,\ndürfen sie (wenn sie wollen) einen W6 würfeln. Ist das Ergebnis höher wie der Zauberstil/5, abgerundet\n(nachträglich dazugekaufte Vorteile zählen nicht, das Maximum ist also immer 3), folgt der Zauber\nden normalen Regeln. Ist das Ergebnis aber höchstens so hoch wie der Zauberstil/5, wird der\nWilde-Magie-Wurf einfach ignoriert. In dem Fall wird der Zauber zwar mit Chaosenergie gewirkt, folgt\naber sonst den normalen Regeln.";
		ret.add(new Style("Dämonologie", Style.WIZARD_STYLE, advantages(af.GB(), null, af.Ket(), ag, af.Par(), null, ag2, ag3, null, ag4, null, ag5, ag6, null, ag7, null, null, null, null, null), specialRules));

		
		ag = new AdvantageGroup();
		ag.add(0, af.Wet());
		ag.add(0, af.MM());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.ZimK());
		ag2.add(0, af.Fet());
		
		specialRules = "Die Ken’tanis ziehen einen Teil ihrer Energie aus Wind, See und Wetter. Bei Unwettern oder auf\nhoher See erhalten sie also 3 Punkte Erleichterung auf alle Zauber. Wenn sie allerdings auf festem\nBoden stehen und das Wetter ruhig, mild und fad ist, erhalten sie dafür 1-2 Punkte Erschwernis.";
		ret.add(new Style("Drakenföhr", Style.WIZARD_STYLE, advantages(null, af.MK(), af.SWmag(), null, af.WMmag(), null, af.SchZa(), ag, null, af.Reg(), af.MK(), null, af.SV(), null, ag2, null, null, null, null, null), specialRules));

		
		ag = new AdvantageGroup();
		ag.add(0, af.Par());
		ag.add(0, af.KS());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.SWmag());
		ag2.add(0, af.HT());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.Erz());
		ag3.add(0, af.GF());
		ag3.add(0, af.Reg());
		ag4 = new AdvantageGroup();
		ag4.add(1, af.Blut());
		ag4.add(1, af.SV());
		ag4.add(2, af.WMmag());
		ag5 = new AdvantageGroup();
		ag5.add(1, af.Ket());
		ag5.add(2, af.CM());
		
		specialRules = "Arkanokraten können niemals auf Formeln oder Gesten verzichten, dafür sind sämtliche Zauberproben\num 1 erleichtert. Wenn Arkanokraten Gedankenfreiheit haben, können sie zwar auf Formeln verzichten,\nmüssen dafür aber immer noch die doppelten Abzüge hinnehmen, die für andere gelten würden.\nGesten können aber ganz normal eingespart werden.\nDa die ersten drei Vorteile der lichten und der dunklen Arkanokratie gleich sind, muss der Magier\nerst ab dem 4. Punkt entscheiden, für welche Seite der Macht er sich entscheidet.";
		ret.add(new Style("Dunkle Arkanokraten", Style.WIZARD_STYLE, advantages(af.Ast(), null, af.Ex(), af.ZS(), ag, ag2, null, af.Rit(), null, af.MK(), af.Stab(), ag3, ag4, null, ag5, null, null, null, null, null), specialRules));

		
		ag = new AdvantageGroup();
		ag.add(0, af.Ex());
		ag.add(0, af.Pan());
		ag.add(0, af.ZT());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Med());
		ag2.add(0, af.Stab());
		ag2.add(0, af.ZT());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.DB());
		ag3.add(0, af.ZS());
		ag4 = new AdvantageGroup();
		ag4.add(0, af.GF());
		ag4.add(0, af.MM());
		
		specialRules = "Erde und Luft stehen einander diametral gegenüber. Alle Erdelementarmagiezauber sind um 2\nPunkte erleichtert, Luftzauber dagegen um 3 erschwert. Zauber, die dem Element Erde nahestehen\n(Heilung, Schadenszauber nichtelementarer Basis und Zauberschilder) sind immer um 1\nerleichtert, während Luftzauber (Hellsicht, Sphärenblick, Illusion und Verschleierung) um 2\nerschwert sind. In Gebirgen und Höhlen können Erdmagier auf ihre Zauberei weitere 1 bis 2 Punkte\nErleichterung bekommen, während tiefe Gewässer oder luftige Höhen das Zaubern um bis zu 3\nPunkte erschweren können. Erdmagier mit Gedankenfreiheit können die Nachteile nicht völlig\nignorieren, sondern nur halbieren (zu ihren Gunsten gerundet).";
		ret.add(new Style("Erdmagie", Style.WIZARD_STYLE, advantages(af.Sich(), null, af.DB(), null, ag, af.Rit(), null, af.DB(), ag2, null, af.Ket(), ag3, af.Reg(), null, ag4, null, null, null, null, null), specialRules));

		
		
		ag = new AdvantageGroup();
		ag.add(0, af.Med());
		ag.add(0, af.Pan());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Sich());
		ag2.add(0, af.ZT());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.Ast());
		ag3.add(0, af.GF());
		ag4 = new AdvantageGroup();
		ag4.add(0, af.Fet());
		ag4.add(0, af.MM());
		ag4.add(0, af.Wa());
		ag4.add(0, af.Wet());
		
		specialRules = "Eremiten profitieren von der Option Harmonie doppelt, dafür sind alle Zauber, die nicht\nin Harmonie gewirkt worden sind, um 1 erschwert. Vampire, Werwölfe und Ifrit mit diesem\nVorteil sind in der Lage, Harmonie normal zu nutzen,wie andere Eremiten auch (Vampire nur,\nwenn sie keine Kleidung in die Aura gebunden haben).Ein Einsiedler, der Krebs im\nSternzeichen ist, erhält 1 Punkt Erleichterung auf alle Zauber (gilt nur, wenn man Einsiedler\naußerhalb von Attarinth spielt, denn „Krebs“ ist inAttarinth kein Sternzeichen).";
		ret.add(new Style("Eremit", Style.WIZARD_STYLE, advantages(ag, null, af.DB(), null, af.Rit(), null, af.ZS(), ag2, af.SWmag(), null, af.DB(), null, ag3, null, ag4, null, null, null, null, null), specialRules));

		
		
		ag = new AdvantageGroup();
		ag.add(0, af.Med());
		ag.add(0, af.Tasch());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Ket());
		ag2.add(0, af.DB());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.Int());
		ag3.add(0, af.HT());
		ag4 = new AdvantageGroup();
		ag4.add(0, af.Reg());
		ag4.add(0, af.DB());
		ag5 = new AdvantageGroup();
		ag5.add(0, af.SchZa());
		ag5.add(0, af.TE());
		ag6 = new AdvantageGroup();
		ag6.add(0, af.SichMK());
		ag6.add(0, af.GF());
		
		specialRules = "In natürlicher Umgebung sind alle Zauberproben um 2 erleichtert, in urbaner dafür um 1-2 erschwert.\nFeen können gleichzeitig zaubern und mit der Zunge ihre Nase berühren.";
		ret.add(new Style("Feen", Style.WIZARD_STYLE, advantages(af.SWmag(), ag, null, ag2, null, ag3, null, ag4, null, ag5, null, ag6, null, null, af.Int(), null, null, null, null, null), specialRules));


		ag = new AdvantageGroup();
		ag.add(0, af.Ket());
		ag.add(0, af.Reg());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.SchZa());
		ag2.add(0, af.ZS());
		ag3 = new AdvantageGroup();
		ag3.add(1, af.MK());
		ag3.add(2, af.nix());
		ag4 = new AdvantageGroup();
		ag4.add(1, af.KS());
		ag4.add(2, af.ZZ());
		ag5 = new AdvantageGroup();
		ag5.add(0, af.Erz());
		ag5.add(0, af.GF());
		ag6 = new AdvantageGroup();
		ag6.add(0, af.MM());
		ag6.add(0, af.ZimK());

		specialRules = "Feuer und Wasser stehen einander diametral gegenüber. Alle Feuerelementarmagiezauber sind\num 2 Punkte erleichtert, Wasserzauber dagegen um 3 erschwert. Zauber, die dem Element\nFeuer nahestehen (Schadenszauber nichtelementarer Basis, Sphärenblick und Veränderung) sind immer um 1 erleichtert, während\nWasserzauber (Gedankenlesen, Beherrschung, Verschleierung) um 2 erschwert sind.\nIn heißen und trockenen Gebieten oder in der Nähe großer Feuer sind Zauber von Feuermagiern\num 1 bis 2 weitere Punkte erleichtert, während große Gewässer oder sehr kalte Orte ihre Zauber um bis zu 3 Punkte\nschwieriger machen können.\nFeuermagier mit Gedankenfreiheit können die Nachteile nicht völlig ignorieren, sondern nur halbieren\n(zu ihren Gunsten gerundet).\nFeuermagier mit Knechtschaft der Seelen kann diese auch nutzen, wenn einer seiner Feuerzauber mit dem Sterbenden in Berührung ist.";
		ret.add(new Style("Feuermagie", Style.WIZARD_STYLE, advantages(af.MK(), null, ag, null, ag2, af.Zei(), null, ag3, ag4, af.WM(), ag5, null, af.UZ(), af.MK(), ag6, null, null, null, null, null), specialRules));


		ag = new AdvantageGroup();
		ag.add(0, af.Med());
		ag.add(0, af.Par());
		ag.add(0, af.Tasch());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Stab());
		ag2.add(0, af.Tasch());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.Scho());
		ag3.add(0, af.Reg());
		ag4 = new AdvantageGroup();
		ag4.add(1, af.Zei());
		ag4.add(2, af.nix());
		ag5 = new AdvantageGroup();
		ag5.add(0, af.MK());
		ag5.add(0, af.Sich());
		ag6 = new AdvantageGroup();
		ag6.add(1, af.UZ());
		ag6.add(2, af.ZS());
		ag7 = new AdvantageGroup();
		ag7.add(0, af.HT());
		ag7.add(0, af.MD());
		
		specialRules = "Ein Jahrmarktszauberer kann seine Zauber (gratis) optisch ein wenig aufpeppen: Sternchen die\nherumschwirren, Glitzer oder Lichteffekte. Sobald er Stabzauber beherrscht, kann er ähnliche\nSpielereien auch mit seinem Zauberstab machen. Da Jahrmarktszauberer aber auch unauffällig\nzaubern (natürlich nur während der Show, er würde doch niemals…), sind diese Effekte kein Muss,\nsondern nur ein Kann.\nDie Kinder lieben es!";
		ret.add(new Style("Jahrmarktszauberei", Style.WIZARD_STYLE, advantages(ag, null, af.SchZa(), ag2, null, ag3, ag4, af.Rit(), af.MK(), null, af.DB(), ag5, ag6, af.MK(), ag7, null, null, null, null, null), specialRules));


		ag = new AdvantageGroup();
		ag.add(0, af.Blut());
		ag.add(0, af.SV());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.GB());
		ag2.add(0, af.ZS());
		ag3 = new AdvantageGroup();
		ag3.add(1, af.Sich());
		ag3.add(2, af.nix());
		ag4 = new AdvantageGroup();
		ag4.add(1, af.Int());
		ag4.add(2, af.HT());
		ag5 = new AdvantageGroup();
		ag5.add(1, af.ZZ());
		ag5.add(2, af.MM());
				
		specialRules = "Wenn dem Kelich keinerlei Blut bzw. Mana fehlt, erhält er +1 auf alle Zauberfertigkeiten.\nFehlen ihm mindestens (Z/2) Punkte Lebensessenz, sind alle Zauberproben um 1 erschwert. Fehlen\nihm gar (Z) Punkte, sind alle Proben um 3 erschwert.";
		ret.add(new Style("Kelich", Style.WIZARD_STYLE, advantages(af.Par(), af.Ast(), null, ag, ag2, null, af.Ket(), null, af.Rit(), null, ag3, ag4, null, null, ag5, null, null, null, null, null), specialRules));

		
		ag = new AdvantageGroup();
		ag.add(0, af.ZimK());
		ag.add(0, af.MD());
		
		specialRules = "keine";
		ret.add(new Style("Königsstil", Style.WIZARD_STYLE, advantages(af.Sich(), af.Ex(), af.Scho(), null, af.Ast(), af.ZS(), af.Zei(), af.SchZa(), null, af.Reg(), af.MK(), af.UZ(), af.Pan(), null, ag, null, null, null, null, null), specialRules));

		ag = new AdvantageGroup();
		ag.add(1, af.Zei());
		ag.add(2, af.nix());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Reg());
		ag2.add(0, af.Scho());
		ag2.add(0, af.SW());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.Ex());
		ag3.add(0, af.KS());
		ag3.add(0, af.Stab());
		ag4 = new AdvantageGroup();
		ag4.add(0, af.WM());
		ag4.add(0, af.ZS());
		ag5 = new AdvantageGroup();
		ag5.add(1, af.UZ());
		ag5.add(2, af.Blut());
		ag5.add(2, af.SV());
		ag6 = new AdvantageGroup();
		ag6.add(1, af.GF());
		ag6.add(2, af.ZZ());
		ag7 = new AdvantageGroup();
		ag7.add(0, af.MM());
		ag7.add(0, af.MD());
		ag7.add(0, af.ZimK());
		
		specialRules = "Kriegsmagier können Zauber, die für gewöhnlich sichtbar sind (Beherrschung, Entzauberung, …),\nnicht unsichtbar machen. Die einzige Ausnahme sind Sphärenblick und Hellsicht – hier\nkönnen sie die Option zum Unsichtbarmachen verwenden.\nKriegsmagier können Schadenszauber nichtelementarer Basis und unmittelbar auf Feinde gewirkte\nKampfzauber (nicht allerdings elementare Geschosse oder durch Kampfzauber gefeuerte mundane\nGeschosse) so schaffen, dass eine Art magischer Brücke zwischen dem Magier und seinem\nZiel hergestellt wird, über die er die nächsten fünf Minuten lang ZZ und KS einsetzen kann,\nwenn er diese Vorteile hat. Wenn er Ex beherrscht, kann er die Zauber auch statt ihrer\nnormalen Wirkung als Exorzismus einsetzen –\ner muss aber noch immer treffen bzw. in\nReichweite sein.";
		ret.add(new Style("Krieg", Style.WIZARD_STYLE, advantages(af.MK(), af.SchZa(), ag, ag2, null, ag3, af.MK(), null, ag4, ag5, null, ag6, af.MK(), null, ag7, null, null, null, null, null), specialRules));

		ag = new AdvantageGroup();
		ag.add(0, af.DB());
		ag.add(0, af.GB());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.WM());
		ag2.add(0, af.Ast());
		
		specialRules = "Wenn La’an von ihrem Manapool zehren, um Magiezehrung einzusparen, kann er die Magiezehrung\num 2 Punkte für 3 Mana senken.";
		ret.add(new Style("La'an", Style.WIZARD_STYLE, advantages(af.Ket(), null, af.Pan(), null, ag, null, af.SWmag(), null, ag2, af.MD(), null, af.ZZ(), null, null, af.Int(), null, null, null, null, null), specialRules));


		ag = new AdvantageGroup();
		ag.add(0, af.Erz());
		ag.add(0, af.MD());
		
		specialRules = "Arkanokraten können niemals auf Formeln oder Gesten verzichten, dafür sind sämtliche Zauberproben\num 1 erleichtert. Wenn Arkanokraten Gedankenfreiheit haben, können sie zwar auf Formeln verzichten,\nmüssen dafür aber immer noch die doppelten Abzüge hinnehmen, die für andere gelten würden.\nGesten können aber ganz normal eingespart werden.\nDa die ersten drei Vorteile der lichten und der dunklen Arkanokratie gleich sind, muss der Magier\nerst ab dem 4. Punkt entscheiden, für welche Seite der Macht er sich entscheidet.";
		ret.add(new Style("Lichte Arkanokraten", Style.WIZARD_STYLE, advantages(af.Ast(), null, af.Ex(), af.ZS(), null, af.SWmag(), af.Zei(), null, null, af.Ket(), null, ag, af.UZ(), null, af.Int(), null, null, null, null, null), specialRules));

		ag = new AdvantageGroup();
		ag.add(0, af.Sich());
		ag.add(0, af.Tasch());
		ag2 = new AdvantageGroup();
		ag2.add(1, af.Scho());
		ag2.add(2, af.Zei());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.DB());
		ag3.add(0, af.SchZa());
		ag4 = new AdvantageGroup();
		ag4.add(3, af.Wet());
		ag4.add(4, af.GB());
		ag5 = new AdvantageGroup();
		ag5.add(1, af.nix());
		ag5.add(2, af.UZ());
		ag6 = new AdvantageGroup();
		ag6.add(0, af.Int());
		ag6.add(0, af.MM());
		ag6.add(0, af.ZimK());
		ag7 = new AdvantageGroup();
		ag7.add(1, af.Men());
		ag7.add(2, af.Ex());
		ag8 = new AdvantageGroup();
		ag8.add(0, af.Erz());
		ag8.add(0, af.Reg());
		AdvantageGroup ag9 = new AdvantageGroup();
		ag9.add(3, af.MWM());
		ag9.add(4, af.HT());
				
		specialRules = "brabbelbrabbel";
		ret.add(new Style("Luftmagie", Style.WIZARD_STYLE, advantages(ag, null, ag2, null, ag3, af.WM(), ag4, null, ag5, ag6, ag7, null, ag8, null, ag9, null, null, null, null, null), specialRules));

		
		ag = new AdvantageGroup();
		ag.add(1, af.Scho());
		ag.add(2, af.Reg());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Med());
		ag2.add(0, af.MK());
		ag2.add(0, af.Pan());
		ag2.add(0, af.Stab());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.Ex());
		ag3.add(0, af.Sich());
		ag4 = new AdvantageGroup();
		ag4.add(1, af.Men());
		ag4.add(2, af.Pan());
		ag5 = new AdvantageGroup();
		ag5.add(0, af.Ast());
		ag5.add(0, af.Erz());
		ag5.add(0, af.Rit());
		
		specialRules = "Disharmonien erschweren bei Miae nicht nur die Probe (wie sie das bei allen Elfen tut),\nsondern senkt zudem die verfügbare Zauberkraft um dasselbe. Solange Hochelfenmagier nicht nur\nnicht unter Disharmonie leiden, sondern auch noch in einer recht harmonischen Umgebung sind\n(z.b. Wälder, friedliche Tempel, ...) sind Zauber entweder um 1 erleichtert oder brauchen 1 Mana\nweniger (nach Willkür des Meisters) oder, in wirklich seltenen Fällen sogar beides.";
		ret.add(new Style("Miae", Style.WIZARD_STYLE, advantages(null, ag, af.DB(), ag2, null, null, af.Ket(), af.SW(), null, af.DB(), af.ZS(), ag3, null, ag4, ag5, null, null, null, null, null), specialRules));
		
		ag = new AdvantageGroup();
		ag.add(1, af.Scho());
		ag.add(2, af.SchZa());
		ag2 = new AdvantageGroup();
		ag2.add(1, af.SchZa());
		ag2.add(2, af.Rit());
		
		specialRules = "Magier, die alle denselben Stil lernen und miteinander geübt haben, können Kettenzauberei (unter\ngutem Kommando) auch einsetzen, wenn ihre Partner nur in der Nähe sind, sich aber nicht berühren\n(sie sollten aber nicht weiter als einen Faden voneinander entfernt sein, können aber auf diese Art\nund Weise auch eine Kette bilden).";
		ret.add(new Style("Militär", Style.WIZARD_STYLE, advantages(af.MK(), ag, af.Ket(), af.SW(), null, af.Zei(), af.MK(), af.ZS(), null, af.MK(), null, af.UZ(), ag2, af.MK(), af.Reg(), null, null, null, null, null), specialRules));
	
		
		ag = new AdvantageGroup();
		ag.add(0, af.Med());
		ag.add(0, af.Tasch());
		ag2 = new AdvantageGroup();
		ag2.add(1, af.MK());
		ag2.add(2, af.DB());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.Med());
		ag3.add(0, af.Tasch());
		ag3.add(0, af.MK());
		ag4 = new AdvantageGroup();
		ag4.add(1, af.Reg());
		ag4.add(2, af.DB());
		ag5 = new AdvantageGroup();
		ag5.add(0, af.Med());
		ag5.add(0, af.MK());
		ag5.add(0, af.Stab());
		ag6 = new AdvantageGroup();
		ag6.add(0, af.Fet());
		ag6.add(0, af.Int());
		ag6.add(0, af.MK());
		ag6.add(0, af.ZZ());
		
		specialRules = "Hexer, die bewusstseinserweiternde Substanzen einnehmen, erhalten +1 auf ihre Zauberfertigkeit,\nsofern der Spielleiter nichts gegen Drogen hat.\nWar nur’n Scherz. Was aber wirklich geht: Du kannst eventuell in Absprache mit dem Spielleiter\nden Stil ein wenig an deinen Helden anpassen.";
		ret.add(new Style("mit Gefühl...", Style.WIZARD_STYLE, advantages(ag, null, ag2, null, ag3, af.WM(), null, ag4, af.GB(), ag5, null, af.SW(), af.MK(), null, ag6, null, null, null, null, null), specialRules));

		
		ag = new AdvantageGroup();
		ag.add(0, af.Ket());
		ag.add(0, af.Rit());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Ast());
		ag2.add(0, af.Wa());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.Fet());
		ag3.add(0, af.SW());
		ag4 = new AdvantageGroup();
		ag4.add(1, af.Ex());
		ag4.add(1, af.ZT());
		ag4.add(2, af.DB());
		ag5 = new AdvantageGroup();
		ag5.add(0, af.Reg());
		ag5.add(0, af.SW());
		ag6 = new AdvantageGroup();
		ag6.add(1, af.WM());
		ag6.add(2, af.DB());
		ag7 = new AdvantageGroup();
		ag7.add(0, af.Fet());
		ag7.add(0, af.GF());
		ag7.add(0, af.Wet());
		
		specialRules = "Ein elfischer Magier kennt eine besondere Form der Veränderungsmagie. Auf Pflanzen gewirkt,\nbewirkt sie, dass sich die Pflanze durch Gedankenkraft formen lässt. Es ist dadurch nicht möglich,\nsie zu schmelzen oder verdampfen zu lassen, sondern sie nur in natürlichem Maße zu verändern.\nSo kann man beispielsweise die Form von Wurzeln und Bäumen so verändern, dass man darin leben\noder bauen kann oder auch Pflanzen zum Wachsen bringen (ein guter Zauberer benötigt bei einem\nStrauch oder kleinen Baum etwa eine halbe Stunde, um ihn ein ganzes Jahr wachsen/blühen zu lassen).\nAußerdem sind sind in natürlicher Umgebung alle Zauberproben um 2 erleichtert, in urbaner dafür\num 1-2 erschwert.";
		ret.add(new Style("N’leygin my", Style.WIZARD_STYLE, advantages(af.Med(), null, af.TE(), null, ag, ag2, ag3, null, ag4, null, ag5, null, ag6, null, ag7, null, null, null, null, null), specialRules));

		
		ag = new AdvantageGroup();
		ag.add(0, af.Med());
		ag.add(0, af.MK());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.SchZa());
		ag2.add(0, af.WM());
		ag2.add(0, af.ZZ());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.HT());
		ag3.add(0, af.SW());
		ag4 = new AdvantageGroup();
		ag4.add(0, af.DB());
		ag4.add(0, af.GB());
		ag5 = new AdvantageGroup();
		ag5.add(0, af.Int());
		ag5.add(0, af.ZimK());
		
		
		specialRules = "Für normale, redliche Bürger kosten Taschenspielertricks nur die Hälfte (auf 5 EP aufgerundet).\nSie erhalten mit der Fähigkeit fünf gratis, nicht nur drei.";
		ret.add(new Style("Normaler Bürger", Style.WIZARD_STYLE, advantages(ag, null, ag2, null, af.Tasch(), ag3, null, null, ag4, null, af.MM(), af.Reg(), null, null, ag5, null, null, null, null, null), specialRules));


		ag = new AdvantageGroup();
		ag.add(1, af.Scho());
		ag.add(2, af.Ast());
		ag2 = new AdvantageGroup();
		ag2.add(1, af.Ex());
		ag2.add(1, af.ZT());
		ag2.add(2, af.nix());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.DB());
		ag3.add(0, af.ZS());
		ag4 = new AdvantageGroup();
		ag4.add(1, af.Men());
		ag4.add(2, af.GF());
		ag4.add(2, af.ZZ());
		ag5 = new AdvantageGroup();
		ag5.add(0, af.Blut());
		ag5.add(0, af.HT());
				
		specialRules = "Alle Zauber (nicht Zeremonien), für die sich keine Zeit gelassen wurde, sind um 1 erschwert. Dafür\nsind alle Zauber, für die man sich mindestens 2 mal Zeit lässt, um 1 erleichtert.";
		ret.add(new Style("Northern Necromancy", Style.WIZARD_STYLE, advantages(af.Par(), ag, af.Ket(), null, af.Rit(), ag2, af.Sich(), null, ag3, null, ag4, af.MM(), null, null, ag5, null, null, null, null, null), specialRules));

		ag = new AdvantageGroup();
		ag.add(0, af.KS());
		ag.add(0, af.Pan());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.DB());
		ag2.add(0, af.ZS());
		
		ag4 = new AdvantageGroup();
		ag4.add(0, af.MD());
		ag4.add(0, af.MM());
				
		specialRules = "Für je 3 Punkte Energie, die Numre aus Blutmagie ziehen, erhalten sie einen 4. gratis dazu. Auf\nWunsch auch als Geschenk verpackt. Die Zusatzfertigkeit „Kettenmagie“ kostet allerdings für Numre\nnicht 7, sondern 10 EP, da sie von Natur aus eher Einzelgänger sind (was nicht heißt, dass sie\nsich nicht die Technik zunutze machen würden – sie haben nur ein wenig Probleme, sich aufeinander\neinzustimmen).";
		ret.add(new Style("Numrašri", Style.WIZARD_STYLE, advantages(af.Par(), null, null, af.Blut(), null, ag, ag2, null, af.Rit(), af.Ket(), null, af.Ast(), af.Reg(), null, ag4, null, null, null, null, null), specialRules));



		ag = new AdvantageGroup();
		ag.add(0, af.Sich());
		ag.add(0, af.Stab());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Erz());
		ag2.add(0, af.Reg());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.Int());
		ag3.add(0, af.MM());
				
		specialRules = "Spruchrollen und Zauberbücher, die von Philosophen gelesen werden, die ihren Zauberstil auf 15\ngebracht haben, erleiden 1 Punkt weniger Abzug auf die Macht. Das bedeutet, dass ein Philosoph\nmit Zauberstil 15 eine Spruchrolle, die von einem Akademiethaumaturgen mit Zauberstil 15\nverfasst worden ist, ohne Einbußen auf die Macht lesen könnte.";
		ret.add(new Style("Philosophenstil", Style.WIZARD_STYLE, advantages(null, af.Scho(), null, af.ZS(), af.Ast(), null, af.Rit(), ag, af.Men(), null, ag2, null, af.Ket(), null, ag3, null, null, null, null, null), specialRules));

		
		
		ag = new AdvantageGroup();
		ag.add(1, af.Scho());
		ag.add(2, af.nix());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.KS());
		ag2.add(0, af.Sich());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.Blut());
		ag3.add(0, af.SV());
		ag4 = new AdvantageGroup();
		ag4.add(1, af.Men());
		ag4.add(2, af.Fet());
		ag4.add(2, af.Int());
		ag5 = new AdvantageGroup();
		ag5.add(0, af.Reg());
		ag5.add(0, af.Rit());
		ag6 = new AdvantageGroup();
		ag6.add(0, af.HT());
		ag6.add(0, af.MD());
		
		specialRules = "keine";
		ret.add(new Style("Schattenlauf", Style.WIZARD_STYLE, advantages(af.MK(), ag, ag2, null, af.SchZa(), null, ag3, af.MK(), null, af.ZimK(), null, ag4, null, ag5, ag6, null, null, null, null, null), specialRules));

		
		
		ag = new AdvantageGroup();
		ag.add(1, af.MD());
		ag.add(2, af.MK());
		ag2 = new AdvantageGroup();
		ag2.add(1, af.MK());
		ag2.add(2, af.ZimK());
		
		specialRules = "Hexer, die alle denselben Stil lernen und miteinander geübt haben, können Kettenzauberei (unter\ngutem Kommando) auch einsetzen, wenn ihre Partner nur in der Nähe sind, sich aber nicht berühren\n(sie sollten aber nicht weiter als einen Faden voneinander entfernt sein, können aber auf diese Art\nund Weise auch eine Kette bilden).";
		ret.add(new Style("Soldatenstil", Style.WIZARD_STYLE, advantages(af.MK(), null, af.Ket(), af.MK(), af.SchZa(), af.Zei(), null, ag, null, af.MK(), af.Reg(), af.UZ(), af.SW(), null, ag2, null, null, null, null, null), specialRules));

		
		ag = new AdvantageGroup();
		ag.add(0, af.Med());
		ag.add(0, af.ZT());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Ex());
		ag2.add(0, af.Stab());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.SV());
		ag3.add(0, af.TE());
		ag3.add(0, af.ZS());
		ag4 = new AdvantageGroup();
		ag4.add(0, af.Wa());
		ag4.add(0, af.Wet());
		ag5 = new AdvantageGroup();
		ag5.add(0, af.Ast());
		ag5.add(0, af.WM());
		ag6 = new AdvantageGroup();
		ag6.add(0, af.Int());
		ag6.add(0, af.MM());
		
		specialRules = "Stammeszauberer können Nichtmagiebegabten die Fertigkeit „Kettenmagie“ lehren, die man\nnormalerweise nur nutzen kann, wenn man den magischen Vorteil Ket beherrscht. Wenn sie diese\nFertigkeit auf 1 heben (also 7 EP darin investieren), kann der Stammeszauberer 1 Punkt Mana von\nihnen ziehen. Was bei einem Magieniveau von 7 natürlich zu Essenzschaden führt.";
		ret.add(new Style("Stammeszauberer", Style.WIZARD_STYLE, advantages(af.Ket(), null, ag, af.Par(), null, ag2, af.Rit(), null, ag3, null, ag4, null, ag5, null, ag6, null, null, null, null, null), specialRules));

		
		ag = new AdvantageGroup();
		ag.add(0, af.Med());
		ag.add(0, af.ZT());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Ket());
		ag2.add(0, af.Rit());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.DB());
		ag3.add(0, af.GB());
		ag4 = new AdvantageGroup();
		ag4.add(0, af.TM());
		ag4.add(0, af.Wa());
		ag5 = new AdvantageGroup();
		ag5.add(0, af.Ast());
		ag5.add(0, af.GF());
		ag5.add(0, af.HT_Stroj());
		ag5.add(0, af.WM());
		ag6 = new AdvantageGroup();
		ag6.add(0, af.Int());
		ag6.add(0, af.ErzStroj());
		
		specialRules = "Štroj erhalten 3 Punkte Erschwernis, wenn sie Beherrschungsmagie auf Menschen wirken. Die 3\nPunkte Erschwernis beim Verzaubern von Tieren gelten zwar grundsätzlich, werden aber dadurch\brelativiert und sogar ins Gegenteil verkehrt, dass alle Beherrschungszauber auf Tiere um\nZoologie/3 erleichtert sind (eine Fertigkeitsspezialisierung ist möglich). Sobald dadurch aus\ndem Malus ein Bonus wird, erhalten Štroj denselben Bonus, wenn sie Gedankenbrücke auf Tiere\nanwenden.\nBeispiel: Sergej hat Zoologie: 14. Dividiert durch 3 ergibt das 5. Das hebt die 3 Punkte\nErschwernis auf Tierbeherrschungszauber auf und erleichtert Beherrschungszauber auf Tiere sogar\num 2. Auch Gedankenbrücken sind um 2 erleichtert.";
		ret.add(new Style("Štroj", Style.WIZARD_STYLE, advantages(null, af.TE(), ag, null, af.Par(), af.DB(), null, ag2, ag3, null, ag4, null, ag5, null, ag6, null, null, null, null, null), specialRules));


		
		ag = new AdvantageGroup();
		ag.add(1, af.Scho());
		ag.add(2, af.Med());
		ag2 = new AdvantageGroup();
		ag2.add(1, af.nix());
		ag2.add(2, af.Pan());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.Erz());
		ag3.add(0, af.GF());
		ag3.add(0, af.SW());
		ag4 = new AdvantageGroup();
		ag4.add(0, af.Men());
		ag4.add(0, af.Sich());
		ag5 = new AdvantageGroup();
		ag5.add(0, af.Int());
		ag5.add(0, af.MM());
		
		specialRules = "Wenn ein Suzateka in seiner Bewegung eingeschränkt ist, erleidet er zwischen 1 (enge\nRäumlichkeiten) und 5 (völlige Fesselung) Punkte Erschwernis. Kann er sich allerdings frei\nbewegen, hat er 1 Punkt Erleichterung auf alle Zauber.";
		ret.add(new Style("Suzate", Style.WIZARD_STYLE, advantages(af.Fet(), null, ag, null, af.Ast(), ag2, af.Rit(), null, af.ZS(), ag3, null, ag4, null, null, ag5, null, null, null, null, null), specialRules));

		
		
		ag = new AdvantageGroup();
		ag.add(0, af.Med());
		ag.add(0, af.Stab());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Ket());
		ag2.add(0, af.Reg());
		ag2.add(0, af.WM());
		ag3 = new AdvantageGroup();
		ag3.add(1, af.TE());
		ag3.add(2, af.Tasch());
		ag4 = new AdvantageGroup();
		ag4.add(0, af.Reg());
		ag4.add(0, af.WM());
		ag5 = new AdvantageGroup();
		ag5.add(0, af.DB());
		ag5.add(0, af.GB());
		ag6 = new AdvantageGroup();
		ag6.add(0, af.Fet());
		ag6.add(0, af.SW());
		ag6.add(0, af.Wet());
		ag7 = new AdvantageGroup();
		ag7.add(1, af.Wa());
		ag7.add(2, af.Int());
		ag7.add(2, af.MM());
		
		specialRules = "In sehr städtischer Umgebung erleidet so ein natürlicher Zauberer 1 bis 2 Punkte Erschwernis auf\nalle Zauberproben.\nDafür dürfen sich Wald-und-Au-Hexer einen der beiden Vorteile aussuchen, wenn sie den Stil wählen:\na)	In jeder natürlichen Umgebung erhalten sie 1 Punkt Erleichterung auf alle\nZauberproben. In ihrem angestammten Lebensraum (Steppe, Wald, Gebirge, …) erhalten sie\nstattdessen sogar 2 Punkte.\nb)	Der Hexer zählt automatisch als Meistermagier (Elementarmagie) (siehe\nfolgenden Abschnitt). Er kann sich dann den Vorteil nicht mehr zusätzlich kaufen.";
		ret.add(new Style("Wald und Au", Style.WIZARD_STYLE, advantages(af.MK(), null, ag, ag2, null, ag3, af.MK(), null, ag4, ag5, null, ag6, af.MK(), null, ag7, null, null, null, null, null), specialRules));

		
		ag = new AdvantageGroup();
		ag.add(0, af.Med());
		ag.add(0, af.ZT());
		ag2 = new AdvantageGroup();
		ag2.add(1, af.Ast());
		ag2.add(2, af.Zei());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.SchZa());
		ag3.add(0, af.Erz());
		ag4 = new AdvantageGroup();
		ag4.add(1, af.nix());
		ag4.add(2, af.UZ());
		ag5 = new AdvantageGroup();
		ag5.add(0, af.Fet());
		ag5.add(0, af.MM());
		ag5.add(0, af.Wet());
		
		specialRules = "In natürlicher Umgebung erhält der Zauberer +1 bis +2 auf alle Zauberproben, in stark urbaner\njedoch -1 bis -2.";
		ret.add(new Style("Wald und Wiesen", Style.WIZARD_STYLE, advantages(null, af.MK(), null, af.SW(), ag, af.ZS(), null, af.Reg(), ag2, null, ag3, null, af.WM(), ag4, ag5, null, null, null, null, null), specialRules));

		
		ag = new AdvantageGroup();
		ag.add(0, af.Pan());
		ag.add(0, af.ZT());
		ag2 = new AdvantageGroup();
		ag2.add(0, af.Blut());
		ag2.add(0, af.SV());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.GF());
		ag3.add(0, af.ZZ());
		ag4 = new AdvantageGroup();
		ag4.add(0, af.MM());
		ag4.add(0, af.SW());
		
		specialRules = "Feuer und Wasser stehen einander diametral gegenüber. Alle Wasserelementarmagiezauber sind um\n2 Punkte erleichtert, Feuerzauber dagegen um 3 erschwert. Zauber, die dem Element Wasser\nnahestehen (Gedankenlesen, Beherrschung, Veränderung) sind immer um 1 erleichtert, während\nFeuerzauber (Bewegung und Entzauberung)  um 2 erschwert sind.\nWassermagier bekommen an und auf größeren Gewässern 1 bis 2 Punkte Erleichterung auf ihre\nZauber, während sehr trockene Gebiete ihre Zauber um bis zu 3 Punkte erschweren.\nWassermagier mit Gedankenfreiheit können die Nachteile nicht völlig ignorieren, sondern nur\nhalbieren (zu ihren Gunsten gerundet).";
		ret.add(new Style("Wassermagie", Style.WIZARD_STYLE, advantages(ag, null, af.Rit(), null, af.ZS(), null, ag2, af.DB(), null, ag3, af.HT(), null, ag4, null, af.Erz(), null, null, null, null, null), specialRules));


		ag = new AdvantageGroup();
		ag.add(1, af.Tasch());
		ag.add(2, af.nix());
		ag2 = new AdvantageGroup();
		ag2.add(3, af.Rit());
		ag2.add(4, af.Scho());
		ag3 = new AdvantageGroup();
		ag3.add(0, af.DB());
		ag3.add(0, af.ZS());
		ag4 = new AdvantageGroup();
		ag4.add(0, af.DB());
		ag4.add(0, af.GB());
		ag5 = new AdvantageGroup();
		ag5.add(3, af.Sich());
		ag5.add(4, af.Men());
		ag6 = new AdvantageGroup();
		ag6.add(1, af.Ast());
		ag6.add(1, af.GF());
		ag6.add(2, af.Fet());
		ag7 = new AdvantageGroup();
		ag7.add(0, af.ErzMK());
		ag7.add(0, af.MM());
		ag8 = new AdvantageGroup();
		ag8.add(0, af.HT());
		ag8.add(0, af.MD());
		specialRules = "Wenn bei einem Zauber sichtbare Energien freigesetzt werden (etwa bei Bewegungsmagie) sind\ndiese äußerst dunkel (meist dunkelviolett oder –blau), sodass sie in der Nacht deutlich schwerer zu\nerkennen sind als die Energien anderer Zauberer. Getarnte Beherrschungs- und Gedankenlesen-\nzauber dauern nur anderthalb mal so lang anstatt doppelt so lang als ungetarnte, und bei einer\ngetarnten Entzauberung darf man im geistigen Kampf W8 würfeln statt W6. Die Erschwernis für\nunsichtbare Sphärenblicke und Hellsichtszauber ist nur +1 statt +2.\nAlle Zauber, die dem Verstecken, Stehlen, Meucheln oder anderen sehr heimlichen Zielen dienen,\nhaben eine um 1 gesenkte Komplexität, wohingegen alle sehr offensiven (Kampfmagie gegen einen\nauf einen zustürmenden Gegner) bzw. offensichtlichen (Gedankenlesen während eines\nGerichtsverfahrens) um 1 erschwert sind. Insgesamt sollten ca. je 25 % der Fälle in diese beiden\nKategorien fallen, und etwa 50 % der Zauber davon unbeeinflusst bleiben (wobei natürlich\nZauberer der Nacht hauptsächlich in den heimlichen 25 % der Fälle navigieren).";
		ret.add(new Style("Weg der Nacht", Style.WIZARD_STYLE, advantages(ag, null, ag2, null, ag3, af.SchZa(), null, ag4, ag5, null, ag6, null, ag7, null, ag8, null, null, null, null, null), specialRules));

		
		
		return ret;
	}
	
	
	private static AdvantageFamily[] advantages(AdvantageFamily a1, AdvantageFamily a2, AdvantageFamily a3, AdvantageFamily a4, AdvantageFamily a5, AdvantageFamily a6, AdvantageFamily a7, AdvantageFamily a8, AdvantageFamily a9, AdvantageFamily a10, AdvantageFamily a11, AdvantageFamily a12, AdvantageFamily a13, AdvantageFamily a14, AdvantageFamily a15, AdvantageFamily a16, AdvantageFamily a17, AdvantageFamily a18, AdvantageFamily a19, AdvantageFamily a20)
	{
		AdvantageFamily[] ret = new AdvantageFamily[20];
		ret[0] = a1;
		ret[1] = a2;
		ret[2] = a3;
		ret[3] = a4;
		ret[4] = a5;
		
		ret[5] = a6;
		ret[6] = a7;
		ret[7] = a8;
		ret[8] = a9;
		ret[9] = a10;
		
		ret[10] = a11;
		ret[11] = a12;
		ret[12] = a13;
		ret[13] = a14;
		ret[14] = a15;
		
		ret[15] = a16;
		ret[16] = a17;
		ret[17] = a18;
		ret[18] = a19;
		ret[19] = a20;
		
		return ret;
	}
	
	public static LinkedList<AdvantageInList> buyArmedAdvantages(boolean offensiveStyle)
	{
		AdvantageFactory af = new AdvantageFactory();
		LinkedList<AdvantageInList> ll = new LinkedList<AdvantageInList>();
		
		ll.add(new AdvantageInList(af._1ZpI()));
		ll.add(new AdvantageInList(af.Anab()));
		ll.add(new AdvantageInList(af.BK()));
		ll.add(new AdvantageInList(af.BR()));
		if (offensiveStyle)
			ll.add(new AdvantageInList(af.E()));
		ll.add(new AdvantageInList(af.EEE()));
		ll.add(new AdvantageInList(af.EvW()));
		ll.add(new AdvantageInList(af.Fl()));
		ll.add(new AdvantageInList(af.FT()));
		ll.add(new AdvantageInList(af.Init()));
		ll.add(new AdvantageInList(af.KA()));
		if (!offensiveStyle)
			ll.add(new AdvantageInList(af.KT()));
		ll.add(new AdvantageInList(af.Lh()));
		if (offensiveStyle)
			ll.add(new AdvantageInList(af.MP()));
		ll.add(new AdvantageInList(af.reit()));
		ll.add(new AdvantageInList(af.RG()));
		ll.add(new AdvantageInList(af.Sch()));
		ll.add(new AdvantageInList(af.STs()));
		ll.add(new AdvantageInList(af.SZ()));
		if (!offensiveStyle)
			ll.add(new AdvantageInList(af.ÜA()));
		ll.add(new AdvantageInList(af.WaS()));
		ll.add(new AdvantageInList(af.WM()));
		ll.add(new AdvantageInList(af.WSch()));
		ll.add(new AdvantageInList(af.zAbw()));
		ll.add(new AdvantageInList(af.zWb()));
		
		return ll;
	}
	
	public static LinkedList<AdvantageInList> buyUnarmedAdvantages()
	{
		AdvantageFactory af = new AdvantageFactory();
		LinkedList<AdvantageInList> ll = new LinkedList<AdvantageInList>();
		
		ll.add(new AdvantageInList(af._1ZpI()));
		ll.add(new AdvantageInList(af.Anab()));
		ll.add(new AdvantageInList(af.BK()));
		ll.add(new AdvantageInList(af.EEE()));
		ll.add(new AdvantageInList(af.EvB()));
		ll.add(new AdvantageInList(af.EvD()));
		ll.add(new AdvantageInList(af.EvW()));
		ll.add(new AdvantageInList(af.Fl()));
		ll.add(new AdvantageInList(af.FT()));
		ll.add(new AdvantageInList(af.Init()));
		ll.add(new AdvantageInList(af.iNkw()));
		ll.add(new AdvantageInList(af.KA()));
		ll.add(new AdvantageInList(af.Kb()));
		ll.add(new AdvantageInList(af.KgB()));
		ll.add(new AdvantageInList(af.Ko()));
		ll.add(new AdvantageInList(af.KTMP()));
		ll.add(new AdvantageInList(af.Lh()));
		ll.add(new AdvantageInList(af.MwrS()));
		ll.add(new AdvantageInList(af.reit()));
		ll.add(new AdvantageInList(af.RG()));
		ll.add(new AdvantageInList(af.Sch()));
		ll.add(new AdvantageInList(af.STs()));
		ll.add(new AdvantageInList(af.SZ()));
		ll.add(new AdvantageInList(af.ÜAE()));
		ll.add(new AdvantageInList(af.WaS()));
		ll.add(new AdvantageInList(af.WSch()));
		ll.add(new AdvantageInList(af.zAbw()));
		
		return ll;
	}
	
	public static LinkedList<AdvantageInList> buyArtillaryAdvantages()
	{
		AdvantageFactory af = new AdvantageFactory();
		LinkedList<AdvantageInList> ll = new LinkedList<AdvantageInList>();
		
		ll.add(new AdvantageInList(af.AA()));
		ll.add(new AdvantageInList(af.An()));
		ll.add(new AdvantageInList(af.BGA()));
		ll.add(new AdvantageInList(af.Fäsch()));
		ll.add(new AdvantageInList(af.Gehen()));
		ll.add(new AdvantageInList(af.Laufen()));
		ll.add(new AdvantageInList(af.plusRW()));
		ll.add(new AdvantageInList(af.reit()));
		ll.add(new AdvantageInList(af.SchSch()));
		ll.add(new AdvantageInList(af.zSch()));		
		
		return ll;
	}
	
	public static final LinkedList<AdvantageInList> buyMagicAdvantages()
	{
		AdvantageFactory af = new AdvantageFactory();
		LinkedList<AdvantageInList> ll = new LinkedList<AdvantageInList>();
		
		ll.add(new AdvantageInList(af.Ast()));
		ll.add(new AdvantageInList(af.Blut()));
		ll.add(new AdvantageInList(af.CM()));
		ll.add(new AdvantageInList(af.DB()));
		ll.add(new AdvantageInList(af.Erz()));
		ll.add(new AdvantageInList(af.Ex()));
		ll.add(new AdvantageInList(af.Fet()));
		ll.add(new AdvantageInList(af.GF()));
		ll.add(new AdvantageInList(af.GB()));
		ll.add(new AdvantageInList(af.HT()));		
		ll.add(new AdvantageInList(af.Ket()));
		ll.add(new AdvantageInList(af.KS()));
		ll.add(new AdvantageInList(af.Int()));
		ll.add(new AdvantageInList(af.MD()));
		ll.add(new AdvantageInList(af.Med()));
		ll.add(new AdvantageInList(af.Men()));
		ll.add(new AdvantageInList(af.MK()));
		ll.add(new AdvantageInList(af.MM()));
		ll.add(new AdvantageInList(af.Pan()));
		ll.add(new AdvantageInList(af.Par()));
		ll.add(new AdvantageInList(af.Reg()));
		ll.add(new AdvantageInList(af.Rit()));
		ll.add(new AdvantageInList(af.SchZa()));
		ll.add(new AdvantageInList(af.Sich()));
		ll.add(new AdvantageInList(af.Stab()));
		ll.add(new AdvantageInList(af.Scho()));
		ll.add(new AdvantageInList(af.SV()));
		ll.add(new AdvantageInList(af.SWmag()));
		ll.add(new AdvantageInList(af.Tasch()));
		ll.add(new AdvantageInList(af.TB()));
		ll.add(new AdvantageInList(af.TE()));
		ll.add(new AdvantageInList(af.UZ()));
		ll.add(new AdvantageInList(af.Wa()));
		ll.add(new AdvantageInList(af.Wet()));
		ll.add(new AdvantageInList(af.WMmag()));
		ll.add(new AdvantageInList(af.Zei()));
		ll.add(new AdvantageInList(af.ZimK()));
		ll.add(new AdvantageInList(af.ZS()));
		ll.add(new AdvantageInList(af.ZT()));
		ll.add(new AdvantageInList(af.ZZ()));
		return ll;
	}
}
