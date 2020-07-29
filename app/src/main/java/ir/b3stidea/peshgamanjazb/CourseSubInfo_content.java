package ir.b3stidea.peshgamanjazb;

public class CourseSubInfo_content {
     String lessontitle;
     String lessonvoice;
     String lessonpdf;

    public CourseSubInfo_content(String lessontitle, String lessonvoice, String lessonpdf) {
        this.lessontitle = lessontitle;
        this.lessonvoice = lessonvoice;
        this.lessonpdf = lessonpdf;
    }

    public CourseSubInfo_content() {
    }

    public String getLessontitle() {
        return lessontitle;
    }

    public void setLessontitle(String lessontitle) {
        this.lessontitle = lessontitle;
    }

    public String getLessonvoice() {
        return lessonvoice;
    }

    public void setLessonvoice(String lessonvoice) {
        this.lessonvoice = lessonvoice;
    }

    public String getLessonpdf() {
        return lessonpdf;
    }

    public void setLessonpdf(String lessonpdf) {
        this.lessonpdf = lessonpdf;
    }
}
