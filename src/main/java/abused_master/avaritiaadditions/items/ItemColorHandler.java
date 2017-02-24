package abused_master.avaritiaadditions.items;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class ItemColorHandler implements IItemColor {

    public int layer0Color;
    public int layer1Color;

    public ItemColorHandler(int layer0Color, int layer1Color){
        this.layer0Color = layer0Color;
        this.layer1Color = layer1Color;
    }

    @Override
    public int getColorFromItemstack(ItemStack stack, int tintIndex){
        if(tintIndex == 0)
            return this.layer0Color;
        if(tintIndex == 1)
            return this.layer1Color;
        return 0;
    }
}