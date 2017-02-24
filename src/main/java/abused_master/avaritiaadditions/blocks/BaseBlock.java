package abused_master.avaritiaadditions.blocks;

import abused_master.avaritiaadditions.AvaritiaAdditions;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BaseBlock extends Block {

    public BaseBlock(Material material, String regName, float Hardness, int level) {
        super(material);
        this.setUnlocalizedName(regName);
        this.setHardness(blockHardness);
        this.setHarvestLevel("Pickaxe", level);
        this.setCreativeTab(AvaritiaAdditions.AvaritiaAdditions);
    }
}
