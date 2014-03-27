package moonrise.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * 
 * @author Moonrise
 * 
 * Abstract class for item's inventory which contains other items 
 * ex) backpack
 * 
 * If you want to make an item which contains other items, make 2 classes for it : child of ItemContainer(this), InventoryItemContainer
 * Then each itemstack of your item will hold inventory in it's nbt data
 * Remember, Item class is singleton. 
 * All informations of inventory should be saved in the InventoryItemContainer class.  
 *
 */
public abstract class InventoryItemContainer implements IInventory {
	
	/**
	 * Inventory of your item
	 * You can simply use this, or you can add new field for your inventory
	 * This field will be saved automatically, but other fields may not
	 * If you want to use other field for your inventory, you must save manually that field's information by save/loadExtraData(NBTTagCompound) methode
	 */
	protected ItemStack[] items;
	private NBTTagCompound tag;
	
	public InventoryItemContainer(int size) {
		this.items = new ItemStack[size];
		this.tag = new NBTTagCompound();
	}
	
	/**
	 * Create new inventory from given itemstack
	 * 
	 * @param stack : Itemstack which contains this inventory
	 */
	public InventoryItemContainer(int size, ItemStack stack) {
		this(size);
		this.readFromNBT(stack.getTagCompound().getCompoundTag("inventory"));
	}
	
	public InventoryItemContainer readFromNBT(NBTTagCompound nbtTag) {
		this.items = new ItemStack[nbtTag.getInteger("size")];
		for (int i=0; i < items.length; i++) {
			items[i] = ItemStack.loadItemStackFromNBT(nbtTag.getCompoundTag("itemstack_" + i));
		}
		this.tag = nbtTag.getCompoundTag("tag");
		this.loadExtraData(tag);
		return this;
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbtTag) {
		nbtTag.setInteger("size", this.items.length);
		for (int i=0; i < items.length; i++) {
			if (items[i] != null)
				nbtTag.setCompoundTag("itemstack_" + i, items[i].writeToNBT(new NBTTagCompound()));
		}
		this.tag = this.saveExtraData(new NBTTagCompound());
		nbtTag.setCompoundTag("tag", tag);
		return nbtTag;
	}
	
	/**
	 * If you want to use another field for store your inventory's information, that field may not be saved automatically
	 * So you can use this method to save your extra data
	 * Also you should implements saveExtraData(NBTTgaCompound) method. 
	 * 
	 * @param tag 
	 */
	protected abstract void loadExtraData(NBTTagCompound tag);
	
	/**
	 * If you want to use anouther field for store your inventory's information, that field may not be loaded automatically
	 * So you can use this method to load your extra data
	 * Also you should implements loadExtraData(NBTTgaCompound) method. 
	 * 
	 * @param tag 
	 */
	protected abstract NBTTagCompound saveExtraData(NBTTagCompound tag);
	
	/**
	 * @return size of this inventory
	 */
	public int getSizeInventory() {
		return this.items.length;
	}

	/**
	 * @param numslot : index of slot
	 * @return itemstack in the given index of slot
	 */
	public ItemStack getStackInSlot(int numslot) {
		if (numslot >= items.length)
			return null;
		return items[numslot];
	}

	/**
	 * Decrease stack size of given index of slot
	 * 
	 * @param numslot : index of slot
	 * @param decrAmount : decreased amount
	 */
	public ItemStack decrStackSize(int numslot, int decrAmount) {
		ItemStack thisItem = this.items[numslot];
		thisItem.stackSize -= decrAmount;
		if (thisItem.stackSize <= 0) 
			thisItem = null;
		return thisItem;
	}

	/**
	 * You may need not to use this method
	 * Throw out returned itemstack to the world
	 * 
	 * @param numslot : index of slot
	 * @return itemstack will be throwed
	 */
	public ItemStack getStackInSlotOnClosing(int numslot) {
		return null;
	}

	public void setInventorySlotContents(int numslot, ItemStack itemstack) {
		this.items[numslot] = itemstack;
	}

	@Override
	public abstract String getInvName();

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	/**
	 * @return this inventory's max stack size. Can be different with item's max stack size
	 */
	public abstract int getInventoryStackLimit();

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
	public boolean isItemValidForSlot(int numslot, ItemStack itemstack) {
		return true;
	}

}
