package unwrittenfun.minecraft.immersiveintegration.items;

import appeng.api.AEApi;
import appeng.api.definitions.IDefinitions;
import appeng.api.util.AEColor;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import unwrittenfun.minecraft.immersiveintegration.ImmersiveIntegration;
import unwrittenfun.minecraft.immersiveintegration.ModInfo;
import unwrittenfun.minecraft.immersiveintegration.blocks.IIBlocks;
import unwrittenfun.minecraft.immersiveintegration.wires.IIWires;

public class IIItems {
  public static String AE_COIL_KEY = "wireCoil"; // Should be aeWireCoil but backwards compatibility.
  public static String II_COIL_KEY = "iiWireCoil";
  public static String FLUID_CONTAINER = "fluidContainer";

  public static final int FLUIX_COIL_META = 0;
  public static final int DENSE_COIL_META = 1;
  public static final int REDSTONE_COIL_META = 0;

  public static Item aeWireCoil;
  public static Item iiWireCoil;
  public static Item fluidContainer;

  public static void registerItems() {
    iiWireCoil = new ItemCoil(ModInfo.MOD_ID + ":" + II_COIL_KEY, new String[] { "Redstone" }, IIWires.redstoneWire);
    fluidContainer = new ItemFluidContainer(FLUID_CONTAINER);

    GameRegistry.registerItem(iiWireCoil, II_COIL_KEY);

    FluidContainerRegistry.registerFluidContainer(IIBlocks.fluidIndustrialFertiliser, new ItemStack(fluidContainer, 1, 0), new ItemStack(Items.glass_bottle));
    FluidContainerRegistry.registerFluidContainer(IIBlocks.fluidIndustrialFertiliser, new ItemStack(fluidContainer, 1, 1), new ItemStack(Items.bucket));

    if (ImmersiveIntegration.cfg.enableAE) registerAE(false);
  }

  public static void registerRecipes() {
    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(iiWireCoil, 4, REDSTONE_COIL_META), " e ", "rsr", " e ", 'r', "dustRedstone", 'e', "ingotElectrum", 's', "stickWood"));
    if (ImmersiveIntegration.cfg.enableAE) registerAE(true);
  }

  private static void registerAE(boolean recipes) {
    if (recipes) {
      IDefinitions ae = AEApi.instance().definitions();
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(aeWireCoil, 4, FLUIX_COIL_META), "cfc", "fsf", "cfc", 'c', ae.parts().cableGlass().stack(AEColor.Transparent, 1), 'f', "crystalPureFluix", 's', "stickWood"));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(aeWireCoil, 4, FLUIX_COIL_META), "cfc", "fsf", "cfc", 'c', ae.parts().cableGlass().stack(AEColor.Transparent, 1), 'f', "crystalFluix", 's', "stickWood"));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(aeWireCoil, 2, DENSE_COIL_META), "wcw", "csc", "wcw", 'c', ae.parts().cableDense().stack(AEColor.Transparent, 1), 'w', new ItemStack(aeWireCoil, 1, FLUIX_COIL_META), 's', "stickWood"));
    } else {
      aeWireCoil = new ItemCoil(ModInfo.MOD_ID + ":" + AE_COIL_KEY, new String[] { "Fluix", "Dense" }, IIWires.fluixWire, IIWires.denseWire);
      GameRegistry.registerItem(aeWireCoil, AE_COIL_KEY);
    }
  }
}
