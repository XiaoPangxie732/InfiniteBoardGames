package cn.maxpixel.mods.infinite_board_games.game.teaming;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;
import java.util.Optional;

public record Teaming(
        boolean duplicatable,
        TeamConfig defaultTeamConfig,
        List<ExplicitTeamConfig> explicitTeams,
        Optional<ImplicitTeamsConfig> implicitTeams
) {
    public static final Codec<Teaming> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("duplicatable").forGetter(Teaming::duplicatable),
            TeamConfig.CODEC.fieldOf("default_team_config").forGetter(Teaming::defaultTeamConfig),
            ExplicitTeamConfig.CODEC.listOf().optionalFieldOf("explicit_teams", List.of()).forGetter(Teaming::explicitTeams),
            ImplicitTeamsConfig.CODEC.optionalFieldOf("implicit_teams").forGetter(Teaming::implicitTeams)
    ).apply(instance, Teaming::new));
}