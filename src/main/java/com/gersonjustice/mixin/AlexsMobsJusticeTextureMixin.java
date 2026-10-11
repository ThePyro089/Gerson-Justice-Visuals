package com.gersonjustice.mixin;

import com.gersonjustice.JusticeMatch;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Alex's Mobs selects the Rocky Chestplate texture in ItemModArmor itself.
 * Modify its return value only for the correctly renamed Rocky item.
 */
@Mixin(targets = "com.github.alexthe666.alexsmobs.item.ItemModArmor", remap = false)
public abstract class AlexsMobsJusticeTextureMixin {
    @Inject(method = "getArmorTexture", at = @At("RETURN"), cancellable = true, remap = false, require = 0)
    private void gersonjustice$justiceTexture(
            ItemStack stack, Entity entity, EquipmentSlot slot,
            ArmorMaterial.Layer layer, boolean innerModel,
            CallbackInfoReturnable<ResourceLocation> cir) {
        if (slot == EquipmentSlot.CHEST && JusticeMatch.matches(stack)) {
            cir.setReturnValue(JusticeMatch.TEXTURE);
        }
    }
}
