package com.sdk.feedback.model;

public class Message {
    private String date;
    private String userId;

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public String getDate() {
        return date;
    }

    public String getUserId() {
        return userId;
    }

    public String getComment() {
        return comment;
    }

    public String getImage() {
        return image;
    }

    public boolean isNew() {
        return isNew;
    }

    public boolean isSent() {
        return sent;
    }

    private int numberOfLikes;
    private String comment;
    private String image;
    private boolean isNew;

    public boolean sent;

    public Message() {
        this.sent = true;
    }

    public Message(String image, String userId, String dateTime, boolean isNew, String comment, int numberOfLikes) {
        this.image = image;
        this.userId = userId;
        this.date = dateTime;
        this.isNew = isNew;
        this.comment = comment;
        this.numberOfLikes = numberOfLikes;
    }
}


/* Location:              D:\apk decompilers\dex2jar-2.0\dex2jar-2.0\classes-dex2jar.jar!\com\cercaapp\data\Message.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */