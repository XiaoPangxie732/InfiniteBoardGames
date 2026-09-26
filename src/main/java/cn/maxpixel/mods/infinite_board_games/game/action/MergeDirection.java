package cn.maxpixel.mods.infinite_board_games.game.action;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public enum MergeDirection implements StringRepresentable {
    HORIZONTAL,// equivalent to [left, right]
    VERTICAL,// equivalent to [up, down]
    LEFT,
    RIGHT,
    UP,
    DOWN,
    LEFT_UP,
    LEFT_DOWN,
    RIGHT_UP,
    RIGHT_DOWN,
    MAIN_DIAGONAL,// equivalent to [right_up, left_down]
    SECONDARY_DIAGONAL;// equivalent to [left_up, right_down]
    public static final Codec<MergeDirection> CODEC = StringRepresentable.fromEnum(MergeDirection::values);

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}