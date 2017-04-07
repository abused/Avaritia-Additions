package abused_master.avaritiaadditions.items.tools;

import abused_master.avaritiaadditions.AvaritiaAdditions;
import abused_master.avaritiaadditions.items.entity.EntityHeavenArrow;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ItemBowInfinity extends Item {

    public ItemBowInfinity()
    {
        this.setUnlocalizedName("infinity_bow");
        this.maxStackSize = 1;
        this.setMaxDamage(9999);
        this.setCreativeTab(AvaritiaAdditions.AvaritiaAdditions);
        GameRegistry.register(this.setRegistryName("infinity_bow"));
    }

    @Override
    public void setDamage(ItemStack stack, int damage){
        super.setDamage(stack, 0);
    }

    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count)
    {
        if (count == 1) {
            this.fire(stack, player.worldObj, player, 0);
        }
    }

    public void fire(ItemStack stack, World world, EntityLivingBase playerBase, int useCount)
    {
        EntityPlayer player = (EntityPlayer) playerBase;
        int max = this.getMaxItemUseDuration(stack);
        float maxf = (float)max;
        int j = max - useCount;
        boolean flag = true;//player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;

        if (flag || player.inventory.hasItemStack(new ItemStack(Items.ARROW)))
        {
            float f = j / maxf;
            f = (f * f + f * 2.0F) / 3.0F;

            if (f < 0.1)
            {
                return;
            }

            if (f > 1.0)
            {
                f = 1.0F;
            }

            EntityArrow entityarrow = new EntityHeavenArrow(world, player);
            entityarrow.setDamage(40.0);


            if (f == 1.0F)
            {
                entityarrow.setIsCritical(true);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

            if (k > 0)
            {
                entityarrow.setDamage(entityarrow.getDamage() + k * 1 + 1);
            }

            int l = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

            if (l > 0)
            {
                entityarrow.setKnockbackStrength(l);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
            {
                entityarrow.setFire(100);
            }

            stack.damageItem(1, player);
            world.playSound(player, player.getPosition(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.MUSIC, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            ItemStack itemstack = this.findAmmo(player);

            if (flag)
            {
                entityarrow.pickupStatus = EntityArrow.PickupStatus.getByOrdinal(2);
            }
            else
            {
                --itemstack.stackSize;

                if (itemstack.stackSize == 0)
                {
                    player.inventory.deleteStack(itemstack);
                }
            }

            if (!world.isRemote)
            {
                world.spawnEntityInWorld(entityarrow);
            }
        }
    }

    private ItemStack findAmmo(EntityPlayer player)
    {
        if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND)))
        {
            return player.getHeldItem(EnumHand.OFF_HAND);
        }
        else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND)))
        {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        }
        else
        {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (this.isArrow(itemstack))
                {
                    return itemstack;
                }
            }

            return null;
        }
    }

    protected boolean isArrow(@Nullable ItemStack stack)
    {
        return stack != null && stack.getItem() instanceof ItemArrow;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 8;
    }

    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }

    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        ArrowNockEvent event = new ArrowNockEvent(player, stack, hand, world, true);
        MinecraftForge.EVENT_BUS.post(event);

        player.setHeldItem(hand, stack);
        return super.onItemRightClick(stack, world, player, hand);
    }

    public int getItemEnchantability()
    {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
}