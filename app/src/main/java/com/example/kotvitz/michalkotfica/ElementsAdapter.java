package com.example.kotvitz.michalkotfica;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ElementsAdapter extends RecyclerView.Adapter<ElementsAdapter.ViewHolder> {

    private final List<ElementInfo> elements;

    public ElementsAdapter(List<ElementInfo> elements) {
        this.elements = elements;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.content_main, viewGroup, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ElementInfo element = elements.get(position);
        TextView titleTextView = viewHolder.elementTitle;
        titleTextView.setText(element.getTitle());
        TextView descTextView = viewHolder.elementDescription;
        descTextView.setText(element.getDesc());
        ImageView imageView = viewHolder.elementImage;
        Glide.with(imageView.getContext()).load(element.getUrl()).into(imageView);
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView elementTitle;
        final TextView elementDescription;
        final ImageView elementImage;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            elementTitle = itemView.findViewById(R.id.element_title);
            elementDescription = itemView.findViewById(R.id.element_desc);
            elementImage = itemView.findViewById(R.id.element_image);
        }
    }
}
