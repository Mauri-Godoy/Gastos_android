package com.mg.gastos.gui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mg.gastos.R;
import com.mg.gastos.db.DbExpense;
import com.mg.gastos.entity.Expense;
import com.mg.gastos.utils.Animator;

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

        DbExpense dbExpense = new DbExpense(requireContext());

        Expense expense = new Expense();
        expense.setAmount(Double.parseDouble(amount.getText().toString()));
        expense.setDescription(description.getText().toString());

        dbExpense.create(expense);

        description.setText("");
        amount.setText("");
    }

    private void setButtonAction(){
        Button button = root.findViewById(R.id.btn_upload);
        button.setOnClickListener(v -> {
            Animator.shake(v);
            create();
        });
    }


}