package com.l2timeus.model;

public class BannedPlayer {
    private String charName;
    private String punishedBy;
    private String reason;
    private String type;
    private String affect;

    public BannedPlayer() {
    }

    public BannedPlayer(String charName, String punishedBy, String reason, String type, String affect) {
        this.charName = charName;
        this.punishedBy = punishedBy;
        this.reason = reason;
        this.type = type;
        this.affect = affect;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getPunishedBy() {
        return punishedBy;
    }

    public void setPunishedBy(String punishedBy) {
        this.punishedBy = punishedBy;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAffect() {
        return affect;
    }

    public void setAffect(String affect) {
        this.affect = affect;
    }
}
