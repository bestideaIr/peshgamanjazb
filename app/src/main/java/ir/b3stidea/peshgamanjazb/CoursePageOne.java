package ir.b3stidea.peshgamanjazb;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CoursePageOne extends AppCompatActivity {
    String[] day = new String[31];
    RecyclerView morningRecyclerView;
    Context context;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courspageone);
        context=CoursePageOne.this;
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
        morningRecyclerView = findViewById(R.id.recyclerView_main);
        initiateExpander();

    }
    private void initiateExpander() {
        List<CourseInfo > courseInfos_all=new ArrayList<CourseInfo>();
        CourseInfo courseInfo =new CourseInfo();
        courseInfo.course_name="هزینه دوره";
        courseInfo.course_title="سرفصل فدراسيون جمهوري اسلامي ايران";
        courseInfo.course_rotbeh="149000" ;
        courseInfo.course_imagurl="http://sheca.ir/databasequery/bsheca/images/jab00.png";
        courseInfo.setPay("true");
        courseInfos_all.add(courseInfo);
        /////////////////////////
        courseInfo = new CourseInfo();
        courseInfo.course_name="هزینه دوره";
        courseInfo.course_title="سرفصل فدراسيون جمهوري اسلامي ايران";
        courseInfo.course_rotbeh="149000" ;
        courseInfo.course_imagurl="http://sheca.ir/databasequery/bsheca/images/jazb00.png";
        courseInfo.setPay("false");
        courseInfos_all.add(courseInfo);
        /////////////////////////
        courseInfo = new CourseInfo();
        courseInfo.course_name="هزینه دوره";
        courseInfo.course_title="سرفصل فدراسيون جمهوري اسلامي ايران";
        courseInfo.course_rotbeh="149000 " ;
        courseInfo.course_imagurl="http://sheca.ir/databasequery/bsheca/images/jazb00.png";
        courseInfo.setPay("false");
        courseInfos_all.add(courseInfo);
        /////////////////////////
        courseInfo = new CourseInfo();
        courseInfo.course_name="هزینه دوره";
        courseInfo.course_title="سرفصل فدراسيون جمهوري اسلامي ايران";
        courseInfo.course_rotbeh="149000" ;
        courseInfo.course_imagurl="http://sheca.ir/databasequery/bsheca/images/jazb00.png";
        courseInfo.setPay("false");
        courseInfos_all.add(courseInfo);
//Course sub Info
        //////////////////////////////////////////
        ///////////////////////////////////////////
        CourseSubInfo lessoninfo =new CourseSubInfo(""," عنوان درس :اول","http://sheca.ir/databasequery/bsheca/images/day1.png");
        /////////////////////سطح مقدماتی///////////////////////
    /*    lessoninfo.Subcoursetitle="";
        lessoninfo.subcoursetitle_en=" عنوان درس :اول";
        Uri uri = Uri.parse("http://arzcoin.ir/databasequery/sheca/day1.png");
        lessoninfo.imagelessonurl=uri.toString();
        lessoninfo_all.add(lessoninfo);
*/
        ///////////////////////////////////////////////////////
        CourseSubInfo lessoninfo1 =new CourseSubInfo(""," عنوان درس :دوم","http://sheca.ir/databasequery/bsheca/images/day1.png");
/*
        lessoninfo1.Subcoursetitle="";
        lessoninfo1.subcoursetitle_en=" عنوان درس :دوم";
        uri = Uri.parse("http://arzcoin.ir/databasequery/sheca/day1.png");
        lessoninfo1.imagelessonurl=uri.toString();
        lessoninfo_all.add(lessoninfo1);
        */
        ///////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////
        CourseSubInfo lessoninfo2 =new CourseSubInfo(""," عنوان درس :سوم","http://sheca.ir/databasequery/bsheca/images/day1.png");
/*
        lessoninfo2.Subcoursetitle="";
        lessoninfo2.subcoursetitle_en=" عنوان درس :سوم";
        uri = Uri.parse("http://arzcoin.ir/databasequery/sheca/day1.png");
        lessoninfo2.imagelessonurl=uri.toString();
        lessoninfo_all.add(lessoninfo2);
        */
        List<CourseSubInfo> lessoninfo_all = new ArrayList<CourseSubInfo>();
        lessoninfo_all.add(lessoninfo);
        ///////////////////////////////////////////////////////
        courseInfos_all.get(0).setArrayList(lessoninfo_all);
        lessoninfo_all = new ArrayList<CourseSubInfo>();
        lessoninfo_all.add(lessoninfo);
        lessoninfo_all.add(lessoninfo1);
        courseInfos_all.get(1).setArrayList(lessoninfo_all);
        lessoninfo_all = new ArrayList<CourseSubInfo>();
        lessoninfo_all.add(lessoninfo);
        lessoninfo_all.add(lessoninfo1);
        lessoninfo_all.add(lessoninfo2);
        courseInfos_all.get(2).setArrayList(lessoninfo_all);
        lessoninfo_all = new ArrayList<CourseSubInfo>();
        lessoninfo_all.add(lessoninfo);
        lessoninfo_all.add(lessoninfo1);
        courseInfos_all.get(3).setArrayList(lessoninfo_all);
        ///////////////////////////////////////////////////////
        CourseRecyclerViewAdapter_one courseRecyclerViewAdapterOne =  new CourseRecyclerViewAdapter_one(context, courseInfos_all);
        morningRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        morningRecyclerView.setAdapter(courseRecyclerViewAdapterOne);


        //ResposofPayment_SW =false;
    }

}
