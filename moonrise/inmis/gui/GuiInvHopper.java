package moonrise.inmis.gui;

import org.lwjgl.opengl.GL11;

import moonrise.inmis.item.InventoryInvHopper;
import moonrise.util.Colors;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiInvHopper extends GuiContainer {
	
	private static final ResourceLocation resource = new ResourceLocation("Inmis", "textures/gui/container/invhopper.png");
	private InventoryPlayer playerInventory;
	private InventoryInvHopper itemInventory;

	public GuiInvHopper(InventoryPlayer invp, InventoryInvHopper invh) {
		super(new ContainerInvHopper(invp, invh));
		this.playerInventory = invp;
		this.itemInventory = invh;
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString(this.itemInventory.getInvName(), 8,  6, Colors.black.code());
		this.fontRenderer.drawString(this.playerInventory.getInvName(), 8, this.ySize - 94, Colors.black.code());
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1f, 1f, 1f, 1f);
		this.mc.getTextureManager().bindTexture(resource);
		int k = (this.width - this.xSize) /2;
		int l = (this.height - this.ySize)/2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, 35);
		this.drawTexturedModalRect(k, l + 35, 0, 126, this.xSize, 96);
	}

}
