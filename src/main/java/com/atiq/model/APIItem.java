package com.atiq.model;

public class APIItem {
    private int itemId;
    private String name;
    private long count;
    private String location;

    public APIItem(int itemId, String name, long count, String location) {
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.location = location;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
