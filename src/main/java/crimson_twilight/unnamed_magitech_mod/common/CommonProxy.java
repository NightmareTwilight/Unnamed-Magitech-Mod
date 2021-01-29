package crimson_twilight.unnamed_magitech_mod.common;

import crimson_twilight.unnamed_magitech_mod.api.capability.player_ki.CapabilityPlayerKi;
import crimson_twilight.unnamed_magitech_mod.api.capability.use_count.CapabilityPlayerUseCount;

public class CommonProxy
{
    public void modConstruction()
    {
    }

    public void preInit()
    {
        CapabilityPlayerUseCount.register();
        CapabilityPlayerKi.register();
    }

    public void preInitEnd()
    {
    }

    public void init()
    {
    }

    public void initEnd()
    {
    }

    public void postInit()
    {
    }

    public void postInitEnd()
    {
    }

    public void serverStarting()
    {
    }

    public void onWorldLoad()
    {
    }
}
