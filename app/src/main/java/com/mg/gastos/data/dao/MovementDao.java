package com.mg.gastos.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mg.gastos.data.entity.Movement;

import java.util.List;

@Dao
public interface MovementDao {

    @Insert
    void insert(Movement movement);

    @Query("SELECT * FROM Movement ORDER BY date desc")
    List<Movement> getAll();

    @Query("SELECT * FROM Movement WHERE SUBSTR(date, 0, 11) BETWEEN :dateFrom AND :dateTo ORDER BY date desc")
    List<Movement> getByDate(String dateFrom, String dateTo);

    @Query("SELECT 0 as 'id', '' as 'description', date, SUM(amount) as 'amount', '' as 'negativeAmount' " +
            "FROM Movement WHERE negativeAmount = :negativeAmount GROUP BY SUBSTR(date, 0, 8) " +
            "ORDER BY date DESC LIMIT(8)")
    List<Movement>  getMonthAndValues(boolean negativeAmount);

}
