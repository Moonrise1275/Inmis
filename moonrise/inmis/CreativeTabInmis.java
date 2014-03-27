package moonrise.inmis;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabInmis extends CreativeTabs {
	
	int idInventoryHopper;

	public CreativeTabInmis(String label, int id) {
		super(label);
		this.idInventoryHopper = id;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack() {
		//return new ItemStack(idInventoryHopper, 1, 0);
		return new ItemStack(Item.eyeOfEnder);
	}

}
