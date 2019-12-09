package com.trendyol.tcg.service.base;

import com.trendyol.tcg.model.Match;
import com.trendyol.tcg.model.Player;

public interface MatchService {

    Match getCurrentMatch();

    Match startMatch(Player player1, Player player2);

    void finishMatch();

    void changeTurnFlag();

    Player checkTurnAndReturnNewPlayer();

}
