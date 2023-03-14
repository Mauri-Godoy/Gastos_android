package com.mg.gastos.gui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mg.gastos.R;
import com.mg.gastos.entity.Expense;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import lombok.Getter;

public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ViewHolder> {

    private final List<Expense> expenses;

    public ExpenseListAdapter(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expense_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String description = expenses.get(position).getDescription();

        if (StringUtils.isBlank(description))
            description = "-";

        holder.getDescription().setText(description);
        holder.getDate().setText(expenses.get(position).getDate());
        holder.getAmount().setText("$" + expenses.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Getter
        private final TextView description, date, amount;

        public ViewHolder(View view) {
            super(view);
            description = (TextView) view.findViewById(R.id.tv_description);
            date = (TextView) view.findViewById(R.id.tv_date);
            amount = (TextView) view.findViewById(R.id.tv_amount);
        }
    }
}
