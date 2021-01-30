package crimson_twilight.unnamed_magitech_mod.common.item;

import crimson_twilight.unnamed_magitech_mod.api.capability.player_ki.CapabilityPlayerKi;
import crimson_twilight.unnamed_magitech_mod.api.capability.player_ki.IPlayerKi;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;

public class ItemBodyCleansingPill extends ItemKiPill
{
    int amount;
    public ItemBodyCleansingPill(String name, int amount) {
        super(name, null, 0, -1);
        this.amount = amount;
    }

    @Override
    public ItemStack consume(ItemStack stack, World world, PlayerEntity playerIn)
    {
        LazyOptional<IPlayerKi> capability = playerIn.getCapability(CapabilityPlayerKi.PLAYER_KI_CAPABILITY);
        IPlayerKi ki = capability.orElseThrow(() -> new IllegalArgumentException("at login"));
        ki.addCorruption(-this.amount);
        if(playerIn.getActivePotionEffect(Effects.HUNGER).getAmplifier() < 1)
        {
            playerIn.addPotionEffect(new EffectInstance(Effects.HUNGER, 5, 1));
        } else if(playerIn.getActivePotionEffect(Effects.HUNGER).getAmplifier() == 1)
        {
            int dur = 5 + playerIn.getActivePotionEffect(Effects.HUNGER).getDuration();
            playerIn.addPotionEffect(new EffectInstance(Effects.HUNGER, dur, 1));
        }
        if(playerIn.getActivePotionEffect(Effects.NAUSEA).getAmplifier() < 1)
        {
            playerIn.addPotionEffect(new EffectInstance(Effects.NAUSEA, 5, 1));
        } else if(playerIn.getActivePotionEffect(Effects.NAUSEA).getAmplifier() == 1)
        {
            int dur = 5 + playerIn.getActivePotionEffect(Effects.NAUSEA).getDuration();
            playerIn.addPotionEffect(new EffectInstance(Effects.NAUSEA, dur, 1));
        }
        return super.consume(stack, world, playerIn);
    }
}
