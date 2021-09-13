package tenumn.xiushengyangxi.joker.block;

import net.minecraft.tileentity.TileEntity;
import tenumn.xiushengyangxi.joker.JokerRegistry;

//麦穗全息投影方块实体
public class WheatHolographicTileEntity extends TileEntity {
    public WheatHolographicTileEntity() {
        super(JokerRegistry.TileEntityTypes.WHEAT_HOLOGRAPHIC.get());
    }
}
