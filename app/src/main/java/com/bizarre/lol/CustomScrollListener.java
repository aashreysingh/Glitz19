package com.bizarre.lol;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import androidx.recyclerview.widget.RecyclerView;


public class CustomScrollListener extends RecyclerView.OnScrollListener {

    View imageview;

    public CustomScrollListener(View view) {

        this.imageview = view;

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newstate) {

        super.onScrollStateChanged(recyclerView, newstate);

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);



        //for Transparency Effect of the view
        if (recyclerView.computeHorizontalScrollOffset() != 0&&getAlphaforView(recyclerView)>=.1) {
            Animation anim = new TranslateAnimation(.25f * (0 - recyclerView.computeHorizontalScrollOffset()), .25f * (0 - recyclerView.computeHorizontalScrollOffset()), 0f, 0f);
            anim.setFillAfter(true);
            anim.setDuration(0);
            imageview.startAnimation(anim);
            imageview.setAlpha(getAlphaforView(recyclerView));

        } else if(recyclerView.computeHorizontalScrollOffset()==0) {
            imageview.setAlpha(1f);
        }

    }

    private float getAlphaforView(RecyclerView recyclerView) {

        return recyclerView.computeHorizontalScrollOffset() * (-1.4f / recyclerView.computeHorizontalScrollExtent()) + 1f;

    }

}
