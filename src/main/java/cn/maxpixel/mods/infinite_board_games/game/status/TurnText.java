package cn.maxpixel.mods.infinite_board_games.game.status;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;

public record TurnText(
        Component ours,
        Component others
) implements StatusText {
    public static final String TYPE = "turn_text";
    public static final MapCodec<TurnText> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ComponentSerialization.CODEC.fieldOf("ours").forGetter(TurnText::ours),
            ComponentSerialization.CODEC.fieldOf("others").forGetter(TurnText::others)
    ).apply(instance, TurnText::new));

    @Override
    public String type() {
        return TYPE;
    }

    @Override
    public MapCodec<? extends StatusText> codec() {
        return MAP_CODEC;
    }
}