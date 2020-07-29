package ir.b3stidea.peshgamanjazb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainPage extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        ConstraintLayout    cons_ur_= findViewById(R.id.ur);
        ConstraintLayout    cons_ul_= findViewById(R.id.ul);
        ConstraintLayout    cons_dr_= findViewById(R.id.dr);
        ConstraintLayout    cons_dl_= findViewById(R.id.dl);



        cons_ur_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(getApplicationContext(),"شکرگزاری",Toast.LENGTH_LONG).show();
            }
        });

        cons_ul_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(getApplicationContext(),"عشق",Toast.LENGTH_LONG).show();
            }
        });

        cons_dr_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(getApplicationContext(),"فایل ها",Toast.LENGTH_LONG).show();
            }
        });

        cons_dl_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(getApplicationContext(),"دره آموزشی",Toast.LENGTH_LONG).show();
              Intent I = new Intent(getApplicationContext(),CoursePageOne.class);
              startActivity(I);
            }
        });



    }
    //------------->Check User Logined
    private boolean CheckUserLogin() {
        if (SharedPref.read(SharedPref.KEY_NAME, null) == null) {
            return false;
        } else {
            return true;
        }
    }


    //-------------->  save information user in Preferences
    private void SavePreferences(JSONArray jsonArray) {
        try {
            JSONObject object = jsonArray.getJSONObject(0);
            SharedPref.write(SharedPref.KEY_MOBILE, object.getString("mobile"));
            SharedPref.write(SharedPref.KEY_NAME, object.getString("fname"));
            SharedPref.write(SharedPref.KEY_FAMILY, object.getString("lname"));
            SharedPref.write(SharedPref.KEY_IMAGE, object.getString("image"));
            SharedPref.write(SharedPref.KEY_WALLET, object.getString("wallet"));

            setInformation();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //--------------> set information information in ui
    private void setInformation() {
      //  txt_username.setText(SharedPref.read(SharedPref.KEY_NAME) + " " + SharedPref.read(SharedPref.KEY_FAMILY));
     //   txt_wallet.setText(SharedPref.read(SharedPref.KEY_WALLET) + " تومان");

   //     if (SharedPref.read(SharedPref.KEY_IMAGE).equals("")) {
    //        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.user);
      //      img_avatar.setImageBitmap(largeIcon);
      //      return;
     //   }

   //     if (!SharedPref.read(SharedPref.KEY_IMAGE).equals("null")) {
    //        Glide.with(this).load(SharedPref.read(SharedPref.KEY_IMAGE)).into(img_avatar);
   //         return;
   //     }


    }


    //--------------> set information information in ui in first time
    private void setFirstInformation() {
        //txt_username.setText("نام کاربری");
       // txt_wallet.setText("0  تومان");

       // Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.user);
      //  img_avatar.setImageBitmap(largeIcon);
    }


}
