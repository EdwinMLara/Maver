package com.example.emlar.maver;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emlar.maver.Models.SuperHero;

import java.util.ArrayList;


public class HeroGridFragment extends Fragment {
    private static final String TAG = HeroListFragment.class.getSimpleName();
    private static final String HERO_DETAIL_FRAGMENT = "hero_detail_fragment";
    public static final String SUPER_HERO = "super_hero";
    RecyclerView recyclerView;
    ArrayList<SuperHero> superHeros;

    public HeroGridFragment() {
        // Required empty public constructor
    }

    public interface  HeroClickListener{

        void onHeroClick(SuperHero superHero);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Log.d(TAG,"Se ha creado un listfragment");

        Bundle bundle = getArguments();

        if (getArguments() != null){
            superHeros = bundle.getParcelableArrayList(MainActivity.HERO_LIST);
            //Toast.makeText(getContext(), "El primer super Heroe: " + superHeros.get(0), Toast.LENGTH_SHORT).show();
        }else {
            Log.d(TAG, "no hay superHeroes");
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hero_grid,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.superHeroesReciclerView);

        HeroGridAdapter heroGridAdapter = new HeroGridAdapter(getContext(), superHeros, new HeroGridFragment.HeroClickListener() {
            @Override
            public void onHeroClick(SuperHero superHero) {
                gotoHeroDetailFragment(superHero);
            }
        });

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int numColumn = (int) (dpWidth/200);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),numColumn));

        recyclerView.setAdapter(heroGridAdapter);

        return view;
    }

    private void gotoHeroDetailFragment(SuperHero superHero) {
        //Toast.makeText(getContext(),"Hero click",Toast.LENGTH_LONG).show();

        HeroDetailFragment heroDetailFragment = new HeroDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(SUPER_HERO,superHero);
        heroDetailFragment.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.placeHolder,heroDetailFragment,HERO_DETAIL_FRAGMENT);
        fragmentTransaction.addToBackStack(HERO_DETAIL_FRAGMENT);
        fragmentTransaction.commit();
    }

}
