package crimson_twilight.unnamed_magitech_mod.common.item;

import crimson_twilight.unnamed_magitech_mod.api.capability.cultivation.CapabilityCultivation;
import crimson_twilight.unnamed_magitech_mod.api.capability.cultivation.ICultivation;
import crimson_twilight.unnamed_magitech_mod.api.capability.player_ki.CapabilityPlayerKi;
import crimson_twilight.unnamed_magitech_mod.api.capability.player_ki.IPlayerKi;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

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
        LazyOptional<IPlayerKi> capabilityKi = playerIn.getCapability(CapabilityPlayerKi.PLAYER_KI_CAPABILITY);
        IPlayerKi ki = capabilityKi.orElseThrow(() -> new IllegalArgumentException("at login"));
        if (this.canGrow) {
            ki.setHasVeins(true);
            LazyOptional<ICultivation> capabilityCulti = playerIn.getCapability(CapabilityCultivation.CULTIVATION_CAPABILITY);
            ICultivation Culti = capabilityCulti.orElseThrow(() -> new IllegalArgumentException("at login"));
            if(Culti.getAscensionLevel() == 0 && Culti.getCultivationRank() == 0) Culti.addCultivationRank(1);
        }
        if (ki.hasVeins()) ki.addMaxKiAmount(this.bonus);
        return super.consume(stack, world, playerIn);
    }
}
