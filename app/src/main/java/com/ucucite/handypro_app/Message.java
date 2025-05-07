package com.ucucite.handypro_app;

public class Message {
    private final String content;
    private final boolean isSentByUser;

    public Message(String content, boolean isSentByUser) {
        this.content = content;
        this.isSentByUser = isSentByUser;
    }

    public String getContent() {
        return content;
    }

    public boolean isSentByUser() {
        return isSentByUser;
    }
}