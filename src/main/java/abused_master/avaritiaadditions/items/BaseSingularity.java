package abused_master.avaritiaadditions.items;

import abused_master.avaritiaadditions.AvaritiaAdditions;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BaseSingularity extends Item {

    public BaseSingularity(String regName){
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setUnlocalizedName(regName);
        this.setCreativeTab(AvaritiaAdditions.AvaritiaAdditions);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return EnumRarity.UNCOMMON;
    }
}
