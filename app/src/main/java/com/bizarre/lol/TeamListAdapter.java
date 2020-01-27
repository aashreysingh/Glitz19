package com.bizarre.lol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import de.hdodenhof.circleimageview.CircleImageView;


public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.ViewHolder> {

    private  Context mcontext;
    private String[] Names, Details, Photos, Branch;
    private ImageLoader imageLoader;
    private String imgurl;

    public TeamListAdapter(Context context, String[] names, String[] details, String[] photos, String[] branch){

        this.mcontext = context;
        this.Names = names;
        this.Details = details;
        this.Photos = photos;
        this.Branch = branch;

    }


    @NonNull
    @Override
    public TeamListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_list_row, parent, false);
        TeamListAdapter.ViewHolder vholder = new TeamListAdapter.ViewHolder(view);
        return vholder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        setupImageLoader();
        imageLoader = ImageLoader.getInstance();

        String name = Names[position];
        String details = Details[position];
        String photo = Photos[position];
        String branch = Branch[position];

        holder.nametv.setText(name);
        holder.detailtv.setText(details);
        holder.branchtv.setText(branch);

        //Context c = mcontext.getApplicationContext();
        //int id = c.getResources().getIdentifier("drawable/"+photo, null, c.getPackageName());

        //holder.displayiv.setImageResource(id);

        imgurl = "drawable://" + (mcontext.getResources().getIdentifier("@drawable/"+photo, "drawable", mcontext.getApplicationContext().getPackageName()));;

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true).build();
        imageLoader.displayImage(imgurl, holder.displayiv, options);

        //if(position==getItemCount()-1)
           // holder.teamdivider.setVisibility(View.INVISIBLE);

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
    public int getItemCount() {

        return this.Names.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nametv, detailtv, branchtv;
        CircleImageView displayiv;

        View teamdivider;

        public ViewHolder(View itemView) {
            super(itemView);

            nametv = itemView.findViewById(R.id.name);
            detailtv = itemView.findViewById(R.id.detail);
            displayiv = itemView.findViewById(R.id.display);
            teamdivider = itemView.findViewById(R.id.view);
            branchtv = itemView.findViewById(R.id.branch);

        }
    }

}



