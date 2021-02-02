package crimson_twilight.unnamed_magitech_mod.api.capability.cultivation;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class CultivationStorage implements Capability.IStorage<ICultivation>
{
    @Override
    public INBT writeNBT(Capability<ICultivation> capability, ICultivation instance, Direction side)
    {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("cb", instance.getCultivationBase());
        nbt.putInt("ratio", instance.getKiToCB());
        nbt.putInt("gain", instance.getCBGain());
        nbt.putInt("lvl", instance.getAscensionLevel());
        nbt.putInt("rank", instance.getCultivationRank());
        nbt.putInt("foundation", instance.getFoundationAmount());
        nbt.putInt("core", instance.getCoreAmount());
        nbt.putInt("soul", instance.getSoulAmount());
        return nbt;
    }

    @Override
    public void readNBT(Capability<ICultivation> capability, ICultivation instance, Direction side, INBT nbt)
    {
        if(nbt instanceof CompoundNBT)
        {
            CompoundNBT tag = ((CompoundNBT)nbt);
            INBT cbTag = tag.get("cb");
            if(cbTag instanceof IntNBT) instance.setCultivationBase(((IntNBT)cbTag).getInt());
            INBT ratioTag = tag.get("ratio");
            if(ratioTag instanceof IntNBT) instance.setKiToCB(((IntNBT)ratioTag).getInt());
            INBT gainTag = tag.get("gain");
            if(gainTag instanceof IntNBT) instance.setCBGain(((IntNBT)gainTag).getInt());
            INBT lvlTag = tag.get("lvl");
            if(lvlTag instanceof IntNBT) instance.setAscensionLevel(((IntNBT)lvlTag).getInt());
            INBT rankTag = tag.get("rank");
            if(rankTag instanceof IntNBT) instance.setCultivationRank(((IntNBT)rankTag).getInt());
            INBT foundationTag = tag.get("foundation");
            if(foundationTag instanceof IntNBT) instance.setFoundationAmount(((IntNBT)foundationTag).getInt());
            INBT coreTag = tag.get("core");
            if(coreTag instanceof IntNBT) instance.setCoreAmount(((IntNBT)coreTag).getInt());
            INBT soulTag = tag.get("soul");
            if(soulTag instanceof IntNBT) instance.setSoulAmount(((IntNBT)soulTag).getInt());
        }
    }
}
