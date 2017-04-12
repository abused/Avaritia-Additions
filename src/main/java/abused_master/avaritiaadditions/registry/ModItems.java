package abused_master.avaritiaadditions.registry;

import abused_master.avaritiaadditions.items.*;
import abused_master.avaritiaadditions.items.tools.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

    public static Item DiamondLattice;
    public static Item NeutronsPile;
    public static Item NeutroniumNugget;
    public static Item NeutroniumIngot;
    public static Item InfinityCatalyst;
    public static Item InfinityIngot;
    public static Item RecordFragment;
    public static Item EndestPearl;
    public static Item UltimateStew;
    public static Item CosmicMeatballs;
    public static Item MatterCluster;

    public static Item IronSingularity;
    public static Item GoldSingularity;
    public static Item LapisSingularity;
    public static Item RedstoneSingularity;
    public static Item QuartzSingularity;
    public static Item CopperSingularity;
    public static Item TinSingularity;
    public static Item LeadSingularity;
    public static Item SilverSingularity;
    public static Item NickelSingularity;

    public static Item infinity_helm;
    public static Item infinity_armor;
    public static Item infinity_pants;
    public static Item infinity_shoes;

    public static Item infinity_pickaxe;
    public static Item infinity_sword;
    public static Item infinity_shovel;
    public static Item infinity_axe;
    public static Item infinity_bow;
    public static Item skulls_sword;

    public static void init() {
        DiamondLattice = regResource("diamond_lattice");
        NeutronsPile = regResource("neutrons_pile");
        NeutroniumNugget = regResource("neutronium_nugget");
        NeutroniumIngot = regResource("neutronium_ingot");
        InfinityCatalyst = regResource("infinity_catalyst");
        InfinityIngot = regResource("infinity_ingot");
        RecordFragment = regResource("record_fragment");
        //EndestPearl = regResource("endest_pearl");
        UltimateStew = new UltimateStew();
        CosmicMeatballs = new CosmicMeatballs();
        IronSingularity = regSingularity("iron_singularity");
        GoldSingularity =regSingularity("gold_singularity");
        LapisSingularity = regSingularity("lapis_singularity");
        RedstoneSingularity = regSingularity("redstone_singularity");
        QuartzSingularity = regSingularity("quartz_singularity");
        CopperSingularity = regSingularity("copper_singularity");
        TinSingularity = regSingularity("tin_singularity");
        LeadSingularity = regSingularity("lead_singularity");
        SilverSingularity = regSingularity("silver_singularity");
        NickelSingularity = regSingularity("nickel_singularity");

        EndestPearl = GameRegistry.register(new ItemEndestPearl().setRegistryName("endest_pearl"));

        infinity_helm = GameRegistry.register(new ItemArmorInfinity(EntityEquipmentSlot.HEAD).setRegistryName("infinity_armor_head"));
        infinity_armor = GameRegistry.register(new ItemArmorInfinity(EntityEquipmentSlot.CHEST).setRegistryName("infinity_armor_chest"));
        infinity_pants = GameRegistry.register(new ItemArmorInfinity(EntityEquipmentSlot.LEGS).setRegistryName("infinity_armor_legs"));
        infinity_shoes = GameRegistry.register(new ItemArmorInfinity(EntityEquipmentSlot.FEET).setRegistryName("infinity_armor_feet"));

        infinity_pickaxe = new ItemPickaxeInfinity();
        infinity_sword = new ItemSwordInfinity();
        infinity_shovel = new ItemShovelInfinity();
        infinity_axe = new ItemAxeInfinity();
        infinity_bow = new ItemInfinityBow();
        skulls_sword = new ItemSwordSkulls();
        MatterCluster = new ItemMatterCluster();
    }

    private static Item regResource(String regName) {
        final Item resource = new BaseItem(regName);
        return regItemResource(regName, resource);
    }

    private static Item regSingularity(String regName) {
        final Item resource = new BaseSingularity(regName);
        return regItemResource(regName, resource);
    }

    private static Item regItemResource(String regName, Item item) {
        item.setRegistryName(regName);
        GameRegistry.register(item);
        return item;
    }

    public static void initRenders() {
        reg(DiamondLattice);
        reg(NeutronsPile);
        reg(NeutroniumNugget);
        reg(NeutroniumIngot);
        reg(InfinityCatalyst);
        reg(InfinityIngot);
        reg(RecordFragment);
        reg(EndestPearl);
        reg(UltimateStew);
        reg(CosmicMeatballs);
        reg(CosmicMeatballs);
        reg(IronSingularity);
        reg(GoldSingularity);
        reg(LapisSingularity);
        reg(RedstoneSingularity);
        reg(QuartzSingularity);
        reg(CopperSingularity);
        reg(TinSingularity);
        reg(LeadSingularity);
        reg(SilverSingularity);
        reg(NickelSingularity);

        reg(infinity_helm);
        reg(infinity_armor);
        reg(infinity_pants);
        reg(infinity_shoes);

        reg(infinity_pickaxe);
        reg(infinity_sword);
        reg(infinity_shovel);
        reg(infinity_axe);
        reg(infinity_bow);
        reg(skulls_sword);
        reg(MatterCluster);
    }

    public static void reg(Item item) {
        ModelResourceLocation res = new ModelResourceLocation(item.getRegistryName().toString(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, res);
    }
    @SideOnly(Side.CLIENT)
    public static void registerColor() {
        setItemColor(IronSingularity, 0x7F7F7F, 0xBFBFBF);
        setItemColor(GoldSingularity, 0xdba213, 0xE8EF23);
        setItemColor(LapisSingularity, 0x224baf, 0x5a82e2);
        setItemColor(RedstoneSingularity, 0x900000, 0xDF0000);
        setItemColor(QuartzSingularity, 0x94867d, 0xeeebe6);
        setItemColor(CopperSingularity, 0x89511A, 0xE47200);
        setItemColor(TinSingularity, 0x9BA9B2, 0xA5C7DE);
        setItemColor(LeadSingularity, 0x3E3D4E, 0x444072);
        setItemColor(SilverSingularity, 0xD5D5D5, 0xF9F9F9);
        setItemColor(NickelSingularity, 0xC4C698, 0xDEE187);
    }

    @SideOnly(Side.CLIENT)
    public static void setItemColor(Item item, int color, int color2){
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemColorHandler(color, color2), item);
    }

    public static boolean isInfinite(EntityPlayer player){
        ItemStack head = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        ItemStack legs = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        ItemStack feet = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);

        if(head == null || chest == null
                || legs == null || feet == null)
            return false;
        if(feet.getItem() == infinity_shoes && legs.getItem() == infinity_pants
                && chest.getItem() == infinity_armor && head.getItem() == infinity_helm)
            return true;
        else
            return false;
    }
}
