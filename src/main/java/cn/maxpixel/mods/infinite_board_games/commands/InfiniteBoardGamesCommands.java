package cn.maxpixel.mods.infinite_board_games.commands;

import cn.maxpixel.mods.infinite_board_games.block.entity.GameTableBlockEntity;
import cn.maxpixel.mods.infinite_board_games.util.I18nKey;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.IdentifierArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;

public class InfiniteBoardGamesCommands {
    public static final String NAME = "infinite_board_games";
    public static final String KEY_POS_NOT_GAME_TABLE = I18nKey.commands(NAME, "failure.pos_not_game_table");
    public static final String KEY_SUCCESS_SET_BASE_TEXTURE = I18nKey.commands(NAME, "success.set_base_texture");
    public static final String KEY_SUCCESS_SET_BASE_TEXTURE_SUFFIX = I18nKey.commands(NAME, "success.set_base_texture_suffix");
    public static final String KEY_SUCCESS_CLEAR_BASE_TEXTURE_SUFFIX = I18nKey.commands(NAME, "success.clear_base_texture_suffix");
    private static final SimpleCommandExceptionType POS_NOT_GAME_TABLE = new SimpleCommandExceptionType(Component.translatable(KEY_POS_NOT_GAME_TABLE));

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal(NAME)
                .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .then(Commands.literal("set_base_texture")
                        .then(Commands.argument("pos", BlockPosArgument.blockPos())
                                .then(Commands.argument("texture", IdentifierArgument.id())
                                        .executes(InfiniteBoardGamesCommands::setBoardBaseTexture)
                                )
                        )
                ).then(Commands.literal("set_base_texture_suffix")
                        .then(Commands.argument("pos", BlockPosArgument.blockPos())
                                .executes(context -> setBoardBaseTextureSuffix(context, true))
                                .then(Commands.argument("suffix", StringArgumentType.word())
                                        .executes(context -> setBoardBaseTextureSuffix(context, false))
                                )
                        )
                )
        );
    }

    private static int setBoardBaseTexture(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerLevel level = context.getSource().getLevel();
        BlockPos pos = BlockPosArgument.getLoadedBlockPos(context, level, "pos");
        Identifier texture = IdentifierArgument.getId(context, "texture");
        if (level.getBlockEntity(pos) instanceof GameTableBlockEntity blockEntity) {
            blockEntity.setBaseTexture(texture);
            blockEntity.updateClients();
            context.getSource().sendSuccess(() -> Component.translatable(KEY_SUCCESS_SET_BASE_TEXTURE, pos.getX(), pos.getY(), pos.getZ(), texture.toString()), false);
            return 1;
        } else {
            throw POS_NOT_GAME_TABLE.create();
        }
    }

    private static int setBoardBaseTextureSuffix(CommandContext<CommandSourceStack> context, boolean clear) throws CommandSyntaxException {
        ServerLevel level = context.getSource().getLevel();
        BlockPos pos = BlockPosArgument.getLoadedBlockPos(context, level, "pos");
        String suffix = clear ? "" : StringArgumentType.getString(context, "suffix");
        if (level.getBlockEntity(pos) instanceof GameTableBlockEntity blockEntity) {
            blockEntity.setBaseTextureSuffix(suffix);
            blockEntity.updateClients();
            context.getSource().sendSuccess(clear ?
                    () -> Component.translatable(KEY_SUCCESS_CLEAR_BASE_TEXTURE_SUFFIX, pos.getX(), pos.getY(), pos.getZ()) :
                    () -> Component.translatable(KEY_SUCCESS_SET_BASE_TEXTURE_SUFFIX, pos.getX(), pos.getY(), pos.getZ(), suffix),
                    false);
            return 1;
        } else {
            throw POS_NOT_GAME_TABLE.create();
        }
    }
}
