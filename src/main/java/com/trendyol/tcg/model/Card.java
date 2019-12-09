package com.trendyol.tcg.model;

import com.trendyol.tcg.model.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Getter
@Setter
public class Card extends BaseModel {

    private String name;

    private Integer manaCost;

    private Integer damage;

    @Override
    public String toString() {
        return name + " - Mana Cost: " + manaCost + " - Damage: " + damage;
    }
}
