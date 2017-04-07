package abused_master.avaritiaadditions.tile.collector;

import abused_master.avaritiaadditions.config.Config;
import abused_master.avaritiaadditions.registry.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class TileCollector extends TileEntity implements ISidedInventory, ITickable {

    public ItemStack[] collectorInv = new ItemStack[1];
    private ItemStack neutrons;
    private int progress;

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.neutrons = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("Neutrons"));
        this.progress = compound.getInteger("Progress");
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("Progress", this.progress);
        if(neutrons != null) {
            NBTTagCompound produce = new NBTTagCompound();
            neutrons.writeToNBT(produce);
            compound.setTag("Neutrons", produce);
        }
        else
            compound.removeTag("Neutrons");
        return super.writeToNBT(compound);
    }

    @Override
    public void update() {
        if(++progress >= Config.NeutronCollector){
            if(neutrons == null)
                neutrons = new ItemStack(ModItems.NeutronsPile);
            else if(neutrons.getItem() == ModItems.NeutronsPile && neutrons.stackSize < 64)
                neutrons.stackSize++;
            progress = 0;
            markDirty();
        }

        BlockPos collectorPos = getPos();
        for (EnumFacing side : EnumFacing.VALUES) {
            BlockPos cip = collectorPos.offset(side);
            TileEntity ite = worldObj.getTileEntity(cip);
            if (ite instanceof IInventory) {
                TileEntityHopper.putStackInInventoryAllSlots((IInventory) ite, neutrons, side.getOpposite());
            }
        }
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return false;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return true;
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Nullable
    @Override
    public ItemStack getStackInSlot(int index) {
        return neutrons;
    }

    @Nullable
    @Override
    public ItemStack decrStackSize(int index, int count) {
        if(neutrons == null)
            return null;
        else {
            if(count < neutrons.stackSize){
                ItemStack take = neutrons.splitStack(count);
                if(neutrons.stackSize <= 0)
                    neutrons = null;
                return take;
            }
            else {
                ItemStack take = neutrons;
                neutrons = null;
                return take;
            }
        }
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
        neutrons = stack;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public String getName() {
        return "Collector";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }
}
