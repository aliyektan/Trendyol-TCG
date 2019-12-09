package com.trendyol.tcg.model;

import com.trendyol.tcg.constant.TCGConstants;
import com.trendyol.tcg.model.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(of = "nickname", callSuper = false)
@Data
public class Player extends BaseModel {

    private String nickname;

    private Deck deck;

    private List<Card> hand;

    private Integer heal;

    private Integer mana;

    private Integer manaSlots;

    private boolean hasHeal;

    private boolean hasMana;

    private boolean turn;

    public Player(String nickname) {
        this.nickname = nickname;
        this.heal = TCGConstants.getInstance().getStarterHeal();
        this.mana = TCGConstants.getInstance().getStarterMana();
        this.manaSlots = TCGConstants.getInstance().getStarterManaSlot();
        this.hasHeal = true;
        this.turn = false;
        this.hasMana = false;
    }

}
