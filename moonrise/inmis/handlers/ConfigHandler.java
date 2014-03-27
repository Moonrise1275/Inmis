package moonrise.inmis.handlers;

import net.minecraftforge.common.Configuration;

public class ConfigHandler {
	
	public static Configuration config;
	
	String categoryGeneral = "general";
	
	public String itemInventoryHopper = "InventoryHopper";
	public String itemToolBelt = "ToolBelt";
	
	public int idItemInventoryHopper = 23701;
	public int idItemToolBelt = 23702;
	
	public String blockTinyChest = "TinyChest";
	
	public int idBlockTinyChest = 1501;
	
	public int generalMaxUpgradeCountInventoryHopper = 64;
	public boolean generalEnablePlayerCheckerThread = true;
	
	public ConfigHandler(Configuration config) {
		this.config = config;
		
		
		this.idItemInventoryHopper = config.getItem(itemInventoryHopper, idItemInventoryHopper).getInt();
		this.idItemToolBelt = config.getItem(itemToolBelt, idItemToolBelt).getInt();
		
		this.idBlockTinyChest = config.getBlock(blockTinyChest, idBlockTinyChest).getInt();
		
		this.generalMaxUpgradeCountInventoryHopper = config.get(categoryGeneral, "MaxUpgradeCountInventoryHopper", this.generalMaxUpgradeCountInventoryHopper).getInt();
		this.generalEnablePlayerCheckerThread = config.get(categoryGeneral, "EnablePlayerCheckerThread", this.generalEnablePlayerCheckerThread).getBoolean(this.generalEnablePlayerCheckerThread);
		
	}

}
