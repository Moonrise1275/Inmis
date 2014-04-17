package moonrise.inmis.handler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import moonrise.inmis.EnumInmisKeys;
import moonrise.inmis.Inmis;
import moonrise.inmis.network.PacketRotateInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class InmisKeyHandler extends KeyHandler {
	
	private boolean RotateInventoryKeyPressed = false;
	private boolean OpenInmisInventoryKeyPressed = false;
	private boolean ChangeArmorKeyPressed = false;
	
	private boolean flag1;
	private boolean flag2;
	private boolean flag3;
	
	public InmisKeyHandler(KeyBinding[] keyBindings, boolean[] repeats) {
		super(keyBindings, repeats);
	}

	@Override
	public String getLabel() {
		return "InmisKeyHandler";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		
		switch (EnumInmisKeys.valueOf(kb.keyDescription)) {
		case RotateInventory:
			this.RotateInventoryKeyPressed = true;
			break;
		case OpenInmisInventory:
			this.OpenInmisInventoryKeyPressed = true;
			break;
		case ChangeArmor:
			this.ChangeArmorKeyPressed = true;
			break;
		}
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		
	}
	
	public void processKey(EntityPlayer player) {
		GuiScreen curscreen = Minecraft.getMinecraft().currentScreen;
		if (curscreen instanceof GuiChat) {
			return;
		}
		
		if (this.RotateInventoryKeyPressed) {
			this.RotateInventoryKeyPressed = false;
			if (flag1) {
				flag1 = false;
			} else {
			flag1 = true;
			
			if (player.openContainer instanceof ContainerPlayer && !(curscreen instanceof GuiInventory)) {
				PacketDispatcher.sendPacketToServer(new PacketRotateInventory((byte)player.inventory.currentItem, !player.isSneaking()).makePacket());
			} else {
				for (byte ii=0; ii<9; ii++) {
					PacketDispatcher.sendPacketToServer(new PacketRotateInventory(ii, true).makePacket());
				}
			}
			}
			
		}
		
		if (this.OpenInmisInventoryKeyPressed) {
			this.OpenInmisInventoryKeyPressed = false;
			if (flag2) {
				flag2 = false;
			} else {
			flag2 = true;
			
			player.openGui(Inmis.instance, Inmis.GUI_INMIS_INVENTORY, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
			}
		}
		
		if (this.ChangeArmorKeyPressed) {
			this.ChangeArmorKeyPressed = false;
			if (flag3) {
				flag3 = false;
			} else {
			flag3 = true;
			
			
			}
		}
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}
	
}
