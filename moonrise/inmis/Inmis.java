package moonrise.inmis;

import java.util.EnumSet;

import moonrise.inmis.handlers.ConfigHandler;
import moonrise.inmis.handlers.PlayerTickHandler;
import moonrise.inmis.handlers.QChangeKeyHandler;
import moonrise.inmis.item.ItemInventoryHopper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(name = Info.MOD_NAME, modid = Info.MOD_ID, version = Info.VERSION, dependencies = Info.DEPENDENCIES)
@NetworkMod(channels = {"Inmis"}, clientSideRequired = true, serverSideRequired = false)
public class Inmis {
	
	public static CreativeTabs tabInmis;
	
	ConfigHandler config;
	PlayerCheckerThread playerChecker;
	
	@Instance()
	public static Inmis instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		
		this.config = new ConfigHandler(new Configuration(e.getSuggestedConfigurationFile()));
		this.tabInmis = new CreativeTabInmis(Info.MOD_NAME, config.idItemInventoryHopper);
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		
		Item itemInventoryHopper = new ItemInventoryHopper(config.idItemInventoryHopper, config.generalMaxUpgradeCountInventoryHopper);
		GameRegistry.registerItem(itemInventoryHopper, config.itemInventoryHopper);
		
		KeyBinding[] keys = {new KeyBinding("Quick slot change", Keyboard.KEY_Z)};
		boolean[] repeats = {false};
		KeyBindingRegistry.registerKeyBinding(new QChangeKeyHandler(keys, repeats));
		TickRegistry.registerTickHandler(new PlayerTickHandler(EnumSet.of(TickType.PLAYER)), Side.SERVER);
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		
	}
	
	@EventHandler
	public void serverStarted(FMLServerStartingEvent e) {
		if (config.generalEnablePlayerCheckerThread) {
			playerChecker = new PlayerCheckerThread();
			playerChecker.setPriority(Thread.MAX_PRIORITY);
			playerChecker.start();
		}
		
	}
	
	@EventHandler
	public void serverStopped(FMLServerStoppedEvent e) {
		
		playerChecker.setOff();
		
	}

}
