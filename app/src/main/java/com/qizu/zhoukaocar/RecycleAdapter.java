package com.qizu.zhoukaocar;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import java.util.List;

/**
 * Created by jiuhao on 2017/11/18.
 * data 2017/11/18
 * time 上午 10:30
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private List<String> mDatas;
    private Context context;
    private LayoutInflater inflater;
    private SparseArray<Integer> heightArray;

    public RecycleAdapter(Context context , List<String> mDatas){
        this.context = context;
        this.mDatas = mDatas;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        heightArray = new SparseArray<Integer>();
        View itemView = inflater.inflate(R.layout.item_recycle,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if (heightArray.get(position) == null){
            Glide.with(context)
                    .load(mDatas.get(position))
                    .asBitmap()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {

                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                            // Do something with bitmap here.
                            int height = bitmap.getHeight(); //获取bitmap信息，可赋值给外部变量操作，也可在此时行操作。
                            bitmap.getWidth();
                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.iv.getLayoutParams();
                            layoutParams.height = height;
                            holder.iv.setLayoutParams(layoutParams);
                            heightArray.put(position,height);
                        }
                    });
        }else {
            int height = heightArray.get(position);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.iv.getLayoutParams();
            layoutParams.height = height;
            holder.iv.setLayoutParams(layoutParams);
        }

        Glide.with(context)
                .load(mDatas.get(position))
                .into(holder.iv);
    }
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.ivImage);
        }
    }
}
