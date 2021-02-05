package crimson_twilight.unnamed_magitech_mod.common.network;

import crimson_twilight.unnamed_magitech_mod.UnnamedMagitechMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class UMMDataPacketHandler
{
    public static SimpleChannel INSTANCE;

    static int id = 0;

    public static void init()
    {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(UnnamedMagitechMod.MODID, "network"), () -> "1.0", (s) -> true, (s) -> true);

        INSTANCE.registerMessage(
            id++,
            CultivatePacket.class,
            CultivatePacket::encode,
            CultivatePacket::decode,
            CultivatePacket::consume
        );
    }
}
