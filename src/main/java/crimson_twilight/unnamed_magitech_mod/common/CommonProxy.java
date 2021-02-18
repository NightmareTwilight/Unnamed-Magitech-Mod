package crimson_twilight.unnamed_magitech_mod.common;

import crimson_twilight.unnamed_magitech_mod.api.capability.cultivation.CapabilityCultivation;
import crimson_twilight.unnamed_magitech_mod.api.capability.player_ki.CapabilityPlayerKi;
import crimson_twilight.unnamed_magitech_mod.api.capability.use_count.CapabilityPlayerUseCount;
import crimson_twilight.unnamed_magitech_mod.common.integration.Eidolon.UMMDeities;
import crimson_twilight.unnamed_magitech_mod.common.integration.Mods;

public class CommonProxy
{
    public void modConstruction()
    {
    }

    public void preInit()
    {
        CapabilityPlayerUseCount.register();
        CapabilityPlayerKi.register();
        CapabilityCultivation.register();
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
