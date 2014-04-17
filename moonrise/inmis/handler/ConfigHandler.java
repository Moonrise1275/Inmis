package moonrise.inmis.handler;

import net.minecraftforge.common.Configuration;

public class ConfigHandler {
	
	private Configuration config;
	
	public boolean enableMiningUnifier = true;
	
	public String[] miningUnifierWhitelist = { "oreCoal", "oreCopper", "oreTin", "oreIron", "oreNickel", "oreGold", "oreRedstone", "oreDiamond", "oreEmerald", "oreRuby", "oreSaphire", "orePerridot" };
	
	public String itemInventoryUpgrade = "inmis.item.invupgrade";
	
	public int idItemInventoryUpgrade = 17301;
	
	public ConfigHandler(Configuration config) {
		this.config = config;
		
		this.enableMiningUnifier = config.get("Unifing", "enableMiningUnifier", this.enableMiningUnifier).getBoolean(this.enableMiningUnifier);
		this.miningUnifierWhitelist = config.get("Unifing", "miningUnifierWhitelist", this.miningUnifierWhitelist).getStringList();
		
		this.idItemInventoryUpgrade = config.getItem(itemInventoryUpgrade, this.idItemInventoryUpgrade).getInt();
	}
}
