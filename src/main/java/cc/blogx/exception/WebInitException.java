package cc.blogx.exception;

import cc.blogx.enums.ExceptionEnum;

/**
 * web请求路径映射异常
 *
 * @author XueYuan.
 * @since 2019-03-30 09:16.
 */
public class WebInitException extends Exception {

    private String msg;
    private String code;

    public WebInitException(ExceptionEnum ex) {
        this.code = ex.getCode();
        this.msg = ex.getMsg();
    }

}
