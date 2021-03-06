package crimson_twilight.unnamed_magitech_mod.common;

import crimson_twilight.unnamed_magitech_mod.UnnamedMagitechMod;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static crimson_twilight.unnamed_magitech_mod.UnnamedMagitechMod.MODID;
import static crimson_twilight.unnamed_magitech_mod.UnnamedMagitechMod.LOGGER;

@Mod.EventBusSubscriber(modid = MODID, bus = Bus.MOD)
public class UMMContent
{
    public static List<Block> registeredUMMBlocks = new ArrayList<>();
    public static List<Item> registeredUMMItems = new ArrayList<>();
    public static List<Class<? extends TileEntity>> registeredUMMTiles = new ArrayList<>();
    public static List<Fluid> registeredUMMFluids = new ArrayList<>();

    public static void modConstruction()
    {

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        checkNonNullNames(registeredUMMBlocks);
        for(Block block : registeredUMMBlocks)
            event.getRegistry().register(block);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        checkNonNullNames(registeredUMMItems);
        for(Item item : registeredUMMItems)
            event.getRegistry().register(item);
    }

    private static <T extends IForgeRegistryEntry<T>> void checkNonNullNames(Collection<T> coll)
    {
        int numNull = 0;
        for(T b : coll)
            if(b.getRegistryName()==null)
            {
                LOGGER.info("Null name for {} (class {})", b, b.getClass());
                ++numNull;
            }
        if(numNull > 0)
            System.exit(1);
    }

    @SubscribeEvent
    public static void registerFluids(RegistryEvent.Register<Fluid> event)
    {
        checkNonNullNames(registeredUMMFluids);
        for(Fluid fluid : registeredUMMFluids)
            event.getRegistry().register(fluid);
    }

    @SubscribeEvent
    public static void registerTEs(RegistryEvent.Register<TileEntityType<?>> event)
    {
    }

    public static void init()
    {

    }

    public static void postInit()
    {

    }
}
