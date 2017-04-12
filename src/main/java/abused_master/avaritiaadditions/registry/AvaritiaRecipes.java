package abused_master.avaritiaadditions.registry;


import fox.spiteful.avaritia.Avaritia;
import fox.spiteful.avaritia.crafting.ExtremeCraftingManager;
import fox.spiteful.avaritia.crafting.ExtremeShapelessOreRecipe;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class AvaritiaRecipes {

    public static ExtremeShapelessOreRecipe catalyst;

    public static void direRecipes() {
        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.Collector, 1),
                "IIQQQQQII",
                "I QQQQQ I",
                "I  RRR  I",
                "X RRRRR X",
                "I RRXRR I",
                "X RRRRR X",
                "I  RRR  I",
                "I       I",
                "IIIXIXIII",
                'X', new ItemStack(Avaritia.matrixIngot, 1),
                'I', new ItemStack(Blocks.IRON_BLOCK, 1),
                'Q', new ItemStack(Blocks.QUARTZ_BLOCK, 1),
                'R', new ItemStack(Blocks.REDSTONE_BLOCK, 1));

        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.InfinityIngot, 1),
                "NNNNNNNNN",
                "NCXXCXXCN",
                "NXCCXCCXN",
                "NCXXCXXCN",
                "NNNNNNNNN",
                'C', new ItemStack(Avaritia.matrixIngot, 1),
                'N', new ItemStack(ModItems.NeutroniumIngot, 1),
                'X', new ItemStack(ModItems.InfinityCatalyst, 1));

        catalyst = ExtremeCraftingManager.getInstance().addShapelessOreRecipe(new ItemStack(ModItems.InfinityCatalyst, 1),
                new ItemStack(Blocks.EMERALD_BLOCK, 1),
                new ItemStack(ModItems.IronSingularity, 1), new ItemStack(ModItems.GoldSingularity, 1),
                new ItemStack(ModItems.LapisSingularity, 1), new ItemStack(ModItems.RedstoneSingularity, 1),
                new ItemStack(ModItems.QuartzSingularity, 1), new ItemStack(ModItems.UltimateStew),
                new ItemStack(ModItems.CosmicMeatballs), new ItemStack(ModItems.EndestPearl));

        ItemStack result = new ItemStack(ModItems.infinity_pickaxe, 1);
        result.addEnchantment(Enchantment.getEnchantmentByID(35), 10);
        ExtremeCraftingManager.getInstance().addRecipe(result,
                " IIIIIII ",
                "IIIICIIII",
                "II  N  II",
                "    N    ",
                "    N    ",
                "    N    ",
                "    N    ",
                "    N    ",
                "    N    ",
                'I', new ItemStack(ModItems.InfinityIngot, 1),
                'C', new ItemStack(ModBlocks.CrystalMatrix),
                'N', new ItemStack(ModItems.NeutroniumIngot, 1));

        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.infinity_sword),
                "       II",
                "      III",
                "     III ",
                "    III  ",
                " C III   ",
                "  CII    ",
                "  NC     ",
                " N  C    ",
                "X        ",
                'I', new ItemStack(ModItems.InfinityIngot, 1),
                'X', new ItemStack(ModItems.InfinityCatalyst, 1),
                'C', new ItemStack(Avaritia.matrixIngot, 1),
                'N', new ItemStack(ModItems.NeutroniumIngot, 1));

        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.infinity_shovel),
                "      III",
                "     IIXI",
                "      III",
                "     N I ",
                "    N    ",
                "   N     ",
                "  N      ",
                " N       ",
                "N        ",
                'I', new ItemStack(ModItems.InfinityIngot, 1),
                'X', new ItemStack(ModBlocks.InfinityBlock, 1),
                'N', new ItemStack(ModItems.NeutroniumIngot, 1));

        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.infinity_axe),
                "   I     ",
                "  IIIII  ",
                "   IIII  ",
                "     IN  ",
                "      N  ",
                "      N  ",
                "      N  ",
                "      N  ",
                "      N  ",
                'I', new ItemStack(ModItems.InfinityIngot, 1),
                'N', new ItemStack(ModItems.NeutroniumIngot, 1));

        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.infinity_bow),
                "   II    ",
                "  I W    ",
                " I  W    ",
                "I   W    ",
                "X   W    ",
                "I   W    ",
                " I  W    ",
                "  I W    ",
                "   II    ",
                'I', new ItemStack(ModItems.InfinityIngot, 1),
                'X', new ItemStack(ModBlocks.CrystalMatrix, 1),
                'W', new ItemStack(Blocks.WOOL, 1, 0));

        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.infinity_armor),
                " NN   NN ",
                "NNN   NNN",
                "NNN   NNN",
                " NIIIIIN ",
                " NIIXIIN ",
                " NIIIIIN ",
                " NIIIIIN ",
                " NIIIIIN ",
                "  NNNNN  ",
                'I', new ItemStack(ModItems.InfinityIngot, 1),
                'X', new ItemStack(ModBlocks.CrystalMatrix, 1),
                'N', new ItemStack(ModItems.NeutroniumIngot, 1));

        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.infinity_helm),
                "  NNNNN  ",
                " NIIIIIN ",
                " N XIX N ",
                " NIIIIIN ",
                " NIIIIIN ",
                " NI I IN ",
                'I', new ItemStack(ModItems.InfinityIngot, 1),
                'X', new ItemStack(ModItems.InfinityCatalyst, 1),
                'N', new ItemStack(ModItems.NeutroniumIngot, 1));

        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.infinity_pants),
                "NNNNNNNNN",
                "NIIIXIIIN",
                "NINNXNNIN",
                "NIN   NIN",
                "NCN   NCN",
                "NIN   NIN",
                "NIN   NIN",
                "NIN   NIN",
                "NNN   NNN",
                'I', new ItemStack(ModItems.InfinityIngot, 1),
                'X', new ItemStack(ModItems.InfinityCatalyst, 1),
                'C', new ItemStack(ModBlocks.CrystalMatrix, 1),
                'N', new ItemStack(ModItems.NeutroniumIngot, 1));

        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.infinity_shoes),
                " NNN NNN ",
                " NIN NIN ",
                " NIN NIN ",
                "NNIN NINN",
                "NIIN NIIN",
                "NNNN NNNN",
                'I', new ItemStack(ModItems.InfinityIngot, 1),
                'N', new ItemStack(ModItems.NeutroniumIngot, 1));

        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(ModItems.skulls_sword),
                "       IX",
                "      IXI",
                "     IXI ",
                "    IXI  ",
                " B IXI   ",
                "  BXI    ",
                "  WB     ",
                " W  B    ",
                "D        ",
                'I', new ItemStack(Avaritia.matrixIngot, 1),
                'X', new ItemStack(Items.BLAZE_POWDER),
                'B', new ItemStack(Items.BONE),
                'D', new ItemStack(Items.NETHER_STAR),
                'W', "logWood");

        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(ModItems.EndestPearl),
                "   EEE   ",
                " EEPPPEE ",
                " EPPPPPE ",
                "EPPPNPPPE",
                "EPPNSNPPE",
                "EPPPNPPPE",
                " EPPPPPE ",
                " EEPPPEE ",
                "   EEE   ",
                'P', new ItemStack(Items.ENDER_PEARL),
                'S', new ItemStack(Items.NETHER_STAR),
                'N', new ItemStack(ModItems.NeutroniumIngot, 1));

        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.Compressor),
                "IIIHHHIII",
                "X N   N X",
                "I N   N I",
                "X N   N X",
                "RNN O NNR",
                "X N   N X",
                "I N   N I",
                "X N   N X",
                "IIIXIXIII",
                'X', new ItemStack(Avaritia.matrixIngot, 1),
                'N', new ItemStack(ModItems.NeutroniumIngot, 1),
                'I', new ItemStack(Blocks.IRON_BLOCK, 1),
                'H', new ItemStack(Blocks.HOPPER, 1),
                'R', new ItemStack(Blocks.REDSTONE_BLOCK, 1),
                'O', new ItemStack(ModBlocks.NeutroniumBlock, 1));

        ExtremeCraftingManager.getInstance().addShapelessOreRecipe(new ItemStack(ModItems.UltimateStew, 1), new ItemStack(Items.WHEAT, 1), new ItemStack(Items.CARROT), new ItemStack(Items.POTATO), new ItemStack(Items.APPLE), new ItemStack(Items.MELON), new ItemStack(Blocks.PUMPKIN), new ItemStack(Blocks.CACTUS), new ItemStack(Blocks.RED_MUSHROOM), new ItemStack(Blocks.BROWN_MUSHROOM));
        ExtremeCraftingManager.getInstance().addShapelessOreRecipe(new ItemStack(ModItems.CosmicMeatballs, 1), new ItemStack(Items.BEEF), new ItemStack(Items.BEEF), new ItemStack(Items.CHICKEN), new ItemStack(Items.CHICKEN), new ItemStack(Items.PORKCHOP), new ItemStack(Items.PORKCHOP), new ItemStack(Items.FISH), new ItemStack(Items.FISH));
    }
}
