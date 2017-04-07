package abused_master.avaritiaadditions.items;

import abused_master.avaritiaadditions.AvaritiaAdditions;
import abused_master.avaritiaadditions.registry.ModItems;
import abused_master.avaritiaadditions.render.InfinityWingsModel;
import abused_master.avaritiaadditions.render.Text;
import com.google.common.collect.Multimap;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemArmorInfinity extends ItemArmor {

    public static EnumRarity cosmic = EnumHelper.addRarity("COSMIC", TextFormatting.RED, "Cosmic");
    public static final ArmorMaterial infinite_armor = EnumHelper.addArmorMaterial("infinity", "avaritiaadditions:infinity_armor", 9999, new int[]{6, 16, 12, 6}, 1000, null, 0);
    public EntityEquipmentSlot slot;

    public static List<String> playersWithHat = new ArrayList<String>();
    public static List<String> playersWithChest = new ArrayList<String>();
    public static List<String> playersWithLeg = new ArrayList<String>();
    public static List<String> playersWithFoot = new ArrayList<String>();

    public ItemArmorInfinity(EntityEquipmentSlot slot){
        super(infinite_armor, 0, slot);
        this.slot = slot;
        setCreativeTab(AvaritiaAdditions.AvaritiaAdditions);
        setUnlocalizedName("infinity_armor_" + slot);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return super.getArmorTexture(stack, entity, slot, type);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        if(!entityLiving.onGround) {
            return InfinityWingsModel.INSTANCE;
        }
        return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
    }

    @Override
    public void setDamage(ItemStack stack, int damage){
        super.setDamage(stack, 0);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (armorType == EntityEquipmentSlot.HEAD) {
            player.setAir(300);
            player.getFoodStats().addStats(20, 20F);
        } else if (armorType == EntityEquipmentSlot.CHEST) {
            Collection effects = player.getActivePotionEffects();
            if (effects.size() > 0) {
                ArrayList<Potion> bad = new ArrayList<Potion>();
                for (Object effect : effects) {
                    if (effect instanceof PotionEffect) {
                        PotionEffect potion = (PotionEffect) effect;
                    }
                }
                if (bad.size() > 0) {
                    for (Potion potion : bad) {
                        player.removeActivePotionEffect(potion);
                    }
                }
            }
        } else if (armorType == EntityEquipmentSlot.LEGS) {
            if (player.isBurning())
                player.extinguish();
        }

        String key = playerKey(player);

        // hat
        Boolean hasHat = playerHasHat(player);
        if (playersWithHat.contains(key)) {
            if (hasHat) {

            } else {
                playersWithHat.remove(key);
            }
        } else if (hasHat) {
            playersWithHat.add(key);
        }

        // chest
        Boolean hasChest = playerHasChest(player);
        if (playersWithChest.contains(key)) {
            if (hasChest) {
                player.capabilities.allowFlying = true;
            } else {
                if (!player.capabilities.isCreativeMode) {
                    player.capabilities.allowFlying = false;
                    player.capabilities.isFlying = false;
                }
                playersWithChest.remove(key);
            }
        } else if (hasChest) {
            playersWithChest.add(key);
        }

        // legs
        Boolean hasLeg = playerHasLeg(player);
        if (playersWithLeg.contains(key)) {
            if (hasLeg) {

            } else {
                playersWithLeg.remove(key);
            }
        } else if (hasLeg) {
            playersWithLeg.add(key);
        }

        boolean hasFoot = playerHasFoot(player);
        if(hasFoot) {
            boolean flying = player.capabilities.isFlying;
            if(player.onGround) {
                player.capabilities.setPlayerWalkSpeed(0.30f);
            }else if(flying) {
                player.capabilities.setFlySpeed(0.15f);
            }
            //if(mc.gameSettings.keyBindJump.isPressed()) {
            //    player.motionY += 0.32f;
            //}
        }
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return this.cosmic;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap multimap = super.getAttributeModifiers(slot, stack);
        //if(armorType == 3)
        //    multimap.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Armor modifier", 0.7, 1));
        return multimap;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (this.slot == EntityEquipmentSlot.FEET) {
            list.add("");
            list.add(TextFormatting.BLUE+"+"+TextFormatting.ITALIC+ Text.makeSANIC("SANIC")+TextFormatting.RESET+""+TextFormatting.BLUE+"% Speed");
        }
        super.addInformation(stack, player, list, par4);
    }

    @Override
    public boolean hasCustomEntity (ItemStack stack) {
        return true;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return false;
    }

    public static boolean playerHasHat(EntityPlayer player) {
        ItemStack head = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        return head != null && head.getItem() == ModItems.infinity_helm;
    }

    public static boolean playerHasChest(EntityPlayer player) {
        ItemStack chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        return chest != null && chest.getItem() == ModItems.infinity_armor;
    }

    public static boolean playerHasLeg(EntityPlayer player) {
        ItemStack legs = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        return legs != null && legs.getItem() == ModItems.infinity_pants;
    }

    public static boolean playerHasFoot(EntityPlayer player) {
        ItemStack boots = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        return boots != null && boots.getItem() == ModItems.infinity_shoes;
    }

    public static String playerKey(EntityPlayer player) {
        return player.getGameProfile().getName() +":"+ player.worldObj.isRemote;
    }

    public static class abilityHandler {
        @SubscribeEvent
        public void jumpBoost(LivingEvent.LivingJumpEvent event) {
            if (event.getEntityLiving() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)event.getEntityLiving();
                String key = playerKey(player);
                if (playersWithFoot.contains(key)) {
                    player.motionY += 0.28f;
                }
            }
        }
    }
}
