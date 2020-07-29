package ir.b3stidea.peshgamanjazb;

import java.util.List;

public class CourseInfo {
    String course_name;
    String course_title;
    String course_rotbeh;
    String course_imagurl;
    String Pay;
    int     numberoflist;
    List<CourseSubInfo> arrayList;
    List<CourseSubInfo> CourseSubInfolist;
    public int getNumberoflist() {
        return numberoflist;
    }

    public void setNumberoflist(int numberoflist) {
        this.numberoflist = getArrayList().size();
    }

    public CourseInfo() {
    }


    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public String getCourse_rotbeh() {
        return course_rotbeh;
    }

    public void setCourse_rotbeh(String course_rotbeh) {
        this.course_rotbeh = course_rotbeh;
    }

    public String getCourse_imagurl() {
        return course_imagurl;
    }

    public void setCourse_imagurl(String course_imagurl) {
        this.course_imagurl = course_imagurl;
    }

    public String getPay() {
        return Pay;
    }

    public void setPay(String pay) {
        Pay = pay;
    }

    public List<CourseSubInfo> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<CourseSubInfo> arrayList) {
        this.arrayList = arrayList;
    }

    public List<CourseSubInfo> getCourseSubInfolist() {
        return CourseSubInfolist;
    }

    public void setCourseSubInfolist(List<CourseSubInfo> courseSubInfolist) {
        CourseSubInfolist = courseSubInfolist;
    }
}
