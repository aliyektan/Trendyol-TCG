package com.trendyol.tcg.util;

import com.trendyol.tcg.constant.TCGConstants;
import com.trendyol.tcg.model.Card;
import com.trendyol.tcg.model.Match;
import com.trendyol.tcg.model.MatchReport;
import com.trendyol.tcg.model.Player;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class PrinterUtil {

    public static void printRefillManaMessage(String nickname) {
        System.out.println("[INFO] " + nickname + "'s mana refilled.");
    }

    public static void printIncreaseManaSlotsMessage(String nickname) {
        System.out.println("[INFO] " + nickname + "'s mana slots checked .");
    }

    public static void printReduceManaMessage(String nickname, Card card) {
        System.out.println("[INFO] " + nickname + " used Card : " + card.getName() + " Mana used : " + card.getManaCost());
    }

    public static void printReduceHealMessage(String nickname, Integer heal, Card card) {
        System.out.println("[INFO] " + nickname + "'s heal reduced to " + heal + ". Damage received : " + card.getDamage());
    }

    public static void printCanMakeMoveMessage(String nickname, Card card) {
        System.out.println("[INFO] " + nickname + " doesn't have enough mana for move card " + card.getName());
    }

    public static void printDrawCardMessage(Player player) {
        System.out.println("[INFO] Drawing a new card for : " + player.getNickname());
    }

    public static void printNewTurnInfo(Player player) {
        System.out.println();
        System.out.println("/////////////////////////////////////////////////////////////////////////////////");
        System.out.println();
        System.out.println("[INFO] " + player.getNickname() + "'s Turn !");
    }

    public static void printScoreBoard(Match currentMatch) {

        System.out.println();
        System.out.println("+++++++++++++++++ SCORE BOARD ++++++++++++++++++");
        System.out.println("+  " + currentMatch.getPlayer1().getNickname() + "'s Heal : " + currentMatch.getPlayer1().getHeal());
        System.out.println("+  " + currentMatch.getPlayer1().getNickname() + "'s Mana : " + currentMatch.getPlayer1().getMana());
        System.out.println("------------------------------------------------");
        System.out.println("+  " + currentMatch.getPlayer2().getNickname() + "'s Heal : " + currentMatch.getPlayer2().getHeal());
        System.out.println("+  " + currentMatch.getPlayer2().getNickname() + "'s Mana : " + currentMatch.getPlayer2().getMana());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();

    }

    public static void printMatchReport(MatchReport matchReport, Match currentMatch) {

        System.out.println();
        System.out.println("++++++++++++++++ MATCH REPORT ++++++++++++++++++");
        System.out.println("+  " + currentMatch.getPlayer1().getNickname() + "'s Heal : " + currentMatch.getPlayer1().getHeal());
        System.out.println("+  " + currentMatch.getPlayer2().getNickname() + "'s Heal : " + currentMatch.getPlayer2().getHeal());
        System.out.println("+  TOTAL MOVES OF THE MATCH : " + matchReport.getMatch().getMoves().size());
        System.out.println("+  WINNER : " + matchReport.getWinner().getNickname());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();

    }

    public static void printActivePlayerHand(Player player) {

        List<Card> hand = player.getHand();

        System.out.println("----- " + player.getNickname() + "'s Hand -----");
        IntStream.range(0, hand.size())
                .forEach(idx -> System.out.println(idx + ". " + hand.get(idx).toString()));


    }

    public static void printMakeMoveMessage() {
        System.out.println();
        System.out.println("[INFO] End turn, enter: " + TCGConstants.getInstance().getOpponentTurnKey());
        System.out.print("Select a card for move: ");
        System.out.println();
    }

    public static void printFinishMatchMessage() {
        System.out.println();
        System.out.println("[INFO] The match has finished !");
    }

    public static void printStartMatchSec1Message() {
        System.out.println();
        System.out.println("[INFO] Match starting ...");
        System.out.println();
    }

    public static void printStartMatchSec2Message() {
        System.out.println();
        System.out.println("[INFO] Match started. Good Luck !");
        System.out.println();
    }

    public static void printDeckOrHandLoadingMessage(String nickname, String which) {
        System.out.println("[INFO] " + nickname + "'s " + which + " loading ...");
    }

    public static void printDeckOrHandLoadedMessage(String nickname, String which) {
        System.out.println("[INFO] " + nickname + "'s " + which + " loaded.");
    }

}
