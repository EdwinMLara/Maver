package com.example.emlar.maver;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emlar.maver.Models.SuperHero;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HeroGridAdapter extends RecyclerView.Adapter<HeroGridAdapter.MyViewHolder> {

    ArrayList<SuperHero> superHeroArrayList;
    Context context;
    HeroGridFragment.HeroClickListener heroClickListener;

    public HeroGridAdapter(Context context, ArrayList superHeroArrayList, HeroGridFragment.HeroClickListener heroClickListener){
        this.context = context;
        this.superHeroArrayList = superHeroArrayList;
        this.heroClickListener = heroClickListener;
    }

    @NonNull
    @Override
    public HeroGridAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hero_grid_item,parent,false);

        return new HeroGridAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroGridAdapter.MyViewHolder holder, int position) {

        SuperHero superHero = superHeroArrayList.get(position);
        holder.bind(context,superHero,heroClickListener);

    }

    @Override
    public int getItemCount() {
        return superHeroArrayList.size();
    }

    static public class MyViewHolder  extends RecyclerView.ViewHolder{
        public ImageView heroPictureimageView;
        public TextView heroDetailNameTextView;

        public MyViewHolder(View itemView) {
            super(itemView);

            heroPictureimageView = (ImageView) itemView.findViewById(R.id.heroPictureImageView);
            heroDetailNameTextView = (TextView) itemView.findViewById(R.id.heroDetailNameTextView);
        }

        public void bind(Context context, final SuperHero superHero, final HeroGridFragment.HeroClickListener heroClickListener){

            heroDetailNameTextView.setText(superHero.getName());
            Picasso.get().load(superHero.getThumbnails().getFullPath()).into(heroPictureimageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    heroClickListener.onHeroClick(superHero);
                }
            });
        }
    }
}