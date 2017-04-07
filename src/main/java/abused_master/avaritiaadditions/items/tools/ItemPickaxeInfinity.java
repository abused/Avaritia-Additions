package abused_master.avaritiaadditions.items.tools;

import abused_master.avaritiaadditions.AvaritiaAdditions;
import abused_master.avaritiaadditions.items.entity.EntityImmortalItem;
import abused_master.avaritiaadditions.registry.ModItems;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemPickaxeInfinity extends ItemPickaxe {

    private static final ToolMaterial opPickaxe = EnumHelper.addToolMaterial("INFINITY_PICKAXE", 32, 9999, 9999F, 6.0F, 200);
    public static EnumRarity cosmic = EnumHelper.addRarity("COSMIC", TextFormatting.RED, "Cosmic");

    public static final Material[] MATERIALS = new Material[] { Material.ROCK, Material.IRON, Material.ICE, Material.GLASS, Material.PISTON, Material.ANVIL, Material.GRASS, Material.GROUND, Material.SAND, Material.SNOW, Material.CRAFTED_SNOW, Material.CLAY };

    public ItemPickaxeInfinity(){
        super(opPickaxe);
        setUnlocalizedName("infinity_pickaxe");
        setCreativeTab(AvaritiaAdditions.AvaritiaAdditions);
        GameRegistry.register(this.setRegistryName("infinity_pickaxe"));
    }

    @Override
    public void setDamage(ItemStack stack, int damage){
        super.setDamage(stack, 0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        ItemStack pick = new ItemStack(this);
        pick.addEnchantment(Enchantment.getEnchantmentByID(35), 10);
        list.add(pick);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return cosmic;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        if(player.isSneaking()) {
            NBTTagCompound tags = stack.getTagCompound();
            if (tags == null) {
                tags = new NBTTagCompound();
                stack.setTagCompound(tags);
            }
            if(EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(35), stack) < 10)
                stack.addEnchantment(Enchantment.getEnchantmentByID(35), 10);
            tags.setBoolean("hammer", !tags.getBoolean("hammer"));
            player.swingArm(hand);
        }
        return super.onItemRightClick(stack, world, player, hand);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase victim, EntityLivingBase player){
        if(stack.getTagCompound() != null){
            if(stack.getTagCompound().getBoolean("hammer")) {
                if(!(victim instanceof EntityPlayer && ModItems.isInfinite((EntityPlayer)victim))) {
                    int i = 10;
                    victim.addVelocity((double) (-MathHelper.sin(player.rotationYaw * (float) Math.PI / 180.0F) * (float) i * 0.5F), 2.0D, (double) (MathHelper.cos(player.rotationYaw * (float) Math.PI / 180.0F) * (float) i * 0.5F));
                }
            }
        }
        return true;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {
        return super.onBlockStartBreak(stack, pos, player);
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
    public boolean hasEffect(ItemStack stack) {
        return false;
    }
}
