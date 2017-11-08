package cn.com.lyk.recordk.utils;

/**
 * Created by lyk on 2017/11/7.
 */

public class ResultMsg {

    /**
     * code : 200
     * message : 添加成功
     * data : null
     */

    private String code;
    private String message;
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
