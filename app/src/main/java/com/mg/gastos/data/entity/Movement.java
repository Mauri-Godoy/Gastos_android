package com.mg.gastos.data.entity;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
@Entity
public class Movement implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private LocalDateTime date;
    private String description;
    private Double amount;
    private boolean negativeAmount;

    @Embedded
    public Category category;
}