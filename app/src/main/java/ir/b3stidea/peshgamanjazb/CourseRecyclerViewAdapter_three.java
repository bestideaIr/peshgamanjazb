package ir.b3stidea.peshgamanjazb;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aroliant on 1/3/2018.
 */

public class CourseRecyclerViewAdapter_three extends RecyclerView.Adapter<CourseRecyclerViewAdapter_three.ViewHolder> {
    List<Integer> counter = new ArrayList<Integer>();
    List<CourseSubInfo_content> itemNameList = new ArrayList<CourseSubInfo_content>();
    Context context;
    Boolean sw=true;
    final StringBuffer buffer=new StringBuffer("http://sheca.ir/databasequery/pj/request.php");

    public CourseRecyclerViewAdapter_three(Context context, List<CourseSubInfo_content> courseSubInfo_content ) {
        this.itemNameList = courseSubInfo_content ;

        this.context = context;
        //Toast.makeText(context.getApplicationContext(), String.valueOf(itemNameList.size())  , Toast.LENGTH_SHORT).show();

        // Log.d("namelist", ostadlist.toString());
        for (int i = 0; i < itemNameList.size(); i++) {
            counter.add(0);
           // Toast.makeText(context.getApplicationContext(), itemNameList.get(i).lessonvoice, Toast.LENGTH_SHORT).show();
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView coursesubinfo_content_pdf_;
        TextView coursesubinfo_content_voice_;
        ConstraintLayout    cons_coursesubinfo_voice_;
        ConstraintLayout    cons_coursesubinfo_pdf_;


        public ViewHolder(View itemView) {
            super(itemView);
            coursesubinfo_content_pdf_ = itemView.findViewById(R.id.txt_pdf);
            coursesubinfo_content_voice_ = itemView.findViewById(R.id.txt_voice);
            cons_coursesubinfo_voice_ = itemView.findViewById(R.id.cons_coursesubinfo_voice);
            cons_coursesubinfo_pdf_=itemView.findViewById(R.id.cons_coursesubinfo_pdf);
        }
    }

    @Override
    public CourseRecyclerViewAdapter_three.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_expanded_three_layout, parent, false);

        CourseRecyclerViewAdapter_three.ViewHolder vh = new CourseRecyclerViewAdapter_three.ViewHolder(v);

        return vh;

    }

    @Override
    public void onBindViewHolder(final CourseRecyclerViewAdapter_three.ViewHolder holder, final int position) {

        holder.coursesubinfo_content_pdf_.setText(itemNameList.get(position).lessonpdf);
        holder.coursesubinfo_content_voice_.setText(itemNameList.get(position).lessonvoice);

        holder.cons_coursesubinfo_voice_.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Toast.makeText(context.getApplicationContext(), "سلام دوره"+String.valueOf(position).toString() , Toast.LENGTH_SHORT).show();
        Intent I = new Intent(context.getApplicationContext(),CoursePagePlay.class);
        // I.putExtra(“value1” , “This value for Next Activity”);
        //I.putExtra(“value2” , “This value for Next Activity”);
        context.startActivity(I)  ;
        }
        });
        holder.cons_coursesubinfo_pdf_.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Toast.makeText(context.getApplicationContext(), "سلام دوره"+String.valueOf(position).toString() , Toast.LENGTH_SHORT).show();
        Intent I = new Intent(context.getApplicationContext(),CoursePagePDF.class);
        // I.putExtra(“value1” , “This value for Next Activity”);
        //I.putExtra(“value2” , “This value for Next Activity”);
        context.startActivity(I)  ;
        }
        });


    }

    @Override
    public int getItemCount() {
        return itemNameList.size();
    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
            //Toast.makeText(context.getApplicationContext(), "Please wait, it may take a few minute...", Toast.LENGTH_SHORT).show();
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {

                //Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }










}
