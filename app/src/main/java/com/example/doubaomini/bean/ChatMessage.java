package com.example.doubaomini.bean;

public class ChatMessage {
    public static final int TYPE_LEFT = 0;
    public static final int TYPE_RIGHT = 1;


    private int type;
    private String content;


    public ChatMessage(int type, String content) {
        this.type = type;
        this.content = content;
    }


    public int getType() { return type; }
    public String getContent() { return content; }
}
