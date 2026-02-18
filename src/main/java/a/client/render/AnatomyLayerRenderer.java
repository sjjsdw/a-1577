package a.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class AnatomyLayerRenderer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation ANATOMY_TEXTURE = new ResourceLocation("a", "textures/entity/anatomy_layer.png");

    public AnatomyLayerRenderer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (livingEntity.isInvisible()) {
            return;
        }

        // The anatomy layer is rendered as a cutout to support transparency for various body features
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(ANATOMY_TEXTURE));
        
        // Synchronize the layer rendering with the parent entity model movements
        this.getParentModel().renderToBuffer(
            matrixStack, 
            vertexConsumer, 
            packedLight, 
            OverlayTexture.NO_OVERLAY, 
            1.0F, 
            1.0F, 
            1.0F, 
            1.0F
        );
    }
}