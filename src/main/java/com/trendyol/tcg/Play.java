package com.trendyol.tcg;

import com.trendyol.tcg.constant.TCGConstants;
import com.trendyol.tcg.model.Card;
import com.trendyol.tcg.model.Match;
import com.trendyol.tcg.model.Player;
import com.trendyol.tcg.service.MatchServiceImpl;
import com.trendyol.tcg.service.MoveServiceImpl;
import com.trendyol.tcg.service.PlayerServiceImpl;
import com.trendyol.tcg.service.base.MatchService;
import com.trendyol.tcg.service.base.MoveService;
import com.trendyol.tcg.service.base.PlayerService;
import com.trendyol.tcg.util.PrinterUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class Play {

    public static void main(String[] args) {
        MatchService matchService = MatchServiceImpl.getInstance();
        PlayerService playerService = PlayerServiceImpl.getInstance();
        MoveService moveService = MoveServiceImpl.getInstance();

        TCGConstants tcgConstants = TCGConstants.getInstance();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Player 1's nickname : ");
        Player player1 = new Player(scanner.nextLine());

        System.out.print("Enter Player 2's nickname : ");
        Player player2 = new Player(scanner.nextLine());

        Match startedMatch = matchService.startMatch(player1, player2);

        while (player1.isHasHeal() || player2.isHasHeal())
            try {

                PrinterUtil.printScoreBoard(startedMatch);

                Player activePlayer = playerService.getActivePlayer(startedMatch);
                PrinterUtil.printActivePlayerHand(activePlayer);

                PrinterUtil.printMakeMoveMessage();

                String value = scanner.nextLine();

                if (value.equals(tcgConstants.getOpponentTurnKey())) {
                    matchService.changeTurnFlag();
                    continue;
                }

                Card selectedCard = activePlayer.getHand().get(Integer.parseInt(value));
                if (moveService.canMakeMoveWithHand(activePlayer))
                    moveService.makeMove(activePlayer, selectedCard);
                else
                    matchService.changeTurnFlag();

            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
    }

}
