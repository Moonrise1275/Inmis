package moonrise.inmis.handlers;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class QChangeKeyHandler extends KeyHandler {
	
	public static boolean keyPressed = false;
	
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
	
}
