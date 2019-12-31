package com.example.freelance.ui.visits;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.freelance.R;
import com.example.freelance.app.App;
import com.example.freelance.data.model.Visit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VisitsAdapter extends RecyclerView.Adapter<VisitsAdapter.VisitsViewHolder> {

    List<Visit> visits;

    public interface OnItemClickListener {

        void onItemClick(Visit visit);
    }
    private final OnItemClickListener listener;

    public VisitsAdapter(List<Visit> visits, OnItemClickListener listener){
        this.visits = visits;
        this.listener = listener;
    }
    @NonNull
    @Override
    public VisitsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(App.getContext()).inflate(R.layout.visits_item, parent, false);
        return new VisitsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitsViewHolder holder, int position) {
        Visit visit = visits.get(position);
        holder.contactText.setText(visit.getContact());
        holder.territoryText.setText(visit.getTerritory());
        Glide.with(App.getContext()).load(visit.getContactPicture()).centerCrop()
                .placeholder(R.drawable.ic_profile_24dp).into(holder.contactImage);
        if (visit.getActualAt()!=null){
            holder.visit_state.setImageResource(R.drawable.ic_check_circle);
        }else {
            holder.visit_state.setImageResource(R.drawable.ic_forward);
        }
        holder.visit_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(visit);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (visits == null)
        return 0;
        return visits.size();
    }

    public class VisitsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.contact_imageView)
        ImageView contactImage;
        @BindView(R.id.contact_textView)
        TextView contactText;
        @BindView(R.id.territory_textView)
        TextView territoryText;
        @BindView(R.id.visit_state)
        ImageView visit_state;
        public VisitsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
