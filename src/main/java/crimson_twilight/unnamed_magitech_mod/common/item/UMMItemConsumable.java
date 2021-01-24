package crimson_twilight.unnamed_magitech_mod.common.item;

import crimson_twilight.unnamed_magitech_mod.api.capability.CapabilityPlayerUseCount;
import crimson_twilight.unnamed_magitech_mod.api.capability.IUseCount;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class UMMItemConsumable extends UMMBaseItem
{
    private boolean canConsume = true;
    private boolean isDrink = false;

    public UMMItemConsumable(String name) {
        super(name);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        //It exists
        LazyOptional<IUseCount> capability = playerIn.getCapability(CapabilityPlayerUseCount.ITEM_USE_COUNT);
        IUseCount count = capability.orElseThrow(() -> new IllegalArgumentException("at login"));
        count.getUseCount("o");
        count.addUseCount("o");

        if(this.canConsume(worldIn, playerIn, itemstack))
        {
            playerIn.setActiveHand(handIn);
            return ActionResult.resultConsume(itemstack);
        } else
        {
            return ActionResult.resultFail(itemstack);
        }
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 16;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entity)
    {
        if(entity instanceof PlayerEntity)
            return canConsume(world, (PlayerEntity)entity, stack) ? consume(stack, world, (PlayerEntity)entity) : stack;
        return stack;
    }

    public boolean canConsume(World worldIn, PlayerEntity playerIn, ItemStack stack)
    {
        return canConsume;
    }

    public void setCanConsume(boolean b)
    {
        canConsume = b;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return isDrink ? UseAction.DRINK : UseAction.EAT;
    }

    public ItemStack consume(ItemStack stack, World world, PlayerEntity playerIn)
    {
        System.out.println("Consumed!");
        stack.setCount(stack.getCount() -1);
        return stack;
    }

    public UMMItemConsumable setDrink()
    {
        isDrink = true;
        return this;
    }
}
