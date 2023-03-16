package com.mg.gastos.gui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mg.gastos.R;
import com.mg.gastos.db.DbExpense;
import com.mg.gastos.entity.Expense;
import com.mg.gastos.utils.Animator;
import com.mg.gastos.utils.Validator;

public class CreateFragment extends Fragment {

    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_create, container, false);

        setButtonAction();

        return root;
    }


    private void create() {

        EditText description = root.findViewById(R.id.et_description);
        EditText amount = root.findViewById(R.id.et_amount);

       if (!Validator.passRequired(amount))
           return;

        if (!Validator.passMinValue(amount, 0.0))
            return;

        DbExpense dbExpense = DbExpense.getInstance(requireContext());

        Expense expense = new Expense();
        expense.setAmount(Double.parseDouble(amount.getText().toString()));
        expense.setDescription(description.getText().toString());

        dbExpense.create(expense);

        Toast.makeText(requireContext(), "Cargado con exito!", Toast.LENGTH_SHORT).show();

        description.setText("");
        amount.setText("");
    }

    private void setButtonAction(){
        Button button = root.findViewById(R.id.btn_upload);
        button.setOnClickListener(v -> {
            Animator.alpha(v);
            create();
        });
    }


}