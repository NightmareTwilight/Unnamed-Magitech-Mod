package crimson_twilight.unnamed_magitech_mod.api.capability.player_ki;

import net.minecraft.nbt.ByteNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class PlayerKiStorage implements Capability.IStorage<IPlayerKi>
{
    @Override
    public INBT writeNBT(Capability<IPlayerKi> capability, IPlayerKi instance, Direction side)
    {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("amount", instance.getKiAmount());
        nbt.putInt("max", instance.getMaxKiAmount());
        nbt.putInt("gen", instance.getKiGenAmount());
        nbt.putBoolean("hasVeins", instance.hasVeins());
        nbt.putInt("corrupt", instance.getCorruption());
        return nbt;
    }

    @Override
    public void readNBT(Capability<IPlayerKi> capability, IPlayerKi instance, Direction side, INBT nbt)
    {
        if(nbt instanceof CompoundNBT)
        {
            CompoundNBT tag = ((CompoundNBT)nbt);
            INBT amountTag = tag.get("amount");
            if(amountTag instanceof IntNBT) instance.setKiAmount(((IntNBT)amountTag).getInt());
            INBT maxTag = tag.get("max");
            if(maxTag instanceof IntNBT) instance.setMaxKiAmount(((IntNBT)maxTag).getInt());
            INBT genTag = tag.get("gen");
            if(genTag instanceof IntNBT) instance.setKiGenAmount(((IntNBT)genTag).getInt());
            INBT veinsTag = tag.get("hasVeins");
            if(veinsTag instanceof ByteNBT) instance.setHasVeins(((ByteNBT)veinsTag).getByte() != 0);
            INBT corruptTag = tag.get("corrupt");
            if(corruptTag instanceof IntNBT) instance.setCorruption(((IntNBT)corruptTag).getInt());
        }
    }
}
