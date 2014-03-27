package moonrise.inmis.item;

import moonrise.util.InventoryItemContainer;
import moonrise.util.Util;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class InventoryInvHopper extends InventoryItemContainer {
	
	private ItemStack filter;
	private int amount;
	
	public InventoryInvHopper() {
		super(1);
	}
	
	public InventoryInvHopper(ItemStack stack) {
		super(1, stack);
	}
	
	@Override
	public ItemStack getStackInSlot(int slot) {
		if (filter == null)
			return null;
		ItemStack result = filter.copy();
		result.stackSize = amount > filter.getMaxStackSize() ? filter.getMaxStackSize() : amount;
		return result;
	}

	@Override
	public ItemStack decrStackSize(int slot, int decrAmount) {
		this.amount -= decrAmount;
		return getStackInSlot(0);
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack newItem) {
		if (this.filter == null && newItem != null) {
			int newAmount = newItem.stackSize;
			newItem.stackSize = 0;
			this.filter = newItem;
			this.amount = newAmount;
		}
	}

	@Override
	public String getInvName() {
		return "Inventory Hopper";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		if (filter == null)
			return 64;
		return filter.getMaxStackSize();
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if (filter == null)
			return true;
		else return Util.compare(filter, itemstack);
	}

	@Override
	protected void loadExtraData(NBTTagCompound tag) {
		this.filter = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("filter"));
		this.amount = tag.getInteger("amount");
	}

	@Override
	protected NBTTagCompound saveExtraData(NBTTagCompound tag) {
		if (filter != null)
			tag.setCompoundTag("filter", filter.writeToNBT(new NBTTagCompound()));
		else
			tag.setCompoundTag("filter", new NBTTagCompound());
		tag.setInteger("amount", amount);
		return tag;
	}

}
