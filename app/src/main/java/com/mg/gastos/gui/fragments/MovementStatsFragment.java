package com.mg.gastos.gui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.itextpdf.text.DocumentException;
import com.mg.gastos.R;
import com.mg.gastos.db.MovementRepository;
import com.mg.gastos.entity.Movement;
import com.mg.gastos.gui.MovementActivity;
import com.mg.gastos.utils.Animator;
import com.mg.gastos.utils.DateUtils;
import com.mg.gastos.utils.PDFGenerator;
import com.mg.gastos.utils.PermissionHelper;

import java.util.ArrayList;
import java.util.List;

public class MovementStatsFragment extends Fragment {

    View root;
    private final int[] colors = new int[]{R.color.chart1, R.color.chart2, R.color.chart3, R.color.chart4,
            R.color.chart5, R.color.secondary, R.color.primary};

    MovementRepository movementRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_movement_stats, container, false);

        PermissionHelper.requestPermission(requireActivity());
        movementRepository = MovementRepository.getInstance(requireContext());

        createBarChart();
        setButtonAction();

        return root;
    }

    private BarChart chart;

    private void createBarChart() {
        setUpLineChart();
        setData();
        MovementActivity.setToolbarTitle("Gastos por Mes");
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

        List<Movement> list = movementRepository.getMonthAndValues();

        for (int i = 0; list.size() > i; i++) {

            ArrayList<BarEntry> entries = new ArrayList<>();

            Movement movement = list.get(i);

            entries.add(new BarEntry(i, movement.getAmount().floatValue()));

            BarDataSet barDataSet = new BarDataSet(entries, DateUtils.parseToMonth(movement.getDate()).toUpperCase());

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
        button.setOnClickListener(v -> {
            Animator.alpha(v);
            if (PermissionHelper.checkPermission(requireActivity())) {
                v.setEnabled(false);
                try {
                    PDFGenerator.generatePdfTable(requireContext(), movementRepository.getAll());
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                PermissionHelper.requestPermission(requireActivity());
                Toast.makeText(requireContext(), "Necesito otorgar los permisos de almacenamiento.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}