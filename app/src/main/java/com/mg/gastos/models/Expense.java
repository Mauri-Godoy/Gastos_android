package com.mg.gastos.models;

import java.util.Date;

import lombok.Data;

@Data
public class Expense {
    private Integer id;
    private Date date;
    private String description;
    private double amount;
}
