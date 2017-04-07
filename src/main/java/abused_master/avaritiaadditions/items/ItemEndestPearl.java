package abused_master.avaritiaadditions.items;

import abused_master.avaritiaadditions.AvaritiaAdditions;
import abused_master.avaritiaadditions.items.entity.EntityEndestPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemEndestPearl extends ItemEnderPearl {

    public ItemEndestPearl() {
        this.setUnlocalizedName("endest_pearl");
        this.maxStackSize = 16;
        this.setCreativeTab(AvaritiaAdditions.AvaritiaAdditions);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
    {
        if (!player.capabilities.isCreativeMode)
        {
            --stack.stackSize;
        }

        world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!world.isRemote)
        {
            world.spawnEntityInWorld(new EntityEndestPearl(world, player));
        }

        return super.onItemRightClick(stack, world, player, hand);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return EnumRarity.RARE;
    }
}
