package com.mg.gastos.entity;

import lombok.Data;

@Data
public class Expense {
    private Integer id;
    private String date;
    private String description;
    private double amount;
}
