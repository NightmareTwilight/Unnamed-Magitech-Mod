package crimson_twilight.unnamed_magitech_mod.common.integration.Eidolon;

import elucent.eidolon.capability.IReputation;
import elucent.eidolon.deity.Deity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;

public class CultivatorDeity extends Deity
{

    public CultivatorDeity(ResourceLocation id, int red, int green, int blue)
    {
        super(id, red, green, blue);
    }

    @Override
    public void onReputationUnlock(PlayerEntity playerEntity, IReputation iReputation, ResourceLocation resourceLocation)
    {

    }

    @Override
    public void onReputationChange(PlayerEntity playerEntity, IReputation iReputation, double v, double v1)
    {

    }
}
