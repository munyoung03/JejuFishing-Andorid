package com.example.afinal.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.afinal.R;
import com.example.afinal.fish.Bang;
import com.example.afinal.fish.Cham;
import com.example.afinal.fish.Defender;
import com.example.afinal.fish.Dom;
import com.example.afinal.fish.Galchi;
import com.example.afinal.fish.Gam;
import com.example.afinal.fish.Gwanga;
import com.example.afinal.fish.Nooa;
import com.example.afinal.fish.Omega3;
import com.example.afinal.fish.Poison;
import com.example.afinal.fish.Ssom;
import com.example.afinal.fish.Stone;

public class NotificationsFragment extends Fragment {
    ImageButton button1;
    ImageButton button2;
    ImageButton button3;
    ImageButton button4;
    ImageButton button5;
    ImageButton button6;
    ImageButton button7;
    ImageButton button8;
    ImageButton button9;
    ImageButton button10;
    ImageButton button11;
    ImageButton button12;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications,null);

        button1 = view.findViewById(R.id.fish_1);
        button2 = view.findViewById(R.id.fish_2);
        button3 = view.findViewById(R.id.fish_3);
        button4 = view.findViewById(R.id.fish_4);
        button5 = view.findViewById(R.id.fish_5);
        button6 = view.findViewById(R.id.fish_6);
        button7 = view.findViewById(R.id.fish_7);
        button8 = view.findViewById(R.id.fish_8);
        button9 = view.findViewById(R.id.fish_9);
        button10 = view.findViewById(R.id.fish_10);
        button11 = view.findViewById(R.id.fish_11);
        button12 = view.findViewById(R.id.fish_12);

        button1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Galchi.class);
            startActivity(intent);
                }
        );
        button2.setOnClickListener(v -> {
                    Intent intent = new Intent(getActivity(), Gam.class);
                    startActivity(intent);
                }
        );
        button3.setOnClickListener(v -> {
                    Intent intent = new Intent(getActivity(), Dom.class);
                    startActivity(intent);
                }
        );
        button4.setOnClickListener(v -> {
                    Intent intent = new Intent(getActivity(), Defender.class);
                    startActivity(intent);
                }
        );
        button5.setOnClickListener(v -> {
                    Intent intent = new Intent(getActivity(), Ssom.class);
                    startActivity(intent);
                }
        );
        button6.setOnClickListener(v -> {
                    Intent intent = new Intent(getActivity(), Stone.class);
                    startActivity(intent);
                }
        );
        button7.setOnClickListener(v -> {
                    Intent intent = new Intent(getActivity(), Nooa.class);
                    startActivity(intent);
                }
        );
        button8.setOnClickListener(v -> {
                    Intent intent = new Intent(getActivity(), Cham.class);
                    startActivity(intent);
                }
        );button9.setOnClickListener(v -> {
                    Intent intent = new Intent(getActivity(), Gwanga.class);
                    startActivity(intent);
                }
        );button10.setOnClickListener(v -> {
                    Intent intent = new Intent(getActivity(), Poison.class);
                    startActivity(intent);
                }
        );button11.setOnClickListener(v -> {
                    Intent intent = new Intent(getActivity(), Omega3.class);
                    startActivity(intent);
                }
        );
        button12.setOnClickListener(v -> {
                    Intent intent = new Intent(getActivity(), Bang.class);
                    startActivity(intent);
                }
        );
        return view;
    }
}