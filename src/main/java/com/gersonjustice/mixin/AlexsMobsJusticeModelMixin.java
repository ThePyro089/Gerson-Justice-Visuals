package com.gersonjustice.mixin;

import com.gersonjustice.JusticeMatch;
import com.gersonjustice.model.JusticeRockyModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Surgical integration: replace only Alex's Mobs' returned armor model for
 * a renamed Rocky Shell Chestplate, never NeoForge's global getArmorModel.
 * Alex's Mobs initializes its models normally before this RETURN callback.
 */
@Mixin(targets = "com.github.alexthe666.alexsmobs.client.render.item.CustomArmorRenderProperties", remap = false)
public abstract class AlexsMobsJusticeModelMixin {
    private static JusticeRockyModel gersonjustice$model;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Inject(method = "getHumanoidArmorModel", at = @At("RETURN"), cancellable = true, remap = false, require = 0)
    private void gersonjustice$useJusticeOnlyForRenamedChestplate(
            LivingEntity entity, ItemStack stack, EquipmentSlot slot,
            HumanoidModel<?> original, CallbackInfoReturnable<HumanoidModel<?>> cir) {
        if (slot != EquipmentSlot.CHEST || !JusticeMatch.matches(stack)) return;
        if (gersonjustice$model == null) {
            gersonjustice$model = JusticeRockyModel.create();
        }
        // Preserve player pose and animation supplied by Alex's Mobs.
        HumanoidModel<?> alexModel = cir.getReturnValue();
        if (alexModel != null) {
            ((HumanoidModel) alexModel).copyPropertiesTo(gersonjustice$model);
        } else {
            ((HumanoidModel) original).copyPropertiesTo(gersonjustice$model);
        }
        cir.setReturnValue(gersonjustice$model);
    }
}
