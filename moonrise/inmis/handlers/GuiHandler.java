package moonrise.inmis.handlers;

import moonrise.inmis.Inmis;
import moonrise.inmis.gui.ContainerInvHopper;
import moonrise.inmis.gui.ContainerToolBelt;
import moonrise.inmis.gui.GuiInvHopper;
import moonrise.inmis.item.InventoryInvHopper;
import moonrise.inmis.item.InventoryToolBelt;
import moonrise.inmis.item.ItemInventoryHopper;
import moonrise.inmis.item.ItemToolBelt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		if (ID == Inmis.GUI_INVHOPPER) {
			ItemStack invHopper = player.getCurrentEquippedItem();
			if (!(invHopper.getItem() instanceof ItemInventoryHopper))
				return null;
			return new ContainerInvHopper(player.inventory, new InventoryInvHopper(invHopper));
		} 
		else if (ID == Inmis.GUI_TOOLBELT) {
			ItemStack toolBelt = player.getCurrentEquippedItem();
			if (!(toolBelt.getItem() instanceof ItemToolBelt))
				return null;
			return new ContainerToolBelt(player.inventory, new InventoryToolBelt(toolBelt));
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		if (ID == Inmis.GUI_INVHOPPER) {
			ItemStack invHopper = player.getCurrentEquippedItem();
			if (!((invHopper.getItem()) instanceof ItemInventoryHopper))
				return null;
			return new GuiInvHopper(player.inventory, new InventoryInvHopper(invHopper));
		}
		
		return null;
	}

}
