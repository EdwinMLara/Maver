package com.example.emlar.maver.API;

import com.example.emlar.maver.Models.Basic;
import com.example.emlar.maver.Models.Data;
import com.example.emlar.maver.Models.SuperHero;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MarvelService {
    String BASE_URl = "https://gateway.marvel.com:443/";

    String API_KEY_KEY  = "apikey";
    String API_KEY_VALUE = "cc3703b905b4d1d9c9cab677674303c3";

    String TIME_STAMP_KEY = "ts";
    String TIME_STAMP_VALUE = "1";

    String HASH_KEY = "hash";
    String HASH_VALUE = "a6b3d1559da452c0a89f563ac05c0971";

    @GET("v1/public/series/{seriesId}/characters")
    Call<Basic<Data<ArrayList<SuperHero>>>> getHeros(@Path("seriesId") int seriesId);


    //Agreagar el query sirve para añadir un parametro get que no simpre sera ocupado

    @GET("v1/public/series/{seriesId}/characters")
    Call<Basic<Data<ArrayList<SuperHero>>>> getHeros(@Path("seriesId") int seriesId, @Query("orderBy") String sort);
}
