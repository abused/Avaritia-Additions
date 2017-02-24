package abused_master.avaritiaadditions.registry;

import abused_master.avaritiaadditions.blocks.BaseBlock;
import abused_master.avaritiaadditions.blocks.Collector;
import abused_master.avaritiaadditions.blocks.NeutroniumCompressor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

    public static Block NeutroniumBlock;
    public static Block InfinityBlock;
    public static Block Deadrock;
    public static Block DeadrockBrick;
    public static Block MossyDeadrock;
    public static Block CrackedDeadrock;
    public static Block ChiseledDeadrock;
    public static Block Compressor;
    public static Block CrystalMatrix;
    public static Block Collector;

    public static void init() {
        NeutroniumBlock = regBlock(Material.IRON, "neutronium_block", 2.5F, 1);
        InfinityBlock = regBlock(Material.IRON, "infinity_block", 2.5F, 1);
        Deadrock = regBlock(Material.ROCK, "deadrock", 2.0F, 1);
        DeadrockBrick = regBlock(Material.ROCK, "deadrock_brick", 2.0F, 1);
        MossyDeadrock = regBlock(Material.ROCK, "deadrock_mossy", 2.0F, 1);
        CrackedDeadrock = regBlock(Material.ROCK, "deadrock_cracked", 2.0F, 1);
        ChiseledDeadrock = regBlock(Material.ROCK, "deadrock_chiseled", 2.0F, 1);
        CrystalMatrix = regBlock(Material.IRON, "crystal_matrix", 50.0F, 3).setResistance(2000.0F);
        Compressor = new NeutroniumCompressor();
        Collector = new Collector();

    }

    private static Block regBlock(Material material, String regName, float hardness, int level) {
        final Block block = new BaseBlock(material, regName, hardness, level);
        final ItemBlock itemBlock = new ItemBlock(block);
        return regBlockRes(regName, itemBlock, block);
    }

    private static Block regBlockRes(String regName, ItemBlock itemBlock, Block block) {
        block.setRegistryName(regName);
        GameRegistry.register(block);
        itemBlock.setRegistryName(regName);
        GameRegistry.register(itemBlock);
        return block;
    }

    public static void initRenders() {
        reg(NeutroniumBlock);
        reg(InfinityBlock);
        reg(DeadrockBrick);
        reg(MossyDeadrock);
        reg(CrackedDeadrock);
        reg(ChiseledDeadrock);
        reg(Deadrock);
        reg(Compressor);
        reg(CrystalMatrix);
        reg(Collector);
    }

    public static void reg(Block block) {
        ModelResourceLocation res = new
                ModelResourceLocation(block.getRegistryName().toString(), "inventory");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, res);
    }
}
