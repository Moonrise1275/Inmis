package moonrise.inmis.handler;

import java.util.Map;

import moonrise.inmis.Inmis;
import moonrise.inmis.gui.ContainerInmisInventory;
import moonrise.inmis.gui.GuiInmisInventory;
import moonrise.inmis.player.InmisPlayer;
import moonrise.inmis.player.InmisPlayerTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	InmisPlayerTracker ptracker;
	
	public GuiHandler(InmisPlayerTracker ptracker) {
		this.ptracker = ptracker;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		switch(ID) {
		case Inmis.GUI_INMIS_INVENTORY:
			return new ContainerInmisInventory(player, ptracker.getInmisPlayer(player.username));
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		switch(ID) {
		case Inmis.GUI_INMIS_INVENTORY:
			return new GuiInmisInventory(player, ptracker.getInmisPlayer(player.username));
		}
		
		return null;
	}

}
