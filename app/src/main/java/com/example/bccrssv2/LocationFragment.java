package com.example.bccrssv2;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.StrictMode;

public class LocationFragment extends Fragment {
//==========================================

    private static List<WeatherContent> weatherList;
    //===============================
    Exception exception = null;
    ListView lvRss;
    String description;
    ArrayList<String>descriptionList = new ArrayList<>();
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

    //=================================================

ListView listView;
SearchView searchView;
ArrayAdapter <String> adapter;
String[] data = {"Location 1", "Location 2", "Location 3",  "Location 10","Location 20"};

public LocationFragment() {
        // Required empty public constructor
        }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Caller Thread Task");
         FetchRssDataTask fetchRssDataTask = new FetchRssDataTask();
       fetchRssDataTask.execute();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Caller Thread Task After Real Thread Call");

        //========================================

        //===============================================

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("On create view started");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        listView = view.findViewById(R.id.lvRss);

        //====================================================


        listView = view.findViewById(R.id.lvRss);

       try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
       // weatherList = new ArrayList<>();

        // Populate the weatherList with sample data
     //   weatherList.add(new WeatherContent("eee","2022-01-01", "Sunny", 20, 30, "sunny"));
    //    weatherList.add(new WeatherContent("ddd","2022-01-02", "Cloudy", 18, 25, "cloudy"));
     //  weatherList.add(new WeatherContent("eee","2022-01-03", "Rainy", 15, 22, "rainy"));

        // Create the custom adapter and set it on the ListView
        System.out.print("Taakuto Adapter");
        Weather2Adapter weatherAdapter = new Weather2Adapter(getActivity(),0, weatherList);
        listView.setAdapter(weatherAdapter);

        //==================================================


      // adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
        // Set the adapter on the ListView
      //  listView.setAdapter(adapter);
        return view;
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
    public static Map<String, String> extractTagsAndValues(String input) {
        // Define the regular expression pattern to match the tags and values
        System.out.println("Extract Tags and Values"+input);
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
    public InputStream getInputStream(URL url){
        try {
            //   URL url = new URL("https://www.bcc.com/rss");
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

    private class FetchRssDataTask extends AsyncTask<Integer, Void, List<WeatherContent>> {

        @Override
        protected List<WeatherContent> doInBackground(Integer... integers) {
            List<String> rssItems = new ArrayList<>();
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String[] locationIds = {"2648579", "2643743","287286","934154","1185241","5128581"};
            String[] locationsNames = {"Gaslow", "London","Oman","Mauritius","Bangladesh","New York"};
            Map<String,String> decsriptionMap = new HashMap<>();

              for(int i3= 0; i3 < locationIds.length;i3++){
                  try {
                      System.out.println(" Thread Task Try");
                      //   URL url = new URL("https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/"+selectedLocationId2);
                      URL url = new URL("https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/"+locationIds[i3]);
                      XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                      factory.setNamespaceAware(false);
                      XmlPullParser xpp = factory.newPullParser();
                      xpp.setInput(getInputStream(url), "UTF_8");

                      boolean insideItem = false;
                      boolean insideImage = false;  // Add a boolean to check if inside <image> tag
                      int eventType = xpp.getEventType();

                      ArrayList<String> imageTitles = new ArrayList<>();  // Declare ArrayList for image titles

                      //Thread.sleep(10_000_0);
                      while (eventType != XmlPullParser.END_DOCUMENT) {
                          if (eventType == XmlPullParser.START_TAG) {
                              if (xpp.getName().equalsIgnoreCase("item")) {
                                  insideItem = true;
                              } else if (xpp.getName().equalsIgnoreCase("title")) {
                                  if (insideItem) {
                                      //    setText(tvDailyTitle,xpp.nextText());
                                      //  System.out.println("location cards title"+xpp.nextText());
                                      titles.add(xpp.nextText());
                                  }
                              } else if (xpp.getName().equalsIgnoreCase("link")) {
                                  if (insideItem) {
                                      links.add(xpp.nextText());
                                      // System.out.println("location cards link"+xpp.nextText());
                                  }
                              }else if (xpp.getName().equalsIgnoreCase("dc:date")) {
                                  if (insideItem) {
                                      // setText(tvDailyDate,xpp.nextText());
                                      System.out.println("location cards date"+xpp.nextText());
                                  }
                              }
                              else if (xpp.getName().equalsIgnoreCase("description")) {
                                  if (insideItem) {
                                      // setText(tvDailyDate,xpp.nextText());
                                   //   System.out.println("location cards description"+xpp.nextText());
                                  //    description = xpp.nextText();
                                      descriptionList.add(xpp.nextText());
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
                      String image="rainy1";


                      // Create a list of WeatherContent objects
                      System.out.println("Before Loop");
                      System.out.println(titles);

                      ArrayList<String>tempList= new ArrayList<>();
                      //===================================================


                     System.out.println("Description List RAW"+descriptionList);
                      System.out.println("Description List RAW SIZE"+descriptionList.size());
                    /* for(String descriptionList:descriptionList)
                      {
                          System.out.println("Description List"+descriptionList);
                          decsriptionMap=extractTagsAndValues(descriptionList);
                          System.out.println("When did you started printing maps");
                          System.out.println(decsriptionMap);
                      }
                    */

                  /*    for (Map.Entry<String, String> entry : decsriptionMap.entrySet()) {
                          System.out.println("Key = " + entry.getKey() +
                                  ", Value = " + entry.getValue());
                          String c = entry.getKey();
                          if(entry.getKey().toLowerCase().contains("wind")){
                            //  wind.setText(entry.getKey());
                            //  windValue.setText(entry.getValue());
                          }
                          if (entry.getKey().toLowerCase().contains("speed")){
                             // wind_speed.setText(entry.getKey());
                            //  wind_speedValue.setText(entry.getValue());
                          }
                          if (entry.getKey().toLowerCase().contains("temp")){
                              tempList.add(entry.getKey()+""+entry.getValue());
                            //  temp.setText(entry.getKey());
                            //  tempValue.setText(entry.getValue());
                          }
                          if (entry.getKey().toLowerCase().contains("humidity")){
                            //  humidity.setText(entry.getKey());
                           //   humidityValue.setText(entry.getValue());
                          }
                          if (entry.getKey().toLowerCase().contains("pressure")){
                             // pressure.setText(entry.getKey());
                             // pressureValue.setText(entry.getValue());
                          }

                      }*/
                      //================================================



                    /*  System.out.println("Tryyyyyyy+tempList"+tempList);
                      for (int i = 0; i < titles.size(); i++) {

                           System.out.println("Description maone aya "+ extractTemperature(titles.get(i),
                                   "Minimum Temperature"));
                         weatherList.add(new WeatherContent(
                                  locationsNames[i],
                                  extractDate(titles.get(i)),
                                  extractWeatherDescription(titles.get(i)),
                                  extractTemperature(titles.get(i),
                                          "Minimum Temperature"),
                                  extractTemperature(titles.get(i),"Maximum Temperature"),
                                  image

                          ));*


                      }*/

                      //     weatherList.add(new WeatherContent("2024-04-28", "Sunny", 25, 30));
                      //  weatherList.add(new WeatherContent("2024-04-29", "Cloudy", 20, 26));
                  /*    Object x = weatherList;
                      System.out.println(" Thread Task Ends");
                      System.out.println(weatherList);
                      System.out.println("Image Titles2: " + imageTitles);
                      System.out.println("datex of weeks2 : " + titles2);
                      System.out.println("datex of weeks2 : " + date_of_week2);*/
                  } catch (MalformedURLException e) {
                      exception = e;
                  } catch (XmlPullParserException e) {
                      exception = e;
                  } catch (IOException e) {
                      exception = e;
                  }
              }

              System.out.println("qqqqqqqqq");
              System.out.println("qqqqqqqqq-Size"+descriptionList.size());
            System.out.println("qqqqqqqqq-List"+descriptionList.get(0));
            System.out.println("qqqqqqqqq-List"+descriptionList.get(1));
            System.out.println("qqqqqqqqq-List"+descriptionList.get(2));
            System.out.println("qqqqqqqqq-List"+descriptionList.get(3));
            System.out.println("qqqqqqqqq-List"+descriptionList.get(4));

            for(int i3= 0; i3 < locationIds.length-1;i3++){
             //   decsriptionMap=extractTagsAndValues(descriptionList.get(i3));
             /*   String[] keysArray = decsriptionMap.keySet().toArray(new String[i3]);

                String firstKey = keysArray[0];
                String firstValue = decsriptionMap.get(firstKey);

                String secondKey = keysArray[0];
                String secondValue = decsriptionMap.get(secondKey);

                System.out.println("First key: " + firstKey);
                System.out.println("First value: " + firstValue);*/
                weatherList.add(new WeatherContent(
                        locationsNames[i3],
                        separateStringByCommas(descriptionList.get(i3))[0],
                        separateStringByCommas(descriptionList.get(i3))[1],
                        separateStringByCommas(descriptionList.get(i3))[2],
                        3,
                        "w3"

                ));
            }

            return weatherList;
        }

        public  String[] separateStringByCommas(String input) {
            String[] separatedArray = input.split(",");
            for (int i = 0; i < separatedArray.length; i++) {
                separatedArray[i] = separatedArray[i].trim();
            }
            return separatedArray;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //   progressDialog.setMessage("Loading RSS Feed please wait ...");
            //   progressDialog.show();
        }



       /* @Override
        protected void onPostExecute(List<String> rssItems) {
            // Create the adapter and set it on the ListView
            RSSAdapter listAdapter = new RSSAdapter(getActivity(), rssItems);
            listView.setAdapter(listAdapter);
        }*/
    }
}