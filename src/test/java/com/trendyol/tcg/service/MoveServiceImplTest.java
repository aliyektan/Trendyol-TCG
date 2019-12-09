package com.trendyol.tcg.service;

import com.trendyol.tcg.model.Card;
import com.trendyol.tcg.model.Match;
import com.trendyol.tcg.model.Move;
import com.trendyol.tcg.model.Player;
import com.trendyol.tcg.service.base.MatchService;
import com.trendyol.tcg.service.base.MoveService;
import com.trendyol.tcg.service.base.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class MoveServiceImplTest {

    @InjectMocks
    MoveService moveService;

    @Mock
    PlayerService playerService;

    MatchService matchService;

    @Before
    public void setUp() throws Exception {
        moveService = MoveServiceImpl.getInstance();
        matchService = MatchServiceImpl.getInstance();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void it_should_make_move() {

        Card card1 = new Card("Card 1", 0, 0);
        Card card2 = new Card("Card 2", 8, 8);

        Player player1 = new Player("player 1");
        Player player2 = new Player("player 2");

        Match match = matchService.startMatch(player1, player2);

        player1.setMana(5);
        player1.setHasMana(true);

        Move move = new Move(player1, card1);

        when(playerService.canMakeMove(player1, card1)).thenReturn(true);
        when(playerService.getOpponentPlayer(player1, match)).thenReturn(player2);
        when(playerService.reduceHeal(player2, card1)).thenReturn(player2);
        when(playerService.reduceMana(player1, card1)).thenReturn(player1);

        assertEquals(move, moveService.makeMove(player1, card1));

        when(playerService.canMakeMove(player2, card2)).thenReturn(false);
        when(playerService.getOpponentPlayer(player2, match)).thenReturn(player1);
        when(playerService.reduceHeal(player1, card2)).thenReturn(player1);
        when(playerService.reduceMana(player2, card2)).thenReturn(player2);

        assertNull(moveService.makeMove(player2, card2));

    }

    @Test
    public void it_should_can_make_move_with_hand() {

        Card card1 = new Card("Card 1", 0, 0);

        List<Card> cards1 = new ArrayList<>();
        cards1.add(card1);

        Player player1 = new Player("player 1");
        player1.setMana(0);
        player1.setHand(cards1);

        assertTrue(moveService.canMakeMoveWithHand(player1));

        Card card2 = new Card("Card 2", 8, 8);

        List<Card> cards2 = new ArrayList<>();
        cards2.add(card2);

        Player player2 = new Player("player 2");
        player2.setMana(0);
        player2.setHand(cards2);

        assertFalse(moveService.canMakeMoveWithHand(player2));

    }

    @Test
    public void it_should_get_instance() {
        assertNotNull(MoveServiceImpl.getInstance());
        assertEquals(moveService, MoveServiceImpl.getInstance());
    }
}