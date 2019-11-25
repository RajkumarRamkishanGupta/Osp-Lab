package com.osp.osplab.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.osp.osplab.NewsDetailActivity;
import com.osp.osplab.R;
import com.osp.osplab.models.News;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private News mNicePlaces = new News();
    private Context mContext;

    public RecyclerAdapter(Context context, News nicePlaces) {
        mNicePlaces = nicePlaces;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        ((ViewHolder) viewHolder).mTitle.setText(mNicePlaces.getArticles().get(i).getTitle());
        ((ViewHolder) viewHolder).mDate.setText(mNicePlaces.getArticles().get(i).getPublishedAt());

        // Set the image
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(mNicePlaces.getArticles().get(i).getUrlToImage())
                .into(((ViewHolder) viewHolder).mImage);

        ((ViewHolder) viewHolder).mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                intent.putExtra("title", mNicePlaces.getArticles().get(i).getTitle());
                intent.putExtra("date", mNicePlaces.getArticles().get(i).getPublishedAt());
                intent.putExtra("image", mNicePlaces.getArticles().get(i).getUrlToImage());
                intent.putExtra("author", mNicePlaces.getArticles().get(i).getAuthor());
                intent.putExtra("content", mNicePlaces.getArticles().get(i).getContent());
                intent.putExtra("link", mNicePlaces.getArticles().get(i).getUrl());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNicePlaces.getArticles().size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImage;
        private TextView mTitle;
        private TextView mDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.item_image);
            mTitle = itemView.findViewById(R.id.item_title);
            mDate = itemView.findViewById(R.id.item_date);
        }
    }
}
