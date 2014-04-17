package moonrise.inmis.network;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import moonrise.inmis.network.PacketInmis.ProtocolException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player p) {
		
		try {
			EntityPlayer player = (EntityPlayer)p;
			ByteArrayDataInput in = ByteStreams.newDataInput(packet.data);
			int packetId = in.readUnsignedByte();
			PacketInmis packetinmis = PacketInmis.constructPacket(packetId);
			packetinmis.read(in);
			packetinmis.excute(player, player.worldObj.isRemote ? Side.CLIENT : Side.SERVER);
		} catch (ProtocolException e) {
            if (p instanceof EntityPlayerMP) {
                    ((EntityPlayerMP) p).playerNetServerHandler.kickPlayerFromServer("Protocol Exception!");
                    Logger.getLogger("DemoMod").warning("Player " + ((EntityPlayer)p).username + " caused a Protocol Exception!");
            }
		} catch (ReflectiveOperationException e) {
            throw new RuntimeException("Unexpected Reflection exception during Packet construction!", e);
		}
		
	}
	
}
