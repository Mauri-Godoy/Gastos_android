package com.mg.gastos.gui.fragments;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mg.gastos.R;
import com.mg.gastos.db.CategoryRepository;
import com.mg.gastos.db.DefaultData;
import com.mg.gastos.db.MovementRepository;
import com.mg.gastos.entity.Category;
import com.mg.gastos.entity.Movement;
import com.mg.gastos.gui.MovementActivity;
import com.mg.gastos.utils.Animator;
import com.mg.gastos.utils.Validator;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CreateFragment extends Fragment {

    private View root;
    private Category category;
    private CategoryRepository categoryRepository;
    private MovementRepository movementRepository;
    private EditText amount;
    boolean negativeValue = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_create, container, false);

        categoryRepository = CategoryRepository.getInstance(requireContext());
        movementRepository = MovementRepository.getInstance(requireContext());

        setButtonAction();
        setSwitchAction();
        setToolbarTitle();

        new Thread(new Runnable() {
            @Override
            public void run() {
                setCategoriesInSelect();
            }
        }).start();

        setChangeListener();

        return root;
    }

    private void setSwitchAction() {
        AppCompatToggleButton switchCompat = root.findViewById(R.id.toggle_value);
        switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            negativeValue = isChecked;
            setCategoriesInSelect();
        });
    }

    private void setToolbarTitle() {
        MovementActivity.setToolbarTitle("Nuevo ".concat(negativeValue ? "gasto" : "ingreso"));
    }

    private void setChangeListener() {
        amount = root.findViewById(R.id.et_amount);

        amount.addTextChangedListener(new TextWatcher() {
            boolean valueDefault = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                valueDefault = s.toString().equals("0");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                if (valueDefault)
                    amount.setText(String.valueOf(str.charAt(start)));
                else if (StringUtils.isBlank(str))
                    amount.setText(R.string.default_amount);
                else {
                    int value = Integer.parseInt(str);
                    String valueStr = String.valueOf(value);
                    if (!valueStr.equals(str)) {
                        amount.setText(valueStr);
                        amount.setSelection(valueStr.length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                amount.setSelection(amount.getText().length());
            }
        });
    }

    private void create() {

        EditText description = root.findViewById(R.id.et_description);

        if (!Validator.passMinValue(amount, 0.0)) return;

        Movement movement = new Movement();
        movement.setAmount(Double.parseDouble(amount.getText().toString()));
        movement.setDescription(description.getText().toString());
        movement.setCategory(category);
        movement.setNegativeAmount(negativeValue);

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

    private void setCategoriesInSelect() {
        MaterialSpinner materialSpinner = root.findViewById(R.id.spinner);

        String typeCode = negativeValue ? DefaultData.moneyOutflow.getCode() : DefaultData.moneyIncome.getCode();

        List<Category> categoryList = categoryRepository.getByTypeCode(typeCode);

        if (!categoryList.isEmpty()) {
            int otherIndex = IntStream.range(0, categoryList.size()).filter(i -> categoryList.get(i).getCode().equals("OTHER")).findFirst().orElse(0);

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