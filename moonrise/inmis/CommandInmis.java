package moonrise.inmis;

import java.util.Iterator;
import java.util.TreeSet;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;
import moonrise.util.AdvCommandBase;
import moonrise.util.Util;

public class CommandInmis extends AdvCommandBase {
	
	PlayerCheckerThread thread;
	
	String[] chatHelp;
	String[] chatInfoFormat;
	
	public CommandInmis(PlayerCheckerThread thread) {
		this.thread = thread;
		
		String[] msgHelp = {"[Inmis] Inmis commands",
				"/inmis help | This one. Isn't it useful?",
				"/inmis rankPlayTime | Show ranking of 10 players with longest playtimes",
				"/inmis printPlayerInfo | Only op can use it. Save all player's info. See InmisPlayerInfo.txt in your minecraft folder" };
		String[] msgInfoFormat = { "[Inmis] All player's info are saved in InmisPlayerInfo.txt",
				"txt file contains various informations same as below",
				"Notch   :   Username",
				"674.15:52:33   :   Total play time after Inmis installed. format is day.hour:minuet:second",
				"2014.03.21_11:33:44   :  Last joined date/time in server. format is year.mounth.day_hour:minuet:second" };
		this.chatHelp = msgHelp;
		this.chatInfoFormat = msgInfoFormat;
	}
	
	enum Commands {
		help, rankPlayTime, printPlayerInfo
	}
	
	@Override
	public void processCommand(ICommandSender sender, String[] params) {
		if (params.length == 0 || Commands.valueOf(params[0]) == null) {
			sender.sendChatToPlayer(Util.newCMC("[Inmis] Invalid command! Use /Inmis help"));
		}
		
		super.processCommand(sender, params);
	}
	
	@Override
	public void processPlayer(EntityPlayer player, String[] params) {
		
		switch(Commands.valueOf(params[0])) {
		case help:
			for (String message : chatHelp)
				player.sendChatToPlayer(Util.newCMC(message));
			return;
		case rankPlayTime:
			int tab;
			if (params.length >= 2)
				tab = new Integer(params[1]);
			else tab = 1;
			player.sendChatToPlayer(Util.newCMC("----------Ranking----------"));
			for (String rank : showRanking(new TreeSet(this.thread.mapPlayer.values()), tab))
				player.sendChatToPlayer(Util.newCMC(rank));
			player.sendChatToPlayer(Util.newCMC("------------End------------"));
			return;
		case printPlayerInfo:
			if (!Util.isOp(player)) {
				player.sendChatToPlayer(Util.newCMC("[Inmis] You cannot use this command!"));
				return;
			} else {
				this.thread.printPlayerInfo();
				for (String message : chatInfoFormat)
					player.sendChatToPlayer(Util.newCMC(message));
				return;
			}
		}
	}
	
	@Override
	public void processServerConsole(ICommandSender console, String[] params) {
		
		switch(Commands.valueOf(params[0])) {
		case help:
			for (String message : chatHelp)
				console.sendChatToPlayer(Util.newCMC(message));
			return;
		case rankPlayTime:
			int tab;
			if (params.length >= 2)
				tab = new Integer(params[1]);
			else tab = 1;
			console.sendChatToPlayer(Util.newCMC("----------Ranking----------"));
			for (String rank : showRanking(new TreeSet(this.thread.mapPlayer.values()), tab))
				console.sendChatToPlayer(Util.newCMC(rank));
			console.sendChatToPlayer(Util.newCMC("------------End------------"));
			return;
		case printPlayerInfo:
			this.thread.printPlayerInfo();
			for (String message : chatInfoFormat)
				console.sendChatToPlayer(Util.newCMC(message));
			return;
			}
	}
	
	private String[] showRanking(TreeSet<PlayerContainer> set, int tab) {
		
		String[] result = new String[10];
		Iterator iterator = set.descendingIterator();
		for (int i=0; i<10*(tab-1); i++)
			if (iterator.hasNext())
				iterator.next();
		for (int i=0; i<result.length; i++) {
			if (iterator.hasNext())
				result[i] = 10*(tab-1)+i+1 +" : " + iterator.next().toString();
			else result[i] = "";
		}
		return result;
	}
	
	@Override
	public String getCommandName() {
		return "inmis";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		StringBuilder result = new StringBuilder("/inmis <command> | <command> : ");
		for (Commands command : Commands.values())
			result.append(command.name()).append(", ");
		result.delete(result.length() -1, result.length());
		return result.toString();
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
		return true;
	}

}
