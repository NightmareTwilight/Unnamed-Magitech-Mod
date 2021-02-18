package crimson_twilight.unnamed_magitech_mod.common.integration.Eidolon;

import crimson_twilight.unnamed_magitech_mod.UnnamedMagitechMod;
import elucent.eidolon.deity.Deities;
import elucent.eidolon.deity.Deity;
import net.minecraft.util.ResourceLocation;

public class UMMDeities extends Deities
{
    public static final Deity
                CULTIVATOR_DEITY = register(new CultivatorDeity(new ResourceLocation(UnnamedMagitechMod.MODID, "cultivator"), 15, 200, 130));

}
