package background;

import java.io.Serializable;

import character.Hero;
import character.Property;

public class SerializableInformation 
	implements Serializable
{
	public static final int ARCHMAGIC_NONE = 0, 
							ARCHMAGIC_UNUSED = 1, 
							ARCHMAGIC_USED = 2;
	
	int version = Constants.FILEVERSION;
	
	boolean masterMode = false;
	boolean languageArea = true;	// determines whether language and font skills are 7 or 4 ep
	
	int epAmountInt;
	int epInt;
	
	int limitationsLeftInt = 35;
	
	int gpInvestedInSpecials = 0;
	int gpInvestedInMoney = 0;
	
	boolean cheapPriceForAdvantages = false;	// e.g. Dam Res (1.5) costs 1.5*35 if true, and 70 if false
	
	int freeArchmagicField = ARCHMAGIC_NONE;

	public int getFreeArchmagicField() {
		return freeArchmagicField;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setFreeArchmagicField(int freeArchmagicField) {
		this.freeArchmagicField = freeArchmagicField;
	}

	public boolean isCheapPriceForAdvantages() {
		return cheapPriceForAdvantages;
	}

	public void setCheapPriceForAdvantages(boolean cheapPriceForAdvantages) {
		this.cheapPriceForAdvantages = cheapPriceForAdvantages;
	}

	public SerializableInformation()
	{
		
	}

	public void increaseEP(int ep)
	{
		if (masterMode)
			return;
		
		epInt += ep;
	}
	
	public int getEpAmountInt() {
		return epAmountInt;
	}

	public void setEpAmountInt(int epAmountInt) {
		this.epAmountInt = epAmountInt;
	}

	public int getEpInt() {
		return epInt;
	}

	public void setEpInt(int epInt) {
		this.epInt = epInt;
	}

	public int getLimitationsLeftInt() {
		return limitationsLeftInt;
	}

	public void setLimitationsLeftInt(int limitationsLeftInt) {
		this.limitationsLeftInt = limitationsLeftInt;
	}

	public int getGpInvestedInSpecials() {
		return gpInvestedInSpecials;
	}

	public void setGpInvestedInSpecials(int gpInvestedInSpecials) {
		this.gpInvestedInSpecials = gpInvestedInSpecials;
	}
	
	public int getGpInvestedInMoney() {
		return gpInvestedInMoney;
	}

	public void setGpInvestedInMoney(int gpInvestedInMoney) {
		this.gpInvestedInMoney = gpInvestedInMoney;
	}

	public boolean isMasterMode() {
		return masterMode;
	}

	public void setMasterMode(boolean masterMode) {
		this.masterMode = masterMode;
	}

	public boolean isLanguageArea() {
		return languageArea;
	}

	public void setLanguageArea(boolean languageArea) {
		this.languageArea = languageArea;
	}
}
