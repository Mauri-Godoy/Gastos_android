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
import com.mg.gastos.db.MovementRepository;
import com.mg.gastos.entity.Movement;
import com.mg.gastos.gui.adapters.MovementListAdapter;

import java.util.List;

public class MovementHistoryFragment extends Fragment {

    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_movement_history, container, false);

        setTotal();
        setMovementList();

        return root;
    }

    private void setMovementList() {
        RecyclerView recyclerView = root.findViewById(R.id.rv_movementList);
        List<Movement> movementList = MovementRepository.getInstance(requireContext()).getAll();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new MovementListAdapter(movementList));
    }

    private void setTotal() {
        TextView textView = root.findViewById(R.id.tv_total);
        Double total = MovementRepository.getInstance(requireContext()).getTotal();
        String totalStr = (total < 0 ? "- " : "") + "$" + (Math.abs(total));
        textView.setText(totalStr);
    }
}