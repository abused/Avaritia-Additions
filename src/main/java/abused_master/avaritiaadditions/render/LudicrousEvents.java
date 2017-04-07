package abused_master.avaritiaadditions.render;

import abused_master.avaritiaadditions.items.tools.ItemSwordInfinity;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LudicrousEvents {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        if (event.getItemStack().getItem() instanceof ItemSwordInfinity) {
            for(int x = 0;x < event.getToolTip().size();x++){
                if(event.getToolTip().get(x).contains(I18n.format("attribute.name.generic.attackDamage"))
                        || event.getToolTip().get(x).contains(I18n.format("Attack Damage"))){
                    event.getToolTip().set(x, TextFormatting.BLUE + "+" + Text.makeFabulous(I18n.format("tip.infinity")) + " " + TextFormatting.BLUE + I18n.format("attribute.name.generic.attackDamage"));
                    return;
                }
            }
        }
    }
}
