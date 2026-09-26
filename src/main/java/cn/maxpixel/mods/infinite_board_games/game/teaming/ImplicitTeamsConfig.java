package cn.maxpixel.mods.infinite_board_games.game.teaming;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record ImplicitTeamsConfig(
        int minTeams,
        int maxTeams
) {
    public static final Codec<ImplicitTeamsConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("min_teams").forGetter(ImplicitTeamsConfig::minTeams),
            Codec.INT.fieldOf("max_teams").forGetter(ImplicitTeamsConfig::maxTeams)
    ).apply(instance, ImplicitTeamsConfig::new));
}