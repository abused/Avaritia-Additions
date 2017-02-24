package abused_master.avaritiaadditions.tile;

import net.minecraft.item.ItemStack;

public class CompressorRecipe {

    private static final CompressorRecipe comp = new CompressorRecipe();

    public static CompressorRecipe instance()
    {
        return comp;
    }

    public CompressorRecipe() {

    }

    public ItemStack product;
    public int cost;
    public ItemStack input;
    public boolean specific;
    public static int modifier = 0;
    public static int multiplier = 1;

    public CompressorRecipe(ItemStack output, int amount, ItemStack ingredient, boolean exact){
        product = output;
        cost = amount;
        input = ingredient;
        specific = exact;
    }

    public CompressorRecipe(ItemStack output, int amount, ItemStack ingredient){
        this(output, amount, ingredient, false);
    }

    public ItemStack getOutput(){
        return product.copy();
    }

    public int getCost(){
        if(specific)
            return cost;
        else
            return this.balanceCost(cost);
    }

    public boolean validInput(ItemStack ingredient){
        return ingredient.isItemEqual(input);
    }

    public String getIngredientName(){
        return input.getDisplayName();
    }

    public Object getIngredient(){
        return input;
    }

    public static int balanceCost(int cost){
        return (cost + modifier) * multiplier;
    }
}
