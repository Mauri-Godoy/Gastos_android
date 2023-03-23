package com.mg.gastos.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class MovementType {

    @PrimaryKey
    @NonNull
    private String code;
    private String name;
}
