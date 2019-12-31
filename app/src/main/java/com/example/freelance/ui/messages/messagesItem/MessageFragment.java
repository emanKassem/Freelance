package com.example.freelance.ui.messages.messagesItem;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.freelance.R;
import com.example.freelance.app.App;
import com.example.freelance.data.model.Message;
import com.example.freelance.data.model.MessageDesc;
import com.example.freelance.ui.login.ViewResult;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MessageFragment extends Fragment {

    @BindView(R.id.message_title_tv)
    TextView messageTitleTV;
    @BindView(R.id.contact_imageView)
    CircleImageView contactImage;
    @BindView(R.id.from_user_tv)
    TextView fromUserTV;
    @BindView(R.id.created_at_tv)
    TextView createdAtTV;
    @BindView(R.id.message_tv)
    TextView messageTV;
    @BindView(R.id.add_reply_FAB)
    FloatingActionButton add_reply_FAB;
    @BindView(R.id.replies_recyclerView)
    RecyclerView replies_recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    MessageViewModel messageViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, view);
        toolbar.setTitle("Messages");
        toolbar.setNavigationOnClickListener(views -> {
            getFragmentManager().popBackStack();
        });
        final ProgressDialog pd = ProgressDialog.show(App.getContext(), "", "Loading...", false, true);
        messageViewModel = ViewModelProviders.of(this, new MessageViewModelFactory()).get(MessageViewModel.class);
        messageViewModel.getMessageResult().observe(this, new Observer<ViewResult>() {
            @Override
            public void onChanged(ViewResult viewResult) {
                pd.dismiss();
                if (viewResult == null) {
                    return;
                }
                if (viewResult.getError() != null) {
                    showFailed(viewResult.getError());
                }
                if (viewResult.getSuccess() != null) {
                    updateUiWithMessages((MessageDesc) viewResult.getSuccess());
                }
            }
        });
        Message message = getArguments().getParcelable("message");
        messageViewModel.getMessage(message.getId());
        return view;
    }

    private void updateUiWithMessages(MessageDesc messageDesc) {
        messageTitleTV.setText(messageDesc.getTitle());
        Glide.with(App.getContext()).load(messageDesc.getFromUserPicture()).centerCrop()
                .placeholder(R.drawable.ic_profile_24dp).into(contactImage);
        fromUserTV.setText(messageDesc.getFromUser());
        createdAtTV.setText(messageDesc.getCreatedAt());
        messageTV.setText(messageDesc.getMessage());
        if (messageDesc.getReplies()!=null){
            replies_recyclerView.setLayoutManager(new LinearLayoutManager(App.getContext()));
            replies_recyclerView.setAdapter(new RepliesAdapter(messageDesc.getReplies()));
        }
    }

    private void showFailed(Integer error) {
        Toast.makeText(App.getContext(), "failed", Toast.LENGTH_LONG).show();
    }
}
