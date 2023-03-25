package com.mg.gastos.gui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.mg.gastos.R;
import com.mg.gastos.data.entity.Movement;
import com.mg.gastos.data.repository.MovementRepository;
import com.mg.gastos.utils.DateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BarChartFragment extends Fragment {

    private final int[] colors = new int[]{R.color.chart1, R.color.chart2, R.color.chart3, R.color.chart4,
            R.color.chart5, R.color.chart6, R.color.chart7, R.color.chart8, R.color.secondary, R.color.primary};

    private BarChart chart;
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_bar_chart, container, false);
        chart = root.findViewById(R.id.barChart);

        createBarChart();

        return root;
    }


    private void createBarChart() {
        setUpLineChart();
        setData();
    }

    private void setUpLineChart() {

        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.getDescription().setEnabled(false);
        chart.setTouchEnabled(false);
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setTextSize(9);
        chart.setDoubleTapToZoomEnabled(false);
        chart.setDrawGridBackground(false);
        chart.getAxisRight().setEnabled(false);
        chart.getAxisRight().setAxisMinimum(0);
        chart.getAxisLeft().setAxisMinimum(0);
        chart.getLegend().setEnabled(false);
    }

    private ArrayList<IBarDataSet> createDataSet() {

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();

        List<Movement> list = MovementRepository.getInstance(requireContext()).getMonthAndValues(true);
        Collections.reverse(list);

        ArrayList<String> labels = new ArrayList<>();

        for (int i = 0; list.size() > i; i++) {

            ArrayList<BarEntry> entries = new ArrayList<>();

            Movement movement = list.get(i);

            entries.add(new BarEntry(i, movement.getAmount().floatValue()));

            BarDataSet barDataSet = new BarDataSet(entries, "");
            labels.add(DateUtils.parseToMonthAndYear(movement.getDate()).toUpperCase());
            barDataSet.setColor(requireContext().getColor(colors[i]));
            dataSets.add(barDataSet);
        }

        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        return dataSets;
    }

    private void setData() {

        ArrayList<IBarDataSet> arrayDataSet = createDataSet();

        BarData data = new BarData(arrayDataSet);
        data.setValueTextSize(10f);

        if (arrayDataSet.size() <= 0) {
            LinearLayout linearLayout = root.findViewById(R.id.layout_barChart);
            linearLayout.setVisibility(View.GONE);
        } else if (arrayDataSet.size() <= 3)
            data.setBarWidth(0.3f);
        else data.setBarWidth(0.9f);

        chart.setData(data);

    }
}