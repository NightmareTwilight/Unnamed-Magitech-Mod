package crimson_twilight.unnamed_magitech_mod.common.item;

import crimson_twilight.unnamed_magitech_mod.api.capability.use_count.CapabilityPlayerUseCount;
import crimson_twilight.unnamed_magitech_mod.api.capability.use_count.IUseCount;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
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
    @Nullable
    private Attribute bonus;
    private double bonusAmount;
    public int startingMaxUse;

    public ItemKiPill(String name, @Nullable Attribute bonus, double bonusAmount)
    {
        super(name);
        this.bonus = bonus;
        this.bonusAmount = bonusAmount;
        this.startingMaxUse = 5;
    }

    public ItemKiPill(String name, @Nullable Attribute bonus, double bonusAmount, int startingMaxUse)
    {
        super(name);
        this.bonus = bonus;
        this.bonusAmount = bonusAmount;
        this.startingMaxUse = startingMaxUse;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        return super.onItemRightClick(worldIn, playerIn, handIn);
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
        if(bonus != null) playerIn.getAttribute(bonus).applyPersistentModifier(new AttributeModifier(this.itemName, this.bonusAmount, AttributeModifier.Operation.MULTIPLY_TOTAL));
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
                if(count.getMaxUseCount(this.itemName) < 0) { tooltip.add(new StringTextComponent( String.valueOf(count.getUseCount(this.itemName) ))); }
                else { tooltip.add(new StringTextComponent( String.valueOf(count.getUseCount(this.itemName) + "/" + count.getMaxUseCount(this.itemName)) )); }
            }
        }
    }
}
