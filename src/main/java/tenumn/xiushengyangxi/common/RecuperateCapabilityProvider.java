package tenumn.xiushengyangxi.common;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import tenumn.xiushengyangxi.common.capabilities.IRecuperateCap;
import tenumn.xiushengyangxi.common.capabilities.RecuperateCap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RecuperateCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT> {
    private IRecuperateCap recuperateCap;

    //提供养生能力
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == ModRegistry.RECUPERATE_CAPABILITY ? LazyOptional.of(this::getOrCreateCapability).cast() : LazyOptional.empty();
    }

    @Nonnull
    IRecuperateCap getOrCreateCapability() {
        if (recuperateCap == null) {
            this.recuperateCap = new RecuperateCap();
        }
        return this.recuperateCap;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return getOrCreateCapability().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        getOrCreateCapability().deserializeNBT(nbt);
    }
}
