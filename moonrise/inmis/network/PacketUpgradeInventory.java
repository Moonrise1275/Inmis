package moonrise.inmis.network;

import moonrise.inmis.Inmis;
import moonrise.inmis.util.Util;
import net.minecraft.entity.player.EntityPlayer;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class PacketUpgradeInventory extends PacketInmis {
	
	byte type;
	
	public PacketUpgradeInventory(byte type) {
		this.type = type;
	}

	@Override
	public void read(ByteArrayDataInput in) {
		this.type = in.readByte();
	}

	@Override
	public void write(ByteArrayDataOutput out) {
		out.writeByte(type);
	}

	@Override
	public void excute(EntityPlayer player, Side side) {
		
		Inmis.instance.ptracker.getInmisPlayer(player.username).upgrade(this.type);
		player.sendChatToPlayer(Util.newCMC("Your Inventory was upgraded to level " + this.type));
	}

}
