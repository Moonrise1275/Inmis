package moonrise.inmis.gui;

import moonrise.inmis.item.InventoryToolBelt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerToolBelt extends Container {
	
	private InventoryPlayer playerInventory;
	private InventoryToolBelt itemInventory;
	
	public ContainerToolBelt(InventoryPlayer inv1, InventoryToolBelt inv2) {
		this.playerInventory = inv1;
		this.itemInventory = inv2;
		this.itemInventory.openChest();
		
		//Draw item's inventory
		for (int i=0; i<itemInventory.getSizeInventory(); i++)
			this.addSlotToContainer(new Slot(this.itemInventory, i, 35 + i*18, 18));
		//Draw player's non-quickslot inventory
		for (int i=0; i<3; i++) {
			for (int j=0; i<9; j++) {
				this.addSlotToContainer(new Slot(this.playerInventory, j + i*9 + 9, 8 + j*18, 49 + i*18));
			}
		}
		//Draw player's quickslot inventory
		for (int i=0; i<9; i++)
			this.addSlotToContainer(new Slot(this.playerInventory, i, 8 + i*18, 107));
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int numslot) {
		
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(numslot);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack stack = slot.getStack();
			itemstack = stack.copy();
			
			if (numslot < this.itemInventory.getSizeInventory()) {
				if (!this.mergeItemStack(stack, this.itemInventory.getSizeInventory(), this.inventorySlots.size(), true))
					return null;
			}
			else if (!this.mergeItemStack(stack, 0, this.itemInventory.getSizeInventory(), false))
				return null;
			if (stack.stackSize == 0)
				slot.putStack(null);
			else
				slot.onSlotChanged();
		}
		return itemstack;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		this.itemInventory.closeChest();
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}
