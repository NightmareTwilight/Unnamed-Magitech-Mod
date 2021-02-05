package crimson_twilight.unnamed_magitech_mod.common.events;

import crimson_twilight.unnamed_magitech_mod.api.capability.cultivation.CapabilityCultivation;
import crimson_twilight.unnamed_magitech_mod.api.capability.cultivation.ICultivation;
import crimson_twilight.unnamed_magitech_mod.api.capability.player_ki.CapabilityPlayerKi;
import crimson_twilight.unnamed_magitech_mod.api.capability.player_ki.IPlayerKi;
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
        LazyOptional<ICultivation> capCu = player.getCapability(CapabilityCultivation.CULTIVATION_CAPABILITY);
        LazyOptional<IPlayerKi> capKI = player.getCapability(CapabilityPlayerKi.PLAYER_KI_CAPABILITY);
        IPlayerKi ki = capKI.orElseThrow(() -> new IllegalArgumentException("at login"));
        this.cultivation = capCu.orElseThrow(() -> new IllegalArgumentException("at login"));
        if(cultivation.getStartTime() != 0 && (player.world.getGameTime() - cultivation.getStartTime()) % 2400==2399 && ki.getKiAmount() >= cultivation.getKiToCB())
        {
            ki.addKiAmount(-cultivation.getKiToCB());
            cultivation.addCultivationBase(cultivation.getCBGain());
        }
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
            cultivation.setStartTime(0);
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
