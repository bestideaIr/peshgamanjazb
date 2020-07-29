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

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aroliant on 1/3/2018.
 */

public class CourseRecyclerViewAdapter_two extends RecyclerView.Adapter<CourseRecyclerViewAdapter_two.ViewHolder> {
    List<Integer> counter = new ArrayList<Integer>();
    List<CourseSubInfo> itemNameList = new ArrayList<CourseSubInfo>();
    Context context;
    Boolean sw=true;
    final StringBuffer buffer=new StringBuffer("http://sheca.ir/databasequery/pj/request.php");

    public CourseRecyclerViewAdapter_two(Context context, List<CourseInfo> courselist,int pointer) {
        this.itemNameList = courselist.get(pointer).arrayList;

        this.context = context;
        // Log.d("namelist", ostadlist.toString());
        for (int i = 0; i < itemNameList.size(); i++) {
            counter.add(0);
            //Toast.makeText(context.getApplicationContext(),itemNameList.get(0).Subcoursetitle, Toast.LENGTH_SHORT).show();
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView coursesubinfo_time_;
        TextView coursesubinfo_title_;
        ImageView coursesubinfo_imagurl_;
        ConstraintLayout    cons_coursesubinfo_;


        //  ImageButton dropBtn;
        RecyclerView cardRecyclerView;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            coursesubinfo_time_ = itemView.findViewById(R.id.txt_time);
            coursesubinfo_title_ = itemView.findViewById(R.id.txt_title);
            coursesubinfo_imagurl_ = itemView.findViewById(R.id.img_subcourse);
            cons_coursesubinfo_=itemView.findViewById(R.id.cons_coursesubinfo);
        }
    }

    @Override
    public CourseRecyclerViewAdapter_two.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_expanded_two_layout, parent, false);

        CourseRecyclerViewAdapter_two.ViewHolder vh = new CourseRecyclerViewAdapter_two.ViewHolder(v);

        return vh;

    }

    @Override
    public void onBindViewHolder(final CourseRecyclerViewAdapter_two.ViewHolder holder, final int position) {

        holder.coursesubinfo_title_.setText(itemNameList.get(position).Subcoursetitle);
        holder.coursesubinfo_time_.setText(itemNameList.get(position).subcoursetitle_en);
/*        String urlperson=itemNameList.get(position).imagelessonurl;
        // Image link from internet
        new CourseRecyclerViewAdapter_two.DownloadImageFromInternet(holder.coursesubinfo_imagurl_).execute(urlperson);
*/
        holder.cons_coursesubinfo_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context.getApplicationContext(), "سلام دوره"+String.valueOf(position).toString() , Toast.LENGTH_SHORT).show();
                Intent I = new Intent(context.getApplicationContext(),CoursePageThree.class);
                // I.putExtra(“value1” , “This value for Next Activity”);
                //I.putExtra(“value2” , “This value for Next Activity”);
                context.startActivity(I)  ;          }
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
