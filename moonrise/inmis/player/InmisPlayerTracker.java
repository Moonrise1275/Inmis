package moonrise.inmis.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import moonrise.inmis.handler.ConfigHandler;
import moonrise.inmis.util.Util;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.IPlayerTracker;

public class InmisPlayerTracker implements IPlayerTracker {
	
	ConfigHandler config;
	private Map<String, InmisPlayer> playermap;
	
	public InmisPlayerTracker(ConfigHandler config) {
		this.config = config;
		this.playermap = new HashMap<String, InmisPlayer>();
	}
	
	public InmisPlayer getInmisPlayer(String username) {
		return this.playermap.get(username);
	}

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		player.sendChatToPlayer(Util.newCMC("Welcome to the minecraft world!"));
		NBTTagCompound nbttag = player.getEntityData();
		
		if (!nbttag.hasKey(InmisPlayer.PROP_NAME)) {
			nbttag.setCompoundTag(InmisPlayer.PROP_NAME, new NBTTagCompound());
		}
		InmisPlayer inmisplayer = new InmisPlayer(player);
		inmisplayer.loadNBTData(player.getEntityData());
		playermap.put(player.username, inmisplayer);
		
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		NBTTagCompound nbttag = player.getEntityData();
		
		NBTTagCompound inmiplayertag = new NBTTagCompound();
		playermap.get(player.username).saveNBTData(inmiplayertag);
		nbttag.setCompoundTag(InmisPlayer.PROP_NAME, inmiplayertag);
		playermap.remove(player.username);
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
		
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
		
	}
	
	@ForgeSubscribe
	public void onPlayerDeathAndDropItems(PlayerDropsEvent e) {
		InmisPlayer inmisplayer = getInmisPlayer(e.entityPlayer.username);
		if (inmisplayer == null) {
			return;
		}
		Random r = new Random();
		for (int i=0; i<inmisplayer.getSizeInventory(); i++) {
			ItemStack stack = inmisplayer.getStackInSlot(i);
			if (stack != null) {
				EntityItem ei = new EntityItem(e.entityPlayer.worldObj, e.entityPlayer.posX, e.entityPlayer.posY, e.entityPlayer.posZ, stack);
				ei.motionX = (r.nextGaussian() - 0.5);
				ei.motionY = (r.nextGaussian() - 0.5);
				ei.motionZ = (r.nextGaussian() - 0.5);
				e.drops.add(ei);
				inmisplayer.setInventorySlotContents(i, null);
			}
		}
	}
	
	@ForgeSubscribe
	public void onPlayerPickUpItem(EntityItemPickupEvent e) {
		if (config.enableMiningUnifier) {
			int oreid = OreDictionary.getOreID(e.item.getEntityItem());
			String orename = OreDictionary.getOreName(oreid);
			boolean flag1 = false;
			for (String name : config.miningUnifierWhitelist) {
				if (name == orename)
					flag1 = true;
			}
			if (flag1) {
				ItemStack stack = OreDictionary.getOres(oreid).get(OreDictionary.getOres(oreid).size());
				stack.stackSize = e.item.getEntityItem().stackSize;
				e.item.setEntityItemStack(stack);
			}
		}
	}

}
