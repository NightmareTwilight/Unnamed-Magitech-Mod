package crimson_twilight.unnamed_magitech_mod.api.capability;

import crimson_twilight.unnamed_magitech_mod.UnnamedMagitechMod;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Pabilo8
 * @since 24.01.2021
 */
public class CapabilityPlayerUseCount implements ICapabilitySerializable<CompoundNBT>
{
	@CapabilityInject(IUseCount.class)
	public static final Capability<IUseCount> ITEM_USE_COUNT = null;
	public static final ResourceLocation RES = new ResourceLocation(UnnamedMagitechMod.MODID,"cap_use_count");
	private LazyOptional<IUseCount> instance = LazyOptional.of(ITEM_USE_COUNT::getDefaultInstance);

	public static void register()
	{
		CapabilityManager.INSTANCE.register(IUseCount.class, new UseCountStorage(), PlayerUseCount::new);
	}

	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
	{
		return ITEM_USE_COUNT.orEmpty(cap,instance);
	}

	@Override
	public CompoundNBT serializeNBT()
	{
		return (CompoundNBT) ITEM_USE_COUNT.getStorage().writeNBT(ITEM_USE_COUNT, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null);
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt)
	{
		ITEM_USE_COUNT.getStorage().readNBT(ITEM_USE_COUNT, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null, nbt);
	}
}
