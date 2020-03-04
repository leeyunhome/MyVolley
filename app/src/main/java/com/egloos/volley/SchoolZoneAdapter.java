package com.egloos.volley;

import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SchoolZoneAdapter extends RecyclerView.Adapter<SchoolZoneAdapter.ViewHolder>{
    ArrayList<SchoolZone> items = new ArrayList<SchoolZone>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.schoolzone_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SchoolZone item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    public void addItem(SchoolZone item){
        items.add(item);
    }

    public void setItems(ArrayList<SchoolZone> items){
        this.items = items;
    }

    public SchoolZone getItem(int position){
        return items.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        TextView textView3;

        public ViewHolder(View itemView){
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
        }

        public void setItem(SchoolZone item){
            textView.setText("관리기관명:"+item.MANAGE_NM);
            textView2.setText("관할경찰서: " + item.MANAGE_POL);
            textView3.setText("관리시설명: " + item.NM);
        }
    }

}
