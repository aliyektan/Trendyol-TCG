package com.trendyol.tcg.model;

import com.trendyol.tcg.model.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Data
public class Deck extends BaseModel {

    private List<Card> cards;

}
