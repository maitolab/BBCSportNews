package com.vsee.bbcsportnews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vsee.bbcsportnews.R;
import com.vsee.bbcsportnews.activity.DetailActivity;
import com.vsee.bbcsportnews.data.ArticleObj;

import java.util.ArrayList;

/**
 * Created by Tien on 1/26/2018.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private ArrayList<ArticleObj> data;
    private Context context;

    public ArticleAdapter(ArrayList<ArticleObj> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_list_news, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ArticleObj article = data.get(position);
        Picasso.with(context)
                .load(article.getUrlToImage())
                .fit()
                .into(holder.icon);
        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());
        holder.time.setText(article.getDisplayTime());
        holder.layout.setOnClickListener(view -> goToNextActivity(position));
    }

    private void goToNextActivity(int position) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailActivity.ARTICLE_KEY, data.get(position));
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public void setData(ArrayList<ArticleObj> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView title;
        public TextView description;
        public TextView time;
        public RelativeLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.item_icon);
            title = itemView.findViewById(R.id.item_title);
            description = itemView.findViewById(R.id.item_description);
            time = itemView.findViewById(R.id.item_time);
            layout = itemView.findViewById(R.id.item_layout);
        }
    }
}
