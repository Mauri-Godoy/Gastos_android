package com.mg.gastos.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
 @AllArgsConstructor
@Entity
public class Category implements Serializable {

    @PrimaryKey @NonNull
    private String code;
    private String name;
    public MovementType movementType;
}
