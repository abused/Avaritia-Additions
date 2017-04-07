package abused_master.avaritiaadditions.proxy;

import abused_master.avaritiaadditions.registry.ModBlocks;
import abused_master.avaritiaadditions.registry.ModItems;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        ModItems.initRenders();
        ModBlocks.initRenders();
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        ModItems.registerColor();
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
}