package crimson_twilight.unnamed_magitech_mod.common.item;

import crimson_twilight.unnamed_magitech_mod.api.capability.player_ki.CapabilityPlayerKi;
import crimson_twilight.unnamed_magitech_mod.api.capability.player_ki.IPlayerKi;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;

public class ItemSpiritVeinPill extends ItemKiPill
{
    private boolean canGrow = false;
    private int bonus = 0;

    public ItemSpiritVeinPill(String name, int bonus, int max) {
        super(name, null, 0, max);
        this.bonus = bonus;
    }

    public ItemSpiritVeinPill(String name, int bonus, int max, boolean canGrow) {
        super(name, null, 0, max);
        this.bonus = bonus;
        this.canGrow = canGrow;
    }

    @Override
    public boolean canConsume(World worldIn, PlayerEntity playerIn, ItemStack stack) {
        LazyOptional<IPlayerKi> capability = playerIn.getCapability(CapabilityPlayerKi.PLAYER_KI_CAPABILITY);
        IPlayerKi ki = capability.orElseThrow(() -> new IllegalArgumentException("at login"));
        if(this.canGrow) return super.canConsume(worldIn, playerIn, stack);
        else if(ki.hasVeins()) return super.canConsume(worldIn, playerIn, stack);
        return false;
    }

    @Override
    public ItemStack consume(ItemStack stack, World world, PlayerEntity playerIn)
    {
        LazyOptional<IPlayerKi> capability = playerIn.getCapability(CapabilityPlayerKi.PLAYER_KI_CAPABILITY);
        IPlayerKi ki = capability.orElseThrow(() -> new IllegalArgumentException("at login"));
        if (this.canGrow) ki.setHasVeins(true);
        if (ki.hasVeins()) ki.addMaxKiAmount(this.bonus);
        return super.consume(stack, world, playerIn);
    }
}
