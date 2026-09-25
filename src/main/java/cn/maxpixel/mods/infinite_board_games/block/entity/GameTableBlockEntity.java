package cn.maxpixel.mods.infinite_board_games.block.entity;

import cn.maxpixel.mods.infinite_board_games.block.GameTableBlock;
import cn.maxpixel.mods.infinite_board_games.menu.GameTableMenu;
import cn.maxpixel.mods.infinite_board_games.registry.BlockEntityRegistry;
import cn.maxpixel.mods.infinite_board_games.registry.ItemRegistry;
import cn.maxpixel.mods.infinite_board_games.util.I18nKey;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.world.Container;
import net.minecraft.world.LockCode;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

public class GameTableBlockEntity extends BlockEntity implements MenuProvider, ContainerSingleItem.BlockContainerSingleItem, Nameable {// TODO: Shall we support transactions?
    public static final String DEFAULT_NAME_KEY = I18nKey.container(GameTableBlock.ID);
    public static final String INSTALLED_GAME_TAG = "InstalledGame";
    public static final String BASE_TEXTURE_TAG = "BaseTexture";
    public static final String BASE_TEXTURE_SUFFIX_TAG = "BaseTextureSuffix";
    private static final Component DEFAULT_NAME = Component.translatable(DEFAULT_NAME_KEY);
    private LockCode lockKey = LockCode.NO_LOCK;
    private @Nullable Component name;
    private Identifier baseTexture = BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_OAK_LOG);
    private String baseTextureSuffix = "";
    private ItemStack installedGame = ItemStack.EMPTY;
    private boolean isInGame;

    public GameTableBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(BlockEntityRegistry.GAME_TABLE.get(), worldPosition, blockState);
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        super.preRemoveSideEffects(pos, state);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.lockKey = LockCode.fromTag(input);
        this.name = parseCustomNameSafe(input, Entity.TAG_CUSTOM_NAME);
        this.baseTexture = input.read(BASE_TEXTURE_TAG, Identifier.CODEC).orElse(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_OAK_LOG));
        this.baseTextureSuffix = input.getString(BASE_TEXTURE_SUFFIX_TAG).orElse("").trim();
        ItemStack newGame = input.read(INSTALLED_GAME_TAG, ItemStack.CODEC).orElse(ItemStack.EMPTY);
        if (!installedGame.isEmpty() && !ItemStack.isSameItemSameComponents(newGame, installedGame) && isInGame) {
            // TODO: Force end game here
            throw new RuntimeException("The game has been forcibly terminated due to data inconsistencies");
        }
        this.installedGame = newGame;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        lockKey.addToTag(output);
        output.storeNullable(Entity.TAG_CUSTOM_NAME, ComponentSerialization.CODEC, this.name);
        if (!installedGame.isEmpty()) {
            output.store(INSTALLED_GAME_TAG, ItemStack.CODEC, installedGame);
        }
        output.store(BASE_TEXTURE_TAG, Identifier.CODEC, baseTexture);
        if (!baseTextureSuffix.isBlank()) output.putString(BASE_TEXTURE_SUFFIX_TAG, baseTextureSuffix);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void updateClients() {
        if (level != null) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
        }
    }

    public void setChangedAndUpdateClients() {
        setChanged();
        updateClients();
    }

    public Identifier getBaseTextureLocation() {
        return baseTextureSuffix.isEmpty() ? baseTexture : baseTexture.withSuffix(baseTextureSuffix);
    }

    public void setBaseTexture(Block textureBlock) {
        setBaseTexture(BuiltInRegistries.BLOCK.getKey(textureBlock));
    }

    public void setBaseTexture(Identifier texture) {
        this.baseTexture = Objects.requireNonNull(texture);
        setChanged();
    }

    public void setBaseTextureSuffix(String suffix) {
        this.baseTextureSuffix = suffix.trim();
        setChanged();
    }

    public boolean isInGame() {
        return isInGame;
    }

    @Override
    public Component getName() {
        return name == null ? DEFAULT_NAME : name;
    }

    @Override
    public Component getDisplayName() {
        return getName();
    }

    public boolean canOpen(Player player) {
        return lockKey.canUnlock(player);
    }

    public boolean isLocked() {
        return !lockKey.equals(LockCode.NO_LOCK);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        if (canOpen(player)) {
            return new GameTableMenu(containerId, inventory, this);
        } else {
            BaseContainerBlockEntity.sendChestLockedNotifications(getBlockPos().getCenter(), player, getName());
            return null;
        }
    }

    @Override
    public BlockEntity getContainerBlockEntity() {
        return this;
    }

    @Override
    public ItemStack getTheItem() {
        return installedGame;
    }

    @Override
    public void setTheItem(ItemStack itemStack) {
        this.installedGame = Objects.requireNonNull(itemStack);
        setChanged();
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack itemStack) {
        return itemStack.is(ItemRegistry.GAME);
    }

    @Override
    public boolean canTakeItem(Container into, int slot, ItemStack itemStack) {
        return !isInGame;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter components) {
        super.applyImplicitComponents(components);
        this.name = components.get(DataComponents.CUSTOM_NAME);
        this.lockKey = components.getOrDefault(DataComponents.LOCK, LockCode.NO_LOCK);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(DataComponents.CUSTOM_NAME, name);
        if (isLocked()) {
            components.set(DataComponents.LOCK, lockKey);
        }
    }

    @Override
    public void removeComponentsFromTag(ValueOutput output) {
        output.discard("CustomName");
        output.discard("lock");
    }
}