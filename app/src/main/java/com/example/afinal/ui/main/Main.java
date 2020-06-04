package com.example.afinal.ui.main;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.afinal.Hotplace1;
import com.example.afinal.Hotplace2;
import com.example.afinal.R;

public class Main extends Fragment {
        Button button;
        Button button1;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, null);
        button = root.findViewById(R.id.main_image1);
        button1 = root.findViewById(R.id.main_image2);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Hotplace1.class);
            startActivity(intent);
        });
        button1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Hotplace2.class);
            startActivity(intent);
        });
        Drawable alpha = root.findViewById(R.id.main_image1).getBackground();
        alpha.setAlpha(100);
        Drawable alpha2 = root.findViewById(R.id.main_image2).getBackground();
        alpha2.setAlpha(100);
        return root;
    }
}