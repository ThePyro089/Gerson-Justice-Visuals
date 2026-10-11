package com.gersonjustice.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

/** A separate chestplate model. This is never used for the ordinary Rocky Shell item. */
public final class JusticeRockyModel extends HumanoidModel {
    public JusticeRockyModel(ModelPart root) {
        super(root);
    }

    public static JusticeRockyModel create() {
        return new JusticeRockyModel(createLayer().bakeRoot());
    }

    private static LayerDefinition createLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0.25F), 0.0F);
        PartDefinition root = mesh.getRoot();
        CubeDeformation rockInflate = new CubeDeformation(0.7F);

        PartDefinition body = root.getChild("body");
        body.addOrReplaceChild("BodyRocky", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-5.0F, -0.5F, -2.0F, 10.0F, 13.0F, 9.0F, rockInflate)
                .texOffs(0, 23).addBox(-4.0F, 0.5F, 6.0F, 8.0F, 11.0F, 4.0F, rockInflate)
                .texOffs(25, 34).addBox(-2.0F, -0.5F, 6.0F, 4.0F, 13.0F, 4.0F, rockInflate),
                PartPose.ZERO);

        // Original Alex's Mobs shoulder mirror directions are preserved.
        // Right shoulder UVs are relocated to the right half (x + 64) of a
        // 128x64 atlas, so each shoulder can be painted separately.
        PartDefinition leftArm = root.getChild("left_arm");
        leftArm.addOrReplaceChild("LeftArmRocky", CubeListBuilder.create()
                .texOffs(25, 23).addBox(-1.0F, -5.0F, -2.1F, 6.0F, 4.0F, 6.0F, rockInflate)
                .texOffs(0, 39).addBox(0.0F, -7.1F, -1.1F, 7.0F, 6.0F, 4.0F, rockInflate),
                PartPose.offset(-1.0F, 2.0F, 0.0F));

        PartDefinition rightArm = root.getChild("right_arm");
        rightArm.addOrReplaceChild("RightArmRocky", CubeListBuilder.create()
                .texOffs(89, 23).mirror().addBox(-5.0F, -5.0F, -2.1F, 6.0F, 4.0F, 6.0F, rockInflate).mirror(false)
                .texOffs(64, 39).mirror().addBox(-7.0F, -7.1F, -1.1F, 7.0F, 6.0F, 4.0F, rockInflate).mirror(false),
                PartPose.offset(1.0F, 2.0F, 0.0F));
        return LayerDefinition.create(mesh, 128, 64);
    }
}
