package com.example.emlar.maver;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.emlar.maver.API.MarverClassService;
import com.example.emlar.maver.Models.Basic;
import com.example.emlar.maver.Models.Data;
import com.example.emlar.maver.Models.SuperHero;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private static final String FRAGMENT_HERO_LIST = "fragment_hero_list";
    public static final int SUCCESS_CODE = 200;

    private FrameLayout frameLayout;
    private ArrayList<SuperHero> superHeroes;
    public static final int  AVENGER_COMIC_ID = 354;
    public static final String HERO_LIST = "hero_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = (FrameLayout) findViewById(R.id.placeHolder);

        getHeroList();

    }

    private void getHeroList(){
        retrofit2.Call<Basic<Data<ArrayList<SuperHero>>>> superHerosCall = MarverClassService.getMarvelApi().getHeros(AVENGER_COMIC_ID);
        superHerosCall.enqueue(new Callback<Basic<Data<ArrayList<SuperHero>>>>() {
            @Override
            public void onResponse(retrofit2.Call<Basic<Data<ArrayList<SuperHero>>>> call, Response<Basic<Data<ArrayList<SuperHero>>>> response) {

                if (response.code() == SUCCESS_CODE) {

                    superHeroes = response.body().getData().getResults();
                    //Toast.makeText(MainActivity.this, "Hero name: " + superHeroes.get(0).getThumbnails().getFullPath(), Toast.LENGTH_SHORT).show();

                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(HERO_LIST,superHeroes);

                    FragmentManager fragmentManager = getSupportFragmentManager();

                    HeroListFragment savedFragment = (HeroListFragment) fragmentManager.findFragmentByTag(FRAGMENT_HERO_LIST);

                    if (savedFragment == null){
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        HeroListFragment heroListFragment = new HeroListFragment();
                        heroListFragment.setArguments(bundle);
                        fragmentTransaction.add(R.id.placeHolder, heroListFragment, FRAGMENT_HERO_LIST);
                        fragmentTransaction.commit();
                    }

                }else{
                    displayErrorMessage(getString(R.string.error_servidor));
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Basic<Data<ArrayList<SuperHero>>>> call, Throwable t) {
                //Toast.makeText(MainActivity.this, "Fail response", Toast.LENGTH_SHORT).show();
                displayErrorMessage(getString(R.string.network_error_msj));
            }
        });
    }

    public void displayErrorMessage(String mensaje){
        Snackbar snackbar = Snackbar.make(frameLayout,mensaje,Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.Ok), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getHeroList();
                    }
                });
        snackbar.show();
    }
}
