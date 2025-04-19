package com.atiq.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class APIItem {
    private int itemId;
    private String name;
    private long count;
    private String location;
}
