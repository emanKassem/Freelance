package com.example.freelance.ui.messages;

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
import com.example.freelance.data.model.Message;
import com.example.freelance.data.model.Visit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder> {

    List<Message> messages;

    public interface OnItemClickListener {

        void onItemClick(Message message);
    }
    private final OnItemClickListener listener;

    public MessagesAdapter(List<Message> messages, OnItemClickListener listener){
        this.messages = messages;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(App.getContext()).inflate(R.layout.visits_item, parent, false);
        return new MessagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.contactText.setText(message.getFromUser());
        holder.messageTitle.setText(message.getTitle());
        Glide.with(App.getContext()).load(message.getFromUserPicture()).centerCrop()
                .placeholder(R.drawable.ic_profile_24dp).into(holder.contactImage);
        holder.forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(message);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (messages == null)
        return 0;
        return messages.size();
    }

    public class MessagesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.contact_imageView)
        ImageView contactImage;
        @BindView(R.id.contact_textView)
        TextView contactText;
        @BindView(R.id.territory_textView)
        TextView messageTitle;
        @BindView(R.id.visit_state)
        ImageView forward;
        public MessagesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
