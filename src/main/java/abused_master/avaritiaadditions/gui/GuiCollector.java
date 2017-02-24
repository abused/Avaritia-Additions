package abused_master.avaritiaadditions.gui;

import abused_master.avaritiaadditions.AvaritiaAdditions;
import abused_master.avaritiaadditions.tile.collector.CollectorContainer;
import abused_master.avaritiaadditions.tile.collector.TileCollector;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiCollector extends GuiContainer {

    int WIDTH = 176;
    int HEIGHT = 166;
    public TileCollector collector;

    public GuiCollector(CollectorContainer container, TileCollector te) {
        super(container);
        xSize = WIDTH;
        ySize = HEIGHT;
        this.collector = (TileCollector) te;

    }

    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        String s = "Neutron Collector";
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(AvaritiaAdditions.MODID, "textures/gui/gui_collector.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}
