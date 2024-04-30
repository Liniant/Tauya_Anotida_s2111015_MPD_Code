package com.example.bccrssv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
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

public class Dashboards2 extends AppCompatActivity {

  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboards2);
    }*/
  private static final int SPLASH_DURATION = 3000; // 3 seconds
    //=========Trial=================
    private ListView listView;
    // ImageView imageWeather;
    private WeatherAdapter adapter;
    private List<WeatherContent> weatherList;
    //===============================
    private TextView tvImageTitle;

    private TextView tvDailyTitle;
    private TextView tvDailyDate;
    ListView lvRss;
    String description;
    public   String selectedLocationId2 = "";//""2648579";
    Map<String,String> map = new HashMap<>();
    Map<String,String> map2 = new HashMap<>();
    List<HashMap<String, String>> dataList = new ArrayList<>();
    List<HashMap<String, String>> dataList2 = new ArrayList<>();
    ArrayList<String> titles=new ArrayList<>();
    ArrayList<String> titles2=new ArrayList<>();
    ArrayList<String> links = new ArrayList<>();
    ArrayList<String> date_of_week = new ArrayList<>();
    ArrayList<String> date_of_week2 = new ArrayList<>();
static  String genericMarkerKey = "";
static  boolean genericMarkerKeyControl = true;
    private Button move;
    TextView location;
   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboards2);



    }*/



    //======================================================================
  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        // Add your custom back button logic here
        // For example, you can navigate back to MainActivity
        Intent intent = new Intent(Dashboards2.this, MainActivity.class);
        startActivity(intent);
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_dashboards2);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // finish(); // Close the current activity and return to the previous activity
                Intent intent = new Intent(Dashboards2.this, MainActivity.class);
                startActivity(intent);
            }
        });


        genericMarkerKey= getIntent().getStringExtra("genericMarkerKey");

        selectedLocationId2= genericMarkerKey;



     /*   Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // finish(); // Close the current activity and return to the previous activity
                Intent intent = new Intent(Dashboards2.this, MainActivity.class);
                startActivity(intent);
            }
        });*/

        move = findViewById(R.id.btnViewDashBoard);

        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] myArray = new String[3];
                List<Integer>c = new ArrayList<>();
                for(int i = 0; i < titles.size(); i++){
                    // map.put("title", titles.get(i));
                    // map.put("date_of_week", date_of_week.get(i));
                    // dataList.add(map);
                    System.out.print("matanga zvatanga"+extractTemperature(titles.get(i),"Maximum Temperature"));
                    //   c.add(extractTemperature(titles.get(i),"Maximum Temperature"));
                    myArray[i] = String.valueOf(extractTemperature(titles.get(i),"Maximum Temperature"));

                }
                genericMarkerKeyControl= false;
               Intent intent = new Intent(Dashboards2.this, Dashboards3.class);
                intent.putExtra("arrayExtra", myArray);

                String location = tvDailyTitle.getText().toString();
                intent.putExtra("intent_location", location);

                intent.putExtra("description", description);


                startActivity(intent);
            }});

        lvRss =  findViewById(R.id.lvRss);
        tvImageTitle = findViewById(R.id.tvImageTitle);

        tvDailyTitle = findViewById(R.id.tvDailyTitle);
        tvDailyDate = findViewById(R.id.tvDailyDate);

        //========================Drop Down Starts==============================================

        String[] locations = {"Select location","Gaslow", "London","Oman","Mauritius","Bangladesh","New York"};
        String[] locationIds = {"0","2648579", "2643743","287286","934154","1185241","5128581"};

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locations);
        // Set the layout resource for the dropdown menu
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerLocations = findViewById(R.id.spinnerLocations);

        spinnerLocations.setAdapter(spinnerAdapter);

        spinnerLocations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve the selected location ID
               /* if(genericMarkerKeyControl){

                }else{
                    String selectedLocationId = locationIds[position];
                    Log.d("Selected ID", selectedLocationId);
                      selectedLocationId2 = selectedLocationId;
                }*/
                if (position != 0) {
                    String selectedLocationId = locationIds[position];
                    Log.d("Selected ID", selectedLocationId);
                    selectedLocationId2 = selectedLocationId;
                }


                // Print the selected ID

                Log.d("=====================","Hezvoko44");
                Log.d(extractWeatherDescription((String) tvDailyTitle.getText()),"Amheno ma gala");
                Log.d(String.valueOf(extractWeatherDescription((String) tvDailyTitle.getText()).toLowerCase().contains("cloud")),"Amheno ma gala");
                Log.d(String.valueOf(extractWeatherDescription((String) tvDailyTitle.getText()).toLowerCase().contains("rain")),"Amheno ma gala2");
                Log.d(tvDailyTitle.getText().toString(),"Hezvoko");
                Log.d("=====================","Hezvoko55");
                if(tvDailyTitle.getText().toString().toLowerCase().contains("rain")){
                    ImageView imagevc = findViewById(R.id.ivDailyImageWeather);
                    imagevc.setImageResource(R.drawable.rainy);

                }
                if(tvDailyTitle.getText().toString().toLowerCase().contains("not")){
                    ImageView imagevc = findViewById(R.id.ivDailyImageWeather);
                    imagevc.setImageResource(R.drawable.sunny);

                }
                if(tvDailyTitle.getText().toString().toLowerCase().contains("sunn")){
                    ImageView imagevc = findViewById(R.id.ivDailyImageWeather);
                    imagevc.setImageResource(R.drawable.sunny);

                }
                if(tvDailyTitle.getText().toString().toLowerCase().contains("cloud")){
                    ImageView imagevc = findViewById(R.id.ivDailyImageWeather);
                    imagevc.setImageResource(R.drawable.cloudy);

                }

                new ProcessInBackground().execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where nothing is selected
                // You can leave this empty or add any desired functionality
            }
        });

        //=====================Drop Down Ends===============================================

        //===========Trial=====================

        //==================================
        new ProcessInBackground().execute();
    }

    public InputStream getInputStream(URL url){
        try {
            //   URL url = new URL("https://www.bcc.com/rss");
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }


    // public class ProcessInBackground extends AsyncTask<Integer, Void, Exception> {
    public class ProcessInBackground extends AsyncTask<Integer, Void, List<WeatherContent>> {
        ProgressDialog progressDialog = new ProgressDialog(Dashboards2.this);
        Exception exception = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
         //   progressDialog.setMessage("Loading RSS Feed please wait ...");
         //   progressDialog.show();
        }

        //===============================================================

        private void setText(final TextView text,final String value){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    text.setText(value);
                }
            });
        }
        @Override
        //   protected Exception doInBackground(Integer... integers) {
        protected List<WeatherContent> doInBackground(Integer... integers) {
            titles.clear();
            titles2.clear();
            links.clear();


System.out.println("mabvira viravira"+selectedLocationId2);
            date_of_week.clear();
            date_of_week2.clear();
            try {
                URL url = new URL("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/"+selectedLocationId2);
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(getInputStream(url), "UTF_8");

                boolean insideItem = false;
                boolean insideImage = false;  // Add a boolean to check if inside <image> tag
                int eventType = xpp.getEventType();

                ArrayList<String> imageTitles = new ArrayList<>();  // Declare ArrayList for image titles

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = true;
                        } else if(xpp.getName().equalsIgnoreCase("title")) {

                            if (insideItem) {
                                titles.add(xpp.nextText());
                            } else if (insideImage) {  // Check if inside <image> tag
                                tvImageTitle.setText(xpp.nextText());
                            }
                        } else if (xpp.getName().equalsIgnoreCase("link")) {
                            if (insideItem) {
                                links.add(xpp.nextText());
                            }
                        }else if (xpp.getName().equalsIgnoreCase("dc:date")) {
                            if (insideItem) {
                                date_of_week.add(xpp.nextText());
                            }
                        } else if (xpp.getName().equalsIgnoreCase("image")) {
                            insideImage = true;  // Set insideImage to true when entering <image> tag
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = false;
                        } else if (xpp.getName().equalsIgnoreCase("image")) {
                            insideImage = false;  // Set insideImage to false when exiting </image> tag
                        }
                    }
                    eventType = xpp.next();
                }

                System.out.println("Image Titles: " + imageTitles);
                System.out.println("datex of weeks : " + date_of_week);
            } catch (MalformedURLException e) {
                exception = e;
            } catch (XmlPullParserException e) {
                exception = e;
            } catch (IOException e) {
                exception = e;
            }


            try {
                URL url = new URL("https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/"+selectedLocationId2);
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(getInputStream(url), "UTF_8");

                boolean insideItem = false;
                boolean insideImage = false;  // Add a boolean to check if inside <image> tag
                int eventType = xpp.getEventType();

                ArrayList<String> imageTitles = new ArrayList<>();  // Declare ArrayList for image titles

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = true;
                        } else if (xpp.getName().equalsIgnoreCase("title")) {
                            if (insideItem) {
                                setText(tvDailyTitle,xpp.nextText());
                            }
                        } else if (xpp.getName().equalsIgnoreCase("link")) {
                            if (insideItem) {
                                links.add(xpp.nextText());
                            }
                        }else if (xpp.getName().equalsIgnoreCase("dc:date")) {
                            if (insideItem) {
                                setText(tvDailyDate,xpp.nextText());
                            }
                        }
                        else if (xpp.getName().equalsIgnoreCase("description")) {
                            if (insideItem) {
                                // setText(tvDailyDate,xpp.nextText());
                                description = xpp.nextText();
                            }
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = false;
                        }
                    }
                    eventType = xpp.next();
                }
                weatherList = new ArrayList<>();
                String image="rainy";


                // Create a list of WeatherContent objects

                for (int i = 0; i < titles.size(); i++) {


                    weatherList.add(new WeatherContent(
                            extractDate(titles.get(i)),
                            extractWeatherDescription(titles.get(i)),
                            extractTemperature(titles.get(i),
                                    "Minimum Temperature"),
                            extractTemperature(titles.get(i),"Maximum Temperature"),
                            image

                    ));

                }

                //     weatherList.add(new WeatherContent("2024-04-28", "Sunny", 25, 30));
                //  weatherList.add(new WeatherContent("2024-04-29", "Cloudy", 20, 26));


                System.out.println("Image Titles2: " + imageTitles);
                System.out.println("datex of weeks2 : " + titles2);
                System.out.println("datex of weeks2 : " + date_of_week2);
            } catch (MalformedURLException e) {
                exception = e;
            } catch (XmlPullParserException e) {
                exception = e;
            } catch (IOException e) {
                exception = e;
            }
            //  return exception;
            return weatherList;
        }
