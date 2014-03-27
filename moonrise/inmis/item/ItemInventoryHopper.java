package moonrise.inmis.item;

import java.util.List;

import moonrise.inmis.Inmis;
import moonrise.util.InventoryItemContainer;
import moonrise.util.ItemContainer;
import moonrise.util.Util;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemInventoryHopper extends ItemContainer {
	
	final int maxUpgrade;

	public ItemInventoryHopper(int id, int maxUpgrade) {
		super(id);
		this.maxUpgrade = maxUpgrade;
		this.setCreativeTab(Inmis.tabInmis);
		this.setMaxStackSize(1);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
		super.onItemRightClick(item, world, player);
		
		if (!player.isSneaking()) {
			
		}
		
		return item;
	}
	
	@Override
	public InventoryItemContainer getInventory() {
		return new InventoryInvHopper();
	}

	@Override
	public Object getModInstance() {
		return Inmis.instance;
	}

	@Override
	public int getGuiId() {
		return Inmis.GUI_INVHOPPER;
	}
	
	@Override
	public void getSubItems(int id, CreativeTabs tab, List list) {
		ItemStack itemstack = new ItemStack(id, 1, 0);
		ItemContainer.createNBT(itemstack);
		list.add(itemstack);
	}

}
