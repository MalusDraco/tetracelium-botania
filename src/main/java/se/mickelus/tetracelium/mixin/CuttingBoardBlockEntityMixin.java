package se.mickelus.tetracelium.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import se.mickelus.tetra.items.modular.IModularItem;
import vectorwing.farmersdelight.common.block.entity.CuttingBoardBlockEntity;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@Mixin(CuttingBoardBlockEntity.class)
public class CuttingBoardBlockEntityMixin {
    private static final Logger logger = LogManager.getLogger();

    @Inject(at = @At("RETURN"), method = "processStoredItemUsingTool(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;)Z", remap = false)
    private void processStoredItemUsingTool(ItemStack toolStack, @Nullable Player player, CallbackInfoReturnable<Boolean> cir) {
        logger.debug("pew pew");
        if (cir.getReturnValue() && player != null && toolStack.getItem() instanceof IModularItem item) {
            item.applyUsageEffects(player, toolStack, 2);
        }
    }
}
