package com.mg.gastos.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mg.gastos.entity.Expense;

import java.util.List;

@Dao
public interface ExpenseDao {

    @Insert
    void insert(Expense expense);

    @Query("SELECT * FROM Expense ORDER BY id desc")
    List<Expense> getAll();

    @Query("SELECT SUM(amount) FROM Expense")
    double getTotal();

    @Query("SELECT 0 as 'id', '' as 'description', date, SUM(amount) as 'amount' " +
            "FROM Expense GROUP BY SUBSTR(date, 0, 8)")
    List<Expense>  getMonthAndValues();
}
