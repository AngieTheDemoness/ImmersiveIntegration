package unwrittenfun.minecraft.immersiveintegration.items;

import blusunrize.immersiveengineering.common.items.ItemIEBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import unwrittenfun.minecraft.immersiveintegration.ImmersiveIntegration;
import unwrittenfun.minecraft.immersiveintegration.ModInfo;

public class ItemFluidContainer extends ItemIEBase
{
  public ItemFluidContainer(String name) {
    super(name, 64, "BottleIndustrialFertiliser", "BucketIndustrialFertiliser");
    setUnlocalizedName(ModInfo.MOD_ID + ":" + name);
    setCreativeTab(ImmersiveIntegration.iiCreativeTab);
  }

  @Override
  public void registerIcons(IIconRegister ir) {
    for(int i = 0; i < this.icons.length; ++i) {
      this.icons[i] = ir.registerIcon(ModInfo.MOD_ID + ":"+ this.itemName + this.getSubNames()[i]);
    }
  }

  @Override
  public boolean hasContainerItem(ItemStack stack)
  {
    return true;
  }
  @Override
  public ItemStack getContainerItem(ItemStack stack)
  {
    return stack.getItemDamage()%2==0?new ItemStack(Items.glass_bottle): new ItemStack(Items.bucket);
  }
  @Override
  public int getItemStackLimit(ItemStack stack)
  {
    return stack.getItemDamage()%2==0?16:1;
  }
}
