package moonrise.inmis.gui;

import moonrise.inmis.item.InventoryInvHopper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerInvHopper extends Container {
	
	private InventoryInvHopper itemInventory;
	private InventoryPlayer playerInventory;
	
	public ContainerInvHopper(InventoryPlayer par1, InventoryInvHopper par2) {
		this.itemInventory = par2;
		this.playerInventory = par1;
		this.itemInventory.openChest();
		
		this.addSlotToContainer(new Slot(this.itemInventory, 0, 8 + 4*18, 18));
		
		for (int i=0; i<3; i++) {
			for (int j=0; j<9; j++) {
				this.addSlotToContainer(new Slot(this.playerInventory, j + i*9 + 9, 8 + j*18, 103 + i*18));
			}
		}
		for (int i=0; i<9; i++) {
			this.addSlotToContainer(new Slot(this.playerInventory, i, 8 + i*18, 161));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.itemInventory.isUseableByPlayer(player);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int numslot) {
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(numslot);
		
		if (slot != null && slot.getHasStack()); {
			ItemStack item = slot.getStack();
			itemstack = item.copy();
			
			if (numslot == 0) {
				if (!this.mergeItemStack(item, 1, this.inventorySlots.size(), true))
					return null;
			} else if (!this.mergeItemStack(item, 0, 1, false))
				return null;
			if (item.stackSize == 0)
				slot.putStack((ItemStack)null);
			else
				slot.onSlotChanged();
		}
		return itemstack;
	}
	
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		this.itemInventory.closeChest();
	}

}
