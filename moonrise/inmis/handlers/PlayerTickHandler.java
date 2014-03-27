package moonrise.inmis.handlers;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Scoreboard;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class PlayerTickHandler implements ITickHandler {
	
	private final EnumSet<TickType> ticksToGet;
	
	public PlayerTickHandler(EnumSet<TickType> ticksToGet) {
		this.ticksToGet = ticksToGet;
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		playerTick((EntityPlayer)tickData[0]);
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		
	}
	
	private void playerTick(EntityPlayer player) {
		
		QChangeKeyHandler.QSlotChange(player);
		
		
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return ticksToGet;
	}

	@Override
	public String getLabel() {
		return "InmisPlayerTick";
	}

}
