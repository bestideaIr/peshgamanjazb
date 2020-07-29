package ir.b3stidea.peshgamanjazb;


import android.Manifest;
import android.app.MediaRouteButton;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.chibde.visualizer.CircleBarVisualizer;
import com.downloader.Error;
import com.downloader.utils.Utils;
import com.warkiz.tickseekbar.OnSeekChangeListener;
import com.warkiz.tickseekbar.SeekParams;
import com.warkiz.tickseekbar.TextPosition;
import com.warkiz.tickseekbar.TickMarkType;
import com.warkiz.tickseekbar.TickSeekBar;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;


public class CoursePagePlay extends AppCompatActivity {
    private static final String TAG = "TAG";
    String[] day = new String[31];
    ImageView btn_mplay_,btn_mpause_,btn_backwared_,btn_forwared_;
    TextView    txt_total_,txt_current_;
    Boolean     play_pause=true;
    public boolean is_now_download = false;
    B_DownloadManager downloadManager;
    static String str_url = "http://www.sheca.ir/databasequery/pj/voice/hozoornew.mp3";
    public ProgressBar  progressbar = null;
    MediaPlayer mp = new MediaPlayer();
    private int seekForwardTime = 15 * 1000; // default 5 second
    private int seekBackwardTime = 15 * 1000; // default 5 second
    public CountDownTimer ctimer = null;
    public TickSeekBar  customSeekBar;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1000);
        }
        btn_mplay_=findViewById(R.id.imageView14) ;
        btn_mpause_=findViewById(R.id.imageView19) ;
        progressbar =  findViewById(R.id.progressbar);
        btn_backwared_=findViewById(R.id.img_backwared) ;
        btn_forwared_=findViewById(R.id.img_forewared) ;
        txt_total_=findViewById(R.id.txt_total);
        txt_current_=findViewById(R.id.txt_current);
        btn_mplay_.setVisibility(View.VISIBLE);
        btn_mpause_.setVisibility(View.GONE);









        File external_m1 =  Environment.getExternalStorageDirectory();
        Log.e(TAG,external_m1.getPath());


        download_file(true);






        try {
            String audioFilePath = Environment.getExternalStorageDirectory().getPath() + "/peshgamanjazb/" + str_url.substring(str_url.lastIndexOf('/') + 1);
            mp.setDataSource(audioFilePath);
            Log.e(TAG,audioFilePath);
            Toast.makeText(getApplicationContext(),"yes",Toast.LENGTH_SHORT).show();
            mp.prepare();
            progressbar.setVisibility(View.GONE);
            progressbar.setProgress(0);
        } catch (IOException e) {
            Log.e(TAG, e.toString());

        }








        btn_mplay_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!is_now_download) {
                    if (play_pause) {
                        play_pause = false;
                        btn_mplay_.setVisibility(View.GONE);
                        btn_mpause_.setVisibility(View.VISIBLE);
                        mp.start();
                        progressbar.setVisibility(View.GONE);
                        Log.e(TAG,"hi1");
                    }
                }
            }
        });

        btn_mpause_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!is_now_download) {
                    if (play_pause) {
                        play_pause = false;
                        mp.start();
                        progressbar.setVisibility(View.GONE);
                        btn_mpause_.setVisibility(View.GONE);
                        btn_mplay_.setVisibility(View.VISIBLE);
                        Log.e(TAG,"hi2");

                    } else {
                        play_pause = true;
                        mp.pause();
                        btn_mpause_.setVisibility(View.GONE);
                        btn_mplay_.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        btn_backwared_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rewindSong(mp);
            }
        });
        btn_forwared_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forwardSong(mp);
            }
        });



       // seekbar_.setMax(mp.getDuration());
        String timeDuration=String.valueOf(mp.getDuration()/1000);
        int value = Integer.parseInt(timeDuration);
        int reminder=mod(value,60);
        int div=(value+reminder)/60;
        timeDuration=String.valueOf(div)+":"+String.valueOf(reminder);
        Log.e(TAG,timeDuration);

       txt_total_.setText(timeDuration);





        customSeekBar = (TickSeekBar) findViewById(R.id.listener);
        customSeekBar.setMax(mp.getDuration());
        customSeekBar.setMin(0);
        final Handler mHandler = new Handler();
        CoursePagePlay.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mp != null) {
                    customSeekBar.setProgress(mp.getCurrentPosition()/1000);
                    String timeDuration=String.valueOf(mp.getCurrentPosition()/1000);
                    timeDuration=CurrentTime(timeDuration);
                    txt_current_.setText(timeDuration);
                }
                mHandler.postDelayed(this, 1000);
            }
        });

        customSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                Log.i(TAG,"onSeeking");
                Log.i(TAG, String.valueOf(seekParams.progress));
            }

            @Override
            public void onStartTrackingTouch(TickSeekBar seekBar) {
                Log.i(TAG,"onStartTrackingTouch");

            }

            @Override
            public void onStopTrackingTouch(TickSeekBar seekBar) {
                Log.i(TAG,"onStopTrackingTouch");
                if(mp != null ){
                    mp.seekTo(seekBar.getProgress()/1000); // clear ' * 1000  '
                }
            }


        });


















    }





    private String CurrentTime(String timeDuration){

        int value = Integer.parseInt(timeDuration);
        int reminder=mod(value,60);
        int div=(value+reminder)/60;
        timeDuration=String.valueOf(div)+":"+String.valueOf(reminder);
        return timeDuration;
    }



    private int mod(int x, int y)
    {
        int result = x % y;
        if (result < 0)
            result += y;
        return result;
    }


    public void forwardSong(MediaPlayer mPlayer) {
        if (mPlayer != null) {
            int currentPosition = mPlayer.getCurrentPosition();
            if (currentPosition + seekForwardTime <= mPlayer.getDuration()) {
                mPlayer.seekTo(currentPosition + seekForwardTime);
            } else {
                mPlayer.seekTo(mPlayer.getDuration());
            }
        }
    }
    public void rewindSong(MediaPlayer mPlayer) {
        if (mPlayer != null) {
            int currentPosition = mPlayer.getCurrentPosition();
            if (currentPosition - seekBackwardTime >= 0) {
                mPlayer.seekTo(currentPosition - seekBackwardTime);
            } else {
                mPlayer.seekTo(0);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1000:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(),"Permisson granted!",Toast.LENGTH_SHORT).show();
                }
        }
    }
