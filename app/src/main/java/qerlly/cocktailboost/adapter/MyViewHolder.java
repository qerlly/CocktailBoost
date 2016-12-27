package qerlly.cocktailboost.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import qerlly.cocktailboost.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImageView;
    public TextView mName;
    public LinearLayout mMainHolder;
    public LinearLayout mNameHolder;

    public MyViewHolder(View itemView) {
        super(itemView);
        mImageView = (ImageView) itemView.findViewById(R.id.image_item);
        mName = (TextView) itemView.findViewById(R.id.name_item);
        mMainHolder = (LinearLayout) itemView.findViewById(R.id.main_holder);
        mNameHolder = (LinearLayout) itemView.findViewById(R.id.name_holder);
    }
}
