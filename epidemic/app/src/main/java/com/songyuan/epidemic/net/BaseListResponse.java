package com.songyuan.epidemic.net;

import java.util.List;

public class BaseListResponse<T> {
    private int errorCode;
    private String message;
    private List<T> attachment;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public List<T> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<T> attachment) {
        this.attachment = attachment;
    }

}