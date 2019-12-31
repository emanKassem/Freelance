package com.example.freelance.ui.tasks.taskItem;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.freelance.R;
import com.example.freelance.app.App;
import com.example.freelance.data.model.MessageDesc;
import com.example.freelance.data.model.Task;
import com.example.freelance.data.model.TaskDesc;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class TaskFragment extends Fragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.task_title_tv)
    TextView task_title_tv;
    @BindView(R.id.contact_imageView)
    CircleImageView contact_imageView;
    @BindView(R.id.from_user_tv)
    TextView from_user_tv;
    @BindView(R.id.created_at_tv)
    TextView created_at_tv;
    @BindView(R.id.task_desc_tv)
    TextView task_desc_tv;
    @BindView(R.id.feedback_tv)
    EditText feedback_tv;
    @BindView(R.id.feedback_submit)
    Button feedback_submit;
    TaskViewModel taskViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        ButterKnife.bind(this, view);
        toolbar.setTitle("Task");
        toolbar.setNavigationOnClickListener(views -> {
            getFragmentManager().popBackStack();
        });
        final ProgressDialog pd = ProgressDialog.show(App.getContext(), "", "Loading...", false, true);
        taskViewModel = ViewModelProviders.of(this, new TaskViewModelFactory()).get(TaskViewModel.class);
        taskViewModel.getTaskResult().observe(this ,viewResult ->
        {
            pd.dismiss();
            if (viewResult == null) {
                return;
            }
            if (viewResult.getError() != null) {
                showFailed(viewResult.getError());
            }
            if (viewResult.getSuccess() != null) {
                updateUiWithMessages((TaskDesc) viewResult.getSuccess());
            }
        });
        Task task = getArguments().getParcelable("task");
        taskViewModel.getTask(task.getId());
        return view;
    }

    private void updateUiWithMessages(TaskDesc task) {
        task_title_tv.setText(task.getTitle());
        task_desc_tv.setText(task.getDescription());
        Glide.with(App.getContext()).load(task.getFromUserPicture()).centerCrop()
                .placeholder(R.drawable.ic_profile_24dp).into(contact_imageView);
        from_user_tv.setText(task.getFromUser());
        created_at_tv.setText(task.getCreatedAt());
        if (task.getFeedback()!=null){
            feedback_tv.setText(task.getFeedback());
            feedback_submit.setVisibility(View.GONE);
        }

    }

    private void showFailed(Integer error) {
    }


}
