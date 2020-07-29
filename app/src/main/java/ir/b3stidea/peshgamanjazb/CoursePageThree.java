package ir.b3stidea.peshgamanjazb;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CoursePageThree extends AppCompatActivity {
    String[] day = new String[31];
    RecyclerView morningRecyclerView;
    Context context;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courspagethree);
        context=CoursePageThree.this;

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
        morningRecyclerView = findViewById(R.id.recyclerView_three);
       // Toast.makeText(context.getApplicationContext(), "صفحه سوم", Toast.LENGTH_SHORT).show();

        initiateExpander();

    }
    private void initiateExpander() {
        List<CourseSubInfo_content > courseInfos_content_all=new ArrayList<CourseSubInfo_content>();
        CourseSubInfo_content courseSubInfo_content =new CourseSubInfo_content();
        courseSubInfo_content.lessontitle="آهنربای ثروت     ـ  ";
        courseSubInfo_content.lessonpdf="سرفصل فدراسيون جمهوري اسلامي ايران";
        courseSubInfo_content.lessonvoice="هفته اول" ;
        courseInfos_content_all.add(courseSubInfo_content);
        ///////////////////////////////////////////////////////
        CourseRecyclerViewAdapter_three courseRecyclerViewAdapter_three =  new CourseRecyclerViewAdapter_three(context, courseInfos_content_all);
        morningRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        morningRecyclerView.setAdapter(courseRecyclerViewAdapter_three);


        //ResposofPayment_SW =false;
    }

}
