package unwrittenfun.minecraft.immersiveintegration.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import unwrittenfun.minecraft.immersiveintegration.ModInfo;
import unwrittenfun.minecraft.immersiveintegration.blocks.IIBlocks;

public class ClientEventHandler
{
  @SubscribeEvent
  public void textureStich(TextureStitchEvent.Pre event)
  {
    if(event.map.getTextureType()==0)
    {
      if(IIBlocks.IIIndustrialFertiliser)
        IIBlocks.fluidIndustrialFertiliser.setIcons(event.map.registerIcon(ModInfo.MOD_ID + ":" + "fluid/industrialfertilizer_still"), event.map.registerIcon(ModInfo.MOD_ID + ":" + "fluid/industrialfert²ilizer_flow"));
    }
  }
}
