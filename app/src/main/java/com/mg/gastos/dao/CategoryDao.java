package com.mg.gastos.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mg.gastos.entity.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insert(List<Category> categoryList);

    @Query("SELECT * FROM Category WHERE movementType = :typeCode")
    List<Category> getByTypeCode(String typeCode);
}
