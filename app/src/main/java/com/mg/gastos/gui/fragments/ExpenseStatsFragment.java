package com.mg.gastos.gui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.mg.gastos.R;
import com.mg.gastos.db.ExpenseRepository;
import com.mg.gastos.entity.Expense;
import com.mg.gastos.gui.ExpenseActivity;
import com.mg.gastos.utils.DateUtils;
import com.mg.gastos.utils.PDFGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExpenseStatsFragment extends Fragment {

    View root;
    private final int[] colors = new int[]{R.color.chart1, R.color.chart2, R.color.chart3, R.color.chart4,
            R.color.chart5, R.color.secondary, R.color.primary};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_expense_stats, container, false);

        createBarChart();
        setButtonAction();

        return root;
    }

    private BarChart chart;

    private void createBarChart() {
        setUpLineChart();
        setData();
        ExpenseActivity.setToolbarTitle("Gastos por Mes");
    }

    private void setUpLineChart() {

        chart = root.findViewById(R.id.chart);

        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.getDescription().setEnabled(false);
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);
        chart.getAxisRight().setEnabled(false);
        chart.getXAxis().setEnabled(false);
        chart.getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
    }

    private ArrayList<IBarDataSet> createDataSet() {

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();

        ExpenseRepository expenseRepository = ExpenseRepository.getInstance(requireContext());

        List<Expense> list = expenseRepository.getMonthAndValues();

        for (int i = 0; list.size() > i; i++) {

            ArrayList<BarEntry> entries = new ArrayList<>();

            Expense expense = list.get(i);

            entries.add(new BarEntry(i, expense.getAmount().floatValue()));

            BarDataSet barDataSet = new BarDataSet(entries, DateUtils.parseToMonth(expense.getDate()).toUpperCase());

            barDataSet.setColor(requireContext().getColor(colors[i]));

            dataSets.add(barDataSet);

        }

        return dataSets;
    }

    private void setData() {

        ArrayList<IBarDataSet> arrayDataSet = createDataSet();

        BarData data = new BarData(arrayDataSet);
        data.setValueTextSize(10f);
        data.setBarWidth(0.9f);
        chart.setData(data);

    }

    private void setButtonAction() {
        Button button = root.findViewById(R.id.btn_generatePdf);
        button.setOnClickListener(v ->
                PDFGenerator.generate(requireContext()));
    }
}