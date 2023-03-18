package com.mg.gastos.gui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mg.gastos.R;
import com.mg.gastos.db.CategoryRepository;
import com.mg.gastos.db.MovementRepository;
import com.mg.gastos.entity.Category;
import com.mg.gastos.entity.Movement;
import com.mg.gastos.utils.Animator;
import com.mg.gastos.utils.Validator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CreateFragment extends Fragment {

    private View root;
    private Category category;
    private CategoryRepository categoryRepository;
    private MovementRepository movementRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_create, container, false);

        categoryRepository = CategoryRepository.getInstance(requireContext());
        movementRepository = MovementRepository.getInstance(requireContext());

        setButtonAction();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        createSelectCategory();
                    }
                }
        ).start();

        return root;
    }


    private void create() {

        EditText description = root.findViewById(R.id.et_description);
        EditText amount = root.findViewById(R.id.et_amount);

        if (!Validator.passRequired(amount))
            return;

        if (!Validator.passMinValue(amount, 0.0))
            return;

        Movement movement = new Movement();
        movement.setAmount(Double.parseDouble(amount.getText().toString()));
        movement.setDescription(description.getText().toString());
        movement.setCategory(category);

        movementRepository.insert(movement);

        Toast.makeText(requireContext(), "Cargado con exito!", Toast.LENGTH_SHORT).show();

        description.setText("");
        amount.setText(R.string.default_amount);
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

        List<Category> categoryList = categoryRepository.getAll();

        if (!categoryList.isEmpty()) {
            int otherIndex = IntStream.range(0, categoryList.size())
                    .filter(i -> categoryList.get(i).getCode().equals("OTHER"))
                    .findFirst().orElse(0);

            category = categoryList.get(otherIndex);

            List<String> strings = categoryList.stream().map(Category::getName).collect(Collectors.toList());

            materialSpinner.setItems(strings);
            materialSpinner.setSelectedIndex(otherIndex);

            materialSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                @Override
                public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                    category = categoryList.get(position);
                }
            });
        }
    }

}