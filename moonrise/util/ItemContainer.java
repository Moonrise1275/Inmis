package moonrise.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * 
 * @author Moonrise
 * 
 * Abstract class for item which contains other items 
 * ex) backpack
 * 
 * If you want to make an item which contains other items, make 2 classes for it : child of ItemContainer(this), InventoryItemContainer
 * Then each itemstack of your item will hold inventory in it's nbt data
 * Remember, Item class is singleton. 
 * All informations of inventory should be saved in the InventoryItemContainer class.  
 *
 */
public abstract class ItemContainer extends Item {
	
	public ItemContainer(int id) {
		super(id);
	}
	
	/**
	 * @return new instance of matching inventory class
	 */
	public abstract InventoryItemContainer getInventory();
	
	/**
	 * @return your mod's instance (below @instance annotation)
	 */
	public abstract Object getModInstance();
	
	/**
	 * You should declare your gui's ids first!
	 * 
	 * @return gui's id
	 */
	public abstract int getGuiId();
	
	/**
	 * Use this method if you need itemstack's inventory instance
	 * 
	 * @param stack : itemstack which contains inventory
	 * @return itemstack's inventory
	 */
	public InventoryItemContainer getInventory(ItemStack stack) {
		return this.getInventory().readFromNBT(createNBT(stack));
	}
	
	@Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer player) {
		createNBT(itemstack);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
		InventoryItemContainer inventory = getInventory().readFromNBT(createNBT(item));
		if (player.isSneaking()) {
			player.openGui(this.getModInstance(), this.getGuiId(), world, 0, 0, 0);
		}
		return item;
	}
	
	public static NBTTagCompound createNBT(ItemStack stack) {
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}
		if (stack.getTagCompound().getCompoundTag("inventory") == null) {
			stack.getTagCompound().setCompoundTag("inventory", new NBTTagCompound());
		}
		return stack.getTagCompound().getCompoundTag("inventory");
	}
	
}
