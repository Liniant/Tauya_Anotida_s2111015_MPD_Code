package com.example.bccrssv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class WeatherAdapter extends ArrayAdapter<WeatherContent> {

    private Context context;
    private List<WeatherContent> weatherList;

    public WeatherAdapter(Context context, int list_item_layout, List<WeatherContent> weatherList) {
        super(context, 0, weatherList);
        this.context = context;
        this.weatherList = weatherList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);
        }


        TextView textDate = convertView.findViewById(R.id.textDate);
        TextView textWeather = convertView.findViewById(R.id.textWeather);

        TextView textMinTemp = convertView.findViewById(R.id.textMinTemp);
       TextView textMaxTemp = convertView.findViewById(R.id.textMaxTemp);

        ImageView image = convertView.findViewById(R.id.imageWeather);


       // ImageView imageWeather = convertView.findViewById(R.id.imageWeather);

        WeatherContent weather = weatherList.get(position);

        // Set the text for the TextViews
        textDate.setText(weather.getDate());
        textWeather.setText(weather.getWeatherDescription());
       String x = String.valueOf(weather.getMinTemperature());
        String x2 = String.valueOf(weather.getMaxTemperature());
       textMinTemp.setText(x+"°C");
        textMaxTemp.setText(x2+"°C");

        if(weather.getWeatherDescription().toLowerCase().contains("rain")){
            image.setImageResource(R.drawable.rainy);
        }
        if(weather.getWeatherDescription().toLowerCase().contains("cloud")){
            image.setImageResource(R.drawable.cloudy);
        }
        if(weather.getWeatherDescription().toLowerCase().contains("sunn")){
            image.setImageResource(R.drawable.sunny);
        }

       // image.setText(weather.getImage());

       // image.setImageResource(weather.getImage());


        return convertView;
    }
}
