package moonrise.inmis;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;

public class CreativeTabInmis extends CreativeTabs {
	
	int idInventoryHopper;

	public CreativeTabInmis(String label, int id) {
		super(label);
		this.idInventoryHopper = id;
	}
	
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex() {
		return this.idInventoryHopper;
	}

}
