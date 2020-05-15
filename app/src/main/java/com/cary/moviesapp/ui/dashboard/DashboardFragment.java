package com.cary.moviesapp.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cary.moviesapp.R;
import com.cary.moviesapp.ui.order.OrderActivity;
import com.cary.moviesapp.ui.order.PiaoHistoryActivity;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        View person_ll= root.findViewById(R.id.constraintLayout);
        View piao_ll= root.findViewById(R.id.line2);

        piao_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DashboardFragment.this.getActivity(), PiaoHistoryActivity.class);
                startActivity(i);
            }
        });
        return root;
    }
}