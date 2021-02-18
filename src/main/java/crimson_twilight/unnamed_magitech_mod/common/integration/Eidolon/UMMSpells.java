package crimson_twilight.unnamed_magitech_mod.common.integration.Eidolon;

import crimson_twilight.unnamed_magitech_mod.UnnamedMagitechMod;
import elucent.eidolon.spell.PrayerSpell;
import elucent.eidolon.spell.Signs;
import elucent.eidolon.spell.Spell;
import elucent.eidolon.spell.Spells;
import net.minecraft.util.ResourceLocation;

public class UMMSpells extends Spells
{
    public static Spell
            CULTIVATION_PRAYER = register(new PrayerSpell(
                    new ResourceLocation(UnnamedMagitechMod.MODID, "cultivate_prayer"),
            UMMDeities.CULTIVATOR_DEITY,
            Signs.BLOOD_SIGN, Signs.SOUL_SIGN, Signs.SOUL_SIGN
            ));
}
