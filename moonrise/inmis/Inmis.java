package moonrise.inmis;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import moonrise.inmis.handler.ConfigHandler;
import moonrise.inmis.handler.GuiHandler;
import moonrise.inmis.handler.InmisCommandHandler;
import moonrise.inmis.item.ModItems;
import moonrise.inmis.network.PacketHandler;
import moonrise.inmis.network.PacketInmis;
import moonrise.inmis.player.InmisPlayer;
import moonrise.inmis.player.InmisPlayerTracker;
import moonrise.inmis.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(name = Inmis.MOD_NAME, modid = Inmis.MOD_ID, version = Inmis.VERSION, dependencies = Inmis.DEPENDENCIES)
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = {PacketInmis.CHANNEL}, packetHandler = PacketHandler.class)
public class Inmis {
	
	public static final String MOD_NAME = "Inmis";
	public static final String MOD_ID = "Inmis";
	public static final String VERSION = "0.6.0";
	public static final String DEPENDENCIES = "";
	
	public static final int GUI_INMIS_INVENTORY = 1, GUI_CHANGE_ARMOR = 2;
	
	ConfigHandler config;
	public InmisPlayerTracker ptracker;
	ModItems items;
	
	@Instance(Inmis.MOD_ID)
	public static Inmis instance;
	
	@SidedProxy(clientSide = "moonrise.inmis.proxy.ClientProxy", serverSide = "moonrise.inmis.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void loadConfiguration(FMLPreInitializationEvent e) {
		this.config = new ConfigHandler(new Configuration(e.getSuggestedConfigurationFile()));
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		
		this.items = new ModItems(config);
		items.registerRecipes();
		
		this.ptracker = new InmisPlayerTracker(config);
		MinecraftForge.EVENT_BUS.register(ptracker);
		GameRegistry.registerPlayerTracker(ptracker);
		NetworkRegistry.instance().registerGuiHandler(instance, new GuiHandler(ptracker));
		
		proxy.bindKeys();
	}
	
	@EventHandler
	public void modIntegration(FMLPostInitializationEvent e) {
		
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent e) {
		e.registerServerCommand(new InmisCommandHandler(ptracker));
	}
	
}
