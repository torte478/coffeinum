package com.example.torte.coffeimun2.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.torte.coffeimun2.FontHelper;
import com.example.torte.coffeimun2.ImageLoader;
import com.example.torte.coffeimun2.R;
import com.example.torte.coffeimun2.TorteMap;
import com.example.torte.coffeimun2.interfaces.RecyclerViewClickListener;
import com.example.torte.coffeimun2.model.CreateList;
import com.example.torte.coffeimun2.model.Menu;

import java.util.ArrayList;

/**
 * Created by dmitry on 11/11/17.
 */

public class CoffeHouseAdapter extends RecyclerView.Adapter<CoffeHouseAdapter.ViewHolder> {

        private ArrayList<Menu> galleryList;
        private Context context;
        private RecyclerViewClickListener mListener;

        public CoffeHouseAdapter(Context context, ArrayList<Menu> galleryList, RecyclerViewClickListener listener, AssetManager assets) {
            this.galleryList = galleryList;
            this.context = context;
            this.mListener = listener;
            this.assets = assets;
        }

        @Override
        public CoffeHouseAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_layout, viewGroup, false);
            return new ViewHolder(view, mListener);
        }

        @Override
        public void onBindViewHolder(CoffeHouseAdapter.ViewHolder viewHolder, int i) {
            viewHolder.title.setText(galleryList.get(i).name);
            viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.AddListener(galleryList.get(i).img, bitmap -> viewHolder.img.setImageBitmap(bitmap));

        }

        @Override
        public int getItemCount() {
            return galleryList.size();
        }

        private AssetManager assets;

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private TextView title;
            private ImageView img;
            public ViewHolder(View view, RecyclerViewClickListener listener) {
                super(view);
                mListener = listener;
                view.setOnClickListener(this);
                title = (TextView)view.findViewById(R.id.title);
                title.setTypeface(FontHelper.GetFont(assets));
                img = (ImageView) view.findViewById(R.id.img);
            }

            @Override
            public void onClick(View view) {
                mListener.onClick(view, galleryList.get(getAdapterPosition()));
            }
        }
}

