package crimson_twilight.unnamed_magitech_mod.common.item;

import crimson_twilight.unnamed_magitech_mod.api.capability.CapabilityPlayerUseCount;
import crimson_twilight.unnamed_magitech_mod.api.capability.IUseCount;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;
import java.util.List;

public class ItemKiPill extends UMMItemConsumable
{
    private Attribute bonus;
    private double bonusAmount;

    public ItemKiPill(String name, Attribute bonus, double bonusAmount)
    {
        super(name);
        this.bonus = bonus;
        this.bonusAmount = bonusAmount;
    }

    @Override
    public boolean canConsume(World worldIn, PlayerEntity playerIn, ItemStack stack)
    {
        LazyOptional<IUseCount> capability = playerIn.getCapability(CapabilityPlayerUseCount.ITEM_USE_COUNT);
        IUseCount count = capability.orElseThrow(() -> new IllegalArgumentException("at login"));
        return count.canUse(this.itemName);
    }

    @Override
    public ItemStack consume(ItemStack stack, World world, PlayerEntity playerIn)
    {
        playerIn.getAttribute(bonus).applyPersistentModifier(new AttributeModifier(this.itemName, this.bonusAmount, AttributeModifier.Operation.MULTIPLY_TOTAL));
        LazyOptional<IUseCount> capability = playerIn.getCapability(CapabilityPlayerUseCount.ITEM_USE_COUNT);
        IUseCount count = capability.orElseThrow(() -> new IllegalArgumentException("at login"));
        count.setUseCount(this.itemName, count.getUseCount(this.itemName) + 1);
        return super.consume(stack, world, playerIn);
    }
    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if(worldIn != null)
        {
            PlayerEntity player = (PlayerEntity) Minecraft.getInstance().player.getEntity();
            if(CapabilityPlayerUseCount.ITEM_USE_COUNT!=null)
            {
                LazyOptional<IUseCount> capability = player.getCapability(CapabilityPlayerUseCount.ITEM_USE_COUNT);
                IUseCount count = capability.orElseThrow(() -> new IllegalArgumentException("at login"));
                tooltip.add(new StringTextComponent(String.valueOf(count.getAllCounts())));
                tooltip.add(new StringTextComponent( String.valueOf(count.getUseCount(this.itemName) + "/" + count.getMaxUseCount(this.itemName)) ));
            }
        }
    }
}
