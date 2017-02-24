package abused_master.avaritiaadditions.items;

import abused_master.avaritiaadditions.AvaritiaAdditions;
import net.minecraft.item.Item;

public class BaseItem extends Item {

    public BaseItem(String regName) {
        this.setUnlocalizedName(regName);
        this.setCreativeTab(AvaritiaAdditions.AvaritiaAdditions);
    }

}
