package com.example.lexuanchinh.newsarticlesearch.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lexuanchinh.newsarticlesearch.R;
import com.example.lexuanchinh.newsarticlesearch.model.Doc;
import com.example.lexuanchinh.newsarticlesearch.model.ListSearch;
import com.example.lexuanchinh.newsarticlesearch.util.APIService;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterListSearch extends RecyclerView.Adapter<AdapterListSearch.ViewHolder> {
    List<Doc> data;
    Context context;
    private IClickListener listener;
    public AdapterListSearch(Context ctx) {
        this.context = ctx;
    }
    public void setData(List<Doc> data) {
        this.data = data;
        notifyDataSetChanged();
    }
    public void setListener(IClickListener listener) {
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listsearch,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Doc doc = data.get(i);
        if(doc!=null){
            viewHolder.txtMain.setText( doc.getHeadline().getMain());
            if(doc.getMultimedia().size()>0){
//                Glide.with(context).load(APIService.BASE_IMAGE+doc.getMultimedia().get(0).getUrl())
//                        .into(viewHolder.thumb_image);
                Picasso.with(context).load(APIService.BASE_IMAGE+doc.getMultimedia().get(0).getUrl())
                                    .into(viewHolder.thumb_image);

            }
            else{
                Glide.with(context).load("https://images-na.ssl-images-amazon.com/images/I/51lbBitzv-L._SX342_QL70_.jpg")
                        .into(viewHolder.thumb_image);
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public interface IClickListener {
        void onItemClick(Doc docs);
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//        public TextView txtMain;
//       public ImageView thumb_image;
        @BindView(R.id.txtMain) TextView txtMain;
        @BindView(R.id.img_Thumbnail) ImageView thumb_image;
        public ViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this,itemView);
//            txtMain=itemView.findViewById(R.id.txtMain);
//            thumb_image=itemView.findViewById(R.id.img_Thumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(data.get(getAdapterPosition()));
        }
    }
    }