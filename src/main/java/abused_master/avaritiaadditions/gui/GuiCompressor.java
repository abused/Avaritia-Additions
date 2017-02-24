package abused_master.avaritiaadditions.gui;

import abused_master.avaritiaadditions.AvaritiaAdditions;
import abused_master.avaritiaadditions.tile.CompressorContainer;
import abused_master.avaritiaadditions.tile.TileCompressor;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class GuiCompressor extends GuiContainer {

    int WIDTH = 176;
    int HEIGHT = 166;
    public TileCompressor compressor;

    public GuiCompressor(CompressorContainer container, TileCompressor te) {
        super(container);
        xSize = WIDTH;
        ySize = HEIGHT;
        this.compressor = (TileCompressor) te;

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        if(compressor.getProgress() > 0) {
            this.fontRendererObj.drawString(compressor.progress + " / " + compressor.target, 41, 49, 4210752);
            this.fontRendererObj.drawString(compressor.ingredient, 41, 60, 4210752);
        }
        String s = "Neutronium Compressor";
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(new ResourceLocation(AvaritiaAdditions.MODID, "textures/gui/gui_compressor.png"));
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        if(compressor.getProgress() > 0){
        this.drawTexturedModalRect(guiLeft + 79, guiTop + 26, 176, 14, compressor.getProgress()* 24 / compressor.getTarget() + 1, 16);
        }
    }
}
