package moonrise.inmis;

import java.util.EnumSet;

import moonrise.inmis.block.ModBlocks;
import moonrise.inmis.handlers.ConfigHandler;
import moonrise.inmis.handlers.GuiHandler;
import moonrise.inmis.handlers.PlayerTickHandler;
import moonrise.inmis.handlers.QChangeKeyHandler;
import moonrise.inmis.item.ModItems;
import moonrise.inmis.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.Configuration;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author Moonrise
 *
 * Main class of mod inmis
 */
@Mod(name = Info.MOD_NAME, modid = Info.MOD_ID, version = Info.VERSION, dependencies = Info.DEPENDENCIES)
@NetworkMod(channels = {"Inmis"}, clientSideRequired = true, serverSideRequired = false)
public class Inmis {
	
	public static CreativeTabs tabInmis;
	@SidedProxy(clientSide = Info.CLIENT_PROXY, serverSide = Info.COMMON_PROXY)
	public static CommonProxy proxy;
	
	/**
	 * Configuration handler class
	 * contains all configurable values
	 */
	ConfigHandler config;
	
	/**
	 * Player checker thread
	 * check who is in the server now
	 * write each player's play time, this week's play time, last play time
	 */
	PlayerCheckerThread playerChecker;
	
	/**
	 * Mod items class
	 * register items, recipes, anything about item
	 */
	ModItems items;
	
	/**
	 * Mod blocks class
	 * register blocks, tile entities, anything about block
	 */
	ModBlocks blocks;
	
	/**
	 * Mod gui ids
	 * used in GuiHandler class and player.openGui method
	 */
	public static final int GUI_INVHOPPER = 1, GUI_TOOLBELT = 2, GUI_ARMORPACK = 3, GUI_INVANCHOR = 4;
	
	@Instance()
	public static Inmis instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		
		this.config = new ConfigHandler(new Configuration(e.getSuggestedConfigurationFile()));
		this.tabInmis = new CreativeTabInmis(Info.MOD_NAME, config.idItemInventoryHopper);
		this.items = new ModItems(config);
		this.blocks = new ModBlocks(config);
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		
		this.items.registerRecipes();
		this.blocks.registerTileEntities();
		
		proxy.keyBind();
		
		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
		
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
		
		e.registerServerCommand(new CommandInmis(playerChecker));
	}
	
	@EventHandler
	public void serverStopped(FMLServerStoppedEvent e) {
		
		playerChecker.setOff();
		
	}
	
}
