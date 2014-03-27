package moonrise.inmis.handlers;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class QChangeKeyHandler extends KeyHandler {
	
	private static boolean keyPressed = false;
	
	public QChangeKeyHandler(KeyBinding[] keys, boolean[] repeats) {
		super(keys, repeats);
	}
	
	@Override
	public String getLabel() {
		return "Quickslot change key";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		keyPressed = true;
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}
	
	public static synchronized void QSlotChange(EntityPlayer player) {
		if (keyPressed) {
			ItemStack[] inventory = player.inventory.mainInventory;
			ItemStack[] buffer = new ItemStack[9];
			
			for (int i=0; i<9; i++)
				buffer[i] = inventory[i+27];
			for (int i=0; i<9; i++)
				inventory[i+27] = inventory[i+18];
			for (int i=0; i<9; i++)
				inventory[i+18] = inventory[i+9];
			for (int i=0; i<9; i++)
				inventory[i+9] = inventory[i];
			for (int i=0; i<9; i++)
				inventory[i] = buffer[i];
			
			player.inventory.mainInventory = inventory;
			keyPressed = false;
		}
	}
	
}
