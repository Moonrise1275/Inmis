package moonrise.util;

import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityCommandBlock;

public abstract class AdvCommandBase implements ICommand {

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

	@Override
	public abstract String getCommandName();

	@Override
	public abstract String getCommandUsage(ICommandSender icommandsender);

	@Override
	public List getCommandAliases() {
		return null;
	}

	@Override
	public abstract boolean canCommandSenderUseCommand(ICommandSender icommandsender);

	@Override
	public List addTabCompletionOptions(ICommandSender icommandsender, String[] astring) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] astring, int i) {
		return false;
	}
	
	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring)
	{
	if(icommandsender instanceof EntityPlayer) // If the sender is a player
	{
	processPlayer((EntityPlayer) icommandsender, astring); // Cast the sender into an EntityPlayer then call the ProcessPlayer method
	}
	else if(icommandsender instanceof TileEntityCommandBlock) // If the sender is a commandblock
	{
	processCommandBlock((TileEntityCommandBlock) icommandsender, astring);
	}
	else // If it's the Server console
	{
	processServerConsole(icommandsender, astring);
	}
	}
	
	public void processPlayer(EntityPlayer player, String[] params) {}
	public void processCommandBlock(TileEntityCommandBlock commandBlock, String[] params){}
	public void processServerConsole(ICommandSender console, String[] params){}
}
