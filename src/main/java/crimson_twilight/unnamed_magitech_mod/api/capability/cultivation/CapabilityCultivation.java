package crimson_twilight.unnamed_magitech_mod.api.capability.cultivation;

import crimson_twilight.unnamed_magitech_mod.UnnamedMagitechMod;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityCultivation implements ICapabilitySerializable<CompoundNBT>
{
    @CapabilityInject(ICultivation.class)
    public static final Capability<ICultivation> CULTIVATION_CAPABILITY = null;
    public static final ResourceLocation RES = new ResourceLocation(UnnamedMagitechMod.MODID, "cultivation");
    private LazyOptional<ICultivation> instance = LazyOptional.of(CULTIVATION_CAPABILITY::getDefaultInstance);

    public static void register()
    {
        CapabilityManager.INSTANCE.register(ICultivation.class, new CultivationStorage(), Cultivation::new);
    }

    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        return CULTIVATION_CAPABILITY.orEmpty(cap, instance);
    }

    @Override
    public CompoundNBT serializeNBT()
    {
        return (CompoundNBT) CULTIVATION_CAPABILITY.getStorage().writeNBT(CULTIVATION_CAPABILITY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt)
    {
        CULTIVATION_CAPABILITY.getStorage().readNBT(CULTIVATION_CAPABILITY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null, nbt);
    }
}
