package abused_master.avaritiaadditions;

import abused_master.avaritiaadditions.minetweaker.Tweak;
import abused_master.avaritiaadditions.proxy.CommonProxy;
import abused_master.avaritiaadditions.registry.ModBlocks;
import abused_master.avaritiaadditions.registry.ModItems;
import abused_master.avaritiaadditions.tile.CompressorRecipeRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = AvaritiaAdditions.MODID, version = AvaritiaAdditions.VERSION, name = AvaritiaAdditions.MODNAME)
public class AvaritiaAdditions {

    public static final String MODID = "avaritiaadditions";
    public static final String MODNAME = "Avaritia Additions";
    public static final String VERSION = "1.2";
    public static final String compressorUid = "Compressor Recipes";
    //public static final String DEPENDENCIES = "avaritia";

    @Mod.Instance
    public static AvaritiaAdditions instance;

    @SidedProxy(clientSide = "abused_master.avaritiaadditions.proxy.ClientProxy", serverSide = "abused_master.avaritiaadditions.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        this.proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        this.proxy.init(e);
        CompressorRecipeRegistry.regVanillaSingularityRecipe();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        this.proxy.postInit(e);
        if(Loader.isModLoaded("MineTweaker3"))
            try {
                Tweak.registrate();
            }
            catch (Throwable ex){
            }
        CompressorRecipeRegistry.registerRecipe();

    }

    public static CreativeTabs AvaritiaAdditions = new CreativeTabs("avaritiaadditions") {
        @Override
        public Item getTabIconItem() {
            return ModItems.InfinityCatalyst;
        }
    };

}
