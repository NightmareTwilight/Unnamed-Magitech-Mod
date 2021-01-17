package crimson_twilight.unnamed_magitech_mod.client;

import crimson_twilight.unnamed_magitech_mod.common.CommonProxy;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import static crimson_twilight.unnamed_magitech_mod.UnnamedMagitechMod.MODID;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = MODID, bus = Bus.MOD)
public class ClientProxy extends CommonProxy
{
    @Override
    public void modConstruction()
    {
        super.modConstruction();
    }

    @Override
    public void preInit()
    {
        super.preInit();
    }

    @Override
    public void preInitEnd()
    {
        super.preInitEnd();
    }

    @Override
    public void init()
    {
        super.init();
    }

    @Override
    public void initEnd()
    {
        super.initEnd();
    }

    @Override
    public void postInit()
    {
        super.postInit();
    }

    @Override
    public void postInitEnd()
    {
        super.postInitEnd();
    }

    @Override
    public void serverStarting()
    {
        super.serverStarting();
    }

    @Override
    public void onWorldLoad()
    {
        super.onWorldLoad();
    }
}
