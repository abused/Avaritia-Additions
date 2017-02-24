package abused_master.avaritiaadditions.tile;

import abused_master.avaritiaadditions.gui.GuiCompressor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

import javax.annotation.Nullable;

public class TileCompressor extends TileEntity implements ISidedInventory, ITickable {

    public ItemStack[] compressorInv = new ItemStack[2];
    public ItemStack input, processing, output;
    public int progress = 0;
    public int target = 0;
    public String ingredient;

    public int packetCount;
    public boolean packet;

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.input = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("Input"));
        this.processing = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("Processing"));
        this.output = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("Output"));
        if(processing != null) {
            this.target = CompressorManager.getPrice(processing);
            if(target != 0) {
                this.progress = compound.getInteger("Progress");
                if (compound.hasKey("Ingredient"))
                    this.ingredient = compound.getString("Ingredient");
            }
            else
                processing = null;
        }
        else {
            progress = 0;
            target = 0;
            ingredient = null;
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        if(input != null) {
            NBTTagCompound produce = new NBTTagCompound();
            input.writeToNBT(produce);
            compound.setTag("Input", produce);
        }
        else
            compound.removeTag("Input");
        if(processing != null) {
            NBTTagCompound produce = new NBTTagCompound();
            processing.writeToNBT(produce);
            compound.setTag("Processing", produce);
            compound.setInteger("Progress", this.progress);
            if(ingredient != null)
                compound.setString("Ingredient", this.ingredient);
            else
                compound.removeTag("Ingredient");
        }
        else {
            compound.removeTag("Processing");
            compound.removeTag("Progress");
            compound.removeTag("Target");
            compound.removeTag("Ingredient");
        }
        if(output != null) {
            NBTTagCompound produce = new NBTTagCompound();
            output.writeToNBT(produce);
            compound.setTag("Output", produce);
        }
        else
            compound.removeTag("Output");
        return super.writeToNBT(compound);
    }

    @Override
    public void update() {
        if(packetCount > 0)
            packetCount--;
        if(input != null && (processing == null || progress < target) ){
            if(CompressorManager.getOutput(input) != null && (output == null || CompressorManager.getOutput(input).isItemEqual(output))) {
                if (processing == null) {
                    processing = CompressorManager.getOutput(input);
                    target = CompressorManager.getCost(input);
                    ingredient = CompressorManager.getName(input);
                }
                if (CompressorManager.getOutput(input).isItemEqual(processing)) {
                    int needed = target - progress;
                    if(needed >= input.stackSize) {
                        progress += input.stackSize;
                        input = null;
                    }
                    else {
                        progress = target;
                        input.stackSize -= needed;
                    }
                }
                markDirty();
                packet = true;
            }
        }
        if (progress >= target && processing != null && (output == null || (output.isItemEqual(processing) && output.stackSize + processing.stackSize <= output.getMaxStackSize()))) {
            if(output == null)
                output = processing.copy();
            else if(output.isItemEqual(processing))
                output.stackSize+= processing.stackSize;

            progress -= target;
            if(progress == 0) {
                processing = null;
                ingredient = null;
            }
            markDirty();
            packet = true;
        }
        if(packet && packetCount <= 0) {
            packetCount = 10;
            packet = false;
        }
    }

    public int getProgress(){
        return progress;
    }

    public int getTarget(){
        return target;
    }

    public String getIngredient(){
        return ingredient;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        if(index == 1 && direction != direction.WEST)
            return true;
        return false;
    }

    @Override
    public int getSizeInventory() {
        return 2;
    }

    @Nullable
    @Override
    public ItemStack getStackInSlot(int index) {
        if(index == 0)
            return input;
        else
            return output;
    }

    @Nullable
    @Override
    public ItemStack decrStackSize(int index, int count) {
        if(index == 0) {
            if (input == null)
                return null;
            else {
                if (count < input.stackSize) {
                    ItemStack take = input.splitStack(count);
                    if (input.stackSize <= 0)
                        input = null;
                    return take;
                } else {
                    ItemStack take = input;
                    input = null;
                    return take;
                }
            }
        }
        else if (index == 1){
            if (output == null)
                return null;
            else {
                if (count < output.stackSize) {
                    ItemStack take = output.splitStack(count);
                    if (output.stackSize <= 0)
                        output = null;
                    return take;
                } else {
                    ItemStack take = output;
                    output = null;
                    return take;
                }
            }
        }
        return null;
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.compressorInv, index);
    }

    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
        if(index == 0)
            input = stack;
        else if(index == 1)
            output = stack;
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
        if(stack == null)
            return false;
        if(index == 0){
            if(processing == null)
                return true;
            if(CompressorManager.getOutput(stack) == null)
                return false;
            if(CompressorManager.getOutput(stack).isItemEqual(processing))
                return true;
        }
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
        for (int i = 0; i < this.compressorInv.length; ++i)
        {
            this.compressorInv[i] = null;
        }
    }

    @Override
    public String getName() {
        return "TileCompressor";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, net.minecraft.util.EnumFacing facing)
    {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }
}
