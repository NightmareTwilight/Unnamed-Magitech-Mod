package crimson_twilight.unnamed_magitech_mod.common.network;

import crimson_twilight.unnamed_magitech_mod.api.capability.cultivation.CapabilityCultivation;
import crimson_twilight.unnamed_magitech_mod.api.capability.cultivation.ICultivation;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class CultivatePacket
{
    UUID player;

    public CultivatePacket(UUID player)
    {
        this.player = player;
    }

    public static void encode(CultivatePacket object, PacketBuffer buffer)
    {
        buffer.writeUniqueId(object.player);
    }

    public static CultivatePacket decode(PacketBuffer buffer)
    {
        return new CultivatePacket(buffer.readUniqueId());
    }

    public static void consume(CultivatePacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            World world;
            if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
            {
                world = Minecraft.getInstance().world;
            }
            else
            {
                if (ctx.get().getSender() == null) return;
                world = ctx.get().getSender().world;
            }
            PlayerEntity player = world.getPlayerByUuid(packet.player);
            LazyOptional<ICultivation> capCulti = player.getCapability(CapabilityCultivation.CULTIVATION_CAPABILITY);
            ICultivation culti = capCulti.orElseThrow(() -> new IllegalArgumentException("at login"));
            culti.setIsCultivating(true);
        });
        ctx.get().setPacketHandled(true);
    }
}
