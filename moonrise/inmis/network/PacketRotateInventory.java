package moonrise.inmis.network;

import moonrise.inmis.Inmis;
import moonrise.inmis.player.InmisPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class PacketRotateInventory extends PacketInmis {
	
	byte numslot;
	boolean goDown;
	
	public PacketRotateInventory(byte numslot, boolean goDown) {
		this.numslot = numslot;
		this.goDown = goDown;
	}
	
	public PacketRotateInventory() {}

	@Override
	public void read(ByteArrayDataInput in) {
		this.numslot = in.readByte();
		this.goDown = in.readBoolean();
	}

	@Override
	public void write(ByteArrayDataOutput out) {
		out.writeByte(numslot);
		out.writeBoolean(goDown);
	}

	@Override
	public void excute(EntityPlayer player, Side side) {
		if (side != Side.SERVER)
			return;
		
		InventoryPlayer inventory = player.inventory;

		rotateInventory(player, inventory, numslot);
		
	}
	
	private void rotateInventory(EntityPlayer player, InventoryPlayer inventory, int i) {
		
		InmisPlayer inmisplayer = Inmis.instance.ptracker.getInmisPlayer(player.username);
		int inventoryUC = inmisplayer.getSizeInventory() /9;
		if (inventoryUC > 0) {
			if (this.goDown) {
				ItemStack buffer = inventory.getStackInSlot(i);
				
				inventory.setInventorySlotContents(i, inventory.getStackInSlot(i+27));
				inventory.setInventorySlotContents(i+27, inventory.getStackInSlot(i+18));
				inventory.setInventorySlotContents(i+18, inventory.getStackInSlot(i+9));
				
				inventory.setInventorySlotContents(i+9, inmisplayer.getStackInSlot(i + 9*(inventoryUC -1)));
				
				for (int ii=inventoryUC -1; ii>=1; ii--) {
					inmisplayer.setInventorySlotContents(ii*9 +i, inmisplayer.getStackInSlot((ii-1)*9 +i));
				}
				
				inmisplayer.setInventorySlotContents(i, buffer);
				
			} else {
				ItemStack buffer = inmisplayer.getStackInSlot(i);
				
				for (int ii=0; ii<inventoryUC -2; ii++) {
					inmisplayer.setInventorySlotContents(ii*9 +i, inmisplayer.getStackInSlot((ii+1)*9 +i));
				}
				
				inmisplayer.setInventorySlotContents(9*(inventoryUC-1) +i, inventory.getStackInSlot(i+9));
				
				inventory.setInventorySlotContents(i+9, inventory.getStackInSlot(i+18));
				inventory.setInventorySlotContents(i+18, inventory.getStackInSlot(i+27));
				inventory.setInventorySlotContents(i+27, inventory.getStackInSlot(i));
				
				inventory.setInventorySlotContents(i, buffer);
				}
		} else {
			if (this.goDown) {
				ItemStack buffer = inventory.getStackInSlot(i);
				
				inventory.setInventorySlotContents(i, inventory.getStackInSlot(i+27));
				inventory.setInventorySlotContents(i+27, inventory.getStackInSlot(i+18));
				inventory.setInventorySlotContents(i+18, inventory.getStackInSlot(i+9));
				
				inventory.setInventorySlotContents(i+9, buffer);
				
			} else {
				ItemStack buffer = inventory.getStackInSlot(i+9);
				
				inventory.setInventorySlotContents(i+9, inventory.getStackInSlot(i+18));
				inventory.setInventorySlotContents(i+18, inventory.getStackInSlot(i+27));
				inventory.setInventorySlotContents(i+27, inventory.getStackInSlot(i));
				
				inventory.setInventorySlotContents(i, buffer);
			}
		}
	}

}
