package com.nomadlab.mymove.repository.interfaces;

//https://yts.lt/api/v2/list_movies.json?limit=20&page=1&sort_by=rating

import com.nomadlab.mymove.repository.models.YtsData;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YtsService {
    @GET("list_movies.json")
    Call<YtsData> repoContributors(
            @Query("sort_by") String sort_by,
            @Query("limit") int limit,
            @Query("page") int page
    );

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://yts.lt/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
