package com.bizarre.lol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private Context mcontext;
    private String[] Event, Time, Venue;

    public ScheduleAdapter(Context context, String[] eventlist, String[] timelist, String[] venuelist) {

        this.mcontext = context;
        this.Event = eventlist;
        this.Time = timelist;
        this.Venue = venuelist;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_row, parent, false);
        ScheduleAdapter.ViewHolder vh = new ScheduleAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String eventitem = Event[position];
        String timeitem = Time[position];
        String venueitem = Venue[position];
        if(position<=1)
            holder.cardView.setVisibility(View.GONE);

        holder.event.setText(eventitem);
        holder.time.setText(timeitem);
        holder.venue.setText(venueitem.toUpperCase());

    }

    @Override
    public int getItemCount() {
        return Event.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView event, time, venue;

        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.schedule_row);
            event = itemView.findViewById(R.id.event_name);
            time = itemView.findViewById(R.id.time_tv);
            venue = itemView.findViewById(R.id.audi_no);

        }

    }
}