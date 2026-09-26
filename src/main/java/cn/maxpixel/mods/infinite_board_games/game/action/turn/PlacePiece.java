package cn.maxpixel.mods.infinite_board_games.game.action.turn;

import cn.maxpixel.mods.infinite_board_games.game.layout.component.GridComponent;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record PlacePiece(
        String id,
        String on
) implements TurnAction {
    public static final String TYPE = "place_piece";
    public static final MapCodec<PlacePiece> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.fieldOf("id").forGetter(PlacePiece::id),
            Codec.STRING.fieldOf("on").forGetter(PlacePiece::on)
    ).apply(instance, PlacePiece::new));

    @Override
    public String type() {
        return TYPE;
    }

    @Override
    public MapCodec<? extends TurnAction> codec() {
        return MAP_CODEC;
    }
}