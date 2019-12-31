package com.example.freelance.ui.messages;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelance.R;
import com.example.freelance.app.App;
import com.example.freelance.data.model.Message;
import com.example.freelance.ui.login.ViewResult;
import com.example.freelance.ui.messages.messagesItem.MessageFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessagesFragment extends Fragment {

    MessagesViewModel messagesViewModel;
    @BindView(R.id.messages_recyclerview)
    RecyclerView messagesRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_messages, container, false);
        ButterKnife.bind(this, view);
        final ProgressDialog pd = ProgressDialog.show(App.getContext(), "", "Loading...", false, true);
        messagesViewModel = ViewModelProviders.of(this, new MessagesViewModelFactory()).get(MessagesViewModel.class);
        messagesViewModel.getMessagesResult().observe(this, new Observer<ViewResult>() {
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
                    updateUiWithMessages((List<Message>) viewResult.getSuccess());
                }
            }
        });
        messagesViewModel.getMessages();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        messagesRecyclerView.setLayoutManager(new LinearLayoutManager(App.getContext()));
    }

    private void updateUiWithMessages(List<Message> success) {
        messagesRecyclerView.setAdapter(new MessagesAdapter(success, message -> {
            MessageFragment messageFragment= new MessageFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("message", message);
            messageFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.drawer_layout, messageFragment, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        }));
    }


    private void showFailed(Integer error) {
        Toast.makeText(App.getContext(), "failed", Toast.LENGTH_LONG).show();
    }

    public static MessagesFragment newInstance() {
        return new MessagesFragment();
    }
}
