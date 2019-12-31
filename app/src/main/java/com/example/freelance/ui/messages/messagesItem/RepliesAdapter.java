package com.example.freelance.ui.messages.messagesItem;

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
import com.example.freelance.data.model.Reply;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

class RepliesAdapter extends RecyclerView.Adapter<RepliesAdapter.RepliesViewHolder> {

    List<Reply> replies;
    public RepliesAdapter(List<Reply> replies) {
        this.replies = replies;
    }

    @NonNull
    @Override
    public RepliesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(App.getContext()).inflate(R.layout.reply_item, parent, false);
        return new RepliesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepliesViewHolder holder, int position) {
        Reply reply = replies.get(position);
        holder.replyMessageTV.setText(reply.getMessage());
        holder.replyUserTV.setText(reply.getFromUser());
        Glide.with(App.getContext()).load(reply.getFromUserPicture()).centerCrop()
                .placeholder(R.drawable.ic_profile_24dp).into(holder.contactImageView);
    }

    @Override
    public int getItemCount() {
        return replies.size();
    }

    public class RepliesViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.contact_imageView)
        CircleImageView contactImageView;
        @BindView(R.id.reply_user_tv)
        TextView replyUserTV;
        @BindView(R.id.reply_message_tv)
        TextView replyMessageTV;
        public RepliesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
