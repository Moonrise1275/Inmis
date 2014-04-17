package moonrise.inmis.item;

import java.util.List;

import moonrise.inmis.Inmis;
import moonrise.inmis.player.InmisPlayer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemInventoryUpgrade extends Item {

	public ItemInventoryUpgrade(int id) {
		super(id);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World workd, EntityPlayer player) {
		InmisPlayer inmisplayer = Inmis.instance.ptracker.getInmisPlayer(player.username);
		int meta = stack.getItemDamage();
		
		switch (meta) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 10:
			if (inmisplayer != null && (inmisplayer.getSizeInventory()/9) < meta) {
				inmisplayer.upgrade((byte)meta);
				stack.stackSize = 0;
				return stack;
			}
			break;
			
		}
		
		return stack;
	}
	
	@Override
	public void getSubItems(int id, CreativeTabs tab, List list) {
		list.add(new ItemStack(id, 1, 1));
		list.add(new ItemStack(id, 1, 2));
		list.add(new ItemStack(id, 1, 3));
		list.add(new ItemStack(id, 1, 4));
		list.add(new ItemStack(id, 1, 5));
		list.add(new ItemStack(id, 1, 6));
		
	}

}
