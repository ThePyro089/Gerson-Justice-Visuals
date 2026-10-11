# Gerson Justice Visuals v0.4.1 — texture interception hotfix

NeoForge 1.21.1, Alex’s Mobs 1.22.17.

## What changed

v0.4.0 started successfully but no longer applied the Justice texture. The new `AlexsMobsJusticeTextureMixin` intercepts `com.github.alexthe666.alexsmobs.item.ItemModArmor.getArmorTexture` at RETURN and substitutes `gersonjustice:textures/armor/rocky_chestplate_justice.png` ONLY when the exact `alexsmobs:rocky_chestplate` has custom anvil name `Chestplate of Justice`. Alex’s Mobs selects the normal texture in that very method. This is an additional, targeted fallback to the existing V0.2.1 ClientHooks texture override.

The separate 128x64 `JusticeRockyModel` and `AlexsMobsJusticeModelMixin` from V0.4.0 remain unchanged. Only renamed chestplates should receive the Justice model and texture. Item display icon in inventory is NOT changed; this concerns the texture when worn.

## Build

Replace ALL contents of your GitHub project with this ZIP's files and run the existing Actions build workflow. Before building, confirm the obsolete `JusticeArmorModelMixin.java` (from V0.3.0) is deleted. `AlexsMobsJusticeTextureMixin.java` is new in V0.4.1.

## Caveats

Source ZIP only: not compiled or tested in Minecraft. Both Alex's Mobs-specific mixins specify `require = 0` to avoid failing startup outright if the method signature differs; if that happens, the feature may silently not apply. If texture still fails, upload compiled V0.4.1 JAR and `latest.log` so the method/mixin attachment can be checked.

Check worn `Chestplate of Justice` against an unrenamed Rocky Shell Chestplate. Both inventory icons will remain the original icon.
