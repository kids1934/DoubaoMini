package com.example.doubaomini.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.doubaomini.R;
import com.example.doubaomini.activity.MainActivity;
import com.example.doubaomini.adapter.ChatAdapter;
import com.example.doubaomini.bean.ChatMessage;
import com.example.doubaomini.databinding.FragmentBaseHomeBinding;
import com.example.doubaomini.view.LoginDialog;
import com.example.doubaomini.viewmodel.BaseHomeVM;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 对话页面基础Fragment
 *
 * @author lisizeng
 * date: 2025/11/XX
 */
public class BaseHomeFragment extends Fragment {

    private FragmentBaseHomeBinding fragmentBaseHomeBinding;
    private BaseHomeVM baseHomeVM;

    private ChatAdapter chatAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentBaseHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_base_home,
                container, false);
        return fragmentBaseHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity activity = (MainActivity)requireActivity();
        baseHomeVM = activity.baseHomeVM;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setStackFromEnd(true);
        fragmentBaseHomeBinding.chatView.setLayoutManager(layoutManager);
        chatAdapter = new ChatAdapter(new ArrayList<>());
        fragmentBaseHomeBinding.chatView.setAdapter(chatAdapter);
        initObserver();
        initListener();
    }

    private void initListener(){
        fragmentBaseHomeBinding.btnSend.setOnClickListener(v -> {
            if(fragmentBaseHomeBinding.etMessage.getText() == null
                    || fragmentBaseHomeBinding.etMessage.getText().toString().isEmpty()){
                Toast.makeText(getActivity(), "输入内容不可以为空", Toast.LENGTH_SHORT).show();
                return;
            }
            String mes = fragmentBaseHomeBinding.etMessage.getText().toString();
            List<ChatMessage> currentMessages = baseHomeVM.message.getValue();
            if (currentMessages == null) {
                currentMessages = new ArrayList<>();
            }
            List<ChatMessage> newMessages = new ArrayList<>(currentMessages);
            newMessages.add(new ChatMessage(ChatMessage.TYPE_RIGHT, mes));
            baseHomeVM.message.setValue(newMessages);
            fragmentBaseHomeBinding.etMessage.setText("");
        });

        fragmentBaseHomeBinding.login.setOnClickListener(v -> {
            new LoginDialog.Builder(requireActivity(), true).create().show();
        });

        fragmentBaseHomeBinding.register.setOnClickListener(v -> {
            new LoginDialog.Builder(requireActivity(), false).create().show();
        });
    }

    private void initObserver(){
        baseHomeVM.message.observe(getViewLifecycleOwner(), newMessages -> {
            // 数据变化时，通知 Adapter 更新
            chatAdapter.setData(newMessages);
            // 滚动到最新一条消息
            fragmentBaseHomeBinding.chatView.scrollToPosition(newMessages.size() - 1);
        });
    }
}