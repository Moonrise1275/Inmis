package moonrise.inmis.handlers;

import net.minecraftforge.common.Configuration;

public class ConfigHandler {
	
	public static Configuration config;
	
	String categoryGeneral = "general";
	
	public String itemInventoryHopper = "InventoryHopper";
	
	public int idItemInventoryHopper = 23701;
	
	public int generalMaxUpgradeCountInventoryHopper = 64;
	public boolean generalEnablePlayerCheckerThread = true;
	
	public ConfigHandler(Configuration config) {
		this.config = config;
		
		
		this.idItemInventoryHopper = config.getItem(itemInventoryHopper, idItemInventoryHopper).getInt();
		
		this.generalMaxUpgradeCountInventoryHopper = config.get(categoryGeneral, "MaxUpgradeCountInventoryHopper", this.generalMaxUpgradeCountInventoryHopper).getInt();
		this.generalEnablePlayerCheckerThread = config.get(categoryGeneral, "EnablePlayerCheckerThread", this.generalEnablePlayerCheckerThread).getBoolean(this.generalEnablePlayerCheckerThread);
		
	}

}
