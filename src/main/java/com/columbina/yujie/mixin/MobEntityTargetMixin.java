package com.columbina.yujie.mixin;

import com.columbina.yujie.item.BigSweatyFootAuraHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public abstract class MobEntityTargetMixin {
	@Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
	private void dongbeiyujie$rejectBigSweatyFootWearerTarget(LivingEntity target, CallbackInfo ci) {
		MobEntity self = (MobEntity) (Object) this;
		if (BigSweatyFootAuraHandler.shouldRepel(self, target)) {
			if (self.getTarget() != null) {
				self.setTarget(null);
			}
			ci.cancel();
		}
	}
}
