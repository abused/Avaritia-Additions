package abused_master.avaritiaadditions.proxy;

import abused_master.avaritiaadditions.AvaritiaAdditions;
import abused_master.avaritiaadditions.config.Config;
import abused_master.avaritiaadditions.gui.GuiHandler;
import abused_master.avaritiaadditions.registry.ModBlocks;
import abused_master.avaritiaadditions.registry.ModItems;
import abused_master.avaritiaadditions.registry.ModRecipes;
import abused_master.avaritiaadditions.tile.TileCompressor;
import abused_master.avaritiaadditions.tile.collector.TileCollector;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        ModItems.init();
        ModBlocks.init();
        Config.init(e);
    }

    public void init(FMLInitializationEvent e) {
        GameRegistry.registerTileEntity(TileCompressor.class, "tile_compresor");
        GameRegistry.registerTileEntity(TileCollector.class, "tile_collector");
        NetworkRegistry.INSTANCE.registerGuiHandler(AvaritiaAdditions.instance, new GuiHandler());
        ModRecipes.initRecipes();
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

}
