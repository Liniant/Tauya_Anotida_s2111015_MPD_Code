package com.example.bccrssv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dashboards3 extends AppCompatActivity {
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboards3);
    }
    */

    static String[] receivedArray = new String[20];
   static  String location2 = "";
   static  String description = "";

    TextView location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboards3);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // finish(); // Close the current activity and return to the previous activity
                Intent intent = new Intent(Dashboards3.this, Dashboards2.class);
                startActivity(intent);
            }
        });

        TextView wind = findViewById(R.id.wind);
        TextView temp = findViewById(R.id.temp);
        TextView humidity = findViewById(R.id.humidity);
        TextView pressure = findViewById(R.id.pressure);

        TextView windValue = findViewById(R.id.windValue);
        TextView tempValue = findViewById(R.id.tempValue);
        TextView humidityValue = findViewById(R.id.humidityValue);
        TextView pressureValue = findViewById(R.id.pressureValue);

        TextView wind_speed = findViewById(R.id.wind_speed);
        TextView wind_speedValue = findViewById(R.id.wind_speedValue);

        Intent intent = getIntent();
       receivedArray = intent.getStringArrayExtra("arrayExtra");
      location2 = getIntent().getStringExtra("intent_location");
        description = getIntent().getStringExtra("description");

        Map<String,String> decsriptionMap = extractTagsAndValues(description);

        for (Map.Entry<String, String> entry : decsriptionMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
            String c = entry.getKey();
            if(entry.getKey().toLowerCase().contains("wind")){
                wind.setText(entry.getKey());
                windValue.setText(entry.getValue());
            }
            if (entry.getKey().toLowerCase().contains("speed")){
                wind_speed.setText(entry.getKey());
                wind_speedValue.setText(entry.getValue());
            }
            if (entry.getKey().toLowerCase().contains("temp")){
                temp.setText(entry.getKey());
                tempValue.setText(entry.getValue());
            }
            if (entry.getKey().toLowerCase().contains("humidity")){
                humidity.setText(entry.getKey());
                humidityValue.setText(entry.getValue());
            }
            if (entry.getKey().toLowerCase().contains("pressure")){
                pressure.setText(entry.getKey());
                pressureValue.setText(entry.getValue());
            }

        }
        System.out.println("trial pass next"+receivedArray);
        System.out.println("did we take data String"+location2);

        System.out.println("Description taken guys #Nyika Inovakwa nevene vayo"+description);

        location = findViewById(R.id.location);
        location.setText(location2);

     /*   for(int i = 0; i < receivedArray.length; i++){

            System.out.println("kunyeba uko "+receivedArray[i]);
        }
*/
        Calendar calendar = Calendar.getInstance();

        // Get today's date components
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Months start from 0
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //====================================================

        LineChart lineChart = findViewById(R.id.lineChart);

// Create dummy data entries with x-values as dates

        List<Entry> entries = new ArrayList<>();
     /**   if(receivedArray==null){
            entries.add(new Entry(getDateInMillis(year, month, day-2), Float.parseFloat("0")));
            entries.add(new Entry(getDateInMillis(year, month, day-1), Float.parseFloat("0")));
            entries.add(new Entry(getDateInMillis(year, month, day), Float.parseFloat("0")));
        }else{
            entries.add(new Entry(getDateInMillis(year, month, day-2), Float.parseFloat(receivedArray[0])));
            entries.add(new Entry(getDateInMillis(year, month, day-1), Float.parseFloat(receivedArray[1])));
            entries.add(new Entry(getDateInMillis(year, month, day), Float.parseFloat(receivedArray[2])));
        }
**/
        entries.add(new Entry(getDateInMillis(year, month, day-2), Float.parseFloat(receivedArray==null?"0":receivedArray[0])));
        entries.add(new Entry(getDateInMillis(year, month, day-1), Float.parseFloat(receivedArray==null?"0":receivedArray[1])));
        entries.add(new Entry(getDateInMillis(year, month, day), Float.parseFloat(receivedArray==null?"0":receivedArray[2])));

        //  entries.add(new Entry(getDateInMillis(2022, 1, 4), 15));
        //  entries.add(new Entry(getDateInMillis(2022, 1, 5), 12));

        // Create a LineDataSet with the entries
        LineDataSet dataSet = new LineDataSet(entries, "3 Day Temperature Forecast");

        // Customize the LineDataSet appearance
        dataSet.setColor(Color.BLUE);
        dataSet.setCircleColor(Color.RED);
        dataSet.setValueTextColor(Color.BLACK);

        // Create a list of LineDataSets
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        // Create a LineData object from the LineDataSet(s)
        LineData lineData = new LineData(dataSets);

        // Set the LineData to the LineChart
        lineChart.setData(lineData);

        // Customize the LineChart appearance
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new DateAxisValueFormatter());
        xAxis.setLabelCount(entries.size()); // Set the number of x-axis labels

        lineChart.getAxisRight().setEnabled(false);
        lineChart.getDescription().setEnabled(false);

        // Refresh the chart
        lineChart.invalidate();

        //========================================================

    }


    // Utility method to convert a date to milliseconds
    private long getDateInMillis(int year, int month, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date date = sdf.parse(year + "-" + month + "-" + day);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Custom ValueFormatter for displaying dates on the x-axis
    // Custom ValueFormatter for displaying dates on the x-axis
    private class DateAxisValueFormatter extends ValueFormatter {

        private final SimpleDateFormat sdf;

        public DateAxisValueFormatter() {
            sdf = new SimpleDateFormat("d MMM", Locale.getDefault());
        }

        @Override
        public String getFormattedValue(float value) {
            return sdf.format(new Date((long) value));
        }
    }

    public static Map<String, String> extractTagsAndValues(String input) {
        // Define the regular expression pattern to match the tags and values
        Map<String, String> tagValueMap = new HashMap<>();
        if(input!=null) {
            String patternString = "([A-Za-z ]+):\\s*([^,]+)";

            // Create a Pattern object
            Pattern pattern = Pattern.compile(patternString);

            // Create a Matcher object
            Matcher matcher = pattern.matcher(input);

            // Create a map to store the extracted tags and values


            // Find and store each tag and value in the map
            while (matcher.find()) {
                String tag = matcher.group(1);
                String value = matcher.group(2);
                tagValueMap.put(tag, value);
            }
        }else{
            tagValueMap.put("No Data", "No Data");
        }
        return tagValueMap;
    }
}