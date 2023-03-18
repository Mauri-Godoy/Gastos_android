package com.mg.gastos.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.mg.gastos.entity.MovementType;

import java.util.List;

@Dao
public interface MovementTypeDao {

    @Insert
    void insert(List<MovementType> movementTypeList);
}
