package com.example.freelance.ui.tasks;

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
import com.example.freelance.data.model.Task;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {

    List<Task> tasks;

    public TasksAdapter(List<Task> tasks){
        this.tasks = tasks;
    }
    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(App.getContext()).inflate(R.layout.tasks_item, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        Task task = tasks.get(position);
        if(task.getSendFromUserIcon()!=null)
            Glide.with(App.getContext()).load(task.getSendFromUserIcon()).centerCrop()
                    .placeholder(R.drawable.ic_profile_24dp).into(holder.userImage);
        holder.title.setText(task.getTitle());
        holder.createdAt.setText("Created At: ".concat(task.getCreatedAt()));
        holder.dueDate.setText("Due Date: ".concat(task.getDueDate()));

    }

    @Override
    public int getItemCount() {
        if (tasks==null)
        return 0;
        return tasks.size();
    }

    public class TasksViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.user_imageView)
        ImageView userImage;
        @BindView(R.id.title_textView)
        TextView title;
        @BindView(R.id.created_at_textView)
        TextView createdAt;
        @BindView(R.id.due_date_textView)
        TextView dueDate;
        public TasksViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
