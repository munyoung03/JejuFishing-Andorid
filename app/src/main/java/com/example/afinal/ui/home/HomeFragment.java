package com.example.afinal.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.afinal.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container, false);
        TextView temp = view.findViewById(R.id.weather_text);
        TextView humidity = view.findViewById(R.id.humidity);
        TextView wind_speed = view.findViewById(R.id.wind_speed);
        ImageView imageView = view.findViewById(R.id.imageView_weather);
        Handler handler = new Handler();
        new Thread(() -> {
            try{
                URL url =  new URL("https://api.openweathermap.org/data/2.5/forecast?lat=33&lon=126&APPID=beb156f9ab48aafb74a83023fab39ef3&fbclid=IwAR2lcLchJ0Fu8oZINThhQ4SP0sWSKkF4gXI1FGUfVRPy4diUg7EkjakDYkA");
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                String str;
                StringBuilder builder = new StringBuilder();
                while((str = br.readLine()) != null) {
                    builder.append(str + "\n");
                        }
                        JSONObject obj = new JSONObject(builder.toString());

                Log.println(Log.DEBUG, "Data : ", Float.toString(Float.parseFloat(obj.getJSONArray("list").getJSONObject(0).getJSONObject("main").get("temp").toString()) - 273.15f));
                handler.post(() -> {
                    try {
                        if(obj.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).get("main").equals("Clouds"))
                        {
                            imageView.setImageResource(R.drawable.sun_c);
                        }
                        else if(obj.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).get("main").equals("Clear"))
                        {
                            imageView.setImageResource(R.drawable.sun);
                        }
                        else if(obj.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).get("main").equals("Rain"))
                        {
                            imageView.setImageResource(R.drawable.rain);
                        }
                        temp.setText(Float.toString((int)(Float.parseFloat(obj.getJSONArray("list").getJSONObject(0).getJSONObject("main").get("temp").toString()) - 273.15f))+"°C");
                        humidity.setText(Float.toString(Float.parseFloat(obj.getJSONArray("list").getJSONObject(0).getJSONObject("main").get("humidity").toString()))+"%");
                        wind_speed.setText(Float.toString(Float.parseFloat(obj.getJSONArray("list").getJSONObject(0).getJSONObject("wind").get("speed").toString()))+"m/s");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
        return  view;
    }

}