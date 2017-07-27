package com.example.damasofc.fruitworld2.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.damasofc.fruitworld2.Activities.MainActivity;
import com.example.damasofc.fruitworld2.Models.Fruit;
import com.example.damasofc.fruitworld2.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public List<Fruit> frutas;
    private int layout;
    private OnItemClickListener itmClickListener;
    private Context context;

    public MyAdapter(List<Fruit> frutas,int layout, OnItemClickListener itmClickListener){
        this.frutas = frutas;
        this.layout = layout;
        this.itmClickListener = itmClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(frutas.get(position),itmClickListener);
    }

    @Override
    public int getItemCount() {
        return frutas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        public ImageView imgBack;
        public TextView fruitName;
        public TextView fruitDetails;
        public TextView cantFruits;
        public ViewHolder(View itemView) {
            super(itemView);
            imgBack = (ImageView) itemView.findViewById(R.id.imgBackground);
            fruitName = (TextView) itemView.findViewById(R.id.nameFruit);
            fruitDetails = (TextView) itemView.findViewById(R.id.detailsFruit);
            cantFruits = (TextView) itemView.findViewById(R.id.cantFruit);
            itemView.setOnCreateContextMenuListener(this);
        }
        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            MenuItem delete = contextMenu.add(Menu.NONE,1,1,"Borrar");
            MenuItem reset = contextMenu.add(Menu.NONE,2,2,"Resetear");
            delete.setOnMenuItemClickListener(change);
            reset.setOnMenuItemClickListener(change);
            contextMenu.setHeaderTitle(frutas.get(getPosition()).getName());
        }
        private MenuItem.OnMenuItemClickListener change = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case 1:
                        frutas.remove(getPosition());
                        notifyItemRemoved(getPosition());
                        return true;
                    case 2:
                        frutas.get(getPosition()).resetCant();
                        notifyDataSetChanged();
                        return true;
                    default:
                        return false;
                }
            }
        };

        public void bind(final Fruit fruta,final OnItemClickListener listener){
            Picasso.with(context).load(fruta.getImgBackground()).fit().into(imgBack);
            fruitName.setText(fruta.getName());
            fruitDetails.setText(fruta.getDetails());
            cantFruits.setText(String.valueOf(fruta.getCantidad()));
            imgBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fruta.addCant();
                    notifyDataSetChanged();
                    if(fruta.getCantidad() == 10){
                        cantFruits.setTextColor(ContextCompat.getColor(context,R.color.colorAccent));
                    }
                    notifyDataSetChanged();
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(fruta, getPosition());
                }
            });
        }
    }
    public	interface	OnItemClickListener	{
        void onItemClick(Fruit fruit,	int	position);
    }
}
