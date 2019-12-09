package com.trendyol.tcg.service;

import com.trendyol.tcg.model.Card;
import com.trendyol.tcg.model.Match;
import com.trendyol.tcg.model.Player;
import com.trendyol.tcg.service.base.MatchService;
import com.trendyol.tcg.service.base.PlayerService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerServiceImplTest {

    PlayerService playerService;

    MatchService matchService;

    @Before
    public void setUp() throws Exception {
        playerService = PlayerServiceImpl.getInstance();
        matchService = MatchServiceImpl.getInstance();
    }

    @Test
    public void it_should_get_active_player() {

        Player player = new Player("player 1");
        Player player2 = new Player("player 2");

        Match match = matchService.startMatch(player, player2);

        assertNotNull(playerService.getActivePlayer(match));
        assertEquals(player, playerService.getActivePlayer(match));

    }

    @Test
    public void it_should_get_opponent_player() {

        Player player = new Player("player 1");
        Player player2 = new Player("player 2");

        Match match = new Match();
        match.setPlayer1(player);
        match.setPlayer2(player2);

        assertNotNull(playerService.getOpponentPlayer(player, match));
        assertEquals(player2, playerService.getOpponentPlayer(player, match));
        assertEquals(player, playerService.getOpponentPlayer(player2, match));

    }

    @Test
    public void it_should_check_players_heal() {

        Player player = new Player("player 1");
        player.setHeal(0);
        player.setHasHeal(false);

        Player player2 = new Player("player 2");

        Match match = new Match();
        match.setPlayer1(player);
        match.setPlayer2(player2);

        assertNotNull(playerService.checkPlayersHeal(match));
        assertEquals(1, (int) playerService.checkPlayersHeal(match));

    }

    @Test
    public void it_should_get_instance() {
        assertNotNull(PlayerServiceImpl.getInstance());
        assertEquals(playerService, PlayerServiceImpl.getInstance());
    }

    @Test
    public void it_should_refill_mana() {
        // Given
        Player player = new Player("player 1");
        player = playerService.increaseManaSlots(player);
        player = playerService.refillMana(player);

        assertEquals(1, (int) player.getMana());
    }

    @Test
    public void it_should_increase_mana_slots() {
        // Given
        Player player = new Player("player 1");
        player = playerService.increaseManaSlots(player);

        assertEquals(1, (int) player.getManaSlots());
    }

    @Test
    public void it_should_reduce_heal() {
        // Given
        Player player = new Player("player 1");
        Card card = new Card("Card 1", 1, 1);
        player = playerService.reduceHeal(player, card);

        assertEquals(29, (int) player.getHeal());
    }

    @Test
    public void it_should_reduce_mana() {
        // Given
        Player player = new Player("player 1");
        Card card = new Card("Card 1", 1, 1);
        player = playerService.increaseManaSlots(player);
        player = playerService.refillMana(player);

        assertEquals(1, (int) player.getMana());

        player = playerService.reduceMana(player, card);

        assertEquals(0, (int) player.getMana());
    }

    @Test
    public void it_should_can_make_move() {
        // Given
        Player player = new Player("player 1");
        Card card = new Card("Card 1", 1, 1);

        assertFalse(playerService.canMakeMove(player, card));

        player = playerService.increaseManaSlots(player);
        player = playerService.refillMana(player);

        assertTrue(playerService.canMakeMove(player, card));

    }

}