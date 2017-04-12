/*
 *
 * Code blatantly jacked from Vazkii
 * Get the original here: https://github.com/Vazkii/Botania/blob/master/src/main/java/vazkii/botania/common/item/equipment/tool/terrasteel/ItemTerraAxe.java
 */

package abused_master.avaritiaadditions.items.tools;

import abused_master.avaritiaadditions.AvaritiaAdditions;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemAxeInfinity extends AvaritiaItemAxe {

    private static final ToolMaterial opAxe = EnumHelper.addToolMaterial("INFINITY_AXE", 32, 9999, 9999F, 20F, 30);
    public static EnumRarity cosmic = EnumHelper.addRarity("COSMIC", TextFormatting.RED, "Cosmic");
    private static Map<Integer, List<BlockSwapper>> blockSwappers = new HashMap();

    public ItemAxeInfinity(){
        super(opAxe);
        setUnlocalizedName("infinity_axe");
        setCreativeTab(AvaritiaAdditions.AvaritiaAdditions);
        GameRegistry.register(this.setRegistryName("infinity_axe"));
    }

    @Override
    public void setDamage(ItemStack stack, int damage){
        super.setDamage(stack, 0);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return cosmic;
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        if (super.getStrVsBlock(stack, state) > 1.0F || state.getMaterial() == Material.LEAVES) {
            return efficiencyOnProperMaterial;
        }
        return Math.max(super.getStrVsBlock(stack, state), 6.0F);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        if(player.isSneaking()) {
            player.swingArm(hand);
            int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(35), stack);
            int silktouch = EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(33), stack);
            boolean silk;
            if(silktouch > 0) {
                silk = true;
            } else {
                silk = false;
            }
            int range = 13;

            ToolHelper.removeBlocksInIteration(player, stack, world, (int)player.posX, (int)player.posY, (int)player.posZ, -range, -3, -range, range, range * 2 - 3, range, null, ToolHelper.materialsAxe, silk, fortune, false);
        }
        return super.onItemRightClick(stack, world, player, hand);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        RayTraceResult raycast = ToolHelper.raytraceFromEntity(player.worldObj, player, true, 10);
        if (raycast != null) {
            breakOtherBlock(player, stack, x, y, z, x, y, z, raycast.sideHit.getHorizontalIndex());
        }
        return false;
    }

    public void breakOtherBlock(EntityPlayer player, ItemStack stack, int x, int y, int z, int originX, int originY, int originZ, int side) {
        if(player.isSneaking())
            return;
        BlockPos coords = new BlockPos(x, y, z);
        addBlockSwapper(player.worldObj, player, stack, coords, coords, 32, false, true, new ArrayList());
    }

    @Override
    public boolean hasCustomEntity (ItemStack stack)
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return false;
    }

    @SubscribeEvent
    public void onTickEnd(TickEvent.WorldTickEvent event) {
        if(event.phase == TickEvent.Phase.END) {
            int dim = event.world.provider.getDimension();
            if(blockSwappers.containsKey(dim)) {
                List<BlockSwapper> swappers = blockSwappers.get(dim);
                List<BlockSwapper> swappersSafe = new ArrayList(swappers);
                swappers.clear();
                for(BlockSwapper s : swappersSafe)
                    if(s != null)
                        s.tick();
            }
        }
    }

    private static BlockSwapper addBlockSwapper(World world, EntityPlayer player, ItemStack stack, BlockPos origCoords, BlockPos coords, int steps, boolean leaves, boolean force, List<String> posChecked) {
        BlockSwapper swapper = new BlockSwapper(world, player, stack, origCoords, coords, steps, leaves, force, posChecked);
        int dim = world.provider.getDimension();
        if(!blockSwappers.containsKey(dim))
            blockSwappers.put(dim, new ArrayList());
        blockSwappers.get(dim).add(swapper);
        return swapper;
    }
    private static class BlockSwapper {
        final World world;
        final EntityPlayer player;
        final ItemStack stack;
        final BlockPos origCoords;
        final int steps;
        final BlockPos coords;
        final boolean leaves;
        final boolean force;
        final List<String> posChecked;
        BlockSwapper(World world, EntityPlayer player, ItemStack stack, BlockPos origCoords, BlockPos coords, int steps, boolean leaves, boolean force, List<String> posChecked) {
            this.world = world;
            this.player = player;
            this.stack = stack;
            this.origCoords = origCoords;
            this.coords = coords;
            this.steps = steps;
            this.leaves = leaves;
            this.force = force;
            this.posChecked = posChecked;
        }
        void tick() {
            Block blockat = world.getBlockState(new BlockPos(coords.getX(), coords.getY(), coords.getZ())).getBlock();
            if(!force && blockat.isAir(blockat.getDefaultState(), world, new BlockPos(coords.getX(), coords.getY(), coords.getZ())))
                return;
            ToolHelper.removeBlockWithDrops(player, stack, world, new BlockPos(coords.getX(), coords.getY(), coords.getZ()), null, ToolHelper.materialsAxe, EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(33), stack) > 0, EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(35), stack), 0F, false);
            if(steps == 0)
                return;
            for(EnumFacing dir : EnumFacing.VALUES) {
                int x = coords.getX() + dir.getFrontOffsetX();
                int y = coords.getY() + dir.getFrontOffsetY();
                int z = coords.getZ() + dir.getFrontOffsetZ();
                String pstr = posStr(x, y, z);
                if(posChecked.contains(pstr))
                    continue;
                Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
                boolean log = block.isWood(world, new BlockPos(x, y, z));
                boolean leaf = block.isLeaves(block.getDefaultState(), world, new BlockPos(x, y, z));
                if(log || leaf) {
                    int steps = this.steps - 1;
                    steps = leaf ? leaves ? steps : 3 : steps;
                    addBlockSwapper(world, player, stack, origCoords, new BlockPos(x, y, z), steps, leaf, false, posChecked);
                    posChecked.add(pstr);
                }
            }
        }
        String posStr(int x, int y, int z) {
            return x + ":" + y + ":" + z;
        }
    }
}