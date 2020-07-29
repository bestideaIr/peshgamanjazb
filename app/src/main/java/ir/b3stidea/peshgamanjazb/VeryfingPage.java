package ir.b3stidea.peshgamanjazb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.stfalcon.smsverifycatcher.OnSmsCatchListener;
import com.stfalcon.smsverifycatcher.SmsVerifyCatcher;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class VeryfingPage extends AppCompatActivity {
    private static final String TAG = "TAG";
    private SmsVerifyCatcher smsVerifyCatcher;
    private Boolean veryfingOk=false;
    private String str_url_requestsms = "http://sheca.ir/databasequery/pj/requestok.php";
    String mobile;
    String MANEUVER_ID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //-------------> set fonts
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("asset/IRANSansMobile_Bold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.veryfingpage);



        Intent intent = getIntent();
//get the attached bundle from the intent
        Bundle extras = intent.getExtras();
//Extracting the stored data from the bundle
         mobile = extras.getString("mobile");



        //------------->get layout login
        LayoutInflater inflater = LayoutInflater.from(this);
        View theInflatedView = inflater.inflate(R.layout.veryfingpage, null);


        //////
        //init views
        final com.goodiebag.pinview.Pinview pinview_ = findViewById(R.id.pinview);
        final Button btnVerify_ = (Button) findViewById(R.id.btnVerify);




        //init SmsVerifyCatcher
        smsVerifyCatcher = new SmsVerifyCatcher(this, new OnSmsCatchListener<String>() {
            @Override
            public void onSmsCatch(String message) {
                String code = parseCode(message);//Parse verification code
                pinview_.setValue(code);//set code in edit text
                //then you can send verification code to server

            }
        });

        //set phone number filter if needed
        //smsVerifyCatcher.setPhoneNumberFilter("777");
        //smsVerifyCatcher.setFilter("regexp");

        //button for sending verification code manual

        btnVerify_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send verification code to server


                String code=pinview_.getValue();
                Log.d(TAG,mobile+"   ::    "+code);
                callvolly(mobile,code);



            }
        });
    }




    public void callvolly(final String usermobile,final String smscode){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        final String url =str_url_requestsms; // <----enter your post url here
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                //Toast.makeText(getApplicationContext(),response , Toast.LENGTH_SHORT).show();
                String u = response;
                String p = "ok";
                if( u.equals( p ) ) {
                    Intent intent = new Intent(VeryfingPage.this, MainPage.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), "دوباره امتحان کنید", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                Toast.makeText(getApplicationContext(), "با مشکل مواجه شد!", Toast.LENGTH_SHORT).show();

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("mobile_no", usermobile);
                MyData.put("code", smscode);

                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);
    }

    /**
     * Parse verification code
     *
     * @param message sms message
     * @return only four numbers from massage string
     */
    private String parseCode(String message) {
        Pattern p = Pattern.compile("\\b\\d{4}\\b");
        Matcher m = p.matcher(message);
        String code = "";
        while (m.find()) {
            code = m.group(0);
        }
        return code;
    }

    @Override
    protected void onStart() {
        super.onStart();
        smsVerifyCatcher.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        smsVerifyCatcher.onStop();
    }

    /**
     * need for Android 6 real time permissions
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        smsVerifyCatcher.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
