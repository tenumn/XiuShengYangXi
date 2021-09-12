package tenumn.xiushengyangxi.common.capabilities;

//养生能力通用接口

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IRecuperateCap extends INBTSerializable<CompoundNBT> {
    boolean canPoop(LivingEntity livingEntity);//能否拉屎

    void poop(LivingEntity livingEntity);//执行拉屎

    int getPoopCountDown();//拉屎的倒计时

    void onTick(LivingEntity livingEntity);
}
