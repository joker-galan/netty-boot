package cc.blogx.service;

import cc.blogx.annotation.Router;
import cc.blogx.annotation.RouterPath;
import cc.blogx.exception.WebInitException;

@Router
@RouterPath(value = "/test")
public class TestApi {

    @RouterPath(value = "/aaa")
    public String test(String abc, WebInitException a) {
        return "123";
    }

}
