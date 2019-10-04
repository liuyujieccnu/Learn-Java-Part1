package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 */
//此处完成的消息实体模型，每个参数都要建立getter和setter
public class Message {
    // TODO: add message model.
    private String content;
    private String username;
    private int onlineCount;
    private String type;

    public Message() {
    }

    public Message(String username,String content,int onlineCount,String type) {
        this.username = username;
        this.content = content;
        this.onlineCount = onlineCount;
        this.type=type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }

}
