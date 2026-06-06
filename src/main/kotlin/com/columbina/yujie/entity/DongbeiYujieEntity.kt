package com.columbina.yujie.entity

import com.columbina.yujie.registry.DongbeiYujieEffects
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.ActiveTargetGoal
import net.minecraft.entity.ai.goal.LookAroundGoal
import net.minecraft.entity.ai.goal.LookAtEntityGoal
import net.minecraft.entity.ai.goal.MeleeAttackGoal
import net.minecraft.entity.ai.goal.RevengeGoal
import net.minecraft.entity.ai.goal.SwimGoal
import net.minecraft.entity.ai.goal.WanderAroundFarGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World

class DongbeiYujieEntity(entityType: EntityType<out HostileEntity>, world: World) : HostileEntity(entityType, world) {

    init {
        this.experiencePoints = 50
    }

    override fun initGoals() {
        this.goalSelector.add(1, SwimGoal(this))
        this.goalSelector.add(2, MeleeAttackGoal(this, 1.0, false)) // Standard zombie bump attack
        this.goalSelector.add(7, WanderAroundFarGoal(this, 1.0))
        this.goalSelector.add(8, LookAtEntityGoal(this, PlayerEntity::class.java, 8.0f))
        this.goalSelector.add(8, LookAroundGoal(this))

        this.targetSelector.add(1, RevengeGoal(this))
        // Target any LivingEntity except players and other Dongbei Yujie entities
        this.targetSelector.add(2, ActiveTargetGoal(this, LivingEntity::class.java, 10, true, false) { target, _ ->
            target !is PlayerEntity && target !is DongbeiYujieEntity
        })
    }

    override fun setTarget(target: LivingEntity?) {
        if (target is PlayerEntity || target is DongbeiYujieEntity) {
            return
        }
        super.setTarget(target)
    }

    override fun tick() {
        super.tick()
        if (!this.entityWorld.isClient && !this.hasStatusEffect(DongbeiYujieEffects.DAIPAI_ENTRY)) {
            this.addStatusEffect(
                StatusEffectInstance(
                    DongbeiYujieEffects.DAIPAI_ENTRY,
                    StatusEffectInstance.INFINITE,
                    0,
                    false,
                    true,
                    true
                )
            )
        }
    }

    companion object {
        fun createDongbeiYujieAttributes(): DefaultAttributeContainer.Builder {
            return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 100.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.ATTACK_DAMAGE, 12.0)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.5)
        }
    }
}
