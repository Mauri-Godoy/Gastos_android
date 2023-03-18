package com.mg.gastos.db;

import com.mg.gastos.entity.Category;

import java.util.Arrays;
import java.util.List;

public class DefaultData {

    public static List<Category> categoryList = Arrays.asList(
            new Category("FEDDING", "Alimentación"),
            new Category("ACCOUNTS_AND_PAYMENTS", "Cuentas y pagos"),
            new Category("TRANSPORT", "Transporte"),
            new Category("HOME", "Casa"),
            new Category("CLOTHES", "Vestimenta"),
            new Category("HEALTH_AND_HIGIENE", "Salud e higiene"),
            new Category("FUN", "Diversión"),
            new Category("OTHER", "Otros")
    );

}
