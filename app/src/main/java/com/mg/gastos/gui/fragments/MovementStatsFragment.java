package com.mg.gastos.gui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.DocumentException;
import com.mg.gastos.R;
import com.mg.gastos.data.entity.Movement;
import com.mg.gastos.data.repository.MovementRepository;
import com.mg.gastos.utils.Animator;
import com.mg.gastos.utils.DateUtils;
import com.mg.gastos.utils.MovementUtils;
import com.mg.gastos.utils.PDFGenerator;
import com.mg.gastos.utils.PermissionHelper;

import java.util.Date;
import java.util.List;

public class MovementStatsFragment extends Fragment {

    private View root;
    private List<Movement> movementList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_movement_stats, container, false);

        PermissionHelper.requestPermission(requireActivity());

        MovementRepository movementRepository = MovementRepository.getInstance(requireContext());
        movementList = movementRepository.getAll();

        setStats();
        setButtonAction();
        setFragment();

        return root;
    }

    private void setStats() {
        TextView lastMovement, totalMovements, total;
        lastMovement = root.findViewById(R.id.tv_lastMovement);
        totalMovements = root.findViewById(R.id.tv_totalMovements);
        total = root.findViewById(R.id.tv_totalMoney);

        if(!movementList.isEmpty()) {
            String lastMovementStr = "Ultimo movimiento: "
                    .concat(DateUtils.parseToTableFormat(movementList.get(0).getDate()));
            lastMovement.setText(lastMovementStr);
        }

        String totalMovementsStr = movementList.size() + " movimientos registrados";
        totalMovements.setText(totalMovementsStr);

        String totalStr = "Total: $" + MovementUtils.getTotal(movementList);
        total.setText(totalStr);
    }

    private void setFragment() {
        requireActivity().getSupportFragmentManager().beginTransaction().add(R.id.layout_frameChart, new BarChartFragment()).commit();
    }

    private void setButtonAction() {
        Button button = root.findViewById(R.id.btn_generatePdf);
        button.setOnClickListener(v -> {
            Animator.alpha(v);
            if (PermissionHelper.checkPermission(requireActivity())) {
                v.setEnabled(false);
                try {
                    PDFGenerator.generatePdfTable(requireContext(), movementList);
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