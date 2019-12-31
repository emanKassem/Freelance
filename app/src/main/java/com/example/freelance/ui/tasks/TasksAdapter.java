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
import com.example.freelance.data.model.Visit;
import com.example.freelance.ui.visits.VisitsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {

    List<Task> tasks;

    public interface OnItemClickListener {

        void onItemClick(Task task);
    }
    private final OnItemClickListener listener;

    public TasksAdapter(List<Task> tasks, OnItemClickListener listener){
        this.tasks = tasks;
        this.listener = listener;
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
        if(task.getFromUserPicture()!=null)
            Glide.with(App.getContext()).load(task.getFromUserPicture()).centerCrop()
                    .placeholder(R.drawable.ic_profile_24dp).into(holder.userImage);
        holder.title.setText(task.getTitle());
        holder.createdAt.setText("Created At: ".concat(task.getCreatedAt()));
        holder.dueDate.setText("Due Date: ".concat(task.getDueDate()));
        if (task.getCompletedAt()==null){
            holder.state.setImageResource(R.drawable.ic_forward);
        }else {
            holder.state.setImageResource(R.drawable.ic_check_circle);
        }
        holder.state.setOnClickListener(view -> {
            listener.onItemClick(task);
        });
    }

    @Override
    public int getItemCount() {
        if (tasks==null)
        return 0;
        return tasks.size();
    }

    public class TasksViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.user_imageView)
        CircleImageView userImage;
        @BindView(R.id.title_textView)
        TextView title;
        @BindView(R.id.created_at_textView)
        TextView createdAt;
        @BindView(R.id.due_date_textView)
        TextView dueDate;
        @BindView(R.id.visit_state)
        ImageView state;
        public TasksViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
