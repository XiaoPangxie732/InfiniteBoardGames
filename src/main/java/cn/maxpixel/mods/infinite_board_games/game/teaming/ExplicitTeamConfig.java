package cn.maxpixel.mods.infinite_board_games.game.teaming;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;

import java.util.Optional;

public record ExplicitTeamConfig(
        TeamConfig config,
        String id,
        Optional<Component> displayName
) {
    public static final Codec<ExplicitTeamConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            TeamConfig.MAP_CODEC.forGetter(ExplicitTeamConfig::config),
            Codec.STRING.fieldOf("id").forGetter(ExplicitTeamConfig::id),
            ComponentSerialization.CODEC.optionalFieldOf("display_name").forGetter(ExplicitTeamConfig::displayName)
    ).apply(instance, ExplicitTeamConfig::new));
}
