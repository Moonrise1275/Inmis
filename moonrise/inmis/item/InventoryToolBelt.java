package moonrise.inmis.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import moonrise.util.InventoryItemContainer;

public class InventoryToolBelt extends InventoryItemContainer {

	public InventoryToolBelt() {
		super(6);
		
	}
	
	public InventoryToolBelt(ItemStack stack) {
		super(6, stack);
	}

	@Override
	protected void loadExtraData(NBTTagCompound tag) {
		
	}

	@Override
	protected NBTTagCompound saveExtraData(NBTTagCompound tag) {
		return tag;
	}

	@Override
	public String getInvName() {
		return "Tool Belt";
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isItemValidForSlot(int numslot, ItemStack itemstack) {
		return itemstack.getMaxStackSize() == 1;
	}

}
