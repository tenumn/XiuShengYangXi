package tenumn.xiushengyangxi.common.capabilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TranslationTextComponent;

public class RecuperateCap implements IRecuperateCap {
    private static final String POOP_COUNT_DOWN = "poop_count_down";
    private int poop_countdown = 0;
    private int MIN_POOP_COUNTDOWN = 1000;

    @Override
    public boolean canPoop(LivingEntity livingEntity) {
        return poop_countdown <= 0;
    }

    @Override
    public void poop(LivingEntity livingEntity) {
        livingEntity.attackEntityFrom(DamageSource.MAGIC, 10);
        poop_countdown = MIN_POOP_COUNTDOWN;
        livingEntity.sendMessage(new TranslationTextComponent("msg.hurt_by_fully_shit"), livingEntity.getUniqueID());
    }

    @Override
    public int getPoopCountDown() {
        return poop_countdown;
    }

    @Override
    public void onTick(LivingEntity livingEntity) {
        if (poop_countdown > 0) poop_countdown--;
        if (canPoop(livingEntity)) poop(livingEntity);
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putInt(POOP_COUNT_DOWN, poop_countdown);
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        poop_countdown = nbt.getInt(POOP_COUNT_DOWN);
    }
}
