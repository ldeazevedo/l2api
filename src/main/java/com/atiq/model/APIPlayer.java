package com.atiq.model;

import net.sf.l2j.gameserver.model.actor.Player;

import java.util.List;
import java.util.stream.Collectors;

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

    public APIPlayer() {
    }

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
        this.level = player.getLevel();
        this.cp = (int) player.getCurrentCp();
        this.hp = (int) player.getCurrentHp();
        this.mp = (int) player.getCurrentMp();
        this.maxCP = player.getMaxCp();
        this.maxHP = player.getMaxHp();
        this.maxMP = player.getMaxMp();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCp() {
        return cp;
    }

    public APIPlayer setCp(int cp) {
        this.cp = cp;
        return this;
    }

    public int getHp() {
        return hp;
    }

    public APIPlayer setHp(int hp) {
        this.hp = hp;
        return this;
    }

    public int getMp() {
        return mp;
    }

    public APIPlayer setMp(int mp) {
        this.mp = mp;
        return this;
    }

    public int getMaxCP() {
        return maxCP;
    }

    public APIPlayer setMaxCP(int maxCP) {
        this.maxCP = maxCP;
        return this;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public APIPlayer setMaxHP(int maxHP) {
        this.maxHP = maxHP;
        return this;
    }

    public int getMaxMP() {
        return maxMP;
    }

    public APIPlayer setMaxMP(int maxMP) {
        this.maxMP = maxMP;
        return this;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public int getPvp() {
        return pvp;
    }

    public void setPvp(int pvp) {
        this.pvp = pvp;
    }

    public int getMapX() {
        return mapX;
    }

    public void setMapX(int mapX) {
        this.mapX = mapX;
    }

    public int getMapY() {
        return mapY;
    }

    public void setMapY(int mapY) {
        this.mapY = mapY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public List<APIItem> getItems() {
        return items;
    }

    public void setItems(List<APIItem> items) {
        this.items = items;
    }
}