/*
       @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);

            List<HashMap<String, String>> dataList = new ArrayList<>();

            for (int i = 0; i < titles.size(); i++) {
                HashMap<String, String> data = new HashMap<>();
                data.put("title", titles.get(i));
                data.put("date_of_week", date_of_week.get(i));
                dataList.add(data);
            }

            String[] from = {"title", "date_of_week"};

            // Create an array of the resource IDs of the TextViews in your list item layout
            int[] to = {android.R.id.text1, android.R.id.text2};
            SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, dataList, android.R.layout.simple_list_item_2, from, to);
            lvRss.setAdapter(adapter);

            progressDialog.dismiss();
        }
*/

        //========================Trial Of class Use=======================================

        protected void onPostExecute(List<WeatherContent> result) {
            listView = findViewById(R.id.lvRss);


            // Create an instance of the custom adapter
            // adapter = new WeatherAdapter((Context) weatherList, weatherList);
            adapter = new WeatherAdapter(Dashboards2.this, R.layout.list_item_layout, result);
            listView.setAdapter(adapter);
            progressDialog.dismiss();
        }

        //=======================Ends Here========================================
    }

    private static String extractDate(String weatherInfo) {
        String[] parts = weatherInfo.split(":");
        return parts[0].trim();
    }
    private static int extractTemperature(String weatherInfo, String temperatureType) {
        int temperature = 0;

        int startIndex = weatherInfo.indexOf(temperatureType);
        if (startIndex != -1) {
            startIndex += temperatureType.length() + 1; // Skip the temperature type and colon
            int endIndex = weatherInfo.indexOf("°C", startIndex);
            if (endIndex != -1) {
                String temperatureString = weatherInfo.substring(startIndex, endIndex);
                temperature = Integer.parseInt(temperatureString.trim());
            }
        }

        return temperature;
    }

    private static String extractWeatherDescription(String weatherInfo) {
        String weatherDescription = "";

        int startIndex = weatherInfo.indexOf(":") + 2; // Skip the day and colon
        int endIndex = weatherInfo.indexOf(",", startIndex);
        if (endIndex != -1) {
            weatherDescription = weatherInfo.substring(startIndex, endIndex);
        }

        return weatherDescription.trim();
    }

    //========================================================================


}