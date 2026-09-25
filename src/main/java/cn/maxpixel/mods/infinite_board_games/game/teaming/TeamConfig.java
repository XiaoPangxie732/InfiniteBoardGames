package cn.maxpixel.mods.infinite_board_games.game.teaming;

import cn.maxpixel.mods.infinite_board_games.game.action.turn.TurnAction;

import java.util.List;

public class TeamConfig {
    private TeamConfig parent;
    private int minPlayers;
    private int maxPlayers;
    private List<TurnAction> turnActions;
}
