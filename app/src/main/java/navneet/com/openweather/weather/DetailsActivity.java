package navneet.com.openweather.weather;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import navneet.com.openweather.R;


public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_CITY = "post_key";
    public static final String EXTRA_PRECIP = "author";
    public static final String EXTRA_DEGREE = "desc";
    public static final String EXTRA_WIND = "exp";
    public static final String EXTRA_HUMIDITY = "image";
   // public static final String EXTRA_URL = "url";
    private TextView textView,text,dat,share,author,more;
    private ImageView imageView;

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
        more=(TextView)findViewById(R.id.textView9);
        share=(TextView)findViewById(R.id.textView7);
        button=(Button)findViewById(R.id.button);
        dat=(TextView)findViewById(R.id.textView4);
    //    webView=(WebView)findViewById(R.id.webView);
        imageView=(ImageView)findViewById(R.id.imageView1);






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
 /*               what.putExtra(Intent.EXTRA_STREAM, uri);
                what.setType("image/jpeg");
                */
                startActivity(Intent.createChooser(what, "Share with"));
            }
        });
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



    }


}
