package com.example.emlar.maver;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emlar.maver.Models.SuperHero;
import com.squareup.picasso.Picasso;


public class HeroDetailFragment extends Fragment {

    SuperHero superHero;
    TextView heroNameTextView;
    TextView heroDescriptionTextView;
    ImageView heroImageView;

    public HeroDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            superHero = getArguments().getParcelable(HeroListFragment.SUPER_HERO);
            //Toast.makeText(getContext(),"Hero : " + superHero.getName(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hero_detail, container, false);

        heroNameTextView = (TextView) view.findViewById(R.id.heroDetailTitleTextView);
        heroDescriptionTextView = (TextView) view.findViewById(R.id.heroDetailDescriptionTextView);
        heroImageView = (ImageView) view.findViewById(R.id.heroDetailThumbnailTextView);

        heroNameTextView.setText(superHero.getName());

        if (superHero.getDescription() != null) {
            heroDescriptionTextView.setText(superHero.getDescription());
        }else {
            heroDescriptionTextView.setText(getString(R.string.No_informacion_msj));
        }

        Picasso.get().load(superHero.getThumbnails().getFullPath()).into(heroImageView);

        return view;
    }

}
