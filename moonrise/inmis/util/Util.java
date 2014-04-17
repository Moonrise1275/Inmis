package moonrise.inmis.util;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EnumChatFormatting;

public class Util {
	
	public static boolean isOp(ICommandSender sender) {
		return MinecraftServer.getServerConfigurationManager(MinecraftServer.getServer()).getOps().contains(sender) || !(sender instanceof EntityPlayer);
	}
	
	public static EntityPlayer getPlayer(String username) {
		if (username == null)
			return null;
		return MinecraftServer.getServerConfigurationManager(MinecraftServer.getServer()).getPlayerForUsername(username);
	}
	
	public static ChatMessageComponent newCMC(String message) {
		return new ChatMessageComponent().setColor(EnumChatFormatting.AQUA).addText(message);
	}
	
	public static void broadcastMessage(String[] messages) {
		ChatMessageComponent chat = new ChatMessageComponent().setColor(EnumChatFormatting.LIGHT_PURPLE);
		for (String message : messages)
			chat.addText(message);
		for (String username : MinecraftServer.getServer().getAllUsernames())
			getPlayer(username).sendChatToPlayer(chat);
	}
	
	public static boolean compare(Object obj1, Object obj2) {
		if (obj1 instanceof ItemStack && obj2 instanceof ItemStack) {
			ItemStack stk1 = (ItemStack)obj1, stk2 = (ItemStack)obj2;
			return stk1.itemID==stk2.itemID && stk1.getItemDamage()==stk2.getItemDamage() && !stk1.hasTagCompound() && !stk2.hasTagCompound();
		}
		
		return false;
	}

}