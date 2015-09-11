package unwrittenfun.minecraft.immersiveintegration.multiblocks;

import blusunrize.immersiveengineering.api.MultiblockHandler;
import blusunrize.immersiveengineering.api.MultiblockHandler.IMultiblock;

public class IIMultiblocks {
  public static IMultiblock industrialCokeOven;
  public static IMultiblock heavyFarmingStation;

  public static void registerMultiblocks() {
    MultiblockHandler.registerMultiblock(industrialCokeOven = new MultiblockIndustrialCokeOven());
    MultiblockHandler.registerMultiblock(heavyFarmingStation = new MultiblockHeavyFarmingStation());
  }
}
