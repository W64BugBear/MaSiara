package dataclasses;

import items.RangeWeaponType;

public class RangeWeaponTypeFactory {

	public static RangeWeaponType[] getRangeWeapons ()
	{
		RangeWeaponType[] ret = new RangeWeaponType[10];
		int i = 0;
		
		ret[i++] = new RangeWeaponType("Bogen (Gs*2+In+Sb+Sicht)/3", RangeWeaponType.BOWS);
		ret[i++] = new RangeWeaponType("Armbrust (Gs*2+In+Sb+Sicht)/3", RangeWeaponType.BOWS);
		ret[i++] = new RangeWeaponType("Wurfspeer (Gs+Gw+K+S+In)/3", RangeWeaponType.JAVELIN);
		ret[i++] = new RangeWeaponType("Wurfbeil (Gs*2+Gw+K+In)/3", RangeWeaponType.AXE);
		ret[i++] = new RangeWeaponType("Seil (Gs*2+Gw+K+In)/3", RangeWeaponType.ROPE);
		ret[i++] = new RangeWeaponType("Wurfmesser (Gs*3+In*2)/3", RangeWeaponType.KNIFE);
		ret[i++] = new RangeWeaponType("Schleuderwaffen (Gs*3+Gw+In)/3", RangeWeaponType.SLINGER);
		ret[i++] = new RangeWeaponType("Blasrohr (Gs*2+Iz+In+Sb)/3", RangeWeaponType.PIPE);
		ret[i++] = new RangeWeaponType("Geschütze (Gs+Iz*2+In+Sb)/3", RangeWeaponType.ARTILLERY);
		ret[i++] = new RangeWeaponType("Kampfmagie (Gs+Iz+In*2+Sb)/3", RangeWeaponType.MAGIC);
		
		return ret;
	}
}
