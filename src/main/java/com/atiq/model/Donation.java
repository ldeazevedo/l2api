package com.atiq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donation {

    private int id;
    private String login;
    private String packageName;
    private String status;
    private String date;
    private String paymentMethod;
    private String ref;
}