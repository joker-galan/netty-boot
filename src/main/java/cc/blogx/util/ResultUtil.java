package cc.blogx.util;

/**
 * @author XueYuan
 * @since 2019-03-23 00:19
 */
public class ResultUtil {
    private boolean success;
    private String code;
    private String msg;
    private String content;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static ResultUtil getSuccess() {
        ResultUtil res = new ResultUtil();
        res.setSuccess(true);
        res.setCode("100");
        res.setMsg("请求成功！");
        return res;
    }

    public static ResultUtil getFail() {
        ResultUtil res = new ResultUtil();
        res.setSuccess(false);
        res.setCode("250");
        res.setMsg("请求失败！");
        return res;
    }
}
