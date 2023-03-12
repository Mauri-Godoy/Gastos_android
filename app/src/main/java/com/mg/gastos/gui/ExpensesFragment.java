package com.mg.gastos.gui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mg.gastos.R;
import com.mg.gastos.db.DbExpense;
import com.mg.gastos.entity.Expense;

import java.util.List;

public class ExpensesFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DbExpense dbExpense = new DbExpense(requireContext());
        List<Expense> expenses = dbExpense.getAll();
        Log.e("Expenses", expenses.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_expenses, container, false);
    }
}