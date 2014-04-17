package moonrise.inmis.player;

import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class InmisPlayer implements IInventory {
	
	public static final String PROP_NAME = "Inmis_Player";
	
	private final EntityPlayer player;
	
	private ItemStack[] inventory;
	
	public InmisPlayer(EntityPlayer player) {
		this.player = player;
		this.inventory = new ItemStack[0];
	}
	
	public void upgrade(byte type) {
		switch(type) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 10:
			if ((inventory.length/9) >= type)
				return;
			ItemStack[] newinv = new ItemStack[type*9];
			for (int ii=0; ii<inventory.length; ii++) {
				newinv[ii] = inventory[ii];
			}
			this.inventory = newinv;
			break;
		}
	}

	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound tag = compound.getCompoundTag(InmisPlayer.PROP_NAME);
		if (tag == null)
			tag = new NBTTagCompound();
		
		tag.setInteger("invLength", this.inventory.length);
		NBTTagList taglist1 = new NBTTagList();
		for (int ii=0; ii<this.inventory.length; ii++) {
			if (this.inventory[ii] != null) {
				NBTTagCompound tag1 = new NBTTagCompound();
				tag1.setInteger("numslot", ii);
				this.inventory[ii].writeToNBT(tag1);
				taglist1.appendTag(tag1);
			}
		}
		tag.setTag("inventory", taglist1);
		
		compound.setCompoundTag(InmisPlayer.PROP_NAME, tag);
	}

	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound tag = compound.getCompoundTag(InmisPlayer.PROP_NAME);
		
		this.inventory = new ItemStack[tag.getInteger("invLength")];
		NBTTagList taglist1 = tag.getTagList("inventory");
		for (int ii=0; ii<taglist1.tagCount(); ii++) {
			NBTTagCompound tag1 = (NBTTagCompound) taglist1.tagAt(ii);
			this.inventory[tag1.getInteger("numslot")] = ItemStack.loadItemStackFromNBT(tag1);
		}
	}
	
	//------------------------IInventory------------------------
	
	@Override
	public int getSizeInventory() {
		return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		if (i>=0 && i<this.inventory.length) {
			return this.inventory[i];
		}
		
		return null;
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (i>=0 && i<this.inventory.length) {
			if (this.inventory[i] == null || this.inventory[i].stackSize <= j)
				return null;
			this.inventory[i].splitStack(j);
			if (this.inventory[i].stackSize <= 0)
				this.inventory[i] = null;
			return this.inventory[i];
		}
		
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		
		if (this.inventory[i] != null) {
			ItemStack stack = this.inventory[i];
			this.inventory[i] = null;
			return stack;
		} else return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		if (i>=0 && i<this.inventory.length) {
			this.inventory[i] = itemstack;
			if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
				itemstack.stackSize = this.getInventoryStackLimit();
			}
		}
	}

	@Override
	public String getInvName() {
		return "inmis.player";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void onInventoryChanged() {
		
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {
		
	}

	@Override
	public void closeChest() {
		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

}
