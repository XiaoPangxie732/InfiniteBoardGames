package cn.maxpixel.mods.infinite_board_games.game.teaming;

import cn.maxpixel.mods.infinite_board_games.game.action.Actions;
import cn.maxpixel.mods.infinite_board_games.game.action.turn.TurnAction;
import cn.maxpixel.mods.infinite_board_games.game.action.turn.TurnActions;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.function.Function;

public record TeamConfig(
        int minPlayers,
        int maxPlayers,
        List<TurnAction> turnActions
) {
    public static final MapCodec<TeamConfig> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.optionalFieldOf("min_players", -2).forGetter(TeamConfig::minPlayers),
            Codec.INT.optionalFieldOf("max_players", -2).forGetter(TeamConfig::maxPlayers),
            Codec.either(TurnActions.CODEC.listOf(), TurnActions.CODEC)
                    .xmap(e -> e.map(Function.identity(), List::of), Either::left)
                    .optionalFieldOf("turn_actions", List.of())
                    .forGetter(TeamConfig::turnActions)
    ).apply(instance, TeamConfig::new));
    public static final Codec<TeamConfig> CODEC = MAP_CODEC.codec();
}
