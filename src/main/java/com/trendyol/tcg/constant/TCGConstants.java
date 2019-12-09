package com.trendyol.tcg.constant;

import com.trendyol.tcg.util.JSONUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@Data
public class TCGConstants {

    private static TCGConstants tcgConstants;
    private Integer starterHeal;
    private Integer starterMana;
    private Integer starterManaSlot;
    private Integer maxManaSlots;
    private Integer deckSize;
    private Integer randomCardCountFirstTurn;
    private List<Integer> manaCosts;
    private List<Integer> cardCountsByManaCost;
    private String opponentTurnKey;

    public static TCGConstants getInstance() {
        if (tcgConstants == null) {
            try {
                tcgConstants = JSONUtil.readTCGConstants();
            } catch (URISyntaxException | IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return tcgConstants;
    }
}
