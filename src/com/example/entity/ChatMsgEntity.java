package com.example.entity;

public class ChatMsgEntity {
    private static final String TAG = ChatMsgEntity.class.getSimpleName();
    //�ļ�����
    private String path;
    //����
    private String date;
    //��������
    private String text;
    //�Ƿ�Ϊ�Է���������Ϣ
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