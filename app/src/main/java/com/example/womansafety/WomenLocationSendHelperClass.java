package com.example.womansafety;

public class WomenLocationSendHelperClass {
    String title_send, message_send;

    public String getTitle_send() {
        return title_send;
    }

    public void setTitle_send(String title_send) {
        this.title_send = title_send;
    }

    public String getMessage_send() {
        return message_send;
    }

    public void setMessage_send(String message_send) {
        this.message_send = message_send;
    }

    public WomenLocationSendHelperClass(String title_send, String message_send) {
        this.title_send=title_send;
        this.message_send=message_send;
    }
}
