package com.mg.gastos.gui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mg.gastos.R;
import com.mg.gastos.data.repository.CategoryRepository;
import com.mg.gastos.data.db.DefaultData;
import com.mg.gastos.data.repository.MovementRepository;
import com.mg.gastos.data.entity.Category;
import com.mg.gastos.data.entity.Movement;
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
    private EditText amount, description;
    private final Movement movement;
    private RadioGroup radioGroup;
    private MaterialSpinner materialSpinner;
    private RadioButton rOutflow, rIncome;
    private ImageButton buttonDelete;

    public CreateFragment(Movement movement) {
        this.movement = movement;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_create, container, false);

        categoryRepository = CategoryRepository.getInstance(requireContext());
        movementRepository = MovementRepository.getInstance(requireContext());
        amount = root.findViewById(R.id.et_amount);
        radioGroup = root.findViewById(R.id.rg_movement);
        description = root.findViewById(R.id.et_description);
        materialSpinner = root.findViewById(R.id.spinner);
        rOutflow = root.findViewById(R.id.r_outflow);
        rIncome = root.findViewById(R.id.r_income);
        buttonDelete = root.findViewById(R.id.btn_delete);

        setRadioGroupAction();
        setChangeListener();
        setCategoriesInSelect();
        setData();
        setButtonSaveAction();
        return root;
    }

    private void setRadioGroupAction() {

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            movement.setNegativeAmount(checkedId == R.id.r_outflow);
            setCategoriesInSelect();
        });
    }

    private void setChangeListener() {

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

    private void createAndUpdate() {

        if (!Validator.passMinValue(amount, 0.0)) return;

        movement.setAmount(Double.parseDouble(amount.getText().toString()));
        movement.setDescription(description.getText().toString());
        movement.setCategory(category);

        if (movement.getId() == null)
            movementRepository.create(movement);
        else {
            movementRepository.update(movement);
            requireActivity().onBackPressed();
        }

        Toast.makeText(requireContext(), "Guardado con exito!", Toast.LENGTH_SHORT).show();

        cleanData();
    }

    private void cleanData() {
        description.setText("");
        amount.setText(R.string.default_amount);
        rIncome.setEnabled(true);
        rOutflow.setEnabled(true);
    }

    private void setData() {
        if (movement.getId() != null) {
            setMovementDataInView();
            setCategory();
        }
    }

    private void setButtonSaveAction() {
        Button buttonUpload = root.findViewById(R.id.btn_save);
        buttonUpload.setOnClickListener(v -> {
            Animator.alpha(v);
            createAndUpdate();
        });
    }

    private void setMovementDataInView() {
        Animator.show(buttonDelete);

        if (movement.isNegativeAmount())
            rOutflow.setChecked(true);
        else
            rIncome.setChecked(true);

        rIncome.setEnabled(false);
        rOutflow.setEnabled(false);

        Double amountValue = movement.getAmount();
        String amountStr = String.valueOf(amountValue.intValue());
        amount.setText(amountStr);
        description.setText(movement.getDescription());
    }

    private void setCategory() {

        List<String> strings = materialSpinner.getItems();

        int otherIndex = IntStream.range(0, strings.size())
                .filter(i -> strings.get(i).equals(movement.getCategory().getName()))
                .findFirst().orElse(0);

        materialSpinner.setSelectedIndex(otherIndex);
    }

    private void setCategoriesInSelect() {
        String typeCode = movement.isNegativeAmount() ? DefaultData.moneyOutflow.getCode() : DefaultData.moneyIncome.getCode();

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