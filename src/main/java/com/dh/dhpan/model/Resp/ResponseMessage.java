package com.dh.dhpan.model.Resp;


public class ResponseMessage {
    private int code;
    private String msg;
    private Object data;

    public static ResponseMessage success(String message) {
        ResponseMessage resp = new ResponseMessage();
        resp.code = 200;
        resp.msg = message;
        return resp;
    }
    public static ResponseMessage success(String message,String data) {
        ResponseMessage resp = new ResponseMessage();
        resp.code = 200;
        resp.msg = message;
        resp.data = data;
        return resp;
    }


    public static ResponseMessage success(String message,Integer ID) {
        ResponseMessage resp = new ResponseMessage();
        resp.code = 200;
        resp.msg = message;
        resp.data = ID;
        return resp;
    }

    public static ResponseMessage error(String message) {
        ResponseMessage resp = new ResponseMessage();
        resp.code = 500;
        resp.msg = message;
        return resp;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
