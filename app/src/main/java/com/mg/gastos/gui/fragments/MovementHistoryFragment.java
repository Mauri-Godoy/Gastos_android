package com.mg.gastos.gui.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mg.gastos.R;
import com.mg.gastos.data.repository.MovementRepository;
import com.mg.gastos.data.entity.Movement;
import com.mg.gastos.gui.adapters.MovementListAdapter;
import com.mg.gastos.utils.Animator;
import com.mg.gastos.utils.MovementUtils;

import java.util.Calendar;
import java.util.List;

import lombok.Setter;

public class MovementHistoryFragment extends Fragment {

    private View root;

    @Setter
    private String dateFromParam, dateToParam;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_movement_history, container, false);

        setDatePickers();
        setMovementList();
        setActionToBtnSearch();
        return root;
    }

    private void setActionToBtnSearch() {
        ImageButton button = root.findViewById(R.id.btn_search);
        button.setOnClickListener(v -> {
            Animator.alpha(v);
            setMovementList();
        });
    }

    private void setMovementList() {
        RecyclerView recyclerView = root.findViewById(R.id.rv_movementList);
        List<Movement> movementList = MovementRepository.getInstance(requireContext()).getByDate(dateFromParam, dateToParam);
        TextView textView = root.findViewById(R.id.tv_movementListEmpty);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new MovementListAdapter(movementList));

        if (movementList.isEmpty())
            Animator.show(textView);
        else
            textView.setVisibility(View.GONE);

        setTotal(MovementUtils.getTotal(movementList));
    }

    private void setTotal(Double total) {
        TextView textView = root.findViewById(R.id.tv_total);
        String totalStr = (total < 0 ? "- " : "") + "$" + (Math.abs(total));
        textView.setText(totalStr);
    }

    private void setDatePickers() {
        Button btnDateFrom = root.findViewById(R.id.btn_dateFrom);
        Button btnDateTo = root.findViewById(R.id.btn_dateTo);

        Calendar calendar = Calendar.getInstance();
        setDatePickerInButton(btnDateTo, calendar, false);

        calendar.add(Calendar.MONTH, -1);
        setDatePickerInButton(btnDateFrom, calendar, true);
    }

    private void setDatePickerInButton(Button btn, Calendar calendar, boolean isDateFrom) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        updateDates(btn, isDateFrom, year, month, day);

        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                updateDates(btn, isDateFrom, year, month, day);
            }
        }, year, month, day);

        btn.setOnClickListener(v -> {
            Animator.alpha(v);
            datePickerDialog.show();
        });
    }

    private void updateDates(Button btn, boolean isDateFrom, int year, int month, int day) {
        btn.setText(parsePickerValuesToDateText(year, month, day));

        if (isDateFrom)
            setDateFromParam(parsePickerValuesToDateParam(year, month, day));
        else setDateToParam(parsePickerValuesToDateParam(year, month, day));
    }

    private String parsePickerValuesToDateText(int year, int month, int dayOfMonth) {

        month++;

        String dayOfMonthStr = dayOfMonth < 10 ? "0".concat(String.valueOf(dayOfMonth)) : String.valueOf(dayOfMonth);
        String monthStr = month < 10 ? "0".concat(String.valueOf(month)) : String.valueOf(month);

        return dayOfMonthStr + "/" + monthStr + "/" + year;
    }

    private String parsePickerValuesToDateParam(int year, int month, int dayOfMonth) {

        month++;

        String dayOfMonthStr = dayOfMonth < 10 ? "0".concat(String.valueOf(dayOfMonth)) : String.valueOf(dayOfMonth);
        String monthStr = month < 10 ? "0".concat(String.valueOf(month)) : String.valueOf(month);

        return year + "/" + monthStr + "/" + dayOfMonthStr;
    }
}