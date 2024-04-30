package com.example.bccrssv2;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class Weather2Adapter extends ArrayAdapter<WeatherContent> {

    private Context context;
    private List<WeatherContent> weatherList;

    public Weather2Adapter(Context context, int list_item_layout, List<WeatherContent> weatherList) {
        super(context, 0, weatherList);
        this.context = context;
        this.weatherList = weatherList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.weather_card_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textViewName = convertView.findViewById(R.id.textViewName);
            viewHolder.textViewDate = convertView.findViewById(R.id.textViewDate);
            viewHolder.textViewWeatherDescription = convertView.findViewById(R.id.textViewWeatherDescription);
            viewHolder.textViewTemperature = convertView.findViewById(R.id.textViewTemperature);
            viewHolder.imageViewWeatherIcon = convertView.findViewById(R.id.imageViewWeatherIcon);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Get the current WeatherContent object
        WeatherContent weather = getItem(position);

        // Set the data into the views
        viewHolder.textViewName.setText(weather.getName());
        viewHolder.textViewDate.setText(weather.getDate());
        viewHolder.textViewWeatherDescription.setText(weather.getWeatherDescription());
        //viewHolder.textViewTemperature.setText(String.format(Locale.getDefault(), "Min: %d°C Max: %d°C", weather.getMinTemperature(), weather.getMaxTemperature()));
        viewHolder.textViewTemperature.setText(String.format(Locale.getDefault(), "%s°C Max: %s°C", weather.getMinTemperature(), weather.getMaxTemperature()));
       viewHolder.imageViewWeatherIcon.setImageResource(getWeatherIconResource(weather.getImage()));

        return convertView;
    }

    private static class ViewHolder {
        TextView textViewName;
        TextView textViewDate;
        TextView textViewWeatherDescription;
        TextView textViewTemperature;
        ImageView imageViewWeatherIcon;
    }

    private int getWeatherIconResource(String imageName) {
       // int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(imageName, "drawable", context.getPackageName());
        if (resourceId != 0) {
            return resourceId;
        } else {
            // If the resource is not found, you can return a default icon resource ID or handle it as per your requirements.
            return R.drawable.baseline_arrow_back_24;
        }
    }
}
