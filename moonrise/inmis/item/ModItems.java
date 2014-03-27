package moonrise.inmis.item;

import cpw.mods.fml.common.registry.GameRegistry;
import moonrise.inmis.handlers.ConfigHandler;
import net.minecraft.item.Item;

public class ModItems {
	
	/**
	 * Configuration hander of this instance
	 * all configurable values are contained in here
	 */
	ConfigHandler config;
	
	/**
	 * Items
	 */
	Item itemInvHopper;
	Item itemToolBelt;
	
	/**
	 * 
	 */
	public ModItems(ConfigHandler config) {
		this.config = config;
		
		//itemInvHopper = new ItemInventoryHopper(config.idItemInventoryHopper, config.generalMaxUpgradeCountInventoryHopper);
		//GameRegistry.registerItem(itemInvHopper, config.itemInventoryHopper);
		itemToolBelt = new ItemToolBelt(config.idItemToolBelt);
		GameRegistry.registerItem(itemToolBelt, config.itemToolBelt);
	}
	
	public void registerRecipes() {
		
	}

}
