package moonrise.inmis.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import moonrise.inmis.handler.ConfigHandler;

public class ModItems {
	
	ConfigHandler config;
	
	Item itemInventoryUpgrade;
	
	public ModItems(ConfigHandler config) {
		this.config = config;
		
		this.itemInventoryUpgrade = new ItemInventoryUpgrade(config.idItemInventoryUpgrade);
		GameRegistry.registerItem(itemInventoryUpgrade, config.itemInventoryUpgrade);
	}
	
	public void registerRecipes() {
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(this.itemInventoryUpgrade, 1, 1), "LSL", "LCL", "wWw", 'L', Item.leather, 'S', Item.silk, 'C', Block.chest, 'W', "logWood", 'w', "plankWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(this.itemInventoryUpgrade, 1, 2), "LSL", "LCL", "pPp", 'L', Item.leather, 'S', Item.silk, 'C', Block.chest, 'P', "blockCopper", 'p', "ingotCopper"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(this.itemInventoryUpgrade, 1, 3), "LSL", "LCL", "iIi", 'L', Item.leather, 'S', Item.silk, 'C', Block.chest, 'I', Block.blockIron, 'i', Item.ingotIron));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(this.itemInventoryUpgrade, 1, 4), "LSL", "LCL", "sSs", 'L', Item.leather, 'S', Item.silk, 'C', Block.chest, 'S', "blockSilver", 's', "ingotSilver"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(this.itemInventoryUpgrade, 1, 5), "LSL", "LCL", "gGg", 'L', Item.leather, 'S', Item.silk, 'C', Block.chest, 'G', Block.blockGold, 'g', Item.ingotGold));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(this.itemInventoryUpgrade, 1, 6), "LSL", "LCL", "dDd", 'L', Item.leather, 'S', Item.silk, 'C', Block.chest, 'D', Block.blockDiamond, 'd', Item.diamond));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(this.itemInventoryUpgrade, 1, 10), "LSL", "LCL", "dDd", 'L', Block.obsidian, 'S', Item.enderPearl, 'C', Block.chest, 'D', Block.dragonEgg, 'd', Block.beacon));
		
	}

}
