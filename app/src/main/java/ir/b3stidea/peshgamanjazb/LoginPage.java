package ir.b3stidea.peshgamanjazb;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class LoginPage extends AppCompatActivity {
    String TAG;
    String  strmobile;
    Button      btn_go_;
    EditText    txt_mobilenumber_;
    Boolean     sensmsok=false;
    private String str_url_requestsms = "http://sheca.ir/databasequery/pj/sendsms.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //-------------> set fonts


        setContentView(R.layout.loginpage);



        //------------->get layout login
        LayoutInflater inflater = LayoutInflater.from(this);
        View theInflatedView = inflater.inflate(R.layout.loginpage, null);





        btn_go_ = (Button) findViewById(R.id.btn_go);
        txt_mobilenumber_=(EditText)findViewById(R.id.txt_mobilenumber) ;

        btn_go_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strmobiletemp=txt_mobilenumber_.getText().toString();
                if(strmobiletemp.length()>9 && strmobiletemp!=null) {
                    if(strmobiletemp.charAt(0)!='0'){
                        Log.d(TAG,String.valueOf(strmobiletemp.charAt(0)));
                        strmobile = "0"+strmobiletemp ;
                    }
                    else{
                        strmobile = strmobiletemp ;

                    }
                    callvolly(strmobile);
                    //create a Bundle object
                    Bundle extras = new Bundle();
                    //Adding key value pairs to this bundle
                    //there are quite a lot data types you can store in a bundle
                    extras.putString("mobile", strmobile);
                    Intent intent = new Intent(LoginPage.this, VeryfingPage.class);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "شماره همراه خود را وارد کنید!", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
    public void callvolly(final String usermobile){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        final String url =str_url_requestsms; // <----enter your post url here
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
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
                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);
    }
}
