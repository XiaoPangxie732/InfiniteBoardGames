package cn.maxpixel.mods.infinite_board_games.game.action;

public enum MergeDirection {
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
    SECONDARY_DIAGONAL// equivalent to [left_up, right_down]
}