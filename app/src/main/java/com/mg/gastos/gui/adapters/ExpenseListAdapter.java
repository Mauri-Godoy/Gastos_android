package com.mg.gastos.gui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mg.gastos.R;
import com.mg.gastos.entity.Expense;
import com.mg.gastos.utils.DateUtils;

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

        Expense expense = expenses.get(position);

        String category = expense.getCategory().getName();

        String description = expense.getDescription();

        if (StringUtils.isNoneBlank(description))
            category = category.concat(": " + description);

        holder.getCategory().setText(category);


        String amount = "$ " + expense.getAmount();
        String dateStr = DateUtils.parseToSimpleDate(expense.getDate());

        holder.getDate().setText(dateStr);
        holder.getAmount().setText(amount);
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Getter
        private final TextView category, date, amount;

        public ViewHolder(View view) {
            super(view);
            category = (TextView) view.findViewById(R.id.tv_category);
            date = (TextView) view.findViewById(R.id.tv_date);
            amount = (TextView) view.findViewById(R.id.tv_amount);
        }
    }
}
