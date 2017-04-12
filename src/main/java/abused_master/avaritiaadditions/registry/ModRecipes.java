package abused_master.avaritiaadditions.registry;

import fox.spiteful.avaritia.Avaritia;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {

    public static void initRecipes() {
        GameRegistry.addRecipe(new ItemStack(ModItems.DiamondLattice), "X X", " X ", "X X", 'X', Items.DIAMOND);
        GameRegistry.addRecipe(new ItemStack(ModItems.NeutroniumIngot), "XXX", "XXX", "XXX", 'X', ModItems.NeutroniumNugget);
        GameRegistry.addRecipe(new ItemStack(ModBlocks.NeutroniumBlock), "XXX", "XXX", "XXX", 'X', ModItems.NeutroniumIngot);
        GameRegistry.addRecipe(new ItemStack(ModItems.NeutroniumNugget), "XXX", "XXX", "XXX", 'X', ModItems.NeutronsPile);
        GameRegistry.addRecipe(new ItemStack(ModBlocks.InfinityBlock), "XXX", "XXX", "XXX", 'X', ModItems.InfinityIngot);


        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.NeutroniumNugget, 9), new ItemStack(ModItems.NeutroniumIngot));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.NeutroniumIngot, 9), new ItemStack(ModBlocks.NeutroniumBlock));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.NeutronsPile, 9), new ItemStack(ModItems.NeutroniumNugget));
        addShapelessRecipe(new ItemStack(ModItems.RecordFragment, 9), "record");
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.CrystalMatrix, 1), "CCC", "CCC", "CCC", 'C', new ItemStack(Avaritia.matrixIngot));

    }

    public static void addShapelessRecipe(ItemStack output, Object... input){
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, input));
    }
}
