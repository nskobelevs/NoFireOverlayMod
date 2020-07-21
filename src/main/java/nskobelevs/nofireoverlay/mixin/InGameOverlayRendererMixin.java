package nskobelevs.nofireoverlay.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffects;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {

    @Redirect(at = @At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;isOnFire()Z"), method="net/minecraft/client/gui/hud/InGameOverlayRenderer.renderOverlays(Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/util/math/MatrixStack;)V")
    private static boolean renderOverlaysMixin(ClientPlayerEntity playerEntity, MinecraftClient minecraftClient, MatrixStack matrixStack) {
        boolean isCreative = playerEntity.isCreative();
        boolean hasFireResistance = playerEntity.hasStatusEffect(StatusEffects.FIRE_RESISTANCE);
        boolean isOnFire = playerEntity.isOnFire();

        return !isCreative && isOnFire && !hasFireResistance;
    }

}