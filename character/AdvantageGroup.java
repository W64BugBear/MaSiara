package character;

import java.util.Iterator;
import java.util.LinkedList;

public class AdvantageGroup 
	extends AdvantageFamily
{
	LinkedList<Advantage> commonpath, path1, path2, path3, path4;
	
	public AdvantageGroup() {
		commonpath = new LinkedList<Advantage>();
		path1 = new LinkedList<Advantage>();
		path2 = new LinkedList<Advantage>();
		path3 = new LinkedList<Advantage>();
		path4 = new LinkedList<Advantage>();
	}

	/**
	 * the return value is the parameter for setSelectedIndex of the according comboBox
	 * @param path which path will be active
	 * @param position which position is active right now
	 * @return
	 */
	public int setActivePath(int path, int position)
	{
		if (getActivePath(position) == path)
			return position;	// already the correct path is chosen - do not change anything
		switch (path) {
		case 1:	// it's not commonpath, but path one. Hence, position 0 is the first field
		case 3: return 0;	// 3 is ... path 1 with something special. I don't know, but it works the same way
		case 2: return path1.size();  // if there are 2 elements, index 2 is the first element of path 2
		case 4: return path3.size(); // same here. or so.
		}
		return position;
	}
	
	/**
	 * which path is active (0 = commonpath)
	 * @param position the selected index of the given combobox
	 * @return
	 */
	public int getActivePath(int position)
	{
		if (!commonpath.isEmpty())
			return 0;
		int runner = path1.size();
		if (runner > position)
			return 1;
		runner += path2.size();
		if (runner > position)
			return 2;
		runner += path3.size();
		if (runner > position)
			return 3;
		return 4;
	}
	
	public void add(int pathno, Advantage advantage)
	{
		switch (pathno) {
		case 0: commonpath.add(advantage); break;
		case 1: path1.add(advantage); break;
		case 2: path2.add(advantage); break;
		case 3: path3.add(advantage); break;
		case 4: path4.add(advantage); break;
		}
		
		return;
	}

	public LinkedList<Advantage> getPath1() {
		return path1;
	}

	public LinkedList<Advantage> getPath2() {
		return path2;
	}

	public LinkedList<Advantage> getCommonpath() {
		return commonpath;
	}

	public void setCommonpath(LinkedList<Advantage> path) {
		this.commonpath = path;
	}
	
	public void setPath1(LinkedList<Advantage> path1) {
		this.path1 = path1;
	}

	public void setPath2(LinkedList<Advantage> path2) {
		this.path2 = path2;
	}

	public String toString()
	{
		String ret = "";
		if (!commonpath.isEmpty())
		{
			Iterator<Advantage> it = commonpath.iterator();
			while (it.hasNext())
			{
				ret += it.next();
				if (it.hasNext())
					ret += " / ";
			}
			return ret;
		}
		
		Iterator<Advantage> it = path1.iterator();
		while (it.hasNext())
		{
			ret += it.next();
			if (it.hasNext())
				ret += " / ";
		}
		
		ret += " | ";
		
		it = path2.iterator();
		while (it.hasNext())
		{
			ret += it.next();
			if (it.hasNext())
				ret += "/";
		}
		
		if (!path3.isEmpty())
		{
			it = path3.iterator();
			while (it.hasNext())
			{
				ret += it.next();
				if (it.hasNext())
					ret += " / ";
			}
			
			ret += " | ";
			
			it = path4.iterator();
			while (it.hasNext())
			{
				ret += it.next();
				if (it.hasNext())
					ret += "/";
			}
		}
		
		return ret;
	}

	public LinkedList<Advantage> getPath3() {
		return path3;
	}

	public LinkedList<Advantage> getPath4() {
		return path4;
	}

	public void setPath3(LinkedList<Advantage> path3) {
		this.path3 = path3;
	}

	public void setPath4(LinkedList<Advantage> path4) {
		this.path4 = path4;
	}
}
