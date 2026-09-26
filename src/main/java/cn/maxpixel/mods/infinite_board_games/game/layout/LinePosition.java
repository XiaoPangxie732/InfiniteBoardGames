package cn.maxpixel.mods.infinite_board_games.game.layout;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public enum LinePosition implements StringRepresentable {
    AROUND,
    INTERSECT,
    NONE;
    public static final Codec<LinePosition> CODEC = StringRepresentable.fromEnum(LinePosition::values);

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}