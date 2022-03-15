package com.youle.springboot_mongo_redis;

public class Response {

    private boolean isSuccess;
    private User user;

    public Response() {
    }

    public Response(boolean isSuccess, User user) {
        this.isSuccess = isSuccess;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Response{" +
                "isSuccess=" + isSuccess +
                ", user=" + user +
                '}';
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
