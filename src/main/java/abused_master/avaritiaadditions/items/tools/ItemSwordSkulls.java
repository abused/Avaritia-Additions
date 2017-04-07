package abused_master.avaritiaadditions.items.tools;

import java.util.List;

import abused_master.avaritiaadditions.AvaritiaAdditions;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemSwordSkulls extends ItemSword {

    public ItemSwordSkulls(){
        super(ToolMaterial.DIAMOND);
        setUnlocalizedName("skullfire_sword");
        setCreativeTab(AvaritiaAdditions.AvaritiaAdditions);
        GameRegistry.register(this.setRegistryName("skullfire_sword"));
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return EnumRarity.EPIC;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public void addInformation(ItemStack item, EntityPlayer player, List tooltip, boolean wut) {
    	tooltip.add(TextFormatting.DARK_GRAY +""+ TextFormatting.ITALIC + I18n.format("tooltip.skullfire_sword.desc"));
    }
}
