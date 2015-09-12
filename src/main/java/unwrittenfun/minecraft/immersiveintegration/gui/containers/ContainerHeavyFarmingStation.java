package unwrittenfun.minecraft.immersiveintegration.gui.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import unwrittenfun.minecraft.immersiveintegration.client.gui.SlotValid;
import unwrittenfun.minecraft.immersiveintegration.tiles.TileHeavyFarmingStation;

public class ContainerHeavyFarmingStation extends Container {
  public TileHeavyFarmingStation farmingStation;
  public InventoryPlayer inventoryPlayer;
  public int prevFluidAmount = 0;
  public int[] prevProgressValues = new int[4];

  public ContainerHeavyFarmingStation(TileHeavyFarmingStation farmingStation, InventoryPlayer inventoryPlayer) {
    this.farmingStation = farmingStation;
    this.inventoryPlayer = inventoryPlayer;

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++) {
        addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 85 + i * 18));
      }
    }

    for (int i = 0; i < 9; i++) {
      addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 143));
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        addSlotToContainer(new SlotValid(farmingStation, i + j, j * 18 + 9, i * 18 + 16));
        addSlotToContainer(new SlotValid(farmingStation, i + j, j * 18 + 74, i * 18 + 16));
      }
    }
  }

  @Override
  public boolean canInteractWith(EntityPlayer player) {
    return true;
  }

  @Override
  public ItemStack transferStackInSlot(EntityPlayer player, int i) {
    Slot slot = getSlot(i);
    if (slot != null && slot.getHasStack()) {
      ItemStack stack = slot.getStack();
      ItemStack result = stack.copy();
      if (i >= 36) {
        if (!mergeItemStack(stack, 0, 36, false)) return null;
      } else {
        boolean merged = false;
        for (int j = 0; j < farmingStation.getSizeInventory(); j++) {
          if (farmingStation.isItemValidForSlot(j, stack)) {
            if (mergeItemStack(stack, 36 + j, 37 + j, false)) {
              merged = true;
              break;
            }
          }
        }
        if (!merged) return null;
      }
      if (stack.stackSize == 0) slot.putStack(null);
      else slot.onSlotChanged();
      slot.onPickupFromSlot(player, stack);
      return result;
    }
    return null;
  }

  @Override
  public void addCraftingToCrafters(ICrafting crafter) {
    super.addCraftingToCrafters(crafter);

    crafter.sendProgressBarUpdate(this, 4, farmingStation.tank.getFluidAmount());

    for (int i = 0; i < 4; i++) {
      crafter.sendProgressBarUpdate(this, i, farmingStation.getProgressFor(i));
    }
  }

  @Override
  public void detectAndSendChanges() {
    super.detectAndSendChanges();
    if (prevFluidAmount != farmingStation.tank.getFluidAmount()) {
      prevFluidAmount = farmingStation.tank.getFluidAmount();
      for (Object crafter : crafters) {
        ((ICrafting) crafter).sendProgressBarUpdate(this, 4, prevFluidAmount);
      }
    }

    for (int i = 0; i < 4; i++) {
      if (prevProgressValues[i] != farmingStation.getProgressFor(i)) {
        prevProgressValues[i] = farmingStation.getProgressFor(i);
        for (Object crafter : crafters) {
          ((ICrafting) crafter).sendProgressBarUpdate(this, i, prevProgressValues[i]);
        }
      }
    }
  }
}
