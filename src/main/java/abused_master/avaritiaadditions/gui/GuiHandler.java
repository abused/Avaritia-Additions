package abused_master.avaritiaadditions.gui;

import abused_master.avaritiaadditions.tile.CompressorContainer;
import abused_master.avaritiaadditions.tile.TileCompressor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    public static final int GUI_COMPRESSOR = 0;
    public static final int GUI_COLLECTOR = 1;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        TileCompressor containerTileEntity = (TileCompressor) te;
        if (ID == GUI_COMPRESSOR)
            if (te instanceof TileCompressor) {
                return new CompressorContainer(player.inventory, containerTileEntity);
            }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);

        if (ID == GUI_COMPRESSOR)
            if (te instanceof TileCompressor) {
                TileCompressor containerTileEntity = (TileCompressor) te;
                return new GuiCompressor(new CompressorContainer(player.inventory, containerTileEntity), containerTileEntity);
            }

        return null;
    }
}
