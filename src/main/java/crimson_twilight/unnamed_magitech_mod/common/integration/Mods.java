package crimson_twilight.unnamed_magitech_mod.common.integration;

import crimson_twilight.unnamed_magitech_mod.UnnamedMagitechMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.versions.forge.ForgeVersion;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public enum Mods
{
    MINECRAFT("minecraft", true),
    FORGE(ForgeVersion.MOD_ID, true),
    UMM(UnnamedMagitechMod.MODID, true),
    EIDOLON("eidolon");

    private final String modid;
    private final boolean loaded;

    Mods(String modid) {
        this(modid, ModList.get().isLoaded(modid));
    }

    private Mods(String modid, boolean loaded) {
        this.modid = modid;
        this.loaded = loaded;
    }

    public String getModId() {
        return this.modid;
    }

    public boolean isPresent() {
        return loaded;
    }

    public void executeIfPresent(Supplier<Runnable> execSupplier) {
        if (this.isPresent()) {
            execSupplier.get().run();
        }
    }

    @Nullable
    public <T> T getIfPresent(Supplier<Supplier<T>> supplierSupplier) {
        if (this.isPresent()) {
            return supplierSupplier.get().get();
        }
        return null;
    }

    public boolean owns(IForgeRegistryEntry<?> entry) {
        return this.isPresent() &&
                entry.getRegistryName() != null &&
                entry.getRegistryName().getNamespace().equals(this.modid);
    }

    @Nonnull
    public ResourceLocation key(String path) {
        return new ResourceLocation(this.getModId(), path);
    }

    public void sendIMC(String method, Supplier<?> thing) {
        if (this.isPresent()) {
            InterModComms.sendTo(UnnamedMagitechMod.MODID, this.getModId(), method, thing);
        }
    }

    @Nullable
    public static Mods byModId(String modId) {
        for (Mods mod : values()) {
            if (mod.getModId().equals(modId)) {
                return mod;
            }
        }
        return null;
    }
}
