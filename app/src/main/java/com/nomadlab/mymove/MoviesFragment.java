package com.nomadlab.mymove;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nomadlab.mymove.adapter.YtsAdapter;
import com.nomadlab.mymove.repository.interfaces.YtsService;
import com.nomadlab.mymove.repository.models.Movie;
import com.nomadlab.mymove.repository.models.YtsData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;

    private MoviesFragment() {
    }

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        recyclerView = view.findViewById(R.id.moviesContainer);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

        rvDataSetting();
        return view;
    }

    private void rvDataSetting(){
        final YtsAdapter adapter = new YtsAdapter();

        //레트로핏
        YtsService movieService = YtsService.retrofit.create(YtsService.class);
        Call<YtsData> call = movieService.repoContributors("rating", 40, 1);
        call.enqueue(new Callback<YtsData>() {
            @Override
            public void onResponse(Call<YtsData> call,
                                   Response<YtsData> response) {
                YtsData yts = response.body();

                adapter.addItems(yts.getData().getMovies());
                recyclerView.setAdapter(adapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }

            @Override
            public void onFailure(Call<YtsData> call, Throwable t) {
                Log.d("TAG","request api error");
            }
        });
    }
}