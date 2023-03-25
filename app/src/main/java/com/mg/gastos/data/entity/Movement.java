package com.mg.gastos.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Movement implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @NonNull
    private LocalDateTime date;
    private LocalDateTime dateModified;

    @NonNull
    private Double amount;

    private String description;
    private boolean negativeAmount = true;

    @Embedded
    public Category category;
}
