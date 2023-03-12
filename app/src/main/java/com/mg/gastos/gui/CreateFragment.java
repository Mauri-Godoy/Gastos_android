package com.mg.gastos.gui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mg.gastos.R;
import com.mg.gastos.db.DbExpense;
import com.mg.gastos.entity.Expense;

public class CreateFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create, container, false);

        create();

        return root;
    }


    private void create() {
        DbExpense dbExpense = new DbExpense(requireContext());

        Expense expense = new Expense();
        expense.setAmount(10);
        expense.setDescription("Hola");

        dbExpense.create(expense);
    }
}