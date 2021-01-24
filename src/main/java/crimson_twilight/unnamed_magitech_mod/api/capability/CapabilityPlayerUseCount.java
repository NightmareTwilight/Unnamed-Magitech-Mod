package crimson_twilight.unnamed_magitech_mod.api.capability;

import crimson_twilight.unnamed_magitech_mod.UnnamedMagitechMod;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Pabilo8
 * @since 24.01.2021
 */
public class CapabilityPlayerUseCount implements ICapabilityProvider
{
	public static final ResourceLocation RES = new ResourceLocation(UnnamedMagitechMod.MODID,"cap_use_count");
	@CapabilityInject(IUseCount.class)
	public static Capability<IUseCount> ITEM_USE_COUNT = null;

	public static void register()
	{
		CapabilityManager.INSTANCE.register(IUseCount.class, new IStorage<IUseCount>()
				{
					@Override
					public INBT writeNBT(Capability<IUseCount> capability, IUseCount instance, Direction side)
					{
						ListNBT listUses = new ListNBT();
						ListNBT listMax = new ListNBT();

						Map<String, Integer> allCounts = instance.getAllCounts();
						allCounts.forEach((key, value) -> {
							CompoundNBT compoundNBT = new CompoundNBT();
							compoundNBT.putString("item", key);
							compoundNBT.putInt("count", value);
							listUses.add(compoundNBT);
						});

						Map<String, Integer> allMaxCounts = instance.getAllMaxCounts();
						allCounts.forEach((key, value) -> {
							CompoundNBT compoundNBT = new CompoundNBT();
							compoundNBT.putString("item", key);
							compoundNBT.putInt("count", value);
							listMax.add(compoundNBT);
						});

						CompoundNBT nbt = new CompoundNBT();
						nbt.put("uses",listUses);
						nbt.put("max",listMax);

						return nbt;
					}

					@Override
					public void readNBT(Capability<IUseCount> capability, IUseCount instance, Direction side, INBT nbt)
					{
						if(nbt instanceof CompoundNBT)
						{
							CompoundNBT tag = ((CompoundNBT)nbt);
							INBT uses = tag.get("uses");
							if(uses instanceof ListNBT)
							{
								LinkedHashMap<String,Integer> map = new LinkedHashMap<>();
								((ListNBT)uses).stream().filter(inbt -> inbt instanceof CompoundNBT).forEach(inbt -> {
									CompoundNBT compound = (CompoundNBT)inbt;
									map.put(compound.getString("item"), compound.getInt("count"));
								});
								instance.setAllCounts(map);
							}
							INBT max = tag.get("max");
							if(max instanceof ListNBT)
							{
								LinkedHashMap<String,Integer> map = new LinkedHashMap<>();
								((ListNBT)max).stream().filter(inbt -> inbt instanceof CompoundNBT).forEach(inbt -> {
									CompoundNBT compound = (CompoundNBT)inbt;
									map.put(compound.getString("item"), compound.getInt("count"));
								});
								instance.setAllMaxCounts(map);
							}


						}
					}
				},
				UseCountStorage::new);
	}

	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nonnull Direction side)
	{
		return ITEM_USE_COUNT.orEmpty(cap,LazyOptional.of(ITEM_USE_COUNT::getDefaultInstance));
	}
}
