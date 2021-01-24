package crimson_twilight.unnamed_magitech_mod.api.capability;

import net.minecraft.entity.player.PlayerEntity;

import java.util.Map;

/**
 * @author Pabilo8
 * @since 24.01.2021
 */
public interface IUseCount
{
	int getUseCount(String itemID);
	int getMaxUseCount(String itemID);

	void setUseCount(String itemID, int count);
	void setMaxUseCount(String itemID, int count);

	default boolean canUse(String itemID)
	{
		float maxUseCount = getMaxUseCount(itemID);
		return maxUseCount==-1||(getUseCount(itemID)<maxUseCount);
	}

	void addUseCount(String itemID);

	Map<String, Integer> getAllCounts();
	Map<String, Integer> getAllMaxCounts();
	void setAllCounts(Map<String, Integer> map);
	void setAllMaxCounts(Map<String, Integer> map);
}
