package com.example.doubaomini.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doubaomini.R;
import com.example.doubaomini.bean.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ChatMessage> list;


    public ChatAdapter(List<ChatMessage> list) {
        this.list = list != null ? list : new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    public void setData(List<ChatMessage> newMessageList) {
        this.list = newMessageList != null ? newMessageList : new ArrayList<>();
        notifyDataSetChanged(); // 通知列表刷新（或用更高效的局部刷新方法）
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ChatMessage.TYPE_LEFT) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_item_left, parent, false);
            return new LeftHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_item_right, parent, false);
            return new RightHolder(v);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage msg = list.get(position);
        if (holder instanceof LeftHolder) {
            ((LeftHolder) holder).txt.setText(msg.getContent());
        } else {
            ((RightHolder) holder).txt.setText(msg.getContent());
        }
    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }



    static class LeftHolder extends RecyclerView.ViewHolder {
        TextView txt;
        LeftHolder(View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txtMessage);
        }
    }


    static class RightHolder extends RecyclerView.ViewHolder {
        TextView txt;
        RightHolder(View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txtMessage);
        }
    }
}
