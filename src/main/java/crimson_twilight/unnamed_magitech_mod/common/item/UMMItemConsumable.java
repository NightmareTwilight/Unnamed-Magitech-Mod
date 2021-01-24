package crimson_twilight.unnamed_magitech_mod.common.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class UMMItemConsumable extends UMMBaseItem
{
    public boolean canConsume = true;
    public UMMItemConsumable(String name) {
        super(name);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if(this.canConsume())
        {
            playerIn.setActiveHand(handIn);
            return ActionResult.resultConsume(itemstack);
        } else
        {
            return ActionResult.resultFail(itemstack);
        }
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entity) {
        return canConsume() ? consume(stack, world, entity) : stack;
    }

    public boolean canConsume()
    {
        return canConsume;
    }

    public void setCanConsume(boolean b)
    {
        canConsume = b;
    }

    public ItemStack consume(ItemStack stack, World world, LivingEntity entity)
    {
        stack.setCount(stack.getCount() -1);
        return stack;
    }
}