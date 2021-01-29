package crimson_twilight.unnamed_magitech_mod.common.item;

import crimson_twilight.unnamed_magitech_mod.api.capability.player_ki.CapabilityPlayerKi;
import crimson_twilight.unnamed_magitech_mod.api.capability.player_ki.IPlayerKi;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;

public class ItemKiGatheringPill extends ItemKiPill
{
    int increase;
    public ItemKiGatheringPill(String name, int increase, int max) {
        super(name, null, 0, max);
        this.increase = increase;
    }

    @Override
    public boolean canConsume(World worldIn, PlayerEntity playerIn, ItemStack stack) {
        LazyOptional<IPlayerKi> capability = playerIn.getCapability(CapabilityPlayerKi.PLAYER_KI_CAPABILITY);
        IPlayerKi ki = capability.orElseThrow(() -> new IllegalArgumentException("at login"));
        if(ki.hasVeins()) return super.canConsume(worldIn, playerIn, stack);
        return false;
    }

    @Override
    public ItemStack consume(ItemStack stack, World world, PlayerEntity playerIn)
    {
        LazyOptional<IPlayerKi> capability = playerIn.getCapability(CapabilityPlayerKi.PLAYER_KI_CAPABILITY);
        IPlayerKi ki = capability.orElseThrow(() -> new IllegalArgumentException("at login"));
        if (ki.hasVeins()) ki.addKiGenAmount(this.increase);
        return super.consume(stack, world, playerIn);
    }
}
