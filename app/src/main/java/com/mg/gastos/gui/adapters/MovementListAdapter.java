package com.mg.gastos.gui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mg.gastos.R;
import com.mg.gastos.entity.Movement;
import com.mg.gastos.utils.DateUtils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import lombok.Getter;

public class MovementListAdapter extends RecyclerView.Adapter<MovementListAdapter.ViewHolder> {

    private final List<Movement> movementList;

    public MovementListAdapter(List<Movement> movementList) {
        this.movementList = movementList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movement_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Movement movement = movementList.get(position);

        String category = movement.getCategory().getName();

        String description = movement.getDescription();

        if (StringUtils.isNoneBlank(description))
            category = category.concat(": " + description);

        holder.getCategory().setText(category);


        String amount = "$ " + movement.getAmount();
        String dateStr = DateUtils.parseToSimpleDate(movement.getDate());

        holder.getDate().setText(dateStr);
        holder.getAmount().setText(amount);
    }

    @Override
    public int getItemCount() {
        return movementList.size();
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
