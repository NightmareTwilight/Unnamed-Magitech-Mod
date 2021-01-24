package crimson_twilight.unnamed_magitech_mod.common.item;

import crimson_twilight.unnamed_magitech_mod.UnnamedMagitechMod;
import crimson_twilight.unnamed_magitech_mod.common.UMMContent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class UMMBaseItem extends Item
{
    public String itemName;
    private int burnTime = -1;

    public UMMBaseItem(String name)
    {
        this(name, new Properties());
    }

    public UMMBaseItem(String name, Properties props)
    {
        super(props.group(UnnamedMagitechMod.itemGroup));
        this.itemName = name;
        setRegistryName(UnnamedMagitechMod.MODID, name);
        UMMContent.registeredUMMItems.add(this);
    }

    public UMMBaseItem setBurnTime(int burnTime)
    {
        this.burnTime = burnTime;
        return this;
    }

    @Override
    public int getBurnTime(ItemStack itemStack)
    {
        return burnTime;
    }
}
