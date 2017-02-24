package abused_master.avaritiaadditions.items;

import abused_master.avaritiaadditions.AvaritiaAdditions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class UltimateStew extends ItemFood {

    public UltimateStew() {
        super(20, 20.0f, false);
        this.setCreativeTab(AvaritiaAdditions.AvaritiaAdditions);
        this.setRegistryName("ultimate_stew");
        this.setUnlocalizedName("ultimate_stew");
        GameRegistry.register(this);
}

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 300, 4));
        super.onFoodEaten(stack, worldIn, player);
    }
}
