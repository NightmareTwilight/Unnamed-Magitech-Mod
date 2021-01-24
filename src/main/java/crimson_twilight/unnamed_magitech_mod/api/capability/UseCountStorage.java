package crimson_twilight.unnamed_magitech_mod.api.capability;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Pabilo8
 * @since 24.01.2021
 */
public class UseCountStorage implements IUseCount
{
	LinkedHashMap<String,Integer> useMap = new LinkedHashMap<>();
	LinkedHashMap<String,Integer> maxUseMap = new LinkedHashMap<>();

	@Override
	public int getUseCount(String itemID)
	{
		return useMap.getOrDefault(itemID,0);
	}

	@Override
	public int getMaxUseCount(String itemID)
	{
		return maxUseMap.getOrDefault(itemID,5);
	}

	@Override
	public void setUseCount(String itemID, int count)
	{
		useMap.put(itemID,count);
	}

	@Override
	public void setmaxUseCount(String itemID, int count)
	{
		maxUseMap.put(itemID,count);
	}

	@Override
	public Map<String, Integer> getAllCounts()
	{
		return useMap;
	}

	@Override
	public Map<String, Integer> getAllMaxCounts()
	{
		return maxUseMap;
	}

	@Override
	public void setAllCounts(Map<String, Integer> map)
	{
		useMap.clear();
		useMap.putAll(map);
	}

	@Override
	public void setAllMaxCounts(Map<String, Integer> map)
	{
		maxUseMap.clear();
		maxUseMap.putAll(map);
	}
}
