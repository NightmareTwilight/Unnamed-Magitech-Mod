package crimson_twilight.unnamed_magitech_mod.api.capability;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerUseCount implements IUseCount
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
		useMap.put(itemID, count);
	}

	@Override
	public void setMaxUseCount(String itemID, int count)
	{
		maxUseMap.put(itemID, count);
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

	@Override
	public void addUseCount(String itemID)
	{
		useMap.put(itemID, useMap.getOrDefault(itemID, 0) + 1);
	}

    @Override
    public void addUseCount(String itemID, int amount)
    {
        useMap.put(itemID, useMap.getOrDefault(itemID, 0) + amount);
    }

	@Override
	public boolean hasRegisteredUseCount(String itemID) {
		return maxUseMap.containsKey(itemID);
	}
}
