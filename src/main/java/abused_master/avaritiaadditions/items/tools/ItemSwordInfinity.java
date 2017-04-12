package abused_master.avaritiaadditions.items.tools;

import abused_master.avaritiaadditions.AvaritiaAdditions;
import abused_master.avaritiaadditions.items.entity.EntityImmortalItem;
import abused_master.avaritiaadditions.registry.ModItems;
import abused_master.avaritiaadditions.render.ICosmicRenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.model.Models;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;

public class ItemSwordInfinity extends ItemSword {

    private static final ToolMaterial opSword = EnumHelper.addToolMaterial("INFINITY_SWORD", 32, 9999, 9999F, -3.0F, 200);
    public static EnumRarity cosmic = EnumHelper.addRarity("COSMIC", TextFormatting.RED, "Cosmic");

    public static Field stupidMojangProtectedVariable;

    public ItemSwordInfinity(){
        super(opSword);
        setUnlocalizedName("infinity_sword");
        setCreativeTab(AvaritiaAdditions.AvaritiaAdditions);
        GameRegistry.register(this.setRegistryName("infinity_sword"));
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase victim, EntityLivingBase player){
        if(player.worldObj.isRemote)
            return true;
        if(victim instanceof EntityPlayer){
            EntityPlayer pvp = (EntityPlayer)victim;
            if(ModItems.isInfinite(pvp)){
                    victim.attackEntityFrom(new DamageSourceInfinitySword(player).setDamageBypassesArmor(), 4.0F);
                return true;
            }
            if(pvp.getHeldItem(player.getActiveHand()) != null && pvp.getHeldItem(player.getActiveHand()).getItem() == ModItems.infinity_sword)
                return true;
        }

        try {
            stupidMojangProtectedVariable.setInt(victim, 60);
        }
        catch(Exception e){
        }
        victim.setHealth(0);
        victim.onDeath(new EntityDamageSource("infinity", player));
        return true;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity){
        if(!entity.worldObj.isRemote && entity instanceof EntityPlayer) {
            EntityPlayer victim = (EntityPlayer)entity;
            if(victim.capabilities.isCreativeMode && !victim.isDead && victim.getHealth() > 0 && !ModItems.isInfinite(victim)){
                victim.setHealth(0);
                victim.onDeath(new EntityDamageSource("infinity", player));
                return true;
            }
        }
        return false;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return cosmic;
    }

    @Override
    public void setDamage(ItemStack stack, int damage){
        super.setDamage(stack, 0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return false;
    }

}
