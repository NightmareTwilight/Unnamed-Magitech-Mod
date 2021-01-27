package crimson_twilight.unnamed_magitech_mod.common;

import crimson_twilight.unnamed_magitech_mod.UnnamedMagitechMod;
import crimson_twilight.unnamed_magitech_mod.api.capability.CapabilityPlayerUseCount;
import crimson_twilight.unnamed_magitech_mod.api.capability.IUseCount;
import crimson_twilight.unnamed_magitech_mod.common.item.ItemKiPill;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.WorldTickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandler
{
    @SubscribeEvent
    public void onLoad(WorldEvent.Load event)
    {
        UnnamedMagitechMod.proxy.onWorldLoad();
    }

    @SubscribeEvent
    public void onCapabilitiesAttachEntity(AttachCapabilitiesEvent<Entity> event)
    {
        if(event.getObject() instanceof PlayerEntity) event.addCapability(CapabilityPlayerUseCount.RES, new CapabilityPlayerUseCount());
    }

    @SubscribeEvent
    public void onCapabilitiesAttachWorld(AttachCapabilitiesEvent<World> event)
    {

    }

    @SubscribeEvent
    public void onEntityJoiningWorld(EntityJoinWorldEvent event)
    {
        if(event.getEntity() instanceof PlayerEntity)
        {
            for (Item item: UMMContent.registeredUMMItems)
            {
                if (item instanceof ItemKiPill)
                {
                    ItemKiPill pill = (ItemKiPill)item;
                    LazyOptional<IUseCount> capability = event.getEntity().getCapability(CapabilityPlayerUseCount.ITEM_USE_COUNT);
                    IUseCount count = capability.orElseThrow(() -> new IllegalArgumentException("at login"));
                    if(!count.hasRegisteredUseCount(pill.itemName))
                    {
                        count.setMaxUseCount(pill.itemName, pill.startingMaxUse);
                        count.setUseCount(pill.itemName, 0);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onWorldTick(WorldTickEvent event)
    {

    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onLivingDropsLowest(LivingDropsEvent event)
    {

    }

    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event)
    {

    }
}
