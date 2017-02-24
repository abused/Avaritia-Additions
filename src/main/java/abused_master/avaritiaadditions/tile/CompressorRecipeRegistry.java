package abused_master.avaritiaadditions.tile;

import abused_master.avaritiaadditions.config.Config;
import abused_master.avaritiaadditions.registry.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class CompressorRecipeRegistry {

    public static ShapelessOreRecipe catalyst;

    public static void regVanillaSingularityRecipe(){
        CompressorManager.addRecipe(new ItemStack(ModItems.IronSingularity, 1), Config.IronSingularity, new ItemStack(Blocks.IRON_BLOCK));
        CompressorManager.addRecipe(new ItemStack(ModItems.GoldSingularity, 1), Config.GoldSingularity, new ItemStack(Blocks.GOLD_BLOCK));
        CompressorManager.addRecipe(new ItemStack(ModItems.LapisSingularity, 1), Config.LapisSingularity, new ItemStack(Blocks.LAPIS_BLOCK));
        CompressorManager.addRecipe(new ItemStack(ModItems.RedstoneSingularity, 1), Config.RedstoneSingularity, new ItemStack(Blocks.REDSTONE_BLOCK));
        CompressorManager.addRecipe(new ItemStack(ModItems.QuartzSingularity, 1), Config.QuartzSingularity, new ItemStack(Blocks.QUARTZ_BLOCK));
    }

    public static void registerRecipe(){

        if(!OreDictionary.getOres("blockCopper").isEmpty()){
            CompressorManager.addOreRecipe(new ItemStack(ModItems.CopperSingularity), Config.CopperSingularity, "blockCopper");
        }
        if(!OreDictionary.getOres("blockTin").isEmpty()){
            CompressorManager.addOreRecipe(new ItemStack(ModItems.TinSingularity), Config.TinSingularity, "blockTin");
        }
        if(!OreDictionary.getOres("blockLead").isEmpty()){
            CompressorManager.addOreRecipe(new ItemStack(ModItems.LeadSingularity), Config.LeadSingularity, "blockLead");
        }
        if(!OreDictionary.getOres("blockSilver").isEmpty()){
            CompressorManager.addOreRecipe(new ItemStack(ModItems.SilverSingularity), Config.SilverSingularity, "blockSilver");
        }
        if(!OreDictionary.getOres("blockNickel").isEmpty()){
            CompressorManager.addOreRecipe(new ItemStack(ModItems.NickelSingularity), Config.NickelSingularity, "blockNickel");
        }
    }

}
