package com.atiq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.l2j.gameserver.model.actor.Player;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIPlayer {

    private int id;
    private String name;
    private int level;
    private int cp;
    private int hp;
    private int mp;
    private int maxCP = 5000;
    private int maxHP = 10000;
    private int maxMP = 3000;
    private int karma;
    private int pk;
    private int pvp;
    private int mapX, mapY;
    private int x, y, z;
    private List<APIItem> items;

    public APIPlayer(int id, String name, int level, int cp, int hp, int mp, int karma, int pk, int pvp, int x, int y, int z, List<APIItem> items) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.cp = cp;
        this.hp = hp;
        this.mp = mp;
        this.karma = karma;
        this.pk = pk;
        this.pvp = pvp;
        this.mapX = getMapX(x);
        this.mapY = getMapY(y);
        this.x = x;
        this.y = y;
        this.z = z;
        this.items = items;
    }

    public APIPlayer(Player player) {
        this.id = player.getObjectId();
        this.name = player.getName();
        this.level = player.getStatus().getLevel();
        this.cp = (int) player.getStatus().getCp();
        this.hp = (int) player.getStatus().getHp();
        this.mp = (int) player.getStatus().getMp();
        this.maxCP = player.getStatus().getMaxCp();
        this.maxHP = player.getStatus().getMaxHp();
        this.maxMP = player.getStatus().getMaxMp();
        this.karma = player.getKarma();
        this.pk = player.getPkKills();
        this.pvp = player.getPvpKills();
        this.mapX = getMapX(player.getX());
        this.mapY = getMapY(player.getY());
        this.x = player.getX();
        this.y = player.getY();
        this.z = player.getZ();
        this.items = player.getInventory().getItems().stream().map(ii -> new APIItem(ii.getItemId(), ii.getItemName(), ii.getCount(), ii.getLocation().name())).collect(Collectors.toList());
    }

    private int getMapX(int x) {
        return 150 + (x + 107823) / 200;
    }

    private int getMapY(int y) {
        return 2580 + (y - 255420) / 200;
    }
}