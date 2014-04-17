package moonrise.inmis.handler;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class InmisPlayerTickHandler implements ITickHandler {
	
	private EnumSet<TickType> ticksToGet;
	private InmisKeyHandler keyhandler;
	
	public InmisPlayerTickHandler(EnumSet<TickType> ticktype, InmisKeyHandler keyhandler) {
		this.ticksToGet = ticktype;
		this.keyhandler = keyhandler;
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		this.playerTick((EntityPlayer)tickData[0]);
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		
	}
	
	private void playerTick(EntityPlayer player) {
		this.keyhandler.processKey(player);
	}

	@Override
	public EnumSet<TickType> ticks() {
		return this.ticksToGet;
	}

	@Override
	public String getLabel() {
		return "InmisPlayerTickHandler";
	}

}
