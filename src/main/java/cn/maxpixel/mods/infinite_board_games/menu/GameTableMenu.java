package cn.maxpixel.mods.infinite_board_games.menu;

import cn.maxpixel.mods.infinite_board_games.registry.ItemRegistry;
import cn.maxpixel.mods.infinite_board_games.registry.MenuTypeRegistry;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

public class GameTableMenu extends AbstractContainerMenu {
    private static final int GAME_INSTALLATION_SLOT = 0;
    private static final int INV_SLOT_START = 1;
    private static final int INV_SLOT_END = 28;
    private static final int HOTBAR_START = 28;
    private static final int HOTBAR_END = 37;
    private final Container gameTable;
    private final GameInstallationSlot gameSlot;

    public GameTableMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(1) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
    }

    public GameTableMenu(int containerId, Inventory inventory, Container gameTable) {
        super(MenuTypeRegistry.GAME_TABLE.get(), containerId);
        this.gameTable = Objects.requireNonNull(gameTable);
        this.gameSlot = new GameInstallationSlot(gameTable, 0, 80, 20);
        addSlot(gameSlot);
        addStandardInventorySlots(inventory, 8, 51);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        ItemStack ret = ItemStack.EMPTY;
        Slot slot = slots.get(slotIndex);

        if (slot.hasItem()) {
            ItemStack rawStack = slot.getItem();
            ret = rawStack.copy();

            if (slotIndex == GAME_INSTALLATION_SLOT) {
                if (!moveItemStackTo(rawStack, INV_SLOT_START, HOTBAR_END, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(rawStack, ret);
            } else if (slotIndex >= INV_SLOT_START && slotIndex < HOTBAR_END) {
                // Try to move the inventory/hotbar slot into the data inventory input slots
                if (!this.moveItemStackTo(rawStack, GAME_INSTALLATION_SLOT, GAME_INSTALLATION_SLOT + 1, false)) {
                    // If cannot move and in player inventory slot, try to move to hotbar
                    if (slotIndex < INV_SLOT_END) {
                        if (!this.moveItemStackTo(rawStack, HOTBAR_START, HOTBAR_END, false)) {
                            // If cannot move, no longer quick move
                            return ItemStack.EMPTY;
                        }
                    }
                    // Else try to move hotbar into player inventory slot
                    else if (!this.moveItemStackTo(rawStack, INV_SLOT_START, INV_SLOT_END, false)) {
                        // If cannot move, no longer quick move
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (rawStack.isEmpty()) {
                // If the raw stack has completely moved out of the slot, set the slot to the empty stack
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                // Otherwise, notify the slot that that the stack count has changed
                slot.setChanged();
            }

            // Execute logic on what to do post move with the remaining stack
            // This can be removed if there are no `Slot` subtypes that override `onTake`
            slot.onTake(player, rawStack);
        }

        return ret; // Return the slot stack
    }

    @Override
    public boolean stillValid(Player player) {
        return gameTable.stillValid(player);
    }

    private static class GameInstallationSlot extends Slot {
        public GameInstallationSlot(Container container, int slot, int x, int y) {
            super(container, slot, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack itemStack) {
            return itemStack.is(ItemRegistry.GAME);
        }
    }
}