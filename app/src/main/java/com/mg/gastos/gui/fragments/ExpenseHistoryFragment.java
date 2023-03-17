package com.mg.gastos.gui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mg.gastos.R;
import com.mg.gastos.db.ExpenseRepository;
import com.mg.gastos.entity.Expense;
import com.mg.gastos.gui.adapters.ExpenseListAdapter;

import java.util.List;

public class ExpenseHistoryFragment extends Fragment {

    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_expense_history, container, false);

        setTotal();
        setExpenseList();

        return root;
    }

    private void setExpenseList() {
        RecyclerView recyclerView = root.findViewById(R.id.rv_expenseList);
        List<Expense> expenses = ExpenseRepository.getInstance(requireContext()).getAll();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new ExpenseListAdapter(expenses));
    }

    private void setTotal() {
        TextView textView = root.findViewById(R.id.tv_total);
        Double total = ExpenseRepository.getInstance(requireContext()).getTotal();
        textView.setText("$ " + total);
    }
}