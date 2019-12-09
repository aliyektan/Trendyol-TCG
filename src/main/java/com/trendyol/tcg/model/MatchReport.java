package com.trendyol.tcg.model;

import com.trendyol.tcg.model.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MatchReport extends BaseModel {

    private Player winner;
    private Player loser;
    private Match match;

}
