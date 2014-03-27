package moonrise.inmis.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import moonrise.inmis.Inmis;
import moonrise.util.InventoryItemContainer;
import moonrise.util.ItemContainer;

public class ItemToolBelt extends ItemContainer {

	public ItemToolBelt(int id) {
		super(id);
		this.setCreativeTab(Inmis.tabInmis);
		this.setMaxStackSize(1);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
		
		int currentSlotNum = player.inventory.currentItem;
		if (currentSlotNum <8) {
			ItemStack rightSlotItem = player.inventory.getStackInSlot(currentSlotNum +1);
			InventoryItemContainer itemInven = this.getInventory(item);
			//for check
			System.out.println("Check 2 : " + itemInven.getSizeInventory());
			ItemStack nextTool = itemInven.getStackInSlot(0);
			
			//for check
			System.out.println("Check 3 : " + itemInven.getSizeInventory());
			
			for (int i=1; i<itemInven.getSizeInventory(); i++)
				itemInven.setInventorySlotContents(i-1, itemInven.getStackInSlot(i));
			itemInven.setInventorySlotContents(itemInven.getSizeInventory()-1, rightSlotItem);
			player.inventory.setInventorySlotContents(currentSlotNum +1, nextTool);
		}
		
		return item;
	}

	@Override
	public InventoryItemContainer getInventory() {
		return new InventoryToolBelt();
	}

	@Override
	public Object getModInstance() {
		return Inmis.instance;
	}

	@Override
	public int getGuiId() {
		return Inmis.GUI_TOOLBELT;
	}
	
	@Override
	public void getSubItems(int id, CreativeTabs tab, List list) {
		ItemStack itemstack = new ItemStack(id, 1, 0);
		ItemContainer.createNBT(itemstack);
		list.add(itemstack);
	}

}
