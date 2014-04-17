package moonrise.inmis.gui;

import org.lwjgl.opengl.GL11;

import moonrise.inmis.player.InmisPlayer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiInmisInventory extends GuiContainer {
	
	private static final ResourceLocation resource = new ResourceLocation("inmis:textures/gui/inventory.png");
	private static final int InventoryWidth = 176;
	
	private InventoryPlayer playerinventory;
	private InmisPlayer inmisplayer;
	
	private int inventoryUC;
	
	public GuiInmisInventory(EntityPlayer player, InmisPlayer inmisplayer) {
		super(new ContainerInmisInventory(player, inmisplayer));
		
		this.playerinventory = player.inventory;
		this.inmisplayer = inmisplayer;
		
		this.inventoryUC = inmisplayer.getSizeInventory() /9;
		this.xSize = InventoryWidth;
		this.ySize = 105 + inventoryUC*18;
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		
		GL11.glColor4f(1f, 1f, 1f, 1f);
		this.mc.getTextureManager().bindTexture(resource);
		int k = (this.width - this.xSize) /2;
		int l = (this.height - this.ySize) /2;
		this.drawTexturedModalRect(k, l + 15 + inventoryUC*18, 1, 1, 176, 90);
		if (inventoryUC > 0) {
			this.drawTexturedModalRect(k, l, 1, 1, 176, 7);
			for (int ii=0; ii<inventoryUC; ii++) {
				this.drawTexturedModalRect(k, l + 7 + ii*18, 1, 8, 176, 18);
			}
			this.drawTexturedModalRect(k, l + 7 + inventoryUC*18, 1, 84, 176, 7);
		}
		
	}
	
	@Override
	public void mouseClicked(int i, int j, int k) {}
	
}
