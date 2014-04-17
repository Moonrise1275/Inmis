package moonrise.inmis.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;

public abstract class PacketInmis {
	
	public static final String CHANNEL = "ChannelInmis";
	private static final BiMap<Integer, Class<? extends PacketInmis>> idMap;
	
	public static PacketInmis constructPacket(int packetId) throws ProtocolException, ReflectiveOperationException {
        Class<? extends PacketInmis> clazz = idMap.get(packetId);
        if (clazz == null) {
                throw new ProtocolException("Unknown Packet Id!");
        } else {
                return clazz.newInstance();
        }
	}

	public static class ProtocolException extends Exception {

        public ProtocolException() {
        }

        public ProtocolException(String message, Throwable cause) {
                super(message, cause);
        }

        public ProtocolException(String message) {
                super(message);
        }

        public ProtocolException(Throwable cause) {
                super(cause);
        }
	}
	
	public final int getPacketId() {
        if (idMap.inverse().containsKey(getClass())) {
                return idMap.inverse().get(getClass()).intValue();
        } else {
                throw new RuntimeException("Packet " + getClass().getSimpleName() + " is missing a mapping!");
        }
	}
	 
	public final Packet makePacket() {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeByte(getPacketId());
		write(out);
		return PacketDispatcher.getPacket(CHANNEL, out.toByteArray());
	}
	
	public abstract void read(ByteArrayDataInput in);
	public abstract void write(ByteArrayDataOutput out);
	public abstract void excute(EntityPlayer player, Side side);
	
	static {
		ImmutableBiMap.Builder<Integer, Class<? extends PacketInmis>> builder = ImmutableBiMap.builder();
		
		builder.put(1, PacketRotateInventory.class);
		
		idMap = builder.build();
	}

}
