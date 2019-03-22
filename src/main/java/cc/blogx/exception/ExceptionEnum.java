package cc.blogx.exception;

/**
 * @author XueYuan
 * @since 2019-03-14 20:59
 */
public enum ExceptionEnum {;


    ExceptionEnum(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    private String msg;
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

