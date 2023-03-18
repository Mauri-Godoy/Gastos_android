package com.mg.gastos.gui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mg.gastos.R;
import com.mg.gastos.db.Database;
import com.mg.gastos.entity.Category;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {

    List<Category> categoryList;
    Context context;

    public CategoryAdapter(Context context) {
        super(context, 0);
        this.context = context;
        this.categoryList = Database.getInstance(context).categoryDao().getAll();
    }

    @Override
    public Category getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.expense_list, parent, false);
        }

        Category category = getItem(position);

        TextView description = (TextView) convertView.findViewById(R.id.tv_description);
        TextView date = (TextView) convertView.findViewById(R.id.tv_date);
        TextView amount = (TextView) convertView.findViewById(R.id.tv_amount);

        date.setText(category.getName());
        amount.setText(category.getCode());
        return convertView;
    }
}
