package com.example.webwerks.neostore.signUp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user_msg")
    @Expose
    private String userMsg;

    public Example(int id, int role_id, String first_name,
                   String last_name, String email, String username,
                   Object profile_pic, Object country_id, String gender,
                   String phone_no, Object dob, boolean is_active, String created,
                   String modified, String access_token, String message, String user_msg) {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserMsg() {
        return userMsg;
    }

    public void setUserMsg(String userMsg) {
        this.userMsg = userMsg;
    }

    @Override
    public String toString() {
        return "Example{" +
                "status=" + status +
                ", data=" + data +
                ", message='" + message + '\'' +
                ", userMsg='" + userMsg + '\'' +
                '}';
    }
}


