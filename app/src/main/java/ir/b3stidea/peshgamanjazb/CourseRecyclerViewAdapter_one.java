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
import androidx.recyclerview.widget.RecyclerView;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Aroliant on 1/3/2018.
 */

public class CourseRecyclerViewAdapter_one extends RecyclerView.Adapter<CourseRecyclerViewAdapter_one.ViewHolder> {

    List<CourseInfo> CourseList = new ArrayList<CourseInfo>();
    List<String> image = new ArrayList<String>();
    List<Integer> counter = new ArrayList<Integer>();
    List<CourseSubInfo> itemNameList = new ArrayList<CourseSubInfo>();
    Context context;
    Boolean sw=true;
    final StringBuffer buffer=new StringBuffer("http://sheca.ir/databasequery/pj/request.php");

    public CourseRecyclerViewAdapter_one(Context context, List<CourseInfo> courselist) {
        this.CourseList = courselist;
        this.context = context;
        // Log.d("namelist", ostadlist.toString());
        for (int i = 0; i < courselist.size(); i++) {
            counter.add(0);
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView course_name_;
        TextView course_title_;
        TextView course_rotbeh_;
        ImageView course_imagurl_;
        ImageView btn_kharid_;
        ImageView btn_kharid_ok_;


        //  ImageButton dropBtn;
        RecyclerView cardRecyclerView;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            course_name_ = itemView.findViewById(R.id.textViewName);
            course_title_ = itemView.findViewById(R.id.textViewVersion);
            course_rotbeh_ = itemView.findViewById(R.id.textViewVersion);
            course_imagurl_ = itemView.findViewById(R.id.imageView7);
            btn_kharid_ = itemView.findViewById(R.id.img_kharid);
            btn_kharid_ok_ = itemView.findViewById(R.id.img_ok);

            //   dropBtn = itemView.findViewById(R.id.recyclerView);
          //  cardRecyclerView = itemView.findViewById(R.id.recyclerView_child_);
          //  cardView = itemView.findViewById(R.id.course_card_view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_expanded_one_layout, parent, false);

        CourseRecyclerViewAdapter_one.ViewHolder vh = new CourseRecyclerViewAdapter_one.ViewHolder(v);

        return vh;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Boolean b = Boolean.valueOf(CourseList.get(position).Pay);


        buffer.append("?MerchantID="+ "0c620eac-b325-11e8-a360-005056a205be");
        buffer.append("&Amount="+"100");
        buffer.append("&Description="+"پیشگامان");
        buffer.append("&CallbackURL="+" http://sheca.ir/databasequery/pj/verify.php");
        buffer.append("&Email="+"hi@gmail.com");
        buffer.append("&Mobile="+"09111434434");
        //final ArrayList<CourseSubInfo>  singleItem=CourseList.get(position).getArrayList();

        List<CourseSubInfo > singleItem;
        singleItem=CourseList.get(position).getArrayList();

        holder.course_name_.setText(CourseList.get(position).course_name);
        holder.course_title_.setText(CourseList.get(position).course_title);
        holder.course_rotbeh_.setText(CourseList.get(position).course_rotbeh);
        String urlperson=CourseList.get(position).course_imagurl;
        // Image link from internet
        new DownloadImageFromInternet(holder.course_imagurl_).execute(urlperson);
        //Toast.makeText(context.getApplicationContext(),singleItem.get(0).subcoursetitle_en, Toast.LENGTH_SHORT).show();

        if(b){
            holder.btn_kharid_.setVisibility(View.GONE);
            holder.btn_kharid_ok_.setVisibility(View.VISIBLE);
            holder.btn_kharid_ok_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context.getApplicationContext(), "سلام دوره"+String.valueOf(position).toString() , Toast.LENGTH_SHORT).show();
                    Intent I = new Intent(context.getApplicationContext(),CoursePageTwo.class);
                    // I.putExtra(“value1” , “This value for Next Activity”);
                    //I.putExtra(“value2” , “This value for Next Activity”);
                    context.startActivity(I);
                }


            });

        }
        else{
            holder.btn_kharid_.setVisibility(View.VISIBLE);
            holder.btn_kharid_ok_.setVisibility(View.GONE);
            holder.btn_kharid_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  Toast.makeText(context.getApplicationContext(), "سلام خرید"+String.valueOf(position).toString() , Toast.LENGTH_SHORT).show();







                }
            });
        }





/*
        holder.cardRecyclerView.setHasFixedSize(true);
        holder.cardRecyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        CourseSubRecyclerViewAdapter    courseSubRecyclerViewAdapter=new CourseSubRecyclerViewAdapter(context,singleItem);
        holder.cardRecyclerView.setAdapter(courseSubRecyclerViewAdapter);

//      final String  str=String.valueOf(singleItem.size());

        final CourseSubRecyclerViewAdapter itemInnerRecyclerView = new CourseSubRecyclerViewAdapter(context,singleItem);


//        holder.cardRecyclerView.setLayoutManager(new GridLayoutManager(context, 1));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    Toast.makeText(context.getApplicationContext(), "ابتدا این بسته را ثبت نام نمایید" + str , Toast.LENGTH_SHORT).show();
                if(sw==true){
                    holder.cardRecyclerView.setVisibility(View.GONE);
                    sw=false;
                }
                else{
                    holder.cardRecyclerView.setVisibility(View.VISIBLE);
                    sw=true;
                }
              }
        });
        holder.cardRecyclerView.setAdapter(itemInnerRecyclerView);

*/
/*

        VerticalModel   verticalModel=arrayList.get(i);
        String  title=verticalModel.getTitle();
        final ArrayList<HorizontalModel>  singleItem=verticalModel.getArrayList();



        holder.textViewTitle.setText(title);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        HorizontalRecyclerViewAdapter    horizontalRecyclerViewAdapter=new HorizontalRecyclerViewAdapter(context,singleItem);
        holder.recyclerView.setAdapter(horizontalRecyclerViewAdapter);
        //^^^^^^^^^^^^^^^^^^^^^^^^^^
        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<singleItem.size();i++)
                    Toast.makeText(context,singleItem.get(i).getName(),Toast.LENGTH_SHORT).show();
            }
        });
*/








    }

    @Override
    public int getItemCount() {
        return CourseList.size();
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
