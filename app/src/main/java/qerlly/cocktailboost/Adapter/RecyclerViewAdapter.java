package qerlly.cocktailboost.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import qerlly.cocktailboost.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {

    List<Item> mItems = Collections.emptyList();
    OnItemClickListener mItemClickListener;
    Context mContext;

    public RecyclerViewAdapter(Context mContext, List<Item> mItems) {
        this.mContext = mContext;
        this.mItems = mItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.mName.setText(mItems.get(position).getName());
        holder.mImageView.setImageResource(R.drawable.logo);

        Bitmap photo = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.jej);

        Palette.generateAsync(photo, new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                int mutedLight = palette.getMutedColor(mContext.getResources().getColor(android.R.color.black));
                holder.mNameHolder.setBackgroundColor(mutedLight);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(holder.itemView, position);
                }
            }
        });
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
