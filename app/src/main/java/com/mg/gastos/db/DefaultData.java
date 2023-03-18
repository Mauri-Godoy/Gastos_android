package com.mg.gastos.db;

import com.mg.gastos.entity.Category;
import com.mg.gastos.entity.MovementType;

import java.util.Arrays;
import java.util.List;

public class DefaultData {

    static MovementType moneyOutflow = new MovementType("MONEY_OUTFLOW", "Ingreso de dinero");
    static MovementType moneyIncome = new MovementType("MONEY_INCOME", "Egreso de dinero");

    public static List<MovementType> movementTypeList = Arrays.asList(
            moneyOutflow,
            moneyIncome
    );

    public static List<Category> categoryList = Arrays.asList(
            new Category("FEDDING", "Alimentación", moneyOutflow),
            new Category("ACCOUNTS_AND_PAYMENTS", "Cuentas y pagos", moneyOutflow),
            new Category("TRANSPORT", "Transporte", moneyOutflow),
            new Category("HOME", "Casa", moneyOutflow),
            new Category("CLOTHES", "Vestimenta", moneyOutflow),
            new Category("HEALTH_AND_HIGIENE", "Salud e higiene", moneyOutflow),
            new Category("FUN", "Diversión", moneyOutflow),
            new Category("OTHER", "Otros", moneyOutflow),
            new Category("INGRESO_FIJO", "Fijo", moneyIncome),
            new Category("INGRESO_VARIABLE", "Variable", moneyIncome)
    );

}
