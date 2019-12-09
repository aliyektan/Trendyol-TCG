package com.trendyol.tcg.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;

public class MatchReportTest {

    private MatchReport object;

    @Before
    public void setUp() throws Exception {

        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        Card card1 = new Card("card1", 1, 1);
        Card card2 = new Card("card2", 1, 1);

        Move move1 = new Move(player1, card1);
        Move move2 = new Move(player2, card2);

        Stack<Move> moves = new Stack<>();
        moves.push(move1);
        moves.push(move2);

        Match match = new Match();
        match.setPlayer1(player1);
        match.setPlayer2(player1);
        match.setMoves(moves);

        object = new MatchReport();
        object.setWinner(player1);
        object.setLoser(player2);
        object.setMatch(match);

    }

    @Test
    public void it_should_to_string() {
        assertNotNull(object.toString());
    }

    @Test
    public void it_should_equals() {
        MatchReport objectToCompare = createObject();
        MatchReport nullMatchReport = null;
        Object nullObject = null;
        assertTrue(objectToCompare.equals(object) && object.equals(objectToCompare));
        assertNotEquals(object, nullMatchReport);
        assertNotEquals(object, nullObject);
    }

    @Test
    public void it_should_hash_code() {
        assertNotNull(object.hashCode());
    }

    private MatchReport createObject() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        Card card1 = new Card("card1", 1, 1);
        Card card2 = new Card("card2", 1, 1);

        Move move1 = new Move(player1, card1);
        Move move2 = new Move(player2, card2);

        Stack<Move> moves = new Stack<>();
        moves.push(move1);
        moves.push(move2);

        Match match = new Match();
        match.setPlayer1(player1);
        match.setPlayer2(player1);
        match.setMoves(moves);

        MatchReport object = new MatchReport();
        object.setWinner(player1);
        object.setLoser(player2);
        object.setMatch(match);

        return object;
    }
}