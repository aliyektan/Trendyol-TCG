package com.trendyol.tcg.service;

import com.trendyol.tcg.model.Card;
import com.trendyol.tcg.model.Match;
import com.trendyol.tcg.model.MatchReport;
import com.trendyol.tcg.model.Player;
import com.trendyol.tcg.service.base.CardService;
import com.trendyol.tcg.service.base.DeckService;
import com.trendyol.tcg.service.base.MatchService;
import com.trendyol.tcg.service.base.PlayerService;
import com.trendyol.tcg.util.PrinterUtil;

import java.util.ArrayList;
import java.util.List;

public class MatchServiceImpl implements MatchService {

    private static Integer turnCount = 0;
    private static Match match = null;
    private static MatchServiceImpl matchServiceImpl;
    private PlayerService playerService = PlayerServiceImpl.getInstance();
    private CardService cardService = CardServiceImpl.getInstance();
    private DeckService deckService = DeckServiceImpl.getInstance();


    /**
     * Singleton Pattern
     */
    private MatchServiceImpl() {
    }

    public static MatchServiceImpl getInstance() {
        if (matchServiceImpl == null) {
            matchServiceImpl = new MatchServiceImpl();
        }
        return matchServiceImpl;
    }

    public Match getCurrentMatch() {
        return match;
    }

    public Match startMatch(Player player1, Player player2) {

        PrinterUtil.printStartMatchSec1Message();

        if (match == null) {
            match = new Match();
            match.setPlayer1(player1);
            match.setPlayer2(player2);
        }

        initPlayersDecksAndHands(player1, player2);

        PrinterUtil.printStartMatchSec2Message();

        changeTurnFlag();

        return match;
    }

    public void finishMatch() {

        MatchReport matchReport = new MatchReport();

        matchReport.setMatch(getCurrentMatch());
        matchReport
                .setWinner(
                        getCurrentMatch().getPlayer1().isHasHeal() ? getCurrentMatch().getPlayer1() : getCurrentMatch().getPlayer2()
                );
        matchReport
                .setLoser(
                        getCurrentMatch().getPlayer1().isHasHeal() ? getCurrentMatch().getPlayer2() : getCurrentMatch().getPlayer1()
                );

        PrinterUtil.printFinishMatchMessage();

        PrinterUtil.printMatchReport(matchReport, getCurrentMatch());

        System.exit(1);

    }

    public void changeTurnFlag() {

        Match currentMatch = getCurrentMatch();

        if (playerService.checkPlayersHeal(currentMatch) == 1 || playerService.checkPlayersHeal(currentMatch) == 2)
            finishMatch();


        Player playerOfNewTurn = checkTurnAndReturnNewPlayer();
        Player playerOfOldTurn = playerService.getOpponentPlayer(playerOfNewTurn, currentMatch);
        playerOfOldTurn.setTurn(false);

        if (turnCount < 2)
            turnCount++;
        else {
            List<Card> playerOfNewTurnHand = playerOfNewTurn.getHand();
            List<Card> newHand = new ArrayList<>(playerOfNewTurnHand);

            Card randomCard = cardService.pickRandomCardForTurn(playerOfNewTurn);
            if (randomCard != null)
                newHand.add(randomCard);

            playerOfNewTurn.setHand(newHand);

            playerService.increaseManaSlots(playerOfNewTurn);
            playerService.refillMana(playerOfNewTurn);
        }

        playerOfNewTurn.setTurn(true);

        PrinterUtil.printNewTurnInfo(playerOfNewTurn);

    }

    public Player checkTurnAndReturnNewPlayer() {

        Player playerOfNewTurn;

        if (match.getPlayer2().isTurn()) {
            playerOfNewTurn = match.getPlayer1();
        } else {
            playerOfNewTurn = match.getPlayer2();
        }

        return playerOfNewTurn;
    }

    private void initPlayersDecksAndHands(Player player1, Player player2) {


        PrinterUtil.printDeckOrHandLoadingMessage(player1.getNickname(), "deck");
        player1.setDeck(deckService.loadDeck());
        PrinterUtil.printDeckOrHandLoadedMessage(player1.getNickname(), "deck");

        PrinterUtil.printDeckOrHandLoadingMessage(player2.getNickname(), "deck");
        player2.setDeck(deckService.loadDeck());
        PrinterUtil.printDeckOrHandLoadedMessage(player2.getNickname(), "deck");

        PrinterUtil.printDeckOrHandLoadingMessage(player1.getNickname(), "hand");
        player1.setHand(cardService.pickRandomCardsForFirstTurn(player1));
        PrinterUtil.printDeckOrHandLoadedMessage(player1.getNickname(), "hand");

        PrinterUtil.printDeckOrHandLoadingMessage(player2.getNickname(), "hand");
        player2.setHand(cardService.pickRandomCardsForFirstTurn(player2));
        PrinterUtil.printDeckOrHandLoadedMessage(player2.getNickname(), "hand");


    }

}
