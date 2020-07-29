package ir.b3stidea.peshgamanjazb;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CoursePageFoure extends AppCompatActivity {
    String[] day = new String[31];
    RecyclerView morningRecyclerView;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courspagefour);
        day[0]="اول";
        day[1]="دوم";
        day[2]="سوم";
        day[3]="چهارم";
        day[4]="پنچم";
        day[5]="ششم";
        day[6]="هفتم";
        day[7]="هشتم";
        day[8]="نهم";
        day[9]="دهم";
        day[10]="یازدهم";
        day[11]="دوازدهم";
        day[12]="سیزدهم";
        day[13]="چهاردهم";
        day[14]="پانزدهم";
        //morningRecyclerView = findViewById(R.id.recyclerView_foure);
      // initiateExpander();
        Toast.makeText(getApplicationContext(), "صفحه چهارم", Toast.LENGTH_SHORT).show();


    }
    private void initiateExpander() {
        List<CourseSubInfo_content > courseInfos_content_all=new ArrayList<CourseSubInfo_content>();
        CourseSubInfo_content courseSubInfo_content =new CourseSubInfo_content();
        courseSubInfo_content.lessontitle="آهنربای ثروت     ـ  ";
        courseSubInfo_content.lessonpdf="سرفصل فدراسيون جمهوري اسلامي ايران";
        courseSubInfo_content.lessonvoice="هفته o,f اول" ;
        courseInfos_content_all.add(courseSubInfo_content);
        /////////////////////////
        courseSubInfo_content = new CourseSubInfo_content();
        courseSubInfo_content.lessontitle="آهنربای ثروت     ـ  ";
        courseSubInfo_content.lessonpdf="سرفصل فدراسيون جمهوري اسلامي ايران";
        courseSubInfo_content.lessonvoice="هفته خوب دوم" ;
        /////////////////////////
        courseInfos_content_all.add(courseSubInfo_content);

        ///////////////////////////////////////////////////////
        CourseRecyclerViewAdapter_three courseRecyclerViewAdapter_three =  new CourseRecyclerViewAdapter_three(getApplicationContext(), courseInfos_content_all);
        morningRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        morningRecyclerView.setAdapter(courseRecyclerViewAdapter_three);


        //ResposofPayment_SW =false;
    }

}
