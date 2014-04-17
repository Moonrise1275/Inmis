package moonrise.inmis.proxy;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import moonrise.inmis.EnumInmisKeys;
import moonrise.inmis.handler.InmisKeyHandler;
import moonrise.inmis.handler.InmisPlayerTickHandler;
import moonrise.inmis.proxy.CommonProxy;

public class ClientProxy extends CommonProxy {
	
	public void bindKeys() {
		KeyBinding[] keys = {new KeyBinding(EnumInmisKeys.RotateInventory.name(), Keyboard.KEY_Z),
							 new KeyBinding(EnumInmisKeys.OpenInmisInventory.name(), Keyboard.KEY_X),
							 new KeyBinding(EnumInmisKeys.ChangeArmor.name(), Keyboard.KEY_C)};
		boolean[] repeats = {false, false, false};
		InmisKeyHandler keyhandler = new InmisKeyHandler(keys, repeats);
		KeyBindingRegistry.registerKeyBinding(keyhandler);
		TickRegistry.registerTickHandler(new InmisPlayerTickHandler(EnumSet.of(TickType.PLAYER), keyhandler), Side.CLIENT);
	}

}
