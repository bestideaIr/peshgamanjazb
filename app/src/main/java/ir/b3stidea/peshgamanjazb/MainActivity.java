package ir.b3stidea.peshgamanjazb;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class MainActivity extends AppCompatActivity {
    Button btn_start_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //-------------> set fonts
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("asset/IRANSansMobile.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.activity_main);
        //------------->get layout login
        LayoutInflater inflater = LayoutInflater.from(this);
        View theInflatedView = inflater.inflate(R.layout.activity_main, null);



        btn_start_ = (Button) findViewById(R.id.btn_start);


    btn_start_.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, LoginPage.class);
        startActivity(intent);
    }
});



   /*     Pinview pinview1 = findViewById(R.id.pinview);
        pinview1.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean fromUser) {
                Toast.makeText(MainActivity.this, pinview.getValue(), Toast.LENGTH_SHORT).show();
            }
        });


        // pinView Customize
        Pinview pinview5 = findViewById(R.id.pinview);
      //  pinview5.setCursorShape(R.drawable.example_cursor);
//        pinview5.setCursorColor(Color.BLUE);
        pinview5.setTextSize(12);
        pinview1.setValue("4352");
        pinview5.setTextColor(Color.BLACK);
        pinview5.showCursor(true);

        */
    }

}
