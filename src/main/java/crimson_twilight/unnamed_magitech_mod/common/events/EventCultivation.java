package crimson_twilight.unnamed_magitech_mod.common.events;

import crimson_twilight.unnamed_magitech_mod.api.capability.cultivation.CapabilityCultivation;
import crimson_twilight.unnamed_magitech_mod.api.capability.cultivation.ICultivation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

public class EventCultivation extends PlayerEvent
{
    protected final ICultivation cultivation;
    public EventCultivation(PlayerEntity player)
    {
        super(player);
        LazyOptional<ICultivation> capability = player.getCapability(CapabilityCultivation.CULTIVATION_CAPABILITY);
        this.cultivation = capability.orElseThrow(() -> new IllegalArgumentException("at login"));

    }

    public static class EventStartCultivation extends EventCultivation
    {
        public EventStartCultivation(PlayerEntity player)
        {
            super(player);
            cultivation.setStartTime(player.world.getGameTime());
        }
    }

    public static class EventEndCultivation extends EventCultivation
    {
        public EventEndCultivation(PlayerEntity player)
        {
            super(player);
        }
    }

    @Cancelable
    public static class EventCultivationBreakthrough extends EventCultivation
    {
        public EventCultivationBreakthrough(PlayerEntity player)
        {
            super(player);
        }
    }
}
