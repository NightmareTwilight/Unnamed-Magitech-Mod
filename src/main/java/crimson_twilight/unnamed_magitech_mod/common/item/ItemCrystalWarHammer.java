package crimson_twilight.unnamed_magitech_mod.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class ItemCrystalWarHammer extends UMMBaseItem
{
    private final double dmg;
    private final double spd;
    private final Multimap<Attribute, AttributeModifier> attributeModifiers;
    private LazyValue<Ingredient> repairMaterial;

    public ItemCrystalWarHammer(Properties properties, String name, double dmg, double spd)
    {
        super(name, properties);
        this.dmg = dmg;
        this.spd = spd;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.dmg, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", this.spd, AttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }

    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving)
    {
    if (state.getBlockHardness(worldIn, pos) > 1.0F) {
        stack.damageItem(2, entityLiving, (entity) -> {
            entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
        });
    }

    return true;
}

    public double getAttackDamage() {
        return this.dmg;
    }

    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
        float poa = 0f;
        for (ItemStack itemStack : attacker.getArmorInventoryList()) { if (!itemStack.isEmpty()) { poa++; } }
        attacker.setAbsorptionAmount(Math.min(20f, attacker.getAbsorptionAmount() + Math.max(5f - poa, 0)));
        stack.damageItem(1, attacker, (entity) -> {
            entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
        });
        return true;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack)
    {
        return slot == EquipmentSlotType.MAINHAND ? this.attributeModifiers : stack.getAttributeModifiers(slot);
    }

    public ItemCrystalWarHammer setRepairMaterial(Supplier<Ingredient> repairMaterialIn) { this.repairMaterial = new LazyValue<>(repairMaterialIn); return this; }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return this.getRepairMaterial().test(repair) || super.getIsRepairable(toRepair, repair);
    }

}
