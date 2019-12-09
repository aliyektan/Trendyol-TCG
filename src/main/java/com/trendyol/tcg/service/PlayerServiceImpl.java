package com.trendyol.tcg.service;

import com.trendyol.tcg.constant.TCGConstants;
import com.trendyol.tcg.model.Card;
import com.trendyol.tcg.model.Match;
import com.trendyol.tcg.model.Player;
import com.trendyol.tcg.service.base.PlayerService;
import com.trendyol.tcg.util.PrinterUtil;

public class PlayerServiceImpl implements PlayerService {

    private static PlayerServiceImpl playerServiceImpl;

    /**
     * Singleton Pattern
     */
    private PlayerServiceImpl() {
    }

    public static PlayerServiceImpl getInstance() {
        if (playerServiceImpl == null) {
            playerServiceImpl = new PlayerServiceImpl();
        }
        return playerServiceImpl;
    }

    public Player getActivePlayer(Match match) {

        if (match.getPlayer1().isTurn())
            return match.getPlayer1();

        else if (match.getPlayer2().isTurn())
            return match.getPlayer2();

        return null;

    }

    public Player getOpponentPlayer(Player player, Match match) {

        if (player.equals(match.getPlayer1()))
            return match.getPlayer2();

        return match.getPlayer1();

    }

    public Player refillMana(Player player) {

        player.setMana(player.getManaSlots());

        PrinterUtil.printRefillManaMessage(player.getNickname());

        return player;


    }

    public Player increaseManaSlots(Player player) {

        if (player.getManaSlots() < TCGConstants.getInstance().getMaxManaSlots())
            player.setManaSlots(player.getManaSlots() + 1);

        PrinterUtil.printIncreaseManaSlotsMessage(player.getNickname());

        return player;

    }

    public Player reduceHeal(Player player, Card card) {

        player.setHeal(player.getHeal() - card.getDamage());
        player.setHasHeal(player.getHeal() > 0);

        PrinterUtil.printReduceHealMessage(player.getNickname(), player.getHeal(), card);

        return player;
    }

    public Player reduceMana(Player player, Card card) {

        player.setMana(player.getMana() - card.getManaCost());
        player.setHasMana(player.getMana() > 0);

        PrinterUtil.printReduceManaMessage(player.getNickname(), card);

        return player;
    }

    public boolean canMakeMove(Player player, Card card) {

        if (card.getManaCost() <= player.getMana())
            return true;

        PrinterUtil.printCanMakeMoveMessage(player.getNickname(), card);

        return false;
    }

    public Integer checkPlayersHeal(Match match) {

        if (!match.getPlayer1().isHasHeal())
            return 1;

        else if (!match.getPlayer2().isHasHeal())
            return 2;

        return 0;

    }
}
