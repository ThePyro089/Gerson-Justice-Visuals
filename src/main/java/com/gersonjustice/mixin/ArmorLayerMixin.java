package com.gersonjustice.mixin;

import com.gersonjustice.JusticeMatch;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.ClientHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Minecraft/NeoForge 1.21.1: override texture selection, not equipment assets. */
@Mixin(value = ClientHooks.class, remap = false)
public abstract class ArmorLayerMixin {
    @Inject(method = "getArmorTexture", at = @At("HEAD"), cancellable = true, require = 1)
    private static void gersonjustice$justiceChestTexture(
            Entity entity, ItemStack armor, ArmorMaterial.Layer layer,
            boolean innerModel, EquipmentSlot slot,
            CallbackInfoReturnable<ResourceLocation> cir) {
        if (slot == EquipmentSlot.CHEST && JusticeMatch.matches(armor)) {
            cir.setReturnValue(JusticeMatch.TEXTURE);
        }
    }
}
