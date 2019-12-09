package com.trendyol.tcg.service;

import com.trendyol.tcg.model.Card;
import com.trendyol.tcg.model.Match;
import com.trendyol.tcg.model.Move;
import com.trendyol.tcg.model.Player;
import com.trendyol.tcg.service.base.MatchService;
import com.trendyol.tcg.service.base.MoveService;
import com.trendyol.tcg.service.base.PlayerService;

import java.util.ArrayList;
import java.util.List;

public class MoveServiceImpl implements MoveService {

    private static MoveServiceImpl moveServiceImpl;
    private MatchService matchService = MatchServiceImpl.getInstance();
    private PlayerService playerService = PlayerServiceImpl.getInstance();

    /**
     * Singleton Pattern
     */
    private MoveServiceImpl() {
    }

    public static MoveServiceImpl getInstance() {
        if (moveServiceImpl == null) {
            moveServiceImpl = new MoveServiceImpl();
        }
        return moveServiceImpl;
    }

    public Move makeMove(Player player, Card card) {

        if (!playerService.canMakeMove(player, card)) {
            System.out.println(player.getNickname() + " can move with another cards or can give turn to opponent.");
            return null;
        }

        Match currentMatch = matchService.getCurrentMatch();

        Player opponent = playerService.getOpponentPlayer(player, currentMatch);

        player = playerService.reduceMana(player, card);

        playerService.reduceHeal(opponent, card);

        Move newMove = new Move(player, card);

        currentMatch.addMove(newMove);

        List<Card> hand = player.getHand();
        List<Card> newHand = new ArrayList<>(hand);
        newHand.remove(card);
        player.setHand(newHand);

        if (!canMakeMoveWithHand(player))
            matchService.changeTurnFlag();
        else
            System.out.println("[INFO] " + player.getNickname() + " can make new move.");

        return newMove;
    }

    public boolean canMakeMoveWithHand(Player player) {

        List<Card> cards = player.getHand();

        return cards.stream().anyMatch(card -> card.getManaCost() <= player.getMana());
    }
}
