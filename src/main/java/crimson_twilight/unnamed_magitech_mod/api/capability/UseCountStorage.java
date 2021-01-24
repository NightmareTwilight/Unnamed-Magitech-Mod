package crimson_twilight.unnamed_magitech_mod.api.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Pabilo8
 * @since 24.01.2021
 */
public class UseCountStorage implements Capability.IStorage<IUseCount>
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
}
