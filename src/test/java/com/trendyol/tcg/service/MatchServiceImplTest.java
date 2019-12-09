package com.trendyol.tcg.service;

import com.trendyol.tcg.model.Match;
import com.trendyol.tcg.model.Player;
import com.trendyol.tcg.service.base.CardService;
import com.trendyol.tcg.service.base.MatchService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class MatchServiceImplTest {

    @InjectMocks
    MatchService matchService;

    @Mock
    CardService cardService;

    @Before
    public void setUp() throws Exception {
        matchService = MatchServiceImpl.getInstance();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void it_should_start_match() {

        Player player = new Player("player 1");
        Player player2 = new Player("player 2");

        assertNotNull(matchService.startMatch(player, player2));

        Match match = matchService.startMatch(player, player2);
        assertEquals(match.getPlayer1(), player);
        assertEquals(match.getPlayer2(), player2);
    }

    @Test
    public void it_should_get_current_match() {

        Player player = new Player("player 1");
        Player player2 = new Player("player 2");

        Match match = matchService.startMatch(player, player2);

        assertNotNull(matchService.getCurrentMatch());
        assertEquals(match, matchService.getCurrentMatch());

    }

    @Test
    public void it_should_change_turn_flag() {

        Player player = new Player("player 1");
        Player player2 = new Player("player 2");

        Match match = matchService.startMatch(player, player2);

        assertEquals(player2, matchService.checkTurnAndReturnNewPlayer());

        matchService.changeTurnFlag();

        assertEquals(player, matchService.checkTurnAndReturnNewPlayer());

        matchService.changeTurnFlag();

        assertEquals(player2, matchService.checkTurnAndReturnNewPlayer());

        when(cardService.pickRandomCardForTurn(player)).thenReturn(null);

        matchService.changeTurnFlag();

        assertEquals(player, matchService.checkTurnAndReturnNewPlayer());

        player.setHasHeal(false);
        player.setHeal(0);

//      Match will end

    }

    @Test
    public void it_should_check_turn_and_return_new_player() {

        Player player = new Player("player 1");
        Player player2 = new Player("player 2");

        Match match = matchService.startMatch(player, player2);

        assertNotNull(matchService.checkTurnAndReturnNewPlayer());
        assertEquals(player, matchService.checkTurnAndReturnNewPlayer());

    }

    @Test
    public void it_should_get_instance() {
        assertNotNull(MatchServiceImpl.getInstance());
        assertEquals(matchService, MatchServiceImpl.getInstance());
    }
}