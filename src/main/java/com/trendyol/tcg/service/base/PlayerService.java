package com.trendyol.tcg.service.base;

import com.trendyol.tcg.model.Card;
import com.trendyol.tcg.model.Match;
import com.trendyol.tcg.model.Player;

public interface PlayerService {

    Player getActivePlayer(Match match);

    Player getOpponentPlayer(Player player, Match match);

    Integer checkPlayersHeal(Match match);

    Player refillMana(Player player);

    Player increaseManaSlots(Player player);

    Player reduceHeal(Player player, Card card);

    Player reduceMana(Player player, Card card);

    boolean canMakeMove(Player player, Card card);

}
