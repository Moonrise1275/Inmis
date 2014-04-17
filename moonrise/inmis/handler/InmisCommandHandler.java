package moonrise.inmis.handler;

import java.util.Map;

import moonrise.inmis.player.InmisPlayer;
import moonrise.inmis.player.InmisPlayerTracker;
import moonrise.inmis.util.Util;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class InmisCommandHandler extends CommandBase {
	
	InmisPlayerTracker ptracker;
	
	public InmisCommandHandler(InmisPlayerTracker ptracker) {
		this.ptracker = ptracker;
	}

	@Override
	public String getCommandName() {
		return "inmis";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "type '/inmis help' for more info";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] astring) {
		
	}

}
