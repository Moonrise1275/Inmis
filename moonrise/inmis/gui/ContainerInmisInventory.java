package moonrise.inmis.gui;

import java.util.ArrayList;
import java.util.LinkedList;

import moonrise.inmis.player.InmisPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.IInventory;

public class ContainerInmisInventory extends Container {
	
	private InventoryPlayer inventoryplayer;
	private InmisPlayer inmisplayer;
	
	public ContainerInmisInventory(EntityPlayer player, InmisPlayer inmisp) {
		this.inventoryplayer = player.inventory;
		this.inmisplayer = inmisp;
		this.inmisplayer.openChest();
		
		int invUC = this.inmisplayer.getSizeInventory()/9;
		
		for (int i=0; i<9; i++) {
			this.addSlotToContainer(new Slot(this.inventoryplayer, i, 8 + i*18, 81 + invUC*18));
		}
		
		for (int i=0; i<3; i++) {
			for (int j=0; j<9; j++) {
				this.addSlotToContainer(new Slot(this.inventoryplayer, 9 + j + i*9, 8 + j*18, 23 + i*18 + invUC*18));
			}
		}
		
		for (int i=0; i<invUC; i++) {
			for (int j=0; j<9; j++) {
				this.addSlotToContainer(new Slot(this.inmisplayer, i*9 + j, 8 + j*18, 8 + i*18));
			}
		}
		
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		return null;//((Slot)(this.inventorySlots.get(i))).getStack();
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}
