package cn.edu.nwsuaf.action;

import java.io.Serializable;

/**
 * Created by luxiaoping on 2016/8/14.
 */
public class JsonResult implements Serializable {
    private boolean success = false;
    private String msg = "";
    private Object object = null;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
