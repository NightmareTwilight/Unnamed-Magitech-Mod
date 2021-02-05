package crimson_twilight.unnamed_magitech_mod;

import crimson_twilight.unnamed_magitech_mod.client.ClientProxy;
import crimson_twilight.unnamed_magitech_mod.common.CommonProxy;
import crimson_twilight.unnamed_magitech_mod.common.EventHandler;
import crimson_twilight.unnamed_magitech_mod.common.UMMContent;
import crimson_twilight.unnamed_magitech_mod.common.network.UMMDataPacketHandler;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(UnnamedMagitechMod.MODID)
public class UnnamedMagitechMod
{
    public static final String MODID = "unnamed_magitech_mod";
    public static final String MODNAME = "Unname Magitech Mod";
    public static final String VERSION = "${version}";
    public static CommonProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public static final SimpleChannel packetHandler = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(MODID, "main"))
            .networkProtocolVersion(() -> VERSION)
            .serverAcceptedVersions(VERSION::equals)
            .clientAcceptedVersions(VERSION::equals)
            .simpleChannel();

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public UnnamedMagitechMod()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loadComplete);
        MinecraftForge.EVENT_BUS.addListener(this::serverStarting);
        MinecraftForge.EVENT_BUS.addListener(this::serverStarted);
        UMMContent.modConstruction();
        proxy.modConstruction();
    }

    public static int max_ascention_level = 3;

    private void setup(final FMLCommonSetupEvent event)
    {
        proxy.preInit();

        proxy.preInitEnd();
        UMMContent.init();
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        proxy.init();
        UMMDataPacketHandler.init();

        proxy.initEnd();
        UMMContent.postInit();
        proxy.postInit();

        proxy.postInitEnd();
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
        LOGGER.info("Client starting");
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {}

    private void processIMC(final InterModProcessEvent event)
    {}

    public void loadComplete(FMLLoadCompleteEvent event)
    {}

    public void serverStarting(FMLServerStartingEvent event)
    {
        LOGGER.info("Server starting");
    }

    public void serverStarted(FMLServerStartedEvent event)
    {
        LOGGER.info("Server started");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent)
        {}
    }

    public static ItemGroup itemGroup = new ItemGroup(MODID)
    {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(UMMContent.HealthKiPill);
        }
    };
}
