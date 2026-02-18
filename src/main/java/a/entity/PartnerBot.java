package a.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.Mob;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import org.jetbrains.annotations.Nullable;

public class PartnerBot extends TamableAnimal {

    private static final EntityDataAccessor<Integer> INTIMACY_LEVEL = SynchedEntityData.defineId(PartnerBot.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_AROUSED = SynchedEntityData.defineId(PartnerBot.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_NAKED = SynchedEntityData.defineId(PartnerBot.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<String> CURRENT_POSE = SynchedEntityData.defineId(PartnerBot.class, EntityDataSerializers.STRING);

    public PartnerBot(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(INTIMACY_LEVEL, 0);
        this.entityData.define(IS_AROUSED, false);
        this.entityData.define(IS_NAKED, false);
        this.entityData.define(CURRENT_POSE, "idle");
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.2D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (this.level().isClientSide) {
            return InteractionResult.SUCCESS;
        }

        if (this.isTame()) {
            if (this.isOwnedBy(player)) {
                if (itemstack.is(Items.DIAMOND)) {
                    this.increaseIntimacy(10);
                    this.setAroused(true);
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                    return InteractionResult.SUCCESS;
                }

                if (player.isShiftKeyDown()) {
                    this.setNaked(!this.isNaked());
                    return InteractionResult.SUCCESS;
                }

                this.setOrderedToSit(!this.isOrderedToSit());
                return InteractionResult.SUCCESS;
            }
        } else if (itemstack.is(Items.IRON_INGOT)) {
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            if (this.random.nextInt(2) == 0) {
                this.tame(player);
                this.navigation.stop();
                this.setTarget(null);
                this.setOrderedToSit(true);
                this.level().broadcastEntityEvent(this, (byte) 7);
            } else {
                this.level().broadcastEntityEvent(this, (byte) 6);
            }
            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }

    public int getIntimacyLevel() {
        return this.entityData.get(INTIMACY_LEVEL);
    }

    public void increaseIntimacy(int amount) {
        this.entityData.set(INTIMACY_LEVEL, this.getIntimacyLevel() + amount);
    }

    public boolean isAroused() {
        return this.entityData.get(IS_AROUSED);
    }

    public void setAroused(boolean aroused) {
        this.entityData.set(IS_AROUSED, aroused);
    }

    public boolean isNaked() {
        return this.entityData.get(IS_NAKED);
    }

    public void setNaked(boolean naked) {
        this.entityData.set(IS_NAKED, naked);
    }

    public String getCurrentPose() {
        return this.entityData.get(CURRENT_POSE);
    }

    public void setCurrentPose(String pose) {
        this.entityData.set(CURRENT_POSE, pose);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("IntimacyLevel", this.getIntimacyLevel());
        compound.putBoolean("IsAroused", this.isAroused());
        compound.putBoolean("IsNaked", this.isNaked());
        compound.putString("CurrentPose", this.getCurrentPose());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(INTIMACY_LEVEL, compound.getInt("IntimacyLevel"));
        this.entityData.set(IS_AROUSED, compound.getBoolean("IsAroused"));
        this.entityData.set(IS_NAKED, compound.getBoolean("IsNaked"));
        this.entityData.set(CURRENT_POSE, compound.getString("CurrentPose"));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return null;
    }
}