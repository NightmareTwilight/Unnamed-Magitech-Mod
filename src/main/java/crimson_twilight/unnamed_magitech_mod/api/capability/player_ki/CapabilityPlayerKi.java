package crimson_twilight.unnamed_magitech_mod.api.capability.player_ki;

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

public class CapabilityPlayerKi implements ICapabilitySerializable<CompoundNBT>
{
    @CapabilityInject(IPlayerKi.class)
    public static final Capability<IPlayerKi> PLAYER_KI_CAPABILITY = null;
    public static final ResourceLocation RES = new ResourceLocation(UnnamedMagitechMod.MODID, "player_ki");
    private LazyOptional<IPlayerKi> instance = LazyOptional.of(PLAYER_KI_CAPABILITY::getDefaultInstance);

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IPlayerKi.class, new PlayerKiStorage(), PlayerKi::new);
    }

    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        return PLAYER_KI_CAPABILITY.orEmpty(cap, instance);
    }

    @Override
    public CompoundNBT serializeNBT()
    {
        return (CompoundNBT) PLAYER_KI_CAPABILITY.getStorage().writeNBT(PLAYER_KI_CAPABILITY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt)
    {
        PLAYER_KI_CAPABILITY.getStorage().readNBT(PLAYER_KI_CAPABILITY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null, nbt);
    }
}