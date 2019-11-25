package com.osp.osplab;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.osp.osplab.adapters.RecyclerAdapter;
import com.osp.osplab.databinding.ActivityNewsBinding;
import com.osp.osplab.models.News;
import com.osp.osplab.viewmodels.NewsActivityViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NewsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private NewsActivityViewModel mNewsActivityViewModel;
    ActivityNewsBinding  binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
       binding = DataBindingUtil.setContentView(this, R.layout.activity_news);

        mNewsActivityViewModel = ViewModelProviders.of(this).get(NewsActivityViewModel.class);

        mNewsActivityViewModel.init();

        mNewsActivityViewModel.getNicePlaces().observe(this, new Observer<News>() {
            @Override
            public void onChanged(News news) {
                initRecyclerView(news);
            }
        });


    }

    private void initRecyclerView(News news){
        mAdapter = new RecyclerAdapter(this, news);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(mAdapter);
    }
}
