package navneet.com.openweather.weather;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import navneet.com.openweather.R;
import navneet.com.openweather.api.pojo.FiveWeathers;
import navneet.com.openweather.api.pojo.Forecast;
import navneet.com.openweather.data.WeatherObject;
import navneet.com.openweather.util.Helper;


public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_CITY = "post_key";
    public static final String EXTRA_PRECIP = "author";
    public static final String EXTRA_DEGREE = "desc";
    public static final String EXTRA_WIND = "exp";
    public static final String EXTRA_HUMIDITY = "image";
   // public static final String EXTRA_URL = "url";
    private TextView textView,text,dat,share,author,more;
    private ImageView imageView;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RequestQueue queue;

   // private WebView webView;
    private Button button;
    private String mCity,desc,date,image,auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        textView=(TextView)findViewById(R.id.textView);
        author=(TextView)findViewById(R.id.textView8);
        text=(TextView)findViewById(R.id.textView2);
      //  more=(TextView)findViewById(R.id.textView9);

        queue = Volley.newRequestQueue(this);
        dat=(TextView)findViewById(R.id.textView4);
    //    webView=(WebView)findViewById(R.id.webView);
        imageView=(ImageView)findViewById(R.id.imageView1);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(DetailsActivity.this, 4);

        recyclerView = (RecyclerView)findViewById(R.id.weather_daily_list);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);


/*

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://newsapi.org"));
                startActivity(i);
            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent what = new Intent();
                what.setAction(Intent.ACTION_SEND);
                what.putExtra(Intent.EXTRA_TEXT, "\nEvent:" + mCity + "\nJob Description:" +desc+ "\nExperience:" + date + "\nCTC:INR 12L - 1NR36L");
                what.setType("text/plain");
 /
                startActivity(Intent.createChooser(what, "Share with"));
            }
        });
        */
       // read=getIntent().getStringExtra(EXTRA_URL);
        auth= getIntent().getStringExtra(EXTRA_PRECIP);
        mCity = getIntent().getStringExtra(EXTRA_CITY);
        desc = getIntent().getStringExtra(EXTRA_DEGREE);
        date = getIntent().getStringExtra(EXTRA_WIND);
        image = getIntent().getStringExtra(EXTRA_HUMIDITY);
        //Picasso.with(this).load(image).into(imageView);
        textView.setText(mCity);
        text.setText(desc);
        dat.setText(date);
        author.setText(auth);


        fiveDaysApiJsonObjectCall(mCity);
    }

    private void fiveDaysApiJsonObjectCall(String city){
        String apiUrl = "http://api.openweathermap.org/data/2.5/forecast?q="+city+ "&APPID="+ Helper.API_KEY+"&units=metric";
        final List<WeatherObject> daysOfTheWeek = new ArrayList<WeatherObject>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              //  Log.d(TAG, "Response 5 days" + response);
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                Forecast forecast = gson.fromJson(response, Forecast.class);
                if (null == forecast) {
                    Toast.makeText(getApplicationContext(), "Nothing was returned", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Response Good", Toast.LENGTH_LONG).show();

                    int[] everyday = new int[]{0,0,0,0,0,0,0};

                    List<FiveWeathers> weatherInfo = forecast.getList();
                    if(null != weatherInfo){
                        for(int i = 0; i < weatherInfo.size(); i++){
                            String time = weatherInfo.get(i).getDt_txt();
                            String shortDay = convertTimeToDay(time);
                            String temp = weatherInfo.get(i).getMain().getTemp();
                            String tempMin = weatherInfo.get(i).getMain().getTemp_min();

                            if(convertTimeToDay(time).equals("Mon") && everyday[0] < 1){
                                daysOfTheWeek.add(new WeatherObject(shortDay, R.drawable.small_weather_icon, temp, tempMin));
                                everyday[0] = 1;
                            }
                            if(convertTimeToDay(time).equals("Tue") && everyday[1] < 1){
                                daysOfTheWeek.add(new WeatherObject(shortDay, R.drawable.small_weather_icon, temp, tempMin));
                                everyday[1] = 1;
                            }
                            if(convertTimeToDay(time).equals("Wed") && everyday[2] < 1){
                                daysOfTheWeek.add(new WeatherObject(shortDay, R.drawable.small_weather_icon, temp, tempMin));
                                everyday[2] = 1;
                            }
                            if(convertTimeToDay(time).equals("Thu") && everyday[3] < 1){
                                daysOfTheWeek.add(new WeatherObject(shortDay, R.drawable.small_weather_icon, temp, tempMin));
                                everyday[3] = 1;
                            }
                            if(convertTimeToDay(time).equals("Fri") && everyday[4] < 1){
                                daysOfTheWeek.add(new WeatherObject(shortDay, R.drawable.small_weather_icon, temp, tempMin));
                                everyday[4] = 1;
                            }
                            if(convertTimeToDay(time).equals("Sat") && everyday[5] < 1){
                                daysOfTheWeek.add(new WeatherObject(shortDay, R.drawable.small_weather_icon, temp, tempMin));
                                everyday[5] = 1;
                            }
                            if(convertTimeToDay(time).equals("Sun") && everyday[6] < 1){
                                daysOfTheWeek.add(new WeatherObject(shortDay, R.drawable.small_weather_icon, temp, tempMin));
                                everyday[6] = 1;
                            }
                            recyclerViewAdapter = new RecyclerViewAdapter(DetailsActivity.this, daysOfTheWeek);
                            recyclerView.setAdapter(recyclerViewAdapter);
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              //  Log.d(TAG, "Error " + error.getMessage());
            }
        });
        queue.add(stringRequest);
    }

    private String convertTimeToDay(String time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:SSSS", Locale.getDefault());
        String days = "";
        try {
            Date date = format.parse(time);
            System.out.println("Our time " + date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            days = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault());
            System.out.println("Our time " + days);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }


}
