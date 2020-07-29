package ir.b3stidea.peshgamanjazb;

public class CourseSubInfo {
     String Subcoursetitle;
     String subcoursetitle_en;
     String imagelessonurl;

    public CourseSubInfo(String subcoursetitle, String subcoursetitle_en, String imagelessonurl) {
        Subcoursetitle = subcoursetitle;
        this.subcoursetitle_en = subcoursetitle_en;
        this.imagelessonurl = imagelessonurl;
    }

    public CourseSubInfo() {
    }

    public CourseSubInfo(CourseSubInfo  courseSubInfo) {
        this.Subcoursetitle = courseSubInfo.Subcoursetitle;
        this.subcoursetitle_en = courseSubInfo.subcoursetitle_en;
        this.imagelessonurl = courseSubInfo.imagelessonurl;

    }

    public String getSubcoursetitle() {
        return Subcoursetitle;
    }

    public void setSubcoursetitle(String subcoursetitle) {
        Subcoursetitle = subcoursetitle;
    }

    public String getSubcoursetitle_en() {
        return subcoursetitle_en;
    }

    public void setSubcoursetitle_en(String subcoursetitle_en) {
        this.subcoursetitle_en = subcoursetitle_en;
    }

    public String getImagelessonurl() {
        return imagelessonurl;
    }

    public void setImagelessonurl(String imagelessonurl) {
        this.imagelessonurl = imagelessonurl;
    }
}
