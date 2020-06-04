package com.example.afinal.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.afinal.Hotplace2;
import com.example.afinal.Hotplace3;
import com.example.afinal.R;
import com.example.afinal.Hotplace1;

public class DashboardFragment extends Fragment {
    private Button button_1;
    private Button button_2;
    private Button button_3;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard,container, false);
        button_1 = root.findViewById(R.id.hot_button1);
        button_2 = root.findViewById(R.id.hot_button2);
        button_3 = root.findViewById(R.id.hot_button3);
        button_1.setOnClickListener((v)->{
                Intent intent = new Intent(getActivity(), Hotplace1.class);
                startActivity(intent);
            }
        );
        button_2.setOnClickListener((v)->{
                    Intent intent = new Intent(getActivity(), Hotplace2.class);
                    startActivity(intent);
                }
        );
        button_3.setOnClickListener((v)->{
                    Intent intent = new Intent(getActivity(), Hotplace3.class);
                    startActivity(intent);
                }
        );
        return root;
    }
}