////////////////////////////////////////////
void downloadFile(String dwnload_file_path, String fileName, String pathToSave) {
    int downloadedSize = 0;
    int totalSize = 0;

    try {
        URL url = new URL(dwnload_file_path);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        Log.e(TAG,"1");

        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);
        Log.e(TAG,"2");

        try {
            urlConnection.connect();
        }
        catch (Exception e){
            Log.e(TAG,"3" +e.toString());

        }
        // connect

        File myDir;
        myDir = new File(pathToSave);
        myDir.mkdirs();
        Log.e(TAG,"4");

        Toast.makeText(this.getApplicationContext(),myDir.getPath(),Toast.LENGTH_SHORT).show();
        // create a new file, to save the downloaded file

        String mFileName = fileName;
        File file = new File(myDir, mFileName);

        FileOutputStream fileOutput = new FileOutputStream(file);

        // Stream used for reading the data from the internet
        InputStream inputStream = urlConnection.getInputStream();

        // this is the total size of the file which we are downloading
        totalSize = urlConnection.getContentLength();

        // runOnUiThread(new Runnable() {
        // public void run() {
        // pb.setMax(totalSize);
        // }
        // });

        // create a buffer...
        byte[] buffer = new byte[1024];
        int bufferLength = 0;

        while ((bufferLength = inputStream.read(buffer)) > 0) {
            fileOutput.write(buffer, 0, bufferLength);
            downloadedSize += bufferLength;
            // update the progressbar //
            // runOnUiThread(new Runnable() {
            // public void run() {
            // pb.setProgress(downloadedSize);
            // float per = ((float)downloadedSize/totalSize) * 100;
            // cur_val.setText("Downloaded " + downloadedSize + "KB / " +
            // totalSize + "KB (" + (int)per + "%)" );
            // }
            // });
        }
        // close the output stream when complete //
        fileOutput.close();
        // runOnUiThread(new Runnable() {
        // public void run() {
        // // pb.dismiss(); // if you want close it..
        // }
        // });

    } catch (final MalformedURLException e) {
        // showError("Error : MalformedURLException " + e);
        e.printStackTrace();
    } catch (final IOException e) {
        // showError("Error : IOException " + e);
        e.printStackTrace();
    } catch (final Exception e) {
        // showError("Error : Please check your internet connection " + e);
    }
}
//////////////////////////


    private void Download_Canele() {
        downloadManager.download_cancel();
        progressbar.setProgress(0);
        is_now_download = false;
    }


    private void download_file(final boolean bol_share) {
        if (is_now_download) {
            Download_Canele();
            return;
        }
        try {

            File fileWithinMyDir = new File(Environment.getExternalStorageDirectory().getPath() + "/peshgamanjazb/" + str_url.substring(str_url.lastIndexOf('/') + 1));

            if (!fileWithinMyDir.exists()) {
                File dir = new File(Environment.getExternalStorageDirectory(), "peshgamanjazb");
                dir.mkdirs();

                is_now_download = true;
                if (bol_share) {
                    Toast.makeText(CoursePagePlay.this, "برای اشتراک گذاری ابتدا باید فایل دانلود شود !", Toast.LENGTH_SHORT).show();
                }
                downloadManager = new B_DownloadManager(CoursePagePlay.this, false);
                downloadManager.setPath(Environment.getExternalStorageDirectory().getPath() + "/peshgamanjazb/");
                downloadManager.setDownload(str_url);
                downloadManager.setOnErrorListner(new B_DownloadManager.OnErrorListner() {
                    @Override
                    public void onError(Error error) {
                        is_now_download = false;
                        Toast.makeText(getApplicationContext(), "دانلود با مشکل روبرو شد ! ", Toast.LENGTH_SHORT).show();
                    }
                });
                downloadManager.setOnCompleteListner(new B_DownloadManager.OnCompleteListner() {
                    @Override
                    public void onComplete() {
                        is_now_download = false;
                        if (bol_share) {
                            share_file();
                        }
                    }
                });
                downloadManager.setOnDownloadProgressListener(new B_DownloadManager.OnDownloadProgressListener() {
                    @Override
                    public void onProgress(int progress) {
                        progressbar.setProgress(progress);
                       // txt_dwonload_progress.setText(convertNumbersToPersian(String.valueOf(progress)) + "%");
                    }
                });
                downloadManager.start();
            } else {
                Toast.makeText(CoursePagePlay.this, "قبلا دانلود شده است", Toast.LENGTH_SHORT).show();
                if (bol_share == false) {
                    Toast.makeText(CoursePagePlay.this, "قبلا دانلود شده است ", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {

            if (bol_share == false) {
                Toast.makeText(CoursePagePlay.this, "قبلا دانلود شده است ", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void share_file() {
        Intent intentShareFile = new Intent(Intent.ACTION_SEND);
        File fileWithinMyDir = new File(Environment.getExternalStorageDirectory().getPath() + "/peshgamanjazb/" + str_url.substring(str_url.lastIndexOf('/') + 1));

        if (fileWithinMyDir.exists()) {

           // intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + Environment.getExternalStorageDirectory().getPath() + "/peshgamanjazb/" + str_url.substring(str_url.lastIndexOf('/') + 1)));
            //intentShareFile.putExtra(Intent.EXTRA_SUBJECT, "");
           // intentShareFile.putExtra(Intent.EXTRA_TEXT, "");
           // startActivity(Intent.createChooser(intentShareFile, "ارسال فایل به"));
        }
    }





}
