package com.reals.ahmadbahri_1202150111_modul6;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>{

    private List<PhotoModel> list;

    public PhotoAdapter(List<PhotoModel> list) {
        this.list = list;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        android.view.View view = layoutInflater.inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PhotoViewHolder holder, int position) {
        final PhotoModel item = list.get(position);
        holder.bindTo(item);
        holder.itemView.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                holder.toDetail(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder{
        private TextView lblUser, lblLike, lblTitle, lblCaption;
        private ImageView lblImage;
        private Context context;

        public PhotoViewHolder(android.view.View v) {
            super(v);

            context=v.getContext();
            lblUser=(TextView)v.findViewById(R.id.lblPhotoUsername);
            lblLike=(TextView)v.findViewById(R.id.lblPhotoLike);
            lblTitle=(TextView)v.findViewById(R.id.lblPhotoTitle);
            lblCaption=(TextView)v.findViewById(R.id.lblPhotoCaption);
            lblImage=(ImageView)v.findViewById(R.id.lblPhotoImage);
        }

        public void bindTo(PhotoModel model){
            PhotoModel curr = model;
            lblUser.setText(curr.getDisplayName());
            lblLike.setText(""+curr.getLike());
            lblTitle.setText(curr.getTitle());
            lblCaption.setText(curr.getCaption());
            //lblImage.setText(curr.getUser());
            Glide.with(context).load(curr.getImgUrl()).error(R.drawable.ic_broken_image_black_24dp).into(lblImage);
        }

        public void toDetail(PhotoModel model){
            Intent i = new Intent(context.getApplicationContext(),View.class);
            i.putExtra(View.EXTRA_ITEM, model);
            context.startActivity(i);
        }
    }
}
