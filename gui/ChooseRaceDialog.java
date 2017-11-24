package gui;


import guidialogelements.BitmapComponent;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreeSelectionModel;

import races.Beastingers;
import races.Dwarves;
import races.EardonsHouse;
import races.Fairies;
import races.Falconites;
import races.Gnolls;
import races.Goblins;
import races.HalfElves;
import races.Halforcs;
import races.HighElves;
import races.KenthaniBarbarians;
import races.KenthaniSailors;
import races.Nurthanis;
import races.Ogres;
import races.Parders;
import races.Race;
import races.ShadowDragons;
import races.Skirks;
import races.Uthos;
import background.Constants;

public class ChooseRaceDialog 
	extends JDialog
implements ActionListener, KeyListener, TreeSelectionListener, MouseListener
{
	public static int TREEWIDTH = 200;
	public static int TREEHEIGHT = 300;
	
	public static int PICTUREHEIGHT = 100;
	
	public static int TEXTWIDTH = 200;
	public static int TEXTHEIGHT = TREEHEIGHT - Constants.OFFSETX*2 - PICTUREHEIGHT - Constants.HEIGHT;
	
	JTree tree;
	JScrollPane treePane, textPane;
	BitmapComponent bmp;
	JTextArea textArea;
	JButton button;
	MainWindow father;
	
	public ChooseRaceDialog(MainWindow owner)
	{
		super(owner, true);		// modal Dialog
		father = owner;
		setLayout(null);
				
		buildTree();
		treePane = new JScrollPane(tree);
		bmp = new BitmapComponent(this);
		textArea = new JTextArea(owner.getHero().getRace().getDesc());
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textPane = new JScrollPane(textArea);
		button = new JButton("wählen");
		
		bmp.setPicture(owner.getHero().getRace().getPictureStrBmp());
		
		button.addActionListener(this);
		button.addKeyListener(this);
		tree.addKeyListener(this);
		tree.addMouseListener(this);
		
		treePane.setBounds(Constants.OFFSETX, Constants.OFFSETY, TREEWIDTH, TREEHEIGHT);
		bmp.setBounds(Constants.OFFSETX + TREEWIDTH + Constants.SPACEX*2, Constants.OFFSETY, bmp.getWidth(), bmp.getHeight());
		textPane.setBounds(Constants.OFFSETX + TREEWIDTH + Constants.SPACEX*2, Constants.OFFSETY + PICTUREHEIGHT + Constants.OFFSETY, TEXTWIDTH, TEXTHEIGHT);
		button.setBounds(Constants.OFFSETX + TREEWIDTH + Constants.SPACEX*2, Constants.OFFSETY + PICTUREHEIGHT + Constants.OFFSETY*2 + TEXTHEIGHT, TEXTWIDTH, Constants.HEIGHT);
		
		add(treePane);
		add(bmp);
		add(textPane);
		add(button);
	}

	public void buildTree()
	{
		DefaultMutableTreeNode root, child, subchild, subsubschild;
		root = new DefaultMutableTreeNode("");
		
		child = new DefaultMutableTreeNode("Humanoide");
		root.add(child);
		{
			subchild = new DefaultMutableTreeNode("Dúradram");
			child.add(subchild);
			{
				subsubschild = new DefaultMutableTreeNode("Eardons Haus");
				subchild.add(subsubschild);
				subsubschild = new DefaultMutableTreeNode("Nur'thanis");
				subchild.add(subsubschild);
				subsubschild = new DefaultMutableTreeNode("Sun'thanis");
				subchild.add(subsubschild);

			//	subsubschild = new DefaultMutableTreeNode("Aesa'thanis");
			//	subchild.add(subsubschild);
				subsubschild = new DefaultMutableTreeNode("Ken'thanis (Seefahrer)");
				subchild.add(subsubschild);
				subsubschild = new DefaultMutableTreeNode("Ken'thanis (Barbaren)");
				subchild.add(subsubschild);
				subsubschild = new DefaultMutableTreeNode("Uthos");
				subchild.add(subsubschild);
			}
			
			subchild = new DefaultMutableTreeNode("Elfen");
			child.add(subchild);
			{
				subsubschild = new DefaultMutableTreeNode("Hochelfen");
				subchild.add(subsubschild);
				subsubschild = new DefaultMutableTreeNode("Halbelfen");
				subchild.add(subsubschild);
				
			}
			
			subchild = new DefaultMutableTreeNode("Zwerge");
			child.add(subchild);
			
			subchild = new DefaultMutableTreeNode("Feen");
			child.add(subchild);
		}
		
		child = new DefaultMutableTreeNode("Goblinoide");
		root.add(child);
		{
			subchild = new DefaultMutableTreeNode("Goblins");
			child.add(subchild);
			subchild = new DefaultMutableTreeNode("Halborken");
			child.add(subchild);
			subchild = new DefaultMutableTreeNode("Oger");
			child.add(subchild);
		}
		
		child = new DefaultMutableTreeNode("Hybride");
		root.add(child);
		{
			subchild = new DefaultMutableTreeNode("Biestinger");
			child.add(subchild);
			subchild = new DefaultMutableTreeNode("Falkoniter");
			child.add(subchild);
			subchild = new DefaultMutableTreeNode("Gnolle");
			child.add(subchild);
			subchild = new DefaultMutableTreeNode("Parder");
			child.add(subchild);
			subchild = new DefaultMutableTreeNode("Skirks");
			child.add(subchild);
		}
		
		child = new DefaultMutableTreeNode("Titanoide");
		root.add(child);
		
		child = new DefaultMutableTreeNode("Phantastoide");
		root.add(child);
		{
			subchild = new DefaultMutableTreeNode("Schattendrachen");
			child.add(subchild);
		}
		tree = new JTree (root);
		tree.setRootVisible(false);
		DefaultTreeSelectionModel def = new DefaultTreeSelectionModel();
		def.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setSelectionModel(def);
		tree.addTreeSelectionListener(this);
		
		tree.setVisible(true);
	}
	
	// this is NOT JComponent.getPreferredSize()!!!
	public Dimension getPreferredSize()
	{
		return new Dimension(button.getX()+TEXTWIDTH+Constants.SPACEX*3, 
				button.getY()+Constants.HEIGHT+Constants.SPACEY*3+45);
		// i think the 45 are the title
	}
	
	public Race getRace()
	{		
		try {
			String race = tree.getSelectionPath().getLastPathComponent().toString();
			Race ret = Race.getRaceByName(race);
			if (ret != null)
				return ret;
		} catch (NullPointerException npe)
		{
			return father.getHero().getRace();
		}
		
		return father.getHero().getRace();
	}

	
	public void actionPerformed(ActionEvent ae)
	{
		dispose();
	}
	
	public void keyTyped(KeyEvent ke){/*nothing*/}
	
	public void keyPressed(KeyEvent ke)
	{
		if (ke.getKeyCode() == ke.VK_ENTER)
			dispose();
	}
	
	public void keyReleased(KeyEvent ke){/*nothing*/}

	public void valueChanged(TreeSelectionEvent tse)
	{
		Race race = getRace();
		if (race == null)
			return;
		bmp.setPicture(race.getPictureStrBmp());
		textArea.setText(race.getDesc());
		textArea.setCaretPosition(0);
		repaint();
		setVisible(true);
	}
	
	public void mouseClicked (MouseEvent me) {
		if (me.getClickCount() > 1)
			dispose();
	}
	
	public void mouseEntered (MouseEvent me) {}
	public void mouseExited (MouseEvent me) {}
	public void mousePressed (MouseEvent me) {}
	public void mouseReleased (MouseEvent me) {}
}
