package moonrise.inmis.item;

import moonrise.inmis.Inmis;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemInventoryHopper extends Item {
	
	int upgradeCount = 0;
	int maxUpgradeCount;

	public ItemInventoryHopper(int par1, int maxUpgrade) {
		super(par1);
		setCreativeTab(Inmis.tabInmis);
		setMaxStackSize(1);
		setHasSubtypes(true);
		
		this.maxUpgradeCount = maxUpgrade;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
		EntityPlayer player = (EntityPlayer)entity;
		
		
		
	}

}
