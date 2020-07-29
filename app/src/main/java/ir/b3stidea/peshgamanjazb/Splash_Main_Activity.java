package ir.b3stidea.peshgamanjazb;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;


public class Splash_Main_Activity extends AppCompatActivity {
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_main);
        videoView = (VideoView) findViewById(R.id.videoView);
        String path = "android.resource://ir.b3stidea.peshgamanjazb/" + R.raw.splash;
        //Toast.makeText(getApplicationContext(),path,Toast.LENGTH_LONG).show();
        final Uri uri = Uri.parse(path);
        videoView.setBackgroundColor(Color.WHITE);
        videoView.postDelayed(new Runnable() {
            @Override
            public void run() {
                videoView.setVideoURI(uri);
            }
        }, 300);
        videoView.postDelayed(new Runnable() {
            @Override
            public void run() {
                videoView.setBackgroundColor(Color.TRANSPARENT);
            }
        }, 700);
        videoView.requestFocus();
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                videoView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(Splash_Main_Activity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 0);
            }
        });


    }
}
