package com.osp.osplab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.osp.osplab.adapters.RecyclerAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class NewsDetailActivity extends AppCompatActivity {

    private ImageView mImage,mLink;
    private TextView mTitle;
    private TextView mDate;
    private TextView mAuthor;
    private TextView mContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        String title = getIntent().getStringExtra("title");
        String date = getIntent().getStringExtra("date");
        String image = getIntent().getStringExtra("image");
        String author = getIntent().getStringExtra("author");
        String content = getIntent().getStringExtra("content");
        final String link = getIntent().getStringExtra("link");

        mImage = findViewById(R.id.detail_image);
        mTitle = findViewById(R.id.detail_title);
        mDate = findViewById(R.id.detail_date);
        mAuthor = findViewById(R.id.detail_author);
        mContent = findViewById(R.id.detail_description);
        mLink = findViewById(R.id.detail_link_image);

        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(this)
                .setDefaultRequestOptions(defaultOptions)
                .load(image)
                .into((mImage));
        mTitle.setText(title);
        mDate.setText(date);
        mAuthor.setText(author);
        mContent.setText(content);
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewsDetailActivity.this, WebviewActivity.class);
                intent.putExtra("link", link);
                startActivity(intent);
            }
        });
    }
}

