package com.mg.gastos.gui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mg.gastos.R;
import com.mg.gastos.db.Database;
import com.mg.gastos.db.ExpenseRepository;
import com.mg.gastos.entity.Category;
import com.mg.gastos.entity.Expense;
import com.mg.gastos.utils.Animator;
import com.mg.gastos.utils.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class CreateFragment extends Fragment {

    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_create, container, false);

        setButtonAction();

        createSelectCategory();

        return root;
    }


    private void create() {

        EditText description = root.findViewById(R.id.et_description);
        EditText amount = root.findViewById(R.id.et_amount);

        if (!Validator.passRequired(amount))
            return;

        if (!Validator.passMinValue(amount, 0.0))
            return;

        ExpenseRepository expenseRepository = ExpenseRepository.getInstance(requireContext());

        Expense expense = new Expense();
        expense.setAmount(Double.parseDouble(amount.getText().toString()));
        expense.setDescription(description.getText().toString());

        expenseRepository.insert(expense);

        Toast.makeText(requireContext(), "Cargado con exito!", Toast.LENGTH_SHORT).show();

        description.setText("");
        amount.setText("");
    }

    private void setButtonAction() {
        Button button = root.findViewById(R.id.btn_upload);
        button.setOnClickListener(v -> {
            Animator.alpha(v);
            create();
        });
    }

    private void createSelectCategory() {
        MaterialSpinner materialSpinner = root.findViewById(R.id.spinner);

        List<Category> categoryList = Database.getInstance(requireContext()).categoryDao().getAll();

        List<String> strings = categoryList.stream().map(Category::getName).collect(Collectors.toList());

        materialSpinner.setItems(strings);
        materialSpinner.setSelectedIndex(strings.size() - 1);
        materialSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, categoryList.get(position).getCode() + item, Snackbar.LENGTH_LONG).show();
            }
        });
    }

}