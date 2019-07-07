package cc.blogx.service;

import cc.blogx.annotation.Router;
import cc.blogx.annotation.Mapping;
import cc.blogx.enums.RouterMethod;
import cc.blogx.exception.WebInitException;

@Router
@Mapping(url = "/test")
public class TestApi {



    @Mapping(url = "/aaa", method = RouterMethod.POST)
    public String test(String abc, WebInitException a) {
        return "123";
    }

}
