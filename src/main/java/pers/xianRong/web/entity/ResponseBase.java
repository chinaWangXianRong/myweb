package pers.xianRong.web.entity;

import java.io.Serializable;

/**
 * Created by user on 2016/12/16.
 */
public class ResponseBase implements Serializable{
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
