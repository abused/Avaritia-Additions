/*
 *
 * Code blatantly jacked from Vazkii
 * Get the original here: https://github.com/Vazkii/Botania/blob/master/src/main/java/vazkii/botania/common/item/equipment/tool/terrasteel/ItemTerraAxe.java
 */

package abused_master.avaritiaadditions.items.tools;

import abused_master.avaritiaadditions.AvaritiaAdditions;
import abused_master.avaritiaadditions.items.entity.EntityImmortalItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAxeInfinity extends AvaritiaItemAxe {

    private static final ToolMaterial opAxe = EnumHelper.addToolMaterial("INFINITY_AXE", 32, 9999, 9999F, 20F, 30);
    public static EnumRarity cosmic = EnumHelper.addRarity("COSMIC", TextFormatting.RED, "Cosmic");

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
            int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack);
            boolean silk = EnchantmentHelper.getEnchantments(stack).containsKey(Enchantments.SILK_TOUCH);

            int range = 13;

            //ToolHelper.removeBlocksInIteration(player, stack, world, (int)player.posX, (int)player.posY, (int)player.posZ, -range, -3, -range, range, range * 2 - 3, range, null, ToolHelper.materialsAxe, silk, fortune, false);
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        RayTraceResult raycast = ToolHelper.raytraceFromEntity(player.worldObj, player, true, 10);
        if (raycast != null) {
            //breakOtherBlock(player, stack, x, y, z, x, y, z, raycast.sideHit.getIndex());
        }
        return false;
    }

    @Override
    public boolean hasCustomEntity (ItemStack stack)
    {
        return true;
    }

    @Override
    public Entity createEntity (World world, Entity location, ItemStack itemstack)
    {
        return new EntityImmortalItem(world, location, itemstack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return false;
    }
}