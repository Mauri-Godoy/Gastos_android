package com.mg.gastos.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mg.gastos.entity.Movement;

import java.util.List;

@Dao
public interface MovementDao {

    @Insert
    void insert(Movement movement);

    @Query("SELECT * FROM Movement ORDER BY id desc")
    List<Movement> getAll();

    @Query("SELECT 0 as 'id', '' as 'description', date, SUM(amount) as 'amount', '' as 'negativeAmount' " +
            "FROM Movement WHERE negativeAmount = :negativeAmount GROUP BY SUBSTR(date, 0, 8)")
    List<Movement>  getMonthAndValues(boolean negativeAmount);
}
