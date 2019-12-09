package com.trendyol.tcg.model;

import com.trendyol.tcg.model.base.BaseModel;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Move extends BaseModel {

    @NonNull
    private Player player;
    private Card card;

}
