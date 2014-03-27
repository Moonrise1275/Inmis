package moonrise.inmis.proxy;

import java.util.EnumSet;

import moonrise.inmis.handlers.PlayerTickHandler;
import moonrise.inmis.handlers.QChangeKeyHandler;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void keyBind() {
		KeyBinding[] keys = {new KeyBinding("Quick slot change key", Keyboard.KEY_Z)};
		boolean[] repeats = {false};
		KeyBindingRegistry.registerKeyBinding(new QChangeKeyHandler(keys, repeats));
		TickRegistry.registerTickHandler(new PlayerTickHandler(EnumSet.of(TickType.PLAYER)), Side.SERVER);
	}

}
