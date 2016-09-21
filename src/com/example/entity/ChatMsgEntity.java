package com.example.entity;

public class ChatMsgEntity {
    private static final String TAG = ChatMsgEntity.class.getSimpleName();
    //文件名字
    private String path;
    //日期
    private String date;
    //聊天内容
    private String text;
    //是否为对方发来的信息
    private boolean isComMeg = true;

    public String getPath() {
        return path;
    }

    public void setPath(String name) {
        this.path = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getMsgType() {
        return isComMeg;
    }

    public void setMsgType(boolean isComMsg) {
    	isComMeg = isComMsg;
    }

    public ChatMsgEntity() {
    }

    public ChatMsgEntity(String name, String date, String text, boolean isComMsg) {
        this.path = name;
        this.date = date;
        this.text = text;
        this.isComMeg = isComMsg;
    }
}