package com.trendyol.tcg.model;

import com.trendyol.tcg.model.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Stack;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class Match extends BaseModel {

    private Player player1;
    private Player player2;
    private Stack<Move> moves;

    public Match() {
        this.moves = new Stack<>();
    }

    public void addMove(Move move) {
        this.moves.push(move);
    }

}
