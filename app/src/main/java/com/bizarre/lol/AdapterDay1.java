package com.bizarre.lol;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;


public class AdapterDay1 extends RecyclerView.Adapter<AdapterDay1.ViewHolder> {


    private Context mcontext;
    private String[] Eventname;
    private String[] Venue;
    private String imgurl;
    private ImageLoader imageLoader;

    public AdapterDay1(Context context, String[] event, String[] venue) {

        this.mcontext = context;
        this.Eventname = event;
        this.Venue = venue;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_items, parent, false);
        ViewHolder vholder = new ViewHolder(view);

        return vholder;
    }

    @Override
    public void onBindViewHolder(ViewHolder vholder, final int position) {

        setupImageLoader();
        imageLoader = ImageLoader.getInstance();

        vholder.event_name.setText(this.Eventname[position]);
        vholder.venue.setText(this.Venue[position].toUpperCase());

        View view = vholder.itemView;
        if (position<=1) {
            view.setVisibility(View.INVISIBLE);
        } else {
            view.setVisibility(View.VISIBLE);

            imgurl = "drawable://" + (mcontext.getResources().getIdentifier("@drawable/item"+(position-1), "drawable", mcontext.getApplicationContext().getPackageName()));;

            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                    .cacheOnDisc(true).resetViewBeforeLoading(true).build();
            imageLoader.displayImage(imgurl, vholder.card, options);
        }

        vholder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mcontext, ExpandedScreen.class);

                intent.putExtra("pos", position + "");
                intent.putExtra("day", "1");


                mcontext.startActivity(intent);

            }
        });
    }

    private void setupImageLoader(){
        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mcontext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP
    }



    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public int getItemCount() {

        return Eventname.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView event_name, venue;
        RoundedImageView card;

        public ViewHolder(View itemView) {
            super(itemView);

            event_name =  itemView.findViewById(R.id.event);
            venue = itemView.findViewById(R.id.venue);

            card = itemView.findViewById(R.id.card_view);
        }

    }
}
