package com.example.freelance.ui.messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.freelance.R;

public class MessagesFragment extends Fragment {

    MessagesViewModel messagesViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_messages, container, false);
        messagesViewModel = ViewModelProviders.of(this).get(MessagesViewModel.class);
        final TextView textView = view.findViewById(R.id.text_message);
        messagesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
        return view;
    }

    public static MessagesFragment newInstance() {
        return new MessagesFragment();
    }
}
