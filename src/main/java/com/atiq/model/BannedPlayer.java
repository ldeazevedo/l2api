package com.atiq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BannedPlayer {
    private String charName;
    private String punishedBy;
    private String reason;
    private String type;
    private String affect;
}