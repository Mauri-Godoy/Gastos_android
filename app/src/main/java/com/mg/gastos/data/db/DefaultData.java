package com.mg.gastos.data.db;

import com.mg.gastos.data.entity.Category;
import com.mg.gastos.data.entity.MovementType;

import java.util.Arrays;
import java.util.List;

public class DefaultData {

    public static MovementType moneyOutflow = new MovementType("MONEY_OUTFLOW", "Ingreso de dinero");
    public static MovementType moneyIncome = new MovementType("MONEY_INCOME", "Egreso de dinero");
    public static MovementType any = new MovementType("ANY", "Cualquiera");

    public static List<MovementType> movementTypeList = Arrays.asList(
            moneyOutflow,
            moneyIncome,
            any
    );

    public static List<Category> categoryList = Arrays.asList(
            new Category("FEDDING", "Alimentación", moneyOutflow),
            new Category("ACCOUNTS_AND_PAYMENTS", "Cuentas y pagos", moneyOutflow),
            new Category("TRANSPORT", "Transporte", moneyOutflow),
            new Category("HOME", "Casa", moneyOutflow),
            new Category("CLOTHES", "Vestimenta", moneyOutflow),
            new Category("HEALTH_AND_HIGIENE", "Salud e higiene", moneyOutflow),
            new Category("FUN", "Diversión", moneyOutflow),
            new Category("INGRESO_FIJO", "Fijo", moneyIncome),
            new Category("INGRESO_VARIABLE", "Variable", moneyIncome),
            new Category("OTHER", "Otros", any)
    );

}
